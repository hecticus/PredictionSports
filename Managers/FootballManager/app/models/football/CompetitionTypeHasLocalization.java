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
@Table(name="competition_type_has_localization")
public class CompetitionTypeHasLocalization extends FootballModel {

    @Id
    private Integer idCompetitionTypeHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_competition_type")
    private CompetitionType competitionType;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    private String name;

    private static Model.Finder<Integer, CompetitionTypeHasLocalization> finder = new Model.Finder<Integer, CompetitionTypeHasLocalization>(Integer.class, CompetitionTypeHasLocalization.class);

    public CompetitionTypeHasLocalization(CompetitionType competitionType, Language language, String name) {
        this.competitionType = competitionType;
        this.language = language;
        this.name = name;
    }

    public Integer getIdCompetitionTypeHasLocalization() {
        return idCompetitionTypeHasLocalization;
    }

    public void setIdCompetitionTypeHasLocalization(Integer idCompetitionTypeHasLocalization) {
        this.idCompetitionTypeHasLocalization = idCompetitionTypeHasLocalization;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
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

    public static boolean exists(CompetitionTypeHasLocalization competitionTypeHasLocalization){
        CompetitionTypeHasLocalization unique = finder.where().eq("competitionType", competitionTypeHasLocalization.getCompetitionType()).eq("language", competitionTypeHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(competitionTypeHasLocalization.getName())){
            unique.setName(competitionTypeHasLocalization.getName());
            unique.update();
        }
        return exists;
    }
}
