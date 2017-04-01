package models.domain;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name="team_has_leagues")
public class TeamHasLeague extends Model {

    @Id
    private Long idTeamHasLeague;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_league")
    private League competition;

    public static  Model.Finder<Long,TeamHasLeague> finder = new Model.Finder<Long,TeamHasLeague>(Long.class,TeamHasLeague.class);

    public TeamHasLeague() {
    }

    public TeamHasLeague(Team team, League competition) {
        this.team = team;
        this.competition = competition;
    }

    public TeamHasLeague(long idTeam, long idLeague) {
        this.team = Team.getById(idTeam);
        this.competition = League.getByID(idLeague);
    }

    public Long getIdTeamHasLeague() {
        return idTeamHasLeague;
    }

    public void setIdTeamHasLeague(Long idTeamHasLeague) {
        this.idTeamHasLeague = idTeamHasLeague;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public League getLeague() {
        return competition;
    }

    public void setLeague(League competition) {
        this.competition = competition;
    }

    public static TeamHasLeague getByValues(long idTeam, long idLeague){
        //EbeanServer server = Ebean.getServer("clients");
        List<TeamHasLeague> aux = finder.where().eq("id_team", idTeam).eq("id_league", idLeague).findList();
        return (aux.size() > 0?  aux.get(0):null);//.findUnique();
    }

    public static List<Team> getTeamsByLeague(long idLeague){
        //EbeanServer server = Ebean.getServer("clients");
        List<TeamHasLeague> aux =  finder.where().eq("id_league", idLeague).findList();
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i< aux.size();i++ )
            teams.add(aux.get(i).getTeam());
        return teams;
    }

    public ObjectNode toJson() {
        return null;
    }
}