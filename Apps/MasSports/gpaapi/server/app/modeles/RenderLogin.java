package modeles;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class RenderLogin extends Model {
    @Id
    private Long id;
    @Column()
    private Date fecha;
    @Column()
    private String msisdn;
    @Column()
    private String club;

    public RenderLogin() {
    }

    public RenderLogin(Long id, Date fecha, String msisdn) {
        this.id = id;
        this.fecha = fecha;
        this.msisdn = msisdn;
    }

    public RenderLogin(String msisdn, String club) {
        this.club = club;
        this.fecha =  new Date();
        this.msisdn = msisdn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "RenderLogin{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", msisdn='" + msisdn + '\'' +
                ", club='" + club + '\'' +
                '}';
    }
}
