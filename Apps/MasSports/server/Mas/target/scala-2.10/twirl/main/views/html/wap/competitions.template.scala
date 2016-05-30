
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
object competitions extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[models.wap.HandsetDetection,com.fasterxml.jackson.databind.JsonNode,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection)(jCompetitions: com.fasterxml.jackson.databind.JsonNode)(route: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.106*/("""

"""),_display_(/*3.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*3.61*/ {_display_(Seq[Any](format.raw/*3.63*/("""

""")))}/*5.3*/else/*5.8*/{_display_(Seq[Any](format.raw/*5.9*/("""

    """),_display_(/*7.6*/html4(true,route, HD)/*7.27*/{_display_(Seq[Any](format.raw/*7.28*/("""
        """),format.raw/*8.9*/("""<div class="row">
        """),_display_(/*9.10*/for(competition <- jCompetitions.get("competitions").iterator()) yield /*9.74*/ {_display_(Seq[Any](format.raw/*9.76*/("""
                """),format.raw/*10.17*/("""<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 h-list" >

                    """),_display_(/*12.22*/if(route == "scorers")/*12.44*/ {_display_(Seq[Any](format.raw/*12.46*/("""
                        """),format.raw/*13.25*/("""<a href='"""),_display_(/*13.35*/routes/*13.41*/.Wap.scorers(competition.get("id_competitions").asInt())),format.raw/*13.97*/("""'>"""),_display_(/*13.100*/competition/*13.111*/.get("name").asText()),format.raw/*13.132*/("""</a>
                    """)))}),format.raw/*14.22*/("""

                    """),_display_(/*16.22*/if(route == "matches")/*16.44*/ {_display_(Seq[Any](format.raw/*16.46*/("""
                        """),format.raw/*17.25*/("""<a href='"""),_display_(/*17.35*/routes/*17.41*/.Wap.matches(competition.get("id_competitions").asInt(),0)),format.raw/*17.99*/("""'>"""),_display_(/*17.102*/competition/*17.113*/.get("name").asText()),format.raw/*17.134*/("""</a>
                    """)))}),format.raw/*18.22*/("""

                    """),_display_(/*20.22*/if(route == "mtm")/*20.40*/ {_display_(Seq[Any](format.raw/*20.42*/("""
                        """),format.raw/*21.25*/("""<a href='"""),_display_(/*21.35*/routes/*21.41*/.Wap.matches(competition.get("id_competitions").asInt(),0)),format.raw/*21.99*/("""'>"""),_display_(/*21.102*/competition/*21.113*/.get("name").asText()),format.raw/*21.134*/("""</a>
                    """)))}),format.raw/*22.22*/("""
                """),format.raw/*23.17*/("""</div>

        """)))}),format.raw/*25.10*/("""
        """),format.raw/*26.9*/("""</div>
    """)))}),format.raw/*27.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,jCompetitions:com.fasterxml.jackson.databind.JsonNode,route:String): play.twirl.api.HtmlFormat.Appendable = apply(HD)(jCompetitions)(route)

  def f:((models.wap.HandsetDetection) => (com.fasterxml.jackson.databind.JsonNode) => (String) => play.twirl.api.HtmlFormat.Appendable) = (HD) => (jCompetitions) => (route) => apply(HD)(jCompetitions)(route)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/competitions.scala.html
                  HASH: 4e09af4d7f4c4ddb567e96baf2664f01f91288aa
                  MATRIX: 802->1|995->105|1023->108|1090->167|1129->169|1149->173|1160->178|1197->179|1229->186|1258->207|1296->208|1331->217|1384->244|1463->308|1502->310|1547->327|1658->411|1689->433|1729->435|1782->460|1819->470|1834->476|1911->532|1942->535|1963->546|2006->567|2063->593|2113->616|2144->638|2184->640|2237->665|2274->675|2289->681|2368->739|2399->742|2420->753|2463->774|2520->800|2570->823|2597->841|2637->843|2690->868|2727->878|2742->884|2821->942|2852->945|2873->956|2916->977|2973->1003|3018->1020|3066->1037|3102->1046|3144->1058
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|35->7|35->7|35->7|36->8|37->9|37->9|37->9|38->10|40->12|40->12|40->12|41->13|41->13|41->13|41->13|41->13|41->13|41->13|42->14|44->16|44->16|44->16|45->17|45->17|45->17|45->17|45->17|45->17|45->17|46->18|48->20|48->20|48->20|49->21|49->21|49->21|49->21|49->21|49->21|49->21|50->22|51->23|53->25|54->26|55->27
                  -- GENERATED --
              */
          