package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.TimeZone;
import play.libs.Json;

/**
 * Created by plesse on 11/26/14.
 */
@Entity
@Table(name="splash_config")
public class SplashConfig extends FootballModel {

    @Id
    private Integer idSplash;

    @Constraints.Required
    @Formats.DateTime(pattern="yyyyMMdd")
    private String date;

    @Constraints.Required
    @Constraints.MaxLength(360)
    private String url;

    @Constraints.Required
    private String status;


    private static Model.Finder<Integer, SplashConfig> finder = new Model.Finder<Integer, SplashConfig>(Integer.class, SplashConfig.class);

    public SplashConfig() {

    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_splash", idSplash);
        obj.put("date", date);
        obj.put("url", url);
        obj.put("status", status);
        return obj;
    }

    public Integer getIdSplash() {
        return idSplash;
    }


    public SplashConfig getSplashConfig(String date)
    {
        //return finder.where().eq("msisdn", msisdn).findUnique();
        return finder.where().gt("date", date).eq("status", 1).orderBy("date asc").setMaxRows(1).findUnique();
    }
    //public void setIdSplash(Integer isSpla) {   this.idTimezone = idTimezone;    }


}
