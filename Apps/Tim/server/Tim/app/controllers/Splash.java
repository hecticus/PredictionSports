package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.basic.SplashConfig;
import play.data.format.Formats;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import models.Config;

import play.libs.Json;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by palenge on 12/7/16.
 */
public class Splash extends Controller {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static Result getCurrentSplash(){
        try {
            Date date = new Date();
            SplashConfig aux = new SplashConfig();
            aux = aux.getSplashConfig(sdf.format(date));;

            ObjectNode response = Json.newObject();
            response.put(Config.ERROR_KEY, 0);
            response.put(Config.DESCRIPTION_KEY, "OK");
            response.put(Config.RESPONSE_KEY,aux!= null? aux.toJson(): Json.newObject());
            //response = hecticusResponse(0, "ok", aux!= null? aux.toJson(): Json.newObject());
            //data.clear();
            return ok(response);
        }catch (Exception ex){
            ObjectNode response = Json.newObject();
            response.put(Config.ERROR_KEY, 1);
            response.put(Config.DESCRIPTION_KEY, "Error buscando el registro");
            response.put(Config.EXCEPTION_KEY, ex.getMessage());
            return badRequest(response);
            //return internalServerError(response);
        }
    }

}



