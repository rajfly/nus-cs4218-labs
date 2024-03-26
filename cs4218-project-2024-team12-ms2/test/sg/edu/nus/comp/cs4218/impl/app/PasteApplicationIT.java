package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

class PasteApplicationIT {
    private static final String TEST_DIRECTORY = "test/temp";
    private static final String FILE_NAME1 = "inputFile1.txt";
    private static final String FILE_NAME2 = "inputFile2.txt";
    private static final String INPUT_CONTENT1 = "Alice" + StringUtils.STRING_NEWLINE +
        "X" + StringUtils.STRING_NEWLINE +
        "Bob" + StringUtils.STRING_NEWLINE +
        "Y" + StringUtils.STRING_NEWLINE +
        "Charles" + StringUtils.STRING_NEWLINE +
        "Z";
    private static final String INPUT_CONTENT2 = "Dave" + StringUtils.STRING_NEWLINE +
        "A" + StringUtils.STRING_NEWLINE +
        "Eve" + StringUtils.STRING_NEWLINE +
        "B" + StringUtils.STRING_NEWLINE +
        "Frank" + StringUtils.STRING_NEWLINE +
        "C";
    private static final String PATH_TO_FILE1 = TEST_DIRECTORY + File.separator + FILE_NAME1;
    private static final String PATH_TO_FILE2 = TEST_DIRECTORY + File.separator + FILE_NAME2;

    private final PasteApplication app = new PasteApplication();
    private InputStream stdin;
    private OutputStream stdout;

    @BeforeEach
    void setUp() throws IOException {
        stdin = new ByteArrayInputStream(INPUT_CONTENT1.getBytes(StandardCharsets.UTF_8));
        stdout = new ByteArrayOutputStream();

        FileUtils.createDirectory(TEST_DIRECTORY);
        FileUtils.createFile(PATH_TO_FILE1);
        FileUtils.writeFileContent(PATH_TO_FILE1, INPUT_CONTENT1);

        FileUtils.createFile(PATH_TO_FILE2);
        FileUtils.writeFileContent(PATH_TO_FILE2, INPUT_CONTENT2);
    }

    @AfterEach
    void tearDownAll() {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @Test
    void run_NoStdOut_ShouldThrowException() {
        String[] args = {};
        Exception thrown = assertThrows(Exception.class, () -> app.run(args, stdin, null));
        assertEquals("paste: " + ERR_NO_OSTREAM, thrown.getMessage());
    }

    @Test
    void run_NullArgs_ShouldThrowException() {
        Exception thrown = assertThrows(Exception.class, () -> app.run(null, stdin, stdout));
        assertEquals("paste: " + ERR_NULL_ARGS, thrown.getMessage());
    }
    @Test
    void run_NoFlagEmptyArgsValidInputAndOutputStream_ShouldPasteOnStdOut() throws Exception {
        String[] args = {};
        app.run(args, stdin, stdout);
        assertEquals("Alice" + StringUtils.STRING_NEWLINE +
            "X" + StringUtils.STRING_NEWLINE +
            "Bob" + StringUtils.STRING_NEWLINE +
            "Y" + StringUtils.STRING_NEWLINE +
            "Charles" + StringUtils.STRING_NEWLINE +
            "Z" + StringUtils.STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_SFlagValidArgsValidInputAndOutputStream_ShouldPasteOnSingleLineOnStdOut() throws Exception {
        String[] args = {"-s"};
        app.run(args, stdin, stdout);
        assertEquals("Alice\tX\tBob\tY\tCharles\tZ" + StringUtils.STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_NoFlagValidArgsValidInputAndOutPutStream_ShouldFormatOutputInMultipleColumns() throws Exception {
        String[] args = {"-", "-"};
        stdin = new ByteArrayInputStream("Alice\nBob\nCharles".getBytes(StandardCharsets.UTF_8));
        app.run(args, stdin, stdout);
        assertEquals("Alice\tBob" + StringUtils.STRING_NEWLINE +
            "Charles" + StringUtils.STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_NoFlagMergeStdinAndFile_ShouldMergeCorrectly() throws Exception {
        String[] args = {"-", PATH_TO_FILE2};
        app.run(args, stdin, stdout);
        assertEquals("Alice\tDave" + StringUtils.STRING_NEWLINE +
            "X\tA" + StringUtils.STRING_NEWLINE +
            "Bob\tEve" + StringUtils.STRING_NEWLINE +
            "Y\tB" + StringUtils.STRING_NEWLINE +
            "Charles\tFrank" + StringUtils.STRING_NEWLINE +
            "Z\tC" + StringUtils.STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_NoFlagMergeFileAndStdin_ShouldMergeCorrectly() throws Exception {
        String[] args = {"-s", PATH_TO_FILE2, "-"};
        app.run(args, stdin, stdout);
        assertEquals("Dave\tA\tEve\tB\tFrank\tC" + StringUtils.STRING_NEWLINE +
            "Alice\tX\tBob\tY\tCharles\tZ" + StringUtils.STRING_NEWLINE, stdout.toString());
    }
}