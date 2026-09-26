package com.hecticus.gpaapi.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Reemplazo de utils.Response del proyecto Play.
 */
public final class ApiResponse {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ApiResponse() {
    }

    public static ObjectNode buildExtendResponse(String message) {
        ObjectNode response = MAPPER.createObjectNode();
        response.put("message", message);
        return response;
    }

    public static ObjectNode buildExtendResponse(String message, JsonNode result) {
        ObjectNode response = MAPPER.createObjectNode();
        response.put("message", message);
        response.set("result", result);
        return response;
    }

    public static ObjectNode accessDenied() {
        return buildExtendResponse("ACCESS DENIED");
    }

    public static ObjectNode accessGranted() {
        return buildExtendResponse("ACCESS GRANTED");
    }

    public static ObjectNode good() {
        return buildExtendResponse("Message Sent!");
    }

    public static ObjectNode empty() {
        return MAPPER.createObjectNode();
    }
}
