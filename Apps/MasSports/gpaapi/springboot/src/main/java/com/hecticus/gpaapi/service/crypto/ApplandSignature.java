package com.hecticus.gpaapi.service.crypto;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class ApplandSignature {

    public String encriptar(String secreto, String mensaje) {
        try {
            String aux = Base64.getEncoder().encodeToString(hashValue(secreto, mensaje));
            return aux.replace("=", "").replace('+', '-').replace('/', '_');
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] hashValue(String secreto, String message) {
        return toHmacSHA256(secreto, message);
    }

    private byte[] toHmacSHA256(String secreto, String value) {
        byte[] hash = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secreto.getBytes(StandardCharsets.US_ASCII), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hash = mac.doFinal(value.getBytes(StandardCharsets.US_ASCII));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }
}
