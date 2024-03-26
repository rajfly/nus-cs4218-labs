package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentResolverTest {
    private ArgumentResolver argumentResolver;
    private List<String> argsList;
    @BeforeEach
    void setUp() {
        argumentResolver = new ArgumentResolver();
        argsList = new ArrayList<>();
    }

    @Test
    void parseArguments_EmptyArguments_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.parseArguments(argsList));
    }

    @Test
    void parseArguments_RandomArguments_ThrowNothing() {
        argsList.add("Hello");
        argsList.add("World");
        assertDoesNotThrow(() -> argumentResolver.parseArguments(argsList));
    }

    @Test
    void resolveOneArgument_EmptyArgument_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.resolveOneArgument(""));
    }

    @Test
    void resolveOneArgument_RandomArgument_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.resolveOneArgument("hello world"));
    }

    @Test
    void resolveOneArgument_ArgumentWithSingleQuote_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.resolveOneArgument("'hello world'"));
    }

    @Test
    void resolveOneArgument_ArgumentWithDoubleQuote_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.resolveOneArgument("\"hello world\""));
    }

    @Test
    void resolveOneArgument_ArgumentWithDoubleQuoteAndSingleQuote_ThrowNothing() {
        assertDoesNotThrow(() -> argumentResolver.resolveOneArgument("\"'hello world'\""));
    }

}