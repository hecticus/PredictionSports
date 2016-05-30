// @SOURCE:/Users/palenge/Hecticus/JobCore/conf/JobCore.routes
// @HASH:b543cf70f02b17a45d5f0d92296b9cb2f85f2698
// @DATE:Thu May 26 17:49:33 VET 2016
package JobCore

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


// @LINE:9
private[this] lazy val controllers_Assets_at0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at0_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:11
private[this] lazy val controllers_Instances_alive1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("alive"))))
private[this] lazy val controllers_Instances_alive1_invoker = createInvoker(
controllers.Instances.alive(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.Instances", "alive", Nil,"GET", """""", Routes.prefix + """alive"""))
        

// @LINE:13
private[this] lazy val controllers_Instances_checkFile2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("check/"),DynamicPart("name", """.+""",false))))
private[this] lazy val controllers_Instances_checkFile2_invoker = createInvoker(
controllers.Instances.checkFile(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.Instances", "checkFile", Seq(classOf[String]),"GET", """""", Routes.prefix + """check/$name<.+>"""))
        

// @LINE:17
private[this] lazy val controllers_InstancesView_list3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/list"))))
private[this] lazy val controllers_InstancesView_list3_invoker = createInvoker(
controllers.InstancesView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """InstancesView
 InstancesView list (look at the default values for pagination parameters)""", Routes.prefix + """instances/list"""))
        

// @LINE:18
private[this] lazy val controllers_InstancesView_lsort4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/lsort"))))
private[this] lazy val controllers_InstancesView_lsort4_invoker = createInvoker(
controllers.InstancesView.lsort(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "lsort", Nil,"GET", """""", Routes.prefix + """instances/lsort"""))
        

// @LINE:20
private[this] lazy val controllers_InstancesView_blank5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/blank"))))
private[this] lazy val controllers_InstancesView_blank5_invoker = createInvoker(
controllers.InstancesView.blank(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "blank", Nil,"GET", """ Add InstancesView""", Routes.prefix + """instances/blank"""))
        

// @LINE:21
private[this] lazy val controllers_InstancesView_submit6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances"))))
private[this] lazy val controllers_InstancesView_submit6_invoker = createInvoker(
controllers.InstancesView.submit(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "submit", Nil,"POST", """""", Routes.prefix + """instances"""))
        

// @LINE:23
private[this] lazy val controllers_InstancesView_sort7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_sort7_invoker = createInvoker(
controllers.InstancesView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "sort", Seq(classOf[String]),"GET", """ Edit existing InstancesView""", Routes.prefix + """instances/sort/$ids<[^/]+>"""))
        

// @LINE:24
private[this] lazy val controllers_InstancesView_edit8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_edit8_invoker = createInvoker(
controllers.InstancesView.edit(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "edit", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """instances/$id<[^/]+>"""))
        

// @LINE:25
private[this] lazy val controllers_InstancesView_update9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_InstancesView_update9_invoker = createInvoker(
controllers.InstancesView.update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "update", Seq(classOf[Integer]),"POST", """""", Routes.prefix + """instances/$id<[^/]+>"""))
        

// @LINE:27
private[this] lazy val controllers_InstancesView_delete10_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("instances/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_InstancesView_delete10_invoker = createInvoker(
controllers.InstancesView.delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.InstancesView", "delete", Seq(classOf[Integer]),"POST", """ Delete a InstancesView""", Routes.prefix + """instances/$id<[^/]+>/delete"""))
        

// @LINE:31
private[this] lazy val controllers_JobsView_list11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/list"))))
private[this] lazy val controllers_JobsView_list11_invoker = createInvoker(
controllers.JobsView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """JobsView
 JobsView list (look at the default values for pagination parameters)""", Routes.prefix + """jobs/list"""))
        

// @LINE:32
private[this] lazy val controllers_JobsView_lsort12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/lsort"))))
private[this] lazy val controllers_JobsView_lsort12_invoker = createInvoker(
controllers.JobsView.lsort(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "lsort", Nil,"GET", """""", Routes.prefix + """jobs/lsort"""))
        

// @LINE:34
private[this] lazy val controllers_JobsView_blank13_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/blank"))))
private[this] lazy val controllers_JobsView_blank13_invoker = createInvoker(
controllers.JobsView.blank(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "blank", Nil,"GET", """ Add JobsView""", Routes.prefix + """jobs/blank"""))
        

// @LINE:35
private[this] lazy val controllers_JobsView_submit14_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs"))))
private[this] lazy val controllers_JobsView_submit14_invoker = createInvoker(
controllers.JobsView.submit(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "submit", Nil,"POST", """""", Routes.prefix + """jobs"""))
        

// @LINE:37
private[this] lazy val controllers_JobsView_sort15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_sort15_invoker = createInvoker(
controllers.JobsView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "sort", Seq(classOf[String]),"GET", """ Edit existing JobsView""", Routes.prefix + """jobs/sort/$ids<[^/]+>"""))
        

// @LINE:38
private[this] lazy val controllers_JobsView_edit16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_edit16_invoker = createInvoker(
controllers.JobsView.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """jobs/$id<[^/]+>"""))
        

// @LINE:39
private[this] lazy val controllers_JobsView_update17_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_JobsView_update17_invoker = createInvoker(
controllers.JobsView.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """jobs/$id<[^/]+>"""))
        

// @LINE:41
private[this] lazy val controllers_JobsView_delete18_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_JobsView_delete18_invoker = createInvoker(
controllers.JobsView.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.JobsView", "delete", Seq(classOf[Long]),"POST", """ Delete a JobsView""", Routes.prefix + """jobs/$id<[^/]+>/delete"""))
        

// @LINE:45
private[this] lazy val controllers_ConfigsView_list19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/list"))))
private[this] lazy val controllers_ConfigsView_list19_invoker = createInvoker(
controllers.ConfigsView.list(fakeValue[Int], fakeValue[String], fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """ConfigsView
 ConfigsView list (look at the default values for pagination parameters)""", Routes.prefix + """configurations/list"""))
        

// @LINE:46
private[this] lazy val controllers_ConfigsView_lsort20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/lsort"))))
private[this] lazy val controllers_ConfigsView_lsort20_invoker = createInvoker(
controllers.ConfigsView.lsort(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "lsort", Nil,"GET", """""", Routes.prefix + """configurations/lsort"""))
        

// @LINE:48
private[this] lazy val controllers_ConfigsView_blank21_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/blank"))))
private[this] lazy val controllers_ConfigsView_blank21_invoker = createInvoker(
controllers.ConfigsView.blank(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "blank", Nil,"GET", """ Add ConfigsView""", Routes.prefix + """configurations/blank"""))
        

// @LINE:49
private[this] lazy val controllers_ConfigsView_submit22_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations"))))
private[this] lazy val controllers_ConfigsView_submit22_invoker = createInvoker(
controllers.ConfigsView.submit(),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "submit", Nil,"POST", """""", Routes.prefix + """configurations"""))
        

// @LINE:51
private[this] lazy val controllers_ConfigsView_sort23_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/sort/"),DynamicPart("ids", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_sort23_invoker = createInvoker(
controllers.ConfigsView.sort(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "sort", Seq(classOf[String]),"GET", """ Edit existing ConfigsView""", Routes.prefix + """configurations/sort/$ids<[^/]+>"""))
        

// @LINE:52
private[this] lazy val controllers_ConfigsView_edit24_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_edit24_invoker = createInvoker(
controllers.ConfigsView.edit(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """configurations/$id<[^/]+>"""))
        

// @LINE:53
private[this] lazy val controllers_ConfigsView_update25_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val controllers_ConfigsView_update25_invoker = createInvoker(
controllers.ConfigsView.update(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """configurations/$id<[^/]+>"""))
        

// @LINE:55
private[this] lazy val controllers_ConfigsView_delete26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configurations/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_ConfigsView_delete26_invoker = createInvoker(
controllers.ConfigsView.delete(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "JobCore", "controllers.ConfigsView", "delete", Seq(classOf[Long]),"POST", """ Delete a ConfigsView""", Routes.prefix + """configurations/$id<[^/]+>/delete"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """alive""","""controllers.Instances.alive()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """check/$name<.+>""","""controllers.Instances.checkFile(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/list""","""controllers.InstancesView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/lsort""","""controllers.InstancesView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/blank""","""controllers.InstancesView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances""","""controllers.InstancesView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/sort/$ids<[^/]+>""","""controllers.InstancesView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/$id<[^/]+>""","""controllers.InstancesView.edit(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/$id<[^/]+>""","""controllers.InstancesView.update(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """instances/$id<[^/]+>/delete""","""controllers.InstancesView.delete(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/list""","""controllers.JobsView.list(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/lsort""","""controllers.JobsView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/blank""","""controllers.JobsView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs""","""controllers.JobsView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/sort/$ids<[^/]+>""","""controllers.JobsView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/$id<[^/]+>""","""controllers.JobsView.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/$id<[^/]+>""","""controllers.JobsView.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs/$id<[^/]+>/delete""","""controllers.JobsView.delete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/list""","""controllers.ConfigsView.list(p:Int ?= 0, s:String ?= "configKey", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/lsort""","""controllers.ConfigsView.lsort()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/blank""","""controllers.ConfigsView.blank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations""","""controllers.ConfigsView.submit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/sort/$ids<[^/]+>""","""controllers.ConfigsView.sort(ids:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/$id<[^/]+>""","""controllers.ConfigsView.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/$id<[^/]+>""","""controllers.ConfigsView.update(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configurations/$id<[^/]+>/delete""","""controllers.ConfigsView.delete(id:Long)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:9
case controllers_Assets_at0_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at0_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:11
case controllers_Instances_alive1_route(params) => {
   call { 
        controllers_Instances_alive1_invoker.call(controllers.Instances.alive())
   }
}
        

// @LINE:13
case controllers_Instances_checkFile2_route(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        controllers_Instances_checkFile2_invoker.call(controllers.Instances.checkFile(name))
   }
}
        

// @LINE:17
case controllers_InstancesView_list3_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_InstancesView_list3_invoker.call(controllers.InstancesView.list(p, s, o, f))
   }
}
        

// @LINE:18
case controllers_InstancesView_lsort4_route(params) => {
   call { 
        controllers_InstancesView_lsort4_invoker.call(controllers.InstancesView.lsort())
   }
}
        

// @LINE:20
case controllers_InstancesView_blank5_route(params) => {
   call { 
        controllers_InstancesView_blank5_invoker.call(controllers.InstancesView.blank())
   }
}
        

// @LINE:21
case controllers_InstancesView_submit6_route(params) => {
   call { 
        controllers_InstancesView_submit6_invoker.call(controllers.InstancesView.submit())
   }
}
        

// @LINE:23
case controllers_InstancesView_sort7_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_InstancesView_sort7_invoker.call(controllers.InstancesView.sort(ids))
   }
}
        

// @LINE:24
case controllers_InstancesView_edit8_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_edit8_invoker.call(controllers.InstancesView.edit(id))
   }
}
        

// @LINE:25
case controllers_InstancesView_update9_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_update9_invoker.call(controllers.InstancesView.update(id))
   }
}
        

// @LINE:27
case controllers_InstancesView_delete10_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_InstancesView_delete10_invoker.call(controllers.InstancesView.delete(id))
   }
}
        

// @LINE:31
case controllers_JobsView_list11_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_JobsView_list11_invoker.call(controllers.JobsView.list(p, s, o, f))
   }
}
        

// @LINE:32
case controllers_JobsView_lsort12_route(params) => {
   call { 
        controllers_JobsView_lsort12_invoker.call(controllers.JobsView.lsort())
   }
}
        

// @LINE:34
case controllers_JobsView_blank13_route(params) => {
   call { 
        controllers_JobsView_blank13_invoker.call(controllers.JobsView.blank())
   }
}
        

// @LINE:35
case controllers_JobsView_submit14_route(params) => {
   call { 
        controllers_JobsView_submit14_invoker.call(controllers.JobsView.submit())
   }
}
        

// @LINE:37
case controllers_JobsView_sort15_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_JobsView_sort15_invoker.call(controllers.JobsView.sort(ids))
   }
}
        

// @LINE:38
case controllers_JobsView_edit16_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_edit16_invoker.call(controllers.JobsView.edit(id))
   }
}
        

// @LINE:39
case controllers_JobsView_update17_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_update17_invoker.call(controllers.JobsView.update(id))
   }
}
        

// @LINE:41
case controllers_JobsView_delete18_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_JobsView_delete18_invoker.call(controllers.JobsView.delete(id))
   }
}
        

// @LINE:45
case controllers_ConfigsView_list19_route(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("configKey")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        controllers_ConfigsView_list19_invoker.call(controllers.ConfigsView.list(p, s, o, f))
   }
}
        

// @LINE:46
case controllers_ConfigsView_lsort20_route(params) => {
   call { 
        controllers_ConfigsView_lsort20_invoker.call(controllers.ConfigsView.lsort())
   }
}
        

// @LINE:48
case controllers_ConfigsView_blank21_route(params) => {
   call { 
        controllers_ConfigsView_blank21_invoker.call(controllers.ConfigsView.blank())
   }
}
        

// @LINE:49
case controllers_ConfigsView_submit22_route(params) => {
   call { 
        controllers_ConfigsView_submit22_invoker.call(controllers.ConfigsView.submit())
   }
}
        

// @LINE:51
case controllers_ConfigsView_sort23_route(params) => {
   call(params.fromPath[String]("ids", None)) { (ids) =>
        controllers_ConfigsView_sort23_invoker.call(controllers.ConfigsView.sort(ids))
   }
}
        

// @LINE:52
case controllers_ConfigsView_edit24_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_edit24_invoker.call(controllers.ConfigsView.edit(id))
   }
}
        

// @LINE:53
case controllers_ConfigsView_update25_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_update25_invoker.call(controllers.ConfigsView.update(id))
   }
}
        

// @LINE:55
case controllers_ConfigsView_delete26_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ConfigsView_delete26_invoker.call(controllers.ConfigsView.delete(id))
   }
}
        
}

}
     