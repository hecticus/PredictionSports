package models.wap;

/**
 * Created by alidaniel on 03/12/2015.
 */
public class Client {

    private Long id;
    private String msisdn;
    private String password;

    public Client() {}


    public Client(Long id, String msisdn, String password) {
        this.id = id;
        this.msisdn = msisdn;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}

}
