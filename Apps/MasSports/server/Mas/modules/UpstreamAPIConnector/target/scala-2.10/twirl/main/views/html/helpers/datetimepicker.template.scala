
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
object datetimepicker extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,Field,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nameId: String, initValue: Field, label: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*3.2*/epochToDate/*3.13*/(epoch: String) = {{
     if(nameId.equalsIgnoreCase("date")){
        epoch
     } else {
        if(!epoch.isEmpty){
            val df = new java.text.SimpleDateFormat("yyyyMMddHHmm")
            /**df.setTimeZone(java.util.TimeZone.getDefault())*/
            df.format(epoch.toLong)
        }
     }
}};
Seq[Any](format.raw/*1.51*/("""
"""),format.raw/*2.62*/("""
"""),format.raw/*13.2*/("""
"""),_display_(/*14.2*/defining(initValue.value.getOrElse(""))/*14.41*/ { value =>_display_(Seq[Any](format.raw/*14.52*/("""
    """),format.raw/*15.5*/("""<dl>
        <dt>"""),_display_(/*16.14*/label),format.raw/*16.19*/("""</dt>
        <dd>
            <div class="input-group" id=""""),_display_(/*18.43*/{nameId}),format.raw/*18.51*/("""z" data-date=""""),_display_(/*18.66*/epochToDate(value)),format.raw/*18.84*/("""" data-date-format="dd-mm-yyyy">
                <span class="input-group-addon datepickerbutton">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
                <input type='text' class="form-control" id=""""),_display_(/*22.62*/{nameId}),format.raw/*22.70*/("""" name=""""),_display_(/*22.79*/{nameId}),format.raw/*22.87*/("""" value=""""),_display_(/*22.97*/epochToDate(value)),format.raw/*22.115*/("""" data-date-format="YYYYMMDDHHmm"/>
            </div>
            <span class="add-on">
                <i class="icon-remove-circle" onclick='$("#"""),_display_(/*25.61*/{nameId}),format.raw/*25.69*/("""").val("")'></i>
            </span>
            <script>
                $(function()"""),format.raw/*28.29*/("""{"""),format.raw/*28.30*/("""
                    """),format.raw/*29.21*/("""$('#"""),_display_(/*29.26*/{nameId}),format.raw/*29.34*/("""z').datetimepicker();
                """),format.raw/*30.17*/("""}"""),format.raw/*30.18*/(""");
            </script>
        </dd>
    </dl>
""")))}),format.raw/*34.2*/("""



"""))}
  }

  def render(nameId:String,initValue:Field,label:String): play.twirl.api.HtmlFormat.Appendable = apply(nameId,initValue,label)

  def f:((String,Field,String) => play.twirl.api.HtmlFormat.Appendable) = (nameId,initValue,label) => apply(nameId,initValue,label)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/helpers/datetimepicker.scala.html
                  HASH: 7957543029662a84d030397463119a71ea59c074
                  MATRIX: 753->1|873->114|892->125|1228->50|1256->112|1284->431|1312->433|1360->472|1409->483|1441->488|1486->506|1512->511|1600->572|1629->580|1671->595|1710->613|1992->868|2021->876|2057->885|2086->893|2123->903|2163->921|2339->1070|2368->1078|2482->1164|2511->1165|2560->1186|2592->1191|2621->1199|2687->1237|2716->1238|2796->1288
                  LINES: 26->1|28->3|28->3|39->1|40->2|41->13|42->14|42->14|42->14|43->15|44->16|44->16|46->18|46->18|46->18|46->18|50->22|50->22|50->22|50->22|50->22|50->22|53->25|53->25|56->28|56->28|57->29|57->29|57->29|58->30|58->30|62->34
                  -- GENERATED --
              */
          