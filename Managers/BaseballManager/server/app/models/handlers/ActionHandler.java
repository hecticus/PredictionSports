package models.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import models.domain.Action;
import models.domain.Game;
import models.domain.Inning;

/**
 * Created by palenge on 1/3/17.
 */
public class ActionHandler {


    public static Action CheckAndGet(String guid) {
        Action tm = Action.getByGuid(guid);
        if(tm == null) {
            return new Action();
        }
        return tm;
    }

    public static boolean Check(String guid) {
        Action tm = Action.getByGuid(guid);
        if(tm == null) {
            return false;
        }
        return true;
    }

    public static void ActionAdd(Game game, boolean bottontop, long event_num, String event_es, String event_des_es, String event_guid, int inning_number)
    {
        try {
            Inning inn = Inning.getByNumberAndGame(game, inning_number);
            if (inn == null) return;
            boolean saveupdate = false;
            Action act = ActionHandler.CheckAndGet(event_guid);
            act.setEvent(EventHandler.CheckAndInsert(event_es));
            act.setInning(inn);
            act.setEventNum(event_num);
            act.setGuid(event_guid);
            act.setBottomTop(bottontop);
            act.setDescripcion(event_des_es);
            if (Check(event_guid)) act.update();
            else act.save();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void ActionAdd(Game game, boolean bottontop, JsonNode json, int inning_number)
    {
        try {
            Inning inn = Inning.getByNumberAndGame(game, inning_number);
            if (inn == null) return;
            boolean saveupdate = false;
            String guid = json.hasNonNull("play_guid") ? json.get("play_guid").asText() : (json.hasNonNull("start_tfs_zulu") ? json.get("start_tfs_zulu").asText() : json.get("tfs_zulu").asText());
            Action act = ActionHandler.CheckAndGet(guid);
            act.setEvent(EventHandler.CheckAndInsert(json.get("event_es").asText()));
            act.setInning(inn);
            act.setEventNum(json.get("event_num").asInt());
            act.setGuid(guid);
            act.setBottomTop(bottontop);
            act.setDescripcion(json.get("des_es").asText());
            if (Check(guid)) act.update();
            else act.save();
        }
        catch(Exception e)
        {
            System.out.println("Erro creando Action con json " + json);
            e.printStackTrace();
        }
    }
}
