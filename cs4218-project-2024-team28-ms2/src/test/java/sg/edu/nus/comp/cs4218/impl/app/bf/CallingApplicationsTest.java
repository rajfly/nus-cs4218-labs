package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CallingApplicationsTest {
    private OutputStream outputStream;
    private ShellImpl shell;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        shell = new ShellImpl();
    }

    @Test
    void run_LsApplication_ThrowNothing() {
        assertDoesNotThrow(() ->  shell.parseAndEvaluate("ls *",outputStream));
    }

    @Test
    void run_CatApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("cat *.txt",outputStream));
    }
    @Test
    void run_EchoApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("echo A B C",outputStream));
    }

    @Test
    void run_CdApplication_ThrowNothing()  {
        assertThrows(CdException.class,() -> shell.parseAndEvaluate("cd A/new",outputStream));
    }

    @Test
    void run_WcApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("wc pom.xml",outputStream));
    }

    @Test
    void run_MkdirApplication_ThrowNothing() throws IOException {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("mkdir example",outputStream));
        Files.deleteIfExists(IOUtils.resolveFilePath("example"));
    }

    @Test
    void run_SortApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("sort README.md",outputStream));
    }

    @Test
    void run_GrepApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("grep 'java' pom.xml",outputStream));
    }

    @Test
    void run_TeeApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("tee - < 'pom.xml'", outputStream));
    }

    @Test
    void run_RmApplication_ThrowNothing() throws IOException {
        Files.createFile(IOUtils.resolveFilePath("test.txt"));
        assertDoesNotThrow(() -> shell.parseAndEvaluate("rm test.txt", outputStream));
        Files.deleteIfExists(IOUtils.resolveFilePath("test.txt"));
    }

    @Test
    void run_CutApplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("cut -b 1,2,3 tee1.txt", outputStream));
    }

    @Test
    void run_MvApplication_ThrowNothing()  {
        assertDoesNotThrow(() ->  shell.parseAndEvaluate("mv pom.xml pom.xml",outputStream));
    }

    @Test
    void run_UniqAplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("uniq pom.xml", outputStream));
    }

    @Test
    void run_PasteAplication_ThrowNothing()  {
        assertDoesNotThrow(() -> shell.parseAndEvaluate("paste pom.xml", outputStream));
    }

}
