package backend.jobs;

import akka.actor.Cancellable;
import backend.HecticusThread;
import models.Apps;
import models.Config;
import models.football.Competition;
import models.football.CompetitionType;
import models.football.Phase;
import utils.DateAndTime;
import utils.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by plesse on 1/30/15.
 */
public class CompetitionDeactivator  extends HecticusThread {


    public CompetitionDeactivator() {
        long start = System.currentTimeMillis();
        setName("CompetitionDeactivator-"+start);
        setInitTime(start);
        setActTime(start);
        setPrevTime(start);
    }

    public CompetitionDeactivator(String name, AtomicBoolean run, Cancellable cancellable) {
        super("CompetitionDeactivator-"+name, run, cancellable);
    }

    public CompetitionDeactivator(String name, AtomicBoolean run) {
        super("CompetitionDeactivator-"+name, run);
    }

    public CompetitionDeactivator(AtomicBoolean run) {
        super("CompetitionDeactivator",run);
    }

    @Override
    public void process(Map args) {
        Utils.printToLog(CompetitionDeactivator.class, null, "Iniciando CompetitionDeactivator", false, null, "support-level-1", Config.LOGGER_INFO);
        try {
            TimeZone timeZone = null;
            Date endDate = null;
            Calendar today = null;
            List<CompetitionType> competitionTypes = CompetitionType.getAll();
            for(CompetitionType competitionType : competitionTypes){
                List<Competition> competitions = competitionType.getCompetitions();
                Collections.sort(competitions, new CompetitionsComparator());
                for(int i = 0; i < competitions.size(); ++i){
                    Competition competition = competitions.get(i);
                    timeZone = competition.getApp().getTimezone().getTimezone();
                    List<Phase> phases = competition.getPhases();
                    Collections.sort(phases, new PhasesComparator());
                    if(!phases.isEmpty()){
                        Phase phase = phases.get(0);
                        endDate = DateAndTime.getDate(phase.getEndDate(), "yyyyMMdd");
                        today = new GregorianCalendar(timeZone);
                        if(endDate.before(today.getTime())){
                            competition.setStatus(0);
                        } else if(competition.getStatus() == 0){
                            competition.setStatus(1);
                        }
                    } else {
                        competition.setStatus(0);
                    }
                }
                competitionType.update();
            }
        } catch (Exception ex) {
            Utils.printToLog(CompetitionDeactivator.class, null, "Error desactivando competencias", false, ex, "support-level-1", Config.LOGGER_ERROR);
        } finally {
            Utils.printToLog(CompetitionDeactivator.class, null, "Termianndo CompetitionDeactivator", false, null, "support-level-1", Config.LOGGER_INFO);
        }
    }
}

class CompetitionsComparator implements Comparator<Competition> {
    @Override
    public int compare(Competition c1, Competition c2) {
        return (int) (c2.getIdCompetitions() - c1.getIdCompetitions());
    }
}

class PhasesComparator implements Comparator<Phase> {
    @Override
    public int compare(Phase c1, Phase c2) {
        return c2.getNivel() - c1.getNivel();
    }
}