package sg.edu.nus.comp.cs4218.impl.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

public class GrepArgsParserTest {
    GrepArgsParser grepArgsParser = new GrepArgsParser();

    @Test
    @SneakyThrows
    void isInvert_CorrectFlag_ShouldReturnTrue() {
        String[] args = {"-v", "pattern", "file.txt"};
        grepArgsParser.parse(args);
        assertTrue(grepArgsParser.isInvert());
    }

    @Test
    @SneakyThrows
    void getPattern_ValidPattern_ShouldReturnCorrectly() {
        String pattern = "";
        String[] args = {"-v", pattern, "file.txt"};
        grepArgsParser.parse(args);
        assertEquals(null, grepArgsParser.getPattern());
    }

    @Test
    @SneakyThrows
    void getFileNames_ValidFiles_ShouldReturnCorrectly() {
        String[] files = {"file1.txt", "file2.txt"};
        String[] args = {"-v", "pattern", files[0], files[1]};
        grepArgsParser.parse(args);
        assertArrayEquals(files, grepArgsParser.getFileNames());
    }
}
