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
    private String userId = "NAVIDAD2022";

    @Inject
    public MaxGamePortalController(AppLandServicio applandServicio) {
        this.applandServicio = applandServicio;
    }

    public Result index(){
        return ok(views.html.portalnvav.index.render());
    }
    
    public Result revoke() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "SUBSCRIPTION_END";
        payload.isEligible = true;
        payload.nextRenewal = 99999999;
        payload.numberOfConcurrentSessions = 1;
        payload.numberOfProfiles = 99999999;
        payload.user = userId;

        this.applandServicio.comunicarStatus("POST", userId, payload, SubcriptionId);
        return ok();
    }

    public Result create() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "SUBSCRIBE";
        payload.isEligible = true;
        payload.nextRenewal = 99999999;
        payload.numberOfConcurrentSessions = 99999999;
        payload.numberOfProfiles = 999999999;
        payload.user = userId;

        this.applandServicio.comunicarStatus("POST", userId, payload, SubcriptionId);
        return ok();
    }

    public Result access(){
        String rutaRedirect = this.applandServicio.obternerRutaDeRedirect(userId, null, SubcriptionId);
        return redirect(rutaRedirect);
    }
}
