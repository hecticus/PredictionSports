package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plesse on 2/11/15.
 */
@Entity
@Table(name="action_has_localization")
public class ActionHasLocalization extends FootballModel {

    @Id
    private Integer idActionHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_action")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    private String mnemonic;

    private String description;

    private static Model.Finder<Integer, ActionHasLocalization> finder = new Model.Finder<Integer, ActionHasLocalization>(Integer.class, ActionHasLocalization.class);

    public ActionHasLocalization(Action action, Language language, String mnemonic, String description) {
        this.action = action;
        this.language = language;
        this.mnemonic = mnemonic;
        this.description = description;
    }

    public Integer getIdActionHasLocalization() {
        return idActionHasLocalization;
    }

    public void setIdActionHasLocalization(Integer idActionHasLocalization) {
        this.idActionHasLocalization = idActionHasLocalization;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("language", language.toJson());
        node.put("mnemonic", mnemonic);
        node.put("description", description);
        return node;
    }

    public static boolean exists(ActionHasLocalization actionHasLocalization){
        ActionHasLocalization unique = finder.where().eq("action", actionHasLocalization.getAction()).eq("language", actionHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getMnemonic().equalsIgnoreCase(actionHasLocalization.getMnemonic())){
            unique.setMnemonic(actionHasLocalization.getMnemonic());
            unique.update();
        }
        if(exists && !unique.getDescription().equalsIgnoreCase(actionHasLocalization.getDescription())){
            unique.setDescription(actionHasLocalization.getDescription());
            unique.update();
        }
        return exists;
    }
}
