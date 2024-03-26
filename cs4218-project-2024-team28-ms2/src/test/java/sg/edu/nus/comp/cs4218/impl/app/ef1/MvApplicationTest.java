package sg.edu.nus.comp.cs4218.impl.app.ef1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.app.MvApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_EXISTS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_NOT_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

/**
 * Test class for {@code MvApplication}
 * <br><br>
 * <p>
 *     Positive:
 * </p>
 * <p>
 *     - Rename existing file
 * </p>
 * <p>
 *     - Rename existing directory
 * </p>
 * <p>
 *     - Move single file to directory
 * </p>
 * <p>
 *     - Move multiple files to directory
 * </p>
 * <p>
 *     - Move directory to other directory
 * </p>
 * <p>
 *     - Move multiple directories to other directory
 * </p>
 * <p>
 *     - Move single file to overwrite other file
 * </p>
 * <br>
 * <p>
 *     Negative:
 * </p>
 * <p>
 *     - Null args
 * </p>
 * <p>
 *     - Null stdout
 * </p>
 * <p>
 *     - Insufficient args
 * </p>
 * <p>
 *     - Non-existent file arg
 * </p>
 * <p>
 *     - Non-existent directory to move to
 * </p>
 * <p>
 *     - Move directory to itself
 * </p>
 * <p>
 *     - Invalid flags
 * </p>
 * <p>
 *     - Move single file to overwrite existing file with n flag
 * </p>
 * <p>
 *     - Move single file to overwrite nested existing file with n flag
 * </p>
 */
public class MvApplicationTest {
    private static final String TEMP = "mvTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    public static final String DEST_DIR = "destDir";

    public static final String OLD_FILE = "old.txt";
    public static final String NEW_FILE = "new.txt";

    public static final String OLD_DIR = "dirOld";
    public static final String NEW_DIR = "dirNew";
    public static final String FILE_A = "a.txt";
    public static final String FILE_B = "b.txt";
    public static final String OTHER_DIR = "otherDir";
    public static final String ANOTHER_DIR = "anotherDir";
    public static final String FILE_TO_OVERWRITE = "overwriteFile.txt";
    public static final String NO_OVERWRITE_FILE = "NotOverwriteThisFile.txt";

    private static MvApplication mvApplication;
    private static OutputStream stdout;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        mvApplication = new MvApplication();
        stdout = new ByteArrayOutputStream();
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = TEMP_PATH.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        stdout.flush();
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
    @DisplayName("Rename existing file")
    void run_RenameExistingFile_RenamesCorrectly() throws AbstractApplicationException, IOException {
        // Given
        createTempFile(OLD_FILE, "");
        String[] args = {OLD_FILE, NEW_FILE};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(NEW_FILE)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OLD_FILE)));
    }

    @Test
    @DisplayName("Rename existing directory")
    void run_RenameExistingDirectory_RenamesCorrectly() throws AbstractApplicationException, IOException {
        // Given
        createTempDir(OLD_DIR);
        String[] args = {OLD_DIR, NEW_DIR};

        // When
        mvApplication.run(args, null, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(NEW_DIR)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OLD_DIR)));
    }

    @Test
    @DisplayName("Move single file to directory")
    void run_MoveSingleFileToDirectory_MovesCorrectly() throws MvException, IOException {
        // Given
        createTempFile(FILE_A, "");
        createTempDir(DEST_DIR);
        String[] args = {FILE_A, DEST_DIR};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + FILE_A)));
        assertFalse(Files.exists(TEMP_PATH.resolve(FILE_A)));
    }

    @Test
    @DisplayName("Move multiple files")
    void run_MoveMultipleFiles_MovesCorrectly() throws IOException, MvException {
        // Given
        createTempFile(FILE_A, "");
        createTempFile(FILE_B, "");
        createTempDir(DEST_DIR);
        String[] args = {FILE_A, FILE_B, DEST_DIR};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + FILE_A)));
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + FILE_B)));
        assertFalse(Files.exists(TEMP_PATH.resolve(FILE_A)));
        assertFalse(Files.exists(TEMP_PATH.resolve(FILE_B)));
    }

    @Test
    @DisplayName("Move directory to other directory")
    void run_MoveDirectoryToOtherDirectory_MovesCorrectly() throws IOException, MvException {
        // Given
        createTempDir(OTHER_DIR);
        createTempDir(DEST_DIR);
        String[] args = {OTHER_DIR, DEST_DIR};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + OTHER_DIR)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OTHER_DIR)));
    }

    @Test
    @DisplayName("Move multiple directories to other directory")
    void run_MoveMultipleDirectoriesToOtherDirectory_MovesCorrectly() throws IOException, MvException {
        // Given
        createTempDir(OTHER_DIR);
        createTempDir(ANOTHER_DIR);
        createTempDir(DEST_DIR);
        String[] args = {OTHER_DIR, ANOTHER_DIR, DEST_DIR};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + OTHER_DIR)));
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + ANOTHER_DIR)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OTHER_DIR)));
        assertFalse(Files.exists(TEMP_PATH.resolve(ANOTHER_DIR)));
    }

    @Test
    @DisplayName("Move single file without n flag")
    void run_MoveSingleFileToExistingFile_OverridesCorrectly() throws IOException, MvException {
        // Given
        createTempFile(FILE_TO_OVERWRITE, "");
        createTempFile(NO_OVERWRITE_FILE, "");
        String[] args = {FILE_TO_OVERWRITE, NO_OVERWRITE_FILE};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(IOUtils.resolveFilePath(NO_OVERWRITE_FILE)));
        assertFalse(Files.exists(IOUtils.resolveFilePath(FILE_TO_OVERWRITE)));
    }

    @Test
    @DisplayName("Move single file to nested existing file with n flag")
    void run_MoveSingleFileToNestedExistingFileWithOFlag_DoesNothing() throws IOException, MvException {
        // Given
        createTempDir(DEST_DIR);
        createTempFile(FILE_A, "");
        createTempFile(DEST_DIR + File.separator + NO_OVERWRITE_FILE, "");
        String[] args = {"-n", FILE_A, DEST_DIR + File.separator + NO_OVERWRITE_FILE};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(FILE_A)));
        assertTrue(Files.exists(TEMP_PATH.resolve(DEST_DIR + File.separator + NO_OVERWRITE_FILE)));
    }

    @Test
    @DisplayName("Move file to itself")
    void run_MoveFileToItself_DoesNothing() throws IOException, MvException {
        // Given
        createTempFile(FILE_A, "");
        String[] args = {FILE_A, FILE_A};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(FILE_A)));
    }

    @Test
    @DisplayName("Move single file to existing file with n flag")
    void run_MoveSingleFileToExistingFileWithOFlag_ThrowsMvException() throws IOException, MvException {
        // Given
        createTempFile(FILE_A, "");
        createTempFile(NO_OVERWRITE_FILE, "");
        String[] args = {"-n", FILE_A, NO_OVERWRITE_FILE};

        // When
        mvApplication.run(args, System.in, stdout);

        // Then
        assertTrue(Files.exists(IOUtils.resolveFilePath(FILE_A)));
        assertTrue(Files.exists(IOUtils.resolveFilePath(NO_OVERWRITE_FILE)));
    }

    @Test
    @DisplayName("Null args")
    void run_NullArgs_ThrowsMvException() {
        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(null, System.in, stdout));

        // Then
        assertExceptionContains(exception, ERR_NULL_ARGS);
    }

    @Test
    @DisplayName("Null stdout")
    void run_NullStdout_ThrowsMvException() {
        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(new String[]{FILE_A, FILE_B}, null, null));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }

    @Test
    @DisplayName("Insufficient args")
    void run_InsufficientArgs_ThrowsMvException() {
        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(new String[]{}, System.in, stdout));

        // Then
        assertExceptionContains(exception, ERR_MISSING_ARG);
    }

    @Test
    @DisplayName("Non-existent file arg")
    void run_MoveNonexistentFileArg_ThrowsMvException() {
        assertThrows(MvException.class, () -> mvApplication.run(new String[]{FILE_A}, System.in, stdout));
    }

    @Test
    @DisplayName("Non-existent directory to move to")
    void run_MoveToNonexistentDirectory_ThrowsMvException() throws IOException {
        // Given
        createTempFile(FILE_A, "");
        createTempFile(FILE_B, "");
        String[] args = {FILE_A, FILE_B, "nonexistentdir"};

        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(args, System.in, stdout));

        // Then
        assertExceptionContains(exception, ERR_IS_NOT_DIR);
    }

    @Test
    @DisplayName("Move directory to itself")
    void run_MoveDirectoryToItself_ThrowsMvException() throws IOException {
        // Given
        createTempDir(DEST_DIR);
        String[] args = {DEST_DIR, DEST_DIR};

        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(args, System.in, stdout));

        // Then
        assertExceptionContains(exception, ERR_FILE_EXISTS);
    }

    @Test
    @DisplayName("Invalid flags")
    void run_InvalidFlags_ThrowsMvException() {
        // Given
        String[] args = {"-a", FILE_A, FILE_B};

        // When
        MvException exception =
            assertThrows(MvException.class, () -> mvApplication.run(args, System.in, stdout));

        // Then
        assertExceptionContains(exception, "-a: " + ERR_INVALID_FLAG);
    }

    @Test
    @DisplayName("mvSrcFileToDestFile no overwrite")
    void mvSrcFileToDestFile_NoOverwrite_MovesCorrectly() throws IOException, MvException {
        // Given
        createTempFile(OLD_FILE, "");

        // When
        mvApplication.mvSrcFileToDestFile(false, OLD_FILE, NEW_FILE);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(NEW_FILE)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OLD_FILE)));
    }

    @Test
    @DisplayName("mvFilesToFolder no overwrite")
    void mvFilesToFolder_NoOverwrite_MovesCorrectly() throws IOException, MvException {
        // Given
        createTempFile(OLD_FILE, "");
        createTempDir(OLD_DIR);

        // When
        mvApplication.mvFilesToFolder(false, OLD_DIR, OLD_FILE);

        // Then
        assertTrue(Files.exists(TEMP_PATH.resolve(OLD_DIR + File.separator + OLD_FILE)));
        assertFalse(Files.exists(TEMP_PATH.resolve(OLD_FILE)));
    }
}
