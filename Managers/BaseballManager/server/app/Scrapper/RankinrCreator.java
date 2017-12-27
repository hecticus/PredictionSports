package Scrapper;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.ActorMaterializerSettings;
import com.avaje.ebean.CallableSql;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Config;
import models.domain.Game;
import models.handlers.*;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.libs.ws.ahc.AhcWSClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletionStage;

//import com.google.inject.Inject;
//import scala.util.parsing.json.JSONArray;
//import scala.util.parsing.json.JSONObject;

/**
 * Created by palenge on 12/28/16.
 */
public class RankinrCreator {



    public static void executeRanking()
    {

        String callSql = "{call sp_rankinggenerator() }";
        CallableSql cs = Ebean.createCallableSql(callSql);
        Ebean.execute(cs);
    }
}
