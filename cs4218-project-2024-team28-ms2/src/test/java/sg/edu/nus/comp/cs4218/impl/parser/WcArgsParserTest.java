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
 * Test class for {@code WcArgsParser}
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
public class WcArgsParserTest {
    private static WcArgsParser wcArgsParser;

    @BeforeEach
    void setUp() {
        wcArgsParser = new WcArgsParser();
    }

    @Test
    @DisplayName("No flags")
    void parse_NoFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"a.txt"};

        // When
        wcArgsParser.parse(args);

        // Then
        assertFalse(wcArgsParser.isByteCount() || wcArgsParser.isLineCount() || wcArgsParser.isWordCount());
    }

    @Test
    @DisplayName("All valid flags")
    void parse_AllFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"-clw", "a.txt"};

        // When
        wcArgsParser.parse(args);

        // Then
        assertTrue(wcArgsParser.isByteCount() && wcArgsParser.isLineCount() && wcArgsParser.isWordCount());
    }

    @Test
    @DisplayName("No arguments")
    void parse_NoArgs_NoException() throws InvalidArgsException {
        // Given
        String[] args = {};

        // When
        wcArgsParser.parse(args);

        // Then
        List<String> filenames = wcArgsParser.getFileNames();
        assertTrue(filenames.isEmpty());
    }

    @Test
    @DisplayName("Invalid flags")
    void parse_InvalidFlags_ThrowsWcException() {
        // Given
        String[] args = {"-a"};

        // When
        InvalidArgsException exception = assertThrows(InvalidArgsException.class,
            () -> wcArgsParser.parse(args));

        // Then
        assertExceptionContains(exception, "-a: " + ERR_INVALID_FLAG);
    }
}
