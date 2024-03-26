package sg.edu.nus.comp.cs4218.impl.util;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public final class FileUtils { //NOPMD - suppressed GodClass - Adheres to SRP as it only handles file utility methods
    private FileUtils() {
    }

    /**
     * Create a file with the given file name. Creates parent directories if they do not exist.
     *
     * @param filePathString The file path of the file to be created
     * @throws IOException If an error occurs during file creation
     */
    public static void createFile(String filePathString) throws IOException {
        Path newFilePath = Paths.get(filePathString);
        Path parentDir = newFilePath.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            // create all nonexistent parent directories
            Files.createDirectories(parentDir);
        }
        Files.createFile(newFilePath);
    }

    /**
     * Create a directory with the given directory name.
     *
     * @param dirPathString The file path of the directory to be created
     * @throws IOException If an error occurs during directory creation
     */
    public static void createDirectory(String dirPathString) throws IOException {
        Path newDirectoryPath = Paths.get(dirPathString);
        Files.createDirectory(newDirectoryPath);
    }

    /**
     * Create a directory with the given directory name.
     *
     * @param dirPathString The file path of the directories to be created
     * @throws IOException If an error occurs during directory creation
     */
    public static void createDirectories(String dirPathString) throws IOException {
        Path newDirectoryPath = Paths.get(dirPathString);
        Files.createDirectories(newDirectoryPath);
    }

    /**
     * Returns if file exists.
     *
     * @param folder File object of folder
     * @return True if folder is empty, false otherwise.
     */

    public static boolean isFolderEmpty(File folder) {
        if (!folder.exists() || !folder.isDirectory()) {
            return true;
        }
        String[] files = folder.list();
        return files == null || files.length == 0;
    }

    /**
     * Removes the specified file or directory recursively.
     *
     * @param file The empty file or directory to be removed
     * @throws IOException If an error occurs during removal
     */
    public static void removeEmptyFolder(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException(ERR_FILE_NOT_FOUND);
        }
        if (!isFolderEmpty(file)) {
            throw new IOException(ERR_DIR_NOT_EMPTY);
        }
        if (!file.canWrite()) {
            throw new IOException(ERR_NO_PERM);
        }
        Files.delete(file.toPath());
    }

    /**
     * Removes the specified file or directory recursively.
     *
     * @param file The file to be removed
     * @throws IOException If an error occurs during removal, or if there are no write permissions
     */
    public static void removeFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException(ERR_FILE_NOT_FOUND);
        }
        if (file.isDirectory()) {
            throw new IOException(ERR_IS_DIR);
        }
        if (!file.canWrite()) {
            throw new IOException(ERR_NO_PERM);
        }
        Files.delete(file.toPath());
    }

    /**
     * Removes the specified file or directory recursively.
     *
     * @param fileToRemove The file or directory to be removed
     * @throws IOException If an error occurs during removal
     */
    public static void removeFilesRecursive(File fileToRemove) throws IOException {
        if (!fileToRemove.exists()) {
            throw new IOException(ERR_FILE_NOT_FOUND);
        }

        if (fileToRemove.isDirectory()) {
            File[] files = fileToRemove.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                removeFilesRecursive(file);
            }
        }

        if (!fileToRemove.canWrite()) {
            throw new IOException(ERR_NO_PERM);
        }
        Files.delete(fileToRemove.toPath());
    }

    /**
     * Reads the file content and returns it as a list of string.
     *
     * @param filePath The file path of the file to be read
     * @return List of strings containing the file content.
     * @throws IOException If an error occurs during file reading
     */
    public static List<String> readFileContent(String filePath) throws IOException {
        validateFile(filePath);
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }

    /**
     * Reads the file content and returns it as a string.
     *
     * @param filePath The file path of the file to be read
     * @return String containing the file content.
     * @throws IOException If an error occurs during file reading
     */
    public static void writeFileContent(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes());
    }

    /**
     * Returns if file exists.
     *
     * @param path Path of file
     * @return True if file exists, false otherwise.
     */
    public static boolean fileExists(Path path) {
        return Files.exists(path);
    }

    /**
     * Returns file content as list of lines.
     *
     * @param path path of file.
     * @return Content of file.
     * @throws IOException If an error occurs during directory creation
     */
    public static List<String> readFileLines(String path) throws IOException {
        Path resolvedPath = Paths.get(path).toAbsolutePath().normalize();
        if (!Files.exists(resolvedPath)) {
            throw new IOException(resolvedPath + ": " + ERR_FILE_NOT_FOUND);
        }
        if (Files.isDirectory(resolvedPath)) {
            throw new IOException(path + ": " + ERR_IS_DIR);
        }
        try {
            return Files.readAllLines(resolvedPath);
        } catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Converts a list of strings into a single string with each element separated by the system's
     * line separator. This method is useful for formatting list output to be human-readable or for
     * comparison with multiline string expectations in tests or text processing.
     *
     * @param listOfStrings The list of strings to be joined into a single multiline string.
     * @return A string representation of the list where each element is separated by a line separator.
     */
    public static String formatListOfStringToMultilineString(List<String> listOfStrings) {
        return String.join(System.lineSeparator(), listOfStrings);
    }

    /**
     * Returns if file exists.
     *
     * @param path Path of file
     * @return True if file exists, false otherwise.
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * Validates that the file exists and is not a directory and can be read.
     *
     * @param file Path of file
     * @throws IOException If file does not exist or is a directory or cannot be read.
     */
    public static void validateFile(String file) throws IOException {
        File node = IOUtils.resolveFilePath(file).toFile();
        if (!node.exists()) {
            throw new IOException(ERR_FILE_NOT_FOUND);
        }
        if (node.isDirectory()) {
            throw new IOException(ERR_IS_DIR);
        }
        if (!node.canRead()) {
            throw new IOException(ERR_NO_PERM);
        }
    }

    /**
     * Appends content to a file, adding a newline before the new content if the file
     * does not already end with a newline.
     *
     * @param path    The path to the file.
     * @param content The content to append.
     * @throws IOException If the file cannot be written to.
     */
    public static void appendToFile(String path, String content) throws IOException {
        Path filePath = IOUtils.resolveFilePath(path);
        try {
            // Initialize toWrite with content assuming the file doesn't exist or is empty
            String toWrite = content;

            // If file exists, check if it ends with a newline
            if (Files.exists(filePath)) {
                String existingContent = Files.readString(filePath, StandardCharsets.UTF_8);

                if (!existingContent.isEmpty() && !existingContent.endsWith(STRING_NEWLINE)) {
                    // If the file doesn't end with a newline, prepend a newline to the content
                    toWrite = STRING_NEWLINE + content;
                }
            }

            Files.write(filePath, toWrite.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IOException(path, e);
        }
    }

    /**
     * Writes to file.
     *
     * @param path    Path of file.
     * @param content Content to be written to file.
     * @throws IOException if file cannot be written to.
     */
    public static void writeToFile(String path, String content) throws IOException {
        Path filePath = IOUtils.resolveFilePath(path);
        try (FileWriter myWriter = new FileWriter(filePath.toString())) {
            myWriter.write(content);
        } catch (IOException e) {
            throw new IOException(path, e);
        }
    }

    /**
     * Filters out empty or whitespace-only lines from a list of strings and joins the remaining lines using
     * the system's line separator. This method is useful for preparing text content for output, ensuring that
     * it doesn't contain unnecessary blank lines.
     *
     * @param lines A {@link List} of {@link String} objects representing lines of text. This list may contain
     *              empty strings or strings that consist solely of whitespace characters.
     * @return A {@link String} where the non-empty, trimmed lines from the input list are concatenated, with
     *         each line separated by the system's line separator. If the input list is empty or contains only
     *         whitespace lines, an empty string is returned.
     */
    public static String filterAndJoinLines(List<String> lines) {
        return lines.stream()
            .filter(line -> !line.trim().isEmpty())
            .collect(Collectors.joining(STRING_NEWLINE));
    }
}
