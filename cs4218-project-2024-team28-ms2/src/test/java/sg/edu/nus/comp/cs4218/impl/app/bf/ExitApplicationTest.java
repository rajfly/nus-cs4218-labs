package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import sg.edu.nus.comp.cs4218.impl.app.*;
import sg.edu.nus.comp.cs4218.impl.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test case will only run if project is using Java 16 and below, due to use of SecurityManager
 */

public class ExitApplicationTest {
    private SecurityManager origSM;
    private static final String[] DEFAULT_ARGS = {"test", "test"};

    @BeforeEach
    public void setUp() {
        origSM = System.getSecurityManager();
        System.setSecurityManager(new CustomSecurityManager());
    }

    @AfterEach
    public void tearDown() {
        System.setSecurityManager(origSM);
    }

    @Test
    @DisabledIf("javaVersionIs17OrAbove") //NOPMD
    public void run_NullParams_ShouldTerminateExecution() {
        ExitApplication app = new ExitApplication();
        assertThrows(SecurityException.class, () -> app.run(null, null, null));
    }

    @Test
    @DisabledIf("javaVersionIs17OrAbove") //NOPMD
    void run_EmptyArgs_ThrowsExitException() {
        ExitApplication app = new ExitApplication();
        assertThrows(SecurityException.class, () -> app.run(new String[]{}, null, null));
    }


    @Test
    @DisabledIf("javaVersionIs17OrAbove") //NOPMD
    void run_ManyArgs_ThrowsExitException() {
        ExitApplication app = new ExitApplication();
        assertThrows(SecurityException.class, () -> app.run(DEFAULT_ARGS, null, null));
    }

    // Test on ApplicationRunner runApp, EXIT case

    @Test
    @DisabledIf("javaVersionIs17OrAbove") //NOPMD
    void runApp_WithManyArg_ThrowsExitException() {
        ApplicationRunner applicationRunner = new ApplicationRunner();
        assertThrows(SecurityException.class, () ->
                applicationRunner.runApp("exit", DEFAULT_ARGS, System.in, System.out));
    }

    @Test
    @DisabledIf("javaVersionIs17OrAbove") //NOPMD
    void runApp_WithNullArg_ThrowsExitException() {
        ApplicationRunner applicationRunner = new ApplicationRunner();
        assertThrows(SecurityException.class, () ->
                applicationRunner.runApp("exit", null, System.in, System.out));
    }

    /**
     * A custom SecurityManager to intercept attempts to exit the JVM.
     * Used to testing environments where you want to ensure
     * that the application logic attempts to exit without actually
     * terminating the JVM.
     */
    private static class CustomSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(java.security.Permission perm) {
            // Allow most operations
        }

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new SecurityException("System.exit() is not allowed");
        }
    }

    static boolean javaVersionIs17OrAbove() {
        return Runtime.version().feature() >= 17;
    }

}

