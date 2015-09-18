package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plesse on 2/10/15.
 */

@Entity
@Table(name="game_match_status_has_localization")
public class GameMatchStatusHasLocalization extends FootballModel {

    @Id
    private Integer idGameMatchStatusHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_game_match_status")
    private GameMatchStatus status;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    @Constraints.Required
    private String name;

    private static Model.Finder<Integer, GameMatchStatusHasLocalization> finder = new Model.Finder<Integer, GameMatchStatusHasLocalization>(Integer.class, GameMatchStatusHasLocalization.class);

    public GameMatchStatusHasLocalization(GameMatchStatus status, Language language, String name) {
        this.status = status;
        this.language = language;
        this.name = name;
    }

    public Integer getIdGameMatchStatusHasLocalization() {
        return idGameMatchStatusHasLocalization;
    }

    public void setIdGameMatchStatusHasLocalization(Integer idGameMatchStatusHasLocalization) {
        this.idGameMatchStatusHasLocalization = idGameMatchStatusHasLocalization;
    }

    public GameMatchStatus getStatus() {
        return status;
    }

    public void setStatus(GameMatchStatus status) {
        this.status = status;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("language", language.toJson());
        node.put("name", name);
        return node;
    }

    public static boolean exists(GameMatchStatusHasLocalization gameMatchStatusHasLocalization){
        GameMatchStatusHasLocalization unique = finder.where().eq("status", gameMatchStatusHasLocalization.getStatus()).eq("language", gameMatchStatusHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(gameMatchStatusHasLocalization.getName())){
            unique.setName(gameMatchStatusHasLocalization.getName());
            unique.update();
        }
        return exists;
    }
}
