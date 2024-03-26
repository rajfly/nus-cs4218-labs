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

public class SortArgsParserTest {
    private SortArgsParser sortArgsParser;

    @BeforeEach
    public void setUp() {
        sortArgsParser = new SortArgsParser();
    }

    @Test
    public void sortArgsParser_TestIsFirstWordNumberFlag_ShouldReturnTrueForIsFirstWordNum() throws InvalidArgsException {
        assertFalse(sortArgsParser.isFirstWordNumber()); // By default, first word number flag should be false
        sortArgsParser.parse("-n"); // Setting first word number flag
        assertTrue(sortArgsParser.isFirstWordNumber());
    }

    @Test
    public void sortArgsParser_TestIsReverseOrderFlag_ShouldReturnTrueForIsReverseOrder() throws InvalidArgsException {
        assertFalse(sortArgsParser.isReverseOrder()); // By default, reverse order flag should be false
        sortArgsParser.parse("-r"); // Setting reverse order flag
        assertTrue(sortArgsParser.isReverseOrder());
    }

    @Test
    public void sortArgsParser_TestIsCaseIndependentFlag_ShouldReturnTrueForIsCaseDependent() throws InvalidArgsException {
        assertFalse(sortArgsParser.isCaseIndependent()); // By default, case independent flag should be false
        sortArgsParser.parse("-f"); // Setting case independent flag
        assertTrue(sortArgsParser.isCaseIndependent());
    }

    @Test
    public void sortArgsParser_TestGetFileNames_ShouldReturnFileNamesAndReturnFalseForAllOptions() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("sort1.txt", "sort2.txt", "sort3.txt");
        sortArgsParser.parse("sort1.txt", "sort2.txt", "sort3.txt");
        assertEquals(expectedFileNames, sortArgsParser.getFileNames());
        assertFalse(sortArgsParser.isFirstWordNumber());
        assertFalse(sortArgsParser.isReverseOrder());
        assertFalse(sortArgsParser.isCaseIndependent());
    }

    @Test
    public void sortArgsParser_TestGetFileNamesWithFlags_ShouldReturnFileNamesAndTrueForAllOptions() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("test1.txt", "test2.txt", "test3.txt");
        sortArgsParser.parse( "-n", "-r", "-f", "test1.txt", "test2.txt", "test3.txt");
        assertEquals(expectedFileNames, sortArgsParser.getFileNames());
        assertTrue(sortArgsParser.isFirstWordNumber());
        assertTrue(sortArgsParser.isReverseOrder());
        assertTrue(sortArgsParser.isCaseIndependent());
    }

    @Test
    public void sortArgsParser_TestIsFirstWordNumberWithFlags_ShouldReturnFileNamesAndTrueForIsFirstWordNum() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("flag1.txt");
        sortArgsParser.parse("-n", "flag1.txt");
        assertEquals(expectedFileNames, sortArgsParser.getFileNames());
        assertTrue(sortArgsParser.isFirstWordNumber());
    }

    @Test
    public void sortArgsParser_TestIsReverseOrderWithFlags_ShouldReturnFileNamesAndTrueForIsReverseOrder() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("flag2.txt");
        sortArgsParser.parse("-r", "flag2.txt");
        assertEquals(expectedFileNames, sortArgsParser.getFileNames());
        assertTrue(sortArgsParser.isReverseOrder());
    }

    @Test
    public void sortArgsParser_TestIsCaseIndependentWithFlags_ShouldReturnFileNamesAndTrueForIsCaseDependent() throws InvalidArgsException {
        List<String> expectedFileNames = Arrays.asList("flag3.txt");
        sortArgsParser.parse( "-f", "flag3.txt");
        assertEquals(expectedFileNames, sortArgsParser.getFileNames());
        assertTrue(sortArgsParser.isCaseIndependent());
    }

    @Test
    public void sortArgsParser_TestInvalidFlag_ShouldThrowInvalidArgsException() {
        assertThrows(InvalidArgsException.class, () -> sortArgsParser.parse("-x"));
    }
}
