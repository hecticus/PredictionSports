package modeles;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class PaxxionActivity extends Model {

    public static Model.Finder<Long, PaxxionActivity> finder = new Model.Finder<Long, PaxxionActivity>(PaxxionActivity.class);

    @Id
    private Long id;
    @Column()
    private String clickId;
    @Column()
    private String date;

    @Column()
    private String msisdn;

    @Column()
    private String origin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public PaxxionActivity(String clickId) {
        this.clickId = clickId;
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf =
                new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        this.date =  sdf.format(c1.getTime());
    }
}
