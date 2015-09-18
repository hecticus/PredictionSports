package job;

import akka.actor.Cancellable;
import backend.HecticusThread;
import com.avaje.ebean.PagingList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.basic.Action;
import models.Config;
import models.clients.Client;
import models.leaderboard.LeaderboardPush;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import utils.Utils;

import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by plesse on 5/6/15.
 */
public class Notificator  extends HecticusThread {

    public Notificator() {
        long start = System.currentTimeMillis();
        setName("Notificator-"+start);
        setInitTime(start);
        setActTime(start);
        setPrevTime(start);
    }

    public Notificator(String name, AtomicBoolean run, Cancellable cancellable) {
        super("Notificator-"+name, run, cancellable);
    }

    public Notificator(String name, AtomicBoolean run) {
        super("Notificator-"+name, run);
    }

    public Notificator(AtomicBoolean run) {
        super("Notificator",run);
    }

    @Override
    public void process(Map args) {
        try {
            Utils.printToLog(Notificator.class, null, "Iniciando Notificator", false, null, "support-level-1", Config.LOGGER_INFO);
            int pmcIdApp = Config.getInt("pmc-id-app");
            int idAction = Integer.parseInt(""+args.get("id_action"));
            int pageSize = Integer.parseInt(""+args.get("page_size"));
            processPushEvents(pmcIdApp, pageSize, idAction);
            Utils.printToLog(Notificator.class, null, "Terminando Notificator", false, null, "support-level-1", Config.LOGGER_INFO);
        } catch (Exception ex) {
            Utils.printToLog(Notificator.class, "Error calculando leadeboards", "Error calculando leadeboards", true, ex, "support-level-1", Config.LOGGER_ERROR);
        }
    }

    private void processPushEvents(int pmcIdApp, int pageSize, int idAction){
        Client client = null;
        PagingList<LeaderboardPush> pushPager = LeaderboardPush.finder.where().orderBy("score asc").findPagingList(pageSize);
        int totalPageCount = pushPager.getTotalPageCount();
        if(totalPageCount > 0) {
            Map<Integer, ArrayList<Integer>> pointClients = new HashMap<>();
            for (int i = 0;isAlive() && i < totalPageCount; i++) {
                List<LeaderboardPush> pushPage = pushPager.getPage(i).getList();
                for(LeaderboardPush leaderboardPush : pushPage){
                    isAlive();
                    int points = leaderboardPush.getScore();
                    client = leaderboardPush.getClient();
                    boolean notified = true;
                    if(client.getStatus() == 1){
                        notified = notifyPointsToUpstream(client, points);
                    }
                    if(notified) {
                        if (pointClients.containsKey(points)) {
                            pointClients.get(points).add(client.getIdClient());
                        } else {
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(client.getIdClient());
                            pointClients.put(points, temp);
                        }
                        leaderboardPush.delete();
                    }
                }

                ObjectNode event = Json.newObject();
                ObjectNode extraParams = Json.newObject();
                Set<Integer> points = pointClients.keySet();
                for(int point : points){
                    isAlive();
                    try {
                        String msg = resolvePointsMessage(point, idAction);
                        event.put("msg", URLEncoder.encode(msg, "UTF-8"));
                        event.put("clients", Json.toJson(pointClients.get(point)));
                        event.put("app", pmcIdApp);
                        extraParams.put("is_news", false);
                        extraParams.put("is_info", true);
                        event.put("extra_params", extraParams);
                        sendPush(event);
                    } catch (Exception e){
                        Utils.printToLog(Notificator.class, "Error creando push de finalizacion de phase", "Error creando push de finalizacion de phase", false, e, "support-level-1", Config.LOGGER_ERROR);
                    } finally {
                        event.removeAll();
                        extraParams.removeAll();
                    }
                }
                pointClients.clear();
            }
        }
    }

    private boolean notifyPointsToUpstream(Client client, int points) {
        try {
            ObjectNode event = Json.newObject();
            ObjectNode metadata = Json.newObject();
            ArrayList<ObjectNode> pointsList = new ArrayList<>(1);
            ObjectNode actualPoints = Json.newObject();
            actualPoints.put("type", "experience");
            actualPoints.put("value", points);
            pointsList.add(actualPoints);
            metadata.put("result", "win");
            metadata.put("points", Json.toJson(pointsList));
            event.put("event_type", "UPD_POINTS");
            event.put("metadata", metadata);
            F.Promise<WSResponse> result = WS.url("http://" + Config.getHost() + "/sportsapi/v2/client/" + client.getIdClient() + "/upstream").post(event);
            ObjectNode response = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
            return response.get("error").asInt() == 0;
        } catch (Exception e){
            Utils.printToLog(Notificator.class, null, "Error enviando notificacion a upstrem", false, e, "support-level-1", Config.LOGGER_ERROR);
            return false;
        }
    }

    private String resolvePointsMessage(int point, int idAction) {
        Action action = Action.finder.where().eq("idAction", idAction).findUnique();
        String msg = action.getMsg();
        msg = msg.replaceFirst("%POINTS%", ""+point);
        return msg;
    }

    private void sendPush(ObjectNode event) {
        F.Promise<WSResponse> result = WS.url("http://" + Config.getPMCHost() + "/events/v1/insert").post(event);
        ObjectNode response = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
    }
}
