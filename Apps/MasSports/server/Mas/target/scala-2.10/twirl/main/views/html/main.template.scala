
package views.html

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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, nav: String = "")(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import be.objectify.deadbolt.java.views.html._
import be.objectify.deadbolt.core.utils.TemplateUtils._

Seq[Any](format.raw/*1.50*/("""


"""),format.raw/*6.1*/("""

"""),format.raw/*8.1*/("""<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>"""),_display_(/*13.17*/title),format.raw/*13.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*14.54*/routes/*14.60*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*14.103*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*15.54*/routes/*15.60*/.Assets.at("stylesheets/main.css")),format.raw/*15.94*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*16.54*/routes/*16.60*/.Assets.at("stylesheets/bootstrap-datetimepicker.min.css")),format.raw/*16.118*/("""">
            <!--link rel="stylesheet" media="screen" href=""""),_display_(/*17.61*/routes/*17.67*/.Assets.at("stylesheets/carousel.css")),format.raw/*17.105*/(""""-->

        <link rel="stylesheet" media="screen" href=""""),_display_(/*19.54*/routes/*19.60*/.Assets.at("stylesheets/ripples.min.css")),format.raw/*19.101*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(/*20.54*/routes/*20.60*/.Assets.at("stylesheets/material-wfont.min.css")),format.raw/*20.108*/("""">

        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*22.59*/routes/*22.65*/.Assets.at("images/favicon.png")),format.raw/*22.97*/("""">
        <script src=""""),_display_(/*23.23*/routes/*23.29*/.Assets.at("javascripts/jquery-2.1.1.min.js")),format.raw/*23.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*24.23*/routes/*24.29*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*24.71*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*25.23*/routes/*25.29*/.Assets.at("javascripts/moment.min.js")),format.raw/*25.68*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*26.23*/routes/*26.29*/.Assets.at("javascripts/bootstrap-datetimepicker.js")),format.raw/*26.82*/("""" type="text/javascript"></script>

        <script src=""""),_display_(/*28.23*/routes/*28.29*/.Assets.at("javascripts/ripples.min.js")),format.raw/*28.69*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*29.23*/routes/*29.29*/.Assets.at("javascripts/material.min.js")),format.raw/*29.70*/("""" type="text/javascript"></script>
    </head>
    <body>

        <div class="container">

            <div class="navbar navbar-default">
                <div class="navbar-header">
                    <div class="navbar-collapse collapse navbar-responsive-collapse">
                        <ul class="nav navbar-nav">
                            <a class="navbar-brand" href=""""),_display_(/*39.60*/controllers/*39.71*/.routes.Application.index()),format.raw/*39.98*/("""">"""),_display_(/*39.101*/Messages("main.start")),format.raw/*39.123*/("""</a>
                            """),_display_(/*40.30*/subjectPresentOr()/*40.48*/ {_display_(Seq[Any](format.raw/*40.50*/("""
                                """),_display_(/*41.34*/defining(Application.getLocalUser(session()))/*41.79*/ { user =>_display_(Seq[Any](format.raw/*41.89*/("""
                                    """),_display_(/*42.38*/if(user.isAdmin())/*42.56*/{_display_(Seq[Any](format.raw/*42.57*/("""
                                        """),format.raw/*43.41*/("""<li class="dropdown">
                                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">"""),_display_(/*44.122*/Messages("main.admin")),format.raw/*44.144*/("""<b class="caret"></b></a>
                                            <ul class="dropdown-menu">
                                                <li class="dropdown-header">"""),_display_(/*46.78*/Messages("main.servers")),format.raw/*46.102*/("""</li>
                                                <li><a href=""""),_display_(/*47.63*/controllers/*47.74*/.routes.InstancesView.list()),format.raw/*47.102*/("""">"""),_display_(/*47.105*/Messages("main.instances")),format.raw/*47.131*/("""</a></li>
                                                <li><a href=""""),_display_(/*48.63*/controllers/*48.74*/.routes.JobsView.list()),format.raw/*48.97*/("""">"""),_display_(/*48.100*/Messages("main.jobs")),format.raw/*48.121*/("""</a></li>
                                                <li class="divider"></li>
                                                <li class="dropdown-header">"""),_display_(/*50.78*/Messages("main.basic")),format.raw/*50.100*/("""</li>
                                                <li><a href=""""),_display_(/*51.63*/controllers/*51.74*/.routes.ConfigsView.list()),format.raw/*51.100*/("""">"""),_display_(/*51.103*/Messages("main.configurations")),format.raw/*51.134*/("""</a></li>
                                                <li><a href=""""),_display_(/*52.63*/controllers/*52.74*/.routes.UsersView.list()),format.raw/*52.98*/("""">"""),_display_(/*52.101*/Messages("main.users")),format.raw/*52.123*/("""</a></li>
                                            </ul>
                                        </li>
                                    """)))}),format.raw/*55.38*/("""
                                """)))}),format.raw/*56.34*/("""

                                """),format.raw/*58.33*/("""<li class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">"""),_display_(/*59.114*/Messages("main.lac")),format.raw/*59.134*/("""<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-header">"""),_display_(/*61.70*/Messages("main.languages")),format.raw/*61.96*/("""</li>
                                        <li><a href=""""),_display_(/*62.55*/controllers/*62.66*/.routes.LanguagesView.list()),format.raw/*62.94*/("""">"""),_display_(/*62.97*/Messages("main.list")),format.raw/*62.118*/("""</a></li>
                                        <li><a href=""""),_display_(/*63.55*/controllers/*63.66*/.routes.LanguagesView.blank),format.raw/*63.93*/("""">"""),_display_(/*63.96*/Messages("main.create")),format.raw/*63.119*/("""</a></li>
                                        <li class="dropdown-header">"""),_display_(/*64.70*/Messages("main.countries")),format.raw/*64.96*/("""</li>
                                        <li><a href=""""),_display_(/*65.55*/controllers/*65.66*/.routes.CountriesView.list()),format.raw/*65.94*/("""">"""),_display_(/*65.97*/Messages("main.list")),format.raw/*65.118*/("""</a></li>
                                        <li><a href=""""),_display_(/*66.55*/controllers/*66.66*/.routes.CountriesView.blank),format.raw/*66.93*/("""">"""),_display_(/*66.96*/Messages("main.create")),format.raw/*66.119*/("""</a></li>
                                        <li class="dropdown-header">"""),_display_(/*67.70*/Messages("main.timezones")),format.raw/*67.96*/("""</li>
                                        <li><a href=""""),_display_(/*68.55*/controllers/*68.66*/.routes.TimezonesView.list()),format.raw/*68.94*/("""">"""),_display_(/*68.97*/Messages("main.list")),format.raw/*68.118*/("""</a></li>
                                        <li><a href=""""),_display_(/*69.55*/controllers/*69.66*/.routes.TimezonesView.blank),format.raw/*69.93*/("""">"""),_display_(/*69.96*/Messages("main.create")),format.raw/*69.119*/("""</a></li>
                                        <li class="dropdown-header">"""),_display_(/*70.70*/Messages("main.devices")),format.raw/*70.94*/("""</li>
                                        <li><a href=""""),_display_(/*71.55*/controllers/*71.66*/.routes.DevicesView.list()),format.raw/*71.92*/("""">"""),_display_(/*71.95*/Messages("main.list")),format.raw/*71.116*/("""</a></li>
                                        <li><a href=""""),_display_(/*72.55*/controllers/*72.66*/.routes.DevicesView.blank),format.raw/*72.91*/("""">"""),_display_(/*72.94*/Messages("main.create")),format.raw/*72.117*/("""</a></li>
                                    </ul>
                                </li>
                                
                            """)))}/*76.30*/{_display_(Seq[Any](format.raw/*76.31*/("""<p/>""")))}),format.raw/*76.36*/("""
                        """),format.raw/*77.25*/("""</ul>
                    </div>
                </div>

                <div class="navbar-collapse collapse navbar-responsive-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">

                            """),_display_(/*85.30*/subjectPresentOr()/*85.48*/ {_display_(Seq[Any](format.raw/*85.50*/("""
                                """),_display_(/*86.34*/defining(Application.getLocalUser(session()))/*86.79*/ { user =>_display_(Seq[Any](format.raw/*86.89*/("""
                                    """),format.raw/*87.37*/("""<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)"><span class="glyphicon glyphicon-user"></span>&nbsp;"""),_display_(/*87.166*/user/*87.170*/.name),format.raw/*87.175*/(""" """),format.raw/*87.176*/("""<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href=""""),_display_(/*89.55*/routes/*89.61*/.Application.profile()),format.raw/*89.83*/("""">"""),_display_(/*89.86*/Messages("playauthenticate.navigation.profile")),format.raw/*89.133*/("""</a></li>
                                        <li class="divider"></li>
                                        <li><a href=""""),_display_(/*91.55*/com/*91.58*/.feth.play.module.pa.controllers.routes.Authenticate.logout()),format.raw/*91.119*/(""""><span class="glyphicon glyphicon-off"></span>&nbsp;"""),_display_(/*91.173*/Messages("playauthenticate.navigation.logout")),format.raw/*91.219*/("""</a></li>
                                    </ul>
                                """)))}),format.raw/*93.34*/("""
                            """)))}/*94.30*/{_display_(Seq[Any](format.raw/*94.31*/("""
                                """),format.raw/*95.33*/("""<a href=""""),_display_(/*95.43*/routes/*95.49*/.Application.login()),format.raw/*95.69*/("""">"""),_display_(/*95.72*/Messages("playauthenticate.navigation.login")),format.raw/*95.117*/("""</a>
                            """)))}),format.raw/*96.30*/("""

                        """),format.raw/*98.25*/("""</li>
                    </ul>
                </div>
            </div>

            """),_display_(/*103.14*/if(flash.containsKey("forbidden"))/*103.48*/ {_display_(Seq[Any](format.raw/*103.50*/("""
                """),format.raw/*104.17*/("""<div class="alert-message warning">
                    <strong>"""),_display_(/*105.30*/Messages("generic.error.title")),format.raw/*105.61*/("""</strong> """),_display_(/*105.72*/flash/*105.77*/.get("forbidden")),format.raw/*105.94*/("""
                """),format.raw/*106.17*/("""</div>
            """)))}),format.raw/*107.14*/("""

            """),_display_(/*109.14*/content),format.raw/*109.21*/("""

            """),format.raw/*111.13*/("""<footer>
                <p>&copy; Hecticus Software Inc. 2014</p>
            </footer>

        </div>


    </body>
</html>"""))}
  }

  def render(title:String,nav:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title,nav)(content)

  def f:((String,String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title,nav) => (content) => apply(title,nav)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/main.scala.html
                  HASH: c62eb17519a4095cada2ac0823ed1fb597c8ad23
                  MATRIX: 734->1|972->49|1001->157|1029->159|1185->288|1211->293|1300->355|1315->361|1380->404|1463->460|1478->466|1533->500|1616->556|1631->562|1711->620|1801->683|1816->689|1876->727|1962->786|1977->792|2040->833|2123->889|2138->895|2208->943|2297->1005|2312->1011|2365->1043|2417->1068|2432->1074|2498->1119|2582->1176|2597->1182|2660->1224|2744->1281|2759->1287|2819->1326|2903->1383|2918->1389|2992->1442|3077->1500|3092->1506|3153->1546|3237->1603|3252->1609|3314->1650|3722->2031|3742->2042|3790->2069|3821->2072|3865->2094|3926->2128|3953->2146|3993->2148|4054->2182|4108->2227|4156->2237|4221->2275|4248->2293|4287->2294|4356->2335|4527->2478|4571->2500|4772->2674|4818->2698|4913->2766|4933->2777|4983->2805|5014->2808|5062->2834|5161->2906|5181->2917|5225->2940|5256->2943|5299->2964|5487->3125|5531->3147|5626->3215|5646->3226|5694->3252|5725->3255|5778->3286|5877->3358|5897->3369|5942->3393|5973->3396|6017->3418|6191->3561|6256->3595|6318->3629|6481->3764|6523->3784|6708->3942|6755->3968|6842->4028|6862->4039|6911->4067|6941->4070|6984->4091|7075->4155|7095->4166|7143->4193|7173->4196|7218->4219|7324->4298|7371->4324|7458->4384|7478->4395|7527->4423|7557->4426|7600->4447|7691->4511|7711->4522|7759->4549|7789->4552|7834->4575|7940->4654|7987->4680|8074->4740|8094->4751|8143->4779|8173->4782|8216->4803|8307->4867|8327->4878|8375->4905|8405->4908|8450->4931|8556->5010|8601->5034|8688->5094|8708->5105|8755->5131|8785->5134|8828->5155|8919->5219|8939->5230|8985->5255|9015->5258|9060->5281|9231->5433|9270->5434|9306->5439|9359->5464|9662->5740|9689->5758|9729->5760|9790->5794|9844->5839|9892->5849|9957->5886|10114->6015|10128->6019|10155->6024|10185->6025|10355->6168|10370->6174|10413->6196|10443->6199|10512->6246|10669->6376|10681->6379|10764->6440|10846->6494|10914->6540|11030->6625|11079->6655|11118->6656|11179->6689|11216->6699|11231->6705|11272->6725|11302->6728|11369->6773|11434->6807|11488->6833|11604->6921|11648->6955|11689->6957|11735->6974|11828->7039|11881->7070|11920->7081|11935->7086|11974->7103|12020->7120|12072->7140|12115->7155|12144->7162|12187->7176
                  LINES: 26->1|30->1|33->6|35->8|40->13|40->13|41->14|41->14|41->14|42->15|42->15|42->15|43->16|43->16|43->16|44->17|44->17|44->17|46->19|46->19|46->19|47->20|47->20|47->20|49->22|49->22|49->22|50->23|50->23|50->23|51->24|51->24|51->24|52->25|52->25|52->25|53->26|53->26|53->26|55->28|55->28|55->28|56->29|56->29|56->29|66->39|66->39|66->39|66->39|66->39|67->40|67->40|67->40|68->41|68->41|68->41|69->42|69->42|69->42|70->43|71->44|71->44|73->46|73->46|74->47|74->47|74->47|74->47|74->47|75->48|75->48|75->48|75->48|75->48|77->50|77->50|78->51|78->51|78->51|78->51|78->51|79->52|79->52|79->52|79->52|79->52|82->55|83->56|85->58|86->59|86->59|88->61|88->61|89->62|89->62|89->62|89->62|89->62|90->63|90->63|90->63|90->63|90->63|91->64|91->64|92->65|92->65|92->65|92->65|92->65|93->66|93->66|93->66|93->66|93->66|94->67|94->67|95->68|95->68|95->68|95->68|95->68|96->69|96->69|96->69|96->69|96->69|97->70|97->70|98->71|98->71|98->71|98->71|98->71|99->72|99->72|99->72|99->72|99->72|103->76|103->76|103->76|104->77|112->85|112->85|112->85|113->86|113->86|113->86|114->87|114->87|114->87|114->87|114->87|116->89|116->89|116->89|116->89|116->89|118->91|118->91|118->91|118->91|118->91|120->93|121->94|121->94|122->95|122->95|122->95|122->95|122->95|122->95|123->96|125->98|130->103|130->103|130->103|131->104|132->105|132->105|132->105|132->105|132->105|133->106|134->107|136->109|136->109|138->111
                  -- GENERATED --
              */
          