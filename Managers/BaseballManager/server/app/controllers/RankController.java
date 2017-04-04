package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import models.domain.*;
import play.libs.Json;
import play.mvc.Result;
import utils.DateAndTime;

import java.text.SimpleDateFormat;
import java.util.*;

//import jdk.nashorn.internal.ir.ObjectNode;
//import play.libs.WS.*;

/**
 * Created by Ferck on 27/2/2017.
 */
public class RankController extends HecticusController {

    public  Result getRankingsForPhase(Integer idCompetition){
        try {
            ObjectNode response = null;
            League competition = League.getByID((long)idCompetition); //TODO que carajo es esto
            if (competition != null) {
                List<Ranking> ranks = null;
                ObjectNode data = Json.newObject();
                final Calendar today = new GregorianCalendar(TimeZone.getDefault());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String formattedToday = simpleDateFormat.format(today.getTime());

                ranks = Ranking.getRanking((long)idCompetition);
                if (ranks != null && !ranks.isEmpty()) {
                    ArrayList rankingObjs = new ArrayList();
                    ArrayList<ObjectNode> group = new ArrayList<>();
                    for (int z = 0; z < ranks.size(); ++z) {
                        group.add(ranks.get(z).toJsonPhaseID());
                    }
                    ObjectNode member = Json.newObject();
                    member.put("group_name", "GENERAL");
                    member.put("ranking", Json.toJson(group));
                    rankingObjs.add(member);
                    data.put("tree", false);
                    data.put("phase", 0);
                    data.put("ranking", Json.toJson(rankingObjs));
                    response = hecticusResponse(0, "ok", data);
                    ranks.clear();
                    rankingObjs.clear();
                } else {
                    response = buildBasicResponse(3, "El ranking  no existe o esta vacio");
                }

            } else {
                response = buildBasicResponse(1, "La competencia " + idCompetition + " no esta disponible para la app ");
            }

            return ok(response);
        }catch (Exception ex){
            ex.printStackTrace();
            return internalServerError(buildBasicResponse(-1, "ocurrio un error al traer los rankings:" + ex.toString()));
        }
    }

}

/*

public class CompetitionsSortComparator implements Comparator<League> {
    @Override
    public int compare(League c1, League c2) {
        return c1.getType().getSort() - c2.getType().getSort();
    }
}


*/