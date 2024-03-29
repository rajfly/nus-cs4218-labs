package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

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
        Environment.currentDirectory = getNormalizedAbsolutePath(path);
    }

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws CdException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException {
        if (args == null) {
            throw new CdException(ERR_NULL_ARGS);
        }
        if (stdin == null) {
            throw new CdException(ERR_NO_ISTREAM);
        }
        if (stdout == null) {
            throw new CdException(ERR_NO_OSTREAM);
        }
        if (args.length == 0) {
            return;
        }
        if (args.length > 1) {
            throw new CdException(ERR_TOO_MANY_ARGS);
        }
        if (stdin == null) {
            throw new CdException(ERR_NULL_STREAMS);
        }
        if (stdout == null) {
            throw new CdException(ERR_NULL_STREAMS);
        }
        changeToDirectory(args[0]);
    }

    private String getNormalizedAbsolutePath(String pathStr) throws AbstractApplicationException {
        if (pathStr == null || pathStr.isBlank()) {
            return Environment.currentDirectory;
        }

        Path path = new File(pathStr).toPath();
        if (!path.isAbsolute()) {
            path = Paths.get(Environment.currentDirectory, pathStr);
        }

        if (!Files.exists(path)) {
            throw new CdException(String.format(pathStr + ": " + ERR_FILE_NOT_FOUND));
        }

        if (!Files.isDirectory(path)) {
            throw new CdException(String.format(pathStr + ": " + ERR_IS_NOT_DIR));
        }

        return path.normalize().toString();
    }
}
