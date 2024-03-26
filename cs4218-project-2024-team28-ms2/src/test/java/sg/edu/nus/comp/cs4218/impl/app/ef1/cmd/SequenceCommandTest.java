package sg.edu.nus.comp.cs4218.impl.app.ef1.cmd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.STRING_NEWLINE;

class SequenceCommandTest {
    private List<Command> commandsList;
    private List<CallCommand> callCommandList;
    private SequenceCommand sequenceCommand;
    private ApplicationRunner appRunner;
    private ArgumentResolver argumentResolver;
    private InputStream inputStream;
    private OutputStream outputStream;
    private List<String> argsList;

    @BeforeEach
    void setUp() {
        commandsList = new ArrayList<>();
        callCommandList = new ArrayList<>();
        sequenceCommand = null;
        appRunner = new ApplicationRunner();
        argumentResolver = new ArgumentResolver();
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
        argsList = new ArrayList<>();
    }

    @Test
    void evaluate_TwoEmptyCallCommands_DisplayError() throws FileNotFoundException, AbstractApplicationException, ShellException {
        CallCommand firstCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        CallCommand secondCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        String expectedResult = "shell: Invalid syntax\nshell: Invalid syntax\n";
        commandsList.add(firstCallCommand);
        commandsList.add(secondCallCommand);
        sequenceCommand = new SequenceCommand(commandsList);
        sequenceCommand.evaluate(inputStream,outputStream);
        assertEquals(expectedResult,outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    void evaluate_TwoEmptyPipeCommands_DisplayError() throws FileNotFoundException, AbstractApplicationException, ShellException {
        CallCommand firstCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        CallCommand secondCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        callCommandList.add(firstCallCommand);
        callCommandList.add(secondCallCommand);
        PipeCommand firstPipeCommand = new PipeCommand(callCommandList);
        PipeCommand secondPipeCommand = new PipeCommand(callCommandList);
        String expectedResult = "shell: Invalid syntax\nshell: Invalid syntax\n";
        commandsList.add(firstPipeCommand);
        commandsList.add(secondPipeCommand);
        sequenceCommand = new SequenceCommand(commandsList);
        sequenceCommand.evaluate(inputStream,outputStream);
        assertEquals(expectedResult,outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    void evaluate_MultipleCallCommandsWithError_DisplayResultAndError() throws FileNotFoundException, AbstractApplicationException, ShellException {
        argsList.add("echo");
        argsList.add("hello world");
        CallCommand firstCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        CallCommand secondCallCommand = new CallCommand(Collections.emptyList(),appRunner,argumentResolver);
        CallCommand thirdCallCommand = new CallCommand(argsList,appRunner,argumentResolver);
        String expectedResult = "hello world" + STRING_NEWLINE
            + "shell: Invalid syntax" + STRING_NEWLINE
            + "hello world" + STRING_NEWLINE;
        commandsList.add(firstCallCommand);
        commandsList.add(secondCallCommand);
        commandsList.add(thirdCallCommand);
        sequenceCommand = new SequenceCommand(commandsList);
        sequenceCommand.evaluate(inputStream,outputStream);
        assertEquals(expectedResult,outputStream.toString());
    }


}