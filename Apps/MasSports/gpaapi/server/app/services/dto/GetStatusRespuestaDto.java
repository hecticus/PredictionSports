package services.dto;

public class GetStatusRespuestaDto {
    private String user;

    private long nextRenewal;

    private boolean isEligible;

    private int numberOfProfiles;

    private int numberOfConcurrentSessions;

    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return this.user;
    }
    public void setNextRenewal(long nextRenewal){
        this.nextRenewal = nextRenewal;
    }
    public long getNextRenewal(){
        return this.nextRenewal;
    }
    public void setIsEligible(boolean isEligible){
        this.isEligible = isEligible;
    }
    public boolean getIsEligible(){
        return this.isEligible;
    }
    public void setNumberOfProfiles(int numberOfProfiles){
        this.numberOfProfiles = numberOfProfiles;
    }
    public int getNumberOfProfiles(){
        return this.numberOfProfiles;
    }
    public void setNumberOfConcurrentSessions(int numberOfConcurrentSessions){
        this.numberOfConcurrentSessions = numberOfConcurrentSessions;
    }
    public int getNumberOfConcurrentSessions(){
        return this.numberOfConcurrentSessions;
    }
}
