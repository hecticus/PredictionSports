
package views.html.devices

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.clients.Device],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(deviceViewForm: Form[models.clients.Device]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.47*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = "Crear Device", nav = "create")/*9.46*/ {_display_(Seq[Any](format.raw/*9.48*/("""

    """),_display_(/*11.6*/if(flash.containsKey("success"))/*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
        """),format.raw/*12.9*/("""<div class="alert-message warning">
            <strong>"""),_display_(/*13.22*/Messages("generic.error.title")),format.raw/*13.53*/("""</strong> """),_display_(/*13.64*/flash/*13.69*/.get("success")),format.raw/*13.84*/("""
        """),format.raw/*14.9*/("""</div>
    """)))}),format.raw/*15.6*/("""

    """),_display_(/*17.6*/if(deviceViewForm.hasErrors)/*17.34*/ {_display_(Seq[Any](format.raw/*17.36*/("""
        """),format.raw/*18.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*20.22*/Messages("generic.error.title")),format.raw/*20.53*/("""</strong> """),_display_(/*20.64*/Messages("generic.error.content")),format.raw/*20.97*/("""
            """),format.raw/*21.13*/("""<p>"""),_display_(/*21.17*/deviceViewForm/*21.31*/.errorsAsJson),format.raw/*21.44*/("""</p>
        </div>
    """)))}),format.raw/*23.6*/("""


    """),_display_(/*26.6*/helper/*26.12*/.form(action = controllers.routes.DevicesView.submit, 'enctype -> "multipart/form-data")/*26.100*/ {_display_(Seq[Any](format.raw/*26.102*/("""

        """),format.raw/*28.9*/("""<fieldset>
            <legend>"""),_display_(/*29.22*/Messages("devices.info")),format.raw/*29.46*/("""</legend>

            """),_display_(/*31.14*/inputText(
                deviceViewForm("name"),
                '_label -> Messages("devices.name"),
                '_help -> Messages("devices.name.help"),
                'class -> "form-control",
                '_error -> deviceViewForm.globalError
            )),format.raw/*37.14*/("""

        """),format.raw/*39.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*42.65*/Messages("devices.submit.create")),format.raw/*42.98*/(""">
            <a href=""""),_display_(/*43.23*/controllers/*43.34*/.routes.DevicesView.list()),format.raw/*43.60*/("""" class="btn btn-primary">"""),_display_(/*43.87*/Messages("generic.cancel")),format.raw/*43.113*/("""</a>
        </div>

    """)))}),format.raw/*46.6*/("""

""")))}))}
  }

  def render(deviceViewForm:Form[models.clients.Device]): play.twirl.api.HtmlFormat.Appendable = apply(deviceViewForm)

  def f:((Form[models.clients.Device]) => play.twirl.api.HtmlFormat.Appendable) = (deviceViewForm) => apply(deviceViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/devices/form.scala.html
                  HASH: 23bd73176b7e1431f9b747ba41a05358de1af96c
                  MATRIX: 751->1|932->119|944->124|1038->46|1066->117|1094->129|1122->132|1174->176|1213->178|1246->185|1287->217|1327->219|1363->228|1447->285|1499->316|1537->327|1551->332|1587->347|1623->356|1665->368|1698->375|1735->403|1775->405|1811->414|1990->566|2042->597|2080->608|2134->641|2175->654|2206->658|2229->672|2263->685|2318->710|2352->718|2367->724|2465->812|2506->814|2543->824|2602->856|2647->880|2698->904|2989->1174|3026->1184|3160->1291|3214->1324|3265->1348|3285->1359|3332->1385|3386->1412|3434->1438|3490->1464
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|42->13|42->13|42->13|42->13|42->13|43->14|44->15|46->17|46->17|46->17|47->18|49->20|49->20|49->20|49->20|50->21|50->21|50->21|50->21|52->23|55->26|55->26|55->26|55->26|57->28|58->29|58->29|60->31|66->37|68->39|71->42|71->42|72->43|72->43|72->43|72->43|72->43|75->46
                  -- GENERATED --
              */
          