package exceptions;

/**
 * Created by plesse on 10/3/14.
 */
public class InvalidLoginException extends Exception {
    public InvalidLoginException(String msg) {
        super(msg);
    }
}
