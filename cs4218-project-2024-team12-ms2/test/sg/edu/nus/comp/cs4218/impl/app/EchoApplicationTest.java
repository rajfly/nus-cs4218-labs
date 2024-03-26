package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class EchoApplicationTest {
    private final EchoInterface echoApplication = new EchoApplication();
    private static final String HELLO = "hello";
    private static final String WORLD = "world";
    private static final String ECHO = "echo: ";

    @Test
    void constructResult_NullArgs_ShouldThrowEchoException() {
        String[] args = null;
        EchoException exception = assertThrows(EchoException.class, () -> echoApplication.constructResult(args));
        assertEquals(ECHO + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void constructResult_EmptyArgs_ShouldReturnNewLine() {
        String[] args = {};
        String result = echoApplication.constructResult(args);
        assertEquals(STRING_NEWLINE, result);
    }

    @Test
    @SneakyThrows
    void constructResult_SimplePhrase_ShouldReturnPhraseWithNewLine() {
        String[] args = {"hello world"};
        String result = echoApplication.constructResult(args);
        String expected = "hello world" + STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void constructResult_PhraseWithExtraSpaces_ShouldReturnPhraseWithNewLine() {
        String[] args = {HELLO, "     ", WORLD};
        String result = echoApplication.constructResult(args);
        String expected = "hello       world" + STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    @SneakyThrows
    void constructResult_PhraseWithMultipleArgs_ShouldReturnBeValid() {
        String[] args = {HELLO, WORLD, ",", "from", "team", "12"};
        String result = echoApplication.constructResult(args);
        String expected = "hello world , from team 12" + STRING_NEWLINE;
        assertEquals(expected, result);
    }


}
