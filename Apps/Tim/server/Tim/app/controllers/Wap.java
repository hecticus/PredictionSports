package controllers;

import play.data.Form;
import play.mvc.*;
import views.html.wap.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.*;
import play.libs.ws.*;
import play.libs.F.Promise;
import play.libs.Json;
import java.util.*;
import models.wap.*;
import play.cache.Cache;
import play.api.mvc.Cookie;
import play.api.mvc.DiscardingCookie;
import static play.data.Form.form;
import java.lang.Object;
import java.util.Arrays;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.ArrayUtils;
import java.text.MessageFormat;
import play.i18n.Messages;
import java.lang.*;
import java.lang.Object;
import java.net.URLEncoder;

public class Wap extends Loading {

    public static JsonNode jLoading = new Loading().getJLoading();
    //public static HandsetDetection HD =  new HandsetDetection();

    public static Boolean resolveAccessHTML5(HandsetDetection HD) {

        String sUserAgent = request().getHeader("User-Agent");


        if (sUserAgent.toLowerCase().contains("opera")) {
            return false;
        }

        if ((HD.getLevelSupport() == null)
                || (HD.getLevelSupport() >= HandsetDetection.HTML4_AJAX)) {
            return true;
        }

        return false;

    }


    public static Result getLogin() {
        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_DEFAULT"));
        return ok(login.render(form,HD,0));
    }

    public static Result getPassword() {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);

        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_DEFAULT"));

        try {
            Form<Client> filledForm = form.bindFromRequest();
            String sMsisdn = filledForm.field("msisdn").value();

            if ((sMsisdn.trim().isEmpty())
                    || (sMsisdn.length() < MIN_LENGTH_MSISDN)
                    || (sMsisdn.length() > MAX_LENGTH_MSISDN)
                    || (!isNumeric(sMsisdn))) {
                flash("error_msisdn", Messages.get("ERROR_MSISDN"));
                return redirect(controllers.routes.Wap.getLogin());
            }


            String sDomain =  URL_FOOTBALL_MANAGER_BRAZIL + "futbolbrasil/v1/clients/create" + getSecuretyTokenParam("?");
            ObjectNode jCompetition = Json.newObject();
            jCompetition.put("country", COUNTRY);
            jCompetition.put("login", sMsisdn);
            jCompetition.put("language",LANGUAGE);
            jCompetition.put("upstreamChannel", UPSTREAM_CHANNEL);


            Promise<WSResponse> wsResponse = WS.url(sDomain).post(jCompetition);

            JsonNode jResponse = wsResponse.get(10000).asJson();

            //System.out.println("<getPassword>");
            //System.out.println(jResponse.toString());
            //System.out.println("</createClient>");

            //System.out.println(jResponse.toString());
            Integer iError = jResponse.get("error").asInt();
            String sDescription = jResponse.get("description").asText();

            if ((iError == 0) || (isTestMsisdnClient(sMsisdn) >= 0)) {
                setAccessControl(filledForm);
                return ok(login.render(filledForm,HD,1));
            } else {
                return ok(error.render(HD, Messages.get("ERROR_SEND_PASSWORD"), false));
            }

        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),false));
        }

    }

    public static Result createClient() {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_DEFAULT"));

        try {
            Form<Client> filledForm = form.bindFromRequest();
            String sPassword = filledForm.field("password").value();

            if (sPassword.trim().isEmpty()) {
                flash("error_password", Messages.get("ERROR_PASSWORD"));
                return ok(login.render(filledForm,HD,1));
            }

            String sDomain =  URL_FOOTBALL_MANAGER_BRAZIL + "futbolbrasil/v1/clients/create" + getSecuretyTokenParam("?");

            ObjectNode jCompetition = Json.newObject();
            jCompetition.put("country",COUNTRY);
            jCompetition.put("login", filledForm.field("msisdn").value());
            jCompetition.put("password",filledForm.field("password").value());
            jCompetition.put("language",LANGUAGE);
            jCompetition.put("upstreamChannel",UPSTREAM_CHANNEL);

            Promise<WSResponse> wsResponse = WS.url(sDomain).post(jCompetition);
            JsonNode jResponse = wsResponse.get(10000).asJson();
            Integer iError = jResponse.get("error").asInt();
            String sDescription = jResponse.get("description").asText();

            /*System.out.println("<createClient>");
            System.out.println(jResponse.toString());
            System.out.println(filledForm.field("msisdn").value());
            System.out.println(filledForm.field("password").value());
            System.out.println("</createClient>");*/

            if ((iError == 0) || (isTestMsisdnClient(filledForm.field("msisdn").value()) >= 0)) {
                setAccessControl(filledForm);
                return redirect(controllers.routes.Wap.index());
            } else {
                return ok(error.render(HD,Messages.get("ERROR_CREATE_CLIENTE"), false));
            }

        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),false));
        }


    }


    public static Result index() {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_DEFAULT"));

        try {


            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            String sDomain = URL_FOOTBALL_MANAGER + "newsapi/" + VERSION + "/news/scroll/1/" + LANGUAGE
                    + "?timezoneName=" + getGMTParam() + getSecuretyTokenParam("&");

            System.out.println("sDomain -> index ->" + sDomain);

            Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            JsonNode jResponse = wsResponse.get(10000).asJson();
            Integer iError = jResponse.get("error").asInt();
            JsonNode jNews = jResponse.get("response").get("news");

            if (iError == 0) {
                return ok(news.render(HD, jNews, "index"));
            } else {
                return ok(error.render(HD,Messages.get("ERROR_NEWS"), true));
            }

        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }

    }

    public static Result news(Integer idNews) {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_DEFAULT"));

        try {

            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            String sDomain = URL_FOOTBALL_MANAGER + "newsapi/" + VERSION + "/news/get/"
                    + idNews + "?timezoneName=" + getGMTParam() + getSecuretyTokenParam("&");

            Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            JsonNode jResponse = wsResponse.get(10000).asJson();
            JsonNode jNews = jResponse.get("response");
            Integer iError = jResponse.get("error").asInt();

            if (iError == 0) {
                return ok(news.render(HD, jNews, "summary"));
            } else {
                return ok(error.render(HD,Messages.get("ERROR_NEWS"), true));
            }

        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }


    }

    public static Result competitions(String route) {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_NEWS"));

        try {

            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            JsonNode jCompetitions = getCompetition();
            return ok(competitions.render(HD, jCompetitions, route));
        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }

    }

    public static Result matches(Integer idCompetition, Integer page) {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_NEWS"));

        try {
            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            Date dNow = new Date();
            SimpleDateFormat sDf = new SimpleDateFormat ("yyyyMMdd");

            String sDomain = URL_FOOTBALL_MANAGER
                    + "footballapi/"
                    + VERSION
                    + "/matches/competition/date/paged/1/" + idCompetition
                    + "/" + sDf.format(dNow)
                    + "?pageSize=" + LIMIT
                    + "&page=" +  page  * LIMIT
                    + "&timezoneName=" + getGMTParam()
                    + getSecuretyTokenParam("&");

            //System.out.println(sDomain);
            Promise<WSResponse> wsResponse = WS.url(sDomain).get();

            JsonNode jResponse = wsResponse.get(10000).asJson();
            Integer iError = jResponse.get("error").asInt();
            String sDescription = jResponse.get("description").asText();
            String nameCompetition = getNameCompetition(idCompetition);
            ObjectNode jOCompetition = Json.newObject();
            ObjectNode jCompetition = Json.newObject();
            jCompetition.put("id", new Integer(idCompetition));
            jCompetition.put("name",new String(nameCompetition));
            jOCompetition.put("competition",jCompetition);

            if (iError == 0) {
                return ok(matches.render(HD, jResponse.get("response"), jOCompetition, LIMIT, page));
            } else {
                return ok(error.render(HD,Messages.get("INFO_MATCH").replace("%DATE%",new SimpleDateFormat("dd/MM/YYYY").format(new Date())),true));
            }
        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }
    }

    public static Result mtm(Integer idCompetition, Integer idMatch, Integer idEvent) {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_NEWS"));

        try {

            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            String sDomain = URL_FOOTBALL_MANAGER + "footballapi/"+ VERSION + "/matches/mam/next/1"
                    + "/" + idCompetition
                    + "/" + idMatch
                    + "/" + LANGUAGE
                    + "/" + idEvent
                    + "?timezoneName=" + getGMTParam()
                    + getSecuretyTokenParam("&");

            Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            JsonNode jResponse = wsResponse.get(10000).asJson();

            /*System.out.println("<mtm>");
            System.out.println(sDomain);
            System.out.println(jResponse.toString());
            System.out.println("</mtm>");*/



            Integer iError = jResponse.get("error").asInt();
            String sDescription = jResponse.get("description").asText();
            String nameCompetition = getNameCompetition(idCompetition);

            if (iError == 0) {
                return ok(mtm.render(HD, jResponse.get("response"), idCompetition,nameCompetition, idMatch));
            } else {
                return ok(error.render(HD,Messages.get("INFO_MTM"),true));
            }

        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }
    }


    public static Result scorers(Integer idCompetition) {

        HandsetDetection HD = new HandsetDetection();
        if (resolveAccessHTML5(HD)) return redirect(URL_HTML5);
        if (HD.getStatus() != 0) return ok(Messages.get("ERROR_NEWS"));

        try {

            if (!getAccessControl())
                return redirect(controllers.routes.Wap.getLogin());

            String sDomain = URL_FOOTBALL_MANAGER
                    + "footballapi/"
                    + VERSION + "/players/competition/scorers/1/"
                    + idCompetition + "?pageSize=10&page=0&timezoneName=" + getGMTParam()
                    + getSecuretyTokenParam("&");

            Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            JsonNode jResponse = wsResponse.get(10000).asJson();
            Integer iError = jResponse.get("error").asInt();
            String sDescription = jResponse.get("description").asText();
            String nameCompetition = getNameCompetition(idCompetition);

            if (iError == 0) {
                return ok(scorers.render(HD, jResponse.get("response").get("scorers"), nameCompetition));
            } else {
                return ok(error.render(HD,Messages.get("INFO_SCORERS"),true));
            }
        } catch (Exception e) {
            return ok(error.render(HD,Messages.get("ERROR_DEFAULT"),true));
        }
    }


    public static JsonNode getCompetition () {

        HandsetDetection HD = new HandsetDetection();
        JsonNode jCompetitions = (JsonNode) Cache.get("competitions");
        String sDomain = URL_FOOTBALL_MANAGER + "footballapi/" + VERSION + "/competitions/list/1/" + LANGUAGE
                + "?timezoneName=" + getGMTParam() + getSecuretyTokenParam("&");

        if (jCompetitions == null) {
            Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            jCompetitions = wsResponse.get(10000).asJson().get("response");
            Cache.set("competitions", jCompetitions, 60 * 60);
        }

        return jCompetitions;

    }

    public static String getNameCompetition (Integer idCompetition) {
        HandsetDetection HD = new HandsetDetection();
        JsonNode jCompetitions = getCompetition();
        Iterator<JsonNode> iJsonCompetitions = jCompetitions.get("competitions").iterator();
        String nameCompetition = "";
        while (iJsonCompetitions.hasNext()) {
            JsonNode jsonCompetition = iJsonCompetitions.next();
            if (idCompetition == jsonCompetition.get("id_competitions").asInt())
                nameCompetition = jsonCompetition.get("name").asText();
        }

        return nameCompetition;

    }

    public static Boolean setAccessControl (Form<Client> filledForm) {

        if (filledForm.field("id").value() != null) {
            session("id", filledForm.field("id").value());
            response().setCookie("id", filledForm.field("id").value(), 86400);
        }

        if (filledForm.field("msisdn").value() != null) {
            session("msisdn", filledForm.field("msisdn").value());
            response().setCookie("msisdn", filledForm.field("msisdn").value(), 86400);
        }

        if (filledForm.field("password").value() != null) {
            session("password", filledForm.field("password").value());
            response().setCookie("password", filledForm.field("password").value(), 86400);
        }

        return true;
    }

    public static Boolean getAccessControl () {
        if ((session("msisdn") != null)
                && (session("password") != null)) {
            return true;
        } else if ((request().cookie("msisdn") != null)
                && (request().cookie("password") != null)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


    public static String getTimeStampFormat() {
        return new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS").format(new Date()) + "UTC";
    }

    public static Integer isTestMsisdnClient(String sMsisdn) {
        String[] arrMsisdn  = {"40766666611", "40766666612",
                "40766666613","40766666614","40766666615",
                "40766666616","40766666617", "40766666618",
                "40766666619","40766666620"};
        return  ArrayUtils.indexOf(arrMsisdn, sMsisdn);
    }

    public static String getGMTParam() {
        return "GMT-0300";
    }


    public static String getSecuretyTokenParam(String prefix) {

        char _charAtBuildVersion = jLoading.get("build_version").asText().charAt(0);
        char _charAtServerVersion = jLoading.get("server_version").asText().charAt(0);
        String token = jLoading.get("company_name").asText();
        token += getAppender(_charAtBuildVersion);
        token += jLoading.get("build_version").asText();
        token += getAppender(_charAtServerVersion);
        token += jLoading.get("server_version").asText();

        try {
            return prefix + "upstreamChannel=" + UPSTREAM_CHANNEL + "&api_password=" + URLEncoder.encode(token, "UTF-8");
        } catch (Exception e) {
            return prefix + "upstreamChannel=" + UPSTREAM_CHANNEL + "&api_password=" + token;
        }

    }

    public static String getAppender(char _index) {

        String _return = "";

        switch (_index) {
            case '1':
                _return = "|";
                break;
            case '2': _return = "@";
                break;
            case '3': _return = "#";
                break;
            case '4': _return = "$";
                break;
            case '5': _return = "%";
                break;
            case '6': _return =  "&";
                break;
            case '7': _return =  "/";
                break;
            case '8': _return =  "(";
                break;
            case '9': _return =  ")";
                break;
            case '0': _return =  "=";
                break;
            default: _return =  "-";
        }

        return  _return;

    }




}

