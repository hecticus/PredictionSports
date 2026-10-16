package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.dto.PushStatusClientAppLand;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.service.crypto.ApplandSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the gpaapi.disable-provider-events kill switch on AppLand events.
 * The flag is new in the Spring Boot rewrite (it does not exist in the legacy Play
 * app), so there is no legacy spec to port.
 */
class AppLandServicioTest {

    private static final String SUBSCRIPTION_ID = "sub1";

    private GpaApiProperties properties;
    private ApplandSignature signature;
    private KrakenServicio krakenServicio;
    private ClienteExternoServicio clienteExternoServicio;
    private HttpGateway http;
    private AppLandServicio service;

    @BeforeEach
    void setUp() {
        properties = new GpaApiProperties();
        signature = mock(ApplandSignature.class);
        krakenServicio = mock(KrakenServicio.class);
        clienteExternoServicio = mock(ClienteExternoServicio.class);
        http = mock(HttpGateway.class);
        service = new AppLandServicio(properties, signature, krakenServicio, clienteExternoServicio, http);
        when(signature.encriptar(anyString(), anyString())).thenReturn("sig1");
    }

    private static PushStatusClientAppLand payload() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "SUBSCRIPTION_START";
        payload.user = "user1";
        return payload;
    }

    @Test
    void skipsAppLandEventWithoutCallingTheProviderWhenDisabled() {
        properties.setDisableProviderEvents(true);
        PushStatusClientAppLand payload = payload();

        service.comunicarStatus("POST", "user1", payload, SUBSCRIPTION_ID);

        verifyNoInteractions(http);
        // Early return: the payload is not mutated and nothing is signed.
        assertEquals(0, payload.numberOfProfiles);
        assertEquals(0, payload.numberOfConcurrentSessions);
    }

    @Test
    void postsAppLandEventWhenEnabled() throws IOException {
        service.comunicarStatus("POST", "user1", payload(), SUBSCRIPTION_ID);

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> body = ArgumentCaptor.forClass(String.class);
        verify(http).postJson(url.capture(), body.capture());

        assertTrue(url.getValue().startsWith(
                "https://api.appland.se/api/subscription/events/" + SUBSCRIPTION_ID + "?key=appland-hecticus&time="));
        assertTrue(url.getValue().endsWith("&signature=sig1"));
        assertTrue(body.getValue().contains("\"event\": \"SUBSCRIPTION_START\""));
    }
}
