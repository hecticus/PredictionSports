package services.dto;

public class AltaSilverPeticionDto {
    private String msisdn;
    private String numeroCorto;
    private String msg;

    public AltaSilverPeticionDto() {
    }

    public AltaSilverPeticionDto(String msisdn, String numeroCorto, String msg) {
        this.msisdn = msisdn;
        this.numeroCorto = numeroCorto;
        this.msg = msg;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getNumeroCorto() {
        return numeroCorto;
    }

    public void setNumeroCorto(String numeroCorto) {
        this.numeroCorto = numeroCorto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
