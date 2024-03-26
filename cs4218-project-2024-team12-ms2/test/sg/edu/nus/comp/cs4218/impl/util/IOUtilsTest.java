package sg.edu.nus.comp.cs4218.impl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;

@EnabledOnOs({OS.LINUX, OS.MAC})
class IOUtilsTest {

    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + File.separator + TEMP_DIR + File.separator;
    private static final String ERR_MSG_FORMAT = "shell: ";

    @BeforeEach
    void setUp() {
        try {
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (IOException e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    @SneakyThrows
    void openInputStream_ValidFile_ShouldReturnInputStream() {
        String testFile = TEMP_DIR_PATH + "a.txt";
        FileUtils.createFile(testFile);

        assertNotNull(IOUtils.openInputStream(testFile));
    }

    @Test
    @SneakyThrows
    void openOutputStream_fileNotFound_ShouldThrowException() {
        String missingFile = TEMP_DIR_PATH + "missing.txt";

        Exception exception = assertThrows(Exception.class, () -> IOUtils.openInputStream(missingFile));
        assertEquals(ERR_MSG_FORMAT + ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void openOutputStream_ValidFile_ShouldReturnOutputStream() {
        String testFile = TEMP_DIR_PATH + "a.txt";
        FileUtils.createFile(testFile);

        assertNotNull(IOUtils.openOutputStream(testFile));
    }

    @Test
    @SneakyThrows
    void closeInputStream_NullInputStream_ShouldNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeInputStream(null));
    }

    @Test
    @SneakyThrows
    void closeInputStream_StdinInputStream_ShouldNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeInputStream(System.in));
    }

    @Test
    @SneakyThrows
    void closeInputStream_ValidInputStream_ShouldNotThrowException() {
        String testFile = TEMP_DIR_PATH + "b.txt";
        FileUtils.createFile(testFile);

        assertDoesNotThrow(() -> IOUtils.closeInputStream(IOUtils.openInputStream(testFile)));
    }

    @Test
    @SneakyThrows
    void closeOutputStream_NullOutputStream_ShouldNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(null));
    }

    @Test
    @SneakyThrows
    void closeOutputStream_StdoutOutputStream_ShouldNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(System.out));
    }

    @Test
    @SneakyThrows
    void getLinesFromInputStream_Valid_ShouldReturnLines() {
        String testFile = TEMP_DIR_PATH + "c.txt";
        FileUtils.createFile(testFile);
        FileUtils.writeFileContent(testFile, "line1" + StringUtils.STRING_NEWLINE + "line2" + StringUtils.STRING_NEWLINE + "line3");

        String[] lines = IOUtils.getLinesFromInputStream(IOUtils.openInputStream(testFile)).toArray(new String[0]);

        assertArrayEquals(new String[]{"line1", "line2", "line3"}, lines);
    }

}