package sg.edu.nus.comp.cs4218.impl.cmd;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;

class CallCommandTest {

    private static final String SYNTAX_ERROR_MSG = "shell: " + ERR_SYNTAX;

    CallCommand callCommand;

    ApplicationRunner appRunner;

    ArgumentResolver argumentResolver;

    List<String> argsList;

    @BeforeEach
    void setUp() {
        appRunner = mock(ApplicationRunner.class);
        argumentResolver = mock(ArgumentResolver.class);
    }

    @Test
    void evaluate_NullArgsList_ShouldThrowException() {
        argsList = null;
        callCommand = new CallCommand(argsList, appRunner, argumentResolver);

        ShellException exception = assertThrows(ShellException.class, () -> callCommand.evaluate(System.in, System.out));

        assertEquals(SYNTAX_ERROR_MSG, exception.getMessage());
    }

    @Test
    void evaluate_EmptyArgsList_ShouldThrowException() {
        callCommand = new CallCommand(argsList, appRunner, argumentResolver);

        ShellException exception = assertThrows(ShellException.class, () -> callCommand.evaluate(System.in, System.out));

        assertEquals(SYNTAX_ERROR_MSG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void evaluate_NonEmptyArgsList_ShouldRun() {
        argsList = new ArrayList<>(List.of("echo", "test"));
        callCommand = new CallCommand(argsList, appRunner, argumentResolver);
        when(argumentResolver.parseArguments(argsList)).thenReturn(argsList);

        callCommand.evaluate(System.in, System.out);

        verify(appRunner).runApp("echo", new String[]{"test"}, System.in, System.out);
    }

}
