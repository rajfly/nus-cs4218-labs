package sg.edu.nus.comp.cs4218.impl.app.ef1.cmd;


import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("PMD")
class GlobbingTest {
    private static final Path DIRECTORY = Paths.get("src","test", "java","sg","edu","nus", "comp" ,"cs4218", "impl","app", "util", "dummyTestFolder", "GlobbingTestFolder");
    private static final String RESOURCE_PATH = DIRECTORY.toString() + File.separator;
    private static final String FOLDER_WITH_FILE = RESOURCE_PATH + "folderWithFile";
    private static final String FWF_PATH = FOLDER_WITH_FILE + File.separator;
    private static final String FILE_1 = RESOURCE_PATH + "file1.txt";
    private static final String FILE_2 = RESOURCE_PATH + "file2.abc";
    private static final String FILE_3 = RESOURCE_PATH + "file3.tac";
    private static final String FWF_FILE_1 = FWF_PATH + "file1.txt";
    private static final String FWF_FILE_2 = FWF_PATH + "file2.abc";
    private static final String FWF_FILE_3 = FWF_PATH + "file3.tac";
    private static String emptyFolder;
    private static Path tempDir;

    private ArgumentResolver argumentResolver;

    @BeforeAll
    static void init() throws IOException {
        tempDir = Files.createTempDirectory(DIRECTORY.toAbsolutePath(), "emptyFolder");
        emptyFolder = RESOURCE_PATH + tempDir.getFileName();
    }

    @AfterAll
    static void tearDown() {
        tempDir.toFile().deleteOnExit();
    }

    @BeforeEach
    void setUp() {
        argumentResolver = new ArgumentResolver();
    }

    @Test
    void testResolveOneArgument_With_SingleAsterisk() throws AbstractApplicationException, ShellException, FileNotFoundException {
        String input = RESOURCE_PATH + "*";
        List<String> expected = Arrays.asList(emptyFolder, FOLDER_WITH_FILE, FILE_1, FILE_2, FILE_3);
        Collections.sort(expected);
        List<String> actual = argumentResolver.resolveOneArgument(input);
        assertEquals(expected, actual);
    }

    @Test
    void testResolveOneArgument_WithSingleAsterisk_Behind() throws AbstractApplicationException, ShellException, FileNotFoundException {
        String input = RESOURCE_PATH + "fi*";
        List<String> expected = Arrays.asList(FILE_1, FILE_2, FILE_3);
        Collections.sort(expected);
        List<String> actual = argumentResolver.resolveOneArgument(input);
        assertEquals(expected, actual);
    }

    @Test
    void testResolveOneArgument_WithSingleAsterisk_InFront() throws AbstractApplicationException, ShellException, FileNotFoundException {
        String input = RESOURCE_PATH + "*.txt";
        List<String> expected = Arrays.asList(FILE_1);
        Collections.sort(expected);
        List<String> actual = argumentResolver.resolveOneArgument(input);
        assertEquals(expected, actual);
    }

    @Test
    void testResolveOneArgument_WithSingleAsterisk_WithNoFileExist() throws AbstractApplicationException, ShellException, FileNotFoundException {
        String input = RESOURCE_PATH + "*.j";
        List<String> expected = Arrays.asList(input);
        Collections.sort(expected);
        List<String> actual = argumentResolver.resolveOneArgument(input);
        assertEquals(expected, actual);
    }

    @Test
    void testResolveOneArgument_WithSingleAsterisk_DisplayAllFilesInAFolder() throws AbstractApplicationException, ShellException, FileNotFoundException {
        String input = FWF_PATH + "*";
        List<String> expected = Arrays.asList(FWF_FILE_1, FWF_FILE_2, FWF_FILE_3);
        List<String> actual = argumentResolver.resolveOneArgument(input);
        assertEquals(expected, actual);
    }

}