package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 5/28/14.
 */
@Entity
@Table(name="ranking", uniqueConstraints = @UniqueConstraint(columnNames = {"id_phases, id_teams"}))
public class Rank  extends FootballModel {

    @Id
    private long idRanking;

    @ManyToOne
    @JoinColumn(name = "id_phases")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_teams")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    private long matches;
    private long matchesWon;
    private long matchesDraw;
    private long matchesLost;
    private long points;
    private long goalsFor;
    private long goalAgainst;

    private int matchesLocal;
    private int matchesVisitor;
    private int matchesLocalWon;
    private int matchesLocalDraw;
    private int matchesLocalLost;
    private int matchesVisitorWon;
    private int matchesVisitorDraw;
    private int matchesVisitorLost;

    private int goalsForLocal;
    private int goalAgainstLocal;

    private int goalsForVisitor;
    private int goalAgainstVisitor;

    private int goalDiff;

    private int pointsLocal;
    private int pointsVisitor;
    private int yellowCards;
    private int redCards;
    private int doubleYellowCard;

    private int penaltyFouls;
    private int penaltyHands;

    private int foulsCommited;
    private int foulsReceived;
    private int penaltyFoulsReceived;

    private int nivel;
    private String nivelDesc;

    private int orden;
    private String ordenDesc;

    //tribunal de disciplina

    private String streak;

    //private Long idCompetition;

    /*
    <puntosanterior1>0</puntosanterior1>
    <jugadosanterior1>0</jugadosanterior1>
    <puntosanterior2>0</puntosanterior2>
    <jugadosanterior2>0</jugadosanterior2>
    <puntosactual>56</puntosactual>
    <difgolactual>25</difgolactual>
    <jugadosactual>26</jugadosactual>
    <puntosdescenso>0</puntosdescenso>
    <jugadosdescenso>0</jugadosdescenso>
    <promediodescenso>0</promediodescenso>
    <promediodescenso2>0</promediodescenso2>
    <descripcionTribDisc/>
    <partidosDefTribDisc/>
    */

    public static Model.Finder<Long,Rank> finder = new Model.Finder<Long,Rank>(Long.class,Rank.class);

    public Rank(Phase phase, Team team, long matches, long matchesWon, long matchesDraw, long matchesLost,
                long points, long goalsFor, long goalAgainst,int matchesLocal, int matchesVisitor,
                int matchesLocalWon, int matchesLocalDraw, int matchesLocalLost, int matchesVisitorWon,
                int matchesVisitorDraw, int matchesVisitorLost, int goalsForLocal, int goalAgainstLocal,
                int goalsForVisitor, int goalAgainstVisitor, int goalDiff, int pointsLocal, int pointsVisitor,
                int yellowCards, int redCards, int doubleYellowCard, int penaltyFouls, int penaltyHands,
                int foulsCommited, int foulsReceived, int penaltyFoulsReceived, int nivel, String nivelDesc,
                int orden, String ordenDesc, String streak, Group group) {
        this.phase = phase;
        this.team = team;
        this.matches = matches;
        this.matchesWon = matchesWon;
        this.matchesDraw = matchesDraw;
        this.matchesLost = matchesLost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalAgainst = goalAgainst;

        this.matchesLocal = matchesLocal;
        this.matchesVisitor = matchesVisitor;
        this.matchesLocalWon = matchesLocalWon;
        this.matchesLocalDraw = matchesLocalDraw;
        this.matchesLocalLost = matchesLocalLost;
        this.matchesVisitorWon = matchesVisitorWon;
        this.matchesVisitorDraw = matchesVisitorDraw;
        this.matchesVisitorLost = matchesVisitorLost;
        this.goalsForLocal = goalsForLocal;
        this.goalAgainstLocal = goalAgainstLocal;
        this.goalsForVisitor = goalsForVisitor;
        this.goalAgainstVisitor = goalAgainstVisitor;
        this.goalDiff = goalDiff;
        this.pointsLocal = pointsLocal;
        this.pointsVisitor = pointsVisitor;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.doubleYellowCard = doubleYellowCard;
        this.penaltyFouls = penaltyFouls;
        this.penaltyHands = penaltyHands;
        this.foulsCommited = foulsCommited;
        this.foulsReceived = foulsReceived;
        this.penaltyFoulsReceived = penaltyFoulsReceived;
        this.nivel = nivel;
        this.nivelDesc = nivelDesc;
        this.orden = orden;
        this.ordenDesc = ordenDesc;
        this.streak = streak;
        this.group = group;
    }

    public Rank(Phase phase, Team team, Group group, long matches, long matchesWon, long matchesDraw, long matchesLost,
                long points, long goalsFor, long goalAgainst) {
        this.phase = phase;
        this.team = team;
        this.group = group;
        this.matches = matches;
        this.matchesWon = matchesWon;
        this.matchesDraw = matchesDraw;
        this.matchesLost = matchesLost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalAgainst = goalAgainst;
        Long diff = goalsFor - goalAgainst;
        this.goalDiff = diff.intValue();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("id_ranking",idRanking);
        node.put("phase",phase.toJson());
        node.put("team",team.toJson());
        node.put("matches",matches);
        node.put("matches_won",matchesWon);
        node.put("matches_drawn", matchesDraw);
        node.put("matches_lost",matchesLost);
        node.put("points",points);
        node.put("goals_for",goalsFor);
        node.put("goal_against",goalAgainst);

        return node;
    }

    public ObjectNode toJsonPhaseID() {
        ObjectNode node = Json.newObject();
        node.put("id_ranking",idRanking);
        node.put("phase",phase.getIdPhases());
        node.put("team",team.toJson());
        node.put("matches",matches);
        node.put("matches_won",matchesWon);
        node.put("matches_drawn", matchesDraw);
        node.put("matches_lost",matchesLost);
        node.put("points",points);
        node.put("goals_for",goalsFor);
        node.put("goal_against",goalAgainst);
        node.put("nivel",nivel);
        node.put("orden",orden);
        return node;
    }

    public long getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(long idRanking) {
        this.idRanking = idRanking;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getMatches() {
        return matches;
    }

    public void setMatches(long matches) {
        this.matches = matches;
    }

    public long getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(long matchesWon) {
        this.matchesWon = matchesWon;
    }

    public long getMatchesDraw() {
        return matchesDraw;
    }

    public void setMatchesDraw(long matchesDraw) {
        this.matchesDraw = matchesDraw;
    }

    public long getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(long matchesLost) {
        this.matchesLost = matchesLost;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public long getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(long goalsFor) {
        this.goalsFor = goalsFor;
    }

    public long getGoalAgainst() {
        return goalAgainst;
    }

    public void setGoalAgainst(long goalAgainst) {
        this.goalAgainst = goalAgainst;
    }

    public int getMatchesLocal() {
        return matchesLocal;
    }

    public void setMatchesLocal(int matchesLocal) {
        this.matchesLocal = matchesLocal;
    }

    public int getMatchesVisitor() {
        return matchesVisitor;
    }

    public void setMatchesVisitor(int matchesVisitor) {
        this.matchesVisitor = matchesVisitor;
    }

    public int getMatchesLocalWon() {
        return matchesLocalWon;
    }

    public void setMatchesLocalWon(int matchesLocalWon) {
        this.matchesLocalWon = matchesLocalWon;
    }

    public int getMatchesLocalDraw() {
        return matchesLocalDraw;
    }

    public void setMatchesLocalDraw(int matchesLocalDraw) {
        this.matchesLocalDraw = matchesLocalDraw;
    }

    public int getMatchesLocalLost() {
        return matchesLocalLost;
    }

    public void setMatchesLocalLost(int matchesLocalLost) {
        this.matchesLocalLost = matchesLocalLost;
    }

    public int getMatchesVisitorWon() {
        return matchesVisitorWon;
    }

    public void setMatchesVisitorWon(int matchesVisitorWon) {
        this.matchesVisitorWon = matchesVisitorWon;
    }

    public int getMatchesVisitorDraw() {
        return matchesVisitorDraw;
    }

    public void setMatchesVisitorDraw(int matchesVisitorDraw) {
        this.matchesVisitorDraw = matchesVisitorDraw;
    }

    public int getMatchesVisitorLost() {
        return matchesVisitorLost;
    }

    public void setMatchesVisitorLost(int matchesVisitorLost) {
        this.matchesVisitorLost = matchesVisitorLost;
    }

    public int getGoalsForLocal() {
        return goalsForLocal;
    }

    public void setGoalsForLocal(int goalsForLocal) {
        this.goalsForLocal = goalsForLocal;
    }

    public int getGoalAgainstLocal() {
        return goalAgainstLocal;
    }

    public void setGoalAgainstLocal(int goalAgainstLocal) {
        this.goalAgainstLocal = goalAgainstLocal;
    }

    public int getGoalsForVisitor() {
        return goalsForVisitor;
    }

    public void setGoalsForVisitor(int goalsForVisitor) {
        this.goalsForVisitor = goalsForVisitor;
    }

    public int getGoalAgainstVisitor() {
        return goalAgainstVisitor;
    }

    public void setGoalAgainstVisitor(int goalAgainstVisitor) {
        this.goalAgainstVisitor = goalAgainstVisitor;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public int getPointsLocal() {
        return pointsLocal;
    }

    public void setPointsLocal(int pointsLocal) {
        this.pointsLocal = pointsLocal;
    }

    public int getPointsVisitor() {
        return pointsVisitor;
    }

    public void setPointsVisitor(int pointsVisitor) {
        this.pointsVisitor = pointsVisitor;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getDoubleYellowCard() {
        return doubleYellowCard;
    }

    public void setDoubleYellowCard(int doubleYellowCard) {
        this.doubleYellowCard = doubleYellowCard;
    }

    public int getPenaltyFouls() {
        return penaltyFouls;
    }

    public void setPenaltyFouls(int penaltyFouls) {
        this.penaltyFouls = penaltyFouls;
    }

    public int getPenaltyHands() {
        return penaltyHands;
    }

    public void setPenaltyHands(int penaltyHands) {
        this.penaltyHands = penaltyHands;
    }

    public int getFoulsCommited() {
        return foulsCommited;
    }

    public void setFoulsCommited(int foulsCommited) {
        this.foulsCommited = foulsCommited;
    }

    public int getFoulsReceived() {
        return foulsReceived;
    }

    public void setFoulsReceived(int foulsReceived) {
        this.foulsReceived = foulsReceived;
    }

    public int getPenaltyFoulsReceived() {
        return penaltyFoulsReceived;
    }

    public void setPenaltyFoulsReceived(int penaltyFoulsReceived) {
        this.penaltyFoulsReceived = penaltyFoulsReceived;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNivelDesc() {
        return nivelDesc;
    }

    public void setNivelDesc(String nivelDesc) {
        this.nivelDesc = nivelDesc;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getOrdenDesc() {
        return ordenDesc;
    }

    public void setOrdenDesc(String ordenDesc) {
        this.ordenDesc = ordenDesc;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static List<Rank> getListByIdPhase(String idPhase){
        return finder.where().eq("id_phases", idPhase).orderBy("nivel asc, orden asc").findList();
    }

    public static List<Rank> getListByListPhase(ArrayList phases){
        return finder.where().in("id_phases", phases).orderBy("nivel asc, orden asc").findList();
    }

    public static List<Rank> getListByExtIdPhase(String idExtPhase){
        Phase _phase = Phase.findByExtId(idExtPhase);
        if(_phase == null)
            return null;

        return finder.where().eq("id_phases", _phase.getIdPhases()).orderBy("nivel asc, orden asc").findList();
    }

    public static Rank getRankBySomething(long idFase, long idTeam){
        return finder.where().eq("id_phases",idFase).eq("id_teams", idTeam).findUnique();
    }

    public void validateRank(){
        try {
            //validate using id_phase
            Rank tr = getRankBySomething(this.getPhase().getIdPhases(),this.team.getIdTeams());
            if (tr != null){
                this.setIdRanking(tr.idRanking);
                this.update();
                //update selected values//
            }else {
                //insert
                this.save();
            }
        } catch (Exception ex){
            //do nothing
        } 
    }


}
