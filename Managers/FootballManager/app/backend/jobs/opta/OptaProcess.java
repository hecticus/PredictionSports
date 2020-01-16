package backend.jobs.opta;

import backend.jobs.opta.repository.OptaRepository;
import backend.jobs.opta.service.OptaServiceImpl;
import backend.jobs.opta.webentity.matches.*;
import backend.jobs.opta.webentity.table.Division;
import backend.jobs.opta.webentity.table.Ranking;
import backend.jobs.opta.webentity.table.StageWebEntity;
import backend.jobs.opta.webentity.table.TableRequest;
import backend.jobs.opta.webentity.team.ContestantWebEntity;
import backend.jobs.opta.webentity.team.TeamRequest;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarWebEntity;
import models.Config;
import models.Language;
import models.football.*;
import models.football.Competition;
import models.football.Venue;
import utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OptaProcess extends ProcessAbstract {
//public class OptaProcess {

    OptaServiceImpl optaServicio;
    OptaRepository optaRepository = new OptaRepository();

    public OptaProcess() {
        optaServicio = new OptaServiceImpl();
    }

    public void process(Map args) {
        super.process(args);
        language = Language.getByID(300);

        Utils.printToLog(Utils.class, "", "Iniicio Proceso OPTA: ", true, e, "support-level-1", Config.LOGGER_ERROR);

        TournamentCalendarRequest tournamentCalendarRequest = null;
        tournamentCalendarRequest = optaRepository.GetTournamentCalendar();


        if (tournamentCalendarRequest != null) {
            List<CompetitionWebEntity> competitionWebEntities = tournamentCalendarRequest.getCompetition();
            for (CompetitionWebEntity competitionWebEntity : competitionWebEntities) {
                try {
                    CompetitionType competitionType = CompetitionWebEntityToCompetitionType(competitionWebEntity);
                    competitionType.validate(language);
                    ProcessCompetitions(competitionWebEntity, competitionType);
                } catch (Exception e) {
                    Utils.printToLog(Utils.class, "", "Error en OPTA PALENGE" + e.getMessage() + e.getStackTrace(), true, e, "support-level-1", Config.LOGGER_ERROR);
                }
            }
        }

        Utils.printToLog(Utils.class, "", "Proceso finalizo Correctamente Proceso OPTA: ", true, e, "support-level-1", Config.LOGGER_ERROR);
        System.out.println("Proceso finalizo Correctamente");
    }

    private void ProcessCompetitions(CompetitionWebEntity competitionWebEntity, CompetitionType competitionType) {
        for (TournamentCalendarWebEntity competitionEntity : competitionWebEntity.getTournamentCalendar()) {
            Competition competition = TournamentCalendarWebEntityToCompetition(competitionEntity, competitionType);
            competition.validate(language);
            ProcessTeams(competition, competitionEntity);
            ProcessMatches(competition, competitionEntity);
            ProcessTables(competition, competitionEntity);
        }
    }

    private void ProcessTeams(Competition competition, TournamentCalendarWebEntity competitionEntity) {
        TeamRequest teamRequest = optaRepository.GetTeams(competitionEntity);

        if (teamRequest != null) {
            for (ContestantWebEntity contestantWebEntity : teamRequest.getContestant()) {
                Team team = ContestantWebEntityToTeam(contestantWebEntity);
                team.validateTeam(competition);
            }
        }
    }

    public void ProcessMatches(Competition competition, TournamentCalendarWebEntity competitionWebEntity) {
        MatchesRequest matchesRequest = optaRepository.GetMatches(competitionWebEntity);
        if (matchesRequest != null && matchesRequest.getMatch() != null) {
            for (MatchWebEntity matchWebEntity : matchesRequest.getMatch()) {
                ProcessGameMatch(matchWebEntity, competition);
            }
        }
    }

    public void ProcessTables(Competition competition, TournamentCalendarWebEntity competitionEntity) {
        TableRequest tableRequest = optaRepository.GetTables(competitionEntity.getId());

        if (tableRequest != null) {
            for (StageWebEntity stageWebEntity : tableRequest.getStage()) {
                Phase phase = ProcessPhases(competition, stageWebEntity);
                List<Ranking> rankings = stageWebEntity.getDivision().get(0).getRanking();

                for (Ranking ranking : rankings) {
                    Group group = new Group(competition, "-");
                    Team team = Team.findByExtId(ranking.getContestantId());
                    Rank currentRank = new Rank(phase, team, group, ranking.getMatchesPlayed(), ranking.getMatchesWon(),
                            ranking.getMatchesDrawn(), ranking.getMatchesLost(), ranking.getPoints(), ranking.getGoalsFor(), ranking.getGoalsAgainst());
                    currentRank.validateRank();
                }
            }
        }
    }

    private GameMatch ProcessGameMatch(MatchWebEntity matchWebEntity, Competition competition) {
        List<Contestant> contestants = matchWebEntity.getMatchInfo().getContestant();
        MatchDetails matchDetails = matchWebEntity.getLiveData().getMatchDetails();

        Phase phase = ProcessPhases(competition, matchWebEntity.getMatchInfo().getStageWebEntity());

        Team localTeam = ProcessTeamFromMatch(competition, contestants.get(0));
        Team awayTeam = ProcessTeamFromMatch(competition, contestants.get(1));

        GameMatchStatus status = new GameMatchStatus(matchDetails.getMatchStatus());
        status.validate(language);

        Venue venue = ProcessVenue(matchWebEntity);

        int localScore = 0;
        int awayScore = 0;

        if (matchDetails.getScores() != null) {
            TotalWebEntity total = matchDetails.getScores().getTotal();
            localScore = total.getHome();
            awayScore = total.getAway();
        }

        MatchInfo matchInfo = matchWebEntity.getMatchInfo();
        String foramttedDate = matchInfo.getDate().replace("-", "").replace("Z", "");
        foramttedDate += matchInfo.getTime().replace(":", "").replace("Z", "");

        GameMatch gameMatch = new GameMatch(phase, localTeam, awayTeam, venue, localTeam.getName(),
                awayTeam.getName(), localScore, awayScore, foramttedDate, status,
                GenerateHash(matchInfo.getId()), competition);
        gameMatch.validateGame();

        return gameMatch;
    }

    private Phase ProcessPhases(Competition competition, StageWebEntity stageWebEntity) {
        Phase phase = StageWebEntityToPhase(stageWebEntity, competition);
        
        if(phase == null) {
            return null;
        }
        
        phase.validate(language);
        return phase;
    }

    private Venue ProcessVenue(MatchWebEntity matchWebEntity) {
        Venue gameVenue = null;
        VenueWebEntity venueWebEntity = matchWebEntity.getMatchInfo().getVenue();
        Countries country = GetCountry(matchWebEntity.getMatchInfo().getCompetition().getCountry().getName());

        if (matchWebEntity != null && venueWebEntity != null) {

            long hash = GenerateHash(venueWebEntity.getId());
            gameVenue = new Venue(hash, venueWebEntity.getLongName(), venueWebEntity.getLongName(), country);
            gameVenue.validateVenue();
        }

        return gameVenue;
    }

    private long GenerateHash(String hashable) {
        return UUID.nameUUIDFromBytes(hashable.getBytes()).getMostSignificantBits();
    }

    private Team ProcessTeamFromMatch(Competition competition, Contestant local) {
        Countries countryLocal = GetCountry(local.getCountry().getName());
        Team localTeam = new Team(local.getName(), String.valueOf(GenerateHash(local.getId())), countryLocal);
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
                country, String.valueOf(GenerateHash(contestantWebEntity.getId())),
                contestantWebEntity.getOfficialName(),
                contestantWebEntity.getShortName(),
                contestantWebEntity.getCode());
    }

    public Phase StageWebEntityToPhase(StageWebEntity stageWebEntity, Competition competition) {

        Phase phase = new Phase(competition, stageWebEntity.getName(), stageWebEntity.getName(),
                stageWebEntity.getStartDate().replace("-", "").replace("Z", ""), stageWebEntity.getEndDate().replace("-", "").replace("Z", ""), String.valueOf(GenerateHash(stageWebEntity.getId())), 0, 0, 0, 1);
        return phase;
    }

    public Countries GetCountry(String identifier) {
        Countries countries = new Countries(identifier);
        countries.validateCountry();
        return countries;
    }
}
