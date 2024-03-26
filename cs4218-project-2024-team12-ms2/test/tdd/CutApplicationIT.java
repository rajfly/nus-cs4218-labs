package tdd;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.exception.CutException;
import sg.edu.nus.comp.cs4218.impl.app.CutApplication;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;

@EnabledOnOs({OS.MAC, OS.LINUX})
class CutApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private CutApplication cutApplication;

    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR;
    private static final String CUT_TEST_DIR = "test/resources/cut";
    private static final String TEST_ONE_FILE = CUT_TEST_DIR + "/test.txt";
    private static final String TEST_TWO_FILE = CUT_TEST_DIR + "/test2.txt";
    private static final String INVALID_FILE_PATH = CUT_TEST_DIR + "/invalid.txt";
    private static final String INVALID_DIR = CUT_TEST_DIR + StringUtils.fileSeparator() + "invalid";
    private static final String CUT_EXCEPTION_MSG = "cut: ";
    private static final String CHAR_POS_FLAG = "-c";
    private static final String BYTE_POS_FLAG = "-b";
    private static final String INPUT_STDIN = "Input stream";
    private static final String RANGE_LIST = "1-3";
    private static final String COMMA_LIST = "1,3";
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        cutApplication = new CutApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
        try {
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (IOException e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void run_NullArgs_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(null, null, null));
        assertEquals(CUT_EXCEPTION_MSG + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_NullStdin_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, RANGE_LIST}, null, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_NO_ISTREAM, exception.getMessage());
    }

    @Test
    void run_NullStdout_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, RANGE_LIST}, inputStream, null));
        assertEquals(CUT_EXCEPTION_MSG + ERR_NO_OSTREAM, exception.getMessage());
    }

    @Test
    void run_InvalidArgs_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{"-d", RANGE_LIST}, inputStream, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_INVALID_FLAG, exception.getMessage());
    }

    @Test
    @DisplayName("cut -c -b 1-3 test.txt")
    void run_BothCharAndByteFlag_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, BYTE_POS_FLAG, RANGE_LIST}, inputStream, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_TOO_MANY_ARGS, exception.getMessage());
    }

    @Test
    @DisplayName("cut 1-3 test.txt")
    void run_NoFlag_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{RANGE_LIST}, inputStream, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_SYNTAX, exception.getMessage());
    }

    @Test
    @DisplayName("cut -c 3-1 test.txt")
    void run_InvalidContinuousReversedRange_ShouldThrowException() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, "3-1", TEST_ONE_FILE}, inputStream, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_INVALID_RANGE, exception.getMessage());
    }

    @Test
    void run_InvalidFilePath_ShouldThrowException() {
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, RANGE_LIST, INVALID_FILE_PATH}, inputStream, outputStream));
        assertEquals(CUT_EXCEPTION_MSG + ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 6-9 test.txt")
    void run_ValidSingleRange_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, "6-9", TEST_ONE_FILE}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "stor";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-3 test.txt")
    void run_ContinuousValidRange_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, RANGE_LIST, TEST_ONE_FILE}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "Map";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,3 test.txt")
    void run_DisjointRange_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, COMMA_LIST, TEST_ONE_FILE}, inputStream, outputStream));

        String result = outputStream.toString();

        String expected = "Mp";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-5")
    void run_CutFromStandardInput_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-5"}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "Input";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-3 -")
    void run_CutFromStandardInWithStringDash_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-3", STRING_DASH}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "Inp";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-3 test.txt test2.txt")
    void run_CutFromMultipleFiles_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-5", TEST_ONE_FILE, TEST_TWO_FILE}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "Maple" + StringUtils.STRING_NEWLINE + "This ";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cat -c 2 test.txt test2.txt")
    void run_CutFromMultipleFilesWithSingleRange_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "2", TEST_ONE_FILE, TEST_TWO_FILE}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "a" + StringUtils.STRING_NEWLINE + "h";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void run_CutFileIsDirectory_ShouldThrowException() {
        assertTrue(new File(TEMP_DIR_PATH).isDirectory());

        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, RANGE_LIST, TEMP_DIR_PATH}, inputStream, outputStream));

        assertEquals(CUT_EXCEPTION_MSG + ERR_IS_DIR, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-1 test.txt")
    void run_SameNumberRange_ShouldReturnExpected() {
        String expected = "M";

        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());
        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-1", TEST_ONE_FILE}, inputStream, outputStream));

        assertEquals(expected, outputStream.toString());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1-100 -")
    void run_OutOfRange_ShouldReturnEntireStream() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());
        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-100", STRING_DASH}, inputStream, outputStream));

        String expected = INPUT_STDIN;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 0-0 test.txt")
    void run_ZeroListRange_ShouldThrowException() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{BYTE_POS_FLAG, "0-0", TEST_ONE_FILE}, inputStream, outputStream));

        assertEquals(CUT_EXCEPTION_MSG + ERR_INVALID_RANGE, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 0,0 test.txt")
    void run_ZeroCommaList_ShouldThrowException() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());
        CutException exception = assertThrows(CutException.class, () -> cutApplication.run(new String[]{CHAR_POS_FLAG, "0,0", TEST_ONE_FILE}, inputStream, outputStream));

        assertEquals(CUT_EXCEPTION_MSG + ERR_INVALID_RANGE, exception.getMessage());
    }


    @Test
    @SneakyThrows
    @DisplayName("cut -b 1-12 -")
    void run_BothEndRanges_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1-12", STRING_DASH}, inputStream, outputStream));

        String expected = INPUT_STDIN;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 1,2,3 test.txt")
    void run_ValidCommaRanges_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(INPUT_STDIN.getBytes());

        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, "1,2,3", TEST_ONE_FILE}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "Map";
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 1,2,5-8,15 test.txt")
    void run_MixedCommaAndRangeList_ShouldReturnExpected() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, "1,2,5-8,15", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "Maestoa";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,2-3,5-8,15 test.txt")
    void run_MixedCommaAndRangeAndNumberList_ShouldReturnExpected() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1,2-3,5-8,15", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "Mapestoa";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,2,3,4,5,6,7,8,9,10 test.txt")
    void run_AllCommaList_ShouldReturnExpected() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1,2,3,4,5,6,7,8,9,10", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "Maplestory";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 1-2,6-8 test.txt")
    void run_AllRangesList_ShouldReturnExpected() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, "1-2,6-8", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "Masto";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -b 5,4,3,2,1 test.txt")
    void run_ReverseCommaList_ShouldReturnSortedList() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{BYTE_POS_FLAG, "5,4,3,2,1", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "Maple";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1,1,1-1 test.txt")
    void run_DuplicatedRanges_ShouldReturnExpected() {
        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1,1,1-1", TEST_ONE_FILE}, inputStream, outputStream));

        String expected = "M";
        String result = outputStream.toString();

        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("cut -c 1 -")
    void run_CutFromEmptyStdIn_ShouldReturnExpected() {
        inputStream = new ByteArrayInputStream(new byte[0]);

        assertDoesNotThrow(() -> cutApplication.run(new String[]{CHAR_POS_FLAG, "1", STRING_DASH}, inputStream, outputStream));
        String result = outputStream.toString();

        String expected = "";
        assertEquals(expected, result);
    }

}
