package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.gpaapi.domain.Clients;
import com.hecticus.gpaapi.domain.Config;
import com.hecticus.gpaapi.domain.LogEntry;
import com.hecticus.gpaapi.domain.Services;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.repository.ClientsRepository;
import com.hecticus.gpaapi.repository.ConfigRepository;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import com.hecticus.gpaapi.repository.ServicesRepository;
import com.hecticus.gpaapi.service.ConfigService;
import com.hecticus.gpaapi.service.LegacyServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class RegisterController {

    private final ClientsRepository clientsRepository;
    private final LogEntryRepository logRepository;
    private final ServicesRepository servicesRepository;
    private final ConfigRepository configRepository;
    private final ConfigService configService;
    private final HttpGateway http;
    private final ObjectMapper mapper;

    public RegisterController(ClientsRepository clientsRepository,
                              LogEntryRepository logRepository,
                              ServicesRepository servicesRepository,
                              ConfigRepository configRepository,
                              ConfigService configService,
                              HttpGateway http,
                              ObjectMapper mapper) {
        this.clientsRepository = clientsRepository;
        this.logRepository = logRepository;
        this.servicesRepository = servicesRepository;
        this.configRepository = configRepository;
        this.configService = configService;
        this.http = http;
        this.mapper = mapper;
    }

    @GetMapping("/header")
    public ObjectNode checkHeader(HttpServletRequest request) {
        StringBuilder aux = new StringBuilder();
        String msisdn = request.getHeader("MSISDN");
        java.util.Enumeration<String> names = request.getHeaderNames();
        while (names != null && names.hasMoreElements()) {
            String key = names.nextElement();
            aux.append(key).append(" = ").append(request.getHeader(key)).append("\n");
        }
        String aux2 = "";
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                aux2 += " __ " + cookie.getName() + " = " + cookie.getValue();
            }
        }
        return ApiResponse.buildExtendResponse(aux + " - COOOKIES " + aux2);
    }

    @PostMapping({"/clienteExterno/getPin", "/clienteExterno/getpin"})
    public ObjectNode getPin(@RequestBody JsonNode json) {
        Services ser = LegacyServices.getServiceByName(text(json, "product"));
        String msisdn = text(json, "msisdn");
        msisdn = (msisdn != null && msisdn.startsWith("507") ? "" : "507") + msisdn;
        String pixel = text(json, "pixel");

        Clients cli = clientsRepository.findByMsisdnAndConfirm(parseLong(msisdn), pixel).orElse(null);
        boolean isNew = cli == null;
        if (cli == null) {
            cli = new Clients();
        }

        String idtok = "";
        if (json.has("idtrx")) {
            idtok = json.get("idtrx").asText();
        }
        if (json.has("IDTRX")) {
            idtok = json.get("IDTRX").asText();
        }
        cli.setToken(idtok);
        cli.setMsisdn(parseLong(msisdn));
        cli.setService(ser);
        cli.setConfirm(pixel);
        cli.setLastUpdate(LocalDateTime.now());

        if (ser == null) {
            return ApiResponse.accessDenied();
        }

        ObjectNode event = mapper.createObjectNode();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("texto", ser.getSms());

        if (isNew) {
            clientsRepository.save(cli);
        } else {
            clientsRepository.save(cli);
        }

        try {
            http.postJson(configService.getString("silver-api-url") + "api/v1/user/generarPin", event);
        } catch (Exception e) {
            return ApiResponse.accessDenied();
        }
        return ApiResponse.buildExtendResponse("Valid");
    }

    @PostMapping("/clienteExterno/confirm")
    public ObjectNode confirmPin(@RequestBody JsonNode json) {
        Services ser = LegacyServices.getServiceByName(text(json, "product"));
        String msisdn = text(json, "msisdn");
        msisdn = (msisdn != null && msisdn.startsWith("507") ? "" : "507") + msisdn;
        String pixel = text(json, "pixel");

        Clients cli = clientsRepository.findByMsisdnAndConfirm(parseLong(msisdn), pixel).orElse(null);
        if (cli == null) {
            return ApiResponse.accessDenied();
        }
        if (ser == null) {
            return ApiResponse.accessDenied();
        }

        ObjectNode event = mapper.createObjectNode();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("pin", text(json, "pin"));

        boolean response;
        try {
            JsonNode jsonr = http.postJson(configService.getString("silver-api-url") + "api/v1/user/confirmarPin", event);
            response = "0".equals(jsonr.get("response").get("code").asText());
            if (response) {
                toKraken(msisdn);
                if (cli.getToken() != null && !cli.getToken().isEmpty()) {
                    callWithTokenGlobality(cli.getToken());
                }
            }
        } catch (Exception e) {
            return ApiResponse.buildExtendResponse("Internal Error");
        }
        return ApiResponse.buildExtendResponse(response ? "Valid" : "Invalid");
    }

    public void toKraken(String msisdn) {
        try {
            http.get("http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn
                    + "&destination=9090&service_type=pacws&msg=GLOBALWEB&received_time=20151118170000");
        } catch (Exception ignored) {
        }
    }

    public void callWithTokenGlobality(String token) {
        try {
            http.get(configService.getString("globality-url") + token);
        } catch (Exception ignored) {
        }
    }

    @GetMapping("/pal/config")
    public String config() {
        Services ser = new Services("md", "1", "GANA @pin", 9090, "COPA", "");
        servicesRepository.save(ser);

        Config con = new Config();
        con.setConfigKey("silver-api-url");
        con.setValue("http://silverapi.hecticus.com/");
        configRepository.save(con);

        con = new Config();
        con.setConfigKey("globality-url");
        con.setValue("http://ad.globadlity.com/wss2s.asmx/ConfirmS2S?trx_id=");
        configRepository.save(con);
        return "";
    }

    @GetMapping("/pal/test")
    public String test() {
        LogEntry entry = new LogEntry();
        entry.setIdentifier("TEST");
        entry.setExtra(String.format("TEST: %s", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        entry.setMsisdn("353805");
        entry.setLastUpdate(LocalDateTime.now());
        logRepository.save(entry);
        return "";
    }

    private String text(JsonNode json, String field) {
        return json.has(field) && !json.get(field).isNull() ? json.get(field).asText() : null;
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }
}
