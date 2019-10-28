package services.silver_servicio;

import utils.WSHandler;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class ManhattanServicio {
    private final String URL_WEB = "http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify";
    //http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify?sub_id=xxxxx&status=yyyyy

    public void CrearAlta(String subId, String status, String msisdn) throws IOException {
        WSHandler.instance().MakeGet(URL_WEB + "?sub_id=" + subId + "&status=" + status + "&msisdn=" + msisdn);
    }
}
