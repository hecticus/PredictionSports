package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.gpaapi.service.DigitelServicio;
import com.hecticus.gpaapi.service.crypto.EncryptServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;

@RestController
public class CiudadJuegoNativeController {

    private final DigitelServicio digitelServicio;
    private final ObjectMapper mapper;

    public CiudadJuegoNativeController(DigitelServicio digitelServicio, ObjectMapper mapper) {
        this.digitelServicio = digitelServicio;
        this.mapper = mapper;
    }

    @GetMapping("/ciudad_juego/test")
    public RedirectView digitelRedirect() {
        return new RedirectView("https://gprs.digitel.com.ve/contenido/subscription?idSc=9424&ac=reg");
    }

    @GetMapping("/ciudad_juego")
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msisdn = "";

        String tmp = getCookie(request, "X-msisdn");
        if (tmp != null && digitelServicio.validarMsisdn(msisdn)) {
            msisdn = tmp;
        }
        String header = request.getHeader("X-msisdn");
        if (header != null && digitelServicio.validarMsisdn(msisdn)) {
            msisdn = header;
        }
        tmp = getCookie(request, "msisdn");
        if (tmp != null && digitelServicio.validarMsisdn(msisdn)) {
            msisdn = tmp;
        }
        header = request.getHeader("msisdn");
        if (header != null && digitelServicio.validarMsisdn(msisdn)) {
            msisdn = header;
        }

        String tel = request.getParameter("tel");
        if (tel != null && !tel.isEmpty()) {
            msisdn = formatFromDigitel(tel);
            String encrypt = EncryptServicio.encrypt(msisdn);
            String route = "https://dev.front.ciudadjuego.hecticus.com/dashboard?msisdn=" + msisdn + "&identifier=" + encrypt;
            Cookie cookie = new Cookie("msisdn", msisdn);
            cookie.setMaxAge(15);
            response.addCookie(cookie);
            return new RedirectView(route);
        }

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("msisdn", msisdn);
        return rootNode;
    }

    public String formatFromDigitel(String msisdn) {
        return new BigInteger(msisdn, 36).toString();
    }

    private String getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
