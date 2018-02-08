package pro.retor.mapmarkerstest.data.api.errors;

/**
 * Created by d.sokolov on 16.03.2017.
 */

public class TokenExpiredException extends Exception {
    public TokenExpiredException(String message) {
        super(message);
    }
}
