package sg.edu.nus.comp.cs4218;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestUtils {
    private TestUtils() {}
    public static void assertExceptionContains(Exception exception, String string) {
        assertTrue(exception.getMessage().contains(string));
    }
}
