package pro.retor.mapmarkerstest.data.api.errors;

/**
 * Created by retor on 15.03.2016.
 */
public class ServerError extends Exception {
    private int statusCode;

    public ServerError() {
        super();
    }

    public ServerError(String message){
        super(message);
    }

    public ServerError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
