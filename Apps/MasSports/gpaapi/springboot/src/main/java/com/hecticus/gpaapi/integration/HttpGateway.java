package com.hecticus.gpaapi.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Reemplazo de utils.WSHandler. Mantiene la semantica sincrona/bloqueante
 * del cliente original, centralizando el transporte HTTP.
 */
@Component
public class HttpGateway {

    private static final Logger log = LoggerFactory.getLogger(HttpGateway.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public HttpGateway(ObjectMapper mapper) {
        this.mapper = mapper;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            return body == null ? "" : body.string();
        }
    }

    public JsonNode getJson(String url) throws IOException {
        String body = get(url);
        if (body == null || body.isBlank()) {
            return mapper.createObjectNode();
        }
        return mapper.readTree(body);
    }

    public JsonNode postJson(String url, JsonNode payload) throws IOException {
        return postJson(url, mapper.writeValueAsString(payload));
    }

    public JsonNode postJson(String url, String payload) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            String raw = body == null ? "" : body.string();
            if (raw.isBlank()) {
                return mapper.createObjectNode();
            }
            return mapper.readTree(raw);
        }
    }

    public String getWithTimeout(String url, long timeoutMillis) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        OkHttpClient timedClient = client.newBuilder()
                .readTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                .callTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                .build();
        try (Response response = timedClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            return body == null ? "" : body.string();
        } catch (Exception e) {
            log.warn("HTTP GET failed url={}: {}", url, e.getMessage());
            throw e;
        }
    }
}
