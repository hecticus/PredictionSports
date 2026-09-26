package com.hecticus.gpaapi.service;

import com.google.gson.Gson;
import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.domain.ClienteAppland;
import com.hecticus.gpaapi.dto.ApplandTokenDto;
import com.hecticus.gpaapi.dto.ClienteExternoWebEntity;
import com.hecticus.gpaapi.dto.ClienteServicioDisableListResponseDto;
import com.hecticus.gpaapi.dto.GetStatusRespuestaDto;
import com.hecticus.gpaapi.dto.PushStatusClientAppLand;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.service.crypto.ApplandSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class AppLandServicio {

    private static final Logger log = LoggerFactory.getLogger(AppLandServicio.class);

    private final GpaApiProperties properties;
    private final ApplandSignature signature;
    private final KrakenServicio krakenServicio;
    private final ClienteExternoServicio clienteExternoServicio;
    private final HttpGateway http;
    private final Gson gson = new Gson();

    public AppLandServicio(GpaApiProperties properties,
                           ApplandSignature signature,
                           KrakenServicio krakenServicio,
                           ClienteExternoServicio clienteExternoServicio,
                           HttpGateway http) {
        this.properties = properties;
        this.signature = signature;
        this.krakenServicio = krakenServicio;
        this.clienteExternoServicio = clienteExternoServicio;
        this.http = http;
    }

    public void comunicarStatus(String metodo, String userId, PushStatusClientAppLand payload, String subscriptionId) {
        long timestamp = obtenerTimeStamp();
        payload.numberOfProfiles = 999999;
        payload.numberOfConcurrentSessions = 999999;
        String parsedPayload = gson.toJson(payload).replace(":", ": ").replace(",", ", ");
        String message = metodo.toUpperCase() + "\r\n" + subscriptionId + "\r\n" + timestamp + "\r\n" + parsedPayload;
        String str = signature.encriptar(properties.getAppland().getServiceSecret(), message);
        String url = "https://api.appland.se/api/subscription/events/" + subscriptionId
                + "?key=" + properties.getAppland().getServiceKey()
                + "&time=" + timestamp
                + "&signature=" + str;
        try {
            http.postJson(url, parsedPayload);
        } catch (Exception e) {
            log.error("Error comunicando status a AppLand", e);
        }
    }

    public void makeUnsubscribeCall(String subscriptionId) {
        try {
            List<ClienteServicioDisableListResponseDto> clientes =
                    krakenServicio.obtenerUsuariosDeshabilitadosPorFecha();
            for (ClienteServicioDisableListResponseDto cliente : clientes) {
                ClienteAppland clienteAppland =
                        clienteExternoServicio.obtenerClienteRenderPorMsisdn(cliente.client.msisdn);
                if (clienteAppland != null) {
                    PushStatusClientAppLand payload = new PushStatusClientAppLand();
                    payload.event = "SUBSCRIPTION_END";
                    payload.isEligible = true;
                    payload.nextRenewal = 99999999;
                    payload.numberOfConcurrentSessions = 1;
                    payload.numberOfProfiles = 1;
                    payload.user = clienteAppland.identifier;
                    this.comunicarStatus("POST", clienteAppland.identifier, payload, subscriptionId);
                }
            }
        } catch (Exception e) {
            log.error("Error en makeUnsubscribeCall", e);
        }
    }

    public GetStatusRespuestaDto generarRespuestaStatus(String usuarioEncriptado) throws Exception {
        ClienteAppland cliente = clienteExternoServicio.obtenerClienteRenderPorIdentificador(usuarioEncriptado);
        if (cliente == null) {
            return null;
        }
        ClienteExternoWebEntity clienteExterno = krakenServicio.obtenerUsuario(cliente.msisdn);
        if (clienteExterno == null || clienteExterno.status != 1) {
            return null;
        }
        long timeStamp = getTimeStamp(clienteExterno);
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
        Date manana = new Date(fechaUltimoCobro.getTime() + (1000L * 60 * 60 * 24));
        return manana.getTime() / 1000L;
    }

    public String obternerRutaDeRedirect(String usuario, String rutaOpcional, String subscriptionId) {
        String currentRuta = "https://api.appland.se/api/subscription/onsubscribe/";
        String token = this.crearTokenAppland(usuario);
        return (rutaOpcional == null ? currentRuta : rutaOpcional) + subscriptionId + "?token=" + token;
    }

    private String crearTokenAppland(String usuario) {
        long timestampActual = obtenerTimeStamp();
        String firmarToken = crearFirma(usuario, timestampActual);
        ApplandTokenDto token = new ApplandTokenDto();
        token.setUser(usuario);
        token.setKey(properties.getAppland().getServiceKey());
        token.setTimestamp(timestampActual);
        token.setSignature(firmarToken);
        return convertirTokenAString(token);
    }

    private String convertirTokenAString(ApplandTokenDto token) {
        String tokenParsed = gson.toJson(token);
        return Base64.getEncoder().encodeToString(tokenParsed.getBytes());
    }

    private long obtenerTimeStamp() {
        return Instant.now().getEpochSecond();
    }

    private String crearFirma(String usuario, long timestamp) {
        String firma = usuario + "\r\n" + timestamp;
        return signature.encriptar(properties.getAppland().getServiceSecret(), firma);
    }
}
