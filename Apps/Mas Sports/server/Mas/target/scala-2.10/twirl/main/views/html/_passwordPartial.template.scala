
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
object _passwordPartial extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(f: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._

Seq[Any](format.raw/*1.14*/("""

"""),format.raw/*5.1*/("""
             """),_display_(/*6.15*/inputPassword(
                f("password"),
                '_label -> Messages("playauthenticate.login.password.placeholder"),
                'class -> "form-control"
             )),format.raw/*10.15*/("""
             
             """),_display_(/*12.15*/inputPassword(
                f("repeatPassword"),
                '_label -> Messages("playauthenticate.login.password.repeat"),
                '_showConstraints -> false,
                '_error -> f.error("password"),
                'class -> "form-control"
             )))}
  }

  def render(f:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(f)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (f) => apply(f)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/_passwordPartial.scala.html
                  HASH: 6337fb26a38e9e57af4eede5850bda21df0ebdfd
                  MATRIX: 806->1|954->13|982->66|1023->81|1229->266|1285->295
                  LINES: 28->1|32->1|34->5|35->6|39->10|41->12
                  -- GENERATED --
              */
          