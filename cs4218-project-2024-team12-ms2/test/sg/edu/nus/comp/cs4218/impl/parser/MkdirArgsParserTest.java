package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirArgsParserTest {
    private static final String TEMP_DIRECTORY= "temp";
    MkdirArgsParser mkdirArgsParser = new MkdirArgsParser();


    @Test
    @SneakyThrows
    void isParent_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-p", TEMP_DIRECTORY};
        mkdirArgsParser.parse(args);
        assertTrue(mkdirArgsParser.isParent());
    }

    @Test
    @SneakyThrows
    void parse_InvalidFlags_ShouldThrowException() {
        String illegalLetter = "z";
        String illegalFlag = "-" + illegalLetter;
        String[] args = {illegalFlag, TEMP_DIRECTORY};
        try {
            mkdirArgsParser.parse(args);
            fail("Expected InvalidArgsException");
        } catch (InvalidArgsException e) {
            assertTrue(e.getMessage().contains(ErrorConstants.ERR_ILLEGAL_FLAG + illegalLetter));
        }
    }

    @Test
    @SneakyThrows
    void parse_Flagp_ShouldNotThrownExceptioon() {
        String flagLetter = "p";
        String flag = "-" + flagLetter;
        String[] args = {flag, TEMP_DIRECTORY};
        assertDoesNotThrow(() -> mkdirArgsParser.parse(args));
        assertEquals(1, mkdirArgsParser.flags.size());
        assertTrue(mkdirArgsParser.flags.contains('p'));

    }

    @Test
    @SneakyThrows
    void getFileNames_ValidArgs_ShouldReturnTrue() {
        String[] args = {"-p", TEMP_DIRECTORY};
        mkdirArgsParser.parse(args);
        assertEquals(TEMP_DIRECTORY, mkdirArgsParser.getFileNames().get(0));
    }

}
