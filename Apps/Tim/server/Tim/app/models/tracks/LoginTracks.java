package models.tracks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by johanna on 3/11/16.
 */

@Entity
@Table(name="login_tracks")
public class LoginTracks extends HecticusModel{
    @Id
    private Integer idLoginTracks;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "log")
    private String log;

    //New IP Addresss column.
    @Column
    private String remote_ip;

    //New type column. 1:Tipo de login para usuarios guest 2:Tipo de login para usuarios registrados
    @Column
    private int type;


    public static Model.Finder<Integer, LoginTracks> finder = new Model.Finder<>(Integer.class, LoginTracks.class);

    public LoginTracks(Client client) {
        this.client = client;
    }

    /**
     * This method stores the client data on the 'tim_sports'.'login_tracks' db table...
     *
     * @param log: The JSON coming from the front-end.
     * @param client: The client object. Used for the client_id.
     * @param remote_ip: The IP from the client making the request.
     * @param type: Flag.1: guest 2: registered 3: n/a.
     */
    public LoginTracks(String log, Client client, String remote_ip, int type) {
        this.log = log;
        this.client = client;
        this.remote_ip = remote_ip;
        this.type = type;
    }

    /**
     * This stores the client by idLoginTracks
     */
    public LoginTracks(Integer idLoginTracks, Client client, String log) {
        this.idLoginTracks = idLoginTracks;
        this.client = client;
        this.log = log;
    }

    /**
     * This method stores the client data on the 'tim_sports'.'login_tracks' db table...
     * It will also transform the JSON to be logged so it only haves one device.
     * This will put the devices node in the last position...
     *
     * @param log: The JSON coming from the front-end.
     * @param client: The client object. Used for the client_id.
     * @param remote_ip: The IP from the client making the request.
     * @param type: Flag.1: guest 2: registered 3: n/a.
     * @param numDevices: The number of devices to leave on the JSON to log.
     */
    public LoginTracks(String log, Client client, String remote_ip, int type, int numDevices) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode new_log = null;
        try {
            //Read the Log String as JSON and select node "devices"
            new_log = mapper.readTree(log);
            JsonNode devices_node = new_log.get("devices");
            List<ObjectNode> Devices = new ArrayList<ObjectNode>();

            //Loop the Node Devices to fill the "Devices" List.
            for (JsonNode Device : devices_node) {
                ObjectNode object = (ObjectNode) Device;
                Devices.add(object);
            }

            //Limit the list to use the last numDevices entries.
            List<ObjectNode> LimitedDevices = Devices.subList(Devices.size()-numDevices, Devices.size());


            //Create a new ObjectNode that can be modified and returned.
            ObjectNode trimmed_log = (ObjectNode) new_log;
            trimmed_log.remove("devices");

            //Delete unwanted nodes
            trimmed_log.remove("push_alerts_teams");
            trimmed_log.remove("push_alerts_info");
            trimmed_log.remove("leaderboards");
            trimmed_log.remove("leaderboard_global");

            //Finally, create the new node.
            trimmed_log.put("devices", LimitedDevices.toString());
            String final_log = trimmed_log.toString().replace("\\","").replace("\"[{","[{").replace("}]\"","}]");

            this.log = final_log;

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.client = client;
        this.remote_ip = remote_ip;
        this.type = type;
    }

    public Integer getIdLoginTracks() {
        return idLoginTracks;
    }
    public void setIdLoginTracks(Integer idLoginTracks) {
        this.idLoginTracks = idLoginTracks;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public String getLog() {
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }

    public String getRemote_ip() {
        return remote_ip;
    }
    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public static Model.Finder<Integer, LoginTracks> getFinder() {
        return finder;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_login_tracks", idLoginTracks);
        response.put("id_client", client.toJsonWithoutRelations());
        response.put("log", log);
        response.put("remote_ip", remote_ip);
        response.put("type", type);
        System.out.println("response: : " + response);
        return response;
    }


}
