package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.WcCountExtractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_PERM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class WcApplication implements WcInterface {

    private static final String NUMBER_FORMAT = CHAR_TAB + "%s";
    private long totalLines;
    private long totalWords;
    private long totalBytes;

    /**
     * Runs the wc application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws WcException If exception occurs with {@code args}, {@code stdin} or {@code stdout}.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
        throws WcException {
        // Format: wc [-clw] [FILES]
        if (stdin == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        if (stdout == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        this.totalLines = 0;
        this.totalWords = 0;
        this.totalBytes = 0;
        WcArgsParser wcArgsParser = new WcArgsParser();
        try {
            wcArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw (WcException) new WcException(e.getMessage()).initCause(e);
        }
        if (wcArgsParser.getFileNames() == null) {
            throw new WcException(ERR_NULL_ARGS);
        }
        String result;
        List<String> filenames = wcArgsParser.getFileNames();
        if (filenames.isEmpty()) {
            result = countFromStdin(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(), wcArgsParser.isWordCount(), stdin);
        } else {
            result = countFromFileAndStdin(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(), wcArgsParser.isWordCount(), stdin, wcArgsParser.getFileNames().toArray(new String[0]));
        }
        try {
            stdout.write(result.getBytes());
        } catch (IOException e) {
            throw (WcException) new WcException(ERR_WRITE_STREAM).initCause(e);
        }
    }

    /**
     * Returns string containing the number of lines, words, and bytes in input files
     *
     * @param isBytes  Boolean option to count the number of Bytes
     * @param isLines  Boolean option to count the number of lines
     * @param isWords  Boolean option to count the number of words
     * @param fileName Array of String of file names
     * @throws WcException if {@code fileName} is null or exception occurs with reading files
     */
    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, //NOPMD
                                 String... fileName) throws WcException {
        if (fileName == null) {
            throw new WcException(ERR_NULL_ARGS);
        }
        List<String> result = new ArrayList<>();
        boolean hasNoFlags = !isLines && !isWords && !isBytes;
        for (String file : fileName) {
            File node = IOUtils.resolveFilePath(file).toFile();
            if (!node.exists()) {
                result.add("wc: " + ERR_FILE_NOT_FOUND);
                continue;
            }
            if (node.isDirectory()) {
                result.add("wc: " + ERR_IS_DIR);
                continue;
            }
            if (!Files.isReadable(IOUtils.resolveFilePath(file))) {
                result.add("wc: " + ERR_NO_PERM);
                continue;
            }

            long[] count;
            WcCountExtractor extractor = new WcCountExtractor();
            try (InputStream input = IOUtils.openInputStream(file)) {
                count = extractor.getCountReport(input);
            } catch (IOException e) {
                throw (WcException) new WcException(ERR_IO_EXCEPTION).initCause(e);
            } catch (ShellException e) {
                throw (WcException) new WcException(e.getMessage()).initCause(e);
            }

            // Update total count
            this.totalLines += count[0];
            this.totalWords += count[1];
            this.totalBytes += count[2];

            // Format all output: "\t%s \t%s \t%s %s"
            // Output in the following order: lines words bytes filename
            String formattedOutput = formatOutput(file, hasNoFlags, isLines, isWords, isBytes, count);
            result.add(formattedOutput);
        }
        return String.join(STRING_NEWLINE, result) + STRING_NEWLINE;
    }

    /**
     * Returns string containing the number of lines, words, and bytes in standard input
     *
     * @param isBytes Boolean option to count the number of Bytes
     * @param isLines Boolean option to count the number of lines
     * @param isWords Boolean option to count the number of words
     * @param stdin   InputStream containing arguments from Stdin
     * @throws WcException if {@code stdin} is null
     */
    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords,
                                 InputStream stdin) throws WcException {
        if (stdin == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        WcCountExtractor wcCountExtractor = new WcCountExtractor();
        long[] count = wcCountExtractor.getCountReport(stdin); // lines words bytes;

        boolean hasNoFlags = !isLines && !isWords && !isBytes;
        String formattedOutput = formatOutput("-", hasNoFlags, isLines, isWords, isBytes, count);

        // Update total count
        this.totalLines += count[0];
        this.totalWords += count[1];
        this.totalBytes += count[2];

        return formattedOutput + STRING_NEWLINE;
    }

    @Override
    public String countFromFileAndStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin, String... fileName) throws WcException {
        List<String> result = new ArrayList<>();

        for (String file : fileName) {
            if (("-").equals(file)) {
                String stdData = countFromStdin(isBytes, isLines, isWords, stdin);
                result.add(stdData);
            } else {
                String fileData = countFromFiles(isBytes, isLines, isWords, file);
                result.add(fileData);
            }
        }

        // Print cumulative counts for all the files
        if (fileName.length > 1) {
            boolean hasNoFlags = !isLines && !isWords && !isBytes;
            String formattedOutput = formatOutput("total", hasNoFlags, isLines, isWords, isBytes,
                this.totalLines, this.totalWords, this.totalBytes);
            result.add(formattedOutput);
            return String.join("", result) + STRING_NEWLINE;
        }

        return String.join("", result);
    }

    private String formatOutput(String file, Boolean hasNoFlags, Boolean isLines, Boolean isWords,
                                Boolean isBytes, long...count) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hasNoFlags) {
            stringBuilder.append(String.format(NUMBER_FORMAT, count[0]));
            stringBuilder.append(String.format(NUMBER_FORMAT, count[1]));
            stringBuilder.append(String.format(NUMBER_FORMAT, count[2]));
        } else {
            if (isLines) {
                stringBuilder.append(String.format(NUMBER_FORMAT, count[0]));
            }
            if (isWords) {
                stringBuilder.append(String.format(NUMBER_FORMAT, count[1]));
            }
            if (isBytes) {
                stringBuilder.append(String.format(NUMBER_FORMAT, count[2]));
            }
        }
        stringBuilder.append(String.format(" %s", file));
        return stringBuilder.toString();
    }
}
