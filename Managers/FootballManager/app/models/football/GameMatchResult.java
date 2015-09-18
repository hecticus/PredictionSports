package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by karina on 5/20/14.
 */
@Entity
@Table(name="game_match_results")
public class GameMatchResult extends FootballModel {

    @Id
    private Long idGameMatchResults;
    @OneToOne(fetch = FetchType.LAZY) //Fetch: to load it together with the rest of the fields (i.e. eagerly) or to load it on-demand (i.e. lazily)
    @JoinColumn(name="id_game_matches")
    private GameMatch gameMatch;
    private Integer homeTeamGoalsHalf;
    private Integer awayTeamGoalsHalf;
    private Integer homeTeamGoals_90;
    private Integer awayTeamGoals_90;
    private Integer homeTeamGoals_105;
    private Integer awayTeamGoals_105;
    private Integer homeTeamGoals_120;
    private Integer awayTeamGoals_120;
    private Integer homeTeamGoalsShootout;
    private Integer awayTeamGoalsShootout;
    private String  scoreStr;

    private static Finder<Long,GameMatchResult> finder = new
            Finder<Long,GameMatchResult>(Long.class,GameMatchResult.class);

    public  GameMatchResult(){ }

    public Long getIdGameMatchResults() {
        return idGameMatchResults;
    }

    public void setIdGameMatchResults(Long idGameMatchResults) {
        this.idGameMatchResults = idGameMatchResults;
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public void setGameMatch(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    public Integer getHomeTeamGoalsHalf() {
        return homeTeamGoalsHalf;
    }

    public void setHomeTeamGoalsHalf(Integer homeTeamGoalsHalf) {
        this.homeTeamGoalsHalf = homeTeamGoalsHalf;
    }

    public Integer getAwayTeamGoalsHalf() {
        return awayTeamGoalsHalf;
    }

    public void setAwayTeamGoalsHalf(Integer awayTeamGoalsHalf) {
        this.awayTeamGoalsHalf = awayTeamGoalsHalf;
    }

    public Integer getHomeTeamGoals_90() {
        return homeTeamGoals_90;
    }

    public void setHomeTeamGoals_90(Integer homeTeamGoals_90) {
        this.homeTeamGoals_90 = homeTeamGoals_90;
    }

    public Integer getAwayTeamGoals_90() {
        return awayTeamGoals_90;
    }

    public void setAwayTeamGoals_90(Integer awayTeamGoals_90) {
        this.awayTeamGoals_90 = awayTeamGoals_90;
    }

    public Integer getHomeTeamGoalsShootout() {
        return homeTeamGoalsShootout;
    }

    public void setHomeTeamGoalsShootout(Integer homeTeamGoalsShootout) {
        this.homeTeamGoalsShootout = homeTeamGoalsShootout;
    }

    public Integer getAwayTeamGoalsShootout() {
        return awayTeamGoalsShootout;
    }

    public void setAwayTeamGoalsShootout(Integer awayTeamGoalsShootout) {
        this.awayTeamGoalsShootout = awayTeamGoalsShootout;
    }

    public String getScoreStr() {
        return scoreStr;
    }

    public void setScoreStr(String scoreStr) {
        this.scoreStr = scoreStr;
    }

    public Integer getHomeTeamGoals_105() {
        return homeTeamGoals_105;
    }

    public void setHomeTeamGoals_105(Integer homeTeamGoals_105) {
        this.homeTeamGoals_105 = homeTeamGoals_105;
    }

    public Integer getAwayTeamGoals_105() {
        return awayTeamGoals_105;
    }

    public void setAwayTeamGoals_105(Integer awayTeamGoals_105) {
        this.awayTeamGoals_105 = awayTeamGoals_105;
    }

    public Integer getHomeTeamGoals_120() {
        return homeTeamGoals_120;
    }

    public void setHomeTeamGoals_120(Integer homeTeamGoals_120) {
        this.homeTeamGoals_120 = homeTeamGoals_120;
    }

    public Integer getAwayTeamGoals_120() {
        return awayTeamGoals_120;
    }

    public void setAwayTeamGoals_120(Integer awayTeamGoals_120) {
        this.awayTeamGoals_120 = awayTeamGoals_120;
    }

    public static GameMatchResult findById(Long id){
        return finder.byId(id);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("id_game_match_results",idGameMatchResults);
        node.put("home_team_goals_half",homeTeamGoalsHalf);
        node.put("home_team_goals_90",homeTeamGoals_90);
        node.put("home_team_goals_105",homeTeamGoals_105);
        node.put("home_team_goals_120",homeTeamGoals_120);
        node.put("home_team_goals_shootout",homeTeamGoalsShootout);
        node.put("away_team_goals_half",awayTeamGoalsHalf);
        node.put("away_team_goals_90",awayTeamGoals_90);
        node.put("away_team_goals_105",awayTeamGoals_105);
        node.put("away_team_goals_120",awayTeamGoals_120);
        node.put("away_team_goals_shootout",awayTeamGoalsShootout);
        node.put("score_str",scoreStr);
        return node;
    }
}
