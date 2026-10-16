package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.domain.LogEntry;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import com.hecticus.gpaapi.service.Constants;
import com.hecticus.gpaapi.service.ConversionService;
import com.hecticus.gpaapi.service.activity.BusinessConfig;
import com.hecticus.gpaapi.service.activity.BusinessConfig.ActivityType;
import com.hecticus.gpaapi.service.activity.BusinessConfig.ConversionType;
import com.hecticus.gpaapi.service.dashboard.DashboardBuffer;
import com.hecticus.gpaapi.service.tracking.ClickData;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClickToSMSController {

    private static final Logger log = LoggerFactory.getLogger(ClickToSMSController.class);

    static final String TABLE_BLIVE = "blive_activity";
    static final String TABLE_PAXXION = "paxxion_activity";
    static final String TABLE_LEARNLIVE = "learn_live_activity";
    static final String TABLE_MAXGAME = "maxgame_activity";

    private final ConversionService conversionService;
    private final LogEntryRepository logRepository;
    private final JdbcTemplate jdbcTemplate;
    private final GpaApiProperties properties;
    private final Map<String, BusinessConfig> businessConfigs;
    private final TransactionTemplate transactionTemplate;

    public ClickToSMSController(ConversionService conversionService,
                                LogEntryRepository logRepository,
                                JdbcTemplate jdbcTemplate,
                                GpaApiProperties properties,
                                PlatformTransactionManager transactionManager) {
        this.conversionService = conversionService;
        this.logRepository = logRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.businessConfigs = initializeBusinessConfigs();
    }

    private Map<String, BusinessConfig> initializeBusinessConfigs() {
        Map<String, BusinessConfig> configs = new HashMap<>();
        String paxxionHandler = properties.getConversion().getPaxxionHandler();
        String paxxionHash = properties.getConversion().getPaxxionHash();
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID),
                new BusinessConfig(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID,
                        ActivityType.BLIVE, ConversionType.MOBIPIUM));
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID),
                new BusinessConfig(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID,
                        ActivityType.PAXXION, ConversionType.TRAFFIC_COMPANY, paxxionHandler, paxxionHash));
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_TEACH_BUSINESS_ID),
                new BusinessConfig(Constants.HAITI_COUNTRY_ID, Constants.HAITI_TEACH_BUSINESS_ID,
                        ActivityType.LEARNLIVE, ConversionType.TRAFFIC_COMPANY, paxxionHandler, paxxionHash));
        configs.put(makeKey(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID),
                new BusinessConfig(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID,
                        ActivityType.MAXGAME, ConversionType.TRAFFIC_COMPANY,
                        properties.getConversion().getMaxgameHandler(),
                        properties.getConversion().getMaxgameHash()));
        return configs;
    }

    @GetMapping("/clicktosms/{country}/{business}/{msisdn}")
    public @ResponseBody String markItem(@PathVariable String country,
                                         @PathVariable String business,
                                         @PathVariable String msisdn,
                                         HttpServletRequest request) {
        try {
            String command = request.getParameter("command");
            if (command == null) {
                command = "";
            }
            processRequest(country, business, msisdn, command);
        } catch (Exception e) {
            log.error("Error in ClickToSMSController.markItem()", e);
        }
        return "";
    }

    public void processRequest(String country, String business, String msisdn, String command) {
        logRequest(msisdn, command, country, business);
        DashboardBuffer.get().addSms(new DashboardBuffer.SmsTrace(
                "REQ", country, business, msisdn, command, null, ""
        ));

        BusinessConfig config = businessConfigs.get(makeKey(country, business));
        if (config == null) {
            log.warn("No configuration found for country={}, business={}, msisdn={}", country, business, msisdn);
            return;
        }
        log.info("Processing SMS click: {}, msisdn={}", config, msisdn);
        handleActivity(config, msisdn, command);
    }

    private void handleActivity(BusinessConfig config, String msisdn, String command) {
        String dateThreshold = getDateAddSeconds(-20);
        switch (config.getActivityType()) {
            case BLIVE:
                handleBliveActivity(msisdn, dateThreshold, config);
                break;
            case PAXXION:
                handlePaxxionActivity(msisdn, dateThreshold, command);
                break;
            case LEARNLIVE:
                handleLearnLiveActivity(msisdn, dateThreshold, config);
                break;
            case MAXGAME:
                handleMaxgameActivity(msisdn, dateThreshold, config, command);
                break;
            default:
                log.warn("Unknown activity type: {}", config.getActivityType());
        }
    }

    private void handleBliveActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        String clickId = claimPendingClick(TABLE_BLIVE, msisdn, dateThreshold, null);
        if (clickId != null && config.getConversionType() == ConversionType.MOBIPIUM) {
            sendConversionMobipium(msisdn, clickId);
        } else if (clickId == null) {
            log.warn("No BliveActivity found for msisdn={}", msisdn);
        }
    }

    private void handlePaxxionActivity(String msisdn, String dateThreshold, String command) {
        String origin = mapCommandToOrigin(command);
        String clickId = claimPendingClick(TABLE_PAXXION, msisdn, dateThreshold, origin);
        if (clickId == null) {
            log.warn("No PaxxionActivity found for msisdn={}, origin={}", msisdn, origin);
            return;
        }
        switch (origin) {
            case "MOB":
                sendConversionMobipium(msisdn, clickId);
                break;
            case "TRA":
                conversionService.sendToTrafficCompany(msisdn,
                        properties.getConversion().getPaxxionHandler(),
                        properties.getConversion().getPaxxionHash(), clickId);
                break;
            case "VIA":
                conversionService.sendToVia(msisdn, clickId);
                break;
            case "SEXY":
                conversionService.sendToSexy(msisdn, clickId);
                break;
            case "CHAT":
                conversionService.sendToChat(msisdn, clickId);
                break;
            default:
                log.warn("Unknown origin/command for PaxxionActivity: {}", command);
        }
    }

    private void handleLearnLiveActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        String clickId = claimPendingClick(TABLE_LEARNLIVE, msisdn, dateThreshold, null);
        if (clickId == null) {
            log.warn("No LearnLiveActivity found for msisdn={}", msisdn);
            return;
        }
        if (config.getConversionType() == ConversionType.MOBIPIUM) {
            sendConversionMobipium(msisdn, clickId);
        }
        if (config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
            conversionService.sendToTrafficCompany(msisdn, config.getTrafficHandler(),
                    config.getTrafficHash(), clickId);
        }
    }

    /**
     * Handle MaxgameActivity by command:
     * - LANDING6 is the Google Ads (2026) flow: it claims a pending GADS click
     *   and counts it only, never sending a postback.
     * - LANDING is the legacy Traffic Company flow: it claims a pending TRA
     *   click and sends the Traffic Company conversion.
     * Any other command is ignored so clicks are not consumed by them.
     */
    private void handleMaxgameActivity(String msisdn, String dateThreshold, BusinessConfig config, String command) {
        // LANDING6: Google Ads only. Claim a pending GADS click and count it,
        // no postback is ever sent in this flow.
        if (Constants.VEN_MAXGAME_COMMAND.equalsIgnoreCase(command)) {
            String gadsClickId = claimPendingClick(TABLE_MAXGAME, msisdn, dateThreshold, ClickData.ORIGIN_GOOGLE);
            if (gadsClickId != null) {
                log.info("Maxgame GADS conversion counted (no postback): msisdn={}, clickId={}", msisdn, gadsClickId);
            } else {
                log.warn("No Maxgame GADS click found for msisdn={}", msisdn);
            }
            return;
        }

        // LANDING (legacy): Traffic Company only. Claim a pending TRA click and
        // send the Traffic Company conversion.
        if (Constants.VEN_MAXGAME_COMMAND_LEGACY.equalsIgnoreCase(command)) {
            String clickId = claimPendingClick(TABLE_MAXGAME, msisdn, dateThreshold, ClickData.ORIGIN_TRA);
            if (clickId != null) {
                log.info("Claimed Maxgame TRA activity: msisdn={}, clickId={}", msisdn, clickId);

                if (config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                    conversionService.sendToTrafficCompany(msisdn, config.getTrafficHandler(),
                            config.getTrafficHash(), clickId);
                }
            } else {
                log.warn("No Maxgame TRA activity found for msisdn={}", msisdn);
            }
            return;
        }

        log.warn("Maxgame conversion skipped: unexpected command={}", command);
    }

    static String mapCommandToOrigin(String command) {
        switch (command) {
            case "LANDING":
                return "MOB";
            case "LANDING2":
                return "VIA";
            case "LANDING3":
                return "TRA";
            case "LANDING4":
                return "SEXY";
            case "LANDING5":
                return "CHAT";
            default:
                return command;
        }
    }

    static String buildClaimSelectSql(String table, String origin) {
        StringBuilder select = new StringBuilder(
                "select id, click_id from " + table + " where msisdn is null and date < ? ");
        if (origin != null) {
            select.append("and origin = ? ");
        }
        select.append("order by id desc limit 1 for update");
        return select.toString();
    }

    /**
     * Atomically claim one pending click so concurrent SMS cannot consume the same row.
     * Runs inside an explicit transaction (TransactionTemplate) so the SELECT ... FOR UPDATE
     * lock is held until the UPDATE commits. A @Transactional annotation would be bypassed
     * here because the activity handlers invoke this method internally (self-invocation).
     *
     * @return claimed clickId, or null when no pending activity matches
     */
    protected String claimPendingClick(String table, String msisdn, String dateThreshold, String origin) {
        try {
            return transactionTemplate.execute(status -> doClaimPendingClick(table, msisdn, dateThreshold, origin));
        } catch (Exception e) {
            log.error("Error claiming click from {} for msisdn={}", table, msisdn, e);
            return null;
        }
    }

    private String doClaimPendingClick(String table, String msisdn, String dateThreshold, String origin) {
        String filters = "table=" + table + ", origin=" + origin;
        String sql = buildClaimSelectSql(table, origin);
        List<Map<String, Object>> rows = origin != null
                ? jdbcTemplate.queryForList(sql, dateThreshold, origin)
                : jdbcTemplate.queryForList(sql, dateThreshold);
        if (rows.isEmpty()) {
            DashboardBuffer.get().addSms(new DashboardBuffer.SmsTrace(
                    "CLAIM", "", "", msisdn, "", null, filters + ", no pending match"
            ));
            return null;
        }
        Map<String, Object> row = rows.get(0);
        Long id = ((Number) row.get("id")).longValue();
        String clickId = (String) row.get("click_id");
        int updated = jdbcTemplate.update("update " + table + " set msisdn = ? where id = ?", msisdn, id);
        if (updated != 1) {
            log.error("Could not mark {}: id={}, msisdn={}", table, id, msisdn);
            DashboardBuffer.get().addSms(new DashboardBuffer.SmsTrace(
                    "CLAIM", "", "", msisdn, "", null, filters + ", update failed id=" + id
            ));
            return null;
        }
        DashboardBuffer.get().addSms(new DashboardBuffer.SmsTrace(
                "CLAIM", "", "", msisdn, "", clickId, filters + ", claimed=" + clickId
        ));
        return clickId;
    }

    private void sendConversionMobipium(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            log.warn("Cannot send Mobipium conversion: clickId is empty");
            return;
        }
        String[] values = clickId.split("---");
        String actualClickId = values[0];
        String source = values.length > 1 ? values[1] : "";
        conversionService.sendToMobipium(msisdn, actualClickId, source);
    }

    protected void logRequest(String msisdn, String command, String country, String business) {
        log.info("[SMS_CLICK] country={}, business={}, msisdn={}, command={}", country, business, msisdn, command);
        try {
            LogEntry entry = new LogEntry();
            entry.setIdentifier("SMS_CLICK");
            entry.setExtra("country=" + country + ", business=" + business + ", command=" + (command == null ? "" : command));
            entry.setMsisdn(msisdn);
            entry.setLastUpdate(LocalDateTime.now());
            logRepository.save(entry);
        } catch (Exception e) {
            log.error("Error storing SMS_CLICK log: msisdn={}, command={}", msisdn, command, e);
        }
    }

    private String makeKey(String country, String business) {
        return country + ":" + business;
    }

    private String getDateAddSeconds(int seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        return sdf.format(calendar.getTime());
    }
}
