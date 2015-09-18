package comparators;

import models.football.GameMatch;
import utils.DateAndTime;

import java.util.*;

/**
 * Created by plessmann on 20/05/15.
 */
public class GameMatchDateComparator implements Comparator<GameMatch> {
    @Override
    public int compare(GameMatch a, GameMatch b) {
        try{
            Calendar aCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            Date aDate = DateAndTime.getDate(a.getDate(), "yyyyMMddHHmmss");
            aCalendar.setTime(aDate);

            Calendar bCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            Date bDate = DateAndTime.getDate(b.getDate(), "yyyyMMddHHmmss");
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