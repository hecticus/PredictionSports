package backend.jobs.opta.mapper;

import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import models.football.Competition;

public class TournamentCalendarWebEntityToCompetition implements Mapper<TournamentCalendarWebEntity, Competition> {
    public Competition MapTo(TournamentCalendarWebEntity obj) {
        return new Competition(obj.getName(), Long.parseLong(obj.getOcId()), null, null );
    }

    public TournamentCalendarWebEntity MapFrom(Competition obj){
        return null;
    }
}
