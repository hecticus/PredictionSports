package models.domain;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by palenge on 1/3/17.
 */
@Entity
public class Event extends Model{

    @Id @GeneratedValue
    private Long idEvent;

    @Constraints.Required
    @Constraints.MaxLength(128)
    @Column(length = 128, nullable = false)
    private String name;

    //@OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    //private List<Action> awayGames = new ArrayList<>();


    public static Model.Finder<Long, Event> finder = new Model.Finder<Long, Event>(Event.class);

    public Long getIdEvent() {

        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public static Event getById(long idEvent){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("id_event", idEvent).findUnique();
    }

    public static Event getByName(String name){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("name", name).findUnique();
    }

    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("id_action",idEvent);
        node.put("mnemonic","bsb");
        node.put("description",name);
        return node;
    }

}
