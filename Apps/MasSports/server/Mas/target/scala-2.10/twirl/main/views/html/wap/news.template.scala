
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
object news extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[models.wap.HandsetDetection,com.fasterxml.jackson.databind.JsonNode,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(jNews: com.fasterxml.jackson.databind.JsonNode)(view: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.97*/("""

"""),_display_(/*3.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*3.61*/ {_display_(Seq[Any](format.raw/*3.63*/("""

    """),_display_(/*5.6*/xhtml(HD)/*5.15*/{_display_(Seq[Any](format.raw/*5.16*/("""
        """),_display_(/*6.10*/if(view == "summary")/*6.31*/ {_display_(Seq[Any](format.raw/*6.33*/("""
            """),format.raw/*7.13*/("""<h3>"""),_display_(/*7.18*/jNews/*7.23*/.get("title").asText()),format.raw/*7.45*/("""</h3>
            <p style="font-size: small;">
                """),_display_(/*9.18*/jNews/*9.23*/.get("summary").asText()),format.raw/*9.47*/("""
            """),format.raw/*10.13*/("""</p>
            <p style="font-size: small;">
                <a href=""""),_display_(/*12.27*/routes/*12.33*/.Wap.index()),format.raw/*12.45*/("""">Volver</a>
            </p>
        """)))}/*14.11*/else/*14.16*/{_display_(Seq[Any](format.raw/*14.17*/("""
            """),_display_(/*15.14*/for(item <- jNews.iterator()) yield /*15.43*/ {_display_(Seq[Any](format.raw/*15.45*/("""
                """),format.raw/*16.17*/("""<p style="font-size: small;">
                    <a href='"""),_display_(/*17.31*/routes/*17.37*/.Wap.news(item.get("idNews").asInt())),format.raw/*17.74*/("""'>"""),_display_(/*17.77*/item/*17.81*/.get("title").asText()),format.raw/*17.103*/("""</a>
                </p>
            """)))}),format.raw/*19.14*/("""
        """)))}),format.raw/*20.10*/("""
    """)))}),format.raw/*21.6*/("""

""")))}/*23.3*/else/*23.8*/{_display_(Seq[Any](format.raw/*23.9*/("""


    """),_display_(/*26.6*/html4(true,"news",HD)/*26.27*/{_display_(Seq[Any](format.raw/*26.28*/("""


        """),_display_(/*29.10*/if(view == "summary")/*29.31*/ {_display_(Seq[Any](format.raw/*29.33*/("""
            """),format.raw/*30.13*/("""<div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 h-content-news" >
                    <h3>"""),_display_(/*32.26*/jNews/*32.31*/.get("title").asText()),format.raw/*32.53*/("""</h3>
                    <br />
                    <p>
                        """),_display_(/*35.26*/jNews/*35.31*/.get("summary").asText()),format.raw/*35.55*/("""
                    """),format.raw/*36.21*/("""</p>
                </div>
            </div>
        """)))}/*39.11*/else/*39.16*/{_display_(Seq[Any](format.raw/*39.17*/("""
            """),format.raw/*40.13*/("""<div class="row">
                """),_display_(/*41.18*/for(item <- jNews.iterator()) yield /*41.47*/ {_display_(Seq[Any](format.raw/*41.49*/("""
                    """),format.raw/*42.21*/("""<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 h-list" >
                        <a href='"""),_display_(/*43.35*/routes/*43.41*/.Wap.news(item.get("idNews").asInt())),format.raw/*43.78*/("""'>"""),_display_(/*43.81*/item/*43.85*/.get("title").asText()),format.raw/*43.107*/("""</a>
                    </div>
                """)))}),format.raw/*45.18*/("""
            """),format.raw/*46.13*/("""</div>
        """)))}),format.raw/*47.10*/("""
    """)))}),format.raw/*48.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,jNews:com.fasterxml.jackson.databind.JsonNode,view:String): play.twirl.api.HtmlFormat.Appendable = apply(HD)(jNews)(view)

  def f:((models.wap.HandsetDetection) => (com.fasterxml.jackson.databind.JsonNode) => (String) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (jNews) => (view) => apply(HD)(jNews)(view)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/news.scala.html
                  HASH: 946086de585bbdee771ad0487a58add4cab00f34
                  MATRIX: 794->1|977->96|1005->99|1072->158|1111->160|1143->167|1160->176|1198->177|1234->187|1263->208|1302->210|1342->223|1373->228|1386->233|1428->255|1519->320|1532->325|1576->349|1617->362|1717->435|1732->441|1765->453|1823->493|1836->498|1875->499|1916->513|1961->542|2001->544|2046->561|2133->621|2148->627|2206->664|2236->667|2249->671|2293->693|2363->732|2404->742|2440->748|2461->752|2473->757|2511->758|2545->766|2575->787|2614->788|2653->800|2683->821|2723->823|2764->836|2920->965|2934->970|2977->992|3086->1074|3100->1079|3145->1103|3194->1124|3269->1181|3282->1186|3321->1187|3362->1200|3424->1235|3469->1264|3509->1266|3558->1287|3681->1383|3696->1389|3754->1426|3784->1429|3797->1433|3841->1455|3921->1504|3962->1517|4009->1533|4045->1539
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|35->7|37->9|37->9|37->9|38->10|40->12|40->12|40->12|42->14|42->14|42->14|43->15|43->15|43->15|44->16|45->17|45->17|45->17|45->17|45->17|45->17|47->19|48->20|49->21|51->23|51->23|51->23|54->26|54->26|54->26|57->29|57->29|57->29|58->30|60->32|60->32|60->32|63->35|63->35|63->35|64->36|67->39|67->39|67->39|68->40|69->41|69->41|69->41|70->42|71->43|71->43|71->43|71->43|71->43|71->43|73->45|74->46|75->47|76->48
                  -- GENERATED --
              */
          