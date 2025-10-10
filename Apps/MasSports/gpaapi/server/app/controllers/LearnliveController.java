package controllers;

import modeles.Alta;
import modeles.LearnLiveActivity;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import services.tracking.ClickData;
import services.tracking.ClickParameterExtractor;
import views.html.learnlive_index;
import views.html.okmanhattan;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

/**
 * LearnLive Controller - Refactored with SOLID principles
 * Now supports three origins: MOB, VIA, and TRA
 */
public class LearnliveController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;
    private ClickParameterExtractor parameterExtractor;

    @Inject
    public LearnliveController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
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
            play.Logger.info("LearnLive index - ClickData: " + clickData.toString());
            
            return ok(learnlive_index.render(
                clickData.getClickId(), 
                clickData.getExtras()
            ));
        } catch (Exception e) {
            play.Logger.error("Error in LearnliveController.index()", e);
            // Fallback to defaults on error
            return ok(learnlive_index.render(
                ClickData.DEFAULT_CLICK_VALUE, 
                ClickData.DEFAULT_EXTRAS
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
                play.Logger.info("LearnLive mark - Saving: " + clickData.toString());
                addClickId(clickData.getCombinedValue(), clickData.getOrigin());
            } else {
                play.Logger.warn("LearnLive mark - Invalid click data received");
            }
            
            return ok();
        } catch (Exception e) {
            play.Logger.error("Error in LearnliveController.mark()", e);
            return ok(); // Return OK even on error to not break client flow
        }
    }

    /**
     * Save click ID to database
     * Updated to support origin tracking
     */
    private void addClickId(String clickId, String origin) {
        LearnLiveActivity learnLiveActivity = new LearnLiveActivity(clickId);
        learnLiveActivity.setOrigin(origin);
        learnLiveActivity.save();
        play.Logger.info("Saved LearnLiveActivity: clickId=" + clickId + ", origin=" + origin);
    }
}
