package backend.jobs.opta.repository;

import backend.jobs.opta.mapper.CompetitionEntityToCompetitionType;
import backend.jobs.opta.mapper.Mapper;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import com.google.gson.Gson;
import models.Language;
import models.football.Competition;
import models.football.CompetitionType;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;

import java.util.ArrayList;
import java.util.List;

public class OptaTournamentCalendarRepositoryImpl {
//    Language language;
//    TournamentCalendarRequest currentTournamentCalendarRequest;
//
//    public OptaTournamentCalendarRepositoryImpl(){
//        ObtainData();
//        Language.getByID(300);
//    }
//
//    public OptaTournamentCalendarRepositoryImpl(Language lang){
//        language = lang;
//        ObtainData();
//    }
//
//    private void ObtainData() {
//        WSRequestHolder holder = WS.url("http://api.performfeeds.com/soccerdata/tournamentcalendar/13xo8a74h96z61ke2931rmv5my/active/authorized?_rt=b&_fmt=json");
//        String json = holder.get().get(3000).getBody();
//        currentTournamentCalendarRequest = new Gson().fromJson(json, TournamentCalendarRequest.class);
//    }
//
//    public List<CompetitionType> GetCompetitionTypes() {
//        Mapper mapperCompetitionType = new CompetitionEntityToCompetitionType();
//        List<CompetitionType> competitionTypes = new ArrayList<>();
//
//        for (CompetitionWebEntity competitionWebEntity : currentTournamentCalendarRequest.getCompetitions()) {
//            CompetitionType competitionType = (CompetitionType) mapperCompetitionType.MapTo(competitionWebEntity);
//            competitionTypes.add(competitionType);
//        }
//
//        return competitionTypes;
//    }
//
//    public List<CompetitionType> GetCompetitionByCompetitionTypes(CompetitionType competitionType) {
//        List<Competition> competitions= new ArrayList<>();
//        currentTournamentCalendarRequest.getCompetitions().get(0).
//
//        Mapper mapperCompetitionType = new CompetitionEntityToCompetitionType();
//        List<Competition> competitions = new ArrayList<>();
//
//        for (CompetitionWebEntity competitionWebEntity : currentTournamentCalendarRequest.getCompetitions()) {
//            CompetitionType competition = (CompetitionType) mapperCompetitionType.MapTo(competitionWebEntity);
//            competitions.add(competition);
//        }
//
//        return competitionTypes;
//    }
//
//    public void SaveCompetitionType(CompetitionType competitionType, Language lang) {
//        competitionType.validate(language);
//    }

}
