package exceptions;

/**
 * Created by plesse on 5/11/15.
 */
public class UpstreamException extends Exception {
    private int code;

    private String request;

    public UpstreamException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public UpstreamException(int code, String msg, String request) {
        super(msg);
        this.code = code;
        this.request = request;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
