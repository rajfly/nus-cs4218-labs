package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * Test class for {@code GrepFileResultsExtractor}
 * <br><br>
 * <p>
 *     Positive:
 * </p>
 * <p>
 *     - Valid pattern no flags
 * </p>
 * <p>
 *     - Valid pattern case insensitive
 * </p>
 * <p>
 *     - Valid pattern prefix filename
 * </p>
 * <p>
 *     - Valid pattern, case insensitive and prefix filename
 * </p>
 * <br>
 * <p>
 *     Negative:
 * </p>
 * <p>
 *     - Invalid pattern
 * </p>
 * <p>
 *     - Null args
 * </p>
 */
public class GrepFileResultsExtractorTest {
    private static final String TEMP_DIR = "grepExtractorTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP_DIR);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static final String FILE_A = "a.txt";
    private static final String FILE_A_LINE_1 = "hello world";
    private static final String FILE_A_LINE_2 = "Hello world";
    private static final String FILE_A_CONTENT = FILE_A_LINE_1 + STRING_NEWLINE + FILE_A_LINE_2;
    private static final String HELLO = "hello";
    private static final String FILENAME_FORMAT = "%s: %s";
    private static StringJoiner lineResults;
    private static StringJoiner countResults;
    private static GrepFileResultsExtractor extractor;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        extractor = new GrepFileResultsExtractor();
        lineResults = new StringJoiner(STRING_NEWLINE);
        countResults = new StringJoiner(STRING_NEWLINE);
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
    @DisplayName("Valid pattern no flags")
    void grepResultsFromFiles_ValidPattern_GeneratesCorrectResults() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        extractor.grepResultsFromFiles(HELLO, false, false,
            lineResults, countResults, filenames);

        // Then
        assertEquals(FILE_A_LINE_1, lineResults.toString());
        assertEquals("1", countResults.toString());
    }

    @Test
    @DisplayName("Valid pattern and case insensitive")
    void grepResultsFromFiles_ValidPatternCase_GeneratesCorrectResults() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        extractor.grepResultsFromFiles(HELLO, true, false,
            lineResults, countResults, filenames);

        // Then
        assertEquals(FILE_A_CONTENT, lineResults.toString());
        assertEquals("2", countResults.toString());
    }

    @Test
    @DisplayName("Valid pattern and prefix filename")
    void grepResultsFromFiles_ValidPrefix_GeneratesCorrectResults() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        extractor.grepResultsFromFiles(HELLO, false, true,
            lineResults, countResults, filenames);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1), lineResults.toString());
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, 1), countResults.toString());
    }

    @Test
    @DisplayName("Valid pattern, case insensitive and prefix filename")
    void grepResultsFromFiles_ValidPatternCasePrefix_GeneratesCorrectResults() throws GrepException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        extractor.grepResultsFromFiles(HELLO, true, true,
            lineResults, countResults, filenames);

        // Then
        assertEquals(String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_1) + STRING_NEWLINE
            + String.format(FILENAME_FORMAT, FILE_A, FILE_A_LINE_2), lineResults.toString());
        assertEquals(FILE_A + ": 2", countResults.toString());
    }

    @Test
    @DisplayName("Invalid pattern")
    void grepResultsFromFiles_InvalidPattern_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> extractor.grepResultsFromFiles("[", true, true,
                lineResults, countResults, filenames));

        // Then
        assertExceptionContains(exception, ERR_INVALID_REGEX);
    }

    @Test
    @DisplayName("Null args")
    void grepResultsFromFiles_NullArgs_ThrowsGrepException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] filenames = {FILE_A};

        // When
        GrepException exception = assertThrows(GrepException.class,
            () -> extractor.grepResultsFromFiles(HELLO, null, null,
                lineResults, countResults, filenames));

        // Then
        assertExceptionContains(exception, ERR_NULL_ARGS);
    }
}
