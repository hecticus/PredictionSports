
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
object error extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[models.wap.HandsetDetection,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(HD: models.wap.HandsetDetection, message: String, viewMenu: Boolean):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.71*/("""

"""),_display_(/*3.2*/if(HD.getLevelSupport() < models.wap.HandsetDetection.HTML)/*3.61*/ {_display_(Seq[Any](format.raw/*3.63*/("""

    """),_display_(/*5.6*/xhtml(HD)/*5.15*/{_display_(Seq[Any](format.raw/*5.16*/("""
        """),format.raw/*6.9*/("""<h3>"""),_display_(/*6.14*/message),format.raw/*6.21*/("""</h3>
    """)))}),format.raw/*7.6*/("""

""")))}/*9.3*/else/*9.8*/{_display_(Seq[Any](format.raw/*9.9*/("""

    """),_display_(/*11.6*/html4(viewMenu,"error", HD)/*11.33*/{_display_(Seq[Any](format.raw/*11.34*/("""
        """),format.raw/*12.9*/("""<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
                <h3 style="padding: 15px !important;">"""),_display_(/*14.56*/message),format.raw/*14.63*/("""</h3>
            </div>
        </div>
    """)))}),format.raw/*17.6*/("""

""")))}))}
  }

  def render(HD:models.wap.HandsetDetection,message:String,viewMenu:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(HD,message,viewMenu)

  def f:((models.wap.HandsetDetection,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (HD,message,viewMenu) => apply(HD,message,viewMenu)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/wap/error.scala.html
                  HASH: 54227279186f94e38930b63a682d5e9976b9a5f5
                  MATRIX: 763->1|920->70|948->73|1015->132|1054->134|1086->141|1103->150|1141->151|1176->160|1207->165|1234->172|1274->183|1294->187|1305->192|1342->193|1375->200|1411->227|1450->228|1486->237|1653->377|1681->384|1756->429
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|34->6|34->6|34->6|35->7|37->9|37->9|37->9|39->11|39->11|39->11|40->12|42->14|42->14|45->17
                  -- GENERATED --
              */
          