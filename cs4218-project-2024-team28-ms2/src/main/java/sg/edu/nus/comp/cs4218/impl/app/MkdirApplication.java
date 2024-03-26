package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_EXISTS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FOLDERS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_TOP_LEVEL_MISSING;

public class MkdirApplication implements MkdirInterface {
    /**
     * Runs the mkdir application with the specified arguments
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               new folder.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws MkdirException If exception occurs with {@code args}, {@code stdin} or {@code stdout}.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MkdirException {
        // Format: mkdir [-p] [DIRECTORIES]
        if (args == null) {
            throw new MkdirException(ERR_NULL_ARGS);
        }

        if (args.length == 0) {
            throw new MkdirException(ERR_NO_FOLDERS);
        }

        MkdirArgsParser mkDirArgsParser = new MkdirArgsParser();
        try {
            mkDirArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw (MkdirException) new MkdirException(e.getMessage()).initCause(e);
        }

        createFolders(mkDirArgsParser.isParent(), mkDirArgsParser.getFileNames().toArray(new String[0]));
    }

    @Override
    public void createFolder(String... folderName) throws MkdirException {
        if (folderName == null) {
            throw new MkdirException(ERR_NULL_ARGS);
        }
        for (String folder : folderName) {
            if (folder.isEmpty()) {
                throw new MkdirException(ERR_NO_FOLDERS);
            }

            String path = getNormalizedAbsolutePath(folder);
            String[] paths = path.trim().split(File.separator + File.separator);
            int pathLength = paths.length;

            StringBuilder curr = new StringBuilder();
            for (int i = 0; i < pathLength - 1; i++) {
                String currPath = paths[i];
                curr.append(currPath);
                if (!Files.exists(IOUtils.resolveFilePath(curr.toString()))) {
                    throw new MkdirException(ERR_TOP_LEVEL_MISSING + ": '" + curr + "'");
                }
                curr.append(File.separator);
            }

            Path folderPath = Path.of(path);

            try {
                Files.createDirectory(folderPath);
            } catch (IOException e) {
                throw (MkdirException) new MkdirException(e.getMessage()).initCause(e); // NOPMD
            }
        }
    }

    private void createFolders(Boolean isParent, String... folders) throws MkdirException {
        for (String folder : folders) {
            String path;
            try {
                path = getNormalizedAbsolutePath(folder);
            } catch (MkdirException e) {
                if (e.getMessage().contains(ERR_FILE_EXISTS) && isParent) {
                    continue;
                }
                throw e;
            }
            Path folderPath = Path.of(path);

            try {
                if (isParent) {
                    Files.createDirectories(folderPath);
                } else {
                    createFolder(folder);
                }
            } catch (IOException e) {
                throw (MkdirException) new MkdirException(ERR_IO_EXCEPTION).initCause(e);
            }
        }
    }

    public String getNormalizedAbsolutePath(String pathStr) throws MkdirException {
        if (StringUtils.isBlank(pathStr)) {
            throw new MkdirException(ERR_NO_ARGS); // should never happen
        }

        Path path = IOUtils.resolveFilePath(pathStr).toAbsolutePath();

        if (Files.exists(path)) {
            throw new MkdirException(String.format("%s: %s", ERR_FILE_EXISTS, pathStr));
        }

        return path.toString();
    }
}
