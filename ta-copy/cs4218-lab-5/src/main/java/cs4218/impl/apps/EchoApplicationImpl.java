package cs4218.impl.apps;

import cs4218.exceptions.EchoException;
import cs4218.interfaces.apps.EchoApplication;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class EchoApplicationImpl implements EchoApplication {
    final OutputStream out;

    public EchoApplicationImpl(OutputStream out) {
        this.out = out;
    }

    @Override
    public void run(List<String> tokens) throws EchoException {
        try {
            String stringToPrint = String.join(" ", tokens);
            IOUtils.write(stringToPrint, this.out);
        } catch (IOException e) {
            throw new EchoException("Could not write to output stream", e);
        }
    }
}
