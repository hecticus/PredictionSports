// @SOURCE:/Users/palenge/Hecticus/JobCore/conf/JobCore.routes
// @HASH:b543cf70f02b17a45d5f0d92296b9cb2f85f2698
// @DATE:Thu May 26 17:49:33 VET 2016

import JobCore.Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:13
// @LINE:11
// @LINE:9
package controllers {

// @LINE:9
class ReverseAssets {


// @LINE:9
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
class ReverseJobsView {


// @LINE:41
def delete(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "jobs/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:32
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs/lsort")
}
                        

// @LINE:35
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "jobs")
}
                        

// @LINE:38
def edit(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:39
def update(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "jobs/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:37
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:31
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:34
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs/blank")
}
                        

}
                          

// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
class ReverseConfigsView {


// @LINE:55
def delete(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "configurations/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                        

// @LINE:46
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configurations/lsort")
}
                        

// @LINE:49
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "configurations")
}
                        

// @LINE:52
def edit(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configurations/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:53
def update(id:Long): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "configurations/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                        

// @LINE:51
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configurations/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:45
def list(p:Int = 0, s:String = "configKey", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configurations/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "configKey") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:48
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configurations/blank")
}
                        

}
                          

// @LINE:13
// @LINE:11
class ReverseInstances {


// @LINE:11
def alive(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "alive")
}
                        

// @LINE:13
def checkFile(name:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "check/" + implicitly[PathBindable[String]].unbind("name", name))
}
                        

}
                          

// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
class ReverseInstancesView {


// @LINE:24
def edit(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "instances/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:18
def lsort(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "instances/lsort")
}
                        

// @LINE:21
def submit(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "instances")
}
                        

// @LINE:25
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "instances/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:23
def sort(ids:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "instances/sort/" + implicitly[PathBindable[String]].unbind("ids", dynamicString(ids)))
}
                        

// @LINE:17
def list(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "instances/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                        

// @LINE:27
def delete(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "instances/" + implicitly[PathBindable[Integer]].unbind("id", id) + "/delete")
}
                        

// @LINE:20
def blank(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "instances/blank")
}
                        

}
                          
}
                  


// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:13
// @LINE:11
// @LINE:9
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:9
class ReverseAssets {


// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
class ReverseJobsView {


// @LINE:41
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:32
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/lsort"})
      }
   """
)
                        

// @LINE:35
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs"})
      }
   """
)
                        

// @LINE:38
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:39
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:37
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:31
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:34
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.JobsView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs/blank"})
      }
   """
)
                        

}
              

// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
class ReverseConfigsView {


// @LINE:55
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:46
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/lsort"})
      }
   """
)
                        

// @LINE:49
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations"})
      }
   """
)
                        

// @LINE:52
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:53
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:51
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:45
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:48
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ConfigsView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configurations/blank"})
      }
   """
)
                        

}
              

// @LINE:13
// @LINE:11
class ReverseInstances {


// @LINE:11
def alive : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Instances.alive",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "alive"})
      }
   """
)
                        

// @LINE:13
def checkFile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Instances.checkFile",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "check/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", name)})
      }
   """
)
                        

}
              

// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
class ReverseInstancesView {


// @LINE:24
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:18
def lsort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.lsort",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/lsort"})
      }
   """
)
                        

// @LINE:21
def submit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.submit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "instances"})
      }
   """
)
                        

// @LINE:25
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:23
def sort : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.sort",
   """
      function(ids) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/sort/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("ids", encodeURIComponent(ids))})
      }
   """
)
                        

// @LINE:17
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:27
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.delete",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:20
def blank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.InstancesView.blank",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "instances/blank"})
      }
   """
)
                        

}
              
}
        


// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
// @LINE:13
// @LINE:11
// @LINE:9
package controllers.ref {


// @LINE:9
class ReverseAssets {


// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:41
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:35
// @LINE:34
// @LINE:32
// @LINE:31
class ReverseJobsView {


// @LINE:41
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "delete", Seq(classOf[Long]), "POST", """ Delete a JobsView""", _prefix + """jobs/$id<[^/]+>/delete""")
)
                      

// @LINE:32
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "lsort", Seq(), "GET", """""", _prefix + """jobs/lsort""")
)
                      

// @LINE:35
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "submit", Seq(), "POST", """""", _prefix + """jobs""")
)
                      

// @LINE:38
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """jobs/$id<[^/]+>""")
)
                      

// @LINE:39
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "update", Seq(classOf[Long]), "POST", """""", _prefix + """jobs/$id<[^/]+>""")
)
                      

// @LINE:37
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "sort", Seq(classOf[String]), "GET", """ Edit existing JobsView""", _prefix + """jobs/sort/$ids<[^/]+>""")
)
                      

// @LINE:31
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """JobsView
 JobsView list (look at the default values for pagination parameters)""", _prefix + """jobs/list""")
)
                      

// @LINE:34
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.JobsView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.JobsView", "blank", Seq(), "GET", """ Add JobsView""", _prefix + """jobs/blank""")
)
                      

}
                          

// @LINE:55
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:49
// @LINE:48
// @LINE:46
// @LINE:45
class ReverseConfigsView {


// @LINE:55
def delete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "delete", Seq(classOf[Long]), "POST", """ Delete a ConfigsView""", _prefix + """configurations/$id<[^/]+>/delete""")
)
                      

// @LINE:46
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "lsort", Seq(), "GET", """""", _prefix + """configurations/lsort""")
)
                      

// @LINE:49
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "submit", Seq(), "POST", """""", _prefix + """configurations""")
)
                      

// @LINE:52
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """configurations/$id<[^/]+>""")
)
                      

// @LINE:53
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "update", Seq(classOf[Long]), "POST", """""", _prefix + """configurations/$id<[^/]+>""")
)
                      

// @LINE:51
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "sort", Seq(classOf[String]), "GET", """ Edit existing ConfigsView""", _prefix + """configurations/sort/$ids<[^/]+>""")
)
                      

// @LINE:45
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """ConfigsView
 ConfigsView list (look at the default values for pagination parameters)""", _prefix + """configurations/list""")
)
                      

// @LINE:48
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ConfigsView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.ConfigsView", "blank", Seq(), "GET", """ Add ConfigsView""", _prefix + """configurations/blank""")
)
                      

}
                          

// @LINE:13
// @LINE:11
class ReverseInstances {


// @LINE:11
def alive(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Instances.alive(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Instances", "alive", Seq(), "GET", """""", _prefix + """alive""")
)
                      

// @LINE:13
def checkFile(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Instances.checkFile(name), HandlerDef(this.getClass.getClassLoader, "", "controllers.Instances", "checkFile", Seq(classOf[String]), "GET", """""", _prefix + """check/$name<.+>""")
)
                      

}
                          

// @LINE:27
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:21
// @LINE:20
// @LINE:18
// @LINE:17
class ReverseInstancesView {


// @LINE:24
def edit(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.edit(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "edit", Seq(classOf[Integer]), "GET", """""", _prefix + """instances/$id<[^/]+>""")
)
                      

// @LINE:18
def lsort(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.lsort(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "lsort", Seq(), "GET", """""", _prefix + """instances/lsort""")
)
                      

// @LINE:21
def submit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.submit(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "submit", Seq(), "POST", """""", _prefix + """instances""")
)
                      

// @LINE:25
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.update(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "update", Seq(classOf[Integer]), "POST", """""", _prefix + """instances/$id<[^/]+>""")
)
                      

// @LINE:23
def sort(ids:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.sort(ids), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "sort", Seq(classOf[String]), "GET", """ Edit existing InstancesView""", _prefix + """instances/sort/$ids<[^/]+>""")
)
                      

// @LINE:17
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.list(p, s, o, f), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """InstancesView
 InstancesView list (look at the default values for pagination parameters)""", _prefix + """instances/list""")
)
                      

// @LINE:27
def delete(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.delete(id), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "delete", Seq(classOf[Integer]), "POST", """ Delete a InstancesView""", _prefix + """instances/$id<[^/]+>/delete""")
)
                      

// @LINE:20
def blank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.InstancesView.blank(), HandlerDef(this.getClass.getClassLoader, "", "controllers.InstancesView", "blank", Seq(), "GET", """ Add InstancesView""", _prefix + """instances/blank""")
)
                      

}
                          
}
        
    