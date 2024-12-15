package services.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptServicio {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "ferchinator-2024";

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String plaintext = "584122820598";

            // Encrypt
            String encryptedText = encrypt(plaintext);
            System.out.println("Encrypted: " + encryptedText);
            String encodedEncrypt = URLEncoder.encode(encryptedText, StandardCharsets.UTF_8.toString());
            System.out.println("Encrypted url encoded: " + encodedEncrypt);

            // Decrypt
            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
