package sg.edu.nus.comp.cs4218.impl.app.bf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FILE_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;

public class CatApplicationTest {
    public CatApplication catApplication;
    private static final String LINE_REGEX = "\\r\\n";
    private static final String[] CATFILES = {"testCatApp1.txt", "testCatApp2.txt"};
    private static final String CAT_EXCEPTION = "cat: ";
    private static final String CAT_ERR_IS_DIR = "This is a directory";
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("cat-application-test");
        catApplication = new CatApplication();
        String file1Content = "Hello World\n";
        Files.write(Paths.get(CATFILES[0]), file1Content.getBytes());
        String file2Content = "How are you\n";
        Files.write(Paths.get(CATFILES[1]), file2Content.getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(CATFILES[0]));
        Files.deleteIfExists(Paths.get(CATFILES[1]));
    }

    @Test
    void catFiles_ValidFiles_ReturnsConcatenatedContent() throws AbstractApplicationException {
        String expectedOutput = "Hello World\nHow are you\n";
        String actualOutput = catApplication.catFiles(false, CATFILES)
                .replaceAll(LINE_REGEX, "\n"); // Normalize line separators
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void catStdin_ValidInput_ReturnsInputContent() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Test input".getBytes(StandardCharsets.UTF_8));
        String expectedOutput = "Test input\n";
        String actualOutput = catApplication.catStdin(false, inputStream)
                .replaceAll(LINE_REGEX, "\n"); // Normalize line separators
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void catFiles_LineNumbersEnabled_PrefixesLineNumbers() throws AbstractApplicationException {
        String expectedOutput = "1 Hello World\n1 How are you\n";
        String actualOutput = catApplication.catFiles(true, CATFILES)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void catFileAndStdin_ValidInput_ReturnsConcatenatedContent() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Test input".getBytes(StandardCharsets.UTF_8));
        String expectedOutput = "Hello World\nTest input\n";
        String actualOutput = catApplication.catFileAndStdin(false, inputStream, CATFILES[0])
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void run_NullOutputStream_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class, () -> catApplication.run(CATFILES, System.in, null));
        assertEquals(CAT_EXCEPTION + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    void run_NullArgs_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class, () -> catApplication.run(null, System.in, System.out));
        assertEquals(CAT_EXCEPTION + ERR_NULL_ARGS, thrown.getMessage());
    }

    @Test
    void run_ArgsContainNull_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class, () -> catApplication.run(new String[]{"A.txt", null},
                System.in, System.out));
        assertEquals(CAT_EXCEPTION + ERR_NULL_ARGS, thrown.getMessage());
    }

    @Test
    void run_StdinAndFileNamesAreNull_PrintsException() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Test".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        catApplication.run(new String[]{}, inputStream, outputStream);
        assertEquals("Test" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void run_InvalidDirectoryAsInput_PrintsException() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        catApplication.run(new String[]{"directory" + File.separatorChar + "file.txt"}, inputStream, outputStream);
        assertEquals(CAT_EXCEPTION + "file.txt" + ": " + ERR_FILE_NOT_FOUND + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    void run_ValidDirectoryAsInput_PrintsException() throws AbstractApplicationException {
        String directoryName = new File(tempDir.toString()).getName();
        String[] files = new String[]{tempDir.toString()};
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        catApplication.run(files, inputStream, outputStream);
        assertEquals(CAT_EXCEPTION + directoryName + ": " + CAT_ERR_IS_DIR + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    void catFiles_EmptyFileArray_ReturnsEmptyString() throws AbstractApplicationException {
        String[] files = {};
        assertEquals("", catApplication.catFiles(false, files));
    }

    @Test
    void catFiles_NullFileArray_ReturnsEmptyString() throws AbstractApplicationException {
        assertEquals("", catApplication.catFiles(false, (String[]) null));
    }

    @Test
    void catFiles_DirectoryAsInput_ThrowsException() {
        String[] files = {"directory\\test.txt"};
        assertThrows(AbstractApplicationException.class, () -> catApplication.catFiles(false, files));
    }

    @Test
    void catFiles_NonExistentFiles_ThrowsException() {
        String[] files = {"nonexistent.txt"};
        assertThrows(AbstractApplicationException.class, () -> catApplication.catFiles(false, files));
    }

    @Test
    void catFiles_NullIsLineNumber_ThrowsException() {
        String[] files = {"A.txt"};
        Throwable thrown = assertThrows(CatException.class, () -> catApplication.catFiles(null, files));
        assertEquals(CAT_EXCEPTION + ERR_NULL_ARGS, thrown.getMessage());
    }

    @Test
    void catFileAndStdin_NullFileNames_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class,
                () -> catApplication.catFileAndStdin(false, System.in, null));
        assertEquals(CAT_EXCEPTION + ERR_NO_FILE_ARGS, thrown.getMessage());
    }

    @Test
    void catFileAndStdin_isLineNumberNull_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class,
                () -> catApplication.catFileAndStdin(null, System.in, CATFILES));
        assertEquals(CAT_EXCEPTION + ERR_NULL_ARGS, thrown.getMessage());
    }

    @Test
    void catFileAndStdin_NullStdin_ThrowsException() {
        Throwable thrown = assertThrows(CatException.class,
                () -> catApplication.catFileAndStdin(false, null, CATFILES));
        assertEquals(CAT_EXCEPTION + ERR_NULL_STREAMS, thrown.getMessage());
    }

    @Test
    void catFiles_BinaryFile_ReturnsBinaryContent() throws IOException, AbstractApplicationException {
        String[] binFiles = {"binaryFile.bin"};
        byte[] binaryContent = {0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x57, 0x6F, 0x72, 0x6C, 0x64}; // Hello World in binary
        if (!Files.exists(Paths.get(binFiles[0]))) {
            Files.createFile(Paths.get(binFiles[0]));
        }
        Files.write(Paths.get(binFiles[0]), binaryContent);
        String output = catApplication.catFiles(false, binFiles).replaceAll(LINE_REGEX, "\n");
        assertEquals("Hello World\n", output);
        Files.deleteIfExists(Paths.get(binFiles[0]));
    }

    @Test
    void catFiles_SpecialCharactersFile_ReturnsConcatenatedContent() throws IOException, AbstractApplicationException {
        String[] specialFiles = {"specialCharacters.txt"};
        String specialContent = "Special characters: \u00A9\u00AE\u2122\n";
        if (!Files.exists(Paths.get(specialFiles[0]))) {
            Files.createFile(Paths.get(specialFiles[0]));
        }
        Files.write(Paths.get(specialFiles[0]), specialContent.getBytes());
        String output = catApplication.catFiles(false, specialFiles[0]).replaceAll(LINE_REGEX, "\n");
        assertEquals(specialContent, output);
        Files.deleteIfExists(Paths.get(specialFiles[0]));
    }

}
