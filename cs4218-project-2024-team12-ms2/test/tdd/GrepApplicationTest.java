package tdd;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GrepApplicationTest {
    private GrepApplication grepApplication;
    private static final String GREP_TEST_DIR = "test/resources/grep";
    private static final String TEST_ONE_FILE = GREP_TEST_DIR + "/brownfox.txt";
    private static final String KEYWORD_QUICK = "Quick";
    private static final String KEYWORD_JUMP = "jump";

    private static final String QUICK_CASE_ANS = "Quick zephyrs blow, vexing daft Jim." + StringUtils.STRING_NEWLINE;
    private static final String QUICK_ANS = "The quick brown fox jumps over the lazy dog." + StringUtils.STRING_NEWLINE +
            "A wizard's job is to vex chumps quickly in fog." + StringUtils.STRING_NEWLINE +
            "The five boxing wizards jump quickly." + StringUtils.STRING_NEWLINE +
            "How vexingly quick daft zebras jump!" + StringUtils.STRING_NEWLINE +
            "Quick zephyrs blow, vexing daft Jim." + StringUtils.STRING_NEWLINE;

    private static final String JUMP_ANS = "The quick brown fox jumps over the lazy dog." + StringUtils.STRING_NEWLINE +
            "The five boxing wizards jump quickly." + StringUtils.STRING_NEWLINE +
            "How vexingly quick daft zebras jump!" + StringUtils.STRING_NEWLINE +
            "Bright vixens jump; dozy fowl quack." + StringUtils.STRING_NEWLINE;

    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        grepApplication = new GrepApplication();
        File file = new File(TEST_ONE_FILE);
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            fail("Unable to read test file");
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            inputStream.close();
        } catch (Exception e) {
            fail("Unable to close input stream");
        }
    }

    @Test
    @SneakyThrows
    void grepFromFiles_KeywordCaseSensitive_Success() {
        String result = grepApplication.grepFromFiles(KEYWORD_QUICK, false, false, false, TEST_ONE_FILE);
        assertEquals(QUICK_CASE_ANS, result);
    }

    @Test
    @SneakyThrows
    void grepFromFiles_KeywordCaseInsenstive_Success() {
        String result = grepApplication.grepFromFiles(KEYWORD_QUICK, true, false, false, TEST_ONE_FILE);
        assertEquals(QUICK_ANS, result);
    }

    @Test
    @SneakyThrows
    void grepFromFiles_KeywordCaseInsensitiveAndCountLines_Success() {
        String result = grepApplication.grepFromFiles(KEYWORD_QUICK, true, true, false, TEST_ONE_FILE);
        String expected = "5" + StringUtils.STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void grepFromFiles_KeywordCaseInsensitiveAndCountLinesAndPrintFileName_Success() {
        String result = grepApplication.grepFromFiles(KEYWORD_QUICK, true, true, true, TEST_ONE_FILE);
        String expected = TEST_ONE_FILE + ": " + "5" + StringUtils.STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void grepFromFiles_InvalidFile_ShouldReportError() {
        String result = grepApplication.grepFromFiles(KEYWORD_QUICK, true, true, false, "invalid.txt");
        assertEquals("invalid.txt: " + ErrorConstants.ERR_FILE_NOT_FOUND + StringUtils.STRING_NEWLINE, result);
    }

    @Test
    @SneakyThrows
    void grepFromStdin_ValidKeyword_Success() {
        String result = grepApplication.grepFromStdin(KEYWORD_JUMP, false, false, false, inputStream);
        assertEquals(JUMP_ANS, result);
    }

    @Test
    @SneakyThrows
    void grepFromStdin_KeywordCaseSensitive_Success() {
        String result = grepApplication.grepFromStdin(KEYWORD_QUICK, false, false, false, inputStream);
        assertEquals(QUICK_CASE_ANS, result);
    }

    @Test
    @SneakyThrows
    void grepFromStdin_CountCaseInsensitive_Success() {
        String result = grepApplication.grepFromStdin(KEYWORD_QUICK, true, true, false, inputStream);
        String expected = "5" + StringUtils.STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void grepFromStdin_InvalidKeyword_ShouldGiveEmptyResult() {
        String result = grepApplication.grepFromStdin("invalid", false, false, false, inputStream);
        assertEquals("", result);
    }

    @Test
    @SneakyThrows
    void grepFromStdin_ClosedStream_ShouldNotThrowException() {
        inputStream = new ByteArrayInputStream(new byte[0]);
        inputStream.close();
        String result = grepApplication.grepFromStdin(KEYWORD_JUMP, false, false, false, inputStream);
        assertEquals("", result);
    }

    @Test
    @SneakyThrows
    void grepFromFileAndStdin_OnlyStdin_Success() {
        String result = grepApplication.grepFromFileAndStdin(KEYWORD_JUMP, false, false, false, inputStream, "-");
        assertEquals(JUMP_ANS, result);
    }

    @Test
    @SneakyThrows
    void grepFromFileAndStdin_OnlyFile_Success() {
        String result = grepApplication.grepFromFileAndStdin(KEYWORD_QUICK, false, false, false, null, TEST_ONE_FILE);
        String expected = "Quick zephyrs blow, vexing daft Jim." + StringUtils.STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void grepFromFileAndStdin_BothFileAndStdin_Success() {
        String result = grepApplication.grepFromFileAndStdin(KEYWORD_JUMP, false, false, false, inputStream, TEST_ONE_FILE);
        assertEquals(JUMP_ANS, result);
    }
}