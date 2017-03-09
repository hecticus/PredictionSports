package models.handlers;

import models.domain.League;

/**
 * Created by palenge on 12/30/16.
 */
public class LeagueHandler {


    public static League CheckAndInsert( String name) {
        League tm = League.getByName(name);
        if(tm == null) {
            tm = new League(name);
            tm.save();
        }
        return tm;
    }
}
