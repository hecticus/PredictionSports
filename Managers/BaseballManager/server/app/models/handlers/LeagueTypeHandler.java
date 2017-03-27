package models.handlers;

import models.domain.League;
import models.domain.LeagueType;

/**
 * Created by palenge on 12/30/16.
 */
public class LeagueTypeHandler {


    public static LeagueType CheckAndInsert(String name) {
        LeagueType tm = LeagueType.getByName(name);
        if(tm == null) {
            tm = new LeagueType(name);
            tm.save();
        }
        return tm;
    }
}
