package backend.jobs.opta.repository;

import backend.jobs.opta.webentity.matches.MatchesRequest;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.table.TableRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import com.google.gson.Gson;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class OptaRepository {
    public String auth = "13xo8a74h96z61ke2931rmv5my";

    public TournamentCalendarRequest GetTournamentCalendar() {
        WSRequestHolder holder = WS.url(generateRoute("tournamentcalendar") + "active/authorized?_rt=b&_fmt=json");
        String json = holder.get().get(15000).getBody();
        TournamentCalendarRequest data = new Gson().fromJson(json, TournamentCalendarRequest.class);
        return data;
    }

    public TeamRequest GetTeams(TournamentCalendarWebEntity competitionWebEntity) {
        WSRequestHolder holder = WS.url(generateRoute("team") + "?_rt=b&_fmt=json&detailed=yes&tmcl=" + competitionWebEntity.getId());
        String json = holder.get().get(15000).getBody();
        return  new Gson().fromJson(json, TeamRequest.class);
    }

    public MatchesRequest GetMatches(TournamentCalendarWebEntity competitionWebEntity) {

        Calendar cal = Calendar.getInstance();


        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        cal.add(Calendar.DAY_OF_MONTH, -5);
        String start = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 18);
        String end = sdf.format(cal.getTime());

        String route = generateRoute("match");
        WSRequestHolder holder = WS.url(route);
        holder = holder.setTimeout(1000)
                .setQueryParameter("_fmt", "json")
                .setQueryParameter("live", "yes")
                .setQueryParameter("_rt", "b")
                .setQueryParameter("tmcl", competitionWebEntity.getId())
                .setQueryParameter("mt.mDt", "[" + start + " TO " + end + "]");
        String json = holder.get().get(15000).getBody();
        return  new Gson().fromJson(json, MatchesRequest.class);
    }

    public TableRequest GetTables(String competitionType) {
        WSRequestHolder holder = WS.url(generateRoute("standings") + "?_fmt=json&_rt=b&tmcl=" + competitionType);
        String json = holder.get().get(15000).getBody();
        return  new Gson().fromJson(json, TableRequest.class);
    }

    public String generateRoute(String action){
        return String.format("http://api.performfeeds.com/soccerdata/%s/%s/", action, auth);
    }
}
