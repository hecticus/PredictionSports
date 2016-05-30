
package views.html.helpers

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
object materialRadio extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[play.api.data.Field,Seq[scala.Tuple2[String, String]],Array[scala.Tuple2[Symbol, Any]],views.html.helper.FieldConstructor,play.api.i18n.Lang,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(field: play.api.data.Field, options: Seq[(String,String)], args: (Symbol,Any)*)(implicit handler: views.html.helper.FieldConstructor, lang: play.api.i18n.Lang):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.162*/("""
"""),_display_(/*2.2*/helper/*2.8*/.input(field, args.map{ x => if(x._1 == '_label) '_name -> x._2 else x }:_*)/*2.84*/ { (id, name, value, htmlArgs) =>_display_(Seq[Any](format.raw/*2.117*/("""
    """),format.raw/*3.5*/("""<div class="form-group">
        <div class="col-lg-12">
            <span class="buttonset" id=""""),_display_(/*5.42*/id),format.raw/*5.44*/("""">
                """),_display_(/*6.18*/options/*6.25*/.map/*6.29*/ { v =>_display_(Seq[Any](format.raw/*6.36*/("""
                    """),format.raw/*7.21*/("""<div class="radio radio-primary">
                        <label for=""""),_display_(/*8.38*/(id)),format.raw/*8.42*/("""_"""),_display_(/*8.44*/v/*8.45*/._1),format.raw/*8.48*/("""">
                            <input type="radio" id=""""),_display_(/*9.54*/(id)),format.raw/*9.58*/("""_"""),_display_(/*9.60*/v/*9.61*/._1),format.raw/*9.64*/("""" name=""""),_display_(/*9.73*/name),format.raw/*9.77*/("""" value=""""),_display_(/*9.87*/v/*9.88*/._1),format.raw/*9.91*/("""" """),_display_(/*9.94*/if(value == Some(v._1))/*9.117*/{_display_(Seq[Any](format.raw/*9.118*/("""checked="checked"""")))}),format.raw/*9.136*/(""" """),_display_(/*9.138*/toHtmlArgs(htmlArgs)),format.raw/*9.158*/("""/>
                            """),_display_(/*10.30*/v/*10.31*/._2),format.raw/*10.34*/("""
                        """),format.raw/*11.25*/("""</label>
                    </div>
                """)))}),format.raw/*13.18*/("""
            """),format.raw/*14.13*/("""</span>
        </div>
    </div>
""")))}),format.raw/*17.2*/("""



"""))}
  }

  def render(field:play.api.data.Field,options:Seq[scala.Tuple2[String, String]],args:Array[scala.Tuple2[Symbol, Any]],handler:views.html.helper.FieldConstructor,lang:play.api.i18n.Lang): play.twirl.api.HtmlFormat.Appendable = apply(field,options,args:_*)(handler,lang)

  def f:((play.api.data.Field,Seq[scala.Tuple2[String, String]],Array[scala.Tuple2[Symbol, Any]]) => (views.html.helper.FieldConstructor,play.api.i18n.Lang) => play.twirl.api.HtmlFormat.Appendable) = (field,options,args) => (handler,lang) => apply(field,options,args:_*)(handler,lang)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/helpers/materialRadio.scala.html
                  HASH: 4b273bd99bf7379a5cb529057309128b504abe82
                  MATRIX: 873->1|1122->161|1149->163|1162->169|1246->245|1317->278|1348->283|1472->381|1494->383|1540->403|1555->410|1567->414|1611->421|1659->442|1756->513|1780->517|1808->519|1817->520|1840->523|1922->579|1946->583|1974->585|1983->586|2006->589|2041->598|2065->602|2101->612|2110->613|2133->616|2162->619|2194->642|2233->643|2282->661|2311->663|2352->683|2411->715|2421->716|2445->719|2498->744|2582->797|2623->810|2688->845
                  LINES: 26->1|29->1|30->2|30->2|30->2|30->2|31->3|33->5|33->5|34->6|34->6|34->6|34->6|35->7|36->8|36->8|36->8|36->8|36->8|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|37->9|38->10|38->10|38->10|39->11|41->13|42->14|45->17
                  -- GENERATED --
              */
          