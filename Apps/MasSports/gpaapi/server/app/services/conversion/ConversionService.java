package services.conversion;

import modeles.log;
import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Date;

/**
 * Service to handle external conversion tracking
 * Follows Single Responsibility Principle
 */
@Singleton
public class ConversionService {

    private static final int MAX_DETAIL_LENGTH = 400;

    private final WSClient wsClient;
    private final OkHttpClient httpClient;

    @Inject
    public ConversionService(WSClient wsClient) {
        this.wsClient = wsClient;
        this.httpClient = new OkHttpClient();
    }

    /**
     * Send conversion to Mobipium
     */
    public void sendToMobipium(String msisdn, String clickId, String source) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("Mobipium: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_MOBIPIUM", "skipped: empty clickId");
            return;
        }

        String url = String.format(
            "https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s", 
            clickId, 
            source != null ? source : ""
        );
        
        Logger.info("Mobipium conversion: " + url);

        saveConversionLog(msisdn, "CONV_MOBIPIUM", "call url=" + url);
        
        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("Mobipium response: " + response.getBody());
                    saveConversionLog(msisdn, "CONV_MOBIPIUM", "http=" + response.getStatus());
                })
                .exceptionally(throwable -> {
                    Logger.error("Mobipium error: " + throwable.getMessage());
                    saveConversionLog(msisdn, "CONV_MOBIPIUM", "error=" + throwable.getMessage());
                    return null;
                });
    }

    /**
     * Send conversion to Traffic Company (Level23)
     */
    public void sendToTrafficCompany(String msisdn, String handler, String hash, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("TrafficCompany: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_TRAFFIC", "skipped: empty clickId");
            return;
        }

        String url = String.format(
            "http://postback.level23.nl/?currency=USD&handler=%s&hash=%s&tracker=%s",
            handler, hash, clickId
        );

        Logger.info("TrafficCompany conversion: " + url);

        saveConversionLog(msisdn, "CONV_TRAFFIC", "call url=" + url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            Logger.debug("TrafficCompany request sent successfully");
            saveConversionLog(msisdn, "CONV_TRAFFIC", "http=" + response.code());
        } catch (IOException e) {
            Logger.error("TrafficCompany error: " + e.getMessage(), e);
            saveConversionLog(msisdn, "CONV_TRAFFIC", "error=" + e.getMessage());
        } catch (Exception e) {
            Logger.error("TrafficCompany unexpected error: " + e.getMessage(), e);
            saveConversionLog(msisdn, "CONV_TRAFFIC", "error=" + e.getMessage());
        }
    }

    /**
     * Send conversion to lktrack (SEXY origin) using transaction_id
     */
    public void sendToSexy(String msisdn, String transactionId) {
        if (transactionId == null || transactionId.isEmpty()) {
            Logger.warn("SEXY: Invalid transaction_id, skipping");
            saveConversionLog(msisdn, "CONV_SEXY", "skipped: empty transaction_id");
            return;
        }

        String url = String.format(
            "%s?key=%s&transaction_id=%s",
            utils.Constants.SEXY_POSTBACK_URL,
            utils.Constants.SEXY_POSTBACK_KEY,
            transactionId
        );

        Logger.info("SEXY conversion: " + url);

        saveConversionLog(msisdn, "CONV_SEXY", "call url=" + url);

        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("SEXY response: " + response.getBody());
                    saveConversionLog(msisdn, "CONV_SEXY", "http=" + response.getStatus());
                })
                .exceptionally(throwable -> {
                    Logger.error("SEXY error: " + throwable.getMessage());
                    saveConversionLog(msisdn, "CONV_SEXY", "error=" + throwable.getMessage());
                    return null;
                });
    }

    /**
     * Send conversion to mobidea (CHAT origin) using click_id
     */
    public void sendToChat(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("CHAT: Invalid click_id, skipping");
            saveConversionLog(msisdn, "CONV_CHAT", "skipped: empty click_id");
            return;
        }

        String url = String.format(
            "%s?click_id=%s&security_token=%s",
            utils.Constants.CHAT_POSTBACK_URL,
            clickId,
            utils.Constants.CHAT_SECURITY_TOKEN
        );

        Logger.info("CHAT conversion: " + url);

        saveConversionLog(msisdn, "CONV_CHAT", "call url=" + url);

        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("CHAT response: " + response.getBody());
                    saveConversionLog(msisdn, "CONV_CHAT", "http=" + response.getStatus());
                })
                .exceptionally(throwable -> {
                    Logger.error("CHAT error: " + throwable.getMessage());
                    saveConversionLog(msisdn, "CONV_CHAT", "error=" + throwable.getMessage());
                    return null;
                });
    }

    /**
     * Send conversion to VIA
     */
    public void sendToVia(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("VIA: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_VIA", "skipped: empty clickId");
            return;
        }

        String url = String.format(
            "http://api.doblevialatam.com:9090/cget.php?token=%s", 
            clickId
        );
        
        Logger.info("VIA conversion: " + url);

        saveConversionLog(msisdn, "CONV_VIA", "call url=" + url);

        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("VIA response: " + response.getBody());
                    saveConversionLog(msisdn, "CONV_VIA", "http=" + response.getStatus());
                })
                .exceptionally(throwable -> {
                    Logger.error("VIA error: " + throwable.getMessage());
                    saveConversionLog(msisdn, "CONV_VIA", "error=" + throwable.getMessage());
                    return null;
                });
    }

    /**
     * Persist the conversion call result in the log table
     */
    private void saveConversionLog(String msisdn, String identifier, String detail) {
        try {
            log entry = new log();
            entry.setMsisdn(msisdn != null ? msisdn : "");
            entry.setIdentifier(identifier);
            entry.setExtra(truncate(detail, MAX_DETAIL_LENGTH));
            entry.setLastUpdate(new Date());
            entry.save();
        } catch (Exception e) {
            Logger.error("Error storing conversion log " + identifier + ", msisdn=" + msisdn, e);
        }
    }

    private static String truncate(String value, int maxLength) {
        if (value == null) {
            return "";
        }
        return value.length() <= maxLength ? value : value.substring(0, maxLength);
    }
}
