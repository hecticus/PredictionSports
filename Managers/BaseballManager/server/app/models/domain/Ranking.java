package models.domain;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import play.api.libs.json.Json;
import play.libs.Json;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class Ranking extends Model  {

    @Id
    private Long idRanking;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Team team;

    @Constraints.Required
    private int matches;

    @Constraints.Required
    private int matchesWon;

    @Constraints.Required
    private int matchesDraw;

    @Constraints.Required
    private int matchesLost;

    @Constraints.Required
    private int points;

    @Constraints.Required
    private int runFor;

    @Constraints.Required
    private int runAgainst;

    public static Finder<Long, Ranking> finder = new Finder<Long, Ranking>(Ranking.class);

    public Long getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(Long idRanking) {
        this.idRanking = idRanking;
    }

    public models.domain.Team getTeam() {
        return team;
    }

    public void setTeam(models.domain.Team team) {
        team = team;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesDraw() {
        return matchesDraw;
    }

    public void setMatchesDraw(int matchesDraw) {
        this.matchesDraw = matchesDraw;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRunfor() {
        return runFor;
    }

    public void setRunfor(int runFor) {
        this.runFor = runFor;
    }

    public int getRunAgainst() {
        return runAgainst;
    }

    public void setRunAgainst(int runAgainst) {
        this.runAgainst = runAgainst;
    }


    public Ranking() {
    }


    public static List<Ranking> getRanking() {
        return finder.where().findList();
    }

    public ObjectNode toJsonPhaseID() {
        ObjectNode node = Json.newObject();
        node.put("id_ranking",idRanking);
        node.put("phase",0);
        node.put("team",team.toJson());
        node.put("matches",matches);
        node.put("matches_won",matchesWon);
        node.put("matches_drawn", matchesDraw);
        node.put("matches_lost",matchesLost);
        node.put("points",points);
        node.put("goals_for",runFor);
        node.put("goal_against",runAgainst);
        node.put("nivel",0);
        node.put("orden",0);
        return node;
    }

}
