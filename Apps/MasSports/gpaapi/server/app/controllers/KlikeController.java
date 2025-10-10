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
 * Now supports three origins: MOB, VIA, and TRA
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
     * Now supports: CLICKID (MOB), token (VIA), tr_token (TRA)
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
     * Now supports: CLICKID (MOB), token (VIA), tr_token (TRA)
     */
    public Result mark() throws IOException {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request());
            
            if (clickData.isValid()) {
                play.Logger.info("Klike mark - Saving: " + clickData.toString());
                addClickId(clickData.getCombinedValue(), clickData.getOrigin());
            } else {
                play.Logger.warn("Klike mark - Invalid click data received");
            }
            
            return ok();
        } catch (Exception e) {
            play.Logger.error("Error in KlikeController.mark()", e);
            return ok(); // Return OK even on error to not break client flow
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
