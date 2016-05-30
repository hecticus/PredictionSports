
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
object restricted extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[models.User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(localUser: models.User = null):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.33*/("""

"""),_display_(/*3.2*/main(Messages("playauthenticate.navigation.restricted"))/*3.58*/ {_display_(Seq[Any](format.raw/*3.60*/("""
    
    """),format.raw/*5.5*/("""<h1>"""),_display_(/*5.10*/Messages("playauthenticate.navigation.restricted")),format.raw/*5.60*/("""</h1>
    <p>
        """),_display_(/*7.10*/Messages("playauthenticate.restricted.secrets")),format.raw/*7.57*/("""
    """),format.raw/*8.5*/("""</p>
""")))}))}
  }

  def render(localUser:models.User): play.twirl.api.HtmlFormat.Appendable = apply(localUser)

  def f:((models.User) => play.twirl.api.HtmlFormat.Appendable) = (localUser) => apply(localUser)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:43 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/restricted.scala.html
                  HASH: 320bcedc30de0ed0cd40d90b2bd18e4816286628
                  MATRIX: 733->1|852->32|880->35|944->91|983->93|1019->103|1050->108|1120->158|1169->181|1236->228|1267->233
                  LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|35->7|35->7|36->8
                  -- GENERATED --
              */
          