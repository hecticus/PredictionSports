package models.manager;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;
import models.domain.Accumulated;
import models.Dao.AccumulatedDao;
import models.manager.responseUtils.Response;

import static play.mvc.Controller.request;

/**
 * Created by drocha on 22/03/17.
 */
public class AccumulatedManager
{

    private static AccumulatedDao accumulatedDao = new AccumulatedDao();

    public static Result GetPointsByClientId() {

        try {



                JsonNode json = request().body().asJson();
                if (json == null)
                    return Response.requiredJson();

                JsonNode clientId = json.get("clientId");
                if (clientId == null)
                    return Response.requiredParameter("clientId");

                JsonNode idSeason = json.get("idSeason");
                if (idSeason == null)
                    return Response.requiredParameter("idSeason");

                JsonNode idApp = json.get("idApp");
                if (idApp == null)
                    return Response.requiredParameter("idApp");

                Accumulated accumulated = accumulatedDao.GetPointsByClientId(idSeason.asText(), idApp.asText(), clientId.asText());
                return Response.foundEntity(Json.toJson(accumulated));

        }catch(Exception e){
            return Response.internalServerErrorLF();
        }
    }
}
