package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FORWARDSLASH;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import javax.naming.NoPermissionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.comp.cs4218.app.MvInterface;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.PathUtils;

@SuppressWarnings({"PMD.LongVariable"})
public class MvApplication implements MvInterface { //NOPMD - suppressed GodClass - Adheres to SRP as it only handles mv command

    /**
     * Executes the move operation based on the provided arguments. It identifies source paths and a destination,
     * executing file or directory moves accordingly. Overwrite behavior is dictated by argument flags. The method
     * directly writes outcomes to the provided OutputStream.
     *
     * @param args   The command-line arguments specifying source(s) and destination.
     * @param stdin  Not used here as 'mv' doesn't interact with stdin.
     * @param stdout Where the execution result is written.
     * @throws MvException For issues like null inputs, missing paths, or execution errors.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MvException {
        if (args == null) {
            throw new MvException(ERR_NULL_ARGS);
        }

        if (stdin == null) {
            throw new MvException(ERR_NO_ISTREAM);
        }

        if (stdout == null) {
            throw new MvException(ERR_NO_OSTREAM);
        }

        MvArgsParser parser = new MvArgsParser();
        try {
            parser.parse(args);
            List<String> sourceFiles = parser.getSourceFiles();
            if (sourceFiles.isEmpty()) {
                throw new MvException(ERR_MISSING_SOURCE);
            }

            String destination = parser.getDestination();
            if (destination == null) {
                throw new MvException(String.format(ERR_MISSING_TARGET_FORMAT, sourceFiles.get(0)));
            }

            String result;
            Path destinationPath = PathUtils.resolveFilePath(destination);
            if (Files.isDirectory(destinationPath)) { // If destination does not exist, this returns false
                result = mvFilesToFolder(!parser.isNoOverwrite(), destination, sourceFiles.toArray(new String[0]));
            } else {
                if (sourceFiles.size() > 1) { // More than one source file
                    throw new MvException(ERR_SYNTAX);
                }
                result = mvSrcFileToDestFile(!parser.isNoOverwrite(), sourceFiles.get(0), destination);
            }

            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
        } catch (MvException e) {
            throw e;
        } catch (Exception e) {
            throw new MvException(e.getMessage(), e);
        }
    }

    /**
     * renames the file named by the source operand to the destination path named by the target operand
     *
     * @param isOverwrite Boolean option to perform overwriting
     * @param srcFile     of path to source file
     * @param destFile    of path to destination file
     * @throws MvException If the method fails to execute.
     */
    @Override
    public String mvSrcFileToDestFile(Boolean isOverwrite, String srcFile, String destFile) throws MvException {
        if(srcFile.equals(destFile)){
            throw new MvException(String.format(ERR_SAME_FILE_FORMAT, srcFile, destFile));
        }

        if (srcFile == null || destFile == null) {
            throw new MvException(ERR_NULL_ARGS);
        }

        StringBuilder output = new StringBuilder();
        try {
            Path sourcePath = resolveAndCheckFilePermission(srcFile).normalize();
            Path destinationPath = PathUtils.resolveFilePath(destFile).normalize();

            if (sourcePath.equals(destinationPath)) {
                throw new MvException(String.format(ERR_SAME_FILE_FORMAT, srcFile, destFile));
            }

            // Only perform the operation and append to output if actual move happens
            boolean isFileMoved = performMoveOperation(sourcePath, destinationPath, isOverwrite, srcFile);
            if (isFileMoved) {
                output.append(srcFile).append(" -> ").append(destFile);
            }
        } catch (IOException e) {
            throw new MvException(e.getMessage() + ": " + ERR_FILE_NOT_FOUND, e);
        } catch (NoPermissionException e) {
            throw new MvException(e.getMessage() + ": " + ERR_NO_PERM, e);
        } catch (Exception e) {
            throw new MvException(e.getMessage(), e);
        }
        return output.toString();
    }

    /**
     * move files to destination folder.
     *
     * @param isOverwrite Boolean option to perform overwriting
     * @param destFolder  of path to destination folder
     * @param filename   Array of String of file names
     * @throws MvException If the method fails to run. Caught exception is stored as a string in the result.
     */
    @Override
    public String mvFilesToFolder(Boolean isOverwrite, String destFolder, String... filename) throws MvException {
        if (destFolder == null) {
            throw new MvException(ERR_MISSING_ARG);
        }
        if (filename == null) {
            throw new MvException(ERR_MISSING_ARG);
        }

        List<String> result = new ArrayList<>();
        try {
            Path destinationPath = PathUtils.resolveFilePath(destFolder).normalize();
            for (String fileName : filename) {
                Path sourcePath = resolveAndCheckFilePermission(fileName).normalize();
                Path resolvedTargetPath = destinationPath.resolve(sourcePath.getFileName()).normalize();
                if (sourcePath.equals(resolvedTargetPath)) {
                    throw new MvException(String.format(ERR_SAME_FILE_FORMAT, fileName, destFolder));
                }
                boolean isFileMoved = performMoveOperation(sourcePath, resolvedTargetPath, isOverwrite, fileName);
                if (isFileMoved) {
                    // File was successfully moved, so add to result
                    result.add(fileName + " -> " + destFolder + CHAR_FORWARDSLASH + sourcePath.getFileName());
                }
            }
        } catch (IOException e) {
            throw new MvException(e.getMessage() + ": " + ERR_FILE_NOT_FOUND, e);
        } catch (NoPermissionException e) {
            throw new MvException(e.getMessage() + ": " + ERR_NO_PERM, e);
        } catch (Exception e) {
            throw new MvException(e.getMessage(), e);
        }
        return String.join(STRING_NEWLINE, result);
    }


    /**
     * Resolves the provided file name to an absolute path and checks if the file exists and has
     * the necessary read and write permissions. If the file does not exist or lacks the required
     * permissions, an exception is thrown.
     *
     * @param fileName The name of the file to be resolved and checked.
     * @return The resolved file path if the file exists and has the necessary permissions.
     * @throws MvException If the file does not exist, is not readable, or is not writable.
     */
    private static Path resolveAndCheckFilePermission(String fileName) throws Exception {
        Path filePath = PathUtils.resolveFilePath(fileName);
        boolean fileExists = FileUtils.fileExists(String.valueOf(filePath));
        boolean hasReadPermission = Files.isReadable(filePath);
        boolean hasWritePermission = Files.isWritable(filePath);

        if (!fileExists){
            throw new FileNotFoundException(fileName);
        }
        if (!hasReadPermission || !hasWritePermission) {
            throw new NoPermissionException(fileName);
        }
        return filePath;
    }

    /**
     * Attempts to move a file or directory from the source path to the destination path. It provides an option
     * to overwrite the destination file if it already exists. If the overwrite option is false and the destination
     * file exists, the move operation will not be performed. If any issues occur during the move operation, such
     * as IO errors, an exception is thrown detailing the failure.
     *
     * @param src The source path of the file or directory to be moved.
     * @param dest The destination path where the file or directory should be moved to.
     * @param isOverwrite  Boolean option to perform overwriting
     * @param srcString The string representation of the source file path, used for error messaging.
     * @throws MvException If the move operation cannot be completed due to issues like lack of permissions,
     *                     non-existence of the source, or IO errors.
     */
    private boolean performMoveOperation(Path src, Path dest, boolean isOverwrite, String srcString) throws MvException {
        try {
            // check if dest is writable
            if (Files.exists(dest) && !Files.isWritable(dest)) {
                if (isOverwrite) {
                    throw new MvException(dest + ": " + ERR_NO_PERM);
                } else {
                    return false;
                }
            }


            if (isOverwrite) {
                Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } else {
                if (FileUtils.fileExists(String.valueOf(dest))) {
                    // File exists and no overwrite allowed, so do nothing and return false
                    return false;
                }
                Files.move(src, dest);
                return true;
            }
        } catch (IOException e) {
            throw new MvException(srcString + ": " + ERR_UNABLE_TO_MOVE, e);
        }
    }
}
