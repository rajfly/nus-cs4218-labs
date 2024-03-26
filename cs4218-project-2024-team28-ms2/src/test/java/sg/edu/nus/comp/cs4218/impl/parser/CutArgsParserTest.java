package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CutArgsParserTest {
    private CutArgsParser cutArgsParser;

    @BeforeEach
    public void setUp() {
        cutArgsParser = new CutArgsParser();
    }

    @Test
    public void cutArgsParser_Test_IsChar() throws InvalidArgsException {
        Boolean expectedIsChar = true;
        cutArgsParser.parse("-c", "1,8", "test.txt");
        assertEquals(expectedIsChar, cutArgsParser.isChar());
    }

    @Test
    public void cutArgsParser_Test_IsByte() throws InvalidArgsException {
        Boolean expectedIsByte = true;
        cutArgsParser.parse("-b", "1,8", "test.txt");
        assertEquals(expectedIsByte, cutArgsParser.isByte());
    }

    @Test
    public void cutArgsParser_Test_GetFiles() throws InvalidArgsException {
        String[] inputArgs = {"-c", "1,8", "file1.txt", "file2.txt", "file3.txt"}; //NOPMD
        cutArgsParser.parse(inputArgs);  // Assuming you have a parse method to set up the state
        String[] result = cutArgsParser.getFiles();
        String[] expected = {"file1.txt", "file2.txt", "file3.txt"};
        assertArrayEquals(expected, result);
    }
    @Test
    public void cutArgsParser_TestRange_SingleValue() throws Exception {
        // Single Value
        cutArgsParser.parse("-c", "7", "file1.txt");
        List<int[]> result1 = cutArgsParser.getRanges();
        int[] expected1 = {7, 7};
        assertArrayEquals(expected1, result1.get(0));
    }

    @Test
    public void cutArgsParser_TestRange_Range() throws Exception {
        // Range
        cutArgsParser.parse("-c", "2-7", "file1.txt");
        List<int[]> result2 = cutArgsParser.getRanges();
        int[] expected2 = {2, 7};
        assertArrayEquals(expected2, result2.get(0));
    }
    @Test
    public void cutArgsParser_TestRange_list() throws Exception {
        // Range
        cutArgsParser.parse("-c", "2,7", "file1.txt");
        List<int[]> result3 = cutArgsParser.getRanges();
        List<int[]> expected3 = new ArrayList<>();
        expected3.add(new int[]{2, 2});
        expected3.add(new int[]{7, 7});
        assertEquals(expected3.size(), result3.size());
        for (int i = 0; i < expected3.size(); i++) {
            assertArrayEquals(expected3.get(i), result3.get(i));
        }
    }


}
