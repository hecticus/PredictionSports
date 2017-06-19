package utils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import play.libs.Json;
import play.mvc.Result;

import javax.persistence.EntityNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static play.mvc.Results.*;

/**
 * Created by yenny on 9/7/16.
 */
public class Response {

    public static ObjectNode buildExtendResponse(String message, JsonNode result){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        response.set("result", result);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message, JsonNode result, JsonNode pager){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        response.set("result", result);
        response.set("pager", pager);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message){
        ObjectNode response = Json.newObject();
        response.put("message", message);
        return response;
    }

    public static String fieldNameToAttributeName(String message){
        StringBuilder name = new StringBuilder(message);
        int index = name.indexOf("_");
        while (index != -1){
            name.deleteCharAt(index);
            name.replace(index, index+1, String.valueOf(Character.toUpperCase(name.charAt(index))));
            index = name.indexOf("_");
        }
        return String.valueOf(name);
    }

    public static Result requiredJson(){
        return badRequest(buildExtendResponse("Expecting Json data"));
    }

    public static Result accessDenied() {
        return ok(buildExtendResponse("ACCESS DENIED"));
    }

    public static Result accessGranted(){
        return ok(buildExtendResponse("ACCESS GRANTED"));
    }

    public static Result Good(){
        return ok(buildExtendResponse("Message Sent!"));
    }

}