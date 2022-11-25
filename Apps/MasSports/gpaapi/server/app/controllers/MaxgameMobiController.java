package controllers;

import modeles.MaxgameActivity;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import views.html.maxgame_mobi_index;

import javax.inject.Inject;
import java.io.IOException;

public class MaxgameMobiController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    private String clickID = "CLICKID";
    private String ip = "ip";

    @Inject
    public MaxgameMobiController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio, WSClient ws) {
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

            try {
                addClickId(clickValue, "");
            } catch (Exception e) {

            }
        }


        return ok(maxgame_mobi_index.render(clickValue, extras));
    }

    public Result setip() {

        String clickId = "";
        if (request().queryString().get(clickID) != null && request().queryString().get(clickID).length > 0) {
            clickId = request().queryString().get(clickID)[0];
        }

        String ipID = "";
        if (request().queryString().get(ip) != null && request().queryString().get(ip).length > 0) {
            ipID = request().queryString().get(ip)[0];
        }


        MaxgameActivity maxgameActivity = MaxgameActivity.finder.where().eq("click_id", clickId).findUnique();
        maxgameActivity.setIp(ipID);
        maxgameActivity.update();
        return ok();
    }


    public Result mark() throws IOException {
        System.out.println("LLLLLLLOOOOO");
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


    private void updateClickId(String clickId) {
        System.out.println("LLLLLLL");
        MaxgameActivity maxgameActivity = MaxgameActivity.finder.where().eq("click_id", clickId).findUnique();
        maxgameActivity.setSent(true);
        maxgameActivity.update();
    }

    private void addClickId(String clickId, String ip) {
        MaxgameActivity maxgameActivity = MaxgameActivity.finder.where().eq("click_id", clickId).findUnique();
        if(maxgameActivity == null) {
            MaxgameActivity learnLiveActivity = new MaxgameActivity(clickId);
            learnLiveActivity.setIp(ip);
            learnLiveActivity.setSent(false);
            learnLiveActivity.setOrigin("MOBI");
            learnLiveActivity.save();
        }
    }
}
