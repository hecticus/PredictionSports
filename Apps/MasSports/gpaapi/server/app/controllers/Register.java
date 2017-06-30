package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.fasterxml.jackson.databind.JsonNode;
//import com.google.inject.Inject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import modeles.Clients;
import modeles.Config;
import modeles.Services;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
//import scala.util.parsing.json.JSONArray;
//import scala.util.parsing.json.JSONObject;

import java.io.IOException;

import play.libs.ws.ahc.AhcWSClient;
import play.libs.ws.*;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionStage;

//import play.api.libs.ws.WS;
//import play.api.libs.ws.WSClient;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.Response;

/**
 * Created by palenge on 10/3/16.
 */
public class Register extends Controller {


    public Result CheckHeader() throws IOException {
        String msisdn = request().hasHeader("MSISDN")?request().getHeader("MSISDN"): "" ;
        String aux = "";
        Iterator<String> iter = request().headers().keySet().iterator();
        while (iter.hasNext()){
            String key = iter.next();
            String values = request().getHeader(key);
            String s = key + " = " + values + "\n";
            aux += s;
        }

        String aux2 = "";
        try {
            for (String cookieStr : request().headers().get("Cookie")) {
                String name = cookieStr.substring(0, cookieStr.indexOf("="));


                Http.Cookie cookie = request().cookie(name); // Get the instance of the cookie !
                aux2 += " __ " + name + " = " + cookie.value();
            }
        }catch(Exception e)
        {
        }
        return ok(Response.buildExtendResponse(aux + " - COOOKIES " +aux2));

    }

    public Result GetPin() throws IOException {
        JsonNode json = request().body().asJson();



        Services ser =  new Services();
        ser = ser.getServiceByName(json.get("product").asText());

        Clients cli = new Clients();
        cli = cli.getClientByMSisdnAndConfirm(json.get("msisdn").asText(), json.get("pixel").asText());

        boolean isnew = cli == null;
        if(cli == null) cli = new Clients();

        String msisdn = json.get("msisdn").asText();
        msisdn = (msisdn.startsWith("507")?"":"507") + msisdn;

        String idtok = "";
        if(json.has("idtrx"))
            idtok= json.get("idtrx").asText();
        if(json.has("IDTRX"))
            idtok= json.get("IDTRX").asText();
        cli.setToken(idtok);
        cli.setMsisdn(Long.parseLong(msisdn));
        cli.setService(ser);
        cli.setConfirm(json.get("pixel").asText());

        if(ser == null)
            return Response.accessDenied();
        //String nullws =  Config.getString("silver-api-url") + "api/v1/user/generarPin";
        ObjectNode event = Json.newObject();
        //event.put("usuario", Config.getString("silver-api-user"));
        //event.put("password", Config.getString("silver-api-pass"));
        event.put("celular", json.get("msisdn").asText());
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("texto", ser.getSms());

        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        if(isnew) cli.insert(); else cli.update();


        String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);
        String wsr =  Config.getString("silver-api-url") + "api/v1/user/generarPin";

        JsonNode p;
        try {
            CompletionStage<JsonNode> jsonPromise = ws.url(wsr).post(event)
                    .thenApply(response -> response.asJson());
            p = jsonPromise.toCompletableFuture().get();
        } catch (Exception e) {
            return Response.accessDenied();
        } finally {
            ws.close();
        }
        return ok(Response.buildExtendResponse("Valid"));
        //return ok(Response.buildExtendResponse("OK", p));
    }

    public Result confirmPin() throws IOException {
        //Sacando datos!!
        //{
        // "operadoraId": "1",
        // "celular" : "04129581109",
        // "numeroCorto": "9090",
        // "productoId": "COPA",
        // "pin": "4445"
        //}

        JsonNode json = request().body().asJson();

        Services ser =  new Services();
        ser = ser.getServiceByName(json.get("product").asText());

        Clients cli = new Clients();
        cli = cli.getClientByMSisdnAndConfirm(json.get("msisdn").asText(), json.get("pixel").asText());

        if(cli == null)
            return Response.accessDenied();
        if(ser == null)
            return Response.accessDenied();

        ObjectNode event = Json.newObject();
        event.put("celular", json.get("msisdn").asText());
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("Pin", ser.getDescripcionProducto());
        boolean response;

        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);
        CompletionStage<JsonNode> jsonPromise2 = ws.url(Config.getString("silver-api-url") + "api/v1/user/confirmarPin").post(event)
                .thenApply(WSResponse::asJson);
        JsonNode jsonr = Json.newObject();
        try {
            jsonr =jsonPromise2.toCompletableFuture().get();
            response = (jsonr.get("response").get("code").asText() == "0");
        } catch (Exception e) {
            return ok("Internal Error");
        } finally {
            ws.close();
        }
        return ok(Response.buildExtendResponse(response? "Valid":"Invalid"));
    }

    public Result Config()
    {
        Services ser =new Services("md", "1", "GANA @pin",9090, "COPA", "");
        ser.insert();

        Config con = new Config();
        con.setConfigKey("silver-api-url");
        con.setValue("http://silverapi.hecticus.com/");
        con.insert();


        con = new Config();
        con.setConfigKey("globality-url");
        con.setValue("http://ad.globadlity.com/wss2s.asmx/ConfirmS2S?trx_id=");
        con.insert();

        //con.insert();
        //String name, String identifier, String sms, int shortCode, int productIdentifier, String descripcionProducto)
        return ok();
    }


    public void ReturnPixels(Clients client)
    {
        if(client.getConfirm().equals("GLOBA"))
        {

        }

    }
    //public Result getMenu(){  return roleManager.GetMenu((User) ctx().args.get("CurrentUser"));  }
}
