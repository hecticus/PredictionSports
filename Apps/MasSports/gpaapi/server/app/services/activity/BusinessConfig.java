package services.activity;

/**
 * Configuration for business-specific handling
 * Follows Value Object pattern
 */
public class BusinessConfig {
    
    private final String country;
    private final String business;
    private final ActivityType activityType;
    private final ConversionType conversionType;
    private final String trafficHandler;
    private final String trafficHash;

    public enum ActivityType {
        BLIVE,
        PAXXION,
        LEARNLIVE,
        MAXGAME
    }

    public enum ConversionType {
        MOBIPIUM,
        TRAFFIC_COMPANY,
        VIA,
        NONE
    }

    public BusinessConfig(String country, String business, ActivityType activityType, ConversionType conversionType) {
        this(country, business, activityType, conversionType, null, null);
    }

    public BusinessConfig(String country, String business, ActivityType activityType, 
                         ConversionType conversionType, String trafficHandler, String trafficHash) {
        this.country = country;
        this.business = business;
        this.activityType = activityType;
        this.conversionType = conversionType;
        this.trafficHandler = trafficHandler;
        this.trafficHash = trafficHash;
    }

    public String getCountry() {
        return country;
    }

    public String getBusiness() {
        return business;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public ConversionType getConversionType() {
        return conversionType;
    }

    public String getTrafficHandler() {
        return trafficHandler;
    }

    public String getTrafficHash() {
        return trafficHash;
    }

    public boolean matches(String country, String business) {
        return this.country.equals(country) && this.business.equals(business);
    }

    @Override
    public String toString() {
        return "BusinessConfig{" +
                "country='" + country + '\'' +
                ", business='" + business + '\'' +
                ", activityType=" + activityType +
                ", conversionType=" + conversionType +
                '}';
    }
}
