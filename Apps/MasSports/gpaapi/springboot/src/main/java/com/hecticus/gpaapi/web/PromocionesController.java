package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.Alta;
import com.hecticus.gpaapi.repository.AltaRepository;
import com.hecticus.gpaapi.service.KrakenServicio;
import com.hecticus.gpaapi.service.SilverServicio;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromocionesController {

    private final KrakenServicio krakenServicio;
    private final SilverServicio silverServicio;
    private final AltaRepository altaRepository;

    public PromocionesController(KrakenServicio krakenServicio,
                                 SilverServicio silverServicio,
                                 AltaRepository altaRepository) {
        this.krakenServicio = krakenServicio;
        this.silverServicio = silverServicio;
        this.altaRepository = altaRepository;
    }

    @GetMapping(value = "/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkPromotion(@RequestParam(name = "clickid", required = false) String clickid,
                                 @RequestParam(name = "pid", required = false) String pid,
                                 @RequestParam(name = "msisdn", required = false) String msisdn) {
        try {
            Alta alta = new Alta("PROMO", clickid, pid, msisdn);
            altaRepository.save(alta);
            krakenServicio.crearAlta(alta.getMsisdn(), "9090", "SILVERWEB");
            silverServicio.crearAlta(alta.getClickid(), alta.getPid());
            return "{\"status\": 1}";
        } catch (Exception e) {
            return "{\"status\": 0}";
        }
    }
}
