package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.*;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.testutils.TestEnvironmentUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class MkdirApplicationPublicTest {
    private static final String TEMP = "mkdirTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static final String TEMP_DIR = "mkdirTest";
    private static final String TEMP_DIR_2 = "mkdirTest2";
    private static final String TEMP_PARENT = "mkdirTestParent";
    private static final String TEMP_CHILD = TEMP_PARENT + File.separator + "mkdirTestChild";
    private static MkdirApplication mkdirApplication;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        mkdirApplication = new MkdirApplication();
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = TEMP_PATH.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(TEMP_PATH)
             .sorted(Comparator.reverseOrder())
             .map(Path::toFile)
             .forEach(File::delete);
        Environment.currentDirectory = ORIGINAL_DIR;
        for (Path file : FILES) {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void createFolder_NullInput_ThrowsException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.createFolder(null);
        });
    }

    @Test
    void createFolder_EmptyInput_ThrowsException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.createFolder("");
        });
        assertEquals(0, Objects.requireNonNull(new File(TEMP_PATH.toString()).list()).length);
    }

    @Test
    void createFolder_OneNewDirectoryInput_Success() throws Exception {
        mkdirApplication.createFolder(TEMP_DIR);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR).toString()).exists());
    }

    @Test
    void createFolder_TwoNewDirectoryInput_Success() throws Exception {
        mkdirApplication.createFolder(TEMP_DIR, TEMP_DIR_2);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR).toString()).exists());
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR_2).toString()).exists());
    }

    @Test
    void createFolder_DirectoryInDirectoryInput_Success() throws Exception {
        mkdirApplication.createFolder(TEMP_PARENT, TEMP_CHILD);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_CHILD).toString()).exists());
    }
}
