package services.appland;

public class CreadorDePost {
    
    public String CrearFirma () {
        String method = "POST";
        String secret = "tyCLoQykxPssf2vlFlSpiLToDh6h8EF2";
        String subscription = "TEST_GAMES_CLUB";
        String user = "12221874384";
        int timestamp = 1488534113;
        String payload = "{\"isEligible\": true, \"event\": \"SUBSCRIBE\", \"user\": 12221874384, " +
                "\"nextRenewal\": 1486373326, \"numberOfProfiles\": 4, \"numberOfConcurrentSessions\": 4}";
        String message = method + "\r\n" + subscription + "\r\n" + timestamp + "\r\n" + payload;

        ManejadorEncriptacion ec = new ManejadorEncriptacion();
        ec.encriptar();
        byte[] keyByte = new ASCIIEncoding().GetBytes(secret);
        byte[] messageBytes = new ASCIIEncoding().GetBytes(message);
        byte[] hash = new HMACSHA256(keyByte).ComputeHash(messageBytes);
String.Concat(Array.ConvertAll(hash, x => x.ToString("X2")));
    String signature = Convert.ToBase64String(hash)
            .TrimEnd('=').Replace('+', '-').Replace('/', '_');
Console.WriteLine(signature); // Outputs: 1HC5588e7ADIrxnTig2UVCDFHLjyv_B6fK7ggQ1Y6U4
    }
        
}
