package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.SortException;

import java.io.*;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
public class SortApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private static final String SORT_HEADER = "sort: ";
    private static final String RESOURCES = Paths.get("test", "resources", "sort").toString();
    private static final String ONE = "1.txt";
    private static final String TWO = "2.txt";
    private static final String THREE = "3.txt";
    private static final String NOT_EXIST = "notExist.txt";
    private final SortInterface app = new SortApplication();

    private OutputStream outputStream;
    @Test
    void run_NumbersFile_ShouldReturnSortedOutput() {
        String[] args = {Paths.get(RESOURCES, ONE).toString(), Paths.get(RESOURCES, TWO).toString()};
        String expectResult = "3" + STRING_NEWLINE +
                "4" + STRING_NEWLINE +
                "5" + STRING_NEWLINE +
                "6" + STRING_NEWLINE +
                "7" + STRING_NEWLINE +
                "8" + STRING_NEWLINE +
                "9" + STRING_NEWLINE;

        outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> {
            app.run(args, System.in, outputStream);
            assertEquals(expectResult, outputStream.toString());
        });
    }

    @Test
    void run_NullStdout_ShouldThrowSortException() {
        String[] args = {Paths.get(RESOURCES, ONE).toString(), Paths.get(RESOURCES, TWO).toString()};
        SortException exception = assertThrows(SortException.class, () -> app.run(args, System.in, null));
        assertEquals(String.format("%s%s", SORT_HEADER, ERR_NULL_STREAMS), exception.getMessage());
    }

    @Test
    void run_InvalidArgs_ShouldThrowSortException() {
        String[] args = {"-n", "-r", "-a", "test/resources/sort/1.txt", "test/resources/sort/2.txt"};
        SortException exception = assertThrows(SortException.class, () -> app.run(args, System.in, new ByteArrayOutputStream()));
        assertEquals(String.format("%s%s", SORT_HEADER, ERR_INVALID_FLAG), exception.getMessage());
    }

    @Test
    void run_OneNumbersFile_ShouldReturnSortedOutput() {
        String[] args = {};
        String expectResult = "3" + STRING_NEWLINE +
                "4" + STRING_NEWLINE +
                "5" + STRING_NEWLINE +
                "7" + STRING_NEWLINE +
                "9" + STRING_NEWLINE;

        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("9\n7\n5\n4\n3\n".getBytes());
        assertDoesNotThrow(() -> {
            app.run(args, inputStream, outputStream);
            assertEquals(expectResult, outputStream.toString());
        });
    }

    @Test
    void run_InvalidFile_ShouldThrowSortException() {
        String[] args = {NOT_EXIST};
        SortException exception = assertThrows(SortException.class, () -> app.run(args, System.in, new ByteArrayOutputStream()));
        assertEquals(String.format("%s%s", SORT_HEADER, ERR_FILE_NOT_FOUND), exception.getMessage());
    }

    @Test
    void run_ValidArgsnr_ShouldTReturnSortedOutput() {
        String[] args = {"-nr", "test/resources/sort/2.txt", "test/resources/sort/3.txt"};
        String expectResult = "aa1" + STRING_NEWLINE +
                "A2" + STRING_NEWLINE +
                "A1" + STRING_NEWLINE +
                "100" + STRING_NEWLINE +
                "10" + STRING_NEWLINE +
                "8" + STRING_NEWLINE +
                "6" + STRING_NEWLINE +
                "1" + STRING_NEWLINE;
        outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> {
            app.run(args, System.in, outputStream);
            assertEquals(expectResult, outputStream.toString());
        });
    }

    @Test
    void run_ValidArgsnrf_ShouldTReturnSortedOutput() {
        String[] args = {"-nr", "-f", "test/resources/sort/2.txt", "test/resources/sort/3.txt"};
        String expectResult = "aa1" + STRING_NEWLINE +
                "A2" + STRING_NEWLINE +
                "A1" + STRING_NEWLINE +
                "100" + STRING_NEWLINE +
                "10" + STRING_NEWLINE +
                "8" + STRING_NEWLINE +
                "6" + STRING_NEWLINE +
                "1" + STRING_NEWLINE;
        outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> {
            app.run(args, System.in, outputStream);
            assertEquals(expectResult, outputStream.toString());
        });
    }

    @Test
    void run_ValidArgsf_ShouldTReturnSortedOutput() {
        String[] args = {"-f"};
        String expectResult = "A1" + STRING_NEWLINE + "a1" + STRING_NEWLINE;
        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("A1\na1\n".getBytes());
        assertDoesNotThrow(() -> {
            app.run(args, inputStream, outputStream);
            assertEquals(expectResult, outputStream.toString());
        });

        String expectedInverse = "a1" + STRING_NEWLINE + "A1" + STRING_NEWLINE;
        outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputInverse = new ByteArrayInputStream("a1\nA1\n".getBytes());
        assertDoesNotThrow(() -> {
            app.run(args, inputInverse, outputStream);
            assertEquals(expectedInverse, outputStream.toString());
        });
    }
}
