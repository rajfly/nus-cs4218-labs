package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_CURR_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class LsApplication implements LsInterface {

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
            paths = resolvePaths(directories);
        } else {
            paths = resolvePaths(folderName);
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
            throw (LsException) new LsException(e.getMessage()).initCause(e);
        }

        Boolean recursive = parser.isRecursive();
        Boolean sortByExt = parser.isSortByExt();
        String[] directories = parser.getDirectories()
                .toArray(new String[parser.getDirectories().size()]);
        String result = listFolderContent(recursive, sortByExt, directories);

        try {
            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
        } catch (Exception e) {
            throw (LsException) new LsException(ERR_WRITE_STREAM).initCause(e);
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
            throw (LsException) new LsException("Unexpected error occurred!").initCause(e);
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
                String formatted = formatContents(contents, isSortByExt);
                String relativePath = getRelativeToCwd(path).toString();
                result.append(StringUtils.isBlank(relativePath) ? PATH_CURR_DIR : relativePath);
                result.append(":" + STRING_NEWLINE); //NOPMD
                result.append(formatted);

                if (!formatted.isEmpty()) {
                    // Empty directories should not have an additional new line
                    result.append(STRING_NEWLINE);
                }
                result.append(STRING_NEWLINE);

                // RECURSE!
                if (isRecursive) {
                    for (Path content : contents) {
                        if (Files.isDirectory(content)) {
                            result.append(buildResult(Collections.singletonList(content), isRecursive, isSortByExt));
                            result.append(STRING_NEWLINE);
                            result.append(STRING_NEWLINE);
                        }
                    }
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
                    result.append(STRING_NEWLINE);
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
     * @return
     */
    private String formatContents(List<Path> contents, Boolean isSortByExt) {
        List<String> fileNames = new ArrayList<>();
        for (Path path : contents) {
            fileNames.add(path.getFileName().toString());
        }

        if (isSortByExt) {
            fileNames.sort((o1, o2) -> {
                String ext1 = getExtension(o1);
                String ext2 = getExtension(o2);
                if (ext1.isEmpty() && !ext2.isEmpty()) {
                    return -1;
                }
                if (ext2.isEmpty() && !ext1.isEmpty()) {
                    return 1;
                }
                if (ext1.isEmpty() && ext2.isEmpty()) {
                    return o1.compareToIgnoreCase(o2);
                }
                int extComp = ext1.compareToIgnoreCase(ext2);
                if (extComp == 0) {
                    return o1.compareToIgnoreCase(o2);
                } else {
                    return extComp;
                }
            });
        } else {
            Collections.sort(fileNames);
        }

        StringBuilder result = new StringBuilder();
        for (String fileName : fileNames) {
            result.append(fileName).append(STRING_NEWLINE);
        }

        return result.toString().trim();
    }

    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
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
            throw new InvalidDirectoryException(getRelativeToCwd(directory).toString());
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
     * Resolve all paths given as arguments into a list of Path objects for easy path management.
     *
     * @param directories
     * @return List of java.nio.Path objects
     */
    private List<Path> resolvePaths(String... directories) {
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
    private Path resolvePath(String directory) {
        if (directory.charAt(0) == CHAR_FILE_SEP || (directory.length() > 1 && directory.charAt(1) == ':')) { //allow : for windows
            // This is an absolute path
            return Paths.get(directory).normalize();
        }

        return Paths.get(Environment.currentDirectory, directory).normalize();
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

        InvalidDirectoryException(String directory, Throwable cause) {
            super(String.format("ls: cannot access '%s': No such file or directory", directory),
                    cause);
        }
    }
}
