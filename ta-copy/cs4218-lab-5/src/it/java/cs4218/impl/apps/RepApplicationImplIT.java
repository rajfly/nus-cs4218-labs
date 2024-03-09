package cs4218.impl.apps;

import cs4218.EnvironmentUtil;
import cs4218.FileUtil;
import cs4218.exceptions.RepException;
import cs4218.impl.parsers.RepArgsParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepApplicationImplIT {
    @TempDir
    Path tempDir;

    RepApplicationImpl repApplication;
    ByteArrayOutputStream out;

    @BeforeEach
    void setUp() throws IOException {
        EnvironmentUtil.currentDirectory = tempDir.toAbsolutePath().toString();
        Path tempFile = tempDir.resolve("temp-file.txt");
        Files.write(tempFile, "This is a temp file!".getBytes());
        this.out = new ByteArrayOutputStream();
        repApplication = new RepApplicationImpl(new RepArgsParserImpl(), System.in, this.out, new FileUtil());
    }

    @Test
    void run_TypicalValues_ReadsFromFileCorrectly() throws RepException {
        // Testing interaction between RepApplication and File system
        List<String> tokens = List.of("temp", "temporary", "temp-file.txt");
        this.repApplication.run(tokens);

        assertEquals("This is a temporary file!", this.out.toString());
    }

    @Test
    void run_FileDoesNotExist_ThrowsRepException() {
        List<String> tokens = List.of("temp", "temporary", "does-not-exist.txt");
        RepException ex = assertThrows(RepException.class, () -> this.repApplication.run(tokens));

        assertEquals("Invalid file path provided", ex.getMessage());
    }

    /*
    1) Do not test all paths.
    2) Test all cases that cannot be verified by unit tests here!
     */
}