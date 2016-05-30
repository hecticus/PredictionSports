
package views.html.languages

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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.basic.Language],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(languageViewForm: Form[models.basic.Language]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.49*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = "Crear Language", nav = "create")/*9.48*/ {_display_(Seq[Any](format.raw/*9.50*/("""

    """),_display_(/*11.6*/if(flash.containsKey("success"))/*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
        """),format.raw/*12.9*/("""<div class="alert-message warning">
            <strong>"""),_display_(/*13.22*/Messages("generic.error.title")),format.raw/*13.53*/("""</strong> """),_display_(/*13.64*/flash/*13.69*/.get("success")),format.raw/*13.84*/("""
        """),format.raw/*14.9*/("""</div>
    """)))}),format.raw/*15.6*/("""

    """),_display_(/*17.6*/if(languageViewForm.hasErrors)/*17.36*/ {_display_(Seq[Any](format.raw/*17.38*/("""
        """),format.raw/*18.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*20.22*/Messages("generic.error.title")),format.raw/*20.53*/("""</strong> """),_display_(/*20.64*/Messages("generic.error.content")),format.raw/*20.97*/("""
            """),format.raw/*21.13*/("""<p>"""),_display_(/*21.17*/languageViewForm/*21.33*/.errorsAsJson),format.raw/*21.46*/("""</p>
        </div>
    """)))}),format.raw/*23.6*/("""


    """),_display_(/*26.6*/helper/*26.12*/.form(action = controllers.routes.LanguagesView.submit, 'enctype -> "multipart/form-data")/*26.102*/ {_display_(Seq[Any](format.raw/*26.104*/("""

        """),format.raw/*28.9*/("""<fieldset>
            <legend>"""),_display_(/*29.22*/Messages("languages.info")),format.raw/*29.48*/("""</legend>

            <div class="row">
                <div class="col-lg-5">
                """),_display_(/*33.18*/inputText(
                    languageViewForm("name"),
                    '_label -> Messages("languages.name"),
                    '_help -> Messages("languages.name.help"),
                    'class -> "form-control",
                    '_error -> languageViewForm.globalError
                )),format.raw/*39.18*/("""
                """),format.raw/*40.17*/("""</div>
                <div class="col-lg-5">
                """),_display_(/*42.18*/inputText(
                    languageViewForm("shortName"),
                    '_label -> Messages("languages.shortname"),
                    '_help -> Messages("languages.shortname.help"),
                    'class -> "form-control",
                    '_error -> languageViewForm.globalError
                )),format.raw/*48.18*/("""
                """),format.raw/*49.17*/("""</div>
                <div class="col-lg-2">
                """),_display_(/*51.18*/materialCheckbox(
                    languageViewForm("active"),
                    '_label -> Messages("languages.active"),
                    'class -> "multiInstanceCheckbox"
                )),format.raw/*55.18*/("""
                """),format.raw/*56.17*/("""</div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                """),_display_(/*61.18*/input(languageViewForm("appLocalizationFile"), '_label -> Messages("languages.localization.file"))/*61.116*/ { (id, name, value, _) =>_display_(Seq[Any](format.raw/*61.142*/("""
                    """),format.raw/*62.21*/("""<input type="text" name=""""),_display_(/*62.47*/name),format.raw/*62.51*/("""" value=""""),_display_(/*62.61*/value),format.raw/*62.66*/("""" readonly="" class="form-control floating-label" placeholder="">
                    <input type="file" name=""""),_display_(/*63.47*/name),format.raw/*63.51*/("""" value=""""),_display_(/*63.61*/value),format.raw/*63.66*/("""" id="inputFile" multiple="">
                """)))}),format.raw/*64.18*/("""
                """),format.raw/*65.17*/("""</div>
            </div>

        </fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*71.65*/Messages("languages.submit.create")),format.raw/*71.100*/(""">
            <a href=""""),_display_(/*72.23*/controllers/*72.34*/.routes.LanguagesView.list()),format.raw/*72.62*/("""" class="btn btn-primary">"""),_display_(/*72.89*/Messages("generic.cancel")),format.raw/*72.115*/("""</a>
        </div>

    """)))}),format.raw/*75.6*/("""

""")))}))}
  }

  def render(languageViewForm:Form[models.basic.Language]): play.twirl.api.HtmlFormat.Appendable = apply(languageViewForm)

  def f:((Form[models.basic.Language]) => play.twirl.api.HtmlFormat.Appendable) = (languageViewForm) => apply(languageViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/languages/form.scala.html
                  HASH: 98984b5b4b149ec58e6ffa58d9b6eb5c4481d621
                  MATRIX: 753->1|936->121|948->126|1042->48|1070->119|1098->131|1126->134|1180->180|1219->182|1252->189|1293->221|1333->223|1369->232|1453->289|1505->320|1543->331|1557->336|1593->351|1629->360|1671->372|1704->379|1743->409|1783->411|1819->420|1998->572|2050->603|2088->614|2142->647|2183->660|2214->664|2239->680|2273->693|2328->718|2362->726|2377->732|2477->822|2518->824|2555->834|2614->866|2661->892|2785->989|3108->1291|3153->1308|3243->1371|3581->1688|3626->1705|3716->1768|3935->1966|3980->1983|4121->2097|4229->2195|4294->2221|4343->2242|4396->2268|4421->2272|4458->2282|4484->2287|4623->2399|4648->2403|4685->2413|4711->2418|4789->2465|4834->2482|5003->2624|5060->2659|5111->2683|5131->2694|5180->2722|5234->2749|5282->2775|5338->2801
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|42->13|42->13|42->13|42->13|42->13|43->14|44->15|46->17|46->17|46->17|47->18|49->20|49->20|49->20|49->20|50->21|50->21|50->21|50->21|52->23|55->26|55->26|55->26|55->26|57->28|58->29|58->29|62->33|68->39|69->40|71->42|77->48|78->49|80->51|84->55|85->56|90->61|90->61|90->61|91->62|91->62|91->62|91->62|91->62|92->63|92->63|92->63|92->63|93->64|94->65|100->71|100->71|101->72|101->72|101->72|101->72|101->72|104->75
                  -- GENERATED --
              */
          