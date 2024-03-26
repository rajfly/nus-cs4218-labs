package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser.STRING_DASH;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

class CatApplicationTest {

    private CatApplication catApplication;
    ByteArrayInputStream inputStream;
    ByteArrayOutputStream outputStream;
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String FILE_ONE_PATH = TEMP_DIR_PATH + "file1.txt";
    private static final String FILE_TWO_PATH = TEMP_DIR_PATH + "file2.txt";
    private static final String INVALID_FILE_PATH = "invalid";
    private static final String ERR_MSG_FORMAT = "cat: ";

    private final String FILE_ONE_CLEAN =
            "Hello" + StringUtils.STRING_NEWLINE + "World";

    private final String FILE_ONE_NUMBERED =
            "1\tHello" + StringUtils.STRING_NEWLINE + "2\tWorld";

    private final String FILE_TWO_CLEAN =
            "Maple" + StringUtils.STRING_NEWLINE + "Story";

    private final String FILE_TWO_NUMBERED =
            "1\tMaple" + StringUtils.STRING_NEWLINE + "2\tStory";

    @BeforeEach
    void setUp() {
        try {
            // Create directory for temporary files that will be cleared after each test
            FileUtils.createDirectory(TEMP_DIR_PATH);
            FileUtils.writeFileContent(FILE_ONE_PATH, FILE_ONE_CLEAN);
            FileUtils.writeFileContent(FILE_TWO_PATH, FILE_TWO_CLEAN);
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
    @SneakyThrows
    void catFiles_ValidFile_ShouldReturnCorrectly() {
        String result = catApplication.catFiles(false, FILE_ONE_PATH);

        assertEquals(FILE_ONE_CLEAN, result);
    }

    @Test
    @SneakyThrows
    void catFiles_ValidFileWithNumbering_ShouldReturnCorrectly() {
        String result = catApplication.catFiles(true, FILE_ONE_PATH);

        assertEquals(FILE_ONE_NUMBERED, result);
    }

    @Test
    @SneakyThrows
    void catFiles_MultipleValidFiles_ShouldReturnCorrectly() {
        String result = catApplication.catFiles(false, FILE_ONE_PATH, FILE_TWO_PATH);

        assertEquals(FILE_ONE_CLEAN + StringUtils.STRING_NEWLINE + FILE_TWO_CLEAN, result);
    }

    @Test
    @SneakyThrows
    void catFiles_MultipleValidFilesNumbered_ShouldReturnCorrectly() {
        String result = catApplication.catFiles(true, FILE_ONE_PATH, FILE_TWO_PATH);

        assertEquals(FILE_ONE_NUMBERED + StringUtils.STRING_NEWLINE + FILE_TWO_NUMBERED, result);
    }

    @Test
    @SneakyThrows
    void catFiles_InvalidFile_ShouldThrowException() {
        CatException exception = assertThrows(CatException.class, () -> catApplication.catFiles(false, INVALID_FILE_PATH));

        assertEquals(ERR_MSG_FORMAT + ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void catStdin_ValidStdIn_ShouldReturnCorrectly() {
        String input = "Hello World";
        inputStream = new ByteArrayInputStream(input.getBytes());

        String result = catApplication.catStdin(false, inputStream);

        assertEquals(input, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cat - file1.txt")
    void catFileAndStdIn_ValidFileAndStdIn_ShouldReturnUnnumberedFile() {
        String input = "This is tiring";
        inputStream = new ByteArrayInputStream(input.getBytes());

        String result = catApplication.catFileAndStdin(false, inputStream, STRING_DASH, FILE_ONE_PATH);

        assertEquals(input + StringUtils.STRING_NEWLINE + FILE_ONE_CLEAN, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cat -n - file1.txt")
    void catFileAndStdIn_ValidFileAndStdIn_ShouldReturnNumberedFile() {
        String input = "This is tiring";
        String numberedInput = String.format("1\t%s", input);
        inputStream = new ByteArrayInputStream(input.getBytes());

        String result = catApplication.catFileAndStdin(true, inputStream, STRING_DASH, FILE_ONE_PATH);

        assertEquals(numberedInput + StringUtils.STRING_NEWLINE + FILE_ONE_NUMBERED, result);
    }

    @Test
    @SneakyThrows
    void catFileAndStdIn_NoFile_ShouldReturnStdIn() {
        String input = "This is tiring";
        inputStream = new ByteArrayInputStream(input.getBytes());

        String result = catApplication.catFileAndStdin(false, inputStream, STRING_DASH);

        assertEquals(input, result);
    }

}