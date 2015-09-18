package controllers.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import controllers.FootballController;
import controllers.HecticusController;
import controllers.Secured;
import exceptions.UpstreamAuthenticationFailureException;
import exceptions.UpstreamException;
import models.Config;
import models.basic.Country;
import models.basic.Language;
import models.clients.Client;
import models.clients.ClientHasDevices;
import models.clients.Device;
import models.clients.FootballClient;
import models.leaderboard.ClientBets;
import models.leaderboard.Leaderboard;
import models.leaderboard.LeaderboardGlobal;
import models.leaderboard.LeaderboardTotal;
import models.pushalerts.ClientHasPushAlerts;
import models.pushalerts.PushAlerts;
import org.apache.commons.codec.binary.Base64;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import utils.DateAndTime;
import utils.Utils;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by plesse on 9/30/14.
 */
@Security.Authenticated(Secured.class)
public class FootballClients extends Clients {

    public static Result create() {
        ObjectNode clientData = getJson();
        try {
            FootballClient client = null;
            String login = null;
            if(clientData.has("login")){
                login = clientData.get("login").asText();

            }
            if(login != null) {
                client = (FootballClient) Client.getAndUpdate(login, clientData);
                if (client != null) {
                    return ok(buildBasicResponse(0, "OK", client.toJson()));
                }
            }
            Client baseClient = Client.create("football", clientData);
            client = (FootballClient) Client.getByID(baseClient.getIdClient());
            ArrayList<ClientHasPushAlerts> pushAlerts = new ArrayList<>();
            if (clientData.has("push_alerts")) {
                Iterator<JsonNode> pushAlertIterator = clientData.get("pushAlerts").elements();
                while (pushAlertIterator.hasNext()) {
                    JsonNode next = pushAlertIterator.next();
                    PushAlerts pushAlert = PushAlerts.finder.byId(next.asInt());
                    if (pushAlert != null) {
                        ClientHasPushAlerts chpa = new ClientHasPushAlerts(client, pushAlert);
                        pushAlerts.add(chpa);
                    }
                }
            }
            int newsPushId = Config.getInt("news-push-id");
            int betsPushId = Config.getInt("bets-push-id");
            PushAlerts newsPushAlert = PushAlerts.finder.byId(newsPushId);
            if (newsPushAlert != null) {
                ClientHasPushAlerts chpa = new ClientHasPushAlerts(client, newsPushAlert);
                pushAlerts.add(chpa);
            }
            PushAlerts betsPushAlert = PushAlerts.finder.byId(betsPushId);
            if (betsPushAlert != null) {
                ClientHasPushAlerts chpa = new ClientHasPushAlerts(client, betsPushAlert);
                pushAlerts.add(chpa);
            }
            if (!pushAlerts.isEmpty()) {
                client.setPushAlerts(pushAlerts);
            }
            if(client.getStatus() != 2) {
                int firstLoginPoints = Config.getInt("first-login-points");
                LeaderboardTotal firstLoginLeaderboard = new LeaderboardTotal(client, firstLoginPoints, 0);
                client.setLeaderboardTotal(firstLoginLeaderboard);
                try {
                    ObjectNode event = Json.newObject();
                    ObjectNode metadata = Json.newObject();
                    ArrayList<ObjectNode> pointsList = new ArrayList<>(1);
                    ObjectNode actualPoints = Json.newObject();
                    actualPoints.put("type", "experience");
                    actualPoints.put("value", firstLoginPoints);
                    pointsList.add(actualPoints);
                    metadata.put("result", "win");
                    metadata.put("points", Json.toJson(pointsList));
                    event.put("event_type", "APP_LAUNCH");
                    event.put("metadata", metadata);
                    F.Promise<WSResponse> result = WS.url("http://" + Config.getHost() + "/sportsapi/v2/client/" + client.getIdClient() + "/upstream").post(event);
                    ObjectNode response = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                } catch (Exception e) {
                    Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando el client con params " + clientData, false, e, "support-level-1", Config.LOGGER_ERROR);
                }
            }
            client.update();
            return created(buildBasicResponse(0, "OK", client.toJson()));
        } catch (Exception ex) {
            ObjectNode response;
            if(ex instanceof UpstreamException){
                UpstreamException upstreamException = (UpstreamException) ex;
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando el client con params " + clientData + " el request fue " + upstreamException.getRequest(), true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildUpstreamResponse(-2, "ocurrio un error creando el registro", upstreamException);
            } else {
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando el client con params " + clientData, true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildBasicResponse(-1, "ocurrio un error creando el registro", ex);
            }
            return internalServerError(response);
        }

    }

    public static Result update(Integer id) {
        ObjectNode clientData = getJson();
        try{
            FootballClient client = (FootballClient) Client.update(id, clientData);
            if(client != null) {
                boolean update = false;
                if(clientData.has("remove_push_alert")){
                    Iterator<JsonNode> alertsIterator = clientData.get("remove_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).findUnique();
                        if(pushAlert == null){
                            continue;
                        }
                        List<ClientHasPushAlerts> clientHasPushAlerts = ClientHasPushAlerts.finder.where().eq("client.idClient", client.getIdClient()).eq("pushAlert.idPushAlert", pushAlert.getIdPushAlert()).findList();
                        if(clientHasPushAlerts != null && !clientHasPushAlerts.isEmpty()){
                            for(ClientHasPushAlerts clientHasPushAlert : clientHasPushAlerts) {
                                client.getPushAlerts().remove(clientHasPushAlert);
                                clientHasPushAlert.delete();
                            }
                            update = true;
                        }
                    }
                }
                if(clientData.has("add_push_alert")) {
                    Iterator<JsonNode> alertsIterator = clientData.get("add_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        int index = client.getPushAlertIndex(next.asInt());
                        if (index == -1) {
                            PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).findUnique();
                            if (pushAlert != null) {
                                ClientHasPushAlerts chpa = new ClientHasPushAlerts(client, pushAlert);
                                client.getPushAlerts().add(chpa);
                                update = true;
                            }
                        }
                    }
                }
                int betsPushId = Config.getInt("bets-push-id");
                int newsPushId = Config.getInt("news-push-id");
                if(clientData.has("receive_news")) {
                    boolean receiveNews = clientData.get("receive_news").asBoolean();
                    int index = client.getPushAlertIDIndex(newsPushId);
                    if(index > -1) {
                        client.getPushAlerts().get(index).setStatus(receiveNews);
                        update = true;
                    }
                }
                if(clientData.has("receive_bets")) {
                    boolean receiveBets = clientData.get("receive_bets").asBoolean();
                    int index = client.getPushAlertIDIndex(betsPushId);
                    if(index > -1) {
                        client.getPushAlerts().get(index).setStatus(receiveBets);
                        update = true;
                    }
                }
                if(clientData.has("receive_min")) {
                    boolean receiveMin = clientData.get("receive_min").asBoolean();
                    for(ClientHasPushAlerts clientHasPushAlerts : client.getPushAlerts()){
                        if(clientHasPushAlerts.getPushAlert().getIdPushAlert() != betsPushId && clientHasPushAlerts.getPushAlert().getIdPushAlert() != newsPushId) {
                            clientHasPushAlerts.setStatus(receiveMin);
                            update = true;
                        }
                    }
                }
                if(update){
                    client.update();
                }
                return ok(buildBasicResponse(0, "OK", client.toJson()));
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception ex) {
            ObjectNode response;
            if(ex instanceof UpstreamException){
                UpstreamException upstreamException = (UpstreamException) ex;
                Utils.printToLog(FootballClients.class,  "Error manejando clients", "error actualizando el client " + id + " el request fue " + upstreamException.getRequest(), true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildUpstreamResponse(-2, "ocurrio un error creando el registro", upstreamException);
            } else {
                Utils.printToLog(FootballClients.class,  "Error manejando clients", "error actualizando el client " + id, true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildBasicResponse(-1, "ocurrio un error creando el registro", ex);
            }
            return internalServerError(response);
        }
    }

    public static Result get(Integer id, String upstreamChannel, Boolean pmc){
        try {
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                if(!pmc) {
                    client.checkStatus(upstreamChannel);
                }
                return ok(buildBasicResponse(0, "OK", pmc?client.toPMCJson():client.toJson()));
            } else {
                return notFound(buildBasicResponse(2, "no existe el registro a consultar"));
            }
        }catch (Exception ex) {
            ObjectNode response;
            if(ex instanceof UpstreamException){
                UpstreamException upstreamException = (UpstreamException) ex;
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo el client " + id + " el request fue " + upstreamException.getRequest(), true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildUpstreamResponse(-2, "ocurrio un error creando el registro", upstreamException);
            } else {
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo el client " + id, true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildBasicResponse(-1, "ocurrio un error creando el registro", ex);
            }
            return internalServerError(response);
        }
    }

    public static Result getPushAlertsForClient(Integer id) {
        try {
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                if(client.getStatus() >= 0) {
                    List<ClientHasPushAlerts> pushAlerts = client.getPushAlerts();
                    ArrayList jsonAlerts = new ArrayList();
                    for(int i=0; i<pushAlerts.size(); i++){
                        jsonAlerts.add(pushAlerts.get(i).toJson());
                    }
                    return ok(buildBasicResponse(0, "OK", Json.toJson(jsonAlerts)));
                }else{
                    return badRequest(buildBasicResponse(3, "cliente " + id + " no se encuentra en status valido"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo la lista de alertas para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static ObjectNode buildUpstreamResponse(int code, String responseMsg, UpstreamException e) {
        ObjectNode responseNode = Json.newObject();
        responseNode.put(Config.ERROR_KEY, code);
        responseNode.put(Config.DESCRIPTION_KEY, responseMsg);
        responseNode.put(Config.EXCEPTION_KEY, e.getMessage());
        responseNode.put(Config.UPSTREAM_CODE, e.getCode());
        return responseNode;
    }

    //ClientBetsWS

    public static Result createBets(Integer id) {
        ObjectNode betsData = getJson();
        try {
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                Iterator<JsonNode> bets = betsData.get("bets").elements();
                Map<Integer, ObjectNode> betsMap = new HashMap<>();
                StringBuilder matchesRequest = new StringBuilder();
                matchesRequest.append("http://").append(Utils.getFootballManagerHost()).append("/footballapi/v1/matches/get/ids/").append(Config.getInt("football-manager-id-app")).append("?");
                int idTournament = -1, idPhase = -1, idGameMatch = -1, clientBet = -1;
                ClientBets clientBets = null;
                while(bets.hasNext()){
                    JsonNode bet = bets.next();
                    idTournament = bet.get("id_tournament").asInt();
                    idGameMatch = bet.get("id_game_match").asInt();
                    clientBet = bet.get("client_bet").asInt();
                    ObjectNode betElement = Json.newObject();
                    betElement.put("id_tournament", idTournament);
                    betElement.put("id_game_match", idGameMatch);
                    betElement.put("client_bet", clientBet);
                    betsMap.put(idGameMatch, betElement);
                    matchesRequest.append("match[]=" + idGameMatch + "&");
                }

                F.Promise<WSResponse> result = WS.url(matchesRequest.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();

                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    Map<Integer, ClientBets> clientBetsAsMap = client.getClientBetsAsMap();
                    JsonNode data = footballResponse.get("response");
                    Iterator<JsonNode> matches = data.get("matches").elements();
                    while (matches.hasNext()) {
                        JsonNode match = matches.next();
                        idGameMatch = match.get("id_game_matches").asInt();
                        if (betsMap.containsKey(idGameMatch)) {
                            ObjectNode betElement = betsMap.get(idGameMatch);
                            idTournament = betElement.get("id_tournament").asInt();
                            idPhase = match.get("phase").asInt();
                            idGameMatch = betElement.get("id_game_match").asInt();
                            clientBet = betElement.get("client_bet").asInt();
                            String dateText = match.get("date").asText();
                            Date date = DateAndTime.getDate(dateText, dateText.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss");
                            Calendar gameDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                            gameDate.setTime(date);
                            gameDate.add(Calendar.HOUR, -1);
                            Date today = new Date(System.currentTimeMillis());
                            if (gameDate.getTime().after(today)) {
                                if (clientBetsAsMap.containsKey(idGameMatch)) {
                                    clientBets = clientBetsAsMap.get(idGameMatch);
                                    clientBets.setClientBet(clientBet);
                                    client.addClientBet(clientBets);
                                } else {
                                    clientBets = new ClientBets(client, idTournament, idPhase, idGameMatch, clientBet, dateText);
                                    client.addClientBet(clientBets);
                                }
                            }
                        }
                    }
                    client.update();
                    return ok(buildBasicResponse(0, "done"));
                } else{
                    return internalServerError(buildBasicResponse(1, "error vaidando partidos"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result createBet(Integer id) {
        ObjectNode betData = getJson();
        try {
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                ObjectNode bet = (ObjectNode) betData.get("bet");
                int idTournament = -1, idPhase = -1, idGameMatch = -1, clientBet = -1;
                ClientBets clientBets = null;
                idGameMatch = bet.get("id_game_match").asInt();
                StringBuilder matchesRequest = new StringBuilder();
                matchesRequest.append("http://").append(Utils.getFootballManagerHost()).append("/footballapi/v2/").append(Config.getInt("football-manager-id-app")).append("/match/").append(idGameMatch);

                F.Promise<WSResponse> result = WS.url(matchesRequest.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();

                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    ObjectNode match = (ObjectNode) footballResponse.get("response");
                    idGameMatch = match.get("id_game_matches").asInt();

                    idTournament = bet.get("id_tournament").asInt();
                    idPhase = match.get("phase").asInt();
                    clientBet = bet.get("client_bet").asInt();

                    String dateText = match.get("date").asText();
                    Date date = DateAndTime.getDate(dateText, dateText.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss");
                    Date today = new Date(System.currentTimeMillis());
                    if (date.after(today)) {
                        clientBets = client.getBet(idTournament, idPhase, idGameMatch);
                        if (clientBets != null) {
                            clientBets.setClientBet(clientBet);
                        } else {
                            clientBets = new ClientBets(client, idTournament, idPhase, idGameMatch, clientBet, dateText);
                        }
                        client.addClientBet(clientBets);
                        client.update();
                        return ok(buildBasicResponse(0, "ok", clientBets.toJsonNoClient()));
                    } else {
                        return badRequest(buildBasicResponse(1, "La apuesta no puede ser creada por ser de un partido pasado"));
                    }
                } else {
                    return (error > 0)?notFound(footballResponse):internalServerError(footballResponse);
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente" + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static Result getBets(Integer id) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if(timezoneNames == null || timezoneNames.length <= 0){
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/date/grouped/" + Config.getInt("football-manager-id-app") + "?timezoneName=" + timezoneName;
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    JsonNode data = footballResponse.get("response");
                    ArrayList<ObjectNode> finalData = new ArrayList<>();
                    ObjectNode responseData = Json.newObject();
                    ArrayList<Integer> matchesIDs = new ArrayList<>();
                    ArrayList<ObjectNode> modifiedFixtures = new ArrayList<>();
                    Map<Integer, ObjectNode> matches = new HashMap<>();
                    Iterator<JsonNode> leagues = data.get("leagues").elements();
                    while (leagues.hasNext()) {
                        int maxBetsCount = 0;
                        int clientBetsCount = 0;
                        ObjectNode league = (ObjectNode) leagues.next();
                        Iterator<JsonNode> fixtures = league.get("fixtures").elements();
                        while (fixtures.hasNext()) {
                            ObjectNode fixture = (ObjectNode) fixtures.next();
                            Iterator<JsonNode> externalMatches = fixture.get("matches").elements();
                            while (externalMatches.hasNext()){
                                ObjectNode externalMatch = (ObjectNode) externalMatches.next();
                                int idGameMatches = externalMatch.get("id_game_matches").asInt();
                                matchesIDs.add(idGameMatches);
                                matches.put(idGameMatches, externalMatch);
                            }
                        }
                        maxBetsCount = matchesIDs.size();
                        List<ClientBets> list = ClientBets.finder.where().eq("client", client).eq("idTournament", league.get("id_competitions").asInt()).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                        if (list != null && !list.isEmpty()) {
                            clientBetsCount+=list.size();
                            ArrayList<ObjectNode> dataFixture = new ArrayList();
                            ArrayList<ObjectNode> orderedFixtures = new ArrayList<>();
                            for (ClientBets clientBets : list) {
                                ObjectNode fixture = matches.get(clientBets.getIdGameMatch());
                                fixture.put("bet", clientBets.toJsonNoClient());
                                modifiedFixtures.add(fixture);
                                matches.remove(clientBets.getIdGameMatch());
                            }
                            Set<Integer> keys = matches.keySet();
                            for (int key : keys) {
                                ObjectNode fixture = matches.get(key);
                                modifiedFixtures.add(fixture);
                            }
                            Collections.sort(modifiedFixtures, new FixturesComparator());

                            String pivot = modifiedFixtures.get(0).get("date").asText().substring(0, 8);
                            for (ObjectNode gameMatch : modifiedFixtures){
                                if(gameMatch.get("date").asText().startsWith(pivot)){
                                    orderedFixtures.add(gameMatch);
                                } else {
                                    ObjectNode round = Json.newObject();
                                    round.put("date", pivot);
                                    round.put("matches", Json.toJson(orderedFixtures));
                                    dataFixture.add(round);
                                    orderedFixtures.clear();
                                    orderedFixtures.add(gameMatch);
                                    pivot = gameMatch.get("date").asText().substring(0, 8);
                                }
                            }
                            if(!orderedFixtures.isEmpty()){
                                ObjectNode round = Json.newObject();
                                round.put("date", pivot);
                                round.put("matches", Json.toJson(orderedFixtures));
                                dataFixture.add(round);
                                orderedFixtures.clear();
                            }

                            league.remove("fixtures");
                            league.put("fixtures", Json.toJson(dataFixture));
                            orderedFixtures.clear();
                            dataFixture.clear();
                        }
                        league.put("total_bets", maxBetsCount);
                        league.put("client_bets", clientBetsCount);
                        finalData.add(league);
                        modifiedFixtures.clear();
                        matchesIDs.clear();
                        matches.clear();
                    }
                    responseData.put("leagues", Json.toJson(finalData));
                    return ok(buildBasicResponse(0, "OK", responseData));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static Result getBetsForDate(Integer id, String date) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if(timezoneNames.length <= 0){
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/all/date/get/" + Config.getInt("football-manager-id-app") + "/" + date + "?timezoneName=" + timezoneName;
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    ObjectNode response = Json.newObject();
                    JsonNode data = footballResponse.get("response");
                    ArrayList<ObjectNode> finalData = new ArrayList<>();
                    ObjectNode responseData = Json.newObject();
                    ArrayList<Integer> matchesIDs = new ArrayList<>();
                    ArrayList<ObjectNode> modifiedFixtures = new ArrayList<>();
                    Map<Integer, ObjectNode> matches = new HashMap<>();
                    int maxBetsCount = 0;
                    int clientBetsCount = 0;
                    Iterator<JsonNode> fixtures = data.get("fixtures").elements();
                    while (fixtures.hasNext()) {
                        ObjectNode fixture = (ObjectNode) fixtures.next();
                        int idGameMatches = fixture.get("id_game_matches").asInt();
                        matchesIDs.add(idGameMatches);
                        matches.put(idGameMatches, fixture);
                    }
                    maxBetsCount = matchesIDs.size();
                    List<ClientBets> list = ClientBets.finder.where().eq("client", client).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                    if (list != null && !list.isEmpty()) {
                        clientBetsCount = list.size();
                        for (ClientBets clientBets : list) {
                            ObjectNode fixture = matches.get(clientBets.getIdGameMatch());
                            fixture.put("bet", clientBets.toJsonNoClient());
                            modifiedFixtures.add(fixture);
                            matches.remove(clientBets.getIdGameMatch());
                        }
                        Set<Integer> keys = matches.keySet();
                        for (int key : keys) {
                            ObjectNode fixture = matches.get(key);
                            modifiedFixtures.add(fixture);
                        }
                        Collections.sort(modifiedFixtures, new FixturesComparator());
                        response.put("fixtures", Json.toJson(modifiedFixtures));
                        response.put("total_bets", maxBetsCount);
                        response.put("client_bets", clientBetsCount);
                    } else {
                        response.put("fixtures", data.get("fixtures"));
                        response.put("total_bets", maxBetsCount);
                        response.put("client_bets", clientBetsCount);
                    }

                    modifiedFixtures.clear();
                    matchesIDs.clear();
                    matches.clear();
                    int points = 0;
                    int correct = 0;
                    List<LeaderboardGlobal> leaderboardGlobalList = client.getLeaderboardGlobal();
                    for(LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList){
                        points += leaderboardGlobal.getScore();
                        correct += leaderboardGlobal.getCorrectBets();
                    }
                    response.put("points", points);
                    response.put("correct_bets", correct);

                    return ok(buildBasicResponse(0, "OK", response));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result getBetsForCompetition(Integer id, Integer idCompetition) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if(timezoneNames == null){//timezoneNames.length <= 0){
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/competition/date/grouped/" + Config.getInt("football-manager-id-app") + "/" + idCompetition + "?timezoneName=" + timezoneName;
                System.out.println(teams);
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    ArrayList<Integer> matchesIDs = new ArrayList<>();
                    ArrayList<ObjectNode> modifiedFixtures = new ArrayList<>();
                    Map<Integer, ObjectNode> matches = new HashMap<>();
                    int maxBetsCount = 0;
                    int clientBetsCount = 0;
                    ObjectNode league = (ObjectNode) footballResponse.get("response");
                    Iterator<JsonNode> fixtures = league.get("fixtures").elements();
                    while (fixtures.hasNext()) {
                        ObjectNode fixture = (ObjectNode) fixtures.next();
                        Iterator<JsonNode> externalMatches = fixture.get("matches").elements();
                        while (externalMatches.hasNext()){
                            ObjectNode externalMatch = (ObjectNode) externalMatches.next();
                            int idGameMatches = externalMatch.get("id_game_matches").asInt();
                            matchesIDs.add(idGameMatches);
                            matches.put(idGameMatches, externalMatch);
                        }
                    }
                    maxBetsCount = matchesIDs.size();
                    List<ClientBets> list = ClientBets.finder.where().eq("client", client).eq("idTournament", league.get("id_competitions").asInt()).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                    if (list != null && !list.isEmpty()) {
                        clientBetsCount+=list.size();
                        ArrayList<ObjectNode> dataFixture = new ArrayList();
                        ArrayList<ObjectNode> orderedFixtures = new ArrayList<>();
                        for (ClientBets clientBets : list) {
                            ObjectNode fixture = matches.get(clientBets.getIdGameMatch());
                            fixture.put("bet", clientBets.toJsonNoClient());
                            modifiedFixtures.add(fixture);
                            matches.remove(clientBets.getIdGameMatch());
                        }
                        Set<Integer> keys = matches.keySet();
                        for (int key : keys) {
                            ObjectNode fixture = matches.get(key);
                            modifiedFixtures.add(fixture);
                        }
                        Collections.sort(modifiedFixtures, new FixturesComparator());

                        String pivot = modifiedFixtures.get(0).get("date").asText().substring(0, 8);
                        Calendar pivotMaximumDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                        Calendar maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                        for (ObjectNode gameMatch : modifiedFixtures){
                            Calendar matchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                            matchDate.setTime(DateAndTime.getDate(gameMatch.get("date").asText(), "yyyyMMddHHmmss", TimeZone.getTimeZone("UTC")));
                            if (matchDate.before(maximumDate)) {
                                orderedFixtures.add(gameMatch);
                            } else {
                                ObjectNode round = Json.newObject();
                                round.put("date", pivot);
                                round.put("matches", Json.toJson(orderedFixtures));
                                dataFixture.add(round);
                                orderedFixtures.clear();
                                orderedFixtures.add(gameMatch);
                                pivot = gameMatch.get("date").asText().substring(0, 8);
                                pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                                maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                            }
                        }
                        if(!orderedFixtures.isEmpty()){
                            ObjectNode round = Json.newObject();
                            round.put("date", pivot);
                            round.put("matches", Json.toJson(orderedFixtures));
                            dataFixture.add(round);
                            orderedFixtures.clear();
                        }

                        league.remove("fixtures");
                        league.put("fixtures", Json.toJson(dataFixture));
                        orderedFixtures.clear();
                        dataFixture.clear();
                    }
                    league.put("total_bets", maxBetsCount);
                    league.put("client_bets", clientBetsCount);
                    modifiedFixtures.clear();
                    matchesIDs.clear();
                    matches.clear();
                    return  ok(buildBasicResponse(0, "OK", league));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static class FixturesComparator implements Comparator<ObjectNode> {
        @Override
        public int compare(ObjectNode o1, ObjectNode o2) {
            int result = o1.get("date").asText().compareTo(o2.get("date").asText());
            if(result == 0){
                return o1.get("id_game_matches").asInt() - o2.get("id_game_matches").asInt();
            }
            return result;
        }
    }

    public static Result getLocale(String lang){
        try {
            Language language = Language.finder.where().eq("active", 1).eq("shortName", lang).findUnique();
            if(language != null) {
                String filePath = Config.getString("locales-path") + language.getAppLocalizationFile();
                if(filePath != null && !filePath.isEmpty()){
                    File file = new File(filePath);
                    if(file != null && file.exists()){
                        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
                        String localization =  new String(encoded, StandardCharsets.UTF_8);
                        response().setHeader("Content-Type", "application/json");
                        return ok(localization);
                    } else {
                        return notFound();
                    }
                }else {
                    return notFound();
                }
            }else {
                return notFound();
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando Idiomas", "error obteniendo localizacion ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1,"Error buscando localizacion",e));
        }
    }

    public static Result setLocale(String lang){
        try {
            ObjectNode locale = getJson();
            Language language = Language.finder.where().eq("active", 1).eq("shortName", lang).findUnique();
            if(language != null) {
                StringBuilder filePath = new StringBuilder();
                filePath.append(Config.getString("locales-path"));
                String appLocalizationFile = language.getAppLocalizationFile();
                boolean update = false;
                if(appLocalizationFile != null && !appLocalizationFile.isEmpty()){
                    filePath.append(appLocalizationFile);
                } else {
                    filePath.append("locale-").append(lang).append(".json");
                    language.setAppLocalizationFile("locale-"+lang+".json");
                    update = true;
                }
                PrintWriter writer = new PrintWriter(filePath.toString(), "UTF-8");
                writer.println(locale);
                writer.close();
                if(update){
                    language.update();
                }
                return created(locale);
            }else {
                return notFound();
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando Idiomas", "error creando localizacion ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1,"Error buscando localizacion",e));
        }
    }

    public static Result getActiveLanguages(){
        try {
            List<Language> activeLanguages = Language.finder.where().eq("active", 1).findList();
            if(activeLanguages != null && !activeLanguages.isEmpty()) {
                ArrayList<ObjectNode> languages = new ArrayList<>();
                for(Language language : activeLanguages){
                    languages.add(language.toJson());
                }
                ObjectNode responseData = Json.newObject();
                responseData.put("languages", Json.toJson(languages));
                return ok(buildBasicResponse(0, "OK", responseData));
            } else {
                return notFound(buildBasicResponse(2, "no hay idiomas activos"));
            }
        }catch (Exception e) {
            Utils.printToLog(Clients.class, "Error manejando Idiomas", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1,"Error buscando idiomas",e));
        }
    }

    public static Result getLeaderboardForClient(Integer id, Integer idTournament, Integer idPhase){
        try {
            ObjectNode responseData = Json.newObject();
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null){
                int leaderboardSize = Config.getInt("leaderboard-size");

                List<Client> friends = null;
                String[] friendsArray = getFromQueryString("friends");
                if (friendsArray != null && friendsArray.length > 0) {
                    friends = Client.getFriends(friendsArray);
                }

                if(idPhase > 0) {
                    Leaderboard clientLeaderboard = null;
                    List<Leaderboard> leaderboards = null;
                    if(friends != null && !friends.isEmpty()){
                        friends.add(client);
                        leaderboards = Leaderboard.finder.where().in("client", friends).eq("idTournament", idTournament).eq("idPhase", idPhase).orderBy("score desc").findList();
                    } else {
                        leaderboards = Leaderboard.finder.where().eq("idTournament", idTournament).eq("idPhase", idPhase).orderBy("score desc").findList();
                    }
                    clientLeaderboard = client.getLeaderboard(idTournament, idPhase);
                    if(leaderboards != null && !leaderboards.isEmpty()) {
                        int index = leaderboards.indexOf(clientLeaderboard);
                        ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                        leaderboardSize = leaderboardSize>leaderboards.size()?leaderboards.size():leaderboardSize;
                        for(int i = 0; i < leaderboardSize; ++i){
                            leaderboardsJson.add(leaderboards.get(i).toJsonSimple());
                        }
                        ObjectNode clientLeaderboardJson = null;
                        if(clientLeaderboard != null) {
                            clientLeaderboardJson = clientLeaderboard.toJsonSimple();
                            clientLeaderboardJson.put("index", index);
                        } else {
                            String nickname = client.getNickname();
                            clientLeaderboardJson = Json.newObject();
                            clientLeaderboardJson.put("id_client", client.getIdClient());
                            clientLeaderboardJson.put("client", nickname==null?"Anônimo":nickname);
                            clientLeaderboardJson.put("score", 0);
                            clientLeaderboardJson.put("hits", 0);
                            clientLeaderboardJson.put("index", leaderboards.size());
                        }

                        responseData.put("leaderboard", Json.toJson(leaderboardsJson));
                        responseData.put("client", clientLeaderboardJson);
                        return ok(buildBasicResponse(0, "OK", responseData));
                    } else {
                        return notFound(buildBasicResponse(3, "leaderboard vacio"));
                    }
                } else {
                    LeaderboardGlobal clientLeaderboardGlobal = null;
                    List<LeaderboardGlobal> globalLeaderboards = null;
                    if(friends != null && !friends.isEmpty()){
                        friends.add(client);
                        globalLeaderboards = LeaderboardGlobal.finder.where().in("client", friends).eq("idTournament", idTournament).orderBy("score desc").findList();
                    } else {
                        globalLeaderboards = LeaderboardGlobal.finder.where().eq("idTournament", idTournament).orderBy("score desc").findList();
                    }
                    clientLeaderboardGlobal = client.getLeaderboardGlobal(idTournament);
                    if(globalLeaderboards != null && !globalLeaderboards.isEmpty()) {
                        int index = globalLeaderboards.indexOf(clientLeaderboardGlobal);
                        ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                        leaderboardSize = leaderboardSize>globalLeaderboards.size()?globalLeaderboards.size():leaderboardSize;
                        for(int i = 0; i < leaderboardSize; ++i){
                            leaderboardsJson.add(globalLeaderboards.get(i).toJsonSimple());
                        }
                        ObjectNode clientLeaderboardJson = null;
                        if(clientLeaderboardGlobal != null) {
                            clientLeaderboardJson = clientLeaderboardGlobal.toJsonSimple();
                            clientLeaderboardJson.put("index", index);
                        } else {
                            String nickname = client.getNickname();
                            clientLeaderboardJson = Json.newObject();
                            clientLeaderboardJson.put("id_client", client.getIdClient());
                            clientLeaderboardJson.put("client", nickname==null?"Anônimo":nickname);
                            clientLeaderboardJson.put("score", 0);
                            clientLeaderboardJson.put("hits", 0);
                            clientLeaderboardJson.put("index", globalLeaderboards.size());
                        }
                        responseData.put("leaderboard", Json.toJson(leaderboardsJson));
                        responseData.put("client", clientLeaderboardJson);
                        return ok(buildBasicResponse(0, "OK", responseData));
                    } else {
                        return ok(buildBasicResponse(3, "leaderboard vacio"));
                    }
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1,"Error buscando el registro",e));
        }
    }

    public static Result getLeaderboardTotalForClient(Integer id){
        try {
            ObjectNode responseData = Json.newObject();
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null){
                int leaderboardSize = Config.getInt("leaderboard-size");

                List<Client> friends = null;
                String[] friendsArray = getFromQueryString("friends");
                if (friendsArray != null && friendsArray.length > 0) {
                    friends = Client.getFriends(friendsArray);
                }
                LeaderboardTotal clientLeaderboardTotal = null;
                List<LeaderboardTotal> totalLeaderboards = null;
                if(friends != null && !friends.isEmpty()){
                    friends.add(client);
                    totalLeaderboards = LeaderboardTotal.finder.where().in("client", friends).orderBy("score desc").findList();
                } else {
                    totalLeaderboards = LeaderboardTotal.finder.where().orderBy("score desc").findList();
                }
                clientLeaderboardTotal = client.getLeaderboardTotal();
                if(totalLeaderboards != null && !totalLeaderboards.isEmpty()) {
                    int index = totalLeaderboards.indexOf(clientLeaderboardTotal);
                    ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                    leaderboardSize = leaderboardSize>totalLeaderboards.size()?totalLeaderboards.size():leaderboardSize;
                    for(int i = 0; i < leaderboardSize; ++i){
                        leaderboardsJson.add(totalLeaderboards.get(i).toJsonSimple());
                    }
                    ObjectNode clientLeaderboardJson = null;
                    if(clientLeaderboardTotal != null) {
                        clientLeaderboardJson = clientLeaderboardTotal.toJsonSimple();
                        clientLeaderboardJson.put("index", index);
                    } else {
                        String nickname = client.getNickname();
                        clientLeaderboardJson = Json.newObject();
                        clientLeaderboardJson.put("id_client", client.getIdClient());
                        clientLeaderboardJson.put("client", nickname==null?"Anônimo":nickname);
                        clientLeaderboardJson.put("score", 0);
                        clientLeaderboardJson.put("hits", 0);
                        clientLeaderboardJson.put("index", totalLeaderboards.size());
                    }
                    responseData.put("leaderboard", Json.toJson(leaderboardsJson));
                    responseData.put("client", clientLeaderboardJson);
                    return ok(buildBasicResponse(0, "OK", responseData));
                } else {
                    return ok(buildBasicResponse(3, "leaderboard vacio"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1,"Error buscando el registro",e));
        }
    }

    public static Result getPersonalLeaderboardForClient(Integer id, Integer idTournament, final Integer idPhase, Boolean global){
        try {
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null){
                ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                if(global){
                    List<LeaderboardGlobal> leaderboardGlobalList = client.getLeaderboardGlobal();
                    for(LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList){
                        leaderboardsJson.add(leaderboardGlobal.toJsonClean());
                    }
                } else {
                    List<Leaderboard> leaderboards = null;
                    ArrayList<ObjectNode> phasesJson = new ArrayList<>();
                    if (idTournament > 0) {
                        leaderboards = client.getLeaderboard(idTournament);
                        if(leaderboards != null && !leaderboards.isEmpty()){
                            final String[] phasesArray = getFromQueryString("phases");
                            if(phasesArray != null && phasesArray.length > 0) {
                                Predicate<Leaderboard> validObjs = new Predicate<Leaderboard>() {
                                    public boolean apply(Leaderboard obj) {
                                        for(int i = 0; i < phasesArray.length; ++i){
                                            if(obj.getIdPhase().intValue() == Integer.parseInt(phasesArray[i])){
                                                return true;
                                            }
                                        }
                                        return false;
                                    }
                                };
                                leaderboards = (List<Leaderboard>) Utils.filterCollection(leaderboards, validObjs);
                            } else if(idPhase > 0) {
                                Predicate<Leaderboard> validObjs = new Predicate<Leaderboard>() {
                                    public boolean apply(Leaderboard obj) {
                                        return obj.getIdPhase().intValue() > idPhase;
                                    }
                                };
                                leaderboards = (List<Leaderboard>) Utils.filterCollection(leaderboards, validObjs);
                            }
                            for(Leaderboard leaderboard : leaderboards){
                                phasesJson.add(leaderboard.toJsonClean());
                            }
                            ObjectNode tournament = Json.newObject();
                            tournament.put("id_tournament", idTournament);
                            tournament.put("phases", Json.toJson(phasesJson));
                            leaderboardsJson.add(tournament);
                        }
                    } else {
                        leaderboards = client.getLeaderboards();
                        if(leaderboards != null && !leaderboards.isEmpty()){
                            int pivot = leaderboards.get(0).getIdTournament();
                            for(Leaderboard leaderboard : leaderboards){
                                if(leaderboard.getIdTournament() == pivot){
                                    phasesJson.add(leaderboard.toJsonClean());
                                } else {
                                    ObjectNode tournament = Json.newObject();
                                    tournament.put("id_tournament", pivot);
                                    tournament.put("phases", Json.toJson(phasesJson));
                                    leaderboardsJson.add(tournament);
                                    phasesJson.clear();
                                    pivot = leaderboard.getIdTournament();
                                    phasesJson.add(leaderboard.toJsonClean());
                                }
                            }
                            if(!leaderboardsJson.isEmpty()){
                                ObjectNode tournament = Json.newObject();
                                tournament.put("id_tournament", pivot);
                                tournament.put("phases", Json.toJson(phasesJson));
                                leaderboardsJson.add(tournament);
                                phasesJson.clear();
                            }
                        }
                    }
                }
                return ok(hecticusResponse(0, "ok", "leaderboard", leaderboardsJson));
            } else {
                return ok(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result dashboard(Integer id, Integer idLanguage) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            String timezoneName = null;
            if(timezoneNames == null || timezoneNames.length <= 0){
                //resturar cuando se cambie la version que no manda el timezone (HOY ES 27/07/2015, avisenle a iñaki cuando quiten esto. mi apuesta es que nadie se dara cuenta)
//                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            } else {
                timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            }
            FootballClient client = (FootballClient) Client.getByID(id);
            if(client != null) {
                String[] favorites = getFromQueryString("teams");
                StringBuilder teamsBuilder = new StringBuilder();
                if (favorites != null && favorites.length > 0) {
                    for(String team : favorites) {
                        teamsBuilder.append("&teams=").append(team);
                    }
                }
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/competitions/list/" + Config.getInt("football-manager-id-app") + "/" + idLanguage + "?closestMatch=true"  + (timezoneName!=null?"&timezoneName=" + timezoneName:"") + (teamsBuilder.length() > 0? teamsBuilder.toString() : "");
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if(error == 0) {
                    ObjectNode response = Json.newObject();
                    JsonNode data = footballResponse.get("response");

                    int points = 0;
                    int correct = 0;
                    List<LeaderboardGlobal> leaderboardGlobalList = client.getLeaderboardGlobal();
                    for(LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList){
                        points += leaderboardGlobal.getScore();
                        correct += leaderboardGlobal.getCorrectBets();
                    }
                    response.put("points", points);
                    response.put("correct_bets", correct);
                    response.put("competitions", data.get("competitions"));

                    return ok(buildBasicResponse(0, "OK", response));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        }catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

}
