
package views.html.jobs

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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.Job],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(jobViewForm: Form[models.Job]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.33*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("jobs.create"), nav = "create")/*9.55*/ {_display_(Seq[Any](format.raw/*9.57*/("""

    """),_display_(/*11.6*/if(flash.containsKey("success"))/*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
        """),format.raw/*12.9*/("""<div class="alert-message warning">
            <strong>Error!</strong> """),_display_(/*13.38*/flash/*13.43*/.get("success")),format.raw/*13.58*/("""
        """),format.raw/*14.9*/("""</div>
    """)))}),format.raw/*15.6*/("""

    """),_display_(/*17.6*/if(jobViewForm.hasErrors)/*17.31*/ {_display_(Seq[Any](format.raw/*17.33*/("""
        """),format.raw/*18.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*20.22*/Messages("generic.error.title")),format.raw/*20.53*/("""</strong> """),_display_(/*20.64*/Messages("generic.error.content")),format.raw/*20.97*/("""
            """),format.raw/*21.13*/("""<p>"""),_display_(/*21.17*/jobViewForm/*21.28*/.errorsAsJson),format.raw/*21.41*/("""</p>
        </div>
    """)))}),format.raw/*23.6*/("""


    """),_display_(/*26.6*/helper/*26.12*/.form(action = controllers.routes.JobsView.submit)/*26.62*/ {_display_(Seq[Any](format.raw/*26.64*/("""

        """),format.raw/*28.9*/("""<fieldset>
            <legend>"""),_display_(/*29.22*/Messages("jobs.info")),format.raw/*29.43*/("""</legend>

            <div class="row">
                <div class="col-lg-6">
                """),_display_(/*33.18*/select(
                    jobViewForm("status"),
                    options("0"->Messages("jobs.status.off"), "1"->Messages("jobs.status.on"), "2"->Messages("jobs.status.running")),
                    '_label -> Messages("jobs.status"),
                    'class -> "form-control"
                )),format.raw/*38.18*/("""
                """),format.raw/*39.17*/("""</div>
                <div class="col-lg-6">
                """),_display_(/*41.18*/inputText(
                    jobViewForm("className"),
                    '_label -> Messages("jobs.className"),
                    '_help -> Messages("jobs.className.help"),
                    'class -> "form-control",
                    '_error -> jobViewForm.globalError
                )),format.raw/*47.18*/("""
                """),format.raw/*48.17*/("""</div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                """),_display_(/*53.18*/inputText(
                    jobViewForm("name"),
                    '_label -> Messages("jobs.name"),
                    '_help -> Messages("jobs.name.help"),
                    'class -> "form-control",
                    '_error -> jobViewForm.globalError
                )),format.raw/*59.18*/("""
                """),format.raw/*60.17*/("""</div>
                <div class="col-lg-6">
                """),_display_(/*62.18*/inputText(
                    jobViewForm("params"),
                    '_label -> Messages("jobs.params"),
                    '_help -> Messages("jobs.params.help"),
                    'class -> "form-control",
                    '_error -> jobViewForm.globalError
                )),format.raw/*68.18*/("""
                """),format.raw/*69.17*/("""</div>
            </div>

            <div class="row">

                <div class="col-lg-6">
                    <h2>"""),_display_(/*75.26*/Messages("jobs.daemon")),format.raw/*75.49*/("""</h2>
                    <div class="row">
                        <div class="col-lg-6">
                        """),_display_(/*78.26*/materialCheckbox(
                            jobViewForm("daemon"),
                            'label -> Messages("jobs.daemon"),
                            '_help -> Messages("jobs.daemon.help"),
                            'class -> "daemonCheckbox"
                        )),format.raw/*83.26*/("""
                        """),format.raw/*84.25*/("""</div>
                        <div class="col-lg-6">
                        """),_display_(/*86.26*/inputText(
                            jobViewForm("daemonFrequency"),
                            '_label -> Messages("jobs.daemon.frequency"),
                            '_help -> Messages("jobs.daemon.frequency.help"),
                            'class -> "form-control daemonFrequency",
                            '_error -> jobViewForm.globalError
                        )),format.raw/*92.26*/("""
                        """),format.raw/*93.25*/("""</div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <h2>"""),_display_(/*98.26*/Messages("jobs.multiinstance")),format.raw/*98.56*/("""</h2>
                    <div class="row">
                        <div class="col-lg-6">
                        """),_display_(/*101.26*/materialCheckbox(
                            jobViewForm("multiInstance"),
                            'label -> Messages("jobs.multiinstance"),
                            '_help -> Messages("jobs.multiinstance.help"),
                            'class -> "multiInstanceCheckbox"
                        )),format.raw/*106.26*/("""
                        """),format.raw/*107.25*/("""</div>
                        <div class="col-lg-6">
                        """),_display_(/*109.26*/inputText(
                            jobViewForm("quantity"),
                            '_label -> Messages("jobs.multiinstance.quantity"),
                            '_help -> Messages("jobs.multiinstance.quantity.help"),
                            'class -> "form-control quantity",
                            '_error -> jobViewForm.globalError
                        )),format.raw/*115.26*/("""
                        """),format.raw/*116.25*/("""</div>
                    </div>
                </div>
            </div>

            <h2>"""),_display_(/*121.18*/Messages("jobs.scheduled")),format.raw/*121.44*/("""</h2>

            <div class="row">
                <div class="col-lg-2">
                """),_display_(/*125.18*/inputText(
                    jobViewForm("nextTimestamp"),
                    '_label -> Messages("jobs.nextTimestamp"),
                    '_help -> Messages("jobs.nextTimestamp.help"),
                    'class -> "form-control frequencyField",
                    '_error -> jobViewForm.globalError
                )),format.raw/*131.18*/("""
                """),format.raw/*132.17*/("""</div>
                <div class="col-lg-2">
                """),_display_(/*134.18*/select(
                    jobViewForm("frequency"),
                    options("0"->Messages("jobs.frequency.once"), "1"->Messages("jobs.frequency.year"), "2"->Messages("jobs.frequency.month") , "3"->Messages("jobs.frequency.week"), "4"->Messages("jobs.frequency.day")),
                    '_label -> Messages("jobs.frequency"),
                    '_help -> Messages("jobs.frequency.help"),
                    'class -> "form-control frequencyField"
                )),format.raw/*140.18*/("""
                """),format.raw/*141.17*/("""</div>
                <div class="col-lg-8">
                    <div class="row frequencyField" id="days">
                        <div class="col-md-1">
                        """),_display_(/*145.26*/materialCheckbox(
                            jobViewForm("sunday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*149.26*/("""
                        """),format.raw/*150.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*152.26*/materialCheckbox(
                            jobViewForm("monday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*156.26*/("""
                        """),format.raw/*157.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*159.26*/materialCheckbox(
                            jobViewForm("tuesday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*163.26*/("""
                        """),format.raw/*164.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*166.26*/materialCheckbox(
                            jobViewForm("wednesday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*170.26*/("""
                        """),format.raw/*171.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*173.26*/materialCheckbox(
                            jobViewForm("thursday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*177.26*/("""
                        """),format.raw/*178.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*180.26*/materialCheckbox(
                            jobViewForm("friday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*184.26*/("""
                        """),format.raw/*185.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*187.26*/materialCheckbox(
                            jobViewForm("saturday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*191.26*/("""
                        """),format.raw/*192.25*/("""</div>
                    </div>
                </div>

            </div>

            """),_display_(/*198.14*/jobdatetimepicker(
                jobViewForm("Date"),
                "date",
                Messages("jobs.timeParams"),
                'format -> "DD/MM/YYYY-HH:mm",
                'class -> "form-control frequencyField"
            )),format.raw/*204.14*/("""


        """),format.raw/*207.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*210.65*/Messages("jobs.submit.create")),format.raw/*210.95*/(""">
            <a href=""""),_display_(/*211.23*/controllers/*211.34*/.routes.JobsView.list()),format.raw/*211.57*/("""" class="btn btn-primary">"""),_display_(/*211.84*/Messages("generic.cancel")),format.raw/*211.110*/("""</a>
        </div>

    """)))}),format.raw/*214.6*/("""

    """),format.raw/*216.5*/("""<script>

        $('.daemonCheckbox').click(function(e) """),format.raw/*218.48*/("""{"""),format.raw/*218.49*/("""
            """),format.raw/*219.13*/("""var attr = $('.frequencyField').attr('disabled');
            if (!("this").checked && typeof attr !== typeof undefined && attr === 'disabled') """),format.raw/*220.95*/("""{"""),format.raw/*220.96*/("""
                """),format.raw/*221.17*/("""$('.frequencyField').removeAttr('disabled');
                $('.daemonFrequency').attr('disabled','disabled');
                $('.quantity').attr('disabled','disabled');
                $('.multiInstanceCheckbox').attr('disabled','disabled');
            """),format.raw/*225.13*/("""}"""),format.raw/*225.14*/(""" """),format.raw/*225.15*/("""else """),format.raw/*225.20*/("""{"""),format.raw/*225.21*/("""
                """),format.raw/*226.17*/("""$('.frequencyField').attr('disabled','disabled');
                $('.daemonFrequency').removeAttr('disabled');
                $('.quantity').removeAttr('disabled');
                $('.multiInstanceCheckbox').removeAttr('disabled');
            """),format.raw/*230.13*/("""}"""),format.raw/*230.14*/("""
        """),format.raw/*231.9*/("""}"""),format.raw/*231.10*/(""")

        $('#frequency').change(function(e) """),format.raw/*233.44*/("""{"""),format.raw/*233.45*/("""
            """),format.raw/*234.13*/("""console.log($(this).val());
            if($(this).val() == "3")"""),format.raw/*235.37*/("""{"""),format.raw/*235.38*/("""
                """),format.raw/*236.17*/("""$('#days').show();
                $('#Datez').attr("data-date-pickDate", false);
                $('#Date').attr("data-date-pickDate", false);
            """),format.raw/*239.13*/("""}"""),format.raw/*239.14*/(""" """),format.raw/*239.15*/("""else """),format.raw/*239.20*/("""{"""),format.raw/*239.21*/("""
                """),format.raw/*240.17*/("""$('#days').hide();
                $('#Datez').removeAttr('data-date-pickDate');
                $('#Date').removeAttr('data-date-pickDate');
            """),format.raw/*243.13*/("""}"""),format.raw/*243.14*/("""
        """),format.raw/*244.9*/("""}"""),format.raw/*244.10*/(""")

        $(document).ready(function(e)"""),format.raw/*246.38*/("""{"""),format.raw/*246.39*/("""
            """),format.raw/*247.13*/("""if (document.getElementById("daemon").checked)"""),format.raw/*247.59*/("""{"""),format.raw/*247.60*/("""
                """),format.raw/*248.17*/("""$('.frequencyField').attr('disabled','disabled');
            """),format.raw/*249.13*/("""}"""),format.raw/*249.14*/(""" """),format.raw/*249.15*/("""else """),format.raw/*249.20*/("""{"""),format.raw/*249.21*/("""
                """),format.raw/*250.17*/("""$('.daemonFrequency').attr('disabled','disabled');
                $('.quantity').attr('disabled','disabled');
                $('.multiInstanceCheckbox').attr('disabled','disabled');
            """),format.raw/*253.13*/("""}"""),format.raw/*253.14*/("""
            """),format.raw/*254.13*/("""if ($("#frequency").val() == "3")"""),format.raw/*254.46*/("""{"""),format.raw/*254.47*/("""
                """),format.raw/*255.17*/("""$('#days').show();
                $('#Datez').attr("data-date-pickDate", false);
                $('#Date').attr("data-date-pickDate", false);
            """),format.raw/*258.13*/("""}"""),format.raw/*258.14*/(""" """),format.raw/*258.15*/("""else """),format.raw/*258.20*/("""{"""),format.raw/*258.21*/("""
                """),format.raw/*259.17*/("""$('#days').hide();
                $('#Datez').removeAttr('data-date-pickDate');
                $('#Date').removeAttr('data-date-pickDate');
            """),format.raw/*262.13*/("""}"""),format.raw/*262.14*/("""
        """),format.raw/*263.9*/("""}"""),format.raw/*263.10*/(""");

    </script>
""")))}))}
  }

  def render(jobViewForm:Form[models.Job]): play.twirl.api.HtmlFormat.Appendable = apply(jobViewForm)

  def f:((Form[models.Job]) => play.twirl.api.HtmlFormat.Appendable) = (jobViewForm) => apply(jobViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/jobs/form.scala.html
                  HASH: c2d7753a4aa412439e9a29f1c3a6fabced38bd95
                  MATRIX: 737->1|904->105|916->110|1010->32|1038->103|1066->115|1094->118|1155->171|1194->173|1227->180|1268->212|1308->214|1344->223|1444->296|1458->301|1494->316|1530->325|1572->337|1605->344|1639->369|1679->371|1715->380|1894->532|1946->563|1984->574|2038->607|2079->620|2110->624|2130->635|2164->648|2219->673|2253->681|2268->687|2327->737|2367->739|2404->749|2463->781|2505->802|2629->899|2953->1202|2998->1219|3088->1282|3406->1579|3451->1596|3591->1709|3894->1991|3939->2008|4029->2071|4338->2359|4383->2376|4532->2498|4576->2521|4719->2637|5020->2917|5073->2942|5179->3021|5581->3402|5634->3427|5783->3549|5834->3579|5978->3695|6308->4003|6362->4028|6469->4107|6870->4486|6924->4511|7046->4605|7094->4631|7215->4724|7561->5048|7607->5065|7698->5128|8193->5601|8239->5618|8448->5799|8682->6011|8736->6036|8843->6115|9077->6327|9131->6352|9238->6431|9473->6644|9527->6669|9634->6748|9871->6963|9925->6988|10032->7067|10268->7281|10322->7306|10429->7385|10663->7597|10717->7622|10824->7701|11060->7915|11114->7940|11233->8031|11496->8272|11535->8283|11670->8390|11722->8420|11774->8444|11795->8455|11840->8478|11895->8505|11944->8531|12001->8557|12035->8563|12121->8620|12151->8621|12193->8634|12366->8778|12396->8779|12442->8796|12728->9053|12758->9054|12788->9055|12822->9060|12852->9061|12898->9078|13174->9325|13204->9326|13241->9335|13271->9336|13346->9382|13376->9383|13418->9396|13511->9460|13541->9461|13587->9478|13772->9634|13802->9635|13832->9636|13866->9641|13896->9642|13942->9659|14125->9813|14155->9814|14192->9823|14222->9824|14291->9864|14321->9865|14363->9878|14438->9924|14468->9925|14514->9942|14605->10004|14635->10005|14665->10006|14699->10011|14729->10012|14775->10029|15000->10225|15030->10226|15072->10239|15134->10272|15164->10273|15210->10290|15395->10446|15425->10447|15455->10448|15489->10453|15519->10454|15565->10471|15748->10625|15778->10626|15815->10635|15845->10636
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|42->13|42->13|42->13|43->14|44->15|46->17|46->17|46->17|47->18|49->20|49->20|49->20|49->20|50->21|50->21|50->21|50->21|52->23|55->26|55->26|55->26|55->26|57->28|58->29|58->29|62->33|67->38|68->39|70->41|76->47|77->48|82->53|88->59|89->60|91->62|97->68|98->69|104->75|104->75|107->78|112->83|113->84|115->86|121->92|122->93|127->98|127->98|130->101|135->106|136->107|138->109|144->115|145->116|150->121|150->121|154->125|160->131|161->132|163->134|169->140|170->141|174->145|178->149|179->150|181->152|185->156|186->157|188->159|192->163|193->164|195->166|199->170|200->171|202->173|206->177|207->178|209->180|213->184|214->185|216->187|220->191|221->192|227->198|233->204|236->207|239->210|239->210|240->211|240->211|240->211|240->211|240->211|243->214|245->216|247->218|247->218|248->219|249->220|249->220|250->221|254->225|254->225|254->225|254->225|254->225|255->226|259->230|259->230|260->231|260->231|262->233|262->233|263->234|264->235|264->235|265->236|268->239|268->239|268->239|268->239|268->239|269->240|272->243|272->243|273->244|273->244|275->246|275->246|276->247|276->247|276->247|277->248|278->249|278->249|278->249|278->249|278->249|279->250|282->253|282->253|283->254|283->254|283->254|284->255|287->258|287->258|287->258|287->258|287->258|288->259|291->262|291->262|292->263|292->263
                  -- GENERATED --
              */
          