package models.handlers;

import models.domain.League;
import models.domain.Team;

/**
 * Created by palenge on 12/29/16.
 */
public class TeamHandler extends Team {


    public static Team CheckAndInsert(Long idTeam, String name, String shortCode, String city, League league) {
        Team tm = Team.getByShortCode(shortCode);
        if(tm.getLeague().getIdLeague() != league.getIdLeague())
        {
            tm.setLeague(league);
            tm.update();
        }
        if(tm == null) {
            tm = new Team(city, idTeam, name, shortCode, league);
            tm.save();
        }
        return tm;
    }

}
