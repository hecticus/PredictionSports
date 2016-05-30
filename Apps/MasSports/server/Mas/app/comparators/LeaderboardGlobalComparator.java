package comparators;

import models.leaderboard.LeaderboardGlobal;

import java.util.Comparator;

/**
 * Created by plessmann on 19/05/15.
 */
public class LeaderboardGlobalComparator implements Comparator<LeaderboardGlobal> {
    @Override
    public int compare(LeaderboardGlobal c1, LeaderboardGlobal c2) {
        int value =  c2.getScore() - c1.getScore();
        if(value == 0){
            value =  c2.getCorrectBets() - c1.getCorrectBets();
        }
        return value;
    }
}