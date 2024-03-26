package sg.edu.nus.comp.cs4218.exception;

public class ExitException extends AbstractApplicationException {
    private static final long serialVersionUID = 6517503252362314995L;
    private int exitStatus; // NOPMD - suppressed ImmutableField, it can have another status code value

    public ExitException(int statusCode) {
        super("exit: " + statusCode);
        exitStatus = statusCode;
    }

    public int getExitStatusCode() {
        return exitStatus;
    }

}
