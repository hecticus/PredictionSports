package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import modeles.ClienteAppland;
import modeles.Config;
import modeles.CiudadJuegoActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.appland.AppLandServicio;
import services.client_externo_servicio.ClienteExternoServicio;
import services.digitel_servicio.DigitelServicio;
import services.digitel_servicio.subscription.Response;
import services.digitel_servicio.subscription.SubscriptionWS;
import services.digitel_servicio.subscription.SubscriptionWSImplService;
import services.dto.ClienteExternoWebEntity;
import services.dto.ClienteServicioDisableListResponseDto;
import services.dto.GetStatusRespuestaDto;
import services.dto.PushStatusClientAppLand;
import services.encrypt.EncryptServicio;
import services.kraken_servicio.KrakenServicio;
import views.html.ciudadjuego.login;
import views.html.ciudadjuego.recover_password;
import views.html.ciudadjuego.sms;
import views.html.ciudadjuego.tyc;
import views.html.ciudadjuego.landing_new;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CiudadJuegoApplandController extends Controller {

    // Array of possible click ID parameter names (ordered by priority)
    private static final String[] CLICK_ID_PARAMS = {
        "click_id",    // New format - highest priority
        "clickid",     // Legacy format
        "clickId",     // CamelCase variant
    };
    
    // Traffic company constants
    private static final String TRAFFIC_URL = "https://postback.level23.nl/";
    private static final String TRAFFIC_HANDLER = "11191";
    private static final String TRAFFIC_HASH = "3c71abda6be99653251370ff838fa4ab";

    private KrakenServicio krakenServicio;
    private AppLandServicio applandServicio;
    private ClienteExternoServicio clienteExternoServicio;
    private String subscriptionId = "HECTI_CIUDA_U_VE";
    private DigitelServicio digitelServicio;
    private OkHttpClient client;

    @Inject
    public CiudadJuegoApplandController(KrakenServicio krakenServicio, AppLandServicio applandServicio, ClienteExternoServicio clienteExternoServicio, DigitelServicio digitelServicio) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.clienteExternoServicio = clienteExternoServicio;
        this.digitelServicio = digitelServicio;
        this.client = new OkHttpClient();
    }

    /**
     * Helper method to get click ID value from request parameters
     * Tries different parameter name variations in order of priority
     * @return click ID value or "NA" if not found
     */
    private String getClickIdFromRequest() {
        for (String paramName : CLICK_ID_PARAMS) {
            if (request().queryString().get(paramName) != null && 
                request().queryString().get(paramName).length > 0) {
                return request().queryString().get(paramName)[0];
            }
        }
        return "NA";
    }

    @Nullable
    private static Result goToCiudadjuego(String msisdn) {
        try {
            String route = "https://www.ciudadjuego.com/dashboard?msisdn=" + msisdn + "&identifier=";

            String encrypt = EncryptServicio.encrypt(msisdn);
            String encodedEncrypt = URLEncoder.encode(encrypt, StandardCharsets.UTF_8.toString());
            route = route + encodedEncrypt;
            Http.Cookie cookie = Http.Cookie.builder("msisdn", msisdn).withMaxAge(15).build();
            response().setCookie(cookie);
            return redirect(route);
        } catch (Exception e) {
            return null;
        }
    }

    public Result LoginTest(String msisdn) throws MalformedURLException {
        SubscriptionWSImplService subscriptionWSImplService = new SubscriptionWSImplService();
        SubscriptionWS subscriptionWS = subscriptionWSImplService.getSubscriptionWSImplPort();
        Response aux = subscriptionWS.validar(msisdn, "9424");
        return ok(aux.toString());
    }

    public Result LoginRedirect() throws MalformedURLException {
        return redirect("https://gprs.digitel.com.ve/contenido/subscription?idSc=9424&ac=reg");
    }

    public Result Login() throws MalformedURLException {

        //TODO chequear si tiene cookie luego si tiene msisdn en casa de dos negativos mandar a digitel
        String msisdn = "";

        if (request().cookie("X-msisdn") != null) {
            msisdn = request().cookie("X-msisdn").value();
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                Result rutaRedirect = getResult(msisdn);
                if (rutaRedirect != null) return rutaRedirect;
            }
        }

        if (request().headers().containsKey("X-msisdn")) {
            msisdn = request().headers().get("X-msisdn")[0];
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                Result rutaRedirect = getResult(msisdn);
                if (rutaRedirect != null) return rutaRedirect;
            }
        }

        if (request().cookie("msisdn") != null) {
            msisdn = request().cookie("msisdn").value();
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                Result rutaRedirect = getResult(msisdn);
                if (rutaRedirect != null) return rutaRedirect;
            }
        }

        if (request().headers().containsKey("msisdn")) {
            msisdn = request().headers().get("msisdn")[0];
            if (digitelServicio.ValidarMsisdn(msisdn)) {
                Result rutaRedirect = getResult(msisdn);
                if (rutaRedirect != null) return rutaRedirect;
            }
        }

        if (request().getQueryString("tel") != null && !request().getQueryString("tel").isEmpty()) {
            msisdn = request().getQueryString("tel");
            return RedirectFromDigitel("", "", msisdn);
        }


        return redirect("https://gprs.digitel.com.ve/contenido/subscription?idSc=9424&ac=reg");
    }

    public Result RedirectFromDigitel(String id, String red, String msisdn) {
        msisdn = new BigInteger(msisdn, 36).toString();  //Long.valueOf(msisdn, 36).toString();// Integer.toString(msisdn, 36);
        Result rutaRedirect = getResult(msisdn);
        if (rutaRedirect != null) return rutaRedirect;
        return ok();
    }

    @Nullable
    private Result getResult(String msisdn) {
        return goToCiudadjuego(msisdn);
    }

    public Result LoginPost() throws IOException {
        Map<String, String[]> aux = request().body().asFormUrlEncoded();
        String msisdn = aux.get("msisdn")[0];
        String contrasena = aux.get("contrasena")[0];


        ClienteAppland clienteAppland = clienteExternoServicio.obtenerClienteRenderSincronizadoConKraken(msisdn, contrasena, 6);
        if (clienteAppland != null) {
            if (contrasena != null && contrasena.equals(clienteAppland.password)) {
                return goToCiudadjuego(msisdn);
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
        if (request().queryString().containsKey("callback")) {
            response().setCookie(Http.Cookie.builder("callback", request().getQueryString("callback")).withMaxAge(15).build());
        }

        if (request().queryString().containsKey("ott")) {
            response().setCookie(Http.Cookie.builder("ott", request().getQueryString("ott")).withMaxAge(15).build());
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
        String amount = Config.getString("appland-current-amount");
        String dater = Config.getString("appland-date-amount");
        return ok(tyc.render(amount, dater));
    }

    public Result GetDisabledAppLandClients() throws IOException {
        List<ClienteServicioDisableListResponseDto> clientes = krakenServicio.obtenerUsuariosDeshabilitadosPorFecha();
        List<ClienteAppland> clientesapp = new ArrayList<>();
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
                clientesapp.add(clienteAppland);
            }
        }
        ObjectNode result = Json.newObject();

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = mapper.valueToTree(clientesapp);


        result.set("Clientes", array);
        return ok(result);
    }

    public Result Landing() {
        // Get clickId from query parameters using helper method
        String clickValue = getClickIdFromRequest();
        
        if (!clickValue.equals("NA")) {
            try {
                // Save clickId when landing page is accessed (used = false)
                addClickId(clickValue, "");
            } catch (Exception e) {
                // Handle exception silently
            }
        }

        return ok(landing_new.render(false, clickValue));
    }

    public Result mark_ciudadjuego() throws IOException {
        String clickValue = getClickIdFromRequest();

        if (!clickValue.equals("NA")) {
            try {
                updateClickId(clickValue);
                sendTrafficPostback(clickValue);
                System.out.println("CiudadJuego Click ID marked as used: " + clickValue);
            } catch (Exception e) {
                System.err.println("Error marking click ID: " + e.getMessage());
            }
        }
        return ok();
    }

    private void updateClickId(String clickId) {
        CiudadJuegoActivity activity = CiudadJuegoActivity.finder.where().eq("click_id", clickId).findUnique();
        if (activity != null) {
            activity.setUsed(true);
            activity.update();
        }
    }

    private void sendTrafficPostback(String clickId) {
        String url = String.format("%s?currency=USD&handler=%s&hash=%s&tracker=%s", 
                                 TRAFFIC_URL, TRAFFIC_HANDLER, TRAFFIC_HASH, clickId);

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.err.println("Traffic postback failed for clickId " + clickId + ": " + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    System.out.println("Traffic postback sent successfully for clickId: " + clickId);
                } finally {
                    response.close();
                }
            }
        });
    }

    private void addClickId(String clickId, String ip) {
        CiudadJuegoActivity existingActivity = CiudadJuegoActivity.finder.where().eq("click_id", clickId).findUnique();
        if (existingActivity == null) {
            CiudadJuegoActivity activity = new CiudadJuegoActivity(clickId);
            activity.setIp(ip);
            activity.setOrigin("CJ");
            activity.setUsed(false); // Initially set as not used
            activity.save();
        }
    }
}


