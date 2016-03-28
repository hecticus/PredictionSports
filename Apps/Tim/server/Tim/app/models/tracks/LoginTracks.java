package models.tracks;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.Date;

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


    public static Model.Finder<Integer, LoginTracks> finder = new Model.Finder<>(Integer.class, LoginTracks.class);

    public LoginTracks(Client client) {
        this.client = client;
    }

    public LoginTracks(String log, Client client, String remote_ip) {
        this.log = log;
        this.client = client;
        this.remote_ip = remote_ip;
    }

    public LoginTracks(Integer idLoginTracks, Client client, String log) {
        this.idLoginTracks = idLoginTracks;
        this.client = client;
        this.log = log;
    }

    public String getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
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
        return response;
    }


}
