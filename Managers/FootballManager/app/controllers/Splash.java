package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.SplashConfig;
import models.football.Scorer;
import play.data.format.Formats;
import play.mvc.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import controllers.HecticusController;
import models.Apps;
import models.Config;
import models.football.Competition;
import models.football.Scorer;
import models.football.Team;
import models.football.TeamHasCompetition;
import play.libs.Json;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by palenge on 12/7/16.
 */
public class Splash extends HecticusController{

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static Result getCurrentSplash(){
        try {
            Date date = new Date();
            SplashConfig aux = new SplashConfig();
            aux = aux.getSplashConfig(sdf.format(date));;

            ObjectNode response;
            response = hecticusResponse(0, "ok", aux!= null? aux.toJson(): Json.newObject());
            //data.clear();
            return ok(response);
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

}



