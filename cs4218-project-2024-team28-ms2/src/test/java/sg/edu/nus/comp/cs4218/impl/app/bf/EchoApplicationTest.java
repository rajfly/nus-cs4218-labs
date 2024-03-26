package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.*;
import org.mockito.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.app.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class EchoApplicationTest {
    private EchoApplication echoApp;
    private OutputStream stdout;
    private Path tempFile;
    private final static String[] DEFAULT_ARGS = {"Hello", "test"};
    private final static String DEFAULT_OUTPUT = "Hello test";
    private final static String EMPTY_STR = "";
    private final static String SPACE_STR = "    ";
    private static final String EXPECTED_OUTPUT = DEFAULT_OUTPUT + System.lineSeparator();

    @BeforeEach
    void setUp() throws IOException {
        echoApp = new EchoApplication();
        stdout = new ByteArrayOutputStream();
        tempFile = Files.createTempFile("testEcho", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        stdout.flush();
        Files.deleteIfExists(tempFile);
    }

    @Test
    void constructResult_TxtArg_ReturnsConcatenatedString() throws AbstractApplicationException {
        String result = echoApp.constructResult(DEFAULT_ARGS);
        assertEquals(DEFAULT_OUTPUT + STRING_NEWLINE, result);
    }

    @Test
    void constructResult_NumArg_ReturnsNum() throws AbstractApplicationException {
        String[] args = {"1234567890"};
        String result = echoApp.constructResult(args);
        assertEquals("1234567890" + STRING_NEWLINE, result);
    }

    @Test
    void constructResult_SpaceArg_ReturnsSpace() throws AbstractApplicationException {
        String[] args = {SPACE_STR};
        String result = echoApp.constructResult(args);
        assertEquals(SPACE_STR + STRING_NEWLINE, result);
    }

    @Test
    void constructResult_NullArg_ThrowsEchoException() {
        assertThrows(EchoException.class, () -> echoApp.constructResult(null));
    }

    @Test
    void run_ValidArgAndOutputStream_WritesExpectedOutput() throws AbstractApplicationException {
        echoApp.run(DEFAULT_ARGS, null, stdout);
        assertEquals(DEFAULT_OUTPUT + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_EmptyArgs_WritesExpectedOutput() throws AbstractApplicationException {
        echoApp.run(new String[]{}, null, stdout);
        assertEquals(EMPTY_STR + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_BlankArgs_WritesExpectedOutput() throws AbstractApplicationException {
        echoApp.run(new String[]{EMPTY_STR}, null, stdout);
        assertEquals(EMPTY_STR + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_SpaceArgs_WritesExpectedOutput() throws AbstractApplicationException {
        echoApp.run(new String[]{SPACE_STR}, null, stdout);
        assertEquals(SPACE_STR + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_NullArgs_ThrowsEchoException() throws AbstractApplicationException {
        assertThrows(EchoException.class, () -> echoApp.run(null, null, stdout));
    }

    @Test
    void run_NullOutputStream_ThrowsEchoException() {
        assertThrows(EchoException.class, () -> echoApp.run(DEFAULT_ARGS, null, null));
    }

    @Test
    void run_IOExceptionOnWrite_ThrowsEchoException() throws IOException {
        OutputStream mockStdout = Mockito.mock(OutputStream.class); //NOPMD
        Mockito.doThrow(IOException.class).when(mockStdout).write(Mockito.any());
        assertThrows(EchoException.class, () -> echoApp.run(DEFAULT_ARGS, null, mockStdout));
        mockStdout.close();
        verify(mockStdout).close();
    }

    @Test
    void run_WritesOutputToFile_Success() throws AbstractApplicationException, IOException {
        try (OutputStream os = Files.newOutputStream(tempFile)) { //NOPMD
            echoApp.run(DEFAULT_ARGS, null, os);
        }

        String content = Files.readString(tempFile);
        assertEquals(EXPECTED_OUTPUT, content);
    }

}
