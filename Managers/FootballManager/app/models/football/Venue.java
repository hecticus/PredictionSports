package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

/**
 * Created by karina on 5/20/14.
 */
@Entity
@Table(name="venues")
public class Venue extends FootballModel {

    @Id
    private Long idVenues;
    @ManyToOne
    @JoinColumn(name="id_country")
    private Countries country;
    private String cityName;
    private String stadiumName;
    private Long extCityId;
    private Long extStadiumId;

    private Long extId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venue", cascade = CascadeType.ALL)
    private List<GameMatch> matches;

    private static Model.Finder<Long,Venue> finder = new Model.Finder<Long,Venue>(Long.class,Venue.class);

    public Long getIdVenues() {
        return idVenues;
    }

    public void setIdVenues(Long idVenues) {
        this.idVenues = idVenues;
    }

    public Long getExtStadiumId() {
        return extStadiumId;
    }

    public void setExtStadiumId(Long extStadiumId) {
        this.extStadiumId = extStadiumId;
    }

    public Long getExtCityId() {
        return extCityId;
    }

    public void setExtCityId(Long extCityId) {
        this.extCityId = extCityId;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public List<GameMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<GameMatch> matches) {
        this.matches = matches;
    }

    public Venue findById(Long id){
        return finder.byId(id);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("id_venues",idVenues);
        node.put("country",country.toJson());
        node.put("city_name",cityName);
        node.put("stadium_name",stadiumName);
        node.put("ext_city_id",extCityId);
        node.put("ext_stadium_id",extStadiumId);

        return node;
    }

    public Venue findByExtId(long extId){
        return finder.where().eq("ext_id", extId).findUnique();
    }

    public Venue(Long extId, String stadiumName, String cityName, Countries country) {
        this.extId = extId;
        this.stadiumName = stadiumName;
        this.cityName = cityName;
        this.country = country;
    }

    /**
     * funcion para validar los venues
     */
    public void validateVenue(){
        Venue toValidate = findByExtId(this.extId);
        if (toValidate != null){
            //values
            this.idVenues = toValidate.idVenues;
            this.cityName = toValidate.cityName;
            this.stadiumName = toValidate.stadiumName;
            this.country = toValidate.country;
            this.extCityId = toValidate.extCityId;
            this.extStadiumId = toValidate.extStadiumId;
            this.extId = toValidate.extId;
        }else {
            this.save();
        }
    }
}
