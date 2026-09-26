package com.hecticus.gpaapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Integracion Digitel. El SOAP (SubscriptionWS) ya no se utiliza; se mantiene
 * un cliente local para no romper las rutas que antes dependian de la validacion.
 */
@Service
public class DigitelServicio {

    private static final Logger log = LoggerFactory.getLogger(DigitelServicio.class);

    public boolean validarMsisdn(String msisdn) {
        if (msisdn == null || msisdn.isBlank()) {
            return false;
        }
        return msisdn.replaceAll("\\D", "").length() >= 10;
    }

    public String validar(String msisdn, String idServicio) {
        boolean activo = validarMsisdn(msisdn);
        log.info("Digitel validar msisdn={}, servicio={}, activo={} (sin SOAP)", msisdn, idServicio, activo);
        return "{\"respuesta\":\"" + (activo ? "SUSCRIPCION-ACTIVA" : "NO-ACTIVA") + "\"}";
    }
}
