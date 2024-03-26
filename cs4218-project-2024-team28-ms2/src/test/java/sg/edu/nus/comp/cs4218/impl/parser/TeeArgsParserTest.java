package sg.edu.nus.comp.cs4218.impl.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

public class TeeArgsParserTest {
    private TeeArgsParser teeArgsParser;

    @BeforeEach
    public void setUp() {
        teeArgsParser = new TeeArgsParser();
    }

    @Test
    public void teeArgsParser_TestIsAppendFlag_ShouldReturnTrueForAppend() throws InvalidArgsException {
        assertFalse(teeArgsParser.isAppend()); // By default, append flag should be false
        teeArgsParser.parse("-a", "tee1.txt"); // Setting append flag
        assertTrue(teeArgsParser.isAppend());
    }

    @Test
    public void teeArgsParser_TestGetFileNames_ShouldReturnFilesAndFalseForAppend() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("file1.txt", "file2.txt", "file3.txt");
        teeArgsParser.parse("file1.txt", "file2.txt", "file3.txt");
        assertEquals(expectedFileNames, teeArgsParser.getFileNames());
        assertFalse(teeArgsParser.isAppend());
    }

    @Test
    public void teeArgsParser_TestGetFileNamesWithFlags_ShouldReturnFilesAndTrueForAppend() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("test1.txt", "test2.txt", "test3.txt");
        teeArgsParser.parse("-a", "test1.txt", "test2.txt", "test3.txt");
        assertEquals(expectedFileNames, teeArgsParser.getFileNames());
        assertTrue(teeArgsParser.isAppend());
    }

    @Test
    public void teeArgsParser_TestInvalidFlag_ShouldThrowInvalidArgsException() {
        assertThrows(InvalidArgsException.class, () -> teeArgsParser.parse("-x"));
    }
}
