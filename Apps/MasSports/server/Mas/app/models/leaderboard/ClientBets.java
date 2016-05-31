package models.leaderboard;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import models.clients.Client;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chrirod on 10/30/14.
 */
@Entity
@Table(name="client_bets")
public class ClientBets extends HecticusModel{

    @Id
    private Long idClientBets;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Constraints.Required
    private Integer idTournament;
    @Constraints.Required
    private Integer idPhase;
    @Constraints.Required
    private Integer idGameMatch;

    @Constraints.Required
    private String gameMatchDate;

    private Integer clientBet;
    private Integer status;

    public static Finder<Integer, ClientBets> finder = new Finder<Integer, ClientBets>(Integer.class, ClientBets.class);

    public ClientBets(Client client, Integer idTournament, Integer idPhase, Integer idGameMatch, Integer clientBet, Integer status, String gameMatchDate) {
        this.client = client;
        this.idTournament = idTournament;
        this.idPhase = idPhase;
        this.idGameMatch = idGameMatch;
        this.clientBet = clientBet;
        this.status = status;
        this.gameMatchDate = gameMatchDate;
    }

    public ClientBets(Client client, Integer idTournament, Integer idPhase, Integer idGameMatch, Integer clientBet, String gameMatchDate) {
        this.client = client;
        this.idTournament = idTournament;
        this.idPhase = idPhase;
        this.idGameMatch = idGameMatch;
        this.clientBet = clientBet;
        this.status = 1;
        this.gameMatchDate = gameMatchDate;
    }

    public Long getIdClientBets() {
        return idClientBets;
    }

    public void setIdClientBets(Long idClientBets) {
        this.idClientBets = idClientBets;
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

    public Integer getIdGameMatch() {
        return idGameMatch;
    }

    public void setIdGameMatch(Integer idGameMatch) {
        this.idGameMatch = idGameMatch;
    }

    public Integer getClientBet() {
        return clientBet;
    }

    public void setClientBet(Integer clientBet) {
        this.clientBet = clientBet;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Integer idPhase) {
        this.idPhase = idPhase;
    }

    public String getGameMatchDate() {
        return gameMatchDate;
    }

    public void setGameMatchDate(String gameMatchDate) {
        this.gameMatchDate = gameMatchDate;
    }

    public static ClientBets getClientBetForMatch(Integer idClient, Integer idTournament, Integer idGameMatch){
        return finder.where().eq("id_client", idClient).eq("id_tournament", idTournament).eq("id_game_match", idGameMatch).findUnique();
    }

    public static List<ClientBets> getClientBetsForTournament(Integer idClient, Integer idTournament){
        return finder.where().eq("id_client", idClient).eq("id_tournament", idTournament).orderBy("id_tournament").findList();
    }

    public static List<ClientBets> getAllClientBets(Integer idClient){
        return finder.where().eq("id_client", idClient).orderBy("id_tournament").findList();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_client_bets",idClientBets);
        objNode.put("client", client.toJsonWithoutRelations());
        objNode.put("id_tournament", idTournament);
        objNode.put("id_phase", idPhase);
        objNode.put("id_game_match", idGameMatch);
        objNode.put("client_bet", clientBet);
        objNode.put("status", status);
        return objNode;
    }

    public ObjectNode toJsonNoClient() {
        ObjectNode objNode = Json.newObject();
        objNode.put("id_tournament", idTournament);
        objNode.put("id_phase", idPhase);
        objNode.put("id_game_match", idGameMatch);
        objNode.put("game_match_date", gameMatchDate);
        objNode.put("client_bet", clientBet);
        objNode.put("status", status);
        return objNode;
    }
}
