package controllers;

import modeles.LearnLiveActivity;
import modeles.LearnLiveRDActivity;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import views.html.learnlive_rd_index;

import javax.inject.Inject;
import java.io.IOException;

public class LearnliveRDController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    @Inject
    public LearnliveRDController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.ws = ws;
    }

    public Result index() throws IOException {

        String clickValue = "NA";
        String extras = "NA";

        if (request().queryString().get("CLICKID") != null && request().queryString().get("CLICKID").length > 0) {
            clickValue = request().queryString().get("CLICKID")[0];
            extras = (request().queryString().get("SOURCE") != null && request().queryString().get("SOURCE").length > 0) ? request().queryString().get("SOURCE")[0] : "";
        }

        return ok(learnlive_rd_index.render(clickValue, extras));
    }


    public Result mark() throws IOException {

        String clickValue = "NA";
        String extras = "NA";

        if (request().queryString().get("CLICKID") != null && request().queryString().get("CLICKID").length > 0) {

            clickValue = request().queryString().get("CLICKID")[0];

            extras = (request().queryString().get("SOURCE") != null && request().queryString().get("SOURCE").length > 0) ? request().queryString().get("SOURCE")[0] : "";

            try {
                addClickId(clickValue + "---" + extras);
            } catch (Exception e) {

            }
        }

        return ok();
    }

    private void sendMessage(String clickId, String source) {
        String call = String.format("https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s", clickId, source);
        System.out.println(call);
        this.ws.url(call)
                .get()
                .thenAccept((WSResponse r) -> {
                    String body = r.getBody();
                });
    }

    private void addClickId(String clickId) {
        LearnLiveRDActivity learnLiveActivity = new LearnLiveRDActivity(clickId);
        learnLiveActivity.save();
    }

    //https://smobipiumlink.com/conversion/index.php?jp={CLICKID}&source={SOURCE}

}
