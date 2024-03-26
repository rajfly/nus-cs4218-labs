package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ApplicationRunnerTest {

    private ApplicationRunner appRunner;
    private InputStream inputStream;
    private OutputStream outputStream;
    private static final String [] ARGS = {"Hello", "World"};

    @BeforeEach
    void setUp() {
        appRunner = new ApplicationRunner();
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void runApp_LsApplication_ThrowsLsException() {
        assertThrows(LsException.class, ()->appRunner.runApp(ApplicationRunner.APP_LS, ARGS, inputStream, null));
    }

    @Test
    public void runApp_WcApplication_ThrowsWcException() {
        assertThrows(WcException.class, ()->appRunner.runApp(ApplicationRunner.APP_WC, ARGS, inputStream, null));
    }

    @Test
    public void runApp_EchoApplication_ThrowsEchoException() {
        assertThrows(EchoException.class, ()->appRunner.runApp(ApplicationRunner.APP_ECHO, ARGS, inputStream, null));
    }

    @Test
    public void runApp_GrepApplication_ThrowsGrepException() {
        assertThrows(GrepException.class, ()->appRunner.runApp(ApplicationRunner.APP_GREP, ARGS, inputStream, null));
    }

    @Test
    public void runApp_PasteApplication_ThrowsPasteException() {
        assertThrows(PasteException.class, ()->appRunner.runApp(ApplicationRunner.APP_PASTE, ARGS, inputStream, null));
    }

    @Test
    public void runApp_CdApplication_ThrowsCdException() {
        assertThrows(CdException.class, ()->appRunner.runApp(ApplicationRunner.APP_CD, ARGS, inputStream, null));
    }

    @Test
    public void runApp_CatApplication_ThrowsCatException() {
        assertThrows(CatException.class, ()->appRunner.runApp(ApplicationRunner.APP_CAT, ARGS, inputStream, null));
    }

    @Test
    public void runApp_RmApplication_ThrowsRmException() {
        assertThrows(RmException.class, ()->appRunner.runApp(ApplicationRunner.APP_RM, ARGS, inputStream, null));
    }

    @Test
    public void runApp_SortApplication_ThrowsSortException() {
        assertThrows(SortException.class, ()->appRunner.runApp(ApplicationRunner.APP_SORT, ARGS, inputStream, null));
    }

    @Test
    public void runApp_TeeApplication_ThrowsTeeException() {
        assertThrows(TeeException.class, ()->appRunner.runApp(ApplicationRunner.APP_TEE, ARGS, inputStream, null));
    }

    @Test
    public void runApp_MvApplication_ThrowsMvException() {
        assertThrows(MvException.class, ()->appRunner.runApp(ApplicationRunner.APP_MV, ARGS, inputStream, null));
    }

    @Test
    public void runApp_CutApplication_ThrowsCutException() {
        assertThrows(CutException.class, ()->appRunner.runApp(ApplicationRunner.APP_CUT, ARGS, inputStream, null));
    }

    @Test
    public void runApp_UniqApplication_ThrowsUniqException() {
        assertThrows(UniqException.class, ()->appRunner.runApp(ApplicationRunner.APP_UNIQ, ARGS, inputStream, null));
    }

    @Test
    public void runApp_UnsupportedApp_ThrowsShellException() {
        String unsupportedApp = "unsupportedApp";

        assertThrows(ShellException.class, () ->
                appRunner.runApp(unsupportedApp, ARGS, inputStream, outputStream));
    }


}

