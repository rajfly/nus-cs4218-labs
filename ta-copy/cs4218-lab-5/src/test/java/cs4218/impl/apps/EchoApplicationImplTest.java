package cs4218.impl.apps;

import cs4218.exceptions.EchoException;
import cs4218.interfaces.apps.EchoApplication;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

class EchoApplicationImplTest {
    EchoApplication app;
    OutputStream out;

    @BeforeEach
    void setUp() {
        this.out = new ByteArrayOutputStream();
        this.app = new EchoApplicationImpl(this.out);
    }

    @Test
    void run_TypicalValue_WritesToStdOut() throws EchoException {
        String command = "hello world";
        List<String> tokens = List.of(command.split(" "));

        app.run(tokens);

        assertEquals("hello world", this.out.toString());
    }

    @Test
    void run_EmptyString_WritesEmptyStringToStdOut() throws EchoException {
        String command = "";
        List<String> tokens = List.of(command.split(" "));

        app.run(tokens);

        assertEquals("", this.out.toString());
    }

    @Test
    void run_OutputStreamClosed_ThrowsEchoException() {
        String command = "hello world";
        List<String> tokens = List.of(command.split(" "));
        try (MockedStatic<IOUtils> ioUtils = mockStatic(IOUtils.class)) {
            ioUtils.when(() -> IOUtils.write(anyString(), (OutputStream) any())).thenThrow(IOException.class);

            EchoException ex = assertThrows(EchoException.class, () -> app.run(tokens));
            assertEquals("Could not write to output stream", ex.getMessage());
        }
    }
}