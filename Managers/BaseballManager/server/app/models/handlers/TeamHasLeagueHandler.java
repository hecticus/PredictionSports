package models.handlers;

import models.domain.TeamHasLeague;

public class TeamHasLeagueHandler extends TeamHasLeague {


    public static TeamHasLeague CheckAndInsert(long idTeam, long idLeague ) {
        TeamHasLeague tm = TeamHasLeague.getByValues(idTeam, idLeague);
        if(tm == null) {
            tm = new TeamHasLeague(idTeam, idLeague);
            tm.save();
        }
        return tm;
    }

}
