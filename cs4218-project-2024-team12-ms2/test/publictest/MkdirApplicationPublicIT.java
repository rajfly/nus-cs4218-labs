package publictest;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class MkdirApplicationPublicIT {
    MkdirApplication mkdirApplication;
    static String pathToTestDir = "TestResources" + File.separator + "mkdirTestDir" + File.separator;
    String tempDir = pathToTestDir + "mkdirTest";
    String tempDir2 = pathToTestDir + "mkdirTest2";
    String tempParent = pathToTestDir + "mkdirTestParent";
    String tempChild = tempParent + File.separator + "mkdirTestChild";

    @BeforeAll
    static void setUpAll() throws IOException {
        FileUtils.createFile(pathToTestDir + File.separator + "EmptyFileForGitTracking.txt");
    }

    @BeforeEach
    void setUp() {
        mkdirApplication = new MkdirApplication();
        deleteDirectory(null, new File(pathToTestDir).listFiles());
    }

    @AfterEach
    void tearDown() throws IOException {
        FileUtils.createFile(pathToTestDir + File.separator + "EmptyFileForGitTracking.txt");
    }

    @AfterAll
    static void tearDownAll() throws IOException {
        FileUtils.removeFilesRecursive(new File("TestResources"));
    }

    public static void deleteDirectory(File directory, File... files) {
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i], files[i].listFiles());
                } else {
                    files[i].delete();
                }
            }
        }
        if (directory != null) {
            directory.delete();
        }
    }

    @Test
    void run_NullInput_ThrowsException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(null, null, null);
        });
    }

    @Test
    void run_EmptyInput_Success() throws Exception {
        String[] args = new String[0];
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(args, null, null);
        });
    }

    @Test
    void run_OneNewDirectoryInput_Success() throws Exception {
        String[] args = new String[1];
        args[0] = tempDir;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
    }


    @Test
    void run_TwoNewDirectoryInput_Success() throws Exception {
        String[] args = new String[2];
        args[0] = tempDir;
        args[1] = tempDir2;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
        assertTrue(new File(tempDir2).exists());
    }

    @Test
    void run_DirectoryInDirectoryInput_Success() throws Exception {
        String[] args = new String[2];
        args[0] = tempParent;
        args[1] = tempChild;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempChild).exists());
    }
}
