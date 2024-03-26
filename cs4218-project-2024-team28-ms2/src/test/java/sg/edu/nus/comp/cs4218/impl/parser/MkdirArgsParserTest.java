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
 * Test class for {@code MkdirArgsParser}
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
public class MkdirArgsParserTest {
    private static MkdirArgsParser mkdirArgsParser;

    @BeforeEach
    void setUp() {
        mkdirArgsParser = new MkdirArgsParser();
    }

    @Test
    @DisplayName("No flags")
    void parse_NoFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"b.txt"};

        // When
        mkdirArgsParser.parse(args);

        // Then
        assertFalse(mkdirArgsParser.isParent());
    }

    @Test
    @DisplayName("All valid flags")
    void parse_AllFlags_NoException() throws InvalidArgsException {
        // Given
        String[] args = {"-p", "b.txt"};

        // When
        mkdirArgsParser.parse(args);

        // Then
        assertTrue(mkdirArgsParser.isParent());
    }

    @Test
    @DisplayName("No arguments")
    void parse_NoArgs_NoException() throws InvalidArgsException {
        // Given
        String[] args = {};

        // When
        mkdirArgsParser.parse(args);

        // Then
        List<String> filenames = mkdirArgsParser.getFileNames();
        assertTrue(filenames.isEmpty());
    }

    @Test
    @DisplayName("Invalid flags")
    void parse_InvalidFlags_ThrowsWcException() {
        // Given
        String[] args = {"-b"};

        // When
        InvalidArgsException exception = assertThrows(InvalidArgsException.class,
            () -> mkdirArgsParser.parse(args));

        // Then
        assertExceptionContains(exception, "-b: " + ERR_INVALID_FLAG);
    }
}
