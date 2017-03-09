package models.handlers;

import models.domain.Team;

/**
 * Created by palenge on 12/29/16.
 */
public class TeamHandler extends Team {


    public static Team CheckAndInsert(Long idTeam, String name, String shortCode, String city) {
        Team tm = Team.getByShortCode(shortCode);
        if(tm == null) {
            tm = new Team(city, idTeam, name, shortCode);
            tm.save();
        }
        return tm;
    }

}
