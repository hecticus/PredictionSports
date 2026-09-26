package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hecticus.gpaapi.domain.ClienteAppland;
import com.hecticus.gpaapi.dto.GetStatusRespuestaDto;
import com.hecticus.gpaapi.dto.PushStatusClientAppLand;
import com.hecticus.gpaapi.service.AppLandServicio;
import com.hecticus.gpaapi.service.ClienteExternoServicio;
import com.hecticus.gpaapi.service.ConfigService;
import com.hecticus.gpaapi.service.KrakenServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class AppLandController {

    private final KrakenServicio krakenServicio;
    private final AppLandServicio applandServicio;
    private final ClienteExternoServicio clienteExternoServicio;
    private final ConfigService configService;
    private final ObjectMapper mapper;
    private final String subscriptionId = "HECTI_MOVIS_U_VE";

    public AppLandController(KrakenServicio krakenServicio,
                             AppLandServicio applandServicio,
                             ClienteExternoServicio clienteExternoServicio,
                             ConfigService configService,
                             ObjectMapper mapper) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
        this.configService = configService;
        this.mapper = mapper;
    }

    @GetMapping("/extapi/login-form")
    public String login(@RequestParam(name = "callback", required = false) String callback,
                        @RequestParam(name = "ott", required = false) String ott,
                        HttpServletResponse response,
                        Model model) {
        if (callback != null) {
            setCookie(response, "callback", callback);
        }
        if (ott != null) {
            setCookie(response, "ott", ott);
        }
        model.addAttribute("error", false);
        return "extapi";
    }

    @GetMapping("/extapi/error/{message}")
    public @ResponseBody String forceError(@PathVariable String message) throws IOException {
        if (message != null) {
            throw new IOException(message);
        }
        return "";
    }

    @PostMapping("/extapi/login")
    public Object loginPost(@RequestParam MultiValueMap<String, String> form,
                            HttpServletRequest request,
                            Model model) {
        String msisdn = form.getFirst("msisdn");
        String contrasena = form.getFirst("contrasena");

        if (msisdn == null || !(msisdn.startsWith("0414") || msisdn.startsWith("0424") || msisdn.startsWith("0434"))) {
            model.addAttribute("error", true);
            return "extapi";
        }

        ClienteAppland clienteAppland = clienteExternoServicio
                .obtenerClienteRenderSincronizadoConKraken(msisdn, contrasena, 6);
        if (clienteAppland != null && contrasena != null && contrasena.equals(clienteAppland.password)) {
            String rutaOpcional = null;
            String extra = "";
            String callback = getCookie(request, "callback");
            if (callback != null) {
                String ott = getCookie(request, "ott");
                extra = ott != null ? "&ott=" + ott : "";
                rutaOpcional = callback;
            }
            String rutaRedirect = applandServicio.obternerRutaDeRedirect(clienteAppland.identifier, rutaOpcional, "HECTI_MOVIS_U_VE");
            rutaRedirect = rutaRedirect + extra;

            PushStatusClientAppLand payload = new PushStatusClientAppLand();
            payload.event = "SUBSCRIBE";
            payload.isEligible = true;
            payload.nextRenewal = 99999999;
            payload.numberOfConcurrentSessions = 1;
            payload.numberOfProfiles = 1;
            payload.user = clienteAppland.identifier;
            applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
            return new RedirectView(rutaRedirect);
        }
        model.addAttribute("error", true);
        return "extapi";
    }

    @GetMapping("/extapi/get-status/subscription/{service}/{msisdn}")
    public @ResponseBody JsonNode getStatus(@PathVariable String service, @PathVariable String msisdn) throws Exception {
        GetStatusRespuestaDto statusRespuesta = applandServicio.generarRespuestaStatus(msisdn);
        if (statusRespuesta == null) {
            return null;
        }
        Gson gson = new Gson();
        return mapper.readTree(gson.toJson(statusRespuesta));
    }

    @GetMapping("/extapi/recover-password")
    public String recoverPassword() {
        return "recover_password";
    }

    @GetMapping("/extapi/login")
    public Object sms(@RequestParam(name = "callback", required = false) String callback,
                      @RequestParam(name = "ott", required = false) String ott,
                      @RequestParam(name = "subscription", required = false) String subscription,
                      HttpServletResponse response,
                      Model model) {
        if (callback != null) {
            setCookie(response, "callback", callback);
        }
        if (ott != null) {
            setCookie(response, "ott", ott);
        }
        if (subscription != null && subscription.equals("HECTI_CIUDA_U_VE")) {
            return new RedirectView("/cj/login");
        }
        model.addAttribute("amount", configService.getString("current-amount"));
        return "appland_sms";
    }

    @GetMapping("/extapi/check-user")
    public @ResponseBody String checkUser() {
        try {
            krakenServicio.obtenerUsuario("4142431600", "10", "9", "6");
            return "{\"status\": 1}";
        } catch (Exception e) {
            return "{\"status\": 0}";
        }
    }

    @GetMapping("/extapi/internal-status/{msisdn}/{status}")
    public @ResponseBody String sendStatus(@PathVariable String msisdn, @PathVariable int status) {
        ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(msisdn);
        if (clienteAppland != null) {
            PushStatusClientAppLand payload = new PushStatusClientAppLand();
            payload.event = status == 1 ? "BILLED_SUCCESS" : "SUBSCRIPTION_END";
            payload.isEligible = true;
            payload.nextRenewal = 99999999;
            payload.numberOfConcurrentSessions = 1;
            payload.numberOfProfiles = 1;
            payload.user = clienteAppland.identifier;
            applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
        }
        return "";
    }

    @GetMapping("/extapi/tyc")
    public String tyc(Model model) {
        model.addAttribute("amount", configService.getString("current-amount"));
        model.addAttribute("dater", configService.getString("date-amount"));
        return "tycappland";
    }

    @GetMapping("/extapi/check-users-disables")
    public @ResponseBody String getDisabledAppLandClients() throws IOException {
        for (var cliente : krakenServicio.obtenerUsuariosDeshabilitadosPorFecha()) {
            ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(cliente.client.msisdn);
            if (clienteAppland != null) {
                PushStatusClientAppLand payload = new PushStatusClientAppLand();
                payload.event = "SUBSCRIPTION_END";
                payload.isEligible = true;
                payload.nextRenewal = 99999999;
                payload.numberOfConcurrentSessions = 99999999;
                payload.numberOfProfiles = 1;
                payload.user = clienteAppland.identifier;
                applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
            }
        }
        return "";
    }

    private void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(15);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
