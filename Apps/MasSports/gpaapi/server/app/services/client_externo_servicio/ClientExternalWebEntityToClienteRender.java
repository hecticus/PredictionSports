package services.client_externo_servicio;

import modeles.ClienteAppland;
import services.dto.ClienteExternoWebEntity;

public class ClientExternalWebEntityToClienteRender {

    public ClienteAppland MapTo(ClienteExternoWebEntity clienteExternoWebEntity) {
        ClienteAppland tmp = new ClienteAppland();
        tmp.msisdn = clienteExternoWebEntity.msisdn;
        tmp.password = clienteExternoWebEntity.password;
        tmp.status = clienteExternoWebEntity.status;
        return tmp;
    }
}
