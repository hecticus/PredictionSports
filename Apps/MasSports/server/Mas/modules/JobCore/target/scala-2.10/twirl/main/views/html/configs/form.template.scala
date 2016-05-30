
package views.html.configs

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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.Config],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(configViewForm: Form[models.Config]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
def /*6.2*/title/*6.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.39*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.12*/("""

"""),_display_(/*8.2*/main(title = Messages("configs.create"), nav = "create")/*8.58*/ {_display_(Seq[Any](format.raw/*8.60*/("""

    """),_display_(/*10.6*/if(flash.containsKey("success"))/*10.38*/ {_display_(Seq[Any](format.raw/*10.40*/("""
        """),format.raw/*11.9*/("""<div class="alert-message warning">
            <strong>Error!</strong> """),_display_(/*12.38*/flash/*12.43*/.get("success")),format.raw/*12.58*/("""
        """),format.raw/*13.9*/("""</div>
    """)))}),format.raw/*14.6*/("""

    """),_display_(/*16.6*/if(configViewForm.hasErrors)/*16.34*/ {_display_(Seq[Any](format.raw/*16.36*/("""
        """),format.raw/*17.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*19.22*/Messages("generic.error.title")),format.raw/*19.53*/("""</strong> """),_display_(/*19.64*/Messages("generic.error.content")),format.raw/*19.97*/("""
            """),format.raw/*20.13*/("""<p>"""),_display_(/*20.17*/configViewForm/*20.31*/.errorsAsJson),format.raw/*20.44*/("""</p>
        </div>
    """)))}),format.raw/*22.6*/("""


    """),_display_(/*25.6*/helper/*25.12*/.form(action = controllers.routes.ConfigsView.submit)/*25.65*/ {_display_(Seq[Any](format.raw/*25.67*/("""

        """),format.raw/*27.9*/("""<fieldset>
            <legend>"""),_display_(/*28.22*/Messages("configs.info")),format.raw/*28.46*/("""</legend>

            """),_display_(/*30.14*/inputText(
                configViewForm("configKey"),
                '_label -> Messages("configs.key"),
                '_help -> Messages("configs.key.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*36.14*/("""

            """),_display_(/*38.14*/inputText(
                configViewForm("value"),
                '_label -> Messages("configs.value"),
                '_help -> Messages("configs.value.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*44.14*/("""

            """),_display_(/*46.14*/inputText(
                configViewForm("description"),
                '_label -> Messages("configs.description"),
                '_help -> Messages("configs.description.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*52.14*/("""


        """),format.raw/*55.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*58.65*/Messages("configs.submit.create")),format.raw/*58.98*/(""">
            <a href=""""),_display_(/*59.23*/controllers/*59.34*/.routes.ConfigsView.list()),format.raw/*59.60*/("""" class="btn btn-primary">"""),_display_(/*59.87*/Messages("generic.cancel")),format.raw/*59.113*/("""</a>
        </div>

    """)))}),format.raw/*62.6*/("""

""")))}))}
  }

  def render(configViewForm:Form[models.Config]): play.twirl.api.HtmlFormat.Appendable = apply(configViewForm)

  def f:((Form[models.Config]) => play.twirl.api.HtmlFormat.Appendable) = (configViewForm) => apply(configViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/configs/form.scala.html
                  HASH: c2e345f425a6881dd64a63c953382ae6fa8b5777
                  MATRIX: 743->1|899->93|911->98|1005->38|1033->91|1061->103|1089->106|1153->162|1192->164|1225->171|1266->203|1306->205|1342->214|1442->287|1456->292|1492->307|1528->316|1570->328|1603->335|1640->363|1680->365|1716->374|1895->526|1947->557|1985->568|2039->601|2080->614|2111->618|2134->632|2168->645|2223->670|2257->678|2272->684|2334->737|2374->739|2411->749|2470->781|2515->805|2566->829|2860->1102|2902->1117|3196->1390|3238->1405|3550->1696|3588->1707|3722->1814|3776->1847|3827->1871|3847->1882|3894->1908|3948->1935|3996->1961|4052->1987
                  LINES: 26->1|29->6|29->6|32->1|34->5|35->6|37->8|37->8|37->8|39->10|39->10|39->10|40->11|41->12|41->12|41->12|42->13|43->14|45->16|45->16|45->16|46->17|48->19|48->19|48->19|48->19|49->20|49->20|49->20|49->20|51->22|54->25|54->25|54->25|54->25|56->27|57->28|57->28|59->30|65->36|67->38|73->44|75->46|81->52|84->55|87->58|87->58|88->59|88->59|88->59|88->59|88->59|91->62
                  -- GENERATED --
              */
          