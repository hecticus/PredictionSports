
package views.html.devices

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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.clients.Device],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.clients.Device], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
def /*32.2*/header/*32.8*/(key:String, title:String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*32.38*/("""
    """),format.raw/*33.5*/("""<th class='"""),_display_(/*33.17*/key/*33.20*/.replace(".","_")),format.raw/*33.37*/(""" """),format.raw/*33.38*/("""header """),_display_(/*33.46*/if(currentSortBy == key){/*33.72*/{if(currentOrder == "asc") "headerSortDown" else "headerSortUp"}}),format.raw/*33.136*/("""'>
        <a href=""""),_display_(/*34.19*/link(0, key)),format.raw/*34.31*/("""">"""),_display_(/*34.34*/title),format.raw/*34.39*/("""</a>
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
    controllers.routes.DevicesView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.147*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("devices.list.head"), nav = "")/*40.55*/ {_display_(Seq[Any](format.raw/*40.57*/("""
    """),format.raw/*41.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h1 id="homeTitle">"""),_display_(/*45.37*/Messages("devices.list.title", currentPage.getTotalRowCount)),format.raw/*45.97*/("""</h1>
            </div>
        </div>

        <!--DIALOG-->
        """),_display_(/*50.10*/if(flash.containsKey("success"))/*50.42*/ {_display_(Seq[Any](format.raw/*50.44*/("""
            """),format.raw/*51.13*/("""<div class="alert-message warning">
                <strong>"""),_display_(/*52.26*/Messages("generic.list.done")),format.raw/*52.55*/("""</strong> """),_display_(/*52.66*/flash/*52.71*/.get("success")),format.raw/*52.86*/("""
            """),format.raw/*53.13*/("""</div>
        """)))}),format.raw/*54.10*/("""
        """),format.raw/*55.9*/("""<div id="alert" class="alert-message warning" style="display: none; ">
            <strong>"""),_display_(/*56.22*/Messages("generic.list.done")),format.raw/*56.51*/("""</strong>
        </div>

        <!--ACTIONS-->
        <div id="actions">
            <form action='"""),_display_(/*61.28*/link(0, "name")),format.raw/*61.43*/("""' method="GET">

                """),_display_(/*63.18*/if(!sortView)/*63.31*/ {_display_(Seq[Any](format.raw/*63.33*/("""
                    """),format.raw/*64.21*/("""<div class="col-lg-6">
                        <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.119*/Messages("devices.list.filter.name")),format.raw/*65.155*/(""">
                    </div>
                    <div class="col-lg-6">
                        <input type="submit" id="searchsubmit" value="""),_display_(/*68.71*/Messages("post.list.filter.name")),format.raw/*68.104*/(""" """),format.raw/*68.105*/("""class="btn btn-info">
                    </div>
                """)))}),format.raw/*70.18*/("""

                """),_display_(/*72.18*/if(sortView)/*72.30*/ {_display_(Seq[Any](format.raw/*72.32*/("""
                    """),format.raw/*73.21*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/devices/sort/" >"""),_display_(/*73.102*/Messages("generic.list.apply")),format.raw/*73.132*/("""</a>
                """)))}),format.raw/*74.18*/("""

            """),format.raw/*76.13*/("""</form>
        </div>

        """),_display_(/*79.10*/if(currentPage.getTotalRowCount == 0)/*79.47*/ {_display_(Seq[Any](format.raw/*79.49*/("""

            """),format.raw/*81.13*/("""<div class="well">
                <em>"""),_display_(/*82.22*/Messages("generic.list.empty")),format.raw/*82.52*/("""</em>
            </div>

        """)))}/*85.11*/else/*85.16*/{_display_(Seq[Any](format.raw/*85.17*/("""
            """),format.raw/*86.13*/("""<div class="row">
                <div class="col-lg-12">
                    <div class="bs-component">
                        <div class="list-group">
                            """),_display_(/*90.30*/for((device, index) <-  currentPage.getList().zipWithIndex) yield /*90.89*/ {_display_(Seq[Any](format.raw/*90.91*/("""
                            """),format.raw/*91.29*/("""<div class="list-group-item">
                                <a href=""""),_display_(/*92.43*/controllers/*92.54*/.routes.DevicesView.edit(device.getIdDevice())),format.raw/*92.100*/("""">
                                    <div class="row-content">
                                        <h4 class="list-group-item-heading">"""),_display_(/*94.78*/Html(""+device.getIdDevice())),format.raw/*94.107*/(""" """),format.raw/*94.108*/("""- """),_display_(/*94.111*/Html(device.getName())),format.raw/*94.133*/("""</h4>
                                    </div>
                                </a>
                            </div>
                            <div class="list-group-separator">
                                <!--separator-->
                            </div>
                            """)))}),format.raw/*101.30*/("""
                        """),format.raw/*102.25*/("""</div>
                    </div>
                </div>
            </div>
        """)))}),format.raw/*106.10*/("""

    """),format.raw/*108.5*/("""</div>

    <div class="bs-docs-section">
        <div class="row">
            <div class="col-lg-12">

                <ul class="pager">

                    """),_display_(/*116.22*/if(currentPage.hasPrev)/*116.45*/ {_display_(Seq[Any](format.raw/*116.47*/("""
                        """),format.raw/*117.25*/("""<li class="previous">
                            <a href=""""),_display_(/*118.39*/link(currentPage.getPageIndex - 1, null)),format.raw/*118.79*/("""">← """),_display_(/*118.84*/Messages("generic.list.previous")),format.raw/*118.117*/("""</a>
                        </li>
                    """)))}/*120.23*/else/*120.28*/{_display_(Seq[Any](format.raw/*120.29*/("""
                        """),format.raw/*121.25*/("""<li class="previous disabled">
                            <a href="javascript:void(0)">← """),_display_(/*122.61*/Messages("generic.list.previous")),format.raw/*122.94*/("""</a>
                        </li>
                    """)))}),format.raw/*124.22*/("""

                    """),format.raw/*126.21*/("""<li class="current">
                        <a>"""),_display_(/*127.29*/Messages("generic.list.listing")),format.raw/*127.61*/(""" """),_display_(/*127.63*/currentPage/*127.74*/.getDisplayXtoYofZ(" "+Messages("generic.list.through")+" ",  " "+Messages("generic.list.of")+" ")),format.raw/*127.172*/("""</a>
                    </li>

                    """),_display_(/*130.22*/if(currentPage.hasNext)/*130.45*/ {_display_(Seq[Any](format.raw/*130.47*/("""
                        """),format.raw/*131.25*/("""<li class="next">
                            <a href=""""),_display_(/*132.39*/link(currentPage.getPageIndex + 1, null)),format.raw/*132.79*/("""">"""),_display_(/*132.82*/Messages("generic.list.next")),format.raw/*132.111*/(""" """),format.raw/*132.112*/("""→</a>
                        </li>
                    """)))}/*134.23*/else/*134.28*/{_display_(Seq[Any](format.raw/*134.29*/("""
                        """),format.raw/*135.25*/("""<li class="next disabled">
                            <a href="javascript:void(0)">"""),_display_(/*136.59*/Messages("generic.list.next")),format.raw/*136.88*/(""" """),format.raw/*136.89*/("""→</a>
                        </li>
                    """)))}),format.raw/*138.22*/("""


                """),format.raw/*141.17*/("""</ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <a href=""""),_display_(/*146.27*/controllers/*146.38*/.routes.DevicesView.blank),format.raw/*146.63*/("""" class="btn btn-primary">"""),_display_(/*146.90*/Messages("devices.list.new")),format.raw/*146.118*/("""</a>
            </div>
        </div>

    </div>

""")))}),format.raw/*152.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.clients.Device],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.clients.Device],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/devices/list.scala.html
                  HASH: 3e20eb7f3a5ed6796473c0f0fa5fd5c22d4d3dc4
                  MATRIX: 796->1|1013->888|1027->894|1134->924|1166->929|1205->941|1217->944|1255->961|1284->962|1319->970|1353->996|1440->1060|1488->1081|1521->1093|1551->1096|1577->1101|1616->1120|1629->1125|1706->275|1717->279|2245->146|2274->273|2302->776|2332->886|2360->1117|2390->1130|2419->1133|2481->1186|2521->1188|2553->1193|2709->1322|2790->1382|2889->1454|2930->1486|2970->1488|3011->1501|3099->1562|3149->1591|3187->1602|3201->1607|3237->1622|3278->1635|3325->1651|3361->1660|3480->1752|3530->1781|3660->1884|3696->1899|3757->1933|3779->1946|3819->1948|3868->1969|4037->2110|4095->2146|4264->2288|4319->2321|4349->2322|4446->2388|4492->2407|4513->2419|4553->2421|4602->2442|4711->2523|4763->2553|4816->2575|4858->2589|4918->2622|4964->2659|5004->2661|5046->2675|5113->2715|5164->2745|5218->2781|5231->2786|5270->2787|5311->2800|5521->2983|5596->3042|5636->3044|5693->3073|5792->3145|5812->3156|5880->3202|6049->3344|6100->3373|6130->3374|6161->3377|6205->3399|6534->3696|6588->3721|6705->3806|6739->3812|6929->3974|6962->3997|7003->3999|7057->4024|7145->4084|7207->4124|7240->4129|7296->4162|7372->4219|7386->4224|7426->4225|7480->4250|7599->4341|7654->4374|7742->4430|7793->4452|7870->4501|7924->4533|7954->4535|7975->4546|8096->4644|8177->4697|8210->4720|8251->4722|8305->4747|8389->4803|8451->4843|8482->4846|8534->4875|8565->4876|8642->4934|8656->4939|8696->4940|8750->4965|8863->5050|8914->5079|8944->5080|9033->5137|9081->5156|9237->5284|9258->5295|9305->5320|9360->5347|9411->5375|9495->5428
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|118->90|118->90|118->90|119->91|120->92|120->92|120->92|122->94|122->94|122->94|122->94|122->94|129->101|130->102|134->106|136->108|144->116|144->116|144->116|145->117|146->118|146->118|146->118|146->118|148->120|148->120|148->120|149->121|150->122|150->122|152->124|154->126|155->127|155->127|155->127|155->127|155->127|158->130|158->130|158->130|159->131|160->132|160->132|160->132|160->132|160->132|162->134|162->134|162->134|163->135|164->136|164->136|164->136|166->138|169->141|174->146|174->146|174->146|174->146|174->146|180->152
                  -- GENERATED --
              */
          