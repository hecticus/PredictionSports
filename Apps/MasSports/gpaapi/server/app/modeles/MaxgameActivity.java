package modeles;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class MaxgameActivity extends Model {

    public static Model.Finder<Long, MaxgameActivity> finder = new Model.Finder<Long, MaxgameActivity>(MaxgameActivity.class);
    @Id
    private Long id;
    @Column()
    private String clickId;
    @Column()
    private String date;

    @Column()
    private boolean sent;

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

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public MaxgameActivity(String clickId) {
        this.clickId = clickId;
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf =
                new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        this.date =  sdf.format(c1.getTime());
    }
}
