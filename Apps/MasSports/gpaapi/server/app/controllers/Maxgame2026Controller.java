package controllers;

import modeles.MaxgameActivity;
import play.mvc.Controller;
import play.mvc.Result;
import services.tracking.ClickData;
import services.tracking.ClickParameterExtractor;
import views.html.maxgame_2026;

import javax.inject.Inject;

/**
 * MaxGame 2026 landing page controller.
 * Saves the received click id into MaxgameActivity with origin MG2026,
 * both on page load and through the mark endpoint.
 */
public class Maxgame2026Controller extends Controller {

    public static final String ORIGIN = "MG2026";

    private final ClickParameterExtractor parameterExtractor;

    @Inject
    public Maxgame2026Controller() {
        this.parameterExtractor = new ClickParameterExtractor();
    }

    /**
     * Index page - displays the landing page and saves the click id when present
     */
    public Result index() {
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request());
            play.Logger.info("Maxgame2026 index - ClickData: " + clickData.toString());

            if (clickData.isValid()) {
                addClickId(clickData.getCombinedValue());
            }

            return ok(maxgame_2026.render());
        } catch (Exception e) {
            play.Logger.error("Error in Maxgame2026Controller.index()", e);
            return ok(maxgame_2026.render());
        }
    }

    /**
     * Mark endpoint - saves click data to database and returns JSON
     */
    public Result mark() {
        com.fasterxml.jackson.databind.node.ObjectNode result = play.libs.Json.newObject();
        try {
            ClickData clickData = parameterExtractor.extractFromRequest(request());

            if (clickData.isValid()) {
                play.Logger.info("Maxgame2026 mark - Saving: " + clickData.toString());
                addClickId(clickData.getCombinedValue());

                result.put("status", "success");
                result.put("token", clickData.getCombinedValue());
                result.put("origin", ORIGIN);
                result.put("message", "Token saved successfully");
            } else {
                play.Logger.warn("Maxgame2026 mark - Invalid click data received");

                result.put("status", "error");
                result.put("error", "Invalid or missing token");
                result.put("message", "No valid click data found in request");
            }
        } catch (Exception e) {
            play.Logger.error("Error in Maxgame2026Controller.mark()", e);

            result.put("status", "error");
            result.put("error", e.getClass().getSimpleName());
            result.put("message", e.getMessage() != null ? e.getMessage() : "Unknown error occurred");
        }
        return ok(result);
    }

    private void addClickId(String clickId) {
        MaxgameActivity maxgameActivity = MaxgameActivity.finder.where().eq("click_id", clickId).findUnique();
        if (maxgameActivity == null) {
            MaxgameActivity activity = new MaxgameActivity(clickId);
            activity.setOrigin(ORIGIN);
            activity.setSent(false);
            activity.save();
            play.Logger.info("Saved MaxgameActivity: clickId=" + clickId + ", origin=" + ORIGIN);
        }
    }
}
