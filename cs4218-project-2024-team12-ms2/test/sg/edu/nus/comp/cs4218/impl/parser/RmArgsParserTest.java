package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

public class RmArgsParserTest {
    private static final String FILE = "file.txt";
    RmArgsParser rmArgsParser = new RmArgsParser();

    @Test
    @SneakyThrows
    void isRecursive_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-r", FILE};
        rmArgsParser.parse(args);
        assertTrue(rmArgsParser.isRecursive());
    }

    @Test
    @SneakyThrows
    void isEmpty_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-d", FILE};
        rmArgsParser.parse(args);
        assertTrue(rmArgsParser.isEmptyFolder());
    }

    @Test
    @SneakyThrows
    void parse_InvalidFlags_ShouldThrowException() {
        String illegalLetter = "z";
        String illegalFlag = "-" + illegalLetter;
        String[] args = {illegalFlag, FILE};
        try {
            rmArgsParser.parse(args);
            fail("Expected InvalidArgsException");
        } catch (InvalidArgsException e) {
            assertTrue(e.getMessage().contains(ErrorConstants.ERR_ILLEGAL_FLAG + illegalLetter));
        }
    }

    @Test
    @SneakyThrows
    void getFileNames_NoFlags_ShouldNotThrowException() {
        String[] args = {FILE};
        try {
            rmArgsParser.parse(args);
            assertFalse(rmArgsParser.isRecursive());
            assertFalse(rmArgsParser.isEmptyFolder());
        } catch (InvalidArgsException e) {
            fail("Unexpected InvalidArgsException");
        }
    }

    @Test
    @SneakyThrows
    void getFileNames_ValidFiles_ShouldNotThrowException() {
        String[] args = {FILE};
        try {
            rmArgsParser.parse(args);
            assertEquals(FILE, rmArgsParser.getFileNames().get(0));
        } catch (InvalidArgsException e) {
            fail("Unexpected InvalidArgsException");
        }
    }

    @Test
    @SneakyThrows
    void getFileNames_NoFiles_ShouldNotThrowException() {
        String[] args = {};
        try {
            rmArgsParser.parse(args);
            assertEquals(0, rmArgsParser.getFileNames().size());
        } catch (InvalidArgsException e) {
            fail("Unexpected InvalidArgsException");
        }
    }

}
