package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sorcerer on 10/27/14.
 */
@Entity
@Table(name="scorers", uniqueConstraints = @UniqueConstraint(columnNames = {"id_competition, external_id"}))
public class Scorer extends FootballModel {

    @Id
    private Integer idScorer;
    private String name;
    private String fullName;
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "id_teams")
    private Team team;
    private Integer goals;
    private Integer byplay;
    private Integer header;
    private Integer freeKick;
    private Integer penalty;
    @ManyToOne
    @JoinColumn(name = "id_country")
    private Countries country;

    private String externalId;

    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition comp;
    private String date;

    private static Model.Finder<Long,Scorer> finder =
            new Model.Finder<Long, Scorer>(Long.class, Scorer.class);

    public Scorer(String name, String fullName, String nickname, Team team, Integer goals, Integer byplay,
                  Integer header, Integer freeKick, Integer penalty, Countries country, String externalId,
                  Competition comp, String date) {
        this.name = name;
        this.fullName = fullName;
        this.nickname = nickname;
        this.team = team;
        this.goals = goals;
        this.byplay = byplay;
        this.header = header;
        this.freeKick = freeKick;
        this.penalty = penalty;
        this.country = country;
        this.externalId = externalId;
        this.comp = comp;
        this.date = date;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode tr = Json.newObject();
        tr.put("idScorer", idScorer);
        tr.put("name",name);
        tr.put("fullName",fullName);
        tr.put("nickname",nickname);
        //tr.put("idTeam",idTeam); //team info
        tr.put("team",team.toJson());
        tr.put("goals",goals);
        tr.put("byplay",byplay);
        tr.put("header",header);
        tr.put("freeKick",freeKick);
        tr.put("penalty",penalty);
        //tr.put("idCountry",idCountry); //country info
        tr.put("idCompetition", comp.getIdCompetitions());
        tr.put("externalId",externalId);
        return tr;
    }

    public static List<Scorer> getTournamentScorers(Long idCompetition, String date){
        return  finder.where().eq("id_competition", idCompetition).eq("date",date).orderBy("").findList();
    }

    public static List<Scorer> getTournamentScorers(Long idCompetition){
        return finder.where().eq("id_competition", idCompetition).setFirstRow(0).setMaxRows(10).orderBy("goals desc").findList();
    }

    public static List<Scorer> getTournamentScorers(Long idCompetition, int page, int pageSize){
        return finder.where().eq("id_competition", idCompetition).setFirstRow(page).setMaxRows(pageSize).orderBy("goals desc").findList();
    }

    public static Scorer getScorer(Competition competition, String externalId){
        return finder.where().eq("comp", competition).eq("external_id", externalId).findUnique();
    }

    /**
     * funcionn para validar al goleador
     */
    public void validateScorer(){
        try {
            Scorer toValidate = getScorer(this.comp, ""+this.externalId);
            if (toValidate != null){// if exist have to update
                this.setIdScorer(toValidate.idScorer);
                this.update();
            }else {
                this.save();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    /**************************** GETTERS AND SETTERS ****************************************************/


    public Integer getIdScorer() {
        return idScorer;
    }

    public void setIdScorer(Integer idScorer) {
        this.idScorer = idScorer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getByplay() {
        return byplay;
    }

    public void setByplay(Integer byplay) {
        this.byplay = byplay;
    }

    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }

    public Integer getFreeKick() {
        return freeKick;
    }

    public void setFreeKick(Integer freeKick) {
        this.freeKick = freeKick;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Competition getCompetition() {
        return comp;
    }

    public void setCompetition(Competition comp) {
        this.comp = comp;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }
}
