package sg.edu.nus.comp.cs4218.impl.app.tdd;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.*;

public class ExitApplicationPublicTest {
    private ExitApplication app;

    @BeforeEach
    public void renewApplication() {
        app = new ExitApplication();
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    @DisabledIf("javaVersionIs17OrAbove")
    // Disable this test on Java 17 and above , Reason: "UnsupportedOperationException on Java => 17"
    public void terminateExecution_GivenAnything_ShouldExitWithStatusCode0() throws AbstractApplicationException {
        app.terminateExecution();
    }

    static boolean javaVersionIs17OrAbove() {
        return Runtime.version().feature() >= 17;
    }
}
