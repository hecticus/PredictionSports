package models.football;

import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.Config;
import models.FootballModel;
import models.Language;
import play.db.ebean.Model;
import play.libs.Json;
import utils.DateAndTime;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by karina on 5/20/14.
 */
@Entity
@Table(name="game_matches", uniqueConstraints = @UniqueConstraint(columnNames = {"id_competition, ext_id"}))
public class GameMatch extends FootballModel {

    public static final short NONE = 0;
    public static final short STARTED = 1;
    public static final short LIVE = 2;
    public static final short FINISHED = 3;
    public static final short SUSPENDED = 4;
    public static final short NOSTARTED = 5;


    @Id
    private Long idGameMatches;
    @ManyToOne
    @JoinColumn(name="id_phases")
    private Phase phase;
    @ManyToOne
    @JoinColumn(name="id_home_team")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name="id_away_team")
    private Team awayTeam;
    @ManyToOne
    @JoinColumn(name="id_venue")
    private Venue venue;
    private Long fifaMatchNumber;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    @javax.persistence.Column(length=14)
    private String date;
    @ManyToOne
    @JoinColumn(name="id_game_match_status")
    private GameMatchStatus status;
    private Integer started;
    private Integer live;
    private Integer finished;
    private Integer suspended;
    private Long extId;

    private Integer fn;

    @ManyToOne
    @JoinColumn(name="id_competition")
    private Competition competition;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "gameMatch", cascade = CascadeType.ALL)
    private GameMatchResult result;

    @OneToMany(mappedBy = "gameMatch", cascade = CascadeType.ALL)
    @OrderBy("_sort asc")
    private List<GameMatchEvent> events;

    public static Model.Finder<Long,GameMatch> finder = new Model.Finder<Long,GameMatch>(Long.class, GameMatch.class);

    public GameMatch(){}

    public GameMatch(Phase phase, Team homeTeam, Team awayTeam, Venue venue, String homeTeamName,
                     String awayTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String date,
                     GameMatchStatus status, Long extId, Competition competition) {
        this.phase = phase;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.venue = venue;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.date = date;
        this.status = status;
        this.extId = extId;
        this.competition = competition;
    }

    public Long getIdGameMatches() {
        return idGameMatches;
    }

    public void setIdGameMatches(Long idGameMatches) {
        this.idGameMatches = idGameMatches;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Long getFifaMatchNumber() {
        return fifaMatchNumber;
    }

    public void setFifaMatchNumber(Long fifa_match_number) {
        this.fifaMatchNumber = fifa_match_number;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GameMatchStatus getStatus() {
        return status;
    }

    public void setStatus(GameMatchStatus status) {
        this.status = status;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public Integer getSuspended() {
        return suspended;
    }

    public void setSuspended(Integer suspended) {
        this.suspended = suspended;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public GameMatchResult getResult() {
        return result;
    }

    public void setResult(GameMatchResult result) {
        this.result = result;
    }

    public Integer getStarted() {
        return started;
    }

    public void setStarted(Integer started) {
        this.started = started;
    }

    public List<GameMatchEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GameMatchEvent> events) {
        this.events = events;
    }

    public Integer getFn() {
        return fn;
    }

    public void setFn(Integer fn) {
        this.fn = fn;
    }

    public static List<GameMatch> getList(Long idPhase, String startd, String endd, short status){
        if(idPhase == 0 && startd.isEmpty() && status == NONE) return finder.all();
        ExpressionList<GameMatch> expr = null;
        if(idPhase > 0){
            expr = finder.where().eq("id_phases",idPhase);
        }

        if(!endd.isEmpty()){
           expr = (expr==null)?finder.where().ge("date", startd).le("date", endd):expr.ge("date",startd).le("date", endd);
        }else{
            expr = (expr==null)?finder.where().ge("date", startd):expr.ge("date", startd);
        }

        switch (status){
            case STARTED: expr = expr.eq("started",1);break;
            case LIVE: expr = expr.eq("live",1);break;
            case FINISHED: expr = expr.eq("finished",1);break;
            case SUSPENDED: expr = expr.eq("suspended",1);break;
            case NOSTARTED: expr = expr.eq("started",0).eq("live", 0).eq("finished", 0).eq("suspended", 0);
            default:break;
        }

        return expr.findList();
    }

    public static GameMatch findById(Long idGameMatch){
        return finder.byId(idGameMatch);
    }

    public static GameMatch findByIdExternal(Long idExt){
        return finder.where().eq("extId",idExt).findUnique();
    }

    public static GameMatch findByIdExternal(String idExt){
        return finder.where().eq("extId",idExt).findUnique();
    }

    public static GameMatch findByIdFifa(Long idFifa){
        return finder.where().eq("fifaMatchNumber",idFifa).findUnique();
    }

    public static List<GameMatch> findAllByIdCompetition(Long idCompetition){
        return finder.where().eq("id_competition",idCompetition).findList();
    }

    public static GameMatch getClosestMatch(Competition competition){
        Calendar today = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        List<GameMatch> latestGameMatch = finder.where().eq("competition", competition).ge("date", sdf.format(today.getTime())).orderBy("date asc").setMaxRows(1).findList();
        if(!latestGameMatch.isEmpty()){
            return latestGameMatch.get(0);
        }
        return null;
    }

    public static List<GameMatch> findAllByIdCompetitionAndDate(Long idCompetition, String date, String operator){
        if(operator.equalsIgnoreCase("gt")) {
            return finder.where().eq("id_competition",idCompetition).gt("date", date).orderBy("date asc").findList();
        } else if(operator.equalsIgnoreCase("lt")){
            return finder.where().eq("id_competition",idCompetition).lt("date", date).orderBy("date asc").findList();
        }
        return finder.where().eq("id_competition",idCompetition).ilike("date", date + "%").orderBy("date asc").findList();
    }

    public static List<GameMatch> findAllByCompetitionBetweenDate(Long idCompetition, String minDate, String maxDate){
        return finder.where().eq("id_competition",idCompetition).between("date", minDate, maxDate).orderBy("date asc").findList();
    }

    public static List<GameMatch> findAllByIdCompetitionAndPhase(Long idCompetition, Long idPhase, String operator){
        if(operator.equalsIgnoreCase("gt")) {
            return finder.where().eq("id_competition",idCompetition).gt("id_phases", idPhase).orderBy("date asc, phase.idPhases asc").findList();
        } else if(operator.equalsIgnoreCase("lt")){
            return finder.where().eq("id_competition",idCompetition).lt("id_phases", idPhase).orderBy("date asc, phase.idPhases asc").findList();
        }
        return finder.where().eq("id_competition",idCompetition).eq("id_phases", idPhase).orderBy("date asc, phase.idPhases asc").findList();
    }

    public static List<GameMatch> findAllByIdCompetitionOrderedByDate(Long idCompetition){
        return finder.where().eq("id_competition",idCompetition).orderBy("date asc").findList();
    }

    public static List<GameMatch> findAllByIdCompetitionAndStatusOrderedByDate(Long idCompetition){
        return finder.where().eq("id_competition",idCompetition).orderBy("date asc").findList();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        json.put("date",date);
        if(phase != null) {
            json.put("phase",phase.getIdPhases());
        }
        json.put("homeTeam",homeTeam.toJson());
        json.put("awayTeam",awayTeam.toJson());
        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("fifa_match_number",fifaMatchNumber);
        json.put("status", status.toJson());
        json.put("ext_id",extId);
        if(result != null) {
            json.put("results", result.toJson());
        }

        return json;
    }

    public ObjectNode toJsonWithCompetitions(final Language language, final Language defaultLanguage, TimeZone timeZone) throws ParseException {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(timeZone);
        Calendar gameMatchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gameMatchDate.setTime(DateAndTime.getDate(date, date.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
        json.put("date",df.format(gameMatchDate.getTime()));
        if(phase != null) {
            json.put("phase",phase.getIdPhases());
        }
        json.put("homeTeam",homeTeam.toJson());
        json.put("awayTeam",awayTeam.toJson());
        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("fifa_match_number",fifaMatchNumber);
        json.put("status", status.getIdGameMatchStatus());
        json.put("ext_id",extId);
        if(result != null) {
            json.put("results", result.toJson());
        }
        json.put("competition", competition.toJsonNoPhases(language, defaultLanguage, false, null));

        return json;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage, TimeZone timeZone) throws ParseException {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(timeZone);
        Calendar gameMatchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gameMatchDate.setTime(DateAndTime.getDate(date, date.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
        json.put("date",df.format(gameMatchDate.getTime()));
        if(phase != null) {
            json.put("phase",phase.getIdPhases());
        }
        json.put("homeTeam",homeTeam.toJson());
        json.put("awayTeam",awayTeam.toJson());
        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("fifa_match_number",fifaMatchNumber);
        json.put("id_status", status.getIdGameMatchStatus());//.toJson(language, defaultLanguage));
        json.put("ext_id",extId);
        if(result != null) {
            json.put("results", result.toJson());
        }

        return json;
    }

    public ObjectNode toJsonPush() {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        json.put("date",date);
        json.put("home_team",homeTeam.toJsonSimple());
        json.put("away_team",awayTeam.toJsonSimple());
        return json;
    }

    public ObjectNode toJsonPush(TimeZone timeZone) throws ParseException {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(timeZone);
        Calendar gameMatchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gameMatchDate.setTime(DateAndTime.getDate(date, date.length() == 8 ? "yyyyMMdd" : "yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
        json.put("date",df.format(gameMatchDate.getTime()));
        json.put("home_team",homeTeam.toJsonSimple());
        json.put("away_team",awayTeam.toJsonSimple());
        return json;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        json.put("date",date);
        if(homeTeam == null){
            ObjectNode obj = Json.newObject();
            obj.put("id_teams",0);
            obj.put("name",homeTeamName);
            obj.put("short_name",homeTeamName);
            obj.put("abbreviation_name",homeTeamName);
            obj.put("team_logo", Config.getString("team-logo-url") + 0 + ".png");
            json.put("home_team",obj);
        } else {
            json.put("home_team",homeTeam.toJsonSimple());
        }

        if(awayTeam == null){
            ObjectNode obj = Json.newObject();
            obj.put("id_teams",0);
            obj.put("name",awayTeamName);
            obj.put("short_name",awayTeamName);
            obj.put("abbreviation_name",awayTeamName);
            obj.put("team_logo", Config.getString("team-logo-url") + 0 + ".png");
            json.put("away_team",obj);
        } else {
            json.put("away_team",awayTeam.toJsonSimple());
        }

        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("status", status.toJson());
        return json;
    }

    public ObjectNode toJsonSimple(TimeZone timeZone) throws ParseException {
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(timeZone);
        Calendar gameMatchDate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gameMatchDate.setTime(DateAndTime.getDate(date, date.length()==8?"yyyyMMdd":"yyyyMMddhhmmss", TimeZone.getTimeZone("UTC")));
        json.put("date",df.format(gameMatchDate.getTime()));
        if(homeTeam == null){
            ObjectNode obj = Json.newObject();
            obj.put("id_teams",0);
            obj.put("name",homeTeamName);
            obj.put("short_name",homeTeamName);
            obj.put("abbreviation_name",homeTeamName);
            obj.put("team_logo", Config.getString("team-logo-url") + 0 + ".png");
            json.put("home_team",obj);
        } else {
            json.put("home_team",homeTeam.toJsonSimple());
        }

        if(awayTeam == null){
            ObjectNode obj = Json.newObject();
            obj.put("id_teams",0);
            obj.put("name",awayTeamName);
            obj.put("short_name",awayTeamName);
            obj.put("abbreviation_name",awayTeamName);
            obj.put("team_logo", Config.getString("team-logo-url") + 0 + ".png");
            json.put("away_team",obj);
        } else {
            json.put("away_team",awayTeam.toJsonSimple());
        }

        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("status", status.toJson());
        return json;
    }

    public ObjectNode toJsonWithEvents(){
        ObjectNode json = toJson();
        ArrayList<ObjectNode> list = new ArrayList<ObjectNode>();
        Iterator<GameMatchEvent> e = events.iterator();
        while(e.hasNext()){
            list.add(e.next().toJson());
        }
        json.put("events", Json.toJson(list));

        return  json;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public static List<GameMatch> getGamematchByDate(Long idCompetition, String date){
        return finder.where().ilike("date", date+"%").eq("competition.idCompetitions", idCompetition).orderBy("date asc").findList();
    }

    public static List<GameMatch> getGamematchBetweenDates(Long idCompetition, String minDate, String maxDate){
        return finder.where().between("date", minDate, maxDate).eq("competition.idCompetitions", idCompetition).orderBy("date asc").findList();
    }

    public static List<GameMatch> getGamematchBetweenDatesForcompetitions(List<Competition> competitions, String minDate, String maxDate){
        if(competitions != null && !competitions.isEmpty()){
            return finder.where().between("date", minDate, maxDate).in("competition", competitions).orderBy("date asc").findList();
        }
        return finder.where().between("date", minDate, maxDate).orderBy("date asc").findList();
    }

    public ObjectNode fixtureJson(){
        ObjectNode json = Json.newObject();
        json.put("id_game_matches",idGameMatches);//id del juego
        int value = 0;
        if (homeTeamGoals > awayTeamGoals){
            value = 1;
        }else if (homeTeamGoals < awayTeamGoals){
            value = 3;
        }else { //iguales
            value = 2;
        }
        json.put("game_result", value);
        json.put("date",date);
        if(phase != null) {
            //json.put("phase",phase.getIdPhases());
            json.put("phase",phase.toJson());
        }
        json.put("homeTeam",homeTeam.toJson());
        json.put("awayTeam",awayTeam.toJson());
        json.put("home_team_goals",homeTeamGoals);
        json.put("away_team_goals",awayTeamGoals);
        json.put("fifa_match_number",fifaMatchNumber);
        json.put("status", status.toJson());
        json.put("ext_id",extId);
        if(result != null) {
            json.put("results", result.toJson());
        }
        json.put("venue",venue.toJson());
        return json;
    }

    public void validateGame(){
        try {
            GameMatch tr =  finder.where().eq("ext_Id", this.extId).eq("id_competition",this.competition.getIdCompetitions()).findUnique();
            if (tr != null){ //existe
                this.setIdGameMatches(tr.idGameMatches);
                this.update();
            }else {
                this.save();
            }

        }catch (PersistenceException ex){
            //do nothing
        }catch (Exception ex){
            //if cant insert due to uniqueness its ok
        }
    }

    public void updateGameData(GameMatchStatus newStatus, int localGoals, int awayGoals){
        try {
            boolean toUpdate = false;
            if (newStatus.getIdGameMatchStatus() != this.getStatus().getIdGameMatchStatus()
                    || localGoals != this.getHomeTeamGoals()
                    || awayGoals != this.getAwayTeamGoals()){
                toUpdate = true;
            }

            if (toUpdate){
                //do the update
                this.setStatus(newStatus);
                this.setHomeTeamGoals(localGoals);
                this.setAwayTeamGoals(awayGoals);
                this.update();
            }

        }catch (Exception ex){
            //cant do anything
        }
    }


    public List<GameMatchEvent> getEventsNoDB(final long idEvent, boolean forward){
        List<GameMatchEvent> tr = null;
        try {
            if(idEvent == 0){
                tr = events;
            } else {
                GameMatchEvent gameMatchEvent = Iterables.find(events, new Predicate<GameMatchEvent>() {
                    public boolean apply(GameMatchEvent gameMatchEvent) {
                        return gameMatchEvent.getIdGameMatchEvents().intValue() == idEvent;
                    }
                });
                int eventIndex = events.indexOf(gameMatchEvent);
                if(forward){
                    tr = events.subList(eventIndex+1, events.size());
                } else {
                    tr = events.subList(0, eventIndex);
                }
            }
        } catch (NoSuchElementException ex){
            tr = null;
        }
        if(tr != null){
            Collections.sort(tr, forward?new GameMatchEventComparatorDesc():new GameMatchEventComparatorAsc());
        }
        return tr;
    }
}

class GameMatchEventComparatorDesc implements Comparator<GameMatchEvent> {
    @Override
    public int compare(GameMatchEvent c1, GameMatchEvent c2) {
        return c2.get_sort() - c1.get_sort();
    }
}

class GameMatchEventComparatorAsc implements Comparator<GameMatchEvent> {
    @Override
    public int compare(GameMatchEvent c1, GameMatchEvent c2) {
        return c1.get_sort() - c2.get_sort();
    }
}