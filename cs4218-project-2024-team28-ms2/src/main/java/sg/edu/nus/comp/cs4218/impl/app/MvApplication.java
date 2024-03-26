package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.MvInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_EXISTS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_NOT_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_MISSING_ARG;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;

public class MvApplication implements MvInterface {
    @Override
    public String mvSrcFileToDestFile(Boolean isNoOverWrite, String srcFile, String destFile) throws MvException {
        Path source = IOUtils.resolveFilePath(srcFile);
        Path dest = IOUtils.resolveFilePath(destFile);

        if (isNoOverWrite) {
            if (!Files.exists(dest)) {
                try {
                    Files.move(source, dest);
                } catch (IOException e) {
                    throw (MvException) new MvException(e.getMessage()).initCause(e);
                }
            }
        } else {
            try {
                Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw (MvException) new MvException(e.getMessage()).initCause(e);
            }
        }

        return null;
    }

    @Override
    public String mvFilesToFolder(Boolean isNoOverWrite, String destFolder, String... fileName) throws MvException {
        for (String srcPath : fileName) {
            Path source = IOUtils.resolveFilePath(srcPath).toAbsolutePath();
            if (!Files.exists(source)) {
                throw new MvException(ERR_FILE_EXISTS);
            }
            if (isNoOverWrite) {
                // if overwriting is not allowed then only allow possibility of moving if its directory
                mvFilesToFolderNoOverWrite(destFolder, srcPath);
            } else {
                Path dest = IOUtils.resolveFilePath(destFolder).toAbsolutePath();
                Path newPath = IOUtils.resolveFilePath(
                    destFolder + File.separator + new File(srcPath).getName()).normalize();
                boolean destIsNotDir = !Files.isDirectory(dest);
                if (destIsNotDir) {
                    throw new MvException(ERR_IS_NOT_DIR);
                } else if (newPath.equals(source) || source.equals(dest)) {
                    throw new MvException(ERR_FILE_EXISTS);
                }
                try {
                    Files.move(source, newPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw (MvException) new MvException(ERR_IO_EXCEPTION).initCause(e);
                }
            }
        }
        return null;
    }

    private void mvFilesToFolderNoOverWrite(String destFolder, String srcPath) throws MvException {
        if (Files.isDirectory(IOUtils.resolveFilePath(destFolder))) {
            try {
                Path newPath = IOUtils.resolveFilePath(destFolder + File.separator + new File(srcPath).getName()).normalize();
                if (!Files.exists(newPath)) {
                    Files.move(IOUtils.resolveFilePath(srcPath),
                        IOUtils.resolveFilePath(destFolder + File.separator + new File(srcPath).getName()).normalize());
                }
            } catch (IOException e) {
                throw (MvException) new MvException(ERR_IO_EXCEPTION).initCause(e);
            }

        } else {
            throw new MvException(ERR_FILE_EXISTS);
        }
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MvException {
        if (args == null) {
            throw new MvException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new MvException(ERR_NULL_STREAMS);
        }

        MvArgsParser parser = new MvArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw (MvException) new MvException(e.getMessage()).initCause(e);
        }

        if ((!parser.isNoOverwrite() && args.length < 2) || (parser.isNoOverwrite() && args.length < 3)) {
            throw new MvException(ERR_MISSING_ARG);
        }


        List<String> filenames = parser.getFileNames();
        List<String> sourceOperands = filenames.subList(0, filenames.size() - 1);
        String targetOperand = filenames.get(filenames.size() - 1);

        if (sourceOperands.size() > 1 || Files.isDirectory(IOUtils.resolveFilePath(targetOperand))) {
            mvFilesToFolder(parser.isNoOverwrite(), targetOperand, sourceOperands.toArray(new String[0]));
        } else {
            mvSrcFileToDestFile(parser.isNoOverwrite(), sourceOperands.get(0), targetOperand);
        }
    }
}
