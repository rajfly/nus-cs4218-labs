package sg.edu.nus.comp.cs4218.exception;

public class TeeException extends AbstractApplicationException {
    public TeeException(String message) {
        super("tee: " + message);
    }
    public TeeException(String message, Throwable cause) {
        super("tee: " + message, cause);
    }


}
