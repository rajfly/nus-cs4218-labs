package sg.edu.nus.comp.cs4218.impl.app.ef1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import sg.edu.nus.comp.cs4218.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.app.*;
import sg.edu.nus.comp.cs4218.impl.util.*;
import sg.edu.nus.comp.cs4218.testutils.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.STRING_NEWLINE;

public class LsApplicationTest {

    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private LsApplication lsApp;
    private ByteArrayOutputStream stdout;
    private ByteArrayInputStream stdin;
    private static final String DIR1 = "dir1";
    private static final String DIR2 = "dir2";
    private static final String FILE1 = "file1.txt";
    private static final String FILE2 = "file2.txt";

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        lsApp = new LsApplication();
        stdout = new ByteArrayOutputStream();
        stdin = new ByteArrayInputStream("".getBytes());
        Environment.currentDirectory = tempDir.toString();
    }

    @AfterAll
    static void reset() {
        Environment.currentDirectory = ORIGINAL_DIR;
    }

    @Test
    void run_NullArgs_ThrowsLsException() {
        assertThrows(LsException.class, () -> lsApp.run(null, stdin, stdout));
    }

    @Test
    void run_NullOutputStream_ThrowsLsException() {
        assertThrows(LsException.class, () -> lsApp.run(new String[]{}, System.in, null));
    }

    @Test
    void test_NoFlags_ListFiles() throws Exception {
        Files.createDirectory(tempDir.resolve(DIR1));
        Files.createFile(tempDir.resolve(FILE1));
        Files.createFile(tempDir.resolve(FILE2));

        lsApp.run(new String[]{}, stdin, stdout);

        String expectedOutput = DIR1 + STRING_NEWLINE + FILE1 + STRING_NEWLINE + FILE2;
        String actualOutput = stdout.toString().trim();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void test_NonExistentFile_ThrowsLsException() throws AbstractApplicationException {
        String[] args = {"nonexistentfile.txt"};
        lsApp.run(args, stdin, stdout);

        assertTrue(stdout.toString().contains("No such file or directory"));
    }

    @Test
    void test_IllegalFlag_ThrowsLsException() {
        Throwable thrown = assertThrows(LsException.class, () -> lsApp.run(new String[]{"-z"}, null, stdout));
        assertTrue(thrown.getMessage().contains("-z: " + ERR_INVALID_FLAG));
    }

    @Test
    void test_LegalFlags_DoesNotThrowLsException() {
        String[] args = {"-R", "-X"};
        Environment.currentDirectory = System.getProperty("user.dir");
        assertDoesNotThrow(() -> {
            lsApp.run(args, System.in, stdout);
        });
    }

    @Test
    void test_WithXFlag_SortedOutput() throws Exception {
        Files.createFile(tempDir.resolve(FILE1));
        Files.createFile(tempDir.resolve("file2.docx"));
        Files.createFile(tempDir.resolve("file3.pdf"));
        Files.createFile(tempDir.resolve("file4.zip"));
        Files.createFile(tempDir.resolve("file5"));
        Files.createFile(tempDir.resolve("file6.xlsx"));
        Files.createFile(tempDir.resolve("file7"));

        lsApp.run(new String[]{"-X"}, stdin, stdout);

        String expectedOutput = "file5" + STRING_NEWLINE + "file7" + STRING_NEWLINE + "file2.docx" + STRING_NEWLINE +
                "file3.pdf" + STRING_NEWLINE + "file1.txt" + STRING_NEWLINE + "file6.xlsx" + STRING_NEWLINE + "file4.zip";
        String actualOutput = stdout.toString().trim();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void test_WithRFlag_ListRecursiveFiles() throws Exception {
        Path dir1 = Files.createDirectory(tempDir.resolve(DIR1));
        Files.createFile(dir1.resolve(FILE1));
        Path dir2 = Files.createDirectory(dir1.resolve(DIR2));
        Files.createFile(dir2.resolve(FILE2));

        lsApp.run(new String[]{"-R", "dir1"}, stdin, stdout);

        String expectedOutput = DIR1 + ":" + STRING_NEWLINE + DIR2 + STRING_NEWLINE + FILE1 + STRING_NEWLINE +
                STRING_NEWLINE + "dir1\\dir2:" + STRING_NEWLINE + FILE2;
        String actualOutput = stdout.toString().trim();
        assertEquals(expectedOutput, actualOutput.trim());
    }
}
