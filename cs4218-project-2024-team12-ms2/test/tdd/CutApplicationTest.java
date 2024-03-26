package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.CutApplication;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CutApplicationTest {
    private CutApplication cutApplication;

    private static final String CUT_TEST_DIR = "test/resources/cut";
    private static final String TEST_ONE_FILE = CUT_TEST_DIR + "/test.txt";
    private static final String INPUT_STREAM = "Input stream";
    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        cutApplication = new CutApplication();
        inputStream = new ByteArrayInputStream(new byte[0]);
    }

    @Test
    void cutFromFiles_CutByChar_ShouldReturnExpected() {
        String expected = "Maple";

        assertDoesNotThrow(() -> {
            String result = cutApplication.cutFromFiles(true, false, List.of(new int[]{1, 5}), TEST_ONE_FILE);
            assertEquals(result, expected);
        });
    }

    @Test
    void cutFromFiles_CutByBytes_ShouldReturnExpected() {
        String expected = "Maple";

        assertDoesNotThrow(() -> {
            String result = cutApplication.cutFromFiles(false, true, List.of(new int[]{1, 5}), TEST_ONE_FILE);
            assertEquals(result, expected);
        });
    }

    @Test
    void cutFromStdIn_CutByChar_ShouldReturnExpected() {
        String expected = "Input";
        inputStream = new ByteArrayInputStream(INPUT_STREAM.getBytes());

        assertDoesNotThrow(() -> {
            String result = cutApplication.cutFromStdin(true, false, List.of(new int[]{1, 5}), inputStream);
            assertEquals(result, expected);
        });
    }

    @Test
    void cutFromStdIn_CutByBytes_ShouldReturnExpected() {
        String expected = " str";
        inputStream = new ByteArrayInputStream(INPUT_STREAM.getBytes());

        assertDoesNotThrow(() -> {
            String result = cutApplication.cutFromStdin(false, true, List.of(new int[]{6, 9}), inputStream);
            assertEquals(result, expected);
        });
    }


}
