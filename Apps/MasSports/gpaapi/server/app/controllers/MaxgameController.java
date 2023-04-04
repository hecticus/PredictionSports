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
import views.html.test;

import javax.inject.Inject;
import java.io.IOException;

public class MaxgameController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;
    private WSClient ws;

    private String clickID = "clickid";
    private String ip = "ip";

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
                addClickId(clickValue, "");
            } catch (Exception e) {

            }
        }

        return ok(maxgame_index.render(clickValue, extras));
    }

    public Result test() throws IOException {

        String clickValue = "NA";
        String extras = "NA";


        if (request().queryString().get(clickID) != null && request().queryString().get(clickID).length > 0) {
            clickValue = request().queryString().get(clickID)[0];

            try {
                addClickId(clickValue, "");
            } catch (Exception e) {

            }
        }

        return ok(test.render(clickValue, extras));
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
            learnLiveActivity.setOrigin("TRA");
            learnLiveActivity.setSent(false);
            learnLiveActivity.save();
        }
    }
}
