package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class RmApplicationIT { //NOPMD - suppressed ClassNamingConventions

    private RmApplication rmApplication;
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String RECURSIVE_FLAG = "-r";
    private static final String EMPTY_DIR_FLAG = "-d";
    private static final String RM_EXCEPTION_MSG = "rm: ";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR;
    private static final String FILE_ONE_PATH = TEMP_DIR_PATH + StringUtils.fileSeparator() + "file1.txt";
    private static final String FILE_TWO_PATH = TEMP_DIR_PATH + StringUtils.fileSeparator() + "file2.txt";
    private static final String NESTED_DIR_PATH = TEMP_DIR_PATH + StringUtils.fileSeparator() + "nested";
    private static final String NESTED_FILE_PATH = NESTED_DIR_PATH + StringUtils.fileSeparator() + "nestedFile.txt";
    private static final String EMPTY_DIR_PATH = TEMP_DIR_PATH + StringUtils.fileSeparator() + "empty";

    @BeforeEach
    void setUp() {
        rmApplication = new RmApplication();
        try {
            // Create directory for temporary files that will be cleared after each test
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            if (new File(TEMP_DIR_PATH).exists()) {
                FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
            }
        } catch (IOException e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    @SneakyThrows
    void run_InvalidArgs_ShouldThrowException() {
        String[] args = {"-i"};
        Exception exception = assertThrows(Exception.class, () -> rmApplication.run(args, System.in, System.out));

        assertEquals(RM_EXCEPTION_MSG + ERR_INVALID_FLAG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm file1.txt")
    void run_RemoveOneFileWithRecursiveFlag_ShouldRemoveFile() {
        FileUtils.createFile(FILE_ONE_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());

        String[] args = {RECURSIVE_FLAG, FILE_ONE_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertFalse(new File(FILE_ONE_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -d -r file1.txt")
    void run_RemoveOneFileWithBothFlags_ShouldRemoveFile() {
        FileUtils.createFile(FILE_ONE_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());

        String[] args = {RECURSIVE_FLAG, EMPTY_DIR_FLAG, FILE_ONE_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertFalse(new File(FILE_ONE_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -d empty")
    void run_RemoveEmptyDirectoryWithEmptyDirFlag_ShouldRemoveEmptyDir() {
        FileUtils.createDirectory(EMPTY_DIR_PATH);
        assertTrue(new File(EMPTY_DIR_PATH).exists());

        String[] args = {EMPTY_DIR_FLAG, EMPTY_DIR_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertFalse(new File(EMPTY_DIR_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -d temp")
    void run_RemoveNonEmptyDirectoryWithEmptyDirFlag_ShouldThrowException() {
        FileUtils.createFile(FILE_ONE_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());

        String[] args = {EMPTY_DIR_FLAG, TEMP_DIR_PATH};
        Exception exception = assertThrows(Exception.class, () -> rmApplication.run(args, System.in, System.out));

        assertEquals(RM_EXCEPTION_MSG + TEMP_DIR_PATH + ": " + "Directory not empty", exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r file1.txt file2.txt")
    void run_RemoveMultipleFilesWithRecursiveFlag_ShouldRemoveFiles() {
        FileUtils.createFile(FILE_ONE_PATH);
        FileUtils.createFile(FILE_TWO_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());
        assertTrue(new File(FILE_TWO_PATH).exists());

        String[] args = {RECURSIVE_FLAG, FILE_ONE_PATH, FILE_TWO_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertFalse(new File(FILE_ONE_PATH).exists());
        assertFalse(new File(FILE_TWO_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r temp")
    void run_RemoveNestedEmptyDirectoriesWithRecursiveFlag_ShouldRemoveDirectories() {
        FileUtils.createDirectory(NESTED_DIR_PATH);
        assertTrue(new File(NESTED_DIR_PATH).exists());

        String[] args = {RECURSIVE_FLAG, TEMP_DIR_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertFalse(new File(TEMP_DIR_PATH).exists());
        assertFalse(new File(NESTED_DIR_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r temp/nested")
    void run_RemoveNestedDirectoryWithRecursiveFlag_ShouldRemoveAllContents() {
        FileUtils.createDirectory(NESTED_DIR_PATH);
        FileUtils.createFile(NESTED_FILE_PATH);
        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertTrue(new File(NESTED_DIR_PATH).exists());
        assertTrue(new File(NESTED_FILE_PATH).exists());

        String[] args = {RECURSIVE_FLAG, NESTED_DIR_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertFalse(new File(NESTED_DIR_PATH).exists());
        assertFalse(new File(NESTED_FILE_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r temp/nested")
    void run_RemoveEmptyDirAndNestedFile_ShouldRemoveOnlyInnerFile() {
        FileUtils.createDirectory(EMPTY_DIR_PATH);
        FileUtils.createFile(NESTED_FILE_PATH);
        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertTrue(new File(EMPTY_DIR_PATH).exists());
        assertTrue(new File(NESTED_DIR_PATH).exists());
        assertTrue(new File(NESTED_FILE_PATH).exists());

        String[] args = {RECURSIVE_FLAG, NESTED_DIR_PATH};
        assertDoesNotThrow(() -> rmApplication.run(args, System.in, System.out));

        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertTrue(new File(EMPTY_DIR_PATH).exists());
        assertFalse(new File(NESTED_DIR_PATH).exists());
        assertFalse(new File(NESTED_FILE_PATH).exists());
    }

    @Test
    @SneakyThrows
    void run_NullArgs_ShouldThrowException() {
        try {
            rmApplication.run(null, System.in, System.out);
        } catch (RmException e) {
            assertEquals("rm: Null arguments", e.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void run_EmptyArgs_ShouldThrowException() {
        try {
            rmApplication.run(new String[0], System.in, System.out);
        } catch (RmException e) {
            assertEquals("rm: Missing Argument", e.getMessage());
        }
    }

    @Test
    void run_NullStream_ShouldThrowException() {
        String[] args = {RECURSIVE_FLAG, NESTED_DIR_PATH};
        Throwable thrown = assertThrows(RmException.class, () -> rmApplication.run(args, null, System.out));
        assertEquals(RM_EXCEPTION_MSG + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    @SneakyThrows
    void run_EmptyFiles_ShouldThrowException() {
        String[] args = {RECURSIVE_FLAG};
        try {
            rmApplication.run(args, System.in, System.out);
        } catch (RmException e) {
            assertEquals("rm: Missing Argument", e.getMessage());
        }
    }

}
