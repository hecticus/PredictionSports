package models;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.football.GameMatchStatusHasLocalization;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.mutable.Buffer;

import javax.persistence.*;
import java.util.*;

/**
 * Created by plesse on 2/10/15.
 */
@Entity
@Table(name="languages")
public class Language extends FootballModel {

    @Id
    private Integer idLanguage;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private String shortName;
    @Constraints.Required
    private Integer active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="language", cascade = CascadeType.ALL)
    private List<GameMatchStatusHasLocalization> gameMatchStatus;

    private static Finder<Integer, Language> finder = new Model.Finder<Integer, Language>(Integer.class, Language.class);

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public List<GameMatchStatusHasLocalization> getGameMatchStatus() {
        return gameMatchStatus;
    }

    public void setGameMatchStatus(List<GameMatchStatusHasLocalization> gameMatchStatus) {
        this.gameMatchStatus = gameMatchStatus;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_language", idLanguage);
        response.put("name", name);
        response.put("short_name", shortName);
        response.put("active", active);
        return response;
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        List<Language> languages = finder.where().eq("active", 1).findList();
        for(Language l: languages) {
            options.put(l.getIdLanguage().toString(), l.getName());
        }
        return options;
    }

    public static scala.collection.immutable.List<Tuple2<String, String>> toSeq() {
        List<Language> languages = finder.where().eq("active", 1).findList();
        ArrayList<Tuple2<String, String>> proxy = new ArrayList<>();
        for(Language l : languages) {
            Tuple2<String, String> t = new Tuple2<>(l.getIdLanguage().toString(), l.getName());
            proxy.add(t);
        }
        Buffer<Tuple2<String, String>> languageBuffer = JavaConversions.asScalaBuffer(proxy);
        scala.collection.immutable.List<Tuple2<String, String>> languageList = languageBuffer.toList();
        return languageList;
    }

    public static Page<Language> page(int page, int pageSize, String sortBy, String order, String filter) {
        return finder.where().ilike("name", "%" + filter + "%").orderBy(sortBy + " " + order).findPagingList(pageSize).getPage(page);
    }

    //Finder Operations

    public static Language getByID(int id){
        return finder.byId(id);
    }

    public static Iterator<Language> getPage(int pageSize, int page){
        Iterator<Language> iterator = null;
        if(pageSize == 0){
            iterator = finder.all().iterator();
        }else{
            iterator = finder.where().setFirstRow(page).setMaxRows(pageSize).findList().iterator();
        }
        return  iterator;
    }
}

