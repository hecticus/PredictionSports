
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
object html4 extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Boolean,String,models.wap.HandsetDetection,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(bMenu: Boolean, sRoute: String, HD: models.wap.HandsetDetection)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
def /*5.2*/menu/*5.6*/(sRoute: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*5.26*/("""
    """),format.raw/*6.5*/("""<div class="row">
        <div class='col-xs-6 col-sm-6 col-md-3 col-lg-3 h-menu """),_display_(/*7.65*/("h-active".when(sRoute == "matches"))),format.raw/*7.103*/("""'>
            <a href='"""),_display_(/*8.23*/routes/*8.29*/.Wap.competitions("matches")),format.raw/*8.57*/("""'>"""),_display_(/*8.60*/Messages/*8.68*/.get("MATCH_LABEL")),format.raw/*8.87*/("""</a>
        </div>
        <div class='col-xs-6 col-sm-6 col-md-3 col-lg-3 h-menu """),_display_(/*10.65*/("h-active".when(sRoute == "scorers"))),format.raw/*10.103*/("""'>
            <a href='"""),_display_(/*11.23*/routes/*11.29*/.Wap.competitions("scorers")),format.raw/*11.57*/("""'>"""),_display_(/*11.60*/Messages/*11.68*/.get("SCORERS_LABEL")),format.raw/*11.89*/("""</a>
        </div>
        <div class='col-xs-6 col-sm-6 col-md-3 col-lg-3 h-menu """),_display_(/*13.65*/("h-active".when(sRoute == "news"))),format.raw/*13.100*/("""'>
            <a href=""""),_display_(/*14.23*/routes/*14.29*/.Wap.index()),format.raw/*14.41*/("""">"""),_display_(/*14.44*/Messages/*14.52*/.get("NEWS_LABEL")),format.raw/*14.70*/("""</a>
        </div>
        <div class='col-xs-6 col-sm-6 col-md-3 col-lg-3 h-menu """),_display_(/*16.65*/("h-active".when(sRoute == "mtm"))),format.raw/*16.99*/("""'>
            <a href='"""),_display_(/*17.23*/routes/*17.29*/.Wap.competitions("mtm")),format.raw/*17.53*/("""'>"""),_display_(/*17.56*/Messages/*17.64*/.get("MTM_LABEL")),format.raw/*17.81*/("""</a>
        </div>
    </div>
""")))};
Seq[Any](format.raw/*1.82*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//PT"
        "http://www.w3.org/TR/html4/loose.dtd">
"""),format.raw/*20.2*/("""

"""),format.raw/*22.1*/("""<html>
    <head>
        <title>"""),_display_(/*24.17*/Messages/*24.25*/.get("APP_TITLE")),format.raw/*24.42*/("""</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" type="text/css" href='"""),_display_(/*27.55*/routes/*27.61*/.Assets.at("stylesheets/wap/main.css")),format.raw/*27.99*/("""'>
        <link rel="stylesheet" type="text/css" href='"""),_display_(/*28.55*/routes/*28.61*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*28.104*/("""'>
        <script src='"""),_display_(/*29.23*/routes/*29.29*/.Assets.at("js/jquery-2.1.3.min.js")),format.raw/*29.65*/("""'></script>
        <script src='"""),_display_(/*30.23*/routes/*30.29*/.Assets.at("js/bootstrap.min.js")),format.raw/*30.62*/("""'></script>
        """),_display_(/*31.10*/ga()),format.raw/*31.14*/("""
    """),format.raw/*32.5*/("""</head>
    <body>
        <div class="container">

            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align: center;" >
                    <img  src='"""),_display_(/*38.33*/HD/*38.35*/.getBanner().get("url").asText()),format.raw/*38.67*/("""' style='position:relative; width:"""),_display_(/*38.102*/HD/*38.104*/.getBanner().get("width").asText()),format.raw/*38.138*/("""px; height:"""),_display_(/*38.150*/HD/*38.152*/.getBanner().get("height").asText()),format.raw/*38.187*/("""px;' />
                </div>
            </div>

            <!--div class="header" style="text-align:center;">"""),_display_(/*42.64*/Messages/*42.72*/.get("APP_TITLE")),format.raw/*42.89*/("""</div-->
            """),_display_(/*43.14*/if(bMenu)/*43.23*/ {_display_(Seq[Any](format.raw/*43.25*/("""
                """),_display_(/*44.18*/menu(sRoute)),format.raw/*44.30*/("""
            """)))}),format.raw/*45.14*/("""
            """),_display_(/*46.14*/content),format.raw/*46.21*/("""
            """),_display_(/*47.14*/if(bMenu)/*47.23*/ {_display_(Seq[Any](format.raw/*47.25*/("""
            """),_display_(/*48.14*/menu(sRoute)),format.raw/*48.26*/("""
            """)))}),format.raw/*49.14*/("""
            """),format.raw/*50.13*/("""<!--div class="header" style="text-align:center;">"""),_display_(/*50.64*/Messages/*50.72*/.get("APP_TITLE")),format.raw/*50.89*/("""</div-->

        </div>

    </body>
</html>"""))}
  }

  def render(bMenu:Boolean,sRoute:String,HD:models.wap.HandsetDetection,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(bMenu,sRoute,HD)(content)

  def f:((Boolean,String,models.wap.HandsetDetection) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (bMenu,sRoute,HD) => (content) => apply(bMenu,sRoute,HD)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/html4.scala.html
                  HASH: f3d0418c3ac6ddaa395a7934792a20fd13655179
                  MATRIX: 768->1|937->214|948->218|1044->238|1075->243|1183->325|1242->363|1293->388|1307->394|1355->422|1384->425|1400->433|1439->452|1550->536|1610->574|1662->599|1677->605|1726->633|1756->636|1773->644|1815->665|1926->749|1983->784|2035->809|2050->815|2083->827|2113->830|2130->838|2169->856|2280->940|2335->974|2387->999|2402->1005|2447->1029|2477->1032|2494->1040|2532->1057|2603->81|2630->102|2768->1089|2797->1091|2858->1125|2875->1133|2913->1150|3192->1402|3207->1408|3266->1446|3350->1503|3365->1509|3430->1552|3482->1577|3497->1583|3554->1619|3615->1653|3630->1659|3684->1692|3732->1713|3757->1717|3789->1722|4029->1935|4040->1937|4093->1969|4156->2004|4168->2006|4224->2040|4264->2052|4276->2054|4333->2089|4474->2203|4491->2211|4529->2228|4578->2250|4596->2259|4636->2261|4681->2279|4714->2291|4759->2305|4800->2319|4828->2326|4869->2340|4887->2349|4927->2351|4968->2365|5001->2377|5046->2391|5087->2404|5165->2455|5182->2463|5220->2480
                  LINES: 26->1|28->5|28->5|30->5|31->6|32->7|32->7|33->8|33->8|33->8|33->8|33->8|33->8|35->10|35->10|36->11|36->11|36->11|36->11|36->11|36->11|38->13|38->13|39->14|39->14|39->14|39->14|39->14|39->14|41->16|41->16|42->17|42->17|42->17|42->17|42->17|42->17|46->1|47->3|49->20|51->22|53->24|53->24|53->24|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|59->30|59->30|60->31|60->31|61->32|67->38|67->38|67->38|67->38|67->38|67->38|67->38|67->38|67->38|71->42|71->42|71->42|72->43|72->43|72->43|73->44|73->44|74->45|75->46|75->46|76->47|76->47|76->47|77->48|77->48|78->49|79->50|79->50|79->50|79->50
                  -- GENERATED --
              */
          