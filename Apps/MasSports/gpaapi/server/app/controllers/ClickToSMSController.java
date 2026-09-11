package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.Transaction;
import modeles.*;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import services.activity.BusinessConfig;
import services.activity.BusinessConfig.ActivityType;
import services.activity.BusinessConfig.ConversionType;
import services.conversion.ConversionService;
import utils.Constants;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClickToSMS Controller - Refactored with SOLID principles
 * Handles SMS-based click tracking for different countries and businesses
 */
public class ClickToSMSController extends Controller {

    static final String TABLE_BLIVE = "blive_activity";
    static final String TABLE_PAXXION = "paxxion_activity";
    static final String TABLE_LEARNLIVE = "learn_live_activity";
    static final String TABLE_MAXGAME = "maxgame_activity";

    private final ConversionService conversionService;
    private final Map<String, BusinessConfig> businessConfigs;

    @Inject
    public ClickToSMSController(ConversionService conversionService) {
        this.conversionService = conversionService;
        this.businessConfigs = initializeBusinessConfigs();
    }

    /**
     * Initialize business configurations (Factory Method Pattern)
     * Follows Open/Closed Principle - easy to add new business configs
     */
    private Map<String, BusinessConfig> initializeBusinessConfigs() {
        Map<String, BusinessConfig> configs = new HashMap<>();

        // Haiti - Blive
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID),
            new BusinessConfig(
                Constants.HAITI_COUNTRY_ID,
                Constants.HAITI_BLIVE_BUSINESS_ID,
                ActivityType.BLIVE,
                ConversionType.MOBIPIUM
            )
        );

        // Haiti - Paxxion
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID),
            new BusinessConfig(
                Constants.HAITI_COUNTRY_ID,
                Constants.HAITI_PAXION_BUSINESS_ID,
                ActivityType.PAXXION,
                ConversionType.TRAFFIC_COMPANY,
                "11240",  // handler
                "0dd1b688a16aa53c03fe0cfe2c114e71"  // hash
            )
        );

        // Haiti - Teach (LearnLive) 
        configs.put(makeKey(Constants.HAITI_COUNTRY_ID, Constants.HAITI_TEACH_BUSINESS_ID),
            new BusinessConfig(
                Constants.HAITI_COUNTRY_ID,
                Constants.HAITI_TEACH_BUSINESS_ID,
                ActivityType.LEARNLIVE,
                ConversionType.TRAFFIC_COMPANY,
                "11240",
                "0dd1b688a16aa53c03fe0cfe2c114e71"
            )
        );

        // Venezuela - Maxgame
        configs.put(makeKey(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID),
            new BusinessConfig(
                Constants.VEN_COUNTRY_ID,
                Constants.VEN_MAXGAME_BUSINESS_ID,
                ActivityType.MAXGAME,
                ConversionType.TRAFFIC_COMPANY,
                "11191",  // handler
                "3c71abda6be99653251370ff838fa4ab"  // hash
            )
        );

        return configs;
    }

    /**
     * Main endpoint to mark SMS activity
     */
    public Result markItem(String country, String business, String msisdn) {
        try {
            // Extract command parameter
            String command = getQueryParamOrEmpty("command");

            // Process the SMS request (logged even if config/activity is not found)
            processRequest(country, business, msisdn, command);

        } catch (Exception e) {
            Logger.error("Error in ClickToSMSController.markItem()", e);
        }
        return ok(); // Always return OK to not break client flow
    }

    /**
     * Process a single incoming SMS request
     */
    public void processRequest(String country, String business, String msisdn, String command) {
        // Log the request (file + database) before any processing
        logRequest(msisdn, command, country, business);

        // Find matching business config
        BusinessConfig config = businessConfigs.get(makeKey(country, business));

        if (config == null) {
            Logger.warn("No configuration found for country=" + country + ", business=" + business + ", msisdn=" + msisdn);
            return;
        }

        Logger.info("Processing SMS click: " + config.toString() + ", msisdn=" + msisdn);

        // Handle activity based on config
        handleActivity(config, msisdn, command);
    }

    /**
     * Handle activity based on configuration (Strategy Pattern)
     */
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
                Logger.warn("Unknown activity type: " + config.getActivityType());
        }
    }

    /**
     * Handle BliveActivity
     */
    private void handleBliveActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        String clickId = claimPendingClick(TABLE_BLIVE, msisdn, dateThreshold, null);

        if (clickId != null) {
            Logger.info("Claimed BliveActivity: msisdn=" + msisdn + ", clickId=" + clickId);

            if (config.getConversionType() == ConversionType.MOBIPIUM) {
                sendConversionMobipium(msisdn, clickId);
            }
        } else {
            Logger.warn("No BliveActivity found for msisdn=" + msisdn);
        }
    }

    /**
     * Handle PaxxionActivity
     */
    private void handlePaxxionActivity(String msisdn, String dateThreshold, String command) {
        // Map incoming command to internal origin value
        String origin = mapCommandToOrigin(command);

        String clickId = claimPendingClick(TABLE_PAXXION, msisdn, dateThreshold, origin);

        if (clickId != null) {
            Logger.info("Claimed PaxxionActivity: msisdn=" + msisdn + ", clickId=" + clickId + ", origin=" + origin);

            switch (origin) {
                case "MOB":
                    sendConversionMobipium(msisdn, clickId);
                    break;
                case "TRA":
                    conversionService.sendToTrafficCompany(
                        msisdn,
                        Constants.HAITI_PAXION_HANDLER,
                        Constants.HAITI_PAXION_HASH,
                        clickId
                    );
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
                    Logger.warn("Unknown origin/command for PaxxionActivity: " + command);
            }
        } else {
            Logger.warn("No PaxxionActivity found for msisdn=" + msisdn + ", origin=" + origin);
        }
    }

    /**
     * Handle LearnLiveActivity
     */
    private void handleLearnLiveActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        String clickId = claimPendingClick(TABLE_LEARNLIVE, msisdn, dateThreshold, null);

        if (clickId != null) {
            Logger.info("Claimed LearnLiveActivity: msisdn=" + msisdn + ", clickId=" + clickId);

            if (config.getConversionType() == ConversionType.MOBIPIUM) {
                sendConversionMobipium(msisdn, clickId);
            }

            if (config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                conversionService.sendToTrafficCompany(
                    msisdn,
                    config.getTrafficHandler(),
                    config.getTrafficHash(),
                    clickId
                );
            }
        } else {
            Logger.warn("No LearnLiveActivity found for msisdn=" + msisdn);
        }
    }

    /**
     * Handle MaxgameActivity
     */
    private void handleMaxgameActivity(String msisdn, String dateThreshold, BusinessConfig config, String command) {
        String clickId = claimPendingClick(TABLE_MAXGAME, msisdn, dateThreshold, null);

        if (clickId != null) {
            Logger.info("Claimed MaxgameActivity: msisdn=" + msisdn + ", clickId=" + clickId);

            // Only send conversion if command is LANDING
            if ("LANDING".equals(command) && config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                conversionService.sendToTrafficCompany(
                    msisdn,
                    config.getTrafficHandler(),
                    config.getTrafficHash(),
                    clickId
                );
            }
        } else {
            Logger.warn("No MaxgameActivity found for msisdn=" + msisdn);
        }
    }

    /**
     * Map incoming command to internal origin value
     */
    static String mapCommandToOrigin(String command) {
        switch (command) {
            case "LANDING":  return "MOB";
            case "LANDING2": return "VIA";
            case "LANDING3": return "TRA";
            case "LANDING4": return "SEXY";
            case "LANDING5": return "CHAT";
            default:         return command;
        }
    }

    /**
     * Build the atomic claim SQL. FOR UPDATE serializes concurrent SMS so each one
     * claims a different pending row (no lost clicks).
     */
    static String buildClaimSelectSql(String table, String origin) {
        StringBuilder select = new StringBuilder(
            "select id, click_id from " + table +
            " where msisdn is null and date < ? ");
        if (origin != null) {
            select.append("and origin = ? ");
        }
        select.append("order by id desc limit 1 for update");
        return select.toString();
    }

    /**
     * Atomically claim one pending click so concurrent SMS cannot consume the same row.
     * Uses SELECT ... FOR UPDATE inside a transaction to serialize claimants
     * (first SMS takes the newest pending row; the next takes the following one).
     *
     * @return claimed clickId, or null when no pending activity matches
     */
    protected String claimPendingClick(String table, String msisdn, String dateThreshold, String origin) {
        String selectSql = buildClaimSelectSql(table, origin);

        Transaction tx = Ebean.beginTransaction();
        try {
            SqlQuery query = Ebean.createSqlQuery(selectSql)
                .setParameter(1, dateThreshold);
            if (origin != null) {
                query.setParameter(2, origin);
            }
            List<SqlRow> rows = query.findList();

            if (rows.isEmpty()) {
                return null;
            }

            SqlRow row = rows.get(0);
            Long id = row.getLong("id");
            String clickId = row.getString("click_id");

            int updated = Ebean.createSqlUpdate("update " + table + " set msisdn = ? where id = ?")
                .setParameter(1, msisdn)
                .setParameter(2, id)
                .execute();

            if (updated != 1) {
                Logger.error("Could not mark " + table + ": id=" + id + ", msisdn=" + msisdn);
                return null;
            }

            tx.commit();
            return clickId;

        } catch (Exception e) {
            Logger.error("Error claiming click from " + table + " for msisdn=" + msisdn, e);
            return null;
        } finally {
            tx.end();
        }
    }

    /**
     * Send Mobipium conversion (split clickId if contains ---)
     */
    private void sendConversionMobipium(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("Cannot send Mobipium conversion: clickId is empty");
            return;
        }

        String[] values = clickId.split("---");
        String actualClickId = values[0];
        String source = values.length > 1 ? values[1] : "";

        conversionService.sendToMobipium(msisdn, actualClickId, source);
    }

    /**
     * Log request to application log and database
     */
    protected void logRequest(String msisdn, String command, String country, String business) {
        Logger.info("[SMS_CLICK] country=" + country + ", business=" + business + ", msisdn=" + msisdn + ", command=" + command);

        try {
            log logEntry = new log();
            logEntry.setIdentifier("SMS_CLICK");
            logEntry.setExtra(command);
            logEntry.setMsisdn(msisdn);
            logEntry.setLastUpdate(new Date());
            logEntry.save();
        } catch (Exception e) {
            Logger.error("Error storing SMS_CLICK log: msisdn=" + msisdn + ", command=" + command, e);
        }
    }

    /**
     * Get query parameter or empty string
     */
    private String getQueryParamOrEmpty(String paramName) {
        String value = request().getQueryString(paramName);
        return (value != null && !value.isEmpty()) ? value : "";
    }

    /**
     * Create composite key for business config map
     */
    private String makeKey(String country, String business) {
        return country + ":" + business;
    }

    /**
     * Get date with seconds offset
     */
    private String getDateAddSeconds(int seconds) {
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        return sdf.format(calendar.getTime());
    }
}
