package controllers;


import Scrapper.EventScrapper;
import Scrapper.Scrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.domain.Game;
import models.handlers.*;
import play.mvc.Controller;
import play.mvc.Result;

import play.libs.ws.*;
//import play.libs.WS.*;

import java.io.IOException;
import java.util.concurrent.CompletionStage;


import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.ws.ahc.*;

/**
 * Created by palenge on 12/28/16.
 */
public class Debugger extends Controller {

   // @Inject WSClient ws;

    public Result Scrapper() throws IOException {

        Scrapper scr = new Scrapper();
        scr.ScrapperDays();
        if(true)
            return ok();

        /*CompletionStage<WSResponse> jsonPromise = ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json").get();
        jsonPromise.handle((result, error) ->
        {
            System.out.println(result);

            return 1;
        });*/
        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);
        try
        {





            JsonNode data = null;
            JsonNode games = null;
            ArrayNode game = null;
            ArrayNode gameArray = null;
            //ws.close();

            CompletionStage<JsonNode> jsonPromise2 = ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json").get()
                    .thenApply(WSResponse::asJson);

            //JsonNode p = jsonPromise2.toCompletableFuture().get(1000, TimeUnit.DAYS);
            JsonNode p = jsonPromise2.toCompletableFuture().get();
            //JsonNode p = e.get();
            data = p.get("data");
            games = data.get("games");
            game = (ArrayNode) games.get("game");
            JsonNode obj;
            JsonNode lineScore;
            ArrayNode Innings;
            JsonNode Inn;

            for(int x=0 ; x< game.size();x++ ){
                //Game current_game = new Game();
                obj = game.get(x);

                //Verificamos que el partido aun no exista
                //if(GameHandler.CheckExist(obj.get("id").asText())) continue;
                Game current_game =  GameHandler.CheckAndGet(obj.get("id").asText());
                current_game.setIdentifier(obj.get("id").asText());
                // Obterner y/o obtener Liga
                current_game.setLeague(LeagueHandler.CheckAndInsert(obj.get("league").asText())) ;

                /// Obterner y/o obtener el Estadio el Venue
                current_game.setVenue(VenueHandler.CheckAndInsert(obj.get("venue_id").asLong(), obj.get("venue").asText())) ;
                /// Obterner y/o obtener el Equipo de casa
                current_game.setHomeTeam(TeamHandler.CheckAndInsert(obj.get("home_team_id").asLong(), obj.get("home_team_name").asText(), obj.get("home_code").asText(), obj.get("home_team_city").asText(),LeagueHandler.CheckAndInsert(obj.get("home_division").asText())));

                /// Obterner y/o obtener el Equipo visitante
                current_game.setAwayTeam(TeamHandler.CheckAndInsert(obj.get("away_team_id").asLong(), obj.get("away_team_name").asText(), obj.get("away_code").asText(), obj.get("away_team_city").asText(), LeagueHandler.CheckAndInsert(obj.get("away_division").asText())));

                current_game.setStatus(StatusHandler.CheckAndInsert(obj.get("status").get("status").asText())); ;


                current_game.setDate(GameHandler.DateUtil(obj.get("time_date").asText()));
                lineScore = obj.get("linescore");
                current_game.setHr(new LineScoreHandler(lineScore.get("hr")));
                current_game.setE(new LineScoreHandler(lineScore.get("e")));
                current_game.setSo(new LineScoreHandler(lineScore.get("so")));
                current_game.setR(new LineScoreHandler(lineScore.get("r")));
                current_game.setH(new LineScoreHandler(lineScore.get("h")));



                if(GameHandler.CheckExist(obj.get("id").asText()))
                    current_game.update();
                else
                    current_game.save();

                Innings = (ArrayNode) lineScore.get("inning");
                for(int i=0 ; i < Innings.size(); i++) {
                    Inn = Innings.get(i);
                    current_game.addInning(InningHandler.CheckAndInsert(current_game, i+1, Inn.hasNonNull("home") ? Inn.get("home").asInt(): 0, Inn.hasNonNull("away") ? Inn.get("away").asInt(): 0));
                }


                EventScrapper evt = new EventScrapper();


                //chequeamos los eventow de este partido en particular
                evt.Scrapper(current_game);

            }



            //JsonNode p = ((CompletableFuture<JsonNode>)jsonPromise2).get();
/*
            Object waitGuard = new Object();
            AtomicReference<JsonNode> resultReference = new AtomicReference();
            synchronized(waitGuard){
                jsonPromise2.thenAccept( jsonNode -> {
                    resultReference.set(jsonNode);
                    waitGuard.notify();
                });
                waitGuard.wait();
            }*/
            //JsonNode p = resultReference.get();
            //CompletableFuture<JsonNode>  e = jsonPromise2.toCompletableFuture();
            //JsonNode p = e.get();
            int q= 2;
            q = 2+2;
        }
        catch(Exception e)
        {
            //ws.close();
            System.out.println(e.getCause());
            ws.close();
        }
        ws.close();
        return ok();
    }
}

        /*
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]


        JsonNode aux;
        //return ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json").get().thenApplyAsync(response -> pepe(response.asJson()));
        //CompletionStage<Result> p =  ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json").get().thenApplyAsync(response -> pepe(response.asJson()));
        //CompletionStage<Result> p =  ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json");
        //p.toCompletableFuture().get();

        try {
            JsonNode data = null;
            JsonNode games = null;
            ArrayNode gameArray = null;

            CompletionStage<JsonNode> jsonPromise = ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_11/master_scoreboard.json").setContentType("application/json").get().get(8000);
            //CompletionStage<JsonNode> jsonPromise = ws.url("https://openlibrary.org/api/books?bibkeys=ISBN:0201558025,LCCN:93005405&format=json").get()
                   // .thenApplyAsync(WSResponse::asJson);
            //JsonNode p = jsonPromise.toCompletableFuture().get(60, TimeUnit.SECONDS);
            CompletableFuture<JsonNode>  e = jsonPromise.toCompletableFuture();
            JsonNode p = e.get();
            data = p.get("data");
            games = data.get("games");
            if (games.isArray()) {
                for (final JsonNode objNode : games) {
                    System.out.println(objNode);
                }
            }
            //Json = games.get("game");
        }catch(Exception e)
        {
            System.out.println(e.getCause());
        }
        //p.
        //responsa.
        //Promise<WS.Response> home
        //JsonNode responser;// = new ObjectNode();
        //ws.url("http://gd2.mlb.com/components/game/mlb/year_2016/month_09/day_10/master_scoreboard.json").get().thenApply(response -> response.asJson());
        //responser.
        //Scrapper p = new Scrapper();
        //p.test();
        //int p  =1 ;
        //p = 1+2;
        return ok();
    }

    public Result pepe(JsonNode tmp)
    {

        return ok();
    }

    public void scrap(JsonNode aux)
    {

    }
}
*/