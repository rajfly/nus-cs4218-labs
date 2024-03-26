package sg.edu.nus.comp.cs4218.impl.app.ef1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.app.UniqApplication;

public class UniqApplicationTest {
    public UniqApplication uniqApplication;
    private static final String LINE_REGEX = "\\r\\n";

    @BeforeEach
    void setUp() {
        uniqApplication = new UniqApplication();
    }

    @Test
    void uniqFromStdin_NoOptions_ReturnsInput() throws AbstractApplicationException {
        String input = "Uniq\nTest1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, false, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(input, output.trim());
    }

    @Test
    void uniqFromStdin_NoOptions_ReturnsMerged() throws AbstractApplicationException {
        String input = "Uniq\nUniq\nTest";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, false, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals("Uniq\nTest", output.trim());
    }

    @Test
    void uniqFromStdin_CountOption_ReturnsInputWithCount() throws AbstractApplicationException {
        String input = "Count\nTest";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(true, false, false, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals("1 Count\n1 Test", output.trim());
    }

    @Test
    void uniqFromStdin_CountOption_ReturnsMergedWithCount() throws AbstractApplicationException {
        String input = "Hello\nHello\nWorld";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(true, false, false, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals("2 Hello\n1 World", output.trim());
    }

    @Test
    void uniqFromStdin_RepeatedOption_ReturnsEmptyString() throws AbstractApplicationException {
        String input = "Repeated\nTest";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, true, false, inputStream, null);
        assertEquals("", output.trim());
    }

    @Test
    void uniqFromStdin_RepeatedOption_ReturnsDuplicateLines() throws AbstractApplicationException {
        String input = "Hi\nHi\nJames";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, true, false, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals("Hi", output.trim());
    }

    @Test
    void uniqFromStdin_AllRepeatedOption_ReturnsEmptyString() throws AbstractApplicationException {
        String input = "All\nRepeated\nTest";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, true, inputStream, null);
        assertEquals("", output.trim());
    }

    @Test
    void uniqFromStdin_AllRepeatedOption_ReturnsAllDuplicateLines() throws AbstractApplicationException {
        String input = "All\nAll\nHappy";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, true, inputStream, null)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals("All\nAll", output.trim());
    }

    @Test
    void uniqFromStdin_EmptyInputStream_ReturnsEmptyString() throws AbstractApplicationException {
        InputStream emptyInputStream = new ByteArrayInputStream("".getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, false, emptyInputStream, null);
        assertEquals("", output);
    }

    @Test
    void uniqFromStdin_SingleLineInput_ReturnsSameLine() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Single line input".getBytes());
        String output = uniqApplication.uniqFromStdin(false, false, false, inputStream, null);
        output = output.replace(System.lineSeparator(), "\n");
        assertEquals("Single line input\n", output);
    }

    @Test
    void uniqFromStdin_MultipleLinesInput_ReturnsMergedLines() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Line 1\nLine 2\nLine 1".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] args = {""};
        uniqApplication.run(args, inputStream, outputStream);
        String output = outputStream.toString();
        output = output.replace(System.lineSeparator(), "\n");
        assertEquals("Line 1\nLine 2\nLine 1\n", output);
    }

    @Test
    void uniqFromStdin_InputWithBlankLines_ReturnsMergedLinesWithoutBlankLines() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Line 1\n\nLine 2\n\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] args = {""};
        uniqApplication.run(args, inputStream, outputStream);
        String output = outputStream.toString();
        output = output.replace(System.lineSeparator(), "\n");
        assertEquals("Line 1\n\nLine 2\n\n", output);
    }

    @Test
    void uniqFromStdin_InputWithDifferentLineEndings_ReturnsMergedLinesWithConsistentLineEndings() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("Line 1\r\nLine 2\rLine 3\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] args = {""};
        uniqApplication.run(args, inputStream, outputStream);
        String output = outputStream.toString();
        output = output.replace(System.lineSeparator(), "\n");
        assertEquals("Line 1\nLine 2\nLine 3\n", output);
    }
    @Test
    void uniqFromFile_NoOptions_ReturnsInput() throws AbstractApplicationException, IOException {
        String input = "Test\nTest\nTee";
        String[] files = {"uniq1.txt"};
        // Create files if they don't exist
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
        }
        Files.write(Paths.get(files[0]), input.getBytes());
        String[] args = {files[0]};
        ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        uniqApplication.run(args, inputStream, outputStream);
        String output = outputStream.toString();
        output = output.replace(System.lineSeparator(), "\n");
        assertEquals("Test\nTee", output.trim());
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void uniqFromStdin_NullInputStream_ThrowsException() {
        assertThrows(UniqException.class, () -> uniqApplication.uniqFromStdin(false, false, false, null, null));
    }

    @Test
    void uniqFromFile_NonexistentFile_ThrowsException() {
        String nonExistentFile = "nonexistent.txt";
        String outputFileName = "output.txt";
        assertThrows(UniqException.class, () -> uniqApplication.uniqFromFile(false, false, false, nonExistentFile, outputFileName));
    }

}
