package com.hecticus.gpaapi.dto;

public class GetStatusRespuestaDto {
    private String user;
    private long nextRenewal;
    private boolean isEligible;
    private int numberOfProfiles;
    private int numberOfConcurrentSessions;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getNextRenewal() {
        return nextRenewal;
    }

    public void setNextRenewal(long nextRenewal) {
        this.nextRenewal = nextRenewal;
    }

    public boolean getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(boolean isEligible) {
        this.isEligible = isEligible;
    }

    public int getNumberOfProfiles() {
        return numberOfProfiles;
    }

    public void setNumberOfProfiles(int numberOfProfiles) {
        this.numberOfProfiles = numberOfProfiles;
    }

    public int getNumberOfConcurrentSessions() {
        return numberOfConcurrentSessions;
    }

    public void setNumberOfConcurrentSessions(int numberOfConcurrentSessions) {
        this.numberOfConcurrentSessions = numberOfConcurrentSessions;
    }
}
