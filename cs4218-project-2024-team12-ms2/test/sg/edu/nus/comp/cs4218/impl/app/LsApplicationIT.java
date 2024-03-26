package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.ErrorConstants;

import static org.junit.jupiter.api.Assertions.*;

class LsApplicationIT { //NOPMD - suppressed ClassNamingConventions


    LsApplication lsApplication;

    @BeforeEach
    void setUp() {
        lsApplication = new LsApplication();
    }

    @Test
    void run_NullArg_ThrowsLSException() {
        Exception exception = assertThrows(LsException.class, () -> lsApplication.run(null, System.in, System.out));
        assertEquals("ls: " + ErrorConstants.ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    void run_NullOutputStream_ThrowsLSException() {
        String[] args = {};
        Exception exception = assertThrows(LsException.class, () -> lsApplication.run(args, System.in, null));
        assertEquals("ls: " + ErrorConstants.ERR_NO_OSTREAM, exception.getMessage());
    }

    @Test
    void run_InvalidArgs_ThrowsInvalidArgsException() {
        String[] args = {"-a", "-b"};
        Exception exception = assertThrows(LsException.class, () -> lsApplication.run(args, System.in, System.out));
        assertEquals("ls: " + ErrorConstants.ERR_ILLEGAL_FLAG + "a", exception.getMessage());
    }


}