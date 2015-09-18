package comparators;

import models.football.Team;
import models.football.TeamHasCompetition;

import java.util.Comparator;

/**
 * Created by plesse on 5/13/15.
 */
public class TeamHasCompetitionComparator implements Comparator<TeamHasCompetition> {
    @Override
    public int compare(TeamHasCompetition t1, TeamHasCompetition t2) {
        return t1.getTeam().getName().compareTo(t2.getTeam().getName());
    }
}