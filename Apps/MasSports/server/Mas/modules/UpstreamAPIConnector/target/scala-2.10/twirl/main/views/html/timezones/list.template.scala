
package views.html.timezones

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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.basic.Timezone],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.basic.Timezone], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
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
    controllers.routes.TimezonesView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.147*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("timezones.list.head"), nav = "")/*40.57*/ {_display_(Seq[Any](format.raw/*40.59*/("""
    """),format.raw/*41.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h1 id="homeTitle">"""),_display_(/*45.37*/Messages("timezones.list.title", currentPage.getTotalRowCount)),format.raw/*45.99*/("""</h1>
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
                        <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.119*/Messages("timezones.list.filter.name")),format.raw/*65.157*/(""">
                    </div>
                    <div class="col-lg-6">
                        <input type="submit" id="searchsubmit" value="""),_display_(/*68.71*/Messages("post.list.filter.name")),format.raw/*68.104*/(""" """),format.raw/*68.105*/("""class="btn btn-info">
                    </div>
                """)))}),format.raw/*70.18*/("""

                """),_display_(/*72.18*/if(sortView)/*72.30*/ {_display_(Seq[Any](format.raw/*72.32*/("""
                    """),format.raw/*73.21*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/timezones/sort/" >"""),_display_(/*73.104*/Messages("generic.list.apply")),format.raw/*73.134*/("""</a>
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
                            """),_display_(/*90.30*/for((timezone, index) <-  currentPage.getList().zipWithIndex) yield /*90.91*/ {_display_(Seq[Any](format.raw/*90.93*/("""
                            """),format.raw/*91.29*/("""<div class="list-group-item">
                                <a href=""""),_display_(/*92.43*/controllers/*92.54*/.routes.TimezonesView.edit(timezone.getIdTimezone())),format.raw/*92.106*/("""">
                                    <div class="row-content">
                                        <h4 class="list-group-item-heading">"""),_display_(/*94.78*/Html(""+timezone.getIdTimezone())),format.raw/*94.111*/(""" """),format.raw/*94.112*/("""- """),_display_(/*94.115*/Html(timezone.getName())),format.raw/*94.139*/("""</h4>
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
                <a href=""""),_display_(/*146.27*/controllers/*146.38*/.routes.TimezonesView.blank),format.raw/*146.65*/("""" class="btn btn-primary">"""),_display_(/*146.92*/Messages("timezones.list.new")),format.raw/*146.122*/("""</a>
            </div>
        </div>

    </div>

""")))}),format.raw/*152.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.basic.Timezone],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.basic.Timezone],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/UpstreamAPIConnector/app/views/timezones/list.scala.html
                  HASH: da1863fce8b6aa4e9070128136dd97c0b5b448d6
                  MATRIX: 798->1|1015->890|1029->896|1136->926|1168->931|1207->943|1219->946|1257->963|1286->964|1321->972|1355->998|1442->1062|1490->1083|1523->1095|1553->1098|1579->1103|1618->1122|1631->1127|1708->275|1719->279|2249->146|2278->273|2306->778|2336->888|2364->1119|2394->1132|2423->1135|2487->1190|2527->1192|2559->1197|2715->1326|2798->1388|2897->1460|2938->1492|2978->1494|3019->1507|3107->1568|3157->1597|3195->1608|3209->1613|3245->1628|3286->1641|3333->1657|3369->1666|3488->1758|3538->1787|3668->1890|3704->1905|3765->1939|3787->1952|3827->1954|3876->1975|4045->2116|4105->2154|4274->2296|4329->2329|4359->2330|4456->2396|4502->2415|4523->2427|4563->2429|4612->2450|4723->2533|4775->2563|4828->2585|4870->2599|4930->2632|4976->2669|5016->2671|5058->2685|5125->2725|5176->2755|5230->2791|5243->2796|5282->2797|5323->2810|5533->2993|5610->3054|5650->3056|5707->3085|5806->3157|5826->3168|5900->3220|6069->3362|6124->3395|6154->3396|6185->3399|6231->3423|6560->3720|6614->3745|6731->3830|6765->3836|6955->3998|6988->4021|7029->4023|7083->4048|7171->4108|7233->4148|7266->4153|7322->4186|7398->4243|7412->4248|7452->4249|7506->4274|7625->4365|7680->4398|7768->4454|7819->4476|7896->4525|7950->4557|7980->4559|8001->4570|8122->4668|8203->4721|8236->4744|8277->4746|8331->4771|8415->4827|8477->4867|8508->4870|8560->4899|8591->4900|8668->4958|8682->4963|8722->4964|8776->4989|8889->5074|8940->5103|8970->5104|9059->5161|9107->5180|9263->5308|9284->5319|9333->5346|9388->5373|9441->5403|9525->5456
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|118->90|118->90|118->90|119->91|120->92|120->92|120->92|122->94|122->94|122->94|122->94|122->94|129->101|130->102|134->106|136->108|144->116|144->116|144->116|145->117|146->118|146->118|146->118|146->118|148->120|148->120|148->120|149->121|150->122|150->122|152->124|154->126|155->127|155->127|155->127|155->127|155->127|158->130|158->130|158->130|159->131|160->132|160->132|160->132|160->132|160->132|162->134|162->134|162->134|163->135|164->136|164->136|164->136|166->138|169->141|174->146|174->146|174->146|174->146|174->146|180->152
                  -- GENERATED --
              */
          