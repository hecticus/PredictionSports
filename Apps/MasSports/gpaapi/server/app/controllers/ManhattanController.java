package controllers;

import modeles.Alta;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.ManhattanServicio;
import services.silver_servicio.SilverServicio;
import views.html.okmanhattan;
import views.html.okmanhattan_Scope0;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class ManhattanController extends Controller {

    private KrakenServicio krakenServicio;
    private ManhattanServicio manhattanServicio;

    @Inject
    public ManhattanController(KrakenServicio krakenServicio, ManhattanServicio manhattanServicio) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
    }

    public Result CheckPromotion() throws IOException {
        try {
            Map<String, String[]> token = request().queryString();
            Alta alta = new Alta("MANHATTAN", token.get("sub_id")[0], token.get("status")[0], token.get("msisdn")[0]);
            // String modo, String clickid, String pid, String msisdn
            alta.insert();

            if(alta.getPid().equals("0")) {
                krakenServicio.CrearAlta(alta.getMsisdn(), "9090", "MANWEB");
                manhattanServicio.CrearAlta(alta.getClickid(), alta.getPid(), alta.getMsisdn());
            } else {
                return ok("{\"status\": 0}");
            }

            return ok(okmanhattan.render());

        } catch (Exception e) {
            FakeAltaTest();
            return ok("{\"status\": 0}");
        }
    }

    private void FakeAltaTest() {
        try {
            Alta alta = new Alta("MANHATTAN", "ERROR", "ERROR", "ERROR");
            alta.insert();
        }  catch (Exception e) {

        }
    }
}
