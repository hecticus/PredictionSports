package backend.jobs.opta.repository;

import backend.jobs.opta.webentity.matches.MatchesRequest;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.table.TableRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import com.google.gson.Gson;
import models.Config;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class OptaRepository {
    public String auth = "13xo8a74h96z61ke2931rmv5my";

    public TournamentCalendarRequest GetTournamentCalendar() {
        String json = null;
        TournamentCalendarRequest data = null;

        try {
            WSRequestHolder holder = WS.url(generateRoute("tournamentcalendar") + "active/authorized?_rt=b&_fmt=json");
            json = holder.setTimeout(25000).get().get(15000).getBody();
            data = new Gson().fromJson(json, TournamentCalendarRequest.class);
        } catch (Exception e) {
            Utils.printToLog(Utils.class, "", "Error en OPTA TOURNAMENTCALENDAR: " + json, true, e, "support-level-1", Config.LOGGER_ERROR);
        }

        return data;
    }

    public TeamRequest GetTeams(TournamentCalendarWebEntity competitionWebEntity) {
        String json = null;
        TeamRequest data = null;

        try {
            WSRequestHolder holder = WS.url(generateRoute("team") + "?_rt=b&_fmt=json&detailed=yes&tmcl=" + competitionWebEntity.getId());
            json = holder.setTimeout(25000).get().get(15000).getBody();
            data = new Gson().fromJson(json, TeamRequest.class);
        } catch (Exception e) {
            Utils.printToLog(Utils.class, "", "Error en OPTA TEAMS: " + json, true, e, "support-level-1", Config.LOGGER_ERROR);
        }

        return data;
    }

    public MatchesRequest GetMatches(TournamentCalendarWebEntity competitionWebEntity) {
        String json = null;
        MatchesRequest data = null;

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            cal.add(Calendar.DAY_OF_MONTH, 40); // -5
            String start = sdf.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 58);
            String end = sdf.format(cal.getTime());

            String route = generateRoute("match");
            WSRequestHolder holder = WS.url(route);
            holder = holder.setTimeout(25000)
                    .setQueryParameter("_fmt", "json")
                    .setQueryParameter("live", "yes")
                    .setQueryParameter("_rt", "b")
                    .setQueryParameter("tmcl", competitionWebEntity.getId())
                    .setQueryParameter("mt.mDt", "[" + start + " TO " + end + "]");
            json = holder.get().get(15000).getBody();
            data = new Gson().fromJson(json, MatchesRequest.class);
        } catch (Exception e) {
            Utils.printToLog(Utils.class, "", "Error en OPTA MATCHES: " + json, true, e, "support-level-1", Config.LOGGER_ERROR);
        }

        return data;
    }

    public TableRequest GetTables(String competitionType) {
        String json = null;
        TableRequest data = null;

        try {
            WSRequestHolder holder = WS.url(generateRoute("standings") + "?_fmt=json&_rt=b&tmcl=" + competitionType);
            json = holder.setTimeout(25000).get().get(15000).getBody();
            data = new Gson().fromJson(json, TableRequest.class);
        } catch (Exception e) {
            Utils.printToLog(Utils.class, "", "Error en OPTA TABLES: " + json, true, e, "support-level-1", Config.LOGGER_ERROR);
        }

        return data;
    }

    public String generateRoute(String action) {
        return String.format("http://api.performfeeds.com/soccerdata/%s/%s/", action, auth);
    }
}
