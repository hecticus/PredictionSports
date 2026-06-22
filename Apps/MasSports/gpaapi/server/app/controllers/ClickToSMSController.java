package controllers;

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

            // Log the request
            logRequest(msisdn, command);

            // Find matching business config
            BusinessConfig config = businessConfigs.get(makeKey(country, business));
            
            if (config == null) {
                Logger.warn("No configuration found for country=" + country + ", business=" + business);
                return ok();
            }

            Logger.info("Processing SMS click: " + config.toString() + ", msisdn=" + msisdn);

            // Handle activity based on config
            handleActivity(config, msisdn, command);

            return ok();

        } catch (Exception e) {
            Logger.error("Error in ClickToSMSController.markItem()", e);
            return ok(); // Return OK to not break client flow
        }
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
                handlePaxxionActivity(msisdn, dateThreshold, config);
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
        BliveActivity activity = BliveActivity.finder.where()
                .eq("msisdn", null)
                .lt("date", dateThreshold)
                .orderBy().desc("id")
                .setMaxRows(1)
                .findUnique();

        if (activity != null) {
            activity.setMsisdn(msisdn);
            activity.save();
            Logger.info("Updated BliveActivity: id=" + activity.getId() + ", msisdn=" + msisdn);

            if (config.getConversionType() == ConversionType.MOBIPIUM) {
                sendConversionMobipium(activity.getClickId());
            }
        } else {
            Logger.warn("No BliveActivity found for msisdn=" + msisdn);
        }
    }

    /**
     * Handle PaxxionActivity
     */
    private void handlePaxxionActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        PaxxionActivity activity = PaxxionActivity.finder.where()
                .eq("msisdn", null)
                .lt("date", dateThreshold)
                .eq("origin", config.getConversionType().getValue())  // origin from config
                .orderBy().desc("id")
                .setMaxRows(1)
                .findUnique();

        if (activity != null) {
            activity.setMsisdn(msisdn);
            activity.save();
            Logger.info("Updated PaxxionActivity: id=" + activity.getId() + ", msisdn=" + msisdn);

            if (config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                conversionService.sendToTrafficCompany(
                    config.getTrafficHandler(),
                    config.getTrafficHash(),
                    activity.getClickId()
                );
            }
        } else {
            Logger.warn("No PaxxionActivity (TRA) found for msisdn=" + msisdn);
        }
    }

    /**
     * Handle LearnLiveActivity
     */
    private void handleLearnLiveActivity(String msisdn, String dateThreshold, BusinessConfig config) {
        LearnLiveActivity activity = LearnLiveActivity.finder.where()
                .eq("msisdn", null)
                .lt("date", dateThreshold)
                .orderBy().desc("id")
                .setMaxRows(1)
                .findUnique();

        if (activity != null) {
            activity.setMsisdn(msisdn);
            activity.save();
            Logger.info("Updated LearnLiveActivity: id=" + activity.getId() + ", msisdn=" + msisdn);

            if (config.getConversionType() == ConversionType.MOBIPIUM) {
                sendConversionMobipium(activity.getClickId());
            }

            if (config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                conversionService.sendToTrafficCompany(
                    config.getTrafficHandler(),
                    config.getTrafficHash(),
                    activity.getClickId()
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
        MaxgameActivity activity = MaxgameActivity.finder.where()
                .eq("msisdn", null)
                .lt("date", dateThreshold)
                .orderBy().desc("id")
                .setMaxRows(1)
                .findUnique();

        if (activity != null) {
            activity.setMsisdn(msisdn);
            activity.save();
            Logger.info("Updated MaxgameActivity: id=" + activity.getId() + ", msisdn=" + msisdn);

            // Only send conversion if command is LANDING
            if ("LANDING".equals(command) && config.getConversionType() == ConversionType.TRAFFIC_COMPANY) {
                conversionService.sendToTrafficCompany(
                    config.getTrafficHandler(),
                    config.getTrafficHash(),
                    activity.getClickId()
                );
            }
        } else {
            Logger.warn("No MaxgameActivity found for msisdn=" + msisdn);
        }
    }

    /**
     * Send Mobipium conversion (split clickId if contains ---)
     */
    private void sendConversionMobipium(String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("Cannot send Mobipium conversion: clickId is empty");
            return;
        }

        String[] values = clickId.split("---");
        String actualClickId = values[0];
        String source = values.length > 1 ? values[1] : "";

        conversionService.sendToMobipium(actualClickId, source);
    }

    /**
     * Log request to database
     */
    private void logRequest(String msisdn, String command) {
        try {
            log logEntry = new log();
            logEntry.setIdentifier("SMS_CLICK");
            logEntry.setExtra(command);
            logEntry.setMsisdn(msisdn);
            logEntry.save();
        } catch (Exception e) {
            Logger.error("Error logging request", e);
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
