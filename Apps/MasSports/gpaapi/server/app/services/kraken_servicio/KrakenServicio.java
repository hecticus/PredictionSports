package services.kraken_servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import services.dto.ClienteExternoWebEntity;
import play.libs.ws.WSClient;
import services.dto.ClienteServicioDisableListResponseDto;
import utils.WSHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Singleton
public class KrakenServicio {

    private final WSClient ws;
    private final String URL = "http://api.hecticus.com/client";

    @Inject
    public KrakenServicio(WSClient ws) {
        this.ws = ws;
    }

    public void CrearAlta(String msisdn, String numeroCorto,  String msg) throws IOException {
        WSHandler.instance().MakeGet("http://02.kapp.hecticus.com/ws/receiveMO.php?source=" + msisdn + "&destination=" + numeroCorto + "&service_type=pacws&msg="+ msg +"&received_time=20151118170000" );
    }

    public ClienteExternoWebEntity obtenerUsuario(String msisdn, String business, String carrier, String country) throws IOException {
        ClienteExternoWebEntity clienteExterno = new ClienteExternoWebEntity();
        JsonNode response =  WSHandler.instance().MakeGetJson(URL +  "/" + msisdn +  "/" + business +  "/" + carrier +  "/" + country);
        ObjectMapper mapper = new ObjectMapper();
        if (response.get("response").isArray()) {
            for (final JsonNode objNode : response.get("response")) {
                ClienteExternoWebEntity value = mapper.readValue(objNode.toString(), ClienteExternoWebEntity.class);
                return value;
            }
        }
        return null;
    }


    public ClienteExternoWebEntity obtenerUsuario(String msisdn, String password, int country) throws IOException {
        ClienteExternoWebEntity clienteExterno = new ClienteExternoWebEntity();
        JsonNode response =  WSHandler.instance().MakeGetJson(URL +  "-recover/" + msisdn +  "/" + country +  "/" + password);
        ObjectMapper mapper = new ObjectMapper();
        response  = response.get("response");
        if (response.has("service")) {
            ClienteExternoWebEntity aux = mapper.readValue(response.get("client").toString(), ClienteExternoWebEntity.class);
            aux.password = response.get("service").get("password").asText();
            return aux;
        }
        return null;
    }


    public ClienteExternoWebEntity obtenerUsuario(String msisdn) throws IOException {
        ClienteExternoWebEntity clienteExterno = new ClienteExternoWebEntity();
        JsonNode response =  WSHandler.instance().MakeGetJson(URL +  "/" + msisdn +  "/6");
        ObjectMapper mapper = new ObjectMapper();
        response  = response.get("response");
        if (response.has("myList")) {
            for (final JsonNode objNode : response.get("myList")) {
                ClienteExternoWebEntity value = mapper.readValue(objNode.toString(), ClienteExternoWebEntity.class);
                if(value.status == 1) {
                    return value;
                }
            }
        }
        return null;
    }

    public List<ClienteServicioDisableListResponseDto> obtenerUsuariosDeshabilitadosPorFecha() throws IOException {
        ClienteExternoWebEntity clienteExterno = new ClienteExternoWebEntity();

        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(7));
        Date dateBefore = Date.from(before);

        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(dateBefore);
        System.out.println(date);

        JsonNode response =  WSHandler.instance().MakeGetJson(URL +  "/disable-list/6/" + date);
        ObjectMapper mapper = new ObjectMapper();
        if (response.get("response")!= null && response.get("response").has("clients")) {
            Gson gson = new Gson();
            ClienteServicioDisableListResponseDto[] value = gson.fromJson(response.get("response").get("clients").toString() ,  ClienteServicioDisableListResponseDto[].class);
            return new ArrayList<>(Arrays.asList(value));
        }
        return new ArrayList<ClienteServicioDisableListResponseDto>();
    }
}
