package controllers.footballapi;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import controllers.HecticusController;
import models.Apps;
import models.Language;
import models.football.Competition;
import models.football.GameMatch;
import models.football.Phase;
import models.football.Rank;
import play.mvc.Result;
import play.libs.Json;
import utils.DateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chrirod on 11/24/14.
 */
public class RankingController extends HecticusController {

    public static Result getRankings(Integer idApp, Integer idLanguage, String formattedToday){
        try {
            Apps app = Apps.findId(idApp);
            ObjectNode response = null;
            if(app != null) {
                Language requestLanguage = null;
                if (idLanguage > 0) {
                    requestLanguage = Language.getByID(idLanguage);
                }
                if (idLanguage <= 0 || requestLanguage == null) {
                    requestLanguage = app.getLanguage();
                }
                List<Competition> competitions = Competition.getCompetitionsByApp(app);
                ArrayList data = new ArrayList();
                List<Rank> ranks = null;
                ObjectNode phase = null;
                ArrayList rankingObjs = new ArrayList();
                for (int i = 0; i < competitions.size(); ++i) {
                    List<Phase> phases = Phase.getPhaseByDate(competitions.get(i).getIdCompetitions(), formattedToday);
                    if (phases != null && !phases.isEmpty()) {
                        ArrayList phasesObjs = new ArrayList();
                        for (int j = 0; j < phases.size(); ++j) {
                            ranks = Rank.finder.where().eq("id_phases", phases.get(j).getIdPhases()).orderBy("nivel asc, orden asc").findList();
                            if (ranks != null && !ranks.isEmpty()) {
                                phase = phases.get(j).toJson(requestLanguage, app.getLanguage());
                                rankingObjs = new ArrayList();
                                for (int z = 0; z < ranks.size(); ++z) {
                                    rankingObjs.add(ranks.get(z).toJsonPhaseID());
                                }
                                phase.put("ranks", Json.toJson(rankingObjs));
                                phasesObjs.add(phase);
                                ranks.clear();
                                rankingObjs.clear();
                            }
                        }
                        ObjectNode competition = competitions.get(i).toJsonSimple(requestLanguage, app.getLanguage());
                        competition.put("phases", Json.toJson(phasesObjs));
                        data.add(competition);
                    }
                }
                response = hecticusResponse(0, "ok", "rankings", data);
                data.clear();
                return ok(response);
            } else {
                response = buildBasicResponse(1, "El app " + idApp + " no existe");
                return notFound(response);
            }
        }catch (Exception ex){
            return internalServerError(buildBasicResponse(-1, "ocurrio un error al traer los rankings:" + ex.toString()));
        }
    }

    public static Result getRankingsForPhase(Integer idApp, Integer idCompetition, Integer idLanguage, Long idPhase, Integer way){
        try {
            ObjectNode response = null;
            final Apps app = Apps.findId(idApp);
            if(app != null) {
                Competition competition = app.getCompetition(idCompetition);
                if (competition != null) {
                    Language requestLanguage = null;
                    if (idLanguage > 0) {
                        requestLanguage = Language.getByID(idLanguage);
                    }
                    if (idLanguage <= 0 || requestLanguage == null) {
                        requestLanguage = app.getLanguage();
                    }
                    Phase phase = null;
                    List<Rank> ranks = null;
                    ObjectNode data = Json.newObject();
                    final Calendar today = new GregorianCalendar(TimeZone.getDefault());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    String formattedToday = simpleDateFormat.format(today.getTime());
                    if (competition.getType().getType() == 0) {//LIGA
                        if (idPhase > 0) {
                            phase = competition.getPhase(idPhase);//Phase.findById(idPhase);
                        } else {
                            phase = Phase.getUniquePhaseByDate(competition, formattedToday);
                        }
                        if (phase != null) {
                            ranks = phase.getRanks();
                            if (ranks != null && !ranks.isEmpty()) {
                                ArrayList rankingObjs = new ArrayList();
                                ArrayList<ObjectNode> group = new ArrayList<>();
                                for (int z = 0; z < ranks.size(); ++z) {
                                    group.add(ranks.get(z).toJsonPhaseID());
                                }
                                ObjectNode member = Json.newObject();
                                member.put("group_name", "GENERAL");
                                member.put("ranking", Json.toJson(group));
                                rankingObjs.add(member);
                                data.put("tree", false);
                                data.put("phase", phase.toJsonSimple(requestLanguage, app.getLanguage()));
                                data.put("ranking", Json.toJson(rankingObjs));
                                response = hecticusResponse(0, "ok", data);
                                ranks.clear();
                                rankingObjs.clear();
                            } else {
                                response = buildBasicResponse(3, "El ranking de la phase " + idPhase + " no existe o esta vacio");
                            }
                        } else {
                            response = buildBasicResponse(4, "La phase " + idPhase + " no existe");
                        }
                    } else if (competition.getType().getType() == 1) {//ARBOL
                        List<Phase> phases = null;
                        if (way != 0 && idPhase <= 0) {
                            //invalid
                            phases = null;
                        } else if (way == 1) {
                            //next
                            phase = competition.getPhase(idPhase);//Phase.findById(idPhase);
                            phases = competition.getPhasesByNivel(phase.getNivel() + 1);//Phase.findByNivel(competition, phase.getNivel() + 1);
                        } else if (way == -1) {
                            //previous
                            phase = competition.getPhase(idPhase);//Phase.findById(idPhase);
                            phases = competition.getPhasesByNivel(phase.getNivel() - 1);//Phase.findByNivel(competition, phase.getNivel() - 1);
                        } else if (idPhase > 0) {
                            phase = competition.getPhase(idPhase);//Phase.findById(idPhase);
                            phases = competition.getPhasesByGlobalName(phase.getGlobalName());//Phase.findByGlobalName(competition, phase.getGlobalName());
                        } else {
                            phases = Phase.getPhaseByDate(competition.getIdCompetitions(), formattedToday);
                        }
                        if (phases != null && !phases.isEmpty()) {
                            phase = phases.get(0);
                            List<GameMatch> gameMatches = GameMatch.finder.where().in("phase", phases).orderBy("phase asc").findList();
                            if (gameMatches != null && !gameMatches.isEmpty()) {
                                GameMatch pivot = gameMatches.get(0);
                                ArrayList rankingObjs = new ArrayList();
                                ArrayList<ObjectNode> group = new ArrayList<>();
                                for (GameMatch gameMatch : gameMatches) {
                                    if (gameMatch.getPhase().getIdPhases() == pivot.getPhase().getIdPhases()) {
                                        group.add(gameMatch.toJson());
                                    } else {
                                        ObjectNode member = Json.newObject();
                                        member.put("group_name", pivot.getPhase().getName(requestLanguage, app.getLanguage()));
                                        member.put("ranking", Json.toJson(group));
                                        rankingObjs.add(member);
                                        group.clear();
                                        group.add(gameMatch.toJson());
                                        pivot = gameMatch;
                                    }
                                }
                                if (!group.isEmpty()) {
                                    ObjectNode member = Json.newObject();
                                    member.put("group_name", pivot.getPhase().getName(requestLanguage, app.getLanguage()));
                                    member.put("ranking", Json.toJson(group));
                                    rankingObjs.add(member);
                                    group.clear();
                                }
                                gameMatches.clear();
                                data.put("tree", true);
                                data.put("phase", phase.toJsonSimple(requestLanguage, app.getLanguage()));
                                data.put("ranking", Json.toJson(rankingObjs));
                                rankingObjs.clear();
                                response = hecticusResponse(0, "ok", data);
                            } else {
                                if (way != 0) {
                                    response = buildBasicResponse(6, "La phase " + idPhase + " no tiene siguiente");
                                } else {
                                    response = buildBasicResponse(3, "El ranking de la phase " + idPhase + " no existe o esta vacio");
                                }
                            }
                        } else {
                            if (way != 0) {
                                response = buildBasicResponse(5, "La phase " + idPhase + " no tiene anterior");
                            } else {
                                response = buildBasicResponse(4, "La phase " + idPhase + " no existe");
                            }
                        }
                    } else {

                        List<Phase> phases = null;
                        if (idPhase > 0) {
                            phase = competition.getPhase(idPhase);
//                            phases = competition.getPhasesByGlobalNameAndDate(phase.getGlobalName(), today, app.getTimezone().getTimezone());
                            phases = competition.getPhasesByGlobalName(phase.getGlobalName());
                        } else {
                            phases = Phase.getPhaseByDate(competition.getIdCompetitions(), formattedToday);
                        }

                        if (phases != null && !phases.isEmpty()) {

                            try {
//                                final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                phase = Iterables.find(phases, new Predicate<Phase>() {
                                    public boolean apply(Phase obj) {
                                        Calendar startCalendar = new GregorianCalendar(app.getTimezone().getTimezone());
                                        Calendar endCalendar = new GregorianCalendar(app.getTimezone().getTimezone());
                                        try {
                                            Date startDate = DateAndTime.getDate(obj.getStartDate(), "yyyyMMdd");
                                            startCalendar.setTime(startDate);
                                            Date endDate = DateAndTime.getDate(obj.getEndDate(), "yyyyMMdd");
                                            endCalendar.setTime(endDate);
//                                            System.out.println(obj.getIdPhases() + sf.format(startCalendar.getTime()) + " " + sf.format(today.getTime()) + " " + sf.format(endCalendar.getTime()) + " " + (today.equals(startCalendar)) + " " + (today.equals(endCalendar)) + " " + (today.after(startCalendar)) + " " + (today.before(endCalendar)) + " " +(today.after(startCalendar) && today.before(endCalendar)) +" " + (today.equals(startCalendar) || today.equals(endCalendar) || (today.after(startCalendar) && today.before(endCalendar))));
                                            return today.equals(startCalendar) || today.equals(endCalendar) || (today.after(startCalendar) && today.before(endCalendar));
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            return false;
                                        }
                                    }
                                });
                            } catch (NoSuchElementException ex){
                                List<Phase> latestPhases = competition.getLatestPhases(today, app.getTimezone().getTimezone());
                                phase = latestPhases.get(latestPhases.size()-1);
                            }

                            if (phase.getNivel() == 1) {//TABLA
                                ranks = phase.getRanksOrderedByGroup();
                                if (ranks != null && !ranks.isEmpty()) {
                                    ArrayList<ObjectNode> group = new ArrayList<>();
                                    Rank pivot = ranks.get(0);
                                    ArrayList rankingObjs = new ArrayList();
                                    for (Rank rank : ranks) {
                                        if (rank.getGroup().getName().equalsIgnoreCase(pivot.getGroup().getName())) {
                                            group.add(rank.toJsonPhaseID());
                                        } else {
                                            ObjectNode member = Json.newObject();
                                            member.put("group_name", pivot.getGroup().getName());
                                            member.put("ranking", Json.toJson(group));
                                            rankingObjs.add(member);
                                            group.clear();
                                            group.add(rank.toJsonPhaseID());
                                            pivot = rank;
                                        }
                                    }
                                    if (!group.isEmpty()) {
                                        ObjectNode member = Json.newObject();
                                        member.put("group_name", pivot.getGroup().getName());
                                        member.put("ranking", Json.toJson(group));
                                        rankingObjs.add(member);
                                        group.clear();
                                    }
                                    data.put("tree", phase.getNivel() > 1);
                                    data.put("phase", phase.toJsonSimple(requestLanguage, app.getLanguage()));
                                    data.put("ranking", Json.toJson(rankingObjs));
                                    rankingObjs.clear();
                                    response = hecticusResponse(0, "ok", data);
                                } else {
                                    response = buildBasicResponse(3, "El ranking de la phase " + idPhase + " no existe o esta vacio");
                                }
                            } else {//ARBOL
                                phase = phases.get(0);
                                List<GameMatch> gameMatches = GameMatch.finder.where().in("phase", phases).orderBy("phase asc").findList();
                                if (gameMatches != null && !gameMatches.isEmpty()) {
                                    GameMatch pivot = gameMatches.get(0);
                                    ArrayList rankingObjs = new ArrayList();
                                    ArrayList<ObjectNode> group = new ArrayList<>();
                                    for (GameMatch gameMatch : gameMatches) {
                                        if (gameMatch.getPhase().getIdPhases() == pivot.getPhase().getIdPhases()) {
                                            group.add(gameMatch.toJson());
                                        } else {
                                            ObjectNode member = Json.newObject();
                                            member.put("group_name", phase.getGlobalName(requestLanguage, app.getLanguage()));
                                            member.put("ranking", Json.toJson(group));
                                            rankingObjs.add(member);
                                            group.clear();
                                            group.add(gameMatch.toJson());
                                            pivot = gameMatch;
                                        }
                                    }
                                    if (!group.isEmpty()) {
                                        ObjectNode member = Json.newObject();
                                        member.put("group_name", phase.getGlobalName(requestLanguage, app.getLanguage()));
                                        member.put("ranking", Json.toJson(group));
                                        rankingObjs.add(member);
                                        group.clear();
                                    }
                                    data.put("tree", true);
                                    data.put("phase", phase.toJsonSimple(requestLanguage, app.getLanguage()));
                                    data.put("ranking", Json.toJson(rankingObjs));
                                    rankingObjs.clear();
                                    response = hecticusResponse(0, "ok", data);
                                } else {
                                    response = buildBasicResponse(5, "El ranking de la phase " + idPhase + " no existe o esta vacio");
                                }
                            }
                            phases.clear();
                        } else {
                            response = buildBasicResponse(4, "El ranking de la phase " + idPhase + " no existe o esta vacio");
                        }
                    }
                } else {
                    response = buildBasicResponse(1, "La competencia " + idCompetition + " no esta disponible para la app " + idApp);
                }
            } else {
                response = buildBasicResponse(1, "El app " + idApp + " no existe");
            }
            return ok(response);
        }catch (Exception ex){
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error al traer los rankings:" + ex.toString()));
        }
    }


}
