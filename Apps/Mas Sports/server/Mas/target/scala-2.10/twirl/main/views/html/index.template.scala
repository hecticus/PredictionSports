
package views.html

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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main(Messages("main.appname"))/*3.32*/ {_display_(Seq[Any](format.raw/*3.34*/("""

    """),format.raw/*5.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h2>"""),_display_(/*9.22*/Messages("main.appname")),format.raw/*9.46*/("""</h2>
            </div>
        </div>

    </div>

""")))}),format.raw/*15.2*/("""
"""))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/index.scala.html
                  HASH: dac6d171683377acb69863cbd6ef1b0e6e91ff83
                  MATRIX: 723->1|828->18|856->21|894->51|933->53|965->59|1105->173|1149->197|1233->251
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|37->9|37->9|43->15
                  -- GENERATED --
              */
          