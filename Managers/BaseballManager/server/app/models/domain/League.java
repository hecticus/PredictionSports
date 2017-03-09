package models.domain;

import com.avaje.ebean.Model;
//import jdk.nashorn.internal.ir.ObjectNode;
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
public class League extends Model {

    @Id
    private Long idLeague;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String name;

    private int status;

    @Column(name="`show`")
    private int show;

    public static Model.Finder<Long, League> finder = new Model.Finder<Long, League>(League.class);

    public Long getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(Long idLeague) {
        this.idLeague = idLeague;
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

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public League(String name) {
        this.name = name;
        this.status = 0;
        this.status = 0;
    }

    public static League getByName(String name) {
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("name", name).findUnique();
    }

    public static League getByID(Long id) {
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("id_league", id).findUnique();
    }
    public static List<League> getActiveLeagues() {
        return finder.where().eq("status", 1).findList();
    }


    public ObjectNode getJsonDashboard(boolean closestMatch) {
        ObjectNode obj = Json.newObject();
        obj.put("sport_id",2); //baseball
        obj.put("id_competition",idLeague); //baseball
        obj.put("ext_id",-1); //no aplica en baseball los id son directos
        obj.put("show",show);
        obj.put("status",status);
        obj.put("competiton_type",100); //No tenemos aun este dato

        if(closestMatch){
            Game match = Game.getClosestMatch(this);
            obj.put("match", match!=null?match.toJson():null);
        }
        return obj;
    }

    public ObjectNode getJson() {
        ObjectNode obj = Json.newObject();
        obj.put("sport_id",2); //baseball
        obj.put("id_competition",idLeague); //baseball
        obj.put("name",name); //no aplica en baseball los id son directos
        obj.put("ext_id",idLeague);
        return obj;
    }


}
