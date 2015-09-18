package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.TimeZone;

/**
 * Created by plesse on 11/26/14.
 */
@Entity
@Table(name="timezones")
public class Timezone extends FootballModel {

    @Id
    private Integer idTimezone;
    private String name;


    private static Model.Finder<Integer, Timezone> finder = new Model.Finder<Integer, Timezone>(Integer.class, Timezone.class);

    public Timezone(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toJson() {
        return null;
    }

    public Integer getIdTimezone() {
        return idTimezone;
    }

    public void setIdTimezone(Integer idTimezone) {
        this.idTimezone = idTimezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeZone getTimezone(){
        return TimeZone.getTimeZone(this.name);
    }
}
