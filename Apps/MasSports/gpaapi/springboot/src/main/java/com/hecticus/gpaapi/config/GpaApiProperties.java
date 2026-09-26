package com.hecticus.gpaapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gpaapi")
public class GpaApiProperties {

    private String applicationSecret = "change-me";
    private String baseUrl = "http://localhost:9000";
    private final Cors cors = new Cors();
    private final Appland appland = new Appland();
    private final Kraken kraken = new Kraken();
    private final Silver silver = new Silver();
    private final Manhattan manhattan = new Manhattan();
    private final Mail mail = new Mail();
    private final Conversion conversion = new Conversion();

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Cors getCors() {
        return cors;
    }

    public Appland getAppland() {
        return appland;
    }

    public Kraken getKraken() {
        return kraken;
    }

    public Silver getSilver() {
        return silver;
    }

    public Manhattan getManhattan() {
        return manhattan;
    }

    public Mail getMail() {
        return mail;
    }

    public Conversion getConversion() {
        return conversion;
    }

    public static class Cors {
        private String allowedOrigins = "*";

        public String getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }
    }

    public static class Appland {
        private String subscriptionId = "HECTI_MOVIS_U_VE";
        private String serviceKey = "appland-hecticus";
        private String serviceSecret = "";

        public String getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public String getServiceKey() {
            return serviceKey;
        }

        public void setServiceKey(String serviceKey) {
            this.serviceKey = serviceKey;
        }

        public String getServiceSecret() {
            return serviceSecret;
        }

        public void setServiceSecret(String serviceSecret) {
            this.serviceSecret = serviceSecret;
        }
    }

    public static class Kraken {
        private String baseUrl = "http://api.hecticus.com/client";
        private String eventsUrl = "http://02.kapp.hecticus.com/ws/receiveMO.php";

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }
    }

    public static class Silver {
        private String postbackUrl = "http://offers.silversol.affise.com/postback";

        public String getPostbackUrl() {
            return postbackUrl;
        }

        public void setPostbackUrl(String postbackUrl) {
            this.postbackUrl = postbackUrl;
        }
    }

    public static class Manhattan {
        private String notifyUrl = "http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify";

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }
    }

    public static class Mail {
        private String from = "alarma@hecticus.com";
        private String to = "soporte.daemons@hecticus.com";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    public static class Conversion {
        private String sexyPostbackUrl = "https://www.lktrack.com/adserver/delivery/cv.php";
        private String sexyPostbackKey = "";
        private String chatPostbackUrl = "https://postback.mobidea.ai/postback";
        private String chatSecurityToken = "";
        private String paxxionHandler = "11240";
        private String paxxionHash = "";
        private String maxgameHandler = "11191";
        private String maxgameHash = "";

        public String getSexyPostbackUrl() {
            return sexyPostbackUrl;
        }

        public void setSexyPostbackUrl(String sexyPostbackUrl) {
            this.sexyPostbackUrl = sexyPostbackUrl;
        }

        public String getSexyPostbackKey() {
            return sexyPostbackKey;
        }

        public void setSexyPostbackKey(String sexyPostbackKey) {
            this.sexyPostbackKey = sexyPostbackKey;
        }

        public String getChatPostbackUrl() {
            return chatPostbackUrl;
        }

        public void setChatPostbackUrl(String chatPostbackUrl) {
            this.chatPostbackUrl = chatPostbackUrl;
        }

        public String getChatSecurityToken() {
            return chatSecurityToken;
        }

        public void setChatSecurityToken(String chatSecurityToken) {
            this.chatSecurityToken = chatSecurityToken;
        }

        public String getPaxxionHandler() {
            return paxxionHandler;
        }

        public void setPaxxionHandler(String paxxionHandler) {
            this.paxxionHandler = paxxionHandler;
        }

        public String getPaxxionHash() {
            return paxxionHash;
        }

        public void setPaxxionHash(String paxxionHash) {
            this.paxxionHash = paxxionHash;
        }

        public String getMaxgameHandler() {
            return maxgameHandler;
        }

        public void setMaxgameHandler(String maxgameHandler) {
            this.maxgameHandler = maxgameHandler;
        }

        public String getMaxgameHash() {
            return maxgameHash;
        }

        public void setMaxgameHash(String maxgameHash) {
            this.maxgameHash = maxgameHash;
        }
    }
}
