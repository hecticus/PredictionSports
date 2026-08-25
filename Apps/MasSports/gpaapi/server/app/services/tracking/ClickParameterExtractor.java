package services.tracking;

import play.Logger;
import play.mvc.Http;

/**
 * Service to extract click tracking parameters from HTTP requests
 * Follows Single Responsibility Principle - only extracts parameters
 * Follows Open/Closed Principle - easy to extend with new parameter types
 */
public class ClickParameterExtractor {

    /**
     * Extracts click data from request with priority order:
     * 1. tr_token (TRA origin) - Highest priority for new tracking source
     * 2. token (VIA origin)
     * 3. transaction_id (SEXY origin)
     * 4. mobidea_id (CHAT origin)
     * 5. CLICKID (MOB origin) - Default/fallback
     */
    public ClickData extractFromRequest(Http.Request request) {
        try {
            // Priority 1: Check for TRA token (new source)
            String trToken = getQueryParam(request, "tr_token");
            if (isValidParam(trToken)) {
                Logger.info("Extracted TRA token: " + trToken);
                return new ClickData(trToken, ClickData.ORIGIN_TRA, ClickData.DEFAULT_EXTRAS);
            }

            // Priority 2: Check for VIA token
            String viaToken = getQueryParam(request, "token");
            if (isValidParam(viaToken)) {
                Logger.info("Extracted VIA token: " + viaToken);
                return new ClickData(viaToken, ClickData.ORIGIN_VIA, ClickData.DEFAULT_EXTRAS);
            }

            // Priority 3: Check for transaction_id (SEXY origin)
            String sexyToken = getQueryParamFromArray(request, "transaction_id");
            if (isValidParam(sexyToken)) {
                Logger.info("Extracted SEXY transaction_id: " + sexyToken);
                return new ClickData(sexyToken, ClickData.ORIGIN_SEXY, ClickData.DEFAULT_EXTRAS);
            }

            // Priority 4: Check for mobidea_id (CHAT origin)
            String chatToken = getQueryParamFromArray(request, "mobidea_id");
            if (isValidParam(chatToken)) {
                Logger.info("Extracted CHAT mobidea_id: " + chatToken);
                return new ClickData(chatToken, ClickData.ORIGIN_CHAT, ClickData.DEFAULT_EXTRAS);
            }

            // Priority 5: Check for standard CLICKID (MOB origin)
            String clickId = getQueryParamFromArray(request, "CLICKID");
            if (isValidParam(clickId)) {
                String source = getQueryParamFromArray(request, "SOURCE");
                Logger.info("Extracted MOB clickId: " + clickId + ", source: " + source);
                return new ClickData(
                    clickId, 
                    ClickData.ORIGIN_MOBILE, 
                    isValidParam(source) ? source : ClickData.DEFAULT_EXTRAS
                );
            }

            // Return default values if nothing found
            Logger.debug("No valid click parameters found, returning defaults");
            return new ClickData(
                ClickData.DEFAULT_CLICK_VALUE, 
                ClickData.ORIGIN_MOBILE, 
                ClickData.DEFAULT_EXTRAS
            );

        } catch (Exception e) {
            Logger.error("Error extracting click parameters from request", e);
            return new ClickData(
                ClickData.DEFAULT_CLICK_VALUE, 
                ClickData.ORIGIN_MOBILE, 
                ClickData.DEFAULT_EXTRAS
            );
        }
    }

    /**
     * Safely get query parameter from request (for single value params like token)
     */
    private String getQueryParam(Http.Request request, String paramName) {
        try {
            String value = request.getQueryString(paramName);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Safely get query parameter from array (for CLICKID, SOURCE params)
     */
    private String getQueryParamFromArray(Http.Request request, String paramName) {
        try {
            String[] values = request.queryString().get(paramName);
            if (values != null && values.length > 0 && values[0] != null) {
                return values[0];
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Check if parameter is valid (not null, not empty, not "NA")
     */
    private boolean isValidParam(String param) {
        return param != null 
            && !param.trim().isEmpty() 
            && !ClickData.DEFAULT_CLICK_VALUE.equals(param);
    }
}
