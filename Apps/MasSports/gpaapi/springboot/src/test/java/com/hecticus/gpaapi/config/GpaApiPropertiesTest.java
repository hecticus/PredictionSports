package com.hecticus.gpaapi.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract of the gpaapi.disable-provider-events kill switch: it must default to
 * false (provider events enabled) so that a missing env var never mutes providers
 * by accident.
 */
class GpaApiPropertiesTest {

    @Test
    void providerEventsAreEnabledByDefault() {
        assertFalse(new GpaApiProperties().isDisableProviderEvents());
    }

    @Test
    void killSwitchCanBeEnabled() {
        GpaApiProperties properties = new GpaApiProperties();

        properties.setDisableProviderEvents(true);

        assertTrue(properties.isDisableProviderEvents());
    }
}
