package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.Config;
import models.FootballModel;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by karina on 5/13/14.
 */
@Entity
@Table(name="teams", uniqueConstraints = @UniqueConstraint(columnNames = {"ext_id"}))
public class Team extends FootballModel {

    @Id
    private Long idTeams;
    @Constraints.Required
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_countries")
    private Countries country;
    @Constraints.Required
    private String extId;

    private String officialName;
    private String shortName;
    private String abbreviationName;
    private String teamLogo;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL)
    private List<TeamHasCompetition> competitions;

    public static  Model.Finder<Long,Team> finder = new Model.Finder<Long,Team>(Long.class,Team.class);

    public Team(String name, String extId, Countries country) {
        this.name = name;
        this.extId = extId;
        this.country = country;
    }

    public Team(String name, Countries country, String extId, String officialName, String shortName, String abbreviationName) {
        this.name = name;
        this.country = country;
        this.extId = extId;
        this.officialName = officialName;
        this.shortName = shortName;
        this.abbreviationName = abbreviationName;
    }

    public Long getIdTeams() {
        return idTeams;
    }

    public void setIdTeams(Long idTeams) {
        this.idTeams = idTeams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
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

    public static  List<Team> getList(){
        return finder.all();
    }

    public static Team findById(Long id){
        return finder.byId(id);
    }

    public List<TeamHasCompetition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<TeamHasCompetition> competitions) {
        this.competitions = competitions;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_teams",idTeams);
        obj.put("name",name);
        obj.put("ext_id",extId);
        obj.put("short_name",shortName);
        obj.put("abbreviation_name",abbreviationName);
        obj.put("team_logo",teamLogo);
        obj.put("country",country.toJson());
        obj.put("team_logo", Config.getString("team-logo-url") + extId + ".png");
        return obj;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode obj = Json.newObject();
        obj.put("id_teams",idTeams);
        obj.put("name",name);
        obj.put("short_name",shortName);
        obj.put("abbreviation_name",abbreviationName);
        obj.put("team_logo", Config.getString("team-logo-url") + extId + ".png");
        return obj;
    }

    public static Team findByExtId(String extId){
        return finder.where().eq("ext_id", extId).findUnique();
    }

    /**
     * funcion para validar los equipos
     */
    public void validateTeam(final Competition competition){
        try {
            Team toValidate = findByExtId(this.extId);
            if (toValidate != null){
                //exist
                this.idTeams = toValidate.idTeams;
//                this.name = toValidate.name;
//                this.extId = toValidate.extId;
//                this.country = toValidate.country;
                this.competitions = toValidate.competitions;
                this.update();
                TeamHasCompetition teamHasCompetition = null;

                try {
                    teamHasCompetition = Iterables.find(competitions, new Predicate<TeamHasCompetition>() {
                        public boolean apply(TeamHasCompetition obj) {
                            return obj.getCompetition().getIdCompetitions().longValue() == competition.getIdCompetitions().longValue();
                        }
                    });
                } catch (NoSuchElementException ex){
                    teamHasCompetition = null;
                }

                if(teamHasCompetition == null){
                    teamHasCompetition = new TeamHasCompetition(this, competition);
                    competitions.add(teamHasCompetition);
                    this.update();
                }

            }else {
                TeamHasCompetition teamHasCompetition = new TeamHasCompetition(this, competition);
                competitions.add(teamHasCompetition);
                //insert in bd
                this.save();
            }
        }catch (Exception ex){
           //do nothing
        }
    }
}
