package models.handlers;

import models.domain.Event;

/**
 * Created by palenge on 1/3/17.
 */
public class EventHandler {

    public static Event CheckAndGet(long id) {
        Event tm = Event.getById(id);
        if(tm == null) {
            return new Event();
        }
        return tm;
    }


    public static Event CheckAndInsert(String name) {
        Event tm = Event.getByName(name);
        if(tm == null) {
            tm = new Event(name);
            tm.save();
        }
        else
        {
            tm.setName(name);
            tm.update();
        }
        return tm;
    }
}
