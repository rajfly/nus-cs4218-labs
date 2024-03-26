package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class MkdirApplication implements MkdirInterface {
    private static final String FLAG_PARENT = "-p";

    /**
     * Runs the mkdir application with the specified arguments.
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws MkdirException When args is invalid.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new MkdirException(ERR_NULL_ARGS);
        }

        if (args.length == 0 || args[0].isBlank()) {
            throw new MkdirException(ERR_MISSING_ARG);
        }

        MkdirArgsParser mkdirArgsParser = new MkdirArgsParser();
        try {
            mkdirArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new MkdirException(ERR_INVALID_FLAG, e);
        }

        if (mkdirArgsParser.getFileNames().isEmpty()) {
            throw new MkdirException(ERR_MISSING_ARG);
        }

        if (mkdirArgsParser.isParent()) {
            // add -p flag to the front of the fileNames
            String[] newFileNames = new String[mkdirArgsParser.getFileNames().size() + 1];
            newFileNames[0] = FLAG_PARENT;
            for (int i = 0; i < mkdirArgsParser.getFileNames().size(); i++) {
                newFileNames[i + 1] = mkdirArgsParser.getFileNames().get(i);
            }
            createFolder(newFileNames);
        } else {
            createFolder(mkdirArgsParser.getFileNames().toArray(new String[0]));
        }
    }

    @Override
    public void createFolder(String... folderNames) throws AbstractApplicationException {
        if (folderNames == null) {
            throw new MkdirException(ERR_NULL_ARGS);
        }
        try {
            if (FLAG_PARENT.equals(folderNames[0])) {
                if (folderNames.length < 2) {
                    throw new MkdirException(ERR_MISSING_ARG);
                }
                for (int i = 1; i < folderNames.length; i++) {
                    Path folderPath = Paths.get(folderNames[i]);
                    String fullFolderPath = Paths.get(Environment.currentDirectory, folderNames[i]).toString();
                    try {
                        FileUtils.createDirectories(fullFolderPath);
                    } catch (FileAlreadyExistsException e) {
                        throw new MkdirException(String.format("%s: %s", folderPath, ERR_FILE_EXISTS), e); //NOPMD - suppressed AvoidDuplicateLiterals - String formatting follows a predefined format
                    } catch (NoSuchFileException e) {
                        throw new MkdirException(String.format("%s: %s", folderPath.getParent(), ERR_FILE_NOT_FOUND), e);
                    }
                }
            } else {
                for (String folderName : folderNames) {
                    Path folderPath = Paths.get(folderName);
                    String fullFolderPath = Paths.get(Environment.currentDirectory, folderName).toString();
                    try {
                        FileUtils.createDirectory(fullFolderPath);
                    } catch (FileAlreadyExistsException e) {
                        throw new MkdirException(String.format("%s: %s", folderName, ERR_FILE_EXISTS), e);
                    } catch (NoSuchFileException e) {
                        throw new MkdirException(String.format("%s: %s", folderPath.getParent(), ERR_FILE_NOT_FOUND), e);
                    }
                }
            }
        } catch (IOException e) {
            throw new MkdirException(ERR_IO_EXCEPTION, e);
        }
    }
}
