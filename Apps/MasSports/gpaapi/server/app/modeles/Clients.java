package modeles;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import play.data.validation.Constraints;
import javax.persistence.Column;
import play.data.validation.Constraints;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;


import javax.persistence.*;
import java.util.UUID;

/**
 * Created by palenge on 5/17/17.
 */
@Entity
public class Clients extends Model {

    @Id
    private Long id;

    public String identifier;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    protected Long msisdn;

    @Column(nullable = false)
    protected String token;

    @Column()
    protected String confirm;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Services service;

    public static Model.Finder<Long, Clients> finder = new Model.Finder<Long, Clients>(Clients.class);


    public String getUuid() {   return identifier;  }

    public void setUuid(String uuid) {  this.identifier = uuid; }

    public Long getMsisdn() {    return msisdn;  }

    public void setMsisdn(Long msisdn) { this.msisdn = msisdn;   }

    public String getToken() {  return token;   }

    public void setToken(String token) {
        this.token = token;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Clients getClientByMSisdnAndConfirm(String msisdn, String confirm)
    {
        return  finder.where().eq("msisdn", msisdn).eq("confirm", confirm).findUnique();
    }

    public static Clients getClientByIdentifier(String identifier)
    {
        return  finder.where().eq("identifier", identifier).findUnique();
    }
}
