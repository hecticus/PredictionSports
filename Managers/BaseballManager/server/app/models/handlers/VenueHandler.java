package models.handlers;

import models.domain.Venue;

/**
 * Created by palenge on 12/30/16.
 */
public class VenueHandler {

    public static Venue CheckAndInsert(Long idVenue, String name) {
        Venue tm = Venue.getById(idVenue);
        if(tm == null) {
            tm = new Venue(idVenue, name);
            tm.save();
        }
        return tm;
    }
}
