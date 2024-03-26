package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.*;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

public class UniqApplicationIT {
    private static final String FILE_CONTENT = "a" + StringUtils.STRING_NEWLINE +
        "b" + StringUtils.STRING_NEWLINE +
        "b" + StringUtils.STRING_NEWLINE +
        "c" + StringUtils.STRING_NEWLINE +
        "c" + StringUtils.STRING_NEWLINE +
        "c";
    private static final String TEST_DIRECTORY = "test/temp";
    private static final String INPUTFILE_NAME = "inputFile.txt";
    private static final String PATH_TO_INPUTFILE = TEST_DIRECTORY + File.separator + INPUTFILE_NAME;

    private UniqApplication app;
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeAll
    static void setUpAll() throws Exception {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            // Ignore exception
        }
        FileUtils.createDirectory(TEST_DIRECTORY);
        FileUtils.writeFileContent(PATH_TO_INPUTFILE, FILE_CONTENT);
    }
    @BeforeEach
    void setUp() throws IOException {
        app = new UniqApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
    }
    @AfterAll
    static void tearDownAll() {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Environment.currentDirectory = System.getProperty("user.dir");
    }
    @Test
    void run_NullArgs_ShouldThrowException() {
        assertThrows(UniqException.class, () -> app.run(null, null, null));
    }

    @Test
    void run_NullOStreamArgs_ShouldThrowException() {
        assertThrows(UniqException.class, () -> app.run(new String[0], this.inputStream, null));
    }

    @Test
    void run_InvalidFlag_ShouldThrowException() {
        String[] args = new String[]{"-z"};
        assertThrows(UniqException.class, () -> app.run(args, this.inputStream, this.outputStream));
    }
    @Test
    void run_MoreThanTwoFiles_ShouldThrowException() {
        String[] args = new String[] {FILE_CONTENT, FILE_CONTENT, FILE_CONTENT};
        assertThrows(UniqException.class, () -> app.run(args, this.inputStream, this.outputStream));
    }
    @Test
    void run_ValidStdinUniqueLines_ShouldFilterSuccessfully() {
        String inputData = "a" + StringUtils.STRING_NEWLINE +
            "a" + StringUtils.STRING_NEWLINE +
            "b" + StringUtils.STRING_NEWLINE +
            "b" + StringUtils.STRING_NEWLINE +
            "c" + StringUtils.STRING_NEWLINE +
            "c";
        inputStream = new ByteArrayInputStream(inputData.getBytes());
        String[] args = {};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals("a" + StringUtils.STRING_NEWLINE +
            "b" + StringUtils.STRING_NEWLINE +
            "c" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }
    @Test
    void run_ValidStdinCountLines_ShouldCountSuccessfully() {
        String inputData = "hello" + StringUtils.STRING_NEWLINE +
            "hello" + StringUtils.STRING_NEWLINE +
            "world";
        inputStream = new ByteArrayInputStream(inputData.getBytes());
        String[] args = {"-c"};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals("\t2 hello" + StringUtils.STRING_NEWLINE +
            "\t1 world" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }
    @Test
    void run_ValidStdinDuplicateLines_ShouldDisplayDuplicatesOnly() {
        String inputData = "alpha" + StringUtils.STRING_NEWLINE +
            "alpha" + StringUtils.STRING_NEWLINE +
            "beta" + StringUtils.STRING_NEWLINE +
            "gamma" + StringUtils.STRING_NEWLINE +
            "gamma";
        inputStream = new ByteArrayInputStream(inputData.getBytes());
        String[] args = {"-d"};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals("alpha" + StringUtils.STRING_NEWLINE +
            "gamma" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }
    @Test
    void run_ValidStdinAllDuplicateLines_ShouldDisplayAllDuplicates() {
        final String one = "one";
        final String two = "two";
        final String three = "three";

        String inputData = String.join(StringUtils.STRING_NEWLINE, one, two, two, three, three);
        inputStream = new ByteArrayInputStream(inputData.getBytes());
        String[] args = {"-D"};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals(two + StringUtils.STRING_NEWLINE +
            two + StringUtils.STRING_NEWLINE +
            three + StringUtils.STRING_NEWLINE +
            three + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    void run_ValidStdinCombineFlags_ShouldWorkCorrectly() {
        inputStream = new ByteArrayInputStream(FILE_CONTENT.getBytes());
        String[] args = {"-c", "-d"};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals("\t2 b" + StringUtils.STRING_NEWLINE +
            "\t3 c" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }
    @Test
    void run_ValidFileInputUniqueLines_ShouldFilterSuccessfully() {
        String[] args = {PATH_TO_INPUTFILE};
        assertDoesNotThrow(() -> app.run(args, inputStream, outputStream));
        assertEquals("a" + StringUtils.STRING_NEWLINE +
            "b" + StringUtils.STRING_NEWLINE +
            "c" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }
}