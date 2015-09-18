package models.football;

import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by karina on 5/13/14.
 */
@Entity
@Table(name="phases", uniqueConstraints = @UniqueConstraint(columnNames = {"id_competitions, ext_id"}))
public class Phase extends FootballModel {

    @Id
    private Long idPhases;
    @ManyToOne
    @JoinColumn(name = "id_competitions")
    private Competition comp;
    @Constraints.Required
    private String globalName;
    @Constraints.Required
    private String name;
    @Constraints.Required
    @Constraints.MaxLength(8)
    private String startDate;
    @Constraints.Required
    @Constraints.MaxLength(8)
    private String endDate;

    private String extId;

    private Integer orden;
    private Integer nivel;
    private Integer fn;

    private boolean pushed;

    private Integer type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phase", cascade = CascadeType.ALL)
    private List<GameMatch> matches;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<PhaseHasLocalization> localizations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="phase", cascade = CascadeType.ALL)
    @OrderBy("points desc, goalDiff desc")
    private List<Rank> ranks;

    private static Model.Finder<Long,Phase> finder = new Model.Finder<Long,Phase>(Long.class,Phase.class);

    public Phase(){

    }

    public Phase(Competition comp, String globalName, String name, String startDate, String endDate, String extId, Integer order, Integer nivel, Integer fn) {
        this.comp = comp;
        this.globalName = globalName;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extId = extId;
        this.orden = order;
        this.nivel = nivel;
        this.fn = fn;
        this.type = 0;
    }

    public Phase(Competition comp, String globalName, String name, String startDate, String endDate, String extId, Integer order, Integer nivel, Integer fn, Integer type) {
        this.comp = comp;
        this.globalName = globalName;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extId = extId;
        this.orden = order;
        this.nivel = nivel;
        this.fn = fn;
        this.type = type;
    }

    public Long getIdPhases() {
        return idPhases;
    }

    public void setIdPhases(Long idPhases) {
        this.idPhases = idPhases;
    }

    public Competition getComp() {
        return comp;
    }

    public void setComp(Competition comp) {
        this.comp = comp;
    }

    public String getGlobalName() {
        return globalName;
    }

    public String getGlobalName(final Language language, final Language defaultLanguage) {
        PhaseHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                public boolean apply(PhaseHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                    public boolean apply(PhaseHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        if(clientLanguage != null){
            return clientLanguage.getGlobalName();
        }
        return globalName;
    }

    public void setGlobalName(String globalName) {
        this.globalName = globalName;
    }

    public String getName() {
        return name;
    }

    public String getName(final Language language, final Language defaultLanguage) {
        PhaseHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                public boolean apply(PhaseHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                    public boolean apply(PhaseHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        if(clientLanguage != null){
            return clientLanguage.getName();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public Integer getOrder() {
        return orden;
    }

    public void setOrder(Integer order) {
        this.orden = order;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getFn() {
        return fn;
    }

    public void setFn(Integer fn) {
        this.fn = fn;
    }

    public List<GameMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<GameMatch> matches) {
        this.matches = matches;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<PhaseHasLocalization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<PhaseHasLocalization> localizations) {
        this.localizations = localizations;
    }

    public static Phase findById(Long id){
        return finder.byId(id);
    }

    public List<Rank> getRanks() {
        Collections.sort(ranks, new RanksComparator());
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public static Phase findByExtId(String idExt){
        return finder.where().eq("extId",idExt).findUnique();
    }

    public static List<Phase> findByNivel(Competition competition, int nivel){
        return finder.where().eq("comp", competition).eq("nivel", nivel).findList();
    }

    public static List<Phase> findByGlobalName(Competition competition, String globalName){
        return finder.where().eq("comp", competition).eq("globalName", globalName).findList();
    }

    public List<Rank> getRanksOrderedByGroup() {
        Collections.sort(ranks, new RanksGroupComparator());
        return ranks;
    }

    public static List<Phase> getList(Long idCompetition,String sd, String end){
        //System.out.println("Phase idCompetition:"+idCompetition);
        //if(sd.isEmpty() && end.isEmpty()) return finder.all();
        ExpressionList<Phase> phases = finder.where().eq("id_competitions",idCompetition);
        if(!sd.isEmpty()){
            if(sd.contains("-")){
                String[] range = sd.split("-");
                phases.ge("startDate", range[0]);
                phases.le("startDate", range[1]);
            }else{
                phases.eq("startDate", sd);
            }
        }

        if(!end.isEmpty()){
            if(end.contains("-")){
                String[] range = end.split("-");
                phases.ge("endDate", range[0]);
                phases.le("endDate", range[1]);
            }else{
                phases.eq("endDate", end);
            }
        }

        return phases.findList();
    }

    public static List<Phase> getAllPhases(Long idCompetition){
        return finder.where().eq("id_competitions",idCompetition).findList();
    }

    public static List<Phase> getTodayPhases(Long idCompetition){
        Calendar today = new GregorianCalendar(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedToday = simpleDateFormat.format(today.getTime());

        return finder.where().eq("id_competitions",idCompetition).le("startDate", formattedToday).ge("endDate", formattedToday).findList();
    }

    public static List<Phase> getPhaseByDate(Long idCompetition, String formattedToday){
        return finder.where().eq("id_competitions",idCompetition).le("startDate", formattedToday).ge("endDate", formattedToday).findList();
    }

    public static Phase getUniquePhaseByDate(Competition competition, String formattedToday){
        return finder.where().eq("comp", competition).le("startDate", formattedToday).ge("endDate", formattedToday).ne("type", 1).findUnique();
    }

    public static List<Phase> getUniquePhaseByDateList(Competition competition, String formattedToday){
        return finder.where().eq("comp", competition).le("startDate", formattedToday).ge("endDate", formattedToday).ne("type", 1).findList();
    }

    public static List<Phase> getPhasesFromDate(Competition competition, String date){
        return finder.where().eq("comp", competition).ge("start_date", date).orderBy("start_date asc").setMaxRows(1).findList();
    }

    public static List<Phase> getLatestPhasesPaged(Competition competition, int index, int size) {
        return finder.where().eq("comp", competition).orderBy("start_date desc").setFirstRow(index).setMaxRows(size).findList();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode obj = Json.newObject();
        obj.put("id_phases",idPhases);
        obj.put("global_name",globalName);
        obj.put("name",name);
        obj.put("start_date",startDate);
        obj.put("end_date",endDate);
        obj.put("ext_id",extId);
        obj.put("pushed", pushed);
        obj.put("type", type);
        return obj;
    }

    public ObjectNode toJsonSimple() {
        ObjectNode obj = Json.newObject();
        obj.put("id_initial_phases",idPhases);
        obj.put("competition", comp.toJsonSimple());
        obj.put("global_name",globalName);
        obj.put("name",name);
        obj.put("start_date",startDate);
        obj.put("end_date",endDate);
        obj.put("ext_id",extId);
        obj.put("pushed", pushed);
        obj.put("type", type);
        return obj;
    }

    public ObjectNode toJsonToPush() {
        ObjectNode obj = Json.newObject();
        obj.put("competition_id", comp.getIdCompetitions());
        obj.put("competition_name", comp.getName());
        obj.put("global_name",globalName);
        obj.put("name",name);
        obj.put("type", type);
        return obj;
    }

    public ObjectNode toJsonSimple(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_initial_phases",idPhases);
        obj.put("competition", comp.toJsonSimple(language, defaultLanguage));
        PhaseHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                public boolean apply(PhaseHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                    public boolean apply(PhaseHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("global_name",clientLanguage!=null?clientLanguage.getGlobalName():globalName);
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("start_date",startDate);
        obj.put("end_date",endDate);
        obj.put("ext_id",extId);
        obj.put("pushed", pushed);
        obj.put("type", type);
        return obj;
    }

    public ObjectNode toJson(final Language language, final Language defaultLanguage) {
        ObjectNode obj = Json.newObject();
        obj.put("id_phases",idPhases);
        PhaseHasLocalization clientLanguage = null;
        try {
            clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                public boolean apply(PhaseHasLocalization obj) {
                    return obj.getLanguage().getIdLanguage().intValue() == language.getIdLanguage().intValue();
                }
            });
        } catch (NoSuchElementException e){
            try {
                clientLanguage = Iterables.find(localizations, new Predicate<PhaseHasLocalization>() {
                    public boolean apply(PhaseHasLocalization obj) {
                        return obj.getLanguage().getIdLanguage().intValue() == defaultLanguage.getIdLanguage().intValue();
                    }
                });
            } catch (NoSuchElementException ex){
                clientLanguage = null;
            }
        }
        obj.put("global_name",clientLanguage!=null?clientLanguage.getGlobalName():globalName);
        obj.put("name",clientLanguage!=null?clientLanguage.getName():name);
        obj.put("start_date",startDate);
        obj.put("end_date",endDate);
        obj.put("ext_id",extId);
        obj.put("pushed", pushed);
        obj.put("has_ranking", hasRanking());
        obj.put("type", type);
        return obj;
    }

    public boolean hasRanking(){
        boolean flag = false;
        if (comp.getType().getType() == 0) {
            flag = ranks != null && !ranks.isEmpty();
        } else if (comp.getType().getType() == 1) {
            flag = matches != null && !matches.isEmpty();
        } else {
            if (nivel == 1) {
                flag = ranks != null && !ranks.isEmpty();
            } else {
                flag = matches != null && !matches.isEmpty();
            }
        }
        return flag;
    }

    public void validate(Language language){
        Phase toValidate = findByExtId(this.extId);
        if (toValidate != null){
            this.idPhases = toValidate.idPhases;
            this.comp = toValidate.comp;
            this.globalName = toValidate.globalName;
            this.name = toValidate.name;
            this.startDate = toValidate.startDate;
            this.endDate = toValidate.endDate;
            this.extId = toValidate.extId;
            this.localizations = toValidate.localizations;
        }else {
            this.save();
        }

        PhaseHasLocalization phaseHasLocalization = new PhaseHasLocalization(this, language, this.globalName, this.name);
        if(!PhaseHasLocalization.exists(phaseHasLocalization)){
            //System.out.println("no existe " + this.getName() + " " + language.getName());
            this.localizations.add(phaseHasLocalization);
            phaseHasLocalization.save();
            this.update();
        }
    }

    public static Phase getPhaseByFn(long idCompetition, int fn){
        if (fn == 0){
            return finder.where().eq("id_competitions",idCompetition).orderBy("fn asc").setMaxRows(1).findUnique();
        }else {
            return finder.where().eq("id_competitions",idCompetition).eq("fn",fn).setMaxRows(1).findUnique();
        }
    }


    public static List<Phase> getPhasesToPush(Competition competition, String date){
        return finder.where().eq("pushed", false).eq("comp", competition).le("end_date", date).orderBy("end_date desc").findList();
    }


}

class RanksGroupComparator implements Comparator<Rank> {
    @Override
    public int compare(Rank c1, Rank c2) {
        int order = c1.getGroup().getName().compareTo(c2.getGroup().getName());
        if(order == 0){
            order = (int)(c2.getPoints() - c1.getPoints());
            if(order == 0){
                order = c2.getGoalDiff() - c1.getGoalDiff();
            }
        }
        return order;
    }
}

class RanksComparator implements Comparator<Rank> {
    @Override
    public int compare(Rank c1, Rank c2) {
        int order = (int)(c2.getPoints() - c1.getPoints());
        if(order == 0){
            order = c2.getGoalDiff() - c1.getGoalDiff();
        }
        return order;
    }
}
