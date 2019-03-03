package backend.jobs.opta.service;

import backend.jobs.opta.mapper.CompetitionEntityToCompetitionType;
import backend.jobs.opta.mapper.Mapper;
import backend.jobs.opta.repository.OptaRepositoryImpl;
import backend.jobs.opta.repository.OptaTournamentCalendarRepositoryImpl;
import backend.jobs.opta.webentity.tournamentcalendar.CompetitionWebEntity;
import backend.jobs.opta.webentity.tournamentcalendar.TournamentCalendarRequest;
import models.Language;
import models.football.CompetitionType;

import java.util.ArrayList;
import java.util.List;

public class OptaServiceImpl {

//    AppServiceImpl appService;
//    OptaRepositoryImpl repo;
//    OptaTournamentCalendarRepositoryImpl optaTournamentCalendarRepositoryImpl;
//
//    public OptaServiceImpl() {
//        repo = new OptaRepositoryImpl();
//        optaTournamentCalendarRepositoryImpl = new OptaTournamentCalendarRepositoryImpl();
//    }
//
//    public OptaServiceImpl(AppServiceImpl appServiceImpl) {
//        repo = new OptaRepositoryImpl();
//        optaTournamentCalendarRepositoryImpl = new OptaTournamentCalendarRepositoryImpl();
//        this.appService = appService;
//    }
//
//    public List<CompetitionType> GetCompetitionTypes() {
//        return optaTournamentCalendarRepositoryImpl.GetCompetitionTypes();
//    }
//
//    public void SaveCompetitionType(CompetitionType competitionType, Language lang) {
//        competitionType.validate(lang);
//    }
//
//    public void SaveCompetitionByCompetitionType(CompetitionType competitionType) {
//
//    }
//
//
//
//
//
//
//
//
//    private List<CompetitionType> ProcessCompetitionTypes(TournamentCalendarRequest data) {
//        Mapper mapperCompetitionType = new CompetitionEntityToCompetitionType();
//        for (CompetitionWebEntity competitionType : data.getCompetitions()) {
//            CompetitionType category = (CompetitionType) mapperCompetitionType.MapTo(competitionType);
//            category.validate(language);
//            ProcessCompetitions(competitionType, category);
//        }
//    }
//
//    private List<CompetitionType> ProcessCompetitionTypes(TournamentCalendarRequest data, Language language) {
//        Mapper mapperCompetitionType = new CompetitionEntityToCompetitionType();
//        List<CompetitionType> competitionTypes = new ArrayList<>();
//        for (CompetitionWebEntity competitionType : data.getCompetitions()) {
//            CompetitionType category = (CompetitionType) mapperCompetitionType.MapTo(competitionType);
//            category.validate(language);
//            competitionTypes.add(category);
//        }
//
//        return competitionTypes;
//    }

}
