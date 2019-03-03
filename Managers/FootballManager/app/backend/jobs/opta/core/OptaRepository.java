package backend.jobs.opta.core;

import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import models.Language;
import models.football.Competition;
import models.football.CompetitionType;
import models.football.Team;

import java.util.List;

public interface OptaRepository {
    List<TournamentCalendarRequest> GetTournamentCalendar() ;
    List<Team> GetTeams(Competition competition) ;
}
