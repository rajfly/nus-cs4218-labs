package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ExitException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitApplicationIT { //NOPMD - suppressed ClassNamingConventions
    private ExitApplication exitApplication;

    @BeforeEach
    void setUp() {
        exitApplication = new ExitApplication();
    }

    @Test
    @SneakyThrows
    void run_Valid_ShouldTerminateExecution() {
        assertThrows(ExitException.class, () -> exitApplication.run(new String[0], System.in, System.out));
    }
}
