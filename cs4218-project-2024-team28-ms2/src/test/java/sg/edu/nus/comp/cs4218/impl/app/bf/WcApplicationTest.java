package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.condition.OS.WINDOWS;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * Test class for {@code WcApplication}
 * <br><br>
 * Positive:
 * <p>
 *     - Single file with no flags
 * </p>
 * <p>
 *     - Single file with c/l/w flag
 * </p>
 * <p>
 *     - Single file with clw flags
 * </p>
 * <p>
 *     - Stdin no dash no flags
 * </p>
 * <p>
 *     - Stdin with dash no flags
 * </p>
 * <p>
 *     - Stdin with dash c/l/w flag
 * </p>
 * <p>
 *     - Stdin with dash clw flags
 * </p>
 * <p>
 *     - Multiple files with and without clw flags
 * </p>
 * <p>
 *     - Stdin with multiple files and clw flags
 * </p>
 * <br>
 * <p>
 *     Negative:
 * </p>
 * <p>
 *     - Null stdin
 * </p>
 * <p>
 *     - Null stdout
 * </p>
 * <p>
 *     - Null args
 * </p>
 * <p>
 *     - Invalid flags
 * </p>
 * <p>
 *     - Stdout exception
 * </p>
 * <p>
 *     - Non existent file
 * </p>
 * <p>
 *     - Directory arg
 * </p>
 * <p>
 *     - Unreadable file
 * </p>
 * <p>
 *     - countFromFiles with null files
 * </p>
 * <p>
 *     - getCountReport with null stdin
 * </p>
 * <p>
 *     - countFromStdin with null stdin
 * </p>
 */
public class WcApplicationTest {
    private static final String TEMP_DIR = "wcTestFolder";
    private static final Path TEMP_PATH = Paths.get(TEMP_DIR);
    private static final String FILE_A = "a.txt";
    private static final String FILE_B = "b.txt";
    private static final String FILE_A_CONTENT = "Hello World!";
    private static final String FILE_B_CONTENT = "World Hello!" + STRING_NEWLINE;
    private static final String STDIN = "-";
    private static final String SINGLE_FORMAT = CHAR_TAB + "%s ";
    private static final String TRIPLE_FORMAT = CHAR_TAB + "%s" + CHAR_TAB + "%s" + CHAR_TAB + "%s ";
    private static final String TOTAL = "total";
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private static WcApplication wcApplication;
    private static InputStream inputstream;
    private static OutputStream outputstream;
    private static Boolean isInputOpen;
    private static final Deque<Path> FILES = new ArrayDeque<>();

    @BeforeEach
    void setUp() throws IOException {
        wcApplication = new WcApplication();
        outputstream = new ByteArrayOutputStream();
        isInputOpen = false;
        Files.createDirectory(TEMP_PATH);
        Environment.currentDirectory = TEMP_PATH.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        outputstream.flush();
        if (isInputOpen) {
            inputstream.close();
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

    private void createTempUnreadableFile(String name, String content) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        Files.write(path, content.getBytes());
        File unreadableFile = path.toFile();
        unreadableFile.setReadable(false);
        FILES.push(path);
    }

    @Test
    @DisplayName("File no flags")
    void run_FileArg_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {FILE_A};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + FILE_A;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString()); // NOPMD
    }

    @Test
    @DisplayName("File c flag")
    void run_FileArgWithCFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-c", FILE_A};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 12) + FILE_A;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("File l flag")
    void run_FileArgWithLFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-l", FILE_A};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 0) + FILE_A;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("File w flag")
    void run_FileArgWithWFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-w", FILE_A};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 2) + FILE_A;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("File clw flags")
    void run_FileArgWithCLWFlags_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-c", "-l", "-w", FILE_A};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + FILE_A;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString()); // NOPMD
    }

    @Test
    @DisplayName("Stdin no dash no flags")
    void run_StdinNoDashNoFlags_ShowsCorrectCount() throws IOException, WcException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with dash no flags")
    void run_StdinWithDashNoFlags_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-"};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with dash c flag")
    void run_StdinWithDashCFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-c", "-"};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 12) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with dash l flag")
    void run_StdinWithDashLFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-l", "-"};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 0) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with dash w flag")
    void run_StdinWithDashWFlag_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-w", "-"};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(SINGLE_FORMAT, 2) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with dash clw flags")
    void run_StdinWithDashCLWFlags_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-clw", "-"};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + STDIN;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Multiple files")
    void run_MultipleFiles_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {FILE_A, FILE_B};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + FILE_A + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 2, 14) + FILE_B + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 4, 26) + TOTAL;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Multiple files with clw flags")
    void run_MultipleFilesWithCLFlags_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-clw", FILE_A, FILE_B};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + FILE_A + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 2, 14) + FILE_B + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 4, 26) + TOTAL;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Stdin with multiple files and clw flags")
    void run_StdinWithMultipleFiles_ShowsCorrectCount() throws WcException, IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        createTempFile(FILE_B, FILE_B_CONTENT);
        String[] args = {"-clw", "-", "-", FILE_B};
        inputstream = new FileInputStream(TEMP_PATH.resolve(FILE_A).toFile());
        isInputOpen = true;

        // When
        wcApplication.run(args, inputstream, outputstream);

        // Then
        String expected = String.format(TRIPLE_FORMAT, 0, 2, 12) + STDIN + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 0, 0, 0) + STDIN + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 2, 14) + FILE_B + STRING_NEWLINE
            + String.format(TRIPLE_FORMAT, 1, 4, 26) + TOTAL;
        assertEquals(expected + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Null stdin")
    void run_NullStdin_ThrowsWcException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {"-", FILE_A};

        // When
        WcException exception =
            assertThrows(WcException.class, () -> wcApplication.run(args, null, outputstream));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }

    @Test
    @DisplayName("Null stdout")
    void run_NullStdout_ThrowsWcException() {
        // When
        WcException exception =
            assertThrows(WcException.class, () -> wcApplication.run(null, System.in, null));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }

    @Test
    @DisplayName("Null args")
    void run_NullArgs_ThrowsWcException() {
        // When
        WcException exception =
            assertThrows(WcException.class, () -> wcApplication.run(null, System.in, outputstream));

        // Then
        assertExceptionContains(exception, ERR_NULL_ARGS);
    }

    @Test
    @DisplayName("Invalid flags")
    void run_InvalidFlags_ThrowsWcException() {
        // Given
        String[] args = {"-a"};

        // When
        WcException exception =
            assertThrows(WcException.class, () -> wcApplication.run(args, System.in, outputstream));

        // Then
        assertExceptionContains(exception, " -a: " + ERR_INVALID_FLAG);
    }

    @Test
    @DisplayName("Stdout Exception")
    void run_OutputStreamException_ThrowsWcException() throws IOException {
        // Given
        createTempFile(FILE_A, FILE_A_CONTENT);
        String[] args = {FILE_A};

        try (OutputStream output = new PipedOutputStream()) {
            // When
            WcException exception = assertThrows(WcException.class, () -> wcApplication.run(args, System.in, output));

            // Then
            assertExceptionContains(exception, ERR_WRITE_STREAM);
        }
    }

    @Test
    @DisplayName("Non existent file")
    void run_NonExistentFile_ThrowsWcException() throws WcException {
        // Given
        String[] args = {"nonexistentfile.txt"};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        assertEquals("wc: " + ERR_FILE_NOT_FOUND + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Directory arg")
    void run_DirectoryArg_ThrowsWcException() throws WcException, IOException {
        // Given
        createTempDir("testDir");
        String[] args = {"testDir"};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        assertEquals("wc: " + ERR_IS_DIR + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("Unreadable file")
    @DisabledOnOs(WINDOWS)
    void run_UnreadableFile_ShowsCorrectError() throws WcException, IOException {
        // Given
        createTempUnreadableFile("unreadable.txt", "example");
        String[] args = {"unreadable.txt"};

        // When
        wcApplication.run(args, System.in, outputstream);

        // Then
        assertEquals("wc: " + ERR_NO_PERM + STRING_NEWLINE, outputstream.toString());
    }

    @Test
    @DisplayName("countFromFiles with null files")
    void countFromFiles_NullFiles_ThrowsWcException() {
        // When
        WcException exception =
            assertThrows(WcException.class,
                () -> wcApplication.countFromFiles(null, null, null, (String[]) null));

        // Then
        assertExceptionContains(exception, ERR_NULL_ARGS);
    }

    @Test
    @DisplayName("countFromStdin with null stdin")
    void countFromStdin_NullStdin_ThrowsWcException() {
        // When
        WcException exception = assertThrows(WcException.class,
            () -> wcApplication.countFromStdin(true, true, true, null));

        // Then
        assertExceptionContains(exception, ERR_NULL_STREAMS);
    }
}
