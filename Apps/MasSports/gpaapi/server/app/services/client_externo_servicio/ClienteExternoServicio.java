package services.client_externo_servicio;

import com.avaje.ebean.Model;
import modeles.ClienteRender;
import services.dto.ClienteExternoWebEntity;
import services.kraken_servicio.KrakenServicio;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.UUID;

@Singleton
public class ClienteExternoServicio {

    private Model.Finder<Long, ClienteRender> finder = new Model.Finder<Long, ClienteRender>(ClienteRender.class);
    private KrakenServicio krakenServicio;
    private ClientExternalWebEntityToClienteRender mapper;

    @Inject
    public ClienteExternoServicio(KrakenServicio krakenServicio) {
        this.krakenServicio = krakenServicio;
        mapper = new ClientExternalWebEntityToClienteRender();
    }

    public ClienteRender crearCliente(ClienteRender clienteExterno) {
        try {
            clienteExterno.insert();
            return clienteExterno;
        } catch (Exception e) {
            System.out.println("Error insertando Cliente Externo");
            return null;
        }
    }

    public ClienteRender actualizarCliente(ClienteRender clienteExterno) {
        try {
            clienteExterno.update();
            return clienteExterno;
        } catch (Exception e) {
            System.out.println("Error actualizando Cliente Externo");
            return null;
        }
    }

    public ClienteRender obtenerClienteRenderPorMsisdn(String msisdn) {
        try {
            ClienteRender clienteRender = finder.query().where().eq("msisdn", msisdn).findUnique();
            return clienteRender;
        } catch (Exception e) {
            System.out.println("Error insertando Cliente Externo");
            return null;
        }
    }

    public ClienteRender obtenerClienteRenderSincronizadoConKraken(String msisdn) {
        try {
            ClienteExternoWebEntity cliente = this.krakenServicio.obtenerUsuario(msisdn);
            ClienteRender clienteRender = obtenerClienteRenderPorMsisdn(msisdn);

            if (clienteRender == null) {
                clienteRender = mapper.MapTo(cliente);
                clienteRender.identifier = generarIdentificador();
                clienteRender = crearCliente(clienteRender);
            } else {
                clienteRender.status = cliente.status;
                clienteRender.password = cliente.password;
                clienteRender = actualizarCliente(clienteRender);
            }

            return clienteRender;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generarIdentificador() {
        return UUID.randomUUID().toString();
    }
}
