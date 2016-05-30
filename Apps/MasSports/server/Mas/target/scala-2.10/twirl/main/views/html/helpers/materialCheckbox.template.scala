
package views.html.helpers

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

/**
* Generate an HTML input checkbox.
*
* Example:
* {{{
* @checkbox(field = myForm("done"))
* }}}
*
* @param field The form field.
* @param args Set of extra HTML attributes ('''id''' and '''label''' are 2 special arguments).
* @param handler The field constructor.
*/
object materialCheckbox extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[play.api.data.Field,Array[scala.Tuple2[Symbol, Any]],views.html.helper.FieldConstructor,play.api.i18n.Lang,play.twirl.api.HtmlFormat.Appendable] {

  /**
* Generate an HTML input checkbox.
*
* Example:
* {{{
* @checkbox(field = myForm("done"))
* }}}
*
* @param field The form field.
* @param args Set of extra HTML attributes ('''id''' and '''label''' are 2 special arguments).
* @param handler The field constructor.
*/
  def apply/*13.2*/(field: play.api.data.Field, args: (Symbol,Any)*)(implicit handler: views.html.helper.FieldConstructor, lang: play.api.i18n.Lang):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*15.2*/boxValue/*15.10*/ = {{ args.toMap.get('value).getOrElse("true") }};
Seq[Any](format.raw/*13.131*/("""

"""),format.raw/*15.58*/("""

"""),_display_(/*17.2*/helper/*17.8*/.input(field, args:_*)/*17.30*/ { (id, name, value, htmlArgs) =>_display_(Seq[Any](format.raw/*17.63*/("""
    """),format.raw/*18.5*/("""<div class="form-group">
        <div class="col-lg-12">
            <div class="checkbox">
                <label>
                    <input type="checkbox" id=""""),_display_(/*22.49*/id),format.raw/*22.51*/("""" name=""""),_display_(/*22.60*/name),format.raw/*22.64*/("""" value=""""),_display_(/*22.74*/boxValue),format.raw/*22.82*/("""" """),_display_(/*22.85*/if(value == Some(boxValue))/*22.112*/{_display_(Seq[Any](format.raw/*22.113*/("""checked="checked"""")))}),format.raw/*22.131*/(""" """),_display_(/*22.133*/toHtmlArgs(htmlArgs.filterKeys(_ != 'value))),format.raw/*22.177*/("""/>
                    <span>"""),_display_(/*23.28*/args/*23.32*/.toMap.get('_text)),format.raw/*23.50*/("""</span>
                </label>
            </div>
        </div>
    </div>
""")))}),format.raw/*28.2*/("""




"""))}
  }

  def render(field:play.api.data.Field,args:Array[scala.Tuple2[Symbol, Any]],handler:views.html.helper.FieldConstructor,lang:play.api.i18n.Lang): play.twirl.api.HtmlFormat.Appendable = apply(field,args:_*)(handler,lang)

  def f:((play.api.data.Field,Array[scala.Tuple2[Symbol, Any]]) => (views.html.helper.FieldConstructor,play.api.i18n.Lang) => play.twirl.api.HtmlFormat.Appendable) = (field,args) => (handler,lang) => apply(field,args:_*)(handler,lang)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/helpers/materialCheckbox.scala.html
                  HASH: f0622efba125b2ea1cf80cd97f7c847692624d89
                  MATRIX: 1375->272|1576->404|1593->412|1673->401|1703->460|1732->463|1746->469|1777->491|1848->524|1880->529|2071->693|2094->695|2130->704|2155->708|2192->718|2221->726|2251->729|2288->756|2328->757|2378->775|2408->777|2474->821|2531->851|2544->855|2583->873|2692->952
                  LINES: 48->13|50->15|50->15|51->13|53->15|55->17|55->17|55->17|55->17|56->18|60->22|60->22|60->22|60->22|60->22|60->22|60->22|60->22|60->22|60->22|60->22|60->22|61->23|61->23|61->23|66->28
                  -- GENERATED --
              */
          