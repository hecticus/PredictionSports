
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
object edit extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Long,Form[models.User],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, userViewForm: Form[models.User]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._
import helper.twitterBootstrap._
import helpers._
def /*7.2*/title/*7.7*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any]())};def /*9.2*/roleGroup/*9.11*/(field: Field, className: String = "role"):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*9.57*/("""
    """),format.raw/*10.5*/("""<div class="twipsies well """),_display_(/*10.32*/className),format.raw/*10.41*/("""">
        <a class="removeRole btn btn-primary danger pull-right">"""),_display_(/*11.66*/Messages("users.role.remove")),format.raw/*11.95*/("""</a>
        """),_display_(/*12.10*/select(
            field("id"),
            models.SecurityRole.toSeq,
            '_label -> Messages("users.role"),
            'class -> "form-control"
        )),format.raw/*17.10*/("""
    """),format.raw/*18.5*/("""</div>
""")))};
Seq[Any](format.raw/*1.45*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*7.12*/("""

"""),format.raw/*19.2*/("""

"""),_display_(/*21.2*/main(title = Messages("users.edit"), nav = "create")/*21.54*/ {_display_(Seq[Any](format.raw/*21.56*/("""

    """),_display_(/*23.6*/if(userViewForm.hasErrors)/*23.32*/ {_display_(Seq[Any](format.raw/*23.34*/("""
        """),format.raw/*24.9*/("""<div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>"""),_display_(/*26.22*/Messages("generic.error.title")),format.raw/*26.53*/("""</strong> """),_display_(/*26.64*/Messages("generic.error.content")),format.raw/*26.97*/("""
            """),format.raw/*27.13*/("""<p>"""),_display_(/*27.17*/userViewForm/*27.29*/.errorsAsJson),format.raw/*27.42*/("""</p>
        </div>
    """)))}),format.raw/*29.6*/("""

    """),_display_(/*31.6*/helper/*31.12*/.form(routes.Signup.doForgotPassword)/*31.49*/ {_display_(Seq[Any](format.raw/*31.51*/("""
        """),_display_(/*32.10*/_emailPartial(userViewForm)),format.raw/*32.37*/("""
        """),format.raw/*33.9*/("""<input type="submit" value=""""),_display_(/*33.38*/Messages("playauthenticate.password.forgot.cta")),format.raw/*33.86*/("""" class="btn btn-primary">
    """)))}),format.raw/*34.6*/("""

    """),_display_(/*36.6*/helper/*36.12*/.form(controllers.routes.UsersView.update(id), 'enctype -> "multipart/form-data", 'id -> "form")/*36.108*/ {_display_(Seq[Any](format.raw/*36.110*/("""

        """),format.raw/*38.9*/("""<fieldset>
            <legend>"""),_display_(/*39.22*/Messages("users.info")),format.raw/*39.44*/("""</legend>

            """),_display_(/*41.14*/materialCheckbox(
                userViewForm("active"),
                'label -> Messages("users.active"),
                '_help -> Messages("users.active.help"),
                'class -> "allCheckbox"
            )),format.raw/*46.14*/("""


            """),_display_(/*49.14*/inputText(
                userViewForm("name"),
                '_label -> Messages("users.name"),
                '_help -> Messages("users.name.help"),
                'class -> "form-control",
                '_error -> userViewForm.globalError
            )),format.raw/*55.14*/("""

            """),_display_(/*57.14*/inputText(
                userViewForm("email"),
                '_label -> Messages("users.email"),
                '_help -> Messages("users.email.help"),
                'class -> "form-control",
                '_error -> userViewForm.globalError
            )),format.raw/*63.14*/("""

            """),_display_(/*65.14*/inputText(
                userViewForm("firstName"),
                '_label -> Messages("users.firstName"),
                '_help -> Messages("users.firstName.help"),
                'class -> "form-control",
                '_error -> userViewForm.globalError
            )),format.raw/*71.14*/("""

            """),_display_(/*73.14*/inputText(
                userViewForm("lastName"),
                '_label -> Messages("users.lastName"),
                '_help -> Messages("users.lastName.help"),
                'class -> "form-control",
                '_error -> userViewForm.globalError
            )),format.raw/*79.14*/("""



            """),format.raw/*83.13*/("""<div id="roles">

                """),_display_(/*85.18*/repeat(userViewForm("securityRoles"))/*85.55*/ { role =>_display_(Seq[Any](format.raw/*85.65*/("""

                    """),_display_(/*87.22*/roleGroup(role)),format.raw/*87.37*/("""

                """)))}),format.raw/*89.18*/("""
                """),_display_(/*90.18*/roleGroup(
                    userViewForm("securityRoles[x]"),
                    className = "role_template"
                )),format.raw/*93.18*/("""

                """),format.raw/*95.17*/("""<div class="manage">
                    <a class="addRole btn btn-primary success">"""),_display_(/*96.65*/Messages("users.role.add")),format.raw/*96.91*/("""</a>
                </div>

            </div>

        </fieldset>

        <div class="actions">
            <input type="submit" value="""),_display_(/*104.41*/Messages("users.submit.update")),format.raw/*104.72*/(""" """),format.raw/*104.73*/("""class="btn btn-success">
            <a href=""""),_display_(/*105.23*/controllers/*105.34*/.routes.UsersView.list()),format.raw/*105.58*/("""" class="btn btn-primary">"""),_display_(/*105.85*/Messages("generic.cancel")),format.raw/*105.111*/("""</a>
        </div>

    """)))}),format.raw/*108.6*/("""

    """),_display_(/*110.6*/form(controllers.routes.UsersView.delete(id), 'class -> "topRight")/*110.73*/ {_display_(Seq[Any](format.raw/*110.75*/("""
        """),format.raw/*111.9*/("""<input type="submit" value="""),_display_(/*111.37*/Messages("users.submit.delete")),format.raw/*111.68*/(""" """),format.raw/*111.69*/("""class="btn btn-danger">

    """)))}),format.raw/*113.6*/("""

    """),format.raw/*115.5*/("""<script type="text/javascript" charset="utf-8">

        $(document).on('click','.removeRole', function(e) """),format.raw/*117.59*/("""{"""),format.raw/*117.60*/("""
            """),format.raw/*118.13*/("""$(this).parents('.role').remove()
            renumberRole()
        """),format.raw/*120.9*/("""}"""),format.raw/*120.10*/(""")

        $(document).on('click','.addRole', function(e) """),format.raw/*122.56*/("""{"""),format.raw/*122.57*/("""
            """),format.raw/*123.13*/("""var template = $('.role_template')
            template.before('<div class="twipsies well role">' + template.html() + '</div>')
            renumberRole()
        """),format.raw/*126.9*/("""}"""),format.raw/*126.10*/(""")

        var renumberRole = function() """),format.raw/*128.39*/("""{"""),format.raw/*128.40*/("""
            """),format.raw/*129.13*/("""$('.role').each(function(i) """),format.raw/*129.41*/("""{"""),format.raw/*129.42*/("""
                """),format.raw/*130.17*/("""$('select', this).each(function() """),format.raw/*130.51*/("""{"""),format.raw/*130.52*/("""
                    """),format.raw/*131.21*/("""$(this).attr('name', $(this).attr('name').replace(/securityRoles\[.+?\]/g, 'securityRoles[' + i + ']'))
                """),format.raw/*132.17*/("""}"""),format.raw/*132.18*/(""")
            """),format.raw/*133.13*/("""}"""),format.raw/*133.14*/(""")
        """),format.raw/*134.9*/("""}"""),format.raw/*134.10*/("""

        """),format.raw/*136.9*/("""$('#form').submit(function() """),format.raw/*136.38*/("""{"""),format.raw/*136.39*/("""
            """),format.raw/*137.13*/("""$('.role_template').remove()
        """),format.raw/*138.9*/("""}"""),format.raw/*138.10*/(""")


    </script>

""")))}),format.raw/*143.2*/("""
"""))}
  }

  def render(id:Long,userViewForm:Form[models.User]): play.twirl.api.HtmlFormat.Appendable = apply(id,userViewForm)

  def f:((Long,Form[models.User]) => play.twirl.api.HtmlFormat.Appendable) = (id,userViewForm) => apply(id,userViewForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu May 26 17:49:44 VET 2016
                  SOURCE: /Users/palenge/Hecticus/TIM_Naty/TimPanama/app/views/users/edit.scala.html
                  HASH: 771c6aeafdd25eb9208c7cfa7a33f6dfaf5875dc
                  MATRIX: 744->1|923->117|935->122|1012->130|1029->139|1151->185|1183->190|1237->217|1267->226|1362->294|1412->323|1453->337|1639->502|1671->507|1718->44|1746->115|1774->127|1803->515|1832->518|1893->570|1933->572|1966->579|2001->605|2041->607|2077->616|2256->768|2308->799|2346->810|2400->843|2441->856|2472->860|2493->872|2527->885|2582->910|2615->917|2630->923|2676->960|2716->962|2753->972|2801->999|2837->1008|2893->1037|2962->1085|3024->1117|3057->1124|3072->1130|3178->1226|3219->1228|3256->1238|3315->1270|3358->1292|3409->1316|3650->1536|3693->1552|3976->1814|4018->1829|4304->2094|4346->2109|4644->2386|4686->2401|4981->2675|5025->2691|5087->2726|5133->2763|5181->2773|5231->2796|5267->2811|5317->2830|5362->2848|5513->2978|5559->2996|5671->3081|5718->3107|5886->3247|5939->3278|5969->3279|6044->3326|6065->3337|6111->3361|6166->3388|6215->3414|6272->3440|6306->3447|6383->3514|6424->3516|6461->3525|6517->3553|6570->3584|6600->3585|6661->3615|6695->3621|6831->3728|6861->3729|6903->3742|7000->3811|7030->3812|7117->3870|7147->3871|7189->3884|7380->4047|7410->4048|7480->4089|7510->4090|7552->4103|7609->4131|7639->4132|7685->4149|7748->4183|7778->4184|7828->4205|7977->4325|8007->4326|8050->4340|8080->4341|8118->4351|8148->4352|8186->4362|8244->4391|8274->4392|8316->4405|8381->4442|8411->4443|8462->4463
                  LINES: 26->1|30->7|30->7|32->9|32->9|34->9|35->10|35->10|35->10|36->11|36->11|37->12|42->17|43->18|45->1|47->6|48->7|50->19|52->21|52->21|52->21|54->23|54->23|54->23|55->24|57->26|57->26|57->26|57->26|58->27|58->27|58->27|58->27|60->29|62->31|62->31|62->31|62->31|63->32|63->32|64->33|64->33|64->33|65->34|67->36|67->36|67->36|67->36|69->38|70->39|70->39|72->41|77->46|80->49|86->55|88->57|94->63|96->65|102->71|104->73|110->79|114->83|116->85|116->85|116->85|118->87|118->87|120->89|121->90|124->93|126->95|127->96|127->96|135->104|135->104|135->104|136->105|136->105|136->105|136->105|136->105|139->108|141->110|141->110|141->110|142->111|142->111|142->111|142->111|144->113|146->115|148->117|148->117|149->118|151->120|151->120|153->122|153->122|154->123|157->126|157->126|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|163->132|163->132|164->133|164->133|165->134|165->134|167->136|167->136|167->136|168->137|169->138|169->138|174->143
                  -- GENERATED --
              */
          