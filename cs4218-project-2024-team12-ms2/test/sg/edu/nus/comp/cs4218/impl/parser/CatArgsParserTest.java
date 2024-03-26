package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatArgsParserTest {

    private CatArgsParser catArgsParser;

    @BeforeEach
    void setUp() {
        catArgsParser = new CatArgsParser();
    }

    @Test
    @SneakyThrows
    void parse_IllegalArgs_ShouldThrowException() {
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> catArgsParser.parse("-a"));

        assertEquals(ErrorConstants.ERR_ILLEGAL_FLAG + "a", exception.getMessage());
    }


    @Test
    @SneakyThrows
    void isLineNumber_True_ShouldReturnTrue() {
        catArgsParser.parse("-n");

        assertTrue(catArgsParser.isLineNumber());
    }

    @Test
    @SneakyThrows
    void getFileList_ValidFileList_ShouldReturnCorrectly() {
        catArgsParser.parse("-n", "file1.txt", "file2.txt");

        assertEquals(catArgsParser.getFileList(), List.of("file1.txt", "file2.txt"));
    }

    @Test
    @SneakyThrows
    void isReadFromStdin_True_ShouldReturnTrue() {
        catArgsParser.parse("-n", "-");

        assertTrue(catArgsParser.isReadFromStdin());
    }

    @Test
    @SneakyThrows
    void isReadFromStdin_False_ShouldReturnFalse() {
        catArgsParser.parse("-n", "file1.txt");

        assertFalse(catArgsParser.isReadFromStdin());
    }
}