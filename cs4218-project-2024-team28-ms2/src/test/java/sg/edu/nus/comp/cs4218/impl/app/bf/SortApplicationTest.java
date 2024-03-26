package sg.edu.nus.comp.cs4218.impl.app.bf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_FLAG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;

public class SortApplicationTest {
    public SortApplication sortApplication;
    private static final String LINE_REGEX = "\\r\\n";
    private static final String SORT_EXCEPTION = "sort: ";
    private Path tempDir;

    @BeforeAll
    static void initialSetup() {
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @BeforeEach
    void setUp() throws IOException {
        sortApplication = new SortApplication();
        tempDir = Files.createTempDirectory("sort-application-test");
    }

    @Test
    void sortFromFiles_ValidInput_ReturnsSortedOutput() throws AbstractApplicationException, IOException {
        String sortContent = "10\n1\n2";
        String[] files = {"sort.txt"};
        // Create files if they don't exist
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
        }
        Files.write(Paths.get(files[0]), sortContent.getBytes());
        String expectedOutput = "1\n2\n10";
        String actualOutput = sortApplication.sortFromFiles(true, false, false, files)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void sortFromFiles_EmptyFile_ReturnsEmptyOutput() throws AbstractApplicationException, IOException {
        String[] files = {"empty.txt"};
        // Create files if they don't exist
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
        }
        String expectedOutput = "";
        String actualOutput = sortApplication.sortFromFiles(true, false, false, files);
        assertEquals(expectedOutput, actualOutput);
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void sortFromFiles_SpecialCharactersInFile_ReturnsSortedOutput() throws AbstractApplicationException, IOException {
        String input = "!\n+\n*\n#\n$\n)\n%\n&\n'\n(";
        String[] files = {"sortSpecialChars.txt"};
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
            Files.write(Paths.get(files[0]), input.getBytes());
        }
        String expectedOutput = "!\n#\n$\n%\n&\n'\n(\n)\n*\n+";
        String actualOutput = sortApplication.sortFromFiles(true, false, false, files)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void sortFromFiles_NegativeNumbersInFile_ReturnsSortedOutput() throws AbstractApplicationException, IOException {
        String input = "-5\n-1\n-3\n2\n6\n4";
        String[] files = {"sortNegative.txt"};
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
            Files.write(Paths.get(files[0]), input.getBytes());
        }
        String expectedOutput = "-5\n-3\n-1\n2\n4\n6";
        String actualOutput = sortApplication.sortFromFiles(true, false, false, files)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void sortFromFiles_MixOfCharactersInFile_ReturnsSortedOutput() throws AbstractApplicationException, IOException {
        String input = "!\n+\n*\n1\n3\n2\nHello\nWorld\njames\nmary";
        String[] files = {"sortMixOfChars.txt"};
        if (!Files.exists(Paths.get(files[0]))) {
            Files.createFile(Paths.get(files[0]));
            Files.write(Paths.get(files[0]), input.getBytes());
        }
        String expectedOutput = "World\nmary\njames\nHello\n3\n2\n1\n+\n*\n!";
        String actualOutput = sortApplication.sortFromFiles(true, true, true, files)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
        Files.deleteIfExists(Paths.get(files[0]));
    }

    @Test
    void sortFromFiles_MultipleFiles_ReturnsMergedAndSortedOutput() throws AbstractApplicationException, IOException {
        String sort1 = "9\n8\n7\n6\n5\n4\n3\n2\n1";
        String sort2 = "19\n18\n17\n16\n15\n14\n13\n12\n11";
        String[] files = {"sort1.txt", "sort2.txt"};
        // Create files if they don't exist
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                Files.createFile(Paths.get(file));
            }
        }
        Files.write(Paths.get(files[0]), sort1.getBytes());
        Files.write(Paths.get(files[1]), sort2.getBytes());
        String expectedOutput = "1\n2\n3\n4\n5\n6\n7\n8\n9\n11\n12\n13\n14\n15\n16\n17\n18\n19";
        String actualOutput = sortApplication.sortFromFiles(true, false, false, files)
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
        // Clean up created files
        for (String file : files) {
            Files.deleteIfExists(Paths.get(file));
        }
    }

    @Test
    void sortFromStdin_ValidInput_ReturnsSortedOutput() throws AbstractApplicationException, IOException {
        String input = "3\n1\n4\n2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String expectedOutput = "1\n2\n3\n4";
        sortApplication.run(new String[]{}, inputStream, outputStream);
        String actualOutput = outputStream.toString(StandardCharsets.UTF_8.name()).trim()
                .replaceAll(LINE_REGEX, "\n");
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void sortFromStdin_EmptyInput_ReturnsEmptyOutput() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        String expectedOutput = "";
        String actualOutput = sortApplication.sortFromStdin(false, false, false, inputStream);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdin_SpecialCharactersInInput_ReturnsSortedOutput() throws AbstractApplicationException {
        String input = "! # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \\ ] ^ _ ` { | } ~";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        String expectedOutput = "! # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \\ ] ^ _ ` { | } ~";
        String actualOutput = sortApplication.sortFromStdin(false, false, false, inputStream);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdin_NullInputStream_ThrowsException() {
        assertThrows(SortException.class, () -> sortApplication.sortFromStdin(false, false, false, null));
    }

    @Test
    void sortFromFiles_NonExistentFile_ThrowsException() {
        String[] files = {"nonexistent.txt"};
        assertThrows(SortException.class, () -> sortApplication.sortFromFiles(false, false, false, files));
    }

    @Test
    void sortFromFiles_InputIsDirectory_ShouldThrowException() {
        String[] files = new String[]{tempDir.toString()};
        Throwable err = assertThrows(SortException.class, () -> {
            sortApplication.sortFromFiles(false, false, false, files);
        });
        assertEquals(SORT_EXCEPTION + ERR_IS_DIR, err.getMessage());
    }

    @Test
    void sortFromFiles_NullFileArray_ThrowsException() {
        assertThrows(SortException.class, () -> sortApplication.sortFromFiles(false, false, false, (String[]) null));
    }

    @Test
    void run_NullArgs_ShouldThrow() {
        Throwable err = assertThrows(SortException.class, () -> {
            sortApplication.run(null, null, null);
        });

        assertEquals(SORT_EXCEPTION + ERR_NULL_ARGS, err.getMessage());

    }

    @Test
    void run_NullOutputStream_ShouldThrow() {
        Throwable err = assertThrows(SortException.class, () -> {
            sortApplication.sortFromStdin(false, false, false, null);
        });

        assertEquals(SORT_EXCEPTION + ERR_NULL_STREAMS, err.getMessage());
    }

    @Test
    void run_InvalidArguments_ShouldThrow() throws IOException {
        String[] invalidArgs = new String[]{"-i", "filename.txt"};
        Path outputFile = Files.createTempFile(tempDir, "output", ".txt");
        Throwable err = assertThrows(SortException.class, () -> {
            sortApplication.run(invalidArgs, null, Files.newOutputStream(outputFile));
        });

        assertEquals(SORT_EXCEPTION + "-i: " + ERR_INVALID_FLAG , err.getMessage());
    }

}
