package com.hecticus.gpaapi;

import org.junit.jupiter.api.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.linkbuilder.ILinkBuilder;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TemplateRenderTest {

    private static final List<String> TEMPLATES = List.of(
            "appland_sms",
            "blive_index",
            "ciudadjuego/landing_new",
            "ciudadjuego/login",
            "ciudadjuego/recover_password",
            "ciudadjuego/sms",
            "ciudadjuego/tyc",
            "extapi",
            "maxgame_2026",
            "klike_index",
            "learnlive_index",
            "learnlive_rd_index",
            "maxgame_index",
            "maxgame_mobi_index",
            "mg",
            "okmanhattan",
            "portalnvav/index",
            "recover_password",
            "tyc",
            "tycappland",
            "wepa",
            "wepaconfirm",
            "wepaerror",
            "wepaget",
            "wapconfirm"
    );

    private SpringTemplateEngine engine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.setLinkBuilder(new ILinkBuilder() {
            @Override
            public String getName() {
                return "test";
            }

            @Override
            public Integer getOrder() {
                return 0;
            }

            @Override
            public String buildLink(IExpressionContext context, String base, Map<String, Object> parameters) {
                if (parameters == null || parameters.isEmpty()) {
                    return base;
                }
                StringBuilder sb = new StringBuilder(base).append('?');
                parameters.forEach((k, v) -> sb.append(k).append('=').append(v).append('&'));
                return sb.toString();
            }
        });
        return engine;
    }

    @Test
    void allTemplatesRender() {
        SpringTemplateEngine engine = engine();
        for (String template : TEMPLATES) {
            Context ctx = new Context();
            ctx.setVariable("clickValue", "NA");
            ctx.setVariable("extras", "NA");
            ctx.setVariable("origin", "MOB");
            ctx.setVariable("error", false);
            ctx.setVariable("amount", "0.50");
            ctx.setVariable("dater", "2026/01/01");
            ctx.setVariable("ttype", "MOBUSI");
            ctx.setVariable("restore", "ttype=test");
            ctx.setVariable("validPin", true);
            ctx.setVariable("msisdn", "584120000000");
            ctx.setVariable("token", "tok");
            ctx.setVariable("used", false);
            ctx.setVariable("outie", true);
            ctx.setVariable("goodie", true);

            String html = engine.process(template, ctx);
            assertNotNull(html, "null output for " + template);
            assertFalse(html.isBlank(), "blank output for " + template);
        }
    }
}
