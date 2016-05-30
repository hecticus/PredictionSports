
package views.html.instances

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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.Instance],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(instanceViewForm: Form[models.Instance]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
def /*6.2*/title/*6.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.43*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.12*/("""

"""),_display_(/*8.2*/main(title = Messages("instances.create"), nav = "create")/*8.60*/ {_display_(Seq[Any](format.raw/*8.62*/("""

    """),_display_(/*10.6*/if(flash.containsKey("success"))/*10.38*/ {_display_(Seq[Any](format.raw/*10.40*/("""
        """),format.raw/*11.9*/("""<div class="alert-message warning">
            <strong>Error!</strong> """),_display_(/*12.38*/flash/*12.43*/.get("success")),format.raw/*12.58*/("""
        """),format.raw/*13.9*/("""</div>
    """)))}),format.raw/*14.6*/("""

    """),_display_(/*16.6*/if(instanceViewForm.hasErrors)/*16.36*/ {_display_(Seq[Any](format.raw/*16.38*/("""
        """),format.raw/*17.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*19.22*/Messages("generic.error.title")),format.raw/*19.53*/("""</strong> """),_display_(/*19.64*/Messages("generic.error.content")),format.raw/*19.97*/("""
            """),format.raw/*20.13*/("""<p>"""),_display_(/*20.17*/instanceViewForm/*20.33*/.errorsAsJson),format.raw/*20.46*/("""</p>
        </div>
    """)))}),format.raw/*22.6*/("""


    """),_display_(/*25.6*/helper/*25.12*/.form(action = controllers.routes.InstancesView.submit)/*25.67*/ {_display_(Seq[Any](format.raw/*25.69*/("""

        """),format.raw/*27.9*/("""<fieldset>
            <legend>"""),_display_(/*28.22*/Messages("instances.info")),format.raw/*28.48*/("""</legend>

            """),_display_(/*30.14*/inputText(
                instanceViewForm("ip"),
                '_label -> Messages("instances.ip"),
                '_help -> Messages("instances.ip.help"),
                'class -> "form-control",
                '_error -> instanceViewForm.globalError
            )),format.raw/*36.14*/("""

            """),_display_(/*38.14*/inputText(
                instanceViewForm("name"),
                '_label -> Messages("instances.name"),
                '_help -> Messages("instances.name.help"),
                'class -> "form-control",
                '_error -> instanceViewForm.globalError
            )),format.raw/*44.14*/("""

            """),_display_(/*46.14*/inputText(
                instanceViewForm("running"),
                '_label -> Messages("instances.running"),
                '_help -> Messages("instances.running.help"),
                'class -> "form-control",
                '_error -> instanceViewForm.globalError
            )),format.raw/*52.14*/("""

            """),_display_(/*54.14*/inputText(
                instanceViewForm("test"),
                '_label -> Messages("instances.test"),
                '_help -> Messages("instances.test.help"),
                'class -> "form-control",
                '_error -> instanceViewForm.globalError
            )),format.raw/*60.14*/("""


        """),format.raw/*63.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*66.65*/Messages("instances.submit.create")),format.raw/*66.100*/(""">
            <a href=""""),_display_(/*67.23*/controllers/*67.34*/.routes.InstancesView.list()),format.raw/*67.62*/("""" class="btn btn-primary">"""),_display_(/*67.89*/Messages("generic.cancel")),format.raw/*67.115*/("""</a>
        </div>

    """)))}),format.raw/*70.6*/("""

""")))}))}
  }

  def render(instanceViewForm:Form[models.Instance]): play.twirl.api.HtmlFormat.Appendable = apply(instanceViewForm)

  def f:((Form[models.Instance]) => play.twirl.api.HtmlFormat.Appendable) = (instanceViewForm) => apply(instanceViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/instances/form.scala.html
                  HASH: 842c2ec0d504c9a1143fe03bdc1e148aaa9d3d2c
                  MATRIX: 747->1|907->97|919->102|1013->42|1041->95|1069->107|1097->110|1163->168|1202->170|1235->177|1276->209|1316->211|1352->220|1452->293|1466->298|1502->313|1538->322|1580->334|1613->341|1652->371|1692->373|1728->382|1907->534|1959->565|1997->576|2051->609|2092->622|2123->626|2148->642|2182->655|2237->680|2271->688|2286->694|2350->749|2390->751|2427->761|2486->793|2533->819|2584->843|2877->1115|2919->1130|3218->1408|3260->1423|3568->1710|3610->1725|3909->2003|3947->2014|4081->2121|4138->2156|4189->2180|4209->2191|4258->2219|4312->2246|4360->2272|4416->2298
                  LINES: 26->1|29->6|29->6|32->1|34->5|35->6|37->8|37->8|37->8|39->10|39->10|39->10|40->11|41->12|41->12|41->12|42->13|43->14|45->16|45->16|45->16|46->17|48->19|48->19|48->19|48->19|49->20|49->20|49->20|49->20|51->22|54->25|54->25|54->25|54->25|56->27|57->28|57->28|59->30|65->36|67->38|73->44|75->46|81->52|83->54|89->60|92->63|95->66|95->66|96->67|96->67|96->67|96->67|96->67|99->70
                  -- GENERATED --
              */
          