package services.dto;

public class ApplandTokenDto {
    private String user;

    private long timestamp;

    private String key;

    private String signature;

    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return this.user;
    }
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
    public long getTimestamp(){
        return this.timestamp;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getSignature(){
        return this.signature;
    }


}
