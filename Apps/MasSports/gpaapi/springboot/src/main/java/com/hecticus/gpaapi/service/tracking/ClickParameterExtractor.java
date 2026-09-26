package com.hecticus.gpaapi.service.tracking;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClickParameterExtractor {

    private static final Logger log = LoggerFactory.getLogger(ClickParameterExtractor.class);

    public ClickData extractFromRequest(HttpServletRequest request) {
        try {
            String trToken = request.getParameter("tr_token");
            if (isValidParam(trToken)) {
                log.info("Extracted TRA token: {}", trToken);
                return new ClickData(trToken, ClickData.ORIGIN_TRA, ClickData.DEFAULT_EXTRAS);
            }

            String viaToken = request.getParameter("token");
            if (isValidParam(viaToken)) {
                log.info("Extracted VIA token: {}", viaToken);
                return new ClickData(viaToken, ClickData.ORIGIN_VIA, ClickData.DEFAULT_EXTRAS);
            }

            String sexyToken = request.getParameter("transaction_id");
            if (isValidParam(sexyToken)) {
                log.info("Extracted SEXY transaction_id: {}", sexyToken);
                return new ClickData(sexyToken, ClickData.ORIGIN_SEXY, ClickData.DEFAULT_EXTRAS);
            }

            String chatToken = request.getParameter("mobidea_id");
            if (isValidParam(chatToken)) {
                log.info("Extracted CHAT mobidea_id: {}", chatToken);
                return new ClickData(chatToken, ClickData.ORIGIN_CHAT, ClickData.DEFAULT_EXTRAS);
            }

            String clickId = request.getParameter("CLICKID");
            if (!isValidParam(clickId)) {
                clickId = request.getParameter("clickid");
            }
            if (isValidParam(clickId)) {
                String source = request.getParameter("SOURCE");
                log.info("Extracted MOB clickId: {}, source: {}", clickId, source);
                return new ClickData(clickId, ClickData.ORIGIN_MOBILE,
                        isValidParam(source) ? source : ClickData.DEFAULT_EXTRAS);
            }

            String googleClickId = request.getParameter("gclid");
            if (!isValidParam(googleClickId)) {
                googleClickId = request.getParameter("gbraid");
            }
            if (!isValidParam(googleClickId)) {
                googleClickId = request.getParameter("wbraid");
            }
            if (!isValidParam(googleClickId)) {
                googleClickId = request.getParameter("dclid");
            }
            if (isValidParam(googleClickId)) {
                String source = request.getParameter("SOURCE");
                log.info("Extracted Google Ads clickId: {}, source: {}", googleClickId, source);
                return new ClickData(googleClickId, ClickData.ORIGIN_GOOGLE,
                        isValidParam(source) ? source : ClickData.DEFAULT_EXTRAS);
            }

            return new ClickData(ClickData.DEFAULT_CLICK_VALUE, ClickData.ORIGIN_MOBILE, ClickData.DEFAULT_EXTRAS);
        } catch (Exception e) {
            log.error("Error extracting click parameters from request", e);
            return new ClickData(ClickData.DEFAULT_CLICK_VALUE, ClickData.ORIGIN_MOBILE, ClickData.DEFAULT_EXTRAS);
        }
    }

    private boolean isValidParam(String param) {
        return param != null && !param.trim().isEmpty() && !ClickData.DEFAULT_CLICK_VALUE.equals(param);
    }
}
