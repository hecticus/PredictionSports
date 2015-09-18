package models.football;

import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karina on 5/20/14.
 */
@Entity
@Table(name="game_match_events",uniqueConstraints = @UniqueConstraint(columnNames = {"ext_id"}))
public class GameMatchEvent extends FootballModel {

    @Id
    private Long idGameMatchEvents;
    @ManyToOne
    @JoinColumn(name = "id_game_matches")
    private GameMatch gameMatch;
    @ManyToOne
    @JoinColumn(name = "id_periods")
    private Period period;
    @ManyToOne
    @JoinColumn(name="id_actions")
    private Action action;
    @ManyToOne
    @JoinColumn(name="id_teams")
    private Team team;
    private String playerA;
    private String playerB;
    private Integer actionMinute;
    @Constraints.MaxLength(14)
    private String date;
    private Integer _sort;

    private Long extId;

    private Boolean generated;

    public static Model.Finder<Long,GameMatchEvent> finder = new Model.Finder<Long,GameMatchEvent>(Long.class,GameMatchEvent.class);

    public GameMatchEvent(GameMatch gameMatch, Period period, Action action, Team team, String playerA, String playerB, Integer actionMinute, String date, Integer _sort, Long extId) {
        this.gameMatch = gameMatch;
        this.period = period;
        this.action = action;
        this.team = team;
        this.playerA = playerA;
        this.playerB = playerB;
        this.actionMinute = actionMinute;
        this.date = date;
        this._sort = _sort;
        this.extId = extId;
        this.generated = false;
    }

    public Long getIdGameMatchEvents() {
        return idGameMatchEvents;
    }

    public void setIdGameMatchEvents(Long idGameMatchEvents) {
        this.idGameMatchEvents = idGameMatchEvents;
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public void setGameMatch(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPlayerA() {
        return playerA;
    }

    public void setPlayerA(String playerA) {
        this.playerA = playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public void setPlayerB(String playerB) {
        this.playerB = playerB;
    }

    public Integer getActionMinute() {
        return actionMinute;
    }

    public void setActionMinute(Integer actionMinute) {
        this.actionMinute = actionMinute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer get_sort() {
        return _sort;
    }

    public void set_sort(Integer _sort) {
        this._sort = _sort;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public static List<GameMatchEvent> getList(GameMatch match,String action, String period,String tstart, String tend, int lastMinute){

        ExpressionList<GameMatchEvent> expr = finder.where().eq("gameMatch", match);
        if(!action.isEmpty()){
            expr = expr.eq("action", Action.findByMnemonic(action));
        }
        if(!period.isEmpty()){
            expr = expr.eq("period", Period.findByShotName(period));
        }

        String day = match.getDate().substring(0,8);
        if(!tstart.isEmpty()){
           expr = (tend.isEmpty())?expr.eq("date",day+tstart):expr.ge("date",day+tstart).le("date",day+tend);
        }

        if(lastMinute > -1){
            expr = expr.ge("action_minute",lastMinute);
        }

        return expr.orderBy("id_game_match_events desc").findList();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json.put("id_game_match_events",idGameMatchEvents);
        json.put("id_game_matches",gameMatch.getIdGameMatches());
        json.put("period",period.toJson());
        json.put("action",action.toJson());
        json.put("id_teams",team.getIdTeams());
        json.put("player_a",playerA);
        json.put("player_b",playerB);
        json.put("action_minute",actionMinute);
        json.put("date",date);
        json.put("_sort",_sort);
        return json;
    }

    public ObjectNode toJsonNoPeriod(Language language, Language defaultLanguage) {
        ObjectNode json = Json.newObject();
        json.put("id_game_match_events",idGameMatchEvents);
        json.put("id_game_matches",gameMatch.getIdGameMatches());
        json.put("action",action.toJson(language, defaultLanguage));
        json.put("teams",team.getName());
        json.put("player_a",playerA);
        json.put("player_b",playerB);
        json.put("action_minute",actionMinute);
        json.put("date",date);
        json.put("_sort",_sort);
        return json;
    }

    public ObjectNode toJsonMarked(Long homeTeam) {
        ObjectNode json = Json.newObject();
        json.put("id_game_match_events",idGameMatchEvents);
        json.put("id_game_matches",gameMatch.getIdGameMatches());
        json.put("action",action.toJson());
        json.put("is_home_team", (team.getIdTeams() == homeTeam));
        json.put("player_a",playerA);
        json.put("player_b",playerB);
        json.put("action_minute",actionMinute);
        json.put("date",date);
        json.put("_sort",_sort);
        return json;
    }

    public ObjectNode toJsonForPush() {
        ObjectNode json = Json.newObject();
        json.put("action", action.toJson());
        json.put("is_home_team", (team.getIdTeams() == gameMatch.getHomeTeam().getIdTeams()));
        json.put("player_a",playerA);
        json.put("player_b",playerB);
        json.put("action_minute",actionMinute);
        return json;
    }

    public void validate() {
        GameMatchEvent tr = finder.where().eq("extId", this.extId).findUnique();
        if (tr != null) {
            //existe
            this.idGameMatchEvents = tr.idGameMatchEvents;
            this.gameMatch = tr.gameMatch;
            this.period = tr.period;
            this.action = tr.action;
            this.team = tr.team;
            this.playerA = tr.playerA;
            this.playerB = tr.playerB;
            this.actionMinute = tr.actionMinute;
            this. date = tr.date;
            this._sort = tr._sort;
            this.extId = tr.extId;

        } else {
            this.save();
        }
    }

    public static List<GameMatchEvent> getEventsForMatch(GameMatch gameMatch, long idEvent, boolean forward, ArrayList<Language> languages){
        List<GameMatchEvent> events = null;
        if(idEvent == 0){
            events = finder.fetch("action").where().eq("gameMatch", gameMatch).in("action.localizations.language", languages).orderBy("_sort desc").findList();
        } else {
            if(forward){
                events = finder.fetch("action").where().eq("gameMatch", gameMatch).in("action.localizations.language", languages).gt("idGameMatchEvents", idEvent).orderBy("_sort desc").findList();
            } else {
                events = finder.fetch("action").where().eq("gameMatch", gameMatch).in("action.localizations.language", languages).lt("idGameMatchEvents", idEvent).orderBy("_sort asc").findList();
            }
        }
        return events;
    }
}
