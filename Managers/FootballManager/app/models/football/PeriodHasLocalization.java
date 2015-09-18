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
@Table(name="period_has_localization")
public class PeriodHasLocalization extends FootballModel {

    @Id
    private Integer idPeriodHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_period")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    @Constraints.Required
    private String name;

    private String shortName;

    private static Model.Finder<Integer, PeriodHasLocalization> finder = new Model.Finder<Integer, PeriodHasLocalization>(Integer.class, PeriodHasLocalization.class);

    public PeriodHasLocalization(Period period, Language language, String name, String shortName) {
        this.period = period;
        this.language = language;
        this.name = name;
        this.shortName = shortName;
    }

    public Integer getIdPeriodHasLocalization() {
        return idPeriodHasLocalization;
    }

    public void setIdPeriodHasLocalization(Integer idPeriodHasLocalization) {
        this.idPeriodHasLocalization = idPeriodHasLocalization;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("language", language.toJson());
        node.put("name", name);
        node.put("short_name", shortName);
        return node;
    }

    public static boolean exists(PeriodHasLocalization periodHasLocalization){
        PeriodHasLocalization unique = finder.where().eq("period", periodHasLocalization.getPeriod()).eq("language", periodHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(periodHasLocalization.getName())){
            unique.setName(periodHasLocalization.getName());
            unique.update();
        }
        if(exists && !unique.getShortName().equalsIgnoreCase(periodHasLocalization.getShortName())){
            unique.setShortName(periodHasLocalization.getShortName());
            unique.update();
        }
        return exists;
    }
}
