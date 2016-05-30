
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
object matches extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[models.wap.HandsetDetection,com.fasterxml.jackson.databind.JsonNode,com.fasterxml.jackson.databind.JsonNode,Integer,Integer,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(jMatches: com.fasterxml.jackson.databind.JsonNode)(jCompetition: com.fasterxml.jackson.databind.JsonNode)(limit: Integer)(page: Integer):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
def /*5.2*/currentTime/*5.13*/(fecha: String) = {{

    import java.util.Date
    import java.text.SimpleDateFormat

    val isoFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    isoFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
    val dates = isoFormat.parse(fecha);
    val curTimeFormat = new SimpleDateFormat("HH:mm").format(dates)
    curTimeFormat.format(curTimeFormat)
}};
Seq[Any](format.raw/*1.172*/("""
"""),format.raw/*3.1*/("""

"""),format.raw/*15.2*/("""

"""),_display_(/*17.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*17.61*/ {_display_(Seq[Any](format.raw/*17.63*/("""

""")))}/*19.3*/else/*19.8*/{_display_(Seq[Any](format.raw/*19.9*/("""

    """),_display_(/*21.6*/html4(true,"matches",HD)/*21.30*/{_display_(Seq[Any](format.raw/*21.31*/("""

        """),format.raw/*23.9*/("""<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
                <table style="width: 100% !important;">
                    <thead>
                    <tr>
                        <th colspan="3">
                            <span>"""),_display_(/*29.36*/jCompetition/*29.48*/.get("competition").get("name").asText()),format.raw/*29.88*/("""</span>
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                    """),_display_(/*35.22*/for(item <- jMatches.get("fixtures").iterator()) yield /*35.70*/ {_display_(Seq[Any](format.raw/*35.72*/("""
                        """),format.raw/*36.25*/("""<tr style="width: 100% !important; height-min:25px  !important;" class="h-list">

                            <td style="width: 33% !important;" >
                                <span>"""),_display_(/*39.40*/item/*39.44*/.get("homeTeam").get("name").asText()),format.raw/*39.81*/("""</span>
                                <br />
                                <span  class="goal">"""),_display_(/*41.54*/item/*41.58*/.get("home_team_goals").asText()),format.raw/*41.90*/("""</span>
                            </td>

                            <td style="width: 33% !important;">
                                <span>"""),_display_(/*45.40*/currentTime(item.get("date").asText())),format.raw/*45.78*/("""</span>
                                <br />
                                """),_display_(/*47.34*/if(item.get("status").get("id_status").asInt() != 3)/*47.86*/ {_display_(Seq[Any](format.raw/*47.88*/("""
                                    """),format.raw/*48.37*/("""<a href='"""),_display_(/*48.47*/routes/*48.53*/.Wap.mtm(jCompetition.get("competition").get("id").asInt(),item.get("id_game_matches").asInt(),0)),format.raw/*48.150*/("""' class='status _"""),_display_(/*48.168*/{item.get("status").get("id_status").asText()}),format.raw/*48.214*/("""'>"""),_display_(/*48.217*/Messages/*48.225*/.get("MATCH-STATUS-" + item.get("status").get("id_status").asText())),format.raw/*48.293*/("""</a>
                                """)))}/*49.35*/else/*49.40*/{_display_(Seq[Any](format.raw/*49.41*/("""
                                    """),format.raw/*50.37*/("""<span  class='status _"""),_display_(/*50.60*/{item.get("status").get("id_status").asText()}),format.raw/*50.106*/("""'>"""),_display_(/*50.109*/Messages/*50.117*/.get("MATCH-STATUS-" + item.get("status").get("id_status").asText())),format.raw/*50.185*/("""</span>
                                """)))}),format.raw/*51.34*/("""
                            """),format.raw/*52.29*/("""</td>

                            <td style="width: 33% !important;">
                                <span>"""),_display_(/*55.40*/item/*55.44*/.get("awayTeam").get("name").asText()),format.raw/*55.81*/("""</span>
                                <br />
                                <span  class="goal">"""),_display_(/*57.54*/item/*57.58*/.get("away_team_goals").asText()),format.raw/*57.90*/("""</span>
                            </td>

                        </tr>
                    """)))}),format.raw/*61.22*/("""

                    """),format.raw/*63.21*/("""</tbody>
                </table>
            </div>
        <div>


        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="text-align:right;" >
                """),_display_(/*71.18*/if(page > 0)/*71.30*/ {_display_(Seq[Any](format.raw/*71.32*/("""
                    """),format.raw/*72.21*/("""<a href='"""),_display_(/*72.31*/routes/*72.37*/.Wap.matches(jCompetition.get("competition").get("id").asInt(),{page - 1})),format.raw/*72.111*/("""'>anterior&nbsp;</a>
                """)))}),format.raw/*73.18*/("""
            """),format.raw/*74.13*/("""</div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="text-align:left;" >
                """),_display_(/*76.18*/if(Math.ceil((jMatches.get("total").asInt()/limit)).toInt > 0)/*76.80*/ {_display_(Seq[Any](format.raw/*76.82*/("""
                    """),_display_(/*77.22*/if(page < Math.ceil((jMatches.get("total").asInt()/limit)).toInt)/*77.87*/ {_display_(Seq[Any](format.raw/*77.89*/("""
                        """),format.raw/*78.25*/("""<a href='"""),_display_(/*78.35*/routes/*78.41*/.Wap.matches(jCompetition.get("competition").get("id").asInt(),{page + 1})),format.raw/*78.115*/("""'>&nbsp;siguiente</a>
                    """)))}),format.raw/*79.22*/("""
                """)))}),format.raw/*80.18*/("""
            """),format.raw/*81.13*/("""</div>
        </div>
    """)))}),format.raw/*83.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,jMatches:com.fasterxml.jackson.databind.JsonNode,jCompetition:com.fasterxml.jackson.databind.JsonNode,limit:Integer,page:Integer): play.twirl.api.HtmlFormat.Appendable = apply(HD)(jMatches)(jCompetition)(limit)(page)

  def f:((models.wap.HandsetDetection) => (com.fasterxml.jackson.databind.JsonNode) => (com.fasterxml.jackson.databind.JsonNode) => (Integer) => (Integer) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (jMatches) => (jCompetition) => (limit) => (page) => apply(HD)(jMatches)(jCompetition)(limit)(page)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/matches.scala.html
                  HASH: aabc450aed8ec305788adf5db2c285fe4872bca4
                  MATRIX: 846->1|1105->195|1124->206|1521->171|1548->192|1577->572|1606->575|1674->634|1714->636|1735->640|1747->645|1785->646|1818->653|1851->677|1890->678|1927->688|2224->958|2245->970|2306->1010|2476->1153|2540->1201|2580->1203|2633->1228|2846->1414|2859->1418|2917->1455|3044->1555|3057->1559|3110->1591|3283->1737|3342->1775|3449->1855|3510->1907|3550->1909|3615->1946|3652->1956|3667->1962|3786->2059|3832->2077|3900->2123|3931->2126|3949->2134|4039->2202|4096->2241|4109->2246|4148->2247|4213->2284|4263->2307|4331->2353|4362->2356|4380->2364|4470->2432|4542->2473|4599->2502|4736->2612|4749->2616|4807->2653|4934->2753|4947->2757|5000->2789|5125->2883|5175->2905|5403->3106|5424->3118|5464->3120|5513->3141|5550->3151|5565->3157|5661->3231|5730->3269|5771->3282|5910->3394|5981->3456|6021->3458|6070->3480|6144->3545|6184->3547|6237->3572|6274->3582|6289->3588|6385->3662|6459->3705|6508->3723|6549->3736|6606->3763
                  LINES: 26->1|28->5|28->5|39->1|40->3|42->15|44->17|44->17|44->17|46->19|46->19|46->19|48->21|48->21|48->21|50->23|56->29|56->29|56->29|62->35|62->35|62->35|63->36|66->39|66->39|66->39|68->41|68->41|68->41|72->45|72->45|74->47|74->47|74->47|75->48|75->48|75->48|75->48|75->48|75->48|75->48|75->48|75->48|76->49|76->49|76->49|77->50|77->50|77->50|77->50|77->50|77->50|78->51|79->52|82->55|82->55|82->55|84->57|84->57|84->57|88->61|90->63|98->71|98->71|98->71|99->72|99->72|99->72|99->72|100->73|101->74|103->76|103->76|103->76|104->77|104->77|104->77|105->78|105->78|105->78|105->78|106->79|107->80|108->81|110->83
                  -- GENERATED --
              */
          