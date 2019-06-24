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
public class Status extends Model  {

    @Id
    private Long idStatus;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String name;

    public static Model.Finder<Long, Status> finder = new Model.Finder<Long, Status>(Status.class);

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public static Status getById(long idStatus){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("id_status", idStatus).findUnique();
    }

    public static Status getByName(String name){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("name", name).findUnique();
    }


    public static Status convertToFootball(Status obj)
    {
        Status aux = new Status();
        switch (obj.getIdStatus().toString())
        {
            case "1":
                aux.setName("Programado");
                aux.setIdStatus(3l);
                break;
            case "2":
                aux.setName("Jugado");
                aux.setIdStatus(1l);
                break;
            case "3":
                aux.setName("Jugando");
                aux.setIdStatus(2l);
                break;
            default:
                aux.setName("Programado");
                aux.setIdStatus(3l);
                break;

        }
        return aux;
    }
}
