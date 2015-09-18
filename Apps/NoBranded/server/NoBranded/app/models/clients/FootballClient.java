package models.clients;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import comparators.LeaderboardComparator;
import comparators.LeaderboardGlobalComparator;
import models.HecticusModel;
import models.Config;
import models.basic.Country;
import models.basic.Language;
import models.leaderboard.*;
import models.pushalerts.ClientHasPushAlerts;
import models.pushalerts.PushAlerts;
import org.apache.commons.codec.binary.Base64;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;
import utils.Utils;

import javax.persistence.*;
import java.util.*;

/**
 * Created by plesse on 9/30/14.
 */
@Entity
@DiscriminatorValue("football")
public class FootballClient extends Client {

    protected String clientType;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<ClientHasPushAlerts> pushAlerts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
    @OrderBy("idTournament asc, idPhase asc")
    private List<Leaderboard> leaderboards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
    @OrderBy("idTournament asc, score desc")
    private List<LeaderboardGlobal> leaderboardGlobal;

    @OneToOne(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
    private LeaderboardTotal leaderboardTotal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade = CascadeType.ALL)
    @OrderBy("idTournament asc, score desc")
    private List<LeaderboardPush> leaderboardPushs;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<ClientBets> clientBets;

    public static Model.Finder<Integer, FootballClient> finder = new Model.Finder<>(Integer.class, FootballClient.class);

    public FootballClient(Integer status, String login, String password, Country country, Language language) {
        super(status, login, password, country, language);
    }

    public FootballClient(String userId, Integer status, String login, String password, Country country, Language language) {
        super(userId, status, login, password, country, language);
    }

    public FootballClient(Integer status, String login, String password, Country country, String lastCheckDate, Language language) {
        super(status, login, password, country, lastCheckDate, language);
    }

    public FootballClient(String userId, Integer status, String login, String password, Country country, String lastCheckDate, Language language) {
        super(userId, status, login, password, country, lastCheckDate, language);
    }

    public List<ClientHasPushAlerts> getPushAlerts() {
        return pushAlerts;
    }

    public void setPushAlerts(List<ClientHasPushAlerts> pushAlerts) {
        this.pushAlerts = pushAlerts;
    }

    public List<Leaderboard> getLeaderboards() {
        Collections.sort(leaderboards, new LeaderboardComparator());
        return leaderboards;
    }

    public void setLeaderboards(List<Leaderboard> leaderboards) {
        this.leaderboards = leaderboards;
    }

    public List<LeaderboardGlobal> getLeaderboardGlobal() {
        Collections.sort(leaderboardGlobal, new LeaderboardGlobalComparator());
        return leaderboardGlobal;
    }

    public void setLeaderboardGlobal(List<LeaderboardGlobal> leaderboardGlobal) {
        this.leaderboardGlobal = leaderboardGlobal;
    }

    public List<ClientBets> getClientBets() {
        return clientBets;
    }

    public void setClientBets(List<ClientBets> clientBets) {
        this.clientBets = clientBets;
    }

    public LeaderboardTotal getLeaderboardTotal() {
        return leaderboardTotal;
    }

    public void setLeaderboardTotal(LeaderboardTotal leaderboardTotal) {
        this.leaderboardTotal = leaderboardTotal;
    }

    public List<LeaderboardPush> getLeaderboardPushs() {
        return leaderboardPushs;
    }

    public void setLeaderboardPushs(List<LeaderboardPush> leaderboardPushs) {
        this.leaderboardPushs = leaderboardPushs;
    }

    public int getPushAlertIndex(final int pushAlertId) {
        final PushAlerts pushAlert = PushAlerts.finder.where().eq("idExt", pushAlertId).findUnique();
        if(pushAlert == null){
            return -1;
        }
        ClientHasPushAlerts clientHasPushAlert = null;
        try {
            clientHasPushAlert = Iterables.find(pushAlerts, new Predicate<ClientHasPushAlerts>() {
                public boolean apply(ClientHasPushAlerts obj) {
                    return obj.getPushAlert().getIdPushAlert().intValue() == pushAlert.getIdPushAlert().intValue();
                }
            });
        } catch (NoSuchElementException ex){
            clientHasPushAlert = null;
        }
        if(clientHasPushAlert == null){
            return -1;
        }
        return pushAlerts.indexOf(clientHasPushAlert);
    }

    public int getPushAlertIDIndex(final int pushAlertId) {
        final PushAlerts pushAlert = PushAlerts.finder.byId(pushAlertId);
        if(pushAlert == null){
            return -1;
        }
        ClientHasPushAlerts clientHasPushAlert = null;
        try {
            clientHasPushAlert = Iterables.find(pushAlerts, new Predicate<ClientHasPushAlerts>() {
                public boolean apply(ClientHasPushAlerts obj) {
                    return obj.getPushAlert().getIdPushAlert().intValue() == pushAlert.getIdPushAlert().intValue();
                }
            });
        } catch (NoSuchElementException ex){
            clientHasPushAlert = null;
        }
        if(clientHasPushAlert == null){
            return -1;
        }
        return pushAlerts.indexOf(clientHasPushAlert);
    }

    public void addClientBet(ClientBets bet){
        if(bet.getIdClientBets() != null && clientBets.contains(bet)){
            clientBets.remove(bet);
        }
        clientBets.add(bet);
    }

    public Map<Integer, ClientBets> getClientBetsAsMap(){
        Map<Integer, ClientBets> betsMap = new HashMap<>();
        for(ClientBets clientBets1 : clientBets){
            betsMap.put(clientBets1.getIdGameMatch(), clientBets1);
        }
        return betsMap;
    }

    public Leaderboard getLeaderboard(final int idTournament, final int idPhase){
        Leaderboard tr = null;
        try {
            tr = Iterables.find(leaderboards, new Predicate<Leaderboard>() {
                public boolean apply(Leaderboard obj) {
                    return obj.getIdTournament().intValue() ==  idTournament && obj.getIdPhase().intValue() == idPhase;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public List<Leaderboard> getLeaderboard(final int idTournament){
        List<Leaderboard> tr;
        try {
            Predicate<Leaderboard> validObjs = new Predicate<Leaderboard>() {
                public boolean apply(Leaderboard obj) {
                    return obj.getIdTournament().intValue() == idTournament;
                }
            };
            Collection<Leaderboard> result = Utils.filterCollection(leaderboards, validObjs);
            tr = (List<Leaderboard>) result;
            Collections.sort(tr, new LeaderboardComparator());
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public void addLeaderboard(final Leaderboard leaderboard){
        Leaderboard tr = null;
        try {
            tr = Iterables.find(leaderboards, new Predicate<Leaderboard>() {
                public boolean apply(Leaderboard obj) {
                    return (obj.getIdTournament().intValue() == leaderboard.getIdTournament()) && (obj.getIdPhase().intValue() == leaderboard.getIdTournament());
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }

        if(tr != null){
            leaderboards.remove(tr);
        }
        leaderboards.add(leaderboard);
    }

    public LeaderboardGlobal getLeaderboardGlobal(final int idTournament){
        LeaderboardGlobal tr = null;
        try {
            tr = Iterables.find(leaderboardGlobal, new Predicate<LeaderboardGlobal>() {
                public boolean apply(LeaderboardGlobal obj) {
                    return obj.getIdTournament().intValue() ==  idTournament;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public ClientBets getBet(final int idTournament, final int idPhase, final int idGameMatch){
        ClientBets tr = null;
        try {
            tr = Iterables.find(clientBets, new Predicate<ClientBets>() {
                public boolean apply(ClientBets obj) {
                    return obj.getIdTournament().intValue() ==  idTournament && obj.getIdPhase().intValue() == idPhase && obj.getIdGameMatch().intValue() == idGameMatch;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public void addLeaderboardGlobal(LeaderboardGlobal newLeaderboardGlobal) {
        leaderboardGlobal.add(newLeaderboardGlobal);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_client", idClient);
        response.put("facebook_id", facebookId);
        response.put("nickname", nickname);
        response.put("user_id", userId);
        response.put("login", login);
        response.put("status", status);
        response.put("session", session);
        response.put("last_check_date", lastCheckDate);
        response.put("auth_token", getAuthToken());
        response.put("country", country.toJsonSimple());
        response.put("language", language.toJson());
        if(devices != null && !devices.isEmpty()){
            ArrayList<ObjectNode> apps = new ArrayList<>();
            for(ClientHasDevices ad : devices){
                apps.add(ad.toJsonWithoutClient());
            }
            response.put("devices", Json.toJson(apps));
        }

        ArrayList<ObjectNode> alertsInfo = new ArrayList<>();
        ArrayList<ObjectNode> alertsTeams = new ArrayList<>();
        if(pushAlerts != null && !pushAlerts.isEmpty()){
            for(ClientHasPushAlerts ad : pushAlerts){
                if(ad.getPushAlert().getIdExt() > 0) {
                    alertsTeams.add(ad.toJsonSimple());
                } else {
                    alertsInfo.add(ad.toJsonSimple());
                }
            }
        }
        response.put("push_alerts_teams", Json.toJson(alertsTeams));
        response.put("push_alerts_info", Json.toJson(alertsInfo));

        ArrayList<ObjectNode> leaderBoard = new ArrayList<>();
        if(leaderboards != null && !leaderboards.isEmpty()){
            for(Leaderboard ad : leaderboards){
                leaderBoard.add(ad.toJsonClean());
            }
        }
        response.put("leaderboards", Json.toJson(leaderBoard));

        ArrayList<ObjectNode> leaderBoardGlobal = new ArrayList<>();
        if(leaderboardGlobal != null && !leaderboardGlobal.isEmpty()){
            for(LeaderboardGlobal ad : leaderboardGlobal){
                leaderBoardGlobal.add(ad.toJsonClean());
            }
        }
        response.put("leaderboard_global", Json.toJson(leaderBoardGlobal));

        if(leaderboardTotal != null) {
            response.put("leaderboard_total", leaderboardTotal.toJsonClean());
        }

        return response;
    }

}

