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

public class CatArgsParserTest {
    private CatArgsParser catArgsParser;

    @BeforeEach
    public void setUp() {
        catArgsParser = new CatArgsParser();
    }

    @Test
    public void catArgsParser_TestGetFileNames_ShouldReturnFilesNamesAndFalseForLineNum() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("cat1.txt", "cat2.txt", "cat3.txt");
        catArgsParser.parse("cat1.txt", "cat2.txt", "cat3.txt");
        assertEquals(expectedFileNames, catArgsParser.getFileNames());
        assertFalse(catArgsParser.isLineNumber());
    }

    @Test
    public void catArgsParser_TestGetFileNamesWithFlags_ShouldReturnFilesNamesAndTrueForLineNum() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("file1.txt", "file2.txt", "file3.txt");
        catArgsParser.parse("-n", "file1.txt", "file2.txt", "file3.txt");
        assertEquals(expectedFileNames, catArgsParser.getFileNames());
        assertTrue(catArgsParser.isLineNumber());
    }

    @Test
    public void catArgsParser_TestIsLineNumberWithMultipleFlags_ShouldThrowInvalidArgsException() {
        assertThrows(InvalidArgsException.class, () -> catArgsParser.parse("-n -n -n"));
    }

    @Test
    public void catArgsParser_TestIsLineNumberWithoutFlag_ShouldReturnFalseForLineNum() throws InvalidArgsException {
        catArgsParser.parse("");
        assertFalse(catArgsParser.isLineNumber());
    }
}
