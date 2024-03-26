package sg.edu.nus.comp.cs4218.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class ShellImplTest {

    private final Shell shell = new ShellImpl();
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() +
            TEMP_DIR + StringUtils.fileSeparator();
    private static final String MKDIR_TEST_FOLDER = "MKDIR_TEST_FOLDER";
    private ByteArrayOutputStream outContent;
    private static  String initialDirectory;

    @BeforeEach
    public void setup() {
        try {
            initialDirectory = Environment.currentDirectory;
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanUp() {
        try {
            Environment.currentDirectory = initialDirectory;
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));

            // Clean up test folder created by mkdir
            final Path mkDirTestFolder = Paths.get(MKDIR_TEST_FOLDER);
            if (Files.exists(mkDirTestFolder)) {
                FileUtils.removeFilesRecursive(new File(MKDIR_TEST_FOLDER));
            }
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_Echo_ShouldReturnExpected() {
        String cmdLine = "echo test";

        assertDoesNotThrow(() -> shell.parseAndEvaluate(cmdLine, System.out));

        assertEquals("test" + STRING_NEWLINE, outContent.toString());
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_Cd_ShouldReturnExpected() {
        String cmdLine = String.format("cd %s", TEMP_DIR_PATH);
        assertDoesNotThrow(() -> shell.parseAndEvaluate(cmdLine, System.out));
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_Mkdir_ShouldReturnExpected() {
        String cmdLine = String.format("mkdir %s", MKDIR_TEST_FOLDER);
        assertDoesNotThrow(() -> shell.parseAndEvaluate(cmdLine, System.out));
        final Path mkDirTestFolder = Paths.get(MKDIR_TEST_FOLDER);
        assertTrue(Files.exists(mkDirTestFolder));
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_Sort_ShouldReturnExpected() {
        String cmdLine = "sort test/resources/sort/1.txt";
        String expected = "3" + STRING_NEWLINE + "4" + STRING_NEWLINE + "5" + STRING_NEWLINE + "7" + STRING_NEWLINE +
                "9" + STRING_NEWLINE;
        assertDoesNotThrow(() -> shell.parseAndEvaluate(cmdLine, System.out));
        assertEquals(expected, outContent.toString());
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_InvalidCommand_ShouldReturnErrorMessage() {
        String cmdLine = "invalidCommand";

        ShellException exception = assertThrows(ShellException.class, () -> shell.parseAndEvaluate(cmdLine, System.out));

        assertEquals("shell: invalidCommand: Invalid app", exception.getMessage());
    }

    @Test
    @SneakyThrows
    void parseAndEvaluate_Null_ShouldReturnErrorMessage() {
        String cmdLine = null;

        ShellException exception = assertThrows(ShellException.class, () -> shell.parseAndEvaluate(cmdLine, System.out));

        assertEquals("shell: Invalid syntax", exception.getMessage());
    }

}