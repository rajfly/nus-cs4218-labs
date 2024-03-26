package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.*;
import sg.edu.nus.comp.cs4218.app.*;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.parser.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FILE_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;

public class RmApplication implements RmInterface {

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null || args.length == 0) {
            throw new RmException(ERR_NULL_ARGS);
        }
        RmArgsParser parser = new RmArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw (RmException) new RmException(e.getMessage()).initCause(e);
        }
        boolean isRecursive = parser.isRecursive();
        boolean isEmptyFolder = parser.isEmptyFolder();
        List<String> filesToRemove = parser.getFiles();

        remove(isEmptyFolder, isRecursive, filesToRemove.toArray(new String[0]));
    }

    @Override
    public void remove(Boolean isEmptyFolder, Boolean isRecursive, String... fileName) throws AbstractApplicationException {
        if (fileName == null || fileName.length == 0) {
            throw new RmException(ERR_MISSING_ARG);
        }

        for (String fileNames : fileName) {
            Path path = Paths.get(Environment.currentDirectory).resolve(fileNames).normalize();

            if (!Files.exists(path)) {
                throw new RmException(fileNames + ": " + ERR_FILE_NOT_FOUND);
            }

            try {
                if (isRecursive && Files.isDirectory(path)) {
                    deleteDirectoryRecursively(path);
                } else if (isEmptyFolder && Files.isDirectory(path)) {
                    if (!isDirectoryEmpty(path)) {
                        throw new RmException(fileNames + ": Directory not empty");
                    }
                    Files.delete(path);
                } else {
                    if (Files.isDirectory(path)) {
                        throw new RmException(fileNames + ": Is a directory, need to add flag");
                    }
                    Files.delete(path);
                }
            } catch (IOException e) {
                throw (RmException) new RmException("Error removing file: " + e).initCause(e);
            }
        }
    }

    private boolean isDirectoryEmpty(Path path) throws IOException {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            return !dirStream.iterator().hasNext();
        }
    }

    private void deleteDirectoryRecursively(Path path) throws IOException {
        List<Path> pathsToDelete = Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (Path p : pathsToDelete) {
            Files.delete(p);
        }
    }

}
