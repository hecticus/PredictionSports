package services.client_externo_servicio;

import com.avaje.ebean.Model;
import modeles.ClienteAppland;
import services.dto.ClienteExternoWebEntity;
import services.kraken_servicio.KrakenServicio;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.UUID;

@Singleton
public class ClienteExternoServicio {

    private Model.Finder<Long, ClienteAppland> finder = new Model.Finder<Long, ClienteAppland>(ClienteAppland.class);
    private KrakenServicio krakenServicio;
    private ClientExternalWebEntityToClienteRender mapper;

    @Inject
    public ClienteExternoServicio(KrakenServicio krakenServicio) {
        this.krakenServicio = krakenServicio;
        mapper = new ClientExternalWebEntityToClienteRender();
    }

    public ClienteAppland crearCliente(ClienteAppland clienteExterno) {
        try {
            clienteExterno.insert();
            return clienteExterno;
        } catch (Exception e) {
            System.out.println("Error insertando Cliente Externo");
            return null;
        }
    }

    public ClienteAppland actualizarCliente(ClienteAppland clienteExterno) {
        try {
            clienteExterno.update();
            return clienteExterno;
        } catch (Exception e) {
            System.out.println("Error actualizando Cliente Externo");
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderPorMsisdn(String msisdn) {
        try {
            ClienteAppland clienteAppland = finder.query().where().eq("msisdn", msisdn).findUnique();
            return clienteAppland;
        } catch (Exception e) {
            System.out.println("Error insertando Cliente Externo");
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderPorIdentificador(String identificador) {
        try {
            ClienteAppland clienteAppland = finder.query().where().eq("identifier", identificador).findUnique();
            return clienteAppland;
        } catch (Exception e) {
            System.out.println("Error insertando Cliente Externo");
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderSincronizadoConKraken(String msisdn) {
        try {
            ClienteExternoWebEntity cliente = this.krakenServicio.obtenerUsuario(msisdn);
            ClienteAppland clienteAppland = obtenerClienteRenderPorMsisdn(msisdn);

            if (cliente == null) {
                throw new Exception();
            }

            cliente.msisdn = msisdn;

            if (clienteAppland == null) {
                clienteAppland = mapper.MapTo(cliente);
                clienteAppland.identifier = generarIdentificador();
                clienteAppland = crearCliente(clienteAppland);
            } else {
                clienteAppland.status = cliente.status;
                clienteAppland.password = cliente.password;
                clienteAppland = actualizarCliente(clienteAppland);
            }

            return clienteAppland;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generarIdentificador() {
        return UUID.randomUUID().toString();
    }
}
