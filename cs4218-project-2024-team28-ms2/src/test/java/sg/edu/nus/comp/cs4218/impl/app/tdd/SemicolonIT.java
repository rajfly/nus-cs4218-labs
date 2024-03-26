package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
public class SemicolonIT { //NOPMD

    Shell shell;
    OutputStream outputStream;
    @BeforeEach
    void setUp() {
        shell = new ShellImpl();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    void run_MultipleApplicationWithSemicolon_NoExceptionThrown() throws FileNotFoundException, AbstractApplicationException, ShellException {
        String commandString = "fake application; fake ; fake ; echo 'hello world'";
        assertDoesNotThrow(() -> shell.parseAndEvaluate(commandString,outputStream));
        String commandString2 = "echo 'Hello world'; echo 'ggwp' | cat";
        assertDoesNotThrow(() -> shell.parseAndEvaluate(commandString2,outputStream));
    }

    @Test
    void run_EmptyApplicationWithSemicolon_ThrowException() {
        String commandString = ";";
        assertThrows(Exception.class,() -> shell.parseAndEvaluate(commandString,outputStream));
    }
}
