
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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Integer,Form[models.basic.Language],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Integer, languageViewForm: Form[models.basic.Language]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.62*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("languages.edit"), nav = "create")/*9.58*/ {_display_(Seq[Any](format.raw/*9.60*/("""

    """),_display_(/*11.6*/if(languageViewForm.hasErrors)/*11.36*/ {_display_(Seq[Any](format.raw/*11.38*/("""
        """),format.raw/*12.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*14.22*/Messages("generic.error.title")),format.raw/*14.53*/("""</strong> """),_display_(/*14.64*/Messages("generic.error.content")),format.raw/*14.97*/("""
            """),format.raw/*15.13*/("""<p>"""),_display_(/*15.17*/languageViewForm/*15.33*/.errorsAsJson),format.raw/*15.46*/("""</p>
        </div>
    """)))}),format.raw/*17.6*/("""


    """),_display_(/*20.6*/helper/*20.12*/.form(controllers.routes.LanguagesView.update(id), 'enctype -> "multipart/form-data")/*20.97*/ {_display_(Seq[Any](format.raw/*20.99*/("""

    """),format.raw/*22.5*/("""<fieldset>
        <legend>"""),_display_(/*23.18*/Messages("languages.info")),format.raw/*23.44*/("""</legend>

        <div class="row">
            <div class="col-lg-5">
                """),_display_(/*27.18*/inputText(
                    languageViewForm("name"),
                    '_label -> Messages("languages.name"),
                    '_help -> Messages("languages.name.help"),
                    'class -> "form-control",
                    '_error -> languageViewForm.globalError
                )),format.raw/*33.18*/("""
            """),format.raw/*34.13*/("""</div>
            <div class="col-lg-5">
                """),_display_(/*36.18*/inputText(
                languageViewForm("shortName"),
                    '_label -> Messages("languages.shortname"),
                    '_help -> Messages("languages.shortname.help"),
                    'class -> "form-control",
                    '_error -> languageViewForm.globalError
                )),format.raw/*42.18*/("""
            """),format.raw/*43.13*/("""</div>
            <div class="col-lg-2">
                """),_display_(/*45.18*/materialCheckbox(
                    languageViewForm("active"),
                    '_label -> Messages("languages.active"),
                    'class -> "multiInstanceCheckbox"
                )),format.raw/*49.18*/("""
            """),format.raw/*50.13*/("""</div>
        </div>

        <div class="row">
            <div class="col-lg-12">
            """),_display_(/*55.14*/input(languageViewForm("appLocalizationFile"), '_label -> Messages("languages.localization.file"))/*55.112*/ { (id, name, value, _) =>_display_(Seq[Any](format.raw/*55.138*/("""
                """),format.raw/*56.17*/("""<input type="text" name=""""),_display_(/*56.43*/name),format.raw/*56.47*/("""" value=""""),_display_(/*56.57*/value),format.raw/*56.62*/("""" readonly="" class="form-control floating-label" placeholder="">
                <input type="file" name=""""),_display_(/*57.43*/name),format.raw/*57.47*/("""" value=""""),_display_(/*57.57*/value),format.raw/*57.62*/("""" id="inputFile" multiple="">
            """)))}),format.raw/*58.14*/("""
            """),format.raw/*59.13*/("""</div>
        </div>



    </fieldset>

    <div class="actions">
        <input type="submit" value="""),_display_(/*67.37*/Messages("languages.submit.update")),format.raw/*67.72*/(""" """),format.raw/*67.73*/("""class="btn btn-success">
        <a href=""""),_display_(/*68.19*/controllers/*68.30*/.routes.LanguagesView.list()),format.raw/*68.58*/("""" class="btn btn-primary">"""),_display_(/*68.85*/Messages("generic.cancel")),format.raw/*68.111*/("""</a>
    </div>

    """)))}),format.raw/*71.6*/("""

    """),_display_(/*73.6*/form(controllers.routes.LanguagesView.delete(id), 'class -> "topRight")/*73.77*/ {_display_(Seq[Any](format.raw/*73.79*/("""
        """),format.raw/*74.9*/("""<input type="submit" value="""),_display_(/*74.37*/Messages("languages.submit.delete")),format.raw/*74.72*/(""" """),format.raw/*74.73*/("""class="btn btn-danger">

    """)))}),format.raw/*76.6*/("""

""")))}),format.raw/*78.2*/("""
"""))}
  }

  def render(id:Integer,languageViewForm:Form[models.basic.Language]): play.twirl.api.HtmlFormat.Appendable = apply(id,languageViewForm)

  def f:((Integer,Form[models.basic.Language]) => play.twirl.api.HtmlFormat.Appendable) = (id,languageViewForm) => apply(id,languageViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/languages/edit.scala.html
                  HASH: a4d932879ce7f2ff7d32d153bbfa9ed88b4cd735
                  MATRIX: 761->1|957->134|969->139|1063->61|1091->132|1119->144|1147->147|1211->203|1250->205|1283->212|1322->242|1362->244|1398->253|1577->405|1629->436|1667->447|1721->480|1762->493|1793->497|1818->513|1852->526|1907->551|1941->559|1956->565|2050->650|2090->652|2123->658|2178->686|2225->712|2341->801|2664->1103|2705->1116|2791->1175|3125->1488|3166->1501|3252->1560|3471->1758|3512->1771|3637->1869|3745->1967|3810->1993|3855->2010|3908->2036|3933->2040|3970->2050|3996->2055|4131->2163|4156->2167|4193->2177|4219->2182|4293->2225|4334->2238|4465->2342|4521->2377|4550->2378|4620->2421|4640->2432|4689->2460|4743->2487|4791->2513|4843->2535|4876->2542|4956->2613|4996->2615|5032->2624|5087->2652|5143->2687|5172->2688|5232->2718|5265->2721
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|43->14|43->14|43->14|43->14|44->15|44->15|44->15|44->15|46->17|49->20|49->20|49->20|49->20|51->22|52->23|52->23|56->27|62->33|63->34|65->36|71->42|72->43|74->45|78->49|79->50|84->55|84->55|84->55|85->56|85->56|85->56|85->56|85->56|86->57|86->57|86->57|86->57|87->58|88->59|96->67|96->67|96->67|97->68|97->68|97->68|97->68|97->68|100->71|102->73|102->73|102->73|103->74|103->74|103->74|103->74|105->76|107->78
                  -- GENERATED --
              */
          