package services.appland;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class ManejadorEncriptacion {

    public String encriptar(String secreto, String mensaje) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secreto.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(mensaje.getBytes()));
            System.out.println(hash);
            return hash;
        }
        catch (Exception e){
            System.out.println("Error");
        }

        return "";
    }
}