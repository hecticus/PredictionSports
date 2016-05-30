package exceptions;

/**
 * Created by plesse on 10/3/14.
 */
public class UpstreamTimeoutException extends Exception {
    public UpstreamTimeoutException(String msg) {
        super(msg);
    }
}
