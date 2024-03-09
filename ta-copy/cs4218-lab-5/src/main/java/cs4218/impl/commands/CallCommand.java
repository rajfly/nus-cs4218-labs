package cs4218.impl.commands;

import cs4218.ApplicationRunner;
import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import cs4218.interfaces.Command;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CallCommand implements Command {
    List<String> argsList;
    ApplicationRunner appRunner;

    public CallCommand(List<String> argsList, ApplicationRunner appRunner) {
        this.argsList = argsList;
        this.appRunner = appRunner;
    }

    @Override
    public void evaluate(InputStream in, OutputStream out) throws ShellException, ApplicationException {
        if (this.argsList.size() == 0) {
            throw new ShellException("Invalid number of arguments");
        }

        String app = this.argsList.remove(0);
        this.appRunner.runApp(app, argsList, in, out);
    }
}
