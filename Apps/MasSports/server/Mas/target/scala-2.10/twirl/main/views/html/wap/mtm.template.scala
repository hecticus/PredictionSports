
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
object mtm extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[models.wap.HandsetDetection,com.fasterxml.jackson.databind.JsonNode,Integer,String,Integer,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(jResponse: com.fasterxml.jackson.databind.JsonNode)(idCompetition: Integer)(nameCompetition: String)(idMatch: Integer):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
def /*4.2*/refresh/*4.9*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*4.13*/("""
    """),format.raw/*5.5*/("""<div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center; background:rgb(122,184,0); color:#ffffff;" >
            <!--a href='"""),_display_(/*7.26*/routes/*7.32*/.Wap.mtm(idCompetition,idMatch,jResponse.get("actions").get(0).get("events").get(0).get("id_game_match_events").asInt())),format.raw/*7.152*/("""'>"""),_display_(/*7.155*/Messages/*7.163*/.get("MTM_REFRESH_LINK")),format.raw/*7.187*/("""</a-->
            <a href='"""),_display_(/*8.23*/routes/*8.29*/.Wap.mtm(idCompetition,idMatch,0)),format.raw/*8.62*/("""'>"""),_display_(/*8.65*/Messages/*8.73*/.get("MTM_REFRESH_LINK")),format.raw/*8.97*/("""</a>
        </div>
    </div>
""")))};
Seq[Any](format.raw/*1.154*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*11.2*/("""

"""),_display_(/*13.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*13.61*/ {_display_(Seq[Any](format.raw/*13.63*/("""
""")))}/*14.3*/else/*14.8*/{_display_(Seq[Any](format.raw/*14.9*/("""

    """),_display_(/*16.6*/html4(true,"mtm", HD)/*16.27*/{_display_(Seq[Any](format.raw/*16.28*/("""


        """),format.raw/*19.9*/("""<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
                <table style="width: 100% !important;">
                    <thead>

                        <tr>
                            <th colspan="2" >
                                <span></span>
                            </th>
                        </tr>

                    </thead>
                    <tbody>

                        <tr style="width: 100% !important; text-align:center;" >
                            <td style="width: 50% !important;" >
                                <h5>"""),_display_(/*35.38*/jResponse/*35.47*/.get("home_team").get("name").asText()),format.raw/*35.85*/("""</h5>
                                <h3>"""),_display_(/*36.38*/jResponse/*36.47*/.get("home_team_goals").asText()),format.raw/*36.79*/("""</h3>
                            </td>
                            <td style="width: 50% !important;">
                                <h5>"""),_display_(/*39.38*/jResponse/*39.47*/.get("away_team").get("name").asText()),format.raw/*39.85*/("""</h5>
                                <h3>"""),_display_(/*40.38*/jResponse/*40.47*/.get("away_team_goals").asText()),format.raw/*40.79*/("""</h3>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>

        """),_display_(/*49.10*/refresh),format.raw/*49.17*/("""

        """),_display_(/*51.10*/for(action <- jResponse.get("actions").iterator()) yield /*51.60*/ {_display_(Seq[Any](format.raw/*51.62*/("""
            """),format.raw/*52.13*/("""<div class="row">

                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >

                    <table style="width: 100% !important;">
                        <thead>
                            <tr>
                                <th colspan="2" class='status """),_display_(/*59.64*/{action.get("period").get("short_name").asText()}),format.raw/*59.113*/("""'>
                                    <span>"""),_display_(/*60.44*/action/*60.50*/.get("period").get("short_name").asText()),format.raw/*60.91*/("""</span>
                                </th>
                            </tr>
                        </thead>
                        <tbody>

                            """),_display_(/*66.30*/for(event <- action.get("events").iterator()) yield /*66.75*/ {_display_(Seq[Any](format.raw/*66.77*/("""
                                """),format.raw/*67.33*/("""<tr  style="width: 100% !important; height-min:25px  !important;" >
                                    <td style="width: 20% !important; padding:5px 0;" >
                                        <span style="font-weight:bold;">"""),_display_(/*69.74*/event/*69.79*/.get("action_minute").asText()),format.raw/*69.109*/("""'</span>
                                    </td>
                                    <td style="width: 80% !important; padding:5px 0; text-align:left;">
                                        <span style="font-weight:bold;">"""),_display_(/*72.74*/event/*72.79*/.get("player_a").asText()),format.raw/*72.104*/("""</span>
                                        <span> de """),_display_(/*73.52*/event/*73.57*/.get("teams").asText()),format.raw/*73.79*/(""", """),_display_(/*73.82*/event/*73.87*/.get("action").get("description").asText()),format.raw/*73.129*/(""" """),format.raw/*73.130*/("""</span>
                                    </td>
                                </tr>
                            """)))}),format.raw/*76.30*/("""

                        """),format.raw/*78.25*/("""</tbody>
                    </table>

                </div>
            </div>
        """)))}),format.raw/*83.10*/("""

        """),_display_(/*85.10*/refresh),format.raw/*85.17*/("""


    """)))}),format.raw/*88.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,jResponse:com.fasterxml.jackson.databind.JsonNode,idCompetition:Integer,nameCompetition:String,idMatch:Integer): play.twirl.api.HtmlFormat.Appendable = apply(HD)(jResponse)(idCompetition)(nameCompetition)(idMatch)

  def f:((models.wap.HandsetDetection) => (com.fasterxml.jackson.databind.JsonNode) => (Integer) => (String) => (Integer) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (jResponse) => (idCompetition) => (nameCompetition) => (idMatch) => apply(HD)(jResponse)(idCompetition)(nameCompetition)(idMatch)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/mtm.scala.html
                  HASH: a2beb4e05796210a3a63ee2aafc9a55967512797
                  MATRIX: 809->1|1050->176|1064->183|1144->187|1175->192|1376->367|1390->373|1531->493|1561->496|1578->504|1623->528|1678->557|1692->563|1745->596|1774->599|1790->607|1834->631|1906->153|1933->174|1961->663|1990->666|2058->725|2098->727|2118->730|2130->735|2168->736|2201->743|2231->764|2270->765|2308->776|2932->1373|2950->1382|3009->1420|3079->1463|3097->1472|3150->1504|3318->1645|3336->1654|3395->1692|3465->1735|3483->1744|3536->1776|3732->1945|3760->1952|3798->1963|3864->2013|3904->2015|3945->2028|4251->2307|4322->2356|4395->2402|4410->2408|4472->2449|4674->2624|4735->2669|4775->2671|4836->2704|5092->2933|5106->2938|5158->2968|5413->3196|5427->3201|5474->3226|5560->3285|5574->3290|5617->3312|5647->3315|5661->3320|5725->3362|5755->3363|5903->3480|5957->3506|6078->3596|6116->3607|6144->3614|6182->3622
                  LINES: 26->1|28->4|28->4|30->4|31->5|33->7|33->7|33->7|33->7|33->7|33->7|34->8|34->8|34->8|34->8|34->8|34->8|38->1|39->3|40->11|42->13|42->13|42->13|43->14|43->14|43->14|45->16|45->16|45->16|48->19|64->35|64->35|64->35|65->36|65->36|65->36|68->39|68->39|68->39|69->40|69->40|69->40|78->49|78->49|80->51|80->51|80->51|81->52|88->59|88->59|89->60|89->60|89->60|95->66|95->66|95->66|96->67|98->69|98->69|98->69|101->72|101->72|101->72|102->73|102->73|102->73|102->73|102->73|102->73|102->73|105->76|107->78|112->83|114->85|114->85|117->88
                  -- GENERATED --
              */
          