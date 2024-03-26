package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;

public class RmApplicationTest {
    private RmApplication rmApplication;
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
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
    void remove_RemoveNonExistingFile_ShouldThrowException() {
        String doesNotExist = "doesNotExist.txt";
        Exception exception = assertThrows(Exception.class, () -> rmApplication.remove(false, false, doesNotExist));

        assertEquals(RM_EXCEPTION_MSG + doesNotExist + ": " + ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -d empty")
    void remove_RemoveEmptyFolderWithEmptyDirFlag_ShouldRemoveFolder() {
        FileUtils.createFile(EMPTY_DIR_PATH);
        assertTrue(new File(EMPTY_DIR_PATH).exists());

        assertDoesNotThrow(() -> rmApplication.remove(true, false, EMPTY_DIR_PATH));

        assertFalse(new File(EMPTY_DIR_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -d temp")
    void remove_RemoveNonEmptyFolderWithEmptyDirFlag_ShouldThrowException() {
        FileUtils.createFile(FILE_ONE_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());

        Exception exception = assertThrows(Exception.class, () -> rmApplication.remove(true, false, TEMP_DIR_PATH));
        assertEquals(RM_EXCEPTION_MSG + TEMP_DIR_PATH + ": " + "Directory not empty", exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r temp")
    void remove_RemoveNonEmptyFolderWithRecursiveFlag_ShouldRemoveContents() {
        FileUtils.createFile(FILE_ONE_PATH);
        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertTrue(new File(FILE_ONE_PATH).exists());

        assertDoesNotThrow(() -> rmApplication.remove(false, true, TEMP_DIR_PATH));

        assertFalse(new File(TEMP_DIR_PATH).exists());
        assertFalse(new File(FILE_ONE_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm file1.txt file2.txt")
    void remove_RemoveMultipleFiles_ShouldRemoveAllFiles() {
        FileUtils.createFile(FILE_ONE_PATH);
        FileUtils.createFile(FILE_TWO_PATH);
        assertTrue(new File(FILE_ONE_PATH).exists());
        assertTrue(new File(FILE_TWO_PATH).exists());

        assertDoesNotThrow(() -> rmApplication.remove(false, false, FILE_ONE_PATH, FILE_TWO_PATH));

        assertFalse(new File(FILE_ONE_PATH).exists());
        assertFalse(new File(FILE_TWO_PATH).exists());
    }

    @Test
    @SneakyThrows
    @DisplayName("rm -r -d temp/nested")
    void remove_nestedDirectoryWithRecursiveFlag_ShouldRemoveAllContents() {
        FileUtils.createDirectory(NESTED_DIR_PATH);
        FileUtils.createFile(NESTED_FILE_PATH);
        assertTrue(new File(NESTED_DIR_PATH).exists());
        assertTrue(new File(NESTED_FILE_PATH).exists());

        assertDoesNotThrow(() -> rmApplication.remove(true, true, NESTED_DIR_PATH));

        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertFalse(new File(NESTED_DIR_PATH).exists());
        assertFalse(new File(NESTED_FILE_PATH).exists());
    }

    @Test
    void remove_NullArgs_ShouldThrowException() {
        assertThrows(Exception.class, () -> rmApplication.remove(false, false, (String[]) null));
    }

    @Test
    void remove_EmptyArgs_ShouldThrowException() {
        assertThrows(Exception.class, () -> rmApplication.remove(false, false, new String[0]));
    }

    @Test
    void remove_NullArg_ShouldThrowException() {
        assertThrows(Exception.class, () -> rmApplication.remove(false, false, (String) null));
    }

    @Test
    void remove_EmptyArg_ShouldThrowException() {
        assertThrows(Exception.class, () -> rmApplication.remove(false, false, ""));
    }

    @Test
    @SneakyThrows
    void remove_IOError_ShouldThrowException() {
        // mockito to mock FileUtils to throw IOException
        FileUtils.createDirectory(NESTED_DIR_PATH);
        FileUtils.createFile(NESTED_FILE_PATH);
        assertTrue(new File(NESTED_DIR_PATH).exists());
        assertTrue(new File(NESTED_FILE_PATH).exists());


        try (var mock = mockStatic(FileUtils.class)) {
            mock.when(() -> FileUtils.removeFilesRecursive(any())).thenThrow(new IOException("IOException"));
            RmException exception = assertThrows(RmException.class, () -> rmApplication.remove(false, true, NESTED_DIR_PATH));

            assertEquals(String.format("%s%s", RM_EXCEPTION_MSG, "IOException"), exception.getMessage());
        }

        assertThrows(Exception.class, () -> rmApplication.remove(false, false, "invalid"));
    }
}
