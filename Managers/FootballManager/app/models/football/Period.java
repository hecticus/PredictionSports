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
@Table(name="periods", uniqueConstraints = @UniqueConstraint(columnNames = {"name","short_name"}))
public class Period extends FootballModel {
    @Id
    private Integer idPeriods;
    private String name;
    private String shortName;
    private Long extId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "period", cascade = CascadeType.ALL)
    private List<GameMatchEvent> events;

    @OneToMany(mappedBy = "period", cascade = CascadeType.ALL)
    private List<PeriodHasLocalization> localizations;

    private static Model.Finder<Integer,Period> finder = new Model.Finder<Integer,Period>(Integer.class,Period.class);
    public Period(){}

    public Period(String name, String shortName, Long extId) {
        this.name = name;
        this.shortName = shortName;
        this.extId = extId;
    }

    public Integer getIdPeriods() {
        return idPeriods;
    }

    public void setIdPeriods(Integer idPeriods) {
        this.idPeriods = idPeriods;
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

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public static Period findById(Integer id){
        return finder.byId(id);
    }

    public static Period findByShotName(String sn){
        return finder.where().eq("shortName",sn).findUnique();
    }

    public List<GameMatchEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GameMatchEvent> events) {
        this.events = events;
    }

    public List<PeriodHasLocalization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<PeriodHasLocalization> localizations) {
        this.localizations = localizations;
    }

    public void validate(Language language) {
        Period tr = findByShotName(this.shortName);
        if (tr != null) {
            //existe
            this.idPeriods = tr.idPeriods;
            this.name = tr.name;
            this.shortName = tr.shortName;
            this.extId = tr.extId;
        } else {
            this.save();
        }
        PeriodHasLocalization periodHasLocalization = new PeriodHasLocalization(this, language, this.name, this.shortName);
        if(!PeriodHasLocalization.exists(periodHasLocalization)){
            //System.out.println("no existe " + this.getName() + " " + language.getName());
            this.localizations.add(periodHasLocalization);
            periodHasLocalization.save();
            this.update();
        }
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_periods",idPeriods);
        obj.put("name",name);
        obj.put("short_name",shortName);
        return obj;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_periods", idPeriods);
        PeriodHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<PeriodHasLocalization>() {
                public boolean apply(PeriodHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<PeriodHasLocalization>() {
                    public boolean apply(PeriodHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("short_name",clientLanguage!=null?clientLanguage.getShortName():shortName);
        return obj;
    }

}
