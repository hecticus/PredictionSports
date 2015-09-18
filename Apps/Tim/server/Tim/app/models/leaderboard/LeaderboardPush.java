package models.leaderboard;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by plesse on 3/2/15.
 */
@Entity
@Table(name="leaderboard_push")
public class LeaderboardPush extends HecticusModel {

    @Id
    private Long idLeaderboardPush;
    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;
    private Integer score;

    public static Finder<Integer, LeaderboardPush> finder = new Finder<Integer, LeaderboardPush>(Integer.class, LeaderboardPush.class);

    public Long getIdLeaderboardPush() {
        return idLeaderboardPush;
    }

    public void setIdLeaderboardPush(Long idLeaderboardPush) {
        this.idLeaderboardPush = idLeaderboardPush;
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

    @Override
    public ObjectNode toJson() {
        return null;
    }
}
