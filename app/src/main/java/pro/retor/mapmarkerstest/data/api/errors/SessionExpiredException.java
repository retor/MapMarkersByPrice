package pro.retor.mapmarkerstest.data.api.errors;

/**
 * Created by d.sokolov on 01.02.2017.
 */

public class SessionExpiredException extends Exception {

    public SessionExpiredException(String message) {
        super(message);
    }
}
