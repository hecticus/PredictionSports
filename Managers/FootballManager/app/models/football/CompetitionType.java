package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.Config;
import models.FootballModel;
import models.Language;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by sorcerer on 12/1/14.
 */
@Entity
@Table(name="competition_type", uniqueConstraints = @UniqueConstraint(columnNames = {"ext_id"}))
public class CompetitionType extends FootballModel {

    //constantes de type

    private static final int TYPE_TABLA = 0;
    private static final int TYPE_LLAVE = 1;

    //atributos
    @Id
    private Integer idCompType;
    private Integer status;
    private String name;
    private Integer type;
    private Long extId;
    private String compLogo;

    private int sort;

    @OneToMany(mappedBy="type", cascade = CascadeType.ALL)
    private List<Competition> competitions;

    @OneToMany(mappedBy = "competitionType", cascade = CascadeType.ALL)
    private List<CompetitionTypeHasLocalization> localizations;

    private static Model.Finder<Long,CompetitionType> finder = new Model.Finder<Long, CompetitionType>(Long.class, CompetitionType.class);

    public CompetitionType(String name, Long extId) {
        this.status = status; //1
        this.name = name;
        this.type = TYPE_TABLA;
        this.extId = extId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public Integer getIdCompType() {
        return idCompType;
    }

    public void setIdCompType(Integer idCompType) {
        this.idCompType = idCompType;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCompLogo() {
        return compLogo;
    }

    public void setCompLogo(String compLogo) {
        this.compLogo = compLogo;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_competition_type",idCompType);
        obj.put("status",status);
        obj.put("name",name);
        obj.put("type", type);
        obj.put("ext_id", extId);
        obj.put("competition_logo", Config.getString("competition-logo-url") + compLogo);
        obj.put("sort", sort);
        return obj;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_competition_type",idCompType);
        obj.put("status",status);
        CompetitionTypeHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<CompetitionTypeHasLocalization>() {
                public boolean apply(CompetitionTypeHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<CompetitionTypeHasLocalization>() {
                    public boolean apply(CompetitionTypeHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("type", type);
        obj.put("ext_id", extId);
        obj.put("competition_logo", Config.getString("competition-logo-url") + compLogo);
        obj.put("sort", sort);
        return obj;
    }

    public static CompetitionType getCompType(long extId){
        return finder.where().eq("ext_id",extId).findUnique();
    }

    public void validate(Language language){
        CompetitionType tr = getCompType(this.extId);
        if (tr != null){
            this.idCompType = tr.idCompType;
            this.status = tr.status;
            this.name = tr.name;
            this.type = tr.type;
            this.extId = tr.extId;
            this.localizations = tr.localizations;
        }else {
            this.save();
        }

        CompetitionTypeHasLocalization competitionTypeHasLocalization = new CompetitionTypeHasLocalization(this, language, this.name);
        if(!CompetitionTypeHasLocalization.exists(competitionTypeHasLocalization)){
            this.localizations.add(competitionTypeHasLocalization);
            competitionTypeHasLocalization.save();
            this.update();
        }
    }

    public static List<CompetitionType> getAll(){
        return finder.all();
    }
}
