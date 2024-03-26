package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class EchoApplicationIT { //NOPMD - suppressed ClassNamingConventions

    private final EchoInterface echoApplication = new EchoApplication();
    private static final String HELLO = "hello";
    private static final String WORLD = "world";
    private static final String ECHO = "echo: ";

    @Test
    @SneakyThrows
    void run_NullInputStream_ShouldThrowEchoException() {
        String[] args = {HELLO, WORLD};
        try (OutputStream outputStream = new PipedOutputStream()) {
            Throwable thrown = assertThrows(EchoException.class, () -> echoApplication.run(args, null, outputStream));
            assertEquals(ECHO + ERR_NO_ISTREAM, thrown.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void run_NullOutputStream_ShouldThrowEchoException() {
        String[] args = {HELLO, WORLD};
        try (InputStream inputStream = new PipedInputStream()) {
            Throwable thrown = assertThrows(EchoException.class, () -> echoApplication.run(args, inputStream, null));
            assertEquals(ECHO + ERR_NO_OSTREAM, thrown.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void run_CloseOutputStream_ShouldThrowEchoException() {
        String[] args = {HELLO, WORLD};
        try (InputStream inputStream = new PipedInputStream();
             OutputStream outputStream = new PipedOutputStream()) {
            Throwable thrown = assertThrows(EchoException.class, () -> echoApplication.run(args, inputStream, outputStream));
            assertEquals(ECHO + ERR_IO_EXCEPTION, thrown.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void run_ValidParameters_ShouldWrite() {
        String[] args = {HELLO, WORLD};
        try (PipedInputStream inputStream = new PipedInputStream();
             PipedOutputStream outputStream = new PipedOutputStream(inputStream)) {
            echoApplication.run(args, inputStream, outputStream);
            byte[] resultBytes = new byte[1024];
            int bytesRead = inputStream.read(resultBytes);
            String result = new String(resultBytes, 0, bytesRead);
            String expected = "hello world" + STRING_NEWLINE;
            assertEquals(expected, result);
        }
    }
}
