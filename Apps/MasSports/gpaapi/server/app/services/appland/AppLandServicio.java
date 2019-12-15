package services.appland;

import com.google.gson.Gson;
import modeles.ClienteAppland;
import services.client_externo_servicio.ClienteExternoServicio;
import services.dto.ClienteExternoWebEntity;
import services.dto.ApplandTokenDto;
import services.dto.GetStatusRespuestaDto;
import services.dto.PushStatusClientAppLand;
import services.kraken_servicio.KrakenServicio;
import services.utils.ManejadorDeContrasenas;
import utils.WSHandler;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

@Singleton
public class AppLandServicio {

    private String subscriptionId = "HECTI_MOVIS_U_VE";
    private String serviceKey = "appland-hecticus";
    private String serviceSecret = "Y2pQAUEj4uAXqlGWcWuC3jyN8cjJjHWj";

    private ManejadorDeContrasenas manejadorDeEncriptacion;
    private KrakenServicio krakenServicio;
    private ClienteExternoServicio clienteExternoServicio;
    private Gson gson = new Gson();

    @Inject
    public AppLandServicio(ManejadorDeContrasenas manejadorDeEncriptacion, KrakenServicio krakenServicio, ClienteExternoServicio clienteExternoServicio){
        this.manejadorDeEncriptacion = manejadorDeEncriptacion;
        this.krakenServicio = krakenServicio;
        this.clienteExternoServicio = clienteExternoServicio;
    }

    public void comunicarStatus(String metodo, String subscripcionId, PushStatusClientAppLand payload){
        long timestamp = obtenerTimeStamp();
        //payload.nextRenewal = timestamp  + 86400;
//        timestamp = 1574801566;
//        payload.nextRenewal = 1574831566;
        payload.numberOfProfiles = 4;
        payload.numberOfConcurrentSessions = 4;
        String parsedPayload = gson.toJson(payload).replace(":", ": ").replace(",", ", ");
        // String parsedPayload = "{\"isEligible\": true, \"event\": \"SUBSCRIBE\", \"user\": \"c6f4d0fb-c2bd-4b6a-8393-b638b2620a14\", \"nextRenewal\": 1574831566, \"numberOfProfiles\": 4, \"numberOfConcurrentSessions\": 4}";

        String message = metodo.toUpperCase() + "\r\n" + subscriptionId  + "\r\n" + timestamp + "\r\n" + parsedPayload;
        System.out.println(message);
        ManejadorEncriptacion ec = new ManejadorEncriptacion();
        String str = ec.encriptar(serviceSecret, message);
        String url = "https://api.appland.se/api/subscription/events/" + subscriptionId + "?key=" + serviceKey + "&time=" + timestamp + "&signature=" + str;
        try {
            WSHandler.instance().MakePostJson(url, parsedPayload);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public GetStatusRespuestaDto generarRespuestaStatus(String usuarioEncriptado) throws Exception {
        ClienteAppland cliente = this.clienteExternoServicio.obtenerClienteRenderPorIdentificador(usuarioEncriptado);
        if(cliente == null || cliente.status != 1) return null;
        ClienteExternoWebEntity clienteExterno = krakenServicio.obtenerUsuario(cliente.msisdn);

        long timeStamp = getTimeStamp(clienteExterno);
        //long nestBill = clienteExterno.
        GetStatusRespuestaDto respuesta = new GetStatusRespuestaDto();
        respuesta.setUser(usuarioEncriptado);
        respuesta.setNumberOfConcurrentSessions(1);
        respuesta.setNumberOfProfiles(1);
        respuesta.setIsEligible(clienteExterno.status == 1);
        respuesta.setNextRenewal(timeStamp);
        return respuesta;
    }

    private long getTimeStamp(ClienteExternoWebEntity clienteExterno) throws ParseException {
        Date fechaUltimoCobro = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH).parse(clienteExterno.last_billed);
        Date manana = new Date(fechaUltimoCobro.getTime() + (1000 * 60 * 60 * 24));
        return manana.getTime() / 1000L;
    }

    public String obternerRutaDeRedirect(String usuario, String rutaOpcional) {
        String currentRuta = "https://api.appland.se/api/subscription/onsubscribe/";
        String token = this.crearTokenAppland(usuario);
        return  rutaOpcional == null? currentRuta: rutaOpcional  + subscriptionId + "?token=" + token;
    }

    private String crearTokenAppland(String usuario) {
        long timestampActual = obtenerTimeStamp();
        String firmarToken = crearFirma(usuario, timestampActual);
        ApplandTokenDto token = new ApplandTokenDto();

        try {
            token.setUser(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

        token.setKey(serviceKey);
        token.setTimestamp(timestampActual);
        token.setSignature(firmarToken);

        return convertirTokenAString(token);
    }

    private String convertirTokenAString(ApplandTokenDto token){
        Gson gson = new Gson();
        String tokenParsed = gson.toJson(token);
        return Base64.getEncoder().encodeToString(tokenParsed.getBytes());
    }

    private long obtenerTimeStamp() {
        return Instant.now().getEpochSecond();
    }

    private String crearFirma(String usuario, long timestamp) {
        String firma = usuario  + "\r\n" + timestamp;
        ManejadorEncriptacion manejador = new ManejadorEncriptacion();
        String firmaBase64 = manejador.encriptar(serviceSecret, firma);
        return firmaBase64;
    }
}
