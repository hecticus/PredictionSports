package backend.jobs.opta.mapper;

import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import models.football.CompetitionType;

public class CompetitionEntityToCompetitionType implements Mapper<CompetitionWebEntity, CompetitionType> {
    public CompetitionType MapTo(CompetitionWebEntity obj) {
        return new CompetitionType(obj.getName(), Long.parseLong(obj.getOcId()));
    }

    public CompetitionWebEntity MapFrom(CompetitionType obj){
        return null;
    }
}
