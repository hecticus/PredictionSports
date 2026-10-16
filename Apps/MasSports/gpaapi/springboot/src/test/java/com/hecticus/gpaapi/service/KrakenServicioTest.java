package com.hecticus.gpaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.integration.HttpGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Unit tests for the gpaapi.disable-provider-events kill switch on Kraken MO events.
 * The flag is new in the Spring Boot rewrite (it does not exist in the legacy Play
 * app), so there is no legacy spec to port.
 */
class KrakenServicioTest {

    private GpaApiProperties properties;
    private HttpGateway http;
    private KrakenServicio service;

    @BeforeEach
    void setUp() {
        properties = new GpaApiProperties();
        http = mock(HttpGateway.class);
        service = new KrakenServicio(http, new ObjectMapper(), properties);
    }

    @Test
    void skipsMoEventWithoutCallingTheProviderWhenDisabled() {
        properties.setDisableProviderEvents(true);

        service.crearAlta("5091234567", "11240", "PING");

        verifyNoInteractions(http);
    }

    @Test
    void sendsMoEventWhenEnabled() throws IOException {
        service.crearAlta("5091234567", "11240", "PING");

        verify(http).get("http://02.kapp.hecticus.com/ws/receiveMO.php"
                + "?source=5091234567&destination=11240&service_type=pacws&msg=PING&received_time=20151118170000");
    }
}
