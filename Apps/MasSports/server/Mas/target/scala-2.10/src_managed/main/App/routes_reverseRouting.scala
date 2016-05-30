// @SOURCE:/Users/palenge/Hecticus/TIM_Naty/TimPanama/conf/App.routes
// @HASH:e39ba4e8bf7b621da3cfa49dd8d48b8ed0cee806
// @DATE:Mon May 30 11:03:11 VET 2016

import App.Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:107
// @LINE:106
package com.feth.play.module.pa.controllers {

// @LINE:107
// @LINE:106
class ReverseAuthenticate {


// @LINE:106
def logout(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/logout")
}
                        

// @LINE:107
def authenticate(provider:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/authenticate/" + implicitly[PathBindable[String]].unbind("provider", dynamicString(provider)))
}
                        

}
                          
}
                  

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:93
// @LINE:92
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:66
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
package controllers.client {

// @LINE:90
// @LINE:80
// @LINE:66
// @LINE:47
// @LINE:40
class ReverseClients {


// @LINE:90
// @LINE:47
def list(pageSize:Integer = 0, page:Integer = 0, pmc:Boolean = false): Call = {
   (pageSize: @unchecked, page: @unchecked, pmc: @unchecked) match {
// @LINE:47
case (pageSize, page, pmc)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/list" + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)), if(pmc == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("pmc", pmc)))))
                                         
   }
}
                                                

// @LINE:80
// @LINE:40
def delete(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:40
case (id)  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/delete/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:66
def getActiveLanguages(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/languages")
}
                        

}
                          

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:63
class ReverseFootballManager {


// @LINE:291
// @LINE:290
def getFixturesDateAll(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String = ""): Call = {
   (idApp: @unchecked, date: @unchecked, idLanguage: @unchecked, timezoneName: @unchecked) match {
// @LINE:290
case (idApp, date, idLanguage, timezoneName)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/all/date/get/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
                                         
   }
}
                                                

// @LINE:287
def getFinishedByDate(idCompetition:Long, date:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/finished/get/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)))
}
                        

// @LINE:279
def getEventsByFifaMatch(id:Long, act:String = "", p:String = "", ts:String = "", te:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/events/get/fifa/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(act == "") None else Some(implicitly[QueryStringBindable[String]].unbind("act", act)), if(p == "") None else Some(implicitly[QueryStringBindable[String]].unbind("p", p)), if(ts == "") None else Some(implicitly[QueryStringBindable[String]].unbind("ts", ts)), if(te == "") None else Some(implicitly[QueryStringBindable[String]].unbind("te", te)))))
}
                        

// @LINE:331
def getTopScorers(idCompetition:Long, date:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/players/topScorers/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)))
}
                        

// @LINE:286
def getTodayFinished(idCompetition:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/finished/get/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition))
}
                        

// @LINE:275
def getGameMatchByExternalId(id:Long, events:Boolean = false): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/get/ext/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(events == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("events", events)))))
}
                        

// @LINE:343
def getPushableEventsForApp(idApp:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/pushable/get/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp))
}
                        

// @LINE:340
// @LINE:339
def getRankings(idApp:Integer, idLanguage:Integer, formattedToday:String): Call = {
   (idApp: @unchecked, idLanguage: @unchecked, formattedToday: @unchecked) match {
// @LINE:339
case (idApp, idLanguage, formattedToday)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "api/v1/rankings/get/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[String]].unbind("formattedToday", dynamicString(formattedToday)) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)))))
                                         
   }
}
                                                

// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
def getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean, first:Boolean): Call = {
   (idApp: @unchecked, idLanguage: @unchecked, newsId: @unchecked, newest: @unchecked, first: @unchecked) match {
// @LINE:255
case (idApp, idLanguage, newsId, newest, first)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "newsapi/v1/news/scroll/up/first/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Long]].unbind("newsId", newsId) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(newest == true) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("newest", newest)), if(first == true) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("first", first)))))
                                         
   }
}
                                                

// @LINE:270
def getTeamsForApp(idApp:Integer, pageSize:Integer = 0, page:Integer = 0): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/team/app/all/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)))))
}
                        

// @LINE:278
def getEventsByExternalMatch(id:Long, act:String = "", p:String = "", ts:String = "", te:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/events/get/ext/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(act == "") None else Some(implicitly[QueryStringBindable[String]].unbind("act", act)), if(p == "") None else Some(implicitly[QueryStringBindable[String]].unbind("p", p)), if(ts == "") None else Some(implicitly[QueryStringBindable[String]].unbind("ts", ts)), if(te == "") None else Some(implicitly[QueryStringBindable[String]].unbind("te", te)))))
}
                        

// @LINE:295
// @LINE:294
def getFixturesDatePaged(idApp:Integer, idLanguage:Integer, date:String, pageSize:Integer = 0, page:Integer = 0, timezoneName:String = ""): Call = {
   (idApp: @unchecked, idLanguage: @unchecked, date: @unchecked, pageSize: @unchecked, page: @unchecked, timezoneName: @unchecked) match {
// @LINE:294
case (idApp, idLanguage, date, pageSize, page, timezoneName)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/date/paged/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
                                         
   }
}
                                                

// @LINE:276
def getGameMatchByFifaId(id:Long, events:Boolean = false): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/get/fifa/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(events == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("events", events)))))
}
                        

// @LINE:332
def getTopScorersByCompetition(idApp:Integer, pageSize:Integer = 0, page:Integer = 0): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/players/competitions/scorers/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)))))
}
                        

// @LINE:330
def getCompetitionTopScorersForClient(idApp:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/players/topScorers/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp))
}
                        

// @LINE:289
// @LINE:288
def getFixturesDate(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String = ""): Call = {
   (idApp: @unchecked, date: @unchecked, idLanguage: @unchecked, timezoneName: @unchecked) match {
// @LINE:288
case (idApp, date, idLanguage, timezoneName)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/date/get/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
                                         
   }
}
                                                

// @LINE:271
def getTeam(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/team/get/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:297
def getFixturesCompetitionDatePaged(idApp:Integer, idCompetition:Integer, date:String, pageSize:Integer = 0, page:Integer = 0, timezoneName:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/competition/date/paged/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
}
                        

// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
def getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer, idEvent:Long, forward:Boolean): Call = {
   (idApp: @unchecked, idCompetition: @unchecked, idMatch: @unchecked, idLanguage: @unchecked, idEvent: @unchecked, forward: @unchecked) match {
// @LINE:309
case (idApp, idCompetition, idMatch, idLanguage, idEvent, forward)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/mam/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[Long]].unbind("idMatch", idMatch) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(idEvent == 0) None else Some(implicitly[QueryStringBindable[Long]].unbind("idEvent", idEvent)), if(forward == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("forward", forward)))))
                                         
   }
}
                                                

// @LINE:277
def getGameMatch(id:Long, events:Boolean = false): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/get/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(events == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("events", events)))))
}
                        

// @LINE:273
def getPhase(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/phase/get/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:269
def getTeams(idCompetition:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/team/competition/all/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition))
}
                        

// @LINE:301
// @LINE:300
// @LINE:299
def getActiveCompetitions(idApp:Integer, idLanguage:Integer, ids:Boolean, closestMatch:Boolean = false, timezoneName:String = ""): Call = {
   (idApp: @unchecked, idLanguage: @unchecked, ids: @unchecked, closestMatch: @unchecked, timezoneName: @unchecked) match {
// @LINE:299
case (idApp, idLanguage, ids, closestMatch, timezoneName)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/competitions/list/ids/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + queryString(List(if(idLanguage == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(ids == true) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("ids", ids)), if(closestMatch == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("closestMatch", closestMatch)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
                                         
   }
}
                                                

// @LINE:252
// @LINE:251
def getNews(idApp:Integer, offset:Integer, count:Integer): Call = {
   (idApp: @unchecked, offset: @unchecked, count: @unchecked) match {
// @LINE:251
case (idApp, offset, count) if offset == 0 && count == 20 =>
  implicit val _rrc = new ReverseRouteContext(Map(("offset", 0), ("count", 20)))
  Call("GET", _prefix + { _defaultPrefix } + "newsapi/v1/news/search/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp))
                                         
// @LINE:252
case (idApp, offset, count)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "newsapi/v1/news/search/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("offset", offset) + "/" + implicitly[PathBindable[Integer]].unbind("count", count))
                                         
   }
}
                                                

// @LINE:333
def getCompetitionTopScorers(idApp:Integer, idCompetition:Integer, pageSize:Integer = 0, page:Integer = 0): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/players/competition/scorers/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)))))
}
                        

// @LINE:250
def getNewsById(idNews:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "newsapi/v1/news/get/" + implicitly[PathBindable[Long]].unbind("idNews", idNews))
}
                        

// @LINE:283
def getGlobalRanking(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/ranking/get/global/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:336
def getAllFixtures(idCompetition:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/calendar/get/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition))
}
                        

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
def getCalendar(idApp:Integer, idCompetition:Integer, date:String, phase:Long, operator:String): Call = {
   (idApp: @unchecked, idCompetition: @unchecked, date: @unchecked, phase: @unchecked, operator: @unchecked) match {
// @LINE:346
case (idApp, idCompetition, date, phase, operator)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "api/v1/calendar/get/date/gt/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(phase == 0) None else Some(implicitly[QueryStringBindable[Long]].unbind("phase", phase)), if(operator == "gt") None else Some(implicitly[QueryStringBindable[String]].unbind("operator", operator)))))
                                         
   }
}
                                                

// @LINE:293
def getFixturesGroupByDate(idApp:Integer, timezoneName:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/date/grouped/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + queryString(List(if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
}
                        

// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
def getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long, way:Integer): Call = {
   (idApp: @unchecked, idCompetition: @unchecked, idLanguage: @unchecked, idPhase: @unchecked, way: @unchecked) match {
// @LINE:318
case (idApp, idCompetition, idLanguage, idPhase, way)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/competitions/ranking/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[Long]].unbind("idPhase", idPhase) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(way == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("way", way)))))
                                         
   }
}
                                                

// @LINE:302
def getPhasesToNotify(idApp:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/competitions/phases/notify/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp))
}
                        

// @LINE:274
def getGameMatches(phase:Long = 0, sd:String = "", ed:String = "", st:Integer = 0): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/get" + queryString(List(if(phase == 0) None else Some(implicitly[QueryStringBindable[Long]].unbind("phase", phase)), if(sd == "") None else Some(implicitly[QueryStringBindable[String]].unbind("sd", sd)), if(ed == "") None else Some(implicitly[QueryStringBindable[String]].unbind("ed", ed)), if(st == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("st", st)))))
}
                        

// @LINE:282
// @LINE:281
def getRankingByIdPhase(id:String, ext:Boolean): Call = {
   (id: @unchecked, ext: @unchecked) match {
// @LINE:281
case (id, ext) if ext == false  =>
  implicit val _rrc = new ReverseRouteContext(Map(("ext", false )))
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/ranking/get/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
                                         
// @LINE:282
case (id, ext) if ext == true  =>
  implicit val _rrc = new ReverseRouteContext(Map(("ext", true )))
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/ranking/get/ext/" + implicitly[PathBindable[String]].unbind("id", dynamicString(id)))
                                         
   }
}
                                                

// @LINE:285
def getFixturesByIDs(idApp:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/get/ids/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp))
}
                        

// @LINE:292
def getFixturesForCompetitionGroupByDate(idApp:Integer, idCompetition:Long, timezoneName:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/competition/date/grouped/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Long]].unbind("idCompetition", idCompetition) + queryString(List(if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
}
                        

// @LINE:63
def getScorers(idClient:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/scorers/" + implicitly[PathBindable[Integer]].unbind("idClient", idClient))
}
                        

// @LINE:306
// @LINE:305
def getCurrentAndLastPhaseForCompetition(idApp:Integer, idCompetition:Integer, date:String, idLanguage:Integer): Call = {
   (idApp: @unchecked, idCompetition: @unchecked, date: @unchecked, idLanguage: @unchecked) match {
// @LINE:305
case (idApp, idCompetition, date, idLanguage)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/competitions/phases/latest/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)))))
                                         
   }
}
                                                

// @LINE:272
def getPhases(idComp:Long, sd:String = "", end:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/phase/getList/" + implicitly[PathBindable[Long]].unbind("idComp", idComp) + queryString(List(if(sd == "") None else Some(implicitly[QueryStringBindable[String]].unbind("sd", sd)), if(end == "") None else Some(implicitly[QueryStringBindable[String]].unbind("end", end)))))
}
                        

// @LINE:280
def getEvents(id:Long, act:String = "", p:String = "", ts:String = "", te:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/matches/events/get/" + implicitly[PathBindable[Long]].unbind("id", id) + queryString(List(if(act == "") None else Some(implicitly[QueryStringBindable[String]].unbind("act", act)), if(p == "") None else Some(implicitly[QueryStringBindable[String]].unbind("p", p)), if(ts == "") None else Some(implicitly[QueryStringBindable[String]].unbind("ts", ts)), if(te == "") None else Some(implicitly[QueryStringBindable[String]].unbind("te", te)))))
}
                        

// @LINE:304
// @LINE:303
def getPhasesForCompetition(idApp:Integer, idCompetition:Integer, idLanguage:Integer, timezoneName:String = ""): Call = {
   (idApp: @unchecked, idCompetition: @unchecked, idLanguage: @unchecked, timezoneName: @unchecked) match {
// @LINE:303
case (idApp, idCompetition, idLanguage, timezoneName)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "footballapi/v1/competitions/phases/" + implicitly[PathBindable[Integer]].unbind("idApp", idApp) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition) + queryString(List(if(idLanguage == 300) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idLanguage", idLanguage)), if(timezoneName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("timezoneName", timezoneName)))))
                                         
   }
}
                                                

}
                          

// @LINE:93
// @LINE:92
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
class ReverseFootballClients {


// @LINE:53
def dashboard(id:Integer, idLanguage:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/dashboard/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[Integer]].unbind("idLanguage", idLanguage))
}
                        

// @LINE:36
def createKraken(msisdn:String, passwd:String, usd:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/krakencreate/" + implicitly[PathBindable[String]].unbind("msisdn", dynamicString(msisdn)) + "/" + implicitly[PathBindable[String]].unbind("passwd", dynamicString(passwd)) + "/" + implicitly[PathBindable[String]].unbind("usd", dynamicString(usd)))
}
                        

// @LINE:84
// @LINE:51
def getBets(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:51
case (id)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/bets/get/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:77
// @LINE:38
def create(): Call = {
   () match {
// @LINE:38
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/create")
                                         
   }
}
                                                

// @LINE:50
def getBetsForDate(id:Integer, date:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/bets/get/date/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("date", dynamicString(date)))
}
                        

// @LINE:79
// @LINE:39
def update(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:39
case (id)  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/update/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:37
def downKraken(msisdn:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/krakendown/" + implicitly[PathBindable[String]].unbind("msisdn", dynamicString(msisdn)))
}
                        

// @LINE:35
def createKrakenweb(msisdn:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/krakencreateweb/" + implicitly[PathBindable[String]].unbind("msisdn", dynamicString(msisdn)))
}
                        

// @LINE:86
// @LINE:57
// @LINE:56
def getLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer): Call = {
   (id: @unchecked, idTournament: @unchecked, idPhase: @unchecked) match {
// @LINE:56
case (id, idTournament, idPhase)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/leaderboard/get/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[Integer]].unbind("idTournament", idTournament) + "/" + implicitly[PathBindable[Integer]].unbind("idPhase", idPhase))
                                         
   }
}
                                                

// @LINE:88
// @LINE:87
// @LINE:59
// @LINE:58
def getPersonalLeaderboardForClient(id:Integer, idTournament:Integer = 0, idPhase:Integer = 0, global:Boolean): Call = {
   (id: @unchecked, idTournament: @unchecked, idPhase: @unchecked, global: @unchecked) match {
// @LINE:58
case (id, idTournament, idPhase, global)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/leaderboard/personal/tournament/" + implicitly[PathBindable[Integer]].unbind("id", id) + queryString(List(if(idTournament == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idTournament", idTournament)), if(idPhase == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("idPhase", idPhase)), if(global == true) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("global", global)))))
                                         
   }
}
                                                

// @LINE:93
def setLocale(lang:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v2/locale.json" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("lang", lang)))))
}
                        

// @LINE:83
// @LINE:52
def createBets(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:52
case (id)  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/bets/create/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:82
def createBet(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v2/client/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/bet")
}
                        

// @LINE:85
// @LINE:49
def getBetsForCompetition(id:Integer, idCompetition:Integer): Call = {
   (id: @unchecked, idCompetition: @unchecked) match {
// @LINE:49
case (id, idCompetition)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/bets/get/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[Integer]].unbind("idCompetition", idCompetition))
                                         
   }
}
                                                

// @LINE:92
def getLocale(lang:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v2/locale.json" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("lang", lang)))))
}
                        

// @LINE:81
// @LINE:48
def getPushAlertsForClient(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:48
case (id)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/alerts/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:78
// @LINE:46
def get(id:Integer, upstreamChannel:String, pmc:Boolean = false): Call = {
   (id: @unchecked, upstreamChannel: @unchecked, pmc: @unchecked) match {
// @LINE:46
case (id, upstreamChannel, pmc)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/get/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("upstreamChannel", dynamicString(upstreamChannel)) + queryString(List(if(pmc == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("pmc", pmc)))))
                                         
   }
}
                                                

// @LINE:60
def getLeaderboardTotalForClient(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/clients/leaderboard/total/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

}
                          
}
                  

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
// @LINE:135
// @LINE:134
// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:89
// @LINE:71
// @LINE:30
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
package controllers {

// @LINE:30
class ReverseAssets {


// @LINE:30
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
class ReverseDevicesView {


// @LINE:188
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/devices/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:182
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/devices/lsort")
}
                        

// @LINE:185
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/devices")
}
                        

// @LINE:189
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/devices/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:187
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/devices/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:181
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/devices/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:191
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/devices/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:184
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/devices/blank")
}
                        

}
                          

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
class ReverseUsersView {


// @LINE:238
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/users/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:244
def update(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:246
def delete(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:243
def edit(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

}
                          

// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
class ReverseJobsView {


// @LINE:220
def delete(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/jobs/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:211
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/jobs/lsort")
}
                        

// @LINE:214
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/jobs")
}
                        

// @LINE:217
def edit(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/jobs/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:218
def update(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/jobs/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:216
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/jobs/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:210
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/jobs/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:213
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/jobs/blank")
}
                        

}
                          

// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
class ReverseCountriesView {


// @LINE:146
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/countries/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:140
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/countries/lsort")
}
                        

// @LINE:143
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/countries")
}
                        

// @LINE:147
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/countries/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:145
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/countries/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:139
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/countries/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:149
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/countries/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:142
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/countries/blank")
}
                        

}
                          

// @LINE:89
class ReverseUpstream {


// @LINE:89
def sendClientEvent(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "sportsapi/v2/client/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/upstream")
}
                        

}
                          

// @LINE:71
class ReverseImageProcessing {


// @LINE:71
def getLocalResourceByWidth(file:String, width:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sportsapi/v1/localimages/" + implicitly[PathBindable[String]].unbind("file", dynamicString(file)) + "/" + implicitly[PathBindable[Integer]].unbind("width", width))
}
                        

}
                          

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseWap {


// @LINE:18
def createClient(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "login/create")
}
                        

// @LINE:13
def competitions(route:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "competitions" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("route", route)))))
}
                        

// @LINE:14
def scorers(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "scorers" + queryString(List(Some(implicitly[QueryStringBindable[Integer]].unbind("id", id)))))
}
                        

// @LINE:12
def matches(id:Integer, page:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "matches" + queryString(List(Some(implicitly[QueryStringBindable[Integer]].unbind("id", id)), Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)))))
}
                        

// @LINE:15
def mtm(id:Integer, playframework_escape_match:Integer, event:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "mtm" + queryString(List(Some(implicitly[QueryStringBindable[Integer]].unbind("id", id)), Some(implicitly[QueryStringBindable[Integer]].unbind("match", playframework_escape_match)), Some(implicitly[QueryStringBindable[Integer]].unbind("event", event)))))
}
                        

// @LINE:11
def news(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "news/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:16
def getLogin(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                        

// @LINE:17
def getPassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "login/password")
}
                        

// @LINE:10
// @LINE:9
def index(): Call = {
   () match {
// @LINE:9
case ()  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix)
                                         
   }
}
                                                

}
                          

// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
class ReverseConfigsView {


// @LINE:234
def delete(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/configurations/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:225
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/configurations/lsort")
}
                        

// @LINE:228
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/configurations")
}
                        

// @LINE:231
def edit(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/configurations/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:232
def update(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/configurations/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:230
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/configurations/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:224
def list(p:Int = 0, s:String = "configKey", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/configurations/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "configKey") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:227
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/configurations/blank")
}
                        

}
                          

// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
class ReverseLanguagesView {


// @LINE:160
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/languages/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:154
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/languages/lsort")
}
                        

// @LINE:157
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/languages")
}
                        

// @LINE:161
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/languages/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:159
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/languages/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:153
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/languages/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:163
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/languages/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:156
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/languages/blank")
}
                        

}
                          

// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:21
// @LINE:20
// @LINE:7
class ReverseApplication {


// @LINE:110
def doSignup(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/signup")
}
                        

// @LINE:20
def checkFile(name:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "check/" + implicitly[PathBindable[String]].unbind("name", name))
}
                        

// @LINE:101
def profile(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/profile")
}
                        

// @LINE:7
def options(url:String): Call = {
   import ReverseRouteContext.empty
   Call("OPTIONS", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("url", url))
}
                        

// @LINE:99
def jsRoutes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/assets/javascript/routes.js")
}
                        

// @LINE:109
def signup(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/signup")
}
                        

// @LINE:104
def doLogin(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/login")
}
                        

// @LINE:98
def restricted(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/restricted")
}
                        

// @LINE:21
def getAppSettings(width:Integer, height:Integer, version:String, so:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "api/loading/" + implicitly[PathBindable[Integer]].unbind("width", width) + "/" + implicitly[PathBindable[Integer]].unbind("height", height) + "/" + implicitly[PathBindable[String]].unbind("version", dynamicString(version)) + "/" + implicitly[PathBindable[String]].unbind("so", dynamicString(so)))
}
                        

// @LINE:96
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin")
}
                        

// @LINE:103
def login(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/login")
}
                        

}
                          

// @LINE:135
// @LINE:134
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
class ReverseSignup {


// @LINE:116
def exists(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/exists")
}
                        

// @LINE:115
def verify(token:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/verify/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
}
                        

// @LINE:112
def unverified(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/unverified")
}
                        

// @LINE:113
def oAuthDenied(provider:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/authenticate/" + implicitly[PathBindable[String]].unbind("provider", dynamicString(provider)) + "/denied")
}
                        

// @LINE:118
def resetPassword(token:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/password/reset/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
}
                        

// @LINE:119
def doResetPassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/accounts/password/reset")
}
                        

// @LINE:135
def doForgotPassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/login/password/forgot")
}
                        

// @LINE:134
def forgotPassword(email:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/login/password/forgot" + queryString(List(if(email == "") None else Some(implicitly[QueryStringBindable[String]].unbind("email", email)))))
}
                        

}
                          

// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
class ReverseTimezonesView {


// @LINE:174
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/timezones/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:168
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/timezones/lsort")
}
                        

// @LINE:171
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/timezones")
}
                        

// @LINE:175
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/timezones/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:173
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/timezones/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:167
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/timezones/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:177
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/timezones/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:170
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/timezones/blank")
}
                        

}
                          

// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
class ReverseInstancesView {


// @LINE:203
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/instances/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:197
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/instances/lsort")
}
                        

// @LINE:200
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/instances")
}
                        

// @LINE:204
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/instances/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:202
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/instances/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:196
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/instances/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:206
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/instances/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:199
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/instances/blank")
}
                        

}
                          

// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
class ReverseAccount {


// @LINE:131
def askMerge(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/merge")
}
                        

// @LINE:132
def doMerge(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/accounts/merge")
}
                        

// @LINE:124
def verifyEmail(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/verify")
}
                        

// @LINE:128
def askLink(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/link")
}
                        

// @LINE:129
def doLink(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "accounts/link")
}
                        

// @LINE:121
def changePassword(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/password/change")
}
                        

// @LINE:126
def link(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "admin/accounts/add")
}
                        

// @LINE:122
def doChangePassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "admin/accounts/password/change")
}
                        

}
                          
}
                  


// @LINE:107
// @LINE:106
package com.feth.play.module.pa.controllers.javascript {
import ReverseRouteContext.empty

// @LINE:107
// @LINE:106
class ReverseAuthenticate {


// @LINE:106
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.feth.play.module.pa.controllers.Authenticate.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/logout"})
      }
   """
)
                        

// @LINE:107
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.feth.play.module.pa.controllers.Authenticate.authenticate",
   """
      function(provider) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/authenticate/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("provider", encodeURIComponent(provider))})
      }
   """
)
                        

}
              
}
        

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:93
// @LINE:92
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:66
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
package controllers.client.javascript {
import ReverseRouteContext.empty

// @LINE:90
// @LINE:80
// @LINE:66
// @LINE:47
// @LINE:40
class ReverseClients {


// @LINE:90
// @LINE:47
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.list",
   """
      function(pageSize, page, pmc) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/list" + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/clients" + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      }
   """
)
                        

// @LINE:80
// @LINE:40
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.delete",
   """
      function(id) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/delete/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      }
   """
)
                        

// @LINE:66
def getActiveLanguages : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.getActiveLanguages",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/languages"})
      }
   """
)
                        

}
              

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:63
class ReverseFootballManager {


// @LINE:291
// @LINE:290
def getFixturesDateAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesDateAll",
   """
      function(idApp, date, idLanguage, timezoneName) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/all/date/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/all/date/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      }
   """
)
                        

// @LINE:287
def getFinishedByDate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFinishedByDate",
   """
      function(idCompetition,date) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/finished/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date))})
      }
   """
)
                        

// @LINE:279
def getEventsByFifaMatch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getEventsByFifaMatch",
   """
      function(id,act,p,ts,te) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/events/get/fifa/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(act == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("act", act)), (p == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("p", p)), (ts == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("ts", ts)), (te == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("te", te))])})
      }
   """
)
                        

// @LINE:331
def getTopScorers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTopScorers",
   """
      function(idCompetition,date) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/players/topScorers/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date))})
      }
   """
)
                        

// @LINE:286
def getTodayFinished : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTodayFinished",
   """
      function(idCompetition) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/finished/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition)})
      }
   """
)
                        

// @LINE:275
def getGameMatchByExternalId : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getGameMatchByExternalId",
   """
      function(id,events) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/get/ext/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(events == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("events", events))])})
      }
   """
)
                        

// @LINE:343
def getPushableEventsForApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getPushableEventsForApp",
   """
      function(idApp) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/pushable/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp)})
      }
   """
)
                        

// @LINE:340
// @LINE:339
def getRankings : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getRankings",
   """
      function(idApp, idLanguage, formattedToday) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/rankings/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("formattedToday", encodeURIComponent(formattedToday)) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/rankings/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("formattedToday", encodeURIComponent(formattedToday))})
      }
      }
   """
)
                        

// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
def getRecentNews : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getRecentNews",
   """
      function(idApp, idLanguage, newsId, newest, first) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/up/first/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/up/first/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/up/rest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/up/rest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/down/first/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/down/first/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/down/rest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/down/rest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + _qS([(newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (newsId == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("newsId", newsId)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(newsId == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("newsId", newsId)), (newest == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("newest", newest)), (first == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("first", first))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("newest", newest) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("first", first) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/scroll/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("newsId", newsId) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("newest", newest) + "/" + (""" + implicitly[PathBindable[Boolean]].javascriptUnbind + """)("first", first)})
      }
      }
   """
)
                        

// @LINE:270
def getTeamsForApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTeamsForApp",
   """
      function(idApp,pageSize,page) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/team/app/all/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page))])})
      }
   """
)
                        

// @LINE:278
def getEventsByExternalMatch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getEventsByExternalMatch",
   """
      function(id,act,p,ts,te) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/events/get/ext/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(act == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("act", act)), (p == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("p", p)), (ts == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("ts", ts)), (te == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("te", te))])})
      }
   """
)
                        

// @LINE:295
// @LINE:294
def getFixturesDatePaged : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesDatePaged",
   """
      function(idApp, idLanguage, date, pageSize, page, timezoneName) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/date/paged/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/date/paged/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      }
   """
)
                        

// @LINE:276
def getGameMatchByFifaId : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getGameMatchByFifaId",
   """
      function(id,events) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/get/fifa/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(events == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("events", events))])})
      }
   """
)
                        

// @LINE:332
def getTopScorersByCompetition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTopScorersByCompetition",
   """
      function(idApp,pageSize,page) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/players/competitions/scorers/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page))])})
      }
   """
)
                        

// @LINE:330
def getCompetitionTopScorersForClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getCompetitionTopScorersForClient",
   """
      function(idApp) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/players/topScorers/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp)})
      }
   """
)
                        

// @LINE:289
// @LINE:288
def getFixturesDate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesDate",
   """
      function(idApp, date, idLanguage, timezoneName) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/date/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/date/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      }
   """
)
                        

// @LINE:271
def getTeam : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTeam",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/team/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:297
def getFixturesCompetitionDatePaged : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesCompetitionDatePaged",
   """
      function(idApp,idCompetition,date,pageSize,page,timezoneName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/competition/date/paged/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
   """
)
                        

// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
def getMinuteToMinuteForCompetition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getMinuteToMinuteForCompetition",
   """
      function(idApp, idCompetition, idMatch, idLanguage, idEvent, forward) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (idEvent == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("idEvent", idEvent)), (forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(idEvent == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("idEvent", idEvent)), (forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/next/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idEvent", idEvent) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/next/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idEvent", idEvent) + _qS([(forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/previous/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idEvent", idEvent) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/mam/previous/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idMatch", idMatch) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idEvent", idEvent) + _qS([(forward == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("forward", forward))])})
      }
      }
   """
)
                        

// @LINE:277
def getGameMatch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getGameMatch",
   """
      function(id,events) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(events == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("events", events))])})
      }
   """
)
                        

// @LINE:273
def getPhase : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getPhase",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/phase/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:269
def getTeams : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getTeams",
   """
      function(idCompetition) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/team/competition/all/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition)})
      }
   """
)
                        

// @LINE:301
// @LINE:300
// @LINE:299
def getActiveCompetitions : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getActiveCompetitions",
   """
      function(idApp, idLanguage, ids, closestMatch, timezoneName) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/list/ids/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (ids == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("ids", ids)), (closestMatch == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("closestMatch", closestMatch)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/list/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (ids == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("ids", ids)), (closestMatch == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("closestMatch", closestMatch)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/list/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(ids == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("ids", ids)), (closestMatch == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("closestMatch", closestMatch)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      }
   """
)
                        

// @LINE:252
// @LINE:251
def getNews : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getNews",
   """
      function(idApp, offset, count) {
      if (offset == """ + implicitly[JavascriptLitteral[Integer]].to(0) + """ && count == """ + implicitly[JavascriptLitteral[Integer]].to(20) + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/search/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/search/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("offset", offset) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("count", count)})
      }
      }
   """
)
                        

// @LINE:333
def getCompetitionTopScorers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getCompetitionTopScorers",
   """
      function(idApp,idCompetition,pageSize,page) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/players/competition/scorers/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page))])})
      }
   """
)
                        

// @LINE:250
def getNewsById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getNewsById",
   """
      function(idNews) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "newsapi/v1/news/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idNews", idNews)})
      }
   """
)
                        

// @LINE:283
def getGlobalRanking : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getGlobalRanking",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/ranking/get/global/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:336
def getAllFixtures : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getAllFixtures",
   """
      function(idCompetition) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/calendar/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition)})
      }
   """
)
                        

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
def getCalendar : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getCalendar",
   """
      function(idApp, idCompetition, date, phase, operator) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/date/gt/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(phase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("phase", phase)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/date/lt/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(phase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("phase", phase)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/date/eq/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(phase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("phase", phase)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/phase/gt/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("phase", phase) + _qS([(date == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("date", date)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/phase/lt/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("phase", phase) + _qS([(date == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("date", date)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/phase/eq/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("phase", phase) + _qS([(date == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("date", date)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/calendar/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + _qS([(date == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("date", date)), (phase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("phase", phase)), (operator == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("operator", operator))])})
      }
      }
   """
)
                        

// @LINE:293
def getFixturesGroupByDate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesGroupByDate",
   """
      function(idApp,timezoneName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/date/grouped/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + _qS([(timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
   """
)
                        

// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
def getRankingsForPhase : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getRankingsForPhase",
   """
      function(idApp, idCompetition, idLanguage, idPhase, way) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (idPhase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("idPhase", idPhase)), (way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(idPhase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("idPhase", idPhase)), (way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/next/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/next/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/previous/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/ranking/previous/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idPhase", idPhase) + _qS([(way == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("way", way))])})
      }
      }
   """
)
                        

// @LINE:302
def getPhasesToNotify : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getPhasesToNotify",
   """
      function(idApp) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/phases/notify/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp)})
      }
   """
)
                        

// @LINE:274
def getGameMatches : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getGameMatches",
   """
      function(phase,sd,ed,st) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/get" + _qS([(phase == null ? null : (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("phase", phase)), (sd == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("sd", sd)), (ed == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("ed", ed)), (st == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("st", st))])})
      }
   """
)
                        

// @LINE:282
// @LINE:281
def getRankingByIdPhase : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getRankingByIdPhase",
   """
      function(id, ext) {
      if (ext == """ + implicitly[JavascriptLitteral[Boolean]].to(false ) + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/ranking/get/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
      if (ext == """ + implicitly[JavascriptLitteral[Boolean]].to(true ) + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/ranking/get/ext/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
      }
   """
)
                        

// @LINE:285
def getFixturesByIDs : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesByIDs",
   """
      function(idApp) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/get/ids/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp)})
      }
   """
)
                        

// @LINE:292
def getFixturesForCompetitionGroupByDate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getFixturesForCompetitionGroupByDate",
   """
      function(idApp,idCompetition,timezoneName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/competition/date/grouped/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idCompetition", idCompetition) + _qS([(timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
   """
)
                        

// @LINE:63
def getScorers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getScorers",
   """
      function(idClient) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/scorers/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idClient", idClient)})
      }
   """
)
                        

// @LINE:306
// @LINE:305
def getCurrentAndLastPhaseForCompetition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition",
   """
      function(idApp, idCompetition, date, idLanguage) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/phases/latest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/phases/latest/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)})
      }
      }
   """
)
                        

// @LINE:272
def getPhases : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getPhases",
   """
      function(idComp,sd,end) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/phase/getList/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idComp", idComp) + _qS([(sd == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("sd", sd)), (end == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("end", end))])})
      }
   """
)
                        

// @LINE:280
def getEvents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getEvents",
   """
      function(id,act,p,ts,te) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/matches/events/get/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + _qS([(act == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("act", act)), (p == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("p", p)), (ts == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("ts", ts)), (te == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("te", te))])})
      }
   """
)
                        

// @LINE:304
// @LINE:303
def getPhasesForCompetition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballManager.getPhasesForCompetition",
   """
      function(idApp, idCompetition, idLanguage, timezoneName) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/phases/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + _qS([(idLanguage == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)), (timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "footballapi/v1/competitions/phases/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idApp", idApp) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage) + _qS([(timezoneName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("timezoneName", timezoneName))])})
      }
      }
   """
)
                        

}
              

// @LINE:93
// @LINE:92
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
class ReverseFootballClients {


// @LINE:53
def dashboard : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.dashboard",
   """
      function(id,idLanguage) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/dashboard/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idLanguage", idLanguage)})
      }
   """
)
                        

// @LINE:36
def createKraken : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.createKraken",
   """
      function(msisdn,passwd,usd) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/krakencreate/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("msisdn", encodeURIComponent(msisdn)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("passwd", encodeURIComponent(passwd)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("usd", encodeURIComponent(usd))})
      }
   """
)
                        

// @LINE:84
// @LINE:51
def getBets : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getBets",
   """
      function(id) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/bets/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/bets"})
      }
      }
   """
)
                        

// @LINE:77
// @LINE:38
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.create",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/create"})
      }
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client"})
      }
      }
   """
)
                        

// @LINE:50
def getBetsForDate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getBetsForDate",
   """
      function(id,date) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/bets/get/date/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("date", encodeURIComponent(date))})
      }
   """
)
                        

// @LINE:79
// @LINE:39
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.update",
   """
      function(id) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/update/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      }
   """
)
                        

// @LINE:37
def downKraken : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.downKraken",
   """
      function(msisdn) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/krakendown/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("msisdn", encodeURIComponent(msisdn))})
      }
   """
)
                        

// @LINE:35
def createKrakenweb : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.createKrakenweb",
   """
      function(msisdn) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/krakencreateweb/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("msisdn", encodeURIComponent(msisdn))})
      }
   """
)
                        

// @LINE:86
// @LINE:57
// @LINE:56
def getLeaderboardForClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getLeaderboardForClient",
   """
      function(id, idTournament, idPhase) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/leaderboard/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/leaderboard/global/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament) + _qS([(idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/leaderboard/global/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament) + _qS([(idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase))])})
      }
      }
   """
)
                        

// @LINE:88
// @LINE:87
// @LINE:59
// @LINE:58
def getPersonalLeaderboardForClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getPersonalLeaderboardForClient",
   """
      function(id, idTournament, idPhase, global) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/leaderboard/personal/tournament/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + _qS([(idTournament == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament)), (idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase)), (global == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("global", global))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/leaderboard/personal/phase/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + _qS([(idTournament == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament)), (idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase)), (global == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("global", global))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/leaderboard/personal/tournament" + _qS([(idTournament == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament)), (idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase)), (global == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("global", global))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/leaderboard/personal/phase" + _qS([(idTournament == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idTournament", idTournament)), (idPhase == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("idPhase", idPhase)), (global == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("global", global))])})
      }
      }
   """
)
                        

// @LINE:93
def setLocale : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.setLocale",
   """
      function(lang) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/locale.json" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("lang", lang)])})
      }
   """
)
                        

// @LINE:83
// @LINE:52
def createBets : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.createBets",
   """
      function(id) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/bets/create/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/bets"})
      }
      }
   """
)
                        

// @LINE:82
def createBet : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.createBet",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/bet"})
      }
   """
)
                        

// @LINE:85
// @LINE:49
def getBetsForCompetition : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getBetsForCompetition",
   """
      function(id, idCompetition) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/bets/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/bets/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("idCompetition", idCompetition)})
      }
      }
   """
)
                        

// @LINE:92
def getLocale : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getLocale",
   """
      function(lang) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/locale.json" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("lang", lang)])})
      }
   """
)
                        

// @LINE:81
// @LINE:48
def getPushAlertsForClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getPushAlertsForClient",
   """
      function(id) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/alerts/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/alerts"})
      }
      }
   """
)
                        

// @LINE:78
// @LINE:46
def get : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.get",
   """
      function(id, upstreamChannel, pmc) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("upstreamChannel", encodeURIComponent(upstreamChannel)) + _qS([(pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + _qS([(upstreamChannel == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("upstreamChannel", upstreamChannel)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      }
   """
)
                        

// @LINE:60
def getLeaderboardTotalForClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.FootballClients.getLeaderboardTotalForClient",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/clients/leaderboard/total/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              
}
        

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
// @LINE:135
// @LINE:134
// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:89
// @LINE:71
// @LINE:30
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:30
class ReverseAssets {


// @LINE:30
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
class ReverseDevicesView {


// @LINE:188
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:182
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/lsort"})
      }
   """
)
                        

// @LINE:185
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices"})
      }
   """
)
                        

// @LINE:189
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:187
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:181
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:191
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:184
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/devices/blank"})
      }
   """
)
                        

}
              

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
class ReverseUsersView {


// @LINE:238
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:244
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:246
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:243
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              

// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
class ReverseJobsView {


// @LINE:220
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:211
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/lsort"})
      }
   """
)
                        

// @LINE:214
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs"})
      }
   """
)
                        

// @LINE:217
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:218
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:216
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:210
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:213
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/jobs/blank"})
      }
   """
)
                        

}
              

// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
class ReverseCountriesView {


// @LINE:146
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:140
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/lsort"})
      }
   """
)
                        

// @LINE:143
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries"})
      }
   """
)
                        

// @LINE:147
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:145
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:139
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:149
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:142
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/countries/blank"})
      }
   """
)
                        

}
              

// @LINE:89
class ReverseUpstream {


// @LINE:89
def sendClientEvent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.sendClientEvent",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/upstream"})
      }
   """
)
                        

}
              

// @LINE:71
class ReverseImageProcessing {


// @LINE:71
def getLocalResourceByWidth : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ImageProcessing.getLocalResourceByWidth",
   """
      function(file,width) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sportsapi/v1/localimages/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", encodeURIComponent(file)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("width", width)})
      }
   """
)
                        

}
              

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseWap {


// @LINE:18
def createClient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.createClient",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login/create"})
      }
   """
)
                        

// @LINE:13
def competitions : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.competitions",
   """
      function(route) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "competitions" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("route", route)])})
      }
   """
)
                        

// @LINE:14
def scorers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.scorers",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "scorers" + _qS([(""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("id", id)])})
      }
   """
)
                        

// @LINE:12
def matches : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.matches",
   """
      function(id,page) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "matches" + _qS([(""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("id", id), (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)])})
      }
   """
)
                        

// @LINE:15
def mtm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.mtm",
   """
      function(id,match,event) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mtm" + _qS([(""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("id", id), (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("match", match), (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("event", event)])})
      }
   """
)
                        

// @LINE:11
def news : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.news",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:16
def getLogin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.getLogin",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:17
def getPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.getPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login/password"})
      }
   """
)
                        

// @LINE:10
// @LINE:9
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Wap.index",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "index"})
      }
      }
   """
)
                        

}
              

// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
class ReverseConfigsView {


// @LINE:234
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:225
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/lsort"})
      }
   """
)
                        

// @LINE:228
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations"})
      }
   """
)
                        

// @LINE:231
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:232
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:230
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:224
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:227
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/configurations/blank"})
      }
   """
)
                        

}
              

// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
class ReverseLanguagesView {


// @LINE:160
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:154
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/lsort"})
      }
   """
)
                        

// @LINE:157
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages"})
      }
   """
)
                        

// @LINE:161
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:159
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:153
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:163
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:156
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/languages/blank"})
      }
   """
)
                        

}
              

// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:21
// @LINE:20
// @LINE:7
class ReverseApplication {


// @LINE:110
def doSignup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.doSignup",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/signup"})
      }
   """
)
                        

// @LINE:20
def checkFile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.checkFile",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "check/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", name)})
      }
   """
)
                        

// @LINE:101
def profile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.profile",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/profile"})
      }
   """
)
                        

// @LINE:7
def options : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.options",
   """
      function(url) {
      return _wA({method:"OPTIONS", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("url", url)})
      }
   """
)
                        

// @LINE:99
def jsRoutes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.jsRoutes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/assets/javascript/routes.js"})
      }
   """
)
                        

// @LINE:109
def signup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.signup",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/signup"})
      }
   """
)
                        

// @LINE:104
def doLogin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.doLogin",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login"})
      }
   """
)
                        

// @LINE:98
def restricted : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.restricted",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/restricted"})
      }
   """
)
                        

// @LINE:21
def getAppSettings : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAppSettings",
   """
      function(width,height,version,so) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/loading/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("width", width) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("height", height) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("version", encodeURIComponent(version)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("so", encodeURIComponent(so))})
      }
   """
)
                        

// @LINE:96
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin"})
      }
   """
)
                        

// @LINE:103
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login"})
      }
   """
)
                        

}
              

// @LINE:135
// @LINE:134
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
class ReverseSignup {


// @LINE:116
def exists : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.exists",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/exists"})
      }
   """
)
                        

// @LINE:115
def verify : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.verify",
   """
      function(token) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/verify/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
      }
   """
)
                        

// @LINE:112
def unverified : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.unverified",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/unverified"})
      }
   """
)
                        

// @LINE:113
def oAuthDenied : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.oAuthDenied",
   """
      function(provider) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/authenticate/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("provider", encodeURIComponent(provider)) + "/denied"})
      }
   """
)
                        

// @LINE:118
def resetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.resetPassword",
   """
      function(token) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/password/reset/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
      }
   """
)
                        

// @LINE:119
def doResetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.doResetPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/password/reset"})
      }
   """
)
                        

// @LINE:135
def doForgotPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.doForgotPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login/password/forgot"})
      }
   """
)
                        

// @LINE:134
def forgotPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Signup.forgotPassword",
   """
      function(email) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login/password/forgot" + _qS([(email == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("email", email))])})
      }
   """
)
                        

}
              

// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
class ReverseTimezonesView {


// @LINE:174
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:168
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/lsort"})
      }
   """
)
                        

// @LINE:171
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones"})
      }
   """
)
                        

// @LINE:175
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:173
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:167
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:177
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:170
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/timezones/blank"})
      }
   """
)
                        

}
              

// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
class ReverseInstancesView {


// @LINE:203
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:197
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/lsort"})
      }
   """
)
                        

// @LINE:200
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances"})
      }
   """
)
                        

// @LINE:204
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:202
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:196
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:206
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:199
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/instances/blank"})
      }
   """
)
                        

}
              

// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
class ReverseAccount {


// @LINE:131
def askMerge : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.askMerge",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/merge"})
      }
   """
)
                        

// @LINE:132
def doMerge : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.doMerge",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/merge"})
      }
   """
)
                        

// @LINE:124
def verifyEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.verifyEmail",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/verify"})
      }
   """
)
                        

// @LINE:128
def askLink : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.askLink",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/link"})
      }
   """
)
                        

// @LINE:129
def doLink : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.doLink",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "accounts/link"})
      }
   """
)
                        

// @LINE:121
def changePassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.changePassword",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/password/change"})
      }
   """
)
                        

// @LINE:126
def link : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.link",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/add"})
      }
   """
)
                        

// @LINE:122
def doChangePassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Account.doChangePassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/accounts/password/change"})
      }
   """
)
                        

}
              
}
        


// @LINE:107
// @LINE:106
package com.feth.play.module.pa.controllers.ref {


// @LINE:107
// @LINE:106
class ReverseAuthenticate {


// @LINE:106
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   com.feth.play.module.pa.controllers.Authenticate.logout(), HandlerDef(this.getClass.getClassLoader, "", "com.feth.play.module.pa.controllers.Authenticate", "logout", Seq(), "GET", """""", _prefix + """admin/logout""")
)
                      

// @LINE:107
def authenticate(provider:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   com.feth.play.module.pa.controllers.Authenticate.authenticate(provider), HandlerDef(this.getClass.getClassLoader, "", "com.feth.play.module.pa.controllers.Authenticate", "authenticate", Seq(classOf[String]), "GET", """""", _prefix + """admin/authenticate/$provider<[^/]+>""")
)
                      

}
                          
}
        

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:93
// @LINE:92
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:66
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
package controllers.client.ref {


// @LINE:90
// @LINE:80
// @LINE:66
// @LINE:47
// @LINE:40
class ReverseClients {


// @LINE:47
def list(pageSize:Integer, page:Integer, pmc:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.list(pageSize, page, pmc), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]), "GET", """""", _prefix + """sportsapi/v1/clients/list""")
)
                      

// @LINE:40
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "delete", Seq(classOf[Integer]), "POST", """""", _prefix + """sportsapi/v1/clients/delete/$id<[^/]+>""")
)
                      

// @LINE:66
def getActiveLanguages(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.getActiveLanguages(), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "getActiveLanguages", Seq(), "GET", """""", _prefix + """sportsapi/v1/languages""")
)
                      

}
                          

// @LINE:352
// @LINE:351
// @LINE:350
// @LINE:349
// @LINE:348
// @LINE:347
// @LINE:346
// @LINE:343
// @LINE:340
// @LINE:339
// @LINE:336
// @LINE:333
// @LINE:332
// @LINE:331
// @LINE:330
// @LINE:325
// @LINE:324
// @LINE:323
// @LINE:322
// @LINE:321
// @LINE:320
// @LINE:319
// @LINE:318
// @LINE:314
// @LINE:313
// @LINE:312
// @LINE:311
// @LINE:310
// @LINE:309
// @LINE:306
// @LINE:305
// @LINE:304
// @LINE:303
// @LINE:302
// @LINE:301
// @LINE:300
// @LINE:299
// @LINE:297
// @LINE:295
// @LINE:294
// @LINE:293
// @LINE:292
// @LINE:291
// @LINE:290
// @LINE:289
// @LINE:288
// @LINE:287
// @LINE:286
// @LINE:285
// @LINE:283
// @LINE:282
// @LINE:281
// @LINE:280
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:271
// @LINE:270
// @LINE:269
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:263
// @LINE:262
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:252
// @LINE:251
// @LINE:250
// @LINE:63
class ReverseFootballManager {


// @LINE:290
def getFixturesDateAll(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesDateAll(idApp, date, idLanguage, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesDateAll", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/all/date/get/$idApp<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:287
def getFinishedByDate(idCompetition:Long, date:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFinishedByDate(idCompetition, date), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFinishedByDate", Seq(classOf[Long], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:279
def getEventsByFifaMatch(id:Long, act:String, p:String, ts:String, te:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getEventsByFifaMatch(id, act, p, ts, te), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getEventsByFifaMatch", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/events/get/fifa/$id<[^/]+>""")
)
                      

// @LINE:331
def getTopScorers(idCompetition:Long, date:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTopScorers(idCompetition, date), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTopScorers", Seq(classOf[Long], classOf[String]), "GET", """""", _prefix + """footballapi/v1/players/topScorers/$idCompetition<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:286
def getTodayFinished(idCompetition:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTodayFinished(idCompetition), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTodayFinished", Seq(classOf[Long]), "GET", """""", _prefix + """footballapi/v1/matches/finished/get/$idCompetition<[^/]+>""")
)
                      

// @LINE:275
def getGameMatchByExternalId(id:Long, events:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getGameMatchByExternalId(id, events), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getGameMatchByExternalId", Seq(classOf[Long], classOf[Boolean]), "GET", """""", _prefix + """footballapi/v1/matches/get/ext/$id<[^/]+>""")
)
                      

// @LINE:343
def getPushableEventsForApp(idApp:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getPushableEventsForApp(idApp), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getPushableEventsForApp", Seq(classOf[Integer]), "GET", """
#pushableEvents""", _prefix + """footballapi/v1/pushable/get/$idApp<[^/]+>""")
)
                      

// @LINE:339
def getRankings(idApp:Integer, idLanguage:Integer, formattedToday:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getRankings(idApp, idLanguage, formattedToday), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getRankings", Seq(classOf[Integer], classOf[Integer], classOf[String]), "GET", """#football manager""", _prefix + """api/v1/rankings/get/$idApp<[^/]+>/$formattedToday<[^/]+>""")
)
                      

// @LINE:255
def getRecentNews(idApp:Integer, idLanguage:Integer, newsId:Long, newest:Boolean, first:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getRecentNews(idApp, idLanguage, newsId, newest, first), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getRecentNews", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Boolean], classOf[Boolean]), "GET", """NewsScroll""", _prefix + """newsapi/v1/news/scroll/up/first/$idApp<[^/]+>/$newsId<[^/]+>""")
)
                      

// @LINE:270
def getTeamsForApp(idApp:Integer, pageSize:Integer, page:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTeamsForApp(idApp, pageSize, page), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTeamsForApp", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/team/app/all/$idApp<[^/]+>""")
)
                      

// @LINE:278
def getEventsByExternalMatch(id:Long, act:String, p:String, ts:String, te:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getEventsByExternalMatch(id, act, p, ts, te), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getEventsByExternalMatch", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/events/get/ext/$id<[^/]+>""")
)
                      

// @LINE:294
def getFixturesDatePaged(idApp:Integer, idLanguage:Integer, date:String, pageSize:Integer, page:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesDatePaged(idApp, idLanguage, date, pageSize, page, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesDatePaged", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer], classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/date/paged/$idApp<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:276
def getGameMatchByFifaId(id:Long, events:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getGameMatchByFifaId(id, events), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getGameMatchByFifaId", Seq(classOf[Long], classOf[Boolean]), "GET", """""", _prefix + """footballapi/v1/matches/get/fifa/$id<[^/]+>""")
)
                      

// @LINE:332
def getTopScorersByCompetition(idApp:Integer, pageSize:Integer, page:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTopScorersByCompetition(idApp, pageSize, page), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTopScorersByCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/players/competitions/scorers/$idApp<[^/]+>""")
)
                      

// @LINE:330
def getCompetitionTopScorersForClient(idApp:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getCompetitionTopScorersForClient(idApp), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getCompetitionTopScorersForClient", Seq(classOf[Integer]), "GET", """
#new football api
#players""", _prefix + """footballapi/v1/players/topScorers/$idApp<[^/]+>""")
)
                      

// @LINE:288
def getFixturesDate(idApp:Integer, date:String, idLanguage:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesDate(idApp, date, idLanguage, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesDate", Seq(classOf[Integer], classOf[String], classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/date/get/$idApp<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:271
def getTeam(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTeam(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTeam", Seq(classOf[Long]), "GET", """""", _prefix + """footballapi/v1/team/get/$id<[^/]+>""")
)
                      

// @LINE:297
def getFixturesCompetitionDatePaged(idApp:Integer, idCompetition:Integer, date:String, pageSize:Integer, page:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesCompetitionDatePaged(idApp, idCompetition, date, pageSize, page, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesCompetitionDatePaged", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer], classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/competition/date/paged/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:309
def getMinuteToMinuteForCompetition(idApp:Integer, idCompetition:Integer, idMatch:Long, idLanguage:Integer, idEvent:Long, forward:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getMinuteToMinuteForCompetition(idApp, idCompetition, idMatch, idLanguage, idEvent, forward), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getMinuteToMinuteForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer], classOf[Long], classOf[Boolean]), "GET", """MaM""", _prefix + """footballapi/v1/matches/mam/$idApp<[^/]+>/$idCompetition<[^/]+>/$idMatch<[^/]+>""")
)
                      

// @LINE:277
def getGameMatch(id:Long, events:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getGameMatch(id, events), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getGameMatch", Seq(classOf[Long], classOf[Boolean]), "GET", """""", _prefix + """footballapi/v1/matches/get/$id<[^/]+>""")
)
                      

// @LINE:273
def getPhase(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getPhase(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getPhase", Seq(classOf[Long]), "GET", """""", _prefix + """footballapi/v1/phase/get/$id<[^/]+>""")
)
                      

// @LINE:269
def getTeams(idCompetition:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getTeams(idCompetition), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getTeams", Seq(classOf[Long]), "GET", """
#afp futbol""", _prefix + """footballapi/v1/team/competition/all/$idCompetition<[^/]+>""")
)
                      

// @LINE:299
def getActiveCompetitions(idApp:Integer, idLanguage:Integer, ids:Boolean, closestMatch:Boolean, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getActiveCompetitions(idApp, idLanguage, ids, closestMatch, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getActiveCompetitions", Seq(classOf[Integer], classOf[Integer], classOf[Boolean], classOf[Boolean], classOf[String]), "GET", """""", _prefix + """footballapi/v1/competitions/list/ids/$idApp<[^/]+>""")
)
                      

// @LINE:251
def getNews(idApp:Integer, offset:Integer, count:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getNews(idApp, offset, count), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getNews", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """newsapi/v1/news/search/$idApp<[^/]+>""")
)
                      

// @LINE:333
def getCompetitionTopScorers(idApp:Integer, idCompetition:Integer, pageSize:Integer, page:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getCompetitionTopScorers(idApp, idCompetition, pageSize, page), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getCompetitionTopScorers", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/players/competition/scorers/$idApp<[^/]+>/$idCompetition<[^/]+>""")
)
                      

// @LINE:250
def getNewsById(idNews:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getNewsById(idNews), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getNewsById", Seq(classOf[Long]), "GET", """""", _prefix + """newsapi/v1/news/get/$idNews<[^/]+>""")
)
                      

// @LINE:283
def getGlobalRanking(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getGlobalRanking(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getGlobalRanking", Seq(classOf[Long]), "GET", """""", _prefix + """footballapi/v1/ranking/get/global/$id<[^/]+>""")
)
                      

// @LINE:336
def getAllFixtures(idCompetition:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getAllFixtures(idCompetition), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getAllFixtures", Seq(classOf[Long]), "GET", """
#calendar""", _prefix + """footballapi/v1/calendar/get/$idCompetition<[^/]+>""")
)
                      

// @LINE:346
def getCalendar(idApp:Integer, idCompetition:Integer, date:String, phase:Long, operator:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getCalendar(idApp, idCompetition, date, phase, operator), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getCalendar", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Long], classOf[String]), "GET", """
#calendar""", _prefix + """api/v1/calendar/get/date/gt/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:293
def getFixturesGroupByDate(idApp:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesGroupByDate(idApp, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesGroupByDate", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/date/grouped/$idApp<[^/]+>""")
)
                      

// @LINE:318
def getRankingsForPhase(idApp:Integer, idCompetition:Integer, idLanguage:Integer, idPhase:Long, way:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getRankingsForPhase(idApp, idCompetition, idLanguage, idPhase, way), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getRankingsForPhase", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Long], classOf[Integer]), "GET", """

#NEW VERSION""", _prefix + """footballapi/v1/competitions/ranking/$idApp<[^/]+>/$idCompetition<[^/]+>/$idPhase<[^/]+>""")
)
                      

// @LINE:302
def getPhasesToNotify(idApp:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getPhasesToNotify(idApp), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getPhasesToNotify", Seq(classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/competitions/phases/notify/$idApp<[^/]+>""")
)
                      

// @LINE:274
def getGameMatches(phase:Long, sd:String, ed:String, st:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getGameMatches(phase, sd, ed, st), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getGameMatches", Seq(classOf[Long], classOf[String], classOf[String], classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/matches/get""")
)
                      

// @LINE:281
def getRankingByIdPhase(id:String, ext:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getRankingByIdPhase(id, ext), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getRankingByIdPhase", Seq(classOf[String], classOf[Boolean]), "GET", """""", _prefix + """footballapi/v1/ranking/get/$id<[^/]+>""")
)
                      

// @LINE:285
def getFixturesByIDs(idApp:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesByIDs(idApp), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesByIDs", Seq(classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/matches/get/ids/$idApp<[^/]+>""")
)
                      

// @LINE:292
def getFixturesForCompetitionGroupByDate(idApp:Integer, idCompetition:Long, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getFixturesForCompetitionGroupByDate(idApp, idCompetition, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getFixturesForCompetitionGroupByDate", Seq(classOf[Integer], classOf[Long], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/competition/date/grouped/$idApp<[^/]+>/$idCompetition<[^/]+>""")
)
                      

// @LINE:63
def getScorers(idClient:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getScorers(idClient), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getScorers", Seq(classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/scorers/$idClient<[^/]+>""")
)
                      

// @LINE:305
def getCurrentAndLastPhaseForCompetition(idApp:Integer, idCompetition:Integer, date:String, idLanguage:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getCurrentAndLastPhaseForCompetition(idApp, idCompetition, date, idLanguage), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getCurrentAndLastPhaseForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[Integer]), "GET", """""", _prefix + """footballapi/v1/competitions/phases/latest/$idApp<[^/]+>/$idCompetition<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:272
def getPhases(idComp:Long, sd:String, end:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getPhases(idComp, sd, end), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getPhases", Seq(classOf[Long], classOf[String], classOf[String]), "GET", """""", _prefix + """footballapi/v1/phase/getList/$idComp<[^/]+>""")
)
                      

// @LINE:280
def getEvents(id:Long, act:String, p:String, ts:String, te:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getEvents(id, act, p, ts, te), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getEvents", Seq(classOf[Long], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """footballapi/v1/matches/events/get/$id<[^/]+>""")
)
                      

// @LINE:303
def getPhasesForCompetition(idApp:Integer, idCompetition:Integer, idLanguage:Integer, timezoneName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballManager.getPhasesForCompetition(idApp, idCompetition, idLanguage, timezoneName), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballManager", "getPhasesForCompetition", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[String]), "GET", """""", _prefix + """footballapi/v1/competitions/phases/$idApp<[^/]+>/$idCompetition<[^/]+>""")
)
                      

}
                          

// @LINE:93
// @LINE:92
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:56
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
class ReverseFootballClients {


// @LINE:53
def dashboard(id:Integer, idLanguage:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.dashboard(id, idLanguage), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "dashboard", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/dashboard/$id<[^/]+>/$idLanguage<[^/]+>""")
)
                      

// @LINE:36
def createKraken(msisdn:String, passwd:String, usd:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.createKraken(msisdn, passwd, usd), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "createKraken", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """sportsapi/v1/clients/krakencreate/$msisdn<[^/]+>/$passwd<[^/]+>/$usd<[^/]+>""")
)
                      

// @LINE:51
def getBets(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getBets(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getBets", Seq(classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/bets/get/$id<[^/]+>""")
)
                      

// @LINE:38
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.create(), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "create", Seq(), "POST", """""", _prefix + """sportsapi/v1/clients/create""")
)
                      

// @LINE:50
def getBetsForDate(id:Integer, date:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getBetsForDate(id, date), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getBetsForDate", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """sportsapi/v1/clients/bets/get/date/$id<[^/]+>/$date<[^/]+>""")
)
                      

// @LINE:39
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """sportsapi/v1/clients/update/$id<[^/]+>""")
)
                      

// @LINE:37
def downKraken(msisdn:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.downKraken(msisdn), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "downKraken", Seq(classOf[String]), "GET", """""", _prefix + """sportsapi/v1/clients/krakendown/$msisdn<[^/]+>""")
)
                      

// @LINE:35
def createKrakenweb(msisdn:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.createKrakenweb(msisdn), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "createKrakenweb", Seq(classOf[String]), "GET", """Clients""", _prefix + """sportsapi/v1/clients/krakencreateweb/$msisdn<[^/]+>""")
)
                      

// @LINE:56
def getLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getLeaderboardForClient(id, idTournament, idPhase), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/leaderboard/get/$id<[^/]+>/$idTournament<[^/]+>/$idPhase<[^/]+>""")
)
                      

// @LINE:58
def getPersonalLeaderboardForClient(id:Integer, idTournament:Integer, idPhase:Integer, global:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getPersonalLeaderboardForClient(id, idTournament, idPhase, global), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getPersonalLeaderboardForClient", Seq(classOf[Integer], classOf[Integer], classOf[Integer], classOf[Boolean]), "GET", """""", _prefix + """sportsapi/v1/clients/leaderboard/personal/tournament/$id<[^/]+>""")
)
                      

// @LINE:93
def setLocale(lang:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.setLocale(lang), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "setLocale", Seq(classOf[String]), "POST", """""", _prefix + """sportsapi/v2/locale.json""")
)
                      

// @LINE:52
def createBets(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.createBets(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "createBets", Seq(classOf[Integer]), "POST", """""", _prefix + """sportsapi/v1/clients/bets/create/$id<[^/]+>""")
)
                      

// @LINE:82
def createBet(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.createBet(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "createBet", Seq(classOf[Integer]), "POST", """""", _prefix + """sportsapi/v2/client/$id<[^/]+>/bet""")
)
                      

// @LINE:49
def getBetsForCompetition(id:Integer, idCompetition:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getBetsForCompetition(id, idCompetition), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getBetsForCompetition", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/bets/get/$id<[^/]+>/$idCompetition<[^/]+>""")
)
                      

// @LINE:92
def getLocale(lang:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getLocale(lang), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getLocale", Seq(classOf[String]), "GET", """""", _prefix + """sportsapi/v2/locale.json""")
)
                      

// @LINE:48
def getPushAlertsForClient(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getPushAlertsForClient(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getPushAlertsForClient", Seq(classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/alerts/$id<[^/]+>""")
)
                      

// @LINE:46
def get(id:Integer, upstreamChannel:String, pmc:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.get(id, upstreamChannel, pmc), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]), "GET", """""", _prefix + """sportsapi/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>""")
)
                      

// @LINE:60
def getLeaderboardTotalForClient(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.FootballClients.getLeaderboardTotalForClient(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.FootballClients", "getLeaderboardTotalForClient", Seq(classOf[Integer]), "GET", """""", _prefix + """sportsapi/v1/clients/leaderboard/total/$id<[^/]+>""")
)
                      

}
                          
}
        

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
// @LINE:135
// @LINE:134
// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:89
// @LINE:71
// @LINE:30
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
package controllers.ref {


// @LINE:30
class ReverseAssets {


// @LINE:30
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:191
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:185
// @LINE:184
// @LINE:182
// @LINE:181
class ReverseDevicesView {


// @LINE:188
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """admin/devices/$id<[^/]+>""")
)
                      

// @LINE:182
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "lsort", Seq(), "GET", """""", _prefix + """admin/devices/lsort""")
)
                      

// @LINE:185
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "submit", Seq(), "POST", """""", _prefix + """admin/devices""")
)
                      

// @LINE:189
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """admin/devices/$id<[^/]+>""")
)
                      

// @LINE:187
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "sort", Seq(classOf[String]), "GET", """ Edit existing DevicesView""", _prefix + """admin/devices/sort/$ids<[^/]+>""")
)
                      

// @LINE:181
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """DevicesView
 DevicesView list (look at the default values for pagination parameters)""", _prefix + """admin/devices/list""")
)
                      

// @LINE:191
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a DevicesView""", _prefix + """admin/devices/$id<[^/]+>/delete""")
)
                      

// @LINE:184
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "blank", Seq(), "GET", """ Add DevicesView""", _prefix + """admin/devices/blank""")
)
                      

}
                          

// @LINE:246
// @LINE:244
// @LINE:243
// @LINE:238
class ReverseUsersView {


// @LINE:238
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.UsersView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """UsersView
 UsersView list (look at the default values for pagination parameters)""", _prefix + """admin/users/list""")
)
                      

// @LINE:244
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.UsersView", "update", Seq(classOf[Long]), "POST", """""", _prefix + """admin/users/$id<[^/]+>""")
)
                      

// @LINE:246
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.UsersView", "delete", Seq(classOf[Long]), "POST", """ Delete a UsersView""", _prefix + """admin/users/$id<[^/]+>/delete""")
)
                      

// @LINE:243
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.UsersView", "edit", Seq(classOf[Long]), "GET", """GET     /admin/users/lsort		                                                                        controllers.UsersView.lsort()
 Add UsersView
 Edit existing UsersView
GET     /admin/users/sort/:ids	                                                                        controllers.UsersView.sort(ids:String)""", _prefix + """admin/users/$id<[^/]+>""")
)
                      

}
                          

// @LINE:220
// @LINE:218
// @LINE:217
// @LINE:216
// @LINE:214
// @LINE:213
// @LINE:211
// @LINE:210
class ReverseJobsView {


// @LINE:220
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "delete", Seq(classOf[Long]), "POST", """ Delete a JobsView""", _prefix + """admin/jobs/$id<[^/]+>/delete""")
)
                      

// @LINE:211
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "lsort", Seq(), "GET", """""", _prefix + """admin/jobs/lsort""")
)
                      

// @LINE:214
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "submit", Seq(), "POST", """""", _prefix + """admin/jobs""")
)
                      

// @LINE:217
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """admin/jobs/$id<[^/]+>""")
)
                      

// @LINE:218
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "update", Seq(classOf[Long]), "POST", """""", _prefix + """admin/jobs/$id<[^/]+>""")
)
                      

// @LINE:216
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "sort", Seq(classOf[String]), "GET", """ Edit existing JobsView""", _prefix + """admin/jobs/sort/$ids<[^/]+>""")
)
                      

// @LINE:210
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """JobsView
 JobsView list (look at the default values for pagination parameters)""", _prefix + """admin/jobs/list""")
)
                      

// @LINE:213
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "blank", Seq(), "GET", """ Add JobsView""", _prefix + """admin/jobs/blank""")
)
                      

}
                          

// @LINE:149
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:143
// @LINE:142
// @LINE:140
// @LINE:139
class ReverseCountriesView {


// @LINE:146
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """admin/countries/$id<[^/]+>""")
)
                      

// @LINE:140
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "lsort", Seq(), "GET", """""", _prefix + """admin/countries/lsort""")
)
                      

// @LINE:143
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "submit", Seq(), "POST", """""", _prefix + """admin/countries""")
)
                      

// @LINE:147
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """admin/countries/$id<[^/]+>""")
)
                      

// @LINE:145
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "sort", Seq(classOf[String]), "GET", """ Edit existing CountriesView""", _prefix + """admin/countries/sort/$ids<[^/]+>""")
)
                      

// @LINE:139
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """CountriesView
 CountriesView list (look at the default values for pagination parameters)""", _prefix + """admin/countries/list""")
)
                      

// @LINE:149
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a CountriesView""", _prefix + """admin/countries/$id<[^/]+>/delete""")
)
                      

// @LINE:142
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "blank", Seq(), "GET", """ Add CountriesView""", _prefix + """admin/countries/blank""")
)
                      

}
                          

// @LINE:89
class ReverseUpstream {


// @LINE:89
def sendClientEvent(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.sendClientEvent(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "sendClientEvent", Seq(classOf[Integer]), "POST", """""", _prefix + """sportsapi/v2/client/$id<[^/]+>/upstream""")
)
                      

}
                          

// @LINE:71
class ReverseImageProcessing {


// @LINE:71
def getLocalResourceByWidth(file:String, width:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ImageProcessing.getLocalResourceByWidth(file, width), HandlerDef(this.getClass.getClassLoader, "", "controllers.ImageProcessing", "getLocalResourceByWidth", Seq(classOf[String], classOf[Integer]), "GET", """funciones para obtener imagenes locales
GET     /sportsapi/v1/localimages/:file/:size                                                           controllers.ImageProcessing.getLocalResourceBySize(file : String, size : String)
imagen width and height""", _prefix + """sportsapi/v1/localimages/$file<[^/]+>/$width<[^/]+>""")
)
                      

}
                          

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
class ReverseWap {


// @LINE:18
def createClient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.createClient(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "createClient", Seq(), "POST", """""", _prefix + """login/create""")
)
                      

// @LINE:13
def competitions(route:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.competitions(route), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "competitions", Seq(classOf[String]), "GET", """""", _prefix + """competitions""")
)
                      

// @LINE:14
def scorers(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.scorers(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "scorers", Seq(classOf[Integer]), "GET", """""", _prefix + """scorers""")
)
                      

// @LINE:12
def matches(id:Integer, page:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.matches(id, page), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "matches", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """matches""")
)
                      

// @LINE:15
def mtm(id:Integer, playframework_escape_match:Integer, event:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.mtm(id, playframework_escape_match, event), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "mtm", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """mtm""")
)
                      

// @LINE:11
def news(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.news(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "news", Seq(classOf[Integer]), "GET", """""", _prefix + """news/$id<[^/]+>""")
)
                      

// @LINE:16
def getLogin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.getLogin(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "getLogin", Seq(), "GET", """""", _prefix + """login""")
)
                      

// @LINE:17
def getPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.getPassword(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "getPassword", Seq(), "POST", """""", _prefix + """login/password""")
)
                      

// @LINE:9
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Wap.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Wap", "index", Seq(), "GET", """""", _prefix + """""")
)
                      

}
                          

// @LINE:234
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:228
// @LINE:227
// @LINE:225
// @LINE:224
class ReverseConfigsView {


// @LINE:234
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "delete", Seq(classOf[Long]), "POST", """ Delete a ConfigsView""", _prefix + """admin/configurations/$id<[^/]+>/delete""")
)
                      

// @LINE:225
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "lsort", Seq(), "GET", """""", _prefix + """admin/configurations/lsort""")
)
                      

// @LINE:228
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "submit", Seq(), "POST", """""", _prefix + """admin/configurations""")
)
                      

// @LINE:231
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """admin/configurations/$id<[^/]+>""")
)
                      

// @LINE:232
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "update", Seq(classOf[Long]), "POST", """""", _prefix + """admin/configurations/$id<[^/]+>""")
)
                      

// @LINE:230
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "sort", Seq(classOf[String]), "GET", """ Edit existing ConfigsView""", _prefix + """admin/configurations/sort/$ids<[^/]+>""")
)
                      

// @LINE:224
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """ConfigsView
 ConfigsView list (look at the default values for pagination parameters)""", _prefix + """admin/configurations/list""")
)
                      

// @LINE:227
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "blank", Seq(), "GET", """ Add ConfigsView""", _prefix + """admin/configurations/blank""")
)
                      

}
                          

// @LINE:163
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:157
// @LINE:156
// @LINE:154
// @LINE:153
class ReverseLanguagesView {


// @LINE:160
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """admin/languages/$id<[^/]+>""")
)
                      

// @LINE:154
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "lsort", Seq(), "GET", """""", _prefix + """admin/languages/lsort""")
)
                      

// @LINE:157
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "submit", Seq(), "POST", """""", _prefix + """admin/languages""")
)
                      

// @LINE:161
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """admin/languages/$id<[^/]+>""")
)
                      

// @LINE:159
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "sort", Seq(classOf[String]), "GET", """ Edit existing LanguagesView""", _prefix + """admin/languages/sort/$ids<[^/]+>""")
)
                      

// @LINE:153
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """LanguagesView
 LanguagesView list (look at the default values for pagination parameters)""", _prefix + """admin/languages/list""")
)
                      

// @LINE:163
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a LanguagesView""", _prefix + """admin/languages/$id<[^/]+>/delete""")
)
                      

// @LINE:156
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "blank", Seq(), "GET", """ Add LanguagesView""", _prefix + """admin/languages/blank""")
)
                      

}
                          

// @LINE:110
// @LINE:109
// @LINE:104
// @LINE:103
// @LINE:101
// @LINE:99
// @LINE:98
// @LINE:96
// @LINE:21
// @LINE:20
// @LINE:7
class ReverseApplication {


// @LINE:110
def doSignup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.doSignup(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "doSignup", Seq(), "POST", """""", _prefix + """admin/signup""")
)
                      

// @LINE:20
def checkFile(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.checkFile(name), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "checkFile", Seq(classOf[String]), "GET", """""", _prefix + """check/$name<.+>""")
)
                      

// @LINE:101
def profile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.profile(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "profile", Seq(), "GET", """""", _prefix + """admin/profile""")
)
                      

// @LINE:7
def options(url:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.options(url), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "options", Seq(classOf[String]), "OPTIONS", """""", _prefix + """$url<.+>""")
)
                      

// @LINE:99
def jsRoutes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.jsRoutes(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "jsRoutes", Seq(), "GET", """""", _prefix + """admin/assets/javascript/routes.js""")
)
                      

// @LINE:109
def signup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.signup(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "signup", Seq(), "GET", """""", _prefix + """admin/signup""")
)
                      

// @LINE:104
def doLogin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.doLogin(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "doLogin", Seq(), "POST", """""", _prefix + """admin/login""")
)
                      

// @LINE:98
def restricted(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.restricted(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "restricted", Seq(), "GET", """ Authenticate""", _prefix + """admin/restricted""")
)
                      

// @LINE:21
def getAppSettings(width:Integer, height:Integer, version:String, so:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAppSettings(width, height, version, so), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getAppSettings", Seq(classOf[Integer], classOf[Integer], classOf[String], classOf[String]), "GET", """""", _prefix + """api/loading/$width<[^/]+>/$height<[^/]+>/$version<[^/]+>/$so<[^/]+>""")
)
                      

// @LINE:96
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """admin""")
)
                      

// @LINE:103
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "login", Seq(), "GET", """""", _prefix + """admin/login""")
)
                      

}
                          

// @LINE:135
// @LINE:134
// @LINE:119
// @LINE:118
// @LINE:116
// @LINE:115
// @LINE:113
// @LINE:112
class ReverseSignup {


// @LINE:116
def exists(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.exists(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "exists", Seq(), "GET", """""", _prefix + """admin/accounts/exists""")
)
                      

// @LINE:115
def verify(token:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.verify(token), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "verify", Seq(classOf[String]), "GET", """""", _prefix + """admin/accounts/verify/$token<[^/]+>""")
)
                      

// @LINE:112
def unverified(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.unverified(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "unverified", Seq(), "GET", """""", _prefix + """admin/accounts/unverified""")
)
                      

// @LINE:113
def oAuthDenied(provider:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.oAuthDenied(provider), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "oAuthDenied", Seq(classOf[String]), "GET", """""", _prefix + """admin/authenticate/$provider<[^/]+>/denied""")
)
                      

// @LINE:118
def resetPassword(token:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.resetPassword(token), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "resetPassword", Seq(classOf[String]), "GET", """""", _prefix + """admin/accounts/password/reset/$token<[^/]+>""")
)
                      

// @LINE:119
def doResetPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.doResetPassword(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "doResetPassword", Seq(), "POST", """""", _prefix + """admin/accounts/password/reset""")
)
                      

// @LINE:135
def doForgotPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.doForgotPassword(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "doForgotPassword", Seq(), "POST", """""", _prefix + """admin/login/password/forgot""")
)
                      

// @LINE:134
def forgotPassword(email:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Signup.forgotPassword(email), HandlerDef(this.getClass.getClassLoader, "", "controllers.Signup", "forgotPassword", Seq(classOf[String]), "GET", """""", _prefix + """admin/login/password/forgot""")
)
                      

}
                          

// @LINE:177
// @LINE:175
// @LINE:174
// @LINE:173
// @LINE:171
// @LINE:170
// @LINE:168
// @LINE:167
class ReverseTimezonesView {


// @LINE:174
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """admin/timezones/$id<[^/]+>""")
)
                      

// @LINE:168
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "lsort", Seq(), "GET", """""", _prefix + """admin/timezones/lsort""")
)
                      

// @LINE:171
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "submit", Seq(), "POST", """""", _prefix + """admin/timezones""")
)
                      

// @LINE:175
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """admin/timezones/$id<[^/]+>""")
)
                      

// @LINE:173
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "sort", Seq(classOf[String]), "GET", """ Edit existing TimezonesView""", _prefix + """admin/timezones/sort/$ids<[^/]+>""")
)
                      

// @LINE:167
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """TimezonesView
 TimezonesView list (look at the default values for pagination parameters)""", _prefix + """admin/timezones/list""")
)
                      

// @LINE:177
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a TimezonesView""", _prefix + """admin/timezones/$id<[^/]+>/delete""")
)
                      

// @LINE:170
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "blank", Seq(), "GET", """ Add TimezonesView""", _prefix + """admin/timezones/blank""")
)
                      

}
                          

// @LINE:206
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:200
// @LINE:199
// @LINE:197
// @LINE:196
class ReverseInstancesView {


// @LINE:203
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """admin/instances/$id<[^/]+>""")
)
                      

// @LINE:197
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "lsort", Seq(), "GET", """""", _prefix + """admin/instances/lsort""")
)
                      

// @LINE:200
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "submit", Seq(), "POST", """""", _prefix + """admin/instances""")
)
                      

// @LINE:204
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """admin/instances/$id<[^/]+>""")
)
                      

// @LINE:202
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "sort", Seq(classOf[String]), "GET", """ Edit existing InstancesView""", _prefix + """admin/instances/sort/$ids<[^/]+>""")
)
                      

// @LINE:196
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """InstancesView
 InstancesView list (look at the default values for pagination parameters)""", _prefix + """admin/instances/list""")
)
                      

// @LINE:206
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a InstancesView""", _prefix + """admin/instances/$id<[^/]+>/delete""")
)
                      

// @LINE:199
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "blank", Seq(), "GET", """ Add InstancesView""", _prefix + """admin/instances/blank""")
)
                      

}
                          

// @LINE:132
// @LINE:131
// @LINE:129
// @LINE:128
// @LINE:126
// @LINE:124
// @LINE:122
// @LINE:121
class ReverseAccount {


// @LINE:131
def askMerge(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.askMerge(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "askMerge", Seq(), "GET", """""", _prefix + """admin/accounts/merge""")
)
                      

// @LINE:132
def doMerge(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.doMerge(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "doMerge", Seq(), "POST", """""", _prefix + """admin/accounts/merge""")
)
                      

// @LINE:124
def verifyEmail(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.verifyEmail(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "verifyEmail", Seq(), "GET", """""", _prefix + """admin/accounts/verify""")
)
                      

// @LINE:128
def askLink(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.askLink(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "askLink", Seq(), "GET", """""", _prefix + """admin/accounts/link""")
)
                      

// @LINE:129
def doLink(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.doLink(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "doLink", Seq(), "POST", """""", _prefix + """accounts/link""")
)
                      

// @LINE:121
def changePassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.changePassword(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "changePassword", Seq(), "GET", """""", _prefix + """admin/accounts/password/change""")
)
                      

// @LINE:126
def link(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.link(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "link", Seq(), "GET", """""", _prefix + """admin/accounts/add""")
)
                      

// @LINE:122
def doChangePassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Account.doChangePassword(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Account", "doChangePassword", Seq(), "POST", """""", _prefix + """admin/accounts/password/change""")
)
                      

}
                          
}
        
    