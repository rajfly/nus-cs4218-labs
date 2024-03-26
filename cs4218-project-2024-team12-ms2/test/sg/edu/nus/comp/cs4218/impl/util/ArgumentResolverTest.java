package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentResolverTest {

    ArgumentResolver argumentResolver = new ArgumentResolver();

    @Test
    void parseArguments_EmptyArgsList_ShouldReturnEmptyList() {
        List<String> input = List.of();
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertEquals(0, result.size());
        });
    }

    @Test
    void parseArguments_SingleArgument_ShouldReturnCorrectly() {
        List<String> input = List.of("a");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a"}, result.toArray());
        });
    }

    @Test
    void parseArguments_MultipleArguments_ShouldReturnCorrectly() {
        List<String> input = List.of("a", "b", "c");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a", "b", "c"}, result.toArray());
        });
    }

    @Test
    void parseArguments_SingleArgumentWithSingleQuotes_ShouldReturnCorrectly() {
        List<String> input = List.of("'a'");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a"}, result.toArray());
        });
    }

    @Test
    void parseArguments_SingleArgumentWithDoubleQuotes_ShouldReturnCorrectly() {
        List<String> input = List.of("\"a\"");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a"}, result.toArray());
        });
    }

    @Test
    void parseArguments_MultipleArgumentWithBacktick_ShouldReturnCorrectly() {
        List<String> input = List.of("'a` ` b'", "'`c`'");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a` ` b", "`c`"}, result.toArray());
        });
    }

    @Test
    void parseArguments_MultipleArgumentWithMixedQuotes_ShouldReturnCorrectly() {
        List<String> input = List.of("`echo 'a'`");
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.parseArguments(input);
            assertArrayEquals(new String[]{"a"}, result.toArray());
        });
    }

    @Test
    void resolveOneArgument_SingleQuotes_ShouldReturnCorrectly() {
        String input = "'a'";
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.resolveOneArgument(input);
            assertArrayEquals(new String[]{"a"}, result.toArray());
        });
    }

    @Test
    void resolveOneArgument_DoubleQuotes_ShouldReturnCorrectly() {
        String input = "\"testing\"";
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.resolveOneArgument(input);
            assertArrayEquals(new String[]{"testing"}, result.toArray());
        });
    }

    @Test
    void resolveOneArgument_Backtick_ShouldReturnCorrectly() {
        String input = "''Travel time Singapore -> Paris is 13h and 15`''";
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.resolveOneArgument(input);
            assertArrayEquals(new String[]{"Travel time Singapore -> Paris is 13h and 15"}, result.toArray());
        });
    }

    @Test
    void resolveOneArgument_BackQuotesWrappedInDoubleQuotes_ShouldReturnCorrectly() {
        String input = "\"`echo 'aa'`\"";
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.resolveOneArgument(input);
            assertEquals(1, result.size());
            assertEquals("aa ", result.get(0));
        });
    }

    @Test
    void resolveOneArgument_SingleQuotesWrappedInDoubleQuotes_ShouldReturnCorrectly() {
        String input = "\"'echo'\"";
        assertDoesNotThrow(() -> {
            List<String> result = argumentResolver.resolveOneArgument(input);
            assertArrayEquals(new String[]{"'echo'"}, result.toArray());
        });
    }

}