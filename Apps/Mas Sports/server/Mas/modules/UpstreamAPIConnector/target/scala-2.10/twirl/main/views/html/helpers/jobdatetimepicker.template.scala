
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
object jobdatetimepicker extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Field,String,String,Array[scala.Tuple2[Symbol, Any]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(initValue: Field, nameId: String, label: String, args: (Symbol,Any)*):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*3.2*/epochToDate/*3.13*/(epoch: String) = {{
     if(nameId.equalsIgnoreCase("date")){
        epoch
     } else {
        if(!epoch.isEmpty){
            val df = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
            /**df.setTimeZone(java.util.TimeZone.getDefault())*/
            df.format(epoch.toLong)
        }
     }
}};
Seq[Any](format.raw/*1.72*/("""
"""),format.raw/*2.62*/("""
"""),format.raw/*13.2*/("""


"""),_display_(/*16.2*/helper/*16.8*/.input(initValue, args:_*)/*16.34*/ { (id, name, value, htmlArgs) =>_display_(Seq[Any](format.raw/*16.67*/("""
    """),format.raw/*17.5*/("""<dl>
        <dt>"""),_display_(/*18.14*/label),format.raw/*18.19*/("""</dt>
        <dd>
            <div class="input-group" id=""""),_display_(/*20.43*/{name}),format.raw/*20.49*/("""z" data-date=""""),_display_(/*20.64*/epochToDate(value.getOrElse(""+System.currentTimeMillis))),format.raw/*20.121*/("""" data-date-format="""),_display_(/*20.141*/htmlArgs('format)),format.raw/*20.158*/(""" """),format.raw/*20.159*/(""">
                <span class="input-group-addon datepickerbutton">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
                <input type='text' id=""""),_display_(/*24.41*/id),format.raw/*24.43*/("""" name=""""),_display_(/*24.52*/name),format.raw/*24.56*/("""" value=""""),_display_(/*24.66*/epochToDate(value.getOrElse(""+System.currentTimeMillis))),format.raw/*24.123*/("""" data-date-format="""),_display_(/*24.143*/htmlArgs('format)),format.raw/*24.160*/(""" """),_display_(/*24.162*/toHtmlArgs(htmlArgs.filterKeys(_ != 'value))),format.raw/*24.206*/(""" """),format.raw/*24.207*/("""/>
            </div>
            <span class="add-on">
                <i class="icon-remove-circle" onclick='$("#"""),_display_(/*27.61*/name),format.raw/*27.65*/("""").val("")'></i>
            </span>
            <script>
                $(function()"""),format.raw/*30.29*/("""{"""),format.raw/*30.30*/("""
                    """),format.raw/*31.21*/("""$('#"""),_display_(/*31.26*/{name}),format.raw/*31.32*/("""z').datetimepicker();
                """),format.raw/*32.17*/("""}"""),format.raw/*32.18*/(""");
            </script>
        </dd>
    </dl>
""")))}),format.raw/*36.2*/("""



"""))}
  }

  def render(initValue:Field,nameId:String,label:String,args:Array[scala.Tuple2[Symbol, Any]]): play.twirl.api.HtmlFormat.Appendable = apply(initValue,nameId,label,args:_*)

  def f:((Field,String,String,Array[scala.Tuple2[Symbol, Any]]) => play.twirl.api.HtmlFormat.Appendable) = (initValue,nameId,label,args) => apply(initValue,nameId,label,args:_*)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/helpers/jobdatetimepicker.scala.html
                  HASH: 0a2a4710e6e599cb80b79c7dd44d7a4ef3ad8ff1
                  MATRIX: 789->1|930->135|949->146|1289->71|1317->133|1345->456|1375->460|1389->466|1424->492|1495->525|1527->530|1572->548|1598->553|1686->614|1713->620|1755->635|1834->692|1882->712|1921->729|1951->730|2181->933|2204->935|2240->944|2265->948|2302->958|2381->1015|2429->1035|2468->1052|2498->1054|2564->1098|2594->1099|2737->1215|2762->1219|2876->1305|2905->1306|2954->1327|2986->1332|3013->1338|3079->1376|3108->1377|3188->1427
                  LINES: 26->1|28->3|28->3|39->1|40->2|41->13|44->16|44->16|44->16|44->16|45->17|46->18|46->18|48->20|48->20|48->20|48->20|48->20|48->20|48->20|52->24|52->24|52->24|52->24|52->24|52->24|52->24|52->24|52->24|52->24|52->24|55->27|55->27|58->30|58->30|59->31|59->31|59->31|60->32|60->32|64->36
                  -- GENERATED --
              */
          