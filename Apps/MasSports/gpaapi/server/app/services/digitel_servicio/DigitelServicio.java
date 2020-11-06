package services.digitel_servicio;

import services.digitel_servicio.subscription.Response;
import services.digitel_servicio.subscription.SubscriptionWS;
import services.digitel_servicio.subscription.SubscriptionWSImplService;
import services.digitel_servicio.subscription.Validar;

import javax.inject.Singleton;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Singleton
public class DigitelServicio {

    public boolean ValidarMsisdn(String msisdn) throws MalformedURLException {
        SubscriptionWSImplService subscriptionWSImplService = new SubscriptionWSImplService();
        SubscriptionWS subscriptionWS = subscriptionWSImplService.getSubscriptionWSImplPort();
        Response aux = subscriptionWS.validar(msisdn, "9424");
        return aux.getRespuesta().equals("SUSCRIPCION-ACTIVA");
    }
}


