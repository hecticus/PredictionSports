package controllers.footballapi;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.HecticusController;
import models.Apps;
import models.Config;
import models.football.Competition;
import models.football.Scorer;
import models.football.Team;
import models.football.TeamHasCompetition;
import play.libs.Json;
import play.mvc.Result;
import utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by plesse on 1/27/15.
 */
public class TeamsController  extends HecticusController {

    public static Result getTeamsForApp(Integer idApp, Integer pageSize, Integer page){
        try {
            Apps app = Apps.findId(idApp);
            ArrayList responseData = new ArrayList();
            List<Team> teams = null;
            if(pageSize == 0){
                teams = Team.finder.all();
            } else {
                teams = Team.finder.fetch("competitions").where().eq("competitions.competition.app", app).orderBy("name asc").setFirstRow(page).setMaxRows(pageSize).findList();
            }
            for(Team team : teams){
                responseData.add(team.toJsonSimple());
            }
            ObjectNode response = hecticusResponse(0, "ok", "teams", responseData);
            teams.clear();
            responseData.clear();
            return ok(response);
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getTeams(Long idCompetition){
        try{
            ArrayList<ObjectNode> teamsList = new ArrayList<ObjectNode>();
            Competition competition = Competition.findById(idCompetition);
            if(competition != null){
                List<TeamHasCompetition> teams = competition.getTeams();
                for(TeamHasCompetition teamHasCompetition : teams){
                    teamsList.add(teamHasCompetition.getTeam().toJsonSimple());
                }
                ObjectNode response = hecticusResponse(0, "ok", "teams", teamsList);
                teams.clear();
                teamsList.clear();
                return ok(response);
            } else {
                return notFound(buildBasicResponse(1, "La competition " + idCompetition + " no existe"));
            }
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getTeams", true, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getTeam(Long idTeam){
        try{
            Team team = Team.findById(idTeam);
            if(team != null){
                return ok(hecticusResponse(0, "ok", team.toJson()));
            } else {
                return notFound(buildBasicResponse(1, "el equipo " + idTeam + " no existe"));
            }
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getTeam, con el parámetro " + idTeam, true, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getTeamsGt(Long idTeam){
        try{
            List<Team> teams = Team.finder.where().gt("idTeams", idTeam).findList();
            ArrayList<ObjectNode> teamsList = new ArrayList<>();
            if(teams != null && !teams.isEmpty()){
                for(Team team : teams){
                    teamsList.add(team.toJsonSimple());
                }
            }
            ObjectNode response = hecticusResponse(0, "ok", "teams", teamsList);
            teams.clear();
            teamsList.clear();
            return ok(response);
        }catch(Exception ex){
            Utils.printToLog(TeamsController.class, "Error desconocido en Play", "Ocurrio un error en getTeam, con el parámetro " + idTeam, true, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }
}
