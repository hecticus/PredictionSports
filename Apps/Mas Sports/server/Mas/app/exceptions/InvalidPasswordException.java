package exceptions;

/**
 * Created by plesse on 10/3/14.
 */
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
