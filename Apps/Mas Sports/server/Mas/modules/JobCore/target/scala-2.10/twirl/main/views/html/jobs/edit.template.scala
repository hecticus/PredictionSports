
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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Long,Form[models.Job],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, jobViewForm: Form[models.Job]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.43*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),_display_(/*9.2*/main(title = Messages("jobs.edit"), nav = "create")/*9.53*/ {_display_(Seq[Any](format.raw/*9.55*/("""

    """),_display_(/*11.6*/if(jobViewForm.hasErrors)/*11.31*/ {_display_(Seq[Any](format.raw/*11.33*/("""
        """),format.raw/*12.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*14.22*/Messages("generic.error.title")),format.raw/*14.53*/("""</strong> """),_display_(/*14.64*/Messages("generic.error.content")),format.raw/*14.97*/("""
            """),format.raw/*15.13*/("""<p>"""),_display_(/*15.17*/jobViewForm/*15.28*/.errorsAsJson),format.raw/*15.41*/("""</p>
        </div>
    """)))}),format.raw/*17.6*/("""


    """),_display_(/*20.6*/helper/*20.12*/.form(controllers.routes.JobsView.update(id), 'enctype -> "multipart/form-data")/*20.92*/ {_display_(Seq[Any](format.raw/*20.94*/("""

        """),format.raw/*22.9*/("""<fieldset>
            <legend>"""),_display_(/*23.22*/Messages("jobs.info")),format.raw/*23.43*/("""</legend>

            <div class="row">
                <div class="col-lg-6">
                    """),_display_(/*27.22*/select(
                        jobViewForm("status"),
                        options("0"->Messages("jobs.status.off"), "1"->Messages("jobs.status.on"), "2"->Messages("jobs.status.running")),
                        '_label -> Messages("jobs.status"),
                        'class -> "form-control"
                    )),format.raw/*32.22*/("""
                """),format.raw/*33.17*/("""</div>
                <div class="col-lg-6">
                    """),_display_(/*35.22*/inputText(
                        jobViewForm("className"),
                        '_label -> Messages("jobs.className"),
                        '_help -> Messages("jobs.className.help"),
                        'class -> "form-control",
                        '_error -> jobViewForm.globalError
                    )),format.raw/*41.22*/("""
                """),format.raw/*42.17*/("""</div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    """),_display_(/*47.22*/inputText(
                        jobViewForm("name"),
                        '_label -> Messages("jobs.name"),
                        '_help -> Messages("jobs.name.help"),
                        'class -> "form-control",
                        '_error -> jobViewForm.globalError
                    )),format.raw/*53.22*/("""
                """),format.raw/*54.17*/("""</div>
                <div class="col-lg-6">
                    """),_display_(/*56.22*/inputText(
                        jobViewForm("params"),
                        '_label -> Messages("jobs.params"),
                        '_help -> Messages("jobs.params.help"),
                        'class -> "form-control",
                        '_error -> jobViewForm.globalError
                    )),format.raw/*62.22*/("""
                """),format.raw/*63.17*/("""</div>
            </div>

            <div class="row">

                <div class="col-lg-6">
                    <h2>"""),_display_(/*69.26*/Messages("jobs.daemon")),format.raw/*69.49*/("""</h2>
                    <div class="row">
                        <div class="col-lg-6">
                            """),_display_(/*72.30*/materialCheckbox(
                                jobViewForm("daemon"),
                                'label -> Messages("jobs.daemon"),
                                '_help -> Messages("jobs.daemon.help"),
                                'class -> "daemonCheckbox"
                            )),format.raw/*77.30*/("""
                        """),format.raw/*78.25*/("""</div>
                        <div class="col-lg-6">
                            """),_display_(/*80.30*/inputText(
                                jobViewForm("daemonFrequency"),
                                '_label -> Messages("jobs.daemon.frequency"),
                                '_help -> Messages("jobs.daemon.frequency.help"),
                                'class -> "form-control daemonFrequency",
                                '_error -> jobViewForm.globalError
                            )),format.raw/*86.30*/("""
                        """),format.raw/*87.25*/("""</div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <h2>"""),_display_(/*92.26*/Messages("jobs.multiinstance")),format.raw/*92.56*/("""</h2>
                    <div class="row">
                        <div class="col-lg-6">
                            """),_display_(/*95.30*/materialCheckbox(
                                jobViewForm("multiInstance"),
                                'label -> Messages("jobs.multiinstance"),
                                '_help -> Messages("jobs.multiinstance.help"),
                                'class -> "multiInstanceCheckbox"
                            )),format.raw/*100.30*/("""
                        """),format.raw/*101.25*/("""</div>
                        <div class="col-lg-6">
                            """),_display_(/*103.30*/inputText(
                                jobViewForm("quantity"),
                                '_label -> Messages("jobs.multiinstance.quantity"),
                                '_help -> Messages("jobs.multiinstance.quantity.help"),
                                'class -> "form-control quantity",
                                '_error -> jobViewForm.globalError
                            )),format.raw/*109.30*/("""
                        """),format.raw/*110.25*/("""</div>
                    </div>
                </div>
            </div>

            <h2>"""),_display_(/*115.18*/Messages("jobs.scheduled")),format.raw/*115.44*/("""</h2>

            <div class="row">
                <div class="col-lg-2">
                    """),_display_(/*119.22*/inputText(
                        jobViewForm("nextTimestamp"),
                        '_label -> Messages("jobs.nextTimestamp"),
                        '_help -> Messages("jobs.nextTimestamp.help"),
                        'class -> "form-control frequencyField",
                        '_error -> jobViewForm.globalError
                    )),format.raw/*125.22*/("""
                """),format.raw/*126.17*/("""</div>
                <div class="col-lg-2">
                    """),_display_(/*128.22*/select(
                        jobViewForm("frequency"),
                        options("0"->Messages("jobs.frequency.once"), "1"->Messages("jobs.frequency.year"), "2"->Messages("jobs.frequency.month") , "3"->Messages("jobs.frequency.week"), "4"->Messages("jobs.frequency.day")),
                        '_label -> Messages("jobs.frequency"),
                        '_help -> Messages("jobs.frequency.help"),
                        'class -> "form-control frequencyField"
                    )),format.raw/*134.22*/("""
                """),format.raw/*135.17*/("""</div>
                <div class="col-lg-8">
                    <div class="row frequencyField" id="days">
                        <div class="col-md-1">
                        """),_display_(/*139.26*/materialCheckbox(
                            jobViewForm("sunday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*143.26*/("""
                        """),format.raw/*144.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*146.26*/materialCheckbox(
                            jobViewForm("monday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*150.26*/("""
                        """),format.raw/*151.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*153.26*/materialCheckbox(
                            jobViewForm("tuesday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*157.26*/("""
                        """),format.raw/*158.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*160.26*/materialCheckbox(
                            jobViewForm("wednesday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*164.26*/("""
                        """),format.raw/*165.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*167.26*/materialCheckbox(
                            jobViewForm("thursday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*171.26*/("""
                        """),format.raw/*172.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*174.26*/materialCheckbox(
                            jobViewForm("friday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*178.26*/("""
                        """),format.raw/*179.25*/("""</div>
                        <div class="col-md-1">
                        """),_display_(/*181.26*/materialCheckbox(
                            jobViewForm("saturday"),
                            'label -> Messages("jobs.daemon"),
                            'class -> "frequencyField"
                        )),format.raw/*185.26*/("""
                        """),format.raw/*186.25*/("""</div>
                    </div>
                </div>

            </div>

            """),_display_(/*192.14*/jobdatetimepicker(
                jobViewForm("Date"),
                "date",
                Messages("jobs.timeParams"),
                'format -> "DD/MM/YYYY-HH:mm",
                'class -> "form-control frequencyField"
            )),format.raw/*198.14*/("""

        """),format.raw/*200.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" value="""),_display_(/*203.41*/Messages("jobs.submit.update")),format.raw/*203.71*/(""" """),format.raw/*203.72*/("""class="btn btn-success">
            <a href=""""),_display_(/*204.23*/controllers/*204.34*/.routes.JobsView.list()),format.raw/*204.57*/("""" class="btn btn-primary">"""),_display_(/*204.84*/Messages("generic.cancel")),format.raw/*204.110*/("""</a>
        </div>

    """)))}),format.raw/*207.6*/("""

    """),_display_(/*209.6*/form(controllers.routes.JobsView.delete(id), 'class -> "topRight")/*209.72*/ {_display_(Seq[Any](format.raw/*209.74*/("""
        """),format.raw/*210.9*/("""<input type="submit" value="""),_display_(/*210.37*/Messages("jobs.submit.delete")),format.raw/*210.67*/(""" """),format.raw/*210.68*/("""class="btn btn-danger">

    """)))}),format.raw/*212.6*/("""

    """),format.raw/*214.5*/("""<script>

        $('.daemonCheckbox').click(function(e) """),format.raw/*216.48*/("""{"""),format.raw/*216.49*/("""
            """),format.raw/*217.13*/("""var attr = $('.frequencyField').attr('disabled');
            if (!("this").checked && typeof attr !== typeof undefined && attr === 'disabled') """),format.raw/*218.95*/("""{"""),format.raw/*218.96*/("""
                """),format.raw/*219.17*/("""$('.frequencyField').removeAttr('disabled');
                $('.daemonFrequency').attr('disabled','disabled');
                $('.quantity').attr('disabled','disabled');
                $('.multiInstanceCheckbox').attr('disabled','disabled');
            """),format.raw/*223.13*/("""}"""),format.raw/*223.14*/(""" """),format.raw/*223.15*/("""else """),format.raw/*223.20*/("""{"""),format.raw/*223.21*/("""
                """),format.raw/*224.17*/("""$('.frequencyField').attr('disabled','disabled');
                $('.daemonFrequency').removeAttr('disabled');
                $('.quantity').removeAttr('disabled');
                $('.multiInstanceCheckbox').removeAttr('disabled');
            """),format.raw/*228.13*/("""}"""),format.raw/*228.14*/("""
        """),format.raw/*229.9*/("""}"""),format.raw/*229.10*/(""")

        $('#frequency').change(function(e) """),format.raw/*231.44*/("""{"""),format.raw/*231.45*/("""
            """),format.raw/*232.13*/("""console.log($(this).val());
            if($(this).val() == "3")"""),format.raw/*233.37*/("""{"""),format.raw/*233.38*/("""
                """),format.raw/*234.17*/("""$('#days').show();
                $('#Datez').attr("data-date-pickDate", false);
                $('#Date').attr("data-date-pickDate", false);
            """),format.raw/*237.13*/("""}"""),format.raw/*237.14*/(""" """),format.raw/*237.15*/("""else """),format.raw/*237.20*/("""{"""),format.raw/*237.21*/("""
                """),format.raw/*238.17*/("""$('#days').hide();
                $('#Datez').removeAttr('data-date-pickDate');
                $('#Date').removeAttr('data-date-pickDate');
            """),format.raw/*241.13*/("""}"""),format.raw/*241.14*/("""
        """),format.raw/*242.9*/("""}"""),format.raw/*242.10*/(""")

        $(document).ready(function(e)"""),format.raw/*244.38*/("""{"""),format.raw/*244.39*/("""
            """),format.raw/*245.13*/("""if (document.getElementById("daemon").checked)"""),format.raw/*245.59*/("""{"""),format.raw/*245.60*/("""
                """),format.raw/*246.17*/("""$('.frequencyField').attr('disabled','disabled');
            """),format.raw/*247.13*/("""}"""),format.raw/*247.14*/(""" """),format.raw/*247.15*/("""else """),format.raw/*247.20*/("""{"""),format.raw/*247.21*/("""
                """),format.raw/*248.17*/("""$('.daemonFrequency').attr('disabled','disabled');
                $('.quantity').attr('disabled','disabled');
                $('.multiInstanceCheckbox').attr('disabled','disabled');
            """),format.raw/*251.13*/("""}"""),format.raw/*251.14*/("""
            """),format.raw/*252.13*/("""if ($("#frequency").val() == "3")"""),format.raw/*252.46*/("""{"""),format.raw/*252.47*/("""
                """),format.raw/*253.17*/("""$('#days').show();
                $('#Datez').attr("data-date-pickDate", false);
                $('#Date').attr("data-date-pickDate", false);
            """),format.raw/*256.13*/("""}"""),format.raw/*256.14*/(""" """),format.raw/*256.15*/("""else """),format.raw/*256.20*/("""{"""),format.raw/*256.21*/("""
                """),format.raw/*257.17*/("""$('#days').hide();
                $('#Datez').removeAttr('data-date-pickDate');
                $('#Date').removeAttr('data-date-pickDate');
            """),format.raw/*260.13*/("""}"""),format.raw/*260.14*/("""
        """),format.raw/*261.9*/("""}"""),format.raw/*261.10*/(""");

    </script>

""")))}),format.raw/*265.2*/("""
"""))}
  }

  def render(id:Long,jobViewForm:Form[models.Job]): play.twirl.api.HtmlFormat.Appendable = apply(id,jobViewForm)

  def f:((Long,Form[models.Job]) => play.twirl.api.HtmlFormat.Appendable) = (id,jobViewForm) => apply(id,jobViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/jobs/edit.scala.html
                  HASH: 2fcfca828d6bea0d1fdd657349ce3567566fdc21
                  MATRIX: 742->1|919->115|931->120|1025->42|1053->113|1081->125|1109->128|1168->179|1207->181|1240->188|1274->213|1314->215|1350->224|1529->376|1581->407|1619->418|1673->451|1714->464|1745->468|1765->479|1799->492|1854->517|1888->525|1903->531|1992->611|2032->613|2069->623|2128->655|2170->676|2298->777|2642->1100|2687->1117|2781->1184|3123->1505|3168->1522|3312->1639|3639->1945|3684->1962|3778->2029|4111->2341|4156->2358|4305->2480|4349->2503|4496->2623|4817->2923|4870->2948|4980->3031|5406->3436|5459->3461|5608->3583|5659->3613|5806->3733|6156->4061|6210->4086|6321->4169|6746->4572|6800->4597|6922->4691|6970->4717|7095->4814|7465->5162|7511->5179|7606->5246|8125->5743|8171->5760|8380->5941|8614->6153|8668->6178|8775->6257|9009->6469|9063->6494|9170->6573|9405->6786|9459->6811|9566->6890|9803->7105|9857->7130|9964->7209|10200->7423|10254->7448|10361->7527|10595->7739|10649->7764|10756->7843|10992->8057|11046->8082|11165->8173|11428->8414|11466->8424|11577->8507|11629->8537|11659->8538|11734->8585|11755->8596|11800->8619|11855->8646|11904->8672|11961->8698|11995->8705|12071->8771|12112->8773|12149->8782|12205->8810|12257->8840|12287->8841|12348->8871|12382->8877|12468->8934|12498->8935|12540->8948|12713->9092|12743->9093|12789->9110|13075->9367|13105->9368|13135->9369|13169->9374|13199->9375|13245->9392|13521->9639|13551->9640|13588->9649|13618->9650|13693->9696|13723->9697|13765->9710|13858->9774|13888->9775|13934->9792|14119->9948|14149->9949|14179->9950|14213->9955|14243->9956|14289->9973|14472->10127|14502->10128|14539->10137|14569->10138|14638->10178|14668->10179|14710->10192|14785->10238|14815->10239|14861->10256|14952->10318|14982->10319|15012->10320|15046->10325|15076->10326|15122->10343|15347->10539|15377->10540|15419->10553|15481->10586|15511->10587|15557->10604|15742->10760|15772->10761|15802->10762|15836->10767|15866->10768|15912->10785|16095->10939|16125->10940|16162->10949|16192->10950|16243->10970
                  LINES: 26->1|30->7|30->7|33->1|35->6|36->7|38->9|38->9|38->9|40->11|40->11|40->11|41->12|43->14|43->14|43->14|43->14|44->15|44->15|44->15|44->15|46->17|49->20|49->20|49->20|49->20|51->22|52->23|52->23|56->27|61->32|62->33|64->35|70->41|71->42|76->47|82->53|83->54|85->56|91->62|92->63|98->69|98->69|101->72|106->77|107->78|109->80|115->86|116->87|121->92|121->92|124->95|129->100|130->101|132->103|138->109|139->110|144->115|144->115|148->119|154->125|155->126|157->128|163->134|164->135|168->139|172->143|173->144|175->146|179->150|180->151|182->153|186->157|187->158|189->160|193->164|194->165|196->167|200->171|201->172|203->174|207->178|208->179|210->181|214->185|215->186|221->192|227->198|229->200|232->203|232->203|232->203|233->204|233->204|233->204|233->204|233->204|236->207|238->209|238->209|238->209|239->210|239->210|239->210|239->210|241->212|243->214|245->216|245->216|246->217|247->218|247->218|248->219|252->223|252->223|252->223|252->223|252->223|253->224|257->228|257->228|258->229|258->229|260->231|260->231|261->232|262->233|262->233|263->234|266->237|266->237|266->237|266->237|266->237|267->238|270->241|270->241|271->242|271->242|273->244|273->244|274->245|274->245|274->245|275->246|276->247|276->247|276->247|276->247|276->247|277->248|280->251|280->251|281->252|281->252|281->252|282->253|285->256|285->256|285->256|285->256|285->256|286->257|289->260|289->260|290->261|290->261|294->265
                  -- GENERATED --
              */
          