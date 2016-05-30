
package views.html.configs

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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Long,Form[models.Config],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, configViewForm: Form[models.Config]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
def /*6.2*/title/*6.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};
Seq[Any](format.raw/*1.49*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*6.12*/("""

"""),_display_(/*8.2*/main(title = Messages("configs.edit"), nav = "create")/*8.56*/ {_display_(Seq[Any](format.raw/*8.58*/("""

    """),_display_(/*10.6*/if(configViewForm.hasErrors)/*10.34*/ {_display_(Seq[Any](format.raw/*10.36*/("""
        """),format.raw/*11.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*13.22*/Messages("generic.error.title")),format.raw/*13.53*/("""</strong> """),_display_(/*13.64*/Messages("generic.error.content")),format.raw/*13.97*/("""
            """),format.raw/*14.13*/("""<p>"""),_display_(/*14.17*/configViewForm/*14.31*/.errorsAsJson),format.raw/*14.44*/("""</p>
        </div>
    """)))}),format.raw/*16.6*/("""


    """),_display_(/*19.6*/helper/*19.12*/.form(controllers.routes.ConfigsView.update(id), 'enctype -> "multipart/form-data")/*19.95*/ {_display_(Seq[Any](format.raw/*19.97*/("""

        """),format.raw/*21.9*/("""<fieldset>
            <legend>"""),_display_(/*22.22*/Messages("configs.info")),format.raw/*22.46*/("""</legend>

            """),_display_(/*24.14*/inputText(
                configViewForm("configKey"),
                '_label -> Messages("configs.key"),
                '_help -> Messages("configs.key.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*30.14*/("""

            """),_display_(/*32.14*/inputText(
                configViewForm("value"),
                '_label -> Messages("configs.value"),
                '_help -> Messages("configs.value.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*38.14*/("""

            """),_display_(/*40.14*/inputText(
                configViewForm("description"),
                '_label -> Messages("configs.description"),
                '_help -> Messages("configs.description.help"),
                'class -> "form-control",
                '_error -> configViewForm.globalError
            )),format.raw/*46.14*/("""

        """),format.raw/*48.9*/("""</fieldset>

        <div class="actions">
            <input type="submit" value="""),_display_(/*51.41*/Messages("configs.submit.update")),format.raw/*51.74*/(""" """),format.raw/*51.75*/("""class="btn btn-success">
            <a href=""""),_display_(/*52.23*/controllers/*52.34*/.routes.ConfigsView.list()),format.raw/*52.60*/("""" class="btn btn-primary">"""),_display_(/*52.87*/Messages("generic.cancel")),format.raw/*52.113*/("""</a>
        </div>

    """)))}),format.raw/*55.6*/("""

    """),_display_(/*57.6*/form(controllers.routes.ConfigsView.delete(id), 'class -> "topRight")/*57.75*/ {_display_(Seq[Any](format.raw/*57.77*/("""
        """),format.raw/*58.9*/("""<input type="submit" value="""),_display_(/*58.37*/Messages("configs.submit.delete")),format.raw/*58.70*/(""" """),format.raw/*58.71*/("""class="btn btn-danger">

    """)))}),format.raw/*60.6*/("""

""")))}),format.raw/*62.2*/("""
"""))}
  }

  def render(id:Long,configViewForm:Form[models.Config]): play.twirl.api.HtmlFormat.Appendable = apply(id,configViewForm)

  def f:((Long,Form[models.Config]) => play.twirl.api.HtmlFormat.Appendable) = (id,configViewForm) => apply(id,configViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/configs/edit.scala.html
                  HASH: 90d861602437204109142d985c01bdc55dfbd227
                  MATRIX: 748->1|914->103|926->108|1020->48|1048->101|1076->113|1104->116|1166->170|1205->172|1238->179|1275->207|1315->209|1351->218|1530->370|1582->401|1620->412|1674->445|1715->458|1746->462|1769->476|1803->489|1858->514|1892->522|1907->528|1999->611|2039->613|2076->623|2135->655|2180->679|2231->703|2525->976|2567->991|2861->1264|2903->1279|3215->1570|3252->1580|3362->1663|3416->1696|3445->1697|3519->1744|3539->1755|3586->1781|3640->1808|3688->1834|3744->1860|3777->1867|3855->1936|3895->1938|3931->1947|3986->1975|4040->2008|4069->2009|4129->2039|4162->2042
                  LINES: 26->1|29->6|29->6|32->1|34->5|35->6|37->8|37->8|37->8|39->10|39->10|39->10|40->11|42->13|42->13|42->13|42->13|43->14|43->14|43->14|43->14|45->16|48->19|48->19|48->19|48->19|50->21|51->22|51->22|53->24|59->30|61->32|67->38|69->40|75->46|77->48|80->51|80->51|80->51|81->52|81->52|81->52|81->52|81->52|84->55|86->57|86->57|86->57|87->58|87->58|87->58|87->58|89->60|91->62
                  -- GENERATED --
              */
          