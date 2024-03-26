package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_ILLEGAL_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.IOException;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.junit.jupiter.api.*;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

public class UniqApplicationTest {
    private static final String STRING1 = "Alice";
    private static final String STRING2 = "Bob";
    private static final String STRING3 = "Charles";
    private static final String STRING_EMPTY = " ";
    private static final String TEST_DIRECTORY = "test/temp";
    private static final String INPUTFILE_NAME = "input_for_uniq.txt";
    private static final String OUTPUTFILE_NAME = "output_for_uniq.txt";
    private static final String EMPTYFILE_NAME = "emptyFile_for_uniq.txt";
    private static final String PATH_INPUTFILE = TEST_DIRECTORY + File.separator + INPUTFILE_NAME;
    private static final String PATH_OUTPUTFILE = TEST_DIRECTORY + File.separator + OUTPUTFILE_NAME;
    private static final String PATH_EMPTYFILE = TEST_DIRECTORY + File.separator + EMPTYFILE_NAME;
    private static final String
        INPUTFILE_CONTENT = String.join(STRING_NEWLINE, STRING1, STRING1, STRING1, STRING2, STRING2,
        STRING_EMPTY, STRING_EMPTY, STRING_EMPTY, STRING2, STRING3, STRING3);
    private UniqApplication app;
    private InputStream inputStream;

    @BeforeAll
    static void setUpAll() throws Exception {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            // Ignore exception
        }
        FileUtils.createDirectory(TEST_DIRECTORY);
        FileUtils.writeFileContent(PATH_INPUTFILE, INPUTFILE_CONTENT);
        FileUtils.createFile(PATH_EMPTYFILE);
    }

    @AfterAll
    static void tearDownAll() {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            // Ignore exception
        }
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @BeforeEach
    void setUp() throws IOException {
        app = new UniqApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            // Ignore exception
        }
        FileUtils.createDirectory(TEST_DIRECTORY);
        FileUtils.writeFileContent(PATH_INPUTFILE, INPUTFILE_CONTENT);
        FileUtils.createFile(PATH_EMPTYFILE);
    }

    @AfterEach
    void tearDown() {
        new File(PATH_OUTPUTFILE).delete();
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY));
        } catch (Exception e) {
            // Ignore exception
        }
    }

    @Test
    public void uniqFromFile_NoFlagWithEmptyFileInputWithNoOutputFile_ShouldThrowException() throws Exception {
        String output = app.uniqFromFile(false, false, false, PATH_EMPTYFILE, null);

        assertEquals("", output);
    }

    @Test
    public void uniqFromFile_CountAndAllRepeatedFlags_ShouldThrowException() {
        // Setting up a scenario where both isCount and isAllRepeated flags are true
        String inputContent = String.join(STRING_NEWLINE, STRING1, STRING1, STRING2, STRING3, STRING3);
        try {
            FileUtils.writeFileContent(PATH_INPUTFILE, inputContent);
        } catch (Exception e) {
            fail("Setup failed with exception: " + e.getMessage());
        }

        // Attempting to run uniqFromFile with conflicting flags should throw a UniqException
        UniqException thrownException = assertThrows(UniqException.class, () -> {
            app.uniqFromFile(true, false, true, PATH_INPUTFILE, null);
        }, "UniqException was expected due to conflicting flags but was not thrown.");

        // Verifying that the thrown exception has the expected message
        assertEquals("uniq: " + ERR_ILLEGAL_FLAG, thrownException.getMessage(), "The error message should indicate the use of illegal flags.");
    }


    @Test
    public void uniqFromFile_NoFlagWithInputFileWithOutputFile_ShouldRemoveAdjacentDuplicateLinesToOutputFile() throws Exception {
        app.uniqFromFile(false, false, false, PATH_INPUTFILE, PATH_OUTPUTFILE);
        List<String> unformattedOutput = FileUtils.readFileContent(PATH_OUTPUTFILE);
        String actualOutput = FileUtils.formatListOfStringToMultilineString(unformattedOutput);
        String expected = String.join(STRING_NEWLINE, STRING1, STRING2, STRING_EMPTY, STRING2, STRING3);

        assertEquals(expected, actualOutput);
    }

    @Test
    public void uniqFromFile_CFlagWithInputFileWithOutputFile_ShouldReturnNumberOfAdjacentDuplicateLinesToOutputFile() throws Exception {
        app.uniqFromFile(true, false, false, PATH_INPUTFILE, PATH_OUTPUTFILE);
        List<String> unformattedOutput = FileUtils.readFileContent(PATH_OUTPUTFILE);
        String actualOutput = FileUtils.formatListOfStringToMultilineString(unformattedOutput);
        String expected = String.join(STRING_NEWLINE, CHAR_TAB + "3 " + STRING1, CHAR_TAB + "2 " + STRING2,
            CHAR_TAB + "3 " + STRING_EMPTY, CHAR_TAB + "1 " + STRING2, CHAR_TAB + "2 " + STRING3);

        assertEquals(expected, actualOutput);
    }

    @Test
    public void uniqFromFile_SmallDFlagWithInputFileWithOutputFile_ShouldReturnOneDuplicateLinePerGroupToOutputFile() throws Exception {
        app.uniqFromFile(false, true, false, PATH_INPUTFILE, PATH_OUTPUTFILE);
        List<String> unformattedOutput = FileUtils.readFileContent(PATH_OUTPUTFILE);
        String actualOutput = FileUtils.formatListOfStringToMultilineString(unformattedOutput);
        String expected = String.join(STRING_NEWLINE, STRING1, STRING2, STRING_EMPTY, STRING3);

        assertEquals(expected, actualOutput);
    }

    @Test
    public void uniqFromFile_NoFlagWithInputFileWithNoOutputFile_ShouldWriteToStdOut() throws Exception {
        String output = app.uniqFromFile(false, false, false, PATH_INPUTFILE, null);
        String expected = String.join(STRING_NEWLINE, STRING1, STRING2, STRING_EMPTY, STRING2, STRING3);

        assertEquals(expected, output);
    }
    @Test
    public void uniqFromFile_AllDFlagWithInputFile_ShouldReturnAllDuplicateLines() throws Exception {
        // Setting up input with adjacent duplicate lines
        String inputContent = String.join(STRING_NEWLINE, STRING1, STRING1, STRING1, STRING2, STRING3, STRING3);
        FileUtils.writeFileContent(PATH_INPUTFILE, inputContent);

        // Invoke uniqFromFile with isAllRepeated flag set to true
        String actualOutput = app.uniqFromFile(false, false, true, PATH_INPUTFILE, null);

        // Expected output should contain all instances of each duplicated line
        String expectedOutput = String.join(STRING_NEWLINE, STRING1, STRING1, STRING1, STRING3, STRING3);

        assertEquals(expectedOutput, actualOutput, "All duplicate lines should be returned.");
    }


    @Test
    public void uniqFromStdin_NoFlagWithStdInWithOutputFile_ShouldRemoveAdjacentDuplicateLinesToOutputFile() throws Exception {
        this.inputStream = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        app.uniqFromStdin(false, false, false, this.inputStream, PATH_INPUTFILE);
        List<String> unformattedOutput = FileUtils.readFileContent(PATH_INPUTFILE);
        String actualOutput = FileUtils.formatListOfStringToMultilineString(unformattedOutput);
        String expected = String.join(STRING_NEWLINE, STRING1, STRING2, STRING_EMPTY, STRING2, STRING3);

        assertEquals(expected, actualOutput);
    }

    @Test
    public void uniqFromStdin_CFlagWithStdInWithOutputFile_ReturnNumberOfAdjacentDuplicateLinesToOutputFile() throws Exception {
        this.inputStream = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        app.uniqFromStdin(true, false, false, this.inputStream, PATH_OUTPUTFILE);
        List<String> unformattedOutput = FileUtils.readFileContent(PATH_OUTPUTFILE);
        String actualOutput = FileUtils.formatListOfStringToMultilineString(unformattedOutput);
        String expected = String.join(STRING_NEWLINE, CHAR_TAB + "3 " + STRING1, CHAR_TAB + "2 " + STRING2,
            CHAR_TAB + "3 " + STRING_EMPTY, CHAR_TAB + "1 " + STRING2, CHAR_TAB + "2 " + STRING3);

        assertEquals(expected, actualOutput);
    }

    @Test
    public void uniqFromStdin_NoFlagsWithStdInWithNoOutputFile_ShouldRemoveAdjacentDuplicateLinesToStdOut() throws Exception {
        this.inputStream = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String output = app.uniqFromStdin(false, false, false, this.inputStream, null);
        String expected = String.join(STRING_NEWLINE, STRING1, STRING2, STRING_EMPTY, STRING2, STRING3);

        assertEquals(expected, output);
    }

    @Test
    public void uniqFromStdin_NoFlagWithEmptyStdInWithNoOutputFile_ShouldThrowException() throws Exception {
        this.inputStream = new ByteArrayInputStream("".getBytes());
        String output = app.uniqFromStdin(false, false, false, this.inputStream, null);

        assertEquals("", output);
    }


}