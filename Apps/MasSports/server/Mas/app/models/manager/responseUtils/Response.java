package models.manager.responseUtils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
//import org.modelmapper.ModelMapper;
import play.libs.Json;
import play.mvc.Result;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static play.mvc.Results.*;

/**
 * Created by yenny on 9/7/16.
 */
public class Response {

    public static ObjectNode buildExtendResponse(String message, JsonNode jsonNode){
        ObjectNode result = Json.newObject();
        result.put("message", message);
        result.set("result", jsonNode);
        return result;
    }

    public static ObjectNode buildExtendResponse(String message){
        ObjectNode result = Json.newObject();
        result.put("message", message);
        return result;
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

    /*
    * ok
    */
    public static Result updatedEntity(JsonNode jsonNode){
        return ok(buildExtendResponse("Successful updated", jsonNode));
    }

    public static Result deletedEntity(){
        return ok(buildExtendResponse("Successful deleted"));
    }

    public static Result foundEntity(JsonNode jsonNode){
        return ok(buildExtendResponse("Successful search", jsonNode));
    }

    /*
    * created
    */
    public static Result createdEntity(JsonNode jsonNode){
        return created(buildExtendResponse("Successful created", jsonNode));
    }

    /*
    * badRequest
    */
    public static Result requiredJson(){
        return badRequest(buildExtendResponse("Expecting Json data"));
    }

    public static Result requiredParameter(String parameter){
        return badRequest(buildExtendResponse("Missing parameter [" + parameter + "]"));
    }

    public static Result invalidParameter(String parameter){
        return badRequest(buildExtendResponse("Invalid parameter [" + parameter + "]"));
    }

    public static Result invalidParameter(InvalidFormatException e){
        final Pattern pattern = Pattern.compile("\\[\"(.+?)\"\\]");
        Matcher matcher = pattern.matcher(e.getPathReference());
        String attribute = "<unknow>";
        if (matcher.find())
            attribute = matcher.group(1);
        while(matcher.find())
            attribute += "." + matcher.group(1);
        return invalidParameter(attribute);
    }

    public static Result invalidParameter(JsonMappingException e){
        final Pattern pattern = Pattern.compile("\\[\"(.+?)\"\\]");
        Matcher matcher = pattern.matcher(e.getPathReference());
        String attribute = "<unknow>";
        if (matcher.find())
            attribute = matcher.group(1);
        while(matcher.find())
            attribute += "." + matcher.group(1);
        return invalidParameter(attribute);
    }

    public static Result invalidParameter(MysqlDataTruncation e){
        final Pattern pattern = Pattern.compile("column \'(.+?)\'");
        Matcher matcher = pattern.matcher(e.getMessage());
        if(matcher.find())
            return invalidParameter(fieldNameToAttributeName(matcher.group(1)));
        else
            return invalidParameter("<unknow>");
    }

    public static Result uniqueViolation(String parameter){
        return badRequest(buildExtendResponse("No unique parameter [" + parameter + "]"));
    }

    public static Result permissionDenied(){
        return badRequest(buildExtendResponse("permission denied"));
    }

    public static Result constraintViolation(MySQLIntegrityConstraintViolationException e){
        return badRequest(e.getMessage());
    }

    /*
    * notFound
    */
    public static Result notFoundEntity(String entityType){
        return notFound(buildExtendResponse("Entity not found [" + entityType + "]"));
    }

    public static Result notFoundEntity(EntityNotFoundException e){
        final Pattern pattern = Pattern.compile("type\\[class (.+?)\\]");
        Matcher matcher = pattern.matcher(e.getMessage());
        if(matcher.find()) {
            String entity = matcher.group(1);
            return notFoundEntity(entity.substring(entity.lastIndexOf(".") + 1));
        }else {
            return notFoundEntity("<unknow>");
        }
    }

    /*
    * internalServerError
    */
    public static Result internalServerErrorLF(){
        return internalServerError(buildExtendResponse("Oops!"));
    }



    public static Result responseExceptionCreated(Exception e){
        Throwable eRoot = Response.getCause(e);
        if(eRoot != null) {
            if (eRoot instanceof EntityNotFoundException)
                return notFoundEntity((EntityNotFoundException) eRoot);

            if (eRoot instanceof InvalidFormatException)
                return invalidParameter((InvalidFormatException) eRoot);

            if (eRoot instanceof JsonMappingException)
                return Response.invalidParameter((JsonMappingException) eRoot);

            if (eRoot instanceof MysqlDataTruncation)
                return invalidParameter((MysqlDataTruncation) eRoot);

            if (eRoot instanceof MySQLIntegrityConstraintViolationException)
                return constraintViolation((MySQLIntegrityConstraintViolationException) eRoot);
        }
        return Response.internalServerErrorLF();
    }

    public static Result responseExceptionUpdated(Exception e){
        Throwable eRoot = Response.getCause(e);
        if(eRoot != null) {
            if (eRoot instanceof EntityNotFoundException)
                return notFoundEntity((EntityNotFoundException) eRoot);

            if (eRoot instanceof InvalidFormatException)
                return invalidParameter((InvalidFormatException) eRoot);

            if (eRoot instanceof JsonMappingException)
                return invalidParameter((JsonMappingException) eRoot);

            if (eRoot instanceof MysqlDataTruncation)
                return invalidParameter((MysqlDataTruncation) eRoot);

            if (eRoot instanceof MySQLIntegrityConstraintViolationException)
                return constraintViolation((MySQLIntegrityConstraintViolationException) eRoot);
        }
        return Response.internalServerErrorLF();
    }

    public static Result responseExceptionDeleted(Exception e){
        Throwable eRoot = Response.getCause(e);
        if(eRoot != null) {
            if(e!=null && e instanceof NullPointerException)
                return notFound(buildExtendResponse("Entity not found"));
        }
        return Response.internalServerErrorLF();
    }



 /*
   public static JsonNode toJson(Object object, Class typeDest){
        if(object != null)
            object = new ModelMapper().map(object, typeDest);
        return Json.toJson(object);
    }

    public static JsonNode toJson(List<?> objectSources, Class typeDest){
        List<Object> objectDests = new ArrayList<>();
        for(Object objectSource: objectSources)
            objectDests.add(new ModelMapper().map(objectSource, typeDest));
        return Json.toJson(objectDests);
    }
     */



    /*
    * get the root exception
    */
    public static Throwable getCause(Throwable e) {
        Throwable cause = null;
        Throwable result = e;
        while(null != (cause = result.getCause())  && (result != cause) )
            result = cause;
        return result;
    }
}
