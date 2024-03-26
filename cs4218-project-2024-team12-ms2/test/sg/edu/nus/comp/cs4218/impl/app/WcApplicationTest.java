package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

class WcApplicationTest {
    private static final String WC_HEADER = "wc: ";
    private static final String RESOURCES = Paths.get("test", "resources", "wc").toString();
    private static final String ONE = "words1.txt";
    private static final String TWO = "words2.txt";
    private static final String THREE = "words3.txt";
    private static final String NON_EXIST = "notExist.txt";
    private static final String DUMMY = "dummy";
    private static final String NO_READ = "noReadPermissionFile.txt";
    private static final String NUMBER_FORMAT = " %7d";
    private final WcInterface app = new WcApplication();

    private static String initialDirectory;

    @BeforeEach
    void setUp() {
        initialDirectory = Environment.currentDirectory;
        Environment.currentDirectory = Paths.get(RESOURCES).toAbsolutePath().toString();
    }

    @AfterEach
    void tearDown() {
        Path path = FileSystems.getDefault().getPath(RESOURCES, NO_READ);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Environment.currentDirectory = initialDirectory;
    }



    @Test
    void countFromFiles_NullFileName_ShouldThrowWcException() {
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFiles(false, false, false, null);
        });
        assertEquals(WC_HEADER + ERR_NO_FILE_ARGS, thrown.getMessage());
    }

    @Test
    void countFromFiles_NodeDoesNotExist_ShouldThrowException() {
        String[] args = {NON_EXIST};
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFiles(false, false, false, args);
        });
        assertEquals(WC_HEADER + ERR_FILE_NOT_FOUND, thrown.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void countFromFiles_NoPermissionToRead_ShouldThrowException() {
        String[] args = {Paths.get(NO_READ).toString()};

        Path path = FileSystems.getDefault().getPath(RESOURCES, NO_READ);
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("---------");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        try {
            Files.createFile(path, attr); // Create file with no read permission
        } catch (IOException e) {
            fail("Unable to create file with no read permission", e);
        }
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFiles(false, false, false, args);
        });
        assertEquals(WC_HEADER + ERR_NO_PERM, thrown.getMessage());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void countFromFiles_ValidInput_ShouldReturnCount() {
        String[] fileNames = {ONE, TWO, THREE};
        String expectedResult =
                String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + " " + ONE + System.lineSeparator() +
                        String.format(NUMBER_FORMAT, 3) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + " " + TWO + System.lineSeparator() +
                        String.format(NUMBER_FORMAT, 1) + String.format(NUMBER_FORMAT, 11) + String.format(NUMBER_FORMAT, 55) + " " + THREE + System.lineSeparator() +
                        String.format(NUMBER_FORMAT, 8) + String.format(NUMBER_FORMAT, 39) + String.format(NUMBER_FORMAT, 185) + " total";
        String result = app.countFromFiles(true, true, true, fileNames);
        assertEquals(expectedResult, result);
    }

    @Test
    void countFromStdin_NullStdin_ShouldThrowException() {
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromStdin(false, false, false, null);
        });
        assertEquals(WC_HEADER + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void countFromStdin_ValidInput_ShouldReturnCount() {
        String input = "Hello World" + STRING_NEWLINE;
        String expectedResult = String.format(NUMBER_FORMAT, 1) + String.format(NUMBER_FORMAT, 2) + String.format(NUMBER_FORMAT, 12);
        String result = app.countFromStdin(true, true, true, new ByteArrayInputStream(input.getBytes()));
        assertEquals(expectedResult, result);
    }

    @Test
    void countFromFileAndStdin_NullStdin_ShouldThrowException() {
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFileAndStdin(false, false, false, null, "file");
        });
        assertEquals(WC_HEADER + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    void countFromFileAndStdin_NullFileName_ShouldThrowException() {
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFileAndStdin(false, false, false, new ByteArrayInputStream("".getBytes()), null);
        });
        assertEquals(WC_HEADER + ERR_NO_FILE_ARGS, thrown.getMessage());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void countFromFileAndStdin_ValidInputDash_ShouldReturnCount() {
        String[] files = {STRING_DASH, ONE};
        String input = "Hello World" + STRING_NEWLINE;
        String expectedResult =
                String.format(NUMBER_FORMAT, 1) + String.format(NUMBER_FORMAT, 2) + String.format(NUMBER_FORMAT, 12) + System.lineSeparator() +
                String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + " " + ONE + System.lineSeparator() +
                        String.format(NUMBER_FORMAT, 5) + String.format(NUMBER_FORMAT, 16) + String.format(NUMBER_FORMAT, 77) + " total";

        String result = app.countFromFileAndStdin(true, true, true, new ByteArrayInputStream(input.getBytes()), files);
        assertEquals(expectedResult, result);
    }

    @Test
    @SneakyThrows
    void countFromFileAndStdin_MissingFileInput_ShouldThrowWcException() {
        String[] files = {STRING_DASH, String.valueOf(CHAR_REDIR_INPUT)};
        Throwable thrown = assertThrows(WcException.class, () -> {
            app.countFromFileAndStdin(false, false, false, System.in, files);
        });
        assertEquals(WC_HEADER + ERR_NO_FILE_ARGS, thrown.getMessage());

    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void countFromFileAndStdin_ValidInputDashAndInputFile_ShouldReturnCount() {
        String[] files = {STRING_DASH, String.valueOf(CHAR_REDIR_INPUT), ONE};
        String expectedResult =
                String.format(
                        String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + System.lineSeparator() +
                        String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + " total");

        String result = app.countFromFileAndStdin(true, true, true, System.in, files);
        assertEquals(expectedResult, result);
    }
}
