
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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Integer,Form[models.Instance],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Integer, instanceViewForm: Form[models.Instance]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.56*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("instances.edit"), nav = "create")/*9.58*/ {_display_(Seq[Any](format.raw/*9.60*/("""

    """),_display_(/*11.6*/if(instanceViewForm.hasErrors)/*11.36*/ {_display_(Seq[Any](format.raw/*11.38*/("""
        """),format.raw/*12.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*14.22*/Messages("generic.error.title")),format.raw/*14.53*/("""</strong> """),_display_(/*14.64*/Messages("generic.error.content")),format.raw/*14.97*/("""
            """),format.raw/*15.13*/("""<p>"""),_display_(/*15.17*/instanceViewForm/*15.33*/.errorsAsJson),format.raw/*15.46*/("""</p>
        </div>
    """)))}),format.raw/*17.6*/("""


    """),_display_(/*20.6*/helper/*20.12*/.form(controllers.routes.InstancesView.update(id), 'enctype -> "multipart/form-data")/*20.97*/ {_display_(Seq[Any](format.raw/*20.99*/("""

        """),format.raw/*22.9*/("""<fieldset>
            <legend>"""),_display_(/*23.22*/Messages("instances.info")),format.raw/*23.48*/("""</legend>

            <div class="row">
                <div class="col-lg-9">
                    """),_display_(/*27.22*/inputText(
                        instanceViewForm("ip"),
                        '_label -> Messages("instances.ip"),
                        '_help -> Messages("instances.ip.help"),
                        'class -> "form-control",
                        '_error -> instanceViewForm.globalError
                    )),format.raw/*33.22*/("""
                """),format.raw/*34.17*/("""</div>
                <div class="col-lg-3">
                    <button id="pinger" class="btn btn-primary" type="button" onclick="ping()">"""),_display_(/*36.97*/Messages("instances.ping")),format.raw/*36.123*/("""</button>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-9">
                """),_display_(/*42.18*/inputText(
                    instanceViewForm("name"),
                    '_label -> Messages("instances.name"),
                    '_help -> Messages("instances.name.help"),
                    'class -> "form-control",
                    '_error -> instanceViewForm.globalError
                )),format.raw/*48.18*/("""
                """),format.raw/*49.17*/("""</div>
                <div class="col-lg-1">
                    """),_display_(/*51.22*/materialCheckbox(
                        instanceViewForm("running"),
                        '_label -> Messages("instances.running"),
                        '_error -> instanceViewForm.globalError,
                        'class -> "allCheckbox"
                    )),format.raw/*56.22*/("""
                """),format.raw/*57.17*/("""</div>
                <div class="col-lg-1">
                    """),_display_(/*59.22*/materialCheckbox(
                        instanceViewForm("test"),
                        '_label -> Messages("instances.test"),
                        '_error -> instanceViewForm.globalError,
                        'class -> "allCheckbox"
                    )),format.raw/*64.22*/("""
                """),format.raw/*65.17*/("""</div>
                <div class="col-lg-1">
                    """),_display_(/*67.22*/materialCheckbox(
                        instanceViewForm("master"),
                        '_label -> Messages("instances.master"),
                        '_error -> instanceViewForm.globalError,
                        'class -> "allCheckbox"
                    )),format.raw/*72.22*/("""
                """),format.raw/*73.17*/("""</div>
            </div>

        </fieldset>

        <div class="actions">
            <input type="submit" value="""),_display_(/*79.41*/Messages("instances.submit.update")),format.raw/*79.76*/(""" """),format.raw/*79.77*/("""class="btn btn-success">
            <a href=""""),_display_(/*80.23*/controllers/*80.34*/.routes.InstancesView.list()),format.raw/*80.62*/("""" class="btn btn-primary">"""),_display_(/*80.89*/Messages("generic.cancel")),format.raw/*80.115*/("""</a>
        </div>

    """)))}),format.raw/*83.6*/("""

    """),_display_(/*85.6*/form(controllers.routes.InstancesView.delete(id), 'class -> "topRight")/*85.77*/ {_display_(Seq[Any](format.raw/*85.79*/("""
        """),format.raw/*86.9*/("""<input type="submit" value="""),_display_(/*86.37*/Messages("instances.submit.delete")),format.raw/*86.72*/(""" """),format.raw/*86.73*/("""class="btn btn-danger">

    """)))}),format.raw/*88.6*/("""
    """),format.raw/*89.5*/("""<script>
        var ping =  function() """),format.raw/*90.32*/("""{"""),format.raw/*90.33*/("""
            """),format.raw/*91.13*/("""var ip = $("#ip").val();
            var pinger = $("#pinger");
            $.ajax("""),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
                """),format.raw/*94.17*/("""url : 'http://'+ip+'/jobcore/alive',
                type: 'GET',
                contentType: "application/json; charset=utf-8",
                dataType: 'text',
                timeout : 60000,
                success : function(data, status) """),format.raw/*99.50*/("""{"""),format.raw/*99.51*/("""
                    """),format.raw/*100.21*/("""pinger.removeClass("btn-danger");
                    pinger.addClass("btn-success");
                """),format.raw/*102.17*/("""}"""),format.raw/*102.18*/(""",
                error : function(xhr, ajaxOptions, thrownError) """),format.raw/*103.65*/("""{"""),format.raw/*103.66*/("""
                    """),format.raw/*104.21*/("""pinger.removeClass("btn-success");
                    pinger.addClass("btn-danger");
                """),format.raw/*106.17*/("""}"""),format.raw/*106.18*/("""
            """),format.raw/*107.13*/("""}"""),format.raw/*107.14*/(""");

        """),format.raw/*109.9*/("""}"""),format.raw/*109.10*/("""
    """),format.raw/*110.5*/("""</script>

""")))}),format.raw/*112.2*/("""
"""))}
  }

  def render(id:Integer,instanceViewForm:Form[models.Instance]): play.twirl.api.HtmlFormat.Appendable = apply(id,instanceViewForm)

  def f:((Integer,Form[models.Instance]) => play.twirl.api.HtmlFormat.Appendable) = (id,instanceViewForm) => apply(id,instanceViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/instances/edit.scala.html
                  HASH: 5a49345457aca08bd5f8e0dd38097ad4e97daa65
                  MATRIX: 755->1|945->128|957->133|1051->55|1079->126|1107->138|1135->141|1199->197|1238->199|1271->206|1310->236|1350->238|1386->247|1565->399|1617->430|1655->441|1709->474|1750->487|1781->491|1806->507|1840->520|1895->545|1929->553|1944->559|2038->644|2078->646|2115->656|2174->688|2221->714|2349->815|2690->1135|2735->1152|2904->1294|2952->1320|3118->1459|3441->1761|3486->1778|3580->1845|3872->2116|3917->2133|4011->2200|4297->2465|4342->2482|4436->2549|4726->2818|4771->2835|4916->2953|4972->2988|5001->2989|5075->3036|5095->3047|5144->3075|5198->3102|5246->3128|5302->3154|5335->3161|5415->3232|5455->3234|5491->3243|5546->3271|5602->3306|5631->3307|5691->3337|5723->3342|5791->3382|5820->3383|5861->3396|5972->3479|6001->3480|6046->3497|6320->3743|6349->3744|6399->3765|6530->3867|6560->3868|6655->3934|6685->3935|6735->3956|6866->4058|6896->4059|6938->4072|6968->4073|7008->4085|7038->4086|7071->4091|7114->4103
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|43->14|43->14|43->14|43->14|44->15|44->15|44->15|44->15|46->17|49->20|49->20|49->20|49->20|51->22|52->23|52->23|56->27|62->33|63->34|65->36|65->36|71->42|77->48|78->49|80->51|85->56|86->57|88->59|93->64|94->65|96->67|101->72|102->73|108->79|108->79|108->79|109->80|109->80|109->80|109->80|109->80|112->83|114->85|114->85|114->85|115->86|115->86|115->86|115->86|117->88|118->89|119->90|119->90|120->91|122->93|122->93|123->94|128->99|128->99|129->100|131->102|131->102|132->103|132->103|133->104|135->106|135->106|136->107|136->107|138->109|138->109|139->110|141->112
                  -- GENERATED --
              */
          