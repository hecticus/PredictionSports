package controllers

import org.specs2.mutable.Specification
import services.conversion.ConversionService
import utils.Constants

import scala.collection.mutable.{ListBuffer, Queue}

class RecordingConversionService extends ConversionService(null) {
  val calls = ListBuffer.empty[String]

  override def sendToMobipium(msisdn: String, clickId: String, source: String): Unit =
    calls += s"MOBIPIUM|$msisdn|$clickId|$source"

  override def sendToTrafficCompany(msisdn: String, handler: String, hash: String, clickId: String): Unit =
    calls += s"TRAFFIC|$msisdn|$handler|$hash|$clickId"

  override def sendToVia(msisdn: String, clickId: String): Unit =
    calls += s"VIA|$msisdn|$clickId"

  override def sendToSexy(msisdn: String, transactionId: String): Unit =
    calls += s"SEXY|$msisdn|$transactionId"

  override def sendToChat(msisdn: String, clickId: String): Unit =
    calls += s"CHAT|$msisdn|$clickId"
}

class TestableClickToSmsController(conversionService: ConversionService, claimedClickIds: Seq[String])
    extends ClickToSMSController(conversionService) {

  val claims = ListBuffer.empty[String]
  val logs = ListBuffer.empty[String]
  private val pending = Queue[String](claimedClickIds: _*)

  override protected def claimPendingClick(table: String, msisdn: String, dateThreshold: String, origin: String): String = {
    claims += s"$table|$msisdn|$origin"
    if (pending.isEmpty) null else pending.dequeue()
  }

  override protected def logRequest(msisdn: String, command: String, country: String, business: String): Unit = {
    logs += s"$country|$business|$msisdn|$command"
  }
}

class ClickToSMSControllerSpec extends Specification {

  private val MSISDN = "5091234567"

  "ClickToSMSController" should {

    "log every incoming request even when the config is unknown" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq.empty)

      controller.processRequest("XX", "YY", MSISDN, "LANDING")

      (controller.logs.toList must_== List(s"XX|YY|$MSISDN|LANDING")) and
        (controller.claims must beEmpty) and
        (conversion.calls must beEmpty)
    }

    "claim a Blive activity and send Mobipium conversion splitting clickId source" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq("click123---facebook"))

      controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID, MSISDN, "LANDING")

      (controller.claims.toList must_== List(s"blive_activity|$MSISDN|null")) and
        (conversion.calls.toList must_== List(s"MOBIPIUM|$MSISDN|click123|facebook"))
    }

    "not send conversion when there is no pending activity" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq.empty)

      controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_BLIVE_BUSINESS_ID, MSISDN, "LANDING")

      (controller.claims.toList must_== List(s"blive_activity|$MSISDN|null")) and
        (conversion.calls must beEmpty)
    }

    "route each Paxxion command to the right provider and filter claim by origin" in {
      val expectations = List(
        ("LANDING", "MOB", s"MOBIPIUM|$MSISDN|px1|"),
        ("LANDING2", "VIA", s"VIA|$MSISDN|px1"),
        ("LANDING3", "TRA", s"TRAFFIC|$MSISDN|${Constants.HAITI_PAXION_HANDLER}|${Constants.HAITI_PAXION_HASH}|px1"),
        ("LANDING4", "SEXY", s"SEXY|$MSISDN|px1"),
        ("LANDING5", "CHAT", s"CHAT|$MSISDN|px1")
      )

      expectations.forall { case (command, origin, expectedCall) =>
        val conversion = new RecordingConversionService
        val controller = new TestableClickToSmsController(conversion, Seq("px1"))

        controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID, MSISDN, command)

        controller.claims.toList == List(s"paxxion_activity|$MSISDN|$origin") &&
          conversion.calls.toList == List(expectedCall)
      } must beTrue
    }

    "claim a LearnLive activity and send Traffic Company conversion" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq("ll1"))

      controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_TEACH_BUSINESS_ID, MSISDN, "KLIKE")

      (controller.claims.toList must_== List(s"learn_live_activity|$MSISDN|null")) and
        (conversion.calls.toList must_== List(
          s"TRAFFIC|$MSISDN|${Constants.HAITI_PAXION_HANDLER}|${Constants.HAITI_PAXION_HASH}|ll1"
        ))
    }

    "claim a Maxgame activity but only send conversion for LANDING command" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq("mg1", "mg2"))

      controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "LANDING")
      controller.processRequest(Constants.VEN_COUNTRY_ID, Constants.VEN_MAXGAME_BUSINESS_ID, MSISDN, "OTHER")

      (controller.claims.toList must_== List(
        s"maxgame_activity|$MSISDN|null",
        s"maxgame_activity|$MSISDN|null"
      )) and
        (conversion.calls.toList must_== List(
          s"TRAFFIC|$MSISDN|11191|3c71abda6be99653251370ff838fa4ab|mg1"
        ))
    }

    "map SMS commands to internal origins" in {
      (ClickToSMSController.mapCommandToOrigin("LANDING") must_== "MOB") and
        (ClickToSMSController.mapCommandToOrigin("LANDING2") must_== "VIA") and
        (ClickToSMSController.mapCommandToOrigin("LANDING3") must_== "TRA") and
        (ClickToSMSController.mapCommandToOrigin("LANDING4") must_== "SEXY") and
        (ClickToSMSController.mapCommandToOrigin("LANDING5") must_== "CHAT") and
        (ClickToSMSController.mapCommandToOrigin("OTRO") must_== "OTRO") and
        (ClickToSMSController.mapCommandToOrigin("") must_== "")
    }

    "build an atomic claim SQL with FOR UPDATE and no origin filter when there is no origin" in {
      val sql = ClickToSMSController.buildClaimSelectSql("blive_activity", null)

      (sql must contain("msisdn is null")) and
        (sql must contain("for update")) and
        (sql must not(contain("origin = ?")))
    }

    "build an atomic claim SQL filtered by origin when origin is present" in {
      val sql = ClickToSMSController.buildClaimSelectSql("paxxion_activity", "MOB")

      (sql must contain("origin = ?")) and
        (sql must contain("for update"))
    }

    "keep the original origin = '' filter when the Paxxion command is empty" in {
      val sql = ClickToSMSController.buildClaimSelectSql("paxxion_activity", "")

      (sql must contain("origin = ?")) and
        (sql must contain("for update"))
    }

    "claim Paxxion with empty origin filter and send no conversion when command is empty" in {
      val conversion = new RecordingConversionService
      val controller = new TestableClickToSmsController(conversion, Seq("px1"))

      controller.processRequest(Constants.HAITI_COUNTRY_ID, Constants.HAITI_PAXION_BUSINESS_ID, MSISDN, "")

      (controller.claims.toList must_== List(s"paxxion_activity|$MSISDN|")) and
        (conversion.calls must beEmpty)
    }
  }
}
