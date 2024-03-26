package sg.edu.nus.comp.cs4218.impl.app.ef2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.app.TeeApplication;

public class TeeApplicationTest {
    public TeeApplication teeApplication;
    private static final String[] TEEFILES = {"file1.txt", "file2.txt"};

    @BeforeEach
    void setUp() {
            teeApplication = new TeeApplication();
    }

    @Test
    void teeFromStdin_ValidInput_WritesToFile() throws AbstractApplicationException, IOException {
        String input = "Hello World\nHow are you?";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        teeApplication.teeFromStdin(false, inputStream, TEEFILES[0]);
        String fileContent = Files.readString(Paths.get(TEEFILES[0])).trim().replaceAll("\\r\\n", "\n");
        assertEquals(input, fileContent); // Compare with the input, not the output stream content
        Files.deleteIfExists(Paths.get(TEEFILES[0])); // Clean up created files
    }

    @Test
    void teeFromStdin_AppendsToExistingFiles_ShouldAppendToFile() throws AbstractApplicationException, IOException {
        // Create existing files with content
        String file1Content = "Existing Content in File 1";
        Files.write(Paths.get(TEEFILES[0]), file1Content.getBytes());

        // Prepare input
        String input = "New Content";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        // Execute command with -a option
        teeApplication.run(new String[]{"-a", TEEFILES[0]}, inputStream, System.out);

        // Verify that input is appended to existing files
        String exp1Content = file1Content + STRING_NEWLINE + input;
        String actual1Content = Files.readString(Paths.get(TEEFILES[0])).trim();
        assertEquals(exp1Content, actual1Content);

        // Clean up created files
        Files.deleteIfExists(Paths.get(TEEFILES[0]));
    }

    @Test
    void teeFromStdin_MultipleFiles_WritesToAllFiles() throws AbstractApplicationException, IOException {
        String input = "Hello\nWorld";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        // Create files if they don't exist
        for (String file : TEEFILES) {
            if (!Files.exists(Paths.get(file))) {
                Files.createFile(Paths.get(file));
            }
        }
        teeApplication.run(new String[]{TEEFILES[0], TEEFILES[1]}, inputStream, System.out);
        for (String file : TEEFILES) {
            String fileContent = Files.readString(Paths.get(file)).trim().replaceAll("\\r\\n", "\n");
            assertEquals(input, fileContent);
            Files.deleteIfExists(Paths.get(file)); // Clean up created files
        }
    }

    @Test
    void teeFromStdin_EmptyInput_DoesNotWriteToFiles() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        teeApplication.run(new String[]{}, inputStream, System.out);
        for (String file : TEEFILES) {
            assertFalse(Files.exists(Paths.get(file))); // Verify that files are not created
        }
    }

    @Test
    void teeFromStdin_SpecialCharacters_WritesCorrectlyToFiles() throws AbstractApplicationException, IOException {
        String input = "Special Characters: \n\t\\\"'`äöüß$€µ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        // Create files if they don't exist
        for (String file : TEEFILES) {
            if (!Files.exists(Paths.get(file))) {
                Files.createFile(Paths.get(file));
            }
        }
        teeApplication.run(new String[]{"file1.txt", "file2.txt"}, inputStream, System.out);
        for (String file : TEEFILES) {
            String fileContent = Files.readString(Paths.get(file)).trim().replaceAll("\\r\\n", "\n");
            assertEquals(input, fileContent);
            Files.deleteIfExists(Paths.get(file)); // Clean up created files
        }
    }

    @Test
    void teeFromStdin_InvalidFileName_ThrowsException() {
        InputStream inputStream = new ByteArrayInputStream("Tee".getBytes(StandardCharsets.UTF_8));
        assertThrows(TeeException.class, () -> teeApplication.teeFromStdin(false, inputStream, "/invalid/file/name"));
    }

    @Test
    void teeFromStdin_NullInputStream_ThrowsException() {
        assertThrows(TeeException.class, () -> teeApplication.teeFromStdin(false, null, "file1.txt", "file2.txt"));
    }

    @Test
    void teeFromStdin_InvalidOption_ThrowsException() {
        InputStream inputStream = new ByteArrayInputStream("Test".getBytes(StandardCharsets.UTF_8));
        assertThrows(TeeException.class, () -> teeApplication.run(new String[]{"-invalid"}, inputStream, System.out));
    }

    @Test
    void teeFromStdin_EmptyFileNamesAndInvalidOption_ThrowsException() {
        InputStream inputStream = new ByteArrayInputStream("Hello".getBytes(StandardCharsets.UTF_8));
        assertThrows(TeeException.class, () -> teeApplication.run(new String[]{"-invalid"}, inputStream, System.out));
    }

    @Test
    void teeFromStdin_EmptyFileNamesAndValidOptionAndInvalidFileName_ThrowsException() {
        InputStream inputStream = new ByteArrayInputStream("Empty".getBytes(StandardCharsets.UTF_8));
        assertThrows(TeeException.class, () -> teeApplication.run(new String[]{"-a", "/invalid/file/name"}, inputStream, System.out));
    }
}
