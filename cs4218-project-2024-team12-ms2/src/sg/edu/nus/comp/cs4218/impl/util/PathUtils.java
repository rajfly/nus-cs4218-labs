package sg.edu.nus.comp.cs4218.impl.util;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_BACK_SLASH;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FORWARDSLASH;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.Environment;

public final class PathUtils {

    private PathUtils() {
    }

    /**
     * Resolve all paths given as arguments into a list of Path objects for easy path management.
     *
     * @param directories
     * @return List of java.nio.Path objects
     */
    public static List<Path> resolvePaths(String... directories) throws IllegalArgumentException {
        List<Path> paths = new ArrayList<>();
        for (int i = 0; i < directories.length; i++) {
            paths.add(resolvePath(directories[i]));
        }

        return paths;
    }

    /**
     * Converts a String into a java.nio.Path objects. Also resolves if the current path provided
     * is an absolute path.
     *
     * @param directory
     * @return
     */
    public static Path resolvePath(String directory) throws IllegalArgumentException {
        if (directory == null || directory.isEmpty()) {
            throw new IllegalArgumentException(directory);
        }
        // Check if the directory is already an absolute path
        if (Paths.get(directory).isAbsolute()) {
            return Paths.get(directory).normalize();
        } else {
            // If not absolute, resolve it against the current directory
            return Paths.get(Environment.currentDirectory, directory).normalize();
        }
    }

    /**
     * Checks if the given file path is a Universal Naming Convention (UNC) path.
     * A UNC path starts with two backslashes or slashes.
     *
     * @param filePath The file path to check.
     * @return True if the path is a UNC path, otherwise false.
     */
    public static boolean isUncPath(String filePath) {
        return filePath != null
                && filePath.startsWith(String.valueOf(CHAR_BACK_SLASH + CHAR_BACK_SLASH))
                || filePath.startsWith(String.valueOf(CHAR_FORWARDSLASH + CHAR_FORWARDSLASH));
    }

    /**
     * Convert UNC paths into absolute paths.
     *
     * @param filePath the path to convert.
     * @return the converted absolute path.
     */
    private static Path normalizeUncPath(String filePath) {
        String nonUncPath = filePath;
        while (isUncPath(nonUncPath)) {
            nonUncPath = nonUncPath.substring(1);
        }
        return Paths.get(nonUncPath);
    }

    /**
     * Resolve the absolute path of the given destination file with respect to our current directory.
     * Throws respective errors if we encounter any along the path to the destination file.
     *
     * @param fileName the path of the destination file we wish to resolve.
     * @return resolution of the path.
     * @throws FileNotFoundException if any name along the path cannot be found, or the path is invalid.
     * @throws InvalidPathException  if path is invalid (i.e., has invalid characters).
     */
    public static Path resolveFilePath(String fileName) throws Exception, InvalidPathException {
        if (StringUtils.isBlank(fileName)) {
            throw new FileNotFoundException(fileName);
        }
        Path nonUncPath = normalizeUncPath(fileName);
        Path parent = nonUncPath.getParent();
        String currentDirectory = Environment.currentDirectory;
        Path currDirPath;
        currDirPath = Paths.get(currentDirectory);
        // Check the path to the file
        while (parent != null) {
            parent = parent.getParent();
        }
        return currDirPath.resolve(nonUncPath);
    }
}
