package cs4218;

import cs4218.exceptions.ApplicationException;
import cs4218.exceptions.ShellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static cs4218.ApplicationRunner.ECHO_APP_NAME;
import static cs4218.ApplicationRunner.REP_APP_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationRunnerIT {
    private ApplicationRunner applicationRunner;
    private List<String> tokens;
    private InputStream inputStream;
    private OutputStream outputStream;
    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        EnvironmentUtil.currentDirectory = tempDir.toAbsolutePath().toString();
        Path tempFile = tempDir.resolve("temp-file.txt");
        Files.write(tempFile, "This is a temp file!".getBytes());
        this.applicationRunner = new ApplicationRunner();
        tokens = new ArrayList<>();
        inputStream = new ByteArrayInputStream(new byte[]{});
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    void runApp_EchoApplication_WritesToOutputCorrectly() throws ShellException, ApplicationException {
        // Test integration of application runner and echo application
        // Unit tests already test that
        //  1. Application runner creates the correct echo application object
        //  2. Internal logic of echo application (single string, empty string etc.) is correct
        // For integration test, we want to test that there are no issues executing the echo application object
        // that is created by applicationRunner.
        String strToEcho = "abc";
        tokens.add(strToEcho);
        applicationRunner.runApp(ECHO_APP_NAME, tokens, inputStream, outputStream);
        assertEquals(strToEcho, outputStream.toString());
    }

    @Test
    void runApp_RepApplication_WriteToOutputCorrectly() throws ShellException, ApplicationException {
        String expectedFileContents = "This is a temporary file!";
        List<String> tokens = List.of("temp", "temporary", "temp-file.txt");
        applicationRunner.runApp(REP_APP_NAME, tokens, inputStream, outputStream);
        assertEquals(expectedFileContents, outputStream.toString());
    }
}
