package backend.jobs.opta.mapper;

import backend.jobs.opta.webentity.team.ContestantWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import models.football.CompetitionType;
import models.football.Countries;
import models.football.Team;

public class ContestantWebEntityToTeam implements Mapper<ContestantWebEntity, Team> {
    public Team MapTo(ContestantWebEntity obj) {
        Countries country = new Countries(obj.getCountry());
        country.validateCountry();
        return new Team(obj.getName(), country, obj.getId(), obj.getOfficialName(), obj.getOfficialName(), obj.getCode());
    }

    public ContestantWebEntity MapFrom(Team obj){
        return null;
    }
}
