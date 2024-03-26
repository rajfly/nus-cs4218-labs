package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class CdApplicationTest {
    private static final String CD_HEADER = "cd: ";
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String NON_EXISTENT_PATH = "/nonExistentPath";
    public static final String STRING_EMPTY = "";
    private static  String initialDirectory;


    private CdInterface cdApplication;

    @BeforeEach
    void setUp() {
        try {
            initialDirectory = Environment.currentDirectory;
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
        cdApplication = new CdApplication();
    }

    @AfterEach
    void cleanUp() {
        try {
            Environment.currentDirectory = initialDirectory;
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void changeToDirectory_NonExistentFileOrDirectory_ShouldThrowCdException() {
        CdException exception = assertThrows(CdException.class,
                () -> cdApplication.changeToDirectory(NON_EXISTENT_PATH));
        String expected = String.format("%s%s: %s", CD_HEADER, NON_EXISTENT_PATH, ERR_FILE_NOT_FOUND);
        assertEquals(expected, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void changeToDirectory_ExistingFileOrDirectory_ShouldChangeDirectory() {
        Path expectedPath = Paths.get(Environment.currentDirectory, TEMP_DIR_PATH);
        cdApplication.changeToDirectory(TEMP_DIR_PATH);
        Path actualPath = Paths.get(Environment.currentDirectory);
        assertTrue(Files.exists(actualPath));
        assertEquals(expectedPath, actualPath);
    }

    @Test
    @SneakyThrows
    void changeToDirectory_BlankPath_ShouldThrowCdException() {
        CdException exception = assertThrows(CdException.class,
                () -> cdApplication.changeToDirectory(STRING_EMPTY));
        assertEquals(CD_HEADER + ERR_MISSING_ARG, exception.getMessage());
    }
}
