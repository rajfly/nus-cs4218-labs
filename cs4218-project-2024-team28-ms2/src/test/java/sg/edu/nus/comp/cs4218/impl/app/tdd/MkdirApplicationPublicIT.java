package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;


public class MkdirApplicationPublicIT { // NOPMD
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
    void run_NullInput_ThrowsException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(null, null, null);
        });
    }

    @Test
    void run_EmptyInput_Success() throws Exception {
        String[] args = new String[0];
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(args, null, null);
        });
    }

    @Test
    void run_OneNewDirectoryInput_Success() throws Exception {
        String[] args = new String[1];
        args[0] = TEMP_DIR;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR).toString()).exists());
    }


    @Test
    void run_TwoNewDirectoryInput_Success() throws Exception {
        String[] args = new String[2];
        args[0] = TEMP_DIR;
        args[1] = TEMP_DIR_2;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR).toString()).exists());
        assertTrue(new File(TEMP_PATH.resolve(TEMP_DIR_2).toString()).exists());
    }

    @Test
    void run_DirectoryInDirectoryInput_Success() throws Exception {
        String[] args = new String[2];
        args[0] = TEMP_PARENT;
        args[1] = TEMP_CHILD;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(TEMP_PATH.resolve(TEMP_CHILD).toString()).exists());
    }
}
