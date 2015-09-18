package comparators;

import models.leaderboard.Leaderboard;

import java.util.Comparator;

/**
 * Created by plessmann on 19/05/15.
 */
public class LeaderboardComparator implements Comparator<Leaderboard> {
    @Override
    public int compare(Leaderboard c1, Leaderboard c2) {
        int value =  c2.getScore() - c1.getScore();
        if(value == 0){
            value =  c2.getCorrectBets() - c1.getCorrectBets();
        }
        return value;
    }
}