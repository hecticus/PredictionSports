package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.domain.Services;

/**
 * Replica exacta del comportamiento de Services.getServiceByName() del proyecto Play:
 * retornaba un servicio "md" fijo en lugar de consultar la base de datos.
 */
public final class LegacyServices {

    private LegacyServices() {
    }

    public static Services getServiceByName(String identifier) {
        Services obj = new Services();
        obj.setId(1L);
        obj.setName("md");
        obj.setIdentifier("1");
        obj.setSms("Tu pin es: @pin. Ingresalo en la pagina web para continuar.");
        obj.setShortCode(9090);
        obj.setProductIdentifier("COPA");
        obj.setDescripcionProducto("test");
        return obj;
    }
}
