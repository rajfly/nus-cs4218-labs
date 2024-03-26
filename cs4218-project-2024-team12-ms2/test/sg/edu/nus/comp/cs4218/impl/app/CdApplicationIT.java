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
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class CdApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private static final String CD_HEADER = "cd: ";
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() +
            TEMP_DIR + StringUtils.fileSeparator();

    private CdInterface cdApplication;
    private static  String initialDirectory;
    ByteArrayInputStream inputStream;
    ByteArrayOutputStream outputStream;


    @BeforeEach
    void setUp() {
        try {
            initialDirectory = Environment.currentDirectory;
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
        cdApplication = new CdApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
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
    void run_NullArgs_ShouldThrowCdException() {
        CdException exception = assertThrows(CdException.class,
                () -> cdApplication.run(null, inputStream, outputStream));
        assertEquals(CD_HEADER + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_LengthMoreThanOneArgs_ShouldThrowCdException() {
        String[] args = {"desktop", "home"};
        CdException exception = assertThrows(CdException.class,
                () -> cdApplication.run(args, inputStream, outputStream));
        assertEquals(CD_HEADER + ERR_TOO_MANY_ARGS, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_NullStreams_ShouldChangeDirectory() {
        String[] args = { TEMP_DIR_PATH };
        Path expectedPath = Paths.get(Environment.currentDirectory, TEMP_DIR_PATH);
        cdApplication.run(args, null, null);
        Path actualPath = Paths.get(Environment.currentDirectory);
        assertEquals(expectedPath, actualPath);
    }

    @Test
    @SneakyThrows
    void run_LengthZeroArgs_ShouldThrowCdException() {
        String[] args = {};
        CdException exception = assertThrows(CdException.class,
                () -> cdApplication.run(args, inputStream, outputStream));
        assertEquals(CD_HEADER + ERR_MISSING_ARG, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void run_ValidArgs_ShouldChangeDirectory() {
        String[] args = { TEMP_DIR_PATH };
        Path expectedPath = Paths.get(Environment.currentDirectory, TEMP_DIR_PATH);
        cdApplication.run(args, inputStream, outputStream);
        Path actualPath = Paths.get(Environment.currentDirectory);
        assertEquals(expectedPath, actualPath);
    }
}
