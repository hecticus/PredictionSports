package backend.jobs.opta.service;

public class OptaServiceImpl {

//    AppServiceImpl appService;
//    OptaRepository repo;
//    OptaTournamentCalendarRepositoryImpl optaTournamentCalendarRepositoryImpl;
//
//    public OptaServiceImpl() {
//        repo = new OptaRepository();
//        optaTournamentCalendarRepositoryImpl = new OptaTournamentCalendarRepositoryImpl();
//    }
//
//    public OptaServiceImpl(AppServiceImpl appServiceImpl) {
//        repo = new OptaRepository();
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
