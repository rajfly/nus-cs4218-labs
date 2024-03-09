package cs4218.impl.commands;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

class PipeCommandTest {

    @Test
    void evaluate_TwoCommands_RunsInOrder() throws ApplicationException, ShellException {
        CallCommand commandOne = mock(CallCommand.class);
        CallCommand commandTwo = mock(CallCommand.class);
        InOrder io = inOrder(commandOne, commandTwo);

        PipeCommand pipeCommand = new PipeCommand(List.of(commandOne, commandTwo));
        pipeCommand.evaluate(null, null);

        io.verify(commandOne).evaluate(any(), any());
        io.verify(commandTwo).evaluate(any(), any());
    }

    // other tests...
}