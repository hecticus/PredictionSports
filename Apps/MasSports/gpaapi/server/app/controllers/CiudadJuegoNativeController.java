package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import services.appland.AppLandServicio;
import services.client_externo_servicio.ClienteExternoServicio;
import services.digitel_servicio.DigitelServicio;
import services.kraken_servicio.KrakenServicio;

import javax.inject.Inject;
import java.math.BigInteger;
import java.net.MalformedURLException;

public class CiudadJuegoNativeController extends Controller {

    private KrakenServicio krakenServicio;
    private AppLandServicio applandServicio;
    private ClienteExternoServicio clienteExternoServicio;
    private String subscriptionId = "HECTI_CIUDA_U_VE";
    private DigitelServicio digitelServicio;

    @Inject
    public CiudadJuegoNativeController(KrakenServicio krakenServicio,
                                       AppLandServicio applandServicio,
                                       ClienteExternoServicio clienteExternoServicio,
                                       DigitelServicio digitelServicio) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
        this.digitelServicio = digitelServicio;
    }


    public Result LoginRedirect() throws MalformedURLException {
        return redirect("http://gprs.digitel.com.ve/suscripcionesPreview.do?idSc=9424&ac=reg&s=null");
    }

    public Result Login() throws MalformedURLException {
        String msisdn = "";

        if (request().cookie("X-msisdn") != null) {
            String tmpMsisdn = request().cookie("X-msisdn").value();
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                msisdn = tmpMsisdn;
            }
        }

        if (request().headers().containsKey("X-msisdn")) {
            String tmpMsisdn = request().headers().get("X-msisdn")[0];
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                msisdn = tmpMsisdn;
            }
        }

        if (request().cookie("msisdn") != null) {
            String tmpMsisdn = request().cookie("msisdn").value();
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                msisdn = tmpMsisdn;
            }
        }

        if (request().headers().containsKey("msisdn")) {
            String tmpMsisdn = request().headers().get("msisdn")[0];
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                msisdn = tmpMsisdn;
            }
        }

        if (request().getQueryString("tel") != null && !request().getQueryString("tel").isEmpty()) {
            msisdn = formatFromDigitel(request().getQueryString("tel"));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("msisdn", msisdn);
        return ok(rootNode);
    }

    public String formatFromDigitel(String msisdn) {
        return new BigInteger(msisdn, 36).toString();
    }
}


