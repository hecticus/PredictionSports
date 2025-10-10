package services.conversion;

import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * Service to handle external conversion tracking
 * Follows Single Responsibility Principle
 */
@Singleton
public class ConversionService {

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
    public void sendToMobipium(String clickId, String source) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("Mobipium: Invalid clickId, skipping");
            return;
        }

        String url = String.format(
            "https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s", 
            clickId, 
            source != null ? source : ""
        );
        
        Logger.info("Mobipium conversion: " + url);
        
        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("Mobipium response: " + response.getBody());
                })
                .exceptionally(throwable -> {
                    Logger.error("Mobipium error: " + throwable.getMessage());
                    return null;
                });
    }

    /**
     * Send conversion to Traffic Company (Level23)
     */
    public void sendToTrafficCompany(String handler, String hash, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("TrafficCompany: Invalid clickId, skipping");
            return;
        }

        String url = String.format(
            "http://postback.level23.nl/?currency=USD&handler=%s&hash=%s&tracker=%s",
            handler, hash, clickId
        );

        Logger.info("TrafficCompany conversion: " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            httpClient.newCall(request).execute();
            Logger.debug("TrafficCompany request sent successfully");
        } catch (IOException e) {
            Logger.error("TrafficCompany error: " + e.getMessage(), e);
        } catch (Exception e) {
            Logger.error("TrafficCompany unexpected error: " + e.getMessage(), e);
        }
    }

    /**
     * Send conversion to VIA
     */
    public void sendToVia(String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            Logger.warn("VIA: Invalid clickId, skipping");
            return;
        }

        String url = String.format(
            "http://api.doblevialatam.com:9090/cget.php?token=%s", 
            clickId
        );
        
        Logger.info("VIA conversion: " + url);
        
        wsClient.url(url)
                .get()
                .thenAccept((WSResponse response) -> {
                    Logger.debug("VIA response: " + response.getBody());
                })
                .exceptionally(throwable -> {
                    Logger.error("VIA error: " + throwable.getMessage());
                    return null;
                });
    }
}
