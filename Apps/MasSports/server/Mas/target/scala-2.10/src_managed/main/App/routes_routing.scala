// @SOURCE:/Users/palenge/Hecticus/TIM_Naty/TimPanama/conf/App.routes
// @HASH:e39ba4e8bf7b621da3cfa49dd8d48b8ed0cee806
// @DATE:Mon May 30 11:03:11 VET 2016
package App

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
  List[(String,Routes)](("jobcore",JobCore.Routes),("upstream",UpstreamAPIConnector.Routes)).foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:7
private[this] lazy val controllers_Application_options0_route = Route("OPTIONS", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),DynamicPart("url", """.+""",false))))
private[this] lazy val controllers_Application_options0_invoker = createInvoker(
controllers.Application.options(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "options", Seq(classOf[String]),"OPTIONS", """""", Routes.prefix + """$url<.+>"""))
        

// @LINE:9
private[this] lazy val controllers_Wap_index1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Wap_index1_invoker = createInvoker(
controllers.Wap.index(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "index", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:10
private[this] lazy val controllers_Wap_index2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("index"))))
private[this] lazy val controllers_Wap_index2_invoker = createInvoker(
controllers.Wap.index(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "index", Nil,"GET", """""", Routes.prefix + """index"""))
        

// @LINE:11
private[this] lazy val controllers_Wap_news3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_Wap_news3_invoker = createInvoker(
controllers.Wap.news(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "news", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """news/$id<[^/]+>"""))
        

// @LINE:12
private[this] lazy val controllers_Wap_matches4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("matches"))))
private[this] lazy val controllers_Wap_matches4_invoker = createInvoker(
controllers.Wap.matches(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "matches", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """matches"""))
        

// @LINE:13
private[this] lazy val controllers_Wap_competitions5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("competitions"))))
private[this] lazy val controllers_Wap_competitions5_invoker = createInvoker(
controllers.Wap.competitions(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "competitions", Seq(classOf[String]),"GET", """""", Routes.prefix + """competitions"""))
        

// @LINE:14
private[this] lazy val controllers_Wap_scorers6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("scorers"))))
private[this] lazy val controllers_Wap_scorers6_invoker = createInvoker(
controllers.Wap.scorers(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "scorers", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """scorers"""))
        

// @LINE:15
private[this] lazy val controllers_Wap_mtm7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("mtm"))))
private[this] lazy val controllers_Wap_mtm7_invoker = createInvoker(
controllers.Wap.mtm(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "mtm", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """mtm"""))
        

// @LINE:16
private[this] lazy val controllers_Wap_getLogin8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
private[this] lazy val controllers_Wap_getLogin8_invoker = createInvoker(
controllers.Wap.getLogin(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "getLogin", Nil,"GET", """""", Routes.prefix + """login"""))
        

// @LINE:17
private[this] lazy val controllers_Wap_getPassword9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login/password"))))
private[this] lazy val controllers_Wap_getPassword9_invoker = createInvoker(
controllers.Wap.getPassword(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "getPassword", Nil,"POST", """""", Routes.prefix + """login/password"""))
        

// @LINE:18
private[this] lazy val controllers_Wap_createClient10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login/create"))))
private[this] lazy val controllers_Wap_createClient10_invoker = createInvoker(
controllers.Wap.createClient(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Wap", "createClient", Nil,"POST", """""", Routes.prefix + """login/create"""))
        

// @LINE:20
private[this] lazy val controllers_Application_checkFile11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("check/"),DynamicPart("name", """.+""",false))))
private[this] lazy val controllers_Application_checkFile11_invoker = createInvoker(
controllers.Application.checkFile(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "checkFile", Seq(classOf[String]),"GET", """""", Routes.prefix + """check/$name<.+>"""))
        

// @LINE:21
private[this] lazy val controllers_Application_getAppSettings12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/loading/"),DynamicPart("width", """[^/]+""",true),StaticPart("/"),DynamicPart("height", """[^/]+""",true),StaticPart("/"),DynamicPart("version", """[^/]+""",true),StaticPart("/"),DynamicPart("so", """[^/]+""",true))))
private[this] lazy val controllers_Application_getAppSettings12_invoker = createInvoker(
controllers.Application.getAppSettings(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "getAppSettings", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """api/loading/$width<[^/]+>/$height<[^/]+>/$version<[^/]+>/$so<[^/]+>"""))
        

// @LINE:30
private[this] lazy val controllers_Assets_at13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at13_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:35
private[this] lazy val controllers_client_FootballClients_createKrakenweb14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/krakencreateweb/"),DynamicPart("msisdn", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_createKrakenweb14_invoker = createInvoker(
controllers.client.FootballClients.createKrakenweb(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "createKrakenweb", Seq(classOf[String]),"GET", """Clients""", Routes.prefix + """sportsapi/v1/clients/krakencreateweb/$msisdn<[^/]+>"""))
        

// @LINE:36
private[this] lazy val controllers_client_FootballClients_createKraken15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/krakencreate/"),DynamicPart("msisdn", """[^/]+""",true),StaticPart("/"),DynamicPart("passwd", """[^/]+""",true),StaticPart("/"),DynamicPart("usd", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_createKraken15_invoker = createInvoker(
controllers.client.FootballClients.createKraken(fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "createKraken", Seq(classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/krakencreate/$msisdn<[^/]+>/$passwd<[^/]+>/$usd<[^/]+>"""))
        

// @LINE:37
private[this] lazy val controllers_client_FootballClients_downKraken16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/krakendown/"),DynamicPart("msisdn", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_downKraken16_invoker = createInvoker(
controllers.client.FootballClients.downKraken(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "downKraken", Seq(classOf[String]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/krakendown/$msisdn<[^/]+>"""))
        

// @LINE:38
private[this] lazy val controllers_client_FootballClients_create17_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/create"))))
private[this] lazy val controllers_client_FootballClients_create17_invoker = createInvoker(
controllers.client.FootballClients.create(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "create", Nil,"POST", """""", Routes.prefix + """sportsapi/v1/clients/create"""))
        

// @LINE:39
private[this] lazy val controllers_client_FootballClients_update18_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/update/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_update18_invoker = createInvoker(
controllers.client.FootballClients.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v1/clients/update/$id<[^/]+>"""))
        

// @LINE:40
private[this] lazy val controllers_client_Clients_delete19_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/delete/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_delete19_invoker = createInvoker(
controllers.client.Clients.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.Clients", "delete", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v1/clients/delete/$id<[^/]+>"""))
        

// @LINE:46
private[this] lazy val controllers_client_FootballClients_get20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/get/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("upstreamChannel", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_get20_invoker = createInvoker(
controllers.client.FootballClients.get(fakeValue[Integer], fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>"""))
        

// @LINE:47
private[this] lazy val controllers_client_Clients_list21_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/list"))))
private[this] lazy val controllers_client_Clients_list21_invoker = createInvoker(
controllers.client.Clients.list(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/list"""))
        

// @LINE:48
private[this] lazy val controllers_client_FootballClients_getPushAlertsForClient22_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/alerts/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getPushAlertsForClient22_invoker = createInvoker(
controllers.client.FootballClients.getPushAlertsForClient(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPushAlertsForClient", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/alerts/$id<[^/]+>"""))
        

// @LINE:49
private[this] lazy val controllers_client_FootballClients_getBetsForCompetition23_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/bets/get/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getBetsForCompetition23_invoker = createInvoker(
controllers.client.FootballClients.getBetsForCompetition(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getBetsForCompetition", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/bets/get/$id<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:50
private[this] lazy val controllers_client_FootballClients_getBetsForDate24_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/bets/get/date/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getBetsForDate24_invoker = createInvoker(
controllers.client.FootballClients.getBetsForDate(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getBetsForDate", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/bets/get/date/$id<[^/]+>/$date<[^/]+>"""))
        

// @LINE:51
private[this] lazy val controllers_client_FootballClients_getBets25_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/bets/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getBets25_invoker = createInvoker(
controllers.client.FootballClients.getBets(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getBets", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/bets/get/$id<[^/]+>"""))
        

// @LINE:52
private[this] lazy val controllers_client_FootballClients_createBets26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/bets/create/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_createBets26_invoker = createInvoker(
controllers.client.FootballClients.createBets(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "createBets", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v1/clients/bets/create/$id<[^/]+>"""))
        

// @LINE:53
private[this] lazy val controllers_client_FootballClients_dashboard27_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/dashboard/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_dashboard27_invoker = createInvoker(
controllers.client.FootballClients.dashboard(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "dashboard", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/dashboard/$id<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:56
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient28_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/leaderboard/get/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("idTournament", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient28_invoker = createInvoker(
controllers.client.FootballClients.getLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/leaderboard/get/$id<[^/]+>/$idTournament<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:57
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient29_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/leaderboard/global/get/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("idTournament", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient29_invoker = createInvoker(
controllers.client.FootballClients.getLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/leaderboard/global/get/$id<[^/]+>/$idTournament<[^/]+>"""))
        

// @LINE:58
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient30_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/leaderboard/personal/tournament/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient30_invoker = createInvoker(
controllers.client.FootballClients.getPersonalLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPersonalLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/leaderboard/personal/tournament/$id<[^/]+>"""))
        

// @LINE:59
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient31_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/leaderboard/personal/phase/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient31_invoker = createInvoker(
controllers.client.FootballClients.getPersonalLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPersonalLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/leaderboard/personal/phase/$id<[^/]+>"""))
        

// @LINE:60
private[this] lazy val controllers_client_FootballClients_getLeaderboardTotalForClient32_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/leaderboard/total/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getLeaderboardTotalForClient32_invoker = createInvoker(
controllers.client.FootballClients.getLeaderboardTotalForClient(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getLeaderboardTotalForClient", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/leaderboard/total/$id<[^/]+>"""))
        

// @LINE:63
private[this] lazy val controllers_client_FootballManager_getScorers33_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/clients/scorers/"),DynamicPart("idClient", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getScorers33_invoker = createInvoker(
controllers.client.FootballManager.getScorers(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getScorers", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v1/clients/scorers/$idClient<[^/]+>"""))
        

// @LINE:66
private[this] lazy val controllers_client_Clients_getActiveLanguages34_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/languages"))))
private[this] lazy val controllers_client_Clients_getActiveLanguages34_invoker = createInvoker(
controllers.client.Clients.getActiveLanguages(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.Clients", "getActiveLanguages", Nil,"GET", """""", Routes.prefix + """sportsapi/v1/languages"""))
        

// @LINE:71
private[this] lazy val controllers_ImageProcessing_getLocalResourceByWidth35_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v1/localimages/"),DynamicPart("file", """[^/]+""",true),StaticPart("/"),DynamicPart("width", """[^/]+""",true))))
private[this] lazy val controllers_ImageProcessing_getLocalResourceByWidth35_invoker = createInvoker(
controllers.ImageProcessing.getLocalResourceByWidth(fakeValue[String], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ImageProcessing", "getLocalResourceByWidth", Seq(classOf[String], classOf[Integer]),"GET", """funciones para obtener imagenes locales
GET     /sportsapi/v1/localimages/:file/:size                                                           controllers.ImageProcessing.getLocalResourceBySize(file : String, size : String)
imagen width and height""", Routes.prefix + """sportsapi/v1/localimages/$file<[^/]+>/$width<[^/]+>"""))
        

// @LINE:77
private[this] lazy val controllers_client_FootballClients_create36_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client"))))
private[this] lazy val controllers_client_FootballClients_create36_invoker = createInvoker(
controllers.client.FootballClients.create(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "create", Nil,"POST", """Clients""", Routes.prefix + """sportsapi/v2/client"""))
        

// @LINE:78
private[this] lazy val controllers_client_FootballClients_get37_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_get37_invoker = createInvoker(
controllers.client.FootballClients.get(fakeValue[Integer], fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>"""))
        

// @LINE:79
private[this] lazy val controllers_client_FootballClients_update38_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_update38_invoker = createInvoker(
controllers.client.FootballClients.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>"""))
        

// @LINE:80
private[this] lazy val controllers_client_Clients_delete39_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_Clients_delete39_invoker = createInvoker(
controllers.client.Clients.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.Clients", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>"""))
        

// @LINE:81
private[this] lazy val controllers_client_FootballClients_getPushAlertsForClient40_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/alerts"))))
private[this] lazy val controllers_client_FootballClients_getPushAlertsForClient40_invoker = createInvoker(
controllers.client.FootballClients.getPushAlertsForClient(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPushAlertsForClient", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/alerts"""))
        

// @LINE:82
private[this] lazy val controllers_client_FootballClients_createBet41_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/bet"))))
private[this] lazy val controllers_client_FootballClients_createBet41_invoker = createInvoker(
controllers.client.FootballClients.createBet(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "createBet", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/bet"""))
        

// @LINE:83
private[this] lazy val controllers_client_FootballClients_createBets42_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/bets"))))
private[this] lazy val controllers_client_FootballClients_createBets42_invoker = createInvoker(
controllers.client.FootballClients.createBets(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "createBets", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/bets"""))
        

// @LINE:84
private[this] lazy val controllers_client_FootballClients_getBets43_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/bets"))))
private[this] lazy val controllers_client_FootballClients_getBets43_invoker = createInvoker(
controllers.client.FootballClients.getBets(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getBets", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/bets"""))
        

// @LINE:85
private[this] lazy val controllers_client_FootballClients_getBetsForCompetition44_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/bets/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getBetsForCompetition44_invoker = createInvoker(
controllers.client.FootballClients.getBetsForCompetition(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getBetsForCompetition", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/bets/$idCompetition<[^/]+>"""))
        

// @LINE:86
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient45_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/leaderboard/global/"),DynamicPart("idTournament", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballClients_getLeaderboardForClient45_invoker = createInvoker(
controllers.client.FootballClients.getLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/leaderboard/global/$idTournament<[^/]+>"""))
        

// @LINE:87
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient46_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/leaderboard/personal/tournament"))))
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient46_invoker = createInvoker(
controllers.client.FootballClients.getPersonalLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPersonalLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/leaderboard/personal/tournament"""))
        

// @LINE:88
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient47_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/leaderboard/personal/phase"))))
private[this] lazy val controllers_client_FootballClients_getPersonalLeaderboardForClient47_invoker = createInvoker(
controllers.client.FootballClients.getPersonalLeaderboardForClient(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getPersonalLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/leaderboard/personal/phase"""))
        

// @LINE:89
private[this] lazy val controllers_Upstream_sendClientEvent48_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/client/"),DynamicPart("id", """[^/]+""",true),StaticPart("/upstream"))))
private[this] lazy val controllers_Upstream_sendClientEvent48_invoker = createInvoker(
controllers.Upstream.sendClientEvent(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Upstream", "sendClientEvent", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """sportsapi/v2/client/$id<[^/]+>/upstream"""))
        

// @LINE:90
private[this] lazy val controllers_client_Clients_list49_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/clients"))))
private[this] lazy val controllers_client_Clients_list49_invoker = createInvoker(
controllers.client.Clients.list(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]),"GET", """""", Routes.prefix + """sportsapi/v2/clients"""))
        

// @LINE:92
private[this] lazy val controllers_client_FootballClients_getLocale50_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/locale.json"))))
private[this] lazy val controllers_client_FootballClients_getLocale50_invoker = createInvoker(
controllers.client.FootballClients.getLocale(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "getLocale", Seq(classOf[String]),"GET", """""", Routes.prefix + """sportsapi/v2/locale.json"""))
        

// @LINE:93
private[this] lazy val controllers_client_FootballClients_setLocale51_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sportsapi/v2/locale.json"))))
private[this] lazy val controllers_client_FootballClients_setLocale51_invoker = createInvoker(
controllers.client.FootballClients.setLocale(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballClients", "setLocale", Seq(classOf[String]),"POST", """""", Routes.prefix + """sportsapi/v2/locale.json"""))
        

// @LINE:96
private[this] lazy val controllers_Application_index52_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin"))))
private[this] lazy val controllers_Application_index52_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """admin"""))
        

// @LINE:98
private[this] lazy val controllers_Application_restricted53_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/restricted"))))
private[this] lazy val controllers_Application_restricted53_invoker = createInvoker(
controllers.Application.restricted,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "restricted", Nil,"GET", """ Authenticate""", Routes.prefix + """admin/restricted"""))
        

// @LINE:99
private[this] lazy val controllers_Application_jsRoutes54_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/assets/javascript/routes.js"))))
private[this] lazy val controllers_Application_jsRoutes54_invoker = createInvoker(
controllers.Application.jsRoutes,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "jsRoutes", Nil,"GET", """""", Routes.prefix + """admin/assets/javascript/routes.js"""))
        

// @LINE:101
private[this] lazy val controllers_Application_profile55_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/profile"))))
private[this] lazy val controllers_Application_profile55_invoker = createInvoker(
controllers.Application.profile,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "profile", Nil,"GET", """""", Routes.prefix + """admin/profile"""))
        

// @LINE:103
private[this] lazy val controllers_Application_login56_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login"))))
private[this] lazy val controllers_Application_login56_invoker = createInvoker(
controllers.Application.login,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "login", Nil,"GET", """""", Routes.prefix + """admin/login"""))
        

// @LINE:104
private[this] lazy val controllers_Application_doLogin57_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login"))))
private[this] lazy val controllers_Application_doLogin57_invoker = createInvoker(
controllers.Application.doLogin,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "doLogin", Nil,"POST", """""", Routes.prefix + """admin/login"""))
        

// @LINE:106
private[this] lazy val com_feth_play_module_pa_controllers_Authenticate_logout58_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/logout"))))
private[this] lazy val com_feth_play_module_pa_controllers_Authenticate_logout58_invoker = createInvoker(
com.feth.play.module.pa.controllers.Authenticate.logout,
HandlerDef(this.getClass.getClassLoader, "App", "com.feth.play.module.pa.controllers.Authenticate", "logout", Nil,"GET", """""", Routes.prefix + """admin/logout"""))
        

// @LINE:107
private[this] lazy val com_feth_play_module_pa_controllers_Authenticate_authenticate59_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/authenticate/"),DynamicPart("provider", """[^/]+""",true))))
private[this] lazy val com_feth_play_module_pa_controllers_Authenticate_authenticate59_invoker = createInvoker(
com.feth.play.module.pa.controllers.Authenticate.authenticate(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "com.feth.play.module.pa.controllers.Authenticate", "authenticate", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/authenticate/$provider<[^/]+>"""))
        

// @LINE:109
private[this] lazy val controllers_Application_signup60_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/signup"))))
private[this] lazy val controllers_Application_signup60_invoker = createInvoker(
controllers.Application.signup,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "signup", Nil,"GET", """""", Routes.prefix + """admin/signup"""))
        

// @LINE:110
private[this] lazy val controllers_Application_doSignup61_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/signup"))))
private[this] lazy val controllers_Application_doSignup61_invoker = createInvoker(
controllers.Application.doSignup,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Application", "doSignup", Nil,"POST", """""", Routes.prefix + """admin/signup"""))
        

// @LINE:112
private[this] lazy val controllers_Signup_unverified62_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/unverified"))))
private[this] lazy val controllers_Signup_unverified62_invoker = createInvoker(
controllers.Signup.unverified,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "unverified", Nil,"GET", """""", Routes.prefix + """admin/accounts/unverified"""))
        

// @LINE:113
private[this] lazy val controllers_Signup_oAuthDenied63_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/authenticate/"),DynamicPart("provider", """[^/]+""",true),StaticPart("/denied"))))
private[this] lazy val controllers_Signup_oAuthDenied63_invoker = createInvoker(
controllers.Signup.oAuthDenied(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "oAuthDenied", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/authenticate/$provider<[^/]+>/denied"""))
        

// @LINE:115
private[this] lazy val controllers_Signup_verify64_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/verify/"),DynamicPart("token", """[^/]+""",true))))
private[this] lazy val controllers_Signup_verify64_invoker = createInvoker(
controllers.Signup.verify(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "verify", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/accounts/verify/$token<[^/]+>"""))
        

// @LINE:116
private[this] lazy val controllers_Signup_exists65_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/exists"))))
private[this] lazy val controllers_Signup_exists65_invoker = createInvoker(
controllers.Signup.exists,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "exists", Nil,"GET", """""", Routes.prefix + """admin/accounts/exists"""))
        

// @LINE:118
private[this] lazy val controllers_Signup_resetPassword66_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/password/reset/"),DynamicPart("token", """[^/]+""",true))))
private[this] lazy val controllers_Signup_resetPassword66_invoker = createInvoker(
controllers.Signup.resetPassword(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "resetPassword", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/accounts/password/reset/$token<[^/]+>"""))
        

// @LINE:119
private[this] lazy val controllers_Signup_doResetPassword67_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/password/reset"))))
private[this] lazy val controllers_Signup_doResetPassword67_invoker = createInvoker(
controllers.Signup.doResetPassword,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "doResetPassword", Nil,"POST", """""", Routes.prefix + """admin/accounts/password/reset"""))
        

// @LINE:121
private[this] lazy val controllers_Account_changePassword68_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/password/change"))))
private[this] lazy val controllers_Account_changePassword68_invoker = createInvoker(
controllers.Account.changePassword,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "changePassword", Nil,"GET", """""", Routes.prefix + """admin/accounts/password/change"""))
        

// @LINE:122
private[this] lazy val controllers_Account_doChangePassword69_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/password/change"))))
private[this] lazy val controllers_Account_doChangePassword69_invoker = createInvoker(
controllers.Account.doChangePassword,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "doChangePassword", Nil,"POST", """""", Routes.prefix + """admin/accounts/password/change"""))
        

// @LINE:124
private[this] lazy val controllers_Account_verifyEmail70_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/verify"))))
private[this] lazy val controllers_Account_verifyEmail70_invoker = createInvoker(
controllers.Account.verifyEmail,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "verifyEmail", Nil,"GET", """""", Routes.prefix + """admin/accounts/verify"""))
        

// @LINE:126
private[this] lazy val controllers_Account_link71_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/add"))))
private[this] lazy val controllers_Account_link71_invoker = createInvoker(
controllers.Account.link,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "link", Nil,"GET", """""", Routes.prefix + """admin/accounts/add"""))
        

// @LINE:128
private[this] lazy val controllers_Account_askLink72_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/link"))))
private[this] lazy val controllers_Account_askLink72_invoker = createInvoker(
controllers.Account.askLink,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "askLink", Nil,"GET", """""", Routes.prefix + """admin/accounts/link"""))
        

// @LINE:129
private[this] lazy val controllers_Account_doLink73_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("accounts/link"))))
private[this] lazy val controllers_Account_doLink73_invoker = createInvoker(
controllers.Account.doLink,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "doLink", Nil,"POST", """""", Routes.prefix + """accounts/link"""))
        

// @LINE:131
private[this] lazy val controllers_Account_askMerge74_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/merge"))))
private[this] lazy val controllers_Account_askMerge74_invoker = createInvoker(
controllers.Account.askMerge,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "askMerge", Nil,"GET", """""", Routes.prefix + """admin/accounts/merge"""))
        

// @LINE:132
private[this] lazy val controllers_Account_doMerge75_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/accounts/merge"))))
private[this] lazy val controllers_Account_doMerge75_invoker = createInvoker(
controllers.Account.doMerge,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Account", "doMerge", Nil,"POST", """""", Routes.prefix + """admin/accounts/merge"""))
        

// @LINE:134
private[this] lazy val controllers_Signup_forgotPassword76_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login/password/forgot"))))
private[this] lazy val controllers_Signup_forgotPassword76_invoker = createInvoker(
controllers.Signup.forgotPassword(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "forgotPassword", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/login/password/forgot"""))
        

// @LINE:135
private[this] lazy val controllers_Signup_doForgotPassword77_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login/password/forgot"))))
private[this] lazy val controllers_Signup_doForgotPassword77_invoker = createInvoker(
controllers.Signup.doForgotPassword,
HandlerDef(this.getClass.getClassLoader, "App", "controllers.Signup", "doForgotPassword", Nil,"POST", """""", Routes.prefix + """admin/login/password/forgot"""))
        

// @LINE:139
private[this] lazy val controllers_CountriesView_list78_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/list"))))
private[this] lazy val controllers_CountriesView_list78_invoker = createInvoker(
controllers.CountriesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """CountriesView
 CountriesView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/countries/list"""))
        

// @LINE:140
private[this] lazy val controllers_CountriesView_lsort79_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/lsort"))))
private[this] lazy val controllers_CountriesView_lsort79_invoker = createInvoker(
controllers.CountriesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/countries/lsort"""))
        

// @LINE:142
private[this] lazy val controllers_CountriesView_blank80_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/blank"))))
private[this] lazy val controllers_CountriesView_blank80_invoker = createInvoker(
controllers.CountriesView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "blank", Nil,"GET", """ Add CountriesView""", Routes.prefix + """admin/countries/blank"""))
        

// @LINE:143
private[this] lazy val controllers_CountriesView_submit81_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries"))))
private[this] lazy val controllers_CountriesView_submit81_invoker = createInvoker(
controllers.CountriesView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "submit", Nil,"POST", """""", Routes.prefix + """admin/countries"""))
        

// @LINE:145
private[this] lazy val controllers_CountriesView_sort82_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_sort82_invoker = createInvoker(
controllers.CountriesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "sort", Seq(classOf[String]),"GET", """ Edit existing CountriesView""", Routes.prefix + """admin/countries/sort/$ids<[^/]+>"""))
        

// @LINE:146
private[this] lazy val controllers_CountriesView_edit83_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_edit83_invoker = createInvoker(
controllers.CountriesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """admin/countries/$id<[^/]+>"""))
        

// @LINE:147
private[this] lazy val controllers_CountriesView_update84_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_CountriesView_update84_invoker = createInvoker(
controllers.CountriesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """admin/countries/$id<[^/]+>"""))
        

// @LINE:149
private[this] lazy val controllers_CountriesView_delete85_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/countries/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_CountriesView_delete85_invoker = createInvoker(
controllers.CountriesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.CountriesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a CountriesView""", Routes.prefix + """admin/countries/$id<[^/]+>/delete"""))
        

// @LINE:153
private[this] lazy val controllers_LanguagesView_list86_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/list"))))
private[this] lazy val controllers_LanguagesView_list86_invoker = createInvoker(
controllers.LanguagesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """LanguagesView
 LanguagesView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/languages/list"""))
        

// @LINE:154
private[this] lazy val controllers_LanguagesView_lsort87_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/lsort"))))
private[this] lazy val controllers_LanguagesView_lsort87_invoker = createInvoker(
controllers.LanguagesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/languages/lsort"""))
        

// @LINE:156
private[this] lazy val controllers_LanguagesView_blank88_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/blank"))))
private[this] lazy val controllers_LanguagesView_blank88_invoker = createInvoker(
controllers.LanguagesView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "blank", Nil,"GET", """ Add LanguagesView""", Routes.prefix + """admin/languages/blank"""))
        

// @LINE:157
private[this] lazy val controllers_LanguagesView_submit89_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages"))))
private[this] lazy val controllers_LanguagesView_submit89_invoker = createInvoker(
controllers.LanguagesView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "submit", Nil,"POST", """""", Routes.prefix + """admin/languages"""))
        

// @LINE:159
private[this] lazy val controllers_LanguagesView_sort90_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_sort90_invoker = createInvoker(
controllers.LanguagesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "sort", Seq(classOf[String]),"GET", """ Edit existing LanguagesView""", Routes.prefix + """admin/languages/sort/$ids<[^/]+>"""))
        

// @LINE:160
private[this] lazy val controllers_LanguagesView_edit91_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_edit91_invoker = createInvoker(
controllers.LanguagesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """admin/languages/$id<[^/]+>"""))
        

// @LINE:161
private[this] lazy val controllers_LanguagesView_update92_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_LanguagesView_update92_invoker = createInvoker(
controllers.LanguagesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """admin/languages/$id<[^/]+>"""))
        

// @LINE:163
private[this] lazy val controllers_LanguagesView_delete93_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/languages/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_LanguagesView_delete93_invoker = createInvoker(
controllers.LanguagesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.LanguagesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a LanguagesView""", Routes.prefix + """admin/languages/$id<[^/]+>/delete"""))
        

// @LINE:167
private[this] lazy val controllers_TimezonesView_list94_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/list"))))
private[this] lazy val controllers_TimezonesView_list94_invoker = createInvoker(
controllers.TimezonesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """TimezonesView
 TimezonesView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/timezones/list"""))
        

// @LINE:168
private[this] lazy val controllers_TimezonesView_lsort95_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/lsort"))))
private[this] lazy val controllers_TimezonesView_lsort95_invoker = createInvoker(
controllers.TimezonesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/timezones/lsort"""))
        

// @LINE:170
private[this] lazy val controllers_TimezonesView_blank96_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/blank"))))
private[this] lazy val controllers_TimezonesView_blank96_invoker = createInvoker(
controllers.TimezonesView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "blank", Nil,"GET", """ Add TimezonesView""", Routes.prefix + """admin/timezones/blank"""))
        

// @LINE:171
private[this] lazy val controllers_TimezonesView_submit97_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones"))))
private[this] lazy val controllers_TimezonesView_submit97_invoker = createInvoker(
controllers.TimezonesView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "submit", Nil,"POST", """""", Routes.prefix + """admin/timezones"""))
        

// @LINE:173
private[this] lazy val controllers_TimezonesView_sort98_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_sort98_invoker = createInvoker(
controllers.TimezonesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "sort", Seq(classOf[String]),"GET", """ Edit existing TimezonesView""", Routes.prefix + """admin/timezones/sort/$ids<[^/]+>"""))
        

// @LINE:174
private[this] lazy val controllers_TimezonesView_edit99_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_edit99_invoker = createInvoker(
controllers.TimezonesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """admin/timezones/$id<[^/]+>"""))
        

// @LINE:175
private[this] lazy val controllers_TimezonesView_update100_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_TimezonesView_update100_invoker = createInvoker(
controllers.TimezonesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """admin/timezones/$id<[^/]+>"""))
        

// @LINE:177
private[this] lazy val controllers_TimezonesView_delete101_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/timezones/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_TimezonesView_delete101_invoker = createInvoker(
controllers.TimezonesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.TimezonesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a TimezonesView""", Routes.prefix + """admin/timezones/$id<[^/]+>/delete"""))
        

// @LINE:181
private[this] lazy val controllers_DevicesView_list102_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/list"))))
private[this] lazy val controllers_DevicesView_list102_invoker = createInvoker(
controllers.DevicesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """DevicesView
 DevicesView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/devices/list"""))
        

// @LINE:182
private[this] lazy val controllers_DevicesView_lsort103_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/lsort"))))
private[this] lazy val controllers_DevicesView_lsort103_invoker = createInvoker(
controllers.DevicesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/devices/lsort"""))
        

// @LINE:184
private[this] lazy val controllers_DevicesView_blank104_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/blank"))))
private[this] lazy val controllers_DevicesView_blank104_invoker = createInvoker(
controllers.DevicesView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "blank", Nil,"GET", """ Add DevicesView""", Routes.prefix + """admin/devices/blank"""))
        

// @LINE:185
private[this] lazy val controllers_DevicesView_submit105_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices"))))
private[this] lazy val controllers_DevicesView_submit105_invoker = createInvoker(
controllers.DevicesView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "submit", Nil,"POST", """""", Routes.prefix + """admin/devices"""))
        

// @LINE:187
private[this] lazy val controllers_DevicesView_sort106_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_sort106_invoker = createInvoker(
controllers.DevicesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "sort", Seq(classOf[String]),"GET", """ Edit existing DevicesView""", Routes.prefix + """admin/devices/sort/$ids<[^/]+>"""))
        

// @LINE:188
private[this] lazy val controllers_DevicesView_edit107_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_edit107_invoker = createInvoker(
controllers.DevicesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """admin/devices/$id<[^/]+>"""))
        

// @LINE:189
private[this] lazy val controllers_DevicesView_update108_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_DevicesView_update108_invoker = createInvoker(
controllers.DevicesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """admin/devices/$id<[^/]+>"""))
        

// @LINE:191
private[this] lazy val controllers_DevicesView_delete109_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/devices/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_DevicesView_delete109_invoker = createInvoker(
controllers.DevicesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.DevicesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a DevicesView""", Routes.prefix + """admin/devices/$id<[^/]+>/delete"""))
        

// @LINE:196
private[this] lazy val controllers_InstancesView_list110_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/list"))))
private[this] lazy val controllers_InstancesView_list110_invoker = createInvoker(
controllers.InstancesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """InstancesView
 InstancesView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/instances/list"""))
        

// @LINE:197
private[this] lazy val controllers_InstancesView_lsort111_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/lsort"))))
private[this] lazy val controllers_InstancesView_lsort111_invoker = createInvoker(
controllers.InstancesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/instances/lsort"""))
        

// @LINE:199
private[this] lazy val controllers_InstancesView_blank112_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/blank"))))
private[this] lazy val controllers_InstancesView_blank112_invoker = createInvoker(
controllers.InstancesView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "blank", Nil,"GET", """ Add InstancesView""", Routes.prefix + """admin/instances/blank"""))
        

// @LINE:200
private[this] lazy val controllers_InstancesView_submit113_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances"))))
private[this] lazy val controllers_InstancesView_submit113_invoker = createInvoker(
controllers.InstancesView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "submit", Nil,"POST", """""", Routes.prefix + """admin/instances"""))
        

// @LINE:202
private[this] lazy val controllers_InstancesView_sort114_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_sort114_invoker = createInvoker(
controllers.InstancesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "sort", Seq(classOf[String]),"GET", """ Edit existing InstancesView""", Routes.prefix + """admin/instances/sort/$ids<[^/]+>"""))
        

// @LINE:203
private[this] lazy val controllers_InstancesView_edit115_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_edit115_invoker = createInvoker(
controllers.InstancesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """admin/instances/$id<[^/]+>"""))
        

// @LINE:204
private[this] lazy val controllers_InstancesView_update116_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_update116_invoker = createInvoker(
controllers.InstancesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """admin/instances/$id<[^/]+>"""))
        

// @LINE:206
private[this] lazy val controllers_InstancesView_delete117_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/instances/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_InstancesView_delete117_invoker = createInvoker(
controllers.InstancesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.InstancesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a InstancesView""", Routes.prefix + """admin/instances/$id<[^/]+>/delete"""))
        

// @LINE:210
private[this] lazy val controllers_JobsView_list118_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/list"))))
private[this] lazy val controllers_JobsView_list118_invoker = createInvoker(
controllers.JobsView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """JobsView
 JobsView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/jobs/list"""))
        

// @LINE:211
private[this] lazy val controllers_JobsView_lsort119_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/lsort"))))
private[this] lazy val controllers_JobsView_lsort119_invoker = createInvoker(
controllers.JobsView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/jobs/lsort"""))
        

// @LINE:213
private[this] lazy val controllers_JobsView_blank120_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/blank"))))
private[this] lazy val controllers_JobsView_blank120_invoker = createInvoker(
controllers.JobsView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "blank", Nil,"GET", """ Add JobsView""", Routes.prefix + """admin/jobs/blank"""))
        

// @LINE:214
private[this] lazy val controllers_JobsView_submit121_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs"))))
private[this] lazy val controllers_JobsView_submit121_invoker = createInvoker(
controllers.JobsView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "submit", Nil,"POST", """""", Routes.prefix + """admin/jobs"""))
        

// @LINE:216
private[this] lazy val controllers_JobsView_sort122_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_sort122_invoker = createInvoker(
controllers.JobsView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "sort", Seq(classOf[String]),"GET", """ Edit existing JobsView""", Routes.prefix + """admin/jobs/sort/$ids<[^/]+>"""))
        

// @LINE:217
private[this] lazy val controllers_JobsView_edit123_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_edit123_invoker = createInvoker(
controllers.JobsView.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/jobs/$id<[^/]+>"""))
        

// @LINE:218
private[this] lazy val controllers_JobsView_update124_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_update124_invoker = createInvoker(
controllers.JobsView.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """admin/jobs/$id<[^/]+>"""))
        

// @LINE:220
private[this] lazy val controllers_JobsView_delete125_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/jobs/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_JobsView_delete125_invoker = createInvoker(
controllers.JobsView.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.JobsView", "delete", Seq(classOf[Long]),"POST", """ Delete a JobsView""", Routes.prefix + """admin/jobs/$id<[^/]+>/delete"""))
        

// @LINE:224
private[this] lazy val controllers_ConfigsView_list126_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/list"))))
private[this] lazy val controllers_ConfigsView_list126_invoker = createInvoker(
controllers.ConfigsView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """ConfigsView
 ConfigsView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/configurations/list"""))
        

// @LINE:225
private[this] lazy val controllers_ConfigsView_lsort127_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/lsort"))))
private[this] lazy val controllers_ConfigsView_lsort127_invoker = createInvoker(
controllers.ConfigsView.lsort(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "lsort", Nil,"GET", """""", Routes.prefix + """admin/configurations/lsort"""))
        

// @LINE:227
private[this] lazy val controllers_ConfigsView_blank128_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/blank"))))
private[this] lazy val controllers_ConfigsView_blank128_invoker = createInvoker(
controllers.ConfigsView.blank(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "blank", Nil,"GET", """ Add ConfigsView""", Routes.prefix + """admin/configurations/blank"""))
        

// @LINE:228
private[this] lazy val controllers_ConfigsView_submit129_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations"))))
private[this] lazy val controllers_ConfigsView_submit129_invoker = createInvoker(
controllers.ConfigsView.submit(),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "submit", Nil,"POST", """""", Routes.prefix + """admin/configurations"""))
        

// @LINE:230
private[this] lazy val controllers_ConfigsView_sort130_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_sort130_invoker = createInvoker(
controllers.ConfigsView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "sort", Seq(classOf[String]),"GET", """ Edit existing ConfigsView""", Routes.prefix + """admin/configurations/sort/$ids<[^/]+>"""))
        

// @LINE:231
private[this] lazy val controllers_ConfigsView_edit131_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_edit131_invoker = createInvoker(
controllers.ConfigsView.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/configurations/$id<[^/]+>"""))
        

// @LINE:232
private[this] lazy val controllers_ConfigsView_update132_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_update132_invoker = createInvoker(
controllers.ConfigsView.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """admin/configurations/$id<[^/]+>"""))
        

// @LINE:234
private[this] lazy val controllers_ConfigsView_delete133_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/configurations/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_ConfigsView_delete133_invoker = createInvoker(
controllers.ConfigsView.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.ConfigsView", "delete", Seq(classOf[Long]),"POST", """ Delete a ConfigsView""", Routes.prefix + """admin/configurations/$id<[^/]+>/delete"""))
        

// @LINE:238
private[this] lazy val controllers_UsersView_list134_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users/list"))))
private[this] lazy val controllers_UsersView_list134_invoker = createInvoker(
controllers.UsersView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.UsersView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """UsersView
 UsersView list (look at the default values for pagination parameters)""", Routes.prefix + """admin/users/list"""))
        

// @LINE:243
private[this] lazy val controllers_UsersView_edit135_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_UsersView_edit135_invoker = createInvoker(
controllers.UsersView.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.UsersView", "edit", Seq(classOf[Long]),"GET", """GET     /admin/users/lsort		                                                                        controllers.UsersView.lsort()
 Add UsersView
 Edit existing UsersView
GET     /admin/users/sort/:ids	                                                                        controllers.UsersView.sort(ids:String)""", Routes.prefix + """admin/users/$id<[^/]+>"""))
        

// @LINE:244
private[this] lazy val controllers_UsersView_update136_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_UsersView_update136_invoker = createInvoker(
controllers.UsersView.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.UsersView", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """admin/users/$id<[^/]+>"""))
        

// @LINE:246
private[this] lazy val controllers_UsersView_delete137_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_UsersView_delete137_invoker = createInvoker(
controllers.UsersView.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.UsersView", "delete", Seq(classOf[Long]),"POST", """ Delete a UsersView""", Routes.prefix + """admin/users/$id<[^/]+>/delete"""))
        

// @LINE:250
private[this] lazy val controllers_client_FootballManager_getNewsById138_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/get/"),DynamicPart("idNews", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getNewsById138_invoker = createInvoker(
controllers.client.FootballManager.getNewsById(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getNewsById", Seq(classOf[Long]),"GET", """""", Routes.prefix + """newsapi/v1/news/get/$idNews<[^/]+>"""))
        

// @LINE:251
private[this] lazy val controllers_client_FootballManager_getNews139_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/search/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getNews139_invoker = createInvoker(
controllers.client.FootballManager.getNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getNews", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """newsapi/v1/news/search/$idApp<[^/]+>"""))
        

// @LINE:252
private[this] lazy val controllers_client_FootballManager_getNews140_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/search/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("offset", """[^/]+""",true),StaticPart("/"),DynamicPart("count", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getNews140_invoker = createInvoker(
controllers.client.FootballManager.getNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getNews", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """newsapi/v1/news/search/$idApp<[^/]+>/$offset<[^/]+>/$count<[^/]+>"""))
        

// @LINE:255
private[this] lazy val controllers_client_FootballManager_getRecentNews141_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/up/first/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews141_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """NewsScroll""", Routes.prefix + """newsapi/v1/news/scroll/up/first/$idApp<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:256
private[this] lazy val controllers_client_FootballManager_getRecentNews142_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/up/first/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews142_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/up/first/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:257
private[this] lazy val controllers_client_FootballManager_getRecentNews143_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/up/rest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews143_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/up/rest/$idApp<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:258
private[this] lazy val controllers_client_FootballManager_getRecentNews144_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/up/rest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews144_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/up/rest/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:259
private[this] lazy val controllers_client_FootballManager_getRecentNews145_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/down/first/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews145_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/down/first/$idApp<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:260
private[this] lazy val controllers_client_FootballManager_getRecentNews146_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/down/first/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews146_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/down/first/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:261
private[this] lazy val controllers_client_FootballManager_getRecentNews147_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/down/rest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews147_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/down/rest/$idApp<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:262
private[this] lazy val controllers_client_FootballManager_getRecentNews148_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/down/rest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews148_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/down/rest/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>"""))
        

// @LINE:263
private[this] lazy val controllers_client_FootballManager_getRecentNews149_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews149_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/$idApp<[^/]+>"""))
        

// @LINE:264
private[this] lazy val controllers_client_FootballManager_getRecentNews150_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews150_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/$idApp<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:265
private[this] lazy val controllers_client_FootballManager_getRecentNews151_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true),StaticPart("/"),DynamicPart("newest", """[^/]+""",true),StaticPart("/"),DynamicPart("first", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews151_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/$idApp<[^/]+>/$newsId<[^/]+>/$newest<[^/]+>/$first<[^/]+>"""))
        

// @LINE:266
private[this] lazy val controllers_client_FootballManager_getRecentNews152_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("newsapi/v1/news/scroll/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("newsId", """[^/]+""",true),StaticPart("/"),DynamicPart("newest", """[^/]+""",true),StaticPart("/"),DynamicPart("first", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRecentNews152_invoker = createInvoker(
controllers.client.FootballManager.getRecentNews(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]),"GET", """""", Routes.prefix + """newsapi/v1/news/scroll/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>/$newest<[^/]+>/$first<[^/]+>"""))
        

// @LINE:269
private[this] lazy val controllers_client_FootballManager_getTeams153_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/team/competition/all/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTeams153_invoker = createInvoker(
controllers.client.FootballManager.getTeams(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTeams", Seq(classOf[Long]),"GET", """
#afp futbol""", Routes.prefix + """footballapi/v1/team/competition/all/$idCompetition<[^/]+>"""))
        

// @LINE:270
private[this] lazy val controllers_client_FootballManager_getTeamsForApp154_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/team/app/all/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTeamsForApp154_invoker = createInvoker(
controllers.client.FootballManager.getTeamsForApp(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTeamsForApp", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/team/app/all/$idApp<[^/]+>"""))
        

// @LINE:271
private[this] lazy val controllers_client_FootballManager_getTeam155_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/team/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTeam155_invoker = createInvoker(
controllers.client.FootballManager.getTeam(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTeam", Seq(classOf[Long]),"GET", """""", Routes.prefix + """footballapi/v1/team/get/$id<[^/]+>"""))
        

// @LINE:272
private[this] lazy val controllers_client_FootballManager_getPhases156_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/phase/getList/"),DynamicPart("idComp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPhases156_invoker = createInvoker(
controllers.client.FootballManager.getPhases(fakeValue[Long], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPhases", Seq(classOf[Long], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/phase/getList/$idComp<[^/]+>"""))
        

// @LINE:273
private[this] lazy val controllers_client_FootballManager_getPhase157_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/phase/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPhase157_invoker = createInvoker(
controllers.client.FootballManager.getPhase(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPhase", Seq(classOf[Long]),"GET", """""", Routes.prefix + """footballapi/v1/phase/get/$id<[^/]+>"""))
        

// @LINE:274
private[this] lazy val controllers_client_FootballManager_getGameMatches158_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/get"))))
private[this] lazy val controllers_client_FootballManager_getGameMatches158_invoker = createInvoker(
controllers.client.FootballManager.getGameMatches(fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getGameMatches", Seq(classOf[Long], classOf[String], classOf[String], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/matches/get"""))
        

// @LINE:275
private[this] lazy val controllers_client_FootballManager_getGameMatchByExternalId159_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/get/ext/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getGameMatchByExternalId159_invoker = createInvoker(
controllers.client.FootballManager.getGameMatchByExternalId(fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getGameMatchByExternalId", Seq(classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/get/ext/$id<[^/]+>"""))
        

// @LINE:276
private[this] lazy val controllers_client_FootballManager_getGameMatchByFifaId160_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/get/fifa/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getGameMatchByFifaId160_invoker = createInvoker(
controllers.client.FootballManager.getGameMatchByFifaId(fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getGameMatchByFifaId", Seq(classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/get/fifa/$id<[^/]+>"""))
        

// @LINE:277
private[this] lazy val controllers_client_FootballManager_getGameMatch161_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getGameMatch161_invoker = createInvoker(
controllers.client.FootballManager.getGameMatch(fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getGameMatch", Seq(classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/get/$id<[^/]+>"""))
        

// @LINE:278
private[this] lazy val controllers_client_FootballManager_getEventsByExternalMatch162_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/events/get/ext/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getEventsByExternalMatch162_invoker = createInvoker(
controllers.client.FootballManager.getEventsByExternalMatch(fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getEventsByExternalMatch", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/events/get/ext/$id<[^/]+>"""))
        

// @LINE:279
private[this] lazy val controllers_client_FootballManager_getEventsByFifaMatch163_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/events/get/fifa/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getEventsByFifaMatch163_invoker = createInvoker(
controllers.client.FootballManager.getEventsByFifaMatch(fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getEventsByFifaMatch", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/events/get/fifa/$id<[^/]+>"""))
        

// @LINE:280
private[this] lazy val controllers_client_FootballManager_getEvents164_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/events/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getEvents164_invoker = createInvoker(
controllers.client.FootballManager.getEvents(fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getEvents", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/events/get/$id<[^/]+>"""))
        

// @LINE:281
private[this] lazy val controllers_client_FootballManager_getRankingByIdPhase165_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/ranking/get/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingByIdPhase165_invoker = createInvoker(
controllers.client.FootballManager.getRankingByIdPhase(fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingByIdPhase", Seq(classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/ranking/get/$id<[^/]+>"""))
        

// @LINE:282
private[this] lazy val controllers_client_FootballManager_getRankingByIdPhase166_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/ranking/get/ext/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingByIdPhase166_invoker = createInvoker(
controllers.client.FootballManager.getRankingByIdPhase(fakeValue[String], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingByIdPhase", Seq(classOf[String], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/ranking/get/ext/$id<[^/]+>"""))
        

// @LINE:283
private[this] lazy val controllers_client_FootballManager_getGlobalRanking167_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/ranking/get/global/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getGlobalRanking167_invoker = createInvoker(
controllers.client.FootballManager.getGlobalRanking(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getGlobalRanking", Seq(classOf[Long]),"GET", """""", Routes.prefix + """footballapi/v1/ranking/get/global/$id<[^/]+>"""))
        

// @LINE:285
private[this] lazy val controllers_client_FootballManager_getFixturesByIDs168_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/get/ids/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesByIDs168_invoker = createInvoker(
controllers.client.FootballManager.getFixturesByIDs(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesByIDs", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/matches/get/ids/$idApp<[^/]+>"""))
        

// @LINE:286
private[this] lazy val controllers_client_FootballManager_getTodayFinished169_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/finished/get/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTodayFinished169_invoker = createInvoker(
controllers.client.FootballManager.getTodayFinished(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTodayFinished", Seq(classOf[Long]),"GET", """""", Routes.prefix + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>"""))
        

// @LINE:287
private[this] lazy val controllers_client_FootballManager_getFinishedByDate170_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/finished/get/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFinishedByDate170_invoker = createInvoker(
controllers.client.FootballManager.getFinishedByDate(fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFinishedByDate", Seq(classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:288
private[this] lazy val controllers_client_FootballManager_getFixturesDate171_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/date/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDate171_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDate(fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDate", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/date/get/$idApp<[^/]+>/$date<[^/]+>"""))
        

// @LINE:289
private[this] lazy val controllers_client_FootballManager_getFixturesDate172_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/date/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDate172_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDate(fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDate", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/date/get/$idApp<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:290
private[this] lazy val controllers_client_FootballManager_getFixturesDateAll173_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/all/date/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDateAll173_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDateAll(fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDateAll", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/all/date/get/$idApp<[^/]+>/$date<[^/]+>"""))
        

// @LINE:291
private[this] lazy val controllers_client_FootballManager_getFixturesDateAll174_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/all/date/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDateAll174_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDateAll(fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDateAll", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/all/date/get/$idApp<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:292
private[this] lazy val controllers_client_FootballManager_getFixturesForCompetitionGroupByDate175_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/competition/date/grouped/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesForCompetitionGroupByDate175_invoker = createInvoker(
controllers.client.FootballManager.getFixturesForCompetitionGroupByDate(fakeValue[Integer], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesForCompetitionGroupByDate", Seq(classOf[Integer], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/competition/date/grouped/$idApp<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:293
private[this] lazy val controllers_client_FootballManager_getFixturesGroupByDate176_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/date/grouped/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesGroupByDate176_invoker = createInvoker(
controllers.client.FootballManager.getFixturesGroupByDate(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesGroupByDate", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/date/grouped/$idApp<[^/]+>"""))
        

// @LINE:294
private[this] lazy val controllers_client_FootballManager_getFixturesDatePaged177_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/date/paged/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDatePaged177_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDatePaged(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDatePaged", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/date/paged/$idApp<[^/]+>/$date<[^/]+>"""))
        

// @LINE:295
private[this] lazy val controllers_client_FootballManager_getFixturesDatePaged178_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/date/paged/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesDatePaged178_invoker = createInvoker(
controllers.client.FootballManager.getFixturesDatePaged(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesDatePaged", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/date/paged/$idApp<[^/]+>/$idLanguage<[^/]+>/$date<[^/]+>"""))
        

// @LINE:297
private[this] lazy val controllers_client_FootballManager_getFixturesCompetitionDatePaged179_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/competition/date/paged/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getFixturesCompetitionDatePaged179_invoker = createInvoker(
controllers.client.FootballManager.getFixturesCompetitionDatePaged(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getFixturesCompetitionDatePaged", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/matches/competition/date/paged/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:299
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions180_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/list/ids/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions180_invoker = createInvoker(
controllers.client.FootballManager.getActiveCompetitions(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean], fakeValue[Boolean], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getActiveCompetitions", Seq(classOf[Integer], classOf[Integer], classOf[Boolean], classOf[Boolean], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/list/ids/$idApp<[^/]+>"""))
        

// @LINE:300
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions181_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/list/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions181_invoker = createInvoker(
controllers.client.FootballManager.getActiveCompetitions(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean], fakeValue[Boolean], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getActiveCompetitions", Seq(classOf[Integer], classOf[Integer], classOf[Boolean], classOf[Boolean], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/list/$idApp<[^/]+>"""))
        

// @LINE:301
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions182_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/list/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getActiveCompetitions182_invoker = createInvoker(
controllers.client.FootballManager.getActiveCompetitions(fakeValue[Integer], fakeValue[Integer], fakeValue[Boolean], fakeValue[Boolean], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getActiveCompetitions", Seq(classOf[Integer], classOf[Integer], classOf[Boolean], classOf[Boolean], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/list/$idApp<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:302
private[this] lazy val controllers_client_FootballManager_getPhasesToNotify183_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/phases/notify/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPhasesToNotify183_invoker = createInvoker(
controllers.client.FootballManager.getPhasesToNotify(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPhasesToNotify", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/phases/notify/$idApp<[^/]+>"""))
        

// @LINE:303
private[this] lazy val controllers_client_FootballManager_getPhasesForCompetition184_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/phases/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPhasesForCompetition184_invoker = createInvoker(
controllers.client.FootballManager.getPhasesForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPhasesForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/phases/$idApp<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:304
private[this] lazy val controllers_client_FootballManager_getPhasesForCompetition185_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/phases/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPhasesForCompetition185_invoker = createInvoker(
controllers.client.FootballManager.getPhasesForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPhasesForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/phases/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:305
private[this] lazy val controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition186_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/phases/latest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition186_invoker = createInvoker(
controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCurrentAndLastPhaseForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/phases/latest/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:306
private[this] lazy val controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition187_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/phases/latest/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition187_invoker = createInvoker(
controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCurrentAndLastPhaseForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/phases/latest/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:309
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition188_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition188_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """MaM""", Routes.prefix + """footballapi/v1/matches/mam/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>"""))
        

// @LINE:310
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition189_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition189_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/mam/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:311
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition190_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/next/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true),StaticPart("/"),DynamicPart("idEvent", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition190_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/mam/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idEvent<[^/]+>"""))
        

// @LINE:312
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition191_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/next/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("idEvent", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition191_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/mam/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>/$idEvent<[^/]+>"""))
        

// @LINE:313
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition192_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/previous/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true),StaticPart("/"),DynamicPart("idEvent", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition192_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/mam/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idEvent<[^/]+>"""))
        

// @LINE:314
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition193_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/matches/mam/previous/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idMatch", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("idEvent", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getMinuteToMinuteForCompetition193_invoker = createInvoker(
controllers.client.FootballManager.getMinuteToMinuteForCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer], fakeValue[Long], fakeValue[Boolean]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """footballapi/v1/matches/mam/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>/$idEvent<[^/]+>"""))
        

// @LINE:318
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase194_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase194_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """

#NEW VERSION""", Routes.prefix + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:319
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase195_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase195_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:320
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase196_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase196_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:321
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase197_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase197_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>"""))
        

// @LINE:322
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase198_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/next/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase198_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:323
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase199_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/next/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase199_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:324
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase200_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/previous/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase200_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:325
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase201_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/competitions/ranking/previous/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("idPhase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankingsForPhase201_invoker = createInvoker(
controllers.client.FootballManager.getRankingsForPhase(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Long], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/competitions/ranking/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>"""))
        

// @LINE:330
private[this] lazy val controllers_client_FootballManager_getCompetitionTopScorersForClient202_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/players/topScorers/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCompetitionTopScorersForClient202_invoker = createInvoker(
controllers.client.FootballManager.getCompetitionTopScorersForClient(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCompetitionTopScorersForClient", Seq(classOf[Integer]),"GET", """
#new football api
#players""", Routes.prefix + """footballapi/v1/players/topScorers/$idApp<[^/]+>"""))
        

// @LINE:331
private[this] lazy val controllers_client_FootballManager_getTopScorers203_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/players/topScorers/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTopScorers203_invoker = createInvoker(
controllers.client.FootballManager.getTopScorers(fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTopScorers", Seq(classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """footballapi/v1/players/topScorers/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:332
private[this] lazy val controllers_client_FootballManager_getTopScorersByCompetition204_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/players/competitions/scorers/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getTopScorersByCompetition204_invoker = createInvoker(
controllers.client.FootballManager.getTopScorersByCompetition(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getTopScorersByCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/players/competitions/scorers/$idApp<[^/]+>"""))
        

// @LINE:333
private[this] lazy val controllers_client_FootballManager_getCompetitionTopScorers205_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/players/competition/scorers/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCompetitionTopScorers205_invoker = createInvoker(
controllers.client.FootballManager.getCompetitionTopScorers(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCompetitionTopScorers", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """footballapi/v1/players/competition/scorers/$idApp<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:336
private[this] lazy val controllers_client_FootballManager_getAllFixtures206_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/calendar/get/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getAllFixtures206_invoker = createInvoker(
controllers.client.FootballManager.getAllFixtures(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getAllFixtures", Seq(classOf[Long]),"GET", """
#calendar""", Routes.prefix + """footballapi/v1/calendar/get/$idCompetition<[^/]+>"""))
        

// @LINE:339
private[this] lazy val controllers_client_FootballManager_getRankings207_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/rankings/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("formattedToday", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankings207_invoker = createInvoker(
controllers.client.FootballManager.getRankings(fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankings", Seq(classOf[Integer], classOf[Integer], classOf[String]),"GET", """#football manager""", Routes.prefix + """api/v1/rankings/get/$idApp<[^/]+>/$formattedToday<[^/]+>"""))
        

// @LINE:340
private[this] lazy val controllers_client_FootballManager_getRankings208_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/rankings/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idLanguage", """[^/]+""",true),StaticPart("/"),DynamicPart("formattedToday", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getRankings208_invoker = createInvoker(
controllers.client.FootballManager.getRankings(fakeValue[Integer], fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getRankings", Seq(classOf[Integer], classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """api/v1/rankings/get/$idApp<[^/]+>/$idLanguage<[^/]+>/$formattedToday<[^/]+>"""))
        

// @LINE:343
private[this] lazy val controllers_client_FootballManager_getPushableEventsForApp209_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("footballapi/v1/pushable/get/"),DynamicPart("idApp", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getPushableEventsForApp209_invoker = createInvoker(
controllers.client.FootballManager.getPushableEventsForApp(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getPushableEventsForApp", Seq(classOf[Integer]),"GET", """
#pushableEvents""", Routes.prefix + """footballapi/v1/pushable/get/$idApp<[^/]+>"""))
        

// @LINE:346
private[this] lazy val controllers_client_FootballManager_getCalendar210_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/date/gt/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar210_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """
#calendar""", Routes.prefix + """api/v1/calendar/get/date/gt/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:347
private[this] lazy val controllers_client_FootballManager_getCalendar211_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/date/lt/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar211_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/date/lt/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:348
private[this] lazy val controllers_client_FootballManager_getCalendar212_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/date/eq/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("date", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar212_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/date/eq/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>"""))
        

// @LINE:349
private[this] lazy val controllers_client_FootballManager_getCalendar213_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/phase/gt/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("phase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar213_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/phase/gt/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>"""))
        

// @LINE:350
private[this] lazy val controllers_client_FootballManager_getCalendar214_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/phase/lt/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("phase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar214_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/phase/lt/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>"""))
        

// @LINE:351
private[this] lazy val controllers_client_FootballManager_getCalendar215_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/phase/eq/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true),StaticPart("/"),DynamicPart("phase", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar215_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/phase/eq/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>"""))
        

// @LINE:352
private[this] lazy val controllers_client_FootballManager_getCalendar216_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("api/v1/calendar/get/"),DynamicPart("idApp", """[^/]+""",true),StaticPart("/"),DynamicPart("idCompetition", """[^/]+""",true))))
private[this] lazy val controllers_client_FootballManager_getCalendar216_invoker = createInvoker(
controllers.client.FootballManager.getCalendar(fakeValue[Integer], fakeValue[Integer], fakeValue[String], fakeValue[Long], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "App", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """api/v1/calendar/get/$idApp<[^/]+>/$idCompetition<[^/]+>"""))
        

// @LINE:354
lazy val JobCore_Routes217 = Include(JobCore.Routes)
        

// @LINE:355
lazy val UpstreamAPIConnector_Routes218 = Include(UpstreamAPIConnector.Routes)
        
def documentation = List(("""OPTIONS""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$url<.+>""","""controllers.Application.options(url:String)"""),("""GET""", prefix,"""controllers.Wap.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """index""","""controllers.Wap.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/$id<[^/]+>""","""controllers.Wap.news(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """matches""","""controllers.Wap.matches(id:Integer, page:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """competitions""","""controllers.Wap.competitions(route:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """scorers""","""controllers.Wap.scorers(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """mtm""","""controllers.Wap.mtm(id:Integer, match:Integer, event:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Wap.getLogin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login/password""","""controllers.Wap.getPassword()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login/create""","""controllers.Wap.createClient()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """check/$name<.+>""","""controllers.Application.checkFile(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/loading/$width<[^/]+>/$height<[^/]+>/$version<[^/]+>/$so<[^/]+>""","""controllers.Application.getAppSettings(width:Integer, height:Integer, version:String, so:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/krakencreateweb/$msisdn<[^/]+>""","""controllers.client.FootballClients.createKrakenweb(msisdn:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/krakencreate/$msisdn<[^/]+>/$passwd<[^/]+>/$usd<[^/]+>""","""controllers.client.FootballClients.createKraken(msisdn:String, passwd:String, usd:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/krakendown/$msisdn<[^/]+>""","""controllers.client.FootballClients.downKraken(msisdn:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/create""","""controllers.client.FootballClients.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/update/$id<[^/]+>""","""controllers.client.FootballClients.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/delete/$id<[^/]+>""","""controllers.client.Clients.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>""","""controllers.client.FootballClients.get(id:Integer, upstreamChannel:String, pmc:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/list""","""controllers.client.Clients.list(pageSize:Integer ?= 0, page:Integer ?= 0, pmc:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/alerts/$id<[^/]+>""","""controllers.client.FootballClients.getPushAlertsForClient(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/bets/get/$id<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballClients.getBetsForCompetition(id:Integer, idCompetition:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/bets/get/date/$id<[^/]+>/$date<[^/]+>""","""controllers.client.FootballClients.getBetsForDate(id:Integer, date:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/bets/get/$id<[^/]+>""","""controllers.client.FootballClients.getBets(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/bets/create/$id<[^/]+>""","""controllers.client.FootballClients.createBets(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/dashboard/$id<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballClients.dashboard(id:Integer, idLanguage:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/leaderboard/get/$id<[^/]+>/$idTournament<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballClients.getLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/leaderboard/global/get/$id<[^/]+>/$idTournament<[^/]+>""","""controllers.client.FootballClients.getLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/leaderboard/personal/tournament/$id<[^/]+>""","""controllers.client.FootballClients.getPersonalLeaderboardForClient(id:Integer, idTournament:Integer ?= 0, idPhase:Integer ?= 0, global:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/leaderboard/personal/phase/$id<[^/]+>""","""controllers.client.FootballClients.getPersonalLeaderboardForClient(id:Integer, idTournament:Integer ?= 0, idPhase:Integer ?= 0, global:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/leaderboard/total/$id<[^/]+>""","""controllers.client.FootballClients.getLeaderboardTotalForClient(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/clients/scorers/$idClient<[^/]+>""","""controllers.client.FootballManager.getScorers(idClient:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/languages""","""controllers.client.Clients.getActiveLanguages()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v1/localimages/$file<[^/]+>/$width<[^/]+>""","""controllers.ImageProcessing.getLocalResourceByWidth(file:String, width:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client""","""controllers.client.FootballClients.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>""","""controllers.client.FootballClients.get(id:Integer, upstreamChannel:String ?= "Android", pmc:Boolean ?= false)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>""","""controllers.client.FootballClients.update(id:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>""","""controllers.client.Clients.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/alerts""","""controllers.client.FootballClients.getPushAlertsForClient(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/bet""","""controllers.client.FootballClients.createBet(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/bets""","""controllers.client.FootballClients.createBets(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/bets""","""controllers.client.FootballClients.getBets(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/bets/$idCompetition<[^/]+>""","""controllers.client.FootballClients.getBetsForCompetition(id:Integer, idCompetition:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/leaderboard/global/$idTournament<[^/]+>""","""controllers.client.FootballClients.getLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/leaderboard/personal/tournament""","""controllers.client.FootballClients.getPersonalLeaderboardForClient(id:Integer, idTournament:Integer ?= 0, idPhase:Integer ?= 0, global:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/leaderboard/personal/phase""","""controllers.client.FootballClients.getPersonalLeaderboardForClient(id:Integer, idTournament:Integer ?= 0, idPhase:Integer ?= 0, global:Boolean ?= false)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/client/$id<[^/]+>/upstream""","""controllers.Upstream.sendClientEvent(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/clients""","""controllers.client.Clients.list(pageSize:Integer ?= 0, page:Integer ?= 0, pmc:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/locale.json""","""controllers.client.FootballClients.getLocale(lang:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sportsapi/v2/locale.json""","""controllers.client.FootballClients.setLocale(lang:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin""","""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/restricted""","""controllers.Application.restricted"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/assets/javascript/routes.js""","""controllers.Application.jsRoutes"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/profile""","""controllers.Application.profile"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login""","""controllers.Application.login"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login""","""controllers.Application.doLogin"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/logout""","""com.feth.play.module.pa.controllers.Authenticate.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/authenticate/$provider<[^/]+>""","""com.feth.play.module.pa.controllers.Authenticate.authenticate(provider:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/signup""","""controllers.Application.signup"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/signup""","""controllers.Application.doSignup"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/unverified""","""controllers.Signup.unverified"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/authenticate/$provider<[^/]+>/denied""","""controllers.Signup.oAuthDenied(provider:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/verify/$token<[^/]+>""","""controllers.Signup.verify(token:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/exists""","""controllers.Signup.exists"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/password/reset/$token<[^/]+>""","""controllers.Signup.resetPassword(token:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/password/reset""","""controllers.Signup.doResetPassword"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/password/change""","""controllers.Account.changePassword"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/password/change""","""controllers.Account.doChangePassword"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/verify""","""controllers.Account.verifyEmail"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/add""","""controllers.Account.link"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/link""","""controllers.Account.askLink"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """accounts/link""","""controllers.Account.doLink"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/merge""","""controllers.Account.askMerge"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/accounts/merge""","""controllers.Account.doMerge"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login/password/forgot""","""controllers.Signup.forgotPassword(email:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login/password/forgot""","""controllers.Signup.doForgotPassword"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/list""","""controllers.CountriesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/lsort""","""controllers.CountriesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/blank""","""controllers.CountriesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries""","""controllers.CountriesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/sort/$ids<[^/]+>""","""controllers.CountriesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/$id<[^/]+>""","""controllers.CountriesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/$id<[^/]+>""","""controllers.CountriesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/countries/$id<[^/]+>/delete""","""controllers.CountriesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/list""","""controllers.LanguagesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/lsort""","""controllers.LanguagesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/blank""","""controllers.LanguagesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages""","""controllers.LanguagesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/sort/$ids<[^/]+>""","""controllers.LanguagesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/$id<[^/]+>""","""controllers.LanguagesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/$id<[^/]+>""","""controllers.LanguagesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/languages/$id<[^/]+>/delete""","""controllers.LanguagesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/list""","""controllers.TimezonesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/lsort""","""controllers.TimezonesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/blank""","""controllers.TimezonesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones""","""controllers.TimezonesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/sort/$ids<[^/]+>""","""controllers.TimezonesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/$id<[^/]+>""","""controllers.TimezonesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/$id<[^/]+>""","""controllers.TimezonesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/timezones/$id<[^/]+>/delete""","""controllers.TimezonesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/list""","""controllers.DevicesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/lsort""","""controllers.DevicesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/blank""","""controllers.DevicesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices""","""controllers.DevicesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/sort/$ids<[^/]+>""","""controllers.DevicesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/$id<[^/]+>""","""controllers.DevicesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/$id<[^/]+>""","""controllers.DevicesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/devices/$id<[^/]+>/delete""","""controllers.DevicesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/list""","""controllers.InstancesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/lsort""","""controllers.InstancesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/blank""","""controllers.InstancesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances""","""controllers.InstancesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/sort/$ids<[^/]+>""","""controllers.InstancesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/$id<[^/]+>""","""controllers.InstancesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/$id<[^/]+>""","""controllers.InstancesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/instances/$id<[^/]+>/delete""","""controllers.InstancesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/list""","""controllers.JobsView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/lsort""","""controllers.JobsView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/blank""","""controllers.JobsView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs""","""controllers.JobsView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/sort/$ids<[^/]+>""","""controllers.JobsView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/$id<[^/]+>""","""controllers.JobsView.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/$id<[^/]+>""","""controllers.JobsView.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/jobs/$id<[^/]+>/delete""","""controllers.JobsView.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/list""","""controllers.ConfigsView.list(p:Int ?= 0, s:String ?= "configKey", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/lsort""","""controllers.ConfigsView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/blank""","""controllers.ConfigsView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations""","""controllers.ConfigsView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/sort/$ids<[^/]+>""","""controllers.ConfigsView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/$id<[^/]+>""","""controllers.ConfigsView.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/$id<[^/]+>""","""controllers.ConfigsView.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/configurations/$id<[^/]+>/delete""","""controllers.ConfigsView.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users/list""","""controllers.UsersView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users/$id<[^/]+>""","""controllers.UsersView.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users/$id<[^/]+>""","""controllers.UsersView.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/users/$id<[^/]+>/delete""","""controllers.UsersView.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/get/$idNews<[^/]+>""","""controllers.client.FootballManager.getNewsById(idNews:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/search/$idApp<[^/]+>""","""controllers.client.FootballManager.getNews(idApp:Integer, offset:Integer = 0, count:Integer = 20)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/search/$idApp<[^/]+>/$offset<[^/]+>/$count<[^/]+>""","""controllers.client.FootballManager.getNews(idApp:Integer, offset:Integer, count:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/up/first/$idApp<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long, newest:Boolean ?= true, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/up/first/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean ?= true, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/up/rest/$idApp<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long, newest:Boolean ?= true, first:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/up/rest/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean ?= true, first:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/down/first/$idApp<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long, newest:Boolean ?= false, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/down/first/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean ?= false, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/down/rest/$idApp<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long, newest:Boolean ?= false, first:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/down/rest/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean ?= false, first:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/$idApp<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long ?= 0, newest:Boolean ?= false, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/$idApp<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long ?= 0, newest:Boolean ?= false, first:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/$idApp<[^/]+>/$newsId<[^/]+>/$newest<[^/]+>/$first<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer ?= 300, newsId:Long, newest:Boolean, first:Boolean)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """newsapi/v1/news/scroll/$idApp<[^/]+>/$idLanguage<[^/]+>/$newsId<[^/]+>/$newest<[^/]+>/$first<[^/]+>""","""controllers.client.FootballManager.getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean, first:Boolean)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/team/competition/all/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getTeams(idCompetition:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/team/app/all/$idApp<[^/]+>""","""controllers.client.FootballManager.getTeamsForApp(idApp:Integer, pageSize:Integer ?= 0, page:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/team/get/$id<[^/]+>""","""controllers.client.FootballManager.getTeam(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/phase/getList/$idComp<[^/]+>""","""controllers.client.FootballManager.getPhases(idComp:Long, sd:String ?= "", end:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/phase/get/$id<[^/]+>""","""controllers.client.FootballManager.getPhase(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/get""","""controllers.client.FootballManager.getGameMatches(phase:Long ?= 0, sd:String ?= "", ed:String ?= "", st:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/get/ext/$id<[^/]+>""","""controllers.client.FootballManager.getGameMatchByExternalId(id:Long, events:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/get/fifa/$id<[^/]+>""","""controllers.client.FootballManager.getGameMatchByFifaId(id:Long, events:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/get/$id<[^/]+>""","""controllers.client.FootballManager.getGameMatch(id:Long, events:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/events/get/ext/$id<[^/]+>""","""controllers.client.FootballManager.getEventsByExternalMatch(id:Long, act:String ?= "", p:String ?= "", ts:String ?= "", te:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/events/get/fifa/$id<[^/]+>""","""controllers.client.FootballManager.getEventsByFifaMatch(id:Long, act:String ?= "", p:String ?= "", ts:String ?= "", te:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/events/get/$id<[^/]+>""","""controllers.client.FootballManager.getEvents(id:Long, act:String ?= "", p:String ?= "", ts:String ?= "", te:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/ranking/get/$id<[^/]+>""","""controllers.client.FootballManager.getRankingByIdPhase(id:String, ext:Boolean = false )"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/ranking/get/ext/$id<[^/]+>""","""controllers.client.FootballManager.getRankingByIdPhase(id:String, ext:Boolean = true )"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/ranking/get/global/$id<[^/]+>""","""controllers.client.FootballManager.getGlobalRanking(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/get/ids/$idApp<[^/]+>""","""controllers.client.FootballManager.getFixturesByIDs(idApp:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getTodayFinished(idCompetition:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFinishedByDate(idCompetition:Long, date:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/date/get/$idApp<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFixturesDate(idApp:Integer, date:String, idLanguage:Integer ?= 300, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/date/get/$idApp<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getFixturesDate(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/all/date/get/$idApp<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFixturesDateAll(idApp:Integer, date:String, idLanguage:Integer ?= 300, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/all/date/get/$idApp<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getFixturesDateAll(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/competition/date/grouped/$idApp<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getFixturesForCompetitionGroupByDate(idApp:Integer, idCompetition:Long, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/date/grouped/$idApp<[^/]+>""","""controllers.client.FootballManager.getFixturesGroupByDate(idApp:Integer, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/date/paged/$idApp<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFixturesDatePaged(idApp:Integer, idLanguage:Integer ?= 300, date:String, pageSize:Integer ?= 0, page:Integer ?= 0, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/date/paged/$idApp<[^/]+>/$idLanguage<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFixturesDatePaged(idApp:Integer, idLanguage:Integer, date:String, pageSize:Integer ?= 0, page:Integer ?= 0, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/competition/date/paged/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getFixturesCompetitionDatePaged(idApp:Integer, idCompetition:Integer, date:String, pageSize:Integer ?= 0, page:Integer ?= 0, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/list/ids/$idApp<[^/]+>""","""controllers.client.FootballManager.getActiveCompetitions(idApp:Integer, idLanguage:Integer ?= 0, ids:Boolean ?= true, closestMatch:Boolean ?= false, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/list/$idApp<[^/]+>""","""controllers.client.FootballManager.getActiveCompetitions(idApp:Integer, idLanguage:Integer ?= 300, ids:Boolean ?= false, closestMatch:Boolean ?= false, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/list/$idApp<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getActiveCompetitions(idApp:Integer, idLanguage:Integer, ids:Boolean ?= false, closestMatch:Boolean ?= false, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/phases/notify/$idApp<[^/]+>""","""controllers.client.FootballManager.getPhasesToNotify(idApp:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/phases/$idApp<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getPhasesForCompetition(idApp:Integer, idCompetition:Integer, idLanguage:Integer ?= 300, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/phases/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getPhasesForCompetition(idApp:Integer, idCompetition:Integer, idLanguage:Integer, timezoneName:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/phases/latest/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(idApp:Integer, idCompetition:Integer, date:String, idLanguage:Integer ?= 300)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/phases/latest/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(idApp:Integer, idCompetition:Integer, date:String, idLanguage:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer ?= 300, idEvent:Long ?= 0, forward:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer, idEvent:Long ?= 0, forward:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idEvent<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer ?= 300, idEvent:Long, forward:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>/$idEvent<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer, idEvent:Long, forward:Boolean ?= true)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idEvent<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer ?= 300, idEvent:Long, forward:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/matches/mam/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>/$idLanguage<[^/]+>/$idEvent<[^/]+>""","""controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer, idEvent:Long, forward:Boolean ?= false)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer ?= 300, idPhase:Long, way:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long, way:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer ?= 300, idPhase:Long ?= 0, way:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long ?= 0, way:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer ?= 300, idPhase:Long, way:Integer ?= 1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/next/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long, way:Integer ?= 1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer ?= 300, idPhase:Long, way:Integer ?= -1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/competitions/ranking/previous/$idApp<[^/]+>/$idCompetition<[^/]+>/$idLanguage<[^/]+>/$idPhase<[^/]+>""","""controllers.client.FootballManager.getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long, way:Integer ?= -1)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/players/topScorers/$idApp<[^/]+>""","""controllers.client.FootballManager.getCompetitionTopScorersForClient(idApp:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/players/topScorers/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getTopScorers(idCompetition:Long, date:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/players/competitions/scorers/$idApp<[^/]+>""","""controllers.client.FootballManager.getTopScorersByCompetition(idApp:Integer, pageSize:Integer ?= 0, page:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/players/competition/scorers/$idApp<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getCompetitionTopScorers(idApp:Integer, idCompetition:Integer, pageSize:Integer ?= 0, page:Integer ?= 0)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/calendar/get/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getAllFixtures(idCompetition:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/rankings/get/$idApp<[^/]+>/$formattedToday<[^/]+>""","""controllers.client.FootballManager.getRankings(idApp:Integer, idLanguage:Integer ?= 300, formattedToday:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/rankings/get/$idApp<[^/]+>/$idLanguage<[^/]+>/$formattedToday<[^/]+>""","""controllers.client.FootballManager.getRankings(idApp:Integer, idLanguage:Integer, formattedToday:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """footballapi/v1/pushable/get/$idApp<[^/]+>""","""controllers.client.FootballManager.getPushableEventsForApp(idApp:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/date/gt/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String, phase:Long ?= 0, operator:String ?= "gt")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/date/lt/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String, phase:Long ?= 0, operator:String ?= "lt")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/date/eq/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String, phase:Long ?= 0, operator:String ?= "eq")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/phase/gt/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String ?= "", phase:Long, operator:String ?= "gt")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/phase/lt/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String ?= "", phase:Long, operator:String ?= "lt")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/phase/eq/$idApp<[^/]+>/$idCompetition<[^/]+>/$phase<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String ?= "", phase:Long, operator:String ?= "eq")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """api/v1/calendar/get/$idApp<[^/]+>/$idCompetition<[^/]+>""","""controllers.client.FootballManager.getCalendar(idApp:Integer, idCompetition:Integer, date:String ?= "", phase:Long ?= 0, operator:String ?= "gt")"""),JobCore.Routes.documentation,UpstreamAPIConnector.Routes.documentation).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:7
case controllers_Application_options0_route(params) => {
   call(params.fromPath[String]("url", None)) { (url) =>
        controllers_Application_options0_invoker.call(controllers.Application.options(url))
   }
}
        

// @LINE:9
case controllers_Wap_index1_route(params) => {
   call { 
        controllers_Wap_index1_invoker.call(controllers.Wap.index())
   }
}
        

// @LINE:10
case controllers_Wap_index2_route(params) => {
   call { 
        controllers_Wap_index2_invoker.call(controllers.Wap.index())
   }
}
        

// @LINE:11
case controllers_Wap_news3_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_Wap_news3_invoker.call(controllers.Wap.news(id))
   }
}
        

// @LINE:12
case controllers_Wap_matches4_route(params) => {
   call(params.fromQuery[Integer]("id", None), params.fromQuery[Integer]("page", None)) { (id, page) =>
        controllers_Wap_matches4_invoker.call(controllers.Wap.matches(id, page))
   }
}
        

// @LINE:13
case controllers_Wap_competitions5_route(params) => {
   call(params.fromQuery[String]("route", None)) { (route) =>
        controllers_Wap_competitions5_invoker.call(controllers.Wap.competitions(route))
   }
}
        

// @LINE:14
case controllers_Wap_scorers6_route(params) => {
   call(params.fromQuery[Integer]("id", None)) { (id) =>
        controllers_Wap_scorers6_invoker.call(controllers.Wap.scorers(id))
   }
}
        

// @LINE:15
case controllers_Wap_mtm7_route(params) => {
   call(params.fromQuery[Integer]("id", None), params.fromQuery[Integer]("match", None), params.fromQuery[Integer]("event", None)) { (id, playframework_escape_match, event) =>
        controllers_Wap_mtm7_invoker.call(controllers.Wap.mtm(id, playframework_escape_match, event))
   }
}
        

// @LINE:16
case controllers_Wap_getLogin8_route(params) => {
   call { 
        controllers_Wap_getLogin8_invoker.call(controllers.Wap.getLogin())
   }
}
        

// @LINE:17
case controllers_Wap_getPassword9_route(params) => {
   call { 
        controllers_Wap_getPassword9_invoker.call(controllers.Wap.getPassword())
   }
}
        

// @LINE:18
case controllers_Wap_createClient10_route(params) => {
   call { 
        controllers_Wap_createClient10_invoker.call(controllers.Wap.createClient())
   }
}
        

// @LINE:20
case controllers_Application_checkFile11_route(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        controllers_Application_checkFile11_invoker.call(controllers.Application.checkFile(name))
   }
}
        

// @LINE:21
case controllers_Application_getAppSettings12_route(params) => {
   call(params.fromPath[Integer]("width", None), params.fromPath[Integer]("height", None), params.fromPath[String]("version", None), params.fromPath[String]("so", None)) { (width, height, version, so) =>
        controllers_Application_getAppSettings12_invoker.call(controllers.Application.getAppSettings(width, height, version, so))
   }
}
        

// @LINE:30
case controllers_Assets_at13_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at13_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:35
case controllers_client_FootballClients_createKrakenweb14_route(params) => {
   call(params.fromPath[String]("msisdn", None)) { (msisdn) =>
        controllers_client_FootballClients_createKrakenweb14_invoker.call(controllers.client.FootballClients.createKrakenweb(msisdn))
   }
}
        

// @LINE:36
case controllers_client_FootballClients_createKraken15_route(params) => {
   call(params.fromPath[String]("msisdn", None), params.fromPath[String]("passwd", None), params.fromPath[String]("usd", None)) { (msisdn, passwd, usd) =>
        controllers_client_FootballClients_createKraken15_invoker.call(controllers.client.FootballClients.createKraken(msisdn, passwd, usd))
   }
}
        

// @LINE:37
case controllers_client_FootballClients_downKraken16_route(params) => {
   call(params.fromPath[String]("msisdn", None)) { (msisdn) =>
        controllers_client_FootballClients_downKraken16_invoker.call(controllers.client.FootballClients.downKraken(msisdn))
   }
}
        

// @LINE:38
case controllers_client_FootballClients_create17_route(params) => {
   call { 
        controllers_client_FootballClients_create17_invoker.call(controllers.client.FootballClients.create())
   }
}
        

// @LINE:39
case controllers_client_FootballClients_update18_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_update18_invoker.call(controllers.client.FootballClients.update(id))
   }
}
        

// @LINE:40
case controllers_client_Clients_delete19_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_delete19_invoker.call(controllers.client.Clients.delete(id))
   }
}
        

// @LINE:46
case controllers_client_FootballClients_get20_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[String]("upstreamChannel", None), params.fromQuery[Boolean]("pmc", Some(false))) { (id, upstreamChannel, pmc) =>
        controllers_client_FootballClients_get20_invoker.call(controllers.client.FootballClients.get(id, upstreamChannel, pmc))
   }
}
        

// @LINE:47
case controllers_client_Clients_list21_route(params) => {
   call(params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[Boolean]("pmc", Some(false))) { (pageSize, page, pmc) =>
        controllers_client_Clients_list21_invoker.call(controllers.client.Clients.list(pageSize, page, pmc))
   }
}
        

// @LINE:48
case controllers_client_FootballClients_getPushAlertsForClient22_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_getPushAlertsForClient22_invoker.call(controllers.client.FootballClients.getPushAlertsForClient(id))
   }
}
        

// @LINE:49
case controllers_client_FootballClients_getBetsForCompetition23_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idCompetition", None)) { (id, idCompetition) =>
        controllers_client_FootballClients_getBetsForCompetition23_invoker.call(controllers.client.FootballClients.getBetsForCompetition(id, idCompetition))
   }
}
        

// @LINE:50
case controllers_client_FootballClients_getBetsForDate24_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[String]("date", None)) { (id, date) =>
        controllers_client_FootballClients_getBetsForDate24_invoker.call(controllers.client.FootballClients.getBetsForDate(id, date))
   }
}
        

// @LINE:51
case controllers_client_FootballClients_getBets25_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_getBets25_invoker.call(controllers.client.FootballClients.getBets(id))
   }
}
        

// @LINE:52
case controllers_client_FootballClients_createBets26_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_createBets26_invoker.call(controllers.client.FootballClients.createBets(id))
   }
}
        

// @LINE:53
case controllers_client_FootballClients_dashboard27_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idLanguage", None)) { (id, idLanguage) =>
        controllers_client_FootballClients_dashboard27_invoker.call(controllers.client.FootballClients.dashboard(id, idLanguage))
   }
}
        

// @LINE:56
case controllers_client_FootballClients_getLeaderboardForClient28_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idTournament", None), params.fromPath[Integer]("idPhase", None)) { (id, idTournament, idPhase) =>
        controllers_client_FootballClients_getLeaderboardForClient28_invoker.call(controllers.client.FootballClients.getLeaderboardForClient(id, idTournament, idPhase))
   }
}
        

// @LINE:57
case controllers_client_FootballClients_getLeaderboardForClient29_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idTournament", None), params.fromQuery[Integer]("idPhase", Some(0))) { (id, idTournament, idPhase) =>
        controllers_client_FootballClients_getLeaderboardForClient29_invoker.call(controllers.client.FootballClients.getLeaderboardForClient(id, idTournament, idPhase))
   }
}
        

// @LINE:58
case controllers_client_FootballClients_getPersonalLeaderboardForClient30_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[Integer]("idTournament", Some(0)), params.fromQuery[Integer]("idPhase", Some(0)), params.fromQuery[Boolean]("global", Some(true))) { (id, idTournament, idPhase, global) =>
        controllers_client_FootballClients_getPersonalLeaderboardForClient30_invoker.call(controllers.client.FootballClients.getPersonalLeaderboardForClient(id, idTournament, idPhase, global))
   }
}
        

// @LINE:59
case controllers_client_FootballClients_getPersonalLeaderboardForClient31_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[Integer]("idTournament", Some(0)), params.fromQuery[Integer]("idPhase", Some(0)), params.fromQuery[Boolean]("global", Some(false))) { (id, idTournament, idPhase, global) =>
        controllers_client_FootballClients_getPersonalLeaderboardForClient31_invoker.call(controllers.client.FootballClients.getPersonalLeaderboardForClient(id, idTournament, idPhase, global))
   }
}
        

// @LINE:60
case controllers_client_FootballClients_getLeaderboardTotalForClient32_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_getLeaderboardTotalForClient32_invoker.call(controllers.client.FootballClients.getLeaderboardTotalForClient(id))
   }
}
        

// @LINE:63
case controllers_client_FootballManager_getScorers33_route(params) => {
   call(params.fromPath[Integer]("idClient", None)) { (idClient) =>
        controllers_client_FootballManager_getScorers33_invoker.call(controllers.client.FootballManager.getScorers(idClient))
   }
}
        

// @LINE:66
case controllers_client_Clients_getActiveLanguages34_route(params) => {
   call { 
        controllers_client_Clients_getActiveLanguages34_invoker.call(controllers.client.Clients.getActiveLanguages())
   }
}
        

// @LINE:71
case controllers_ImageProcessing_getLocalResourceByWidth35_route(params) => {
   call(params.fromPath[String]("file", None), params.fromPath[Integer]("width", None)) { (file, width) =>
        controllers_ImageProcessing_getLocalResourceByWidth35_invoker.call(controllers.ImageProcessing.getLocalResourceByWidth(file, width))
   }
}
        

// @LINE:77
case controllers_client_FootballClients_create36_route(params) => {
   call { 
        controllers_client_FootballClients_create36_invoker.call(controllers.client.FootballClients.create())
   }
}
        

// @LINE:78
case controllers_client_FootballClients_get37_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[String]("upstreamChannel", Some("Android")), params.fromQuery[Boolean]("pmc", Some(false))) { (id, upstreamChannel, pmc) =>
        controllers_client_FootballClients_get37_invoker.call(controllers.client.FootballClients.get(id, upstreamChannel, pmc))
   }
}
        

// @LINE:79
case controllers_client_FootballClients_update38_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_update38_invoker.call(controllers.client.FootballClients.update(id))
   }
}
        

// @LINE:80
case controllers_client_Clients_delete39_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_Clients_delete39_invoker.call(controllers.client.Clients.delete(id))
   }
}
        

// @LINE:81
case controllers_client_FootballClients_getPushAlertsForClient40_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_getPushAlertsForClient40_invoker.call(controllers.client.FootballClients.getPushAlertsForClient(id))
   }
}
        

// @LINE:82
case controllers_client_FootballClients_createBet41_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_createBet41_invoker.call(controllers.client.FootballClients.createBet(id))
   }
}
        

// @LINE:83
case controllers_client_FootballClients_createBets42_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_createBets42_invoker.call(controllers.client.FootballClients.createBets(id))
   }
}
        

// @LINE:84
case controllers_client_FootballClients_getBets43_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_client_FootballClients_getBets43_invoker.call(controllers.client.FootballClients.getBets(id))
   }
}
        

// @LINE:85
case controllers_client_FootballClients_getBetsForCompetition44_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idCompetition", None)) { (id, idCompetition) =>
        controllers_client_FootballClients_getBetsForCompetition44_invoker.call(controllers.client.FootballClients.getBetsForCompetition(id, idCompetition))
   }
}
        

// @LINE:86
case controllers_client_FootballClients_getLeaderboardForClient45_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("idTournament", None), params.fromQuery[Integer]("idPhase", Some(0))) { (id, idTournament, idPhase) =>
        controllers_client_FootballClients_getLeaderboardForClient45_invoker.call(controllers.client.FootballClients.getLeaderboardForClient(id, idTournament, idPhase))
   }
}
        

// @LINE:87
case controllers_client_FootballClients_getPersonalLeaderboardForClient46_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[Integer]("idTournament", Some(0)), params.fromQuery[Integer]("idPhase", Some(0)), params.fromQuery[Boolean]("global", Some(true))) { (id, idTournament, idPhase, global) =>
        controllers_client_FootballClients_getPersonalLeaderboardForClient46_invoker.call(controllers.client.FootballClients.getPersonalLeaderboardForClient(id, idTournament, idPhase, global))
   }
}
        

// @LINE:88
case controllers_client_FootballClients_getPersonalLeaderboardForClient47_route(params) => {
   call(params.fromPath[Integer]("id", None), params.fromQuery[Integer]("idTournament", Some(0)), params.fromQuery[Integer]("idPhase", Some(0)), params.fromQuery[Boolean]("global", Some(false))) { (id, idTournament, idPhase, global) =>
        controllers_client_FootballClients_getPersonalLeaderboardForClient47_invoker.call(controllers.client.FootballClients.getPersonalLeaderboardForClient(id, idTournament, idPhase, global))
   }
}
        

// @LINE:89
case controllers_Upstream_sendClientEvent48_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_Upstream_sendClientEvent48_invoker.call(controllers.Upstream.sendClientEvent(id))
   }
}
        

// @LINE:90
case controllers_client_Clients_list49_route(params) => {
   call(params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[Boolean]("pmc", Some(false))) { (pageSize, page, pmc) =>
        controllers_client_Clients_list49_invoker.call(controllers.client.Clients.list(pageSize, page, pmc))
   }
}
        

// @LINE:92
case controllers_client_FootballClients_getLocale50_route(params) => {
   call(params.fromQuery[String]("lang", None)) { (lang) =>
        controllers_client_FootballClients_getLocale50_invoker.call(controllers.client.FootballClients.getLocale(lang))
   }
}
        

// @LINE:93
case controllers_client_FootballClients_setLocale51_route(params) => {
   call(params.fromQuery[String]("lang", None)) { (lang) =>
        controllers_client_FootballClients_setLocale51_invoker.call(controllers.client.FootballClients.setLocale(lang))
   }
}
        

// @LINE:96
case controllers_Application_index52_route(params) => {
   call { 
        controllers_Application_index52_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:98
case controllers_Application_restricted53_route(params) => {
   call { 
        controllers_Application_restricted53_invoker.call(controllers.Application.restricted)
   }
}
        

// @LINE:99
case controllers_Application_jsRoutes54_route(params) => {
   call { 
        controllers_Application_jsRoutes54_invoker.call(controllers.Application.jsRoutes)
   }
}
        

// @LINE:101
case controllers_Application_profile55_route(params) => {
   call { 
        controllers_Application_profile55_invoker.call(controllers.Application.profile)
   }
}
        

// @LINE:103
case controllers_Application_login56_route(params) => {
   call { 
        controllers_Application_login56_invoker.call(controllers.Application.login)
   }
}
        

// @LINE:104
case controllers_Application_doLogin57_route(params) => {
   call { 
        controllers_Application_doLogin57_invoker.call(controllers.Application.doLogin)
   }
}
        

// @LINE:106
case com_feth_play_module_pa_controllers_Authenticate_logout58_route(params) => {
   call { 
        com_feth_play_module_pa_controllers_Authenticate_logout58_invoker.call(com.feth.play.module.pa.controllers.Authenticate.logout)
   }
}
        

// @LINE:107
case com_feth_play_module_pa_controllers_Authenticate_authenticate59_route(params) => {
   call(params.fromPath[String]("provider", None)) { (provider) =>
        com_feth_play_module_pa_controllers_Authenticate_authenticate59_invoker.call(com.feth.play.module.pa.controllers.Authenticate.authenticate(provider))
   }
}
        

// @LINE:109
case controllers_Application_signup60_route(params) => {
   call { 
        controllers_Application_signup60_invoker.call(controllers.Application.signup)
   }
}
        

// @LINE:110
case controllers_Application_doSignup61_route(params) => {
   call { 
        controllers_Application_doSignup61_invoker.call(controllers.Application.doSignup)
   }
}
        

// @LINE:112
case controllers_Signup_unverified62_route(params) => {
   call { 
        controllers_Signup_unverified62_invoker.call(controllers.Signup.unverified)
   }
}
        

// @LINE:113
case controllers_Signup_oAuthDenied63_route(params) => {
   call(params.fromPath[String]("provider", None)) { (provider) =>
        controllers_Signup_oAuthDenied63_invoker.call(controllers.Signup.oAuthDenied(provider))
   }
}
        

// @LINE:115
case controllers_Signup_verify64_route(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Signup_verify64_invoker.call(controllers.Signup.verify(token))
   }
}
        

// @LINE:116
case controllers_Signup_exists65_route(params) => {
   call { 
        controllers_Signup_exists65_invoker.call(controllers.Signup.exists)
   }
}
        

// @LINE:118
case controllers_Signup_resetPassword66_route(params) => {
   call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Signup_resetPassword66_invoker.call(controllers.Signup.resetPassword(token))
   }
}
        

// @LINE:119
case controllers_Signup_doResetPassword67_route(params) => {
   call { 
        controllers_Signup_doResetPassword67_invoker.call(controllers.Signup.doResetPassword)
   }
}
        

// @LINE:121
case controllers_Account_changePassword68_route(params) => {
   call { 
        controllers_Account_changePassword68_invoker.call(controllers.Account.changePassword)
   }
}
        

// @LINE:122
case controllers_Account_doChangePassword69_route(params) => {
   call { 
        controllers_Account_doChangePassword69_invoker.call(controllers.Account.doChangePassword)
   }
}
        

// @LINE:124
case controllers_Account_verifyEmail70_route(params) => {
   call { 
        controllers_Account_verifyEmail70_invoker.call(controllers.Account.verifyEmail)
   }
}
        

// @LINE:126
case controllers_Account_link71_route(params) => {
   call { 
        controllers_Account_link71_invoker.call(controllers.Account.link)
   }
}
        

// @LINE:128
case controllers_Account_askLink72_route(params) => {
   call { 
        controllers_Account_askLink72_invoker.call(controllers.Account.askLink)
   }
}
        

// @LINE:129
case controllers_Account_doLink73_route(params) => {
   call { 
        controllers_Account_doLink73_invoker.call(controllers.Account.doLink)
   }
}
        

// @LINE:131
case controllers_Account_askMerge74_route(params) => {
   call { 
        controllers_Account_askMerge74_invoker.call(controllers.Account.askMerge)
   }
}
        

// @LINE:132
case controllers_Account_doMerge75_route(params) => {
   call { 
        controllers_Account_doMerge75_invoker.call(controllers.Account.doMerge)
   }
}
        

// @LINE:134
case controllers_Signup_forgotPassword76_route(params) => {
   call(params.fromQuery[String]("email", Some(""))) { (email) =>
        controllers_Signup_forgotPassword76_invoker.call(controllers.Signup.forgotPassword(email))
   }
}
        

// @LINE:135
case controllers_Signup_doForgotPassword77_route(params) => {
   call { 
        controllers_Signup_doForgotPassword77_invoker.call(controllers.Signup.doForgotPassword)
   }
}
        

// @LINE:139
case controllers_CountriesView_list78_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_CountriesView_list78_invoker.call(controllers.CountriesView.list(p, s, o, f))
   }
}
        

// @LINE:140
case controllers_CountriesView_lsort79_route(params) => {
   call { 
        controllers_CountriesView_lsort79_invoker.call(controllers.CountriesView.lsort())
   }
}
        

// @LINE:142
case controllers_CountriesView_blank80_route(params) => {
   call { 
        controllers_CountriesView_blank80_invoker.call(controllers.CountriesView.blank())
   }
}
        

// @LINE:143
case controllers_CountriesView_submit81_route(params) => {
   call { 
        controllers_CountriesView_submit81_invoker.call(controllers.CountriesView.submit())
   }
}
        

// @LINE:145
case controllers_CountriesView_sort82_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_CountriesView_sort82_invoker.call(controllers.CountriesView.sort(ids))
   }
}
        

// @LINE:146
case controllers_CountriesView_edit83_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_edit83_invoker.call(controllers.CountriesView.edit(id))
   }
}
        

// @LINE:147
case controllers_CountriesView_update84_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_update84_invoker.call(controllers.CountriesView.update(id))
   }
}
        

// @LINE:149
case controllers_CountriesView_delete85_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_CountriesView_delete85_invoker.call(controllers.CountriesView.delete(id))
   }
}
        

// @LINE:153
case controllers_LanguagesView_list86_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_LanguagesView_list86_invoker.call(controllers.LanguagesView.list(p, s, o, f))
   }
}
        

// @LINE:154
case controllers_LanguagesView_lsort87_route(params) => {
   call { 
        controllers_LanguagesView_lsort87_invoker.call(controllers.LanguagesView.lsort())
   }
}
        

// @LINE:156
case controllers_LanguagesView_blank88_route(params) => {
   call { 
        controllers_LanguagesView_blank88_invoker.call(controllers.LanguagesView.blank())
   }
}
        

// @LINE:157
case controllers_LanguagesView_submit89_route(params) => {
   call { 
        controllers_LanguagesView_submit89_invoker.call(controllers.LanguagesView.submit())
   }
}
        

// @LINE:159
case controllers_LanguagesView_sort90_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_LanguagesView_sort90_invoker.call(controllers.LanguagesView.sort(ids))
   }
}
        

// @LINE:160
case controllers_LanguagesView_edit91_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_edit91_invoker.call(controllers.LanguagesView.edit(id))
   }
}
        

// @LINE:161
case controllers_LanguagesView_update92_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_update92_invoker.call(controllers.LanguagesView.update(id))
   }
}
        

// @LINE:163
case controllers_LanguagesView_delete93_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_LanguagesView_delete93_invoker.call(controllers.LanguagesView.delete(id))
   }
}
        

// @LINE:167
case controllers_TimezonesView_list94_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_TimezonesView_list94_invoker.call(controllers.TimezonesView.list(p, s, o, f))
   }
}
        

// @LINE:168
case controllers_TimezonesView_lsort95_route(params) => {
   call { 
        controllers_TimezonesView_lsort95_invoker.call(controllers.TimezonesView.lsort())
   }
}
        

// @LINE:170
case controllers_TimezonesView_blank96_route(params) => {
   call { 
        controllers_TimezonesView_blank96_invoker.call(controllers.TimezonesView.blank())
   }
}
        

// @LINE:171
case controllers_TimezonesView_submit97_route(params) => {
   call { 
        controllers_TimezonesView_submit97_invoker.call(controllers.TimezonesView.submit())
   }
}
        

// @LINE:173
case controllers_TimezonesView_sort98_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_TimezonesView_sort98_invoker.call(controllers.TimezonesView.sort(ids))
   }
}
        

// @LINE:174
case controllers_TimezonesView_edit99_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_edit99_invoker.call(controllers.TimezonesView.edit(id))
   }
}
        

// @LINE:175
case controllers_TimezonesView_update100_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_update100_invoker.call(controllers.TimezonesView.update(id))
   }
}
        

// @LINE:177
case controllers_TimezonesView_delete101_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_TimezonesView_delete101_invoker.call(controllers.TimezonesView.delete(id))
   }
}
        

// @LINE:181
case controllers_DevicesView_list102_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_DevicesView_list102_invoker.call(controllers.DevicesView.list(p, s, o, f))
   }
}
        

// @LINE:182
case controllers_DevicesView_lsort103_route(params) => {
   call { 
        controllers_DevicesView_lsort103_invoker.call(controllers.DevicesView.lsort())
   }
}
        

// @LINE:184
case controllers_DevicesView_blank104_route(params) => {
   call { 
        controllers_DevicesView_blank104_invoker.call(controllers.DevicesView.blank())
   }
}
        

// @LINE:185
case controllers_DevicesView_submit105_route(params) => {
   call { 
        controllers_DevicesView_submit105_invoker.call(controllers.DevicesView.submit())
   }
}
        

// @LINE:187
case controllers_DevicesView_sort106_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_DevicesView_sort106_invoker.call(controllers.DevicesView.sort(ids))
   }
}
        

// @LINE:188
case controllers_DevicesView_edit107_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_edit107_invoker.call(controllers.DevicesView.edit(id))
   }
}
        

// @LINE:189
case controllers_DevicesView_update108_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_update108_invoker.call(controllers.DevicesView.update(id))
   }
}
        

// @LINE:191
case controllers_DevicesView_delete109_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_DevicesView_delete109_invoker.call(controllers.DevicesView.delete(id))
   }
}
        

// @LINE:196
case controllers_InstancesView_list110_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_InstancesView_list110_invoker.call(controllers.InstancesView.list(p, s, o, f))
   }
}
        

// @LINE:197
case controllers_InstancesView_lsort111_route(params) => {
   call { 
        controllers_InstancesView_lsort111_invoker.call(controllers.InstancesView.lsort())
   }
}
        

// @LINE:199
case controllers_InstancesView_blank112_route(params) => {
   call { 
        controllers_InstancesView_blank112_invoker.call(controllers.InstancesView.blank())
   }
}
        

// @LINE:200
case controllers_InstancesView_submit113_route(params) => {
   call { 
        controllers_InstancesView_submit113_invoker.call(controllers.InstancesView.submit())
   }
}
        

// @LINE:202
case controllers_InstancesView_sort114_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_InstancesView_sort114_invoker.call(controllers.InstancesView.sort(ids))
   }
}
        

// @LINE:203
case controllers_InstancesView_edit115_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_edit115_invoker.call(controllers.InstancesView.edit(id))
   }
}
        

// @LINE:204
case controllers_InstancesView_update116_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_update116_invoker.call(controllers.InstancesView.update(id))
   }
}
        

// @LINE:206
case controllers_InstancesView_delete117_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_delete117_invoker.call(controllers.InstancesView.delete(id))
   }
}
        

// @LINE:210
case controllers_JobsView_list118_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_JobsView_list118_invoker.call(controllers.JobsView.list(p, s, o, f))
   }
}
        

// @LINE:211
case controllers_JobsView_lsort119_route(params) => {
   call { 
        controllers_JobsView_lsort119_invoker.call(controllers.JobsView.lsort())
   }
}
        

// @LINE:213
case controllers_JobsView_blank120_route(params) => {
   call { 
        controllers_JobsView_blank120_invoker.call(controllers.JobsView.blank())
   }
}
        

// @LINE:214
case controllers_JobsView_submit121_route(params) => {
   call { 
        controllers_JobsView_submit121_invoker.call(controllers.JobsView.submit())
   }
}
        

// @LINE:216
case controllers_JobsView_sort122_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_JobsView_sort122_invoker.call(controllers.JobsView.sort(ids))
   }
}
        

// @LINE:217
case controllers_JobsView_edit123_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_edit123_invoker.call(controllers.JobsView.edit(id))
   }
}
        

// @LINE:218
case controllers_JobsView_update124_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_update124_invoker.call(controllers.JobsView.update(id))
   }
}
        

// @LINE:220
case controllers_JobsView_delete125_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_delete125_invoker.call(controllers.JobsView.delete(id))
   }
}
        

// @LINE:224
case controllers_ConfigsView_list126_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("configKey")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_ConfigsView_list126_invoker.call(controllers.ConfigsView.list(p, s, o, f))
   }
}
        

// @LINE:225
case controllers_ConfigsView_lsort127_route(params) => {
   call { 
        controllers_ConfigsView_lsort127_invoker.call(controllers.ConfigsView.lsort())
   }
}
        

// @LINE:227
case controllers_ConfigsView_blank128_route(params) => {
   call { 
        controllers_ConfigsView_blank128_invoker.call(controllers.ConfigsView.blank())
   }
}
        

// @LINE:228
case controllers_ConfigsView_submit129_route(params) => {
   call { 
        controllers_ConfigsView_submit129_invoker.call(controllers.ConfigsView.submit())
   }
}
        

// @LINE:230
case controllers_ConfigsView_sort130_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_ConfigsView_sort130_invoker.call(controllers.ConfigsView.sort(ids))
   }
}
        

// @LINE:231
case controllers_ConfigsView_edit131_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_edit131_invoker.call(controllers.ConfigsView.edit(id))
   }
}
        

// @LINE:232
case controllers_ConfigsView_update132_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_update132_invoker.call(controllers.ConfigsView.update(id))
   }
}
        

// @LINE:234
case controllers_ConfigsView_delete133_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_delete133_invoker.call(controllers.ConfigsView.delete(id))
   }
}
        

// @LINE:238
case controllers_UsersView_list134_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_UsersView_list134_invoker.call(controllers.UsersView.list(p, s, o, f))
   }
}
        

// @LINE:243
case controllers_UsersView_edit135_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UsersView_edit135_invoker.call(controllers.UsersView.edit(id))
   }
}
        

// @LINE:244
case controllers_UsersView_update136_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UsersView_update136_invoker.call(controllers.UsersView.update(id))
   }
}
        

// @LINE:246
case controllers_UsersView_delete137_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UsersView_delete137_invoker.call(controllers.UsersView.delete(id))
   }
}
        

// @LINE:250
case controllers_client_FootballManager_getNewsById138_route(params) => {
   call(params.fromPath[Long]("idNews", None)) { (idNews) =>
        controllers_client_FootballManager_getNewsById138_invoker.call(controllers.client.FootballManager.getNewsById(idNews))
   }
}
        

// @LINE:251
case controllers_client_FootballManager_getNews139_route(params) => {
   call(params.fromPath[Integer]("idApp", None), Param[Integer]("offset", Right(0)), Param[Integer]("count", Right(20))) { (idApp, offset, count) =>
        controllers_client_FootballManager_getNews139_invoker.call(controllers.client.FootballManager.getNews(idApp, offset, count))
   }
}
        

// @LINE:252
case controllers_client_FootballManager_getNews140_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("offset", None), params.fromPath[Integer]("count", None)) { (idApp, offset, count) =>
        controllers_client_FootballManager_getNews140_invoker.call(controllers.client.FootballManager.getNews(idApp, offset, count))
   }
}
        

// @LINE:255
case controllers_client_FootballManager_getRecentNews141_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(true)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews141_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:256
case controllers_client_FootballManager_getRecentNews142_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(true)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews142_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:257
case controllers_client_FootballManager_getRecentNews143_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(true)), params.fromQuery[Boolean]("first", Some(false))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews143_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:258
case controllers_client_FootballManager_getRecentNews144_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(true)), params.fromQuery[Boolean]("first", Some(false))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews144_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:259
case controllers_client_FootballManager_getRecentNews145_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews145_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:260
case controllers_client_FootballManager_getRecentNews146_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews146_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:261
case controllers_client_FootballManager_getRecentNews147_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(false))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews147_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:262
case controllers_client_FootballManager_getRecentNews148_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("newsId", None), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(false))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews148_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:263
case controllers_client_FootballManager_getRecentNews149_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[Long]("newsId", Some(0)), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews149_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:264
case controllers_client_FootballManager_getRecentNews150_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[Long]("newsId", Some(0)), params.fromQuery[Boolean]("newest", Some(false)), params.fromQuery[Boolean]("first", Some(true))) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews150_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:265
case controllers_client_FootballManager_getRecentNews151_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("newsId", None), params.fromPath[Boolean]("newest", None), params.fromPath[Boolean]("first", None)) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews151_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:266
case controllers_client_FootballManager_getRecentNews152_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("newsId", None), params.fromPath[Boolean]("newest", None), params.fromPath[Boolean]("first", None)) { (idApp, idLanguage, newsId, newest, first) =>
        controllers_client_FootballManager_getRecentNews152_invoker.call(controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first))
   }
}
        

// @LINE:269
case controllers_client_FootballManager_getTeams153_route(params) => {
   call(params.fromPath[Long]("idCompetition", None)) { (idCompetition) =>
        controllers_client_FootballManager_getTeams153_invoker.call(controllers.client.FootballManager.getTeams(idCompetition))
   }
}
        

// @LINE:270
case controllers_client_FootballManager_getTeamsForApp154_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0))) { (idApp, pageSize, page) =>
        controllers_client_FootballManager_getTeamsForApp154_invoker.call(controllers.client.FootballManager.getTeamsForApp(idApp, pageSize, page))
   }
}
        

// @LINE:271
case controllers_client_FootballManager_getTeam155_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_client_FootballManager_getTeam155_invoker.call(controllers.client.FootballManager.getTeam(id))
   }
}
        

// @LINE:272
case controllers_client_FootballManager_getPhases156_route(params) => {
   call(params.fromPath[Long]("idComp", None), params.fromQuery[String]("sd", Some("")), params.fromQuery[String]("end", Some(""))) { (idComp, sd, end) =>
        controllers_client_FootballManager_getPhases156_invoker.call(controllers.client.FootballManager.getPhases(idComp, sd, end))
   }
}
        

// @LINE:273
case controllers_client_FootballManager_getPhase157_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_client_FootballManager_getPhase157_invoker.call(controllers.client.FootballManager.getPhase(id))
   }
}
        

// @LINE:274
case controllers_client_FootballManager_getGameMatches158_route(params) => {
   call(params.fromQuery[Long]("phase", Some(0)), params.fromQuery[String]("sd", Some("")), params.fromQuery[String]("ed", Some("")), params.fromQuery[Integer]("st", Some(0))) { (phase, sd, ed, st) =>
        controllers_client_FootballManager_getGameMatches158_invoker.call(controllers.client.FootballManager.getGameMatches(phase, sd, ed, st))
   }
}
        

// @LINE:275
case controllers_client_FootballManager_getGameMatchByExternalId159_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[Boolean]("events", Some(false))) { (id, events) =>
        controllers_client_FootballManager_getGameMatchByExternalId159_invoker.call(controllers.client.FootballManager.getGameMatchByExternalId(id, events))
   }
}
        

// @LINE:276
case controllers_client_FootballManager_getGameMatchByFifaId160_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[Boolean]("events", Some(false))) { (id, events) =>
        controllers_client_FootballManager_getGameMatchByFifaId160_invoker.call(controllers.client.FootballManager.getGameMatchByFifaId(id, events))
   }
}
        

// @LINE:277
case controllers_client_FootballManager_getGameMatch161_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[Boolean]("events", Some(false))) { (id, events) =>
        controllers_client_FootballManager_getGameMatch161_invoker.call(controllers.client.FootballManager.getGameMatch(id, events))
   }
}
        

// @LINE:278
case controllers_client_FootballManager_getEventsByExternalMatch162_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[String]("act", Some("")), params.fromQuery[String]("p", Some("")), params.fromQuery[String]("ts", Some("")), params.fromQuery[String]("te", Some(""))) { (id, act, p, ts, te) =>
        controllers_client_FootballManager_getEventsByExternalMatch162_invoker.call(controllers.client.FootballManager.getEventsByExternalMatch(id, act, p, ts, te))
   }
}
        

// @LINE:279
case controllers_client_FootballManager_getEventsByFifaMatch163_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[String]("act", Some("")), params.fromQuery[String]("p", Some("")), params.fromQuery[String]("ts", Some("")), params.fromQuery[String]("te", Some(""))) { (id, act, p, ts, te) =>
        controllers_client_FootballManager_getEventsByFifaMatch163_invoker.call(controllers.client.FootballManager.getEventsByFifaMatch(id, act, p, ts, te))
   }
}
        

// @LINE:280
case controllers_client_FootballManager_getEvents164_route(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[String]("act", Some("")), params.fromQuery[String]("p", Some("")), params.fromQuery[String]("ts", Some("")), params.fromQuery[String]("te", Some(""))) { (id, act, p, ts, te) =>
        controllers_client_FootballManager_getEvents164_invoker.call(controllers.client.FootballManager.getEvents(id, act, p, ts, te))
   }
}
        

// @LINE:281
case controllers_client_FootballManager_getRankingByIdPhase165_route(params) => {
   call(params.fromPath[String]("id", None), Param[Boolean]("ext", Right(false ))) { (id, ext) =>
        controllers_client_FootballManager_getRankingByIdPhase165_invoker.call(controllers.client.FootballManager.getRankingByIdPhase(id, ext))
   }
}
        

// @LINE:282
case controllers_client_FootballManager_getRankingByIdPhase166_route(params) => {
   call(params.fromPath[String]("id", None), Param[Boolean]("ext", Right(true ))) { (id, ext) =>
        controllers_client_FootballManager_getRankingByIdPhase166_invoker.call(controllers.client.FootballManager.getRankingByIdPhase(id, ext))
   }
}
        

// @LINE:283
case controllers_client_FootballManager_getGlobalRanking167_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_client_FootballManager_getGlobalRanking167_invoker.call(controllers.client.FootballManager.getGlobalRanking(id))
   }
}
        

// @LINE:285
case controllers_client_FootballManager_getFixturesByIDs168_route(params) => {
   call(params.fromPath[Integer]("idApp", None)) { (idApp) =>
        controllers_client_FootballManager_getFixturesByIDs168_invoker.call(controllers.client.FootballManager.getFixturesByIDs(idApp))
   }
}
        

// @LINE:286
case controllers_client_FootballManager_getTodayFinished169_route(params) => {
   call(params.fromPath[Long]("idCompetition", None)) { (idCompetition) =>
        controllers_client_FootballManager_getTodayFinished169_invoker.call(controllers.client.FootballManager.getTodayFinished(idCompetition))
   }
}
        

// @LINE:287
case controllers_client_FootballManager_getFinishedByDate170_route(params) => {
   call(params.fromPath[Long]("idCompetition", None), params.fromPath[String]("date", None)) { (idCompetition, date) =>
        controllers_client_FootballManager_getFinishedByDate170_invoker.call(controllers.client.FootballManager.getFinishedByDate(idCompetition, date))
   }
}
        

// @LINE:288
case controllers_client_FootballManager_getFixturesDate171_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[String]("date", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, date, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getFixturesDate171_invoker.call(controllers.client.FootballManager.getFixturesDate(idApp, date, idLanguage, timezoneName))
   }
}
        

// @LINE:289
case controllers_client_FootballManager_getFixturesDate172_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[String]("date", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, date, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getFixturesDate172_invoker.call(controllers.client.FootballManager.getFixturesDate(idApp, date, idLanguage, timezoneName))
   }
}
        

// @LINE:290
case controllers_client_FootballManager_getFixturesDateAll173_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[String]("date", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, date, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getFixturesDateAll173_invoker.call(controllers.client.FootballManager.getFixturesDateAll(idApp, date, idLanguage, timezoneName))
   }
}
        

// @LINE:291
case controllers_client_FootballManager_getFixturesDateAll174_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[String]("date", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, date, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getFixturesDateAll174_invoker.call(controllers.client.FootballManager.getFixturesDateAll(idApp, date, idLanguage, timezoneName))
   }
}
        

// @LINE:292
case controllers_client_FootballManager_getFixturesForCompetitionGroupByDate175_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Long]("idCompetition", None), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idCompetition, timezoneName) =>
        controllers_client_FootballManager_getFixturesForCompetitionGroupByDate175_invoker.call(controllers.client.FootballManager.getFixturesForCompetitionGroupByDate(idApp, idCompetition, timezoneName))
   }
}
        

// @LINE:293
case controllers_client_FootballManager_getFixturesGroupByDate176_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, timezoneName) =>
        controllers_client_FootballManager_getFixturesGroupByDate176_invoker.call(controllers.client.FootballManager.getFixturesGroupByDate(idApp, timezoneName))
   }
}
        

// @LINE:294
case controllers_client_FootballManager_getFixturesDatePaged177_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[String]("date", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idLanguage, date, pageSize, page, timezoneName) =>
        controllers_client_FootballManager_getFixturesDatePaged177_invoker.call(controllers.client.FootballManager.getFixturesDatePaged(idApp, idLanguage, date, pageSize, page, timezoneName))
   }
}
        

// @LINE:295
case controllers_client_FootballManager_getFixturesDatePaged178_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[String]("date", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idLanguage, date, pageSize, page, timezoneName) =>
        controllers_client_FootballManager_getFixturesDatePaged178_invoker.call(controllers.client.FootballManager.getFixturesDatePaged(idApp, idLanguage, date, pageSize, page, timezoneName))
   }
}
        

// @LINE:297
case controllers_client_FootballManager_getFixturesCompetitionDatePaged179_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idCompetition, date, pageSize, page, timezoneName) =>
        controllers_client_FootballManager_getFixturesCompetitionDatePaged179_invoker.call(controllers.client.FootballManager.getFixturesCompetitionDatePaged(idApp, idCompetition, date, pageSize, page, timezoneName))
   }
}
        

// @LINE:299
case controllers_client_FootballManager_getActiveCompetitions180_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(0)), params.fromQuery[Boolean]("ids", Some(true)), params.fromQuery[Boolean]("closestMatch", Some(false)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idLanguage, ids, closestMatch, timezoneName) =>
        controllers_client_FootballManager_getActiveCompetitions180_invoker.call(controllers.client.FootballManager.getActiveCompetitions(idApp, idLanguage, ids, closestMatch, timezoneName))
   }
}
        

// @LINE:300
case controllers_client_FootballManager_getActiveCompetitions181_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[Boolean]("ids", Some(false)), params.fromQuery[Boolean]("closestMatch", Some(false)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idLanguage, ids, closestMatch, timezoneName) =>
        controllers_client_FootballManager_getActiveCompetitions181_invoker.call(controllers.client.FootballManager.getActiveCompetitions(idApp, idLanguage, ids, closestMatch, timezoneName))
   }
}
        

// @LINE:301
case controllers_client_FootballManager_getActiveCompetitions182_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[Boolean]("ids", Some(false)), params.fromQuery[Boolean]("closestMatch", Some(false)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idLanguage, ids, closestMatch, timezoneName) =>
        controllers_client_FootballManager_getActiveCompetitions182_invoker.call(controllers.client.FootballManager.getActiveCompetitions(idApp, idLanguage, ids, closestMatch, timezoneName))
   }
}
        

// @LINE:302
case controllers_client_FootballManager_getPhasesToNotify183_route(params) => {
   call(params.fromPath[Integer]("idApp", None)) { (idApp) =>
        controllers_client_FootballManager_getPhasesToNotify183_invoker.call(controllers.client.FootballManager.getPhasesToNotify(idApp))
   }
}
        

// @LINE:303
case controllers_client_FootballManager_getPhasesForCompetition184_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idCompetition, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getPhasesForCompetition184_invoker.call(controllers.client.FootballManager.getPhasesForCompetition(idApp, idCompetition, idLanguage, timezoneName))
   }
}
        

// @LINE:304
case controllers_client_FootballManager_getPhasesForCompetition185_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[String]("timezoneName", Some(""))) { (idApp, idCompetition, idLanguage, timezoneName) =>
        controllers_client_FootballManager_getPhasesForCompetition185_invoker.call(controllers.client.FootballManager.getPhasesForCompetition(idApp, idCompetition, idLanguage, timezoneName))
   }
}
        

// @LINE:305
case controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition186_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromQuery[Integer]("idLanguage", Some(300))) { (idApp, idCompetition, date, idLanguage) =>
        controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition186_invoker.call(controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(idApp, idCompetition, date, idLanguage))
   }
}
        

// @LINE:306
case controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition187_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromPath[Integer]("idLanguage", None)) { (idApp, idCompetition, date, idLanguage) =>
        controllers_client_FootballManager_getCurrentAndLastPhaseForCompetition187_invoker.call(controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(idApp, idCompetition, date, idLanguage))
   }
}
        

// @LINE:309
case controllers_client_FootballManager_getMinuteToMinuteForCompetition188_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[Long]("idEvent", Some(0)), params.fromQuery[Boolean]("forward", Some(false))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition188_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:310
case controllers_client_FootballManager_getMinuteToMinuteForCompetition189_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[Long]("idEvent", Some(0)), params.fromQuery[Boolean]("forward", Some(false))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition189_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:311
case controllers_client_FootballManager_getMinuteToMinuteForCompetition190_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("idEvent", None), params.fromQuery[Boolean]("forward", Some(true))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition190_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:312
case controllers_client_FootballManager_getMinuteToMinuteForCompetition191_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("idEvent", None), params.fromQuery[Boolean]("forward", Some(true))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition191_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:313
case controllers_client_FootballManager_getMinuteToMinuteForCompetition192_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("idEvent", None), params.fromQuery[Boolean]("forward", Some(false))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition192_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:314
case controllers_client_FootballManager_getMinuteToMinuteForCompetition193_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Long]("idMatch", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("idEvent", None), params.fromQuery[Boolean]("forward", Some(false))) { (idApp, idCompetition, idMatch, idLanguage, idEvent, forward) =>
        controllers_client_FootballManager_getMinuteToMinuteForCompetition193_invoker.call(controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward))
   }
}
        

// @LINE:318
case controllers_client_FootballManager_getRankingsForPhase194_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(0))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase194_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:319
case controllers_client_FootballManager_getRankingsForPhase195_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(0))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase195_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:320
case controllers_client_FootballManager_getRankingsForPhase196_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromQuery[Long]("idPhase", Some(0)), params.fromQuery[Integer]("way", Some(0))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase196_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:321
case controllers_client_FootballManager_getRankingsForPhase197_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Integer]("idLanguage", None), params.fromQuery[Long]("idPhase", Some(0)), params.fromQuery[Integer]("way", Some(0))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase197_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:322
case controllers_client_FootballManager_getRankingsForPhase198_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(1))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase198_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:323
case controllers_client_FootballManager_getRankingsForPhase199_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(1))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase199_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:324
case controllers_client_FootballManager_getRankingsForPhase200_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(-1))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase200_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:325
case controllers_client_FootballManager_getRankingsForPhase201_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[Long]("idPhase", None), params.fromQuery[Integer]("way", Some(-1))) { (idApp, idCompetition, idLanguage, idPhase, way) =>
        controllers_client_FootballManager_getRankingsForPhase201_invoker.call(controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way))
   }
}
        

// @LINE:330
case controllers_client_FootballManager_getCompetitionTopScorersForClient202_route(params) => {
   call(params.fromPath[Integer]("idApp", None)) { (idApp) =>
        controllers_client_FootballManager_getCompetitionTopScorersForClient202_invoker.call(controllers.client.FootballManager.getCompetitionTopScorersForClient(idApp))
   }
}
        

// @LINE:331
case controllers_client_FootballManager_getTopScorers203_route(params) => {
   call(params.fromPath[Long]("idCompetition", None), params.fromPath[String]("date", None)) { (idCompetition, date) =>
        controllers_client_FootballManager_getTopScorers203_invoker.call(controllers.client.FootballManager.getTopScorers(idCompetition, date))
   }
}
        

// @LINE:332
case controllers_client_FootballManager_getTopScorersByCompetition204_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0))) { (idApp, pageSize, page) =>
        controllers_client_FootballManager_getTopScorersByCompetition204_invoker.call(controllers.client.FootballManager.getTopScorersByCompetition(idApp, pageSize, page))
   }
}
        

// @LINE:333
case controllers_client_FootballManager_getCompetitionTopScorers205_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[Integer]("pageSize", Some(0)), params.fromQuery[Integer]("page", Some(0))) { (idApp, idCompetition, pageSize, page) =>
        controllers_client_FootballManager_getCompetitionTopScorers205_invoker.call(controllers.client.FootballManager.getCompetitionTopScorers(idApp, idCompetition, pageSize, page))
   }
}
        

// @LINE:336
case controllers_client_FootballManager_getAllFixtures206_route(params) => {
   call(params.fromPath[Long]("idCompetition", None)) { (idCompetition) =>
        controllers_client_FootballManager_getAllFixtures206_invoker.call(controllers.client.FootballManager.getAllFixtures(idCompetition))
   }
}
        

// @LINE:339
case controllers_client_FootballManager_getRankings207_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromQuery[Integer]("idLanguage", Some(300)), params.fromPath[String]("formattedToday", None)) { (idApp, idLanguage, formattedToday) =>
        controllers_client_FootballManager_getRankings207_invoker.call(controllers.client.FootballManager.getRankings(idApp, idLanguage, formattedToday))
   }
}
        

// @LINE:340
case controllers_client_FootballManager_getRankings208_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idLanguage", None), params.fromPath[String]("formattedToday", None)) { (idApp, idLanguage, formattedToday) =>
        controllers_client_FootballManager_getRankings208_invoker.call(controllers.client.FootballManager.getRankings(idApp, idLanguage, formattedToday))
   }
}
        

// @LINE:343
case controllers_client_FootballManager_getPushableEventsForApp209_route(params) => {
   call(params.fromPath[Integer]("idApp", None)) { (idApp) =>
        controllers_client_FootballManager_getPushableEventsForApp209_invoker.call(controllers.client.FootballManager.getPushableEventsForApp(idApp))
   }
}
        

// @LINE:346
case controllers_client_FootballManager_getCalendar210_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromQuery[Long]("phase", Some(0)), params.fromQuery[String]("operator", Some("gt"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar210_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:347
case controllers_client_FootballManager_getCalendar211_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromQuery[Long]("phase", Some(0)), params.fromQuery[String]("operator", Some("lt"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar211_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:348
case controllers_client_FootballManager_getCalendar212_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromPath[String]("date", None), params.fromQuery[Long]("phase", Some(0)), params.fromQuery[String]("operator", Some("eq"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar212_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:349
case controllers_client_FootballManager_getCalendar213_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[String]("date", Some("")), params.fromPath[Long]("phase", None), params.fromQuery[String]("operator", Some("gt"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar213_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:350
case controllers_client_FootballManager_getCalendar214_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[String]("date", Some("")), params.fromPath[Long]("phase", None), params.fromQuery[String]("operator", Some("lt"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar214_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:351
case controllers_client_FootballManager_getCalendar215_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[String]("date", Some("")), params.fromPath[Long]("phase", None), params.fromQuery[String]("operator", Some("eq"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar215_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:352
case controllers_client_FootballManager_getCalendar216_route(params) => {
   call(params.fromPath[Integer]("idApp", None), params.fromPath[Integer]("idCompetition", None), params.fromQuery[String]("date", Some("")), params.fromQuery[Long]("phase", Some(0)), params.fromQuery[String]("operator", Some("gt"))) { (idApp, idCompetition, date, phase, operator) =>
        controllers_client_FootballManager_getCalendar216_invoker.call(controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator))
   }
}
        

// @LINE:354
case JobCore_Routes217(handler) => handler
        

// @LINE:355
case UpstreamAPIConnector_Routes218(handler) => handler
        
}

}
     