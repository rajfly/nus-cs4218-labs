package sg.edu.nus.comp.cs4218.impl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.io.CleanupMode.ON_SUCCESS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_ASTERISK;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

class RegexArgumentTest {

    private final static String ASTERISK_REGEX = "[^" + fileSeparator() + "]*";
    private final static String DIR = "dir";
    private final static String DIR_A = "dirA";
    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private RegexArgument regexArgument;

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
        regexArgument = new RegexArgument();
    }

    @Test
    @SneakyThrows
    void append_StringWithAsterisk_ShouldAppendAsterisk() {
        appendToRegArg(regexArgument, "dir*");
        StringBuilder expected = new StringBuilder();
        for (char c : DIR.toCharArray()) {
            expected.append(Pattern.quote(String.valueOf(c)));
        }
        expected.append(ASTERISK_REGEX);
        assertEquals(expected.toString(), regexArgument.getRegex());
    }

    @Test
    @SneakyThrows
    void append_StringWithFileSeparatorAndAsterisk_ShouldMatch() {
        appendToRegArg(regexArgument, DIR + fileSeparator() + CHAR_ASTERISK);
        StringBuilder expected = new StringBuilder();
        for (char c : DIR.toCharArray()) {
            expected.append(Pattern.quote(String.valueOf(c)));
        }
        expected.append(Pattern.quote(fileSeparator())).append(ASTERISK_REGEX);
        Pattern pattern = Pattern.compile(expected.toString());
        assertTrue(pattern.matcher(DIR + fileSeparator() + FILE1).matches());
        assertTrue(pattern.matcher(DIR + fileSeparator() + FILE2).matches());
    }

    @Test
    @SneakyThrows
    void appendAsterisk_Asterisk_ShouldMatch() {
        regexArgument.appendAsterisk();
        Pattern pattern = Pattern.compile(regexArgument.getRegex());
        assertTrue(pattern.matcher(FILE1).matches());
        assertTrue(pattern.matcher(FILE2).matches());
    }

    @Test
    @SneakyThrows
    void appendAsterisk_FileWithAsterisk_ShouldMatch() {
        appendToRegArg(regexArgument, "file");
        regexArgument.appendAsterisk();
        Pattern pattern = Pattern.compile(regexArgument.getRegex());
        assertTrue(pattern.matcher(FILE1).matches());
        assertTrue(pattern.matcher(FILE2).matches());
        assertFalse(pattern.matcher("fil").matches());
    }

    @Test
    @SneakyThrows
    void toString_RegularPattern_ShouldReturnPlaintext() {
        appendToRegArg(regexArgument, DIR + fileSeparator() + CHAR_ASTERISK);
        assertEquals(DIR + fileSeparator() + CHAR_ASTERISK, regexArgument.toString());
    }

    @Test
    void merge_RegularPattern_ShouldMerge(){
        RegexArgument other = new RegexArgument("file");
        appendToRegArg(regexArgument,DIR + fileSeparator());
        regexArgument.merge(other);
        assertEquals(DIR + fileSeparator() + "file", regexArgument.toString());
    }

    @Test
    @SneakyThrows
    void globFiles_Wildcard_ShouldMatchFiles() {
        appendToRegArg(regexArgument, "" + CHAR_ASTERISK);
        List<String> expected = Files.list(sharedTempDir)
                .map(sharedTempDir::relativize)
                .map(Path::toString)
                .collect(Collectors.toList());
        assertListEqualsIgnoreOrder(expected, regexArgument.globFiles());
    }
    @Test
    void globFiles_RelPathNonWildcard_ShouldContainNothingButItself() {
        appendToRegArg(regexArgument, DIR);
        assertListEqualsIgnoreOrder(Arrays.asList(DIR), regexArgument.globFiles());
    }

    @Test
    void globFiles_RelPathWildcard_ShouldMatchDirectories() {
        appendToRegArg(regexArgument, "dir*");
        assertListEqualsIgnoreOrder(Arrays.asList(DIR_A, "dirB"), regexArgument.globFiles());
    }

    @Test
    void globFiles_RelPathWildcard_ShouldMatchInnerDirectoryA() {
        appendToRegArg(regexArgument, DIR_A);
        appendToRegArg(regexArgument, fileSeparator());
        appendToRegArg(regexArgument, CHAR_ASTERISK + "");
        assertListEqualsIgnoreOrder(Arrays.asList(DIR_A + fileSeparator() + "fileA1", DIR_A + fileSeparator()+ "fileA2"),
                regexArgument.globFiles());
    }

    @Test
    void globFiles_AbsPathNonWildcard_ShouldShowResults() {
        appendToRegArg(regexArgument, sharedTempDir.resolve(DIR).toString());
        assertListEqualsIgnoreOrder(Arrays.asList(sharedTempDir.resolve(DIR).toString()), regexArgument.globFiles());
    }

    /**
     * Appends a string to the regex argument
     *
     * @param regArg the regex argument
     * @param pattern the string to append
     */
    private void appendToRegArg(RegexArgument regArg, String pattern) {
        for (char c : pattern.toCharArray()) {
            if (c == CHAR_ASTERISK) {
                regArg.appendAsterisk();
            } else {
                regArg.append(c);
            }
        }
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

        Path innerDirA = Files.createDirectory(dir.resolve(DIR_A));
        Path innerDirB = Files.createDirectory(dir.resolve("dirB"));

        Files.createFile(dir.resolve(FILE1));

        Files.createFile(innerDirA.resolve("fileA1"));
        Files.createFile(innerDirA.resolve("fileA2"));
        Files.createFile(innerDirB.resolve("fileB1"));
        Files.createFile(innerDirB.resolve("fileB2"));

        Path hiddenFile = Files.createFile(dir.resolve(".hidden"));

        if (System.getProperty("os.name").toLowerCase(Locale.getDefault()).contains("win")) {
            try {
                Files.setAttribute(hiddenFile, "dos:hidden", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Asserts that two lists are equal, ignoring order
     *
     * @param expected the expected list
     * @param actual   the actual list
     */
    private void assertListEqualsIgnoreOrder(List<String> expected, List<String> actual) {
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}