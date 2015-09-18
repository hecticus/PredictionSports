package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by plesse on 12/18/14.
 */
@Entity
@Table(name="team_has_competitions")
public class TeamHasCompetition extends FootballModel {

    @Id
    private Long idTeamHasCompetition;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_competitions")
    private Competition competition;

    public static  Model.Finder<Long,TeamHasCompetition> finder = new Model.Finder<Long,TeamHasCompetition>(Long.class,TeamHasCompetition.class);

    public TeamHasCompetition(Team team, Competition competition) {
        this.team = team;
        this.competition = competition;
    }

    public Long getIdTeamHasCompetition() {
        return idTeamHasCompetition;
    }

    public void setIdTeamHasCompetition(Long idTeamHasCompetition) {
        this.idTeamHasCompetition = idTeamHasCompetition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public ObjectNode toJson() {
        return null;
    }
}
