package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_COLON;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FORWARDSLASH;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SPACE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import sg.edu.nus.comp.cs4218.Environment;

class PasteApplicationTest {
    private static final String INPUT_CONTENT = "Alice\nX\nBob\nY\nCharles\nZ";
    private final PasteApplication app = new PasteApplication();
    private InputStream stdin;
    private static final String FILE_A_PATH = "test/resources/paste/A.txt";

    @BeforeEach
    void setUp() {
        stdin = new ByteArrayInputStream(INPUT_CONTENT.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    @DisplayName("paste test/resources/paste/missingFile.txt")
    void mergeFile_FileInputDoesNotExist_ShouldThrowException() {
        String currentDir = Environment.currentDirectory;
        String missingFilePath = "test/resources/paste/missingFile.txt";
        Exception thrown = assertThrows(Exception.class, () -> app.mergeFile(false, missingFilePath));
        assertEquals("paste: " + currentDir + CHAR_FORWARDSLASH + missingFilePath
            + CHAR_COLON + CHAR_SPACE
            + ERR_FILE_NOT_FOUND, thrown.getMessage());
    }

    @Test
    @DisplayName("paste test/resources/paste")
    void mergeFile_NoFlagAndDirInput_ShouldThrowException() {
        String dirFile = "test/resources/paste";
        Exception thrown = assertThrows(Exception.class, () -> app.mergeFile(false, dirFile));
        String expected = String.format("paste: " + "%s: " + ERR_IS_DIR, dirFile);
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    @DisplayName("paste -s < <STDIN>")
    void mergeStdin_SFlagAndStdIn_ShouldPasteOnSingleLine() throws Exception {
        String result = app.mergeStdin(true, stdin);
        String expected = "Alice\tX\tBob\tY\tCharles\tZ";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("paste -s - A.txt < <STDIN>")
    void mergeFileAndStdin_SFlagWithDashWithFile_ShouldPasteEachInputContentOnTwoSeperateLines() throws Exception {
        String result = app.mergeFileAndStdin(true, stdin, "-", FILE_A_PATH);
        String expected = "Alice\tX\tBob\tY\tCharles\tZ" + STRING_NEWLINE + "A\tB\tC\tD";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("paste - A.txt - < <STDIN>")
    void mergeFileAndStdin_NoFlagWithDashWithFileWithDashInput_ShouldReadDashFromStdinAndPasteInOrder() throws Exception {
        String result = app.mergeFileAndStdin(false, stdin, "-", FILE_A_PATH, "-");
        String expected = "Alice\tA\tX" + STRING_NEWLINE + "Bob\tB\tY" + STRING_NEWLINE + "Charles\tC\tZ" + STRING_NEWLINE + "\tD";
        assertEquals(expected, result);
    }
}