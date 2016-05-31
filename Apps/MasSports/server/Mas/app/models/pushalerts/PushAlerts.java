package models.pushalerts;

import com.fasterxml.jackson.databind.node.ObjectNode;
import job.TeamsSynchronizer;
import models.HecticusModel;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by chrirod on 10/29/14.
 */
@Entity
@Table(name="push_alerts")
public class PushAlerts extends HecticusModel {

    @Id
    private Integer idPushAlert;
    @Constraints.Required
    private String name;

    @Constraints.Required
    private Integer idExt;

    @Constraints.Required
    private Boolean pushable;

    private String officialName;
    private String shortName;
    private String abbreviationName;
    private String teamLogo;

    public static Model.Finder<Integer, PushAlerts> finder = new Model.Finder<Integer, PushAlerts>(Integer.class, PushAlerts.class);

    public PushAlerts(String name, Integer idExt, Boolean pushable) {
        this.name = name;
        this.idExt = idExt;
        this.pushable = pushable;
    }

    public PushAlerts(String name, Integer idExt, Boolean pushable, String officialName, String shortName, String abbreviationName, String teamLogo) {
        this.name = name;
        this.idExt = idExt;
        this.pushable = pushable;
        this.officialName = officialName;
        this.shortName = shortName;
        this.abbreviationName = abbreviationName;
        this.teamLogo = teamLogo;
    }

    public Integer getIdPushAlert() {
        return idPushAlert;
    }

    public void setIdPushAlert(Integer idPushAlert) {
        this.idPushAlert = idPushAlert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdExt() {
        return idExt;
    }

    public void setIdExt(Integer idExt) {
        this.idExt = idExt;
    }

    public Boolean getPushable() {
        return pushable;
    }

    public void setPushable(Boolean pushable) {
        this.pushable = pushable;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_push_alert",idPushAlert);
        objNode.put("name", name);
        objNode.put("id_ext", idExt);

        try {
            objNode.put("official_name", URLDecoder.decode(officialName, TeamsSynchronizer.ENCODING));
        } catch (Exception e) {
            objNode.put("official_name", officialName);
        }

        try {
            objNode.put("short_name", URLDecoder.decode(shortName, TeamsSynchronizer.ENCODING));
        } catch (Exception e) {
            objNode.put("short_name", shortName);
        }

        try {
            objNode.put("abbreviation_name", URLDecoder.decode(abbreviationName, TeamsSynchronizer.ENCODING));
        } catch (Exception e) {
            objNode.put("abbreviation_name", abbreviationName);
        }

        objNode.put("team_logo",teamLogo);
        objNode.put("pushable", pushable);
        return objNode;
    }

    public static PushAlerts getLastTeamAlert(){
        return finder.where().ne("idExt", -1).orderBy("idExt desc").setMaxRows(1).findUnique();
    }
}
