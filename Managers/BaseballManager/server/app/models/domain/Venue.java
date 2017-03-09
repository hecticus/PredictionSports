package models.domain;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class Venue extends Model  {

    @Id
    private Long idVenue;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String name;

    public static Model.Finder<Long, Venue> finder = new Model.Finder<Long, Venue>(Venue.class);

    public Long getIdVenue() {
        return idVenue;
    }

    public void setIdVenue(Long idVenue) {
        this.idVenue = idVenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue(Long idVenue, String name) {
        this.idVenue = idVenue;
        this.name = name;
    }

    public static Venue getById(long idVenue){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("id_venue", idVenue).findUnique();
    }
}
