package models.Dao;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import models.domain.Accumulated;
import models.manager.responseUtils.Response;
import play.libs.Json;

import java.util.List;

/**
 * Created by drocha on 22/03/17.
 */
public class AccumulatedDao
{
    public AccumulatedDao()   {

    }


    public Accumulated GetPointsByClientId(String idSeason,String idApp,String clientId)
    {
        String sql = " CALL `GetPointsByClientId`(:idSeason,:idApp,:clientId) ";

        SqlQuery query  = Ebean.createSqlQuery(sql)
                .setParameter("idSeason",idSeason)
                .setParameter("idApp",idApp)
                .setParameter("clientId",clientId);
        SqlRow result = query.findUnique();


        return toAccumulated(result);

    }




    public Accumulated toAccumulated(SqlRow result)
    {
        Accumulated accumulated = new Accumulated();

        accumulated.setPoints(Integer.parseInt(result.getString("puntos")));
        accumulated.setStrMSISDN(result.getString("s1"));
        return accumulated;
    }


}
