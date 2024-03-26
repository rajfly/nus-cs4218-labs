package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsTest {
    private final static String RANDOM_FILE = "someRandomFile.txt";

    @BeforeAll
    static void initialSetup() {
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @BeforeEach
    void setUp() {
        File file = new File(RANDOM_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File(RANDOM_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    void openInputStream_fileNotFound_ThrowShellException() {
        assertThrows(ShellException.class, () -> IOUtils.openInputStream(RANDOM_FILE));
        File file = new File(RANDOM_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void openInputStream_fileExists_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.openInputStream("IOUtilsTest.txt"));
    }

    @Test
    void openOutputStream_fileNotFound_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.openOutputStream(RANDOM_FILE));
        File file = new File(RANDOM_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void openOutputStream_fileExists_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.openOutputStream("IOUtilsTest.txt"));
    }

    @Test
    void closeInputStream_EmptyInputString_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.closeInputStream(System.in));
    }

    @Test
    void closeOutputStream_EmptyOutputString_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(System.out));
    }

    @Test
    void resolveFilePath_EmptyFilePath_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.resolveFilePath(""));
    }

    @Test
    void getLinesFromInputStream_EmptyInputStream_ThrowNothing() {
        assertDoesNotThrow(() -> IOUtils.getLinesFromInputStream(null));
    }
}