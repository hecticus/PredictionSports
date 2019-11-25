package services.client_externo_servicio;

import modeles.ClienteRender;
import services.dto.ClienteExternoWebEntity;

public class ClientExternalWebEntityToClienteRender {

    public ClienteRender MapTo(ClienteExternoWebEntity clienteExternoWebEntity) {
        ClienteRender tmp = new ClienteRender();
        tmp.msisdn = clienteExternoWebEntity.msisdn;
        tmp.password = clienteExternoWebEntity.password;
        tmp.status = clienteExternoWebEntity.status;
        return tmp;
    }
}
