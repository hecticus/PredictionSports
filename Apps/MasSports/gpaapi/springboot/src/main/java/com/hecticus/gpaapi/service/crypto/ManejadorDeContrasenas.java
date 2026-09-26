package com.hecticus.gpaapi.service.crypto;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Component
public class ManejadorDeContrasenas {

    private static final String ALGO = "AES";
    private static final byte[] KEY_VALUE =
            new byte[]{'P', 'A', 'L', 'E', 'N', 'G', 'E', 'R', 'U', 'L', 'E', 'X', '1', '9', '8', '7'};

    public String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encVal);
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue, StandardCharsets.UTF_8);
    }

    private static Key generateKey() {
        return new SecretKeySpec(KEY_VALUE, ALGO);
    }
}
