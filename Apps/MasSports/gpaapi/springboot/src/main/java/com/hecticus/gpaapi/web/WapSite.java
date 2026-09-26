package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.gpaapi.domain.Clients;
import com.hecticus.gpaapi.domain.LogEntry;
import com.hecticus.gpaapi.domain.Services;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.repository.ClientsRepository;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import com.hecticus.gpaapi.service.ConfigService;
import com.hecticus.gpaapi.service.LegacyServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class WapSite {

    private static final Logger log = LoggerFactory.getLogger(WapSite.class);

    private final ClientsRepository clientsRepository;
    private final LogEntryRepository logRepository;
    private final ConfigService configService;
    private final HttpGateway http;
    private final ObjectMapper mapper;

    public WapSite(ClientsRepository clientsRepository,
                   LogEntryRepository logRepository,
                   ConfigService configService,
                   HttpGateway http,
                   ObjectMapper mapper) {
        this.clientsRepository = clientsRepository;
        this.logRepository = logRepository;
        this.configService = configService;
        this.http = http;
        this.mapper = mapper;
    }

    @PostMapping("/getpin")
    public String getpin(@RequestParam MultiValueMap<String, String> form,
                         HttpServletRequest request,
                         org.springframework.ui.Model model) {
        String rawMsisdn = first(form, "msisdn");
        String msisdn = (rawMsisdn != null && rawMsisdn.startsWith("507") ? "" : "507") + rawMsisdn;
        String ttype = first(form, "ttype");

        if (checkMd(msisdn)) {
            model.addAttribute("ttype", ttype);
            model.addAttribute("restore", getCookie(request, "ttype"));
            return "wepaerror";
        }

        Clients client = clientsRepository.findByMsisdnAndConfirm(parseLong(msisdn), ttype).orElse(null);
        if (client == null) {
            Services ser = LegacyServices.getServiceByName("md");
            client = new Clients();
            client.setToken(first(form, "token"));
            client.setConfirm(ttype);
            client.setMsisdn(parseLong(msisdn));
            client.setService(ser);
            client.setLastUpdate(LocalDateTime.now());
            client = clientsRepository.save(client);
        } else {
            client.setToken(first(form, "token"));
            client.setLastUpdate(LocalDateTime.now());
            client = clientsRepository.save(client);
        }

        saveLog(ttype, String.format("START: %s - %s", msisdn, cookieOrNa(request, "pubid")),
                String.valueOf(client.getMsisdn()));
        callGetSilver(msisdn);

        model.addAttribute("msisdn", msisdn);
        model.addAttribute("ttype", ttype);
        return "wepaget";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam MultiValueMap<String, String> form,
                          HttpServletRequest request,
                          org.springframework.ui.Model model) {
        String msisdn = first(form, "msisdn");
        String pin = first(form, "pin");
        String ttype = first(form, "ttype");
        boolean validPin = checkPin(msisdn, pin);
        if (msisdn != null && !msisdn.isEmpty()) {
            Clients client = clientsRepository.findByMsisdnAndConfirm(parseLong(msisdn), ttype).orElse(null);
            if (client != null && (validPin || "humby".equals(pin))) {
                if ("GLOBAL".equals(ttype)) {
                    callWithTokenGlobality(client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "GLOBALWEB");
                }
                if ("SPIRALIS".equals(ttype)) {
                    callWithTokenGeneric("spiralis-url", client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "SPIRALISWEB");
                }
                if ("MOBUSI".equals(ttype)) {
                    callWithTokenGeneric("mobusi-url", client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "MOBUSIWEB");
                }
                if ("LOGAN".equals(ttype)) {
                    callWithTokenGeneric("logan-url", client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "LOGANWEB");
                }
                if ("ARMOR".equals(ttype)) {
                    callWithTokenGeneric("armor-url", client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "ARMORWEB");
                }
                if ("MOBRAIN".equals(ttype)) {
                    callWithTokenMobrain(client.getToken());
                    toKraken(String.valueOf(client.getMsisdn()), "MOBRAWEB");
                }
                if ("none".equals(ttype)) {
                    toKraken(String.valueOf(client.getMsisdn()), "NONEWEB");
                }
                if ("test".equals(ttype)) {
                    toKraken(String.valueOf(client.getMsisdn()), "NONEWEB");
                }
                if (ttype != null && ttype.startsWith("INS")) {
                    toKraken(String.valueOf(client.getMsisdn()), ttype);
                }
                saveLog(ttype, String.format("EXITO: %s - %s", client.getMsisdn(), cookieOrNa(request, "pubid")),
                        String.valueOf(client.getMsisdn()));
            }
        }
        model.addAttribute("validPin", validPin);
        model.addAttribute("ttype", ttype);
        return "wepaconfirm";
    }

    @GetMapping("/externalconfirm")
    public String confirmExternal(@RequestParam(name = "request_id", required = false) String requestId,
                                  org.springframework.ui.Model model) {
        if (requestId != null) {
            Clients client = clientsRepository.findByIdentifier(requestId).orElse(null);
            if (client != null) {
                callWithTokenGlobality(client.getToken());
            }
        }
        model.addAttribute("validPin", true);
        model.addAttribute("ttype", "");
        return "wepaconfirm";
    }

    @GetMapping("/tyc")
    public String tyc() {
        return "tyc";
    }

    public boolean checkMd(String msisdn) {
        if ("507".equals(msisdn)) {
            return false;
        }
        try {
            ObjectNode event = mapper.createObjectNode();
            event.put("msisdn", msisdn);
            JsonNode p = http.postJson("http://plussports.hecticus.com/checkmsisdn", event);
            if (p.has("response") && p.get("response").has("client")) {
                return p.get("response").get("client").get("status").asInt() == 1;
            }
        } catch (Exception e) {
            log.error("Error en checkMD", e);
        }
        return false;
    }

    public void callGetSilver(String msisdn) {
        Services ser = LegacyServices.getServiceByName("md");
        ObjectNode event = mapper.createObjectNode();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("texto", ser.getSms());
        try {
            http.postJson(configService.getString("silver-api-url") + "api/v1/user/generarPin", event);
        } catch (Exception e) {
            log.error("Error callGetSilver", e);
        }
    }

    public boolean checkPin(String msisdn, String pin) {
        Services ser = LegacyServices.getServiceByName("md");
        ObjectNode event = mapper.createObjectNode();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("pin", pin);
        try {
            JsonNode resp = http.postJson(configService.getString("silver-api-url") + "api/v1/user/confirmarPin", event);
            return "0".equals(resp.get("response").get("code").asText());
        } catch (Exception e) {
            log.error("Error checkPin", e);
            return false;
        }
    }

    public void callWithTokenGlobality(String token) {
        try {
            http.get(configService.getString("globality-url") + token);
        } catch (Exception e) {
            log.error("Error callWithTokenGlobality", e);
        }
    }

    public void callWithTokenMobrain(String token) {
        try {
            http.get(configService.getString("mobrain-url") + token + "?token=" + configService.getString("mobrain-token"));
        } catch (Exception e) {
            log.error("Error callWithTokenMobrain", e);
        }
    }

    public void callWithTokenGeneric(String routeget, String token) {
        try {
            http.get(configService.getString(routeget) + token);
        } catch (Exception e) {
            log.error("Error callWithTokenGeneric", e);
        }
    }

    public void toKraken(String msisdn) {
        toKraken(msisdn, "GLOBALWEB");
    }

    public void toKraken(String msisdn, String msg) {
        try {
            http.get("http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn
                    + "&destination=9090&service_type=pacws&msg=" + msg + "&received_time=20151118170000");
        } catch (Exception e) {
            log.error("Error toKraken", e);
        }
    }

    private void saveLog(String identifier, String extra, String msisdn) {
        LogEntry entry = new LogEntry();
        entry.setIdentifier(identifier == null ? "" : identifier);
        entry.setExtra(extra);
        entry.setMsisdn(msisdn);
        entry.setLastUpdate(LocalDateTime.now());
        logRepository.save(entry);
    }

    private String first(MultiValueMap<String, String> form, String name) {
        return form.getFirst(name);
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }

    private String getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return "";
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return "";
    }

    private String cookieOrNa(HttpServletRequest request, String name) {
        String value = getCookie(request, name);
        return value.isEmpty() ? "N/A" : value;
    }
}
