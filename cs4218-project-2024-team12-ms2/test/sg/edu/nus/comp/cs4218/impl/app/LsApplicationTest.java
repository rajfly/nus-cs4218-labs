package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;
import sg.edu.nus.comp.cs4218.impl.util.RegexArgument;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.io.CleanupMode.ON_SUCCESS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_ASTERISK;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class LsApplicationTest {
    public static final String DIR_ROOT = "dir";
    public static final String DIR_A = "dirA";
    public static final String DIR_B = "dirB";

    public static final String FILE_ROOT_1 = "file1";

    public static final String FILE_A_1 = "fileA1";
    public static final String FILE_A_2 = "fileA2";

    public static final String FILE_B_1 = "fileB1";
    public static final String FILE_B_2 = "fileB2";
    public static final String DIR_SEPARATOR = ":" + StringUtils.STRING_NEWLINE;
    public static final String LS_CANNOT_ACCESS = "ls: cannot access ";
    private LsApplication lsApplication;
    static String originDirectory;

    @TempDir(cleanup = ON_SUCCESS)
    static Path sharedTempDir;
    @BeforeAll
    static void beforeAll() {
        try {
            originDirectory = Environment.currentDirectory;
            Environment.currentDirectory = sharedTempDir.toString();
            populateDirectory(sharedTempDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void afterAll() {
        Environment.currentDirectory = originDirectory;
    }
    @BeforeEach
    void setUp() {
        lsApplication = new LsApplication();
    }

    @Test
    @SneakyThrows
    void listFolderContent_NoArguments_Success() {
        String result = lsApplication.listFolderContent(false, false);
        String expected = buildExpectedLsOutput(true, new String[]{DIR_A, DIR_B, FILE_ROOT_1});
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_NoArgumentsRecursive_Success() {
        String result = lsApplication.listFolderContent(true, false);
        String expected = buildExpectedLsOutput(false, new String[]{"." + File.separator, DIR_A, DIR_B, FILE_ROOT_1}, new String[]{DIR_A, FILE_A_1, FILE_A_2}, new String[]{DIR_B, FILE_B_1, FILE_B_2});
        System.out.println(expected);
        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_NotRecursiveNotSort_Success() throws AbstractApplicationException {
        String result = lsApplication.listFolderContent(false, false, DIR_A);
        String expected = buildExpectedLsOutput(false, new String[]{DIR_A, FILE_A_1, FILE_A_2});
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_ListMultipleDirectories_Success() throws AbstractApplicationException {
        String result = lsApplication.listFolderContent(false, false, DIR_A, DIR_B);
        String expected = buildExpectedLsOutput(false, new String[]{DIR_A, FILE_A_1, FILE_A_2}, new String[]{DIR_B, FILE_B_1, FILE_B_2});
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_ListMultipleDirWithInvalid_Success() throws AbstractApplicationException {
        String result = lsApplication.listFolderContent(false, false, DIR_A, "invalid", DIR_B);
        String dirA = buildExpectedLsOutput(false, new String[]{DIR_A, FILE_A_1, FILE_A_2});
        String invalid = LS_CANNOT_ACCESS + "'invalid': " + ErrorConstants.ERR_FILE_NOT_FOUND;
        String dirB = buildExpectedLsOutput(false, new String[]{DIR_B, FILE_B_1, FILE_B_2});
        String expected = dirA + STRING_NEWLINE + STRING_NEWLINE + invalid + STRING_NEWLINE + dirB;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_DirIncludesHiddenFile_ShouldNotShowHiddenFile() throws AbstractApplicationException {
        String result = lsApplication.listFolderContent(false, false, ".");
        String expected = buildExpectedLsOutput(false, new String[]{"." + File.separator, DIR_A, DIR_B, FILE_ROOT_1});
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void listFolderContent_InvalidPath_ThrowsException() {
        String result = lsApplication.listFolderContent(false, false, "invalid");
        assertEquals(LS_CANNOT_ACCESS + "'invalid': " + ErrorConstants.ERR_FILE_NOT_FOUND, result);
    }

    /**
     * Populates directory with a set of files and folders for testing
     *
     * @param dir the directory to populate
     */
    public static void populateDirectory(Path dir) throws IOException {
        // Create a directory structure like this:
        // dir
        // ├── dirA
        // │   ├── fileA1
        // │   └── fileA2
        // ├── dirB
        // │   ├── fileB1
        // │   └── fileB2
        // ├── file1
        // ├── .hidden

        Path innerDirA = Files.createDirectory(dir.resolve("dirA"));
        Path innerDirB = Files.createDirectory(dir.resolve("dirB"));

        Files.createFile(dir.resolve("file1"));

        Files.createFile(innerDirA.resolve("fileA1"));
        Files.createFile(innerDirA.resolve("fileA2"));
        Files.createFile(innerDirB.resolve("fileB1"));
        Files.createFile(innerDirB.resolve("fileB2"));

        Path hiddenFile = Files.createFile(dir.resolve(".hidden"));

        if (System.getProperty("os.name").startsWith("Windows")) {
            try {
                Files.setAttribute(hiddenFile, "dos:hidden", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Builds the expected output of the ls command
     *
     * @param isCWD       whether the command is run on the current working directory
     * @param directories each array contains expected output for a directory,
     *                    the first element in an array is the directory, the rest are the files
     * @return the expected output of the ls command
     */
    private String buildExpectedLsOutput(boolean isCWD, String[]... directories) {
        StringBuilder expected = new StringBuilder();
        if (isCWD) {
            for (String file: directories[0]) {
                expected.append(file).append(StringUtils.STRING_NEWLINE);
            }
            return expected.toString().trim();
        }
        for (String[] directory : directories) {
            expected.append(directory[0]).append(DIR_SEPARATOR);
            for (int i = 1; i < directory.length; i++) {
                expected.append(directory[i]).append(StringUtils.STRING_NEWLINE);
            }
            expected.append(StringUtils.STRING_NEWLINE);
        }
        return expected.toString().trim();
    }
}