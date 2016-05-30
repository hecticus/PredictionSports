// @SOURCE:/Users/palenge/Hecticus/TIM_Naty/UpstreamAPIConnector/conf/UpstreamAPIConnector.routes
// @HASH:efd2ab176c722b76543f9491fd059aff6b6b9071
// @DATE:Thu May 26 17:49:33 VET 2016
package UpstreamAPIConnector

import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Assets_versioned0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_versioned0_invoker = createInvoker(
controllers.Assets.versioned(fakeValue[String], fakeValue[Asset]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:10
private[this] lazy val controllers_Upstream_sendClientEvent1_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/upstream"))))
private[this] lazy val controllers_Upstream_sendClientEvent1_invoker = createInvoker(
controllers.Upstream.sendClientEvent(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "sendClientEvent", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """api/v1/client/$id<[^/]+>/upstream"""))
        

// @LINE:11
private[this] lazy val controllers_Upstream_resetUpstreamPass2_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/resetpass"))))
private[this] lazy val controllers_Upstream_resetUpstreamPass2_invoker = createInvoker(
controllers.Upstream.resetUpstreamPass(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "resetUpstreamPass", Nil,"POST", """""", Routes.prefix + """api/v1/clients/upstream/resetpass"""))
        

// @LINE:14
private[this] lazy val controllers_Upstream_upstreamFakeCreate3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/game/user/subscribe"))))
private[this] lazy val controllers_Upstream_upstreamFakeCreate3_invoker = createInvoker(
controllers.Upstream.upstreamFakeCreate(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "upstreamFakeCreate", Nil,"POST", """#FAKE UPSTREAM URL""", Routes.prefix + """api/v1/clients/upstream/game/user/subscribe"""))
        

// @LINE:15
private[this] lazy val controllers_Upstream_upstreamFakeLogin4_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/game/user/login"))))
private[this] lazy val controllers_Upstream_upstreamFakeLogin4_invoker = createInvoker(
controllers.Upstream.upstreamFakeLogin(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "upstreamFakeLogin", Nil,"POST", """""", Routes.prefix + """api/v1/clients/upstream/game/user/login"""))
        

// @LINE:16
private[this] lazy val controllers_Upstream_upstreamFakeStatus5_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/game/user/status"))))
private[this] lazy val controllers_Upstream_upstreamFakeStatus5_invoker = createInvoker(
controllers.Upstream.upstreamFakeStatus(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "upstreamFakeStatus", Nil,"POST", """""", Routes.prefix + """api/v1/clients/upstream/game/user/status"""))
        

// @LINE:17
private[this] lazy val controllers_Upstream_upstreamFakeResetPass6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/game/user/password"))))
private[this] lazy val controllers_Upstream_upstreamFakeResetPass6_invoker = createInvoker(
controllers.Upstream.upstreamFakeResetPass(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "upstreamFakeResetPass", Nil,"POST", """""", Routes.prefix + """api/v1/clients/upstream/game/user/password"""))
        

// @LINE:18
private[this] lazy val controllers_Upstream_upstreamFakeEventSend7_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/upstream/game/user/event"))))
private[this] lazy val controllers_Upstream_upstreamFakeEventSend7_invoker = createInvoker(
controllers.Upstream.upstreamFakeEventSend(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.Upstream", "upstreamFakeEventSend", Nil,"POST", """""", Routes.prefix + """api/v1/clients/upstream/game/user/event"""))
        

// @LINE:23
private[this] lazy val controllers_client_Clients_create8_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/create"))))
private[this] lazy val controllers_client_Clients_create8_invoker = createInvoker(
controllers.client.Clients.create(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "create", Nil,"POST", """#V1""", Routes.prefix + """api/v1/clients/create"""))
        

// @LINE:24
private[this] lazy val controllers_client_Clients_update9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/update/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_update9_invoker = createInvoker(
controllers.client.Clients.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """api/v1/clients/update/$id<[^/]+>"""))
        

// @LINE:25
private[this] lazy val controllers_client_Clients_delete10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/delete/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_delete10_invoker = createInvoker(
controllers.client.Clients.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "delete", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """api/v1/clients/delete/$id<[^/]+>"""))
        

// @LINE:26
private[this] lazy val controllers_client_Clients_get11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/get/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("upstreamChannel", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_get11_invoker = createInvoker(
controllers.client.Clients.get(fakeValue[Integer], fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """api/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>"""))
        

// @LINE:27
private[this] lazy val controllers_client_Clients_list12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/list"))))
private[this] lazy val controllers_client_Clients_list12_invoker = createInvoker(
controllers.client.Clients.list(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """api/v1/clients/list"""))
        

// @LINE:30
private[this] lazy val controllers_client_Clients_get13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/pmc/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_get13_invoker = createInvoker(
controllers.client.Clients.get(fakeValue[Integer], fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]),"GET", """##Clients PMC""", Routes.prefix + """api/v1/clients/pmc/get/$id<[^/]+>"""))
        

// @LINE:31
private[this] lazy val controllers_client_Clients_list14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/pmc/list/"),DynamicPart("page", """[^/]+""",true),StaticPart("/"),DynamicPart("pageSize", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_list14_invoker = createInvoker(
controllers.client.Clients.list(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """api/v1/clients/pmc/list/$page<[^/]+>/$pageSize<[^/]+>"""))
        

// @LINE:32
private[this] lazy val controllers_client_Clients_cleanDevices15_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/clients/pmc/clean"))))
private[this] lazy val controllers_client_Clients_cleanDevices15_invoker = createInvoker(
controllers.client.Clients.cleanDevices(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "cleanDevices", Nil,"POST", """""", Routes.prefix + """api/v1/clients/pmc/clean"""))
        

// @LINE:35
private[this] lazy val controllers_client_Clients_create16_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v2/client"))))
private[this] lazy val controllers_client_Clients_create16_invoker = createInvoker(
controllers.client.Clients.create(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "create", Nil,"POST", """#V2""", Routes.prefix + """api/v2/client"""))
        

// @LINE:36
private[this] lazy val controllers_client_Clients_get17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_get17_invoker = createInvoker(
controllers.client.Clients.get(fakeValue[Integer], fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """api/v2/client/$id<[^/]+>"""))
        

// @LINE:37
private[this] lazy val controllers_client_Clients_update18_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_update18_invoker = createInvoker(
controllers.client.Clients.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """api/v2/client/$id<[^/]+>"""))
        

// @LINE:38
private[this] lazy val controllers_client_Clients_delete19_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_delete19_invoker = createInvoker(
controllers.client.Clients.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.client.Clients", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """api/v2/client/$id<[^/]+>"""))
        

// @LINE:42
private[this] lazy val controllers_CountriesView_list20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/list"))))
private[this] lazy val controllers_CountriesView_list20_invoker = createInvoker(
controllers.CountriesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """CountriesView
 CountriesView list (look at the default values for pagination parameters)""", Routes.prefix + """countries/list"""))
        

// @LINE:43
private[this] lazy val controllers_CountriesView_lsort21_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/lsort"))))
private[this] lazy val controllers_CountriesView_lsort21_invoker = createInvoker(
controllers.CountriesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "lsort", Nil,"GET", """""", Routes.prefix + """countries/lsort"""))
        

// @LINE:45
private[this] lazy val controllers_CountriesView_blank22_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/blank"))))
private[this] lazy val controllers_CountriesView_blank22_invoker = createInvoker(
controllers.CountriesView.blank(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "blank", Nil,"GET", """ Add CountriesView""", Routes.prefix + """countries/blank"""))
        

// @LINE:46
private[this] lazy val controllers_CountriesView_submit23_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries"))))
private[this] lazy val controllers_CountriesView_submit23_invoker = createInvoker(
controllers.CountriesView.submit(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "submit", Nil,"POST", """""", Routes.prefix + """countries"""))
        

// @LINE:48
private[this] lazy val controllers_CountriesView_sort24_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_sort24_invoker = createInvoker(
controllers.CountriesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "sort", Seq(classOf[String]),"GET", """ Edit existing CountriesView""", Routes.prefix + """countries/sort/$ids<[^/]+>"""))
        

// @LINE:49
private[this] lazy val controllers_CountriesView_edit25_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_edit25_invoker = createInvoker(
controllers.CountriesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """countries/$id<[^/]+>"""))
        

// @LINE:50
private[this] lazy val controllers_CountriesView_update26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_update26_invoker = createInvoker(
controllers.CountriesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """countries/$id<[^/]+>"""))
        

// @LINE:52
private[this] lazy val controllers_CountriesView_delete27_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("countries/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_CountriesView_delete27_invoker = createInvoker(
controllers.CountriesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.CountriesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a CountriesView""", Routes.prefix + """countries/$id<[^/]+>/delete"""))
        

// @LINE:56
private[this] lazy val controllers_LanguagesView_list28_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/list"))))
private[this] lazy val controllers_LanguagesView_list28_invoker = createInvoker(
controllers.LanguagesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """LanguagesView
 LanguagesView list (look at the default values for pagination parameters)""", Routes.prefix + """languages/list"""))
        

// @LINE:57
private[this] lazy val controllers_LanguagesView_lsort29_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/lsort"))))
private[this] lazy val controllers_LanguagesView_lsort29_invoker = createInvoker(
controllers.LanguagesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "lsort", Nil,"GET", """""", Routes.prefix + """languages/lsort"""))
        

// @LINE:59
private[this] lazy val controllers_LanguagesView_blank30_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/blank"))))
private[this] lazy val controllers_LanguagesView_blank30_invoker = createInvoker(
controllers.LanguagesView.blank(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "blank", Nil,"GET", """ Add LanguagesView""", Routes.prefix + """languages/blank"""))
        

// @LINE:60
private[this] lazy val controllers_LanguagesView_submit31_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages"))))
private[this] lazy val controllers_LanguagesView_submit31_invoker = createInvoker(
controllers.LanguagesView.submit(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "submit", Nil,"POST", """""", Routes.prefix + """languages"""))
        

// @LINE:62
private[this] lazy val controllers_LanguagesView_sort32_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_sort32_invoker = createInvoker(
controllers.LanguagesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "sort", Seq(classOf[String]),"GET", """ Edit existing LanguagesView""", Routes.prefix + """languages/sort/$ids<[^/]+>"""))
        

// @LINE:63
private[this] lazy val controllers_LanguagesView_edit33_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_edit33_invoker = createInvoker(
controllers.LanguagesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """languages/$id<[^/]+>"""))
        

// @LINE:64
private[this] lazy val controllers_LanguagesView_update34_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_update34_invoker = createInvoker(
controllers.LanguagesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """languages/$id<[^/]+>"""))
        

// @LINE:66
private[this] lazy val controllers_LanguagesView_delete35_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("languages/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_LanguagesView_delete35_invoker = createInvoker(
controllers.LanguagesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.LanguagesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a LanguagesView""", Routes.prefix + """languages/$id<[^/]+>/delete"""))
        

// @LINE:70
private[this] lazy val controllers_TimezonesView_list36_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/list"))))
private[this] lazy val controllers_TimezonesView_list36_invoker = createInvoker(
controllers.TimezonesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """TimezonesView
 TimezonesView list (look at the default values for pagination parameters)""", Routes.prefix + """timezones/list"""))
        

// @LINE:71
private[this] lazy val controllers_TimezonesView_lsort37_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/lsort"))))
private[this] lazy val controllers_TimezonesView_lsort37_invoker = createInvoker(
controllers.TimezonesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "lsort", Nil,"GET", """""", Routes.prefix + """timezones/lsort"""))
        

// @LINE:73
private[this] lazy val controllers_TimezonesView_blank38_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/blank"))))
private[this] lazy val controllers_TimezonesView_blank38_invoker = createInvoker(
controllers.TimezonesView.blank(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "blank", Nil,"GET", """ Add TimezonesView""", Routes.prefix + """timezones/blank"""))
        

// @LINE:74
private[this] lazy val controllers_TimezonesView_submit39_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones"))))
private[this] lazy val controllers_TimezonesView_submit39_invoker = createInvoker(
controllers.TimezonesView.submit(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "submit", Nil,"POST", """""", Routes.prefix + """timezones"""))
        

// @LINE:76
private[this] lazy val controllers_TimezonesView_sort40_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_sort40_invoker = createInvoker(
controllers.TimezonesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "sort", Seq(classOf[String]),"GET", """ Edit existing TimezonesView""", Routes.prefix + """timezones/sort/$ids<[^/]+>"""))
        

// @LINE:77
private[this] lazy val controllers_TimezonesView_edit41_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_edit41_invoker = createInvoker(
controllers.TimezonesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """timezones/$id<[^/]+>"""))
        

// @LINE:78
private[this] lazy val controllers_TimezonesView_update42_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_update42_invoker = createInvoker(
controllers.TimezonesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """timezones/$id<[^/]+>"""))
        

// @LINE:80
private[this] lazy val controllers_TimezonesView_delete43_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("timezones/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_TimezonesView_delete43_invoker = createInvoker(
controllers.TimezonesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.TimezonesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a TimezonesView""", Routes.prefix + """timezones/$id<[^/]+>/delete"""))
        

// @LINE:84
private[this] lazy val controllers_DevicesView_list44_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/list"))))
private[this] lazy val controllers_DevicesView_list44_invoker = createInvoker(
controllers.DevicesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """DevicesView
 DevicesView list (look at the default values for pagination parameters)""", Routes.prefix + """devices/list"""))
        

// @LINE:85
private[this] lazy val controllers_DevicesView_lsort45_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/lsort"))))
private[this] lazy val controllers_DevicesView_lsort45_invoker = createInvoker(
controllers.DevicesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "lsort", Nil,"GET", """""", Routes.prefix + """devices/lsort"""))
        

// @LINE:87
private[this] lazy val controllers_DevicesView_blank46_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/blank"))))
private[this] lazy val controllers_DevicesView_blank46_invoker = createInvoker(
controllers.DevicesView.blank(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "blank", Nil,"GET", """ Add DevicesView""", Routes.prefix + """devices/blank"""))
        

// @LINE:88
private[this] lazy val controllers_DevicesView_submit47_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices"))))
private[this] lazy val controllers_DevicesView_submit47_invoker = createInvoker(
controllers.DevicesView.submit(),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "submit", Nil,"POST", """""", Routes.prefix + """devices"""))
        

// @LINE:90
private[this] lazy val controllers_DevicesView_sort48_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_sort48_invoker = createInvoker(
controllers.DevicesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "sort", Seq(classOf[String]),"GET", """ Edit existing DevicesView""", Routes.prefix + """devices/sort/$ids<[^/]+>"""))
        

// @LINE:91
private[this] lazy val controllers_DevicesView_edit49_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_edit49_invoker = createInvoker(
controllers.DevicesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """devices/$id<[^/]+>"""))
        

// @LINE:92
private[this] lazy val controllers_DevicesView_update50_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_update50_invoker = createInvoker(
controllers.DevicesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """devices/$id<[^/]+>"""))
        

// @LINE:94
private[this] lazy val controllers_DevicesView_delete51_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("devices/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_DevicesView_delete51_invoker = createInvoker(
controllers.DevicesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "UpstreamAPIConnector", "controllers.DevicesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a DevicesView""", Routes.prefix + """devices/$id<[^/]+>/delete"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.versioned(path:String = "/public", file:Asset)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/client/$id<[^/]+>/upstream""","""controllers.Upstream.sendClientEvent(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/resetpass""","""controllers.Upstream.resetUpstreamPass()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/game/user/subscribe""","""controllers.Upstream.upstreamFakeCreate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/game/user/login""","""controllers.Upstream.upstreamFakeLogin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/game/user/status""","""controllers.Upstream.upstreamFakeStatus()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/game/user/password""","""controllers.Upstream.upstreamFakeResetPass()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/upstream/game/user/event""","""controllers.Upstream.upstreamFakeEventSend()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/create""","""controllers.client.Clients.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/update/$id<[^/]+>""","""controllers.client.Clients.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/delete/$id<[^/]+>""","""controllers.client.Clients.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>""","""controllers.client.Clients.get(id:Integer, upstreamChannel:String, pmc:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/list""","""controllers.client.Clients.list(pageSize:Integer ?= 0, page:Integer ?= 0, pmc:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/pmc/get/$id<[^/]+>""","""controllers.client.Clients.get(id:Integer, upstreamChannel:String ?= "Android", pmc:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/pmc/list/$page<[^/]+>/$pageSize<[^/]+>""","""controllers.client.Clients.list(pageSize:Integer, page:Integer, pmc:Boolean ?= true)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/clients/pmc/clean""","""controllers.client.Clients.cleanDevices()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v2/client""","""controllers.client.Clients.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v2/client/$id<[^/]+>""","""controllers.client.Clients.get(id:Integer, upstreamChannel:String ?= "Android", pmc:Boolean ?= false)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v2/client/$id<[^/]+>""","""controllers.client.Clients.update(id:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v2/client/$id<[^/]+>""","""controllers.client.Clients.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/list""","""controllers.CountriesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/lsort""","""controllers.CountriesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/blank""","""controllers.CountriesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries""","""controllers.CountriesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/sort/$ids<[^/]+>""","""controllers.CountriesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/$id<[^/]+>""","""controllers.CountriesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/$id<[^/]+>""","""controllers.CountriesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """countries/$id<[^/]+>/delete""","""controllers.CountriesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/list""","""controllers.LanguagesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/lsort""","""controllers.LanguagesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/blank""","""controllers.LanguagesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages""","""controllers.LanguagesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/sort/$ids<[^/]+>""","""controllers.LanguagesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/$id<[^/]+>""","""controllers.LanguagesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/$id<[^/]+>""","""controllers.LanguagesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """languages/$id<[^/]+>/delete""","""controllers.LanguagesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/list""","""controllers.TimezonesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/lsort""","""controllers.TimezonesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/blank""","""controllers.TimezonesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones""","""controllers.TimezonesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/sort/$ids<[^/]+>""","""controllers.TimezonesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/$id<[^/]+>""","""controllers.TimezonesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/$id<[^/]+>""","""controllers.TimezonesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """timezones/$id<[^/]+>/delete""","""controllers.TimezonesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/list""","""controllers.DevicesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/lsort""","""controllers.DevicesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/blank""","""controllers.DevicesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices""","""controllers.DevicesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/sort/$ids<[^/]+>""","""controllers.DevicesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/$id<[^/]+>""","""controllers.DevicesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/$id<[^/]+>""","""controllers.DevicesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """devices/$id<[^/]+>/delete""","""controllers.DevicesView.delete(id:Integer)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Assets_versioned0_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(controllers.Assets.versioned(path, file))
   }
}
        

// @LINE:10
case controllers_Upstream_sendClientEvent1_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_Upstream_sendClientEvent1_invoker.call(controllers.Upstream.sendClientEvent(id))
   }
}
        

// @LINE:11
case controllers_Upstream_resetUpstreamPass2_route(params) => {
   call { 
        controllers_Upstream_resetUpstreamPass2_invoker.call(controllers.Upstream.resetUpstreamPass())
   }
}
        

// @LINE:14
case controllers_Upstream_upstreamFakeCreate3_route(params) => {
   call { 
        controllers_Upstream_upstreamFakeCreate3_invoker.call(controllers.Upstream.upstreamFakeCreate())
   }
}
        

// @LINE:15
case controllers_Upstream_upstreamFakeLogin4_route(params) => {
   call { 
        controllers_Upstream_upstreamFakeLogin4_invoker.call(controllers.Upstream.upstreamFakeLogin())
   }
}
        

// @LINE:16
case controllers_Upstream_upstreamFakeStatus5_route(params) => {
   call { 
        controllers_Upstream_upstreamFakeStatus5_invoker.call(controllers.Upstream.upstreamFakeStatus())
   }
}
        

// @LINE:17
case controllers_Upstream_upstreamFakeResetPass6_route(params) => {
   call { 
        controllers_Upstream_upstreamFakeResetPass6_invoker.call(controllers.Upstream.upstreamFakeResetPass())
   }
}
        

// @LINE:18
case controllers_Upstream_upstreamFakeEventSend7_route(params) => {
   call { 
        controllers_Upstream_upstreamFakeEventSend7_invoker.call(controllers.Upstream.upstreamFakeEventSend())
   }
}
        

// @LINE:23
case controllers_client_Clients_create8_route(params) => {
   call { 
        controllers_client_Clients_create8_invoker.call(controllers.client.Clients.create())
   }
}
        

// @LINE:24
case controllers_client_Clients_update9_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_update9_invoker.call(controllers.client.Clients.update(id))
   }
}
        

// @LINE:25
case controllers_client_Clients_delete10_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_delete10_invoker.call(controllers.client.Clients.delete(id))
   }
}
        

// @LINE:26
case controllers_client_Clients_get11_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[String]("upstreamChannel", None), params.fromQuery[Boolean]("pmc", Some(false))) { (id, upstreamChannel, pmc) =>
        controllers_client_Clients_get11_invoker.call(controllers.client.Clients.get(id, upstreamChannel, pmc))
   }
}
        

// @LINE:27
case controllers_client_Clients_list12_route(params) => {
   call(params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[Boolean]("pmc", Some(false))) { (pageSize, page, pmc) =>
        controllers_client_Clients_list12_invoker.call(controllers.client.Clients.list(pageSize, page, pmc))
   }
}
        

// @LINE:30
case controllers_client_Clients_get13_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[String]("upstreamChannel", Some("Android")), params.fromQuery[Boolean]("pmc", Some(true))) { (id, upstreamChannel, pmc) =>
        controllers_client_Clients_get13_invoker.call(controllers.client.Clients.get(id, upstreamChannel, pmc))
   }
}
        

// @LINE:31
case controllers_client_Clients_list14_route(params) => {
   call(params.fromPath[Integer]("pageSize", None), params.fromPath[Integer]("page", None), params.fromQuery[Boolean]("pmc", Some(true))) { (pageSize, page, pmc) =>
        controllers_client_Clients_list14_invoker.call(controllers.client.Clients.list(pageSize, page, pmc))
   }
}
        

// @LINE:32
case controllers_client_Clients_cleanDevices15_route(params) => {
   call { 
        controllers_client_Clients_cleanDevices15_invoker.call(controllers.client.Clients.cleanDevices())
   }
}
        

// @LINE:35
case controllers_client_Clients_create16_route(params) => {
   call { 
        controllers_client_Clients_create16_invoker.call(controllers.client.Clients.create())
   }
}
        

// @LINE:36
case controllers_client_Clients_get17_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[String]("upstreamChannel", Some("Android")), params.fromQuery[Boolean]("pmc", Some(false))) { (id, upstreamChannel, pmc) =>
        controllers_client_Clients_get17_invoker.call(controllers.client.Clients.get(id, upstreamChannel, pmc))
   }
}
        

// @LINE:37
case controllers_client_Clients_update18_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_update18_invoker.call(controllers.client.Clients.update(id))
   }
}
        

// @LINE:38
case controllers_client_Clients_delete19_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_delete19_invoker.call(controllers.client.Clients.delete(id))
   }
}
        

// @LINE:42
case controllers_CountriesView_list20_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_CountriesView_list20_invoker.call(controllers.CountriesView.list(p, s, o, f))
   }
}
        

// @LINE:43
case controllers_CountriesView_lsort21_route(params) => {
   call { 
        controllers_CountriesView_lsort21_invoker.call(controllers.CountriesView.lsort())
   }
}
        

// @LINE:45
case controllers_CountriesView_blank22_route(params) => {
   call { 
        controllers_CountriesView_blank22_invoker.call(controllers.CountriesView.blank())
   }
}
        

// @LINE:46
case controllers_CountriesView_submit23_route(params) => {
   call { 
        controllers_CountriesView_submit23_invoker.call(controllers.CountriesView.submit())
   }
}
        

// @LINE:48
case controllers_CountriesView_sort24_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_CountriesView_sort24_invoker.call(controllers.CountriesView.sort(ids))
   }
}
        

// @LINE:49
case controllers_CountriesView_edit25_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_edit25_invoker.call(controllers.CountriesView.edit(id))
   }
}
        

// @LINE:50
case controllers_CountriesView_update26_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_update26_invoker.call(controllers.CountriesView.update(id))
   }
}
        

// @LINE:52
case controllers_CountriesView_delete27_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_delete27_invoker.call(controllers.CountriesView.delete(id))
   }
}
        

// @LINE:56
case controllers_LanguagesView_list28_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_LanguagesView_list28_invoker.call(controllers.LanguagesView.list(p, s, o, f))
   }
}
        

// @LINE:57
case controllers_LanguagesView_lsort29_route(params) => {
   call { 
        controllers_LanguagesView_lsort29_invoker.call(controllers.LanguagesView.lsort())
   }
}
        

// @LINE:59
case controllers_LanguagesView_blank30_route(params) => {
   call { 
        controllers_LanguagesView_blank30_invoker.call(controllers.LanguagesView.blank())
   }
}
        

// @LINE:60
case controllers_LanguagesView_submit31_route(params) => {
   call { 
        controllers_LanguagesView_submit31_invoker.call(controllers.LanguagesView.submit())
   }
}
        

// @LINE:62
case controllers_LanguagesView_sort32_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_LanguagesView_sort32_invoker.call(controllers.LanguagesView.sort(ids))
   }
}
        

// @LINE:63
case controllers_LanguagesView_edit33_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_edit33_invoker.call(controllers.LanguagesView.edit(id))
   }
}
        

// @LINE:64
case controllers_LanguagesView_update34_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_update34_invoker.call(controllers.LanguagesView.update(id))
   }
}
        

// @LINE:66
case controllers_LanguagesView_delete35_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_delete35_invoker.call(controllers.LanguagesView.delete(id))
   }
}
        

// @LINE:70
case controllers_TimezonesView_list36_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_TimezonesView_list36_invoker.call(controllers.TimezonesView.list(p, s, o, f))
   }
}
        

// @LINE:71
case controllers_TimezonesView_lsort37_route(params) => {
   call { 
        controllers_TimezonesView_lsort37_invoker.call(controllers.TimezonesView.lsort())
   }
}
        

// @LINE:73
case controllers_TimezonesView_blank38_route(params) => {
   call { 
        controllers_TimezonesView_blank38_invoker.call(controllers.TimezonesView.blank())
   }
}
        

// @LINE:74
case controllers_TimezonesView_submit39_route(params) => {
   call { 
        controllers_TimezonesView_submit39_invoker.call(controllers.TimezonesView.submit())
   }
}
        

// @LINE:76
case controllers_TimezonesView_sort40_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_TimezonesView_sort40_invoker.call(controllers.TimezonesView.sort(ids))
   }
}
        

// @LINE:77
case controllers_TimezonesView_edit41_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_edit41_invoker.call(controllers.TimezonesView.edit(id))
   }
}
        

// @LINE:78
case controllers_TimezonesView_update42_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_update42_invoker.call(controllers.TimezonesView.update(id))
   }
}
        

// @LINE:80
case controllers_TimezonesView_delete43_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_delete43_invoker.call(controllers.TimezonesView.delete(id))
   }
}
        

// @LINE:84
case controllers_DevicesView_list44_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_DevicesView_list44_invoker.call(controllers.DevicesView.list(p, s, o, f))
   }
}
        

// @LINE:85
case controllers_DevicesView_lsort45_route(params) => {
   call { 
        controllers_DevicesView_lsort45_invoker.call(controllers.DevicesView.lsort())
   }
}
        

// @LINE:87
case controllers_DevicesView_blank46_route(params) => {
   call { 
        controllers_DevicesView_blank46_invoker.call(controllers.DevicesView.blank())
   }
}
        

// @LINE:88
case controllers_DevicesView_submit47_route(params) => {
   call { 
        controllers_DevicesView_submit47_invoker.call(controllers.DevicesView.submit())
   }
}
        

// @LINE:90
case controllers_DevicesView_sort48_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_DevicesView_sort48_invoker.call(controllers.DevicesView.sort(ids))
   }
}
        

// @LINE:91
case controllers_DevicesView_edit49_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_edit49_invoker.call(controllers.DevicesView.edit(id))
   }
}
        

// @LINE:92
case controllers_DevicesView_update50_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_update50_invoker.call(controllers.DevicesView.update(id))
   }
}
        

// @LINE:94
case controllers_DevicesView_delete51_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_delete51_invoker.call(controllers.DevicesView.delete(id))
   }
}
        
}

}
     