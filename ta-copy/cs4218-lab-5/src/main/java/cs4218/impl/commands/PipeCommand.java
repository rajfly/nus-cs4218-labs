package cs4218.impl.commands;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import cs4218.interfaces.Command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class PipeCommand implements Command {
    List<CallCommand> callCommands;

    public PipeCommand(List<CallCommand> callCommands) {
        this.callCommands = callCommands;
    }

    @Override
    public void evaluate(InputStream in, OutputStream out) throws ShellException, ApplicationException {
        // This is a simplified implementation of pipe
        ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream();
        callCommands.get(0).evaluate(in, tempOutputStream);
        ByteArrayInputStream tempInputStream = new ByteArrayInputStream(tempOutputStream.toByteArray());
        callCommands.get(1).evaluate(tempInputStream, out);
    }
}
