package utils;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;


/**
 * Created by Ferck on 26/12/2017.
 */


public class WSHandler {

    final Logger.ALogger logger = Logger.of(this.getClass().getName());


    //private static WSClient ws;
    ActorSystem system;
    ActorMaterializerSettings settings;
    ActorMaterializer materializer ;


    private static WSHandler _instance;
    public static  WSHandler instance()
    {
        if(_instance ==  null)
            _instance =  new WSHandler();
    return _instance;
    }

    public WSClient getWS()
    {
        AsyncHttpClientConfig asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();
        //AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfig);

        return new AhcWSClient(asyncHttpClientConfig, materializer);
    }

    public WSHandler()
    {
        String name = "wsclient";
        system = ActorSystem.create(name);
        settings = ActorMaterializerSettings.create(system);
        materializer = ActorMaterializer.create(settings, system, name);
        logger.info("El sistema Creo nuevo WSCHandler");
    }

    public String MakeGet(String url) throws IOException {
        WSClient ws = getWS();
        CompletionStage<String> jsonPromise2 = ws.url(url).get()
                .thenApply(WSResponse::getBody);
        String jsonr = "";
        try {
            jsonr =jsonPromise2.toCompletableFuture().get();
        } catch (InterruptedException e) {
            logger.error("interruptedException llamado: " + e.getMessage());

            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.error("ExecutionException llamado: " + e.getMessage());

            e.printStackTrace();
        }finally {
            ws.close();
        }
        return jsonr;
    }

    public JsonNode MakeGetJson(String url) throws IOException {
        WSClient ws = getWS();
        CompletionStage<JsonNode> jsonPromise2 = ws.url(url).get()
                .thenApply(WSResponse::asJson);
        JsonNode jsonr = Json.newObject();
        try {
            jsonr =jsonPromise2.toCompletableFuture().get();
        } catch (Exception e) {
        } finally {
            ws.close();
        }
        return jsonr;
    }

    public JsonNode MakePostJson(String url, ObjectNode objectNode) throws IOException {
        WSClient ws = getWS();
        CompletionStage<JsonNode> jsonPromise2 = ws.url(url).post(objectNode)
                .thenApply(WSResponse::asJson);
        JsonNode jsonr = Json.newObject();
        try {
            jsonr =jsonPromise2.toCompletableFuture().get();
        } catch (InterruptedException e) {
            logger.error("interruptedException llamado: " + e.getMessage());

            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.error("ExecutionException llamado: " + e.getMessage());

            e.printStackTrace();
        }finally {
            ws.close();
        }
        return jsonr;
    }
}
