package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.hecticus.gpaapi.domain.CiudadJuegoActivity;
import com.hecticus.gpaapi.domain.ClienteAppland;
import com.hecticus.gpaapi.dto.GetStatusRespuestaDto;
import com.hecticus.gpaapi.dto.PushStatusClientAppLand;
import com.hecticus.gpaapi.service.DigitelServicio;
import com.hecticus.gpaapi.repository.CiudadJuegoActivityRepository;
import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.service.AppLandServicio;
import com.hecticus.gpaapi.service.ClienteExternoServicio;
import com.hecticus.gpaapi.service.ConfigService;
import com.hecticus.gpaapi.service.ConversionService;
import com.hecticus.gpaapi.service.KrakenServicio;
import com.hecticus.gpaapi.service.crypto.EncryptServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CiudadJuegoApplandController {

    private static final Logger log = LoggerFactory.getLogger(CiudadJuegoApplandController.class);

    private static final String[] CLICK_ID_PARAMS = {"click_id", "clickid", "clickId"};
    private static final String SUBSCRIPTION_ID = "HECTI_CIUDA_U_VE";

    private final KrakenServicio krakenServicio;
    private final AppLandServicio applandServicio;
    private final ClienteExternoServicio clienteExternoServicio;
    private final DigitelServicio digitelServicio;
    private final CiudadJuegoActivityRepository activityRepository;
    private final ConfigService configService;
    private final ConversionService conversionService;
    private final GpaApiProperties properties;
    private final ObjectMapper mapper;

    public CiudadJuegoApplandController(KrakenServicio krakenServicio,
                                        AppLandServicio applandServicio,
                                        ClienteExternoServicio clienteExternoServicio,
                                        DigitelServicio digitelServicio,
                                        CiudadJuegoActivityRepository activityRepository,
                                        ConfigService configService,
                                        ConversionService conversionService,
                                        GpaApiProperties properties,
                                        ObjectMapper mapper) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
        this.digitelServicio = digitelServicio;
        this.activityRepository = activityRepository;
        this.configService = configService;
        this.conversionService = conversionService;
        this.properties = properties;
        this.mapper = mapper;
    }

    private String getClickIdFromRequest(HttpServletRequest request) {
        for (String paramName : CLICK_ID_PARAMS) {
            String value = request.getParameter(paramName);
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return "NA";
    }

    @GetMapping("/cj/test/{msisdn}")
    public @ResponseBody String loginTest(@PathVariable String msisdn) {
        var response = digitelServicio.validar(msisdn, "9424");
        return response == null ? "null" : response.toString();
    }

    @GetMapping("/cj/test")
    public RedirectView loginRedirect() {
        return new RedirectView("https://gprs.digitel.com.ve/contenido/subscription?idSc=9424&ac=reg");
    }

    @GetMapping({"/cj", "/cj/", "/cj/login"})
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        String tmp = getCookie(request, "X-msisdn");
        if (tmp != null && digitelServicio.validarMsisdn(tmp)) {
            return getResult(tmp, response);
        }
        String header = request.getHeader("X-msisdn");
        if (header != null && digitelServicio.validarMsisdn(header)) {
            return getResult(header, response);
        }
        tmp = getCookie(request, "msisdn");
        if (tmp != null && digitelServicio.validarMsisdn(tmp)) {
            return getResult(tmp, response);
        }
        header = request.getHeader("msisdn");
        if (header != null && digitelServicio.validarMsisdn(header)) {
            return getResult(header, response);
        }
        String tel = request.getParameter("tel");
        if (tel != null && !tel.isEmpty()) {
            return doRedirectFromDigitel(tel, response);
        }
        return new RedirectView("https://gprs.digitel.com.ve/contenido/subscription?idSc=9424&ac=reg");
    }

    @PostMapping({"/cj", "/cj/", "/cj/login"})
    public Object loginPost(@RequestParam MultiValueMap<String, String> form,
                            HttpServletResponse response,
                            Model model) throws IOException {
        String msisdn = form.getFirst("msisdn");
        String contrasena = form.getFirst("contrasena");
        ClienteAppland clienteAppland = clienteExternoServicio
                .obtenerClienteRenderSincronizadoConKraken(msisdn, contrasena, 6);
        if (clienteAppland != null && contrasena != null && contrasena.equals(clienteAppland.password)) {
            return goToCiudadjuego(msisdn, response);
        }
        model.addAttribute("error", true);
        return "ciudadjuego/login";
    }

    @GetMapping("/cj/{id}/{reg}/{msisdn}")
    public Object redirectFromDigitel(@PathVariable String id,
                                      @PathVariable String reg,
                                      @PathVariable String msisdn,
                                      HttpServletResponse response) {
        return doRedirectFromDigitel(msisdn, response);
    }

    private Object doRedirectFromDigitel(String msisdn, HttpServletResponse response) {
        String decoded = new BigInteger(msisdn, 36).toString();
        return getResult(decoded, response);
    }

    private Object getResult(String msisdn, HttpServletResponse response) {
        return goToCiudadjuego(msisdn, response);
    }

    private Object goToCiudadjuego(String msisdn, HttpServletResponse response) {
        try {
            String route = "https://www.ciudadjuego.com/dashboard?msisdn=" + msisdn + "&identifier=";
            String encrypt = EncryptServicio.encrypt(msisdn);
            String encodedEncrypt = URLEncoder.encode(encrypt, StandardCharsets.UTF_8.toString());
            route = route + encodedEncrypt;
            setCookie(response, "msisdn", msisdn);
            return new RedirectView(route);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/cj/get-status/subscription/{service}/{msisdn}")
    public @ResponseBody JsonNode getStatus(@PathVariable String service, @PathVariable String msisdn) throws Exception {
        GetStatusRespuestaDto statusRespuesta = applandServicio.generarRespuestaStatus(msisdn);
        if (statusRespuesta == null) {
            return null;
        }
        Gson gson = new Gson();
        return mapper.readTree(gson.toJson(statusRespuesta));
    }

    @GetMapping("/cj/recover-password")
    public String recoverPassword() {
        return "ciudadjuego/recover_password";
    }

    @GetMapping("/cj/sms")
    public String sms(@RequestParam(name = "callback", required = false) String callback,
                      @RequestParam(name = "ott", required = false) String ott,
                      HttpServletResponse response,
                      Model model) {
        if (callback != null) {
            setCookie(response, "callback", callback);
        }
        if (ott != null) {
            setCookie(response, "ott", ott);
        }
        model.addAttribute("amount", configService.getString("current-amount"));
        return "ciudadjuego/sms";
    }

    @GetMapping("/cj/check-user")
    public @ResponseBody String checkUser() {
        try {
            krakenServicio.obtenerUsuario("4142431600", "10", "9", "6");
            return "{\"status\": 1}";
        } catch (Exception e) {
            return "{\"status\": 0}";
        }
    }

    @GetMapping("/cj/tyc")
    public String tyc(Model model) {
        model.addAttribute("amount", configService.getString("appland-current-amount"));
        model.addAttribute("dater", configService.getString("appland-date-amount"));
        return "ciudadjuego/tyc";
    }

    @GetMapping("/cj/check-users-disables")
    public @ResponseBody ObjectNode getDisabledAppLandClients() throws IOException {
        List<ClienteAppland> clientesapp = new ArrayList<>();
        for (var cliente : krakenServicio.obtenerUsuariosDeshabilitadosPorFecha()) {
            ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(cliente.client.msisdn);
            if (clienteAppland != null) {
                PushStatusClientAppLand payload = new PushStatusClientAppLand();
                payload.event = "SUBSCRIPTION_END";
                payload.isEligible = true;
                payload.nextRenewal = 99999999;
                payload.numberOfConcurrentSessions = 1;
                payload.numberOfProfiles = 1;
                payload.user = clienteAppland.identifier;
                applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, SUBSCRIPTION_ID);
                clientesapp.add(clienteAppland);
            }
        }
        ObjectNode result = mapper.createObjectNode();
        ArrayNode array = mapper.valueToTree(clientesapp);
        result.set("Clientes", array);
        return result;
    }

    @GetMapping("/landing")
    public String landing(HttpServletRequest request, Model model) {
        String clickValue = getClickIdFromRequest(request);
        if (!"NA".equals(clickValue)) {
            try {
                addClickId(clickValue, "");
            } catch (Exception ignored) {
            }
        }
        model.addAttribute("used", false);
        model.addAttribute("clickValue", clickValue);
        return "ciudadjuego/landing_new";
    }

    @GetMapping("/mark_ciudadjuego")
    public @ResponseBody String markCiudadjuego(HttpServletRequest request) {
        String clickValue = getClickIdFromRequest(request);
        if (!"NA".equals(clickValue)) {
            try {
                updateClickId(clickValue);
                sendTrafficPostback(clickValue);
            } catch (Exception e) {
                log.error("Error marking click ID: {}", e.getMessage());
            }
        }
        return "";
    }

    private void sendTrafficPostback(String clickId) {
        try {
            conversionService.sendToTrafficCompany("", properties.getConversion().getMaxgameHandler(),
                    properties.getConversion().getMaxgameHash(), clickId);
        } catch (Exception e) {
            log.error("Traffic postback failed for clickId {}: {}", clickId, e.getMessage());
        }
    }

    private void updateClickId(String clickId) {
        activityRepository.findFirstByClickIdOrderByIdDesc(clickId).ifPresent(activity -> {
            activity.setUsed(true);
            activityRepository.save(activity);
        });
    }

    private void addClickId(String clickId, String ip) {
        if (activityRepository.findFirstByClickIdOrderByIdDesc(clickId).isEmpty()) {
            CiudadJuegoActivity activity = new CiudadJuegoActivity();
            activity.setClickId(clickId);
            activity.setDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            activity.setIp(ip);
            activity.setOrigin("CJ");
            activity.setUsed(false);
            activityRepository.save(activity);
        }
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
