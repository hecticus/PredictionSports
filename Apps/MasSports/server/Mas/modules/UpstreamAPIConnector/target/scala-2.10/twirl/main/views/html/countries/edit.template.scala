
package views.html.countries

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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Integer,Form[models.basic.Country],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Integer, countryViewForm: Form[models.basic.Country]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*8.2*/title/*8.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};def /*11.2*/timezoneGroup/*11.15*/(field: Field, className: String = "timezone"):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*11.65*/("""
    """),format.raw/*12.5*/("""<div class="twipsies well """),_display_(/*12.32*/className),format.raw/*12.41*/("""">
        <div class="row">
            <div class="col-lg-7">
            """),_display_(/*15.14*/select(
                field("timezone.idTimezone"),
                models.basic.Timezone.toSeq,
                '_label -> Messages("countries.timezone"),
                'class -> "form-control"
            )),format.raw/*20.14*/("""
            """),format.raw/*21.13*/("""</div>
            <div class="col-lg-1">
                """),_display_(/*23.18*/materialCheckbox(
                    field("active"),
                    '_label -> Messages("countries.timezone.active"),
                    'class -> "timezoneCheckbox"
                )),format.raw/*27.18*/("""
            """),format.raw/*28.13*/("""</div>
            <div class="col-lg-4">
                <a class="removeTimezone btn btn-primary danger pull-right">"""),_display_(/*30.78*/Messages("countries.timezone.remove")),format.raw/*30.115*/("""</a>
            </div>
        </div>
    </div>
""")))};
Seq[Any](format.raw/*1.60*/("""

"""),format.raw/*6.1*/("""

"""),format.raw/*8.12*/("""


"""),format.raw/*34.2*/("""

"""),_display_(/*36.2*/main(title = Messages("countries.edit"), nav = "create")/*36.58*/ {_display_(Seq[Any](format.raw/*36.60*/("""

    """),_display_(/*38.6*/if(countryViewForm.hasErrors)/*38.35*/ {_display_(Seq[Any](format.raw/*38.37*/("""
        """),format.raw/*39.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*41.22*/Messages("generic.error.title")),format.raw/*41.53*/("""</strong> """),_display_(/*41.64*/Messages("generic.error.content")),format.raw/*41.97*/("""
            """),format.raw/*42.13*/("""<p>"""),_display_(/*42.17*/countryViewForm/*42.32*/.errorsAsJson),format.raw/*42.45*/("""</p>
        </div>
    """)))}),format.raw/*44.6*/("""


    """),_display_(/*47.6*/helper/*47.12*/.form(controllers.routes.CountriesView.update(id), 'enctype -> "multipart/form-data", 'id -> "form")/*47.112*/ {_display_(Seq[Any](format.raw/*47.114*/("""

        """),format.raw/*49.9*/("""<fieldset>
            <legend>"""),_display_(/*50.22*/Messages("countries.info")),format.raw/*50.48*/("""</legend>

            <div class="row">
                <div class="col-lg-4">
                    """),_display_(/*54.22*/inputText(
                        countryViewForm("name"),
                        '_label -> Messages("countries.name"),
                        '_help -> Messages("countries.name.help"),
                        'class -> "form-control",
                        '_error -> countryViewForm.globalError
                    )),format.raw/*60.22*/("""
                """),format.raw/*61.17*/("""</div>
                <div class="col-lg-3">
                    """),_display_(/*63.22*/inputText(
                        countryViewForm("shortName"),
                        '_label -> Messages("countries.shortname"),
                        '_help -> Messages("countries.shortname.help"),
                        'class -> "form-control",
                        '_error -> countryViewForm.globalError
                    )),format.raw/*69.22*/("""
                """),format.raw/*70.17*/("""</div>
                <div class="col-lg-4">
                    """),_display_(/*72.22*/select(
                        countryViewForm("language.idLanguage"),
                        models.basic.Language.toSeq,
                        '_label -> Messages("countries.language"),
                        'class -> "form-control"
                    )),format.raw/*77.22*/("""
                """),format.raw/*78.17*/("""</div>
                <div class="col-lg-1">
                    """),_display_(/*80.22*/materialCheckbox(
                        countryViewForm("active"),
                        '_label -> Messages("countries.active"),
                        'class -> "multiInstanceCheckbox"
                    )),format.raw/*84.22*/("""
                """),format.raw/*85.17*/("""</div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h2>"""),_display_(/*90.26*/Messages("countries.timezones")),format.raw/*90.57*/("""</h2>
                </div>
            </div>

            <div id="timezones">

                """),_display_(/*96.18*/repeat(countryViewForm("timezones"))/*96.54*/ { timezone =>_display_(Seq[Any](format.raw/*96.68*/("""
                    """),_display_(/*97.22*/timezoneGroup(timezone)),format.raw/*97.45*/("""
                """)))}),format.raw/*98.18*/("""
                """),_display_(/*99.18*/timezoneGroup(
                    countryViewForm("timezones[x]"),
                    className = "timezone_template"
                )),format.raw/*102.18*/("""

                """),format.raw/*104.17*/("""<div class="manage">
                    <a class="addTimezone btn btn-primary success">"""),_display_(/*105.69*/Messages("countries.timezone.add")),format.raw/*105.103*/("""</a>
                </div>

            </div>



        </fieldset>

        <div class="actions">
            <input type="submit" value="""),_display_(/*115.41*/Messages("countries.submit.update")),format.raw/*115.76*/(""" """),format.raw/*115.77*/("""class="btn btn-success">
            <a href=""""),_display_(/*116.23*/controllers/*116.34*/.routes.CountriesView.list()),format.raw/*116.62*/("""" class="btn btn-primary">"""),_display_(/*116.89*/Messages("generic.cancel")),format.raw/*116.115*/("""</a>
        </div>

    """)))}),format.raw/*119.6*/("""

    """),_display_(/*121.6*/form(controllers.routes.CountriesView.delete(id), 'class -> "topRight")/*121.77*/ {_display_(Seq[Any](format.raw/*121.79*/("""
        """),format.raw/*122.9*/("""<input type="submit" value="""),_display_(/*122.37*/Messages("countries.submit.delete")),format.raw/*122.72*/(""" """),format.raw/*122.73*/("""class="btn btn-danger">

    """)))}),format.raw/*124.6*/("""

    """),format.raw/*126.5*/("""<script type="text/javascript" charset="utf-8">



    $(document).on('click','.removeTimezone', function(e) """),format.raw/*130.59*/("""{"""),format.raw/*130.60*/("""
        """),format.raw/*131.9*/("""$(this).parents('.timezone').remove()
        renumberTimezones()
    """),format.raw/*133.5*/("""}"""),format.raw/*133.6*/(""")

    $(document).on('click','.addTimezone', function(e) """),format.raw/*135.56*/("""{"""),format.raw/*135.57*/("""
        """),format.raw/*136.9*/("""var template = $('.timezone_template')
        template.before('<div class="twipsies well timezone">' + template.html() + '</div>')
        renumberTimezones()
    """),format.raw/*139.5*/("""}"""),format.raw/*139.6*/(""")

    var renumberTimezones = function() """),format.raw/*141.40*/("""{"""),format.raw/*141.41*/("""
        """),format.raw/*142.9*/("""$('.timezone').each(function(i) """),format.raw/*142.41*/("""{"""),format.raw/*142.42*/("""
            """),format.raw/*143.13*/("""$('select', this).each(function() """),format.raw/*143.47*/("""{"""),format.raw/*143.48*/("""
                """),format.raw/*144.17*/("""$(this).attr('name', $(this).attr('name').replace(/timezones\[.+?\]/g, 'timezones[' + i + ']'))
                $(this).attr('id', $(this).attr('id').replace(/timezones\_x/g, 'timezones' + i))
            """),format.raw/*146.13*/("""}"""),format.raw/*146.14*/(""")
            $('input', this).each(function() """),format.raw/*147.46*/("""{"""),format.raw/*147.47*/("""
                """),format.raw/*148.17*/("""$(this).attr('name', $(this).attr('name').replace(/timezones\[.+?\]/g, 'timezones[' + i + ']'))
            """),format.raw/*149.13*/("""}"""),format.raw/*149.14*/(""")
        """),format.raw/*150.9*/("""}"""),format.raw/*150.10*/(""")
    """),format.raw/*151.5*/("""}"""),format.raw/*151.6*/("""

    """),format.raw/*153.5*/("""$('#form').submit(function() """),format.raw/*153.34*/("""{"""),format.raw/*153.35*/("""
        """),format.raw/*154.9*/("""$('.timezone_template').remove()
    """),format.raw/*155.5*/("""}"""),format.raw/*155.6*/(""")

    $('input.timezoneCheckbox').on('change', function() """),format.raw/*157.57*/("""{"""),format.raw/*157.58*/("""
        """),format.raw/*158.9*/("""$('input.timezoneCheckbox').not(this).prop('checked', false);
    """),format.raw/*159.5*/("""}"""),format.raw/*159.6*/(""");

    </script>

""")))}),format.raw/*163.2*/("""
"""))}
  }

  def render(id:Integer,countryViewForm:Form[models.basic.Country]): play.twirl.api.HtmlFormat.Appendable = apply(id,countryViewForm)

  def f:((Integer,Form[models.basic.Country]) => play.twirl.api.HtmlFormat.Appendable) = (id,countryViewForm) => apply(id,countryViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/countries/edit.scala.html
                  HASH: da2f91c7a461e9b85c41e3b1c0edb3f596196052
                  MATRIX: 760->1|954->133|966->138|1044->147|1066->160|1193->210|1225->215|1279->242|1309->251|1413->328|1646->540|1687->553|1773->612|1985->803|2026->816|2172->935|2231->972|2321->59|2349->130|2378->143|2408->1023|2437->1026|2502->1082|2542->1084|2575->1091|2613->1120|2653->1122|2689->1131|2868->1283|2920->1314|2958->1325|3012->1358|3053->1371|3084->1375|3108->1390|3142->1403|3197->1428|3231->1436|3246->1442|3356->1542|3397->1544|3434->1554|3493->1586|3540->1612|3668->1713|4013->2037|4058->2054|4152->2121|4512->2460|4557->2477|4651->2544|4934->2806|4979->2823|5073->2890|5307->3103|5352->3120|5501->3242|5553->3273|5680->3373|5725->3409|5777->3423|5826->3445|5870->3468|5919->3486|5964->3504|6123->3641|6170->3659|6287->3748|6344->3782|6514->3924|6571->3959|6601->3960|6676->4007|6697->4018|6747->4046|6802->4073|6851->4099|6908->4125|6942->4132|7023->4203|7064->4205|7101->4214|7157->4242|7214->4277|7244->4278|7305->4308|7339->4314|7477->4423|7507->4424|7544->4433|7642->4503|7671->4504|7758->4562|7788->4563|7825->4572|8017->4736|8046->4737|8117->4779|8147->4780|8184->4789|8245->4821|8275->4822|8317->4835|8380->4869|8410->4870|8456->4887|8690->5092|8720->5093|8796->5140|8826->5141|8872->5158|9009->5266|9039->5267|9077->5277|9107->5278|9141->5284|9170->5285|9204->5291|9262->5320|9292->5321|9329->5330|9394->5367|9423->5368|9511->5427|9541->5428|9578->5437|9672->5503|9701->5504|9752->5524
                  LINES: 26->1|30->8|30->8|32->11|32->11|34->11|35->12|35->12|35->12|38->15|43->20|44->21|46->23|50->27|51->28|53->30|53->30|58->1|60->6|62->8|65->34|67->36|67->36|67->36|69->38|69->38|69->38|70->39|72->41|72->41|72->41|72->41|73->42|73->42|73->42|73->42|75->44|78->47|78->47|78->47|78->47|80->49|81->50|81->50|85->54|91->60|92->61|94->63|100->69|101->70|103->72|108->77|109->78|111->80|115->84|116->85|121->90|121->90|127->96|127->96|127->96|128->97|128->97|129->98|130->99|133->102|135->104|136->105|136->105|146->115|146->115|146->115|147->116|147->116|147->116|147->116|147->116|150->119|152->121|152->121|152->121|153->122|153->122|153->122|153->122|155->124|157->126|161->130|161->130|162->131|164->133|164->133|166->135|166->135|167->136|170->139|170->139|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|175->144|177->146|177->146|178->147|178->147|179->148|180->149|180->149|181->150|181->150|182->151|182->151|184->153|184->153|184->153|185->154|186->155|186->155|188->157|188->157|189->158|190->159|190->159|194->163
                  -- GENERATED --
              */
          