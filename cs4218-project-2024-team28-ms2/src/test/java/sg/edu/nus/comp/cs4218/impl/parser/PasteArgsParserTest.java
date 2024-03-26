package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import static org.junit.jupiter.api.Assertions.*;

public class PasteArgsParserTest {
    @Test
    public void pasteArgsParser_Test_IsSerial() throws InvalidArgsException {
        PasteArgsParser parser = new PasteArgsParser();
        assertFalse(parser.isSerial()); // By default, should return false
        parser.parse("-s","test.txt"); // Set serial flag
        assertTrue(parser.isSerial());
    }

    @Test
    public void pasteArgsParser_Test_testNumFile() throws InvalidArgsException {
        PasteArgsParser parser = new PasteArgsParser();
        parser.parse("file1","file2","file3"); //NOPMD
        assertEquals(3, parser.numFile()); // Three files provided

        parser = new PasteArgsParser();
        parser.parse("-", "file1","-","file2");
        assertEquals(2, parser.numFile()); // Two files provided
    }

    @Test
    public void pasteArgsParser_Test_testNumStdin() throws InvalidArgsException {
        PasteArgsParser parser = new PasteArgsParser();
        parser.parse("file1", "-", "file2", "-", "file3");
        assertEquals(2, parser.numStdin()); // Two stdin markers provided

        parser = new PasteArgsParser();
        parser.parse("-");
        assertEquals(1, parser.numStdin()); // One stdin marker provided
    }

    @Test
    public void pasteArgsParser_Test_testGetFiles() throws InvalidArgsException {
        PasteArgsParser parser = new PasteArgsParser();
        parser.parse("file1", "file2", "file3");
        String[] expected = {"file1", "file2", "file3"};
        assertArrayEquals(expected, parser.getFiles());

        parser = new PasteArgsParser();
        parser.parse("file1", "-", "file3");
        String[] expected2 = {"file1", "-", "file3"};
        assertArrayEquals(expected2, parser.getFiles());
    }




}
