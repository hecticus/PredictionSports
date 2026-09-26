package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.integration.HttpGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SilverServicio {

    private static final Logger log = LoggerFactory.getLogger(SilverServicio.class);

    private final HttpGateway http;
    private final GpaApiProperties properties;

    public SilverServicio(HttpGateway http, GpaApiProperties properties) {
        this.http = http;
        this.properties = properties;
    }

    public void crearAlta(String clickid, String pid) {
        try {
            http.get(properties.getSilver().getPostbackUrl() + "?clickid=" + clickid + "&pid=" + pid);
        } catch (Exception e) {
            log.warn("Silver crearAlta fallo (ignorado): {}", e.getMessage());
        }
    }
}
