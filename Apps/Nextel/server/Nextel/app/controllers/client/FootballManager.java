package controllers.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.FootballController;
import models.Config;
import models.clients.Client;
import models.clients.FootballClient;
import models.pushalerts.ClientHasPushAlerts;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by plesse on 12/18/14.
 */
public class FootballManager extends FootballController {

    public static Result getScorers(Integer idClient) {

        FootballClient client = (FootballClient) Client.getByID(idClient);
        StringBuilder teams = new StringBuilder();
        teams.append("http://" + Utils.getFootballManagerHost() + "/footballapi/v1/players/topScorers/" + Config.getInt("football-manager-id-app") + "?");
        for(ClientHasPushAlerts clientHasPushAlerts :client.getPushAlerts()){
            Integer idExt = clientHasPushAlerts.getPushAlert().getIdExt();
            if(idExt != null && idExt > 0){
                teams.append("teams[]=").append(idExt).append("&");
            }
        }

        F.Promise<WSResponse> result = WS.url(teams.toString()).get();
        ObjectNode footballResponse = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();

        footballResponse.get("error");
        JsonNode data = footballResponse.get("response");

        ObjectNode response = buildBasicResponse(0, "OK", data.get("data"));
        return ok(response);
    }

    public static ObjectNode callFootballManger(String path){
        StringBuilder url = new StringBuilder();
        url.append("http://").append(Utils.getFootballManagerHost()).append(request().path());
        F.Promise<WSResponse> result = WS.url(url.toString()).get();
        ObjectNode response = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
        return response;
    }

    //News
    public static Result getNews(Integer idApp, Integer offset, Integer count){
        return ok(callFootballManger(request().path()));
    }

    public static Result getNewsById(Long idNews){
        return ok(callFootballManger(request().path()));
    }


    public static Result getRecentNews(Integer idApp, Integer idLanguage, Long newsId, Boolean newest, Boolean first){
        return ok(callFootballManger(request().path()));
    }
    //End News

    //TeamController
    public static Result getTeamsForApp(Integer idApp, Integer pageSize, Integer page){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTeams(Long idCompetition){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTeam(Long idTeam){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTeamsGt(Long idTeam){
        return ok(callFootballManger(request().path()));
    }
    //End TeamController

    //AfpFutbolWs
    public static Result getCompetition(Integer idApp){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTeams(Integer idCompetition){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTeam2(Long idTeam){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPhases(Long idCompetitions, String sd, String end){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPhase(Long idPhase){
        return ok(callFootballManger(request().path()));
    }


    public static Result getGameMatches(Long idPhase, String sd, String ed, Integer status){ //sd:start date, ed: end date
        return ok(callFootballManger(request().path()));
    }

    public static Result getGameMatch(Long idMatch, boolean withEvents){
        return ok(callFootballManger(request().path()));
    }

    public static Result getGameMatchByExternalId(Long idExternal,boolean withEvents){
        return ok(callFootballManger(request().path()));
    }

    public static Result getGameMatchByFifaId(Long idFifa,boolean withEvents){
        return ok(callFootballManger(request().path()));
    }

    public static Result getEvents(Long idMatches,String action, String period,String tstart, String tend){
        return ok(callFootballManger(request().path()));
    }

    public static Result getEventsByExternalMatch(Long idMatches,String action, String period,String tstart, String tend){
        return ok(callFootballManger(request().path()));
    }

    public static Result getEventsByFifaMatch(Long idMatches,String action,String period,String tstart, String tend){
        return ok(callFootballManger(request().path()));
    }

    public static Result getRankingByIdPhase(String id,boolean ext){
        return ok(callFootballManger(request().path()));
    }

    public static Result getGlobalRanking(Long idCompetition){
        return ok(callFootballManger(request().path()));
    }
    //End AfpFutbolWs

    //MatchesController
    public static Result getFixtures(Long idCompetition){
        return ok(callFootballManger(request().path()));
    }

    public static Result getTodayFinished(Long idCompetition){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFinishedByDate(Long idCompetition, String date){
        return ok(callFootballManger(request().path()));
    }

    public static Result getAllFixtures(long idCompetition){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesDate(Integer idApp, String date, Integer idLanguage, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesDateAll(Integer idApp, String date, Integer idLanguage, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesGroupByDate(Integer idApp, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesForCompetitionGroupByDate(Integer idApp, Long idCompetition, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesDatePagedWrapper(final Integer idApp, final Integer idLanguage, final String date, final Integer pageSize, final Integer page, final String timezoneName) {
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesDatePaged(Integer idApp, Integer idLanguage, String date, Integer pageSize,Integer page, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesCompetitionDatePaged(Integer idApp, Integer idCompetition, String date, Integer pageSize, Integer page, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesByIDs(Integer idApp){
        return ok(callFootballManger(request().path()));
    }

    public static Result getFixturesByID(Integer idApp, Integer idGameMatch){
        return ok(callFootballManger(request().path()));
    }

    public static Result getActiveCompetitions(Integer idApp, Integer idLanguage, Boolean ids, Boolean closestMatch, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPhasesForCompetition(Integer idApp, Integer idCompetition, Integer idLanguage, String timezoneName){
        return ok(callFootballManger(request().path()));
    }

    public static Result getCurrentAndLastPhaseForCompetition(Integer idApp, Integer idCompetition, String date, Integer idLanguage){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPhasesToNotify(Integer idApp){
        return ok(callFootballManger(request().path()));
    }

    public static Result getCalendar(Integer idApp, Integer idCompetition, String date, Long phase, String operator){
        return ok(callFootballManger(request().path()));
    }

    public static Result getMinuteToMinuteForCompetition(Integer idApp, Integer idCompetition, Long idMatch, Integer idLanguage, Long idEvent, Boolean forward){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPushableEventsForApp(Integer idApp){
        return ok(callFootballManger(request().path()));
    }
    //End MatchesController

    //RankingController
    public static Result getRankings(Integer idApp, Integer idLanguage, String formattedToday){
        return ok(callFootballManger(request().path()));
    }

    public static Result getRankingsForPhase(Integer idApp, Integer idCompetition, Integer idLanguage, Long idPhase, Integer way){
        return ok(callFootballManger(request().path()));
    }
    //End RankingController

    //PlayersController
    public static Result getTopScorers(Long idCompetition, String date){
        return ok(callFootballManger(request().path()));
    }

    public static Result getPlayersByTeam(Long idTeam){
        return ok(callFootballManger(request().path()));
    }


    public static Result getTopScorersByCompetition(Integer idApp, Integer pageSize, Integer page){
        return ok(callFootballManger(request().path()));
    }

    public static Result getCompetitionTopScorers(Integer idApp, Integer idCompetition, Integer pageSize,Integer page){
        return ok(callFootballManger(request().path()));
    }

    public static Result getCompetitionTopScorersForClient(Integer idApp){
        return ok(callFootballManger(request().path()));
    }
    //End RankingController
}
