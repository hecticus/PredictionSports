package models.football;

import com.avaje.ebean.Expr;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import comparators.GameMatchDateComparator;
import comparators.TeamHasCompetitionComparator;
import models.Apps;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;
import utils.DateAndTime;
import utils.Utils;

import javax.persistence.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by karina on 5/13/14.
 */
@Entity
@Table(name="competitions", uniqueConstraints = @UniqueConstraint(columnNames = {"id_comp_type, id_app, ext_id"}))
public class Competition  extends FootballModel {
    @Id
    private Long idCompetitions;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private Long extId;

    @ManyToOne
    @JoinColumn(name = "id_app")
    private Apps app;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_comp_type")
    private CompetitionType type;

    @OneToMany(mappedBy="comp", cascade = CascadeType.ALL)
    @OrderBy("orden asc, nivel asc")
    public List<Phase> phases;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="competition", cascade = CascadeType.ALL)
    private List<TeamHasCompetition> teams;

    @OneToMany(mappedBy="competition", cascade = CascadeType.ALL)
    private List<GameMatch> matches;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition", cascade = CascadeType.ALL)
    private List<CompetitionHasLocalization> localizations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="comp", cascade = CascadeType.ALL)
    @OrderBy("goals desc")
    private List<Scorer> scorers;

    public Competition(String name, Long extId, Apps idApp, CompetitionType type) {
        this.name = name;
        this.extId = extId;
        this.app = idApp;
        this.status = 0;
        this.type = type;
    }

    private static Model.Finder<Long,Competition> finder = new Model.Finder<Long, Competition>(Long.class, Competition.class);

    public Long getIdCompetitions() {
        return idCompetitions;
    }

    public void setIdCompetitions(Long idCompetitions) {
        this.idCompetitions = idCompetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExtId() {
        return extId;
    }

    public void setExtId(Long extId) {
        this.extId = extId;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }

    public static Competition findById(Long id){
        return finder.byId(id);
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public List<TeamHasCompetition> getTeams() {
        Collections.sort(teams, new TeamHasCompetitionComparator());
        return teams;
    }

    public void setTeams(List<TeamHasCompetition> teams) {
        this.teams = teams;
    }

    public List<GameMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<GameMatch> matches) {
        this.matches = matches;
    }

    public List<CompetitionHasLocalization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<CompetitionHasLocalization> localizations) {
        this.localizations = localizations;
    }

    public List<Scorer> getScorers() {
        return scorers;
    }

    public void setScorers(List<Scorer> scorers) {
        this.scorers = scorers;
    }

    public static List<Competition> getCompetitionsByApp(Apps app){
        return finder.where().eq("app", app).eq("status", 1).findList();
    }

    public static List<Competition> getCompetitionsByAppNotIn(Apps app, ArrayList<Long> competitionsIDs){
        return finder.where().eq("app", app).eq("status", 1).not(Expr.in("idCompetitions", competitionsIDs)).findList();
    }

    public static List<Competition> getActiveCompetitionsByApp(Apps app){
        return finder.where().eq("app", app).eq("status", 1).findList();
    }

    public static List<Competition> getActiveCompetitionsByAppAndTeams(Apps app, List<Team> teams){
        return finder.fetch("teams").where().eq("app", app).eq("status", 1).in("teams.team", teams).findList();
    }

    public static Competition findByExtId(long id){
        return finder.where().eq("ext_id", id).findUnique();
    }

    public static Competition findByCompExt(Apps app, long extId ){
        return finder.where().eq("app", app).eq("ext_id", extId).findUnique();
    }

    public static Competition getCompetitionByApp(Apps app, int idCompetition){
        return finder.where().eq("app", app).eq("idCompetitions", idCompetition).eq("status", 1).findUnique();
    }

    public static List<Competition> getCompetitionsPage(Apps app, int page, int pageSize, String date){
        return finder.fetch("matches").where().eq("app", app).eq("status", 1).ilike("matches.date", date+"%").setFirstRow(page).setMaxRows(pageSize).findList();
    }

    public static List<Competition> getCompetitionsPage(Apps app, int page, int pageSize, String minDate, String maxDate){
        return finder.fetch("matches").where().eq("app", app).eq("status", 1).between("matches.date", minDate, maxDate).setFirstRow(page).setMaxRows(pageSize).findList();
    }

    public static List<Competition> getCompetitionsPage(Apps app, int page, int pageSize, String date, List<Team> teams){
        return finder.fetch("teams").fetch("matches").where().eq("app", app).eq("status", 1).ilike("matches.date", date + "%").in("teams.team", teams).setFirstRow(page).setMaxRows(pageSize).findList();
    }

    public static List<Competition> getCompetitionsPage(Apps app, int page, int pageSize, String minDate, String maxDate, List<Team> teams){
        return finder.fetch("teams").fetch("matches").where().eq("app", app).eq("status", 1).between("matches.date", minDate, maxDate).in("teams.team", teams).setFirstRow(page).setMaxRows(pageSize).findList();
    }

    public List<Scorer> getScorers(Integer page, Integer pageSize) {
        Collections.sort(scorers, new ScorersComparator());
        List<Scorer> result = new ArrayList<>();
        int n = 0, k = 0;
        for (int i = 0; n < pageSize && i < scorers.size(); ++i) {
            Scorer element = scorers.get(i);
            ++k;
            if(k > page) {
                ++n;
                result.add(element);
            }
        }
        return result;
    }

    public List<Phase> getPhases(final Calendar today){
        List<Phase> tr;
        final TimeZone timeZone = today.getTimeZone();
        try {
            Predicate<Phase> validObjs = new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    Calendar startDateCalendar = new GregorianCalendar(timeZone);
                    try {
                        Date startDate = DateAndTime.getDate(obj.getStartDate(), "yyyyMMdd");
                        startDateCalendar.setTime(startDate);
                        return (startDateCalendar.before(today) || startDateCalendar.equals(today));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }

                }
            };
            Collection<Phase> result = Utils.filterCollection(phases, validObjs);
            tr = (List<Phase>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public void validate(Language language){
        //check if exist
        Competition fromDb = findByCompExt(this.app, this.extId);
        if ( fromDb != null){
            //existe
            this.name = fromDb.name;
            this.extId = fromDb.extId;
            this.status = fromDb.status;
            this.app = fromDb.app;
            this.idCompetitions = fromDb.idCompetitions;
            this.type = fromDb.type;
            this.localizations = fromDb.localizations;
        }else {
            //insertar
            this.save();
        }
        CompetitionHasLocalization competitionHasLocalization = new CompetitionHasLocalization(this, language, this.name);
        if(!CompetitionHasLocalization.exists(competitionHasLocalization)){
            //System.out.println("no existe " + this.getName() + " " + language.getName());
            this.localizations.add(competitionHasLocalization);
            competitionHasLocalization.save();
            this.update();
        }
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        obj.put("name",name);
        obj.put("ext_id",extId);
        obj.put("competiton_type", type.toJson());
        if(phases != null && !phases.isEmpty()){
            ArrayList<ObjectNode> phasesList = new ArrayList<>(phases.size());
            for(Phase phase : phases){
                phasesList.add(phase.toJson());
            }
            obj.put("phases", Json.toJson(phasesList));
        }
        if(teams != null && !teams.isEmpty()){
            ArrayList<ObjectNode> phasesList = new ArrayList<>(teams.size());
            for(TeamHasCompetition phase : teams){
                phasesList.add(phase.getTeam().toJson());
            }
            obj.put("teams", Json.toJson(phasesList));
        }

        if(matches != null && !matches.isEmpty()){
            ArrayList<ObjectNode> phasesList = new ArrayList<>(matches.size());
            for(GameMatch phase : matches){
                phasesList.add(phase.toJson());
            }
            obj.put("matches", Json.toJson(phasesList));
        }
        return obj;
    }

    public ObjectNode toJsonNoPhases() {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        obj.put("name",name);
        obj.put("ext_id",extId);
        obj.put("competiton_type", type.toJson());
        return obj;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        obj.put("name",name);
        obj.put("ext_id",extId);
        return obj;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        CompetitionHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                public boolean apply(CompetitionHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                    public boolean apply(CompetitionHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("ext_id",extId);
        obj.put("competiton_type", type.toJson(language, defaultLanguage));
        if(phases != null && !phases.isEmpty()){
            ArrayList<ObjectNode> phasesList = new ArrayList<>(phases.size());
            for(Phase phase : phases){
                phasesList.add(phase.toJson(language, defaultLanguage));
            }
            obj.put("phases", Json.toJson(phasesList));
        }
        return obj;
    }

    public ObjectNode toJsonNoPhases(final Language language, final Language defaultLanguage, boolean closestMatch, TimeZone timeZone) throws ParseException {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        CompetitionHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                public boolean apply(CompetitionHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                    public boolean apply(CompetitionHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("ext_id",extId);
        obj.put("competiton_type", type.toJson(language, defaultLanguage));
        if(closestMatch){
            GameMatch match = GameMatch.getClosestMatch(this);
            obj.put("match", match!=null?(timeZone != null?match.toJsonPush(timeZone):match.toJsonPush()):null);
        }
        return obj;
    }

    public ObjectNode toJsonSimple(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_competitions",idCompetitions);
        CompetitionHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                public boolean apply(CompetitionHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<CompetitionHasLocalization>() {
                    public boolean apply(CompetitionHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("ext_id",extId);
        return obj;
    }

    public GameMatch getMatch(final long idGameMatch){
        GameMatch tr = null;
        try {
            tr = Iterables.find(matches, new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    return obj.getIdGameMatches().longValue() == idGameMatch;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public Phase getPhase(final long idPhase){
        Phase tr = null;
        try {
            tr = Iterables.find(phases, new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    return obj.getIdPhases().longValue() == idPhase;
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public List<Phase> getPhasesByNivel(final int nivel){
        List<Phase> tr;
        try {
            Predicate<Phase> validObjs = new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    return obj.getNivel().intValue() == nivel;
                }
            };
            Collection<Phase> result = Utils.filterCollection(phases, validObjs);
            tr = (List<Phase>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public Phase getActivePhase(final Calendar date, final TimeZone timeZone){
        Phase tr = null;
        try {
            tr = Iterables.find(phases, new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    Calendar startCalendar = new GregorianCalendar(timeZone);
                    Calendar endCalendar = new GregorianCalendar(timeZone);
                    try {
                        Date startDate = DateAndTime.getDate(obj.getStartDate(), "yyyyMMdd");
                        startCalendar.setTime(startDate);
                        Date endDate = DateAndTime.getDate(obj.getEndDate(), "yyyyMMdd");
                        endCalendar.setTime(endDate);
                        return (date.equals(startCalendar) || date.equals(endCalendar) || (date.after(startCalendar) && date.before(endCalendar))) && obj.getType().intValue() != 1;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            });
        } catch (NoSuchElementException ex){
            tr = null;
        }
        return tr;
    }

    public List<Phase> getLatestPhases(final Calendar date, final TimeZone timeZone){
        List<Phase> tr;
        try {
            Predicate<Phase> validObjs = new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    Calendar endCalendar = new GregorianCalendar(timeZone);
                    try {
                        Date endDate = DateAndTime.getDate(obj.getEndDate(), "yyyyMMdd");
                        endCalendar.setTime(endDate);
                        return endCalendar.before(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };
            Collection<Phase> result = Utils.filterCollection(phases, validObjs);
            tr = (List<Phase>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<Phase> getPhasesByGlobalNameAndDate(final String globalName, final Calendar date, final TimeZone timeZone){
        List<Phase> tr;
        try {
            Predicate<Phase> validObjs = new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    Calendar endCalendar = new GregorianCalendar(timeZone);
                    try {
                        Date endDate = DateAndTime.getDate(obj.getEndDate(), "yyyyMMdd");
                        endCalendar.setTime(endDate);
                        return date.before(endCalendar) && obj.getGlobalName().equalsIgnoreCase(globalName);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };
            Collection<Phase> result = Utils.filterCollection(phases, validObjs);
            tr = (List<Phase>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<Phase> getPhasesByGlobalName(final String globalName){
        List<Phase> tr;
        try {
            Predicate<Phase> validObjs = new Predicate<Phase>() {
                public boolean apply(Phase obj) {
                    return obj.getGlobalName().equalsIgnoreCase(globalName);
                }
            };
            Collection<Phase> result = Utils.filterCollection(phases, validObjs);
            tr = (List<Phase>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<GameMatch> getMatchesByDate(final String date){
        List<GameMatch> tr;
        try {
            Predicate<GameMatch> validObjs = new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    return obj.getDate().startsWith(date);
                }
            };
            Collection<GameMatch> result = Utils.filterCollection(matches, validObjs);
            tr = (List<GameMatch>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<GameMatch> getMatchesByDate(final Calendar minDate, final Calendar maxDate){
        List<GameMatch> tr;
        try {
            Predicate<GameMatch> validObjs = new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    Calendar endCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    Date endDate = null;
                    try {
                        endDate = DateAndTime.getDate(obj.getDate(), "yyyyMMddHHmmss");
                        endCalendar.setTime(endDate);
                        return (endCalendar.after(minDate) && endCalendar.before(maxDate)) || endCalendar.equals(minDate) || endCalendar.equals(maxDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };
            Collection<GameMatch> result = Utils.filterCollection(matches, validObjs);
            tr = (List<GameMatch>) result;
            Collections.sort(tr, new GameMatchDateComparator());
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<GameMatch> getMatchesByDateDB(final String minDate, final String maxDate){
        return GameMatch.findAllByCompetitionBetweenDate(this.getIdCompetitions(), minDate, maxDate);
    }

    public List<GameMatch> getMatchesByDate(final String date, int page, int pageSize){
        List<GameMatch> tr;
        try {
            Predicate<GameMatch> validObjs = new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    return obj.getDate().startsWith(date);
                }
            };
            Collection<GameMatch> result = Utils.filterCollection(matches, validObjs, page, pageSize);
            tr = (List<GameMatch>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public List<GameMatch> getMatchesByDate(final Calendar minDate, final Calendar maxDate, int page, int pageSize){
        List<GameMatch> tr;
        try {
            Predicate<GameMatch> validObjs = new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    Calendar endCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    Date endDate = null;
                    try {
                        endDate = DateAndTime.getDate(obj.getDate(), "yyyyMMddHHmmss");
                        endCalendar.setTime(endDate);
                        return (endCalendar.after(minDate) && endCalendar.before(maxDate)) || endCalendar.equals(minDate) || endCalendar.equals(maxDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            };
            Collection<GameMatch> result = Utils.filterCollection(matches, validObjs, page, pageSize);
            tr = (List<GameMatch>) result;
        } catch (NoSuchElementException e){
            tr = null;
        }
        return tr;
    }

    public int countMatchesByDate(final String date){
        int tr;
        try {
            Predicate<GameMatch> validObjs = new Predicate<GameMatch>() {
                public boolean apply(GameMatch obj) {
                    return obj.getDate().startsWith(date);
                }
            };
            Collection<GameMatch> result = Utils.filterCollection(matches, validObjs);
            tr = result.size();
        } catch (NoSuchElementException e){
            tr = 0;
        }
        return tr;
    }

}

class ScorersComparator implements Comparator<Scorer> {
    @Override
    public int compare(Scorer c1, Scorer c2) {
        return c2.getGoals() - c1.getGoals();
    }
}

//class GameMatchComparatorDesc implements Comparator<GameMatch> {
//    @Override
//    public int compare(GameMatch c1, GameMatch c2) {
//        return (int) (c2.getIdGameMatches() - c1.getIdGameMatches());
//    }
//}
//
//class GameMatchComparatorAsc implements Comparator<GameMatch> {
//    @Override
//    public int compare(GameMatch c1, GameMatch c2) {
//        return (int) (c1.getIdGameMatches() - c2.getIdGameMatches());
//    }
//}