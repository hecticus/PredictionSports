package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import com.hecticus.gpaapi.domain.LogEntry;
import com.hecticus.gpaapi.integration.HttpGateway;
import com.hecticus.gpaapi.repository.LogEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversionService {

    private static final Logger log = LoggerFactory.getLogger(ConversionService.class);
    private static final int MAX_DETAIL_LENGTH = 400;
    private static final long SEXY_REQUEST_TIMEOUT_MS = 2 * 60 * 1000;

    private final HttpGateway http;
    private final LogEntryRepository logRepository;
    private final GpaApiProperties properties;

    public ConversionService(HttpGateway http, LogEntryRepository logRepository, GpaApiProperties properties) {
        this.http = http;
        this.logRepository = logRepository;
        this.properties = properties;
    }

    public void sendToMobipium(String msisdn, String clickId, String source) {
        if (clickId == null || clickId.isEmpty()) {
            log.warn("Mobipium: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_MOBIPIUM", "skipped: empty clickId");
            return;
        }
        String url = String.format(
                "https://smobipiumlink.com/conversion/index.php?jp=%s&source=%s",
                clickId, source != null ? source : "");
        log.info("Mobipium conversion: {}", url);
        saveConversionLog(msisdn, "CONV_MOBIPIUM", "call url=" + url);
        try {
            String body = http.get(url);
            log.debug("Mobipium response: {}", body);
            saveConversionLog(msisdn, "CONV_MOBIPIUM", "http=200");
        } catch (Exception e) {
            log.error("Mobipium error: {}", e.getMessage());
            saveConversionLog(msisdn, "CONV_MOBIPIUM", "error=" + e.getMessage());
        }
    }

    public void sendToTrafficCompany(String msisdn, String handler, String hash, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            log.warn("TrafficCompany: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_TRAFFIC", "skipped: empty clickId");
            return;
        }
        String url = String.format(
                "http://postback.level23.nl/?currency=USD&handler=%s&hash=%s&tracker=%s",
                handler, hash, clickId);
        log.info("TrafficCompany conversion: {}", url);
        saveConversionLog(msisdn, "CONV_TRAFFIC", "call url=" + url);
        try {
            http.get(url);
            saveConversionLog(msisdn, "CONV_TRAFFIC", "http=200");
        } catch (Exception e) {
            log.error("TrafficCompany error: {}", e.getMessage());
            saveConversionLog(msisdn, "CONV_TRAFFIC", "error=" + e.getMessage());
        }
    }

    public void sendToSexy(String msisdn, String transactionId) {
        if (transactionId == null || transactionId.isEmpty()) {
            log.warn("SEXY: Invalid transaction_id, skipping");
            saveConversionLog(msisdn, "CONV_SEXY", "skipped: empty transaction_id");
            return;
        }
        String url = String.format("%s?key=%s&transaction_id=%s",
                properties.getConversion().getSexyPostbackUrl(),
                properties.getConversion().getSexyPostbackKey(), transactionId);
        log.info("SEXY conversion: {}", url);
        saveConversionLog(msisdn, "CONV_SEXY", "call url=" + url);
        try {
            http.getWithTimeout(url, SEXY_REQUEST_TIMEOUT_MS);
            saveConversionLog(msisdn, "CONV_SEXY", "http=200");
        } catch (Exception e) {
            log.error("SEXY error: {}", e.getMessage());
            saveConversionLog(msisdn, "CONV_SEXY", "error=" + e.getMessage());
        }
    }

    public void sendToChat(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            log.warn("CHAT: Invalid click_id, skipping");
            saveConversionLog(msisdn, "CONV_CHAT", "skipped: empty click_id");
            return;
        }
        String url = String.format("%s?click_id=%s&security_token=%s",
                properties.getConversion().getChatPostbackUrl(), clickId,
                properties.getConversion().getChatSecurityToken());
        log.info("CHAT conversion: {}", url);
        saveConversionLog(msisdn, "CONV_CHAT", "call url=" + url);
        try {
            http.get(url);
            saveConversionLog(msisdn, "CONV_CHAT", "http=200");
        } catch (Exception e) {
            log.error("CHAT error: {}", e.getMessage());
            saveConversionLog(msisdn, "CONV_CHAT", "error=" + e.getMessage());
        }
    }

    public void sendToVia(String msisdn, String clickId) {
        if (clickId == null || clickId.isEmpty()) {
            log.warn("VIA: Invalid clickId, skipping");
            saveConversionLog(msisdn, "CONV_VIA", "skipped: empty clickId");
            return;
        }
        String url = String.format("http://api.doblevialatam.com:9090/cget.php?token=%s", clickId);
        log.info("VIA conversion: {}", url);
        saveConversionLog(msisdn, "CONV_VIA", "call url=" + url);
        try {
            http.get(url);
            saveConversionLog(msisdn, "CONV_VIA", "http=200");
        } catch (Exception e) {
            log.error("VIA error: {}", e.getMessage());
            saveConversionLog(msisdn, "CONV_VIA", "error=" + e.getMessage());
        }
    }

    private void saveConversionLog(String msisdn, String identifier, String detail) {
        try {
            LogEntry entry = new LogEntry();
            entry.setMsisdn(msisdn != null ? msisdn : "");
            entry.setIdentifier(identifier);
            entry.setExtra(truncate(detail, MAX_DETAIL_LENGTH));
            entry.setLastUpdate(LocalDateTime.now());
            logRepository.save(entry);
        } catch (Exception e) {
            log.error("Error storing conversion log {}, msisdn={}", identifier, msisdn, e);
        }
    }

    private static String truncate(String value, int maxLength) {
        if (value == null) {
            return "";
        }
        return value.length() <= maxLength ? value : value.substring(0, maxLength);
    }
}
