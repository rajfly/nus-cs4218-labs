package cs4218;

import cs4218.impl.commands.CallCommand;
import cs4218.impl.commands.PipeCommand;
import cs4218.interfaces.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandBuilderTest {
    CommandBuilder commandBuilder;

    @BeforeEach
    void setUp() {
        commandBuilder = new CommandBuilder();
    }

    @Test
    void getCommand_LineDoesNotContainPipeOperator_ReturnsCallCommand() {
        Command command = commandBuilder.getCommand("echo hello", null);

        assertEquals(CallCommand.class, command.getClass());
    }

    @Test
    void getCommand_LineContainsPipeOperator_ReturnsPipeCommand() {
        Command command = commandBuilder.getCommand("echo hello | rep hello hello", null);

        assertEquals(PipeCommand.class, command.getClass());
    }
}