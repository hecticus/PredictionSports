package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import models.domain.Game;
import models.domain.League;
import models.domain.Team;
import models.domain.TeamHasLeague;
import play.libs.Json;
import play.libs.ws.*;
import play.libs.ws.ahc.*;
import play.mvc.Result;
import utils.DateAndTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

//import jdk.nashorn.internal.ir.ObjectNode;
//import play.libs.WS.*;

/**
 * Created by Ferck on 27/2/2017.
 */
public class TeamsController extends HecticusController {
    
    public Result getTeams(Long idLeague){
        try{
            League League = models.domain.League.getByID(idLeague);
            ArrayList<ObjectNode> teamsList = new ArrayList<ObjectNode>();
            if(League != null){
                List<Team> teams = TeamHasLeague.getTeamsByLeague(idLeague);
                for(Team team : teams) {
                    teamsList.add(Team.getById(team.getIdTeam()).toJson());
                }
                ObjectNode response = hecticusResponse(0, "ok", "teams", teamsList);
                teams.clear();
                teamsList.clear();
                return ok(response);
            } else {
                return notFound(buildBasicResponse(1, "La League " + idLeague + " no existe"));
            }
        }catch(Exception ex){
            //Utils.printToLog(null, "Error desconocido en Play", "Ocurrio un error en getTeams", true, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public Result getTeamsGt(Long idTeam){
        try{
            List<Team> teams = Team.finder.where().gt("id_team", idTeam).findList();
            ArrayList<ObjectNode> teamsList = new ArrayList<>();
            if(teams != null && !teams.isEmpty()){
                for(Team team : teams){
                    teamsList.add(team.toJson());
                }
            }
            ObjectNode response = hecticusResponse(0, "ok", "teams", teamsList);
            teams.clear();
            teamsList.clear();
            return ok(response);
        }catch(Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

}
