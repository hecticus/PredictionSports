
package views.html.users

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
object list extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template5[com.avaje.ebean.Page[models.User],String,String,String,Boolean,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.User], currentSortBy: String, currentOrder: String, currentFilter: String, sortView: Boolean):play.twirl.api.HtmlFormat.Appendable = {
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
    controllers.routes.UsersView.list(newPage, sortBy, order, currentFilter)

}};
Seq[Any](format.raw/*1.137*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*27.2*/("""

"""),format.raw/*31.37*/("""
"""),format.raw/*36.2*/("""

"""),format.raw/*38.12*/("""

"""),_display_(/*40.2*/main(title = Messages("users.list.head"), nav = "")/*40.53*/ {_display_(Seq[Any](format.raw/*40.55*/("""
    """),format.raw/*41.5*/("""<div class="bs-docs-section">

        <div class="row">
            <div class="col-lg-12">
                <h1 id="homeTitle">"""),_display_(/*45.37*/Messages("users.list.title", currentPage.getTotalRowCount)),format.raw/*45.95*/("""</h1>
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
                        <input type="search" class="form-control floating-label" id="searchbox" name="f" placeholder="""),_display_(/*65.119*/Messages("users.list.filter.name")),format.raw/*65.153*/(""">
                    </div>
                    <div class="col-lg-6">
                        <input type="submit" id="searchsubmit" value="""),_display_(/*68.71*/Messages("users.list.filter.name")),format.raw/*68.105*/(""" """),format.raw/*68.106*/("""class="btn btn-info">
                    </div>
                """)))}),format.raw/*70.18*/("""

                """),_display_(/*72.18*/if(sortView)/*72.30*/ {_display_(Seq[Any](format.raw/*72.32*/("""
                    """),format.raw/*73.21*/("""<a class="btn success" id="ajax" href="#" data-url="/garotas/v1/users/sort/" >"""),_display_(/*73.100*/Messages("generic.list.apply")),format.raw/*73.130*/("""</a>
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
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>"""),_display_(/*93.42*/Messages("users.name")),format.raw/*93.64*/("""</th>
                                    <th>"""),_display_(/*94.42*/Messages("users.email")),format.raw/*94.65*/("""</th>
                                </tr>
                                </thead>
                                <tbody>
                                """),_display_(/*98.34*/for((user, index) <-  currentPage.getList().zipWithIndex) yield /*98.91*/ {_display_(Seq[Any](format.raw/*98.93*/("""
                                    """),format.raw/*99.37*/("""<tr>
                                        <td>
                                            <a href=""""),_display_(/*101.55*/controllers/*101.66*/.routes.UsersView.edit(user.id)),format.raw/*101.97*/("""">
                                                """),_display_(/*102.50*/Html(user.name)),format.raw/*102.65*/("""
                                            """),format.raw/*103.45*/("""</a>
                                        </td>
                                        <td>"""),_display_(/*105.46*/Html(user.email)),format.raw/*105.62*/("""</td>
                                    </tr>
                                """)))}),format.raw/*107.34*/("""
                                """),format.raw/*108.33*/("""</tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        """)))}),format.raw/*114.10*/("""

    """),format.raw/*116.5*/("""</div>

    <div class="bs-docs-section">
        <div class="row">
            <div class="col-lg-12">

                <ul class="pager">

                    """),_display_(/*124.22*/if(currentPage.hasPrev)/*124.45*/ {_display_(Seq[Any](format.raw/*124.47*/("""
                        """),format.raw/*125.25*/("""<li class="previous">
                            <a href=""""),_display_(/*126.39*/link(currentPage.getPageIndex - 1, null)),format.raw/*126.79*/("""">← """),_display_(/*126.84*/Messages("generic.list.previous")),format.raw/*126.117*/("""</a>
                        </li>
                    """)))}/*128.23*/else/*128.28*/{_display_(Seq[Any](format.raw/*128.29*/("""
                        """),format.raw/*129.25*/("""<li class="previous disabled">
                            <a href="javascript:void(0)">← """),_display_(/*130.61*/Messages("generic.list.previous")),format.raw/*130.94*/("""</a>
                        </li>
                    """)))}),format.raw/*132.22*/("""

                    """),format.raw/*134.21*/("""<li class="current">
                        <a>"""),_display_(/*135.29*/Messages("generic.list.listing")),format.raw/*135.61*/(""" """),_display_(/*135.63*/currentPage/*135.74*/.getDisplayXtoYofZ(" "+Messages("generic.list.through")+" ",  " "+Messages("generic.list.of")+" ")),format.raw/*135.172*/("""</a>
                    </li>

                    """),_display_(/*138.22*/if(currentPage.hasNext)/*138.45*/ {_display_(Seq[Any](format.raw/*138.47*/("""
                        """),format.raw/*139.25*/("""<li class="next">
                            <a href=""""),_display_(/*140.39*/link(currentPage.getPageIndex + 1, null)),format.raw/*140.79*/("""">"""),_display_(/*140.82*/Messages("generic.list.next")),format.raw/*140.111*/(""" """),format.raw/*140.112*/("""→</a>
                        </li>
                    """)))}/*142.23*/else/*142.28*/{_display_(Seq[Any](format.raw/*142.29*/("""
                        """),format.raw/*143.25*/("""<li class="next disabled">
                            <a href="javascript:void(0)">"""),_display_(/*144.59*/Messages("generic.list.next")),format.raw/*144.88*/(""" """),format.raw/*144.89*/("""→</a>
                        </li>
                    """)))}),format.raw/*146.22*/("""


                """),format.raw/*149.17*/("""</ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <a href=""""),_display_(/*154.27*/controllers/*154.38*/.routes.Application.signup),format.raw/*154.64*/("""" class="btn btn-primary">"""),_display_(/*154.91*/Messages("users.list.new")),format.raw/*154.117*/("""</a>
            </div>
        </div>

    </div>

""")))}),format.raw/*160.2*/("""
"""))}
  }

  def render(currentPage:com.avaje.ebean.Page[models.User],currentSortBy:String,currentOrder:String,currentFilter:String,sortView:Boolean): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def f:((com.avaje.ebean.Page[models.User],String,String,String,Boolean) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentSortBy,currentOrder,currentFilter,sortView) => apply(currentPage,currentSortBy,currentOrder,currentFilter,sortView)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/users/list.scala.html
                  HASH: d0d053091727f731fea9a06d5c6e9c61da25eca4
                  MATRIX: 784->1|991->876|1005->882|1112->912|1144->917|1183->929|1195->932|1233->949|1262->950|1297->958|1331->984|1418->1048|1466->1069|1499->1081|1529->1084|1555->1089|1594->1108|1607->1113|1684->265|1695->269|2221->136|2250->263|2278->764|2308->874|2336->1105|2366->1118|2395->1121|2455->1172|2495->1174|2527->1179|2683->1308|2762->1366|2861->1438|2902->1470|2942->1472|2983->1485|3071->1546|3121->1575|3159->1586|3173->1591|3209->1606|3250->1619|3297->1635|3333->1644|3452->1736|3502->1765|3632->1868|3668->1883|3729->1917|3751->1930|3791->1932|3840->1953|4009->2094|4065->2128|4234->2270|4290->2304|4320->2305|4417->2371|4463->2390|4484->2402|4524->2404|4573->2425|4680->2504|4732->2534|4785->2556|4827->2570|4887->2603|4933->2640|4973->2642|5015->2656|5082->2696|5133->2726|5187->2762|5200->2767|5239->2768|5280->2781|5656->3130|5699->3152|5773->3199|5817->3222|6002->3380|6075->3437|6115->3439|6180->3476|6312->3580|6333->3591|6386->3622|6466->3674|6503->3689|6577->3734|6701->3830|6739->3846|6852->3927|6914->3960|7101->4115|7135->4121|7325->4283|7358->4306|7399->4308|7453->4333|7541->4393|7603->4433|7636->4438|7692->4471|7768->4528|7782->4533|7822->4534|7876->4559|7995->4650|8050->4683|8138->4739|8189->4761|8266->4810|8320->4842|8350->4844|8371->4855|8492->4953|8573->5006|8606->5029|8647->5031|8701->5056|8785->5112|8847->5152|8878->5155|8930->5184|8961->5185|9038->5243|9052->5248|9092->5249|9146->5274|9259->5359|9310->5388|9340->5389|9429->5446|9477->5465|9633->5593|9654->5604|9702->5630|9757->5657|9806->5683|9890->5736
                  LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->38|34->38|36->6|36->6|58->1|60->5|61->27|63->31|64->36|66->38|68->40|68->40|68->40|69->41|73->45|73->45|78->50|78->50|78->50|79->51|80->52|80->52|80->52|80->52|80->52|81->53|82->54|83->55|84->56|84->56|89->61|89->61|91->63|91->63|91->63|92->64|93->65|93->65|96->68|96->68|96->68|98->70|100->72|100->72|100->72|101->73|101->73|101->73|102->74|104->76|107->79|107->79|107->79|109->81|110->82|110->82|113->85|113->85|113->85|114->86|121->93|121->93|122->94|122->94|126->98|126->98|126->98|127->99|129->101|129->101|129->101|130->102|130->102|131->103|133->105|133->105|135->107|136->108|142->114|144->116|152->124|152->124|152->124|153->125|154->126|154->126|154->126|154->126|156->128|156->128|156->128|157->129|158->130|158->130|160->132|162->134|163->135|163->135|163->135|163->135|163->135|166->138|166->138|166->138|167->139|168->140|168->140|168->140|168->140|168->140|170->142|170->142|170->142|171->143|172->144|172->144|172->144|174->146|177->149|182->154|182->154|182->154|182->154|182->154|188->160
                  -- GENERATED --
              */
          