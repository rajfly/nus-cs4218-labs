package cs4218;

import cs4218.exceptions.ShellException;
import cs4218.impl.apps.EchoApplicationImpl;
import cs4218.impl.apps.RepApplicationImpl;
import cs4218.interfaces.apps.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationRunnerTest {
    ApplicationRunner appRunner;

    @BeforeEach
    void setUp() {
        appRunner = new ApplicationRunner();
    }

    @Test
    void createApp_RepCommand_CreateRepApplication() throws ShellException {
        Application app = appRunner.createApp("rep", null, null);
        assertEquals(RepApplicationImpl.class, app.getClass());
    }

    @Test
    void createApp_EchoCommand_CreateEchoApplication() throws ShellException {
        Application app = appRunner.createApp("echo", null, null);
        assertEquals(EchoApplicationImpl.class, app.getClass());
    }

    @Test
    void createApp_InvalidCommand_ThrowsShellException() {
        ShellException ex = assertThrows(ShellException.class,
                () -> appRunner.createApp("invalid", null, null));
        assertEquals("Command not found", ex.getMessage());
    }
}