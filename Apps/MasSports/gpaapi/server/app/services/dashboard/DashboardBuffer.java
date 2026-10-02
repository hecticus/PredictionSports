package services.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * In-memory bounded buffer with the latest tracked events for the admin dashboard.
 * Keeps full (untruncated) details so provider click ids and their transformations
 * can be inspected; capped so memory overhead stays minimal.
 * Newest entries first.
 */
public class DashboardBuffer {

    private static final DashboardBuffer INSTANCE = new DashboardBuffer();

    public static DashboardBuffer get() {
        return INSTANCE;
    }

    private static final int CAPACITY = 200;
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** One landing page load: what arrived and how it was extracted. */
    public static class ClickTrace {
        public final String time;
        public final String path;
        public final String rawQuery;
        public final String clickId;
        public final String origin;
        public final String extras;

        public ClickTrace(String path, String rawQuery, String clickId, String origin, String extras) {
            this.time = now();
            this.path = path;
            this.rawQuery = rawQuery;
            this.clickId = clickId;
            this.origin = origin;
            this.extras = extras;
        }
    }

    /** One incoming clicktosms request or one claim attempt. */
    public static class SmsTrace {
        public final String time;
        public final String kind; // REQ or CLAIM
        public final String country;
        public final String business;
        public final String msisdn;
        public final String command;
        public final String clickId; // claimed click id (CLAIM only)
        public final String detail;

        public SmsTrace(String kind, String country, String business, String msisdn, String command, String clickId, String detail) {
            this.time = now();
            this.kind = kind;
            this.country = country;
            this.business = business;
            this.msisdn = msisdn;
            this.command = command;
            this.clickId = clickId;
            this.detail = detail;
        }
    }

    /** One conversion call or result for a provider. */
    public static class ConvTrace {
        public final String time;
        public final String identifier; // CONV_TRAFFIC, CONV_MOBIPIUM, ...
        public final String msisdn;
        public final String detail; // full url, http status + body, or error

        public ConvTrace(String identifier, String msisdn, String detail) {
            this.time = now();
            this.identifier = identifier;
            this.msisdn = msisdn;
            this.detail = detail;
        }
    }

    private final LinkedBlockingDeque<ClickTrace> clicks = new LinkedBlockingDeque<>(CAPACITY);
    private final LinkedBlockingDeque<SmsTrace> smsEvents = new LinkedBlockingDeque<>(CAPACITY);
    private final LinkedBlockingDeque<ConvTrace> conversions = new LinkedBlockingDeque<>(CAPACITY);

    public DashboardBuffer() {
    }

    public void addClick(ClickTrace trace) {
        clicks.offerFirst(trace);
    }

    public void addSms(SmsTrace trace) {
        smsEvents.offerFirst(trace);
    }

    public void addConversion(ConvTrace trace) {
        conversions.offerFirst(trace);
    }

    public List<ClickTrace> lastClicks(int limit) {
        return copy(clicks, limit);
    }

    public List<SmsTrace> lastSms(int limit) {
        return copy(smsEvents, limit);
    }

    public List<ConvTrace> lastConversions(int limit) {
        return copy(conversions, limit);
    }

    private static <T> List<T> copy(LinkedBlockingDeque<T> deque, int limit) {
        List<T> result = new ArrayList<>();
        for (T item : deque) {
            if (result.size() >= limit) {
                break;
            }
            result.add(item);
        }
        return result;
    }

    private static String now() {
        return new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }
}
