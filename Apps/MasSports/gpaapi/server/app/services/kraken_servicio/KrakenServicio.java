package services.kraken_servicio;

import utils.WSHandler;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class KrakenServicio {
    public void CrearAlta(String msisdn, String numeroCorto,  String msg) throws IOException {
        WSHandler.instance().MakeGet("http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn + "&destination=" + numeroCorto + "&service_type=pacws&msg="+ msg +"&received_time=20151118170000" );
    }
}
