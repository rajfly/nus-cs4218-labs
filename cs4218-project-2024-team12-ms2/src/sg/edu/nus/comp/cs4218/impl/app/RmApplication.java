package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.RmInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.FileUtils.*;

public class RmApplication implements RmInterface {
    /**
     * Runs application with specified input data and specified output stream.
     *
     * @param args      Array of arguments for the application.
     * @param stdin     An InputStream. The input data.
     * @param stdout    An OutputStream. The output data.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new RmException(ERR_NULL_ARGS);
        }

        if (args.length == 0 || args[0].isBlank()) {
            throw new RmException(ERR_MISSING_ARG);
        }

        if (stdin == null || stdout == null) {
            throw new RmException(ERR_NULL_STREAMS);
        }

        RmArgsParser rmArgsParser = new RmArgsParser();
        try {
            rmArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new RmException(ERR_INVALID_FLAG, e);
        }

        if (rmArgsParser.getFileNames().isEmpty()) {
            throw new RmException(ERR_MISSING_ARG);
        }

        remove(rmArgsParser.isEmptyFolder(), rmArgsParser.isRecursive(), rmArgsParser.getFileNames().toArray(new String[0]));
    }

    /**
     * Remove the file. (It does not remove folder by default)
     * Assumptions:
     * - -r flag will override -d flag if they are both present
     * operation will stop when an IOException occurs
     *
     * @param isEmptyFolder Boolean option to delete a folder only if it is empty
     * @param isRecursive   Boolean option to recursively delete the folder contents (traversing
     *                      through all folders inside the specified folder)
     * @param fileName      Array of String of file names
     * @throws RmException  If IOException occurs
     */
    @Override
    public void remove(Boolean isEmptyFolder, Boolean isRecursive, String... fileName) throws AbstractApplicationException {
        if (fileName == null) {
            throw new RmException(ERR_NULL_ARGS);
        }

        if (fileName.length == 0) {
            throw new RmException(ERR_MISSING_ARG);
        }

        for (String file : fileName) {
            if (file == null || file.isBlank()) {
                throw new RmException(ERR_MISSING_ARG);
            }
        }

        for (String name : fileName) {
            try {
                File file = new File(name);
                if (isRecursive) {
                    removeFilesRecursive(file);
                } else if (isEmptyFolder) {
                    removeEmptyFolder(file);
                } else {
                    removeFile(file);
                }
            }
            catch (IOException e) {
                String[] knownErrors = {ERR_FILE_NOT_FOUND, ERR_DIR_NOT_EMPTY, ERR_IS_DIR};
                for (String error : knownErrors) {
                    String message = e.getMessage();
                    if (message.contains(error)) {
                        throw new RmException(name + ": " +error, e);
                    }
                }
                throw new RmException(ERR_IO_EXCEPTION, e);
            }

        }
    }
}
