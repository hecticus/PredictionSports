package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.dto.PushStatusClientAppLand;
import com.hecticus.gpaapi.service.AppLandServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MaxGamePortalController {

    private static final String SUBSCRIPTION_ID = "HECTI_MOVIS_U_VE";
    private static final String USER_ID = "NAVIDAD2022";

    private final AppLandServicio applandServicio;

    public MaxGamePortalController(AppLandServicio applandServicio) {
        this.applandServicio = applandServicio;
    }

    @GetMapping("/navidad")
    public String index() {
        return "portalnvav/index";
    }

    @GetMapping("/portalrevoke")
    public @ResponseBody String revoke() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "SUBSCRIPTION_END";
        payload.isEligible = true;
        payload.nextRenewal = 99999999;
        payload.numberOfConcurrentSessions = 1;
        payload.numberOfProfiles = 99999999;
        payload.user = USER_ID;
        applandServicio.comunicarStatus("POST", USER_ID, payload, SUBSCRIPTION_ID);
        return "";
    }

    @GetMapping("/portalcreate")
    public @ResponseBody String create() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "SUBSCRIBE";
        payload.isEligible = true;
        payload.nextRenewal = 99999999;
        payload.numberOfConcurrentSessions = 99999999;
        payload.numberOfProfiles = 999999999;
        payload.user = USER_ID;
        applandServicio.comunicarStatus("POST", USER_ID, payload, SUBSCRIPTION_ID);
        return "";
    }

    @GetMapping("/portal_access")
    public RedirectView access() {
        String rutaRedirect = applandServicio.obternerRutaDeRedirect(USER_ID, null, SUBSCRIPTION_ID);
        return new RedirectView(rutaRedirect);
    }
}
