package models.leaderboard;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chrirod on 10/30/14.
 */
@Entity
@Table(name="leaderboard")
public class Leaderboard extends HecticusModel{

    @Id
    private Long idLeaderboard;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Constraints.Required
    private Integer idTournament;
    @Constraints.Required
    private Integer idPhase;

    private Integer score;

    private Integer correctBets;

    public static Model.Finder<Integer, Leaderboard> finder = new Model.Finder<Integer, Leaderboard>(Integer.class, Leaderboard.class);

    public Leaderboard(Client client, Integer idTournament, Integer idPhase, Integer score, Integer correctBets) {
        this.client = client;
        this.idTournament = idTournament;
        this.score = score;
        this.idPhase = idPhase;
        this.correctBets = correctBets;
    }

    public Long getIdLeaderboard() {
        return idLeaderboard;
    }

    public void setIdLeaderboard(Long idLeaderboard) {
        this.idLeaderboard = idLeaderboard;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(Integer idTournament) {
        this.idTournament = idTournament;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Integer idPhase) {
        this.idPhase = idPhase;
    }

    public Integer getCorrectBets() {
        return correctBets;
    }

    public void setCorrectBets(Integer correctBets) {
        this.correctBets = correctBets;
    }

    public void increaseCorrectBets() {
        this.correctBets++;
    }

    public static Leaderboard getLeaderboardByClientAndTournament(Integer idClient, Integer idTournament){
        return finder.where().eq("id_client", idClient).eq("id_tournament",idTournament).findUnique();
    }

    public static List<Leaderboard> getLeaderboardsByClient(Integer idClient){
        return finder.where().eq("id_client", idClient).orderBy("id_tournament").findList();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_leaderboard",idLeaderboard);
        objNode.put("client", client.toJsonWithoutRelations());
        objNode.put("id_tournament", idTournament);
        objNode.put("score", score);
        objNode.put("hits", correctBets);
        return objNode;
    }

    public ObjectNode toJsonClean() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_tournament", idTournament);
        objNode.put("id_phase", idPhase);
        objNode.put("score", score);
        objNode.put("hits", correctBets);
        return objNode;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_client", client.getIdClient());
        String nickname = client.getNickname();
        objNode.put("client", nickname==null?"Anônimo":nickname);
        objNode.put("score", score);
        objNode.put("hits", correctBets);
        return objNode;
    }
}
