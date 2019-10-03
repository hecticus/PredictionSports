package services.silver_servicio;

import utils.WSHandler;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class SilverServicio {
    private final String URL_SILVER = "http://offers.silversol.affise.com/postback";

    public void CrearAlta(String clickid, String pid) throws IOException {
        // http://offers.silversol.affise.com/postback?clickid={sub1}&pid={sub2}
        WSHandler.instance().MakeGet(URL_SILVER + "?clickid=" + clickid + "&pid=" + pid);
    }
}
