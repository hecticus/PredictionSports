
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
object scorers extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[models.wap.HandsetDetection,com.fasterxml.jackson.databind.JsonNode,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(jScorers: com.fasterxml.jackson.databind.JsonNode)(nameCompetition: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._

Seq[Any](format.raw/*1.111*/("""
"""),format.raw/*3.1*/("""
"""),_display_(/*4.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*4.61*/ {_display_(Seq[Any](format.raw/*4.63*/("""

""")))}/*6.3*/else/*6.8*/{_display_(Seq[Any](format.raw/*6.9*/("""

    """),_display_(/*8.6*/html4(true,"scorers",HD)/*8.30*/{_display_(Seq[Any](format.raw/*8.31*/("""
        """),format.raw/*9.9*/("""<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
                <table class="table">
                    <thead>
                    <tr>
                        <th style="background: rgb(122,184,0);" colspan="4">"""),_display_(/*14.78*/nameCompetition),format.raw/*14.93*/("""</th>
                    </tr>
                    <tr>
                        <th></th>
                        <th style="text-align:left; ">"""),_display_(/*18.56*/Messages/*18.64*/.get("PLAYER_LABEL")),format.raw/*18.84*/("""</th>
                        <th style="text-align:left; ">"""),_display_(/*19.56*/Messages/*19.64*/.get("TEAM_LABEL")),format.raw/*19.82*/("""</th>
                        <th>"""),_display_(/*20.30*/Messages/*20.38*/.get("GOALS_LABEL")),format.raw/*20.57*/("""</th>
                    </tr>
                    </thead>
                    <tbody>
                    """),_display_(/*24.22*/for(index <- 0 until jScorers.size) yield /*24.57*/{_display_(Seq[Any](format.raw/*24.58*/("""
                    """),format.raw/*25.21*/("""<tr>
                        <td><span style="vertical-align: middle;">"""),_display_(/*26.68*/{index + 1}),format.raw/*26.79*/("""</span></td>
                        <td style="text-align: left;"><span style="vertical-align: middle;">"""),_display_(/*27.94*/jScorers/*27.102*/.get(index).get("name").asText()),format.raw/*27.134*/("""</span></td>
                        <td style="text-align: left;"><span style="vertical-align: middle;">"""),_display_(/*28.94*/jScorers/*28.102*/.get(index).get("team").get("name").asText()),format.raw/*28.146*/("""</span></td>
                        <td><span style="vertical-align: middle;">"""),_display_(/*29.68*/jScorers/*29.76*/.get(index).get("goals").asText()),format.raw/*29.109*/("""</span></td>
                    </tr>
                    """)))}),format.raw/*31.22*/("""
                    """),format.raw/*32.21*/("""</tbody>
                </table>
            </div>
        <div>
    """)))}),format.raw/*36.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,jScorers:com.fasterxml.jackson.databind.JsonNode,nameCompetition:String): play.twirl.api.HtmlFormat.Appendable = apply(HD)(jScorers)(nameCompetition)

  def f:((models.wap.HandsetDetection) => (com.fasterxml.jackson.databind.JsonNode) => (String) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (jScorers) => (nameCompetition) => apply(HD)(jScorers)(nameCompetition)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/scorers.scala.html
                  HASH: 0eb3ff1b8f77900fc6d26a063235b25dcbf6097a
                  MATRIX: 797->1|1013->110|1040->131|1067->133|1134->192|1173->194|1193->198|1204->203|1241->204|1273->211|1305->235|1343->236|1378->245|1658->498|1694->513|1867->659|1884->667|1925->687|2013->748|2030->756|2069->774|2131->809|2148->817|2188->836|2325->946|2376->981|2415->982|2464->1003|2563->1075|2595->1086|2728->1192|2746->1200|2800->1232|2933->1338|2951->1346|3017->1390|3124->1470|3141->1478|3196->1511|3287->1571|3336->1592|3438->1664
                  LINES: 26->1|29->1|30->3|31->4|31->4|31->4|33->6|33->6|33->6|35->8|35->8|35->8|36->9|41->14|41->14|45->18|45->18|45->18|46->19|46->19|46->19|47->20|47->20|47->20|51->24|51->24|51->24|52->25|53->26|53->26|54->27|54->27|54->27|55->28|55->28|55->28|56->29|56->29|56->29|58->31|59->32|63->36
                  -- GENERATED --
              */
          