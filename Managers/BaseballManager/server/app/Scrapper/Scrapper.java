package Scrapper;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.fasterxml.jackson.databind.JsonNode;
//import com.google.inject.Inject;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Config;
import models.domain.Game;
import models.domain.TeamHasLeague;
import models.handlers.*;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.ws.WSResponse;
//import scala.util.parsing.json.JSONArray;
//import scala.util.parsing.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import play.libs.ws.ahc.AhcWSClient;
import play.mvc.*;
import play.libs.ws.*;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * Created by palenge on 12/28/16.
 */
public class Scrapper {

    public int number_days = 6;

    public void ScrapperDays() throws IOException {
        //RankinrCreator rg = new RankinrCreator();
        //Scrapper(DateUtil(15));
        for (int i = 0 ; i< number_days; i++)
        {
            Scrapper(DateUtil(i));
        }

        RankinrCreator.executeRanking();

    }


    public Date DateUtil(int offset)
    {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR,  24 * offset);
        cal.add(Calendar.HOUR,  -24 );
        //cal.add(Calendar.MONTH, 2);
        return cal.getTime();
        //int month = cal.get(Calendar.MONTH);
    }
    public void  Scrapper(Date date) throws IOException {

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
            JsonNode game = null;
            ArrayNode gameArray = null;
            //ws.close();


            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            System.out.println("http://gd2.mlb.com/components/game/mlb/year_"+ cal.get(Calendar.YEAR) + "/month_" + (cal.get(Calendar.MONTH) < 10? "0" + (cal.get(Calendar.MONTH)+1): cal.get(Calendar.MONTH)+1) + "/day_" + (cal.get(Calendar.DAY_OF_MONTH) < 10? "0" + cal.get(Calendar.DAY_OF_MONTH): cal.get(Calendar.DAY_OF_MONTH)) + "/master_scoreboard.json");
            CompletionStage<JsonNode> jsonPromise2 = ws.url("http://gd2.mlb.com/components/game/mlb/year_"+ cal.get(Calendar.YEAR) + "/month_" + (cal.get(Calendar.MONTH) < 10? "0" + (cal.get(Calendar.MONTH)+1): cal.get(Calendar.MONTH)+1) + "/day_" + (cal.get(Calendar.DAY_OF_MONTH) < 10? "0" + cal.get(Calendar.DAY_OF_MONTH): cal.get(Calendar.DAY_OF_MONTH)) + "/master_scoreboard.json").get()
                    .thenApply(WSResponse::asJson);

            //JsonNode p = jsonPromise2.toCompletableFuture().get(1000, TimeUnit.DAYS);
            JsonNode p = jsonPromise2.toCompletableFuture().get();
            //JsonNode p = e.get();
            data = p.get("data");
            games = data.get("games");
            game =  games.get("game");
            JsonNode obj;
            JsonNode lineScore;
            JsonNode Innings;
            JsonNode Inn;
            if(game == null) { System.out.println("Dia " + cal.getTime() + " No tiene partidos"); ws.close(); return; }
            for(int x=0 ; x< game.size();x++ ){
                //Game current_game = new Game();
                obj = game.get(x);

                //Verificamos que el partido aun no exista
                //if(GameHandler.CheckExist(obj.get("id").asText())) continue;
                Game current_game =  GameHandler.CheckAndGet(obj.get("id").asText());
                current_game.setIdentifier(obj.get("id").asText());
                // Obterner y/o obtener Liga
                //current_game.setLeague(LeagueHandler.CheckAndInsert(obj.get("league").asText())) ;
                current_game.setLeague(LeagueHandler.CheckAndInsert("MLB")) ;

                String[] laux = obj.get("league").asText().split("");

                /// Obterner y/o obtener el Estadio el Venue
                current_game.setVenue(VenueHandler.CheckAndInsert(obj.get("venue_id").asLong(), obj.get("venue").asText())) ;

                /// Obterner y/o obtener el Equipo de casa
                current_game.setHomeTeam(TeamHandler.CheckAndInsert(obj.get("home_team_id").asLong(), obj.get("home_team_name").asText(), obj.get("home_code").asText(), obj.get("home_team_city").asText(),LeagueHandler.CheckAndInsert(laux[1] + obj.get("home_division").asText())));

                /// Obterner y/o obtener el Equipo visitante
                current_game.setAwayTeam(TeamHandler.CheckAndInsert(obj.get("away_team_id").asLong(), obj.get("away_team_name").asText(), obj.get("away_code").asText(), obj.get("away_team_city").asText(), LeagueHandler.CheckAndInsert(laux[0] + obj.get("away_division").asText())));

                current_game.setStatus(StatusHandler.CheckAndInsert(obj.get("status").get("status").asText())); ;



                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
                Date convertedCurrentDate = sdf.parse(obj.get("time_date").asText());




                Calendar cal2 = Calendar.getInstance(); // creates calendar



                cal2.setTime(convertedCurrentDate); // sets calendar time/date
                cal2.add(Calendar.HOUR_OF_DAY, (obj.get("ampm").asText().equals("PM")?12:0) ); // pone doce horas adicionales si es pm

                //esto cuadra las horas para ser exacto and stuff
                cal2.add(Calendar.HOUR_OF_DAY, Config.getInt("hour_diff")); // adds one hour
                cal2.add(Calendar.MINUTE, Config.getInt("minute_diff")); // adds one hour


                current_game.setDate(cal2.getTime());
                if(current_game.getStatus().getIdStatus() > 1  && (current_game.getStatus().getIdStatus() < 4)) {
                    lineScore = obj.get("linescore");
                    current_game.setHr(new LineScoreHandler(lineScore.get("hr")));
                    current_game.setE(new LineScoreHandler(lineScore.get("e")));
                    current_game.setSo(new LineScoreHandler(lineScore.get("so")));
                    current_game.setR(new LineScoreHandler(lineScore.get("r")));
                    current_game.setH(new LineScoreHandler(lineScore.get("h")));
                }

                if(GameHandler.CheckExist(obj.get("id").asText()))
                    current_game.update();
                else
                    current_game.save();

                ///ZOna de agregar partido a la magia de la league para referencias de ams adelante.get
                TeamHasLeagueHandler.CheckAndInsert(current_game.getHomeTeam().getIdTeam(), current_game.getLeague().getIdLeague());
                TeamHasLeagueHandler.CheckAndInsert(current_game.getAwayTeam().getIdTeam(), current_game.getLeague().getIdLeague());

                if(current_game.getStatus().getIdStatus() > 1 && (current_game.getStatus().getIdStatus() < 4)) {
                        Innings =  obj.get("linescore").get("inning");
                        for(int i=0 ; i < Innings.size(); i++) {
                            Inn = Innings.get(i);
                            current_game.addInning(InningHandler.CheckAndInsert(current_game, i+1, Inn.hasNonNull("home") ? Inn.get("home").asInt(): 0, Inn.hasNonNull("away") ? Inn.get("away").asInt(): 0));
                        }


                    EventScrapper evt = new EventScrapper();


                    //chequeamos los eventow de este partido en particular
                    //evt.Scrapper(current_game);
                }

            }
        }
        catch(Exception e)
        {
            //ws.close();
            e.printStackTrace();
            ws.close();
        }
        ws.close();
    }
}
