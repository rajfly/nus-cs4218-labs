package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.TestUtils.assertExceptionContains;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;

/**
 * Test class for {@code GrepArgsParser}
 * <br><br>
 * <p>
 *     Positive:
 * </p>
 * <p>
 *     - No flags
 * </p>
 * <p>
 *     - All valid flags
 * </p>
 * <p>
 *     - No arguments
 * </p>
 * <br>
 * <p>
 *     - Negative:
 * </p>
 * <p>
 *     - Invalid flags
 * </p>
 */
public class GrepArgsParserTest {
    private static GrepArgsParser grepArgsParser;

    @BeforeEach
    void setUp() {
        grepArgsParser = new GrepArgsParser();
    }

    @Test
    @DisplayName("No flags")
    void parse_NoFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"d.txt"};

        // When
        grepArgsParser.parse(args);

        // Then
        assertFalse(grepArgsParser.isCaseSensitive() || grepArgsParser.isCountLines()
            || grepArgsParser.isPrefixFileName());
    }

    @Test
    @DisplayName("All valid flags")
    void parse_AllFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"-icH", "d.txt"};

        // When
        grepArgsParser.parse(args);

        // Then
        assertTrue(grepArgsParser.isCaseSensitive() && grepArgsParser.isCountLines()
            && grepArgsParser.isPrefixFileName());
    }

    @Test
    @DisplayName("No arguments")
    void parse_NoArgs_NoException() throws InvalidArgsException {
        // Given
        String[] args = {};

        // When
        grepArgsParser.parse(args);

        // Then
        List<String> filenames = grepArgsParser.getFileNames();
        assertTrue(filenames.isEmpty());
    }

    @Test
    @DisplayName("Invalid flags")
    void parse_InvalidFlags_ThrowsWcException() {
        // Given
        String[] args = {"-d"};

        // When
        InvalidArgsException exception = assertThrows(InvalidArgsException.class,
            () -> grepArgsParser.parse(args));

        // Then
        assertExceptionContains(exception, "-d: " + ERR_INVALID_FLAG);
    }
}
