package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.io.CleanupMode.ON_SUCCESS;

class PathUtilsTest {
    public static final String DIR_A = "dirA";
    public static final String FILE_A_1 = "fileA1";
    static String originDirectory;

    @TempDir(cleanup = ON_SUCCESS)
    static Path sharedTempDir;
    @BeforeAll
    static void beforeAll() {
        try {
            originDirectory = Environment.currentDirectory;
            Environment.currentDirectory = sharedTempDir.toString();
            Path innerDirA = Files.createDirectory(sharedTempDir.resolve(DIR_A));
            Files.createFile(innerDirA.resolve(FILE_A_1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void afterAll() {
        Environment.currentDirectory = originDirectory;
    }

    @Test
    void resolvePaths_ResolvesRelativePaths_Success() {
        List<Path> paths = PathUtils.resolvePaths(DIR_A, DIR_A + StringUtils.fileSeparator() + FILE_A_1);
        assertEquals(2, paths.size());
        assertEquals(sharedTempDir.resolve(DIR_A), paths.get(0));
        assertEquals(sharedTempDir.resolve(DIR_A).resolve(FILE_A_1), paths.get(1));
    }

    @Test
    void resolvePaths_ResolvesAbsolutePaths_Success() {
        List<Path> paths = PathUtils.resolvePaths(Paths.get(sharedTempDir.toString(),DIR_A).toString(),
                Paths.get(sharedTempDir.toString(),DIR_A, FILE_A_1).toString());
        assertEquals(2, paths.size());
        assertEquals(sharedTempDir.resolve(DIR_A), paths.get(0));
        assertEquals(sharedTempDir.resolve(DIR_A).resolve(FILE_A_1), paths.get(1));
    }

    @Test
    void resolvePaths_ResolvesMixedPaths_Success() {
        List<Path> paths = PathUtils.resolvePaths(DIR_A, Paths.get(sharedTempDir.toString(),DIR_A, FILE_A_1).toString());
        assertEquals(2, paths.size());
        assertEquals(sharedTempDir.resolve(DIR_A), paths.get(0));
        assertEquals(sharedTempDir.resolve(DIR_A).resolve(FILE_A_1), paths.get(1));
    }

    @Test
    void resolvePath_ResolvesRelativePath_Success() {
        Path path = PathUtils.resolvePath(DIR_A);
        System.out.println(path);
        assertEquals(sharedTempDir.resolve(DIR_A), path);
    }

    @Test
    void resolvePath_ResolvesAbsolutePath_Success() {
        Path path = PathUtils.resolvePath(Paths.get(sharedTempDir.toString(),DIR_A).toString());
        System.out.println(path);
        assertEquals(sharedTempDir.resolve(DIR_A), path);
    }
}