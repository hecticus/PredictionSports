
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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Integer,Form[models.clients.Device],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Integer, deviceViewForm: Form[models.clients.Device]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.60*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("devices.edit"), nav = "create")/*9.56*/ {_display_(Seq[Any](format.raw/*9.58*/("""

    """),_display_(/*11.6*/if(deviceViewForm.hasErrors)/*11.34*/ {_display_(Seq[Any](format.raw/*11.36*/("""
        """),format.raw/*12.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*14.22*/Messages("generic.error.title")),format.raw/*14.53*/("""</strong> """),_display_(/*14.64*/Messages("generic.error.content")),format.raw/*14.97*/("""
            """),format.raw/*15.13*/("""<p>"""),_display_(/*15.17*/deviceViewForm/*15.31*/.errorsAsJson),format.raw/*15.44*/("""</p>
        </div>
    """)))}),format.raw/*17.6*/("""


    """),_display_(/*20.6*/helper/*20.12*/.form(controllers.routes.DevicesView.update(id), 'enctype -> "multipart/form-data")/*20.95*/ {_display_(Seq[Any](format.raw/*20.97*/("""

    """),format.raw/*22.5*/("""<fieldset>
        <legend>"""),_display_(/*23.18*/Messages("devices.info")),format.raw/*23.42*/("""</legend>


        """),_display_(/*26.10*/inputText(
            deviceViewForm("name"),
            '_label -> Messages("devices.name"),
            '_help -> Messages("devices.name.help"),
            'class -> "form-control",
            '_error -> deviceViewForm.globalError
        )),format.raw/*32.10*/("""

    """),format.raw/*34.5*/("""</fieldset>

    <div class="actions">
        <input type="submit" value="""),_display_(/*37.37*/Messages("devices.submit.update")),format.raw/*37.70*/(""" """),format.raw/*37.71*/("""class="btn btn-success">
        <a href=""""),_display_(/*38.19*/controllers/*38.30*/.routes.DevicesView.list()),format.raw/*38.56*/("""" class="btn btn-primary">"""),_display_(/*38.83*/Messages("generic.cancel")),format.raw/*38.109*/("""</a>
    </div>

    """)))}),format.raw/*41.6*/("""

    """),_display_(/*43.6*/form(controllers.routes.DevicesView.delete(id), 'class -> "topRight")/*43.75*/ {_display_(Seq[Any](format.raw/*43.77*/("""
        """),format.raw/*44.9*/("""<input type="submit" value="""),_display_(/*44.37*/Messages("devices.submit.delete")),format.raw/*44.70*/(""" """),format.raw/*44.71*/("""class="btn btn-danger">

    """)))}),format.raw/*46.6*/("""

""")))}),format.raw/*48.2*/("""
"""))}
  }

  def render(id:Integer,deviceViewForm:Form[models.clients.Device]): play.twirl.api.HtmlFormat.Appendable = apply(id,deviceViewForm)

  def f:((Integer,Form[models.clients.Device]) => play.twirl.api.HtmlFormat.Appendable) = (id,deviceViewForm) => apply(id,deviceViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/devices/edit.scala.html
                  HASH: 778c44f833baebafb1ef8d70c3fdc76ae32f695d
                  MATRIX: 759->1|953->132|965->137|1059->59|1087->130|1115->142|1143->145|1205->199|1244->201|1277->208|1314->236|1354->238|1390->247|1569->399|1621->430|1659->441|1713->474|1754->487|1785->491|1808->505|1842->518|1897->543|1931->551|1946->557|2038->640|2078->642|2111->648|2166->676|2211->700|2259->721|2526->967|2559->973|2661->1048|2715->1081|2744->1082|2814->1125|2834->1136|2881->1162|2935->1189|2983->1215|3035->1237|3068->1244|3146->1313|3186->1315|3222->1324|3277->1352|3331->1385|3360->1386|3420->1416|3453->1419
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|43->14|43->14|43->14|43->14|44->15|44->15|44->15|44->15|46->17|49->20|49->20|49->20|49->20|51->22|52->23|52->23|55->26|61->32|63->34|66->37|66->37|66->37|67->38|67->38|67->38|67->38|67->38|70->41|72->43|72->43|72->43|73->44|73->44|73->44|73->44|75->46|77->48
                  -- GENERATED --
              */
          