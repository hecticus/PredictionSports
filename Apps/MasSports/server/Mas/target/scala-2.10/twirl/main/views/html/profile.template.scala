
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
object profile extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[models.User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(localUser: models.User = null):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import com.feth.play.module.pa.views.html._

Seq[Any](format.raw/*1.33*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/main(Messages("playauthenticate.profile.title"))/*5.50*/ {_display_(Seq[Any](format.raw/*5.52*/("""

    """),format.raw/*7.5*/("""<h1>"""),_display_(/*7.10*/Messages("playauthenticate.profile.title")),format.raw/*7.52*/("""</h1>
    <p>
    Your name is """),_display_(/*9.19*/localUser/*9.28*/.name),format.raw/*9.33*/(""" """),format.raw/*9.34*/("""and your email address is """),_display_(/*9.61*/if(!localUser.email)/*9.81*/ {_display_(Seq[Any](format.raw/*9.83*/("""<em>&lt;unknown&gt;</em>.""")))}/*9.110*/else/*9.115*/{_display_(Seq[Any](format.raw/*9.116*/("""
    	"""),_display_(/*10.7*/localUser/*10.16*/.email),format.raw/*10.22*/(""".
	    <i>
	    """),_display_(/*12.7*/if(!localUser.emailValidated && localUser.email)/*12.55*/ {_display_(Seq[Any](format.raw/*12.57*/("""
	      """),format.raw/*13.8*/("""(<a href=""""),_display_(/*13.19*/routes/*13.25*/.Account.verifyEmail),format.raw/*13.45*/("""">unverified - click to verify</a>)
	    """)))}/*14.8*/else/*14.13*/{_display_(Seq[Any](format.raw/*14.14*/("""
	      """),format.raw/*15.8*/("""(verified)
	    """)))}),format.raw/*16.7*/("""</i>
    """)))}),format.raw/*17.6*/("""
    """),format.raw/*18.5*/("""<br />
        """),_display_(/*19.10*/if(localUser.firstName && localUser.lastName)/*19.55*/ {_display_(Seq[Any](format.raw/*19.57*/("""
            """),format.raw/*20.13*/("""Your first name is """),_display_(/*20.33*/localUser/*20.42*/.firstName),format.raw/*20.52*/(""" """),format.raw/*20.53*/("""and your last name is """),_display_(/*20.76*/localUser/*20.85*/.lastName),format.raw/*20.94*/("""
            """),format.raw/*21.13*/("""<br/>
        """)))}),format.raw/*22.10*/("""
    """),_display_(/*23.6*/defining(localUser.getProviders())/*23.40*/ { providers =>_display_(Seq[Any](format.raw/*23.55*/("""
        """),_display_(/*24.10*/if(providers.size() > 0)/*24.34*/ {_display_(Seq[Any](format.raw/*24.36*/("""
        """),_display_(/*25.10*/if(providers.size() ==1)/*25.34*/ {_display_(Seq[Any](format.raw/*25.36*/("""
            """),_display_(/*26.14*/Messages("playauthenticate.profile.providers_one")),format.raw/*26.64*/("""
        """)))}/*27.11*/else/*27.16*/{_display_(Seq[Any](format.raw/*27.17*/("""
            """),_display_(/*28.14*/Messages("playauthenticate.profile.providers_many",providers.size().toString())),format.raw/*28.93*/("""
        """)))}),format.raw/*29.10*/("""
        """),_display_(/*30.10*/for(p <- providers) yield /*30.29*/ {_display_(Seq[Any](format.raw/*30.31*/("""
            """),_display_(/*31.14*/_providerIcon(p)),format.raw/*31.30*/("""
        """)))}),format.raw/*32.10*/("""
        """),format.raw/*33.9*/("""<br/>
        """)))}),format.raw/*34.10*/("""
    """)))}),format.raw/*35.6*/("""

    """),format.raw/*37.5*/("""<br/>
    """),_display_(/*38.6*/currentAuth()/*38.19*/ { auth =>_display_(Seq[Any](format.raw/*38.29*/("""
        """),_display_(/*39.10*/Messages("playauthenticate.profile.logged")),format.raw/*39.53*/(""" """),_display_(/*39.55*/_providerIcon(auth.getProvider())),format.raw/*39.88*/("""<br/>
        """),_display_(/*40.10*/if(auth.expires() != -1)/*40.34*/{_display_(Seq[Any](format.raw/*40.35*/("""
            """),_display_(/*41.14*/Messages("playauthenticate.profile.session", auth.getId(), Application.formatTimestamp(auth.expires()))),format.raw/*41.117*/("""
        """)))}/*42.11*/else/*42.16*/{_display_(Seq[Any](format.raw/*42.17*/("""
            """),_display_(/*43.14*/Messages("playauthenticate.profile.session_endless", auth.getId())),format.raw/*43.80*/("""
        """)))}),format.raw/*44.10*/("""
    """)))}),format.raw/*45.6*/("""
    """),format.raw/*46.5*/("""<br/>
    <ul>
    	<li><a href=""""),_display_(/*48.20*/routes/*48.26*/.Account.changePassword),format.raw/*48.49*/("""">"""),_display_(/*48.52*/Messages("playauthenticate.profile.password_change")),format.raw/*48.104*/("""</a></li>
    </ul>
    </p>
""")))}),format.raw/*51.2*/("""
"""))}
  }

  def render(localUser:models.User): play.twirl.api.HtmlFormat.Appendable = apply(localUser)

  def f:((models.User) => play.twirl.api.HtmlFormat.Appendable) = (localUser) => apply(localUser)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/profile.scala.html
                  HASH: 7d7c9c2ee8dfda71c35757e3646d761ce959c3b3
                  MATRIX: 730->1|892->32|920->79|947->81|1003->129|1042->131|1074->137|1105->142|1167->184|1225->216|1242->225|1267->230|1295->231|1348->258|1376->278|1415->280|1460->307|1473->312|1512->313|1545->320|1563->329|1590->335|1633->352|1690->400|1730->402|1765->410|1803->421|1818->427|1859->447|1919->490|1932->495|1971->496|2006->504|2053->521|2093->531|2125->536|2168->552|2222->597|2262->599|2303->612|2350->632|2368->641|2399->651|2428->652|2478->675|2496->684|2526->693|2567->706|2613->721|2645->727|2688->761|2741->776|2778->786|2811->810|2851->812|2888->822|2921->846|2961->848|3002->862|3073->912|3102->923|3115->928|3154->929|3195->943|3295->1022|3336->1032|3373->1042|3408->1061|3448->1063|3489->1077|3526->1093|3567->1103|3603->1112|3649->1127|3685->1133|3718->1139|3755->1150|3777->1163|3825->1173|3862->1183|3926->1226|3955->1228|4009->1261|4051->1276|4084->1300|4123->1301|4164->1315|4289->1418|4318->1429|4331->1434|4370->1435|4411->1449|4498->1515|4539->1525|4575->1531|4607->1536|4668->1570|4683->1576|4727->1599|4757->1602|4831->1654|4891->1684
                  LINES: 26->1|29->1|31->4|32->5|32->5|32->5|34->7|34->7|34->7|36->9|36->9|36->9|36->9|36->9|36->9|36->9|36->9|36->9|36->9|37->10|37->10|37->10|39->12|39->12|39->12|40->13|40->13|40->13|40->13|41->14|41->14|41->14|42->15|43->16|44->17|45->18|46->19|46->19|46->19|47->20|47->20|47->20|47->20|47->20|47->20|47->20|47->20|48->21|49->22|50->23|50->23|50->23|51->24|51->24|51->24|52->25|52->25|52->25|53->26|53->26|54->27|54->27|54->27|55->28|55->28|56->29|57->30|57->30|57->30|58->31|58->31|59->32|60->33|61->34|62->35|64->37|65->38|65->38|65->38|66->39|66->39|66->39|66->39|67->40|67->40|67->40|68->41|68->41|69->42|69->42|69->42|70->43|70->43|71->44|72->45|73->46|75->48|75->48|75->48|75->48|75->48|78->51
                  -- GENERATED --
              */
          