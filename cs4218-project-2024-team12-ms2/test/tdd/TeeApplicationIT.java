package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_ILLEGAL_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.app.TeeApplication;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

@SuppressWarnings({"PMD.LongVariable"})
public class TeeApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private static final String TEMP_FOLDER = "test/temp";
    private static final String FILE1_CONTENT = "line 1 file1" + STRING_NEWLINE + "line 2 file1";
    private static final String FILE2_CONTENT = "line 1 file2.txt";
    private static final String STD_INPUT = "User input 1" + STRING_NEWLINE + "User input 2";
    private static final String FILE1_NAME = "file1.txt";
    private static final String FILE2_NAME = "file2.txt";
    private static final String FILE1_PATH = TEMP_FOLDER + File.separator + FILE1_NAME;
    private static final String FILE2_PATH = TEMP_FOLDER + File.separator + FILE2_NAME;

    private TeeApplication app;
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() throws Exception {
        FileUtils.createDirectory(TEMP_FOLDER);
        FileUtils.createFile(FILE1_PATH);
        FileUtils.writeFileContent(FILE1_PATH, FILE1_CONTENT);
        FileUtils.createFile(FILE2_PATH);
        FileUtils.writeFileContent(FILE2_PATH, FILE2_CONTENT);

        inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        outputStream = new ByteArrayOutputStream();
        app = new TeeApplication();
    }

    @AfterEach
    public void tearDownAll() {
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_FOLDER));
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
        Environment.currentDirectory = System.getProperty("user.dir");

    }

    /**
     * Asserts that the content of the files specified by {@code FILE1_PATH} and {@code FILE2_PATH} have not changed.
     *
     * @throws Exception If an error occurs while reading the file content or if the file content does not match the expected content.
     */
    private void assertFilesNotChanged() throws Exception {
        String file1ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE1_PATH)) + STRING_NEWLINE;
        String file2ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE2_PATH)) + STRING_NEWLINE;

        // Adjusting for files that do not end with a newline.
        if (!FILE1_CONTENT.endsWith(STRING_NEWLINE)) {
            file1ActualContent = file1ActualContent.trim();
        }

        if (!FILE2_CONTENT.endsWith(STRING_NEWLINE)) {
            file2ActualContent = file2ActualContent.trim();
        }

        assertEquals(FILE1_CONTENT, file1ActualContent);
        assertEquals(FILE2_CONTENT, file2ActualContent);
    }

    /**
     * run Tests
     */
    @Test
    void run_NullArgs_ShouldThrowTeeException() {
        TeeException exception = assertThrows(TeeException.class, () -> app.run(null, null, null));
        assertEquals("tee: " + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_NullOStream_ShouldThrowTeeException() {
        String[] args = {};
        TeeException exception = assertThrows(TeeException.class, () -> app.run(args, null, null));
        assertEquals("tee: " + ERR_NO_OSTREAM, exception.getMessage());
    }

    @Test
    void run_FlagInvalid_ShouldThrowTeeException() {
        String[] args = new String[]{"-t"};
        TeeException exception = assertThrows(TeeException.class, () -> app.run(args, this.inputStream, this.outputStream));
        assertEquals(String.format("tee: " + ERR_ILLEGAL_FLAG + 't'), exception.getMessage());
    }

    @Test
    void run_NoFlagNoFiles_ShouldWriteToStdOut() throws Exception {
        TeeApplication app = spy(new TeeApplication());

        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String[] args = {};
        app.run(args, inputStream, outputStream);

        verify(app, times(1)).teeFromStdin(false, inputStream);
        String expectedOutput = STD_INPUT + STRING_NEWLINE;
        assertEquals(expectedOutput, outputStream.toString());
        assertFilesNotChanged();
    }

    @Test
    void run_AFlagWithFile_ShouldWriteToStdOutAndFile() throws Exception {
        TeeApplication app = spy(new TeeApplication());

        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String[] args = new String[]{FILE1_PATH};
        app.run(args, inputStream, outputStream);

        verify(app, times(1)).teeFromStdin(false, inputStream, FILE1_PATH);
        assertEquals(STD_INPUT + STRING_NEWLINE, outputStream.toString());

        String file1ActualContent = FileUtils.filterAndJoinLines(FileUtils.readFileLines(FILE1_PATH));
        String file2ActualContent = FileUtils.filterAndJoinLines(FileUtils.readFileLines(FILE2_PATH));

        assertEquals(STD_INPUT, file1ActualContent);
        assertEquals(FILE2_CONTENT, file2ActualContent);
    }

    @Test
    void run_AFlagWithTwoFiles_ShouldWriteToStdOutAndAppendFiles() throws Exception {
        TeeApplication app = spy(new TeeApplication());

        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String[] args = new String[]{"-a", FILE1_PATH, FILE2_PATH};
        app.run(args, inputStream, outputStream);

        verify(app, times(1)).teeFromStdin(true, inputStream, FILE1_PATH, FILE2_PATH);

        // Use the utility method to clean up and join file content for comparison
        String file1ActualContent = FileUtils.filterAndJoinLines(FileUtils.readFileLines(FILE1_PATH));
        String file2ActualContent = FileUtils.filterAndJoinLines(FileUtils.readFileLines(FILE2_PATH));

        String expectedFile1Content = (FILE1_CONTENT + STRING_NEWLINE + STD_INPUT).trim();
        String expectedFile2Content = (FILE2_CONTENT + STRING_NEWLINE + STD_INPUT).trim();

        assertEquals(expectedFile1Content, file1ActualContent);
        assertEquals(expectedFile2Content, file2ActualContent);
    }

}
