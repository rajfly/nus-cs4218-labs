package sg.edu.nus.comp.cs4218.impl.app.bf.cmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CommandSubstitutionTest {

    private static ApplicationRunner appRunner;
    private static ArgumentResolver argsResolver;
    private static OutputStream outputStream;
    private static InputStream inputStream;

    @BeforeEach
    void setUp() {
        appRunner = mock(ApplicationRunner.class);
        argsResolver = new ArgumentResolver();
        outputStream = mock(OutputStream.class);
        inputStream = mock(InputStream.class);
    }

    @Test
    void testCommandSubstitution_With_BackQuote() {
        // command : echo `echo Hello World`
        // Expected : Hello World
        try {
            List<String> argsList = Arrays.asList("echo", "`echo Hello World`"); //NOPMD
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = String.join(" ", parsedArgsList);

                String expected = "Hello World";
                assertEquals(expected, actual);
                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testCommandSubstitution_Without_BackQuote() {
        // command : echo echo Hello World
        // Expected : echo Hello World
        try {
            List<String> argsList = Arrays.asList("echo", "echo Hello World");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = String.join(" ", parsedArgsList);

                String expected = "echo Hello World";
                assertEquals(expected, actual);
                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testCommandSubstitution_With_MultiCmd() {
        // command : echo `echo Hello` `echo World`
        // Expected : Hello World
        try {
            List<String> argsList = Arrays.asList("echo", "`echo Hello`","`echo World`");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = String.join(" ", parsedArgsList);

                String expected = "Hello World";
                assertEquals(expected, actual);
                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testCommandSubstitution_Without_Arg() {
        // command : echo `echo`
        // Expected : white space
        try {
            List<String> argsList = Arrays.asList("echo", "`echo `");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = String.join(" ", parsedArgsList);

                String expected = "";
                assertEquals(expected, actual);
                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }
}
