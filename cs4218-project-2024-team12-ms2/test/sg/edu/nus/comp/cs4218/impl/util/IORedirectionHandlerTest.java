package sg.edu.nus.comp.cs4218.impl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MULTIPLE_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;

class IORedirectionHandlerTest {

    private static final String SYNTAX_ERROR_MSG = "shell: " + ERR_SYNTAX;
    private static final String STDOUT = ">";
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String ERR_MSG_FORMAT = "shell: ";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String OUTPUT_PATH = TEMP_DIR_PATH + "out.txt";
    private static final String FILE_ONE_PATH = TEMP_DIR_PATH + "in.txt";
    private static final String FILE_TWO_PATH = TEMP_DIR_PATH + "in2.txt";
    private static final String ECHO_APP = "echo";
    ;

    @BeforeEach
    public void setUp() {
        try {
            // Create directory for temporary files that will be cleared after each test
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    public void cleanUp() {
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void extractRedirOptions_NullArgsList_ShouldThrowException() {
        IORedirectionHandler redirHandler = new IORedirectionHandler(null, System.in, System.out, new ArgumentResolver());

        ShellException exception = assertThrows(ShellException.class, redirHandler::extractRedirOptions);
        assertEquals(SYNTAX_ERROR_MSG, exception.getMessage());
    }

    @Test
    void extractRedirOptions_EmptyArgsList_ShouldThrowException() {
        ArrayList<String> argsList = new ArrayList<>();
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());

        ShellException exception = assertThrows(ShellException.class, redirHandler::extractRedirOptions);
        assertEquals(SYNTAX_ERROR_MSG, exception.getMessage());
    }

    @Test
    void extractRedirOptions_DuplicateRedirOutput_ShouldThrowException() {
        List<String> argsList = List.of(ECHO_APP, "blah", STDOUT, STDOUT);
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());

        ShellException exception = assertThrows(ShellException.class, redirHandler::extractRedirOptions);
        assertEquals(SYNTAX_ERROR_MSG, exception.getMessage());
    }

    @Test
    void extractRedirOptions_NonEmptyArgsList_ShouldNotThrowException() {
        List<String> argsList = List.of(ECHO_APP, "sleep");
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());
        assertDoesNotThrow(redirHandler::extractRedirOptions);

        assert (redirHandler.getNoRedirArgsList().equals(argsList));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void extractRedirOptions_ValidEchoRedirOutput_ShouldNotThrowException() {
        List<String> argsList = List.of(ECHO_APP, "hello", STDOUT, OUTPUT_PATH);
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());

        assertDoesNotThrow(redirHandler::extractRedirOptions);
        assert (redirHandler.getNoRedirArgsList().equals(List.of("echo", "hello")));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void extractRedirOptions_ValidCatRedirOutput_ShouldNotThrowException() {
        List<String> argsList = List.of("cat", "in.txt", STDOUT, OUTPUT_PATH);
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());

        assertDoesNotThrow(redirHandler::extractRedirOptions);
        assert (redirHandler.getNoRedirArgsList().equals(List.of("cat", "in.txt")));
    }

    @Test
    @SneakyThrows
    void extractRedirOptions_MultipleInputRedirections_ShouldThrowException() {
        FileUtils.createFile(FILE_ONE_PATH);
        FileUtils.createFile(FILE_TWO_PATH);
        List<String> argsList = List.of(ECHO_APP, "hello", "<", FILE_ONE_PATH, "<", FILE_TWO_PATH);
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, new ArgumentResolver());

        ShellException exception = assertThrows(ShellException.class, redirHandler::extractRedirOptions);

        assertEquals(ERR_MSG_FORMAT + ERR_MULTIPLE_STREAMS, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void extractRedirOptions_MultipleFileSegments_ShouldThrowException() {
        FileUtils.createFile(FILE_ONE_PATH);
        FileUtils.createFile(FILE_TWO_PATH);
        ArgumentResolver argumentResolver = mock(ArgumentResolver.class);
        when(argumentResolver.resolveOneArgument(any())).thenReturn(List.of(FILE_ONE_PATH, FILE_TWO_PATH));
        List<String> argsList = List.of("cat", "<", FILE_ONE_PATH + "<" + FILE_TWO_PATH);
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, System.in, System.out, argumentResolver);

        ShellException exception = assertThrows(ShellException.class, redirHandler::extractRedirOptions);

        assertEquals(ERR_MSG_FORMAT + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    void getInputStream_Valid_ShouldReturnInputStream() {
        IORedirectionHandler redirHandler = new IORedirectionHandler(new ArrayList<>(), System.in, System.out, new ArgumentResolver());
        assertEquals(System.in, redirHandler.getInputStream());
    }

    @Test
    void getOutputStream_Valid_ShouldReturnOutputStream() {
        IORedirectionHandler redirHandler = new IORedirectionHandler(new ArrayList<>(), System.in, System.out, new ArgumentResolver());
        assertEquals(System.out, redirHandler.getOutputStream());
    }


}