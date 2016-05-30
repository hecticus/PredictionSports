
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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.Job],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Job], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
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
    controllers.routes.JobsView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.136*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("jobs.list.head"), nav = "")/*40.52*/ {_display_(Seq[Any](format.raw/*40.54*/("""
    """),format.raw/*41.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h1 id="homeTitle">"""),_display_(/*45.37*/Messages("jobs.list.title", currentPage.getTotalRowCount)),format.raw/*45.94*/("""</h1>
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
                """),format.raw/*64.17*/("""<div class="col-lg-6">
                    <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.115*/Messages("jobs.list.filter.name")),format.raw/*65.148*/(""">
                </div>
                <div class="col-lg-6">
                    <input type="submit" id="searchsubmit" value="""),_display_(/*68.67*/Messages("jobs.list.filter.name")),format.raw/*68.100*/(""" """),format.raw/*68.101*/("""class="btn btn-info">
                </div>
                """)))}),format.raw/*70.18*/("""

                """),_display_(/*72.18*/if(sortView)/*72.30*/ {_display_(Seq[Any](format.raw/*72.32*/("""
                """),format.raw/*73.17*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/jobs/sort/" >"""),_display_(/*73.95*/Messages("generic.list.apply")),format.raw/*73.125*/("""</a>
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
                            """),_display_(/*90.30*/for((job, index) <-  currentPage.getList().zipWithIndex) yield /*90.86*/ {_display_(Seq[Any](format.raw/*90.88*/("""
                            """),format.raw/*91.29*/("""<div class="list-group-item">
                                <a href=""""),_display_(/*92.43*/controllers/*92.54*/.routes.JobsView.edit(job.getId())),format.raw/*92.88*/("""">
                                    <div class="row-content">
                                        <h4 class="list-group-item-heading">"""),_display_(/*94.78*/Html(""+job.getId())),format.raw/*94.98*/(""" """),format.raw/*94.99*/("""- """),_display_(/*94.102*/Html(job.getName())),format.raw/*94.121*/("""</h4>
                                        <p class="list-group-item-text">"""),_display_(/*95.74*/Html(job.getClassName())),format.raw/*95.98*/("""</p>
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
                <a href=""""),_display_(/*147.27*/controllers/*147.38*/.routes.JobsView.blank),format.raw/*147.60*/("""" class="btn btn-primary">"""),_display_(/*147.87*/Messages("jobs.list.new")),format.raw/*147.112*/("""</a>
            </div>
        </div>

    </div>

""")))}),format.raw/*153.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.Job],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.Job],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/modules/JobCore/app/views/jobs/list.scala.html
                  HASH: 78e1f0507c98ebe30de1331e2ce8721ef49bf227
                  MATRIX: 782->1|988->874|1002->880|1109->910|1137->911|1176->923|1188->926|1226->943|1255->944|1290->952|1324->978|1411->1042|1455->1059|1488->1071|1518->1074|1544->1079|1579->1094|1592->1099|1669->264|1680->268|2205->135|2234->262|2262->762|2292->872|2320->1091|2350->1104|2379->1107|2438->1157|2478->1159|2510->1164|2666->1293|2744->1350|2843->1422|2884->1454|2924->1456|2965->1469|3053->1530|3103->1559|3141->1570|3155->1575|3191->1590|3232->1603|3279->1619|3315->1628|3434->1720|3484->1749|3614->1852|3650->1867|3711->1901|3733->1914|3773->1916|3818->1933|3983->2070|4038->2103|4195->2233|4250->2266|4280->2267|4373->2329|4419->2348|4440->2360|4480->2362|4525->2379|4630->2457|4682->2487|4735->2509|4777->2523|4837->2556|4883->2593|4923->2595|4965->2609|5032->2649|5083->2679|5137->2715|5150->2720|5189->2721|5230->2734|5440->2917|5512->2973|5552->2975|5609->3004|5708->3076|5728->3087|5783->3121|5952->3263|5993->3283|6022->3284|6053->3287|6094->3306|6200->3385|6245->3409|6573->3705|6627->3730|6744->3815|6778->3821|6968->3983|7001->4006|7042->4008|7096->4033|7184->4093|7246->4133|7279->4138|7335->4171|7411->4228|7425->4233|7465->4234|7519->4259|7638->4350|7693->4383|7781->4439|7832->4461|7909->4510|7963->4542|7993->4544|8014->4555|8135->4653|8216->4706|8249->4729|8290->4731|8344->4756|8428->4812|8490->4852|8521->4855|8573->4884|8604->4885|8681->4943|8695->4948|8735->4949|8789->4974|8902->5059|8953->5088|8983->5089|9072->5146|9120->5165|9276->5293|9297->5304|9341->5326|9396->5353|9444->5378|9528->5431
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|118->90|118->90|118->90|119->91|120->92|120->92|120->92|122->94|122->94|122->94|122->94|122->94|123->95|123->95|130->102|131->103|135->107|137->109|145->117|145->117|145->117|146->118|147->119|147->119|147->119|147->119|149->121|149->121|149->121|150->122|151->123|151->123|153->125|155->127|156->128|156->128|156->128|156->128|156->128|159->131|159->131|159->131|160->132|161->133|161->133|161->133|161->133|161->133|163->135|163->135|163->135|164->136|165->137|165->137|165->137|167->139|170->142|175->147|175->147|175->147|175->147|175->147|181->153
                  -- GENERATED --
              */
          