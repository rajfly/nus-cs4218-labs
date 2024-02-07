package cs4218.impl.apps;

import cs4218.FileUtil;
import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import cs4218.stubs.RepArgsParserStub;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepApplicationImplTest {
    RepApplicationImpl app;
    RepArgsParserStub argsStub;
    OutputStream out;
    FileUtil fileUtil;

    @BeforeEach
    void setUp() {
        this.out = new ByteArrayOutputStream();
        this.argsStub = new RepArgsParserStub(); // Can write our own stubs
        this.fileUtil = mock(FileUtil.class); // Or create mocks using mockito
        this.app = new RepApplicationImpl(argsStub, this.out, fileUtil);
    }

    // Now passing
    // Failed previously because run adds System.lineSeparator() to result (already commented out)
    @Test
    void run_SimpleValues_PrintsCorrectValues() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("Hello", "abced", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("abced World!", this.out.toString());
    }

    // Test char mode
    @Test
    void run_SimpleValuesInCharMode_PrintsCorrectValues() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("eo", "X", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("HXllX WXrld!", this.out.toString());
    }

    // If string is "lll", then pattern of "ll" should only replace first two characters of string
    @Test
    void run_ThreeConsecChars_DoesNotReplaceLastChar() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("ll", "e", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Helllo World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("Heelo World!", this.out.toString());
    }

    // If pattern has duplicate chars e.g., "ll", it should not affect output
    @Test
    void run_DuplicatePatternInCharMode_PrintsCorrectValues() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("ll", "e", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("Heeeo Wored!", this.out.toString());
    }

    // If pattern is also in replacement, replacement only happens once and not twice
    @Test
    void run_PatternInReplacementInCharMode_DoesNotReplaceReplacement() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("l", "el", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("Heelelo Woreld!", this.out.toString());
    }

    // Bug when using special regex characters as pattern because character mode uses regex
    @ParameterizedTest
    @ValueSource(strings = {"[", "]", ".", "+", "*", "?", "^", "$", "(", ")", "{", "}", "|", "\\"})
    void run_ComplexPatternValuesInCharMode_PrintsCorrectValues(String pattern) throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList(pattern, "", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_CHARACTERS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn(pattern + "Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("Hello World!", this.out.toString());
    }

    // Test ASCII and Non-ASCII replacement values
    @ParameterizedTest
    @ValueSource(strings = {"!@#$%^&*()_+", "你好"})
    void run_ComplexReplacementValues_PrintsCorrectValues(String replacement) throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("Hello", replacement, "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals(replacement + " World!", this.out.toString());
    }

    @Test
    void run_ArgsValidationFailed_ThrowsRepException() {
        this.argsStub.setException(new RepException("Exception Message"));

        RepException ex = assertThrows(RepException.class, () -> this.app.run(Collections.emptyList()));
        assertEquals("Exception Message", ex.getMessage());
    }
}