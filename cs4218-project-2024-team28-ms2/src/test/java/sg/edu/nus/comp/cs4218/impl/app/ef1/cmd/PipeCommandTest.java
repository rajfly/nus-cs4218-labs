package sg.edu.nus.comp.cs4218.impl.app.ef1.cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PipeCommandTest {
    private InputStream inputStream;
    private OutputStream outputStream;
    private AbstractApplicationException absAppException;
    private ShellException shellException;
    private FileNotFoundException fileException;
    private PipeCommand pipeCommand;
    private List<CallCommand> callCommands;

    @BeforeEach
    void setUp() {
        absAppException = null;
        shellException = null;
        fileException = null;
        pipeCommand = null;
        callCommands = new ArrayList<>();
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void tearDown() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    @Test
    void evaluate_TwoCallCommands_NoException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        String correctResult = "test";
        // stub behaviour of call command to return "test"
       doAnswer(invocation -> {
           outputStream.write("test".getBytes());
           return null;
       }).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
       doNothing().when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
       callCommands.add(callCommand1);
       callCommands.add(callCommand2);

       // Create a Pipe command instance with the mocked callCommand
        pipeCommand = new PipeCommand(callCommands);
        pipeCommand.evaluate(inputStream,outputStream);
        assertEquals(correctResult,outputStream.toString());
    }

    @Test
    void evaluate_FirstCommandError_ThrowApplicationException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        absAppException = mock(AbstractApplicationException.class);
        doThrow(absAppException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(AbstractApplicationException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_FirstCommandError_ThrowShellException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        shellException = mock(ShellException.class);
        doThrow(shellException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(ShellException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_FirstCommandError_ThrowFileNotFoundException()  throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        fileException = mock(FileNotFoundException.class);
        doThrow(fileException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(FileNotFoundException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_SecondCommandError_ThrowApplicationException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        absAppException = mock(AbstractApplicationException.class);
        doThrow(absAppException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(AbstractApplicationException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_SecondCommandError_ThrowShellException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        shellException = mock(ShellException.class);
        doThrow(shellException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(ShellException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_SecondCommandError_ThrowFileNotFoundException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        fileException = mock(FileNotFoundException.class);
        doThrow(fileException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        doNothing().when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(FileNotFoundException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_TwoCommandsError_ThrowFileNotFoundException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        fileException = mock(FileNotFoundException.class);
        absAppException = mock(AbstractApplicationException.class);
        doThrow(fileException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        doThrow(absAppException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(FileNotFoundException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_TwoCommandsError_ThrowAbstractApplicationException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        fileException = mock(FileNotFoundException.class);
        absAppException = mock(AbstractApplicationException.class);
        doThrow(fileException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        doThrow(absAppException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(AbstractApplicationException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }

    @Test
    void evaluate_TwoCommandsError_ThrowShellException() throws AbstractApplicationException, ShellException, FileNotFoundException {
        CallCommand callCommand1 = mock(CallCommand.class);
        CallCommand callCommand2 = mock(CallCommand.class);
        shellException = mock(ShellException.class);
        absAppException = mock(AbstractApplicationException.class);
        doThrow(shellException).when(callCommand1).evaluate(any(InputStream.class),any(OutputStream.class));
        doThrow(absAppException).when(callCommand2).evaluate(any(InputStream.class),any(OutputStream.class));
        callCommands.add(callCommand1);
        callCommands.add(callCommand2);

        pipeCommand = new PipeCommand(callCommands);
        assertThrows(ShellException.class, () -> pipeCommand.evaluate(inputStream,outputStream));
    }
}