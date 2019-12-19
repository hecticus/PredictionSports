package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import modeles.ClienteAppland;
import play.mvc.Http;
import services.client_externo_servicio.ClienteExternoServicio;
import services.dto.ClienteExternoWebEntity;
import play.mvc.Controller;
import play.mvc.Result;
import services.appland.AppLandServicio;
import services.dto.GetStatusRespuestaDto;
import services.dto.PushStatusClientAppLand;
import services.kraken_servicio.KrakenServicio;
import views.html.extapi;
import views.html.recover_password;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class AppLandController extends Controller {

    private KrakenServicio krakenServicio;
    private AppLandServicio applandServicio;
    private ClienteExternoServicio clienteExternoServicio;

    @Inject
    public AppLandController(KrakenServicio krakenServicio, AppLandServicio applandServicio, ClienteExternoServicio clienteExternoServicio) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
    }

    public Result Login() {
        return ok(extapi.render(false));
    }

    public Result LoginPost() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        String msisdn = aux.get("msisdn")[0];
        String contrasena = aux.get("contrasena")[0];

        ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderSincronizadoConKraken(msisdn);
        if(clienteAppland != null) {
            if(clienteAppland.password.equals(contrasena)) {
                String rutaOpcional = null;

                if(request().queryString().containsKey("callback")) {
                    rutaOpcional  = request().getQueryString("callback");
                }

                String rutaRedirect = this.applandServicio.obternerRutaDeRedirect(clienteAppland.identifier, rutaOpcional);

                PushStatusClientAppLand payload = new PushStatusClientAppLand();
                payload.event =  "SUBSCRIBE";
                payload.isEligible = true;
                payload.nextRenewal = 99999999;
                payload.numberOfConcurrentSessions = 1;
                payload.numberOfProfiles = 1;
                payload.user =  clienteAppland.identifier;

                this.applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload);
                return redirect(rutaRedirect);
            }
        }
        return ok(extapi.render(true));
    }

    public Result GetStatus(String subscripcionId, String userId) throws Exception {
        GetStatusRespuestaDto statusRespuesta = this.applandServicio.generarRespuestaStatus(userId);
        if(statusRespuesta == null) return notFound();
        Gson gson = new Gson();
        String tokenParsed = gson.toJson(statusRespuesta);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(tokenParsed);
        // return ok(tokenParsed).as("application/json");
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
        this.applandServicio.comunicarStatus("POST", "12345678", payload );
        return ok();
    }


    public Result RecoverPassword() {
        return ok(recover_password.render());
    }

    public Result checkUser() throws IOException {
        try {
            Map<String, String[]> token = request().queryString();
            ClienteExternoWebEntity clienteExterno = krakenServicio.obtenerUsuario("4142431600","10","9","6");
            return ok("{\"status\": 1}");
        } catch (Exception e) {
            return ok("{\"status\": 0}");
        }
    }

    public Result SendStatus (String msisdn, int status) {
         ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderPorMsisdn(msisdn);
         if(clienteAppland != null) {
             PushStatusClientAppLand payload = new PushStatusClientAppLand();
             payload.event =  status == 1 ? "BILLED_SUCCESS" : "SUBSCRIPTION_END";
             payload.isEligible = true;
             payload.nextRenewal = 99999999;
             payload.numberOfConcurrentSessions = 1;
             payload.numberOfProfiles = 1;
             payload.user =  clienteAppland.identifier;

             this.applandServicio.comunicarStatus("POST", clienteAppland.identifier, payload);
         }
         return ok();
    }
}


