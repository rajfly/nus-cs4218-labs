package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

import static org.junit.jupiter.api.Assertions.*;

class CommandBuilderTest {
    private ApplicationRunner applicationRunner;

    @BeforeEach
    void setUp() {
        applicationRunner = new ApplicationRunner();
    }

    @Test
    void parseCommand_nullCommand_ThrowShellException() {
        assertThrows(ShellException.class,() ->  CommandBuilder.parseCommand(null,applicationRunner));
    }

    @Test
    void parseCommand_emptyCommand_ThrowShellException() {
        assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("",applicationRunner));
    }

    @Test
    void parseCommand_emptyCommandWithSemicolon_ThrowShellException() {
        assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("; cat",applicationRunner));
    }

    @Test
    void parseCommand_emptyCommandWithPipe_ThrowShellException() {
        assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("| cat",applicationRunner));
    }

    @Test
    void parseCommand_emptyCommandWithInputRedirection_ThrowNothing() {
        assertDoesNotThrow(() -> CommandBuilder.parseCommand("< cat",applicationRunner));
    }

    @Test
    void parseCommand_emptyCommandWithOutputRedirection_ThrowNothing() {
        assertDoesNotThrow(() -> CommandBuilder.parseCommand("> cat",applicationRunner));
    }

    @Test
    void parseCommand_twoCommandsWithSemicolon_ThrowNothing() {
        assertDoesNotThrow(() ->  CommandBuilder.parseCommand("ls *; echo 'Hello world'",applicationRunner));
    }

    @Test
    void parseCommand_PipeCommand_ThrowNothing() throws ShellException {
        assertDoesNotThrow(() -> CommandBuilder.parseCommand("ls * | echo 'List of files and directories'",applicationRunner));
        assertEquals(PipeCommand.class,CommandBuilder.parseCommand("ls * | echo 'List of files and directories'",applicationRunner).getClass());

    }
}