package controllers.footballapi;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.HecticusController;
import models.Apps;
import models.Config;
import models.football.*;
import play.libs.Json;
import play.mvc.Result;
import utils.DateAndTime;
import utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by karina on 5/13/14.
 */
public class AfpFutbolWs extends HecticusController {

    private static final short NORMAL = 0;
    private static final short EXTERNAL = 1;
    private static final short FIFA = 2;

    public static Result getCompetition(Integer idApp){
        try {
            Apps app = Apps.findId(idApp);
            List<Competition> fullList = Competition.getCompetitionsByApp(app);
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

    public static Result getTeams(Integer idCompetition){
        ObjectNode result = Json.newObject();
        try{
            List<Team> list = Team.getList();
            ArrayList<ObjectNode> jlist = new ArrayList<ObjectNode>();
            Iterator<Team> it = list.iterator();
            while(it.hasNext()){
                jlist.add(it.next().toJson());
            }
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY, Json.toJson(jlist));
            return ok(result);
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getTeams", true, ex, "support-level-1", Config.LOGGER_ERROR);
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());

        }
        return badRequest(result);
    }

    public static Result getTeam(Long idTeam){
        ObjectNode result = Json.newObject();
        try{
            Team team = Team.findById(idTeam);
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY,(team!=null)?team.toJson():null);
            return ok(result);
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getTeam, con el parámetro " + idTeam, true, ex, "support-level-1", Config.LOGGER_ERROR);
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());

        }
        return badRequest(result);
    }

    public static Result getPhases(Long idCompetitions, String sd, String end){
        ObjectNode result = Json.newObject();
        try{
            List<Phase> list = Phase.getList(idCompetitions, sd, end);
            ArrayList<ObjectNode> jlist = new ArrayList<ObjectNode>();
            Iterator<Phase> it = list.iterator();
            while(it.hasNext()){
                jlist.add(it.next().toJson());
            }
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");

            Competition comp = Competition.findById(idCompetitions);
            if(comp == null){
                result.put(Config.ERROR_KEY, 1);
                result.put(Config.DESCRIPTION_KEY,"No existe la competition id:"+idCompetitions);
            } else {
                ObjectNode node = Json.newObject();
                node.put("competition",comp.toJson());
                node.put("phases", Json.toJson(jlist));
                result.put(Config.RESPONSE_KEY,node);
            }
            return ok(result);
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getPhases", true, ex, "support-level-1", Config.LOGGER_ERROR);
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());
        }
        return badRequest(result);
    }

    public static Result getPhase(Long idPhase){
        ObjectNode result = Json.newObject();
        try{
            Phase phase = Phase.findById(idPhase);
            ObjectNode node = null;
            if(phase != null){
                node = phase.toJson();
                node.put("competition",phase.getComp().toJson());
            }
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY,node);
            return ok(result);
        }catch(Exception ex){
            Utils.printToLog(AfpFutbolWs.class, "Error desconocido en Play", "Ocurrio un error en getPhase, con el parámetro " + idPhase, true, ex, "support-level-1", Config.LOGGER_ERROR);
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());

        }
        return badRequest(result);
    }


    public static Result getGameMatches(Long idPhase, String sd, String ed, Integer status){ //sd:start date, ed: end date
        ObjectNode result = Json.newObject();

        try{
            boolean run = true;
            Phase phase = new Phase();
            if(idPhase > 0){
                phase = Phase.findById(idPhase);
                run = (phase != null);
            }
            ArrayList<ObjectNode> list = new ArrayList<ObjectNode>();
            if(run){
                if(!sd.isEmpty() && !DateAndTime.validDate(sd, "yyyyMMddHHmmss")){
                    throw new Exception("El formato de tiempo sd = "+sd+" es incorrecto");
                }
                if(!ed.isEmpty() && !DateAndTime.validDate(ed, "yyyyMMddHHmmss")){
                    throw new Exception("El formato de tiempo ed = "+ed+" es incorrecto");
                }
                Iterator<GameMatch> it = (idPhase>0 && sd.isEmpty())?phase.getMatches().iterator():
                                          GameMatch.getList(idPhase, sd, ed, Short.parseShort("" + status)).iterator();

                while (it.hasNext()){
                    list.add(it.next().toJson());
                }
            }else throw new Exception("La phase con id: "+idPhase+" no exite");
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY, Json.toJson(list));
            return ok(result);
        }catch (Exception ex){
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage()+": "+ex.getLocalizedMessage());
        }

        return badRequest(result);
    }

    public static Result getGameMatch(Long idMatch, boolean withEvents){
        ObjectNode result = getTypeMatch(NORMAL,idMatch,withEvents);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    public static Result getGameMatchByExternalId(Long idExternal,boolean withEvents){
        ObjectNode result = getTypeMatch(EXTERNAL,idExternal,withEvents);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    public static Result getGameMatchByFifaId(Long idFifa,boolean withEvents){
        ObjectNode result = getTypeMatch(FIFA,idFifa,withEvents);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    public static Result getEvents(Long idMatches,String action, String period,String tstart, String tend){
        ObjectNode result = getTypeMatchEvents(NORMAL,idMatches,action,period,tstart,tend);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    public static Result getEventsByExternalMatch(Long idMatches,String action, String period,String tstart, String tend){
        ObjectNode result = getTypeMatchEvents(EXTERNAL,idMatches,action,period,tstart,tend);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    public static Result getEventsByFifaMatch(Long idMatches,String action,String period,String tstart, String tend){
        ObjectNode result = getTypeMatchEvents(FIFA,idMatches,action,period,tstart,tend);
        int error = result.get(Config.ERROR_KEY).asInt();
        if(error == 0){
            return ok(result);
        }
        return badRequest(result);
    }

    private  static ObjectNode getTypeMatch(short type, Long idMatch, boolean withEvents){
        ObjectNode result = Json.newObject();

        try{

            GameMatch match;
            if(type == EXTERNAL) match = GameMatch.findByIdExternal(idMatch);
            else if(type == FIFA) match = GameMatch.findByIdFifa(idMatch);
            else match = GameMatch.findById(idMatch);

            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY,
                    Json.toJson(match != null ? (withEvents) ? match.toJsonWithEvents() : match.toJson() : null));

        }catch (Exception ex){
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());
        }

        return result;
    }

    private static ObjectNode getTypeMatchEvents(short type, Long idMatch,String action,String period,String tstart, String tend){
        ObjectNode result = Json.newObject();
        try{
            int lastMinute = -1;

            String[] minuteRequest = request().queryString().get("last_minute");
            if(minuteRequest != null){
                lastMinute = Integer.parseInt(minuteRequest[0]);
            }

            boolean run = true;
            GameMatch match;
            if(type == EXTERNAL) match = GameMatch.findByIdExternal(idMatch);
            else if(type == FIFA) match = GameMatch.findByIdFifa(idMatch);
            else match = GameMatch.findById(idMatch);

            run = (match != null);
            ArrayList<ObjectNode> list = new ArrayList<ObjectNode>();
            if(run){
                String date = match.getDate().substring(0,8);
                if(!tstart.isEmpty() && !DateAndTime.validDate(date + tstart, "yyyyMMddHHmmss")){
                    throw new Exception("El formato de tiempo ts = "+tstart+" es incorrecto");
                }
                if(!tend.isEmpty() && !DateAndTime.validDate(date + tend, "yyyyMMddHHmmss")){
                    throw new Exception("El formato de tiempo te = "+tend+" es incorrecto");
                }
                Iterator<GameMatchEvent> it = (action.isEmpty() && period.isEmpty() && tstart.isEmpty() && tend.isEmpty() && lastMinute < 0)?
                        match.getEvents().iterator():
                        GameMatchEvent.getList(match, action, period, tstart, tend, lastMinute).iterator();

                ObjectNode node;
                long idTeam = -1;

                while (it.hasNext()){
                    node = it.next().toJson();
                    idTeam = node.get("id_teams").asLong();

                    if(idTeam >= 0){
                        Team team = Team.findById(idTeam);
                        if(team != null){
                            node.remove("id_teams");
                            node.put("team",team.toJson());
                        }
                    }

                    list.add(node);
                }
            }else throw new Exception("El game match con id: "+idMatch+" no exite");
            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put("home_goals", match.getHomeTeamGoals());
            result.put("away_goals", match.getAwayTeamGoals());
            result.put(Config.RESPONSE_KEY, Json.toJson(list));
        }catch (Exception ex){
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage());
        }

        return result;
    }

    public static Result getRankingByIdPhase(String id,boolean ext){

        ObjectNode result = Json.newObject();

        try{
            List<Rank> list = null;
            if(ext) {
                list = Rank.getListByExtIdPhase(id);
            } else {
                list = Rank.getListByIdPhase(id);
            }

            if(list == null) {
                throw new Exception("Error generico cargando el Ranking para el idPhase:"+id+",ext:"+ext);
            }

            ArrayList<ObjectNode> jlist = new ArrayList<ObjectNode>();
            Iterator<Rank> it = list.iterator();
            while(it.hasNext()){
                jlist.add(it.next().toJson());
            }

            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY, Json.toJson(jlist));
            return ok(result);
        } catch (Exception ex) {
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage()+": "+ex.getLocalizedMessage());
        }

        return badRequest(result);
    }

    public static Result getGlobalRanking(Long idCompetition){
        ObjectNode result = Json.newObject();
        try {

           List<Phase> list = Phase.getList(idCompetition, "", "");
            if (list == null) {
                throw new Exception("Error obteniendo phases para el competition id:"+idCompetition);
            }

           ArrayList<ObjectNode> jlist = new ArrayList<ObjectNode>();
            Iterator<Phase> it = list.iterator();

            ArrayList<ObjectNode> innerList;
            Iterator<Rank> innerIt;

            ObjectNode node;
            ObjectNode aux;


            Phase aPhase;
            while(it.hasNext()){
                aPhase = it.next();
                List<Rank> rankList = Rank.getListByIdPhase(""+aPhase.getIdPhases());
                innerList = new ArrayList<ObjectNode>();
                innerIt = rankList.iterator();

                while(innerIt.hasNext()){
                    aux = innerIt.next().toJson();
                    if(aux.has("phase")) aux.remove("phase");
                    innerList.add(aux);
                }

                node = aPhase.toJson();
                node.put("ranking", Json.toJson(innerList));
                jlist.add(node);
            }

            result.put(Config.ERROR_KEY, 0);
            result.put(Config.DESCRIPTION_KEY,"OK");
            result.put(Config.RESPONSE_KEY, Json.toJson(jlist));
            return ok(result);

        } catch (Exception ex) {
            result.put(Config.ERROR_KEY, 1);
            result.put(Config.DESCRIPTION_KEY,ex.getMessage()+": "+ex.getLocalizedMessage());
        }

        return badRequest(result);
    }
}
