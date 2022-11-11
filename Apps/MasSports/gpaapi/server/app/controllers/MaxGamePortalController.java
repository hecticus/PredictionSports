package controllers;

import play.mvc.Controller;
import services.appland.AppLandServicio;
import services.client_externo_servicio.ClienteExternoServicio;
import services.dto.PushStatusClientAppLand;
import services.kraken_servicio.KrakenServicio;
import play.mvc.Result;
import views.html.extapi;

import javax.inject.Inject;

public class MaxGamePortalController extends Controller {

    private AppLandServicio applandServicio;
    private String SubcriptionId = "HECTI_MOVIS_U_VE";

    @Inject
    public MaxGamePortalController(AppLandServicio applandServicio) {
        this.applandServicio = applandServicio;
    }

    public Result index(){
        return ok(views.html.portalnvav.index.render());
    }

    public Result access(){
        String rutaRedirect = this.applandServicio.obternerRutaDeRedirect("NAVIDAD_2022", null, SubcriptionId);
//        PushStatusClientAppLand payload = new PushStatusClientAppLand();
//        payload.event = "SUBSCRIBE";
//        payload.isEligible = true;
//        payload.nextRenewal = 99999999;
//        payload.numberOfConcurrentSessions = 1;
//        payload.numberOfProfiles = 1;
//        payload.user = "NAVIDAD_2022";
//
//        this.applandServicio.comunicarStatus("POST", "NAVIDAD_2022", payload, SubcriptionId);
        return redirect(rutaRedirect);
    }
}
