package com.hecticus.gpaapi.web;

import com.hecticus.gpaapi.domain.RenderLogin;
import com.hecticus.gpaapi.repository.RenderLoginRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenderLoginController {

    private final RenderLoginRepository repository;

    public RenderLoginController(RenderLoginRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login-render")
    public String getLogin(@RequestParam(name = "msisdn", required = false) String msisdn,
                           @RequestParam(name = "business", required = false) String business) {
        RenderLogin login = new RenderLogin(msisdn, business);
        login = repository.save(login);
        return String.format("RenderLogin{id=%d, fecha=%s, msisdn='%s', club='%s'}",
                login.getId(), login.getFecha(), login.getMsisdn(), login.getClub());
    }
}
