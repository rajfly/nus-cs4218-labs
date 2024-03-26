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
public class QuotingTest {
    private static final String ECHO = "echo";
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
    void testQuoting_SingleQuote_WithoutSpecialChars() {
        // Cmd: echo 'Hello World'
        // Expected: echo Hello World
        try {
            List<String> argsList;
            argsList = Arrays.asList("echo", "'Hello Jane'"); //NOPMD
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo Hello Jane";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_DoubleQuote_WithoutSpecialChars() {
        // Cmd: echo "Hello World"
        // Expected: echo Hello World
        try {
            List<String> argsList = Arrays.asList("echo", "\"Hey World\"");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo Hey World";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_BackQuote_WithoutSpecialChars() {
        // Cmd: echo `echo Hello World`
        // Expected: echo Hello World
        try {
            List<String> argsList = Arrays.asList(ECHO, "`echo Hello World`");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1);
                String actual = app + " " + String.join(" ", parsedArgsList);

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
    void testQuoting_DoubleQuote_WithEnabledBackQuote() {
        // Cmd: echo "This is space:`echo " "`."
        // Expected: echo This is space: .
        try {
            List<String> argsList = Arrays.asList(ECHO, "\"This is space:`echo \" \"`.\"");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1); // parsedArgsList (Globbing is done here)
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo This is space: .";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_SingleQuote_WithDisableBackQuote() {
        // Cmd: echo 'This is space:`echo " "`.'
        // Expected: echo This is space:`echo " "`.
        try {
            List<String> argsList = Arrays.asList(ECHO, "'This is space:`echo \" \"`.'");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1); // parsedArgsList (Globbing is done here)
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo This is space:`echo \" \"`.";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_DoubleQuote_WithSingleQuote() {
        // Cmd: echo "This is space:' '."
        // Expected: echo This is space:' '.
        try {
            List<String> argsList = Arrays.asList(ECHO, "\"This is space:' '.\"");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1); // parsedArgsList (Globbing is done here)
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo This is space:' '.";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_DoubleQuote_WithSingleQuoteEnableBackQuote() {
        // Cmd: echo "'This is space `echo " "`'"
        // Expected: echo 'This is space  '
        try {
            List<String> argsList = Arrays.asList(ECHO, "\"'This is space `echo \" \"`'\"");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1); // parsedArgsList (Globbing is done here)
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo 'This is space  '";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testQuoting_SingleQuote_WithDoubleQuoteDisableBackQuote() {
        // Cmd: echo '"This is space `echo " "`"'
        // Expected: echo "This is space `echo " "`"
        try {
            List<String> argsList = Arrays.asList(ECHO, "'\"This is space `echo \" \"`\"'");
            CallCommand cmd = new CallCommand(argsList, appRunner, argsResolver);

            doAnswer(invocation -> {
                String app = invocation.getArgument(0);
                String[] parsedArgsList = invocation.getArgument(1); // parsedArgsList (Globbing is done here)
                String actual = app + " " + String.join(" ", parsedArgsList);

                String expected = "echo \"This is space `echo \" \"`\"";
                assertEquals(expected, actual);

                return null;
            }).when(appRunner).runApp(any(), any(), any(), any());

            cmd.evaluate(inputStream, outputStream);
        } catch (Exception e) {
            fail();
        }
    }
}
