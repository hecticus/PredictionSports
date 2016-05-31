
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
object xhtml extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[models.wap.HandsetDetection,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import play.i18n._
import com.feth.play.module.pa.views.html._

Seq[Any](format.raw/*1.50*/("""
"""),format.raw/*4.1*/("""<!DOCTYPE html
        PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//PT"
        "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>"""),_display_(/*9.13*/Messages/*9.21*/.get("APP_TITLE")),format.raw/*9.38*/("""</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    """),_display_(/*11.6*/ga()),format.raw/*11.10*/("""
"""),format.raw/*12.1*/("""</head>
    <body style="margin:0; padding:0; width:"""),_display_(/*13.46*/HD/*13.48*/.getBanner().get("width").asText()),format.raw/*13.82*/("""px; height:"""),_display_(/*13.94*/HD/*13.96*/.getBanner().get("height").asText()),format.raw/*13.131*/("""px;" >
        <div style='width:"""),_display_(/*14.28*/HD/*14.30*/.getBanner().get("width").asText()),format.raw/*14.64*/("""px; height:"""),_display_(/*14.76*/HD/*14.78*/.getBanner().get("height").asText()),format.raw/*14.113*/("""px; text-align: center;' >
            <img  src='"""),_display_(/*15.25*/HD/*15.27*/.getBanner().get("url").asText()),format.raw/*15.59*/("""'
                  style='width:"""),_display_(/*16.33*/HD/*16.35*/.getBanner().get("width").asText()),format.raw/*16.69*/("""px; height:"""),_display_(/*16.81*/HD/*16.83*/.getBanner().get("height").asText()),format.raw/*16.118*/("""px;' />
            <!--h3 style="text-transform: uppercase;">"""),_display_(/*17.56*/Messages/*17.64*/.get("APP_TITLE")),format.raw/*17.81*/("""</h3-->
            """),_display_(/*18.14*/content),format.raw/*18.21*/("""
        """),format.raw/*19.9*/("""</div>
    </body>
</html>
"""))}
  }

  def render(HD:models.wap.HandsetDetection,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(HD)(content)

  def f:((models.wap.HandsetDetection) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (content) => apply(HD)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:45 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/xhtml.scala.html
                  HASH: 826c014a75e79c9129c33c6031b09bcc4b088331
                  MATRIX: 753->1|951->49|978->115|1196->307|1212->315|1249->332|1362->419|1387->423|1415->424|1495->477|1506->479|1561->513|1600->525|1611->527|1668->562|1729->596|1740->598|1795->632|1834->644|1845->646|1902->681|1980->732|1991->734|2044->766|2105->800|2116->802|2171->836|2210->848|2221->850|2278->885|2368->948|2385->956|2423->973|2471->994|2499->1001|2535->1010
                  LINES: 26->1|30->1|31->4|36->9|36->9|36->9|38->11|38->11|39->12|40->13|40->13|40->13|40->13|40->13|40->13|41->14|41->14|41->14|41->14|41->14|41->14|42->15|42->15|42->15|43->16|43->16|43->16|43->16|43->16|43->16|44->17|44->17|44->17|45->18|45->18|46->19
                  -- GENERATED --
              */
          