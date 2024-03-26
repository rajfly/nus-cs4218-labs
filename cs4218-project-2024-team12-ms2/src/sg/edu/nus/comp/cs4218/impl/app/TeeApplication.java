package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import sg.edu.nus.comp.cs4218.app.TeeInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class TeeApplication implements TeeInterface {

    /**
     * Runs application with specified input data and specified output stream.
     *
     * @param args
     * @param stdin
     * @param stdout
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws TeeException {
        if (args == null) {
            throw new TeeException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new TeeException(ERR_NO_OSTREAM);
        }
        if (stdin == null) {
            throw new TeeException(ERR_NO_ISTREAM);
        }

        TeeArgsParser parser = new TeeArgsParser();
        try {
            parser.parse(args);
            String result = teeFromStdin(parser.isAppend(), stdin, parser.getFiles());
            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
        } catch (Exception e) {
            throw new TeeException(e.getMessage(), e);
        }
    }

    /**
     * Reads from standard input and write to both the standard output and files
     *
     * @param isAppend Boolean option to append the standard input to the contents of the input files
     * @param stdin    InputStream containing arguments from Stdin
     * @param fileName Array of String of file names
     * @return A string representation of the content read from stdin. This content is what gets written to both stdout and the specified files.
     * @throws AbstractApplicationException If any IO errors occur during the read/write operations,
     *                                      or if the specified files cannot be found or accessed.
     *                                      Specifically, a TeeException is thrown encapsulating
     *                                      the underlying IOExceptions with appropriate error messages.
     */
    @Override
    public String teeFromStdin(Boolean isAppend, InputStream stdin, String... fileName) throws
        AbstractApplicationException {
        StringBuilder resultBuilder = new StringBuilder();
        List<String> lines;
        try {
            lines = IOUtils.getLinesFromInputStream(stdin);
        } catch (Exception e) {
            throw new TeeException(ERR_READING_STREAM, e);
        }
        String content = String.join(STRING_NEWLINE, lines);
        resultBuilder.append(content);

        for (String file : fileName) {
            try {
                if (isAppend) {
                    FileUtils.appendToFile(file, content);
                } else {
                    FileUtils.writeToFile(file, content);
                }
            } catch (IOException e) {
                throw new TeeException(e.getMessage() + ": " + ERR_FILE_NOT_FOUND, e);
            }
        }

        return resultBuilder.toString();
    }
}
