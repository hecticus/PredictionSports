package com.hecticus.gpaapi.service.dashboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Java port of the legacy Play spec server/test/services/DashboardBufferSpec.scala.
 */
class DashboardBufferTest {

    @Test
    void returnsTracesNewestFirstUpToRequestedLimit() {
        DashboardBuffer buffer = new DashboardBuffer();

        for (int i = 1; i <= 10; i++) {
            buffer.addClick(new DashboardBuffer.ClickTrace("/maxgame", "/maxgame?tr_token=t" + i, "t" + i, "TRA", "NA"));
        }

        List<DashboardBuffer.ClickTrace> last5 = buffer.lastClicks(5);

        assertEquals(5, last5.size());
        assertEquals("t10", last5.get(0).clickId);
        assertEquals("t6", last5.get(4).clickId);
    }

    @Test
    void keepsFullUntruncatedConversionDetailInMemory() {
        DashboardBuffer buffer = new DashboardBuffer();
        String longDetail = "http=200 body=" + "x".repeat(2000);

        buffer.addConversion(new DashboardBuffer.ConvTrace("CONV_TRAFFIC", "msisdn", longDetail));

        assertEquals(longDetail, buffer.lastConversions(1).get(0).detail);
    }
}
