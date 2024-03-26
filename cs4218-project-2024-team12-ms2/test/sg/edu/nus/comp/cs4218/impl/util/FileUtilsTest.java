package sg.edu.nus.comp.cs4218.impl.util;

import jdk.jfr.Enabled;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

class FileUtilsTest {

    private static final String TESTS_DIR = "test";
    private static final String TEMP_DIR = "temp";
    private static final String TEMP_DIR_PATH = TESTS_DIR + File.separator + TEMP_DIR + File.separator;
    private static final String NESTED_DIR_PATH = TEMP_DIR_PATH + "nested" + File.separator;
    private static final String NESTED_FILE_PATH = NESTED_DIR_PATH + "nestedFile.txt";
    private static final String NEW_FILE_PATH = TEMP_DIR_PATH + "newFile.txt";
    private static final String MISSING_DIR = TEMP_DIR_PATH + "doesNotExistDir";
    private static final String MISSING_FILE = TEMP_DIR_PATH + "doesNotExistFile.txt";
    private static final String NO_READ_FILE = TEMP_DIR_PATH + "noReadPermissions.txt";
    private static final String NO_WRITE_DIR = TEMP_DIR_PATH + "noWriteDir";
    private static final String NO_WRITE_FILE = TEMP_DIR_PATH + "noWriteFile.txt";

    @BeforeEach
    void setUp() {
        try {
            // Create directory for temporary files that will be cleared after each test
            FileUtils.createDirectory(TEMP_DIR_PATH);
            Files.createFile(new File(NO_READ_FILE).toPath());
            new File(NO_READ_FILE).setReadable(false);
            FileUtils.createDirectory(NO_WRITE_DIR);
            new File(NO_WRITE_DIR).setWritable(false);
            FileUtils.createFile(NO_WRITE_FILE);
            new File(NO_WRITE_FILE).setWritable(false);
        } catch (Exception e) {
            fail("Unable to create test temp folder");
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            // so that this can be removed
            new File(NO_WRITE_DIR).setWritable(true);
            new File(NO_WRITE_FILE).setWritable(true);
            FileUtils.removeFilesRecursive(new File(TEMP_DIR_PATH));
        } catch (IOException e) {
            fail("Unable to remove test temp folder");
        }
    }

    @Test
    void createFile_FileDoesNotExist_ShouldReturnSuccess() {
        String filePath = NEW_FILE_PATH;
        assertFalse(new File(filePath).exists());

        assertDoesNotThrow(() -> FileUtils.createFile(filePath));

        assertTrue(new File(filePath).exists());
    }

    @Test
    void createFile_FileAlreadyExists_ShouldThrowException() {
        String filePath = NEW_FILE_PATH;
        assertFalse(new File(filePath).exists());
        assertDoesNotThrow(() -> FileUtils.createFile(filePath));
        assertTrue(new File(filePath).exists());

        assertThrows(IOException.class, () -> FileUtils.createFile(filePath));
    }

    @Test
    void createDirectory_DirectoryDoesNotExist_ShouldReturnSuccess() {
        String folderPath = TEMP_DIR_PATH + "newFolder";
        assertFalse(new File(folderPath).exists());

        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));

        assertTrue(new File(folderPath).exists());
    }

    @Test
    void createFile_ParentDirDoesNotExistYet_ShouldReturnSuccess() {
        String filePath = NESTED_FILE_PATH;
        assertFalse(new File(filePath).exists());

        assertDoesNotThrow(() -> FileUtils.createFile(filePath));

        // check that all the parent directories to the nested file gets created
        assertTrue(new File(TEMP_DIR_PATH).exists());
        assertTrue(new File(NESTED_DIR_PATH).exists());
        assertTrue(new File(filePath).exists());
    }

    @Test
    void createDirectory_DirectoryAlreadyExists_ShouldThrowException() {
        String folderPath = TEMP_DIR_PATH + "newFolder";
        assertFalse(new File(folderPath).exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(new File(folderPath).exists());

        assertThrows(IOException.class, () -> FileUtils.createDirectory(folderPath));
    }

    @Test
    void createDirectories_DirectoryDoesNotExist_ShouldReturnSuccess() {
        String folderPath = NESTED_DIR_PATH;
        assertFalse(new File(folderPath).exists());

        assertDoesNotThrow(() -> FileUtils.createDirectories(folderPath));

        assertTrue(new File(folderPath).exists());
    }

    @Test
    void removeFilesRecursive_NestedDirectory_ShouldReturnSuccess() {
        String folderPath = TEMP_DIR_PATH + "newFolder";
        String outerFilePath = folderPath + File.separator + "outerFile.txt";
        String subFolderPath = folderPath + File.separator + "subFolder";
        String subFolderFilePath = subFolderPath + File.separator + "nestedFile.txt";

        assertFalse(new File(folderPath).exists());
        assertFalse(new File(outerFilePath).exists());
        assertFalse(new File(subFolderPath).exists());
        assertFalse(new File(subFolderFilePath).exists());

        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertDoesNotThrow(() -> FileUtils.createFile(outerFilePath));
        assertDoesNotThrow(() -> FileUtils.createDirectory(subFolderPath));
        assertDoesNotThrow(() -> FileUtils.createFile(subFolderFilePath));

        assertTrue(new File(folderPath).exists());
        assertTrue(new File(outerFilePath).exists());
        assertTrue(new File(subFolderPath).exists());
        assertTrue(new File(subFolderFilePath).exists());

        // Delete root folder, which should also delete its contents
        assertDoesNotThrow(() -> FileUtils.removeFilesRecursive(new File(folderPath)));

        assertFalse(new File(folderPath).exists());
        assertFalse(new File(outerFilePath).exists());
        assertFalse(new File(subFolderPath).exists());
        assertFalse(new File(subFolderFilePath).exists());
    }

    @Test
    @SneakyThrows
    void removeFilesRecursive_FileDoesNotExist_ShouldThrowException() {
        String filePath = MISSING_FILE;
        assertFalse(new File(filePath).exists());
        try {
            FileUtils.removeFilesRecursive(new File(filePath));
            fail("Expected IOException");
        } catch (IOException e) {
            assertEquals(ERR_FILE_NOT_FOUND, e.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void writeFileContent_ValidContent_ShouldReturnSuccess() {
        String filePath = NEW_FILE_PATH;
        String content = "Hello, world!";

        assertFalse(new File(filePath).exists());

        assertDoesNotThrow(() -> FileUtils.writeFileContent(filePath, content));

        assertTrue(new File(filePath).exists());
        assertEquals(content, FileUtils.readFileContent(filePath).get(0));
    }

    @Test
    @SneakyThrows
    void writeFileContent_EmptyContent_ShouldReturnSuccess() {
        String filePath = NEW_FILE_PATH;
        String content = "";

        assertFalse(new File(filePath).exists());

        assertDoesNotThrow(() -> FileUtils.writeFileContent(filePath, content));

        assertTrue(new File(filePath).exists());
        assertTrue(FileUtils.readFileContent(filePath).isEmpty());
    }

    @Test
    void readFileContent_FileDoesNotExist_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.readFileContent(MISSING_FILE));
        assertEquals(ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void readFileContent_IsDirectory_ShouldThrowException() {
        String folderPath = NESTED_DIR_PATH;
        assertFalse(new File(folderPath).exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(new File(folderPath).exists());

        Exception exception = assertThrows(IOException.class, () -> FileUtils.readFileContent(folderPath));
        assertEquals(ERR_IS_DIR, exception.getMessage());
    }

    @Test
    void fileExists_FileExists_ShouldReturnTrue() {
        String filePath = NEW_FILE_PATH;
        assertFalse(new File(filePath).exists());
        assertDoesNotThrow(() -> FileUtils.createFile(filePath));
        assertTrue(new File(filePath).exists());

        assertTrue(FileUtils.fileExists(new File(filePath).toPath()));
    }

    @Test
    void fileExists_FileDoesNotExist_ShouldReturnFalse() {
        String filePath = MISSING_FILE;
        assertFalse(new File(filePath).exists());

        assertFalse(FileUtils.fileExists(new File(filePath).toPath()));
    }

    @Test
    void fileExists_DirectoryExists_ShouldReturnTrue() {
        String folderPath = NESTED_DIR_PATH;
        assertFalse(new File(folderPath).exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(new File(folderPath).exists());

        assertTrue(FileUtils.fileExists(new File(folderPath).toPath()));
    }

    @Test
    void fileExists_DirectoryDoesNotExist_ShouldReturnFalse() {
        String folderPath = MISSING_DIR;
        assertFalse(new File(folderPath).exists());

        assertFalse(FileUtils.fileExists(new File(folderPath).toPath()));
    }

    @Test
    @SneakyThrows
    void readFileLines_FileExists_ShouldReturnContent() {
        String filePath = NEW_FILE_PATH;
        String content = "Hello, world!";
        assertDoesNotThrow(() -> FileUtils.writeFileContent(filePath, content));

        assertDoesNotThrow(() -> FileUtils.readFileLines(filePath));
        assertEquals(content, FileUtils.readFileLines(filePath).get(0));
    }

    @Test
    @SneakyThrows
    void readFileLines_FileDoesNotExist_ShouldThrowException() {
        String currentPath = new File(".").getCanonicalPath();
        String relativeFilePath = "doesNotExist.txt";
        assertFalse(new File(relativeFilePath).exists());

        Exception exception = assertThrows(IOException.class, () -> FileUtils.readFileLines(relativeFilePath));
        assertEquals(currentPath + File.separator + relativeFilePath + ": " + ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    @SneakyThrows
    void readFileLines_FileIsDirectory_ShouldThrowException() {
        String folderPath = NESTED_DIR_PATH;
        assertFalse(new File(folderPath).exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(new File(folderPath).exists());

        Exception exception = assertThrows(IOException.class, () -> FileUtils.readFileLines(folderPath));

        assertEquals(folderPath + ": " + ERR_IS_DIR, exception.getMessage());
    }

    @Test
    @SneakyThrows
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void readFileLines_IOException_ShouldThrowException() {
        String filePath = NEW_FILE_PATH;
        String content = "Hello, world!";
        assertDoesNotThrow(() -> FileUtils.writeFileContent(filePath, content));

        assertDoesNotThrow(() -> new File(filePath).setReadable(false));

        assertThrows(IOException.class, () -> FileUtils.readFileLines(filePath));
    }

    @Test
    void formatListOfStringToMultilineString_ValidStringList_ShouldReturnFormattedString() {
        List<String> lines = List.of("Hello", "World!");
        String expected = "Hello" + StringUtils.STRING_NEWLINE + "World!";

        assertEquals(expected, FileUtils.formatListOfStringToMultilineString(lines));
    }

    @Test
    void formatListOfStringToMultilineString_EmptyStringList_ShouldReturnEmptyString() {
        List<String> lines = List.of();
        String expected = "";

        assertEquals(expected, FileUtils.formatListOfStringToMultilineString(lines));
    }

    @Test
    void isFolderEmpty_EmptyFolder_ShouldReturnTrue() {
        String folderPath = TEMP_DIR_PATH + "emptyFolder1";
        File file = new File(folderPath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(file.exists());
        assertTrue(FileUtils.isFolderEmpty(file));
    }

    @Test
    void isFolderEmpty_NonEmptyFolder_ShouldReturnFalse() {
        String folderPath = TEMP_DIR_PATH + "file123.txt";
        File file = new File(folderPath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createFile(folderPath));
        assertTrue(file.exists());
        assertTrue(FileUtils.isFolderEmpty(file));
    }

    @Test
    void removeEmptyFolder_EmptyFolder_ShouldReturnSuccess() {
        String folderPath = TEMP_DIR_PATH + "emptyFolder2";
        File file = new File(folderPath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(file.exists());
        assertDoesNotThrow(() -> FileUtils.removeEmptyFolder(file));
        assertFalse(file.exists());
    }

    @Test
    void removeEmptyFolder_FolderDoesNotExist_ShouldThrowException() {
        String folderPath = MISSING_DIR;
        assertFalse(new File(folderPath).exists());
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeEmptyFolder(new File(folderPath)));
        assertEquals(ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void removeEmptyFolder_NonEmptyFolder_ShouldThrowException() {
        String folderPath = TEMP_DIR_PATH + "file123.txt";
        File file = new File(folderPath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createFile(folderPath));
        assertTrue(file.exists());
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeEmptyFolder(new File(TEMP_DIR_PATH)));
        assertEquals("Directory not empty", exception.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void removeEmptyFolder_NoPermissions_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeEmptyFolder(new File(NO_WRITE_DIR)));
        assertEquals(ERR_NO_PERM, exception.getMessage());
    }

    @Test
    void removeFile_FileExists_ShouldReturnSuccess() {
        String filePath = TEMP_DIR_PATH + "emptyFolder";
        File file = new File(filePath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createFile(filePath));
        assertTrue(file.exists());
        assertDoesNotThrow(() -> FileUtils.removeFile(file));
        assertFalse(file.exists());
    }

    @Test
    void removeFile_FileDoesNotExist_ShouldThrowException() {
        String filePath = MISSING_FILE;
        assertFalse(new File(filePath).exists());
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeFile(new File(filePath)));
        assertEquals(ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void removeFile_NoWritePermissions_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeFile(new File(NO_WRITE_FILE)));
        assertEquals(ERR_NO_PERM, exception.getMessage());
    }

    @Test
    void removeFile_DirectoryExists_ShouldThrowException() {
        String folderPath = TEMP_DIR_PATH + "emptyFolder";
        File file = new File(folderPath);
        assertFalse(file.exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(file.exists());
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeFile(file));
        assertEquals(ERR_IS_DIR, exception.getMessage());
    }

    @Test
    void removeFileRecursive_FileNoWritePermissions_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeFilesRecursive(new File(NO_WRITE_FILE)));
        assertEquals(ERR_NO_PERM, exception.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void removeFileRecursive_DirNoWritePermissions_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.removeFilesRecursive(new File(NO_WRITE_DIR)));
        assertEquals(ERR_NO_PERM, exception.getMessage());
    }

    @Test
    void fileExists_FilePathExists_ShouldReturnTrue() {
        Path filePath = new File(TEMP_DIR_PATH).toPath();

        assertTrue(FileUtils.fileExists(filePath));
    }

    @Test
    void fileExists_FilePathDoesNotExist_ShouldReturnFalse() {
        Path filePath = new File(MISSING_FILE).toPath();

        assertFalse(FileUtils.fileExists(filePath));
    }

    @Test
    void fileExists_FilePathStringExists_ShouldReturnTrue() {
        assertTrue(FileUtils.fileExists(TEMP_DIR_PATH));
    }

    @Test
    void fileExists_FilePathStringDoesNotExist_ShouldReturnFalse() {
        assertFalse(FileUtils.fileExists(MISSING_FILE));
    }

    @Test
    void validateFile_FileDoesNotExist_ShouldThrowException() {
        String filePath = MISSING_FILE;
        assertFalse(new File(filePath).exists());

        Exception exception = assertThrows(IOException.class, () -> FileUtils.validateFile(filePath));

        assertEquals(ERR_FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void validateFile_FileIsDirectory_ShouldThrowException() {
        String folderPath = NESTED_DIR_PATH;
        assertFalse(new File(folderPath).exists());
        assertDoesNotThrow(() -> FileUtils.createDirectory(folderPath));
        assertTrue(new File(folderPath).exists());

        Exception exception = assertThrows(IOException.class, () -> FileUtils.validateFile(folderPath));

        assertEquals(ERR_IS_DIR, exception.getMessage());
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void validateFile_FileNoReadPermissions_ShouldThrowException() {
        Exception exception = assertThrows(IOException.class, () -> FileUtils.validateFile(NO_READ_FILE));

        assertEquals(ERR_NO_PERM, exception.getMessage());
    }
}