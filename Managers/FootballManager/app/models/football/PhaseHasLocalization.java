package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plesse on 2/11/15.
 */
@Entity
@Table(name="phase_has_localization")
public class PhaseHasLocalization extends FootballModel {

    @Id
    private Integer idPhaseHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_phase")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    @Constraints.Required
    private String globalName;

    private String name;

    private static Model.Finder<Integer, PhaseHasLocalization> finder = new Model.Finder<Integer, PhaseHasLocalization>(Integer.class, PhaseHasLocalization.class);

    public PhaseHasLocalization(Phase phase, Language language, String globalName, String name) {
        this.phase = phase;
        this.language = language;
        this.globalName = globalName;
        this.name = name;
    }

    public Integer getIdPhaseHasLocalization() {
        return idPhaseHasLocalization;
    }

    public void setIdPhaseHasLocalization(Integer idPhaseHasLocalization) {
        this.idPhaseHasLocalization = idPhaseHasLocalization;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getGlobalName() {
        return globalName;
    }

    public void setGlobalName(String globalName) {
        this.globalName = globalName;
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
        node.put("global_name", globalName);
        return node;
    }

    public static boolean exists(PhaseHasLocalization phaseHasLocalization){
        PhaseHasLocalization unique = finder.where().eq("phase", phaseHasLocalization.getPhase()).eq("language", phaseHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(phaseHasLocalization.getName())){
            unique.setName(phaseHasLocalization.getName());
            unique.update();
        }
        if(exists && !unique.getGlobalName().equalsIgnoreCase(phaseHasLocalization.getGlobalName())){
            unique.setGlobalName(phaseHasLocalization.getGlobalName());
            unique.update();
        }
        return exists;
    }
}
