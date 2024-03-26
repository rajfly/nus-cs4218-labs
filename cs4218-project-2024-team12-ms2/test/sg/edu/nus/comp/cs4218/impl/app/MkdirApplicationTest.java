package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class MkdirApplicationTest {
    private static final String MKDIR_HEADER = "mkdir: ";
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String TEST_DIRECTORY = "cs4218";
    private static final String[] TEST_DIRECTORIES = {"cs4218", "cs3203", "cs3219"};
    private static final String PARENT_DIR = "parent";
    private static final String CHILD_DIR = "parent" + StringUtils.fileSeparator() + "child";
    private static final String GRAND_CHILD_DIR = "parent" + StringUtils.fileSeparator() + "child" + StringUtils.fileSeparator() +
            "grandchild";

    private static String initialDirectory;
    private MkdirApplication mkdirApplication;

    @BeforeEach
    void setUp() {
        try {
            initialDirectory = Environment.currentDirectory;
            FileUtils.createDirectory(TEMP_DIR_PATH);
            Environment.currentDirectory = Paths.get(TEMP_DIR_PATH).toString();
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
        mkdirApplication = new MkdirApplication();
    }

    /**
     * Clear the directories.
     *
     * @param directories The directories to clear
     */
    @SneakyThrows
    private void clearDir(String... directories) {
        for (String dir : directories) {
            if (Files.exists(Path.of(dir))) {
                FileUtils.removeFilesRecursive(new File(dir));
            }
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            String[] dirToClear = {TEMP_DIR_PATH, PARENT_DIR, CHILD_DIR, GRAND_CHILD_DIR};
            clearDir(dirToClear);
            clearDir(TEST_DIRECTORIES);
            Environment.currentDirectory = initialDirectory;
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    @SneakyThrows
    void createFolder_OneDirectory_ShouldCreateDirectory() {
        String[] folderNames = {TEST_DIRECTORY};
        mkdirApplication.createFolder(folderNames);
        Path expected = Paths.get(Environment.currentDirectory, TEST_DIRECTORY);
        assertTrue(Files.exists(expected));
    }

    @Test
    @SneakyThrows
    void createFolder_MultipleDirectory_ShouldCreateDirectories() {
        mkdirApplication.createFolder(TEST_DIRECTORIES);
        for (String dir : TEST_DIRECTORIES) {
            Path expected = Paths.get(Environment.currentDirectory, dir);
            assertTrue(Files.exists(expected));
        }
    }

    @Test
    @SneakyThrows
    void createFolder_DuplicatedDirectory_ShouldThrowMkdirException() {
        final String[] duplicatedDir = {TEST_DIRECTORY, TEST_DIRECTORY};
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.createFolder(duplicatedDir));
        String expected = String.format("%s%s: %s", MKDIR_HEADER, TEST_DIRECTORY, ERR_FILE_EXISTS);
        assertEquals(expected, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void createFolder_InvalidDirectory_ShouldThrowMkdirException() {
        final String invalidDir = "invalid" + StringUtils.fileSeparator() + "dir";
        Path invalidPath = Paths.get(invalidDir);
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.createFolder(new String[]{invalidDir}));
        String expected = String.format("%s%s: %s", MKDIR_HEADER, invalidPath.getParent(), ERR_FILE_NOT_FOUND);
        assertEquals(expected, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void createFolder_WithOptionP_ShouldCreateParentDirectories() {
        final String[] folderNames = {"-p", GRAND_CHILD_DIR};
        Path expectedParent = Paths.get(Environment.currentDirectory, PARENT_DIR);
        Path expectedChild = Paths.get(Environment.currentDirectory, CHILD_DIR);
        Path expectedGrand = Paths.get(Environment.currentDirectory, GRAND_CHILD_DIR);
        mkdirApplication.createFolder(folderNames);
        assertTrue(Files.exists(expectedParent));
        assertTrue(Files.exists(expectedChild));
        assertTrue(Files.exists(expectedGrand));
    }

    @Test
    @SneakyThrows
    void createFolder_WithMissingArgsAfterOption_ShouldThrowMkdirException() {
        final String[] folderNames = {"-p"};
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.createFolder(folderNames));
        assertEquals(String.format("%s%s", MKDIR_HEADER, ERR_MISSING_ARG), exception.getMessage());
    }

    @Test
    @SneakyThrows
    void createFolder_FileUtilsThrowsIOException_ShouldThrowMkdirException() {
        String[] folderNames = {"exampleFolder"};
        try (var mock = mockStatic(FileUtils.class)) {
            mock.when(() -> FileUtils.createDirectory(any())).thenThrow(new IOException());
            MkdirException exception = assertThrows(MkdirException.class, () -> mkdirApplication.createFolder(folderNames));

            assertEquals(String.format("%s%s", MKDIR_HEADER, "IOException"), exception.getMessage());
        }
    }

}
