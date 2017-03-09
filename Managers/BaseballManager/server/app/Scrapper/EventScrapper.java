package Scrapper;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import models.domain.Event;
import models.domain.Game;
import models.handlers.*;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;
import play.mvc.Result;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

/**
 * Created by palenge on 1/3/17.
 */
public class EventScrapper {


    public EventScrapper() {
    }

    public void Scrapper(Game game) throws IOException {


        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        String name = "wsclientEvent";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);

        String jsonString = "{}";

        ObjectMapper mapper = new ObjectMapper();

        //ArrayNode action;
        //ArrayNode atbat;
        try
        {

            JsonNode data = null;
            JsonNode games = null;
            ArrayNode inning = null;
            ArrayNode gameArray = null;
            //ws.close();
            String gui = "gid_" + game.getIdentifier().replace('/','_').replace('-','_');
            String str[] = gui.split("_");
            System.out.println("URL: http://gd2.mlb.com/components/game/mlb/year_" + str[1] + "/month_" + str[2] + "/day_" + str[3] + "/"+ gui + "/game_events.json");
            CompletionStage<JsonNode> jsonPromise2 = ws.url("http://gd2.mlb.com/components/game/mlb/year_" + str[1] + "/month_" + str[2] + "/day_" + str[3] + "/"+ gui + "/game_events.json").get()
                    .thenApply(WSResponse::asJson);

            //JsonNode p = jsonPromise2.toCompletableFuture().get(1000, TimeUnit.DAYS);
            JsonNode p = jsonPromise2.toCompletableFuture().get();
            //JsonNode p = e.get();
            data = p.get("data");
            games = data.get("game");
            inning = (ArrayNode) games.get("inning");
            if(inning == null) {System.out.println("No hay eventos para " + gui); ws.close(); return; }
            JsonNode obj;
            JsonNode lineScore;
            ArrayNode Innings;
            JsonNode Inn;

            JsonNode bottom;
            JsonNode top;
            JsonNode action ;
            JsonNode atbat  ;





            int inning_number =0;

            for(int x=0 ; x< inning.size();x++ ){
                obj = inning.get(x);
                inning_number = obj.get("num").asInt();
                Event event = new Event();

                //cuadramos los datos del bottom
                bottom = obj.get("bottom");
                if(bottom.hasNonNull("atbat")) {
                    if (bottom.get("atbat").isArray()) {
                        atbat = bottom.get("atbat");
                        for (int i = 0; i < atbat.size(); i++) {
                            JsonNode evtac = atbat.get(i);
                            if (evtac == null) {
                                System.out.println("Error en " + atbat);
                                continue;
                            }
                            ActionHandler.ActionAdd(game, false, evtac, inning_number);
                            //gid_2016_09_10_balmlb_detmlb_1atbat.get("des_es").asText()
                            //public static void ActionAdd(Game game, boolean bottontop, long event_num, String event_es, String event_des_es, String event_guid, int inning_number)

                        }
                    } else {
                        JsonNode evtac = bottom.get("atbat");
                        ActionHandler.ActionAdd(game, false, evtac, inning_number);
                    }
                }

                if(bottom.hasNonNull("action")) {
                    if(bottom.get("action").isArray()) {
                        atbat =  bottom.get("action");
                        for (int i = 0; i < atbat.size(); i++) {
                            JsonNode evtac = atbat.get(i);
                            if(evtac == null) {   System.out.println("Error en "+ atbat); continue; }
                            ActionHandler.ActionAdd(game, false, evtac, inning_number);
                        }
                    }
                    else {
                        JsonNode evtac = bottom.get("action");
                        if(evtac == null) {   System.out.println("Error en "+ evtac); continue; }
                        ActionHandler.ActionAdd(game, false, evtac, inning_number);
                    }

                }

                //cuadramos lso datos del top
                bottom = obj.get("top");
                atbat =  bottom.get("atbat");
                if(bottom.hasNonNull("atbat")) {
                    if (bottom.get("atbat").isArray()) {
                        atbat = bottom.get("atbat");
                        for (int i = 0; i < atbat.size(); i++) {
                            JsonNode evtac = atbat.get(i);
                            if (evtac == null) {
                                System.out.println("Error en " + atbat);
                                continue;
                            }
                            ActionHandler.ActionAdd(game, true, evtac, inning_number);
                            //gid_2016_09_10_balmlb_detmlb_1atbat.get("des_es").asText()
                            //public static void ActionAdd(Game game, boolean bottontop, long event_num, String event_es, String event_des_es, String event_guid, int inning_number)
                        }
                    } else {
                        JsonNode evtac = bottom.get("atbat");
                        if (evtac == null) {
                            System.out.println("Error en " + evtac);
                            continue;
                        }
                        ActionHandler.ActionAdd(game, true, evtac, inning_number);
                    }
                }

                if(bottom.hasNonNull("action")) {
                    if(bottom.get("action").isArray()) {
                        atbat =  bottom.get("action");
                        for (int i = 0; i < atbat.size(); i++) {
                            JsonNode evtac = atbat.get(i);
                            if(evtac == null) {   System.out.println("Error en "+ atbat); continue; }
                            ActionHandler.ActionAdd(game, true, evtac, inning_number);
                        }
                    }
                    else {
                        JsonNode evtac = bottom.get("action");
                        if(evtac == null) {   System.out.println("Error en "+ evtac); continue; }
                        ActionHandler.ActionAdd(game, true, evtac, inning_number);
                    }

                }
            }

        }
        catch(NullPointerException  e)
        {
            //ws.close();
            //if(atbat != null && action != null)
            e.printStackTrace();
            System.out.println("Error en la gui" + game.getIdentifier() + " - "  + e.getStackTrace());
            ws.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error en la gui" + game.getIdentifier() + " - " + e.getStackTrace());
            ws.close();
        }
        ws.close();
    }
}

