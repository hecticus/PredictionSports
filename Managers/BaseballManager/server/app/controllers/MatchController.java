package controllers;

import Scrapper.EventScrapper;
import Scrapper.Scrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//import jdk.nashorn.internal.ir.ObjectNode;
import models.Config;
import models.domain.*;
import models.handlers.*;
import play.mvc.Controller;
import play.mvc.Result;

import play.libs.ws.*;
//import play.libs.WS.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;


import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.ws.ahc.*;
import play.libs.Json;
import utils.DateAndTime;

import java.util.*;

/**
 * Created by Ferck on 27/2/2017.
 */
public class MatchController extends HecticusController {

    public Result getActiveCompetitions() {
        List<League> Leagues = League.getActiveLeagues();
        ArrayList<ObjectNode> competitions = new ArrayList<ObjectNode>(Leagues.size());
        //ArrayNode competitions = new ArrayList<ObjectNode>(Leagues.size());
        for (League league : Leagues) {
            ObjectNode tmp = league.getJsonDashboard(true);
            competitions.add(tmp);
            //competitions.put(tmp);

        }

        return ok(hecticusResponse(0, "ok", "competitions", competitions));
    }

    public Result getFixturesForCompetitionGroupByDateNoPhase(Long idLeague) {
        try {
            Calendar today = new GregorianCalendar();
            today.add(Calendar.HOUR_OF_DAY, Config.getInt("server_hour_diff")); // adds one hour
            today.add(Calendar.MINUTE, Config.getInt("server_minute_diff")); // adds one hour
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            ArrayList<ObjectNode> data = new ArrayList();
            League competition = League.getByID(idLeague);
            if (competition != null) {
                int gamesLimit = Config.getInt("game-match-limit");
                List<Game> game = Game.finder.where().eq("league", competition).ne("status_id_status", 6).ne("status_id_status", 7).ne("status_id_status", 8).ge("game_date", today.getTime()).setMaxRows(gamesLimit).orderBy("game_date asc").findList();
                if (game != null && !game.isEmpty()) {
                    ArrayList<ObjectNode> fixtures = new ArrayList<ObjectNode>();
                    String pivot = new SimpleDateFormat("yyyyMMdd").format(game.get(0).getDate()).toString();
                    Calendar pivotMaximumDate = new GregorianCalendar();
                    pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd"));
                    Calendar maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate);
                    Calendar matchDate = null;
                    Calendar pivotDate = new GregorianCalendar();
                    pivotDate.setTime(DateAndTime.getDate(new SimpleDateFormat("yyyyMMdd").format(game.get(0).getDate()).toString(), "yyyyMMdd"));
                    SimpleDateFormat tzFormatter = new SimpleDateFormat("yyyyMMdd");
                    //tzFormatter.setTimeZone(timeZone);
                    for (Game Game : game) {
                        matchDate = new GregorianCalendar();
                        matchDate.setTime(DateAndTime.getDate(new SimpleDateFormat("yyyyMMdd").format(Game.getDate()).toString(), "yyyyMMdd"));
                        if (matchDate.before(maximumDate)) {
                            fixtures.add(Game.toJsonSimpleDate());
                        } else {
                            ObjectNode round = Json.newObject();
                            round.put("date", tzFormatter.format(pivotDate.getTime()));
                            round.put("matches", Json.toJson(fixtures));
                            data.add(round);
                            fixtures.clear();
                            fixtures.add(Game.toJsonSimpleDate());
                            pivot = new SimpleDateFormat("yyyyMMdd").format(Game.getDate()).toString();
                            pivotDate.setTime(DateAndTime.getDate(new SimpleDateFormat("yyyyMMdd").format(Game.getDate()).toString(), "yyyyMMdd"));
                            pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd"));
                            maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate);
                        }
                    }
                    if (!fixtures.isEmpty()) {
                        ObjectNode round = Json.newObject();
                        round.put("date", tzFormatter.format(pivotDate.getTime()));
                        round.put("matches", Json.toJson(fixtures));
                        data.add(round);
                        fixtures.clear();
                    }
                    game.clear();
                }
                ObjectNode competitionJson = competition.getJson();
                competitionJson.put("fixtures", Json.toJson(data));
                data.clear();
                return ok(hecticusResponse(0, "OK", competitionJson));
            } else {
                return notFound(buildBasicResponse(1, "La competencia " + idLeague + " no existe"));
            }

        } catch (Exception ex) {
            //Utils.printToLog(MatchesController.class, null, "Error", false, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public Result getFixturesByID(Integer idGameMatch) {
        try {
            Game gameMatch = Game.finder.where().eq("idGame", idGameMatch).findUnique();
            if (gameMatch != null) {
                return ok(hecticusResponse(0, "ok", gameMatch.toJsonSimpleDate()));
            } else {
                return notFound(buildBasicResponse(1, "El match " + idGameMatch + " no existe"));
            }
        } catch (Exception ex) {
            //Utils.printToLog(MatchesController.class, null, "Error", false, ex, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }


    public  Result getFixturesDatePaged(String date, Integer pageSize,Integer page, String timezoneName){
        try {
            long start = System.currentTimeMillis();
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            //Apps app = Apps.findId(idApp);
            //Utils.printToLog(MatchesController.class, "", "find id:" + (System.currentTimeMillis() - start), false, null, "support-level-1", Config.LOGGER_INFO);
                /*long time = System.currentTimeMillis();
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }*/
                Calendar today = new GregorianCalendar();
                if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("today")){
                    today.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
                    today.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
                    today.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
                }
                Calendar minimumDate = DateAndTime.getMinimumDate(today);
                Calendar maximumDate = DateAndTime.getMaximumDate(today);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
                //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                //Utils.printToLog(MatchesController.class, "", "time:"+(System.currentTimeMillis() - time), false, null, "support-level-1", Config.LOGGER_INFO);
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                long comt = System.currentTimeMillis();
                List<League> competitions = null;
              //  if (teams != null && !teams.isEmpty()) {
                //    competitions = League.getCompetitionsPage(app, page, pageSize, sdf.format(minimumDate.getTime()), sdf.format(maximumDate.getTime()), teams);
                //    teams.clear();
                //} else {
                    competitions = League.getLeaguePage( page, pageSize, minimumDate.getTime(), maximumDate.getTime());
               // }

                //Utils.printToLog(MatchesController.class, "", "query comp:" + (System.currentTimeMillis() - comt), false, null, "support-level-1", Config.LOGGER_INFO);
                if (competitions != null && !competitions.isEmpty()) {
                    long sort = System.currentTimeMillis();
                    //Collections.sort(competitions, new CompetitionsSortComparator());


                    //Utils.printToLog(MatchesController.class, "", "sort:" + (System.currentTimeMillis() - sort), false, null, "support-level-1", Config.LOGGER_INFO);
                    ArrayList data = new ArrayList();
                    List<Game> fullList = null;
                    for (League competition : competitions) {
                        fullList = competition.getMatchesByDateDB(sdf.format(minimumDate.getTime()), sdf.format(maximumDate.getTime()));
//                        fullList = competition.getMatchesByDate(minimumDate, maximumDate);
                        if (fullList != null && !fullList.isEmpty()) {
                            ObjectNode competitionJson = competition.toJsonNoPhases(false);
                            for (int i = 0; i < fullList.size(); i++) {
                                data.add(fullList.get(i).toJsonSimpleDateMTM());
                            }
                            competitionJson.put("fixtures", Json.toJson(data));
                            data.clear();
                            responseData.add(competitionJson);
                            fullList.clear();
                        }

                        //Utils.printToLog(MatchesController.class, "", "ite:" + (System.currentTimeMillis() - fort), false, null, "support-level-1", Config.LOGGER_INFO);
                    }

                    competitions.clear();
                }
                ObjectNode response = hecticusResponse(0, "ok", "leagues", responseData);
                responseData.clear();
                return ok(response);

        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }


    public  Result getMinuteToMinuteForCompetition( Integer idCompetition, Long idMatch, Long idEvent, Boolean forward){
        try {
            ArrayList<ObjectNode> responseData = new ArrayList();
            League competition = League.getByID((long)idCompetition);
            if (competition != null) {
                Game gameMatch = Game.getById(idMatch);   //competition.getMatch(idMatch);
                if (gameMatch != null) {
                    //List<Action> events = gameMatch.getEventsNoDB(idEvent, forward);
                    List<Action> events = null;
                    ObjectNode resp = Json.newObject();

                    resp.put("home_team", gameMatch.getHomeTeam().toJson());
                    resp.put("home_team_goals", gameMatch.getHrHome());
                    resp.put("away_team", gameMatch.getAwayTeam().toJson());
                    resp.put("away_team_goals", gameMatch.getHrAway());

                    //resp.put("status", gameMatch.getStatus().(requestLanguage, app.getLanguage()));

                    ObjectNode aux = Json.newObject();
                    Status tmp = Status.convertToFootball(gameMatch.getStatus());
                    //json.put("id_status", tmp.getIdStatus());

                    aux.put("id_status", tmp.getIdStatus());
                    aux.put("name", tmp.getName());
                    resp.put("status", aux);



                    if (events != null & !events.isEmpty()) {
                        Action pivot = events.get(0);
                        ArrayList<ObjectNode> periodData = new ArrayList<>();
                        for (Action gameMatchEvent : events) {
                            if (gameMatchEvent.getInning().getIdInning() == pivot.getInning().getIdInning()) {
                                periodData.add(gameMatchEvent.toJsonNoInning(idMatch));
                            } else {
                                ObjectNode period = Json.newObject();
                                period.put("period", pivot.getInning().toJson());
                                period.put("events", Json.toJson(periodData));
                                periodData.clear();
                                periodData.add(gameMatchEvent.toJsonNoInning(idMatch));
                                pivot = gameMatchEvent;
                                responseData.add(period);
                            }
                        }
                        events.clear();
                        if (!periodData.isEmpty()) {
                            ObjectNode period = Json.newObject();
                            period.put("period", pivot.getInning().toJson());
                            period.put("events", Json.toJson(periodData));
                            periodData.clear();
                            responseData.add(period);
                        }

                    }

                    resp.put("actions", Json.toJson(responseData));
                    responseData.clear();
                    return ok(hecticusResponse(0, "ok", resp));
                } else {
                    return notFound(buildBasicResponse(2, "El partido " + idMatch + " no existe"));
                }
            } else {
                return notFound(buildBasicResponse(3, "La competencia " + idCompetition + " no existe, o no esta activa, para el app "));
            }

        } catch (Exception ex) {
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

}

/*

public class CompetitionsSortComparator implements Comparator<League> {
    @Override
    public int compare(League c1, League c2) {
        return c1.getType().getSort() - c2.getType().getSort();
    }
}


*/