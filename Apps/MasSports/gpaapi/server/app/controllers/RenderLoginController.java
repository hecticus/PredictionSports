package controllers;

import modeles.RenderLogin;
import play.mvc.Controller;
import play.mvc.Result;
import services.kraken_servicio.KrakenServicio;
import services.silver_servicio.SilverServicio;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class RenderLoginController extends Controller {
    private KrakenServicio krakenServicio;
    private SilverServicio silverServicio;

    @Inject
    public RenderLoginController(KrakenServicio krakenServicio, SilverServicio silverServicio) {
        this.krakenServicio = krakenServicio;
        this.silverServicio = silverServicio;
    }

    public Result GetLogin() throws IOException {
        Map<String, String[]> token = request().queryString();
        RenderLogin login = new RenderLogin(token.get("msisdn")[0], token.get("business")[0]);
        login.insert();
        return ok(login.toString());
    }
}
