package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_ISTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import sg.edu.nus.comp.cs4218.app.TeeInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class TeeApplication implements TeeInterface {
    /**
     * Runs application with specified input data and specified output stream.
     *
     * @param args
     * @param stdin
     * @param stdout
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new TeeException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new TeeException(ERR_NO_OSTREAM);
        }

        TeeArgsParser parser = new TeeArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw (TeeException) new TeeException(e.getMessage()).initCause(e);
        }

        boolean isAppend = parser.isAppend();
        String[] filenames = parser.getFileNames().toArray(new String[0]);
        String output = teeFromStdin(isAppend, stdin, filenames);

        try {
            stdout.write(output.getBytes());
        } catch (Exception e) {
            throw (TeeException) new TeeException(ERR_WRITE_STREAM).initCause(e);
        }

    }

    /**
     * Reads from standard input and write to both the standard output and files
     *
     * @param isAppend Boolean option to append the standard input to the contents of the input files
     * @param stdin    InputStream containing arguments from Stdin
     * @param fileName Array of String of file names
     * @return
     * @throws Exception
     */
    @Override
    public String teeFromStdin(Boolean isAppend, InputStream stdin, String... fileName) throws AbstractApplicationException {
        if (stdin == null) {
            throw new TeeException(ERR_NO_ISTREAM);
        }

        if (fileName == null) {
            throw new TeeException(ERR_NULL_ARGS);
        }

        List<String> teeContent = getLinesFromInputStream(stdin);

        String teeContentString = String.join(STRING_NEWLINE ,teeContent) + STRING_NEWLINE;

        for (String file : fileName) {
            // Creates file if file does not exist
            Path path = Paths.get(file);
            if (Files.notExists(path) || !Files.isRegularFile(path)) {
                try {
                    Files.createFile(Paths.get(file));
                } catch (IOException e) {
                    throw (TeeException) new TeeException(e.getMessage()).initCause(e);
                }
            }
            // Checks if file is a directory
            try {
                if (Files.isDirectory(path)) {
                    return String.format("tee: %s: " + ERR_IS_DIR, file) + STRING_NEWLINE + teeContentString;
                }
                if (!Files.isWritable(path)) {
                    continue; //skip to next file
                }
            } catch (Exception e) {
                throw (TeeException) new TeeException(e.getMessage()).initCause(e);
            }

            writeToFile(isAppend, teeContentString, file);
        }

        return teeContentString;
    }

    /**
     * Gets the lines from input stream
     *
     * @param stdin
     * @return
     * @throws TeeException
     */
    private List<String> getLinesFromInputStream(InputStream stdin) throws TeeException {
        try {
            return IOUtils.getLinesFromInputStream(stdin);
        } catch (Exception e) {
            throw (TeeException) new TeeException(e.getMessage()).initCause(e);
        }
    }


    /**
     * Write all lines to files
     *
     * @param isAppend
     * @param content
     * @param filename
     * @throws TeeException
     */
    private void writeToFile(boolean isAppend, String content, String filename) throws TeeException {
        try {
            FileWriter writer; //NOPMD
            if (isAppend) {
                writer = new FileWriter(filename, true);
                BufferedWriter out = new BufferedWriter(writer); //NOPMD
                out.write(STRING_NEWLINE + content);
                out.close();
            } else {
                writer = new FileWriter(filename);
                BufferedWriter out = new BufferedWriter(writer); //NOPMD
                out.write(content);
                out.close();
            }
        } catch (IOException e) {
            throw (TeeException) new TeeException(e.getMessage()).initCause(e);
        }
    }
}
