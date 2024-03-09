package cs4218.interfaces;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;

import java.io.InputStream;
import java.io.OutputStream;

public interface Command {
    void evaluate(InputStream in, OutputStream out) throws ShellException, ApplicationException;
}
