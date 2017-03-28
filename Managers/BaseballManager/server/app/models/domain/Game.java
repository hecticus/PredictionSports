package models.domain;

import com.avaje.ebean.Model;
import models.handlers.LineScoreHandler;
import play.data.format.Formats;
import play.data.validation.Constraints;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import utils.DateAndTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class Game extends Model  {

    @Id
    private Long idGame;



    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 120, nullable = false)
    private String identifier;


    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Team homeTeam;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Team awayTeam;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private League league;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Venue venue;



    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/MM/dd hh:mm")
    @Column(columnDefinition = "datetime")
    public Date gameDate;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToMany

    public List<Inning> inning;

    //HomeRuns
    private int hrHome;
    private int hrAway;

    //Errores
    private int eHome;
    private int eAway;

    //Ponchados
    private int soHome;
    private int soAway;

    //Carreras
    private int rHome;
    private int rAway;

    //Bases Robadas
    private int sbHome;
    private int sbAway;

    //Hits
    private int hHome;
    private int hAway;


    public Game() {
    }

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static Model.Finder<Long, Game> finder = new Model.Finder<Long, Game>(Game.class);

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }


    public int getHrHome() {
        return hrHome;
    }

    public void setHrHome(int hrHome) {
        this.hrHome = hrHome;
    }

    public int getHrAway() {
        return hrAway;
    }

    public void setHrAway(int hrAway) {
        this.hrAway = hrAway;
    }

    public int geteHome() {
        return eHome;
    }

    public void seteHome(int eHome) {
        this.eHome = eHome;
    }

    public int geteAway() {
        return eAway;
    }

    public void seteAway(int eAway) {
        this.eAway = eAway;
    }

    public int getSoHome() {
        return soHome;
    }

    public void setSoHome(int soHome) {
        this.soHome = soHome;
    }

    public int getSoAway() {
        return soAway;
    }

    public void setSoAway(int soAway) {
        this.soAway = soAway;
    }

    public int getrHome() {
        return rHome;
    }

    public void setrHome(int rHome) {
        this.rHome = rHome;
    }

    public int getrAway() {
        return rAway;
    }

    public void setrAway(int rAway) {
        this.rAway = rAway;
    }

    public int getSbHome() {
        return sbHome;
    }

    public void setSbHome(int sbHome) {
        this.sbHome = sbHome;
    }

    public int getSbAway() {
        return sbAway;
    }

    public void setSbAway(int sbAway) {
        this.sbAway = sbAway;
    }

    public int gethHome() {
        return hHome;
    }

    public void sethHome(int hHome) {
        this.hHome = hHome;
    }

    public int gethAway() {
        return hAway;
    }

    public void sethAway(int hAway) {
        this.hAway = hAway;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setHr(int home, int away)
    {
        this.hrHome = home;
        this.hrAway = away;
    }

    public void setHr(LineScoreHandler ls)
    {
        this.hrHome = ls.getHome();
        this.hrAway = ls.getAway();
    }

    public void setE(int home, int away)
    {
        this.eHome = home;
        this.eAway = away;
    }

    public void setE(LineScoreHandler ls)
    {
        this.eHome = ls.getHome();
        this.eAway = ls.getAway();
    }

    public void setSo(int home, int away)
    {
        this.soHome = home;
        this.soAway = away;
    }

    public void setSo(LineScoreHandler ls)
    {
        this.soHome = ls.getHome();
        this.soAway = ls.getAway();
    }

    public void setR(int home, int away)
    {
        this.rHome = home;
        this.rAway = away;
    }

    public void setR(LineScoreHandler ls)
    {
        this.rHome = ls.getHome();
        this.rAway = ls.getAway();
    }

    public void setSb(int home, int away)
    {
        this.sbHome = home;
        this.sbAway = away;
    }

    public void setSb(LineScoreHandler ls)
    {
        this.sbHome = ls.getHome();
        this.sbAway = ls.getAway();
    }

    public void setH(LineScoreHandler ls)
    {
        this.hHome = ls.getHome();
        this.hAway = ls.getAway();
    }

    public Date getDate() {
        return gameDate;
    }

    public void setDate(Date date) {
        this.gameDate = date;
    }

    public List<Inning> getInning() {
        return inning;
    }

    public void setInning(List<Inning> inning) {
        this.inning = inning;
    }

    public void addInning(Inning inn)
    {
        if(inning == null)
            inning = new ArrayList<>();
        inning.add(inn);
    }


    public static Game getByIdentifier(String identifier){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("identifier", identifier).findUnique();
    }

    public static Game getClosestMatch(League league)
    {
        Calendar today = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        List<Game> latestGameMatch = finder.where().eq("league", league).ge("game_date",  sdf.format(today.getTime())).orderBy("game_date asc").setMaxRows(1).findList();
        if(!latestGameMatch.isEmpty())
            return latestGameMatch.get(0);
        return null;
    }

    public ObjectNode toJson()
    {
        ObjectNode json = Json.newObject();
        json.put("id_game", idGame.toString());
        json.put("id_league", league.getIdLeague().toString());
        json.put("date", new SimpleDateFormat("yyyyMMddhhmmss").format(gameDate).toString()).toString();
        json.put("home_team",homeTeam.toJson());
        json.put("away_team",awayTeam.toJson());
        json.put("home_run",rHome);

        json.put("away_run",rAway);
        return json;
    }


    public ObjectNode toJsonSimpleDate()
    {
        ObjectNode json = Json.newObject();
        json.put("id_game", idGame.toString());
        json.put("id_league", league.getIdLeague().toString());
        json.put("date",  new SimpleDateFormat("yyyyMMddhhmmss").format(gameDate).toString()).toString(); // DateAndTime.getDate(new SimpleDateFormat("yyyyMMdd").format(gameDate.toString()), "yyyyMMdd"));
        json.put("home_team",homeTeam.toJson());
        json.put("away_team",awayTeam.toJson());
        json.put("home_run",rHome);
        json.put("away_run",rAway);
        ObjectNode aux = Json.newObject();
        Status tmp = Status.convertToFootball(this.status);
        aux.put("id_status", tmp.getIdStatus());
        aux.put("name", tmp.getName());
        json.put("status", aux);
        return json;
    }





}
