package controllers.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import controllers.FootballController;
import controllers.HecticusController;
import controllers.Secured;
import controllers.Upstream;
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
import models.tracks.LoginTracks;
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

import java.io.*;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import models.Job;


import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by plesse on 9/30/14.
 */
//@Security.Authenticated(Secured.class)
public class FootballClients extends Clients {

    public static Result createKrakenweb(String msisdn) {
        try {
            Upstream.EventKraken(msisdn);
            //Client.createkraken(msisdn, pass, usd);
            return ok(buildBasicResponse(0, "OK"));
        } catch (Exception ex) {

            return Results.badRequest(buildBasicResponse(3, "ocurrio un error ", ex));
        }

    }

    public static Result createKraken(String msisdn, String pass, String usd) {
        try {
            Client.createkraken(msisdn, pass, usd);
            //return ok(buildBasicResponse(0, "OK"));
            FootballClient client = null;
            Client baseClient = Client.getByLogin(msisdn);
            if (baseClient != null) {
                client = new FootballClient(Client.getByID(baseClient.getIdClient()));

                int firstLoginPoints = Config.getInt("first-login-points");
                LeaderboardTotal firstLoginLeaderboard = new LeaderboardTotal(client, firstLoginPoints, 0);
                if (client.getLeaderboardTotal() == null) {
                    client.setLeaderboardTotal(firstLoginLeaderboard);
                    client.update();
                }
                return ok(buildBasicResponse(0, "OK"));

            } else {
                return Results.badRequest(buildBasicResponse(3, "ocurrio un error recuperando usuario"));
            }
        } catch (Exception ex) {

            return Results.badRequest(buildBasicResponse(3, "ocurrio un error recuperando password", ex));
        }

    }

    public static Result downKraken(String msisdn) {
        try {
            Client.downkraken(msisdn);
            return ok(buildBasicResponse(0, "OK"));
        } catch (Exception ex) {

            return Results.badRequest(buildBasicResponse(3, "ocurrio un error recuperando password", ex));
        }

    }

    public static Result down(String msisdn) {
        try {
            //http://02.kapp.hecticus.com/ws/receiveMO.php?destination=9090&service_type=pacws&msg=APPCANCELAR&received_time=20151118170000&source=XXXXXXXXXX
            //http://silversolempresas.com/DesconexionUnificada/webServices/wsSuscripciones.asmx/Baja?OperadoraId=1&NumeroCorto=9090&Telefono=string
            StringBuilder url = new StringBuilder();
            url.append("http://").append(Utils.getSilverHost()).append(msisdn);
            F.Promise<WSResponse> result = WS.url(url.toString()).get();
            String prueba = result.get(1000).getBody();

            url = new StringBuilder();
            url.append("http://").append(Utils.getKrakenHost()).append(msisdn);
            result = WS.url(url.toString()).get();
            prueba = result.get(1000).getBody();

            Client.downkraken(msisdn);
            return ok(buildBasicResponse(0, "OK"));
        } catch (Exception ex) {

            return Results.badRequest(buildBasicResponse(3, "ocurrio un error dando de baja par ale numero " + msisdn, ex));
        }

    }


//    public static uploadMethoKraken(){
//        String ws = daemonUrl.getValueConf()+"/KrakenDaemon/v1/prediction";
//        ObjectNode event = Json.newObject();
//        event.put("source", "9090");
//        event.put("destination", msysdn);
//        event.put("id_business", 23);
//        event.put("id_carrier", 11);
//        //event.put("msg_to_send", code);
//        event.put("id_country", 8);
//        event.put("external_id", id en tu db);
//        event.put("msg", "GANA");
//        event.put("origin", "PLUSSPORT");
//        F.Promise<WSResponse> result = WS.url(ws).post(event);
//        String json = result.get(10000).getBody();
//    }

    public static Result checkMsisdn() {
        ObjectNode data = getJson();
        ObjectNode ret = Json.newObject();
        if (data.has("msisdn")) {
            Client client = Client.getByLogin(data.get("msisdn").asText());
            if(client != null)
                ret.put("client", client.toJsonWithoutRelations());
        }
        return ok(buildBasicResponse(0, "OK", ret));
    }

    public static Result checkPin() {
        ObjectNode data = getJson();
        ObjectNode ret = Json.newObject();
        if (data.has("pin") && data.has("login")) {
            boolean confirm = SilverAPI.confirmPin(data.get("pin").asText(), data.get("login").asText());
            ret.put("valid", confirm ? "1" : "0");
        }
        return ok(buildBasicResponse(0, "OK", ret));
    }

    public static Result smsOld() {
        ObjectNode data = getJson();
        ObjectNode ret = Json.newObject();
        if (data.has("msisdn")) {
            Upstream.EventKraken(data.get("msisdn").asText());
            ret.put("go", "1");
            //ret.put("valid", confirm ? "1": "0");
        } else
            ret.put("go", "0");
        return ok(buildBasicResponse(0, "OK", ret));
    }

    public static Result create() {
        ObjectNode clientData = getJson();
        Logger.of("upstream_subscribe").trace("app_request: " + clientData);
        String remote_ip = request().remoteAddress();
        clientData.put("remote_ip", remote_ip);
        Boolean isRecuperar = false;


        try {
            FootballClient client = null;
            String login = null;
            if (clientData.has("login")) {
                login = clientData.get("login").asText();
                login = login.replaceAll("[ \\-+^]*", "");
                clientData.put("login", login);
            }
            if (login != null) { //cuando es un invitado
                boolean isRemind = !clientData.has("password");
                //client = (FootballClient) Client
                // .getAndUpdate(login, clientData, isRemind);
                // //Con esta linea no me funciona

                //client = (FootballClient) Client.getAndUpdate(login, clientData, isRemind);
                Client tmp = Client.getAndUpdate(login, clientData);
                if (tmp != null)
                    client = new FootballClient(tmp);
                if (tmp == null && !isRemind) {
                    ObjectNode responseNode = Json.newObject();
                    responseNode.put("error", -2);
                    responseNode.put("description", "error");
                    responseNode.put("exception", "error");
                    responseNode.put("upstream_code", 401);
                    return internalServerError(responseNode);
                }
                if (tmp != null) {
                    if (isRemind) {
                        //Logger.of("upstream_subscribe").trace("app_request: " + clientData);
                        //TODO Manbdar MT directamente cambios apra soportar el metodo
                        //SilverAPI.GetPin(login);
                        Upstream.EventKraken(client);
                        //Client.subscribe(client, clientData, "remind_password");
                    }
                    return ok(buildBasicResponse(0, "OK", client.toJson()));
                } else if (isRemind) isRecuperar = true;
            }
            Client baseClient = Client.create("football", clientData);
            if (isRecuperar) Upstream.EventKraken(baseClient);
            client = new FootballClient(Client.getByID(baseClient.getIdClient()));
            // client = (FootballClient) Client.getByID(baseClient.getIdClient());
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
            if (client.getStatus() != 2 || !(client.getLogin() != null ? client.getLogin().equalsIgnoreCase(Config.getString("upstreamGuestUser")) : false)) {
                int firstLoginPoints = Config.getInt("first-login-points");
                LeaderboardTotal firstLoginLeaderboard = new LeaderboardTotal(client, firstLoginPoints, 0);

                //TODO ver como hacer para que solo entre una vez
                client.setLeaderboardTotal(firstLoginLeaderboard);
            }
//                try {
//                    ObjectNode event = Json.newObject();
//                    ObjectNode metadata = Json.newObject();
////                    ArrayList<ObjectNode> pointsList = new ArrayList<>(1);
////                    ObjectNode actualPoints = Json.newObject();
////                    actualPoints.put("type", "experience");
////                    actualPoints.put("value", firstLoginPoints);
////                    pointsList.add(actualPoints);
////                    metadata.put("result", "win");
////                    metadata.put("points", Json.toJson(pointsList));
//                    event.put("event_type", "APP_LAUNCH");
//                    event.put("metadata", metadata);
//                    F.Promise<WSResponse> result = WS.url("http://" + Config.getHost() + "/sportsapi/v2/client/" + client.getIdClient() + "/upstream").post(event);
//                    ObjectNode response = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
//                } catch (Exception e) {
//                    Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando el client con params " + clientData, false, e, "support-level-1", Config.LOGGER_ERROR);
//                }
//            }
            //Creación de la instancia login_tracks
            //LoginTracks track = new LoginTracks(client.toJson().toString(),baseClient,remote_ip);
            //track.save();

            if (client.getLogin() == null) {
                //Upstream.EventKraken(baseClient);
                if (client.getLogin() == null) {
                    client.setLogin(Config.getString("upstreamGuestUser"));
                }
                if (client.getPassword() == null) {
                    client.setPassword(Config.getString("upstreamGuestPassword"));
                }
                if (client.getUserId() == null) {
                    client.setUserId(Config.getString("upstreamUserID"));
                }
                client.setStatus(2);
            }

            /*if(client.getLogin() == null) {
                client.setLogin("guest");//gin = ::
                client.setPassword("guest");//gin = ::
                client.setNickname("guest");//gin = ::
                client.setUserId("1");//gin = ::
            }*/

            client.update();
            return created(buildBasicResponse(0, "OK", client.toJson()));
        } catch (Exception ex) {
            ObjectNode response;
            if (ex instanceof UpstreamException) {
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

    /**
     * index Created by Leonel on 9/30/14.
     * Get IP Address of remote client
     */
    public static Result remoteIp() {
        String remote = request().remoteAddress();
        return ok(remote);
    }


    public static Result update(Integer id) {
        ObjectNode clientData = getJson();
        //Logger.of("upstream_subscribe").trace("update " + id + " app_request: " + clientData);
        try {
            if (clientData.has("login")) {
                String login = clientData.get("login").asText();
                Client byLogin = Client.getByLogin(login);
                if (byLogin != null) {
                    id = byLogin.getIdClient();
                }
            }
            // FootballClient client = (FootballClient) Client.update(id, clientData);
            FootballClient client = new FootballClient(Client.update(id, clientData));
            if (client != null) {


                boolean update = false;
                if (clientData.has("remove_push_alert")) {
                    Iterator<JsonNode> alertsIterator = clientData.get("remove_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).eq("sport_id", 1).findUnique();
                        if (pushAlert == null) {
                            continue;
                        }
                        List<ClientHasPushAlerts> clientHasPushAlerts = ClientHasPushAlerts.finder.where().eq("client.idClient", client.getIdClient()).eq("pushAlert.idPushAlert", pushAlert.getIdPushAlert()).findList();
                        if (clientHasPushAlerts != null && !clientHasPushAlerts.isEmpty()) {
                            for (ClientHasPushAlerts clientHasPushAlert : clientHasPushAlerts) {
                                client.getPushAlerts().remove(clientHasPushAlert);
                                clientHasPushAlert.delete();
                            }
                            update = true;
                        }
                    }
                }
                if (clientData.has("add_push_alert")) {
                    Iterator<JsonNode> alertsIterator = clientData.get("add_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        int index = client.getPushAlertIndex(next.asInt());
                        if (index == -1) {
                            PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).eq("sport_id", 1).findUnique();
                            if (pushAlert != null) {
                                ClientHasPushAlerts chpa = new ClientHasPushAlerts(client, pushAlert);
                                client.getPushAlerts().add(chpa);
                                update = true;
                            }
                        }
                    }
                }

                if (clientData.has("remove_push_alert_baseball")) {
                    Iterator<JsonNode> alertsIterator = clientData.get("remove_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).eq("sport_id", 2).findUnique();
                        if (pushAlert == null) {
                            continue;
                        }
                        List<ClientHasPushAlerts> clientHasPushAlerts = ClientHasPushAlerts.finder.where().eq("client.idClient", client.getIdClient()).eq("pushAlert.idPushAlert", pushAlert.getIdPushAlert()).findList();
                        if (clientHasPushAlerts != null && !clientHasPushAlerts.isEmpty()) {
                            for (ClientHasPushAlerts clientHasPushAlert : clientHasPushAlerts) {
                                client.getPushAlerts().remove(clientHasPushAlert);
                                clientHasPushAlert.delete();
                            }
                            update = true;
                        }
                    }
                }
                if (clientData.has("add_push_alert_baseball")) {
                    Iterator<JsonNode> alertsIterator = clientData.get("add_push_alert").elements();
                    while (alertsIterator.hasNext()) {
                        JsonNode next = alertsIterator.next();
                        int index = client.getPushAlertIndex(next.asInt());
                        if (index == -1) {
                            PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", next.asInt()).eq("sport_id", 2).findUnique();
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
                if (clientData.has("receive_news")) {
                    boolean receiveNews = clientData.get("receive_news").asBoolean();
                    int index = client.getPushAlertIDIndex(newsPushId);
                    if (index > -1) {
                        client.getPushAlerts().get(index).setStatus(receiveNews);
                        update = true;
                    }
                }
                if (clientData.has("receive_bets")) {
                    boolean receiveBets = clientData.get("receive_bets").asBoolean();
                    int index = client.getPushAlertIDIndex(betsPushId);
                    if (index > -1) {
                        client.getPushAlerts().get(index).setStatus(receiveBets);
                        update = true;
                    }
                }
                if (clientData.has("receive_min")) {
                    boolean receiveMin = clientData.get("receive_min").asBoolean();
                    for (ClientHasPushAlerts clientHasPushAlerts : client.getPushAlerts()) {
                        if (clientHasPushAlerts.getPushAlert().getIdPushAlert() != betsPushId && clientHasPushAlerts.getPushAlert().getIdPushAlert() != newsPushId) {
                            clientHasPushAlerts.setStatus(receiveMin);
                            update = true;
                        }
                    }
                }

                String[] remind = getFromQueryString("remind");
                if (remind != null && remind.length > 0) {
                    if (Boolean.parseBoolean(remind[0])) {
                        //Logger.of("upstream_subscribe").trace("app_request: " + clientData);
                        Client.subscribe(client, clientData, "remind_password_on_update");
                    }
                }

                if (update) {
                    client.update();
                }
                return ok(buildBasicResponse(0, "OK", client.toJson()));
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception ex) {
            ObjectNode response;
            if (ex instanceof UpstreamException) {
                UpstreamException upstreamException = (UpstreamException) ex;
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error actualizando el client " + id + " el request fue " + upstreamException.getRequest(), true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildUpstreamResponse(-2, "ocurrio un error creando el registro", upstreamException);
            } else {
                Utils.printToLog(FootballClients.class, "Error manejando clients", "error actualizando el client " + id, true, ex, "support-level-1", Config.LOGGER_ERROR);
                response = buildBasicResponse(-1, "ocurrio un error creando el registro", ex);
            }
            return internalServerError(response);
        }
    }

    public static Result get(Integer id, String upstreamChannel, Boolean pmc) {
        try {
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                if (!pmc) {
                    client.checkStatus(upstreamChannel);
                }
                return ok(buildBasicResponse(0, "OK", pmc ? client.toPMCJson() : client.toJson()));
            } else {
                return notFound(buildBasicResponse(2, "no existe el registro a consultar"));
            }
        } catch (Exception ex) {
            ObjectNode response;
            if (ex instanceof UpstreamException) {
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
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                if (client.getStatus() >= 0) {
                    List<ClientHasPushAlerts> pushAlerts = client.getPushAlerts();
                    ArrayList jsonAlerts = new ArrayList();
                    for (int i = 0; i < pushAlerts.size(); i++) {
                        jsonAlerts.add(pushAlerts.get(i).toJson());
                    }
                    return ok(buildBasicResponse(0, "OK", Json.toJson(jsonAlerts)));
                } else {
                    return badRequest(buildBasicResponse(3, "cliente " + id + " no se encuentra en status valido"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
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
            //FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                Iterator<JsonNode> bets = betsData.get("bets").elements();
                Map<Integer, ObjectNode> betsMap = new HashMap<>();
                StringBuilder matchesRequest = new StringBuilder();
                matchesRequest.append("http://").append(Utils.getFootballManagerHost()).append("/footballapi/v1/matches/get/ids/").append(Config.getInt("football-manager-id-app")).append("?");
                int idTournament = -1, idPhase = -1, idGameMatch = -1, clientBet = -1;
                ClientBets clientBets = null;
                while (bets.hasNext()) {
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
                int betWindow = Config.getInt("bet-window");
                F.Promise<WSResponse> result = WS.url(matchesRequest.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();

                int error = footballResponse.get("error").asInt();
                if (error == 0) {
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
                            gameDate.add(Calendar.MINUTE, -betWindow);
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
                } else {
                    return internalServerError(buildBasicResponse(1, "error vaidando partidos"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result createBet(Integer id) {
        ObjectNode betData = getJson();
        try {
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                ObjectNode bet = (ObjectNode) betData.get("bet");
                int idTournament = -1, idPhase = -1, idGameMatch = -1, clientBet = -1;
                ClientBets clientBets = null;
                idGameMatch = bet.get("id_game_match").asInt();
                StringBuilder matchesRequest = new StringBuilder();
                matchesRequest.append("http://").append(Utils.getFootballManagerHost()).append("/footballapi/v2/").append(Config.getInt("football-manager-id-app")).append("/match/").append(idGameMatch);

                F.Promise<WSResponse> result = WS.url(matchesRequest.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int betWindow = Config.getInt("bet-window");
                int error = footballResponse.get("error").asInt();
                if (error == 0) {
                    ObjectNode match = (ObjectNode) footballResponse.get("response");
                    idGameMatch = match.get("id_game_matches").asInt();

                    idTournament = bet.get("id_tournament").asInt();
                    idPhase = match.get("phase").asInt();
                    clientBet = bet.get("client_bet").asInt();

                    String dateText = match.get("date").asText();
                    Date date = DateAndTime.getDate(dateText, dateText.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss");
                    Calendar gameDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    gameDate.setTime(date);
                    gameDate.add(Calendar.MINUTE, -betWindow);
                    Date today = new Date(System.currentTimeMillis());
                    client.getClientsBet();

                    if (gameDate.getTime().after(today)) {
                        clientBets = client.getBet(idTournament, idPhase, idGameMatch);
                        if (clientBets != null) {
                            clientBets.setClientBet(clientBet);
                        } else {
                            clientBets = new ClientBets(client, idTournament, idPhase, idGameMatch, clientBet, dateText);
                            if ((client.getStatus() != 1) && (client.getClientsBet().size() >= Config.getInt("visitor-number-bets")))
                                return ok(buildBasicResponse(1, "ERROR - Bet number reached", clientBets.toJsonNoClient()));
                        }

                        client.addClientBet(clientBets);
                        client.update();
                        return ok(buildBasicResponse(0, "ok", clientBets.toJsonNoClient()));
                    } else {
                        return badRequest(buildBasicResponse(1, "La apuesta no puede ser creada por ser de un partido pasado"));
                    }
                } else {
                    return (error > 0) ? notFound(footballResponse) : internalServerError(footballResponse);
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente" + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    ///Nueva funcion super sensual de fernando que soporta multiples deportes in da same place pasalr el Identificador del deporte and boom eady to go
    public static Result createBetNew(Integer id) {
        ObjectNode betData = getJson();
        try {
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                ObjectNode bet = (ObjectNode) betData.get("bet");
                int idTournament = -1, idPhase = -1, idGameMatch = -1, clientBet = -1;
                ClientBets clientBets = null;
                idGameMatch = bet.get("id_game_match").asInt();
                StringBuilder matchesRequest = new StringBuilder();


                int sportId = bet.get("sport_id") == null? 1:bet.get("sport_id").asInt() ;

                //Para Baseball
                if (sportId == 2) {
                    matchesRequest.append("http://").append(Utils.getBaseBallManagerHost()).append("/baseballapi/v2/match/").append(idGameMatch);
                }
                else
                {
                    matchesRequest.append("http://").append(Utils.getFootballManagerHost()).append("/footballapi/v2/").append(Config.getInt("football-manager-id-app")).append("/match/").append(idGameMatch);
                }
                    F.Promise<WSResponse> result = WS.url(matchesRequest.toString()).get();
                    ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                    int betWindow = Config.getInt("bet-window");
                    int error = footballResponse.get("error").asInt();
                    if (error == 0) {
                        ObjectNode match = (ObjectNode) footballResponse.get("response");

                        String dateText = match.get("date").asText();

                        if (sportId == 2) {
                            idGameMatch = match.get("id_game").asInt();
                            idTournament = bet.get("id_tournament").asInt();
                            idPhase = 1;
                        }
                        else
                        {
                            idGameMatch = match.get("id_game_matches").asInt();
                            idTournament = bet.get("id_tournament").asInt();
                            idPhase = match.get("phase").asInt();
                        }
                        clientBet = bet.get("client_bet").asInt();

                        Date date = DateAndTime.getDate(dateText, dateText.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss");
                        Calendar gameDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        gameDate.setTime(date);
                        gameDate.add(Calendar.MINUTE, -betWindow);
                        Date today = new Date(System.currentTimeMillis());
                        client.getClientsBet();

                        if (sportId == 2) 
                            gameDate.add(Calendar.HOUR_OF_DAY, 5); 
                        

                        if (gameDate.getTime().after(today)) {
                            clientBets = client.getBet(idTournament, idPhase, idGameMatch, sportId);
                            if (clientBets != null) {
                                clientBets.setClientBet(clientBet);
                            } else {
                                clientBets = new ClientBets(client, idTournament, idPhase, idGameMatch, clientBet, dateText, sportId);
                                if ((client.getStatus() != 1) && (client.getClientsBet().size() >= Config.getInt("visitor-number-bets")))
                                    return ok(buildBasicResponse(1, "ERROR - Bet number reached", clientBets.toJsonNoClient()));
                            }

                            client.addClientBet(clientBets);
                            client.update();
                            return ok(buildBasicResponse(0, "ok", clientBets.toJsonNoClient()));
                        } else {
                            return badRequest(buildBasicResponse(1, "La apuesta no puede ser creada por ser de un partido pasado"));
                        }
                    } else {
                        return (error > 0) ? notFound(footballResponse) : internalServerError(footballResponse);
                    }



            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente" + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static Result getBets(Integer id) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if (timezoneNames == null || timezoneNames.length <= 0) {
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/date/grouped/" + Config.getInt("football-manager-id-app") + "?timezoneName=" + timezoneName;
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if (error == 0) {
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
                            while (externalMatches.hasNext()) {
                                ObjectNode externalMatch = (ObjectNode) externalMatches.next();
                                int idGameMatches = externalMatch.get("id_game_matches").asInt();
                                matchesIDs.add(idGameMatches);
                                matches.put(idGameMatches, externalMatch);
                            }
                        }
                        maxBetsCount = matchesIDs.size();
                        List<ClientBets> list = ClientBets.finder.where().eq("client", client).eq("idTournament", league.get("id_competitions").asInt()).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                        if (list != null && !list.isEmpty()) {
                            clientBetsCount += list.size();
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
                            for (ObjectNode gameMatch : modifiedFixtures) {
                                if (gameMatch.get("date").asText().startsWith(pivot)) {
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
                            if (!orderedFixtures.isEmpty()) {
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
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result getBetsForDate(Integer id, String date) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if (timezoneNames.length <= 0) {
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/all/date/get/" + Config.getInt("football-manager-id-app") + "/" + date + "?timezoneName=" + timezoneName;
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if (error == 0) {
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
                    for (LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList) {
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
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result getBetsForCompetition(Integer id, Integer idCompetition) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if (timezoneNames == null) {//timezoneNames.length <= 0){
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String ssptid = getFromQueryString("sport_id") == null? "":getFromQueryString("sport_id")[0];
            if(ssptid.equals("2")) ///Validacion bien chingona que detecta si quieres el status 2 para obtener los valores de baseball
                return getBetsForCompetitionBaseball(id, idCompetition);
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/matches/competition/date/grouped/" + Config.getInt("football-manager-id-app") + "/" + idCompetition + "?timezoneName=" + timezoneName;
                System.out.println(teams);
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if (error == 0) {
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
                        while (externalMatches.hasNext()) {
                            ObjectNode externalMatch = (ObjectNode) externalMatches.next();
                            int idGameMatches = externalMatch.get("id_game_matches").asInt();
                            matchesIDs.add(idGameMatches);
                            matches.put(idGameMatches, externalMatch);
                        }
                    }
                    maxBetsCount = matchesIDs.size();
                    List<ClientBets> list = ClientBets.finder.where().eq("client", client).eq("idTournament", league.get("id_competitions").asInt()).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                    if (list != null && !list.isEmpty()) {
                        clientBetsCount += list.size();
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
                        for (ObjectNode gameMatch : modifiedFixtures) {
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
                                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                                String formatted = format1.format(matchDate.getTime());
                                pivot = formatted;
                                pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                                maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                            }
                        }
                        if (!orderedFixtures.isEmpty()) {
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
                    return ok(buildBasicResponse(0, "OK", league));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static Result getBetsForCompetitionBaseball(Integer id, Integer idCompetition) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            if (timezoneNames == null) {//timezoneNames.length <= 0){
                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            }
            String timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                String teams = "http://" + Utils.getBaseBallManagerHost() + "/baseballapi/v1/matches/competition/date/grouped/" + idCompetition + "?timezoneName=" + timezoneName;
                System.out.println(teams);
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();
                if (error == 0) {
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
                        while (externalMatches.hasNext()) {
                            ObjectNode externalMatch = (ObjectNode) externalMatches.next();
                            int idGameMatches = externalMatch.get("id_game").asInt();
                            matchesIDs.add(idGameMatches);
                            matches.put(idGameMatches, externalMatch);
                        }
                    }
                    maxBetsCount = matchesIDs.size();
                    List<ClientBets> list = ClientBets.finder.where().eq("client", client).eq("idTournament", league.get("id_competition").asInt()).in("idGameMatch", matchesIDs).orderBy("idGameMatch asc").findList();
                    if (list != null && !list.isEmpty()) {
                        clientBetsCount += list.size();
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
                        Collections.sort(modifiedFixtures, new FixturesComparatorBaseball());

                        String pivot = modifiedFixtures.get(0).get("date").asText().substring(0, 8);
                        Calendar pivotMaximumDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        pivotMaximumDate.setTime(DateAndTime.getDate(pivot, "yyyyMMdd", TimeZone.getTimeZone("UTC")));
                        Calendar maximumDate = DateAndTime.getMaximumDate(pivotMaximumDate, timezoneName);
                        for (ObjectNode gameMatch : modifiedFixtures) {
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
                        if (!orderedFixtures.isEmpty()) {
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
                    return ok(buildBasicResponse(0, "OK", league));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }


    public static class FixturesComparator implements Comparator<ObjectNode> {
        @Override
        public int compare(ObjectNode o1, ObjectNode o2) {
            int result = o1.get("date").asText().compareTo(o2.get("date").asText());
            if (result == 0) {
                return o1.get("id_game_matches").asInt() - o2.get("id_game_matches").asInt();
            }
            return result;
        }
    }

    public static class FixturesComparatorBaseball implements Comparator<ObjectNode> {
        @Override
        public int compare(ObjectNode o1, ObjectNode o2) {
            int result = o1.get("date").asText().compareTo(o2.get("date").asText());
            if (result == 0) {
                return o1.get("id_game").asInt() - o2.get("id_game").asInt();
            }
            return result;
        }
    }

    public static Result getLocale(String lang) {
        try {
            Language language = Language.finder.where().eq("active", 1).eq("shortName", lang).findUnique();
            if (language != null) {
                String filePath = Config.getString("locales-path") + language.getAppLocalizationFile();
                if (filePath != null && !filePath.isEmpty()) {
                    File file = new File(filePath);
                    if (file != null && file.exists()) {
                        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
                        String localization = new String(encoded, StandardCharsets.UTF_8);
                        response().setHeader("Content-Type", "application/json");
                        return ok(localization);
                    } else {
                        return notFound();
                    }
                } else {
                    return notFound();
                }
            } else {
                return notFound();
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando Idiomas", "error obteniendo localizacion ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando localizacion", e));
        }
    }

    public static Result setLocale(String lang) {
        try {
            ObjectNode locale = getJson();
            Language language = Language.finder.where().eq("active", 1).eq("shortName", lang).findUnique();
            if (language != null) {
                StringBuilder filePath = new StringBuilder();
                filePath.append(Config.getString("locales-path"));
                String appLocalizationFile = language.getAppLocalizationFile();
                boolean update = false;
                if (appLocalizationFile != null && !appLocalizationFile.isEmpty()) {
                    filePath.append(appLocalizationFile);
                } else {
                    filePath.append("locale-").append(lang).append(".json");
                    language.setAppLocalizationFile("locale-" + lang + ".json");
                    update = true;
                }
                PrintWriter writer = new PrintWriter(filePath.toString(), "UTF-8");
                writer.println(locale);
                writer.close();
                if (update) {
                    language.update();
                }
                return created(locale);
            } else {
                return notFound();
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando Idiomas", "error creando localizacion ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando localizacion", e));
        }
    }

    public static Result getActiveLanguages() {
        try {
            List<Language> activeLanguages = Language.finder.where().eq("active", 1).findList();
            if (activeLanguages != null && !activeLanguages.isEmpty()) {
                ArrayList<ObjectNode> languages = new ArrayList<>();
                for (Language language : activeLanguages) {
                    languages.add(language.toJson());
                }
                ObjectNode responseData = Json.newObject();
                responseData.put("languages", Json.toJson(languages));
                return ok(buildBasicResponse(0, "OK", responseData));
            } else {
                return notFound(buildBasicResponse(2, "no hay idiomas activos"));
            }
        } catch (Exception e) {
            Utils.printToLog(Clients.class, "Error manejando Idiomas", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando idiomas", e));
        }
    }

    public static Result getLeaderboardForClient(Integer id, Integer idTournament, Integer idPhase) {
        try {
            ObjectNode responseData = Json.newObject();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                int leaderboardSize = Config.getInt("leaderboard-size");

                List<Client> friends = null;
                String[] friendsArray = getFromQueryString("friends");
                if (friendsArray != null && friendsArray.length > 0) {
                    //   friends = Client.getFriends(friendsArray);
                }

                if (idPhase > 0) {
                    Leaderboard clientLeaderboard = null;
                    List<Leaderboard> leaderboards = null;
                    if (friends != null && !friends.isEmpty()) {
                        friends.add(client);
                        leaderboards = Leaderboard.finder.where().in("client", friends).eq("idTournament", idTournament).eq("idPhase", idPhase).orderBy("score desc").findList();
                    } else {
                        leaderboards = Leaderboard.finder.where().eq("idTournament", idTournament).eq("idPhase", idPhase).orderBy("score desc").findList();
                    }
                    clientLeaderboard = client.getLeaderboard(idTournament, idPhase);
                    if (leaderboards != null && !leaderboards.isEmpty()) {
                        int index = leaderboards.indexOf(clientLeaderboard);
                        ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                        leaderboardSize = leaderboardSize > leaderboards.size() ? leaderboards.size() : leaderboardSize;
                        for (int i = 0; i < leaderboardSize; ++i) {
                            leaderboardsJson.add(leaderboards.get(i).toJsonSimple());
                        }
                        ObjectNode clientLeaderboardJson = null;
                        if (clientLeaderboard != null) {
                            clientLeaderboardJson = clientLeaderboard.toJsonSimple();
                            clientLeaderboardJson.put("index", index);
                        } else {
                            String nickname = client.getNickname();
                            clientLeaderboardJson = Json.newObject();
                            clientLeaderboardJson.put("id_client", client.getIdClient());
                            clientLeaderboardJson.put("client", nickname == null ? "Anônimo" : nickname);
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
                    if (friends != null && !friends.isEmpty()) {
                        friends.add(client);
                        globalLeaderboards = LeaderboardGlobal.finder.where().in("client", friends).eq("idTournament", idTournament).orderBy("score desc").findList();
                    } else {
                        globalLeaderboards = LeaderboardGlobal.finder.where().eq("idTournament", idTournament).orderBy("score desc").findList();
                    }
                    clientLeaderboardGlobal = client.getLeaderboardGlobal(idTournament);
                    if (globalLeaderboards != null && !globalLeaderboards.isEmpty()) {
                        int index = globalLeaderboards.indexOf(clientLeaderboardGlobal);
                        ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                        leaderboardSize = leaderboardSize > globalLeaderboards.size() ? globalLeaderboards.size() : leaderboardSize;
                        for (int i = 0; i < leaderboardSize; ++i) {
                            leaderboardsJson.add(globalLeaderboards.get(i).toJsonSimple());
                        }
                        ObjectNode clientLeaderboardJson = null;
                        if (clientLeaderboardGlobal != null) {
                            clientLeaderboardJson = clientLeaderboardGlobal.toJsonSimple();
                            clientLeaderboardJson.put("index", index);
                        } else {
                            String nickname = client.getNickname();
                            clientLeaderboardJson = Json.newObject();
                            clientLeaderboardJson.put("id_client", client.getIdClient());
                            clientLeaderboardJson.put("client", nickname == null ? "Anônimo" : nickname);
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
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result getLeaderboardTotalForClient(Integer id) {
        try {
            ObjectNode responseData = Json.newObject();
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                int leaderboardSize = Config.getInt("leaderboard-size");

                List<Client> friends = null;
                String[] friendsArray = getFromQueryString("friends");
                if (friendsArray != null && friendsArray.length > 0) {
                    friends = Client.getFriends(friendsArray);
                }
                LeaderboardTotal clientLeaderboardTotal = null;
                List<LeaderboardTotal> totalLeaderboards = null;
                if (friends != null && !friends.isEmpty()) {
                    friends.add(client);
                    totalLeaderboards = LeaderboardTotal.finder.where().in("client", friends).orderBy("totalscore desc").findList();
                } else {
                    totalLeaderboards = LeaderboardTotal.finder.where().orderBy("totalscore desc").findList();
                }
                clientLeaderboardTotal = client.getLeaderboardTotal();
                if (totalLeaderboards != null && !totalLeaderboards.isEmpty()) {
                    int index = totalLeaderboards.indexOf(clientLeaderboardTotal);
                    ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                    leaderboardSize = leaderboardSize > totalLeaderboards.size() ? totalLeaderboards.size() : leaderboardSize;
                    int auux =0;
                    for (int i = 0; i < leaderboardSize; ++i) {
                        auux++;
                        leaderboardsJson.add(totalLeaderboards.get(i).toJsonSimple());
                    }
                    ObjectNode clientLeaderboardJson = null;

                    Long millis = Long.parseLong(Config.getString("lastmillis"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(millis);
                    calendar.add(Calendar.HOUR, -5);


                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH) +1;
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);



                    if (clientLeaderboardTotal != null) {
                        clientLeaderboardJson = clientLeaderboardTotal.toJsonSimple();
                        clientLeaderboardJson.put("index", index);
                    } else {
                        String nickname = client.getNickname();
                        clientLeaderboardJson = Json.newObject();
                        clientLeaderboardJson.put("id_client", client.getIdClient());
                        clientLeaderboardJson.put("client", nickname == null ? "Anônimo" : nickname);
                        clientLeaderboardJson.put("score", 0);
                        clientLeaderboardJson.put("hits", 0);
                        clientLeaderboardJson.put("index", totalLeaderboards.size());
                    }

                    clientLeaderboardJson.put("ddate",  mDay  + "/" + mMonth + "/" +  mYear );
                    clientLeaderboardJson.put("dhour", hour + ":" + (calendar.get(Calendar.MINUTE)<10? "0"+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE) ));
                     clientLeaderboardJson.put("auux", auux);

                    responseData.put("leaderboard", Json.toJson(leaderboardsJson));
                    responseData.put("client", clientLeaderboardJson);
                    return ok(buildBasicResponse(0, "OK", responseData));
                } else {
                    return ok(buildBasicResponse(3, "leaderboard vacio"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result getPersonalLeaderboardForClient(Integer id, Integer idTournament, final Integer idPhase, Boolean global) {
        try {
            //  FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                ArrayList<ObjectNode> leaderboardsJson = new ArrayList<>();
                if (global) {
                    List<LeaderboardGlobal> leaderboardGlobalList = client.getLeaderboardGlobal();
                    for (LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList) {
                        if(leaderboardGlobal.getScore() > 0 && leaderboardGlobal.getCorrectBets() > 0 )
                            leaderboardsJson.add(leaderboardGlobal.toJsonClean());
                    }
                } else {
                    List<Leaderboard> leaderboards = null;
                    ArrayList<ObjectNode> phasesJson = new ArrayList<>();
                    if (idTournament > 0) {
                        leaderboards = client.getLeaderboard(idTournament);
                        if (leaderboards != null && !leaderboards.isEmpty()) {
                            final String[] phasesArray = getFromQueryString("phases");
                            if (phasesArray != null && phasesArray.length > 0) {
                                Predicate<Leaderboard> validObjs = new Predicate<Leaderboard>() {
                                    public boolean apply(Leaderboard obj) {
                                        for (int i = 0; i < phasesArray.length; ++i) {
                                            if (obj.getIdPhase().intValue() == Integer.parseInt(phasesArray[i])) {
                                                return true;
                                            }
                                        }
                                        return false;
                                    }
                                };
                                leaderboards = (List<Leaderboard>) Utils.filterCollection(leaderboards, validObjs);
                            } else if (idPhase > 0) {
                                Predicate<Leaderboard> validObjs = new Predicate<Leaderboard>() {
                                    public boolean apply(Leaderboard obj) {
                                        return obj.getIdPhase().intValue() > idPhase;
                                    }
                                };
                                leaderboards = (List<Leaderboard>) Utils.filterCollection(leaderboards, validObjs);
                            }
                            for (Leaderboard leaderboard : leaderboards) {
                                phasesJson.add(leaderboard.toJsonClean());
                            }
                            ObjectNode tournament = Json.newObject();
                            tournament.put("id_tournament", idTournament);
                            tournament.put("phases", Json.toJson(phasesJson));
                            leaderboardsJson.add(tournament);
                        }
                    } else {
                        leaderboards = client.getLeaderboards();
                        if (leaderboards != null && !leaderboards.isEmpty()) {
                            int pivot = leaderboards.get(0).getIdTournament();
                            for (Leaderboard leaderboard : leaderboards) {
                                if (leaderboard.getIdTournament() == pivot) {
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
                            if (!leaderboardsJson.isEmpty()) {
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
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error obteniendo los idiomas activos ", true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result dashboard(Integer id, Integer idLanguage) {
        try {
            String[] timezoneNames = getFromQueryString("timezoneName");
            String timezoneName = null;
            if (timezoneNames == null || timezoneNames.length <= 0) {
                //resturar cuando se cambie la version que no manda el timezone (HOY ES 27/07/2015, avisenle a iñaki cuando quiten esto. mi apuesta es que nadie se dara cuenta)
//                return badRequest(buildBasicResponse(1, "Falta el parametro timezonName"));
            } else {
                timezoneName = timezoneNames[0].replaceAll(" ", "").trim();
            }
            // FootballClient client = (FootballClient) Client.getByID(id);
            FootballClient client = new FootballClient(Client.getByID(id));
            if (client != null) {
                String[] favorites = getFromQueryString("teams");
                StringBuilder teamsBuilder = new StringBuilder();
                if (favorites != null && favorites.length > 0) {
                    for (String team : favorites) {
                        teamsBuilder.append("&teams=").append(team);
                    }
                }
                String teams = "http://" + Utils.getFootballManagerHost() + "/footballapi/v1/competitions/list/" + Config.getInt("football-manager-id-app") + "/" + idLanguage + "?closestMatch=true" + (timezoneName != null ? "&timezoneName=" + timezoneName : "") + (teamsBuilder.length() > 0 ? teamsBuilder.toString() : "");
                F.Promise<WSResponse> result = WS.url(teams.toString()).get();
                ObjectNode footballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                int error = footballResponse.get("error").asInt();

                teams = "http://" + Utils.getBaseBallManagerHost() + "/baseballapi/v1/competitions/list?closestMatch=true" + (timezoneName != null ? "&timezoneName=" + timezoneName : "") + (teamsBuilder.length() > 0 ? teamsBuilder.toString() : "");
                result = WS.url(teams.toString()).get();
                ObjectNode baseballResponse = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
                error = baseballResponse.get("error").asInt();

                if (error == 0) {
                    ObjectNode response = Json.newObject();
                    JsonNode data = footballResponse.get("response");

                    JsonNode aux = data.get("competitions");
                    JsonNode aux2 = baseballResponse.get("response").get("competitions");
                    ObjectNode tmp = Json.newObject();
                    tmp.put("competitions_football", aux);
                    tmp.put("competitions_baseball", aux2);

                    int points = 0;
                    int correct = 0;
                    if (client.getLeaderboardTotal() != null) {
                        points = client.getLeaderboardTotal().getScore() + client.getLeaderboardTotal().getSmsscore();
                        correct = client.getLeaderboardTotal().getCorrectBets();
                    } else {
                        List<LeaderboardGlobal> leaderboardGlobalList = client.getLeaderboardGlobal();
                        for (LeaderboardGlobal leaderboardGlobal : leaderboardGlobalList) {
                            points += leaderboardGlobal.getScore();
                            correct += leaderboardGlobal.getCorrectBets(); 
                        }
                    }


                    //Job job = Job.getByID(7l);
                    Long millis = Long.parseLong(Config.getString("lastmillis"));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(millis);
                    calendar.add(Calendar.HOUR, -5);

                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH) +1;
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);


                    response.put("points", points);
                    response.put("ddate",  mDay  + "/" + mMonth + "/" +  mYear );
                    response.put("dhour", hour + ":" + (calendar.get(Calendar.MINUTE)<10? "0"+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE) ));
                    response.put("correct_bets", correct);

                    if(getFromQueryString("new") != null)
                        response.put("competitions", tmp);
                    else
                        response.put("competitions", aux);


                    return ok(buildBasicResponse(0, "OK", response));
                } else {
                    return internalServerError(buildBasicResponse(3, "error llamando a footballmanager"));
                }
            } else {
                return notFound(buildBasicResponse(2, "no existe el cliente " + id));
            }
        } catch (Exception e) {
            Utils.printToLog(FootballClients.class, "Error manejando clients", "error creando clientbets para el client " + id, true, e, "support-level-1", Config.LOGGER_ERROR);
            return internalServerError(buildBasicResponse(1, "Error buscando el registro", e));
        }
    }

    public static Result generatePin(String msisdn) {
        if (!msisdn.isEmpty()) {
            Client client = Client.getByLogin(msisdn);
            boolean isGenerated = false;
            if (client == null) {
                SilverAPI.GetPin(msisdn);
                isGenerated = true;
            } else {
                if (client.getStatus() < 0) {
                    SilverAPI.GetPin(msisdn);
                    isGenerated = true;
                } else {
                    Upstream.EventKraken(msisdn);
                }
            }
            ObjectNode response = Json.newObject();
            response.put("isGen", isGenerated);
            return ok(buildBasicResponse(0, "OK", response));
        } else {
            return badRequest(buildBasicResponse(1, "Error el dato no puede ser vacio"));
        }
    }

    public static Result verifyPin(String msisdn, String pin) {
        if (!msisdn.isEmpty() && !pin.isEmpty()) {
            boolean isPinConfirmed = SilverAPI.confirmPin(msisdn, pin);
            if (isPinConfirmed) {
                FootballClient.createMasClient(msisdn, pin, "");
                SilverAPI.broadcastEvent(msisdn, pin);// this call should be asyncronous
            }
            ObjectNode response = Json.newObject();
            response.put("isCheck", isPinConfirmed);
            return ok(buildBasicResponse(0, "OK", response));
        } else {
            return badRequest(buildBasicResponse(1, "Error los datos no pueden ser vacios"));
        }
    }

    public static Result broadcastEventReturn(String msisdn, String pin, String serviceId) {
        if (!msisdn.isEmpty() && !pin.isEmpty() && !serviceId.isEmpty()) {
            FootballClient.createMasClient(msisdn, pin, serviceId);
            return ok(buildBasicResponse(0, "OK"));
        } else {
            return badRequest(buildBasicResponse(1, "Error los datos nos pueden ser vacios"));
        }
    }

}
