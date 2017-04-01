package models.handlers;

import models.domain.League;
import models.domain.LeagueType;

/**
 * Created by palenge on 12/30/16.
 */
public class LeagueTypeHandler {


    public static LeagueType CheckAndInsert(int name) {
        LeagueType tm = LeagueType.getByID(name);
        if(tm == null) {
            tm = new LeagueType("");
            tm.save();
        }
        return tm;
    }
}
