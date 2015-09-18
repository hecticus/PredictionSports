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
@Table(name="competition_has_localization")
public class CompetitionHasLocalization extends FootballModel {

    @Id
    private Integer idCompetitionHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    private String name;


    private static Model.Finder<Integer, CompetitionHasLocalization> finder = new Model.Finder<Integer, CompetitionHasLocalization>(Integer.class, CompetitionHasLocalization.class);

    public CompetitionHasLocalization(Competition competition, Language language, String name) {
        this.competition = competition;
        this.language = language;
        this.name = name;
    }

    public Integer getIdCompetitionHasLocalization() {
        return idCompetitionHasLocalization;
    }

    public void setIdCompetitionHasLocalization(Integer idCompetitionHasLocalization) {
        this.idCompetitionHasLocalization = idCompetitionHasLocalization;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
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

    public static boolean exists(CompetitionHasLocalization competitionHasLocalization){
        CompetitionHasLocalization unique = finder.where().eq("competition", competitionHasLocalization.getCompetition()).eq("language", competitionHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(competitionHasLocalization.getName())){
            unique.setName(competitionHasLocalization.getName());
            unique.update();
        }
        return exists;
    }
}
