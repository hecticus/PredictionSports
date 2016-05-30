
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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.basic.Timezone],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(timezoneViewForm: Form[models.basic.Timezone]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.49*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = "Crear Timezone", nav = "create")/*9.48*/ {_display_(Seq[Any](format.raw/*9.50*/("""

    """),_display_(/*11.6*/if(flash.containsKey("success"))/*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
        """),format.raw/*12.9*/("""<div class="alert-message warning">
            <strong>"""),_display_(/*13.22*/Messages("generic.error.title")),format.raw/*13.53*/("""</strong> """),_display_(/*13.64*/flash/*13.69*/.get("success")),format.raw/*13.84*/("""
        """),format.raw/*14.9*/("""</div>
    """)))}),format.raw/*15.6*/("""

    """),_display_(/*17.6*/if(timezoneViewForm.hasErrors)/*17.36*/ {_display_(Seq[Any](format.raw/*17.38*/("""
        """),format.raw/*18.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*20.22*/Messages("generic.error.title")),format.raw/*20.53*/("""</strong> """),_display_(/*20.64*/Messages("generic.error.content")),format.raw/*20.97*/("""
            """),format.raw/*21.13*/("""<p>"""),_display_(/*21.17*/timezoneViewForm/*21.33*/.errorsAsJson),format.raw/*21.46*/("""</p>
        </div>
    """)))}),format.raw/*23.6*/("""


    """),_display_(/*26.6*/helper/*26.12*/.form(action = controllers.routes.TimezonesView.submit, 'enctype -> "multipart/form-data")/*26.102*/ {_display_(Seq[Any](format.raw/*26.104*/("""

        """),format.raw/*28.9*/("""<fieldset>
            <legend>"""),_display_(/*29.22*/Messages("timezones.info")),format.raw/*29.48*/("""</legend>

            """),_display_(/*31.14*/inputText(
                timezoneViewForm("name"),
                '_label -> Messages("timezones.name"),
                '_help -> Messages("timezones.name.help"),
                'class -> "form-control",
                '_error -> timezoneViewForm.globalError
            )),format.raw/*37.14*/("""

        """),format.raw/*39.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*42.65*/Messages("timezones.submit.create")),format.raw/*42.100*/(""">
            <a href=""""),_display_(/*43.23*/controllers/*43.34*/.routes.TimezonesView.list()),format.raw/*43.62*/("""" class="btn btn-primary">"""),_display_(/*43.89*/Messages("generic.cancel")),format.raw/*43.115*/("""</a>
        </div>

    """)))}),format.raw/*46.6*/("""

""")))}))}
  }

  def render(timezoneViewForm:Form[models.basic.Timezone]): play.twirl.api.HtmlFormat.Appendable = apply(timezoneViewForm)

  def f:((Form[models.basic.Timezone]) => play.twirl.api.HtmlFormat.Appendable) = (timezoneViewForm) => apply(timezoneViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/timezones/form.scala.html
                  HASH: 9c1792be53b37a9a7ca9b509c1a8ef062a165d56
                  MATRIX: 753->1|936->121|948->126|1042->48|1070->119|1098->131|1126->134|1180->180|1219->182|1252->189|1293->221|1333->223|1369->232|1453->289|1505->320|1543->331|1557->336|1593->351|1629->360|1671->372|1704->379|1743->409|1783->411|1819->420|1998->572|2050->603|2088->614|2142->647|2183->660|2214->664|2239->680|2273->693|2328->718|2362->726|2377->732|2477->822|2518->824|2555->834|2614->866|2661->892|2712->916|3011->1194|3048->1204|3182->1311|3239->1346|3290->1370|3310->1381|3359->1409|3413->1436|3461->1462|3517->1488
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|42->13|42->13|42->13|42->13|42->13|43->14|44->15|46->17|46->17|46->17|47->18|49->20|49->20|49->20|49->20|50->21|50->21|50->21|50->21|52->23|55->26|55->26|55->26|55->26|57->28|58->29|58->29|60->31|66->37|68->39|71->42|71->42|72->43|72->43|72->43|72->43|72->43|75->46
                  -- GENERATED --
              */
          