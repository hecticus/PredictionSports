// @SOURCE:/Users/palenge/Hecticus/TIM_Naty/UpstreamAPIConnector/conf/UpstreamAPIConnector.routes
// @HASH:efd2ab176c722b76543f9491fd059aff6b6b9071
// @DATE:Thu May 26 17:49:33 VET 2016

import UpstreamAPIConnector.Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
package controllers.client {

// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
class ReverseClients {


// @LINE:31
// @LINE:27
def list(pageSize:Integer, page:Integer, pmc:Boolean): Call = {
   (pageSize: @unchecked, page: @unchecked, pmc: @unchecked) match {
// @LINE:27
case (pageSize, page, pmc)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "api/v1/clients/list" + queryString(List(if(pageSize == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("pageSize", pageSize)), if(page == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("page", page)), if(pmc == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("pmc", pmc)))))
                                         
   }
}
                                                

// @LINE:35
// @LINE:23
def create(): Call = {
   () match {
// @LINE:23
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/create")
                                         
   }
}
                                                

// @LINE:37
// @LINE:24
def update(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:24
case (id)  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/update/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:32
def cleanDevices(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/pmc/clean")
}
                        

// @LINE:38
// @LINE:25
def delete(id:Integer): Call = {
   (id: @unchecked) match {
// @LINE:25
case (id)  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/delete/" + implicitly[PathBindable[Integer]].unbind("id", id))
                                         
   }
}
                                                

// @LINE:36
// @LINE:30
// @LINE:26
def get(id:Integer, upstreamChannel:String, pmc:Boolean): Call = {
   (id: @unchecked, upstreamChannel: @unchecked, pmc: @unchecked) match {
// @LINE:26
case (id, upstreamChannel, pmc)  =>
  import ReverseRouteContext.empty
  Call("GET", _prefix + { _defaultPrefix } + "api/v1/clients/get/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("upstreamChannel", dynamicString(upstreamChannel)) + queryString(List(if(pmc == false) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("pmc", pmc)))))
                                         
   }
}
                                                

}
                          
}
                  

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:6
package controllers {

// @LINE:6
class ReverseAssets {


// @LINE:6
def versioned(file:Asset): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[Asset]].unbind("file", file))
}
                        

}
                          

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
class ReverseDevicesView {


// @LINE:91
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "devices/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:85
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "devices/lsort")
}
                        

// @LINE:88
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "devices")
}
                        

// @LINE:92
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "devices/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:90
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "devices/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:84
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "devices/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:94
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "devices/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:87
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "devices/blank")
}
                        

}
                          

// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
class ReverseCountriesView {


// @LINE:49
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "countries/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:43
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "countries/lsort")
}
                        

// @LINE:46
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "countries")
}
                        

// @LINE:50
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "countries/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:48
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "countries/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:42
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "countries/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:52
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "countries/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:45
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "countries/blank")
}
                        

}
                          

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseUpstream {


// @LINE:16
def upstreamFakeStatus(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/game/user/status")
}
                        

// @LINE:18
def upstreamFakeEventSend(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/game/user/event")
}
                        

// @LINE:14
def upstreamFakeCreate(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/game/user/subscribe")
}
                        

// @LINE:15
def upstreamFakeLogin(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/game/user/login")
}
                        

// @LINE:17
def upstreamFakeResetPass(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/game/user/password")
}
                        

// @LINE:10
def sendClientEvent(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/client/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/upstream")
}
                        

// @LINE:11
def resetUpstreamPass(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "api/v1/clients/upstream/resetpass")
}
                        

}
                          

// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
class ReverseLanguagesView {


// @LINE:63
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "languages/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:57
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "languages/lsort")
}
                        

// @LINE:60
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "languages")
}
                        

// @LINE:64
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "languages/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:62
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "languages/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:56
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "languages/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:66
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "languages/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:59
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "languages/blank")
}
                        

}
                          

// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
class ReverseTimezonesView {


// @LINE:77
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "timezones/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:71
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "timezones/lsort")
}
                        

// @LINE:74
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "timezones")
}
                        

// @LINE:78
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "timezones/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:76
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "timezones/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:70
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "timezones/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:80
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "timezones/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:73
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "timezones/blank")
}
                        

}
                          
}
                  


// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
package controllers.client.javascript {
import ReverseRouteContext.empty

// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
class ReverseClients {


// @LINE:31
// @LINE:27
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.list",
   """
      function(pageSize, page, pmc) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/list" + _qS([(pageSize == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize)), (page == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("page", page)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/pmc/list/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("page", page) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("pageSize", pageSize) + _qS([(pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      }
   """
)
                        

// @LINE:35
// @LINE:23
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.create",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/create"})
      }
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v2/client"})
      }
      }
   """
)
                        

// @LINE:37
// @LINE:24
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.update",
   """
      function(id) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/update/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      }
   """
)
                        

// @LINE:32
def cleanDevices : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.cleanDevices",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/pmc/clean"})
      }
   """
)
                        

// @LINE:38
// @LINE:25
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.delete",
   """
      function(id) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/delete/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      if (true) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
      }
   """
)
                        

// @LINE:36
// @LINE:30
// @LINE:26
def get : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.client.Clients.get",
   """
      function(id, upstreamChannel, pmc) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("upstreamChannel", encodeURIComponent(upstreamChannel)) + _qS([(pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/pmc/get/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + _qS([(upstreamChannel == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("upstreamChannel", upstreamChannel)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v2/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + _qS([(upstreamChannel == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("upstreamChannel", upstreamChannel)), (pmc == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("pmc", pmc))])})
      }
      }
   """
)
                        

}
              
}
        

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:6
class ReverseAssets {


// @LINE:6
def versioned : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.versioned",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
class ReverseDevicesView {


// @LINE:91
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:85
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/lsort"})
      }
   """
)
                        

// @LINE:88
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "devices"})
      }
   """
)
                        

// @LINE:92
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:90
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:84
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:94
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:87
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DevicesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "devices/blank"})
      }
   """
)
                        

}
              

// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
class ReverseCountriesView {


// @LINE:49
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:43
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/lsort"})
      }
   """
)
                        

// @LINE:46
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "countries"})
      }
   """
)
                        

// @LINE:50
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:48
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:42
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:52
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:45
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CountriesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "countries/blank"})
      }
   """
)
                        

}
              

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseUpstream {


// @LINE:16
def upstreamFakeStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.upstreamFakeStatus",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/game/user/status"})
      }
   """
)
                        

// @LINE:18
def upstreamFakeEventSend : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.upstreamFakeEventSend",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/game/user/event"})
      }
   """
)
                        

// @LINE:14
def upstreamFakeCreate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.upstreamFakeCreate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/game/user/subscribe"})
      }
   """
)
                        

// @LINE:15
def upstreamFakeLogin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.upstreamFakeLogin",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/game/user/login"})
      }
   """
)
                        

// @LINE:17
def upstreamFakeResetPass : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.upstreamFakeResetPass",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/game/user/password"})
      }
   """
)
                        

// @LINE:10
def sendClientEvent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.sendClientEvent",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/client/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/upstream"})
      }
   """
)
                        

// @LINE:11
def resetUpstreamPass : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Upstream.resetUpstreamPass",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/clients/upstream/resetpass"})
      }
   """
)
                        

}
              

// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
class ReverseLanguagesView {


// @LINE:63
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:57
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/lsort"})
      }
   """
)
                        

// @LINE:60
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "languages"})
      }
   """
)
                        

// @LINE:64
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:62
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:56
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:66
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:59
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.LanguagesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "languages/blank"})
      }
   """
)
                        

}
              

// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
class ReverseTimezonesView {


// @LINE:77
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:71
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/lsort"})
      }
   """
)
                        

// @LINE:74
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones"})
      }
   """
)
                        

// @LINE:78
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:76
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:70
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:80
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:73
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.TimezonesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "timezones/blank"})
      }
   """
)
                        

}
              
}
        


// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
package controllers.client.ref {


// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
class ReverseClients {


// @LINE:27
def list(pageSize:Integer, page:Integer, pmc:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.list(pageSize, page, pmc), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "list", Seq(classOf[Integer], classOf[Integer], classOf[Boolean]), "GET", """""", _prefix + """api/v1/clients/list""")
)
                      

// @LINE:23
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.create(), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "create", Seq(), "POST", """#V1""", _prefix + """api/v1/clients/create""")
)
                      

// @LINE:24
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """api/v1/clients/update/$id<[^/]+>""")
)
                      

// @LINE:32
def cleanDevices(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.cleanDevices(), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "cleanDevices", Seq(), "POST", """""", _prefix + """api/v1/clients/pmc/clean""")
)
                      

// @LINE:25
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "delete", Seq(classOf[Integer]), "POST", """""", _prefix + """api/v1/clients/delete/$id<[^/]+>""")
)
                      

// @LINE:26
def get(id:Integer, upstreamChannel:String, pmc:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.client.Clients.get(id, upstreamChannel, pmc), HandlerDef(this.getClass.getClassLoader, "", "controllers.client.Clients", "get", Seq(classOf[Integer], classOf[String], classOf[Boolean]), "GET", """""", _prefix + """api/v1/clients/get/$id<[^/]+>/$upstreamChannel<[^/]+>""")
)
                      

}
                          
}
        

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:6
package controllers.ref {


// @LINE:6
class ReverseAssets {


// @LINE:6
def versioned(path:String, file:Asset): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.versioned(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:94
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:88
// @LINE:87
// @LINE:85
// @LINE:84
class ReverseDevicesView {


// @LINE:91
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """devices/$id<[^/]+>""")
)
                      

// @LINE:85
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "lsort", Seq(), "GET", """""", _prefix + """devices/lsort""")
)
                      

// @LINE:88
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "submit", Seq(), "POST", """""", _prefix + """devices""")
)
                      

// @LINE:92
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """devices/$id<[^/]+>""")
)
                      

// @LINE:90
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "sort", Seq(classOf[String]), "GET", """ Edit existing DevicesView""", _prefix + """devices/sort/$ids<[^/]+>""")
)
                      

// @LINE:84
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """DevicesView
 DevicesView list (look at the default values for pagination parameters)""", _prefix + """devices/list""")
)
                      

// @LINE:94
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a DevicesView""", _prefix + """devices/$id<[^/]+>/delete""")
)
                      

// @LINE:87
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DevicesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.DevicesView", "blank", Seq(), "GET", """ Add DevicesView""", _prefix + """devices/blank""")
)
                      

}
                          

// @LINE:52
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:43
// @LINE:42
class ReverseCountriesView {


// @LINE:49
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """countries/$id<[^/]+>""")
)
                      

// @LINE:43
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "lsort", Seq(), "GET", """""", _prefix + """countries/lsort""")
)
                      

// @LINE:46
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "submit", Seq(), "POST", """""", _prefix + """countries""")
)
                      

// @LINE:50
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """countries/$id<[^/]+>""")
)
                      

// @LINE:48
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "sort", Seq(classOf[String]), "GET", """ Edit existing CountriesView""", _prefix + """countries/sort/$ids<[^/]+>""")
)
                      

// @LINE:42
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """CountriesView
 CountriesView list (look at the default values for pagination parameters)""", _prefix + """countries/list""")
)
                      

// @LINE:52
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a CountriesView""", _prefix + """countries/$id<[^/]+>/delete""")
)
                      

// @LINE:45
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CountriesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CountriesView", "blank", Seq(), "GET", """ Add CountriesView""", _prefix + """countries/blank""")
)
                      

}
                          

// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:11
// @LINE:10
class ReverseUpstream {


// @LINE:16
def upstreamFakeStatus(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.upstreamFakeStatus(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "upstreamFakeStatus", Seq(), "POST", """""", _prefix + """api/v1/clients/upstream/game/user/status""")
)
                      

// @LINE:18
def upstreamFakeEventSend(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.upstreamFakeEventSend(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "upstreamFakeEventSend", Seq(), "POST", """""", _prefix + """api/v1/clients/upstream/game/user/event""")
)
                      

// @LINE:14
def upstreamFakeCreate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.upstreamFakeCreate(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "upstreamFakeCreate", Seq(), "POST", """#FAKE UPSTREAM URL""", _prefix + """api/v1/clients/upstream/game/user/subscribe""")
)
                      

// @LINE:15
def upstreamFakeLogin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.upstreamFakeLogin(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "upstreamFakeLogin", Seq(), "POST", """""", _prefix + """api/v1/clients/upstream/game/user/login""")
)
                      

// @LINE:17
def upstreamFakeResetPass(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.upstreamFakeResetPass(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "upstreamFakeResetPass", Seq(), "POST", """""", _prefix + """api/v1/clients/upstream/game/user/password""")
)
                      

// @LINE:10
def sendClientEvent(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.sendClientEvent(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "sendClientEvent", Seq(classOf[Integer]), "POST", """""", _prefix + """api/v1/client/$id<[^/]+>/upstream""")
)
                      

// @LINE:11
def resetUpstreamPass(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Upstream.resetUpstreamPass(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Upstream", "resetUpstreamPass", Seq(), "POST", """""", _prefix + """api/v1/clients/upstream/resetpass""")
)
                      

}
                          

// @LINE:66
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:60
// @LINE:59
// @LINE:57
// @LINE:56
class ReverseLanguagesView {


// @LINE:63
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """languages/$id<[^/]+>""")
)
                      

// @LINE:57
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "lsort", Seq(), "GET", """""", _prefix + """languages/lsort""")
)
                      

// @LINE:60
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "submit", Seq(), "POST", """""", _prefix + """languages""")
)
                      

// @LINE:64
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """languages/$id<[^/]+>""")
)
                      

// @LINE:62
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "sort", Seq(classOf[String]), "GET", """ Edit existing LanguagesView""", _prefix + """languages/sort/$ids<[^/]+>""")
)
                      

// @LINE:56
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """LanguagesView
 LanguagesView list (look at the default values for pagination parameters)""", _prefix + """languages/list""")
)
                      

// @LINE:66
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a LanguagesView""", _prefix + """languages/$id<[^/]+>/delete""")
)
                      

// @LINE:59
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.LanguagesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.LanguagesView", "blank", Seq(), "GET", """ Add LanguagesView""", _prefix + """languages/blank""")
)
                      

}
                          

// @LINE:80
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:74
// @LINE:73
// @LINE:71
// @LINE:70
class ReverseTimezonesView {


// @LINE:77
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """timezones/$id<[^/]+>""")
)
                      

// @LINE:71
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "lsort", Seq(), "GET", """""", _prefix + """timezones/lsort""")
)
                      

// @LINE:74
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "submit", Seq(), "POST", """""", _prefix + """timezones""")
)
                      

// @LINE:78
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """timezones/$id<[^/]+>""")
)
                      

// @LINE:76
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "sort", Seq(classOf[String]), "GET", """ Edit existing TimezonesView""", _prefix + """timezones/sort/$ids<[^/]+>""")
)
                      

// @LINE:70
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """TimezonesView
 TimezonesView list (look at the default values for pagination parameters)""", _prefix + """timezones/list""")
)
                      

// @LINE:80
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a TimezonesView""", _prefix + """timezones/$id<[^/]+>/delete""")
)
                      

// @LINE:73
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.TimezonesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.TimezonesView", "blank", Seq(), "GET", """ Add TimezonesView""", _prefix + """timezones/blank""")
)
                      

}
                          
}
        
    