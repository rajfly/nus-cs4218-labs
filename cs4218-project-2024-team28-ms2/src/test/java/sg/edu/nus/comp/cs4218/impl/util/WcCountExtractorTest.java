package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

public class WcCountExtractorTest {
    private static final String TEMP_DIR = "wcCountExtractorTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP_DIR);
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static final String FILE_A = "a.txt";
    private static final String FILE_A_CONTENT = "Hello world!\nWorld Hello!\n";
    private static WcCountExtractor wcCountExtractor;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        wcCountExtractor = new WcCountExtractor();
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

    @Test
    @DisplayName("Count lines, words and bytes")
    void getCountReport_NormalFile_ReportsCorrectly() throws IOException, WcException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);

        // When
        try (InputStream inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile())) {
            // Then
            assertDoesNotThrow(
                () -> {
                    long[] report = wcCountExtractor.getCountReport(inputstream);
                    assertEquals(2, report[0]);
                    assertEquals(4, report[1]);
                    assertEquals(26, report[2]);
                }
            );
        }
    }

    @Test
    @DisplayName("Null stdin")
    void getCountReport_NullStdin_ThrowsWcException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);

        // When
        WcException exception = assertThrows(WcException.class, () -> wcCountExtractor.getCountReport(null));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }
}
