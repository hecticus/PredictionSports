package models.domain;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by palenge on 12/30/16.
 */
@Entity
public class Action extends Model {

    @Id
    private Long idAction;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Inning inning;


    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Event event;


    @Constraints.Required
    @Constraints.MaxLength(128)
    @Column(length = 128, nullable = false)
    private String guid;

    @Constraints.Required
    private int mode;

    @Constraints.Required
    private long eventNum;

    @Constraints.Required
    private boolean bottomTop;

    @Constraints.Required
    @Column(length = 1024, nullable = false)
    private String descripcion;



    public static Model.Finder<Long, Action> finder = new Model.Finder<Long, Action>(Action.class);

    public Long getIdAction() {
        return idAction;
    }

    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }

    public String getGuid() {
        return guid;
    }


    public Action() {
    }

    public Action(Inning inning, String guid, int mode, boolean bottomTop, String descripcion) {
        this.inning = inning;
        this.guid = guid;
        this.mode = mode;
        this.bottomTop = bottomTop;
        this.descripcion = descripcion;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isBottomTop() {
        return bottomTop;
    }

    public void setBottomTop(boolean bottomTop) {
        this.bottomTop = bottomTop;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getEventNum() {
        return eventNum;
    }

    public void setEventNum(long eventNum) {
        this.eventNum = eventNum;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


    public static Action getById(long idAction){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("id_event", idAction).findUnique();
    }



    public static Action getByGuid(String guid){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("guid", guid).findUnique();
    }
}

