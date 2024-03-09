package cs4218.exceptions;

public class EchoException extends ApplicationException {
    public EchoException(String message) {
        super(message);
    }

    public EchoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public String formatException() {
        return "echo: " + this.getMessage();
    }
}
