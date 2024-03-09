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

class PipeCommandIT {
    @Test
    void evaluate_TypicalCase_Success() throws ApplicationException, ShellException {
        List<String> argsList1 = new ArrayList<>(List.of("echo", "echo-string"));
        List<String> argsList2 = new ArrayList<>(List.of("rep", "echo", "hello", "-"));
        CallCommand callCommand1 = new CallCommand(argsList1, new ApplicationRunner());
        CallCommand callCommand2 = new CallCommand(argsList2, new ApplicationRunner());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{});

        PipeCommand pipeCommand = new PipeCommand(List.of(callCommand1, callCommand2));

        pipeCommand.evaluate(in, out);
        assertEquals("hello-string", out.toString());
    }
}