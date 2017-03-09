package models.handlers;

import models.domain.Status;

/**
 * Created by palenge on 12/30/16.
 */
public class StatusHandler {
    public static Status CheckAndInsert(String name) {
        Status tm = Status.getByName(name);
        if(tm == null) {
            tm = new Status(name);
            tm.save();
        }
        return tm;
    }
    
}
