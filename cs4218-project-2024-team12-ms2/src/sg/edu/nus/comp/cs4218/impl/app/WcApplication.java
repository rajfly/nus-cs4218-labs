package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.FileUtils.validateFile;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class WcApplication implements WcInterface { //NOPMD - suppressed GodClass - Adheres to SRP as it only handles wc command

    private static final String NUMBER_FORMAT = " %7d";
    private static final int LINES_INDEX = 0;
    private static final int WORDS_INDEX = 1;
    private static final int BYTES_INDEX = 2;

    /**
     * Runs the wc application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws  WcException If an I/O exception occurs
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException {
        // Format: wc [-clw] [FILES]
        if (args == null) {
            throw new WcException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        WcArgsParser wcArgsParser = new WcArgsParser();
        try {
            wcArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new WcException(ERR_INVALID_FLAG, e);
        }
        String result;
        if (wcArgsParser.getFileNames().isEmpty()) {
            result = countFromStdin(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(), wcArgsParser.isWordCount(), stdin);
        } else if (wcArgsParser.filesContainDash()){
            if (wcArgsParser.getFileNames().size() == 1) {
                result = countFromStdin(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(), wcArgsParser.isWordCount(), stdin);
            } else {
                result = countFromFileAndStdin(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(),
                        wcArgsParser.isWordCount(), stdin, wcArgsParser.getFileNames().toArray(new String[0]));
            }
        } else {
            result = countFromFiles(wcArgsParser.isByteCount(), wcArgsParser.isLineCount(), wcArgsParser.isWordCount(), wcArgsParser.getFileNames().toArray(new String[0]));
        }
        try {
            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
        } catch (IOException e) {
            throw new WcException(ERR_WRITE_STREAM, e);
        }
    }

    /**
     * Returns string containing the number of lines, words, and bytes in input files
     *
     * @param isBytes  Boolean option to count the number of Bytes
     * @param isLines  Boolean option to count the number of lines
     * @param isWords  Boolean option to count the number of words
     * @param fileName Array of String of file names
     * @throws AbstractApplicationException If an I/O exception occurs
     */
    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, //NOPMD
                                 String... fileName) throws AbstractApplicationException {
        if (fileName == null) {
            throw new WcException(ERR_NO_FILE_ARGS);
        }
        List<String> result = new ArrayList<>();
        long[] total = new long[3]; // lines, words, bytes


        for (String file : fileName) {
            try {
                validateFile(file);
            } catch (IOException e) {
                throw new WcException(e.getMessage(), e);
            }

            InputStream input; //NOPMD - suppressed CloseResource - Ensured that input is closed
            try {
                input = IOUtils.openInputStream(file);
            } catch (ShellException e) {
                throw new WcException(e.getMessage(), e);
            }
            long[] count = getCountReport(input); // lines words bytes
            try {
                IOUtils.closeInputStream(input);
            } catch (ShellException e) {
                throw new WcException(e.getMessage(), e);
            }

            // Update total count

            total[0] += count[0]; // lines
            total[1] += count[1]; // words
            total[2] += count[2]; // bytes

            StringBuilder formattedOutput = formatOutput(isBytes, isLines, isWords, count);
            formattedOutput.append(String.format(" %s", file));
            result.add(formattedOutput.toString());
        }

        // Print cumulative counts for all the files
        if (fileName.length > 1) {
            StringBuilder formattedOutput = formatOutput(isBytes, isLines, isWords, total);
            formattedOutput.append(" total");
            result.add(formattedOutput.toString());
        }
        return String.join(STRING_NEWLINE, result);
    }

    /**
     * Returns string containing the number of lines, words, and bytes in standard input
     *
     * @param isBytes Boolean option to count the number of Bytes
     * @param isLines Boolean option to count the number of lines
     * @param isWords Boolean option to count the number of words
     * @param stdin   InputStream containing arguments from Stdin
     * @throws AbstractApplicationException If an I/O exception occurs
     */
    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords,
                                 InputStream stdin) throws AbstractApplicationException {
        if (stdin == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        long[] count = getCountReport(stdin); // lines words bytes;

        StringBuilder formattedOutput = formatOutput(isBytes, isLines, isWords, count);

        return formattedOutput.toString();
    }

    @Override
    public String countFromFileAndStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin,
                                        String... fileName) throws AbstractApplicationException {
        if (stdin == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }

        if (fileName == null) {
            throw new WcException(ERR_NO_FILE_ARGS);
        }

        List<String> result = new ArrayList<>();

        long[] total = {0, 0, 0}; // contains [lines, words, bytes] count

        for (int i = 0; i < fileName.length; i++) {
            String file = fileName[i];
            if (!STRING_DASH.equals(file)) { // read from file
                processData(file, total, isBytes, isLines, isWords, result, false);
                continue;
            }

            // Read from stdin
            int nextIndex = i + 1;
            if (nextIndex < fileName.length && fileName[nextIndex].equals("<")) { // take in stdin from file
                // read from file
                if (nextIndex + 1 >= fileName.length) {
                    throw new WcException(ERR_NO_FILE_ARGS);
                }
                String stdInFile = fileName[nextIndex + 1];
                processData(stdInFile, total, isBytes, isLines, isWords, result, true);
                i = nextIndex + 1;
            } else { // take in stdin from stdin
                long[] stdinCount = getCountReport(stdin); // lines words bytes
                total[0] += stdinCount[0];
                total[1] += stdinCount[1];
                total[2] += stdinCount[2];

                result.add(formatOutput(isBytes, isLines, isWords, stdinCount).toString());
            }
        }

        StringBuilder formattedOutput = formatOutput(isBytes, isLines, isWords, total);
        formattedOutput.append(" total");
        result.add(formattedOutput.toString());

        return String.join(STRING_NEWLINE, result);
    };


    /**
     * Returns array containing the number of lines, words, and bytes based on data in InputStream.
     *
     * @param input An InputStream
     * @throws AbstractApplicationException If an I/O exception occurs
     */
    public long[] getCountReport(InputStream input) throws AbstractApplicationException {
        if (input == null) {
            throw new WcException(ERR_NULL_STREAMS);
        }
        long[] result = new long[3]; // lines, words, bytes

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int inRead = 0;
        boolean inWord = false;
        try {
            while ((inRead = input.read(data, 0, data.length)) != -1) {
                for (int i = 0; i < inRead; ++i) {
                    if (Character.isWhitespace(data[i])) {
                        // Use <newline> character here. (Ref: UNIX)
                        if (data[i] == '\n') {
                            ++result[LINES_INDEX];
                        }
                        if (inWord) {
                            ++result[WORDS_INDEX];
                        }

                        inWord = false;
                    } else {
                        inWord = true;
                    }
                }
                result[BYTES_INDEX] += inRead;
                buffer.write(data, 0, inRead);
            }
            buffer.flush();
            if (inWord) {
                ++result[WORDS_INDEX]; // To handle last word
            }
        } catch (IOException e) {
            throw new WcException(ERR_IO_EXCEPTION, e);
        }

        return result;
    }


    /**
        * Returns a formatted string containing the number of lines, words, and bytes.
        * @param isBytes Boolean option to count the number of Bytes
        * @param isLines Boolean option to count the number of lines
        * @param isWords Boolean option to count the number of words
        * @param total Array of long containing the total count of lines, words, and bytes
        * @return StringBuilder containing the formatted string
     */
    private StringBuilder formatOutput(Boolean isBytes, Boolean isLines, Boolean isWords, long... total) {
        // Format all output: " %7d %7d %7d %s"
        // Output in the following order: lines words bytes filename
        StringBuilder formattedOutput = new StringBuilder();
        if (isLines) {
            formattedOutput.append(String.format(NUMBER_FORMAT, total[0]));
        }
        if (isWords) {
            formattedOutput.append(String.format(NUMBER_FORMAT, total[1]));
        }
        if (isBytes) {
            formattedOutput.append(String.format(NUMBER_FORMAT, total[2]));
        }
        return formattedOutput;
    }

    /**
     * Processes the data from the file and updates the total count and result list.
     * @param file The file to process
     * @param total The total count of lines, words, and bytes
     * @param isBytes Boolean option to count the number of Bytes
     * @param isLines Boolean option to count the number of lines
     * @param isWords Boolean option to count the number of words
     * @param result The list of results
     * @param isStdin Boolean option to indicate if the file is stdin
     * @throws AbstractApplicationException If an I/O exception occurs
     */

    private void processData(String file, long[] total, boolean isBytes, boolean isLines, boolean isWords,
                             List<String> result, boolean isStdin) throws AbstractApplicationException {
        try {
            validateFile(file);
        } catch (IOException e) {
            throw new WcException(e.getMessage(), e);
        }

        InputStream input; //NOPMD - suppressed CloseResource - Ensured that input is closed
        try {
            input = IOUtils.openInputStream(file);
        } catch (ShellException e) {
            throw new WcException(e.getMessage(), e);
        }
        long[] count = getCountReport(input); // lines words bytes
        try {
            IOUtils.closeInputStream(input);
        } catch (ShellException e) {
            throw new WcException(e.getMessage(), e);
        }

        // Update total count
        total[0] += count[0]; // lines
        total[1] += count[1]; // words
        total[2] += count[2]; // bytes

        StringBuilder formattedOutput = formatOutput(isBytes, isLines, isWords, count);
        if (!isStdin) {
            formattedOutput.append(String.format(" %s", file));
        }
        result.add(formattedOutput.toString());
    }
}

