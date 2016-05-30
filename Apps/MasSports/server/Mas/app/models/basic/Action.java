package models.basic;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plesse on 1/29/15.
 */
@Entity
@Table(name="actions")
public class Action extends HecticusModel {


    @Id
    private Integer idAction;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private String msg;
    @Constraints.Required
    private Integer extId;

    @OneToOne
    @JoinColumn(name = "id_language")
    @Constraints.Required
    private Language language;

    public static Model.Finder<Integer, Action> finder = new Model.Finder<Integer, Action>(Integer.class, Action.class);

    public Action(String name, String msg, Integer extId) {
        this.name = name;
        this.msg = msg;
        this.extId = extId;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_action", idAction);
        response.put("name", name);
        response.put("msg", msg);
        response.put("extId", extId);
        return response;
    }
}
