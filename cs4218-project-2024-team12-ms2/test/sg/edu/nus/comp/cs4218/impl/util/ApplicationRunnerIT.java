package sg.edu.nus.comp.cs4218.impl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_APP;

class ApplicationRunnerIT { //NOPMD - suppressed ClassNamingConventions

    ApplicationRunner applicationRunner = new ApplicationRunner();
    ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + StringUtils.fileSeparator() + TEMP_DIR + StringUtils.fileSeparator();
    private static final String HELLO_WORLD = "Hello World";

    private static String initialDirectory;

    @BeforeEach
    void setUp() {
        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        initialDirectory = Environment.currentDirectory;
        try {
            FileUtils.createDirectory(TEMP_DIR_PATH);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    void cleanUp() {
        Environment.currentDirectory = initialDirectory;
        try {
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (Exception e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    @SneakyThrows
    void runApp_LsApp_ShouldReturnCorrectOutput() {
        String[] args = new String[0];

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_LS, args, System.in, System.out));

        assertFalse(outputStream.toString().isEmpty());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void runApp_WcApp_ShouldReturnCorrectOutput() {
        String wcFile = TEMP_DIR_PATH + "wc.txt";
        String content = "First line" + StringUtils.STRING_NEWLINE + "Second line";
        FileUtils.createFile(wcFile);
        FileUtils.writeFileContent(wcFile, content);

        String[] args = new String[]{wcFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_WC, args, inputStream, outputStream));

        String numberFormat = " %7d";
        String expectedOutput = String.format("%s%s%s %s%n",
                String.format(numberFormat, 1),
                String.format(numberFormat, 4),
                String.format(numberFormat, 22),
                wcFile);
        String actualOutput = outputStream.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @SneakyThrows
    void runApp_EchoApp_ShouldReturnCorrectOutput() {
        String[] args = new String[]{HELLO_WORLD};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_ECHO, args, inputStream, outputStream));

        assertEquals(HELLO_WORLD + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_ExitApp_ShouldExitWithoutException() {
        String[] args = new String[0];

        assertThrows(ExitException.class, () -> applicationRunner.runApp(APP_EXIT, args, inputStream, outputStream));
    }

    @Test
    @SneakyThrows
    void runApp_GrepApp_ShouldReturnCorrectOutput() {
        String grepFile = TEMP_DIR_PATH + "grep.txt";
        FileUtils.createFile(grepFile);
        FileUtils.writeFileContent(grepFile, HELLO_WORLD);

        String[] args = new String[]{"Hello", grepFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_GREP, args, inputStream, outputStream));

        assertEquals(HELLO_WORLD + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_CdApp_ShouldReturnCorrectOutput() {
        String dirPath = TEMP_DIR_PATH + "testDir";
        FileUtils.createDirectory(dirPath);
        String[] args = new String[]{dirPath};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_CD, args, inputStream, outputStream));
    }

    @Test
    @SneakyThrows
    void runApp_SortApp_ShouldReturnCorrectOutput() {
        String sortFile = TEMP_DIR_PATH + "sort.txt";
        FileUtils.createFile(sortFile);
        FileUtils.writeFileContent(sortFile, "c" + StringUtils.STRING_NEWLINE + "b" + StringUtils.STRING_NEWLINE + "a");

        String[] args = new String[]{sortFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_SORT, args, inputStream, outputStream));

        assertEquals("a" + StringUtils.STRING_NEWLINE + "b" + StringUtils.STRING_NEWLINE + "c" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_CatApp_ShouldReturnCorrectOutput() {
        String catFile = TEMP_DIR_PATH + "cat.txt";
        FileUtils.createFile(catFile);
        FileUtils.writeFileContent(catFile, HELLO_WORLD);

        String[] args = new String[]{catFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_CAT, args, inputStream, outputStream));

        assertEquals(HELLO_WORLD + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_PasteApp_ShouldReturnCorrectOutput() {
        String pasteFile1 = TEMP_DIR_PATH + "paste1.txt";
        String pasteFile2 = TEMP_DIR_PATH + "paste2.txt";
        FileUtils.createFile(pasteFile1);
        FileUtils.createFile(pasteFile2);
        FileUtils.writeFileContent(pasteFile1, "Hello");
        FileUtils.writeFileContent(pasteFile2, "World");

        String[] args = new String[]{pasteFile1, pasteFile2};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_PASTE, args, inputStream, outputStream));

        assertEquals("Hello\tWorld" + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_UniqApp_ShouldReturnCorrectOutput() {
        String uniqFile = TEMP_DIR_PATH + "uniq.txt";
        FileUtils.createFile(uniqFile);
        FileUtils.writeFileContent(uniqFile, HELLO_WORLD + StringUtils.STRING_NEWLINE + HELLO_WORLD);

        String[] args = new String[]{uniqFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_UNIQ, args, inputStream, outputStream));

        assertEquals(HELLO_WORLD + StringUtils.STRING_NEWLINE, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_CutApp_ShouldReturnCorrectOutput() {
        String cutFile = TEMP_DIR_PATH + "cut.txt";
        FileUtils.createFile(cutFile);
        FileUtils.writeFileContent(cutFile, HELLO_WORLD);

        String[] args = new String[]{"-c", "1-5", cutFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_CUT, args, inputStream, outputStream));

        String expected = "Hello";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @SneakyThrows
    void runApp_TeeApp_ShouldReturnCorrectOutput() {
        String teeFile = TEMP_DIR_PATH + "tee.txt";
        FileUtils.createFile(teeFile);
        FileUtils.writeFileContent(teeFile, HELLO_WORLD);
        inputStream = new ByteArrayInputStream(HELLO_WORLD.getBytes());

        String[] args = new String[]{teeFile};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_TEE, args, inputStream, outputStream));

        assertEquals(HELLO_WORLD + StringUtils.STRING_NEWLINE, outputStream.toString());
        assertEquals(HELLO_WORLD, String.join("", FileUtils.readFileContent(teeFile)));
    }

    @Test
    @SneakyThrows
    void runApp_RmApp_ShouldRemoveFile() {
        String rmFile = TEMP_DIR_PATH + "rm.txt";
        FileUtils.createFile(rmFile);
        FileUtils.writeFileContent(rmFile, HELLO_WORLD);

        String[] args = new String[]{rmFile};

        assertDoesNotThrow(() -> applicationRunner.runApp("rm", args, inputStream, outputStream));

        assertFalse(new File(rmFile).exists());
    }

    @Test
    @SneakyThrows
    void runApp_InvalidApp_ShouldThrowShellException() {
        String[] args = new String[0];
        String invalidApp = "invalidApp";

        ShellException exception = assertThrows(ShellException.class, () -> applicationRunner.runApp(invalidApp, args, inputStream, outputStream));

        assertEquals("shell: " + invalidApp + ": " + ERR_INVALID_APP, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void runApp_MvApp_ShouldMoveFile() {
        String mvFile = TEMP_DIR_PATH + "mv.txt";
        String mvFileNew = TEMP_DIR_PATH + "mvNew.txt";
        FileUtils.createFile(mvFile);
        FileUtils.writeFileContent(mvFile, HELLO_WORLD);
        assertTrue(new File(mvFile).exists());
        assertFalse(new File(mvFileNew).exists());

        String[] args = new String[]{mvFile, mvFileNew};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_MV, args, inputStream, outputStream));

        assertFalse(new File(mvFile).exists());
        assertTrue(new File(mvFileNew).exists());
    }

    @Test
    @SneakyThrows
    void runApp_MkdirApp_ShouldCreateDirectory() {
        String dirPath = TEMP_DIR_PATH + "testDir";
        assertFalse(new File(dirPath).exists());

        String[] args = new String[]{dirPath};

        assertDoesNotThrow(() -> applicationRunner.runApp(APP_MKDIR, args, inputStream, outputStream));

        assertTrue(new File(dirPath).exists());
        assertTrue(new File(dirPath).isDirectory());
    }

}
