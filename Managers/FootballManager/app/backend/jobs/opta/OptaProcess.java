package backend.jobs.opta;

import backend.jobs.opta.repository.OptaRepositoryImpl;
import backend.jobs.opta.service.OptaServiceImpl;
import backend.jobs.opta.webentity.matches.*;
import backend.jobs.opta.webentity.team.ContestantWebEntity;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import models.football.*;
import models.football.Competition;

import java.util.List;
import java.util.Map;

public class OptaProcess extends ProcessAbstract {

    OptaServiceImpl optaServicio;
    OptaRepositoryImpl opta = new OptaRepositoryImpl();

    public OptaProcess() {
        optaServicio = new OptaServiceImpl();
    }

    public void process(Map args) {
        super.process(args);
        TournamentCalendarRequest tournamentCalendarRequest = opta.GetTournamentCalendar();
        List<CompetitionWebEntity> competitionWebEntities = tournamentCalendarRequest.getCompetition();

        for (CompetitionWebEntity competitionWebEntity : competitionWebEntities) {
            CompetitionType competitionType = CompetitionWebEntityToCompetitionType(competitionWebEntity);
            competitionType.validate(language);
            ProcessCompetitions(competitionWebEntity, competitionType);
        }
    }

    private void ProcessCompetitions(CompetitionWebEntity competitionWebEntity, CompetitionType competitionType) {
        for (TournamentCalendarWebEntity competitionEntity : competitionWebEntity.getTournamentCalendar()) {
            Competition competition = TournamentCalendarWebEntityToCompetition(competitionEntity, competitionType);
            competition.validate(language);
            ProcessTeams(competition, competitionEntity);
            ProcessMatches(competition, competitionEntity);
        }
    }

    private void ProcessTeams(Competition competition, TournamentCalendarWebEntity competitionEntity) {
        TeamRequest teamRequest = opta.GetTeams(competitionEntity);
        for (ContestantWebEntity contestantWebEntity : teamRequest.getContestant()) {
            Team team = ContestantWebEntityToTeam(contestantWebEntity);
            team.validateTeam(competition);
        }
    }

    public void ProcessMatches(Competition competition, TournamentCalendarWebEntity competitionWebEntity) {
        MatchesRequest matchesRequest = opta.GetMatches(competitionWebEntity);
        for (MatchWebEntity matchWebEntity : matchesRequest.getMatch()) {
            Phase phase = ProcessPhases(competition, matchWebEntity.getMatchInfo().getStageWebEntity());



        }
    }

    private Phase ProcessPhases(Competition competition,StageWebEntity stageWebEntity) {
        Phase phase = StageWebEntityToPhase(stageWebEntity, competition);
        phase.validate(language);
        return phase;
    }

    private GameMatch ProcessGameMatch(MatchInfo matchInfo, Competition competition) {
        List<Contestant> contestants = matchInfo.getContestant();
        Team localTeam = ProcessTeamFromMatch(competition,  contestants.get(0));
        Team awayTeam = ProcessTeamFromMatch(competition,  contestants.get(1));


        GameMatchStatus status = new GameMatchStatus(statusName);
        status.validate(language);
        return nul;

    }

    private Team ProcessTeamFromMatch(Competition competition, Contestant local) {
        Countries countryLocal = GetCountry(local.getCountry().getName());
        Team localTeam = new Team(local.getName(), local.getId(), countryLocal);
        localTeam.validateTeam(competition);
        return localTeam;
    }

    public CompetitionType CompetitionWebEntityToCompetitionType(CompetitionWebEntity obj) {
        return new CompetitionType(obj.getName(), Long.parseLong(obj.getOcId()));
    }

    public Competition TournamentCalendarWebEntityToCompetition(TournamentCalendarWebEntity tournamentCalendarWebEntity, CompetitionType competitionType) {
        return new Competition(competitionType.getName() + " " + tournamentCalendarWebEntity.getName(), Long.parseLong(tournamentCalendarWebEntity.getOcId()), app, competitionType);
    }

    public Team ContestantWebEntityToTeam(ContestantWebEntity contestantWebEntity) {
        Countries country = GetCountry(contestantWebEntity.getCountry());
        return new Team(contestantWebEntity.getName(),
                country, contestantWebEntity.getId(),
                contestantWebEntity.getOfficialName(),
                contestantWebEntity.getShortName(),
                contestantWebEntity.getCode());
    }

    public Phase StageWebEntityToPhase(StageWebEntity StageWebEntity, Competition competition) {
        Phase phase = new Phase(competition, StageWebEntity.getName(), StageWebEntity.getName(),
                StageWebEntity.getStartDate(), StageWebEntity.getEndDate(), StageWebEntity.getId(), 0, 0, 0, 1);
        return phase;
    }

    public Countries GetCountry(String identifier){
        Countries countries = new Countries(identifier);
        countries.validateCountry();
        return countries;
    }
}
