import backend.ServerInstance;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.exceptions.AccessDeniedException;
import com.feth.play.module.pa.exceptions.AuthException;
import controllers.routes;
import exceptions.CouldNotCreateInstanceException;
import models.Config;
import models.SecurityRole;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import utils.Utils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by plesse on 7/10/14.
 */
public class Global extends GlobalSettings {

    private void initialData() {
        if (SecurityRole.find.findRowCount() == 0) {
            for (final String roleName : Arrays
                    .asList(controllers.Application.USER_ROLE)) {
                final SecurityRole role = new SecurityRole();
                role.roleName = roleName;
                role.save();
            }
        }
    }

    @Override
    public void onStart(Application application) {
        super.onStart(application);

        PlayAuthenticate.setResolver(new PlayAuthenticate.Resolver() {

            @Override
            public Call login() {
                // Your login page
                return routes.Application.login();
            }

            @Override
            public Call afterAuth() {
                // The user will be redirected to this page after authentication
                // if no original URL was saved
                return routes.Application.index();
            }

            @Override
            public Call afterLogout() {
                return routes.Application.index();
            }

            @Override
            public Call auth(final String provider) {
                // You can provide your own authentication implementation,
                // however the default should be sufficient for most cases
                return com.feth.play.module.pa.controllers.routes.Authenticate
                        .authenticate(provider);
            }

            @Override
            public Call askMerge() {
                return routes.Account.askMerge();
            }

            @Override
            public Call askLink() {
                return routes.Account.askLink();
            }

            @Override
            public Call onException(final AuthException e) {
                if (e instanceof AccessDeniedException) {
                    return routes.Signup
                            .oAuthDenied(((AccessDeniedException) e).getProviderKey());
                }

                // more custom problem handling here...
                return super.onException(e);
            }
        });
        initialData();
        try{
            ServerInstance.getInstance();
        } catch (CouldNotCreateInstanceException ex){
            Utils.printToLog(Global.class, "ERROR CRITICO Apagando " + Config.getString("app-name"), "No se pudo crear la instancia", true, ex, "support-level-1", Config.LOGGER_ERROR);
            super.onStop(application);
        }
    }

    @Override
    public void onStop(Application application) {
        try {
            ServerInstance.getInstance().shutdown();
        } catch (Exception ex) {

        }
        super.onStop(application);
    }

    @SuppressWarnings("rawtypes")
    Action newAction = new Action.Simple() {
        @Override
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            F.Promise<String> promiseOfString = F.Promise.promise(
                    new F.Function0<String>() {
                        public String apply() {
                            return "You dont have access to this service, contact the Administrator for more information";
                        }
                    }
            );

            return promiseOfString.map(
                    new F.Function<String, Result>() {
                        public Result apply(String i) {
                            return forbidden(i);
                        }
                    }
            );
        }
    };

    private class ActionWrapper extends Action.Simple {
        public ActionWrapper(Action<?> action) {
            this.delegate = action;
        }

        @Override
        public F.Promise<Result> call(Http.Context ctx) throws java.lang.Throwable {
            F.Promise<Result> result = this.delegate.call(ctx);
            Http.Response response = ctx.response();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, HECTICUS-X-AUTH-TOKEN");
            return result;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Action onRequest(Http.Request request, Method actionMethod) {
        String ipString = request.remoteAddress();
        String invoker = actionMethod.getDeclaringClass().getName();
        String[] octetos = ipString.split("\\.");
        Logger.of("request").trace("ipString = " + ipString  + " request.path() = " + request.path() + " invoker = " + invoker);
        if(invoker.startsWith("controllers.news") ||
                invoker.startsWith("controllers.footballapi") ||
                invoker.startsWith("controllers.Application") ||
                invoker.startsWith("controllers.events") ||
                invoker.startsWith("controllers.UsersView") ||
                invoker.startsWith("controllers.NewsView") ||
                invoker.startsWith("controllers.Instances") ||
                invoker.startsWith("controllers.ConfigsView")){
            if(ipString.equals("127.0.0.1") || ipString.startsWith("10.0.3")
                    || (ipString.startsWith("10.182.") && Integer.parseInt(octetos[2]) <= 127 )
                    || ipString.startsWith("10.181.")
                    || ipString.startsWith("10.208.")
                    || request.path().equals("190.14.219.174")
                    || request.path().equals("201.249.204.73")
                    || request.path().equals("186.74.13.178")){
                if(!invoker.startsWith("controllers.Application")){
                    Logger.info("Pass request from " + ipString + " to " + invoker);
                }
//                return super.onRequest(request, actionMethod);
                return new ActionWrapper(super.onRequest(request, actionMethod));
            }else{
                Logger.info("Deny request from " + ipString + " to " + invoker);
                return new ActionWrapper(newAction);
            }
        }else{
            Logger.info("Deny request from " + ipString + " to " + invoker);
            return new ActionWrapper(newAction);
        }
    }
}
