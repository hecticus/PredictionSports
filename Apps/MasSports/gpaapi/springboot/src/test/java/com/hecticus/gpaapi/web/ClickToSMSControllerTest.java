package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import com.hecticus.gpaapi.service.Constants;
import com.hecticus.gpaapi.service.ConversionService;
import com.hecticus.gpaapi.service.tracking.ClickData;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Java port of the legacy Play spec server/test/controllers/ClickToSMSControllerSpec.scala.
 * Provider calls are asserted with a Mockito mock (equivalent to the legacy
 * RecordingConversionService) and claims are stubbed with the same origin-matching
 * queue the legacy spec used.
 */
class ClickToSMSControllerTest {

    private static final String MSISDN = "5091234567";

    // Mirror the legacy hardcoded values (utils.Constants + ClickToSMSController config).
    private static final String PAXXION_HANDLER = "11240";
    private static final String PAXXION_HASH = "0dd1b688a16aa53c03fe0cfe2c114e71";
    private static final String MAXGAME_HANDLER = "11191";
    private static final String MAXGAME_HASH = "3c71abda6be99653251370ff838fa4ab";

    private static GpaApiProperties testProperties() {
        GpaApiProperties properties = new GpaApiProperties();
        properties.getConversion().setPaxxionHash(PAXXION_HASH);
        properties.getConversion().setMaxgameHash(MAXGAME_HASH);
        return properties;
    }

    private static ConversionService conversionMock() {
        return mock(ConversionService.class);
    }

    private static class TestableClickToSmsController extends ClickToSMSController {

        final List<String> claims = new ArrayList<>();
        final List<String> logs = new ArrayList<>();
        private final Deque<String> pending = new ArrayDeque<>();
        private final Map<String, String> originByClickId = new HashMap<>();

        TestableClickToSmsController(ConversionService conversionService, List<String> claimedClickIds) {
            this(conversionService, claimedClickIds, Map.of());
        }

        TestableClickToSmsController(ConversionService conversionService, List<String> claimedClickIds,
                                     Map<String, String> originByClickId) {
            super(conversionService, mock(LogEntryRepository.class), mock(JdbcTemplate.class),
                    testProperties(), mock(PlatformTransactionManager.class));
            this.pending.addAll(claimedClickIds);
            this.originByClickId.putAll(originByClickId);
        }

        @Override
        protected String claimPendingClick(String table, String msisdn, String dateThreshold, String origin) {
            claims.add(table + "|" + msisdn + "|" + origin);
            String claimed = null;
            int scanSize = pending.size();
            int scanned = 0;
            while (scanned < scanSize && claimed == null) {
                String id = pending.poll();
                String idOrigin = originByClickId.get(id);
                boolean matchesOrigin = origin == null || origin.equalsIgnoreCase(idOrigin);
                if (matchesOrigin) {
                    claimed = id;
                } else {
                    pending.add(id);
                }
                scanned++;
            }
            return claimed;
        }

        List<String> remaining() {
            return new ArrayList<>(pending);
        }

        @Override
        protected void logRequest(String msisdn, String command, String country, String business) {
            logs.add(country + "|" + business + "|" + msisdn + "|" + command);
        }
    }

    @Test
    void logsEveryIncomingRequestEvenWhenConfigIsUnknown() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(conversion, List.of());

        controller.processRequest("XX", "YY", MSISDN, "LANDING");

        assertEquals(List.of("XX|YY|" + MSISDN + "|LANDING"), controller.logs);
        assertTrue(controller.claims.isEmpty());
        verifyNoInteractions(conversion);
    }

    @Test
    void claimsBliveActivityAndSendsMobipiumConversionSplittingClickIdSource() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller =
                new TestableClickToSmsController(conversion, List.of("click123---facebook"));

        controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID, MSISDN, "LANDING");

        assertEquals(List.of("blive_activity|" + MSISDN + "|null"), controller.claims);
        verify(conversion).sendToMobipium(MSISDN, "click123", "facebook");
        verifyNoMoreInteractions(conversion);
    }

    @Test
    void doesNotSendConversionWhenThereIsNoPendingActivity() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(conversion, List.of());

        controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID, MSISDN, "LANDING");

        assertEquals(List.of("blive_activity|" + MSISDN + "|null"), controller.claims);
        verifyNoInteractions(conversion);
    }

    @Test
    void routesEachPaxxionCommandToTheRightProviderAndFiltersClaimByOrigin() {
        Map<String, String> expectations = new LinkedHashMap<>();
        expectations.put("LANDING", "MOB");
        expectations.put("LANDING2", "VIA");
        expectations.put("LANDING3", "TRA");
        expectations.put("LANDING4", "SEXY");
        expectations.put("LANDING5", "CHAT");

        expectations.forEach((command, origin) -> {
            ConversionService conversion = conversionMock();
            TestableClickToSmsController controller = new TestableClickToSmsController(
                    conversion, List.of("px1"), Map.of("px1", origin));

            controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID, MSISDN, command);

            assertEquals(List.of("paxxion_activity|" + MSISDN + "|" + origin), controller.claims,
                    "claims for " + command);
            switch (origin) {
                case "MOB" -> verify(conversion).sendToMobipium(MSISDN, "px1", "");
                case "VIA" -> verify(conversion).sendToVia(MSISDN, "px1");
                case "TRA" -> verify(conversion).sendToTrafficCompany(MSISDN, PAXXION_HANDLER, PAXXION_HASH, "px1");
                case "SEXY" -> verify(conversion).sendToSexy(MSISDN, "px1");
                case "CHAT" -> verify(conversion).sendToChat(MSISDN, "px1");
                default -> fail("unexpected origin " + origin);
            }
            verifyNoMoreInteractions(conversion);
        });
    }

    @Test
    void claimsLearnLiveActivityAndSendsTrafficCompanyConversion() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(conversion, List.of("ll1"));

        controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_TEACH_BUSINESS_ID, MSISDN, "KLIKE");

        assertEquals(List.of("learn_live_activity|" + MSISDN + "|null"), controller.claims);
        verify(conversion).sendToTrafficCompany(MSISDN, PAXXION_HANDLER, PAXXION_HASH, "ll1");
        verifyNoMoreInteractions(conversion);
    }

    @Test
    void landing6ClaimsOnlyPendingGadsClickNeverPostbackableAndSendsNoConversion() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(
                conversion,
                List.of("mg1", "gads1"),
                Map.of("gads1", ClickData.ORIGIN_GOOGLE, "mg1", ClickData.ORIGIN_TRA));

        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "landing6");

        assertEquals(List.of("maxgame_activity|" + MSISDN + "|GADS"), controller.claims);
        verifyNoInteractions(conversion);
        assertEquals(List.of("mg1"), controller.remaining());
    }

    @Test
    void landing6DoesNotClaimWhenThereIsNoPendingGadsClick() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(
                conversion,
                List.of("mg1"),
                Map.of("mg1", ClickData.ORIGIN_TRA));

        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "LANDING6");

        assertEquals(List.of("maxgame_activity|" + MSISDN + "|GADS"), controller.claims);
        verifyNoInteractions(conversion);
        assertEquals(List.of("mg1"), controller.remaining());
    }

    @Test
    void landingClaimsMaxgameTraActivityAndSendsConversion() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(
                conversion, List.of("mg0"), Map.of("mg0", ClickData.ORIGIN_TRA));

        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "LANDING");

        assertEquals(List.of("maxgame_activity|" + MSISDN + "|TRA"), controller.claims);
        verify(conversion).sendToTrafficCompany(MSISDN, MAXGAME_HANDLER, MAXGAME_HASH, "mg0");
        verifyNoMoreInteractions(conversion);
    }

    @Test
    void landingDoesNotClaimGadsClick() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(
                conversion, List.of("gads1"), Map.of("gads1", ClickData.ORIGIN_GOOGLE));

        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "LANDING");

        assertEquals(List.of("maxgame_activity|" + MSISDN + "|TRA"), controller.claims);
        verifyNoInteractions(conversion);
        assertEquals(List.of("gads1"), controller.remaining());
    }

    @Test
    void doesNotClaimAnyMaxgameActivityWhenTheCommandIsNotAMaxgameCommand() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(conversion, List.of("mg1"));

        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "OTHER");
        controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "LANDING7");

        assertTrue(controller.claims.isEmpty());
        verifyNoInteractions(conversion);
    }

    @Test
    void mapsSmsCommandsToInternalOrigins() {
        assertEquals("MOB", ClickToSMSController.mapCommandToOrigin("LANDING"));
        assertEquals("VIA", ClickToSMSController.mapCommandToOrigin("LANDING2"));
        assertEquals("TRA", ClickToSMSController.mapCommandToOrigin("LANDING3"));
        assertEquals("SEXY", ClickToSMSController.mapCommandToOrigin("LANDING4"));
        assertEquals("CHAT", ClickToSMSController.mapCommandToOrigin("LANDING5"));
        assertEquals("OTRO", ClickToSMSController.mapCommandToOrigin("OTRO"));
        assertEquals("", ClickToSMSController.mapCommandToOrigin(""));
    }

    @Test
    void buildsAtomicClaimSqlWithForUpdateAndNoOriginFilterWhenThereIsNoOrigin() {
        String sql = ClickToSMSController.buildClaimSelectSql("blive_activity", null);

        assertTrue(sql.contains("msisdn is null"));
        assertTrue(sql.contains("for update"));
        assertFalse(sql.contains("origin = ?"));
    }

    @Test
    void buildsAtomicClaimSqlFilteredByOriginWhenOriginIsPresent() {
        String sql = ClickToSMSController.buildClaimSelectSql("paxxion_activity", "MOB");

        assertTrue(sql.contains("origin = ?"));
        assertTrue(sql.contains("for update"));
    }

    @Test
    void keepsTheOriginalOriginEmptyFilterWhenThePaxxionCommandIsEmpty() {
        String sql = ClickToSMSController.buildClaimSelectSql("paxxion_activity", "");

        assertTrue(sql.contains("origin = ?"));
        assertTrue(sql.contains("for update"));
    }

    @Test
    void claimsPaxxionWithEmptyOriginFilterAndSendsNoConversionWhenCommandIsEmpty() {
        ConversionService conversion = conversionMock();
        TestableClickToSmsController controller = new TestableClickToSmsController(conversion, List.of("px1"));

        controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID, MSISDN, "");

        assertEquals(List.of("paxxion_activity|" + MSISDN + "|"), controller.claims);
        verifyNoInteractions(conversion);
    }
}
