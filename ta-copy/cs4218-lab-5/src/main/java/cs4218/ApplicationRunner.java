package cs4218;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import cs4218.impl.apps.EchoApplicationImpl;
import cs4218.impl.apps.RepApplicationImpl;
import cs4218.impl.parsers.RepArgsParserImpl;
import cs4218.interfaces.apps.Application;
import cs4218.interfaces.parsers.RepArgsParser;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ApplicationRunner {
    final static String ECHO_APP_NAME = "echo";
    final static String REP_APP_NAME = "rep";

    public void runApp(String appName, List<String> tokens, InputStream in, OutputStream out) throws ShellException, ApplicationException {
        Application app = createApp(appName, in, out);
        app.run(tokens);
    }

    Application createApp(String appName, InputStream in, OutputStream out) throws ShellException {
        if (REP_APP_NAME.equals(appName)) {
            RepArgsParser args = new RepArgsParserImpl();
            return new RepApplicationImpl(args, in, out, new FileUtil());
        } else if (ECHO_APP_NAME.equals(appName)) {
            return new EchoApplicationImpl(out);
        } else {
            throw new ShellException("Command not found");
        }
    }
}
