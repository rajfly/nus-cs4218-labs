package cs4218;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShellIT {
    Shell shell;
    ByteArrayOutputStream out;
    PrintStream stdOut;

    @BeforeEach
    void setUp() {
        shell = new Shell(new ApplicationRunner(), new CommandBuilder());
        out = new ByteArrayOutputStream();
        stdOut = new PrintStream(out, true);
        System.setOut(this.stdOut);
    }

    @AfterEach
    void tearDown() {
        stdOut.close();
    }

    @Test
    void start_Echo_WritesToStdOut() {
        String inputString = "echo hello" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);

        shell.start();

        String expected = "> hello\n> ".replaceAll("\\n", System.lineSeparator());
        assertEquals(expected, this.out.toString());
    }

    @Test
    void start_EchoPipeIntoRep_WritesToStdOut() {
        String inputString = "echo hello | rep hello goodbye -" + System.lineSeparator();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);

        shell.start();

        String expected = "> goodbye\n> ".replaceAll("\\n", System.lineSeparator());
        assertEquals(expected, this.out.toString());
    }

    // other cases...
}