package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"PMD.ClassNamingConventions"})
class MvApplicationIT {

    private static final String TEST_DIRECTORY1 = "test/temp1";
    private static final String TEST_DIRECTORY2 = "test/temp2";
    private static final String TEST_FILE1_NAME = "testFile1.txt";
    private static final String TEST_FILE2_NAME = "testFile2.txt";
    private static final String TEST_FILE1_PATH1 = TEST_DIRECTORY1 + File.separator + TEST_FILE1_NAME;
    private static final String TEST_FILE2_PATH1 = TEST_DIRECTORY1 + File.separator + TEST_FILE2_NAME;
    private static final String TEST_FILE1_PATH2 = TEST_DIRECTORY2 + File.separator + TEST_FILE1_NAME;

    private final MvApplication app = new MvApplication();

    @BeforeEach
    void setUp() throws Exception {
        FileUtils.createDirectory(TEST_DIRECTORY1);
        FileUtils.createDirectory(TEST_DIRECTORY2);

        FileUtils.createFile(TEST_FILE1_PATH1);
        FileUtils.writeFileContent(TEST_FILE1_PATH1, "I am test file 1");

        FileUtils.createFile(TEST_FILE2_PATH1);
        FileUtils.writeFileContent(TEST_FILE2_PATH1, "I am test file 2");
    }

    @AfterEach
    void tearDown() throws IOException {
        FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY1));
        FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY2));
    }

    @Test
    void run_InvalidFlagValidArgsValidInputAndOutputStream_ShouldThrowException() {
        String[] args = {"-a", TEST_FILE1_PATH1, TEST_FILE1_PATH2};
        assertThrows(MvException.class, () -> app.run(args, System.in, new ByteArrayOutputStream()));
    }

    @Test
    void run_NoFlagEmptyArgsValidInputAndOutputStream_ShouldThrowException() {
        String[] args = {""};
        assertThrows(MvException.class, () -> app.run(args, System.in, new ByteArrayOutputStream()));
    }

    @Test
    void run_NoFlagNullArgsValidInputAndOutputStream_ThrowsException() {
        assertThrows(MvException.class, () -> app.run(null, System.in, new ByteArrayOutputStream()));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    @DisplayName("mv test/temp1/testFile1.txt test/temp2/")
    void run_NoFlagValidArgsValidInputAndOutputStreamWithOverwrite_ShouldMoveInputFileToDestFolder() throws MvException {
        String[] args = {TEST_FILE1_PATH1, TEST_DIRECTORY2};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        app.run(args, System.in, outputStream);
        assertEquals(TEST_FILE1_PATH1 + " -> " + TEST_FILE1_PATH2 + System.lineSeparator(), outputStream.toString());
        assertFalse(new File(TEST_FILE1_PATH1).exists());
        assertTrue(new File(TEST_FILE1_PATH2).exists());
    }

    @Test
    @DisplayName("mv -n test/temp1/testFile1.txt test/temp1/testFile2.txt")
    void run_NFlagValidArgsValidInputAndOutputStreamNoOverWrite_ShouldNotRenameDestFile() throws MvException {
        String[] args = {"-n", TEST_FILE1_PATH1, TEST_FILE2_PATH1};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        app.run(args, System.in, outputStream);
        assertTrue(new File(TEST_FILE1_PATH1).exists(), "Source file should remain after no-overwrite operation.");
        assertTrue(new File(TEST_FILE2_PATH1).exists(), "Destination file should remain after no-overwrite operation.");
        assertEquals("", outputStream.toString().trim(), "There should be no output for no-overwrite operation.");
    }

    @Test
    @DisplayName("mv test/temp1/testFile1.txt test/temp1/testFile2.txt")
    void run_NoFlagValidArgsValidInputAndOutputStreamWithOverWrite_ShouldRenameInputFile() throws MvException, IOException {
        // Ensures different target for renaming to test overwriting explicitly
        String overwriteFilePath = TEST_DIRECTORY1 + File.separator + TEST_FILE2_NAME;
        FileUtils.writeFileContent(overwriteFilePath, "Content to be overwritten");

        String[] args = {TEST_FILE1_PATH1, overwriteFilePath};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        app.run(args, System.in, outputStream);
        assertEquals(TEST_FILE1_PATH1 + " -> " + overwriteFilePath, outputStream.toString().trim());
        assertFalse(new File(TEST_FILE1_PATH1).exists(), "Source file should not exist after successful rename.");
        assertTrue(new File(overwriteFilePath).exists(), "Destination file should exist and be overwritten.");
    }

}
