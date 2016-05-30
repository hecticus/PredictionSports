
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
object signup extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(signupForm: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.23*/("""

    """),format.raw/*4.1*/("""    """),format.raw/*5.1*/("""
    """),_display_(/*6.6*/main(Messages("playauthenticate.signup.title"))/*6.53*/ {_display_(Seq[Any](format.raw/*6.55*/("""

        """),format.raw/*8.9*/("""<div class="row">
            <div class="span6">
                <h1>"""),_display_(/*10.22*/Messages("playauthenticate.signup.title")),format.raw/*10.63*/("""</h1>
            </div>
        </div>

        <div id="signup" class="row">

            <div class="span3">
                """),format.raw/*17.53*/("""
                """),_display_(/*18.18*/helper/*18.24*/.form(routes.Application.doSignup)/*18.58*/ {_display_(Seq[Any](format.raw/*18.60*/("""

                    """),_display_(/*20.22*/if(signupForm.hasGlobalErrors)/*20.52*/ {_display_(Seq[Any](format.raw/*20.54*/("""
                        """),format.raw/*21.25*/("""<p class="error">
                            <span class="label label-important">"""),_display_(/*22.66*/signupForm/*22.76*/.globalError.message),format.raw/*22.96*/("""</span>
                        </p>
                    """)))}),format.raw/*24.22*/("""

                    """),_display_(/*26.22*/inputText(
                        signupForm("name"),
                        '_label -> Messages("playauthenticate.signup.name"),
                        'class -> "form-control"
                    )),format.raw/*30.22*/("""

                    """),_display_(/*32.22*/_emailPartial(signupForm)),format.raw/*32.47*/("""

                    """),_display_(/*34.22*/_passwordPartial(signupForm)),format.raw/*34.50*/("""

                    """),format.raw/*36.21*/("""<input type="submit" value=""""),_display_(/*36.50*/Messages("playauthenticate.signup.now")),format.raw/*36.89*/("""" class="btn btn-primary">
                    """)))}),format.raw/*37.22*/("""
            """),format.raw/*38.13*/("""</div>

            <div class="span3">
                """),_display_(/*41.18*/Messages("playauthenticate.signup.oauth")),format.raw/*41.59*/("""
                """),format.raw/*42.58*/("""
                """),_display_(/*43.18*/_providerPartial(skipCurrent=false)),format.raw/*43.53*/("""
            """),format.raw/*44.13*/("""</div>

        </div>

    """)))}))}
  }

  def render(signupForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(signupForm)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (signupForm) => apply(signupForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/signup.scala.html
                  HASH: feeab772b1826723a4b090b0434880ac3d6394d5
                  MATRIX: 796->1|953->22|985->45|1015->83|1046->89|1101->136|1140->138|1176->148|1274->219|1336->260|1492->424|1537->442|1552->448|1595->482|1635->484|1685->507|1724->537|1764->539|1817->564|1927->647|1946->657|1987->677|2076->735|2126->758|2349->960|2399->983|2445->1008|2495->1031|2544->1059|2594->1081|2650->1110|2710->1149|2789->1197|2830->1210|2914->1267|2976->1308|3021->1366|3066->1384|3122->1419|3163->1432
                  LINES: 28->1|32->1|34->4|34->5|35->6|35->6|35->6|37->8|39->10|39->10|46->17|47->18|47->18|47->18|47->18|49->20|49->20|49->20|50->21|51->22|51->22|51->22|53->24|55->26|59->30|61->32|61->32|63->34|63->34|65->36|65->36|65->36|66->37|67->38|70->41|70->41|71->42|72->43|72->43|73->44
                  -- GENERATED --
              */
          