package modeles;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Ferck on 14/6/2017.
 */

@Entity
public class log extends Model  {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Constraints.MaxLength(16)
    @Column(length = 16, nullable = false)
    protected String msisdn;

    @Constraints.MaxLength(16)
    @Column(length = 16, nullable = false)
    protected String identifier;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/MM/dd hh:mm")
    @Column(columnDefinition = "datetime")
    public Date lastUpdate;

    public static Finder<Long, log> finder = new Finder<Long, log>(log.class);

    public log() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
