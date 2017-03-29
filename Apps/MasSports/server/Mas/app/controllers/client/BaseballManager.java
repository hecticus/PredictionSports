package controllers.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.FootballController;
import job.TeamsSynchronizer;
import models.Config;
import models.clients.Client;
import models.clients.FootballClient;
import models.pushalerts.ClientHasPushAlerts;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSRequestHolder;
import play.mvc.Result;
import utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseballManager extends FootballController {


    public static ObjectNode callBaseballManager(String path){
        Set<Map.Entry<String, String[]>> entries = request().queryString().entrySet();
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String[]> entry : entries) {
            for (String s : entry.getValue()) {
                queryString.append(entry.getKey()).append("=").append(s).append("&");
            }
        }
        StringBuilder url = new StringBuilder();
        try {
            url.append("http://").append(Utils.getBaseBallManagerHost()).append(request().path()).append((queryString.toString().equals(""))?"":"?").append(URLEncoder.encode(queryString.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ObjectNode response = Json.newObject();
        try {
            F.Promise<WSResponse> result = WS.url(url.toString()).get();
            response = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
            url.delete(0, url.length());
            queryString.delete(0, url.length());
        }catch (Exception e)
        {
            Utils.printToLog(FootballClients.class, "Error manejando ", "error actualizando el clientel request fue", false, e, "support-level-1", Config.LOGGER_ERROR);

        }

        return response;
    }

    public static Result getActiveCompetitions(Boolean ids, Boolean closestMatch, String timezoneName){
        return ok(callBaseballManager(request().path()));
    }

    public static Result testsync() throws UnsupportedEncodingException {
        job.TeamsSynchronizer aux = new TeamsSynchronizer();
        aux.synchTeamsBaseball(0);
        return ok("");
    }

    public static Result getTeams(Long idCompetition){
        return ok(callBaseballManager(request().path()));
    }

    public static Result getFixturesDatePaged(Integer idApp, Integer idLanguage, String date, Integer pageSize,Integer page, String timezoneName){
        return ok(callBaseballManager(request().path()));
    }
}
