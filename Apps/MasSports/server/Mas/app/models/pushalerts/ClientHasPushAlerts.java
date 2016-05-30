package models.pushalerts;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by chrirod on 10/29/14.
 */
@Entity
@Table(name="client_has_push_alerts")
public class ClientHasPushAlerts extends HecticusModel {
    @Id
    private Integer idClientHasPushAlert;
    @Constraints.Required
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_push_alert")
    private PushAlerts pushAlert;

    private boolean status;

    public static Model.Finder<Integer, ClientHasPushAlerts> finder = new Model.Finder<Integer, ClientHasPushAlerts>(Integer.class, ClientHasPushAlerts.class);

    public ClientHasPushAlerts(Client client, PushAlerts pushAlert) {
        this.client = client;
        this.pushAlert = pushAlert;
        this.status = true;
    }

    public Integer getIdClientHasPushAlert() {
        return idClientHasPushAlert;
    }

    public void setIdClientHasPushAlert(Integer idClientHasPushAlert) {
        this.idClientHasPushAlert = idClientHasPushAlert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PushAlerts getPushAlert() {
        return pushAlert;
    }

    public void setPushAlert(PushAlerts pushAlert) {
        this.pushAlert = pushAlert;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_client_has_push_alerts", idClientHasPushAlert);
        response.put("push_alert", pushAlert.toJson());
        response.put("status", status);
        return response;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode response = Json.newObject();
        response.put("push_alert", pushAlert.toJson());
        response.put("status", status);
        return response;
    }
}
