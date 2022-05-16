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
import views.html.learnlive_index;
import views.html.okmanhattan;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class LearnliveController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    @Inject
    public LearnliveController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.ws = ws;
    }

    public Result index() throws IOException {

        String clickValue = "NA";
        String pubidValue = "NA";
        String origin = "NA";
        String extras = "NA";
        String subExtras = "NA";

        if (request().queryString().get("jp") != null && request().queryString().get("jp").length > 0) {

            clickValue = request().queryString().get("jp")[0];

            if (!clickValue.isEmpty() && clickValue.contains("|")) {
                String[] clickValueTmp = clickValue.split("\\|");
                extras = clickValueTmp[1];
                clickValue = clickValueTmp[0];
                if (clickValueTmp.length > 2) {
                    subExtras = clickValueTmp[2];
                }

            }

            try {
                sendMessage(clickValue, extras);
                addClickId(clickValue);
            } catch (Exception e) {

            }
        }


        return ok(learnlive_index.render());
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
        LearnLiveActivity learnLiveActivity =  new LearnLiveActivity(clickId);
        learnLiveActivity.save();
    }

    //https://smobipiumlink.com/conversion/index.php?jp={CLICKID}&source={SOURCE}

}
