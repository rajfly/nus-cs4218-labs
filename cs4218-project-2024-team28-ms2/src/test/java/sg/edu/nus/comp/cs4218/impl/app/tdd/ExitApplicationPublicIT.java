package sg.edu.nus.comp.cs4218.impl.app.tdd;

import org.junit.jupiter.api.*;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

import org.junit.jupiter.api.condition.*;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.*;

public class ExitApplicationPublicIT {

    public static final String EXIT_MSG = "exit: 0";

    @Test
    @ExpectSystemExitWithStatus(0)
    @DisabledIf("javaVersionIs17OrAbove")
        // Disable this test on Java 17 and above , Reason: "UnsupportedOperationException on Java => 17"
    void run_NoArgument_ExitsSuccessfully() throws AbstractApplicationException {
        ExitApplication app = new ExitApplication();
        app.run(null, null, null);
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    @DisabledIf("javaVersionIs17OrAbove")
    // Disable this test on Java 17 and above , Reason: "UnsupportedOperationException on Java => 17"
    public void run_WithArguments_ExitsSuccessfully() throws AbstractApplicationException {
        ExitApplication app = new ExitApplication();
        app.run(new String[]{"anything", "here"}, System.in, System.out);
    }

    static boolean javaVersionIs17OrAbove() {
        return Runtime.version().feature() >= 17;
    }
}
