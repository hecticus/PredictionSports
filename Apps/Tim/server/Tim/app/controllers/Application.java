package controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;

import models.Config;
import models.basic.Language;
import org.apache.commons.codec.binary.Base64;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.Session;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import utils.Utils;
import views.html.*;

public class Application extends Controller {

	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    public static Result options(String url){
        response().setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        return ok("OK");
    }

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result checkFile(String name){
        File file = new File(name);
        //Logger.info("nameFile "+name+", path "+file.getAbsolutePath());
        if(file.exists()){
            return ok("OK");
        }else{
            return badRequest("file not found");
        }
    }
	
	public static ObjectNode getJson(){
        ObjectNode jsonInfo = (ObjectNode) request().body().asJson();
        if(jsonInfo == null){
            Map<String,String[]> b = request().body().asFormUrlEncoded();
            if(b == null){
                return null;
            }
            jsonInfo = Json.newObject();
            Set<String> keys = b.keySet();
            Iterator<String> it = keys.iterator();
            while(it.hasNext()){
                String key = (String)it.next();
                jsonInfo.put(key, Json.toJson(b.get(key)[0]));
            }
        }
        return jsonInfo;
    }

     /*Plugin Authenticate*/

    public static User getLocalUser(final Http.Session session) {
        final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
        final User localUser = User.findByAuthUserIdentity(currentAuthUser);
        return localUser;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result restricted() {
        final User localUser = getLocalUser(session());
        return ok(restricted.render(localUser));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result profile() {
        final User localUser = getLocalUser(session());
        return ok(profile.render(localUser));
    }

    public static Result login() {
        return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
    }

    public static Result doLogin() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MyUsernamePasswordAuthProvider.MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(login.render(filledForm));
        } else {
            // Everything was filled
            return UsernamePasswordAuthProvider.handleLogin(ctx());
        }
    }

    public static Result signup() {
        return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
    }

	public static Result jsRoutes() {
		return ok(
				Routes.javascriptRouter("jsRoutes", controllers.routes.javascript.Signup.forgotPassword()))
				.as("text/javascript");
	}

	public static Result doSignup() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MyUsernamePasswordAuthProvider.MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(signup.render(filledForm));
        } else {
            // Everything was filled
            // do something with your part of the form before handling the user
            // signup
            return UsernamePasswordAuthProvider.handleSignup(ctx());
        }
    }

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}    
    
    //Initial load settings WS for the Mobile App
    public static Result getAppSettings(Integer width, Integer height, String version, String so){
        try {
            ObjectNode data = Json.newObject();
            data.put("company_name", Config.getString("company-name"));
            data.put("app_version",Config.getString("app-version"));
            ObjectNode response = Json.newObject();
            data.put("build_version", Config.getString("build-version"));
            //colocamos la configuracion de upstream para que la tengamos en la app
            data.put("upstream_app_key", Config.getString("upstreamAppKey"));
            data.put("upstream_app_version", Config.getString("upstreamAppVersion"));
            data.put("upstream_service_id", Config.getString("upstreamServiceID"));
            data.put("upstream_url", Config.getString("upstreamURL"));
            String upstreamGuestUser = Config.getString("upstreamGuestUser");
            String upstreamGuestPassword = Config.getString("upstreamGuestPassword");
            String authString = upstreamGuestUser+":"+upstreamGuestPassword;
            byte[] encodedBytes = Base64.encodeBase64(authString.getBytes());
            authString =  new String(encodedBytes);
            data.put("upstream_guest_auth_token", authString);
            data.put("upstream_guest_user", upstreamGuestUser);
            data.put("upstream_guest_password", upstreamGuestPassword);
            data.put("upstream_guest_user_id", Config.getString("upstreamUserID"));
            data.put("server_version", Config.getString("server-version"));
            Language language = Language.finder.byId(Config.getInt("default-language"));
            data.put("default_language", language.toJson());
            ObjectNode versionObject = Json.newObject();
            String lastVersion = null;
            String lastVersionURL = null;
            if(so.equalsIgnoreCase("android")){
                lastVersion = Utils.getAndroidVersion();
                lastVersionURL = Utils.getAndroidVersionURL();
            } else {
                lastVersion = Utils.getiOSVersion();
                lastVersionURL = Utils.getiOSVersionURL();
            }
            if(lastVersion != null && lastVersionURL != null) {
                int updateAvailable = 0; //There isn't update available
                int mandatory = 0; //The update isn't mandatory
                if(lastVersion.compareTo(version) > 0){
                    //There is update available
                    updateAvailable = 1;
                    String firstLastVersionToken = lastVersion.substring(0,lastVersion.indexOf("."));
                    String firstVersionToken = version.substring(0,version.indexOf("."));

                    if(firstLastVersionToken.compareTo(firstVersionToken)>0){
                        mandatory = 1;
                    }
                }
                
                versionObject.put("update",updateAvailable);
                versionObject.put("mandatory",mandatory);
                if(updateAvailable>0){
                    versionObject.put("download",lastVersionURL);
                    versionObject.put("new_version",lastVersion);
                }
            } else {
                versionObject.put("update",0);
            }
            data.put("version", versionObject);
            data.put("max_news", Config.getString("max-news"));
            data.put("wap_terms", Config.getString("wap_terms"));
            data.put("wap_help",Config.getString("wap_help"));
            response.put(Config.ERROR_KEY, 0);
            response.put(Config.DESCRIPTION_KEY, "OK");
            response.put(Config.RESPONSE_KEY,data);
            return ok(response);
        }catch (Exception e) {
            Utils.printToLog(Application.class, "Error manejando settings", "obteniendo los settings del app ", true, e, "support-level-1", Config.LOGGER_ERROR);
            ObjectNode response = Json.newObject();
            response.put(Config.ERROR_KEY, 1);
            response.put(Config.DESCRIPTION_KEY, "Error buscando el registro");
            response.put(Config.EXCEPTION_KEY, e.getMessage());
            return badRequest(response);
        }
    }

}
