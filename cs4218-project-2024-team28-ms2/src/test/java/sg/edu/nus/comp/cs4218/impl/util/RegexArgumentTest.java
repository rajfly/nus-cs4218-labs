package sg.edu.nus.comp.cs4218.impl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;

public class RegexArgumentTest {
    private RegexArgument regexArgument;

    @BeforeEach
    public void setUp() {
        regexArgument = new RegexArgument();
    }

    @Test
    public void regexArgument_TestAppend_ShouldReturnAAndFalseForRegex() {
        regexArgument.append('a');
        assertEquals("a", regexArgument.toString());
        assertFalse(regexArgument.isRegexArgument());
    }

    @Test
    public void regexArgument_TestAppendAsterisk_ShouldReturnTrueForRegexWithAsterisk() {
        regexArgument.appendAsterisk();
        assertEquals("*", regexArgument.toString());
        assertTrue(regexArgument.isRegexArgument());
    }

    @Test
    public void regexArgument_TestMergeWithRegexArgument_ShouldReturnRegexArguments() {
        RegexArgument other = new RegexArgument("bcd");
        regexArgument.merge(other);
        assertEquals("bcd", regexArgument.toString());
        assertFalse(regexArgument.isRegexArgument());
    }

    @Test
    public void regexArgument_TestMergeWithString_ShouldReturnGivenString() {
        regexArgument.merge("efg");
        assertEquals("efg", regexArgument.toString());
        assertFalse(regexArgument.isRegexArgument());
    }

    @Test
    public void regexArgument_TestNoRegexGlobFiles_ShouldReturnPlaintext() {
        List<String> globbedFiles = regexArgument.globFiles();
        assertEquals(1, globbedFiles.size());
        assertEquals("", globbedFiles.get(0));
    }

    @Test
    public void globFiles_NoRegex_ReturnsSinglePlainTextFile() {
        regexArgument.merge("file.txt");
        List<String> globbedFiles = regexArgument.globFiles();
        assertEquals(1, globbedFiles.size());
        assertTrue(globbedFiles.contains("file.txt"));
    }

    @Test
    public void globFiles_SingleAsterisk_ReturnsAllFilesInCurrentDirectory() throws IOException {
        // Create temporary directory
        Path tempDir = Files.createTempDirectory("testDir");

        // Create temporary files
        File file1 = new File(tempDir.toFile(), "file1.txt");
        File file2 = new File(tempDir.toFile(), "file2.txt");
        file1.createNewFile();
        file2.createNewFile();

        // Set current directory
        Environment.currentDirectory = tempDir.toString();

        // Merge regex pattern with asterisk
        regexArgument.appendAsterisk();

        // Test globFiles() method
        List<String> globbedFiles = regexArgument.globFiles();
        assertEquals(2, globbedFiles.size());
        assertTrue(globbedFiles.contains("file1.txt"));
        assertTrue(globbedFiles.contains("file2.txt"));

        // Delete temporary directory and files
        Files.deleteIfExists(file1.toPath());
        Files.deleteIfExists(file2.toPath());
        Files.deleteIfExists(tempDir);
    }

    @Test
    public void globFiles_EmptyRegexPattern_ReturnsSinglePlainTextFile() {
        regexArgument.merge(".*");
        List<String> globbedFiles = regexArgument.globFiles();
        assertEquals(1, globbedFiles.size());
        assertTrue(globbedFiles.contains(".*"));
    }

    @Test
    public void regexArgument_EmptyInput_ShouldReturnEmptyString() {
        RegexArgument regexArgument = new RegexArgument("");
        assertTrue(regexArgument.isEmpty());
        assertEquals("", regexArgument.toString());
    }

    @Test
    public void regexArgument_SpecialCharactersInput_ShouldMatchCorrectly() {
        RegexArgument regexArgument = new RegexArgument(".*[\\-+.*?]");
        assertFalse(regexArgument.isRegexArgument());
        assertEquals(".*[\\-+.*?]", regexArgument.toString());
    }

    @Test
    public void regexArgument_EscapeCharactersInput_ShouldMatchCorrectly() {
        RegexArgument regexArgument = new RegexArgument(".*\\\\test");
        assertFalse(regexArgument.isRegexArgument());
        assertEquals(".*\\\\test", regexArgument.toString());
    }

}
