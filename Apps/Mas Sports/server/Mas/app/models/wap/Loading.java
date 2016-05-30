package models.wap;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.Form;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import models.Config;

import static play.data.Form.form;

/**
 * Created by alidaniel on 07/17/2015.
 */
public class Loading  extends Controller {

    public static Integer LIMIT = 5;
    public static Integer MIN_LENGTH_MSISDN = 11;
    public static Integer MAX_LENGTH_MSISDN = 12;
    public static Integer COUNTRY = 1; //Brasil
    public static Integer LANGUAGE = 405; //Portuguese
    public static String UPSTREAM_CHANNEL = "WEB";
    public static String UPSTREAM_EVENT_LOGIN = "LOGIN";
    public static Form<Client> form = form(Client.class);

    public static String URL_FOOTBALL_MANAGER = "http://tim.sports.hecticus.com/";
    public static String URL_FOOTBALL_MANAGER_BRAZIL = "http://tim.sports.hecticus.com/";
    public static String URL_HTML5 = "http://m.timpalpites.com/";
    public static String VERSION = "v1";
    public static String PREFIX = "55";

    private JsonNode jLoading;

    public Loading() {

        /* ------------ */
        COUNTRY = Config.getInt("country-default"); //Brasil - 4
        LANGUAGE = Config.getInt("language-default");  //Español - 300
        URL_FOOTBALL_MANAGER = Config.getString("host-url").toString(); // "http://tim.sports.hecticus.com/";
        URL_FOOTBALL_MANAGER_BRAZIL = Config.getString("host-url").toString(); //"http://tim.sports.hecticus.com/";
        URL_HTML5 = Config.getString("url-html5").toString();
         /* ---------- */
        ctx().changeLang("es");
        String sDomain = URL_FOOTBALL_MANAGER_BRAZIL + "api/loading/0/0/" + VERSION + "/wap";
        System.out.println("UrlWs -> " + sDomain.toString());
        try {

            F.Promise<WSResponse> wsResponse = WS.url(sDomain).get();
            JsonNode jResponse = wsResponse.get(10000).asJson();

        Integer iError = jResponse.get("error").asInt();
        String sDescription = jResponse.get("description").asText();
        jResponse = jResponse.get("response");
        System.out.println("jResponse -> " + jResponse.toString());

        this.jLoading = jResponse;
        }catch (Exception ex){
            System.out.println("Error -"+ex.getMessage());
        }
    }




    public JsonNode getJLoading(){
        return jLoading;
    }

    public void setJLoading(JsonNode jLoading) {
        this.jLoading = jLoading;
    }






}
