package backend.jobs.opta.repository;

import backend.jobs.opta.webentity.matches.MatchesRequest;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.table.TableRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import com.google.gson.Gson;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

public class OptaRepository {
    public String auth = "13xo8a74h96z61ke2931rmv5my";

    public TournamentCalendarRequest GetTournamentCalendar() {
        WSRequestHolder holder = WS.url(generateRoute("tournamentcalendar") + "active/authorized?_rt=b&_fmt=json");
        String json = holder.get().get(3000).getBody();
        TournamentCalendarRequest data = new Gson().fromJson(json, TournamentCalendarRequest.class);
        return data;
    }

    public TeamRequest GetTeams(TournamentCalendarWebEntity competitionWebEntity) {
        WSRequestHolder holder = WS.url(generateRoute("team") + "?_rt=b&_fmt=json&detailed=yes&tmcl=" + competitionWebEntity.getId());
        String json = holder.get().get(3000).getBody();
        return  new Gson().fromJson(json, TeamRequest.class);
    }

    public MatchesRequest GetMatches(TournamentCalendarWebEntity competitionWebEntity) {
        WSRequestHolder holder = WS.url(generateRoute("match") + "?_fmt=json&live=yes&_rt=b&tmcl=" + competitionWebEntity.getId());
        String json = holder.get().get(3000).getBody();
        return  new Gson().fromJson(json, MatchesRequest.class);
    }

    public TableRequest GetTables(String competitionType) {
        WSRequestHolder holder = WS.url(generateRoute("standings") + "?_fmt=json&_rt=b&tmcl=" + competitionType);
        String json = holder.get().get(3000).getBody();
        return  new Gson().fromJson(json, TableRequest.class);
    }

    public String generateRoute(String action){
        return String.format("http://api.performfeeds.com/soccerdata/%s/%s/", action, auth);
    }
}
