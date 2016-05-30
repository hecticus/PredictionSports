
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.Instance],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Instance], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
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
    controllers.routes.InstancesView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.141*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("instances.list.head"), nav = "")/*40.57*/ {_display_(Seq[Any](format.raw/*40.59*/("""
    """),format.raw/*41.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h1 id="homeTitle">"""),_display_(/*45.37*/Messages("instances.list.title", currentPage.getTotalRowCount)),format.raw/*45.99*/("""</h1>
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
                        <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.119*/Messages("instances.list.filter.name")),format.raw/*65.157*/(""">
                    </div>
                    <div class="col-lg-6">
                        <input type="submit" id="searchsubmit" value="""),_display_(/*68.71*/Messages("instances.list.filter.name")),format.raw/*68.109*/(""" """),format.raw/*68.110*/("""class="btn btn-info">
                    </div>
                """)))}),format.raw/*70.18*/("""

                """),_display_(/*72.18*/if(sortView)/*72.30*/ {_display_(Seq[Any](format.raw/*72.32*/("""
                    """),format.raw/*73.21*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/instances/sort/" >"""),_display_(/*73.104*/Messages("generic.list.apply")),format.raw/*73.134*/("""</a>
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
                            """),_display_(/*90.30*/for((instance, index) <-  currentPage.getList().zipWithIndex) yield /*90.91*/ {_display_(Seq[Any](format.raw/*90.93*/("""
                            """),format.raw/*91.29*/("""<div class="list-group-item">
                                <a href=""""),_display_(/*92.43*/controllers/*92.54*/.routes.InstancesView.edit(instance.getIdInstance())),format.raw/*92.106*/("""">
                                    <div class="row-content">
                                        <h4 class="list-group-item-heading">"""),_display_(/*94.78*/Html(""+instance.getIdInstance())),format.raw/*94.111*/(""" """),format.raw/*94.112*/("""- """),_display_(/*94.115*/Html(instance.getName())),format.raw/*94.139*/("""</h4>
                                        <p class="list-group-item-text">"""),_display_(/*95.74*/Html(instance.getIp())),format.raw/*95.96*/("""</p>
                                    </div>
                                </a>
                            </div>
                            <div class="list-group-separator">
                                <!--separator-->
                            </div>
                            """)))}),format.raw/*102.30*/("""
                        """),format.raw/*103.25*/("""</div>
                    </div>
                </div>
            </div>
        """)))}),format.raw/*107.10*/("""

    """),format.raw/*109.5*/("""</div>

    <div class="bs-docs-section">
        <div class="row">
            <div class="col-lg-12">

                <ul class="pager">

                    """),_display_(/*117.22*/if(currentPage.hasPrev)/*117.45*/ {_display_(Seq[Any](format.raw/*117.47*/("""
                        """),format.raw/*118.25*/("""<li class="previous">
                            <a href=""""),_display_(/*119.39*/link(currentPage.getPageIndex - 1, null)),format.raw/*119.79*/("""">← """),_display_(/*119.84*/Messages("generic.list.previous")),format.raw/*119.117*/("""</a>
                        </li>
                    """)))}/*121.23*/else/*121.28*/{_display_(Seq[Any](format.raw/*121.29*/("""
                        """),format.raw/*122.25*/("""<li class="previous disabled">
                            <a href="javascript:void(0)">← """),_display_(/*123.61*/Messages("generic.list.previous")),format.raw/*123.94*/("""</a>
                        </li>
                    """)))}),format.raw/*125.22*/("""

                    """),format.raw/*127.21*/("""<li class="current">
                        <a>"""),_display_(/*128.29*/Messages("generic.list.listing")),format.raw/*128.61*/(""" """),_display_(/*128.63*/currentPage/*128.74*/.getDisplayXtoYofZ(" "+Messages("generic.list.through")+" ",  " "+Messages("generic.list.of")+" ")),format.raw/*128.172*/("""</a>
                    </li>

                    """),_display_(/*131.22*/if(currentPage.hasNext)/*131.45*/ {_display_(Seq[Any](format.raw/*131.47*/("""
                        """),format.raw/*132.25*/("""<li class="next">
                            <a href=""""),_display_(/*133.39*/link(currentPage.getPageIndex + 1, null)),format.raw/*133.79*/("""">"""),_display_(/*133.82*/Messages("generic.list.next")),format.raw/*133.111*/(""" """),format.raw/*133.112*/("""→</a>
                        </li>
                    """)))}/*135.23*/else/*135.28*/{_display_(Seq[Any](format.raw/*135.29*/("""
                        """),format.raw/*136.25*/("""<li class="next disabled">
                            <a href="javascript:void(0)">"""),_display_(/*137.59*/Messages("generic.list.next")),format.raw/*137.88*/(""" """),format.raw/*137.89*/("""→</a>
                        </li>
                    """)))}),format.raw/*139.22*/("""


                """),format.raw/*142.17*/("""</ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <a href=""""),_display_(/*147.27*/controllers/*147.38*/.routes.InstancesView.blank),format.raw/*147.65*/("""" class="btn btn-primary">"""),_display_(/*147.92*/Messages("instances.list.new")),format.raw/*147.122*/("""</a>
            </div>
        </div>

    </div>

""")))}),format.raw/*153.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.Instance],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.Instance],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/instances/list.scala.html
                  HASH: 09a15d6a281874595d8f6e4b18dab8e8dd3287d8
                  MATRIX: 792->1|1003->884|1017->890|1124->920|1156->925|1195->937|1207->940|1245->957|1274->958|1309->966|1343->992|1430->1056|1478->1077|1511->1089|1541->1092|1567->1097|1606->1116|1619->1121|1696->269|1707->273|2237->140|2266->267|2294->772|2324->882|2352->1113|2382->1126|2411->1129|2475->1184|2515->1186|2547->1191|2703->1320|2786->1382|2885->1454|2926->1486|2966->1488|3007->1501|3095->1562|3145->1591|3183->1602|3197->1607|3233->1622|3274->1635|3321->1651|3357->1660|3476->1752|3526->1781|3656->1884|3692->1899|3753->1933|3775->1946|3815->1948|3864->1969|4033->2110|4093->2148|4262->2290|4322->2328|4352->2329|4449->2395|4495->2414|4516->2426|4556->2428|4605->2449|4716->2532|4768->2562|4821->2584|4863->2598|4923->2631|4969->2668|5009->2670|5051->2684|5118->2724|5169->2754|5223->2790|5236->2795|5275->2796|5316->2809|5526->2992|5603->3053|5643->3055|5700->3084|5799->3156|5819->3167|5893->3219|6062->3361|6117->3394|6147->3395|6178->3398|6224->3422|6330->3501|6373->3523|6701->3819|6755->3844|6872->3929|6906->3935|7096->4097|7129->4120|7170->4122|7224->4147|7312->4207|7374->4247|7407->4252|7463->4285|7539->4342|7553->4347|7593->4348|7647->4373|7766->4464|7821->4497|7909->4553|7960->4575|8037->4624|8091->4656|8121->4658|8142->4669|8263->4767|8344->4820|8377->4843|8418->4845|8472->4870|8556->4926|8618->4966|8649->4969|8701->4998|8732->4999|8809->5057|8823->5062|8863->5063|8917->5088|9030->5173|9081->5202|9111->5203|9200->5260|9248->5279|9404->5407|9425->5418|9474->5445|9529->5472|9582->5502|9666->5555
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|118->90|118->90|118->90|119->91|120->92|120->92|120->92|122->94|122->94|122->94|122->94|122->94|123->95|123->95|130->102|131->103|135->107|137->109|145->117|145->117|145->117|146->118|147->119|147->119|147->119|147->119|149->121|149->121|149->121|150->122|151->123|151->123|153->125|155->127|156->128|156->128|156->128|156->128|156->128|159->131|159->131|159->131|160->132|161->133|161->133|161->133|161->133|161->133|163->135|163->135|163->135|164->136|165->137|165->137|165->137|167->139|170->142|175->147|175->147|175->147|175->147|175->147|181->153
                  -- GENERATED --
              */
          