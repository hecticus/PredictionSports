package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import modeles.ClienteAppland;
import modeles.Config;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.appland.AppLandServicio;
import services.client_externo_servicio.ClienteExternoServicio;
import services.dto.ClienteExternoWebEntity;
import services.dto.ClienteServicioDisableListResponseDto;
import services.dto.GetStatusRespuestaDto;
import services.dto.PushStatusClientAppLand;
import services.kraken_servicio.KrakenServicio;
import views.html.ciudadjuego.login;
import views.html.ciudadjuego.tyc;
import views.html.ciudadjuego.sms;
import views.html.ciudadjuego.recover_password;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CiudadJuegoApplandController extends Controller {

    private KrakenServicio krakenServicio;
    private AppLandServicio applandServicio;
    private ClienteExternoServicio clienteExternoServicio;
    private String subscriptionId = "HECTI_CIUDA_U_VE";

    @Inject
    public CiudadJuegoApplandController(KrakenServicio krakenServicio, AppLandServicio applandServicio, ClienteExternoServicio clienteExternoServicio) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
    }

    public Result Login() {
        if (request().queryString().containsKey("callback")) {
            response().setCookie(Http.Cookie.builder("callback", request().getQueryString("callback")).withMaxAge(15).build());
        }

        if (request().queryString().containsKey("ott")) {
            response().setCookie(Http.Cookie.builder("ott", request().getQueryString("ott")).withMaxAge(15).build());
        }

        return ok(login.render(false));
    }

    public Result LoginPost() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        String msisdn = aux.get("msisdn")[0];
        String contrasena = aux.get("contrasena")[0];

        if(msisdn.startsWith("0412") == false){
            return ok(login.render(true));
        }

        ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderSincronizadoConKraken(msisdn, contrasena, 6);
        if (clienteAppland != null) {
            if (contrasena != null && contrasena.equals(clienteAppland.password)) {
                String rutaOpcional = null;
                String extra = "";

                if (request().cookie("callback") != null) {
                    extra = request().cookie("ott") != null ? "&ott=" + request().cookie("ott").value() : "";
                    rutaOpcional = request().cookie("callback").value();
                }


                String rutaRedirect = this.applandServicio.obternerRutaDeRedirect(clienteAppland.identifier, rutaOpcional, subscriptionId);
                rutaRedirect = rutaRedirect + extra;
                PushStatusClientAppLand payload = new PushStatusClientAppLand();
                payload.event = "SUBSCRIBE";
                payload.isEligible = true;
                payload.nextRenewal = 99999999;
                payload.numberOfConcurrentSessions = 1;
                payload.numberOfProfiles = 1;
                payload.user = clienteAppland.identifier;

                this.applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
                return redirect(rutaRedirect);
            }
        }
        return ok(login.render(true));
    }

    public Result GetStatus(String subscripcionId, String userId) throws Exception {
        GetStatusRespuestaDto statusRespuesta = this.applandServicio.generarRespuestaStatus(userId);
        if (statusRespuesta == null) return notFound();
        Gson gson = new Gson();
        String tokenParsed = gson.toJson(statusRespuesta);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(tokenParsed);
        return ok(jsonNode);
    }

    public Result createPush() {
        PushStatusClientAppLand payload = new PushStatusClientAppLand();
        payload.event = "ALTA";
        payload.isEligible = true;
        payload.nextRenewal = 99999999;
        payload.numberOfConcurrentSessions = 1;
        payload.numberOfProfiles = 1;
        payload.user = "12345678";
        this.applandServicio.comunicarStatus("POST", "12345678", payload, subscriptionId);
        return ok();
    }

    public Result RecoverPassword() {
        return ok(recover_password.render());
    }

    public Result Sms() {
         if(request().queryString().containsKey("callback")) {
            response().setCookie(Http.Cookie.builder("callback",  request().getQueryString("callback") ).withMaxAge(15).build());
        }

        if(request().queryString().containsKey("ott")) {
            response().setCookie(Http.Cookie.builder("ott",  request().getQueryString("ott") ).withMaxAge(15).build());
        }

        String amount = Config.getString("current-amount");
        return ok(sms.render(amount));
    }

    public Result checkUser() throws IOException {
        try {
            Map<String, String[]> token = request().queryString();
            ClienteExternoWebEntity clienteExterno = krakenServicio.obtenerUsuario("4142431600", "10", "9", "6");
            return ok("{\"status\": 1}");
        } catch (Exception e) {
            return ok("{\"status\": 0}");
        }
    }

    public Result SendStatus(String msisdn, int status) {
        ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(msisdn);
        if (clienteAppland != null) {
            PushStatusClientAppLand payload = new PushStatusClientAppLand();
            payload.event = status == 1 ? "BILLED_SUCCESS" : "SUBSCRIPTION_END";
            payload.isEligible = true;
            payload.nextRenewal = 99999999;
            payload.numberOfConcurrentSessions = 1;
            payload.numberOfProfiles = 1;
            payload.user = clienteAppland.identifier;

            this.applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
        }
        return ok();
    }

    public Result TYC() {
        String amount = Config.getString("current-amount");
        String dater = Config.getString("date-amount");
        return ok(tyc.render(amount, dater));
    }

    public Result GetDisabledAppLandClients() throws IOException {
        List<ClienteServicioDisableListResponseDto> clientes = krakenServicio.obtenerUsuariosDeshabilitadosPorFecha();
        for (ClienteServicioDisableListResponseDto cliente : clientes) {
            ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(cliente.client.msisdn);
            if (clienteAppland != null) {
                PushStatusClientAppLand payload = new PushStatusClientAppLand();
                payload.event = "SUBSCRIPTION_END";
                payload.isEligible = true;
                payload.nextRenewal = 99999999;
                payload.numberOfConcurrentSessions = 1;
                payload.numberOfProfiles = 1;
                payload.user = clienteAppland.identifier;

                this.applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
            }
        }
        ObjectNode result = Json.newObject();
        return ok("");
    }
}

