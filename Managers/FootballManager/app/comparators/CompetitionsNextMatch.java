package comparators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.DateAndTime;

import java.util.*;

/**
 * Created by plessmann on 10/12/15.
 */
public class CompetitionsNextMatch implements Comparator<ObjectNode> {
    @Override
    public int compare(ObjectNode c1, ObjectNode c2) {
        JsonNode c1Match = c1.get("match");
        JsonNode c2Match = c2.get("match");
        if(!c1Match.has("date") && !c2Match.has("date")){
            return 0;
        } else if(c1Match.has("date") && !c2Match.has("date")){
            return -1;
        } else if(!c1Match.has("date") && c2Match.has("date")){
            return 1;
        }
        try{
            Calendar aCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            Date aDate = DateAndTime.getDate(c1Match.get("date").asText(), "yyyyMMddHHmmss");
            aCalendar.setTime(aDate);

            Calendar bCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            Date bDate = DateAndTime.getDate(c2Match.get("date").asText(), "yyyyMMddHHmmss");
            bCalendar.setTime(bDate);

            if(aCalendar.getTime().before(bCalendar.getTime())){
                return -1;
            } else if (aCalendar.equals(bCalendar)){
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
