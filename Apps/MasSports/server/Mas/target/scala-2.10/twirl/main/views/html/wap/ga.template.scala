
package views.html.wap

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
object ga extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script>
  (function(i,s,o,g,r,a,m)"""),format.raw/*2.27*/("""{"""),format.raw/*2.28*/("""i['GoogleAnalyticsObject']=r;i[r]=i[r]||function()"""),format.raw/*2.78*/("""{"""),format.raw/*2.79*/("""
  """),format.raw/*3.3*/("""(i[r].q=i[r].q||[]).push(arguments)"""),format.raw/*3.38*/("""}"""),format.raw/*3.39*/(""",i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  """),format.raw/*5.3*/("""}"""),format.raw/*5.4*/(""")(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-48149750-12', 'auto');
  ga('send', 'pageview');
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/ga.scala.html
                  HASH: 294fdcdea518a187d0b520ac5d62822c8f7d5fec
                  MATRIX: 799->0|861->35|889->36|966->86|994->87|1023->90|1085->125|1113->126|1266->253|1293->254
                  LINES: 29->1|30->2|30->2|30->2|30->2|31->3|31->3|31->3|33->5|33->5
                  -- GENERATED --
              */
          