package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class CdApplication implements CdInterface {



    @Override
    public void changeToDirectory(String path) throws AbstractApplicationException {
        if (path.isBlank()) {
            throw new CdException(ERR_MISSING_ARG);
        }
        Environment.currentDirectory = getNormalizedAbsolutePath(path);
    }

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws CdException When args is invalid.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException {
        if (args == null) {
            throw new CdException(ERR_NULL_ARGS);
        }

        if (args.length > 1) {
            throw new CdException(ERR_TOO_MANY_ARGS);
        }

        if (args.length == 0 || args[0].isBlank()) {
            throw new CdException(ERR_MISSING_ARG);
        }

        changeToDirectory(args[0]);
    }

    /**
     * Returns the normalized absolute path of the given path.
     *
     * @param pathStr The path to be normalized.
     * @return The normalized absolute path of the given path.
     * @throws CdException If the path is invalid.
     */
    private String getNormalizedAbsolutePath(String pathStr) throws AbstractApplicationException {
        Path path = new File(pathStr).toPath();
        if (!path.isAbsolute()) {
            path = Paths.get(Environment.currentDirectory, pathStr);
        }

        if (!Files.exists(path)) {
            throw new CdException(String.format("%s: %s", pathStr, ERR_FILE_NOT_FOUND));
        }

        if (!Files.isDirectory(path)) {
            throw new CdException(String.format("%s: %s", pathStr, ERR_IS_NOT_DIR));
        }
        if (!Files.isExecutable(path)) {
            throw new CdException(String.format("%s: %s", pathStr, ERR_NO_PERM));
        }
        return path.normalize().toString();
    }
}
