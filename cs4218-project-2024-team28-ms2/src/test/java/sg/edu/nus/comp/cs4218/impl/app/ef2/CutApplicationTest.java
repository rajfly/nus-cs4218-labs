package sg.edu.nus.comp.cs4218.impl.app.ef2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.app.CutApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CutApplicationTest {
    private static CutApplication cutApp;
    private static final Path DIRECTORY = Paths.get("src","test", "java","sg","edu","nus", "comp" ,"cs4218", "impl","app", "util", "dummyTestFolder", "CutTestFolder");
    private static final String FOLDERNAME = DIRECTORY.toString() + StringUtils.fileSeparator();
    private static String fileNameTest = "test.txt";

    @BeforeAll
    static void initialSetup() {
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @AfterAll
    static void reset() {
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @BeforeEach
    void setUp() {
        cutApp = new CutApplication();
    }

    @Test
    void testCut_WithCommaSeparatedNumbersFromFile_InChar() {
        String expectResult = "Ts";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        ranges.add(new int[]{8, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(true, false, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithCommaSeparatedNumbersFromFile_InBytes() {
        String expectResult = "Ts";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        ranges.add(new int[]{8, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(false, true, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithRangeNumberFromFile_InChar() {
        String expectResult = "oday is";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{2, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(true, false, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithRangeNumberFromFile_InBytes() {
        String expectResult = "Today is";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(false, true, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithSingleNumberFromFile_InChar() {
        String expectResult = "o";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{2, 2});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(true, false, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithSingleNumberFromFile_InBytes() {
        String expectResult = "o";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{2, 2});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromFiles(false, true, ranges, FOLDERNAME + CHAR_FILE_SEP + fileNameTest);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithCommaSeparatedNumbersFromStdin_InChar() {
        String original = "Today is Wednesday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "Ts";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        ranges.add(new int[]{8, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(true, false, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithCommaSeparatedNumbersFromStdin_InBytes() {
        String original = "Today is Monday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "TM";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        ranges.add(new int[]{10, 10});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(false, true, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithRangeNumberFromStdin_InChar() {
        String original = "Today is Friday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "day is";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{3, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(true, false, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithRangeNumberFromStdin_InBytes() {
        String original = "Today is Tuesday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "Today is";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 8});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(false, true, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithSingleNumberFromStdin_InChar() {
        String original = "Today is Tuesday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "T";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(true, false, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testCut_WithSingleNumberFromStdin_InBytes() {
        String original = "Today is Tuesday.";
        InputStream stdin = new ByteArrayInputStream(original.getBytes());
        String expectResult = "T";
        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{1, 1});
        assertDoesNotThrow(() -> {
            String realResult = cutApp.cutFromStdin(false, true, ranges, stdin);
            assertEquals(expectResult, realResult);
        });
    }

}
