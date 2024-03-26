package tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.app.TeeApplication;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

@SuppressWarnings({"PMD.LongVariable"})
public class TeeApplicationTest {
    private static final String TEMP_FOLDER = "test" + fileSeparator() + "temp";
    private static final String FILE1_NAME = "file1.txt";
    private static final String FILE2_NAME = "file2.txt";
    private static final String FILE1_PATH = TEMP_FOLDER + File.separator + FILE1_NAME;
    private static final String FILE2_PATH = TEMP_FOLDER + File.separator + FILE2_NAME;
    private static final String FILE1_CONTENT = "Initial content for file1.txt";
    private static final String FILE2_CONTENT = "Initial content for file2.txt" + STRING_NEWLINE;
    private static final String STD_INPUT = "User input";
    private static final String FILE_NOT_EXIST_PATH = TEMP_FOLDER + File.separator + "nonExistentFile.txt";

    private TeeApplication app;
    private InputStream inputStream;

    @BeforeEach
    void setUp() throws Exception {
        FileUtils.createDirectory(TEMP_FOLDER);
        FileUtils.createFile(FILE1_PATH);
        FileUtils.writeFileContent(FILE1_PATH, FILE1_CONTENT);
        FileUtils.createFile(FILE2_PATH);
        FileUtils.writeFileContent(FILE2_PATH, FILE2_CONTENT);

        inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
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

    @Test
    void teeFromStdin_NoFlagWithStdInWithNoFile_ShouldWriteToStdOutOnly() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(false, this.inputStream);
        assertEquals(output, STD_INPUT);
        assertFilesNotChanged();
    }

    @Test
    void teeFromStdin_NoFlagWithStdInWithFile_ShouldWriteToStdOutAndFile() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(false, this.inputStream, FILE1_PATH);
        assertEquals(output, STD_INPUT);

        String file1ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE1_PATH)).trim();
        assertEquals(STD_INPUT, file1ActualContent);
    }

    @Test
    void teeFromStdin_NoFlagWithStdInWithTwoFiles_ShouldWriteToStdOutAndTwoFiles() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(false, this.inputStream, FILE1_PATH, FILE2_PATH);
        assertEquals(output, STD_INPUT);

        String file1ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE1_PATH)).trim();
        String file2ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE2_PATH)).trim();

        assertEquals(STD_INPUT, file1ActualContent);
        assertEquals(STD_INPUT, file2ActualContent);
    }

    @Test
    void teeFromStdin_NoFlagWithStdInWithNonExistentFile_ShouldWriteToStdOutAndCreateAndWriteToNewFile() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(false, this.inputStream, FILE_NOT_EXIST_PATH);
        assertEquals(output, STD_INPUT);

        String nonExistentFileActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE_NOT_EXIST_PATH)).trim();
        assertEquals(STD_INPUT, nonExistentFileActualContent);
    }
    @Test
    void teeFromStdin_AFlagWithStdInWithNonExistentFile_ShouldWriteToStdOutAndCreateAndAppendToNewFile() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(true, this.inputStream, FILE_NOT_EXIST_PATH);
        assertEquals(output, STD_INPUT);

        String nonExistentFileActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE_NOT_EXIST_PATH)).trim();
        assertEquals(STD_INPUT, nonExistentFileActualContent);
    }

    @Test
    void teeFromStdin_AFlagWithStdInWithFileEndingWithNoNewLine_ShouldWriteToStdOutAndAppendFileOnNewLine() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(true, this.inputStream, FILE1_PATH);
        assertEquals(output, STD_INPUT);

        String file1ActualContent = String.join(STRING_NEWLINE, FileUtils.readFileLines(FILE1_PATH)).trim();
        String expectedOutput = FILE1_CONTENT + STRING_NEWLINE + STD_INPUT;
        assertEquals(expectedOutput, file1ActualContent);
    }

    @Test
    void teeFromStdin_AFlagWithStdInWithFileEndingWithNewLine_ShouldWriteToStdOutAndAppendFileOnNewLine() throws Exception {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String output = app.teeFromStdin(true, this.inputStream, FILE2_PATH);
        assertEquals(output, STD_INPUT);

        String file2ActualContent = FileUtils.filterAndJoinLines(FileUtils.readFileLines(FILE2_PATH));
        String expectedOutput = FILE2_CONTENT + STD_INPUT;
        assertEquals(expectedOutput, file2ActualContent);
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void teeFromStdin_NoFlagWithStdInWithFileWithNonExistentParentDir_ShouldThrowTeeException() {
        this.inputStream = new ByteArrayInputStream(STD_INPUT.getBytes());
        String path = "nonExistentParentDirectory" + fileSeparator() + FILE1_NAME;

        TeeException exception = assertThrows(TeeException.class, () -> app.teeFromStdin(false, this.inputStream, path));
        assertEquals("tee: " + path + ": " + ERR_FILE_NOT_FOUND, exception.getMessage());
    }
}
