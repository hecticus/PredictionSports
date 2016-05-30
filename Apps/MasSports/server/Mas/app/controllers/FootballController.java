package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.UpstreamException;
import models.Config;
import play.libs.Json;

import static play.libs.Jsonp.jsonp;

/**
 * Created by plessmann on 02/06/15.
 */
public class FootballController extends HecticusController {

    public static ObjectNode buildUpstreamResponse(int code, String responseMsg, UpstreamException e) {
        ObjectNode responseNode = Json.newObject();
        responseNode.put(Config.ERROR_KEY, code);
        responseNode.put(Config.DESCRIPTION_KEY, responseMsg);
        responseNode.put(Config.EXCEPTION_KEY, e.getMessage());
        responseNode.put(Config.UPSTREAM_CODE, e.getCode());
        return responseNode;
    }


}
