package cs4218.interfaces.apps;

import cs4218.exceptions.EchoException;

import java.util.List;

public interface EchoApplication extends Application {
    void run(List<String> tokens) throws EchoException;
}
