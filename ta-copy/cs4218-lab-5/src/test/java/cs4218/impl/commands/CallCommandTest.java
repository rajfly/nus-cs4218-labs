package cs4218.impl.commands;

import cs4218.ApplicationRunner;
import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CallCommandTest {

    ApplicationRunner appRunner = mock(ApplicationRunner.class);

    @Test
    void evaluate_ValidNumberOfArguments_EvaluatesSuccessfully() throws ApplicationException, ShellException {
        List<String> argsList = new ArrayList<>(List.of("app", "arguments"));
        CallCommand callCommand = new CallCommand(argsList, appRunner);
        callCommand.evaluate(null, null);
        verify(appRunner).runApp("app", List.of("arguments"), null, null);
    }

    @Test
    void evaluate_InvalidNumberOfArguments_ThrowsShellException() {
        List<String> argsList = new ArrayList<>();
        CallCommand callCommand = new CallCommand(argsList, appRunner);
        Exception ex = assertThrows(ShellException.class, () -> callCommand.evaluate(null, null));
        assertEquals("Invalid number of arguments", ex.getMessage());
    }
}