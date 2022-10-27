package controllers;

import modeles.BliveActivity;
import modeles.LearnLiveActivity;
import modeles.MaxgameActivity;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import views.html.maxgame_index;

import javax.inject.Inject;
import java.io.IOException;

public class MaxgameController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    private String clickID = "clickid";

    @Inject
    public MaxgameController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.ws = ws;
    }

    public Result index() throws IOException {

        String clickValue = "NA";
        String extras = "NA";

        if (request().queryString().get(clickID) != null && request().queryString().get(clickID).length > 0) {
            clickValue = request().queryString().get(clickID)[0];

            try {
                addClickId(clickValue);
            } catch (Exception e) {

            }
        }

        return ok(maxgame_index.render(clickValue, extras));
    }


    public Result mark() throws IOException {
        String clickValue = "NA";

        if (request().queryString().get(clickID) != null && request().queryString().get(clickID).length > 0) {
            clickValue = request().queryString().get(clickID)[0];

            try {
                updateClickId(clickValue);
            } catch (Exception e) {

            }
        }
        return ok();
    }

    private void sendMessage(String clickId, String source) {
        String call = String.format("https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s", clickId, source);
        this.ws.url(call)
            .get()
            .thenAccept((WSResponse r) -> {
                String body = r.getBody();
            });
    }

    private void updateClickId(String clickId) {
        MaxgameActivity maxgameActivity = MaxgameActivity.finder.where().eq("click_id", clickId).findUnique();
        maxgameActivity.setSent(true);
        maxgameActivity.update();
    }

    private void addClickId(String clickId) {
        MaxgameActivity learnLiveActivity = new MaxgameActivity(clickId);
        learnLiveActivity.setSent(false);
        learnLiveActivity.save();
    }
}
