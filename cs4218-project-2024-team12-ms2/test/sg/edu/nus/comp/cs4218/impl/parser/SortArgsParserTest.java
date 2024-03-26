package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

public class SortArgsParserTest {
    private static final String FILE = "file.txt";
    SortArgsParser sortArgsParser = new SortArgsParser();


    @Test
    @SneakyThrows
    void isFirstWordNumber_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-n", FILE};
        sortArgsParser.parse(args);
        assertTrue(sortArgsParser.isFirstWordNumber());
    }

    @Test
    @SneakyThrows
    void isReverseOrder_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-r", FILE};
        sortArgsParser.parse(args);
        assertTrue(sortArgsParser.isReverseOrder());
    }

    @Test
    @SneakyThrows
    void isCaseIndependent_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-f", FILE};
        sortArgsParser.parse(args);
        assertTrue(sortArgsParser.isCaseIndependent());
    }

    @Test
    @SneakyThrows
    void parse_InvalidFlags_ShouldThrowException() {
        String illegalLetter = "z";
        String illegalFlag = "-" + illegalLetter;
        String[] args = {illegalFlag, FILE};
        try {
            sortArgsParser.parse(args);
            fail("Expected InvalidArgsException");
        } catch (InvalidArgsException e) {
            assertTrue(e.getMessage().contains(ErrorConstants.ERR_ILLEGAL_FLAG + illegalLetter));
        }
    }

    @Test
    @SneakyThrows
    void parse_Flagnr_ShouldNotThrownExceptioon() {
        String flagLetter = "nr";
        String flag = "-" + flagLetter;
        String[] args = {flag, FILE};
        assertDoesNotThrow(() -> sortArgsParser.parse(args));
        assertEquals(2, sortArgsParser.flags.size());
        assertTrue(sortArgsParser.flags.contains('n'));
        assertTrue(sortArgsParser.flags.contains('r'));

    }

    @Test
    @SneakyThrows
    void getFileNames_ValidArgs_ShouldReturnTrue() {
        String[] args = {"-f", FILE};
        sortArgsParser.parse(args);
        assertEquals(FILE, sortArgsParser.getFileNames().get(0));
    }

}
