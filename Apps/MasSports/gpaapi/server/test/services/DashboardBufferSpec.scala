package services

import org.specs2.mutable.Specification
import services.dashboard.DashboardBuffer

class DashboardBufferSpec extends Specification {

  "DashboardBuffer" should {

    "return traces newest-first up to the requested limit" in {
      val buffer = new DashboardBuffer

      for (i <- 1 to 10) {
        buffer.addClick(new DashboardBuffer.ClickTrace("/maxgame", "/maxgame?tr_token=t" + i, "t" + i, "TRA", "NA"))
      }

      val last5 = buffer.lastClicks(5)

      (last5.size must_== 5) and
        (last5.get(0).clickId must_== "t10") and
        (last5.get(4).clickId must_== "t6")
    }

    "keep full untruncated conversion detail in memory" in {
      val buffer = new DashboardBuffer
      val longDetail = "http=200 body=" + ("x" * 2000)

      buffer.addConversion(new DashboardBuffer.ConvTrace("CONV_TRAFFIC", "msisdn", longDetail))

      buffer.lastConversions(1).get(0).detail must_== longDetail
    }
  }
}
