package caches;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import models.Config;
import models.clients.Client;
import models.clients.FootballClient;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by plesse on 1/23/15.
 */
public class ClientsCache {

    private static ClientsCache me;
    private static long CACHE_TIMEOUT;
    private LoadingCache<Integer, Map<Integer, ArrayList<Integer>>> teamClientCache;

    private LoadingCache<Integer, Map<Integer, ArrayList<Integer>>> tournamentClientCache;

    public ClientsCache() {
        CACHE_TIMEOUT = Config.getLong("guava-caches-update-delay");
        teamClientCache = CacheBuilder.newBuilder().refreshAfterWrite(CACHE_TIMEOUT, TimeUnit.MINUTES).build(
                new CacheLoader<Integer, Map<Integer, ArrayList<Integer>>>(){
                    @Override
//                    public ArrayList<Integer> load(Integer k) throws Exception {
                    public Map<Integer, ArrayList<Integer>> load(Integer k) throws Exception {
                        return getClientFromDB(k);
                    }
                });
        tournamentClientCache = CacheBuilder.newBuilder().refreshAfterWrite(CACHE_TIMEOUT, TimeUnit.MINUTES).build(
                new CacheLoader<Integer, Map<Integer, ArrayList<Integer>>>(){
                    @Override
//                    public ArrayList<Integer> load(Integer k) throws Exception {
                    public Map<Integer, ArrayList<Integer>> load(Integer k) throws Exception {
                        return getTournamentClientFromDB(k);
                    }
                });
    }

    public static ClientsCache getInstance() {
        if (me == null) {
            me = new ClientsCache();
        }
        return me;
    }

    public Map<Integer, ArrayList<Integer>> getTeamClients(int k) throws MalformedURLException, HTTPException, IOException, Exception {
        return teamClientCache.get(k);
    }

    public Map<Integer, ArrayList<Integer>> getTournamentClients(int k) throws MalformedURLException, HTTPException, IOException, Exception {
        return tournamentClientCache.get(k);
    }

    private Map<Integer, ArrayList<Integer>> getClientFromDB(Integer k) {
        Map<Integer, ArrayList<Integer>> cl = new HashMap<>();
        List<FootballClient> pushAlerts = FootballClient.finder.where().eq("pushAlerts.pushAlert.idExt", k).eq("pushAlerts.status", 1).orderBy("idClient asc").findList();
        for(FootballClient client : pushAlerts){
            if(!cl.containsKey(client.getLanguage().getIdLanguage())){
                cl.put(client.getLanguage().getIdLanguage(), new ArrayList<Integer>());
            }
            cl.get(client.getLanguage().getIdLanguage()).add(client.getIdClient());
        }
        return cl;
    }


    private Map<Integer, ArrayList<Integer>> getTournamentClientFromDB(Integer k) {
        Map<Integer, ArrayList<Integer>> cl = new HashMap<>();
        List<FootballClient> pushAlerts = null;
        if(k == -1){
            pushAlerts = FootballClient.finder.where().gt("status", 0).eq("pushAlerts.pushAlert.idPushAlert", Config.getInt("news-push-id")).eq("pushAlerts.status", 1).orderBy("idClient asc").findList();
        } else {
            pushAlerts = FootballClient.finder.where().eq("leaderboards.idTournament", k).orderBy("idClient asc").findList();
        }
        for(FootballClient client : pushAlerts){
            if(!cl.containsKey(client.getLanguage().getIdLanguage())){
                cl.put(client.getLanguage().getIdLanguage(), new ArrayList<Integer>());
            }
            cl.get(client.getLanguage().getIdLanguage()).add(client.getIdClient());
        }
        return cl;
    }

}
