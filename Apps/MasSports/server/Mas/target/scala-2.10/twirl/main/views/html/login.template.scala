
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(loginForm: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import com.feth.play.module.pa.views.html._

Seq[Any](format.raw/*1.22*/("""

"""),format.raw/*6.1*/("""
"""),_display_(/*7.2*/main(Messages("playauthenticate.login.title"))/*7.48*/ {_display_(Seq[Any](format.raw/*7.50*/("""

    """),format.raw/*9.5*/("""<div class="well bs-component">





        <div class="row">
            <div class="col-lg-6">

                """),_display_(/*18.18*/helper/*18.24*/.form(routes.Application.doLogin, 'class -> "form-horizontal")/*18.86*/ {_display_(Seq[Any](format.raw/*18.88*/("""

                    """),_display_(/*20.22*/if(loginForm.hasGlobalErrors)/*20.51*/ {_display_(Seq[Any](format.raw/*20.53*/("""
                        """),format.raw/*21.25*/("""<div class="alert alert-dismissable alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <h4>Danger!</h4>
                            <p>"""),_display_(/*24.33*/loginForm/*24.42*/.globalError.message),format.raw/*24.62*/("""</p>
                        </div>
                    """)))}),format.raw/*26.22*/("""

                    """),format.raw/*28.21*/("""<fieldset>
                        <legend>"""),_display_(/*29.34*/Messages("playauthenticate.login.title")),format.raw/*29.74*/("""</legend>



                        <div class="form-group">
                            <label for="email" class="col-lg-2 control-label">Email</label>
                            <div id="email_field" class="col-lg-8" >
                                <input id="email" name="email" type="email" class="form-control" placeholder="Email">
                                <span class="help-inline"></span>
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group" >
                            <label for="password" class="col-lg-2 control-label">Password</label>
                            <div id="password_field" class="col-lg-8">
                                <input id="password" name="password" type="password" class="form-control" placeholder="Contraseña">
                                <span class="help-inline"></span>
                                <span class="help-block"></span>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="submit" class="btn btn-primary" value=""""),_display_(/*54.87*/Messages("playauthenticate.login.now")),format.raw/*54.125*/("""">"""),_display_(/*54.128*/Messages("playauthenticate.login.now")),format.raw/*54.166*/("""</button>
                            </div>
                        </div>



                    </fieldset>

                """)))}),format.raw/*62.18*/("""

                """),format.raw/*68.25*/("""


            """),format.raw/*71.13*/("""</div>
        </div>



    </div>



    <div id="login" class="row">

            <!--div class="col-lg-6 span3">
  """),format.raw/*83.39*/("""
        """),_display_(/*84.10*/helper/*84.16*/.form(routes.Application.doLogin)/*84.49*/ {_display_(Seq[Any](format.raw/*84.51*/("""

            """),_display_(/*86.14*/if(loginForm.hasGlobalErrors)/*86.43*/ {_display_(Seq[Any](format.raw/*86.45*/("""
                """),format.raw/*87.17*/("""<p class="error">
                    <span class="label label-important">"""),_display_(/*88.58*/loginForm/*88.67*/.globalError.message),format.raw/*88.87*/("""</span>
                </p>
            """)))}),format.raw/*90.14*/("""

            """),_display_(/*92.14*/_emailPartial(loginForm)),format.raw/*92.38*/("""

            """),_display_(/*94.14*/inputPassword(
                loginForm("password"),
                '_showConstraints -> false,
                '_label -> Messages("playauthenticate.login.password.placeholder")
            )),format.raw/*98.14*/("""

            """),format.raw/*100.13*/("""<input type="submit" value=""""),_display_(/*100.42*/Messages("playauthenticate.login.now")),format.raw/*100.80*/("""" class="btn btn-primary"><br/>
            <br/>
            <a href="javascript:void(0);" onclick="window.location.href = jsRoutes.controllers.Signup.forgotPassword($('#email').val() || null).absoluteURL();">"""),_display_(/*102.162*/Messages("playauthenticate.login.forgot.password")),format.raw/*102.212*/("""</a>

        """)))}),format.raw/*104.10*/("""
    """),format.raw/*105.5*/("""</div>

    <div class="col-lg-6 span3">
        """),_display_(/*108.10*/Messages("playauthenticate.login.oauth")),format.raw/*108.50*/("""
        """),format.raw/*109.50*/("""
        """),_display_(/*110.10*/_providerPartial(skipCurrent=false)),format.raw/*110.45*/("""
        """),_display_(/*111.10*/providerAvailable("basic")/*111.36*/ { available: Boolean =>_display_(Seq[Any](format.raw/*111.60*/("""
            """),_display_(/*112.14*/if(available)/*112.27*/ {_display_(Seq[Any](format.raw/*112.29*/("""
                """),format.raw/*113.17*/("""<br>
                <a href=""""),_display_(/*114.27*/com/*114.30*/.feth.play.module.pa.controllers.routes.Authenticate.authenticate("basic")),format.raw/*114.104*/("""">"""),_display_(/*114.107*/Messages("playauthenticate.login.basic")),format.raw/*114.147*/("""</a>
            """)))}),format.raw/*115.14*/("""
        """)))}),format.raw/*116.10*/("""
    """),format.raw/*117.5*/("""</div-->

    </div>

""")))}),format.raw/*121.2*/("""



"""),format.raw/*125.1*/("""<!--form class="form-horizontal">
<fieldset>
    <legend>Legend</legend>
    <div class="form-group">
        <label for="inputEmail" class="col-lg-2 control-label">Email</label>
        <div class="col-lg-10">
            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="col-lg-2 control-label">Password</label>
        <div class="col-lg-10">
            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            <div class="checkbox">
                <label>
                    <input type="checkbox"> Checkbox
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="inputFile" class="col-lg-2 control-label">File</label>
        <div class="col-lg-10">
            <input type="text" readonly="" class="form-control floating-label" placeholder="Browse...">
            <input type="file" id="inputFile" multiple="">
        </div>
    </div>
    <div class="form-group">
        <label for="textArea" class="col-lg-2 control-label">Textarea</label>
        <div class="col-lg-10">
            <textarea class="form-control" rows="3" id="textArea"></textarea>
            <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Radios</label>
        <div class="col-lg-10">
            <div class="radio radio-primary">
                <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                    Option one is this
                </label>
            </div>
            <div class="radio radio-primary">
                <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                    Option two can be something else
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="select" class="col-lg-2 control-label">Selects</label>
        <div class="col-lg-10">
            <select class="form-control" id="select">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
            <br>
            <select multiple="" class="form-control">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-10 col-lg-offset-2">
            <button class="btn btn-default">Cancel</button>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</fieldset>
</form-->"""))}
  }

  def render(loginForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(loginForm)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (loginForm) => apply(loginForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:42 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/login.scala.html
                  HASH: c0c2ccc97dde868d77606236a254c7804d897d9c
                  MATRIX: 795->1|995->21|1023->119|1050->121|1104->167|1143->169|1175->175|1318->291|1333->297|1404->359|1444->361|1494->384|1532->413|1572->415|1625->440|1876->664|1894->673|1935->693|2023->750|2073->772|2144->816|2205->856|3524->2148|3584->2186|3615->2189|3675->2227|3835->2356|3881->2629|3924->2644|4071->2799|4108->2809|4123->2815|4165->2848|4205->2850|4247->2865|4285->2894|4325->2896|4370->2913|4472->2988|4490->2997|4531->3017|4604->3059|4646->3074|4691->3098|4733->3113|4948->3307|4991->3321|5048->3350|5108->3388|5348->3599|5421->3649|5468->3664|5501->3669|5579->3719|5641->3759|5679->3809|5717->3819|5774->3854|5812->3864|5848->3890|5911->3914|5953->3928|5976->3941|6017->3943|6063->3960|6122->3991|6135->3994|6232->4068|6264->4071|6327->4111|6377->4129|6419->4139|6452->4144|6506->4167|6538->4171
                  LINES: 28->1|33->1|35->6|36->7|36->7|36->7|38->9|47->18|47->18|47->18|47->18|49->20|49->20|49->20|50->21|53->24|53->24|53->24|55->26|57->28|58->29|58->29|83->54|83->54|83->54|83->54|91->62|93->68|96->71|108->83|109->84|109->84|109->84|109->84|111->86|111->86|111->86|112->87|113->88|113->88|113->88|115->90|117->92|117->92|119->94|123->98|125->100|125->100|125->100|127->102|127->102|129->104|130->105|133->108|133->108|134->109|135->110|135->110|136->111|136->111|136->111|137->112|137->112|137->112|138->113|139->114|139->114|139->114|139->114|139->114|140->115|141->116|142->117|146->121|150->125
                  -- GENERATED --
              */
          