package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LsArgsParserTest {

    private LsArgsParser lsParser;

    private static final String DIR_1 = "directory1";
    private static final String DIR_2 = "directory2";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";

    @BeforeEach
    void setUp() {
        lsParser = new LsArgsParser();
    }

    @Test
    public void parse_NoFlags_ReturnsDirectories() throws Exception {
        String[] args = {DIR_1, DIR_2};
        lsParser.parse(args);
        assertFalse(lsParser.isRecursive());
        assertFalse(lsParser.isSortByExt());
        assertEquals(Arrays.asList(args), lsParser.getDirectories());
    }

    @Test
    public void parse_RecursiveFlag_Success() throws Exception {
        String[] args = {"-R", DIR_1};
        lsParser.parse(args);
        assertTrue(lsParser.isRecursive());
        assertFalse(lsParser.isSortByExt());
        assertEquals(List.of(DIR_1), lsParser.getDirectories());
    }

    @Test
    public void parse_SortByExtensionFlag_Success() throws Exception {
        String[] args = {"-X", FILE_1, FILE_2};
        lsParser.parse(args);
        assertFalse(lsParser.isRecursive());
        assertTrue(lsParser.isSortByExt());
        assertEquals(Arrays.asList(FILE_1, FILE_2), lsParser.getDirectories());
    }

    @Test
    public void parse_MultipleFlags_Success() throws Exception {
        String[] args = {"-R", "-X", DIR_1};
        lsParser.parse(args);
        assertTrue(lsParser.isRecursive());
        assertTrue(lsParser.isSortByExt());
        assertEquals(List.of(DIR_1), lsParser.getDirectories());
    }

    @Test
    public void parse_UnknownFlags_Ignored() throws Exception {
        String[] args = {"-Z", DIR_1};
        assertThrows(Exception.class, () -> lsParser.parse(args));
    }

    @Test
    public void parse_NoArguments_EmptyDirectories() throws Exception {
        String[] args = {};
        lsParser.parse(args);
        assertFalse(lsParser.isRecursive());
        assertFalse(lsParser.isSortByExt());
        assertTrue(lsParser.getDirectories().isEmpty());
    }
}
