package models.domain;


import com.avaje.ebean.Model;
//import jdk.nashorn.internal.ir.ObjectNode;
import models.Config;
import play.data.validation.Constraints;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import play.libs.Json;

/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class LeagueType extends Model  {

    @Id
    private Long idLeagueType;

    private int status;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String name;

    private int type;

    private int sort;

    private String comp_logo;

    public Long getIdLeagueType() {
        return idLeagueType;
    }

    public void setIdLeagueType(Long idLeagueType) {
        this.idLeagueType = idLeagueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getComp_logo() {
        return comp_logo;
    }

    public void setComp_logo(String comp_logo) {
        this.comp_logo = comp_logo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static Model.Finder<Long, LeagueType> finder = new Model.Finder<Long, LeagueType>(LeagueType.class);

    public LeagueType(String name) {
        this.name = name;
        this.status = 1;
        this.type = 1;
        this.sort = 0;
        this.comp_logo = "";

    }

    public static LeagueType getByName(String name) {
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("name", name).findUnique();
    }


    public ObjectNode getJson() {
        ObjectNode obj = Json.newObject();
        obj.put("status",this.status); //baseball
        obj.put("name",this.name); //no aplica en baseball los id son directos
        obj.put("type",this.type);
        obj.put("sort",this.sort);
        obj.put("comp_logo", Config.getString("competition-logo-url") + this.comp_logo + "png");
        return obj;
    }
}
