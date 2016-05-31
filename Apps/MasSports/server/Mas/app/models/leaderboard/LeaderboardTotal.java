package models.leaderboard;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plessmann on 02/06/15.
 */
@Entity
@Table(name="leaderboard_total")
public class LeaderboardTotal extends HecticusModel {

    @Id
    private Long idLeaderboardTotal;
    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;
    private Integer score;

    private Integer correctBets;

    public static Finder<Long, LeaderboardTotal> finder = new Finder<Long, LeaderboardTotal>(Long.class, LeaderboardTotal.class);

    public LeaderboardTotal(Client client, Integer score, Integer correctBets) {
        this.client = client;
        this.score = score;
        this.correctBets = correctBets;
    }

    public Long getIdLeaderboardTotal() {
        return idLeaderboardTotal;
    }

    public void setIdLeaderboardTotal(Long idLeaderboardTotal) {
        this.idLeaderboardTotal = idLeaderboardTotal;
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

    public Integer getCorrectBets() {
        return correctBets;
    }

    public void setCorrectBets(Integer correctBets) {
        this.correctBets = correctBets;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_leaderboard_total",idLeaderboardTotal);
        objNode.put("client", client.toJsonWithoutRelations());
        objNode.put("score", score);
        objNode.put("hits", correctBets);
        return objNode;
    }

    public ObjectNode toJsonClean() {
        ObjectNode objNode = Json.newObject();
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
