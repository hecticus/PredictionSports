package controllers.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.FootballController;
import models.Config;
import models.clients.Client;
import models.clients.FootballClient;
import models.pushalerts.ClientHasPushAlerts;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by palenge on 6/29/16.
 */
public class SilverAPI {


        public static void GetPin(String msisdn )
        {
            String ws =  Config.getString("silver-api-url") + "api/v1/user/generarPin";
            ObjectNode event = Json.newObject();
            //event.put("usuario", Config.getString("silver-api-user"));
            //event.put("password", Config.getString("silver-api-pass"));
            event.put("celular", msisdn);
            event.put("operadoraId",  Config.getString("silver-api-operadora"));
            event.put("numeroCorto", Config.getString("silver-api-shortcode"));
            event.put("productoId", Config.getString("silver-api-id"));
            event.put("texto", Config.getString("silver-api-text"));

            F.Promise<WSResponse> result = WS.url(ws).post(event);
            //String json = result.get(10000).getBody();

            ObjectNode jsonResponse = (ObjectNode)result.get(10000, TimeUnit.MILLISECONDS).asJson();
            //TODO sistema de respuesta de mensajes.
            //sonResponse.get("response").get("code") == "0"
        }

        public static boolean confirmPin(String msisdn, String pin)
        {
            //Sacando datos!!
            //{
            // "operadoraId": "1",
            // "celular" : "04129581109",
            // "numeroCorto": "9090",
            // "productoId": "COPA",
            // "pin": "4445"
            //}
            String ws =  Config.getString("silver-api-url") + "api/v1/user/confirmarPin";
            ObjectNode event = Json.newObject();
            event.put("celular", msisdn);
            event.put("operadoraId",  Config.getString("silver-api-operadora"));
            event.put("numeroCorto", Config.getString("silver-api-shortcode"));
            event.put("productoId", Config.getString("silver-api-id"));
            event.put("pin", pin);
            boolean response;
            try {
                F.Promise<WSResponse> result = WS.url(ws).post(event);
                //String json = result.get(10000).getBody();
                ObjectNode jsonResponse = (ObjectNode)result.get(10000, TimeUnit.MILLISECONDS).asJson();
                response = (jsonResponse.get("response").get("code").asText() == "0");
            } catch (Exception ex){
                Utils.printToLog(SilverAPI.class, "Error en confirmPin", "error manejando llamada al servicio confirmarPin de Silver API", true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = false;
            }

            return response;
        }

        public static boolean broadcastEvent(String msisdn, String pin){
            String ws = Config.getString("kraken-play-url") + "KrakenClients/v1/services/create/plussports/" +msisdn;
            ObjectNode event = Json.newObject();
            event.put("id_country", Config.getString("silver-api-kraken-country"));
            event.put("id_business", Config.getString("silver-api-kraken-business"));
            event.put("id_carrier", Config.getString("silver-api-kraken-carrier"));
            event.put("password", pin);
            boolean response;
            try {
                F.Promise<WSResponse> result = WS.url(ws).post(event);
                ObjectNode jsonResponse = (ObjectNode) result.get(100000, TimeUnit.MILLISECONDS).asJson();
                response = (jsonResponse.get("response").get("code").asText() == "0");
            } catch (Exception ex){
                Utils.printToLog(SilverAPI.class, "Error en broadcastEvent", "error manejando llamada al servicio plussports de Kraken Play", true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = false;
            }

            return response;
        }
}
