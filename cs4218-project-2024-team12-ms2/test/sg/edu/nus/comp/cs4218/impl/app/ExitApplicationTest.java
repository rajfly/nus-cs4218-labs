package sg.edu.nus.comp.cs4218.impl.app;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ExitException;

import static org.junit.jupiter.api.Assertions.*;

class ExitApplicationTest {
    private ExitApplication exitApplication;

    @BeforeEach
    void setUp() {
        exitApplication = new ExitApplication();
    }

    @Test
    @SneakyThrows
    void terminateExecution_Valid_ShouldThrowExitException() {
        assertThrows(ExitException.class, () -> exitApplication.terminateExecution());
    }

}