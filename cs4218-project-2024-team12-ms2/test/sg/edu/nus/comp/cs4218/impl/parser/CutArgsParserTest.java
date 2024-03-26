package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class CutArgsParserTest {

    CutArgsParser cutArgsParser;
    private static final String FILE_ONE = "file1.txt";
    private static final String FILE_TWO = "file2.txt";
    private static final String LIST_RANGE = "1-8";
    private static final String LIST_COMMA = "1,8";

    @BeforeEach
    void setUp() {
        cutArgsParser = new CutArgsParser();
    }

    @Test
    void isChar_isChar_ShouldReturnTrue() {
        cutArgsParser.flags.add(CutArgsParser.FLAG_CHAR);
        assertTrue(cutArgsParser.isChar());
    }

    @Test
    void isChar_isNotChar_ShouldReturnFalse() {
        cutArgsParser.flags.add(CutArgsParser.FLAG_BYTE);
        assertFalse(cutArgsParser.isChar());
    }

    @Test
    void isByte_IsByte_ShouldReturnTrue() {
        cutArgsParser.flags.add(CutArgsParser.FLAG_BYTE);
        assertTrue(cutArgsParser.isByte());
    }

    @Test
    void isByte_IsNotByte_ShouldReturnFalse() {
        cutArgsParser.flags.add(CutArgsParser.FLAG_CHAR);
        assertFalse(cutArgsParser.isByte());
    }

    @Test
    @DisplayName("cut -c test.txt")
    void getFileList_MissingRange_ShouldThrowException() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add(FILE_ONE);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cutArgsParser.getListRanges());
        assertEquals(CutArgsParser.INVALID_RANGE, exception.getMessage());
    }

    @Test
    @DisplayName("cut -b 1")
    void getFileList_NoFile_ShouldReturnEmptyList() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_BYTE);
        cutArgsParser.nonFlagArgs.add("1");

        assertEquals(0, cutArgsParser.getFileList().size());
    }

    @Test
    @DisplayName("cut -b 1.2")
    void getFileList_InvalidFloatRange_ShouldThrowException() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_BYTE);
        cutArgsParser.nonFlagArgs.add("1.2");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cutArgsParser.getListRanges());
        assertEquals(CutArgsParser.INVALID_RANGE, exception.getMessage());
    }

    @Test
    @DisplayName("cut -c 1,8 file1.txt file2.txt")
    void getFileList_HasFile_ShouldReturnList() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add(LIST_RANGE);
        cutArgsParser.nonFlagArgs.add(FILE_ONE);
        cutArgsParser.nonFlagArgs.add(FILE_TWO);

        assertEquals(2, cutArgsParser.getFileList().size());
    }

    @Test
    void getListRanges_CommaSeparated_ShouldReturnCorrectRange() {
        cutArgsParser.nonFlagArgs.add(LIST_COMMA);

        assertEquals(2, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
        assertArrayEquals(new int[]{8, 8}, cutArgsParser.getListRanges().get(1));
    }

    @Test
    @DisplayName("cut -b 1-8")
    void getListRanges_RangeSeparated_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_BYTE);
        cutArgsParser.nonFlagArgs.add(LIST_RANGE);

        assertEquals(1, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 8}, cutArgsParser.getListRanges().get(0));
    }

    @Test
    @DisplayName("cut -c 1")
    void getListRanges_StandaloneNumber_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("1");

        assertEquals(1, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
    }

    @Test
    @DisplayName("cut -c 1 -")
    void getListRanges_StandaloneNumberWithNoFiles_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("1");
        cutArgsParser.nonFlagArgs.add(StringUtils.STRING_DASH);

        assertEquals(1, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
    }

    @Test
    @DisplayName("cut -c a-b")
    void getListRanges_NotNumber_ShouldThrowException() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("a-b");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cutArgsParser.getListRanges());
        assertEquals(CutArgsParser.INVALID_RANGE, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,2,3")
    void getListRanges_InvalidRange_ShouldNotThrowException() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("1,2,3");

        assertEquals(3, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
        assertArrayEquals(new int[]{2, 2}, cutArgsParser.getListRanges().get(1));
        assertArrayEquals(new int[]{3, 3}, cutArgsParser.getListRanges().get(2));
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 1,2,5-8 test.txt")
    void getListRanges_ListRangeAndCommaRange_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_BYTE);
        cutArgsParser.nonFlagArgs.add("1,2,5-8");
        cutArgsParser.nonFlagArgs.add(FILE_ONE);

        assertEquals(3, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
        assertArrayEquals(new int[]{2, 2}, cutArgsParser.getListRanges().get(1));
        assertArrayEquals(new int[]{5, 8}, cutArgsParser.getListRanges().get(2));
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,2,5-8,14,15-17 test.txt")
    void getListRanges_MixedListAndCommaAndNumberRanges_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("1,2,5-8,14,15-17");
        cutArgsParser.nonFlagArgs.add(FILE_ONE);

        assertEquals(5, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
        assertArrayEquals(new int[]{2, 2}, cutArgsParser.getListRanges().get(1));
        assertArrayEquals(new int[]{5, 8}, cutArgsParser.getListRanges().get(2));
        assertArrayEquals(new int[]{14, 14}, cutArgsParser.getListRanges().get(3));
        assertArrayEquals(new int[]{15, 17}, cutArgsParser.getListRanges().get(4));
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 5-8,2,14,15-17,1 test.txt")
    void getListRanges_CheckRangeIsSorted_ShouldReturnCorrectRange() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("5-8,2,14,15-17,1");
        cutArgsParser.nonFlagArgs.add(FILE_ONE);

        assertEquals(5, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
        assertArrayEquals(new int[]{2, 2}, cutArgsParser.getListRanges().get(1));
        assertArrayEquals(new int[]{5, 8}, cutArgsParser.getListRanges().get(2));
        assertArrayEquals(new int[]{14, 14}, cutArgsParser.getListRanges().get(3));
        assertArrayEquals(new int[]{15, 17}, cutArgsParser.getListRanges().get(4));
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,1,1-1 test.txt")
    void getListRanges_DuplicatedRanges_ShouldRemoveDuplicates() {
        cutArgsParser.legalFlags.add(CutArgsParser.FLAG_CHAR);
        cutArgsParser.nonFlagArgs.add("1,1,1-1");
        cutArgsParser.nonFlagArgs.add(FILE_ONE);

        assertEquals(1, cutArgsParser.getListRanges().size());
        assertArrayEquals(new int[]{1, 1}, cutArgsParser.getListRanges().get(0));
    }

}