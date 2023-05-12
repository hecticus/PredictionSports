package controllers;

import modeles.PaxxionActivity;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import views.html.klike_index;
import views.html.learnlive_index;

import javax.inject.Inject;
import java.io.IOException;

public class KlikeController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    @Inject
    public KlikeController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.ws = ws;
    }

    public Result index() throws IOException {

        String clickValue = "NA";
        String extras = "NA";
        String origin = "MOB";

        if (request().queryString().get("CLICKID") != null && request().queryString().get("CLICKID").length > 0) {
            clickValue = request().queryString().get("CLICKID")[0];
            extras = (request().queryString().get("SOURCE") != null && request().queryString().get("SOURCE").length > 0) ? request().queryString().get("SOURCE")[0] : "";
        }

        if (request().getQueryString("token") != null && !request().getQueryString("token").equals("")) {
            clickValue = request().getQueryString("token");
            origin = "VIA";
        }

        if (request().getQueryString("tr_token") != null && !request().getQueryString("tr_token").equals("")) {
            clickValue = request().getQueryString("tr_token");
            origin = "TRA";
        }

        return ok(klike_index.render(clickValue, extras, origin));
    }


    public Result mark() throws IOException {
        String clickValue = "NA";
        String extras = "NA";
        String origin = "MOB";

        if (request().queryString().get("CLICKID") != null && request().queryString().get("CLICKID").length > 0) {
            clickValue = request().queryString().get("CLICKID")[0];
            extras = (request().queryString().get("SOURCE") != null && request().queryString().get("SOURCE").length > 0) ? request().queryString().get("SOURCE")[0] : "";
            try {
                addClickId(clickValue + "---" + extras, origin);
            } catch (Exception e) {

            }
        }

        if (request().queryString().get("token") != null) {
            origin = "VIA";
            clickValue = request().queryString().get("token")[0];
            try {
                addClickId(clickValue, origin );
            } catch (Exception e) {

            }
        }

        if (request().queryString().get("tr_token") != null) {
            origin = "TRA";
            clickValue = request().queryString().get("tr_token")[0];
            try {
                addClickId(clickValue, origin );
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

    private void addClickId(String clickId, String origin) {
        PaxxionActivity PaxxionActivity = new PaxxionActivity(clickId);
        PaxxionActivity.setOrigin(origin);
        PaxxionActivity.save();
    }

    //https://smobipiumlink.com/conversion/index.php?jp={CLICKID}&source={SOURCE}

}
