package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.integration.HttpGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Unit tests for the gpaapi.disable-provider-events kill switch on the Manhattan
 * notify call. The flag is new in the Spring Boot rewrite (it does not exist in
 * the legacy Play app), so there is no legacy spec to port.
 */
class ManhattanServicioTest {

    private GpaApiProperties properties;
    private HttpGateway http;
    private ManhattanServicio service;

    @BeforeEach
    void setUp() {
        properties = new GpaApiProperties();
        http = mock(HttpGateway.class);
        service = new ManhattanServicio(http, properties);
    }

    @Test
    void skipsNotifyWithoutCallingTheProviderWhenDisabled() {
        properties.setDisableProviderEvents(true);

        service.crearAlta("sub1", "1", "5091234567");

        verifyNoInteractions(http);
    }

    @Test
    void notifiesWhenEnabled() throws IOException {
        service.crearAlta("sub1", "1", "5091234567");

        verify(http).get("http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify"
                + "?sub_id=sub1&status=1&msisdn=5091234567");
    }
}
