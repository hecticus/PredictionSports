package controllers.client;

import models.manager.responseUtils.Response;
import models.manager.AccumulatedManager;
import play.mvc.Result;


/**
 * Created by drocha on 22/03/17.
 */


public class Accumulateds
{
    private static AccumulatedManager accumulatedManager = new AccumulatedManager();

    public static Result GetPointsByClientId()
    {
        return accumulatedManager.GetPointsByClientId();
    }
}
