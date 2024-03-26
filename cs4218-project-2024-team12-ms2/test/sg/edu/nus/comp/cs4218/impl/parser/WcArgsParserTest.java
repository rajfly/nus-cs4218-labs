package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

public class WcArgsParserTest {
    private static final String FILE = "file.txt";
    WcArgsParser wcArgsParser = new WcArgsParser();

    @Test
    @SneakyThrows
    void isByteCount_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-c", FILE};
        wcArgsParser.parse(args);
        assertTrue(wcArgsParser.isByteCount());
    }

    @Test
    @SneakyThrows
    void isLineCount_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-l", FILE};
        wcArgsParser.parse(args);
        assertTrue(wcArgsParser.isLineCount());
    }

    @Test
    @SneakyThrows
    void isWordCount_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-w", FILE};
        wcArgsParser.parse(args);
        assertTrue(wcArgsParser.isWordCount());
    }

    @Test
    @SneakyThrows
    void filesContainDash_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-", FILE};
        wcArgsParser.parse(args);
        assertTrue(wcArgsParser.filesContainDash());
    }

    @Test
    @SneakyThrows
    void parse_InvalidFlags_ShouldThrowException() {
        String illegalLetter = "z";
        String illegalFlag = "-" + illegalLetter;
        String[] args = {illegalFlag, FILE};
        try {
            wcArgsParser.parse(args);
            fail("Expected InvalidArgsException");
        } catch (InvalidArgsException e) {
            assertTrue(e.getMessage().contains(ErrorConstants.ERR_ILLEGAL_FLAG + illegalLetter));
        }
    }

    @Test
    @SneakyThrows
    void parse_Flagclw_ShouldNotThrownExceptioon() {
        String flagLetter = "clw";
        String flag = "-" + flagLetter;
        String[] args = {flag, FILE};
        assertDoesNotThrow(() -> wcArgsParser.parse(args));
        assertEquals(3, wcArgsParser.flags.size());
    }

    @Test
    @SneakyThrows
    void parse_FlagclwWithMultipleFiles_ShouldNotThrownExceptioon() {
        String flagLetter = "clw";
        String flag = "-" + flagLetter;
        String[] args = {flag, FILE, FILE};
        assertDoesNotThrow(() -> wcArgsParser.parse(args));
        assertEquals(3, wcArgsParser.flags.size());
    }

    @Test
    @SneakyThrows
    void getFileNames_ValidArgs_ShouldReturnTrue() {
        String[] args = {"-c", FILE};
        wcArgsParser.parse(args);
        assertEquals(FILE, wcArgsParser.getFileNames().get(0));
        assertEquals(1, wcArgsParser.getFileNames().size());
    }

    @Test
    @SneakyThrows
    void getFileNames_ValidArgsWithMultipleFiles_ShouldReturnTrue() {
        String[] args = {"-c", FILE, FILE};
        wcArgsParser.parse(args);
        assertEquals(FILE, wcArgsParser.getFileNames().get(0));
        assertEquals(FILE, wcArgsParser.getFileNames().get(1));
        assertEquals(2, wcArgsParser.getFileNames().size());
    }

    @Test
    @SneakyThrows
    void getFileNames_ValidArgsWithNoFlag_ShouldReturnTrue() {
        String[] args = {FILE};
        wcArgsParser.parse(args);
        assertTrue(wcArgsParser.isByteCount());
        assertTrue(wcArgsParser.isLineCount());
        assertTrue(wcArgsParser.isWordCount());
    }


}
