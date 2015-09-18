package models.wap;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.ws.*;
import play.libs.F.Promise;
import play.libs.Json;

/**
 * Created by alidaniel on 02/12/2015.
 */
public class HandsetDetection  extends Controller {

    public static final Integer HTML4_AJAX = 4;
    public static final Integer HTML = 3;
    public static final Integer XHTML_ADV = 2;
    public static final Integer XHTML_SIMPLE = 1;
    public static final Integer CHTML = 0;
    public static final Integer WML = -1;

    private Integer levelSupport;
    private String contentType;
    private String resolution_width;
    private String resolution_height;

    private String general_platform; // Android|iOS|Series40
    private String general_platform_version;
    private String general_type; // Mobile|Tablet

    private String general_vendor;
    private String general_model;
    private Integer status;
    private JsonNode banner;

    public HandsetDetection()  {

        try {

            ObjectNode dataJson = Json.newObject();
            dataJson.put("user-agent", request().getHeader("User-Agent"));
            dataJson.put("options", "legacy, markup, xhtml_ui, hd_specs, display");

            Promise<WSResponse> wsDevice = WS.url("http://api.handsetdetection.com/apiv3/site/detect/54356.json")
                    .setHeader("Content-Type", "application/json")
                    .setAuth("a5397171b7", "g!j76FfQZBCVQLG7")
                    .post(dataJson);

            JsonNode jDevice = wsDevice.get(10000).asJson();
            this.status = jDevice.get("status").asInt();


            if (this.status == 0) {

                JsonNode jMarkup = jDevice.get("markup");
                JsonNode jXhtmlUi = jDevice.get("xhtml_ui");
                JsonNode jHdSpecs = jDevice.get("hd_specs");
                JsonNode jDisplay = jDevice.get("display");


                /*System.out.println("---------------------------------");
                System.out.println(jDevice.toString());
                System.out.println(jHdSpecs.get("general_platform").asText());
                System.out.println(jHdSpecs.get("general_platform_version").asText());
                System.out.println(jHdSpecs.get("general_type").asText());
                System.out.println(jHdSpecs.get("general_vendor").asText());
                System.out.println(jHdSpecs.get("general_model").asText());
                System.out.println(jDisplay.get("resolution_width").asText());
                System.out.println("---------------------------------");*/

                this.levelSupport = jMarkup.get("xhtml_support_level").asInt();
                this.contentType = jXhtmlUi.get("xhtmlmp_preferred_mime_type").asText();
                this.resolution_width = jDisplay.get("resolution_width").asText();
                this.resolution_height = jDisplay.get("resolution_height").asText();
                this.general_platform = jHdSpecs.get("general_platform").asText();
                this.general_platform_version = jHdSpecs.get("general_platform_version").asText();
                this.general_type = jHdSpecs.get("general_type").asText();
                this.general_vendor = jHdSpecs.get("general_vendor").asText();
                this.general_model = jHdSpecs.get("general_model").asText();


                /*
                Corte 1 o resolución baja: 200px de ancho
                Corte 2 o resolución media: 400px de ancho
                Corte 3 o resolución alta: 800px de ancho
                */

                String _zizeBanner = "h_small.jpg";
                if (jDisplay.get("resolution_width").asInt() >= 200) {
                    _zizeBanner = "h_medium.jpg";
                }  else if (jDisplay.get("resolution_width").asInt() >= 400) {
                    _zizeBanner = "h_hight.jpg";
                }

                Promise<WSResponse> wsResponse = WS.url("http://brazil.footballmanager.hecticus.com/sportsapi/v1/localimages/" + _zizeBanner + "/"+ jDisplay.get("resolution_width").asText()).get();
                JsonNode jResponse = wsResponse.get(10000).asJson();
                Integer iError = jResponse.get("error").asInt();
                JsonNode jBanner = jResponse.get("response");
                this.banner = jBanner;

                if ((this.levelSupport == this.XHTML_SIMPLE)
                        || (this.levelSupport ==  this.XHTML_ADV)){
                    response().setContentType(this.contentType);
                } else {
                    response().setContentType("text/html");
                }

            }


        } catch (Exception e) {
            //this.status = -1;
        }

    }



    public Integer getLevelSupport(){
        return levelSupport;
    }

    public void setLevelSupport(Integer levelSupport) {
        this.levelSupport = levelSupport;
    }

    public String getContentType(){
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getResolutionWidth(){
        return resolution_width;
    }

    public void setDisplayX(String resolution_width) {
        this.resolution_width = resolution_width;
    }

    public String getResolutionHeight(){
        return resolution_height;
    }

    public void setResolutionHeight(String resolution_height) {
        this.resolution_height = resolution_height;
    }


    public String getGeneralPlatform(){
        return general_platform;
    }

    public void setGeneralPlatform(String general_platform) {
        this.general_platform = general_platform;
    }
    public String getGeneralPlatformVersion(){
        return general_platform_version;
    }

    public void setGeneralPlatformVersion(String general_platform_version) {
        this.general_platform_version = general_platform_version;
    }

    public String getGeneralType(){
        return general_type;
    }

    public void setGeneralType(String general_type) {
        this.general_type = general_type;
    }

    public String getGeneralVendor(){
        return general_vendor;
    }

    public void setGeneralVendor(String general_vendor) {
        this.general_vendor = general_vendor;
    }

    public String getGeneralModel(){
        return general_model;
    }

    public void setGeneralModel(String general_model) {
        this.general_model = general_model;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public JsonNode getBanner(){
        return banner;
    }

    public void setBanner(JsonNode banner) {
        this.banner = banner;
    }

}
