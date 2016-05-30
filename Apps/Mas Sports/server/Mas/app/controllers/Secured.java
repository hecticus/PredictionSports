package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import utils.Utils;

/**
 * Created by plesse on 3/12/15.
 */
public class Secured extends Security.Authenticator {

    public final static String AUTH_TOKEN_HEADER = "HECTICUS-X-AUTH-TOKEN";
    public static final String AUTH_QUERY_STRING = "api_password";

    /**
     * Metodo para definir la validacion del token donde se envie el md5 de autenticacion
     *
     * @param ctx       context de la aplicacion para minar el header
     * @return          null para rechazar y cualquier string para aceptar
     */
    @Override
    public String getUsername(Http.Context ctx) {
        boolean isSecured = Utils.getIsSecured();
        if(isSecured) {
            Http.Request request = ctx.request();
            String realOrigin = null;
            if (request.headers().containsKey("X-Forwarded-For")) {
                realOrigin = request.headers().get("X-Forwarded-For")[0];
            }
            String ipString = request.remoteAddress();
            boolean secured = true;
            if (realOrigin != null && !realOrigin.isEmpty() && (realOrigin.startsWith("127.0.0.1") || realOrigin.startsWith("10."))) {
                secured = false;
            }
            if (secured) {
                String apiPassword = getApiPassword(request);
                if (apiPassword != null && !apiPassword.isEmpty()) {
                    boolean valid = validateAuthToken(apiPassword);
                    if (valid) {
                        Logger.of("secured").trace("ip  = " + ipString + " realOrigin = " + realOrigin + " Status: ACCEPTED, Valid Token " + apiPassword);
                        return "OK";
                    }
                    Logger.of("secured").trace("ip  = " + ipString + " realOrigin = " + realOrigin + " Status: DENIED, Invalid token = " + apiPassword);
                } else {
                    Logger.of("secured").trace("ip  = " + ipString + " realOrigin = " + realOrigin + " Status: DENIED, Missing Header " + AUTH_TOKEN_HEADER + " or Query String " + AUTH_QUERY_STRING);
                }
                return null;
            } else {
                return "OK";
            }
        } else {
            return "OK";
        }
    }

    private String getApiPassword(Http.Request request) {
        String apiPassword = request.getQueryString(AUTH_QUERY_STRING);
        if(Utils.acceptHeader() && (apiPassword == null || apiPassword.isEmpty())) {
            apiPassword = request.getHeader(AUTH_TOKEN_HEADER);
        }
        return apiPassword;
    }

    private boolean validateAuthToken(String apiPassword) {
        try {
            String companyName = Config.getString("company-name");//1234
            String buildVersion = Config.getString("build-version");//9
            String serverVersion = Config.getString("server-version");//1
            String keyCompanyName = apiPassword.substring(0, companyName.length());//1234
            char first = apiPassword.charAt(companyName.length());//)
            String keyBuildVersion = apiPassword.substring(companyName.length() + 1, companyName.length() + 1 + buildVersion.length());//
            char second = apiPassword.charAt(companyName.length() + 1 + buildVersion.length());
            String keyServerVersion = apiPassword.substring(companyName.length() + 1 + keyBuildVersion.length() + 1);
            boolean valid = companyName.contentEquals(keyCompanyName) && buildVersion.contentEquals(keyBuildVersion) && serverVersion.contentEquals(keyServerVersion);
            int indexFirst = buildVersion.charAt(0) - 48;
            int indexSecond = serverVersion.charAt(0) - 48;
            valid &= validChar(indexFirst, first);
            valid &= validChar(indexSecond, second);
            System.out.println(keyCompanyName + " " + first + " " + keyBuildVersion + " " + second + " " + keyServerVersion + " " + indexFirst + " " + indexSecond + " " + valid);
            return valid;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validChar(int caseToValidate, char charToValidate){
        switch (caseToValidate){
            case 1:
                return charToValidate == '|';
            case 2:
                return charToValidate == '@';
            case 3:
                return charToValidate == '#';
            case 4:
                return charToValidate == '$';
            case 5:
                return charToValidate == '%';
            case 6:
                return charToValidate == '&';
            case 7:
                return charToValidate == '/';
            case 8:
                return charToValidate == '(';
            case 9:
                return charToValidate == ')';
            case 0:
                return charToValidate == '=';
            default:
                return false;
        }
    }

    /**
     * Metodo para manejar los request rechazados por no tener el token de autenticacion o poner tener el token incorrecto
     *
     * @param ctx       contexto http (obligatorio)
     * @return          respuesta a dar al usuario rechazado
     */
    @Override
    public Result onUnauthorized(Http.Context ctx) {
        ObjectNode response = Json.newObject();
        response.put("error", 1);
        response.put("description", "Invalid User");
        return forbidden(response);
    }



}