
package views.html.timezones

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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Integer,Form[models.basic.Timezone],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Integer, timezoneViewForm: Form[models.basic.Timezone]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.62*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("timezones.edit"), nav = "create")/*9.58*/ {_display_(Seq[Any](format.raw/*9.60*/("""

    """),_display_(/*11.6*/if(timezoneViewForm.hasErrors)/*11.36*/ {_display_(Seq[Any](format.raw/*11.38*/("""
        """),format.raw/*12.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*14.22*/Messages("generic.error.title")),format.raw/*14.53*/("""</strong> """),_display_(/*14.64*/Messages("generic.error.content")),format.raw/*14.97*/("""
            """),format.raw/*15.13*/("""<p>"""),_display_(/*15.17*/timezoneViewForm/*15.33*/.errorsAsJson),format.raw/*15.46*/("""</p>
        </div>
    """)))}),format.raw/*17.6*/("""


    """),_display_(/*20.6*/helper/*20.12*/.form(controllers.routes.TimezonesView.update(id), 'enctype -> "multipart/form-data")/*20.97*/ {_display_(Seq[Any](format.raw/*20.99*/("""

    """),format.raw/*22.5*/("""<fieldset>
        <legend>"""),_display_(/*23.18*/Messages("timezones.info")),format.raw/*23.44*/("""</legend>


        """),_display_(/*26.10*/inputText(
            timezoneViewForm("name"),
            '_label -> Messages("timezones.name"),
            '_help -> Messages("timezones.name.help"),
            'class -> "form-control",
            '_error -> timezoneViewForm.globalError
        )),format.raw/*32.10*/("""

    """),format.raw/*34.5*/("""</fieldset>

    <div class="actions">
        <input type="submit" value="""),_display_(/*37.37*/Messages("timezones.submit.update")),format.raw/*37.72*/(""" """),format.raw/*37.73*/("""class="btn btn-success">
        <a href=""""),_display_(/*38.19*/controllers/*38.30*/.routes.TimezonesView.list()),format.raw/*38.58*/("""" class="btn btn-primary">"""),_display_(/*38.85*/Messages("generic.cancel")),format.raw/*38.111*/("""</a>
    </div>

    """)))}),format.raw/*41.6*/("""

    """),_display_(/*43.6*/form(controllers.routes.TimezonesView.delete(id), 'class -> "topRight")/*43.77*/ {_display_(Seq[Any](format.raw/*43.79*/("""
        """),format.raw/*44.9*/("""<input type="submit" value="""),_display_(/*44.37*/Messages("timezones.submit.delete")),format.raw/*44.72*/(""" """),format.raw/*44.73*/("""class="btn btn-danger">

    """)))}),format.raw/*46.6*/("""

""")))}),format.raw/*48.2*/("""
"""))}
  }

  def render(id:Integer,timezoneViewForm:Form[models.basic.Timezone]): play.twirl.api.HtmlFormat.Appendable = apply(id,timezoneViewForm)

  def f:((Integer,Form[models.basic.Timezone]) => play.twirl.api.HtmlFormat.Appendable) = (id,timezoneViewForm) => apply(id,timezoneViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/timezones/edit.scala.html
                  HASH: 4777470a55333859579eb4935f15f906685d9d30
                  MATRIX: 761->1|957->134|969->139|1063->61|1091->132|1119->144|1147->147|1211->203|1250->205|1283->212|1322->242|1362->244|1398->253|1577->405|1629->436|1667->447|1721->480|1762->493|1793->497|1818->513|1852->526|1907->551|1941->559|1956->565|2050->650|2090->652|2123->658|2178->686|2225->712|2273->733|2548->987|2581->993|2683->1068|2739->1103|2768->1104|2838->1147|2858->1158|2907->1186|2961->1213|3009->1239|3061->1261|3094->1268|3174->1339|3214->1341|3250->1350|3305->1378|3361->1413|3390->1414|3450->1444|3483->1447
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|43->14|43->14|43->14|43->14|44->15|44->15|44->15|44->15|46->17|49->20|49->20|49->20|49->20|51->22|52->23|52->23|55->26|61->32|63->34|66->37|66->37|66->37|67->38|67->38|67->38|67->38|67->38|70->41|72->43|72->43|72->43|73->44|73->44|73->44|73->44|75->46|77->48
                  -- GENERATED --
              */
          