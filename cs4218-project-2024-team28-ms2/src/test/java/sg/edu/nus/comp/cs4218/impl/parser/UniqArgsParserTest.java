package sg.edu.nus.comp.cs4218.impl.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

public class UniqArgsParserTest {
    private UniqArgsParser uniqArgsParser;

    @BeforeEach
    public void setUp() {
        uniqArgsParser = new UniqArgsParser();
    }

    @Test
    public void uniqArgsParser_TestIsCountFlag_ShouldReturnTrueForIsCount() throws InvalidArgsException {
        assertFalse(uniqArgsParser.isCount()); // By default, count flag should be false
        uniqArgsParser.parse("-c"); // Setting count flag
        assertTrue(uniqArgsParser.isCount());
    }

    @Test
    public void uniqArgsParser_TestIsRepeatedFlag_ShouldReturnTrueForIsRepeated() throws InvalidArgsException {
        assertFalse(uniqArgsParser.isRepeated()); // By default, repeated flag should be false
        uniqArgsParser.parse("-d"); // Setting repeated flag
        assertTrue(uniqArgsParser.isRepeated());
    }

    @Test
    public void uniqArgsParser_TestIsAllRepeatedFlag_ShouldReturnTrueForIsAllRepeated() throws InvalidArgsException {
        assertFalse(uniqArgsParser.isAllRepeated()); // By default, all repeated flag should be false
        uniqArgsParser.parse("-D"); // Setting all repeated flag
        assertTrue(uniqArgsParser.isAllRepeated());
    }

    @Test
    public void uniqArgsParser_TestGetInputFileName_ShouldReturnInputFileNamesAndReturnFalseForAllOptions() throws InvalidArgsException {
        uniqArgsParser.parse("test1.txt");
        assertEquals("test1.txt", uniqArgsParser.getInputFileName());
        assertFalse(uniqArgsParser.isCount());
        assertFalse(uniqArgsParser.isRepeated());
        assertFalse(uniqArgsParser.isAllRepeated());
    }

    @Test
    public void uniqArgsParser_TestGetOutputFileName_ShouldReturnOutputFileNames() throws InvalidArgsException {
        uniqArgsParser.parse("input1.txt", "output1.txt");
        assertEquals("output1.txt", uniqArgsParser.getOutputFileName());
    }

    @Test
    public void uniqArgsParser_TestGetInputFileNameWithoutArgs_ShouldReturnEmptyInputFileNamesAndFalseForAllOptions() throws InvalidArgsException {
        uniqArgsParser.parse("");
        assertEquals("", uniqArgsParser.getInputFileName());
        assertFalse(uniqArgsParser.isCount());
        assertFalse(uniqArgsParser.isRepeated());
        assertFalse(uniqArgsParser.isAllRepeated());
    }

    @Test
    public void uniqArgsParser_TestGetOutputFileNameWithoutArgs_ShouldReturnEmptyOutputFileNamesAndFalseForAllOptions() throws InvalidArgsException {
        uniqArgsParser.parse("");
        assertEquals("", uniqArgsParser.getOutputFileName());
        assertFalse(uniqArgsParser.isCount());
        assertFalse(uniqArgsParser.isRepeated());
        assertFalse(uniqArgsParser.isAllRepeated());
    }

    @Test
    public void uniqArgsParser_TestGetInputFileNameWithCountFlag_ShouldReturnInputFileNamesAndTrueForIsCount() throws InvalidArgsException {
        uniqArgsParser.parse("-c", "input2.txt");
        assertEquals("input2.txt", uniqArgsParser.getInputFileName());
        assertTrue(uniqArgsParser.isCount());
    }

    @Test
    public void uniqArgsParser_TestGetOutputFileNameWithCountFlag_ShouldReturnOutputFileNamesAndTrueForIsCount() throws InvalidArgsException {
        uniqArgsParser.parse("-c", "input3.txt", "output3.txt");
        assertEquals("output3.txt", uniqArgsParser.getOutputFileName());
        assertTrue(uniqArgsParser.isCount());
    }

    @Test
    public void uniqArgsParser_TestInvalidFlag_ShouldThrowInvalidArgsException() {
        assertThrows(InvalidArgsException.class, () -> uniqArgsParser.parse("-x"));
    }

}
