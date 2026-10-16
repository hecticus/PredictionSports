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
 * Unit tests for the gpaapi.disable-provider-events kill switch on the Silver
 * postback. The flag is new in the Spring Boot rewrite (it does not exist in the
 * legacy Play app), so there is no legacy spec to port.
 */
class SilverServicioTest {

    private GpaApiProperties properties;
    private HttpGateway http;
    private SilverServicio service;

    @BeforeEach
    void setUp() {
        properties = new GpaApiProperties();
        http = mock(HttpGateway.class);
        service = new SilverServicio(http, properties);
    }

    @Test
    void skipsPostbackWithoutCallingTheProviderWhenDisabled() {
        properties.setDisableProviderEvents(true);

        service.crearAlta("click1", "pid1");

        verifyNoInteractions(http);
    }

    @Test
    void sendsPostbackWhenEnabled() throws IOException {
        service.crearAlta("click1", "pid1");

        verify(http).get("http://offers.silversol.affise.com/postback?clickid=click1&pid=pid1");
    }
}
