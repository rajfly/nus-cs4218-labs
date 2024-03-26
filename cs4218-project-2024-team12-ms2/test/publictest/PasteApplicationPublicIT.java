package publictest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static publictest.testutils.TestStringUtils.CHAR_FILE_SEP;
import static publictest.testutils.TestStringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;

import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.app.PasteApplication;
import publictest.testutils.TestEnvironmentUtil;
import publictest.testutils.TestStringUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

public class PasteApplicationPublicIT {
    private static final String TEMP = "temp-paste";
    private static final String DIR = "dir";
    private static final String TEST_LINE = "Test line 1\nTest line 2\nTest line 3";
    public static final String EXPECTED_TEXT = "Test line 1\tTest line 2\tTest line 3";
    private static final String ERR_NO_SUCH_FILE = "paste: %s: No such file or directory" + STRING_NEWLINE;
    private static final Deque<Path> files = new ArrayDeque<>();
    private static Path TEMP_PATH;
    private static Path DIR_PATH;

    private PasteApplication pasteApplication;

    @BeforeEach
    void setUp() {
        pasteApplication = new PasteApplication();
    }

    @BeforeAll
    static void createTemp() throws NoSuchFieldException, IllegalAccessException, IOException {
        TEMP_PATH = Paths.get(TestEnvironmentUtil.getCurrentDirectory(), TEMP);
        Files.createDirectory(TEMP_PATH);
        DIR_PATH = Paths.get(TestEnvironmentUtil.getCurrentDirectory(), TEMP + TestStringUtils.CHAR_FILE_SEP + DIR);
        Files.createDirectory(DIR_PATH);
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(DIR_PATH);
        Files.delete(TEMP_PATH);
    }

    private void createFile(String name, String text) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        Files.write(path, text.getBytes(StandardCharsets.UTF_8));
        files.push(path);
    }

    private String[] toArgs(String flag, String... files) {
        List<String> args = new ArrayList<>();
        if (!flag.isEmpty()) {
            args.add("-" + flag);
        }
        for (String file : files) {
            if (file.equals("-")) {
                args.add(file);
            } else {
                args.add(Paths.get(TEMP, file).toString());
            }
        }
        return args.toArray(new String[0]);
    }

    @Test
    void run_SingleStdinNullStdout_ThrowsException() {
        InputStream inputStream = new ByteArrayInputStream(TEST_LINE.getBytes(StandardCharsets.UTF_8));
        assertThrows(PasteException.class, () -> pasteApplication.run(toArgs(""), inputStream, null));
    }

    @Test
    void run_NullStdinNullFilesNoFlag_ThrowsException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertThrows(PasteException.class, () -> pasteApplication.run(toArgs(""), null, output));
    }

    @Test
    void run_NullStdinNullFilesFlag_ThrowsException() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assertThrows(PasteException.class, () -> pasteApplication.run(toArgs("n"), null, output));
    }

    //mergeStdin cases
    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_SingleStdinNoFlag_DisplaysStdinContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(TEST_LINE.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs(""), inputStream, output);
        assertEquals((TEST_LINE + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }


    @Test
    void run_SingleStdinFlag_DisplaysNonParallelStdinContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(TEST_LINE.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs("s"), inputStream, output);
        assertEquals((EXPECTED_TEXT + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_SingleStdinDashNoFlag_DisplaysStdinContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(TEST_LINE.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs("", "-"), inputStream, output);
        assertEquals((TEST_LINE + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleStdinDashFlag_DisplaysNonParallelStdinContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(TEST_LINE.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs("s", "-"), inputStream, output);
        assertEquals((EXPECTED_TEXT + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleEmptyStdinNoFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs(""), inputStream, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleEmptyStdinFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String text = "";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        pasteApplication.run(toArgs("s"), inputStream, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    //mergeFiles cases
    @Test
    void run_NonexistentFileNoFlag_ShouldThrowException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String nonexistentFileName = "nonexistent_file.txt";
        PasteException e = assertThrows(PasteException.class,
                () -> pasteApplication.run(toArgs("", nonexistentFileName), System.in, output));
        String expectedOutput = String.format(ERR_NO_SUCH_FILE, TestEnvironmentUtil.getCurrentDirectory()
                + StringUtils.CHAR_FILE_SEP + TEMP
                + StringUtils.CHAR_FILE_SEP + nonexistentFileName).trim();
        assertEquals(expectedOutput, e.getMessage());
    }

    @Test
    void run_DirectoryNoFlag_ShouldThrowException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PasteException e = assertThrows(PasteException.class,
                () -> pasteApplication.run(toArgs("", DIR), System.in, output));
        String expectedOutput = String.format("paste: " + "%s: " + ERR_IS_DIR, TEMP + StringUtils.CHAR_FILE_SEP + DIR);
        assertEquals(expectedOutput, e.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_SingleFileNoFlag_DisplaysFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileA.txt";
        String text = TEST_LINE;
        createFile(fileName, text);
        pasteApplication.run(toArgs("", fileName), System.in, output);
        assertEquals((text + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleFileFlag_DisplaysNonParallelFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileB.txt";
        createFile(fileName, TEST_LINE);
        pasteApplication.run(toArgs("s", fileName), System.in, output);
        assertEquals((EXPECTED_TEXT + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleEmptyFileNoFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileC.txt";
        String text = "";
        createFile(fileName, text);
        pasteApplication.run(toArgs("", fileName), System.in, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleEmptyFileFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileD.txt";
        String text = "";
        createFile(fileName, text);
        pasteApplication.run(toArgs("s", fileName), System.in, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_SingleFileUnknownFlag_Throws() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName = "fileE.txt";
        createFile(fileName, TEST_LINE);
        assertThrows(PasteException.class, () -> pasteApplication.run(toArgs("a", fileName), System.in, output));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_MultipleFilesNoFlag_DisplaysMergedFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileF.txt";
        String fileName2 = "fileG.txt";
        String text1 = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        String text2 = "Test line 2.1\nTest line 2.2";
        String expectedText = "Test line 1.1\tTest line 2.1\nTest line 1.2\tTest line 2.2\nTest line 1.3";
        createFile(fileName1, text1);
        createFile(fileName2, text2);
        pasteApplication.run(toArgs("", fileName1, fileName2), System.in, output);
        assertEquals((expectedText + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_MultipleFilesFlag_DisplaysNonParallelMergedFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileH.txt";
        String fileName2 = "fileI.txt";
        String text1 = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        String text2 = "Test line 2.1\nTest line 2.2";
        String expectedText = "Test line 1.1\tTest line 1.2\tTest line 1.3\nTest line 2.1\tTest line 2.2";
        createFile(fileName1, text1);
        createFile(fileName2, text2);
        pasteApplication.run(toArgs("s", fileName1, fileName2), System.in, output);
        assertEquals((expectedText + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_MultipleEmptyFilesNoFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileJ.txt";
        String fileName2 = "fileK.txt";
        String text = "";
        createFile(fileName1, text);
        createFile(fileName2, text);
        pasteApplication.run(toArgs("", fileName1, fileName2), System.in, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void run_MultipleEmptyFilesFlag_DisplaysEmpty() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileName1 = "fileL.txt";
        String fileName2 = "fileM.txt";
        String text = "";
        createFile(fileName1, text);
        createFile(fileName2, text);
        pasteApplication.run(toArgs("s", fileName1, fileName2), System.in, output);
        assertEquals(text, output.toString(StandardCharsets.UTF_8));
    }

    //mergeFilesAndStdin cases
    @Test
    void run_SingleStdinNonexistentFileNoFlag_ThrowsException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes(StandardCharsets.UTF_8));
        String nonexistentFileName = "nonexistent_file.txt";
        PasteException e = assertThrows(PasteException.class,
                () -> pasteApplication.run(toArgs("", nonexistentFileName), inputStream, output));
        assertEquals(String.format(ERR_NO_SUCH_FILE, TestEnvironmentUtil.getCurrentDirectory() + StringUtils.CHAR_FILE_SEP
                        + TEMP + StringUtils.CHAR_FILE_SEP
                        + nonexistentFileName).trim(),
                e.getMessage());
    }

    @Test
    void run_SingleStdinDirectoryNoFlag_DisplaysMergedStdinFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes(StandardCharsets.UTF_8));
        String expectedText = String.format("paste: " + "%s: " + ERR_IS_DIR, TEMP + StringUtils.CHAR_FILE_SEP + DIR);
        PasteException e = assertThrows(PasteException.class,
                () -> pasteApplication.run(toArgs("", DIR, "-"), inputStream, output));
        assertEquals(expectedText, e.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_SingleStdinDashSingleFileNoFlag_DisplaysMergedStdinFileContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String stdinText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes(StandardCharsets.UTF_8));
        String fileName = "fileN.txt";
        String fileText = "Test line 2.1\nTest line 2.2";
        createFile(fileName, fileText);
        String expectedText = "Test line 1.1\tTest line 2.1\nTest line 1.2\tTest line 2.2\nTest line 1.3";
        pasteApplication.run(toArgs("", "-", fileName), inputStream, output);
        assertEquals((expectedText + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_SingleFileSingleStdinDashNoFlag_DisplaysNonParallelMergedFileStdinContents() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String fileText = "Test line 1.1\nTest line 1.2\nTest line 1.3";
        String fileName = "fileO.txt";
        createFile(fileName, fileText);
        String stdinText = "Test line 2.1\nTest line 2.2";
        InputStream inputStream = new ByteArrayInputStream(stdinText.getBytes(StandardCharsets.UTF_8));
        String expectedText = "Test line 1.1\tTest line 2.1\nTest line 1.2\tTest line 2.2\nTest line 1.3";
        pasteApplication.run(toArgs("", fileName, "-"), inputStream, output);
        assertEquals((expectedText + STRING_NEWLINE), output.toString(StandardCharsets.UTF_8));
    }
}
