package sg.edu.nus.comp.cs4218.exception;

public class PasteException extends AbstractApplicationException {

    public PasteException(String message) {
        super("paste: " + message);
    }

    public PasteException(String message, Throwable cause) {
        super("paste: " + message, cause);
    }
}
