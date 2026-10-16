package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.domain.LogEntry;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the gpaapi.disable-provider-events kill switch on the conversion
 * postbacks. The flag is new in the Spring Boot rewrite (it does not exist in the
 * legacy Play app), so there is no legacy spec to port.
 *
 * Disabled: the provider must never be called and the skip must be recorded in `log`.
 * Enabled: the legacy provider call (same URLs) is still attempted.
 */
class ConversionServiceTest {

    private static final String MSISDN = "5091234567";
    private static final String SKIPPED_DETAIL = "skipped: provider events disabled";

    private HttpGateway http;
    private LogEntryRepository logRepository;
    private GpaApiProperties properties;
    private ConversionService service;

    @BeforeEach
    void setUp() {
        http = mock(HttpGateway.class);
        logRepository = mock(LogEntryRepository.class);
        properties = new GpaApiProperties();
        service = new ConversionService(http, logRepository, properties);
    }

    private void disableProviderEvents() {
        properties.setDisableProviderEvents(true);
    }

    private LogEntry onlySavedEntry() {
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(logRepository).save(captor.capture());
        return captor.getValue();
    }

    private List<String> savedExtras() {
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(logRepository, atLeastOnce()).save(captor.capture());
        return captor.getAllValues().stream().map(LogEntry::getExtra).toList();
    }

    private void assertSkippedEntry(String identifier) {
        LogEntry entry = onlySavedEntry();
        assertEquals(MSISDN, entry.getMsisdn());
        assertEquals(identifier, entry.getIdentifier());
        assertEquals(SKIPPED_DETAIL, entry.getExtra());
        assertNotNull(entry.getLastUpdate());
    }

    @Test
    void skipsMobipiumWithoutCallingTheProviderWhenDisabled() {
        disableProviderEvents();

        service.sendToMobipium(MSISDN, "click1", "fb");

        verifyNoInteractions(http);
        assertSkippedEntry("CONV_MOBIPIUM");
    }

    @Test
    void skipsTrafficCompanyWithoutCallingTheProviderWhenDisabled() {
        disableProviderEvents();

        service.sendToTrafficCompany(MSISDN, "11191", "hash1", "click1");

        verifyNoInteractions(http);
        assertSkippedEntry("CONV_TRAFFIC");
    }

    @Test
    void skipsSexyWithoutCallingTheProviderWhenDisabled() {
        disableProviderEvents();

        service.sendToSexy(MSISDN, "tx1");

        verifyNoInteractions(http);
        assertSkippedEntry("CONV_SEXY");
    }

    @Test
    void skipsChatWithoutCallingTheProviderWhenDisabled() {
        disableProviderEvents();

        service.sendToChat(MSISDN, "click1");

        verifyNoInteractions(http);
        assertSkippedEntry("CONV_CHAT");
    }

    @Test
    void skipsViaWithoutCallingTheProviderWhenDisabled() {
        disableProviderEvents();

        service.sendToVia(MSISDN, "click1");

        verifyNoInteractions(http);
        assertSkippedEntry("CONV_VIA");
    }

    @Test
    void killSwitchTakesPrecedenceOverEmptyClickIdValidation() {
        disableProviderEvents();

        service.sendToMobipium(MSISDN, null, "fb");
        service.sendToTrafficCompany(MSISDN, "11191", "hash1", "");
        service.sendToSexy(MSISDN, null);
        service.sendToChat(MSISDN, "");
        service.sendToVia(MSISDN, null);

        verifyNoInteractions(http);
        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(logRepository, times(5)).save(captor.capture());
        assertEquals(
                List.of("CONV_MOBIPIUM", "CONV_TRAFFIC", "CONV_SEXY", "CONV_CHAT", "CONV_VIA"),
                captor.getAllValues().stream().map(LogEntry::getIdentifier).toList());
        assertTrue(captor.getAllValues().stream().allMatch(e -> SKIPPED_DETAIL.equals(e.getExtra())));
    }

    @Test
    void sendsMobipiumWithLegacyUrlWhenEnabled() throws IOException {
        service.sendToMobipium(MSISDN, "click1", "fb");

        verify(http).get("https://smobipiumlink.com/conversion/index.php?jp=click1&source=fb");
        verifyNoMoreInteractions(http);
        assertFalse(savedExtras().contains(SKIPPED_DETAIL));
    }

    @Test
    void sendsTrafficCompanyWithLegacyUrlWhenEnabled() throws IOException {
        when(http.getResult(anyString())).thenReturn(new HttpGateway.HttpResult(200, "ok"));

        service.sendToTrafficCompany(MSISDN, "11191", "hash1", "click1");

        String expectedUrl = "https://postback.level23.nl/?currency=USD&handler=11191&hash=hash1&tracker=click1";
        verify(http).getResult(expectedUrl);
        verifyNoMoreInteractions(http);
        List<String> extras = savedExtras();
        assertTrue(extras.contains("call url=" + expectedUrl));
        assertTrue(extras.contains("http=200 body=ok"));
    }

    @Test
    void sendsSexyWithLegacyUrlAndTwoMinuteTimeoutWhenEnabled() throws IOException {
        service.sendToSexy(MSISDN, "tx1");

        String expectedUrl = "https://www.lktrack.com/adserver/delivery/cv.php?key=&transaction_id=tx1";
        verify(http).getWithTimeout(expectedUrl, 120_000L);
        verifyNoMoreInteractions(http);
        assertFalse(savedExtras().contains(SKIPPED_DETAIL));
    }

    @Test
    void sendsChatWithLegacyUrlWhenEnabled() throws IOException {
        service.sendToChat(MSISDN, "click1");

        verify(http).get("https://postback.mobidea.ai/postback?click_id=click1&security_token=");
        verifyNoMoreInteractions(http);
        assertFalse(savedExtras().contains(SKIPPED_DETAIL));
    }

    @Test
    void sendsViaWithLegacyUrlWhenEnabled() throws IOException {
        service.sendToVia(MSISDN, "click1");

        verify(http).get("http://api.doblevialatam.com:9090/cget.php?token=click1");
        verifyNoMoreInteractions(http);
        assertFalse(savedExtras().contains(SKIPPED_DETAIL));
    }
}
