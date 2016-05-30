
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
object form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[models.basic.Country],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(countryViewForm: Form[models.basic.Country]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};def /*9.2*/timezoneGroup/*9.15*/(field: Field, className: String = "timezone"):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*9.65*/("""
    """),format.raw/*10.5*/("""<div class="twipsies well """),_display_(/*10.32*/className),format.raw/*10.41*/("""">
        <div class="row">
            <div class="col-lg-7">
            """),_display_(/*13.14*/select(
                field("timezone.idTimezone"),
                models.basic.Timezone.toSeq,
                '_label -> Messages("countries.timezone"),
                'class -> "form-control"
            )),format.raw/*18.14*/("""
            """),format.raw/*19.13*/("""</div>
            <div class="col-lg-1">
            """),_display_(/*21.14*/materialCheckbox(
                field("active"),
                '_label -> Messages("countries.timezone.active"),
                'class -> "timezoneCheckbox"
            )),format.raw/*25.14*/("""
            """),format.raw/*26.13*/("""</div>
            <div class="col-lg-4">
                <a class="removeTimezone btn btn-primary danger pull-right">"""),_display_(/*28.78*/Messages("countries.timezone.remove")),format.raw/*28.115*/("""</a>
            </div>
        </div>
    </div>
""")))};
Seq[Any](format.raw/*1.47*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),format.raw/*32.2*/("""

"""),_display_(/*34.2*/main(title = Messages("countries.create"), nav = "create")/*34.60*/ {_display_(Seq[Any](format.raw/*34.62*/("""

    """),_display_(/*36.6*/if(flash.containsKey("success"))/*36.38*/ {_display_(Seq[Any](format.raw/*36.40*/("""
    """),format.raw/*37.5*/("""<div class="alert-message warning">
        <strong>Error!</strong> """),_display_(/*38.34*/flash/*38.39*/.get("success")),format.raw/*38.54*/("""
    """),format.raw/*39.5*/("""</div>
    """)))}),format.raw/*40.6*/("""

    """),_display_(/*42.6*/if(countryViewForm.hasErrors)/*42.35*/ {_display_(Seq[Any](format.raw/*42.37*/("""
        """),format.raw/*43.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*45.22*/Messages("generic.error.title")),format.raw/*45.53*/("""</strong> """),_display_(/*45.64*/Messages("generic.error.content")),format.raw/*45.97*/("""
            """),format.raw/*46.13*/("""<p>"""),_display_(/*46.17*/countryViewForm/*46.32*/.errorsAsJson),format.raw/*46.45*/("""</p>
        </div>
    """)))}),format.raw/*48.6*/("""


    """),_display_(/*51.6*/helper/*51.12*/.form(action = controllers.routes.CountriesView.submit, 'enctype -> "multipart/form-data", 'id -> "form")/*51.117*/ {_display_(Seq[Any](format.raw/*51.119*/("""

        """),format.raw/*53.9*/("""<fieldset>
            <legend>"""),_display_(/*54.22*/Messages("countries.info")),format.raw/*54.48*/("""</legend>

            <div class="row">
                <div class="col-lg-4">
                    """),_display_(/*58.22*/inputText(
                        countryViewForm("name"),
                        '_label -> Messages("countries.name"),
                        '_help -> Messages("countries.name.help"),
                        'class -> "form-control",
                        '_error -> countryViewForm.globalError
                    )),format.raw/*64.22*/("""
                """),format.raw/*65.17*/("""</div>
                <div class="col-lg-3">
                    """),_display_(/*67.22*/inputText(
                        countryViewForm("shortName"),
                        '_label -> Messages("countries.shortname"),
                        '_help -> Messages("countries.shortname.help"),
                        'class -> "form-control",
                        '_error -> countryViewForm.globalError
                    )),format.raw/*73.22*/("""
                """),format.raw/*74.17*/("""</div>
                <div class="col-lg-4">
                    """),_display_(/*76.22*/select(
                        countryViewForm("language.idLanguage"),
                        models.basic.Language.toSeq,
                        '_label -> Messages("countries.language"),
                        'class -> "form-control"
                    )),format.raw/*81.22*/("""
                """),format.raw/*82.17*/("""</div>
                <div class="col-lg-1">
                    """),_display_(/*84.22*/materialCheckbox(
                        countryViewForm("active"),
                        '_label -> Messages("countries.active"),
                        'class -> "multiInstanceCheckbox"
                    )),format.raw/*88.22*/("""
                """),format.raw/*89.17*/("""</div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h2>"""),_display_(/*94.26*/Messages("countries.timezones")),format.raw/*94.57*/("""</h2>
                </div>
            </div>

            <div id="timezones">

                """),_display_(/*100.18*/repeat(countryViewForm("timezones"))/*100.54*/ { timezone =>_display_(Seq[Any](format.raw/*100.68*/("""
                    """),_display_(/*101.22*/timezoneGroup(timezone)),format.raw/*101.45*/("""
                """)))}),format.raw/*102.18*/("""
                """),_display_(/*103.18*/timezoneGroup(
                    countryViewForm("timezones[x]"),
                    className = "timezone_template"
                )),format.raw/*106.18*/("""

                """),format.raw/*108.17*/("""<div class="manage">
                    <a class="addTimezone btn btn-primary success">"""),_display_(/*109.69*/Messages("countries.timezone.add")),format.raw/*109.103*/("""</a>
                </div>

            </div>

        </fieldset>

        <div class="actions">
            <input type="submit" class="btn btn-success" value="""),_display_(/*117.65*/Messages("countries.submit.create")),format.raw/*117.100*/(""">
            <a href=""""),_display_(/*118.23*/controllers/*118.34*/.routes.CountriesView.list()),format.raw/*118.62*/("""" class="btn btn-primary">"""),_display_(/*118.89*/Messages("generic.cancel")),format.raw/*118.115*/("""</a>
        </div>

    """)))}),format.raw/*121.6*/("""

    """),format.raw/*123.5*/("""<script type="text/javascript" charset="utf-8">



        $(document).on('click','.removeTimezone', function(e) """),format.raw/*127.63*/("""{"""),format.raw/*127.64*/("""
            """),format.raw/*128.13*/("""$(this).parents('.timezone').remove()
            renumberTimezones()
        """),format.raw/*130.9*/("""}"""),format.raw/*130.10*/(""")

        $(document).on('click','.addTimezone', function(e) """),format.raw/*132.60*/("""{"""),format.raw/*132.61*/("""
            """),format.raw/*133.13*/("""var template = $('.timezone_template')
            template.before('<div class="twipsies well timezone">' + template.html() + '</div>')
            renumberTimezones()
        """),format.raw/*136.9*/("""}"""),format.raw/*136.10*/(""")

        var renumberTimezones = function() """),format.raw/*138.44*/("""{"""),format.raw/*138.45*/("""
            """),format.raw/*139.13*/("""$('.timezone').each(function(i) """),format.raw/*139.45*/("""{"""),format.raw/*139.46*/("""
                """),format.raw/*140.17*/("""$('select', this).each(function() """),format.raw/*140.51*/("""{"""),format.raw/*140.52*/("""
                    """),format.raw/*141.21*/("""$(this).attr('name', $(this).attr('name').replace(/timezones\[.+?\]/g, 'timezones[' + i + ']'))
                    $(this).attr('id', $(this).attr('id').replace(/timezones\_x/g, 'timezones' + i))
                """),format.raw/*143.17*/("""}"""),format.raw/*143.18*/(""")
                $('input', this).each(function() """),format.raw/*144.50*/("""{"""),format.raw/*144.51*/("""
                    """),format.raw/*145.21*/("""$(this).attr('name', $(this).attr('name').replace(/timezones\[.+?\]/g, 'timezones[' + i + ']'))
                """),format.raw/*146.17*/("""}"""),format.raw/*146.18*/(""")
            """),format.raw/*147.13*/("""}"""),format.raw/*147.14*/(""")
        """),format.raw/*148.9*/("""}"""),format.raw/*148.10*/("""

        """),format.raw/*150.9*/("""$('#form').submit(function() """),format.raw/*150.38*/("""{"""),format.raw/*150.39*/("""
            """),format.raw/*151.13*/("""$('.timezone_template').remove()
        """),format.raw/*152.9*/("""}"""),format.raw/*152.10*/(""")

        $('input.timezoneCheckbox').on('change', function() """),format.raw/*154.61*/("""{"""),format.raw/*154.62*/("""
            """),format.raw/*155.13*/("""$('input.timezoneCheckbox').not(this).prop('checked', false);
        """),format.raw/*156.9*/("""}"""),format.raw/*156.10*/(""");

    </script>

""")))}))}
  }

  def render(countryViewForm:Form[models.basic.Country]): play.twirl.api.HtmlFormat.Appendable = apply(countryViewForm)

  def f:((Form[models.basic.Country]) => play.twirl.api.HtmlFormat.Appendable) = (countryViewForm) => apply(countryViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/countries/form.scala.html
                  HASH: ec69258dd24b19b2c2652029309a3024210abdc4
                  MATRIX: 752->1|933->119|945->124|1022->132|1043->145|1169->195|1201->200|1255->227|1285->236|1389->313|1622->525|1663->538|1745->593|1941->768|1982->781|2128->900|2187->937|2277->46|2305->117|2333->129|2362->988|2391->991|2458->1049|2498->1051|2531->1058|2572->1090|2612->1092|2644->1097|2740->1166|2754->1171|2790->1186|2822->1191|2864->1203|2897->1210|2935->1239|2975->1241|3011->1250|3190->1402|3242->1433|3280->1444|3334->1477|3375->1490|3406->1494|3430->1509|3464->1522|3519->1547|3553->1555|3568->1561|3683->1666|3724->1668|3761->1678|3820->1710|3867->1736|3995->1837|4340->2161|4385->2178|4479->2245|4839->2584|4884->2601|4978->2668|5261->2930|5306->2947|5400->3014|5634->3227|5679->3244|5828->3366|5880->3397|6008->3497|6054->3533|6107->3547|6157->3569|6202->3592|6252->3610|6298->3628|6457->3765|6504->3783|6621->3872|6678->3906|6870->4070|6928->4105|6980->4129|7001->4140|7051->4168|7106->4195|7155->4221|7212->4247|7246->4253|7388->4366|7418->4367|7460->4380|7566->4458|7596->4459|7687->4521|7717->4522|7759->4535|7963->4711|7993->4712|8068->4758|8098->4759|8140->4772|8201->4804|8231->4805|8277->4822|8340->4856|8370->4857|8420->4878|8662->5091|8692->5092|8772->5143|8802->5144|8852->5165|8993->5277|9023->5278|9066->5292|9096->5293|9134->5303|9164->5304|9202->5314|9260->5343|9290->5344|9332->5357|9401->5398|9431->5399|9523->5462|9553->5463|9595->5476|9693->5546|9723->5547
                  LINES: 26->1|30->7|30->7|32->9|32->9|34->9|35->10|35->10|35->10|38->13|43->18|44->19|46->21|50->25|51->26|53->28|53->28|58->1|60->6|61->7|63->32|65->34|65->34|65->34|67->36|67->36|67->36|68->37|69->38|69->38|69->38|70->39|71->40|73->42|73->42|73->42|74->43|76->45|76->45|76->45|76->45|77->46|77->46|77->46|77->46|79->48|82->51|82->51|82->51|82->51|84->53|85->54|85->54|89->58|95->64|96->65|98->67|104->73|105->74|107->76|112->81|113->82|115->84|119->88|120->89|125->94|125->94|131->100|131->100|131->100|132->101|132->101|133->102|134->103|137->106|139->108|140->109|140->109|148->117|148->117|149->118|149->118|149->118|149->118|149->118|152->121|154->123|158->127|158->127|159->128|161->130|161->130|163->132|163->132|164->133|167->136|167->136|169->138|169->138|170->139|170->139|170->139|171->140|171->140|171->140|172->141|174->143|174->143|175->144|175->144|176->145|177->146|177->146|178->147|178->147|179->148|179->148|181->150|181->150|181->150|182->151|183->152|183->152|185->154|185->154|186->155|187->156|187->156
                  -- GENERATED --
              */
          