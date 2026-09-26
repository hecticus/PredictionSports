package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.integration.HttpGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ManhattanServicio {

    private static final Logger log = LoggerFactory.getLogger(ManhattanServicio.class);

    private final HttpGateway http;
    private final GpaApiProperties properties;

    public ManhattanServicio(HttpGateway http, GpaApiProperties properties) {
        this.http = http;
        this.properties = properties;
    }

    public void crearAlta(String subId, String status, String msisdn) {
        try {
            http.get(properties.getManhattan().getNotifyUrl()
                    + "?sub_id=" + subId + "&status=" + status + "&msisdn=" + msisdn);
        } catch (Exception e) {
            log.warn("Manhattan crearAlta fallo (ignorado): {}", e.getMessage());
        }
    }
}
