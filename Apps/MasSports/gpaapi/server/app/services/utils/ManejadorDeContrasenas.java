package services.utils;

import java.security.Key;
        import javax.crypto.Cipher;
        import sun.misc.BASE64Encoder;
        import sun.misc.BASE64Decoder;
        import javax.crypto.spec.SecretKeySpec;

public class ManejadorDeContrasenas {

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'P', 'A', 'L', 'E', 'N', 'G', 'E', 'R', 'U', 'L', 'E', 'X', '1', '9', '8', '7'};

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }
}