
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.Config],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Config], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*32.2*/header/*32.8*/(key:String, title:String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*32.38*/("""
"""),format.raw/*33.1*/("""<th class='"""),_display_(/*33.13*/key/*33.16*/.replace(".","_")),format.raw/*33.33*/(""" """),format.raw/*33.34*/("""header """),_display_(/*33.42*/if(currentSortBy == key){/*33.68*/{if(currentOrder == "asc") "headerSortDown" else "headerSortUp"}}),format.raw/*33.132*/("""'>
    <a href=""""),_display_(/*34.15*/link(0, key)),format.raw/*34.27*/("""">"""),_display_(/*34.30*/title),format.raw/*34.35*/("""</a>
</th>
""")))};def /*38.2*/title/*38.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};def /*6.2*/link/*6.6*/(newPage:Int, newSortBy:String) = {{

    var sortBy = currentSortBy
    var order = currentOrder

    if(newSortBy != null) {
        sortBy = newSortBy
        if(currentSortBy == newSortBy) {
            if(currentOrder == "asc") {
                order = "desc"
            } else {
                order = "asc"
            }
        } else {
            order = "asc"
        }
    }

    // Generate the link
    controllers.routes.ConfigsView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.139*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("configs.list.head"), nav = "")/*40.55*/ {_display_(Seq[Any](format.raw/*40.57*/("""
"""),format.raw/*41.1*/("""<div class="bs-docs-section">

    <div class="row">
        <div class="col-lg-12">
            <h1 id="homeTitle">"""),_display_(/*45.33*/Messages("configs.list.title", currentPage.getTotalRowCount)),format.raw/*45.93*/("""</h1>
        </div>
    </div>

    <!--DIALOG-->
    """),_display_(/*50.6*/if(flash.containsKey("success"))/*50.38*/ {_display_(Seq[Any](format.raw/*50.40*/("""
        """),format.raw/*51.9*/("""<div class="alert-message warning">
            <strong>"""),_display_(/*52.22*/Messages("generic.list.done")),format.raw/*52.51*/("""</strong> """),_display_(/*52.62*/flash/*52.67*/.get("success")),format.raw/*52.82*/("""
        """),format.raw/*53.9*/("""</div>
    """)))}),format.raw/*54.6*/("""
    """),format.raw/*55.5*/("""<div id="alert" class="alert-message warning" style="display: none; ">
        <strong>"""),_display_(/*56.18*/Messages("generic.list.done")),format.raw/*56.47*/("""</strong>
    </div>

    <!--ACTIONS-->
    <div id="actions">
        <form action='"""),_display_(/*61.24*/link(0, "name")),format.raw/*61.39*/("""' method="GET">

            """),_display_(/*63.14*/if(!sortView)/*63.27*/ {_display_(Seq[Any](format.raw/*63.29*/("""
                """),format.raw/*64.17*/("""<div class="col-lg-6">
                    <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.115*/Messages("configs.list.filter.name")),format.raw/*65.151*/(""">
                </div>
                <div class="col-lg-6">
                    <input type="submit" id="searchsubmit" value="""),_display_(/*68.67*/Messages("configs.list.filter.name")),format.raw/*68.103*/(""" """),format.raw/*68.104*/("""class="btn btn-info">
                </div>
            """)))}),format.raw/*70.14*/("""

            """),_display_(/*72.14*/if(sortView)/*72.26*/ {_display_(Seq[Any](format.raw/*72.28*/("""
                """),format.raw/*73.17*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/configurations/sort/" >"""),_display_(/*73.105*/Messages("generic.list.apply")),format.raw/*73.135*/("""</a>
            """)))}),format.raw/*74.14*/("""

        """),format.raw/*76.9*/("""</form>
    </div>

    """),_display_(/*79.6*/if(currentPage.getTotalRowCount == 0)/*79.43*/ {_display_(Seq[Any](format.raw/*79.45*/("""

    """),format.raw/*81.5*/("""<div class="well">
        <em>"""),_display_(/*82.14*/Messages("generic.list.empty")),format.raw/*82.44*/("""</em>
    </div>

    """)))}/*85.7*/else/*85.12*/{_display_(Seq[Any](format.raw/*85.13*/("""
    """),format.raw/*86.5*/("""<div class="row">
        <div class="col-lg-12">
            <div class="bs-component">
                <div class="list-group">
                    <table class="table table-striped table-hover ">
                        <thead>
                            <tr>
                                <th>"""),_display_(/*93.38*/Messages("configs.key")),format.raw/*93.61*/("""</th>
                                <th>"""),_display_(/*94.38*/Messages("configs.value")),format.raw/*94.63*/("""</th>
                            </tr>
                        </thead>
                        <tbody>
                        """),_display_(/*98.26*/for((config, index) <-  currentPage.getList().zipWithIndex) yield /*98.85*/ {_display_(Seq[Any](format.raw/*98.87*/("""
                            """),format.raw/*99.29*/("""<tr>
                                <td>
                                    <a href=""""),_display_(/*101.47*/controllers/*101.58*/.routes.ConfigsView.edit(config.getIdConfig())),format.raw/*101.104*/("""">
                                        """),_display_(/*102.42*/Html(config.getConfigKey())),format.raw/*102.69*/("""
                                    """),format.raw/*103.37*/("""</a>
                                </td>
                                <td>"""),_display_(/*105.38*/Html(config.getValue())),format.raw/*105.61*/("""</td>
                            </tr>
                        """)))}),format.raw/*107.26*/("""
                        """),format.raw/*108.25*/("""</tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    """)))}),format.raw/*114.6*/("""

"""),format.raw/*116.1*/("""</div>

<div class="bs-docs-section">
    <div class="row">
        <div class="col-lg-12">

            <ul class="pager">

                """),_display_(/*124.18*/if(currentPage.hasPrev)/*124.41*/ {_display_(Seq[Any](format.raw/*124.43*/("""
                    """),format.raw/*125.21*/("""<li class="previous">
                        <a href=""""),_display_(/*126.35*/link(currentPage.getPageIndex - 1, null)),format.raw/*126.75*/("""">← """),_display_(/*126.80*/Messages("generic.list.previous")),format.raw/*126.113*/("""</a>
                    </li>
                """)))}/*128.19*/else/*128.24*/{_display_(Seq[Any](format.raw/*128.25*/("""
                    """),format.raw/*129.21*/("""<li class="previous disabled">
                        <a href="javascript:void(0)">← """),_display_(/*130.57*/Messages("generic.list.previous")),format.raw/*130.90*/("""</a>
                    </li>
                """)))}),format.raw/*132.18*/("""

                """),format.raw/*134.17*/("""<li class="current">
                    <a>"""),_display_(/*135.25*/Messages("generic.list.listing")),format.raw/*135.57*/(""" """),_display_(/*135.59*/currentPage/*135.70*/.getDisplayXtoYofZ(" "+Messages("generic.list.through")+" ",  " "+Messages("generic.list.of")+" ")),format.raw/*135.168*/("""</a>
                </li>

                """),_display_(/*138.18*/if(currentPage.hasNext)/*138.41*/ {_display_(Seq[Any](format.raw/*138.43*/("""
                    """),format.raw/*139.21*/("""<li class="next">
                        <a href=""""),_display_(/*140.35*/link(currentPage.getPageIndex + 1, null)),format.raw/*140.75*/("""">"""),_display_(/*140.78*/Messages("generic.list.next")),format.raw/*140.107*/(""" """),format.raw/*140.108*/("""→</a>
                    </li>
                """)))}/*142.19*/else/*142.24*/{_display_(Seq[Any](format.raw/*142.25*/("""
                    """),format.raw/*143.21*/("""<li class="next disabled">
                        <a href="javascript:void(0)">"""),_display_(/*144.55*/Messages("generic.list.next")),format.raw/*144.84*/(""" """),format.raw/*144.85*/("""→</a>
                    </li>
                """)))}),format.raw/*146.18*/("""


            """),format.raw/*149.13*/("""</ul>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <a href=""""),_display_(/*154.23*/controllers/*154.34*/.routes.ConfigsView.blank),format.raw/*154.59*/("""" class="btn btn-primary">"""),_display_(/*154.86*/Messages("configs.list.new")),format.raw/*154.114*/("""</a>
        </div>
    </div>

</div>

""")))}),format.raw/*160.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.Config],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.Config],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/configs/list.scala.html
                  HASH: 9c2e8d34500b18cb678cc1bb490478deb35ae785
                  MATRIX: 788->1|997->880|1011->886|1118->916|1146->917|1185->929|1197->932|1235->949|1264->950|1299->958|1333->984|1420->1048|1464->1065|1497->1077|1527->1080|1553->1085|1588->1100|1601->1105|1678->267|1689->271|2217->138|2246->265|2274->768|2304->878|2332->1097|2362->1110|2391->1113|2453->1166|2493->1168|2521->1169|2665->1286|2746->1346|2828->1402|2869->1434|2909->1436|2945->1445|3029->1502|3079->1531|3117->1542|3131->1547|3167->1562|3203->1571|3245->1583|3277->1588|3392->1676|3442->1705|3556->1792|3592->1807|3649->1837|3671->1850|3711->1852|3756->1869|3921->2006|3979->2042|4136->2172|4194->2208|4224->2209|4313->2267|4355->2282|4376->2294|4416->2296|4461->2313|4577->2401|4629->2431|4678->2449|4715->2459|4766->2484|4812->2521|4852->2523|4885->2529|4944->2561|4995->2591|5036->2615|5049->2620|5088->2621|5120->2626|5448->2927|5492->2950|5562->2993|5608->3018|5765->3148|5840->3207|5880->3209|5937->3238|6053->3326|6074->3337|6143->3383|6215->3427|6264->3454|6330->3491|6438->3571|6483->3594|6580->3659|6634->3684|6776->3795|6806->3797|6976->3939|7009->3962|7050->3964|7100->3985|7184->4041|7246->4081|7279->4086|7335->4119|7403->4168|7417->4173|7457->4174|7507->4195|7622->4282|7677->4315|7757->4363|7804->4381|7877->4426|7931->4458|7961->4460|7982->4471|8103->4569|8176->4614|8209->4637|8250->4639|8300->4660|8380->4712|8442->4752|8473->4755|8525->4784|8556->4785|8625->4835|8639->4840|8679->4841|8729->4862|8838->4943|8889->4972|8919->4973|9000->5022|9044->5037|9180->5145|9201->5156|9248->5181|9303->5208|9354->5236|9426->5277
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|121->93|121->93|122->94|122->94|126->98|126->98|126->98|127->99|129->101|129->101|129->101|130->102|130->102|131->103|133->105|133->105|135->107|136->108|142->114|144->116|152->124|152->124|152->124|153->125|154->126|154->126|154->126|154->126|156->128|156->128|156->128|157->129|158->130|158->130|160->132|162->134|163->135|163->135|163->135|163->135|163->135|166->138|166->138|166->138|167->139|168->140|168->140|168->140|168->140|168->140|170->142|170->142|170->142|171->143|172->144|172->144|172->144|174->146|177->149|182->154|182->154|182->154|182->154|182->154|188->160
                  -- GENERATED --
              */
          