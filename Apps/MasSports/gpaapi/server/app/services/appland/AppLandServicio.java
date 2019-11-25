package services.appland;

import com.google.gson.Gson;
import services.dto.ClienteExternoWebEntity;
import services.dto.ApplandTokenDto;
import services.dto.GetStatusRespuestaDto;
import services.dto.PushStatusClientAppLand;
import services.kraken_servicio.KrakenServicio;
import services.utils.ManejadorDeContrasenas;
import utils.WSHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    private Gson gson = new Gson();

    @Inject
    public AppLandServicio(ManejadorDeContrasenas manejadorDeEncriptacion, KrakenServicio krakenServicio){
        this.manejadorDeEncriptacion = manejadorDeEncriptacion;
        this.krakenServicio = krakenServicio;
    }

    public void comunicarStatus(String metodo, String subscripcionId, PushStatusClientAppLand payload){
        long timestamp = obtenerTimeStamp();
        String parsedPayload = gson.toJson(payload);
        String message = metodo + "\r\n" + subscriptionId + "\r\n" + timestamp + "\r\n" + parsedPayload;
        ManejadorEncriptacion ec = new ManejadorEncriptacion();
        String encripted = ec.encriptar(serviceSecret, message);
        String url = "https://api.appland.se/api/subscription/events/" + subscriptionId + "?key=" + serviceKey + "&time=" + timestamp + "&signature=" + timestamp;
        try {
            WSHandler.instance().MakePostJson(url, parsedPayload);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public GetStatusRespuestaDto generarRespuestaStatus(String usuarioEncriptado) throws Exception {
        String msisdn = this.manejadorDeEncriptacion.decrypt(usuarioEncriptado);
        ClienteExternoWebEntity clienteExterno = krakenServicio.obtenerUsuario(msisdn);

        Date fechaUltimoCobro = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH).parse(clienteExterno.last_billed);
        Date manana = new Date(fechaUltimoCobro.getTime() + (1000 * 60 * 60 * 24));
        long timeStamp = manana.getTime() / 1000L;
        //long nestBill = clienteExterno.
        GetStatusRespuestaDto respuesta = new GetStatusRespuestaDto();
        respuesta.setUser(usuarioEncriptado);
        respuesta.setNumberOfConcurrentSessions(1);
        respuesta.setNumberOfProfiles(1);
        respuesta.setIsEligible(clienteExterno.status == 1);
        respuesta.setNextRenewal(timeStamp);
        return respuesta;
    }

    public String obternerRutaDeRedirect(String usuario) {
        try {
            usuario = this.manejadorDeEncriptacion.encrypt(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String token = this.crearTokenAppland(usuario);
        return  "https://api.appland.se/api/subscription/onsubscribe/" + subscriptionId + "?token=" + token;
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
        String firmarEncriptada = manejador.encriptar(serviceSecret, firma);
        String firmaBase64 = Base64.getEncoder().encodeToString(firmarEncriptada.getBytes(StandardCharsets.UTF_8));
        return firmaBase64;
    }
}
