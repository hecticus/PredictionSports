package models.handlers;

import models.domain.Game;
import models.domain.Inning;

/**
 * Created by palenge on 12/30/16.
 */
public class InningHandler {
    public static Inning CheckAndInsert(Game game, int InningNumber, int home, int away) {
        Inning tm = Inning.getByNumberAndGame(game, InningNumber);
        if(tm == null) {
            tm = new Inning(game, InningNumber, home, away);
            tm.save();
        }
        else
        {
            tm.setHomeAway(home, away);
            tm.update();
        }
        return tm;
    }
    
}
