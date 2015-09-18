package exceptions;

/**
 * Created by christian on 12/4/14.
 */
public class UpstreamAuthenticationFailureException extends Exception {
    public UpstreamAuthenticationFailureException(String msg) {
        super(msg);
    }
}
