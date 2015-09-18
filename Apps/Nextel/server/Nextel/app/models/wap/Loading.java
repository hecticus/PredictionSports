package models.wap;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.Form;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;

import static play.data.Form.form;

/**
 * Created by alidaniel on 07/17/2015.
 */
public class Loading  extends Controller {

    public static Integer LIMIT = 5;
    public static Integer MIN_LENGTH_MSISDN = 11;
    public static Integer MAX_LENGTH_MSISDN = 11;
    public static Integer COUNTRY = 1; //Brasil
    public static Integer LANGUAGE = 405; //Portuguese
    public static String UPSTREAM_CHANNEL = "WEB";
    public static String UPSTREAM_EVENT_LOGIN = "LOGIN";
    public static Form<Client> form = form(Client.class);

    public static final String URL_FOOTBALL_MANAGER = "http://footballmanager.hecticus.com/";
    public static final String URL_FOOTBALL_MANAGER_BRAZIL = "http://brazil.footballmanager.hecticus.com/";
    public static final String URL_HTML5 = "http://timfutebol.hecticus.com/";
    public static final String VERSION = "v1";

    private JsonNode jLoading;

    public Loading() {
        ctx().changeLang("pt");
        String sDomain = URL_FOOTBALL_MANAGER_BRAZIL + "api/loading/0/0/" + VERSION + "/wap";
        F.Promise<WSResponse> wsResponse = WS.url(sDomain).get();
        JsonNode jResponse = wsResponse.get(10000).asJson();
        Integer iError = jResponse.get("error").asInt();
        String sDescription = jResponse.get("description").asText();
        jResponse = jResponse.get("response");
        //System.out.println("jResponse -> " + jResponse.toString());

        this.jLoading = jResponse;

    }




    public JsonNode getJLoading(){
        return jLoading;
    }

    public void setJLoading(JsonNode jLoading) {
        this.jLoading = jLoading;
    }






}
