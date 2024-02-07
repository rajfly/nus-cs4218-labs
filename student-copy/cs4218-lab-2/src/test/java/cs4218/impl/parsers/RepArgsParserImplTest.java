package cs4218.impl.parsers;

import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RepArgsParserImplTest {
    RepArgsParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new RepArgsParserImpl();
    }

    @Test
    void getMode_WithFlag_ReplaceCharactersModeSelected() throws RepException {
        parser.parseAndValidate(Arrays.asList("rep", "-c", "pattern", "replacement", "path"));

        assertEquals(REPLACE_MODE.REPLACE_CHARACTERS, parser.getMode());
    }

    // Try asserting some exceptions
    @Disabled
    @Test
    void parseAndValidate_WithInvalidFlag_ThrowsRepException() {

    }

    // Find the bug (hint: number of arguments)
    @Disabled
    @Test
    void parseAndValidate_WithoutFilePath_ThrowsRepException() {

    }

    // Find the bug (hint: flag)
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {})
    void parseAndValidate_ComplexPattern_ReturnsCorrectValue(String pattern) throws RepException {

    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.WINDOWS, OS.MAC}) // Check for OS specific bugs
    void parseAndValidate_EmptyString_ThrowsRepException() {
        assertThrows(RepException.class, () -> parser.parseAndValidate(Collections.emptyList()));
    }

    @Nested // Similar tests can be grouped together (same method or setup or ...)
    class SimpleValues {
        @BeforeEach
        void setUp() throws RepException {
            String filePath = Path.of("file-path", "to", "file.txt").toString();
            parser.parseAndValidate(Arrays.asList("rep", "hello", "world", filePath));
        }

        @Test
        void getPattern_SimpleValues_ReturnsCorrectValue() {
            String pattern = parser.getPattern();

            assertEquals("hello", pattern);
        }

        @Test
        void getReplacement_SimpleValues_ReturnsCorrectValue() {
            String replacement = parser.getReplacement();

            assertEquals("world", replacement);
        }

        @Test
        void getFilePath_SimpleValues_ReturnsCorrectValue() {
            String filePath = parser.getFilePath();

            String expected = Path.of("file-path", "to", "file.txt").toString();
            assertEquals(expected, filePath);
        }
    }
}