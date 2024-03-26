package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isBlank_Null_ShouldReturnTrue() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    void isBlank_EmptyString_ShouldReturnTrue() {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    void isBlank_MultiWhitespaces_ShouldReturnTrue() {
        assertTrue(StringUtils.isBlank("     "));
    }

    @Test
    void isBlank_Newlines_ShouldReturnTrue() {
        assertTrue(StringUtils.isBlank("\n"));
        assertTrue(StringUtils.isBlank("\r"));
        assertTrue(StringUtils.isBlank("\t"));
    }

    @Test
    void isBlank_WhitespacesWithNewlines_ShouldReturnTrue() {
        assertTrue(StringUtils.isBlank("    \n   "));
        assertTrue(StringUtils.isBlank("     \r"));
        assertTrue(StringUtils.isBlank("\t    "));
    }

    @Test
    void fileSeparator_Windows_ShouldReturnBackslash() {
        String osName = "Windows";
        String expected = "\\" + File.separator;
        System.setProperty("os.name", osName);

        assertEquals(expected, StringUtils.fileSeparator());
    }

    @Test
    void fileSeparator_Linux_ShouldReturnForwardSlash() {
        String osName = "Linux";
        String expected = File.separator;
        System.setProperty("os.name", osName);

        assertEquals(expected, StringUtils.fileSeparator());
    }

    @Test
    void multiplyChar_NegativeNum_ShouldReturnEmptyString() {
        assertTrue(StringUtils.multiplyChar('a', -1).isEmpty());
    }

    @Test
    void multiplyChar_Zero_ShouldReturnEmptyString() {
        assertTrue(StringUtils.multiplyChar('a', 0).isEmpty());
    }

    @Test
    void multiplyChar_PositiveNum_ShouldReturnString() {
        assertEquals("aaa", StringUtils.multiplyChar('a', 3));
    }

    @Test
    void tokenize_EmptyString_ShouldReturnEmptyArray() {
        assertEquals(0, StringUtils.tokenize("").length);
    }

    @Test
    void tokenize_validString_ShouldReturnArray() {
        String[] expected = {"a", "b", "c"};
        assertEquals(Arrays.toString(expected), Arrays.toString(StringUtils.tokenize("a b c")));
    }

    @Test
    void tokenize_validStringWithNewlines_ShouldReturnArray() {
        String[] expected = {"a", "b", "c"};
        String testString = "a" + System.lineSeparator() + "b" + System.lineSeparator() + "c";

        assertEquals(Arrays.toString(expected), Arrays.toString(StringUtils.tokenize(testString)));
    }

    @Test
    void isNumber_ValidNumber_ShouldReturnTrue() {
        assertTrue(StringUtils.isNumber("123"));
    }

    @Test
    void isNumber_BigNumber_ShouldReturnTrue() {
        assertTrue(StringUtils.isNumber("12345678901234567890123456789012345678901234567890123456789012345678901234567890"));
    }

    @Test
    void isNumber_emptyString_ShouldReturnFalse() {
        assertFalse(StringUtils.isNumber(""));
    }
}