package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by karina on 5/13/14.
 */
@Entity
public class Countries extends FootballModel {

    @Id
    private Long idCountries;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private String extId;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Venue> venues;

    private static Model.Finder<Long,Countries> finder = new Model.Finder<Long,Countries>(Long.class,Countries.class);

    public Countries(String name, String extId) {
        this.name = name;
        this.extId = extId;
    }

    public Countries(String name) {
        this.name = name;
        this.extId = name;
    }

    public Long getIdCountries() {
        return idCountries;
    }

    public void setIdCountries(Long idCountries) {
        this.idCountries = idCountries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId){
        this.extId = extId;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public static  List<Countries> all(){
        return finder.all();
    }

    public static Countries findById(Long id){
        return finder.byId(id);
    }

    public static Countries findByExtId(String id){
        return finder.where().eq("ext_id",id).findUnique();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_countries",idCountries);
        obj.put("name",name);
        obj.put("ext_id",extId);

        return obj;
    }

    public void validateCountry(){
        Countries toValidate =  findByExtId(this.extId);
        if (toValidate != null){
            this.idCountries = toValidate.idCountries;
            this.name = toValidate.name;
            this.extId = toValidate.extId;
            this.idCountries = toValidate.idCountries;
        }else {
            this.save();
        }
    }
}
