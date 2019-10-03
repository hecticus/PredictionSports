package controllers;

import modeles.Alta;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.SilverServicio;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class PromocionesController extends Controller {

    private KrakenServicio krakenServicio;
    private SilverServicio silverServicio;

    @Inject
    public PromocionesController(KrakenServicio krakenServicio, SilverServicio silverServicio) {
        this.krakenServicio = krakenServicio;
        this.silverServicio = silverServicio;
    }

    public Result CheckPromotion() throws IOException {
        Map<String, String[]> token = request().queryString();
        Alta alta = new Alta(token.get("clickid")[0], token.get("pid")[0], token.get("msisdn")[0]);
        alta.insert();
        krakenServicio.CrearAlta(alta.getMsisdn(), "9090", "SILVERWEB");
        silverServicio.CrearAlta(alta.getClickid(), alta.getPid());
        return ok(alta.toString());
    }
}
