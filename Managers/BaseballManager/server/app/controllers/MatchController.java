package controllers;

import Scrapper.EventScrapper;
import Scrapper.Scrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//import jdk.nashorn.internal.ir.ObjectNode;
import models.Config;
import models.domain.Game;
import models.domain.League;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            ArrayList<ObjectNode> data = new ArrayList();
            League competition = League.getByID(idLeague);
            if (competition != null) {
                int gamesLimit = Config.getInt("game-match-limit");
                List<Game> game = Game.finder.where().eq("league", competition).ge("game_date", sdf.format(today.getTime())).setMaxRows(gamesLimit).orderBy("game_date asc").findList();
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
}
