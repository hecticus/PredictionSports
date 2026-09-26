package com.hecticus.gpaapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.dto.ClienteExternoWebEntity;
import com.hecticus.gpaapi.dto.ClienteServicioDisableListResponseDto;
import com.hecticus.gpaapi.integration.HttpGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class KrakenServicio {

    private static final Logger log = LoggerFactory.getLogger(KrakenServicio.class);

    private final HttpGateway http;
    private final ObjectMapper mapper;
    private final GpaApiProperties properties;

    public KrakenServicio(HttpGateway http, ObjectMapper mapper, GpaApiProperties properties) {
        this.http = http;
        this.mapper = mapper;
        this.properties = properties;
    }

    public void crearAlta(String msisdn, String numeroCorto, String msg) {
        try {
            http.get(properties.getKraken().getEventsUrl()
                    + "?source=" + msisdn
                    + "&destination=" + numeroCorto
                    + "&service_type=pacws&msg=" + msg
                    + "&received_time=20151118170000");
        } catch (Exception e) {
            log.warn("Kraken crearAlta fallo (ignorado): {}", e.getMessage());
        }
    }

    public ClienteExternoWebEntity obtenerUsuario(String msisdn, String business, String carrier, String country) throws IOException {
        if (msisdn.startsWith("0412")) {
            msisdn = msisdn.replace("0412", "58412");
        }
        JsonNode response = http.getJson(properties.getKraken().getBaseUrl()
                + "/" + msisdn + "/" + business + "/" + carrier + "/" + country);
        if (response.has("response") && response.get("response").isArray()) {
            for (final JsonNode objNode : response.get("response")) {
                return mapper.readValue(objNode.toString(), ClienteExternoWebEntity.class);
            }
        }
        return null;
    }

    public ClienteExternoWebEntity obtenerUsuario(String msisdn, String password, int country) throws IOException {
        if (msisdn.startsWith("0412")) {
            msisdn = msisdn.replace("0412", "58412");
        }
        JsonNode response = http.getJson(properties.getKraken().getBaseUrl()
                + "-recover/" + msisdn + "/" + country + "/" + password);
        response = response.get("response");
        if (response != null && response.has("service")) {
            ClienteExternoWebEntity aux = mapper.readValue(response.get("client").toString(), ClienteExternoWebEntity.class);
            aux.password = response.get("service").get("password").asText();
            return aux;
        }
        return null;
    }

    public ClienteExternoWebEntity obtenerUsuario(String msisdn) throws IOException {
        if (msisdn.startsWith("0412")) {
            msisdn = msisdn.replace("0412", "58412");
        }
        JsonNode response = http.getJson(properties.getKraken().getBaseUrl() + "/" + msisdn + "/6");
        response = response.get("response");
        if (response != null && response.has("myList")) {
            for (final JsonNode objNode : response.get("myList")) {
                ClienteExternoWebEntity value = mapper.readValue(objNode.toString(), ClienteExternoWebEntity.class);
                if (value.status == 1) {
                    return value;
                }
            }
        }
        return null;
    }

    public List<ClienteServicioDisableListResponseDto> obtenerUsuariosDeshabilitadosPorFecha() throws IOException {
        Instant before = Instant.now().minus(Duration.ofDays(1));
        Date dateBefore = Date.from(before);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(dateBefore);

        JsonNode response = http.getJson(properties.getKraken().getBaseUrl() + "/disable-list/6/" + date);
        if (response.get("response") != null && response.get("response").has("clients")) {
            Gson gson = new Gson();
            ClienteServicioDisableListResponseDto[] value =
                    gson.fromJson(response.get("response").get("clients").toString(),
                            ClienteServicioDisableListResponseDto[].class);
            return new ArrayList<>(Arrays.asList(value));
        }
        return new ArrayList<>();
    }
}
