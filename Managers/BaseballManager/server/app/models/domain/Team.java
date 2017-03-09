package models.domain;

import com.avaje.ebean.Model;
import models.Config;
import play.data.validation.Constraints;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;



/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class Team extends Model {

    @Id
    private Long idTeam;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String name;

    @Constraints.Required
    @Constraints.MaxLength(3)
    @Column(length = 3, nullable = false)
    private String shortCode;

    @Constraints.Required
    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private List<Game> homeGames = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private List<Game> awayGames = new ArrayList<>();

    public static Model.Finder<Long, Team> finder = new Model.Finder<Long, Team>(Team.class);

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Team() {
    }

    public Team(String city, Long idTeam, String name, String shortCode) {
        this.city = city;
        this.idTeam = idTeam;
        this.name = name;
        this.shortCode = shortCode;
    }

    public static Team getByShortCode(String shortCode){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("short_code", shortCode).findUnique();
    }

    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_teams",idTeam);
        obj.put("name",name);
        obj.put("short_name", shortCode);
        obj.put("abbreviation_name",shortCode);
        obj.put("team_logo", Config.getString("team-logo-url") + idTeam + ".png");
        return obj;
    }
}
