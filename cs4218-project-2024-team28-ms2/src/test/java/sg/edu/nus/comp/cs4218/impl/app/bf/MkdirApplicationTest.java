package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_EXISTS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FOLDERS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_TOP_LEVEL_MISSING;

/**
 * Test class for {@code MkDirApplication}
 * <br><br>
 * <p>
 *     Positive:
 * </p>
 * <p>
 *     - Single directory arg
 * </p>
 * <p>
 *     - Single nested directory arg
 * </p>
 * <p>
 *     - Multiple directory args
 * </p>
 * <p>
 *     - Single parent directory arg
 * </p>
 * <p>
 *     - Multiple parent directory args
 * </p>
 * <p>
 *     - Directory already exists but p flag supplied
 * </p>
 * <p>
 *     - Nested directory already exists but p flag supplied
 * </p>
 * <br>
 * <p>
 *      Negative:
 * </p>
 * <p>
 *     - Null directory arg
 * </p>
 * <p>
 *     - Empty directory arg
 * </p>
 * <p>
 *     - Invalid flags
 * </p>
 * <p>
 *     - Directory arg exists as file
 * </p>
 * <p>
 *     - Directory already exists
 * </p>
 * <p>
 *     - Directory args without top level directory and p flag
 * </p>
 * <p>
 *     - getNormalizedAbsolutePath with empty path string
 * </p>
 */
public class MkdirApplicationTest {
    private static final String TEMP = "mkdirTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static final String TEST_DIR = "mkdirTestParent";
    private static final String NESTED_DIR = "abc";
    private static MkdirApplication mkdir;
    private static OutputStream out;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        mkdir = new MkdirApplication();
        out = new ByteArrayOutputStream();
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = TEMP_PATH.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        out.flush();
        Files.walk(TEMP_PATH)
             .sorted(Comparator.reverseOrder())
             .map(Path::toFile)
             .forEach(File::delete);
        Environment.currentDirectory = ORIGINAL_DIR;
        for (Path file : FILES) {
            Files.deleteIfExists(file);
        }
    }

    private void createTempFile(String name, String content) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        Files.write(path, content.getBytes());
        FILES.push(path);
    }

    private void createTempDir(String name) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createDirectory(path);
        FILES.push(path);
    }

    @Test
    @DisplayName("Single directory arg")
    void run_SingleDirectoryArg_CreatesCorrectDirectories() throws MkdirException {
        // Given
        String[] args = {TEST_DIR};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertTrue(TEMP_PATH.resolve(TEST_DIR).toFile().exists());
    }

    @Test
    @DisplayName("Single nested directory arg")
    void run_SingleNestedDirectoryArg_CreatesCorrectDirectories() throws MkdirException, IOException {
        // Given
        createTempDir(TEST_DIR);
        String[] args = {TEST_DIR + File.separator + NESTED_DIR};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertTrue(TEMP_PATH.resolve(TEST_DIR + File.separator + NESTED_DIR).toFile().exists());
    }

    @Test
    @DisplayName("Multiple directory args")
    void run_MultipleDirectoryArgs_CreatesCorrectFolders() throws MkdirException {
        // Given
        String[] args = {"test1", "test2", "test3"};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertTrue(TEMP_PATH.resolve("test1").toFile().exists());
        assertTrue(TEMP_PATH.resolve("test2").toFile().exists());
        assertTrue(TEMP_PATH.resolve("test3").toFile().exists());
    }

    @Test
    @DisplayName("Single parent directory arg")
    void run_SingleParentDirectoryArg_CreatesCorrectDirectories() throws MkdirException {
        // Given
        String[] args = {"-p", "a/b"};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertTrue(TEMP_PATH.resolve("a").toFile().exists());
        assertTrue(TEMP_PATH.resolve("a" + File.separator + "b").toFile().exists());
    }

    @Test
    @DisplayName("Multiple parent directory args")
    void run_MultipleParentDirectoryArgs_CreatesCorrectDirectories() throws MkdirException {
        // Given
        String[] args = {"-p", "a/b", "c/d"};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertTrue(TEMP_PATH.resolve("a").toFile().exists());
        assertTrue(TEMP_PATH.resolve("a" + File.separator + "b").toFile().exists());
        assertTrue(TEMP_PATH.resolve("c").toFile().exists());
        assertTrue(TEMP_PATH.resolve("c" + File.separator + "d").toFile().exists());
    }

    @Test
    @DisplayName("Directory already exists but p flag supplied")
    void run_ExistingDirectoryWithPFlag_NoException() throws IOException, MkdirException {
        // Given
        createTempDir(TEST_DIR);
        String[] args = {"-p", TEST_DIR};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertEquals("", out.toString());
    }

    @Test
    @DisplayName("Nested directory already exists but p flag supplied")
    void run_ExistingNestedDirectoryWithPFlag_NoException() throws IOException, MkdirException {
        // Given
        createTempDir(TEST_DIR);
        createTempDir(TEST_DIR + File.separator + NESTED_DIR);
        String[] args = {"-p", TEST_DIR + File.separator + NESTED_DIR};

        // When
        mkdir.run(args, System.in, out);

        // Then
        assertEquals("", out.toString());
    }

    @Test
    @DisplayName("Null directory arg")
    void run_NullDirectoryArg_ThrowsMkdirException() {
        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(null, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_NULL_ARGS);
    }

    @Test
    @DisplayName("Empty directory arg")
    void run_EmptyDirectoryArg_ThrowsMkdirException() {
        // Given
        String[] args = {};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_NO_FOLDERS);
    }

    @Test
    @DisplayName("Invalid flags")
    void run_InvalidFlags_ThrowsMkdirException() {
        // Given
        String[] args = {"-a"};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, " -a: " + ERR_INVALID_FLAG);
    }

    @Test
    @DisplayName("Directory arg exists as file")
    void run_ExistingDirectoryArgAsFile_ThrowsMkdirException() throws IOException {
        // Given
        createTempFile("a", "example");
        String[] args = {"a"};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_FILE_EXISTS);
    }

    @Test
    @DisplayName("Directory already exists")
    void run_DirectoryAlreadyExists_ThrowsMkdirException() throws IOException {
        // Given
        createTempDir(TEST_DIR);
        String[] args = {TEST_DIR};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_FILE_EXISTS);
    }

    @Test
    @DisplayName("Directory already exists as file")
    void run_DirectoryAlreadyExistsAsFile_ThrowsMkdirException() throws IOException {
        // Given
        createTempFile("a.txt", "example");
        String[] args = {"a.txt"};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_FILE_EXISTS);
    }

    @Test
    @DisplayName("Directory args without top level directory and p flag")
    void run_DirectoryArgsNoPFlag_ThrowsMkdirException() {
        // Given
        String[] args = {"a/b"};

        // When
        MkdirException exception = assertThrows(MkdirException.class, () -> mkdir.run(args, System.in, out));

        // Then
        assertExceptionContains(exception, ERR_TOP_LEVEL_MISSING);
    }

    @Test
    @DisplayName("getNormalizedAbsolutePath with empty path string")
    void getNormalizedAbsolutePath_EmptyPathString_ThrowsMkdirException() {
        // When
        MkdirException exception = assertThrows(MkdirException.class,
            () -> mkdir.getNormalizedAbsolutePath(""));

        // Then
        assertExceptionContains(exception, ERR_NO_ARGS);
    }
}
