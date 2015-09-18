package job;

import akka.actor.Cancellable;
import backend.HecticusThread;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import models.pushalerts.PushAlerts;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by plesse on 5/4/15.
 */
public class TeamsSynchronizer extends HecticusThread {

    public static final String ENCODING = "UTF-8";

    public TeamsSynchronizer() {
        long start = System.currentTimeMillis();
        setName("TeamsSynchronizer-"+start);
        setInitTime(start);
        setActTime(start);
        setPrevTime(start);
    }

    public TeamsSynchronizer(String name, AtomicBoolean run, Cancellable cancellable) {
        super("TeamsSynchronizer-"+name, run, cancellable);
    }

    public TeamsSynchronizer(String name, AtomicBoolean run) {
        super("TeamsSynchronizer-"+name, run);
    }

    public TeamsSynchronizer(AtomicBoolean run) {
        super("TeamsSynchronizer",run);
    }

    @Override
    public void process(Map args) {
        try {
            PushAlerts lastTeamAlert = PushAlerts.getLastTeamAlert();
            int id = lastTeamAlert != null ? lastTeamAlert.getIdExt() : 0;
            synchTeams(id);
        } catch (Exception ex) {
            Utils.printToLog(TeamsSynchronizer.class, "Error sincronizando equipos", "Error sincronizando equipos", true, ex, "support-level-1", Config.LOGGER_ERROR);
        }
    }

    private void synchTeams(int idExt) throws UnsupportedEncodingException {
        F.Promise<WSResponse> result = WS.url("http://" + Utils.getFootballManagerHost() + "/footballapi/v2/teams/" + idExt).get();
        ObjectNode response = (ObjectNode) result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
        JsonNode data = null;
        int error = response.get("error").asInt();
        if(error == 0) {
            data = response.get("response");
            if(data.has("teams")){
                Iterator<JsonNode> teams = data.get("teams").elements();
                while (isAlive() && teams.hasNext()){
                    JsonNode next = teams.next();
                    String officialName = null,
                            shortName = null,
                            abbreviationName = null,
                            teamLogo = null;
                    try {
                        if (next.hasNonNull("officialName"))
                            officialName = URLEncoder.encode(next.get("officialName").asText(), ENCODING);
                        if (next.hasNonNull("short_name"))
                            shortName = URLEncoder.encode(next.get("short_name").asText(), ENCODING);
                        if (next.hasNonNull("abbreviation_name"))
                            abbreviationName = URLEncoder.encode(next.get("abbreviation_name").asText(), ENCODING);
                        if (next.hasNonNull("team_logo"))
                            teamLogo = next.get("team_logo").asText();
                    }catch (Exception ex){
                        //failed do nothing
                    }
                    PushAlerts pushAlerts = new PushAlerts(URLEncoder.encode(next.get("name").asText(), "UTF-8"),
                            next.get("id_teams").asInt(),
                            true,
                            officialName,
                            shortName,
                            abbreviationName,
                            teamLogo);
                    pushAlerts.save();
                }
            }
        }
    }

}
