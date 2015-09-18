package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.FootballModel;
import models.Language;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by karina on 5/20/14.
 */
@Entity
@Table(name="actions", uniqueConstraints = @UniqueConstraint(columnNames = {"ext_id"}))
public class Action extends FootballModel {

    @Id
    private Integer idActions;
    private String mnemonic;
    private String description;
    private String extId;
    //private Integer ext_id;
    @OneToMany(mappedBy = "action")
    private List<GameMatchEvent> events;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL)
    private List<ActionHasLocalization> localizations;

    private static Model.Finder<Integer,Action> finder = new Model.Finder<Integer,Action>(Integer.class,Action.class);

    public Action(){}

    public Action(String mnemonic, String description, String extId) {
        this.mnemonic = mnemonic;
        this.description = description;
        this.extId = extId;
    }

    public Integer getIdActions() {
        return idActions;
    }

    public void setIdActions(Integer idActions) {
        this.idActions = idActions;
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

    public static Action findById(Integer id){
        return finder.byId(id);
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public List<GameMatchEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GameMatchEvent> events) {
        this.events = events;
    }

    public static Action findByMnemonic(String mnemonic){
        return  finder.where().eq("mnemonic",mnemonic).findUnique();
    }

    public static Action findByExtId(String extId){
        return  finder.where().eq("extId",extId).findUnique();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("id_action",idActions);
        node.put("mnemonic",mnemonic);
        node.put("description",description);
        return node;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage) {
        ObjectNode node = Json.newObject();
        node.put("id_action",idActions);
        ActionHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<ActionHasLocalization>() {
                public boolean apply(ActionHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<ActionHasLocalization>() {
                    public boolean apply(ActionHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        node.put("mnemonic", clientLanguage!=null?clientLanguage.getMnemonic():mnemonic);
        node.put("description", clientLanguage!=null?clientLanguage.getDescription():description);
        return node;
    }

    public void validate(Language language) {
        Action tr = findByExtId(this.extId);
        if (tr != null) {
            //existe
            this.idActions = tr.idActions;
            this.description = tr.description;
            this.mnemonic = tr.mnemonic;
            this.extId = tr.extId;
            this.localizations = tr.localizations;
        } else {
            this.save();
        }
        ActionHasLocalization actionHasLocalization = new ActionHasLocalization(this, language, this.mnemonic, this.description);
        if(!ActionHasLocalization.exists(actionHasLocalization)){
            this.localizations.add(actionHasLocalization);
            actionHasLocalization.save();
            this.update();
        }
    }

}
