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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sorcerer on 10/28/14.
 */
public class PlayersController extends HecticusController {

    public static Result getTopScorers(Long idCompetition, String date){
        try {
            List<Scorer> fullList = Scorer.getTournamentScorers(idCompetition,date);
            ArrayList data = new ArrayList();
            if (fullList != null && !fullList.isEmpty()){
                //i got data
                for (int i = 0; i < fullList.size(); i++){
                    data.add(fullList.get(i).toJson());
                }
            }
            //build response
            ObjectNode response;
            response = hecticusResponse(0, "ok", "news", data);
            data.clear();
            return ok(response);
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getPlayersByTeam(Long idTeam){
        try{List<Scorer> fullList = null;
            ArrayList data = new ArrayList();
            if (fullList != null && !fullList.isEmpty()){
                //i got data
                for (int i = 0; i < fullList.size(); i++){
                    data.add(fullList.get(i).toJson());
                }
            }
            //build response
            ObjectNode response;
            response = hecticusResponse(0, "ok", "news", data);
            data.clear();
            return ok(response);
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }


    public static Result getTopScorersByCompetition(Integer idApp, Integer pageSize, Integer page){
        try {
            ObjectNode response = null;
            Apps app = Apps.findId(idApp);
            if(app != null) {
                ArrayList data = new ArrayList();
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams[]");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                List<Competition> competitionsByApp = null;
                if (teams != null && !teams.isEmpty()) {
                    competitionsByApp = Competition.getActiveCompetitionsByAppAndTeams(app, teams);
                    teams.clear();
                } else {
                    competitionsByApp = app.getCompetitions();//Competition.getActiveCompetitionsByApp(app);
                }
                for (Competition competition : competitionsByApp) {
                    List<Scorer> fullList = null;
                    if(pageSize == 0){
                        fullList = competition.getScorers();//Scorer.getTournamentScorers(competition.getIdCompetitions());
                    } else {
                        fullList = competition.getScorers(page, pageSize);
                    }
                    ObjectNode competitionJson = competition.toJsonSimple();
                    if (fullList != null && !fullList.isEmpty()) {
                        //i got data
                        for (int i = 0; i < fullList.size(); i++) {
                            data.add(fullList.get(i).toJson());
                        }
                        competitionJson.put("scorers", Json.toJson(data));
                        data.clear();
                    }
                    responseData.add(competitionJson);
                }
                competitionsByApp.clear();
                response = hecticusResponse(0, "ok", "leagues", responseData);
                responseData.clear();
                return ok(response);
            } else {
                response = buildBasicResponse(1, "El app " + idApp + " no existe");
                return notFound(response);
            }
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getCompetitionTopScorers(Integer idApp, Integer idCompetition, Integer pageSize,Integer page){
        try {
            ObjectNode response = null;
            Apps app = Apps.findId(idApp);
            Competition competition = Competition.getCompetitionByApp(app, idCompetition);
            if(competition != null) {
                ArrayList<ObjectNode> scorers = new ArrayList<>();
                List<Scorer> tournamentScorers = Scorer.getTournamentScorers(competition.getIdCompetitions(), page, pageSize);
                for(Scorer scorer : tournamentScorers) {
                    scorers.add(scorer.toJson());
                }
                tournamentScorers.clear();
                response = hecticusResponse(0, "ok", "scorers", scorers);
                scorers.clear();
                return ok(response);
            } else {
                response = buildBasicResponse(1, "La competencia " + idCompetition + " no esta disponible para el app " + idApp);
                return notFound(response);
            }
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getCompetitionTopScorersForClient(Integer idApp){
        try {
            Apps app = Apps.findId(idApp);
            int scorersToDeliver = Config.getInt("scorers-to-deliver");
            Map<String, String[]> requestMap = request().queryString();
            String[] teamsArray = requestMap.get("teams[]");
            ArrayList<Long> competitionsIDs = new ArrayList<>();
            ObjectNode response = null;
            for(int i = 0; i < teamsArray.length; ++i){
                Team team = Team.findById(Long.parseLong(teamsArray[i]));
                if(team != null){
                    for(TeamHasCompetition teamHasCompetition : team.getCompetitions()){
                        Competition competition = teamHasCompetition.getCompetition();
                        if(competition.getApp().getIdApp() == app.getIdApp() && !competitionsIDs.contains(competition.getIdCompetitions())){
                            competitionsIDs.add(teamHasCompetition.getCompetition().getIdCompetitions());
                        }
                    }
                } else {
                    System.out.println("null " + teamsArray[i]);
                }
            }

            List<Competition> otherCompetitions = null;
            if(competitionsIDs != null && !competitionsIDs.isEmpty()){
                otherCompetitions = Competition.getCompetitionsByAppNotIn(app, competitionsIDs);
            } else {
                otherCompetitions = Competition.getCompetitionsByApp(app);
            }
            if(otherCompetitions != null && !otherCompetitions.isEmpty()){
                for(Competition competition : otherCompetitions){
                    competitionsIDs.add(competition.getIdCompetitions());
                }
                otherCompetitions.clear();
            }

            if(competitionsIDs != null && !competitionsIDs.isEmpty()) {
                ArrayList<ObjectNode> data = new ArrayList<>();
                List<Scorer> tournamentScorers = null;
                ArrayList<ObjectNode> scorers = new ArrayList<>();
                for (long id : competitionsIDs) {
                    tournamentScorers = Scorer.getTournamentScorers(id, 0, scorersToDeliver);
                    ObjectNode comp = Json.newObject();
                    comp.put("id_competition", id);
                    for (Scorer scorer : tournamentScorers) {
                        scorers.add(scorer.toJson());
                    }
                    comp.put("scorers", Json.toJson(scorers));
                    data.add(comp);
                    tournamentScorers.clear();
                    scorers.clear();
                }
                competitionsIDs.clear();
                response = hecticusResponse(0, "ok", "data", data);
                data.clear();
                return ok(response);
            } else {
                response = buildBasicResponse(1, "no hay data disponible");
                return notFound(response);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }
}
