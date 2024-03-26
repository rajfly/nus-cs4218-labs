package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ArgsParserTest {

    private ArgsParser parser;

    @BeforeEach
    void setParser() {
        parser = new ArgsParser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "X", "c", "l", "w", "v", "n", "r", "f"})
    void run_ValidArgFlags_DoesNotThrowException(String arg) {
        assertDoesNotThrow(() -> parser.parse(arg));
    }

}