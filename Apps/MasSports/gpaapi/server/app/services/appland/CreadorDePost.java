package services.appland;

public class CreadorDePost {

    public String CrearFirma() {
        String method = "POST";
        String secret = "tyCLoQykxPssf2vlFlSpiLToDh6h8EF2";
        String subscription = "TEST_GAMES_CLUB";
        String user = "12221874384";
        int timestamp = 1488534113;
        String payload = "{\"isEligible\": true, \"event\": \"SUBSCRIBE\", \"user\": 12221874384, " +
                "\"nextRenewal\": 1486373326, \"numberOfProfiles\": 4, \"numberOfConcurrentSessions\": 4}";
        String message = method + "\r\n" + subscription + "\r\n" + timestamp + "\r\n" + payload;

        return "";
    }

}
