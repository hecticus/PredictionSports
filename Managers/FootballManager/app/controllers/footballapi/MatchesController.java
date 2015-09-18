package controllers.footballapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comparators.CompetitionsSortComparator;
import controllers.HecticusController;
import models.Apps;
import models.Config;
import models.Language;
import models.football.*;
import play.libs.F;
import play.libs.Json;
import play.mvc.Result;
import utils.DateAndTime;
import utils.Utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sorcerer on 10/29/14.
 */
public class MatchesController extends HecticusController {

    //retorna el calendario de la competicion
    public static Result getFixtures(Long idCompetition){
        return ok();
    }

    //todays calendar

    //todays result
    public static Result getTodayFinished(Long idCompetition){
        try {
            TimeZone timeZone = TimeZone.getDefault();//Apps.getTimezone(idApp);
            Calendar today = new GregorianCalendar(timeZone);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.setTimeZone(timeZone);
            String todaysDate = simpleDateFormat.format(today.getTime());
            return getFinishedByDate(idCompetition, todaysDate);
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFinishedByDate(Long idCompetition, String date){
        try {
            List<GameMatch> fullList = GameMatch.getGamematchByDate(idCompetition, date);
            ArrayList data = new ArrayList();
            LinkedHashMap values = new LinkedHashMap();
            String id = null;
            String gameResult = null;
            if (fullList != null && !fullList.isEmpty()) {
                for (int i = 0; i < fullList.size(); i++) {
                    id = fullList.get(i).fixtureJson().get("id_game_matches").asText();
                    gameResult = fullList.get(i).fixtureJson().get("game_result").asText();
                    values.put(id, gameResult);
                }
                data.add(values);
                values.clear();
                fullList.clear();
            }
            //build response
            ObjectNode response;
            response = hecticusResponse(0, "ok", "results", data);
            return ok(response);
        } catch (Exception ex) {
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getAllFixtures(long idCompetition){
        try {
            List<GameMatch> fullList = GameMatch.findAllByIdCompetition(idCompetition);
            ArrayList data = new ArrayList();
            if (fullList != null && !fullList.isEmpty()) {
                for (int i = 0; i < fullList.size(); i++) {
                    data.add(fullList.get(i).fixtureJson());
                }
            }
            //build response
            ObjectNode response;
            response = hecticusResponse(0, "ok", "results", data);
            data.clear();
            return ok(response);
        } catch (Exception ex) {
            return badRequest(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesDate(Integer idApp, String date, Integer idLanguage, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("today")){
                    today.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
                    today.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
                    today.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
                }
                String minimumDate = DateAndTime.getMinimumDate(today, timezoneName, "yyyMMddHHmmss");
                String maximumDate = DateAndTime.getMaximumDate(today, timezoneName, "yyyMMddHHmmss");
                Language requestLanguage = null;
                if(idLanguage > 0) {
                    requestLanguage = Language.getByID(idLanguage);
                }
                if (idLanguage <= 0 || requestLanguage == null){
                    requestLanguage = app.getLanguage();
                }
                ArrayList data = new ArrayList();
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                List<Competition> competitionsByApp = null;
                if (teams != null && !teams.isEmpty()) {
                    competitionsByApp = Competition.getActiveCompetitionsByAppAndTeams(app, teams);
                    teams.clear();
                } else {
                    competitionsByApp = app.getCompetitions();
                }
                ObjectNode competitionJson = null;
                List<GameMatch> fullList = null;
                for (Competition competition : competitionsByApp) {
                    fullList = GameMatch.getGamematchBetweenDates(competition.getIdCompetitions(), minimumDate, maximumDate);
                    competitionJson = competition.toJsonNoPhases(requestLanguage, app.getLanguage(), false, timeZone);
                    if (fullList != null && !fullList.isEmpty()) {
                        for (GameMatch gameMatch : fullList) {
                            data.add(gameMatch.toJson(requestLanguage, app.getLanguage(), timeZone));
                        }
                        fullList.clear();
                    }
                    competitionJson.put("fixtures", Json.toJson(data));
                    data.clear();
                    responseData.add(competitionJson);
                }
                competitionsByApp.clear();
                ObjectNode response = hecticusResponse(0, "ok", "leagues", responseData);
                responseData.clear();
                return ok(response);
            } else {
                return notFound(buildBasicResponse(4, "La aplicacion  " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesDateAll(Integer idApp, String date, Integer idLanguage, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("today")){
                    today.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
                    today.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
                    today.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
                }
                String minimumDate = DateAndTime.getMinimumDate(today, timezoneName, "yyyMMddHHmmss");
                String maximumDate = DateAndTime.getMaximumDate(today, timezoneName, "yyyMMddHHmmss");
                Language requestLanguage = null;
                if(idLanguage > 0) {
                    requestLanguage = Language.getByID(idLanguage);
                }
                if (idLanguage <= 0 || requestLanguage == null){
                    requestLanguage = app.getLanguage();
                }
                ArrayList data = new ArrayList();
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                List<Competition> competitionsByApp = null;
                if (teams != null && !teams.isEmpty()) {
                    competitionsByApp = Competition.getActiveCompetitionsByAppAndTeams(app, teams);
                    teams.clear();
                }
                List<GameMatch> fullList = null;
                fullList = GameMatch.getGamematchBetweenDatesForcompetitions(competitionsByApp, minimumDate, maximumDate);
                for (GameMatch gameMatch : fullList) {
                    data.add(gameMatch.toJsonWithCompetitions(requestLanguage, app.getLanguage(), timeZone));
                }
                fullList.clear();
                if(competitionsByApp != null) {
                    competitionsByApp.clear();
                }
                ObjectNode response = hecticusResponse(0, "ok", "fixtures", data);
                data.clear();
                return ok(response);
            } else {
                return notFound(buildBasicResponse(4, "La aplicacion  " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesGroupByDate(Integer idApp, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                String minimumDate = DateAndTime.getMinimumDate(today, timezoneName, "yyyMMdd");
                ArrayList<ObjectNode> data = new ArrayList();
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                List<Competition> competitionsByApp = null;
                if (teams != null && !teams.isEmpty()) {
                    competitionsByApp = Competition.getActiveCompetitionsByAppAndTeams(app, teams);
                    teams.clear();
                } else {
                    competitionsByApp = app.getCompetitions();
                }
                List<Phase> phases = null;
                List<GameMatch> gameMatches = null;
                String pivot = null;
                ArrayList<ObjectNode> fixtures = new ArrayList<>();
                for (Competition competition : competitionsByApp) {
                    phases = Phase.getPhasesFromDate(competition, minimumDate);
                    if (phases == null || phases.isEmpty()) {
                        phases = Phase.getLatestPhasesPaged(competition, 0, 1);
                    }
                    if (phases != null & !phases.isEmpty()) {
                        gameMatches = GameMatch.finder.where().eq("competition", competition).in("phase", phases).orderBy("date asc").findList();
                        if (gameMatches != null && !gameMatches.isEmpty()) {
                            pivot = gameMatches.get(0).getDate().substring(0, 8);
                            for (GameMatch gameMatch : gameMatches) {
                                if (gameMatch.getDate().startsWith(pivot)) {
                                    fixtures.add(gameMatch.toJsonSimple(timeZone));
                                } else {
                                    ObjectNode round = Json.newObject();
                                    round.put("date", pivot);
                                    round.put("matches", Json.toJson(fixtures));
                                    data.add(round);
                                    fixtures.clear();
                                    fixtures.add(gameMatch.toJsonSimple(timeZone));
                                    pivot = gameMatch.getDate().substring(0, 8);
                                }
                            }
                            if (!fixtures.isEmpty()) {
                                ObjectNode round = Json.newObject();
                                round.put("date", pivot);
                                round.put("matches", Json.toJson(fixtures));
                                data.add(round);
                                fixtures.clear();
                            }
                            gameMatches.clear();
                        }
                        phases.clear();
                    }
                    if (!data.isEmpty()) {
                        ObjectNode competitionJson = competition.toJsonSimple();
                        competitionJson.put("fixtures", Json.toJson(data));
                        data.clear();
                        responseData.add(competitionJson);
                    }
                }
                competitionsByApp.clear();
                ObjectNode response = hecticusResponse(0, "OK", "leagues", responseData);
                responseData.clear();
                return ok(response);
            } else {
                return notFound(buildBasicResponse(1, "El app " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesForCompetitionGroupByDate(Integer idApp, Long idCompetition, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                String date = DateAndTime.getMinimumDate(today, timezoneName, "yyyMMdd");
                ArrayList<ObjectNode> data = new ArrayList();
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    List<Phase> phases = Phase.getPhasesFromDate(competition, date);
                    if (phases == null || phases.isEmpty()) {
                        phases = Phase.getLatestPhasesPaged(competition, 0, 1);
                    }
                    List<Phase> uniquePhaseByDate = Phase.getUniquePhaseByDateList(competition, date);
                    if(uniquePhaseByDate != null){
                        phases.addAll(uniquePhaseByDate);
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    df.setTimeZone(TimeZone.getTimeZone("UTC"));
                    if (phases != null & !phases.isEmpty()) {
                        List<GameMatch> gameMatches = GameMatch.finder.where().eq("competition", competition).in("phase", phases).orderBy("date asc").findList();
                        if (gameMatches != null && !gameMatches.isEmpty()) {
                            ArrayList<ObjectNode> fixtures = new ArrayList<>();
                            String pivot = gameMatches.get(0).getDate().substring(0, 8);
                            Calendar pivotMaximumDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                            pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                            Calendar maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                            Calendar matchDate = null;
                            Calendar pivotDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                            pivotDate.setTime(DateAndTime.getDate(gameMatches.get(0).getDate(), gameMatches.get(0).getDate().length()==8?"yyyyMMdd":"yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
                            SimpleDateFormat tzFormatter = new SimpleDateFormat("yyyyMMdd");
                            tzFormatter.setTimeZone(timeZone);
                            for (GameMatch gameMatch : gameMatches) {
                                matchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                                matchDate.setTime(DateAndTime.getDate(gameMatch.getDate(), gameMatch.getDate().length() == 8 ? "yyyyMMdd":"yyyyMMddHHmmss", TimeZone.getTimeZone("UTC")));
                                if (matchDate.before(maximumDate)) {
                                    fixtures.add(gameMatch.toJsonSimple(timeZone));
                                } else {
                                    ObjectNode round = Json.newObject();
                                    round.put("date", tzFormatter.format(pivotDate.getTime()));
                                    round.put("matches", Json.toJson(fixtures));
                                    data.add(round);
                                    fixtures.clear();
                                    fixtures.add(gameMatch.toJsonSimple(timeZone));
                                    pivot = gameMatch.getDate().substring(0, 8);
                                    pivotDate.setTime(DateAndTime.getDate(gameMatch.getDate(), gameMatch.getDate().length()==8?"yyyyMMdd":"yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
                                    pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                                    maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                                }
                            }
                            if (!fixtures.isEmpty()) {
                                ObjectNode round = Json.newObject();
                                round.put("date", tzFormatter.format(pivotDate.getTime()));
                                round.put("matches", Json.toJson(fixtures));
                                data.add(round);
                                fixtures.clear();
                            }
                            gameMatches.clear();
                        }
                    }
                    phases.clear();
                    ObjectNode competitionJson = competition.toJsonSimple();
                    competitionJson.put("fixtures", Json.toJson(data));
                    data.clear();
                    return ok(hecticusResponse(0, "OK", competitionJson));
                } else {
                    return notFound(buildBasicResponse(1, "La competencia " + idCompetition + " no existe"));
                }
            } else {
                return notFound(buildBasicResponse(1, "El app " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static F.Promise<Result> getFixturesDatePagedWrapper(final Integer idApp, final Integer idLanguage, final String date, final Integer pageSize, final Integer page, final String timezoneName) {
        F.Promise<Result> promiseOfObjectNode = F.Promise.promise(
            new F.Function0<Result>() {
                public Result apply() {
                    return getFixturesDatePaged(idApp, idLanguage, date, pageSize, page, timezoneName);
                }
            }
        );

        return promiseOfObjectNode.map(
            new F.Function<Result, Result>() {
                public Result apply(Result i) {
                    return i;
                }
            }
        );
    }

    public static Result getFixturesDatePaged(Integer idApp, Integer idLanguage, String date, Integer pageSize,Integer page, String timezoneName){
        try {
            long start = System.currentTimeMillis();
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            Utils.printToLog(MatchesController.class, "", "find id:" + (System.currentTimeMillis() - start), false, null, "support-level-1", Config.LOGGER_INFO);
            if(app != null) {
                long time = System.currentTimeMillis();
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("today")){
                    today.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
                    today.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
                    today.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
                }
                Calendar minimumDate = DateAndTime.getMinimumDate(today, timezoneName);
                Calendar maximumDate = DateAndTime.getMaximumDate(today, timezoneName);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                Utils.printToLog(MatchesController.class, "", "time:"+(System.currentTimeMillis() - time), false, null, "support-level-1", Config.LOGGER_INFO);
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                long comt = System.currentTimeMillis();
                List<Competition> competitions = null;
                if (teams != null && !teams.isEmpty()) {
                    competitions = Competition.getCompetitionsPage(app, page, pageSize, sdf.format(minimumDate.getTime()), sdf.format(maximumDate.getTime()), teams);
                    teams.clear();
                } else {
                    competitions = Competition.getCompetitionsPage(app, page, pageSize, sdf.format(minimumDate.getTime()), sdf.format(maximumDate.getTime()));
                }

                Utils.printToLog(MatchesController.class, "", "query comp:" + (System.currentTimeMillis() - comt), false, null, "support-level-1", Config.LOGGER_INFO);
                if (competitions != null && !competitions.isEmpty()) {
                    long sort = System.currentTimeMillis();
                    Collections.sort(competitions, new CompetitionsSortComparator());
                    Language requestLanguage = null;
                    if(idLanguage > 0) {
                        requestLanguage = Language.getByID(idLanguage);
                    }
                    if (idLanguage <= 0 || requestLanguage == null){
                        requestLanguage = app.getLanguage();
                    }

                    Utils.printToLog(MatchesController.class, "", "sort:" + (System.currentTimeMillis() - sort), false, null, "support-level-1", Config.LOGGER_INFO);
                    ArrayList data = new ArrayList();
                    List<GameMatch> fullList = null;
                    for (Competition competition : competitions) {
                        fullList = competition.getMatchesByDateDB(sdf.format(minimumDate.getTime()), sdf.format(maximumDate.getTime()));
//                        fullList = competition.getMatchesByDate(minimumDate, maximumDate);
                        if (fullList != null && !fullList.isEmpty()) {
                            ObjectNode competitionJson = competition.toJsonNoPhases(requestLanguage, app.getLanguage(), false, timeZone);
                            for (int i = 0; i < fullList.size(); i++) {
                                data.add(fullList.get(i).toJson(requestLanguage, app.getLanguage(), timeZone));
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
            } else {
                return notFound(buildBasicResponse(2, "El app " + idApp + " no existe"));
            }
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesCompetitionDatePaged(Integer idApp, Integer idCompetition, String date, Integer pageSize, Integer page, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                if(timeZone == null){
                    return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
                }
                Calendar today = new GregorianCalendar(timeZone);
                if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("today")){
                    today.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
                    today.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
                    today.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
                }
                Calendar minimumDate = DateAndTime.getMinimumDate(today, timezoneName);
                Calendar maximumDate = DateAndTime.getMaximumDate(today, timezoneName);
//                ArrayList responseData = new ArrayList();
                Competition competition = app.getCompetition(idCompetition);
                if(competition != null){
                    List<GameMatch> matchesByDate = competition.getMatchesByDate(minimumDate, maximumDate, page, pageSize);
                    if (matchesByDate != null && !matchesByDate.isEmpty()) {
                        ArrayList data = new ArrayList();
                        for (int i = 0; i < matchesByDate.size(); i++) {
                            data.add(matchesByDate.get(i).toJson());
                        }
                        int totalMatches = competition.countMatchesByDate(date);
                        ObjectNode matches = Json.newObject();
                        matches.put("total", totalMatches);
                        matches.put("fixtures", Json.toJson(data));
                        data.clear();
//                        responseData.add(matches);
                        matchesByDate.clear();
                        return ok(hecticusResponse(0, "ok", matches));
                    } else {
                        return ok(buildBasicResponse(3, "No hay partidos para la fecha " + date));
                    }
                } else {
                    return notFound(buildBasicResponse(2, "La competencia " + idCompetition + " no existe"));
                }
            } else {
                return notFound(buildBasicResponse(1, "El app " + idApp + " no existe"));
            }
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesByIDs(Integer idApp){
        try {
            String[] matches = getFromQueryString("match[]");
            ArrayList<Long> matchesIDs = new ArrayList<>();
            ArrayList responseData = new ArrayList();
            ObjectNode response = null;
            for(String match : matches){
                matchesIDs.add(Long.parseLong(match));
            }
            List<GameMatch> gameMatches = GameMatch.finder.where().in("idGameMatches", matchesIDs).findList();
            for(GameMatch gameMatch : gameMatches){
                responseData.add(gameMatch.toJson());
            }
            response = hecticusResponse(0, "ok", "matches", responseData);
            responseData.clear();
            gameMatches.clear();
            matchesIDs.clear();
            return ok(response);
        }catch (Exception ex){
            ex.printStackTrace();
            return badRequest(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getFixturesByID(Integer idApp, Integer idGameMatch){
        try {
            Apps app = Apps.findId(idApp);
            if(app != null){
                GameMatch gameMatch = GameMatch.finder.where().eq("idGameMatches", idGameMatch).findUnique();
                if(gameMatch != null) {
                    return ok(hecticusResponse(0, "ok", gameMatch.toJson()));
                } else {
                    return notFound(buildBasicResponse(1, "El match " + idGameMatch + " no existe"));
                }
            } else {
                return notFound(buildBasicResponse(1, "El app " + idApp + " no existe"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }


    public static Result getActiveCompetitions(Integer idApp, Integer idLanguage, Boolean ids, Boolean closestMatch, String timezoneName){
        try {
            Apps app = Apps.findId(idApp);
            ObjectNode response = null;
            if(app != null) {
                TimeZone timeZone = null;
                if(!timezoneName.isEmpty()){
                    timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                }
                ArrayList data = new ArrayList();
                ArrayList responseData = new ArrayList();
                List<Team> teams = null;
                Language requestLanguage = null;
                if(idLanguage > 0) {
                    requestLanguage = Language.getByID(idLanguage);
                }
                if (idLanguage <= 0 || requestLanguage == null){
                    requestLanguage = app.getLanguage();
                }
                String[] favorites = getFromQueryString("teams");
                if (favorites != null && favorites.length > 0) {
                    teams = Team.finder.where().in("idTeams", favorites).findList();
                }
                List<Competition> competitionsByApp = null;
                if (teams != null && !teams.isEmpty()) {
                    competitionsByApp = Competition.getActiveCompetitionsByAppAndTeams(app, teams);
                    teams.clear();
                } else {
                    competitionsByApp = app.getCompetitions();
                }
                Collections.sort(competitionsByApp, new CompetitionsSortComparator());
                ArrayList competitions = null;
                if (ids) {
                    competitions = new ArrayList<Long>(competitionsByApp.size());
                } else {
                    competitions = new ArrayList<ObjectNode>(competitionsByApp.size());
                }
                for (Competition competition : competitionsByApp) {
                    competitions.add(ids ? competition.getIdCompetitions() : (competition.toJsonNoPhases(requestLanguage, app.getLanguage(), closestMatch, timeZone)));
                }
                response = hecticusResponse(0, "ok", ids ? "ids" : "competitions", competitions);
                competitions.clear();
                return ok(response);
            } else {
                response = buildBasicResponse(1, "El app " + idApp + " no existe");
                return notFound(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getPhasesForCompetition(Integer idApp, Integer idCompetition, Integer idLanguage, String timezoneName){
        try {
            if(timezoneName.isEmpty()){
                return badRequest(buildBasicResponse(1, "Es necesario pasar un timezone"));
            }
            timezoneName = timezoneName.replaceAll(" ", "").trim();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                ArrayList<ObjectNode> responseData = new ArrayList();
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    TimeZone timeZone = DateAndTime.getTimezoneFromID(timezoneName);
                    Calendar today = new GregorianCalendar(timeZone);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    simpleDateFormat.setTimeZone(timeZone);
                    List<Phase> phases = competition.getPhases(today);
                    if (phases != null && !phases.isEmpty()) {
                        Language requestLanguage = null;
                        if(idLanguage > 0) {
                            requestLanguage = Language.getByID(idLanguage);
                        }
                        if (idLanguage <= 0 || requestLanguage == null){
                            requestLanguage = app.getLanguage();
                        }
                        if (competition.getType().getType() == 0) {
                            responseData.add(phases.get(0).toJson(requestLanguage, app.getLanguage()));
                        } else {
                            Phase pivot = phases.get(0);
                            for (Phase phase : phases) {
                                if (phase.getNivel() != pivot.getNivel()) {
                                    ObjectNode pivotJson = pivot.toJson(requestLanguage, app.getLanguage());
                                    responseData.add(pivotJson);
                                }
                                pivot = phase;
                            }
                            ObjectNode pivotJson = pivot.toJson(requestLanguage, app.getLanguage());
                            responseData.add(pivotJson);
                            phases.clear();
                        }
                        ObjectNode data = Json.newObject();
                        data.put("phases", Json.toJson(responseData));
                        responseData.clear();
                        return ok(hecticusResponse(0, "ok", data));
                    } else {
                        return notFound(buildBasicResponse(2, "La competition " + idCompetition + " no tiene phases"));
                    }
                } else {
                    return notFound(buildBasicResponse(1, "La competition " + idCompetition + " no existe"));
                }
            } else {
                return notFound(buildBasicResponse(1, "El app " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getCurrentAndLastPhaseForCompetition(Integer idApp, Integer idCompetition, String date, Integer idLanguage){
        try {
            ObjectNode response = null;
            Apps app = Apps.findId(idApp);
            if(app != null) {
                ArrayList<ObjectNode> responseData = new ArrayList();
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    Language requestLanguage = null;
                    if(idLanguage > 0) {
                        requestLanguage = Language.getByID(idLanguage);
                    }
                    if (idLanguage <= 0 || requestLanguage == null){
                        requestLanguage = app.getLanguage();
                    }
                    Calendar dateCalendar = new GregorianCalendar(app.getTimezone().getTimezone());
                    Date dateDate = DateAndTime.getDate(date, "yyyyMMdd");
                    dateCalendar.setTime(dateDate);
                    Phase activePhase = competition.getActivePhase(dateCalendar, app.getTimezone().getTimezone());
                    List<Phase> latestPhases = competition.getLatestPhases(dateCalendar, app.getTimezone().getTimezone());
                    ObjectNode data = Json.newObject();
                    if(activePhase != null) {
                        data.put("active_phase", activePhase.toJson(requestLanguage, app.getLanguage()));
                    }
                    if(latestPhases != null && !latestPhases.isEmpty()){
                        Phase phase = latestPhases.get(latestPhases.size() - 1);
                        data.put("last_phase", phase.toJson(requestLanguage, app.getLanguage()));
                    }
                    latestPhases.clear();
                    response = hecticusResponse(0, "ok", data);
                    return ok(response);
                } else {
                    response = buildBasicResponse(1, "La competition " + idCompetition + " no existe");
                    return notFound(response);
                }
            } else {
                response = buildBasicResponse(1, "El app " + idApp + " no existe");
                return notFound(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getPhasesToNotify(Integer idApp){
        try {
            ObjectNode response = null;
            Apps app = Apps.findId(idApp);
            ArrayList<ObjectNode> responseData = new ArrayList();
            List<Competition> competitions = Competition.getActiveCompetitionsByApp(app);
            if(competitions != null && !competitions.isEmpty()) {
                TimeZone timeZone = Apps.getTimezone(idApp);
                Calendar today = new GregorianCalendar(timeZone);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                simpleDateFormat.setTimeZone(timeZone);
                String date = simpleDateFormat.format(today.getTime());
                Collections.sort(competitions, new CompetitionsSortComparator());
                List<Phase> phases = null;
                for(Competition competition : competitions) {
                    phases = Phase.getPhasesToPush(competition, date);
                    if (phases != null && !phases.isEmpty()) {
                        responseData.add(phases.get(0).toJsonToPush());
                        for(Phase phase : phases){
                            phase.setPushed(true);
                            phase.update();
                        }
                        phases.clear();
                    }
                }
                competitions.clear();
                ObjectNode data = Json.newObject();
                data.put("phases", Json.toJson(responseData));
                responseData.clear();
                response = hecticusResponse(0, "ok", data);
                return ok(response);
            } else {
                response = buildBasicResponse(0, "La app " + idApp + " no tiene competencia");
                return notFound(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getCalendar(Integer idApp, Integer idCompetition, String date, Long phase, String operator){
        try {
            ObjectNode response = null;
            Apps app = Apps.findId(idApp);
            if(app != null) {
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    List<GameMatch> gameMatches = null;
                    if (date != null && !date.isEmpty() && operator != null && !operator.isEmpty()) {
                        gameMatches = GameMatch.findAllByIdCompetitionAndDate(competition.getIdCompetitions(), date, operator);
                    } else if (phase > 0 && operator != null && !operator.isEmpty()) {
                        gameMatches = GameMatch.findAllByIdCompetitionAndPhase(competition.getIdCompetitions(), phase, operator);
                    } else {
                        gameMatches = GameMatch.findAllByIdCompetitionOrderedByDate(competition.getIdCompetitions());
                    }
                    ArrayList<JsonNode> calendar = new ArrayList<>();
                    if (gameMatches != null && !gameMatches.isEmpty()) {
                        ArrayList<ObjectNode> day = new ArrayList<>();
                        GameMatch pivot = gameMatches.get(0);
                        for (GameMatch gameMatch : gameMatches) {
                            if (gameMatch.getDate().startsWith(pivot.getDate().substring(0, 8))) {
                                day.add(gameMatch.toJson());
                            } else {
                                calendar.add(Json.toJson(day));
                                day.clear();
                                pivot = gameMatch;
                                day.add(gameMatch.toJson());
                            }
                        }
                        calendar.add(Json.toJson(day));
                        gameMatches.clear();
                    }
                    response = hecticusResponse(0, "ok", "days", calendar);
                    return ok(response);
                } else {
                    response = buildBasicResponse(1, "la competencia " + idCompetition + " no existe, o no esta activa, para el app " + idApp);
                    return notFound(response);
                }
            } else {
                response = buildBasicResponse(0, "La app " + idApp + " no tiene competencia");
                return notFound(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getMinuteToMinuteForCompetition(Integer idApp, Integer idCompetition, Long idMatch, Integer idLanguage, Long idEvent, Boolean forward){
        try {
            ArrayList<ObjectNode> responseData = new ArrayList();
            Apps app = Apps.findId(idApp);
            if(app != null) {
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    GameMatch gameMatch = competition.getMatch(idMatch);
                    if (gameMatch != null) {
                        List<GameMatchEvent> events = gameMatch.getEventsNoDB(idEvent, forward);
                        ObjectNode resp = Json.newObject();
                        Language requestLanguage = Language.getByID(idLanguage);
                        if(requestLanguage == null){
                            requestLanguage = app.getLanguage();
                        }
                        resp.put("home_team", gameMatch.getHomeTeam().toJsonSimple());
                        resp.put("home_team_goals", gameMatch.getHomeTeamGoals());
                        resp.put("away_team", gameMatch.getAwayTeam().toJsonSimple());
                        resp.put("away_team_goals", gameMatch.getAwayTeamGoals());

                        resp.put("status", gameMatch.getStatus().toJson(requestLanguage, app.getLanguage()));

                        if (events != null & !events.isEmpty()) {
                            GameMatchEvent pivot = events.get(0);
                            ArrayList<ObjectNode> periodData = new ArrayList<>();
                            for (GameMatchEvent gameMatchEvent : events) {
                                if (gameMatchEvent.getPeriod().getIdPeriods() == pivot.getPeriod().getIdPeriods()) {
                                    periodData.add(gameMatchEvent.toJsonNoPeriod(requestLanguage, app.getLanguage()));
                                } else {
                                    ObjectNode period = Json.newObject();
                                    period.put("period", pivot.getPeriod().toJson());
                                    period.put("events", Json.toJson(periodData));
                                    periodData.clear();
                                    periodData.add(gameMatchEvent.toJsonNoPeriod(requestLanguage, app.getLanguage()));
                                    pivot = gameMatchEvent;
                                    responseData.add(period);
                                }
                            }
                            events.clear();
                            if (!periodData.isEmpty()) {
                                ObjectNode period = Json.newObject();
                                period.put("period", pivot.getPeriod().toJson());
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
                    return notFound(buildBasicResponse(3, "La competencia " + idCompetition + " no existe, o no esta activa, para el app " + idApp));
                }
            } else {
                return notFound(buildBasicResponse(4, "La aplicacion  " + idApp + " no existe"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }

    public static Result getPushableEventsForApp(Integer idApp){
        try {
            Apps app = Apps.findId(idApp);
            ObjectNode response = null;
            if(app != null) {
                ObjectNode responseData = Json.newObject();
                List<Competition> competitions = app.getCompetitions();
                TimeZone timeZone = TimeZone.getDefault();//Apps.getTimezone(idApp);
                Calendar today = new GregorianCalendar(timeZone);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                simpleDateFormat.setTimeZone(timeZone);
                String todaysDate = simpleDateFormat.format(today.getTime());
                ArrayList<GameMatch> matches = new ArrayList<>();
                if (competitions != null && !competitions.isEmpty()) {
                    Collections.sort(competitions, new CompetitionsSortComparator());
                    List<GameMatch> todayMatches = null;
                    for (Competition competition : competitions) {
                        todayMatches = GameMatch.findAllByIdCompetitionAndDate(competition.getIdCompetitions(), todaysDate, "eq");
                        if (todayMatches != null && !todayMatches.isEmpty()) {
                            matches.addAll(todayMatches);
                            todayMatches.clear();
                        }
                    }
                    if (!matches.isEmpty()) {
                        ArrayList<ObjectNode> minToMin = new ArrayList<>();
                        ArrayList<ObjectNode> match = new ArrayList<>();
                        List<GameMatchEvent> eventsToPush = GameMatchEvent.finder.where().in("gameMatch", matches).eq("generated", false).orderBy("gameMatch.idGameMatches asc, _sort asc").findList();
                        if (eventsToPush != null && !eventsToPush.isEmpty()) {
                            GameMatchEvent pivot = eventsToPush.get(0);
                            for (GameMatchEvent gameMatchEvent : eventsToPush) {
                                if (gameMatchEvent.getGameMatch().getIdGameMatches() == pivot.getGameMatch().getIdGameMatches()) {
                                    match.add(gameMatchEvent.toJsonForPush());
                                } else {
                                    ObjectNode data = Json.newObject();
                                    data.put("match", pivot.getGameMatch().toJsonPush());
                                    data.put("events", Json.toJson(match));
                                    minToMin.add(data);
                                    match.clear();
                                    match.add(gameMatchEvent.toJsonForPush());
                                    pivot = gameMatchEvent;
                                }
                                gameMatchEvent.setGenerated(true);
                                gameMatchEvent.update();
                            }
                            if (!match.isEmpty()) {
                                ObjectNode data = Json.newObject();
                                data.put("match", pivot.getGameMatch().toJsonPush());
                                data.put("events", Json.toJson(match));
                                minToMin.add(data);
                                match.clear();
                            }
                            if (!minToMin.isEmpty()) {
                                responseData.put("min_to_min", Json.toJson(minToMin));
                            }
                            eventsToPush.clear();
                        }
                        matches.clear();
                    }
                    competitions.clear();
                }
                List<News> ungeneratedNews = null;
                if (Config.getInt("push-all-news") == 1) {
                    ungeneratedNews = News.finder.where().eq("id_app", idApp).eq("generated", false).ilike("publicationDate", todaysDate + "%").findList();
                } else {
                    ungeneratedNews = News.finder.where().eq("id_app", idApp).eq("generated", false).ilike("publicationDate", todaysDate + "%").eq("featured", true).findList();
                }

                if (ungeneratedNews != null && !ungeneratedNews.isEmpty()) {
                    ArrayList<ObjectNode> newsToPush = new ArrayList<>();
                    for (News news : ungeneratedNews) {
                        newsToPush.add(news.toJsonPush());
                        news.setGenerated(true);
                        news.update();
                    }
                    if (!newsToPush.isEmpty()) {
                        responseData.put("news", Json.toJson(newsToPush));
                        newsToPush.clear();
                    }
                    ungeneratedNews.clear();
                }

                response = hecticusResponse(0, "ok", responseData);
                return ok(response);
            } else {
                response = buildBasicResponse(4, "La aplicacion  " + idApp + " no existe");
                return notFound(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error:" + ex.toString()));
        }
    }


}
