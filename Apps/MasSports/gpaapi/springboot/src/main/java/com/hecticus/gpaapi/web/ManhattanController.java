package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.Alta;
import com.hecticus.gpaapi.repository.AltaRepository;
import com.hecticus.gpaapi.service.KrakenServicio;
import com.hecticus.gpaapi.service.ManhattanServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ManhattanController {

    private final KrakenServicio krakenServicio;
    private final ManhattanServicio manhattanServicio;
    private final AltaRepository altaRepository;

    public ManhattanController(KrakenServicio krakenServicio,
                               ManhattanServicio manhattanServicio,
                               AltaRepository altaRepository) {
        this.krakenServicio = krakenServicio;
        this.manhattanServicio = manhattanServicio;
        this.altaRepository = altaRepository;
    }

    @GetMapping("/manhattan/confirmurl")
    public String checkPromotion(@RequestParam(name = "sub_id", required = false) String subId,
                                 @RequestParam(name = "status", required = false) String status,
                                 @RequestParam(name = "msisdn", required = false) String msisdn,
                                 HttpServletResponse response) throws IOException {
        try {
            Alta alta = new Alta("MANHATTAN", subId, status, msisdn);
            alta = altaRepository.save(alta);

            if ("0".equals(alta.getPid())) {
                krakenServicio.crearAlta(alta.getMsisdn(), "9090", "MANWEB");
                manhattanServicio.crearAlta(alta.getClickid(), alta.getPid(), alta.getMsisdn());
            } else {
                writeJson(response, "{\"status\": 0}");
                return null;
            }
            return "okmanhattan";
        } catch (Exception e) {
            fakeAltaTest();
            writeJson(response, "{\"status\": 0}");
            return null;
        }
    }

    private void writeJson(HttpServletResponse response, String body) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(body);
    }

    private void fakeAltaTest() {
        try {
            Alta alta = new Alta("MANHATTAN", "ERROR", "ERROR", "ERROR");
            altaRepository.save(alta);
        } catch (Exception ignored) {
        }
    }
}
