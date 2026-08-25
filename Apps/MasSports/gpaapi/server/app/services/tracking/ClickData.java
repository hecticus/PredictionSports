package services.tracking;

/**
 * Data class to encapsulate click tracking information
 * Follows Single Responsibility Principle - only holds data
 */
public class ClickData {
    private final String clickId;
    private final String origin;
    private final String extras;

    public static final String DEFAULT_CLICK_VALUE = "NA";
    public static final String DEFAULT_EXTRAS = "NA";
    
    // Origin types
    public static final String ORIGIN_MOBILE = "MOB";
    public static final String ORIGIN_VIA = "VIA";
    public static final String ORIGIN_TRA = "TRA";
    public static final String ORIGIN_SEXY = "SEXY";
    public static final String ORIGIN_CHAT = "CHAT";

    public ClickData(String clickId, String origin, String extras) {
        this.clickId = clickId != null ? clickId : DEFAULT_CLICK_VALUE;
        this.origin = origin != null ? origin : ORIGIN_MOBILE;
        this.extras = extras != null ? extras : DEFAULT_EXTRAS;
    }

    public String getClickId() {
        return clickId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getExtras() {
        return extras;
    }

    public boolean isValid() {
        return clickId != null && !clickId.trim().isEmpty() && !DEFAULT_CLICK_VALUE.equals(clickId);
    }

    public String getCombinedValue() {
        if (DEFAULT_EXTRAS.equals(extras) || extras.isEmpty()) {
            return clickId;
        }
        return clickId + "---" + extras;
    }

    @Override
    public String toString() {
        return "ClickData{clickId='" + clickId + "', origin='" + origin + "', extras='" + extras + "'}";
    }
}
