package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.SortException;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class SortApplicationTest {
    private static final String SORT_HEADER = "sort: ";
    private static final String RESOURCES = Paths.get("test", "resources", "sort").toString();
    private static final String ONE = "1.txt";
    private static final String TWO = "2.txt";
    private static final String NO_READ = "noReadPermissionFile.txt";
    private static final String NON_EXIST = "notExist.txt";
    private final SortInterface app = new SortApplication();

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
    }


    @Test
    void sortFromStdin_NullStdin_ShouldThrowException() {
        Throwable thrown = assertThrows(SortException.class, () -> {
            app.sortFromStdin(false, false, false, null);
        });
        assertEquals(SORT_HEADER + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    void sortFromStdin_ValidInput_ShouldReturnSortedOutput() {
        String original = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D" +
                STRING_NEWLINE + "1" + STRING_NEWLINE + "10";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "1" + STRING_NEWLINE + "10" + STRING_NEWLINE + "A" + STRING_NEWLINE + "D" +
                STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(false, false, false, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithFirstWordNumber_ShouldReturnSortedOutput() {
        String original = "10" + STRING_NEWLINE + "1" + STRING_NEWLINE + "2";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "1" + STRING_NEWLINE + "2" + STRING_NEWLINE + "10" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(true, false, false, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithReverseOrder_ShouldReturnSortedOutput() {
        String original = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "c" + STRING_NEWLINE + "b" + STRING_NEWLINE + "D" + STRING_NEWLINE + "A" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(false, true, false, stdin);
            assertEquals(expectResult, realResult);
        });
    }


    @Test
    void sortFromStdin_ValidInputWithCaseIndependent_ShouldReturnSortedOutput() {
        String original = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(false, false, true, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithFirstWordNumberAndReverseOrder_ShouldReturnSortedOutput() {
        String original = "A1" + STRING_NEWLINE + "A" + STRING_NEWLINE + "A" + STRING_NEWLINE + "A";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "A1" + STRING_NEWLINE + "A" + STRING_NEWLINE + "A" + STRING_NEWLINE + "A" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(true, true, false, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithFirstWordNumberAndCaseIndependent_ShouldReturnSortedOutput() {
        String original = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(true, false, true, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithReverseOrderAndCaseIndependent_ShouldReturnSortedOutput() {
        String original = "A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "c" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "D" + STRING_NEWLINE + "c" + STRING_NEWLINE + "b" + STRING_NEWLINE + "A" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(false, true, true, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromStdin_ValidInputWithFirstWordNumberAndReverseOrderAndCaseIndependent_ShouldReturnSortedOutput() {
        String original = "A1" + STRING_NEWLINE + "B" + STRING_NEWLINE + "A" + STRING_NEWLINE + "C";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "C" + STRING_NEWLINE + "B" + STRING_NEWLINE + "A1" + STRING_NEWLINE + "A" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromStdin(true, true, true, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void sortFromFiles_NullFiles_ShouldThrowException() {
        Throwable thrown = assertThrows(SortException.class, () -> {
            app.sortFromFiles(false, false, false, (String[]) null);
        });
        assertEquals(SORT_HEADER + ERR_NULL_ARGS, thrown.getMessage());
    }

    @Test
    void sortFromFiles_NodeDoesNotExist_ShouldThrowException() {
        String[] args = {NON_EXIST};
        Throwable thrown = assertThrows(SortException.class, () -> {
            app.sortFromFiles(false, false, false, args);
        });
        assertEquals(SORT_HEADER + ERR_FILE_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void sortFromFiles_IsDirectory_ShouldThrowException() {
        String[] args = {Paths.get(RESOURCES).toString()};
        Throwable thrown = assertThrows(SortException.class, () -> {
            app.sortFromFiles(false, false, false, args);
        });
        assertEquals(SORT_HEADER + ERR_IS_DIR, thrown.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void sortFromFiles_NoPermissionToRead_ShouldThrowException() {
        String[] args = {Paths.get(RESOURCES, NO_READ).toString()};

        Path path = FileSystems.getDefault().getPath(RESOURCES, NO_READ);
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("---------");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        try {
            Files.createFile(path, attr); // Create file with no read permission
        } catch (IOException e) {
            e.printStackTrace();
        }
        Throwable thrown = assertThrows(SortException.class, () -> {
            app.sortFromFiles(false, false, false, args);
        });
        assertEquals(SORT_HEADER + ERR_NO_PERM, thrown.getMessage());
    }

    @Test
    void sortFromFiles_ValidInput_ShouldReturnSortedOutput() {
        String[] args = {Paths.get(RESOURCES, ONE).toString(), Paths.get(RESOURCES, TWO).toString()};
        String expectResult = "3" + STRING_NEWLINE +
                "4" + STRING_NEWLINE +
                "5" + STRING_NEWLINE +
                "6" + STRING_NEWLINE +
                "7" + STRING_NEWLINE +
                "8" + STRING_NEWLINE +
                "9" + STRING_NEWLINE;
        assertDoesNotThrow(() -> {
            String realResult = app.sortFromFiles(false, false, false, args);
            assertEquals(expectResult, realResult);
        });
    }

}
