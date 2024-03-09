package cs4218.impl.commands;

import cs4218.ApplicationRunner;
import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallCommandIT {
    @Test
    void evaluate_TypicalCase_Success() throws ApplicationException, ShellException {
        List<String> argsList = new ArrayList<>(List.of("echo", "echo-string"));
        CallCommand callCommand = new CallCommand(argsList, new ApplicationRunner());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{});
        callCommand.evaluate(in, out);
        assertEquals("echo-string", out.toString());
    }
}