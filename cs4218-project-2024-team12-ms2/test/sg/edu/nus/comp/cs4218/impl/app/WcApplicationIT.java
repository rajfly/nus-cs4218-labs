package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

public class WcApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private static final String WC_HEADER = "wc: ";
    private static final String RESOURCES = Paths.get("test", "resources", "wc").toString();
    private static final String ONE = "words1.txt";
    private static final String TWO = "words2.txt";
    private static final String THREE = "words3.txt";
    private static final String NOT_EXIST = "notExist.txt";
    private static final String NUMBER_FORMAT = " %7d";
    private final WcInterface app = new WcApplication();

    private OutputStream outputStream;

    @Test
    void run_NullStdout_ShouldThrowWcException() {
        String[] args = {Paths.get(RESOURCES, ONE).toString()};
        WcException exception = assertThrows(WcException.class, () -> app.run(args, System.in, null));
        assertEquals(String.format("%s%s", WC_HEADER, ERR_NULL_STREAMS), exception.getMessage());
    }

    @Test
    void run_NullArgs_ShouldThrowWcException() {
        WcException exception = assertThrows(WcException.class, () -> app.run(null, System.in, new ByteArrayOutputStream()));
        assertEquals(String.format("%s%s", WC_HEADER, ERR_NULL_ARGS), exception.getMessage());
    }

    @Test
    void run_InvalidArgs_ShouldThrowWcException() {
        String[] args = {"-x", Paths.get(RESOURCES, ONE).toString()};
        WcException exception = assertThrows(WcException.class, () -> app.run(args, System.in, new ByteArrayOutputStream()));
        assertEquals(String.format("%s%s", WC_HEADER, ERR_INVALID_FLAG), exception.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_ValidFile_ShouldNotThrowWcException() {
        String path = Paths.get(RESOURCES, ONE).toString();
        String[] args = {path};
        outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> app.run(args, System.in, outputStream));
        String expected = String.format(NUMBER_FORMAT + NUMBER_FORMAT + NUMBER_FORMAT + " %s\n", 4, 14, 65,
                path);
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_DashArg_ShouldNotThrowWcException() {
        String[] args = {"-"};
        outputStream = new ByteArrayOutputStream();
        String input = Paths.get(RESOURCES, ONE).toString();
        assertDoesNotThrow(() -> app.run(args, IOUtils.openInputStream(input), outputStream));
        String expected = String.format(NUMBER_FORMAT + NUMBER_FORMAT + NUMBER_FORMAT + STRING_NEWLINE, 4, 14, 65);
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_Stdin_ShouldNotThrowWcException() {
        String[] args = {};
        outputStream = new ByteArrayOutputStream();
        String input = Paths.get(RESOURCES, ONE).toString();
        assertDoesNotThrow(() -> app.run(args, IOUtils.openInputStream(input), outputStream));
        String expected = String.format(NUMBER_FORMAT + NUMBER_FORMAT + NUMBER_FORMAT + STRING_NEWLINE, 4, 14, 65);
        assertEquals(expected, outputStream.toString());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void run_ValidInputDashAndInputFile_ShouldReturnCount() {
        String[] args = {STRING_DASH, String.valueOf(CHAR_REDIR_INPUT), Paths.get(RESOURCES, ONE).toString()};
        outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> app.run(args, System.in, outputStream));
        String expected = String.format(
                String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + STRING_NEWLINE +
                        String.format(NUMBER_FORMAT, 4) + String.format(NUMBER_FORMAT, 14) + String.format(NUMBER_FORMAT, 65) + " total") + STRING_NEWLINE;

        assertEquals(expected, outputStream.toString());
    }
}
