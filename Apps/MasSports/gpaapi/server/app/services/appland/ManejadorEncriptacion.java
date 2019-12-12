package services.appland;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ManejadorEncriptacion {

    public String encriptar(String secreto, String mensaje) {
        try {
            String aux  = Base64.getEncoder().encodeToString(hashValue(secreto, mensaje));
            System.out.println(mensaje);
            return  aux.replace("=", "").replace('+', '-').replace('/', '_');
//            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//
//            byte[] keyByte = secreto.getBytes(StandardCharsets.US_ASCII);
//            byte[] messageBytes = mensaje.getBytes(StandardCharsets.US_ASCII);
//            byte[] hash = secreto.getBytes(StandardCharsets.US_ASCII);
//
//
//            SecretKeySpec secret_key = new SecretKeySpec(keyByte, "HmacSHA256");
//            sha256_HMAC.init(secret_key);
//
//            hash = sha256_HMAC.doFinal(mensaje.getBytes());
//            String hashFinal = ByteArrayToString(hash);
//            System.out.println(hashFinal);
//            return hashFinal;
        } catch (Exception e) {
            System.out.println("Error");
        }

        return null;
    }

    private static String ByteArrayToString(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }


    public byte[] hashValue(String secreto, String message) {
        return toHmacSHA256(secreto, message);
//        String hashHexed = toHex(hash);
//        return hashHexed;
    }

    private String toHex(byte[] value) {
        String hexed = String.format("%040x", new BigInteger(1, value));
        return hexed;
    }

    private byte[] toHmacSHA256(String secreto, String value) {
        byte[] hash = null;
        try {
            byte[] keyByte = secreto.getBytes(StandardCharsets.US_ASCII);
            byte[] messageBytes = value.getBytes(StandardCharsets.US_ASCII);
            SecretKey secretKey = new SecretKeySpec(secreto.getBytes(StandardCharsets.US_ASCII), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hash = mac.doFinal(value.getBytes(StandardCharsets.US_ASCII));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return hash;
    }
}

/*
    public static void Main() {
        string user = "12221874384";
        int timestamp = 1488534113;
        string message = user + "\r\n" + timestamp;
        string secret = "tyCLoQykxPssf2vlFlSpiLToDh6h8EF2";
        byte[] keyByte = new ASCIIEncoding().GetBytes(secret);
        byte[] messageBytes = new ASCIIEncoding().GetBytes(message);
        byte[] hash = new HMACSHA256(keyByte).ComputeHash(messageBytes);
        String.Concat(Array.ConvertAll(hash, x = > x.ToString("X2")));
        string signature = Convert.ToBase64String(hash)
                .TrimEnd('=').Replace('+', '-').Replace('/', '_');
        Console.WriteLine(signature); // Outputs: fhUP5N2dAmoQ_ngdcl495is9xA-klRvp5SXbu_eKv70   }
    }
    */