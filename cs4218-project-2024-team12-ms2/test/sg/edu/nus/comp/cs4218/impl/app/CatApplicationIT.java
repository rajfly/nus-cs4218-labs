package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

class CatApplicationIT { //NOPMD - suppressed ClassNamingConventions

    private CatApplication catApplication;
    ByteArrayInputStream inputStream;
    ByteArrayOutputStream outputStream;
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String FILE_ONE_PATH = TEMP_DIR_PATH + "file1.txt";
    private static final String ERR_MSG_FORMAT = "cat: ";
    private final String FILE_ONE_CLEAN =
            "Hello" + StringUtils.STRING_NEWLINE + "World";

    @BeforeEach
    void setUp() {
        try {
            // Create directory for temporary files that will be cleared after each test
            FileUtils.createDirectory(TEMP_DIR_PATH);
            FileUtils.writeFileContent(FILE_ONE_PATH, FILE_ONE_CLEAN);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
        catApplication = new CatApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() {
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void run_NullArgs_ShouldThrowException() {
        CatException exception = assertThrows(CatException.class, () -> catApplication.run(null, inputStream, outputStream));

        assertEquals(ERR_MSG_FORMAT + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_NullInputStream_ShouldThrowException() {
        CatException exception = assertThrows(CatException.class, () -> catApplication.run(new String[0], null, outputStream));

        assertEquals(ERR_MSG_FORMAT + ERR_NULL_STREAMS, exception.getMessage());
    }

    @Test
    void run_NullOutputStream_ShouldThrowException() {
        CatException exception = assertThrows(CatException.class, () -> catApplication.run(new String[0], inputStream, null));

        assertEquals(ERR_MSG_FORMAT + ERR_NULL_STREAMS, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_ValidArgs_ShouldReturnCorrectly() {
        assertDoesNotThrow(() -> catApplication.run(new String[]{FILE_ONE_PATH}, inputStream, outputStream));

        assertEquals(outputStream.toString(), FILE_ONE_CLEAN + StringUtils.STRING_NEWLINE);
    }

    @Test
    @SneakyThrows
    void run_IllegalArgs_ShouldThrowException() {
        CatException exception = assertThrows(CatException.class, () -> catApplication.run(new String[]{"-a"}, inputStream, outputStream));

        assertEquals(ERR_MSG_FORMAT + ErrorConstants.ERR_ILLEGAL_FLAG + "a", exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("cat - file1.txt")
    void run_ReadFromInputStreamAndFile_ShouldReturnCorrectly() {
        String input = "Input" + StringUtils.STRING_NEWLINE + "Stream";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        assertDoesNotThrow(() -> catApplication.run(new String[]{StringUtils.STRING_DASH, FILE_ONE_PATH}, inputStream, outputStream));

        assertEquals(input + StringUtils.STRING_NEWLINE + FILE_ONE_CLEAN + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    @DisplayName("cat file1.txt -")
    void run_ReadFromFileAndInputStream_ShouldReturnCorrectly() {
        String input = "Input" + StringUtils.STRING_NEWLINE + "Stream";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        assertDoesNotThrow(() -> catApplication.run(new String[]{FILE_ONE_PATH, StringUtils.STRING_DASH}, inputStream, outputStream));

        assertEquals(FILE_ONE_CLEAN + StringUtils.STRING_NEWLINE + input + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

}
