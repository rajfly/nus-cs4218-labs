package sg.edu.nus.comp.cs4218.impl.app.ef2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.app.GrepApplication.IS_DIRECTORY;
import static sg.edu.nus.comp.cs4218.impl.app.GrepApplication.NULL_POINTER;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * Test class for {@code GrepApplication}
 * <br><br>
 * <p>
 *     Positive:
 * </p>
 * <p>
 *     - Empty pattern matches single file
 * </p>
 * <p>
 *     - Valid pattern matches single file
 * </p>
 * <p>
 *     - Valid pattern does not match single file with absolute path
 * </p>
 * <p>
 *     - Valid pattern matches multiple files
 * </p>
 * <p>
 *     - Valid pattern matches only one file among multiple files
 * </p>
 * <p>
 *     - Valid pattern matches stdin
 * </p>
 * <p>
 *     - Valid pattern does not match with stdin
 * </p>
 * <p>
 *     - Valid pattern and i/c/H flag with files
 * </p>
 * <p>
 *     - Valid pattern and icH flags with stdin
 * </p>
 * <p>
 *     - Valid pattern and i/c/H flag with stdin
 * </p>
 * <p>
 *     - Valid pattern and icH flags with stdin
 * </p>
 * <br>
 * <p>
 *     Negative:
 * </p>
 * <p>
 *     - Invalid flags
 * </p>
 * <p>
 *     - No files and null stdin
 * </p>
 * <p>
 *     - Null pattern
 * </p>
 * <p>
 *     - Invalid pattern with files/stdin
 * </p>
 * <p>
 *     - Stdin IOException
 * </p>
 * <p>
 *     - Provided file does not exist with files/stdin
 * </p>
 * <p>
 *     - Provided file is a directory
 * </p>
 * <p>
 *     - {@code grepFromFiles} with null arguments
 * </p>
 */
public class GrepApplicationTest {
    private static final String TEMP = "grepTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static final String TEST_DIR = "testDir";
    private static final String FILE_A = "a.txt";
    private static final String FILE_B = "b.txt";
    private static final String NONEXISTENT_FILE = "nonexistentfile.txt";
    private static final String HELLO = "hello";
    private static final String FILE_A_LINE_1 = "hello world";
    private static final String FILE_A_LINE_2 = "Hello world";
    private static final String FILE_A_CONTENT = FILE_A_LINE_1 + STRING_NEWLINE + FILE_A_LINE_2;
    private static final String FILE_B_CONTENT = "hello job";
    private static final String STDIN = "(standard input)";
    private static final String FILENAME_FORMAT = "%s: %s";
    private static GrepApplication grepApplication;
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static Boolean isInputOpen = false;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        grepApplication = new GrepApplication();
        outputStream = new ByteArrayOutputStream();
        isInputOpen = false;
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = TEMP_PATH.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.flush();
        if (isInputOpen) {
            inputStream.close();
        }
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
    @DisplayName("Empty pattern matches single file")
    void run_EmptyPatternMatchesSingleFile_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"", FILE_A};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(FILE_A_CONTENT + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern matches single file")
    void run_ValidPatternMatchesSingleFile_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {HELLO, FILE_A};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(FILE_A_LINE_1 + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern does not match single file")
    void run_ValidPatternDoesNotMatchSingleFile_ShowsCorrectLines() throws GrepException, IOException {
        // Given=
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"hell0", FILE_A};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern matches multiple files")
    void run_ValidPatternMatchesMultipleFiles_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {HELLO, FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, FILE_B_CONTENT) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern matches only one file")
    void run_ValidPatternMatchesOnlyOneFile_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"world", FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_2) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern matches stdin")
    void run_ValidPatternMatchesStdin_ShowsCorrectLines() throws IOException, GrepException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {HELLO, "-"};
        inputStream = new FileInputStream(Environment.currentDirectory + StringUtils.CHAR_FILE_SEP + FILE_A);
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(FILE_A_LINE_1 + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern does not match stdin")
    void run_ValidPatternDoesNotMatchStdin_ShowsCorrectLines() throws IOException, GrepException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"cs4218", "-"};
        inputStream = new FileInputStream(Environment.currentDirectory + StringUtils.CHAR_FILE_SEP + FILE_A);
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and i flag with files")
    void run_ValidPatternWithIFlagFiles_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-i", HELLO, FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_2) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, FILE_B_CONTENT) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and c flag with files")
    void run_ValidPatternWithCFlagFiles_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-c", "world", FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, 2) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, 0) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and H flag with files")
    void run_ValidPatternWithHFlagFiles_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-H", HELLO, FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, FILE_B_CONTENT) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and icH flags with files")
    void run_ValidPatternWithICHFlagsFiles_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-icH", HELLO, FILE_A, FILE_B};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, 2) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, 1) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and i flag with stdin")
    void run_ValidPatternWithIFlagStdin_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-i", HELLO};
        inputStream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(FILE_A_CONTENT + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and c flag with stdin")
    void run_ValidPatternWithCFlagStdin_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-c", "world", "-", FILE_B};
        inputStream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, STDIN, 2) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, 0) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and H flag with stdin")
    void run_ValidPatternWithHFlagStdin_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-H", HELLO, "-", FILE_B};
        inputStream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, STDIN, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, FILE_B_CONTENT) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Valid pattern and icH flags with stdin")
    void run_ValidPatternWithICHFlagsStdin_ShowsCorrectLines() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-icH", HELLO, "-", FILE_B};
        inputStream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        grepApplication.run(args, inputStream, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, STDIN, 2) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_B, 1) + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("Invalid flags")
    void run_InvalidFlags_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-a", FILE_A};

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, System.in, outputStream));

        // Then
        assertExceptionContains(exception, " -a: " + ERR_INVALID_FLAG);
    }

    @Test
    @DisplayName("Null stdin")
    void run_NoFilesAndNullStdin_ThrowsGrepException() {
        // Given
        String[] args = {HELLO};

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, null, outputStream));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }

    @Test
    @DisplayName("Null pattern")
    void run_NullPattern_ThrowsGrepException() {
        // Given
        String[] args = {};

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, System.in, outputStream));

        // Then
        assertExceptionContains(exception, ERR_NO_REGEX);
    }

    @Test
    @DisplayName("Invalid pattern files")
    void run_InvalidPattern_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"[", FILE_A};

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, System.in, outputStream));

        // Then
        assertExceptionContains(exception, ERR_INVALID_REGEX);
    }

    @Test
    @DisplayName("Invalid pattern stdin")
    void run_InvalidPatternStdin_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"[", "-"};
        inputStream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, inputStream, outputStream));

        // Then
        assertExceptionContains(exception, ERR_INVALID_REGEX);
    }

    @Test
    @DisplayName("Stdin IOException")
    void run_StdinIOException_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {HELLO, "-"};
        inputStream = Files.newInputStream(Path.of(Environment.currentDirectory + StringUtils.CHAR_FILE_SEP + FILE_A));
        inputStream.close();

        // When
        GrepException exception = assertThrows(GrepException.class, () -> grepApplication.run(args, inputStream, outputStream));

        // Then
        assertExceptionContains(exception, ERR_IO_EXCEPTION);
    }

    @Test
    @DisplayName("File does not exist files")
    void run_NonExistingFile_ThrowsGrepException() throws GrepException {
        // Given
        String[] args = {HELLO, NONEXISTENT_FILE};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(NONEXISTENT_FILE + ": " + ERR_FILE_NOT_FOUND + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("File does not exist stdin")
    void run_NonExistingFileStdin_ThrowsGrepException() throws GrepException {
        // Given
        String[] args = {HELLO, NONEXISTENT_FILE};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(NONEXISTENT_FILE + ": " + ERR_FILE_NOT_FOUND + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @DisplayName("File is a directory")
    void run_FileIsADirectory_ThrowsGrepException() throws GrepException, IOException {
        // Given
        createTempDir(TEST_DIR);
        String[] args = {HELLO, TEST_DIR};

        // When
        grepApplication.run(args, System.in, outputStream);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, TEST_DIR, IS_DIRECTORY) + STRING_NEWLINE,
            outputStream.toString());
    }

    @Test
    @DisplayName("grepFromFiles with null pattern")
    void grepFromFiles_NullPattern_ThrowsGrepException() {
        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> grepApplication.grepFromFiles(null, true,
                true, true, new String[]{}));

        // Then
        assertExceptionContains(exception, NULL_POINTER);
    }

    @Test
    @DisplayName("grepFromFiles with null case insensitive")
    void grepFromFiles_NullCase_ThrowsGrepException() {
        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> grepApplication.grepFromFiles("", null,
                true, true, new String[]{}));

        // Then
        assertExceptionContains(exception, NULL_POINTER);
    }

    @Test
    @DisplayName("grepFromFiles with null is count lines")
    void grepFromFiles_NullIsCountLines_ThrowsGrepException() {
        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> grepApplication.grepFromFiles("", true,
                null, true, new String[]{}));

        // Then
        assertExceptionContains(exception, NULL_POINTER);
    }

    @Test
    @DisplayName("grepFromFiles with null is prefix filename")
    void grepFromFiles_NullPrefix_ThrowsGrepException() {
        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> grepApplication.grepFromFiles("", true,
                true, null, new String[]{}));

        // Then
        assertExceptionContains(exception, NULL_POINTER);
    }

    @Test
    @DisplayName("grepFromFiles with null filenames")
    void grepFromFiles_NullFilenames_ThrowsGrepException() {
        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> grepApplication.grepFromFiles("", true,
                true, true, (String) null));

        // Then
        assertExceptionContains(exception, NULL_POINTER);
    }
}
