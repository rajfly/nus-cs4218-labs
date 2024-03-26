package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.PathUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_CURR_DIR;

public class LsApplication implements LsInterface { //NOPMD - suppressed GodClass - Adheres to SRP as it only handles Ls

    private final static String PATH_CURR_DIR = STRING_CURR_DIR + CHAR_FILE_SEP;

    @Override
    public String listFolderContent(Boolean isRecursive, Boolean isSortByExt,
                                    String... folderName) throws AbstractApplicationException {
        if (folderName.length == 0 && !isRecursive) {
            return listCwdContent(isSortByExt);
        }

        List<Path> paths;
        if (folderName.length == 0 && isRecursive) {
            String[] directories = new String[1];
            directories[0] = Environment.currentDirectory;
            paths = PathUtils.resolvePaths(directories);
        } else {
            paths = PathUtils.resolvePaths(folderName);
        }

        return buildResult(paths, isRecursive, isSortByExt);
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException {
        if (args == null) {
            throw new LsException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new LsException(ERR_NO_OSTREAM);
        }

        LsArgsParser parser = new LsArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw new LsException(e.getMessage(), e);
        }

        Boolean recursive = parser.isRecursive();
        Boolean sortByExt = parser.isSortByExt();
        String[] directories = parser.getDirectories()
                .toArray(new String[parser.getDirectories().size()]);
        String result = listFolderContent(recursive, sortByExt, directories);

        try {
            stdout.write(result.getBytes());
            stdout.write(StringUtils.STRING_NEWLINE.getBytes());
        } catch (Exception e) {
            throw new LsException(ERR_WRITE_STREAM, e);
        }
    }

    /**
     * Lists only the current directory's content and RETURNS. This does not account for recursive
     * mode in cwd.
     *
     * @param isSortByExt
     * @return
     */
    private String listCwdContent(Boolean isSortByExt) throws AbstractApplicationException {
        String cwd = Environment.currentDirectory;
        try {
            return formatContents(getContents(Paths.get(cwd)), isSortByExt);
        } catch (InvalidDirectoryException e) {
            throw new LsException("Unexpected error occurred!", e);
        }
    }

    /**
     * Builds the resulting string to be written into the output stream.
     * <p>
     * NOTE: This is recursively called if user wants recursive mode.
     *
     * @param paths       - list of java.nio.Path objects to list
     * @param isRecursive - recursive mode, repeatedly ls the child directories
     * @param isSortByExt - sorts folder contents alphabetically by file extension (characters after the last ‘.’ (without quotes)). Files with no extension are sorted first.
     * @return String to be written to output stream.
     */
    private String buildResult(List<Path> paths, Boolean isRecursive, Boolean isSortByExt) {
        StringBuilder result = new StringBuilder();
        for (Path path : paths) {
            try {
                List<Path> contents = getContents(path);
                if (contents.isEmpty() && isRecursive) {
                    continue;
                }
                String formatted = formatContents(contents, isSortByExt);
                String relativePath = getRelativeToCwd(path).toString();
                if (result.length() != 0 && !(result.substring(result.length() - StringUtils.STRING_NEWLINE.length()) + "").equals(StringUtils.STRING_NEWLINE)) {
                    result.append(StringUtils.STRING_NEWLINE);
                    result.append(StringUtils.STRING_NEWLINE);
                }
                result.append(StringUtils.isBlank(relativePath) ? PATH_CURR_DIR : relativePath);
                if (Files.isDirectory(path)) {
                    result.append(':').append(StringUtils.STRING_NEWLINE);
                } else {
                    result.append(StringUtils.STRING_NEWLINE);
                }
                result.append(formatted);

                if (!formatted.isEmpty()) {
                    // Empty directories should not have an additional new line
                    result.append(StringUtils.STRING_NEWLINE);
                }
                result.append(StringUtils.STRING_NEWLINE);

                // RECURSE!
                if (isRecursive) {
                    result.append(buildResult(contents, isRecursive, isSortByExt));
                }
            } catch (InvalidDirectoryException e) {
                // NOTE: This is pretty hackish IMO - we should find a way to change this
                // If the user is in recursive mode, and if we resolve a file that isn't a directory
                // we should not spew the error message.
                //
                // However the user might have written a command like `ls invalid1 valid1 -R`, what
                // do we do then?
                if (!isRecursive) {
                    result.append(e.getMessage());
                    result.append(StringUtils.STRING_NEWLINE);
                }
            }
        }

        return result.toString().trim();
    }

    /**
     * Formats the contents of a directory into a single string.
     *
     * @param contents    - list of items in a directory
     * @param isSortByExt - sorts folder contents alphabetically by file extension (characters after the last ‘.’ (without quotes)). Files with no extension are sorted first.
     * @return String of formatted contents
     */
    private String formatContents(List<Path> contents, Boolean isSortByExt) {
        List<String> fileNames = new ArrayList<>();
        for (Path path : contents) {
            fileNames.add(path.getFileName().toString());
        }

        if (isSortByExt) {
            // Sort by file extensions
            Collections.sort(fileNames, new Comparator<String>() {
                @Override
                public int compare(String fileName1, String fileName2) {
                    String ext1 = getFileExtension(fileName1);
                    String ext2 = getFileExtension(fileName2);
                    return ext1.compareToIgnoreCase(ext2);
                }
            });
        }

        StringBuilder result = new StringBuilder();
        for (String fileName : fileNames) {
            result.append(fileName);
            result.append(StringUtils.STRING_NEWLINE);
        }

        return result.toString().trim();
    }

    /**
     * Extracts the file extension from a file name.
     * @param fileName - name of the file
     * @return file extension
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // no extension
        } else {
            return fileName.substring(dotIndex + 1).toLowerCase(Locale.getDefault());
        }
    }

    /**
     * Gets the contents in a single specified directory.
     *
     * @param directory
     * @return List of files + directories in the passed directory.
     */
    private List<Path> getContents(Path directory)
            throws InvalidDirectoryException {
        if (!Files.exists(directory)) {
            throw new InvalidDirectoryException(getRelativeToCwd(directory).toString());
        }

        if (!Files.isDirectory(directory)) {
            return List.of();
        }

        List<Path> result = new ArrayList<>();
        File pwd = directory.toFile();
        for (File f : pwd.listFiles()) {
            if (!f.isHidden()) {
                result.add(f.toPath());
            }
        }

        Collections.sort(result);

        return result;
    }

    /**
     * Converts a path to a relative path to the current directory.
     *
     * @param path
     * @return
     */
    private Path getRelativeToCwd(Path path) {
        return Paths.get(Environment.currentDirectory).relativize(path);
    }

    private class InvalidDirectoryException extends Exception {
        InvalidDirectoryException(String directory) {
            super(String.format("ls: cannot access '%s': No such file or directory", directory));
        }
    }
}
