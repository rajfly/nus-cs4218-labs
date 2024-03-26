package sg.edu.nus.comp.cs4218.impl.app.ef2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import org.mockito.*;
import sg.edu.nus.comp.cs4218.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.app.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_RM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FILE_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;

public class RmApplicationTest {
    private RmApplication rmApp;
    private static final String ORIGINAL_DIR = Environment.currentDirectory;

    @TempDir
    Path tempDir; // JUnit creates and cleans up this directory

    @BeforeEach
    void setUp() {
        rmApp = new RmApplication();
        // Set the current directory to the temp directory for the context of these tests
        Environment.currentDirectory = tempDir.toString();
    }

    @AfterAll
    static void reset() {
        Environment.currentDirectory = ORIGINAL_DIR;
    }

    @Test
    void run_NullArgs_ThrowsRmException() {
        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(null, null, null));
        assertEquals(APP_RM + ": " + ERR_NULL_ARGS, thrown.getMessage());
        assertTrue(Files.exists(tempDir));
    }

    @Test
    void run_EmptyArgs_ThrowsRmException() {
        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(new String[]{}, null, null));
        assertEquals(APP_RM + ": " + ERR_NULL_ARGS, thrown.getMessage());
        assertTrue(Files.exists(tempDir));
    }

    @Test
    void run_InvalidFlags_ThrowsRmException() {
        assertThrows(RmException.class, () -> rmApp.run(new String[]{"-a", "test"}, null, null));
    }

    @Test
    void run_SingleFile_FileIsRemoved() throws Exception {
        Path file = tempDir.resolve("test.txt");
        Files.createFile(file);

        rmApp.run(new String[]{"test.txt"}, null, null);

        // Then - file is removed
        assertTrue(Files.notExists(file));
    }

    @Test
    void run_NonExistentFile_ThrowsRmException() throws Exception {
        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(new String[]{"test2.txt"}, null, null));
        assertEquals(APP_RM + ": test2.txt: " + ERR_FILE_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void run_NonExistentDir_ThrowsRmException() throws Exception {
        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(new String[]{"-d", "test2"}, null, null));
        assertEquals(APP_RM + ": test2: " + ERR_FILE_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void run_EmptyDirectoryWithDFlag_DirectoryIsRemoved() throws Exception {
        Path dir = Files.createDirectory(tempDir.resolve("emptyDir"));

        rmApp.run(new String[]{"-d", "emptyDir"}, null, null);

        // Then - directory is removed
        assertTrue(Files.notExists(dir));
    }

    @Test
    void run_NonEmptyDirectoryWithoutFlag_ThrowsRmException() throws Exception {
        String dirName = "nonEmptyDir";
        Path dir = Files.createDirectory(tempDir.resolve(dirName));
        Files.createFile(dir.resolve("file.txt"));

        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(new String[]{dirName}, null, null));
        assertEquals(APP_RM + ": " + dirName + ": Is a directory, need to add flag", thrown.getMessage());
        assertTrue(Files.exists(dir));
    }

    @Test
    void run_NonEmptyDirectoryWithDFlag_ThrowsRmException() throws Exception {
        String dirName = "nonEmptyDir";
        Path dir = Files.createDirectory(tempDir.resolve(dirName));
        Files.createFile(dir.resolve("file.txt"));

        Throwable thrown = assertThrows(RmException.class, () -> rmApp.run(new String[]{"-d", dirName}, null, null));
        assertEquals(APP_RM + ": " + dirName + ": Directory not empty", thrown.getMessage());
        assertTrue(Files.exists(dir));
    }

    @Test
    void run_NonEmptyDirectoryWithRFlag_DirectoryIsRemoved() throws Exception {
        String dirName = "nonEmptyDir";
        Path dir = Files.createDirectory(tempDir.resolve(dirName));
        Files.createFile(dir.resolve("file.txt"));

        rmApp.run(new String[]{"-r", dirName}, null, null);

        assertTrue(Files.notExists(dir));
    }

    @Test
    void remove_NullFilename_ThrowsRmException() {
        Throwable thrown = assertThrows(RmException.class, () -> rmApp.remove(true, true, null));
        assertTrue(thrown.getMessage().contains(ERR_MISSING_ARG));
    }

    @Test
    void remove_MockWhenIOExceptionIsThrown_ThrowsRmException() {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            // Setup
            String fileName = "nonDeletableFile.txt";
            Path path = Paths.get(Environment.currentDirectory).resolve(fileName).normalize();

            mockedFiles.when(() -> Files.exists(any(Path.class))).thenReturn(true);
            mockedFiles.when(() -> Files.isDirectory(any(Path.class))).thenReturn(false);
            mockedFiles.when(() -> Files.delete(any(Path.class))).thenThrow(IOException.class);

            // Action & Assert
            RmException thrown = assertThrows(RmException.class, () ->
                    rmApp.remove(false, false, fileName)
            );

            assertTrue(thrown.getMessage().contains("Error removing file:"));
        }
    }
}
