package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PipeCommandTest {

    PipeCommand pipeCommand;
    List<CallCommand> callCommands;
    InputStream mockStdin;
    OutputStream mockStdout;

    ApplicationRunner appRunner;
    ArgumentResolver argumentResolver;
    private ArgumentCaptor<byte[]> argumentCaptor;

    @Captor
    private ArgumentCaptor<InputStream> inputCaptor;

    @Captor
    private ArgumentCaptor<OutputStream> outputCaptor;

    private static final List<String> ECHO_HELLO = List.of("echo", "hello");
    private static final List<String> ECHO_WORLD = List.of("echo", "world");

    @BeforeEach
    void setup() {
        mockStdin = System.in;
        mockStdout = mock(OutputStream.class);
        appRunner = new ApplicationRunner();
        argumentResolver = new ArgumentResolver();
        callCommands = new LinkedList<>();
        argumentCaptor = ArgumentCaptor.forClass(byte[].class);
        inputCaptor = ArgumentCaptor.forClass(InputStream.class);
        outputCaptor = ArgumentCaptor.forClass(OutputStream.class);
    }

    @Test
    void evaluate_CallCommandsIsNull_ThrowsShellException() {
        pipeCommand = new PipeCommand(null);
        Exception exception = assertThrows(ShellException.class, () -> pipeCommand.evaluate(mockStdin, mockStdout));
        assertEquals("shell: " + ErrorConstants.ERR_SYNTAX, exception.getMessage());
    }

    @Test
    void evaluate_OneCommand_ThrowsShellException() {
        callCommands.add(new CallCommand(ECHO_HELLO, appRunner, argumentResolver));
        pipeCommand = new PipeCommand(callCommands);
        Exception exception = assertThrows(ShellException.class, () -> pipeCommand.evaluate(mockStdin, mockStdout));
        assertEquals("shell: " + ErrorConstants.ERR_SYNTAX, exception.getMessage());
    }

    @Test
    void evaluate_ThreeCommandsWithInvalidCommand_ThrowsShellException() {
        callCommands.add(new CallCommand(ECHO_HELLO, appRunner, argumentResolver));
        callCommands.add(new CallCommand(List.of("invalidCommand"), appRunner, argumentResolver));
        callCommands.add(new CallCommand(ECHO_HELLO, appRunner, argumentResolver));
        pipeCommand = new PipeCommand(callCommands);
        Exception exception = assertThrows(ShellException.class, () -> pipeCommand.evaluate(mockStdin, mockStdout));
        assertEquals("shell: " + "invalidCommand: " + ErrorConstants.ERR_INVALID_APP, exception.getMessage());
    }

    @Test
    void evaluate_MockThreeCommands_EvaluatedInOrder() throws IOException, AbstractApplicationException, ShellException {
        CallCommand firstCommand = mock(CallCommand.class);
        CallCommand secondCommand = mock(CallCommand.class);
        CallCommand thirdCommand = mock(CallCommand.class);
        callCommands.add(firstCommand);
        callCommands.add(secondCommand);
        callCommands.add(thirdCommand);
        pipeCommand = new PipeCommand(callCommands);
        pipeCommand.evaluate(mockStdin, mockStdout);

        InOrder inOrder = inOrder(firstCommand, secondCommand, thirdCommand);
        inOrder.verify(firstCommand).evaluate(any(InputStream.class), any(OutputStream.class));
        inOrder.verify(secondCommand).evaluate(any(InputStream.class), any(OutputStream.class));
        inOrder.verify(thirdCommand).evaluate(any(InputStream.class), any(OutputStream.class));
    }
    @Test
    void evaluate_ValidTwoCommands_OutputSecondCommandToStdout() throws IOException, AbstractApplicationException, ShellException {
        CallCommand firstCommand = new CallCommand(ECHO_HELLO, appRunner, argumentResolver);
        CallCommand secondCommand = new CallCommand(ECHO_WORLD, appRunner, argumentResolver);
        callCommands.add(firstCommand);
        callCommands.add(secondCommand);
        pipeCommand = new PipeCommand(callCommands);
        pipeCommand.evaluate(mockStdin, mockStdout);

        verify(mockStdout).write(argumentCaptor.capture());
        String expectedString = "world" + StringUtils.STRING_NEWLINE;
        assertArrayEquals(expectedString.getBytes(), argumentCaptor.getValue());

    }

    @Test
    void evaluate_MockTwoCommands_OutputOfFirstIsInputToSecond() throws Exception {
        CallCommand firstCommand = mock(CallCommand.class);
        CallCommand secondCommand = mock(CallCommand.class);
        callCommands.add(firstCommand);
        callCommands.add(secondCommand);
        pipeCommand = new PipeCommand(callCommands);
        // Setup the first command to write to its OutputStream
        doAnswer(invocation -> {
            OutputStream outputStream = invocation.getArgument(1);
            outputStream.write("output from first command".getBytes());
            return null;
        }).when(firstCommand).evaluate(any(InputStream.class), any(OutputStream.class));

        pipeCommand.evaluate(mockStdin, mockStdout);

        // Capture the OutputStream used by the first command
        verify(firstCommand).evaluate(any(InputStream.class), outputCaptor.capture());
        byte[] outputFromFirst = ((ByteArrayOutputStream) outputCaptor.getValue()).toByteArray();

        // Check that the input to the second command is the output from the first command
        verify(secondCommand).evaluate(inputCaptor.capture(), any(OutputStream.class));
        byte[] inputToSecond = inputCaptor.getValue().readAllBytes();
        assertArrayEquals(outputFromFirst, inputToSecond);
    }
}