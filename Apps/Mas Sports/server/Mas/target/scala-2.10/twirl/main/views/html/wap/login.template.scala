
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[models.wap.Client],models.wap.HandsetDetection,Integer,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(clientForm: Form[models.wap.Client], HD: models.wap.HandsetDetection, step: Integer):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
def /*4.2*/msisdnForm/*4.12*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*4.16*/("""

    """),format.raw/*6.5*/("""<form  method="post" action=""""),_display_(/*6.35*/routes/*6.41*/.Wap.getPassword()),format.raw/*6.59*/("""">
        <table>
            <tr>
                <td><b>+55&nbsp</b></td>
                <td><input type="text" name="msisdn" size="12" /></td>
            </tr>
        </table>
        <br />

        <input type="submit" value='"""),_display_(/*15.38*/Messages/*15.46*/.get("BUTTON_NEXT")),format.raw/*15.65*/("""' />
    </form>

""")))};
Seq[Any](format.raw/*1.87*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*18.2*/("""


"""),_display_(/*21.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*21.61*/ {_display_(Seq[Any](format.raw/*21.63*/("""

    """),_display_(/*23.6*/xhtml(HD)/*23.15*/{_display_(Seq[Any](format.raw/*23.16*/("""

        """),_display_(/*25.10*/if(step == 0)/*25.23*/ {_display_(Seq[Any](format.raw/*25.25*/("""

            """),format.raw/*27.13*/("""<h4>"""),_display_(/*27.18*/Messages/*27.26*/.get("LOGIN_TITLE")),format.raw/*27.45*/("""</h4>
            <h5>"""),_display_(/*28.18*/Messages/*28.26*/.get("LOGIN_TITLE_MSISDN")),format.raw/*28.52*/("""</h5>

            """),_display_(/*30.14*/if(flash.containsKey("error_msisdn"))/*30.51*/ {_display_(Seq[Any](format.raw/*30.53*/("""
                """),format.raw/*31.17*/("""<h6 style="color:red;">"""),_display_(/*31.41*/flash/*31.46*/.get("error_msisdn")),format.raw/*31.66*/("""</h6>
            """)))}),format.raw/*32.14*/("""

            """),_display_(/*34.14*/msisdnForm),format.raw/*34.24*/("""

        """)))}/*36.11*/else/*36.16*/{_display_(Seq[Any](format.raw/*36.17*/("""

            """),format.raw/*38.13*/("""<h4>"""),_display_(/*38.18*/Messages/*38.26*/.get("LOGIN_TITLE_PASSWORD")),format.raw/*38.54*/("""</h4>
            <h5>"""),_display_(/*39.18*/(clientForm("msisdn").value)),format.raw/*39.46*/("""</h5>

            """),_display_(/*41.14*/if(flash.containsKey("error_password"))/*41.53*/ {_display_(Seq[Any](format.raw/*41.55*/("""
                """),format.raw/*42.17*/("""<h6 style="color:red;">"""),_display_(/*42.41*/flash/*42.46*/.get("error_password")),format.raw/*42.68*/("""</h6>
            """)))}),format.raw/*43.14*/("""

            """),format.raw/*45.13*/("""<form id="create" method="post" action=""""),_display_(/*45.54*/routes/*45.60*/.Wap.createClient()),format.raw/*45.79*/("""">
                <input type="hidden" name="msisdn" value='"""),_display_(/*46.60*/(clientForm("msisdn").value)),format.raw/*46.88*/("""'  />
                <input type="password" name="password" size="12" /><br /><br/>
                <input type="submit" value='"""),_display_(/*48.46*/Messages/*48.54*/.get("BUTTON_CONFIRM")),format.raw/*48.76*/("""'>
            </form>

            <form id="again" method="post" action=""""),_display_(/*51.53*/routes/*51.59*/.Wap.getPassword()),format.raw/*51.77*/("""">
                <input type="hidden" name="msisdn" value='"""),_display_(/*52.60*/(clientForm("msisdn").value)),format.raw/*52.88*/("""' />
                <a href="javascript:document.getElementById('again').submit();">"""),_display_(/*53.82*/Messages/*53.90*/.get("BUTTON_AGAIN_PASSWORD")),format.raw/*53.119*/("""</a>
            </form>
        """)))}),format.raw/*55.10*/("""

    """)))}),format.raw/*57.6*/("""

""")))}/*59.3*/else/*59.8*/{_display_(Seq[Any](format.raw/*59.9*/("""

    """),_display_(/*61.6*/html4(false,"",HD)/*61.24*/{_display_(Seq[Any](format.raw/*61.25*/("""
        """),format.raw/*62.9*/("""<div class="row">

            """),_display_(/*64.14*/if(step == 0)/*64.27*/ {_display_(Seq[Any](format.raw/*64.29*/("""

                """),format.raw/*66.17*/("""<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding:5px !important" >

                    <h4>"""),_display_(/*68.26*/Messages/*68.34*/.get("LOGIN_TITLE")),format.raw/*68.53*/("""</h4>
                    <h5>"""),_display_(/*69.26*/Messages/*69.34*/.get("LOGIN_TITLE_MSISDN")),format.raw/*69.60*/("""</h5>

                    """),_display_(/*71.22*/if(flash.containsKey("error_msisdn"))/*71.59*/ {_display_(Seq[Any](format.raw/*71.61*/("""
                        """),format.raw/*72.25*/("""<h6 style="color:red;">"""),_display_(/*72.49*/flash/*72.54*/.get("error_msisdn")),format.raw/*72.74*/("""</h6>
                    """)))}),format.raw/*73.22*/("""

                """),format.raw/*75.17*/("""</div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding:5px !important">
                    """),_display_(/*77.22*/msisdnForm),format.raw/*77.32*/("""
                """),format.raw/*78.17*/("""</div>

            """)))}/*80.15*/else/*80.20*/{_display_(Seq[Any](format.raw/*80.21*/("""

                """),format.raw/*82.17*/("""<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding:5px !important">

                    <h4>"""),_display_(/*84.26*/Messages/*84.34*/.get("LOGIN_TITLE_PASSWORD")),format.raw/*84.62*/("""</h4>
                    <!--h5>"""),_display_(/*85.29*/(clientForm("msisdn").value)),format.raw/*85.57*/("""</h5-->

                    """),_display_(/*87.22*/if(flash.containsKey("error_password"))/*87.61*/ {_display_(Seq[Any](format.raw/*87.63*/("""
                        """),format.raw/*88.25*/("""<h6 style="color:red;">"""),_display_(/*88.49*/flash/*88.54*/.get("error_password")),format.raw/*88.76*/("""</h6>
                    """)))}),format.raw/*89.22*/("""

                """),format.raw/*91.17*/("""</div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding:5px !important">
                    <form id="create" method="post" action=""""),_display_(/*93.62*/routes/*93.68*/.Wap.createClient()),format.raw/*93.87*/("""">
                        <input type="hidden" name="msisdn" value='"""),_display_(/*94.68*/(clientForm("msisdn").value)),format.raw/*94.96*/("""'   />
                        <input type="password" name="password" size="10" /><br /><br />
                        <input type="submit" value='"""),_display_(/*96.54*/Messages/*96.62*/.get("BUTTON_CONFIRM")),format.raw/*96.84*/("""'>
                    </form>
                    <form id="again" method="post" action=""""),_display_(/*98.61*/routes/*98.67*/.Wap.getPassword()),format.raw/*98.85*/("""">
                        <input type="hidden" name="msisdn" value='"""),_display_(/*99.68*/(clientForm("msisdn").value)),format.raw/*99.96*/("""' />
                        <br />
                        <a href="javascript:document.getElementById('again').submit();">"""),_display_(/*101.90*/Messages/*101.98*/.get("BUTTON_AGAIN_PASSWORD")),format.raw/*101.127*/("""</a>
                        <br />
                        <br />
                    </form>
                </div>

            """)))}),format.raw/*107.14*/("""

        """),format.raw/*109.9*/("""</div>
    """)))}),format.raw/*110.6*/("""
""")))}))}
  }

  def render(clientForm:Form[models.wap.Client],HD:models.wap.HandsetDetection,step:Integer): play.twirl.api.HtmlFormat.Appendable = apply(clientForm,HD,step)

  def f:((Form[models.wap.Client],models.wap.HandsetDetection,Integer) => play.twirl.api.HtmlFormat.Appendable) = (clientForm,HD,step) => apply(clientForm,HD,step)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/login.scala.html
                  HASH: 5e5165e363dbb94ab3e5e48466ab7f8e6298f931
                  MATRIX: 780->1|954->109|972->119|1052->123|1084->129|1140->159|1154->165|1192->183|1455->419|1472->427|1512->446|1570->86|1597->107|1625->465|1655->469|1723->528|1763->530|1796->537|1814->546|1853->547|1891->558|1913->571|1953->573|1995->587|2027->592|2044->600|2084->619|2134->642|2151->650|2198->676|2245->696|2291->733|2331->735|2376->752|2427->776|2441->781|2482->801|2532->820|2574->835|2605->845|2635->857|2648->862|2687->863|2729->877|2761->882|2778->890|2827->918|2877->941|2926->969|2973->989|3021->1028|3061->1030|3106->1047|3157->1071|3171->1076|3214->1098|3264->1117|3306->1131|3374->1172|3389->1178|3429->1197|3518->1259|3567->1287|3724->1417|3741->1425|3784->1447|3887->1523|3902->1529|3941->1547|4030->1609|4079->1637|4192->1723|4209->1731|4260->1760|4325->1794|4362->1801|4383->1805|4395->1810|4433->1811|4466->1818|4493->1836|4532->1837|4568->1846|4627->1878|4649->1891|4689->1893|4735->1911|4874->2023|4891->2031|4931->2050|4989->2081|5006->2089|5053->2115|5108->2143|5154->2180|5194->2182|5247->2207|5298->2231|5312->2236|5353->2256|5411->2283|5457->2301|5613->2430|5644->2440|5689->2457|5729->2479|5742->2484|5781->2485|5827->2503|5965->2614|5982->2622|6031->2650|6092->2684|6141->2712|6198->2742|6246->2781|6286->2783|6339->2808|6390->2832|6404->2837|6447->2859|6505->2886|6551->2904|6747->3073|6762->3079|6802->3098|6899->3168|6948->3196|7123->3344|7140->3352|7183->3374|7301->3465|7316->3471|7355->3489|7452->3559|7501->3587|7654->3712|7672->3720|7724->3749|7888->3881|7926->3891|7969->3903
                  LINES: 26->1|28->4|28->4|30->4|32->6|32->6|32->6|32->6|41->15|41->15|41->15|45->1|46->3|47->18|50->21|50->21|50->21|52->23|52->23|52->23|54->25|54->25|54->25|56->27|56->27|56->27|56->27|57->28|57->28|57->28|59->30|59->30|59->30|60->31|60->31|60->31|60->31|61->32|63->34|63->34|65->36|65->36|65->36|67->38|67->38|67->38|67->38|68->39|68->39|70->41|70->41|70->41|71->42|71->42|71->42|71->42|72->43|74->45|74->45|74->45|74->45|75->46|75->46|77->48|77->48|77->48|80->51|80->51|80->51|81->52|81->52|82->53|82->53|82->53|84->55|86->57|88->59|88->59|88->59|90->61|90->61|90->61|91->62|93->64|93->64|93->64|95->66|97->68|97->68|97->68|98->69|98->69|98->69|100->71|100->71|100->71|101->72|101->72|101->72|101->72|102->73|104->75|106->77|106->77|107->78|109->80|109->80|109->80|111->82|113->84|113->84|113->84|114->85|114->85|116->87|116->87|116->87|117->88|117->88|117->88|117->88|118->89|120->91|122->93|122->93|122->93|123->94|123->94|125->96|125->96|125->96|127->98|127->98|127->98|128->99|128->99|130->101|130->101|130->101|136->107|138->109|139->110
                  -- GENERATED --
              */
          