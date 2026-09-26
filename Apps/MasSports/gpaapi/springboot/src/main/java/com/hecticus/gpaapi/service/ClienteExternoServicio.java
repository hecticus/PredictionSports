package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.domain.ClienteAppland;
import com.hecticus.gpaapi.dto.ClienteExternoWebEntity;
import com.hecticus.gpaapi.repository.ClienteApplandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteExternoServicio {

    private static final Logger log = LoggerFactory.getLogger(ClienteExternoServicio.class);

    private final ClienteApplandRepository repository;
    private final KrakenServicio krakenServicio;

    public ClienteExternoServicio(ClienteApplandRepository repository, KrakenServicio krakenServicio) {
        this.repository = repository;
        this.krakenServicio = krakenServicio;
    }

    public ClienteAppland crearCliente(ClienteAppland clienteExterno) {
        try {
            return repository.save(clienteExterno);
        } catch (Exception e) {
            log.error("Error insertando Cliente Externo", e);
            return null;
        }
    }

    public ClienteAppland actualizarCliente(ClienteAppland clienteExterno) {
        try {
            return repository.save(clienteExterno);
        } catch (Exception e) {
            log.error("Error actualizando Cliente Externo", e);
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderPorMsisdn(String msisdn) {
        try {
            return repository.findFirstByMsisdnOrderByIdAsc(msisdn).orElse(null);
        } catch (Exception e) {
            log.error("Error obteniendo Cliente Externo por msisdn", e);
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderPorIdentificador(String identificador) {
        try {
            return repository.findByIdentifier(identificador).orElse(null);
        } catch (Exception e) {
            log.error("Error obteniendo Cliente Externo por identificador", e);
            return null;
        }
    }

    public ClienteAppland obtenerClienteRenderSincronizadoConKraken(String msisdn, String contrasena, int pais) {
        try {
            ClienteExternoWebEntity cliente = krakenServicio.obtenerUsuario(msisdn, contrasena, pais);
            if (cliente == null) {
                throw new IllegalStateException("Cliente no encontrado en Kraken");
            }

            ClienteAppland clienteAppland = obtenerClienteRenderPorMsisdn(msisdn);
            cliente.msisdn = msisdn;

            if (clienteAppland == null) {
                clienteAppland = mapTo(cliente);
                clienteAppland.identifier = UUID.randomUUID().toString();
                clienteAppland = crearCliente(clienteAppland);
            } else {
                clienteAppland.status = cliente.status;
                clienteAppland.password = cliente.password;
                clienteAppland = actualizarCliente(clienteAppland);
            }
            return clienteAppland;
        } catch (Exception e) {
            log.error("Error sincronizando cliente con Kraken", e);
            return null;
        }
    }

    public ClienteAppland obtenerClienteRender(String msisdn) {
        try {
            ClienteAppland clienteAppland = obtenerClienteRenderPorMsisdn(msisdn);
            if (clienteAppland == null) {
                clienteAppland = new ClienteAppland();
                clienteAppland.status = 1;
                clienteAppland.password = msisdn;
                clienteAppland.msisdn = msisdn;
                clienteAppland.identifier = UUID.randomUUID().toString();
                return crearCliente(clienteAppland);
            }
            clienteAppland.status = 1;
            clienteAppland.password = msisdn;
            return actualizarCliente(clienteAppland);
        } catch (Exception e) {
            log.error("Error obteniendo/creando Cliente Render", e);
            return null;
        }
    }

    private ClienteAppland mapTo(ClienteExternoWebEntity source) {
        ClienteAppland tmp = new ClienteAppland();
        tmp.msisdn = source.msisdn;
        tmp.password = source.password;
        tmp.status = source.status;
        return tmp;
    }

    public Optional<ClienteAppland> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }
}
