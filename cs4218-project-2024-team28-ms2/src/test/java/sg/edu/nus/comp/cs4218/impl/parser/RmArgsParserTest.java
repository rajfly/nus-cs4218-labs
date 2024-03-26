package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.app.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RmArgsParserTest {

    private static final String DIR_1 = "directory1";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";

    private RmArgsParser rmParser;

    @BeforeEach
    void setUp() {
        rmParser = new RmArgsParser();
    }

    @Test
    public void parse_NoFlags_Success() throws InvalidArgsException {
        String[] noFlagArgs = {FILE_1, FILE_2};
        rmParser.parse(noFlagArgs);
        assertFalse(rmParser.isRecursive());
        assertFalse(rmParser.isEmptyFolder());
        assertEquals(Arrays.asList(noFlagArgs), rmParser.getFiles());
    }

    @Test
    public void parse_RecursiveFlag_Success() throws InvalidArgsException {
        String[] recursiveArgs = {"-r", FILE_1, FILE_2};
        rmParser.parse(recursiveArgs);
        assertTrue(rmParser.isRecursive());
        assertFalse(rmParser.isEmptyFolder());
        assertEquals(List.of(FILE_1, FILE_2), rmParser.getFiles());
    }

    @Test
    public void parse_EmptyFolderFlag_Success() throws InvalidArgsException {
        String[] emptyFolderArgs = {"-d", DIR_1};
        rmParser.parse(emptyFolderArgs);
        assertFalse(rmParser.isRecursive());
        assertTrue(rmParser.isEmptyFolder());
        assertEquals(List.of(DIR_1), rmParser.getFiles());
    }

    @Test
    public void parse_MultipleFlags_Success() throws InvalidArgsException {
        String[] multiArgs = {"-r", "-d", DIR_1, FILE_1};
        rmParser.parse(multiArgs);
        assertTrue(rmParser.isRecursive());
        assertTrue(rmParser.isEmptyFolder());
        assertEquals(List.of(DIR_1, FILE_1), rmParser.getFiles());
    }

    @Test
    public void parse_UnknownFlags_ThrowsException(){
        String[] args = {"-a", "-b", FILE_1};
        assertThrows(InvalidArgsException.class, () -> rmParser.parse(args));
    }

    @Test
    public void parse_noArguments_Success() throws InvalidArgsException {
        String[] args = {};
        rmParser.parse(args);
        assertFalse(rmParser.isRecursive());
        assertFalse(rmParser.isEmptyFolder());
        assertTrue(rmParser.getFiles().isEmpty());
    }
}
