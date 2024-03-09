package cs4218;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import cs4218.interfaces.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShellTest {
    static Command command;
    static Shell shell;

    static CommandBuilder cb;

    @BeforeEach
    void setUp() {
        command = mock(Command.class);
        cb = mock(CommandBuilder.class);
        when(cb.getCommand(any(), any())).thenReturn(command);
        shell = new Shell(null, cb);
    }

    @Test
    void evaluateCommands_OneCommand_CommandEvaluatedOnce() throws ApplicationException, ShellException {
        String inputString = "echo hello" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);

        shell.start();

        verify(cb, times(1)).getCommand(eq("echo hello"), any());
        verify(command, times(1)).evaluate(any(), any());
    }

    @Test
    void evaluateCommands_TwoCommands_CommandEvaluatedTwice() throws ApplicationException, ShellException {
        String inputString = "echo hello" + System.lineSeparator() + "echo goodbye" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);

        shell.start();

        verify(cb, times(1)).getCommand(eq("echo hello"), any());
        verify(cb, times(1)).getCommand(eq("echo goodbye"), any());
        verify(command, times(2)).evaluate(any(), any());
    }
}