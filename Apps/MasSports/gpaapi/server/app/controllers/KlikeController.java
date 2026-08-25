package controllers;

import modeles.PaxxionActivity;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import services.tracking.ClickData;
import services.tracking.ClickParameterExtractor;
import views.html.klike_index;
import views.html.learnlive_index;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Klike/Paxxion Controller - Refactored with SOLID principles
 * Now supports origins: MOB, VIA, TRA, SEXY, CHAT
 */
public class KlikeController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;
    private ClickParameterExtractor parameterExtractor;

    @Inject
    public KlikeController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.ws = ws;
        this.parameterExtractor = new ClickParameterExtractor();
    }

    /**
     * Index page - displays the click landing page
     * Now supports: CLICKID (MOB), token (VIA), tr_token (TRA), transaction_id (SEXY), mobidea_id (CHAT)
     */
    public Result index() throws IOException {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request());
            play.Logger.info("Klike index - ClickData: " + clickData.toString());
            
            return ok(klike_index.render(
                clickData.getClickId(), 
                clickData.getExtras(), 
                clickData.getOrigin()
            ));
        } catch (Exception e) {
            play.Logger.error("Error in KlikeController.index()", e);
            // Fallback to defaults on error
            return ok(klike_index.render(
                ClickData.DEFAULT_CLICK_VALUE, 
                ClickData.DEFAULT_EXTRAS, 
                ClickData.ORIGIN_MOBILE
            ));
        }
    }

    /**
     * Mark endpoint - saves click data to database
     * Now supports: CLICKID (MOB), token (VIA), tr_token (TRA), transaction_id (SEXY), mobidea_id (CHAT)
     * Returns JSON with status and token information
     */
    public Result mark() throws IOException {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request());
            
            if (clickData.isValid()) {
                play.Logger.info("Klike mark - Saving: " + clickData.toString());
                addClickId(clickData.getCombinedValue(), clickData.getOrigin());
                
                // Return success JSON
                com.fasterxml.jackson.databind.node.ObjectNode result = play.libs.Json.newObject();
                result.put("status", "success");
                result.put("token", clickData.getCombinedValue());
                result.put("origin", clickData.getOrigin());
                result.put("message", "Token saved successfully");
                return ok(result);
            } else {
                play.Logger.warn("Klike mark - Invalid click data received");
                
                // Return error JSON for invalid data
                com.fasterxml.jackson.databind.node.ObjectNode result = play.libs.Json.newObject();
                result.put("status", "error");
                result.put("error", "Invalid or missing token");
                result.put("message", "No valid click data found in request");
                return ok(result);
            }
        } catch (Exception e) {
            play.Logger.error("Error in KlikeController.mark()", e);
            
            // Return error JSON on exception
            com.fasterxml.jackson.databind.node.ObjectNode result = play.libs.Json.newObject();
            result.put("status", "error");
            result.put("error", e.getClass().getSimpleName());
            result.put("message", e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
            return ok(result);
        }
    }

    /**
     * Save click ID to database
     * Updated to support origin tracking
     */
    private void addClickId(String clickId, String origin) {
        PaxxionActivity paxxionActivity = new PaxxionActivity(clickId);
        paxxionActivity.setOrigin(origin);
        paxxionActivity.save();
        play.Logger.info("Saved PaxxionActivity: clickId=" + clickId + ", origin=" + origin);
    }

    //https://smobipiumlink.com/conversion/index.php?jp={CLICKID}&source={SOURCE}

}
