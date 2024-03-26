package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class MkdirApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private static final String MKDIR_HEADER = "mkdir: ";
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String TEST_DIRECTORY = "cs4218";
    private static final String[] TEST_DIRECTORIES = {"cs4218", "cs3203", "cs3219"};

    private static  String initialDirectory;
    private MkdirApplication mkdirApplication;
    ByteArrayInputStream inputStream;
    ByteArrayOutputStream outputStream;
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
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() {
        try {
            for (String dir : TEST_DIRECTORIES) {
                if (Files.exists(Path.of(dir))) {
                    FileUtils.removeFilesRecursive(new File(dir));
                }
            }
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
            Environment.currentDirectory = initialDirectory;
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void run_NullArgs_ShouldThrowMkdirException() {
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.run(null, inputStream, outputStream));
        assertEquals(MKDIR_HEADER + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_LengthZeroArgs_ShouldThrowMkdirException() {
        String[] args = {};
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.run(args, inputStream, outputStream));
        assertEquals(MKDIR_HEADER + ERR_MISSING_ARG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_ValidArgs_ShouldCreateDirectory() {
        mkdirApplication.run(TEST_DIRECTORIES, inputStream, outputStream);
        for (String dir :  TEST_DIRECTORIES) {
            Path expected = Paths.get(Environment.currentDirectory, dir);
            assertTrue(Files.exists(expected));
        }
    }

    @Test
    @SneakyThrows
    void run_ValidArgsWithNullStreams_ShouldCreateDirectory() {
        mkdirApplication.run(TEST_DIRECTORIES, null, null);
        for (String dir :  TEST_DIRECTORIES) {
            Path expected = Paths.get(Environment.currentDirectory, dir);
            assertTrue(Files.exists(expected));
        }
    }

    @Test
    @SneakyThrows
    void run_ValidNestedArgs_ShouldCreateDirectory() {
        final String nestedDir = "cs4218/lectures";
        String[] folders = {TEST_DIRECTORY, nestedDir};
        mkdirApplication.run(folders, inputStream, outputStream);
        for (String dir :  folders) {
            Path expected = Paths.get(Environment.currentDirectory, dir);
            assertTrue(Files.exists(expected));
        }
    }

    @Test
    @SneakyThrows
    void run_ValidArgsWithParentFlag_ShouldCreateDirectory() {
        final String parentDir = TEST_DIRECTORY + StringUtils.fileSeparator() + TEMP_DIR;
        String[] folders = {"-p", parentDir};
        mkdirApplication.run(folders, inputStream, outputStream);
        Path expected = Paths.get(Environment.currentDirectory, parentDir);
        assertTrue(Files.exists(expected));
    }

    @Test
    @SneakyThrows
    void run_InvalidFlagArg_ShouldThrowMkdirException() {
        final String[] folders = {"-a", TEST_DIRECTORY};
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.run(folders, inputStream, outputStream));
        assertEquals(MKDIR_HEADER + ERR_INVALID_FLAG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_EmptyFolderName_ShouldThrowMkdirException() {
        final String[] folders = {"-p" };
        MkdirException exception = assertThrows(MkdirException.class,
                () -> mkdirApplication.run(folders, inputStream, outputStream));
        assertEquals(MKDIR_HEADER + ERR_MISSING_ARG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_MultipleFoldersWithParent_ShouldBeSuccess() {
        String folder1 = TEMP_DIR + StringUtils.fileSeparator() + TEST_DIRECTORY;
        String folder2 = TEST_DIRECTORY + StringUtils.fileSeparator() + TEMP_DIR;
        final String[] folders = {"-p", folder1, folder2};
        mkdirApplication.run(folders, inputStream, outputStream);
        Path expected1 = Paths.get(Environment.currentDirectory, folder1);
        Path expected2 = Paths.get(Environment.currentDirectory, folder2);
        assertTrue(Files.exists(expected1));
        assertTrue(Files.exists(expected2));
    }
}
