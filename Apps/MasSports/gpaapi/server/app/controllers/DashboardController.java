package controllers;

import modeles.BliveActivity;
import modeles.LearnLiveActivity;
import modeles.MaxgameActivity;
import modeles.PaxxionActivity;
import modeles.log;
import play.Configuration;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.dashboard.DashboardBuffer;
import views.html.dashboard_index;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Admin dashboard to inspect the latest tracked clicks, clicktosms requests,
 * claims and conversion calls (in-memory live trace + database history).
 * Protected with HTTP Basic auth handled natively by the browser
 * (credentials configured in application.conf).
 */
public class DashboardController extends Controller {

    private final Configuration configuration;
    private final DashboardBuffer buffer;

    @Inject
    public DashboardController(Configuration configuration) {
        this.configuration = configuration;
        this.buffer = DashboardBuffer.get();
    }

    public Result index() {
        if (!isAuthed()) {
            return unauthorized().withHeader(Http.HeaderNames.WWW_AUTHENTICATE, "Basic realm=\"dashboard\"");
        }
        List<DashboardBuffer.ConvTrace> convMem = buffer.lastConversions(100);
        List<DashboardBuffer.SmsTrace> smsMem = buffer.lastSms(200);
        List<log> smsDb = lastDbSmsClicks(200);
        List<log> convDb = lastDbConversions();

        List<log> smsDbShown = smsDb.size() > 100 ? smsDb.subList(0, 100) : smsDb;

        return ok(dashboard_index.render(
            buffer.lastClicks(100),
            lastDbClicks(),
            smsMem,
            toDbSmsRows(smsDbShown),
            correlateConvMem(convMem, smsMem),
            correlateConvDb(convDb, smsDb)
        ));
    }

    private boolean isAuthed() {
        String auth = request().getHeader(Http.HeaderNames.AUTHORIZATION);
        if (auth == null || !auth.startsWith("Basic ")) {
            return false;
        }
        try {
            String decoded = new String(Base64.getDecoder().decode(auth.substring(6).trim()), "UTF-8");
            int separator = decoded.indexOf(':');
            if (separator < 0) {
                return false;
            }
            String user = decoded.substring(0, separator);
            String pass = decoded.substring(separator + 1);
            return adminUser().equals(user) && adminPass().equals(pass);
        } catch (Exception e) {
            return false;
        }
    }

    private String adminUser() {
        return configuration.getString("dashboard.admin.user", "admin");
    }

    private String adminPass() {
        return configuration.getString("dashboard.admin.pass", "humbilumby_2026");
    }

    /**
     * Conversion (memory trace) correlated with the clicktosms request that
     * triggered it: the platform command, its time and the claimed click id.
     */
    public static class ConvRow {
        public final String time;
        public final String identifier;
        public final String business;
        public final String msisdn;
        public final String detail;
        public final String reqCommand;
        public final String reqTime;
        public final String claimedClickId;

        public ConvRow(String time, String identifier, String business, String msisdn, String detail,
                       String reqCommand, String reqTime, String claimedClickId) {
            this.time = time;
            this.identifier = identifier;
            this.business = business;
            this.msisdn = msisdn;
            this.detail = detail;
            this.reqCommand = reqCommand;
            this.reqTime = reqTime;
            this.claimedClickId = claimedClickId;
        }
    }

    /**
     * Conversion (log table) correlated with the SMS_CLICK log row of the same
     * msisdn, plus the provider id extracted from the postback url.
     */
    public static class DbConvRow {
        public final Long id;
        public final String time;
        public final String identifier;
        public final String business;
        public final String msisdn;
        public final String detail;
        public final String command;
        public final String commandTime;
        public final String providerId;

        public DbConvRow(Long id, String time, String identifier, String business, String msisdn, String detail,
                         String command, String commandTime, String providerId) {
            this.id = id;
            this.time = time;
            this.identifier = identifier;
            this.business = business;
            this.msisdn = msisdn;
            this.detail = detail;
            this.command = command;
            this.commandTime = commandTime;
            this.providerId = providerId;
        }
    }

    private List<ConvRow> correlateConvMem(List<DashboardBuffer.ConvTrace> convs, List<DashboardBuffer.SmsTrace> sms) {
        List<ConvRow> rows = new ArrayList<>();
        for (DashboardBuffer.ConvTrace c : convs) {
            String reqCommand = null;
            String reqTime = null;
            String reqBusiness = null;
            String claimed = null;

            for (DashboardBuffer.SmsTrace s : sms) {
                if (!equalsSafe(c.msisdn, s.msisdn) || s.time.compareTo(c.time) > 0) {
                    continue;
                }
                if ("REQ".equals(s.kind) && reqCommand == null) {
                    reqCommand = s.command;
                    reqTime = s.time;
                    reqBusiness = businessLabel(s.country, s.business);
                }
                if ("CLAIM".equals(s.kind) && claimed == null && s.clickId != null) {
                    claimed = s.clickId;
                }
            }
            String business = reqBusiness != null ? reqBusiness : convBusiness(c.identifier, c.detail);
            rows.add(new ConvRow(c.time, c.identifier, business, c.msisdn, c.detail, reqCommand, reqTime, claimed));
        }
        return rows;
    }

    private List<DbConvRow> correlateConvDb(List<log> convs, List<log> smsClicks) {
        List<DbConvRow> rows = new ArrayList<>();
        for (log c : convs) {
            String command = null;
            String commandTime = null;
            Long bestId = null;

            for (log s : smsClicks) {
                if (!equalsSafe(c.getMsisdn(), s.getMsisdn()) || s.getId() >= c.getId()) {
                    continue;
                }
                if (bestId == null || s.getId() > bestId) {
                    bestId = s.getId();
                    command = parseDbCommand(s.getExtra());
                    commandTime = formatDate(s.getLastUpdate());
                }
            }
            rows.add(new DbConvRow(c.getId(), formatDate(c.getLastUpdate()), c.getIdentifier(),
                convBusiness(c.getIdentifier(), c.getExtra()), c.getMsisdn(), c.getExtra(),
                command, commandTime, extractProviderId(c.getExtra())));
        }
        return rows;
    }

    private static boolean equalsSafe(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * Parsed SMS_CLICK log row (new format carries country/business/command,
     * legacy rows carry the plain command).
     */
    public static class DbSmsRow {
        public final Long id;
        public final String time;
        public final String country;
        public final String business;
        public final String msisdn;
        public final String command;

        public DbSmsRow(Long id, String time, String country, String business, String msisdn, String command) {
            this.id = id;
            this.time = time;
            this.country = country;
            this.business = business;
            this.msisdn = msisdn;
            this.command = command;
        }
    }

    private List<DbSmsRow> toDbSmsRows(List<log> smsClicks) {
        List<DbSmsRow> rows = new ArrayList<>();
        for (log s : smsClicks) {
            rows.add(new DbSmsRow(s.getId(), formatDate(s.getLastUpdate()),
                parseDbField(s.getExtra(), "country"), parseDbField(s.getExtra(), "business"),
                s.getMsisdn(), parseDbCommand(s.getExtra())));
        }
        return rows;
    }

    /**
     * Parse "command=..." from the SMS_CLICK extra; legacy rows carry the plain command.
     */
    static String parseDbCommand(String extra) {
        String field = parseDbField(extra, "command");
        return field != null ? field : (extra == null ? "" : extra);
    }

    static String parseDbField(String extra, String field) {
        if (extra == null) {
            return null;
        }
        int at = extra.indexOf(field + "=");
        if (at < 0) {
            return null;
        }
        int start = at + field.length() + 1;
        int end = start;
        while (end < extra.length()) {
            char ch = extra.charAt(end);
            if (ch == ',' || ch == ' ') {
                break;
            }
            end++;
        }
        return extra.substring(start, end);
    }

    /**
     * Label the business from the clicktosms country/business pair.
     */
    static String businessLabel(String country, String business) {
        if (country == null) {
            return null;
        }
        if ("6".equals(country) && "10".equals(business)) return "MAXGAME";
        if ("6".equals(country) && "6".equals(business)) return "CIUDADJUEGO";
        if ("14".equals(country) && "6".equals(business)) return "BLIVE";
        if ("14".equals(country) && "5".equals(business)) return "PAXXION";
        if ("14".equals(country) && "4".equals(business)) return "TEACH";
        return country + ":" + business;
    }

    /**
     * Label the business from a conversion identifier (and handler for shared
     * identifiers: CONV_TRAFFIC is used by both Haiti and Maxgame).
     */
    static String convBusiness(String identifier, String detail) {
        if ("CONV_MOBIPIUM".equals(identifier)) {
            return "BLIVE";
        }
        if ("CONV_VIA".equals(identifier) || "CONV_SEXY".equals(identifier) || "CONV_CHAT".equals(identifier)) {
            return "PAXXION";
        }
        if ("CONV_TRAFFIC".equals(identifier)) {
            return detail != null && detail.contains("handler=11191") ? "MAXGAME" : "HAITI";
        }
        return identifier;
    }

    private static String formatDate(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * Extract the provider click id from a postback url detail
     * (tracker= for level23, jp= mobipium, token= via, transaction_id= sexy, click_id= chat).
     */
    static String extractProviderId(String detail) {
        if (detail == null) {
            return null;
        }
        String[] params = {"tracker=", "jp=", "token=", "transaction_id=", "click_id="};
        for (String param : params) {
            int at = detail.indexOf(param);
            if (at >= 0) {
                int start = at + param.length();
                int end = start;
                while (end < detail.length()) {
                    char ch = detail.charAt(end);
                    if (ch == '&' || ch == ' ') {
                        break;
                    }
                    end++;
                }
                return detail.substring(start, end);
            }
        }
        return null;
    }

    /**
     * Simple row to merge the four activity tables into one clicks table.
     */
    public static class DbClickRow {
        public final String source;
        public final Long id;
        public final String date;
        public final String clickId;
        public final String origin;
        public final String msisdn;
        public final String extra;

        public DbClickRow(String source, Long id, String date, String clickId, String origin, String msisdn, String extra) {
            this.source = source;
            this.id = id;
            this.date = date;
            this.clickId = clickId;
            this.origin = origin;
            this.msisdn = msisdn;
            this.extra = extra;
        }
    }

    private List<DbClickRow> lastDbClicks() {
        List<DbClickRow> rows = new ArrayList<>();

        for (BliveActivity a : BliveActivity.finder.orderBy().desc("id").setMaxRows(100).findList()) {
            rows.add(new DbClickRow("BLIVE", a.getId(), a.getDate(), a.getClickId(), null, a.getMsisdn(), null));
        }
        for (PaxxionActivity a : PaxxionActivity.finder.orderBy().desc("id").setMaxRows(100).findList()) {
            rows.add(new DbClickRow("PAXXION", a.getId(), a.getDate(), a.getClickId(), a.getOrigin(), a.getMsisdn(), null));
        }
        for (LearnLiveActivity a : LearnLiveActivity.finder.orderBy().desc("id").setMaxRows(100).findList()) {
            rows.add(new DbClickRow("LEARNLIVE", a.getId(), a.getDate(), a.getClickId(), a.getOrigin(), a.getMsisdn(), null));
        }
        for (MaxgameActivity a : MaxgameActivity.finder.orderBy().desc("id").setMaxRows(100).findList()) {
            rows.add(new DbClickRow("MAXGAME", a.getId(), a.getDate(), a.getClickId(), a.getOrigin(), a.getMsisdn(),
                a.isSent() ? "sent" : "pending"));
        }

        Collections.sort(rows, new Comparator<DbClickRow>() {
            public int compare(DbClickRow a, DbClickRow b) {
                int byDate = String.valueOf(b.date).compareTo(String.valueOf(a.date));
                return byDate != 0 ? byDate : Long.compare(b.id, a.id);
            }
        });
        return rows.size() > 100 ? rows.subList(0, 100) : rows;
    }

    private List<log> lastDbSmsClicks(int limit) {
        return log.finder.where().eq("identifier", "SMS_CLICK")
            .orderBy().desc("id").setMaxRows(limit).findList();
    }

    private List<log> lastDbConversions() {
        return log.finder.where().in("identifier", Arrays.asList(
                "CONV_TRAFFIC", "CONV_MOBIPIUM", "CONV_SEXY", "CONV_CHAT", "CONV_VIA"))
            .orderBy().desc("id").setMaxRows(100).findList();
    }
}
