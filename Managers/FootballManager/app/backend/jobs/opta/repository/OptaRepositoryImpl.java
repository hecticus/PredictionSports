package backend.jobs.opta.repository;

import backend.jobs.opta.core.OptaRepository;
import backend.jobs.opta.mapper.CompetitionEntityToCompetitionType;
import backend.jobs.opta.mapper.ContestantWebEntityToTeam;
import backend.jobs.opta.mapper.Mapper;
import backend.jobs.opta.webentity.matches.MatchesRequest;
import backend.jobs.opta.webentity.team.ContestantWebEntity;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import com.google.gson.Gson;
import models.Language;
import models.football.Competition;
import models.football.CompetitionType;
import models.football.Team;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

import java.util.ArrayList;
import java.util.List;

public class OptaRepositoryImpl{

    public TournamentCalendarRequest GetTournamentCalendar() {
        WSRequestHolder holder = WS.url("http://api.performfeeds.com/soccerdata/tournamentcalendar/13xo8a74h96z61ke2931rmv5my/active/authorized?_rt=b&_fmt=json");
        String json = holder.get().get(3000).getBody();
        TournamentCalendarRequest data = new Gson().fromJson(json, TournamentCalendarRequest.class);
        return data;
    }

    public TeamRequest GetTeams(TournamentCalendarWebEntity competitionWebEntity) {
        WSRequestHolder holder = WS.url("http://api.performfeeds.com/soccerdata/team/13xo8a74h96z61ke2931rmv5my?_rt=b&_fmt=json&detailed=yes&tmcl=" + competitionWebEntity.getId());
        String json = holder.get().get(3000).getBody();
        return  new Gson().fromJson(json, TeamRequest.class);
    }

    public MatchesRequest GetMatches(TournamentCalendarWebEntity competitionWebEntity) {
        WSRequestHolder holder = WS.url(" http://api.performfeeds.com/soccerdata/match/13xo8a74h96z61ke2931rmv5my?_fmt=json&_rt=b&tmcl=408bfjw6uz5k19zk4am50ykmh" + competitionWebEntity.getId());
        String json = holder.get().get(3000).getBody();
        return  new Gson().fromJson(json, MatchesRequest.class);
    }




}
