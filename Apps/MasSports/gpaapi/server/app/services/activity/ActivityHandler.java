package services.activity;

/**
 * Strategy interface for handling different activity types
 * Follows Strategy Pattern and Open/Closed Principle
 */
public interface ActivityHandler {
    
    /**
     * Find and update activity with msisdn
     * @param msisdn The phone number
     * @param dateThreshold Date threshold for search
     * @param origin Optional origin filter (for PaxxionActivity)
     * @return true if activity was found and updated
     */
    boolean findAndUpdateActivity(String msisdn, String dateThreshold, String origin);
    
    /**
     * Get the click ID from the last found activity
     * @return Click ID or null
     */
    String getClickId();
    
    /**
     * Get the source/extras from the last found activity
     * @return Source or null
     */
    String getSource();
}
