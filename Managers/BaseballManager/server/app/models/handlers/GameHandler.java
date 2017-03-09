package models.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import models.domain.Game;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by palenge on 12/30/16.
 */
public class GameHandler {
    public static boolean CheckExist(String identifier) {
        Game tm = Game.getByIdentifier(identifier);
        if(tm == null) {
          return false;
        }
        return true;
    }

    public static Game CheckAndGet(String identifier) {
        Game tm = Game.getByIdentifier(identifier);
        if(tm == null) {
            return new Game();
        }
        return tm;
    }

    public static Date DateUtil(String date)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date myDate = new Date();
        try {
            myDate = df.parse(date);
            String newDateString = df.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    @Deprecated
    public static Game fillLineScore(JsonNode json)
    {
        Game aux  = new Game();
        aux.setHr(new LineScoreHandler(json.get("hr")));
        aux.setE(new LineScoreHandler(json.get("e")));
        aux.setSo(new LineScoreHandler(json.get("so")));
        aux.setR(new LineScoreHandler(json.get("r")));
        aux.setH(new LineScoreHandler(json.get("h")));
        return aux;
    }

}
