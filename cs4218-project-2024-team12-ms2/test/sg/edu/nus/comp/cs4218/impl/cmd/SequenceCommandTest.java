package sg.edu.nus.comp.cs4218.impl.cmd;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class SequenceCommandTest {

    private static ApplicationRunner appRunner;
    private static ArgumentResolver argResolver;
    private ByteArrayOutputStream outputStream;
    private static final String HELLO = "Hello";
    private static final String WORLD = "World";
    private static final String TEST = "Test";

    @BeforeEach
    void setUp() {
        appRunner = new ApplicationRunner();
        argResolver = new ArgumentResolver();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("echo hello; echo world; echo test")
    void evaluate_EchoSequenceCommand_ShouldReturnExpected() {
        CallCommand command1 = new CallCommand(List.of(APP_ECHO, HELLO), appRunner, argResolver);
        CallCommand command2 = new CallCommand(List.of(APP_ECHO, WORLD), appRunner, argResolver);
        CallCommand command3 = new CallCommand(List.of(APP_ECHO, TEST), appRunner, argResolver);
        SequenceCommand sequenceCommand = new SequenceCommand(List.of(command1, command2, command3));

        assertEquals(3, sequenceCommand.getCommands().size());
        assertDoesNotThrow(() -> sequenceCommand.evaluate(System.in, System.out));

        String expected = HELLO + STRING_NEWLINE + WORLD + STRING_NEWLINE + TEST + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());

    }

    @Test
    void evaluate_ExitCommand_ShouldCatchExitException() {
        CallCommand command1 = new CallCommand(List.of(APP_ECHO, WORLD), appRunner, argResolver);
        CallCommand command2 = new CallCommand(List.of(APP_EXIT), appRunner, argResolver);
        SequenceCommand sequenceCommand = new SequenceCommand(List.of(command1, command2));

        assertEquals(2, sequenceCommand.getCommands().size());

        assertThrows(ExitException.class, () -> sequenceCommand.evaluate(System.in, System.out));
    }

    @Test
    @SneakyThrows
    void evaluate_InvalidCommand_ShouldCatchShellException() {
        CallCommand command1 = new CallCommand(List.of("InvalidCommand"), appRunner, argResolver);
        CallCommand command2 = new CallCommand(List.of(APP_ECHO, WORLD), appRunner, argResolver);
        SequenceCommand sequenceCommand = new SequenceCommand(List.of(command1, command2));
        assertEquals(2, sequenceCommand.getCommands().size());

        sequenceCommand.evaluate(System.in, System.out);

        assertEquals("shell: InvalidCommand: Invalid app" + STRING_NEWLINE + WORLD + STRING_NEWLINE, outputStream.toString());
    }

}