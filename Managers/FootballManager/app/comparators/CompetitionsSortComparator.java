package comparators;

import models.football.Competition;

import java.util.Comparator;

/**
 * Created by plesse on 4/28/15.
 */
public class CompetitionsSortComparator implements Comparator<Competition> {
    @Override
    public int compare(Competition c1, Competition c2) {
        return c1.getType().getSort() - c2.getType().getSort();
    }
}
