package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.isBlank;

public class StringUtilsTest {

    @Test
    public void fileSeparator_WindowsAndOthers_ReturnFileSeparator() {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        assertTrue(StringUtils.fileSeparator().equals(File.separator) || (osName.contains("win") && StringUtils.fileSeparator().equals("\\" + File.separator)));
    }

    @Test
    public void isBlank_NullString_ReturnsTrue() {
        assertTrue(isBlank(null));
    }

    @Test
    public void isBlank_EmptyString_ReturnsTrue() {
        assertTrue(isBlank(""));
    }

    @Test
    public void isBlank_Whitespace_ReturnsTrue() {
        assertTrue(isBlank("   "));
    }

    @Test
    public void isBlank_NonWhitespace_ReturnsFalse() {
        assertFalse(isBlank("text"));
    }

    @Test
    public void multiplyChar_NegativeNum_ReturnsEmptyString() {
        assertEquals("", StringUtils.multiplyChar('a', -1));
    }

    @Test
    public void multiplyChar_PositiveNum_ReturnsRepeatedChars() {
        assertEquals("aaa", StringUtils.multiplyChar('a', 3));
    }

    @Test
    public void tokenize_BlankString_ReturnsEmptyArray() {
        assertArrayEquals(new String[0], StringUtils.tokenize(""));
    }

    @Test
    public void tokenize_WhitespaceDelimitedString_ReturnsTokens() {
        assertArrayEquals(new String[]{"Hello", "World"}, StringUtils.tokenize("Hello World"));
    }

    @Test
    public void isNumber_ValidNumber_ReturnsTrue() {
        assertTrue(StringUtils.isNumber("123"));
    }

    @Test
    public void isNumber_InvalidNumber_ReturnsFalse() {
        assertFalse(StringUtils.isNumber("text"));
    }
}
