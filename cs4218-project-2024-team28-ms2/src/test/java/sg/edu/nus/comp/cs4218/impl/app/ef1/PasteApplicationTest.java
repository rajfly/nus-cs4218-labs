package sg.edu.nus.comp.cs4218.impl.app.ef1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.PasteApplication;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

class PasteApplicationTest {
    private static PasteApplication pasteApp;
    private static final Path DIRECTORY = Paths.get("src","test", "java","sg","edu","nus", "comp" ,"cs4218", "impl","app", "util", "dummyTestFolder", "PasteTestFolder");
    private static final String ABSOLUTE_PATH = DIRECTORY.toFile().getAbsolutePath();
    private static final String DASH = "-";
    private static final String FILE_A = ABSOLUTE_PATH + fileSeparator() + "file_1_col_a.txt";
    private static final String FILE_A_S = ABSOLUTE_PATH + fileSeparator() + "file_1_row_a.txt";
    private static final String FILE_B = ABSOLUTE_PATH + fileSeparator() + "file_1_col_b.txt";
    private static final String FILE_C = ABSOLUTE_PATH + fileSeparator() + "file_1_col_c.txt";
    private static final String FILE_A_A = ABSOLUTE_PATH + fileSeparator() + "file_2_col_aa.txt";
    private static final String FILE_A_A_A = ABSOLUTE_PATH + fileSeparator() + "file_3_col_aaa.txt";
    private static final String FILE_A_B = ABSOLUTE_PATH + fileSeparator() + "file_2_col_ab.txt";
    private static final String FILE_A_B_C = ABSOLUTE_PATH + fileSeparator() + "file_3_col_abc.txt";
    private static final String FILE_B_A = ABSOLUTE_PATH + fileSeparator() + "file_2_col_ba.txt";
    private static final String FILE_A_A_S = ABSOLUTE_PATH + fileSeparator() + "file_2_row_aa.txt";
    private static final String FILE_A_A_A_S = ABSOLUTE_PATH + fileSeparator() + "file_3_row_aaa.txt";
    private static final String FILE_A_B_S = ABSOLUTE_PATH + fileSeparator() + "file_2_row_ab.txt";
    private static final String FILE_B_A_S = ABSOLUTE_PATH + fileSeparator() + "file_2_row_ba.txt";
    private static final String FILE_A_B_C_S = ABSOLUTE_PATH + fileSeparator() + "file_3_row_abc.txt";

    @BeforeAll
    public static void setUp() {
        pasteApp = new PasteApplication();
    }

    private String getExpectedResult(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        }
        return String.join(STRING_NEWLINE, result);
    }

    @Test
    void testPaste_MergeStdin_Parallel() throws Exception {
        String input = getExpectedResult(FILE_A);
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expectResult = getExpectedResult(FILE_A);
        assertDoesNotThrow(() -> {
            String realResult = pasteApp.mergeStdin(false,stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    void testPaste_MergeStdin_Serial() throws Exception {
        String input = getExpectedResult(FILE_A);
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expectResult = getExpectedResult(FILE_A_S);
        assertDoesNotThrow(() -> {
            String realResult = pasteApp.mergeStdin(true,stdin);
            assertEquals(expectResult, realResult);
        });
    }

    @Test
    public void testPaste_MergeMultipleSameFile_Parallel() throws Exception {
        // Double files
        String expected = getExpectedResult(FILE_A_A);
        assertEquals(expected, pasteApp.mergeFile(false, FILE_A, FILE_A));

        // Triple files
        expected = getExpectedResult(FILE_A_A_A);
        assertEquals(expected, pasteApp.mergeFile(false,FILE_A, FILE_A, FILE_A));
    }

    @Test
    public void testPaste_MergeMultipleDifferentFile_Parallel() throws Exception {
        // Double files
        String expected = getExpectedResult(FILE_A_B);
        assertEquals(expected, pasteApp.mergeFile(false,FILE_A, FILE_B));

        // Triple files
        expected = getExpectedResult(FILE_A_B_C);
        assertEquals(expected, pasteApp.mergeFile(false,FILE_A, FILE_B, FILE_C));
    }

    @Test
    public void testPaste_MergeTwoSameFileStdin_Parallel() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false, inStream, DASH, FILE_A));
        }

        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream, FILE_A, DASH));
        }
    }

    @Test
    public void testPaste_MergeTwoDifferentFileStdin_Parallel() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_B);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream, DASH, FILE_B));
        }
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_B_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false, inStream, FILE_B, DASH));
        }
    }

    @Test
    public void testPaste_MergeThreeSameFileStdin_Parallel() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,DASH, FILE_A, FILE_A));
        }

        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,FILE_A, DASH, FILE_A));
        }

        // Double files, stdin Index at 2
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,FILE_A, FILE_A, DASH));
        }
    }

    @Test
    public void testPaste_MergeThreeDifferentFileStdin_Parallel() throws Exception {
        // Double files, stdin Index at 0
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_B_C);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,DASH, FILE_B, FILE_C));
        }

        try (InputStream inStream = new FileInputStream(FILE_B)) {
            String expected = getExpectedResult(FILE_A_B_C);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,FILE_A, DASH, FILE_C));
        }

        try (InputStream inStream = new FileInputStream(FILE_C)) {
            String expected = getExpectedResult(FILE_A_B_C);
            assertEquals(expected, pasteApp.mergeFileAndStdin(false,inStream,FILE_A, FILE_B, DASH));
        }
    }

    @Test
    public void testPaste_MergeMultipleSameFile_Serial() throws Exception {
        // Double files
        String expected = getExpectedResult(FILE_A_A_S);
        assertEquals(expected, pasteApp.mergeFile(true,FILE_A, FILE_A));

        // Triple files
        expected = getExpectedResult(FILE_A_A_A_S);
        assertEquals(expected, pasteApp.mergeFile(true,FILE_A, FILE_A, FILE_A));
    }

    @Test
    public void testPaste_MergeMultipleDifferentFile_Serial() throws Exception {
        // Double files
        String expected = getExpectedResult(FILE_A_B_S);
        assertEquals(expected, pasteApp.mergeFile(true,FILE_A, FILE_B));

        // Triple files
        expected = getExpectedResult(FILE_A_B_C_S);
        assertEquals(expected, pasteApp.mergeFile(true,FILE_A, FILE_B, FILE_C));
    }

    @Test
    public void testPaste_MergeTwoSameFileStdin_Serial() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true, inStream, DASH, FILE_A));
        }

        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream, FILE_A, DASH));
        }
    }

    @Test
    public void testPaste_MergeTwoDifferentFileStdin_Serial() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_B_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream, DASH, FILE_B));
        }
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_B_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true, inStream, FILE_B, DASH));
        }
    }

    @Test
    public void testPaste_MergeThreeSameFileStdin_Serial() throws Exception {
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,DASH, FILE_A, FILE_A));
        }

        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,FILE_A, DASH, FILE_A));
        }

        // Double files, stdin Index at 2
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_A_A_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,FILE_A, FILE_A, DASH));
        }
    }

    @Test
    public void testPaste_MergeThreeDifferentFileStdin_Serial() throws Exception {
        // Double files, stdin Index at 0
        try (InputStream inStream = new FileInputStream(FILE_A)) {
            String expected = getExpectedResult(FILE_A_B_C_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,DASH, FILE_B, FILE_C));
        }

        try (InputStream inStream = new FileInputStream(FILE_B)) {
            String expected = getExpectedResult(FILE_A_B_C_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,FILE_A, DASH, FILE_C));
        }

        try (InputStream inStream = new FileInputStream(FILE_C)) {
            String expected = getExpectedResult(FILE_A_B_C_S);
            assertEquals(expected, pasteApp.mergeFileAndStdin(true,inStream,FILE_A, FILE_B, DASH));
        }
    }


}
