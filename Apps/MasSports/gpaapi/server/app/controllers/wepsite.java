package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.avaje.ebeaninternal.server.lib.util.Str;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import modeles.Clients;
import modeles.Config;
import modeles.Services;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.*;
import utils.Response;
import views.html.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * Created by Ferck on 17/6/2017.
 */
public class wepsite extends Controller {

    public final String urlSilver = "http://silversolempresas.com";

    public Result index()  {
        String token  = "";
        String ttype = "";
        Services ser =  new Services();
        ser = ser.getServiceByName("md");
        if(request().queryString().containsKey("token")) {
            ttype = "GLOBA";
            token  = request().getQueryString("token");
        }
        String msisdn = request().hasHeader("MSISDN")?request().getHeader("MSISDN"): "" ;
        if (!msisdn.isEmpty()){
            InsertClient(msisdn,ttype, token, ser);
            String url = urlSilver+ "/suscripcion/servicios/api/Wssuscripcion.asmx/SuscripcionWap";
            return redirect(getSuscriptionWap(url,));
        }
        //Obtener Token
        return ok(wepa.render(msisdn, token, ttype));
    }

    public Result getpin() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        if(!aux.get("token")[0].isEmpty())
        {
            Clients client = new Clients();
            client = client.getClientByMSisdnAndConfirm(aux.get("msisdn")[0],aux.get("ttype")[0]);
            if(client == null)
            {
                client = new Clients();
                client.setToken(aux.get("token")[0]);
                client.setConfirm(aux.get("ttype")[0]);
                client.setMsisdn(Integer.parseInt(aux.get("msisdn")[0]));
                client.save();
            }
            else
            {
                client.setToken(aux.get("token")[0]);
                client.update();
            }
        }
        CallGetSilver(aux.get("msisdn")[0]);
        return ok(wepaget.render(aux.get("msisdn")[0], aux.get("ttype")[0]));
    }

    public void CallGetSilver(String msisdn) throws IOException {
        Services ser =  new Services();
        ser = ser.getServiceByName("md");

        ObjectNode event = Json.newObject();
        event.put("usuario", Config.getString("silver-api-user"));
        event.put("password", Config.getString("silver-api-pass"));
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("texto", ser.getSms());

        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

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
        } finally {
            ws.close();
        }
    }

    public Result confirm() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        boolean p = false;
        p = CheckPin(aux.get("msisdn")[0],aux.get("pin")[0]);
        if(!aux.get("msisdn")[0].isEmpty())
        {
            Clients client = new Clients();
            client = client.getClientByMSisdnAndConfirm(aux.get("msisdn")[0],aux.get("ttype")[0]);
            if(client != null)
            {
                String pin = aux.get("pin")[0];
                if(p)
                    CallWithTokenGlobality(client.getToken());
            }
        }
        return ok(wepaconfirm.render(p));
    }

    public boolean CheckPin(String msisdn, String pin) throws IOException {
        Services ser =  new Services();
        ser = ser.getServiceByName("md");

        ObjectNode event = Json.newObject();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("pin",  pin);
        boolean response = false;

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
        try {
            response = (jsonPromise2.toCompletableFuture().get().get("response").get("code").asText() == "0");
        } catch (Exception e) {
        } finally {
            ws.close();
        }
        return response;
    }

    public void CallWithTokenGlobality(String token) throws IOException {
        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);
        try {
            ws.url(Config.getString("glocality-url") + token).get().toCompletableFuture().get();
        } catch (Exception e) {
        } finally {
            ws.close();
        }
    }

    /*Creado por Erick Subero
     * Esto genera un nuevo UUID.
     */
    public String GenerateUUID(){
        UUID idClient = UUID.randomUUID();
        return idClient.toString();
    }

    /*Creado por Erick Subero
     * Esto registra en BD un nuevo cliente.
     */
    public void InsertClient(String msisdn, String ttype, String token, Services service){
        Clients client =  new Clients();

        client.setMsisdn(Integer.parseInt(msisdn));
        client.setUuid(GenerateUUID());
        client.setConfirm(ttype);
        client.setToken(token);
        client.setService(service);
        try {
            client.insert();
        } catch (Exception e) {
        } finally {
        }
    }

    private static String getSuscriptionWap(String urlOrigen,String usuario, String password, String operadoraId, String celular, String numeroCorto, String productoId, String descripcionProducto, String request_id, String urlFinal, String urlLanding) {
        String queryParameters = "usuario=" + usuario + "&password=" + password + "&operadoraId=" + operadoraId + "&celular=" + celular + "&numeroCorto=" + numeroCorto + "&productoId=" + productoId + "&descripcionProducto=" + descripcionProducto + "&request_id=" + request_id + "&urlFinal=" + urlFinal+"&urlLanding=" + urlLanding;
        StringBuilder url = new StringBuilder();
        //url.append("http://").append("silversolempresas.com/suscripcion/servicios/api/WsSuscripcion.asmx/SuscripcionWap").append("?").append(queryParameters);
        url.append(urlOrigen).append("?").append(queryParameters);
        return url.toString();
    }
}
