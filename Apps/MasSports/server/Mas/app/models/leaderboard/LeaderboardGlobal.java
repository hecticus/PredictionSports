package models.leaderboard;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by chrirod on 10/30/14.
 */
@Entity
@Table(name="leaderboard_global")
public class LeaderboardGlobal extends HecticusModel{

    @Id
    private Long idLeaderboardGlobal;
    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Constraints.Required
    private Integer idTournament;
    private Integer score;

    private Integer correctBets;

    public static Finder<Integer, LeaderboardGlobal> finder = new Finder<Integer, LeaderboardGlobal>(Integer.class, LeaderboardGlobal.class);

    public LeaderboardGlobal(Client client, Integer idTournament, Integer score, Integer correctBets) {
        this.client = client;
        this.score = score;
        this.idTournament = idTournament;
        this.correctBets = correctBets;
    }

    public static LeaderboardGlobal getLeaderboardByClient(Integer idClient){
        return finder.where().eq("id_client", idClient).findUnique();
    }

    public Long getIdLeaderboardGlobal() {
        return idLeaderboardGlobal;
    }

    public void setIdLeaderboardGlobal(Long idLeaderboardGlobal) {
        this.idLeaderboardGlobal = idLeaderboardGlobal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(Integer idTournament) {
        this.idTournament = idTournament;
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

    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_leaderboard_global",idLeaderboardGlobal);
        objNode.put("client", client.toJsonWithoutRelations());
        objNode.put("id_tournament", idTournament);
        objNode.put("score", score);
        objNode.put("hits", correctBets);
        return objNode;
    }

    public ObjectNode toJsonClean() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_tournament", idTournament);
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
