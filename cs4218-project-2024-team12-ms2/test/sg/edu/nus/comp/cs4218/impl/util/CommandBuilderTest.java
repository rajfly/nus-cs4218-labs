package sg.edu.nus.comp.cs4218.impl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;

class CommandBuilderTest {
    ApplicationRunner applicationRunner;
    private static final String ERR_MSG_FORMAT = "shell: ";

    @BeforeEach
    void setUp() {
        applicationRunner = new ApplicationRunner();
    }

    @Test
    @SneakyThrows
    void parseCommand_EmptyCommand_ShouldThrowException() {
        ShellException exception = assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("", applicationRunner));

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseCommand_NewLineCommand_ShouldThrowException() {
        ShellException exception = assertThrows(ShellException.class, () -> CommandBuilder.parseCommand(StringUtils.STRING_NEWLINE, applicationRunner));

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseCommand_RubbishCommand_ShouldThrowException() {
        ShellException exception = assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("???;", applicationRunner));

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseCommand_ValidCallCommand_ShouldReturnCommand() {
        String commandString = "echo blah";
        Command command = CommandBuilder.parseCommand(commandString, applicationRunner);

        assertEquals(CallCommand.class, command.getClass());
        CallCommand callCommand = (CallCommand) command;
        assertEquals(2, callCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "blah"}, callCommand.getArgsList().toArray());
    }

    @Test
    @SneakyThrows
    void parseCommand_ValidPipeCommand_ShouldReturnCommand() {
        String commandString = "echo first | echo second";
        Command command = CommandBuilder.parseCommand(commandString, applicationRunner);

        assertEquals(PipeCommand.class, command.getClass());
        PipeCommand pipeCommand = (PipeCommand) command;
        assertEquals(2, pipeCommand.getCallCommands().size());

        assertEquals(CallCommand.class, pipeCommand.getCallCommands().get(0).getClass());
        CallCommand firstCallCommand = pipeCommand.getCallCommands().get(0);
        assertEquals(2, firstCallCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "first"}, firstCallCommand.getArgsList().toArray());

        assertEquals(CallCommand.class, pipeCommand.getCallCommands().get(1).getClass());
        CallCommand secondCallCommand = pipeCommand.getCallCommands().get(1);
        assertEquals(2, secondCallCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "second"}, secondCallCommand.getArgsList().toArray());
    }

    @Test
    @SneakyThrows
    void parseCommand_ValidSequenceCommand_ShouldReturnCommand() {
        String commandString = "echo hello ; echo world";
        Command command = CommandBuilder.parseCommand(commandString, applicationRunner);

        assertEquals(SequenceCommand.class, command.getClass());
        SequenceCommand sequenceCommand = (SequenceCommand) command;
        assertEquals(2, sequenceCommand.getCommands().size());

        assertEquals(CallCommand.class, sequenceCommand.getCommands().get(0).getClass());
        CallCommand firstEchoCommand = (CallCommand) sequenceCommand.getCommands().get(0);
        assertEquals(2, firstEchoCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "hello"}, firstEchoCommand.getArgsList().toArray());

        assertEquals(CallCommand.class, sequenceCommand.getCommands().get(1).getClass());
        CallCommand secondEchoCommand = (CallCommand) sequenceCommand.getCommands().get(1);
        assertEquals(2, secondEchoCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "world"}, secondEchoCommand.getArgsList().toArray());
    }

    @Test
    @SneakyThrows
    void parseCommand_InvalidSequenceStartsWithSemicolon_ShouldThrowException() {
        ShellException exception = assertThrows(ShellException.class, () -> CommandBuilder.parseCommand(";echo hello world", applicationRunner));

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseCommand_InvalidStartWithPipeOperator_ShouldThrowException() {
        ShellException exception = assertThrows(ShellException.class, () -> CommandBuilder.parseCommand("|echo hello", applicationRunner));

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseCommand_ValidPipeWithSequenceCommand_ShouldReturnCommand() {
        String commandString = "echo test | cat ; ls";
        Command command = CommandBuilder.parseCommand(commandString, applicationRunner);

        assertEquals(SequenceCommand.class, command.getClass());

        SequenceCommand sequenceCommand = (SequenceCommand) command;

        assertEquals(2, sequenceCommand.getCommands().size());
        assertEquals(PipeCommand.class, sequenceCommand.getCommands().get(0).getClass());
        assertEquals(CallCommand.class, sequenceCommand.getCommands().get(1).getClass());
        PipeCommand pipeCommand = (PipeCommand) sequenceCommand.getCommands().get(0);
        assertEquals(2, pipeCommand.getCallCommands().size());

        assertEquals(CallCommand.class, pipeCommand.getCallCommands().get(0).getClass());
        CallCommand echoCommand = pipeCommand.getCallCommands().get(0);
        assertEquals(2, echoCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_ECHO, "test"}, echoCommand.getArgsList().toArray());

        assertEquals(CallCommand.class, pipeCommand.getCallCommands().get(1).getClass());
        CallCommand catCommand = pipeCommand.getCallCommands().get(1);
        assertEquals(1, catCommand.getArgsList().size());
        assertArrayEquals(new String[]{APP_CAT}, catCommand.getArgsList().toArray());

        CallCommand lsCommand = (CallCommand) sequenceCommand.getCommands().get(1);
        assertEquals(1, lsCommand.getArgsList().size());
        assertEquals(APP_LS, lsCommand.getArgsList().get(0));
        assertArrayEquals(new String[]{APP_LS}, lsCommand.getArgsList().toArray());
    }

    @Test
    @SneakyThrows
    void parseCommand_RedirOutputCommand_ShouldReturnCommand() {
        String commandString = "echo hello > file1.txt";
        Command command = CommandBuilder.parseCommand(commandString, applicationRunner);

        assertEquals(CallCommand.class, command.getClass());
        CallCommand callCommand = (CallCommand) command;

        assertEquals(4, callCommand.getArgsList().size());
        List<String> expectedArgsList = List.of(APP_ECHO, "hello", ">", "file1.txt");
        assertEquals(expectedArgsList, callCommand.getArgsList());
    }

}