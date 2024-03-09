package cs4218.exceptions;

public class ShellException extends Exception {
    public ShellException(String message) {
        super(message);
    }

    public String formatException() {
        return "shell: " + this.getMessage();
    }
}
