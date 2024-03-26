package sg.edu.nus.comp.cs4218.impl.app.bf;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import sg.edu.nus.comp.cs4218.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.app.*;
import sg.edu.nus.comp.cs4218.impl.util.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ISTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

public class CdApplicationTest {
    private static final String ORIGINAL_DIR = Environment.currentDirectory;
    private CdApplication cdApp;
    private ByteArrayOutputStream stdout;
    private ByteArrayInputStream stdin;

    @TempDir
    Path tempDir;

    @BeforeAll
    static void initialSetup() {
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @BeforeEach
    void setUp() {
        cdApp = new CdApplication();
        stdout = new ByteArrayOutputStream();
        stdin = new ByteArrayInputStream("".getBytes());
        Environment.currentDirectory = tempDir.toString();
    }

    @AfterEach
    void tearDown() throws IOException {
        stdout.flush();
        stdin.close();
    }

    @AfterAll
    static void reset() {
        Environment.currentDirectory = ORIGINAL_DIR;
    }

    @Test
    void run_NullArgs_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApp.run(null, stdin, stdout));
    }

    @Test
    void run_EmptyArgs_DoesNotChangeDirectory() throws AbstractApplicationException {
        cdApp.run(new String[]{}, stdin, stdout);
        assertEquals(tempDir.toString(), Environment.currentDirectory);
    }

    @Test
    void run_SpaceArgs_DoesNotChangeDirectory() throws AbstractApplicationException {
        cdApp.run(new String[]{"  "}, stdin, stdout);
        assertEquals(tempDir.toString(), Environment.currentDirectory);
    }

    @Test
    void run_DotDotString_ChangeToParentDirectory() throws Exception {
        Path parentDir = tempDir.getParent();
        cdApp.run(new String[]{".."}, stdin, stdout);
        assertEquals(parentDir.toString(), Environment.currentDirectory);
    }

    @Test
    void run_NullStdin_ThrowsCdException() {
        Throwable thrown = assertThrows(CdException.class, () -> cdApp.run(new String[]{}, null, stdout));
        assertTrue(thrown.getMessage().contains(ERR_NO_ISTREAM));
    }

    @Test
    void run_NullStdout_ThrowsCdException() {
        Throwable thrown = assertThrows(CdException.class, () -> cdApp.run(new String[]{}, stdin, null));
        assertTrue(thrown.getMessage().contains(ERR_NO_OSTREAM));
    }

    @Test
    void run_TooManyArgs_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApp.run(new String[]{"dir1", "dir2"}, stdin, stdout));
    }

    @Test
    void run_NonExistentDirectory_ThrowsCdException() {
        assertThrows(CdException.class, () -> cdApp.run(new String[]{"nonexistentDir"}, stdin, stdout));
    }

    @Test
    void run_ValidDirectory_ChangesCurrentDirectory() throws Exception {
        Path newDir = Files.createDirectory(tempDir.resolve("newDir"));
        cdApp.run(new String[]{newDir.toString()}, stdin, stdout);
        assertEquals(newDir.toString(), Environment.currentDirectory);
    }

    @Test
    void run_DirectoryIsAFile_ThrowsCdException() throws Exception {
        Path file = Files.createFile(tempDir.resolve("file.txt"));
        assertThrows(CdException.class, () -> cdApp.run(new String[]{file.toString()}, stdin, stdout));
    }

    @Test
    void run_AbsolutePath_ChangesCurrentDirectory() throws Exception {
        Path absoluteDir = Files.createDirectory(tempDir.resolve("absoluteDir"));
        cdApp.run(new String[]{absoluteDir.toAbsolutePath().toString()}, stdin, stdout);
        assertEquals(absoluteDir.toAbsolutePath().toString(), Environment.currentDirectory);
    }

    @Test
    void run_RelativePath_ChangesCurrentDirectory() throws Exception {
        Path relativeDir = Files.createDirectory(tempDir.resolve("relativeDir"));
        cdApp.run(new String[]{"relativeDir"}, stdin, stdout);
        assertEquals(relativeDir.toAbsolutePath().toString(), Environment.currentDirectory);
    }

    @Test
    void run_RootDirectory_ChangeToRootDirectory() throws Exception {
        // This test will depend on the OS. For Unix-based systems, the root is "/", and for Windows, it might be "C:\\"
        String rootPath = FileSystems.getDefault().getRootDirectories().iterator().next().toString();
        cdApp.run(new String[]{rootPath}, stdin, stdout);
        assertEquals(rootPath, Environment.currentDirectory);
    }

    @Test
    void run_ChangeDirectoryTwoLevels_ChangesCurrentDirectory() throws Exception {
        Path levelOneDir = Files.createDirectory(tempDir.resolve("LevelOne"));
        Path levelTwoDir = Files.createDirectory(levelOneDir.resolve("LevelTwo"));

        // Relative path
        cdApp.run(new String[]{"LevelOne" + StringUtils.fileSeparator() + "LevelTwo"}, stdin, stdout);
        assertEquals(levelTwoDir.toString(), Environment.currentDirectory);
    }

    @Test
    void run_ChangeDirectory_ChangeDirectoryAndListContents() throws Exception {
        Path newDir = Files.createDirectory(tempDir.resolve("listDir"));
        Files.createFile(newDir.resolve("testfile.txt"));

        cdApp.run(new String[]{newDir.toString()}, stdin, stdout);
        assertEquals(newDir.toString(), Environment.currentDirectory);

        // After changing directory, list the contents
        File dir = new File(Environment.currentDirectory);
        String[] filesList = dir.list();
        assertTrue(Arrays.asList(filesList).contains("testfile.txt"));
    }
}
