package controllers;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import akka.stream.Client;
import com.avaje.ebeaninternal.server.lib.util.Str;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import modeles.Clients;
import modeles.Config;
import modeles.Services;
import modeles.log;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.w3c.dom.Document;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.*;
import utils.Response;
import utils.WSHandler;
import views.html.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.Date;

/**
 * Created by Ferck on 17/6/2017.
 */
public class WapSite extends Controller {

    public final String urlSilver = "http://silversolempresas.com";
    ////XPALX Parametros de silver api que son iguales para todo el mundo
    public static final String SILVER_API_USER = "nemhttp";
    public static final String SILVER_API_PASS = "dsad3ss";
    public static final String urlSub = "/suscripcion/servicios/api/Wssuscripcion.asmx/SuscripcionWap";

    ///La que llega desde los diferentes agregadores
    public Result index() throws IOException {
        String token  = "NA";
        String ttype = "none";
        Services ser =  new Services();
        ser = ser.getServiceByName("md");
        if(request().queryString().containsKey("IDTRX")) {
            ttype = "GLOBA";
            token  = request().getQueryString("IDTRX");
            Http.Cookie cookie = Http.Cookie.builder("ttype","IDTRX=" + request().getQueryString("IDTRX")).build();
            response().setCookie(cookie);
        }

        if(request().queryString().containsKey("idtrx")) {
            ttype = "GLOBA";
            token  = request().getQueryString("idtrx");
            Http.Cookie cookie = Http.Cookie.builder("ttype","idtrx=" + request().getQueryString("idtrx")).build();
            response().setCookie(cookie);
        }

        if(request().queryString().containsKey("click_id")) {
            ttype = "SPIRALIS";
            token  = request().getQueryString("click_id");
            Http.Cookie cookie = Http.Cookie.builder("ttype","click_id=" + request().getQueryString("click_id")).build();
            response().setCookie(cookie);
        }

        if(request().queryString().containsKey("HASH")) {
            ttype = "MOBUSI";
            token  = request().getQueryString("HASH");
            Http.Cookie cookie = Http.Cookie.builder("ttype","HASH=" + request().getQueryString("HASH")).build();
            response().setCookie(cookie);

            if(request().queryString().containsKey("pubid")) {

                cookie = Http.Cookie.builder("pubid","PUBID=" + request().getQueryString("pubid")).build();
                response().setCookie(cookie);
            }
        }

        if(request().queryString().containsKey("hash")) {
            ttype = "MOBUSI";
            token  = request().getQueryString("hash");
            Http.Cookie cookie = Http.Cookie.builder("ttype","hash=" + request().getQueryString("hash")).build();
            response().setCookie(cookie);
            if(request().queryString().containsKey("pubid")) {
                cookie = Http.Cookie.builder("pubid","PUBID=" + request().getQueryString("pubid")).build();
                response().setCookie(cookie);
            }
        }

        if(request().queryString().containsKey("mobrainid")) {
            ttype = "MOBRAIN";
            token  = request().getQueryString("mobrainid");// + "@@@" +  request().getQueryString("mobrapu");
            Http.Cookie cookie = Http.Cookie.builder("ttype","mobrainid=" + request().getQueryString("mobrainid")).build();
            response().setCookie(cookie);
        }

        if(request().queryString().containsKey("source")) {
            ttype  = request().getQueryString("source");
            token  = request().getQueryString("source");
        }

        if(request().queryString().containsKey("test")) {
            ttype = "test";
            token  = request().getQueryString("test");
            Http.Cookie cookie = Http.Cookie.builder("ttype","test=" + request().getQueryString("test")).build();
            response().setCookie(cookie);
        }


        String msisdn = request().cookie("User-Identity-Forward-msisdn") == null ? "" : request().cookie("User-Identity-Forward-msisdn").value();
        return ok(wepa.render(msisdn, token, ttype));
    }


    ///pantalla para salvar el usuaior introducido
    public Result getpin() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        String msisdn = (String.join("", aux.get("msisdn")).startsWith("507")?"":"507") + String.join("", aux.get("msisdn"));

        log tmp = new log();
//        tmp.setIdentifier(aux.get("ttype")[0]);
//        tmp.setExtra(String.format("TEST: %s - %s", aux.get("msisdn")[0] , String.join("", aux.get("msisdn"))));
//        tmp.setMsisdn("50700000000");
//        tmp.setLastUpdate(new Date());
//        tmp.save();

        //if(!aux.get("token")[0].isEmpty())
        //{

        if(checkMD(msisdn)) {
            return ok(wepaerror.render(aux.get("ttype")[0], request().cookies().get("ttype") == null? "": request().cookies().get("ttype").value()));
        }

        Clients client = new Clients();
        client = client.getClientByMSisdnAndConfirm(msisdn,aux.get("ttype")[0]);



        if(client == null)
        {
            Services ser =  new Services();
            ser = ser.getServiceByName("md");
            client = new Clients();
            client.setToken(aux.get("token")[0]);
            client.setConfirm(aux.get("ttype")[0]);
            client.setMsisdn(Long.parseLong(msisdn));
            client.setService(ser);
            client.setLastUpdate(new Date());
            client.save();
        }
        else
        {
            client.setToken(aux.get("token")[0]);
            client.setLastUpdate(new Date());
            client.update();
        }
        //}

        tmp = new log();
        tmp.setIdentifier(aux.get("ttype")[0]);
        tmp.setExtra(String.format("START: %s - %s", msisdn, request().cookies().get("pubid") == null? "N/A": request().cookies().get("pubid").value()));
        tmp.setMsisdn(String.format(client.getMsisdn().toString()));
        tmp.setLastUpdate(new Date());
        tmp.save();

        CallGetSilver(msisdn);
        return ok(wepaget.render(msisdn, aux.get("ttype")[0]));
    }


    public boolean checkMD(String msisdn) throws IOException
    {
        if(msisdn.equals("507"))
        {
            return false;
        }
        ObjectNode event = Json.newObject();
        event.put("msisdn", msisdn);
        String url = "http://plussports.hecticus.com/checkmsisdn";
        JsonNode p =  WSHandler.instance().MakePostJson(url, event);
        if(p.has("response")) {
            if (p.get("response").has("client"))
                return p.get("response").get("client").get("status").asInt() == 1;
        }
        return false;

    }

    ///Para llamar a Silver el pimer paso
    public void CallGetSilver(String msisdn) throws IOException {
        Services ser =  new Services();
        ser = ser.getServiceByName("md");

        ObjectNode event = Json.newObject();
        event.put("celular", msisdn);
        event.put("operadoraId", ser.getIdentifier());
        event.put("numeroCorto", ser.getShortCode());
        event.put("productoId", ser.getProductIdentifier());
        event.put("texto", ser.getSms());

        String url = Config.getString("silver-api-url") + "api/v1/user/generarPin";
        WSHandler.instance().MakePostJson(url, event);
    }

    public Result confirm() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        boolean validPin =  false;
        validPin = CheckPin(aux.get("msisdn")[0],aux.get("pin")[0]);
        if(!aux.get("msisdn")[0].isEmpty())
        {
            Clients client = new Clients();
            client = client.getClientByMSisdnAndConfirm(aux.get("msisdn")[0],aux.get("ttype")[0]);
            if(client != null)
            {
                String ttype = aux.get("ttype")[0];
                String pin = aux.get("pin")[0];
                if(validPin) {

                    if(ttype.equals("GLOBAL")) {
                        CallWithTokenGlobality(client.getToken());
                        toKraken(client.getMsisdn().toString(), "GLOBALWEB");

                    }
                    if(ttype.equals("SPIRALIS")) {
                        CallWithTokenSpiralis(client.getToken());
                        toKraken(client.getMsisdn().toString(), "SPIRALISWEB");

                    }
                    if(ttype.equals("MOBUSI")) {
                        CallWithTokenGeneric("mobusi-url", client.getToken());
                        toKraken(client.getMsisdn().toString(), "MOBUSIWEB");

                    }

                    if(ttype.equals("MOBRAIN")) {
                        CallWithTokenMobrain(client.getToken());
                        toKraken(client.getMsisdn().toString(), "MOBRAWEB");

                    }
                    if(ttype.equals("none")) {
                        toKraken(client.getMsisdn().toString(), "NONEWEB");
                    }
                    if(ttype.equals("test")) {
                        toKraken(client.getMsisdn().toString(), "NONEWEB");
                    }
                    if(ttype.startsWith("INS")) {
                        toKraken(client.getMsisdn().toString(), ttype);
                    }

                    log tmp = new log();
                    tmp.setIdentifier(ttype);
                    tmp.setExtra(String.format("EXITO: %s - %s", client.getMsisdn().toString(), request().cookies().get("pubid") == null? "N/A": request().cookies().get("pubid").value()));
                    tmp.setMsisdn(String.format(client.getMsisdn().toString()));
                    tmp.setLastUpdate(new Date());
                    tmp.save();
                }
            }
        }
        return ok(wepaconfirm.render(validPin, aux.get("ttype")[0]));
    }

    //Valida que el usuario contrasena introducido
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

        String url = Config.getString("silver-api-url") + "api/v1/user/confirmarPin";
        response = (WSHandler.instance().MakePostJson(url, event).get("response").get("code").asText() == "0");
        return response;
    }

    public void CallWithTokenGlobality(String token) throws IOException {
        String url = Config.getString("globality-url") + token;
        WSHandler.instance().MakeGet(url);
    }


    public void CallWithTokenMobrain(String token) throws IOException {
        String url = Config.getString("mobrain-url")  + token + "?token=" + Config.getString("mobrain-token");
        WSHandler.instance().MakeGet(url);
    }

    public void CallWithTokenGeneric(String routeget, String token) throws IOException {
        String url = Config.getString(routeget) + token;
        WSHandler.instance().MakeGet(url);
    }

    public void CallWithTokenSpiralis(String token) throws IOException {
        String url = Config.getString("spiralis-url") + token;
        WSHandler.instance().MakeGet(url);
    }

    public void toKraken(String msisdn) throws IOException {
        // http://02.kapp.hecticus.com/ws/receiveMO.php?source=50765070490&destination=9090&service_type=pacws&msg=GLOBALWEB&received_time=20151118170000
        String url = "http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn + "&destination=9090&service_type=pacws&msg=GLOBALWEB&received_time=20151118170000" ;
        WSHandler.instance().MakeGet(url);
    }

    public void toKraken(String msisdn, String msg) throws IOException {
        // http://02.kapp.hecticus.com/ws/receiveMO.php?source=50765070490&destination=9090&service_type=pacws&msg=GLOBALWEB&received_time=20151118170000
        WSHandler.instance().MakeGet("http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn + "&destination=9090&service_type=pacws&msg="+ msg +"&received_time=20151118170000" );
    }
    /**Creado por Erick Subero
     * Esto genera un nuevo UUID.
     *
     **/
    public String GenerateUUID(){
        UUID idClient = UUID.randomUUID();
        return idClient.toString();
    }

    /**Creado por Erick Subero
     * Esto registra en BD un nuevo cliente.
     **/
    public Clients InsertClient(String msisdn, String ttype, String token, Services service){

        Clients client =  new Clients();
        client = client.getClientByMSisdnAndConfirm(msisdn, ttype);
        if(client == null) {
            client =  new Clients();
            client.setMsisdn(Long.parseLong(msisdn));
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
        return client;
    }






    private static String getSuscriptionWap(String urlOrigen,Services ser, Long msisdn, String urlFinal, String urlLanding) throws UnsupportedEncodingException {
        String usuario = SILVER_API_USER;
        String password = SILVER_API_PASS;
        String operadoraId = ser.getIdentifier();
        String celular = Long.toString(msisdn);
        String numeroCorto = Integer.toString(ser.getShortCode());
        String productoId = ser.getProductIdentifier();
        String descripcionProducto = ser.getDescripcionProducto();
        String request_id = Long.toString(ser.getId());
        String queryParameters = "usuario=" + usuario + "&password=" + password + "&operadoraId=" + operadoraId + "&celular=" + celular + "&numeroCorto=" + numeroCorto + "&productoId=" + productoId + "&DescripcionProducto=" + URLEncoder.encode(descripcionProducto, "UTF-8") + "&request_id=" + request_id + "&urlFinal=" + URLEncoder.encode(urlFinal, "UTF-8") +"&urlLanding=" + URLEncoder.encode(urlLanding, "UTF-8");
        StringBuilder url = new StringBuilder();
        //url.append("http://").append("silversolempresas.com/suscripcion/servicios/api/WsSuscripcion.asmx/SuscripcionWap").append("?").append(queryParameters);
        url.append(urlOrigen).append("?").append(queryParameters);
        return url.toString();
    }

    public Result GetSuscription() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        if(!aux.get("urlAlta")[0].isEmpty())
        {

        }
        CallGetSilver(aux.get("msisdn")[0]);
        return ok(wepaget.render(aux.get("msisdn")[0], aux.get("ttype")[0]));
    }


    //Cuando la confirmacion es externa
    public Result confirmExternal() throws IOException {
        String id =  "";
        if(request().queryString().containsKey("request_id")) {
            id  = request().getQueryString("request_id");

            Clients clit  = Clients.getClientByIdentifier(id);
            if(clit !=null){
                CallWithTokenGlobality(clit.getToken());
            }
        }
        return ok(wepaconfirm.render(true,""));
    }


    ///Zona no borrar en caso de emergencia
      /*



    public String GetSubsWAP(Clients client) throws IOException {

        String urlFinal = request().host() + "/wap/confirm";
        String urlLanding = request().host() + "/assets/image.jpg";
        JsonNode response = Json.newObject();

        AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setMaxRequestRetry(0)
                .setShutdownQuietPeriod(0)
                .setShutdownTimeout(0).build();

        String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        ActorMaterializerSettings settings = ActorMaterializerSettings.create(system);
        ActorMaterializer materializer = ActorMaterializer.create(settings, system, name);

        WSClient ws = new AhcWSClient(config, materializer);

        String urlOrigen = urlSilver+urlSub;

        //CompletionStage<JsonNode> jsonPromise2 = ws.url(Config.getString("silver-api-url") + "api/v1/user/subscripcionWap").get()
        String aux = getSuscriptionWap(urlOrigen,client.getService(), client.getMsisdn(), urlFinal, urlLanding);
        CompletionStage<String> jsonPromise2 = ws.url(aux).get()
                .thenApply(WSResponse::getBody);
        String pepe = "";
        String temp = "";
        try {
            pepe = jsonPromise2.toCompletableFuture().get();
            //temp = pepe.getElementsByTagName("urlAlta").item(0).getFirstChild().getTextContent();
            temp = pepe.substring(pepe.indexOf("&lt;urlAlta&gt;") + 15, pepe.indexOf("&lt;/urlAlta&gt;"));
            //response = jsonPromise2.toCompletableFuture().get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            ws.close();
        }
        return temp;
    }
    */
}
