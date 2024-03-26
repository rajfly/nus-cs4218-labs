package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SAME_FILE_FORMAT;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

@SuppressWarnings({"PMD.LongVariable"})
class MvApplicationTest {
    private static final String TEST_DIRECTORY_1 = "test/temp1";
    private static final String TEST_DIRECTORY_2 = "test/temp2";
    private static final String TEST_FILE_1_NAME = "testFile1.txt";
    private static final String TEST_FILE_2_NAME = "testFile2.txt";
    private static final String TEST_FILE_3_NAME = "testFile3.txt";
    private static final String NONEXISTENT_TEST_FILE_1 = "nonExistentTestFile1";
    private static final String NONEXISTENT_TEST_FILE_2 = "nonExistentTestFile2";
    private static final String TEST_FILE_1_CONTENT = "I am test file 1";
    private static final String TEST_FILE_2_CONTENT = "I am test file 2";
    private static final String TEST_FILE_3_CONTENT = "I am test file 3";
    private static final String PATH_1_TO_TEST_FILE_1 = TEST_DIRECTORY_1 + File.separator + TEST_FILE_1_NAME;
    private static final String PATH_2_TO_TEST_FILE_1 = TEST_DIRECTORY_2 + File.separator + TEST_FILE_1_NAME;
    private static final String PATH_2_TO_TEST_FILE_3 = TEST_DIRECTORY_2 + File.separator + TEST_FILE_3_NAME;
    private static final String PATH_1_TO_TEST_FILE_2 = TEST_DIRECTORY_1 + File.separator + TEST_FILE_2_NAME;
    private static final String PATH_1_TO_RENAMED_TEST_FILE_1 = TEST_DIRECTORY_1 + File.separator + "newTestFile1.txt";

    private final MvApplication app = new MvApplication();

    @BeforeEach
    void setUp() throws Exception {
        FileUtils.createDirectory(TEST_DIRECTORY_1);
        FileUtils.createDirectory(TEST_DIRECTORY_2);

        FileUtils.createFile(PATH_1_TO_TEST_FILE_1);
        FileUtils.writeFileContent(PATH_1_TO_TEST_FILE_1, TEST_FILE_1_CONTENT);

        FileUtils.createFile(PATH_1_TO_TEST_FILE_2);
        FileUtils.writeFileContent(PATH_1_TO_TEST_FILE_2, TEST_FILE_2_CONTENT);

        FileUtils.createFile(PATH_2_TO_TEST_FILE_3);
        FileUtils.writeFileContent(PATH_2_TO_TEST_FILE_3, TEST_FILE_3_CONTENT);
    }

    @AfterEach
    void tearDownAll() {
        try {
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY_1));
            FileUtils.removeFilesRecursive(new File(TEST_DIRECTORY_2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @Test
    void mvSrcFileToDestFile_ValidSrcFileAndDestFileWithOverwrite_ShouldRenameDestFile() throws MvException {
        String output = app.mvSrcFileToDestFile(true, PATH_1_TO_TEST_FILE_1, PATH_2_TO_TEST_FILE_3);

        assertEquals(String.format("%s -> %s", PATH_1_TO_TEST_FILE_1, PATH_2_TO_TEST_FILE_3), output);

        assertFalse(new File(PATH_1_TO_TEST_FILE_1).exists());
        assertTrue(new File(PATH_2_TO_TEST_FILE_3).exists());
    }
    @Test
    void mvSrcFileToDestFile_ValidSrcFileAndNonExistentDestFileWithOverwrite_ShouldRenameSrcFileToDestFile() throws MvException {
        String output = app.mvSrcFileToDestFile(true, PATH_1_TO_TEST_FILE_1, PATH_1_TO_RENAMED_TEST_FILE_1);

        assertEquals(
            String.format("%s -> %s%snewTestFile1.txt", PATH_1_TO_TEST_FILE_1, TEST_DIRECTORY_1, File.separator), output);

        assertFalse(new File(PATH_1_TO_TEST_FILE_1).exists());
        assertTrue(new File(PATH_1_TO_RENAMED_TEST_FILE_1).exists());
    }

    @Test
    void mvSrcFileToDestFile_NonExistentSrcFileAndNonExistentDestFileWithOverwrite_ShouldThrowException() {
        MvException exception = assertThrows(MvException.class, () -> app.mvSrcFileToDestFile(true, NONEXISTENT_TEST_FILE_1, NONEXISTENT_TEST_FILE_2));
        String actualMsg = exception.getMessage();
        assertEquals(String.format("mv: %s: %s", NONEXISTENT_TEST_FILE_1, ERR_FILE_NOT_FOUND), actualMsg);
    }

    @Test
    void mvSrcFileToDestFile_SameSrcFileAndDestFileWithOverwrite_ShouldThrowException() {
        Exception exception = assertThrows(MvException.class, () -> app.mvSrcFileToDestFile(true, PATH_1_TO_TEST_FILE_1,
            PATH_1_TO_TEST_FILE_1));
        String actualMsg = exception.getMessage();
        assertEquals( "mv: " + String.format(ERR_SAME_FILE_FORMAT, PATH_1_TO_TEST_FILE_1, PATH_1_TO_TEST_FILE_1), actualMsg);
    }

    @Test
    void mvSrcFileToDestFile_ValidSrcFileAndDestFileNoOverwrite_ShouldNotRenameDestFile() throws MvException {
        File destFile = new File(PATH_1_TO_TEST_FILE_2);
        assertTrue(destFile.exists());  // Ensures the destination file exists before the operation

        String output = app.mvSrcFileToDestFile(false, PATH_1_TO_TEST_FILE_1, PATH_1_TO_TEST_FILE_2);
        assertEquals("", output);  // Now expects the full paths

        File srcFile = new File(PATH_1_TO_TEST_FILE_1);
        assertTrue(srcFile.exists());  // Verifies the source file still exists post-operation
        assertTrue(destFile.exists());  // Verifies the destination file still exists post-operation
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void mvFilesToFolder_ValidSrcFileAndDestFolderWithOverwrite_ShouldMoveSrcFileToDestFolder() throws MvException {
        String output = app.mvFilesToFolder(true, TEST_DIRECTORY_2, PATH_1_TO_TEST_FILE_1);

        // The output should reflect the actual move operation's source and destination paths.
        assertEquals(PATH_1_TO_TEST_FILE_1 + " -> " + PATH_2_TO_TEST_FILE_1, output);

        // After moving, the file should no longer exist in the original location.
        File originalFile = new File(PATH_1_TO_TEST_FILE_1);
        assertFalse(originalFile.exists(), "File should not exist in the original location after moving.");

        // After moving, the file should exist in the new location.
        File movedFile = new File(PATH_2_TO_TEST_FILE_3);
        assertTrue(movedFile.exists(), "File should exist in the new location after moving.");
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void mvFilesToFolder_ValidMultipleSrcFileAndDestFolderWithOverwrite_ShouldMoveAllSrcFilesToDestFolder() throws MvException {
        // Moves files from TEST_DIRECTORY1 to TEST_DIRECTORY2
        String output = app.mvFilesToFolder(true, TEST_DIRECTORY_2, PATH_1_TO_TEST_FILE_1, PATH_1_TO_TEST_FILE_2);
        String expectedOutput = PATH_1_TO_TEST_FILE_1 + " -> " + PATH_2_TO_TEST_FILE_1 + System.lineSeparator() +
            PATH_1_TO_TEST_FILE_2 + " -> " + TEST_DIRECTORY_2 + File.separator + TEST_FILE_2_NAME;
        assertEquals(expectedOutput, output);

        // Verify that files no longer exist in their original location (TEST_DIRECTORY1)
        File originalFile1 = new File(PATH_1_TO_TEST_FILE_1);
        File originalFile2 = new File(PATH_1_TO_TEST_FILE_2);
        assertFalse(originalFile1.exists(), "File1 should not exist in the original location after moving.");
        assertFalse(originalFile2.exists(), "File2 should not exist in the original location after moving.");

        // Verify that files now exist in the destination directory (TEST_DIRECTORY2)
        File movedFile1 = new File(TEST_DIRECTORY_2, TEST_FILE_1_NAME);
        File movedFile2 = new File(TEST_DIRECTORY_2, TEST_FILE_2_NAME);
        assertTrue(movedFile1.exists(), "File1 should exist in the new location after moving.");
        assertTrue(movedFile2.exists(), "File2 should exist in the new location after moving.");
    }

    @Test
    void mvFilesToFolder_InvalidSrcFileWithOverwrite_ShouldThrowException() {
        Exception exception = assertThrows(MvException.class, () -> app.mvFilesToFolder(true, TEST_DIRECTORY_1, NONEXISTENT_TEST_FILE_1));
        String actualMsg = exception.getMessage();
        assertEquals("mv: " + NONEXISTENT_TEST_FILE_1 + ": " + ERR_FILE_NOT_FOUND, actualMsg);
    }


    @Test
    void mvFilesToFolder_ValidSrcFileWithNullDestFolderWithOverwrite_ShouldThrowException() {
        Exception exception = assertThrows(MvException.class, () -> app.mvFilesToFolder(true, null,
            PATH_1_TO_TEST_FILE_1));
        String actualMsg = exception.getMessage();
        assertEquals("mv: " + ERR_MISSING_ARG, actualMsg);
    }
}