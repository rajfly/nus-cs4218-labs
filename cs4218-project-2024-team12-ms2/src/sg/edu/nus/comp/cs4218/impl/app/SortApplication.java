package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.FileUtils.validateFile;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class SortApplication implements SortInterface {

    /**
     * Runs the sort application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws SortException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        // Format: sort [-nrf] [FILES]
        if (stdout == null) {
            throw new SortException(ERR_NULL_STREAMS);
        }
        SortArgsParser sortArgsParser = new SortArgsParser();
        try {
            sortArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new SortException(ERR_INVALID_FLAG, e);
        }
        StringBuilder output = new StringBuilder();

        if (sortArgsParser.getFileNames().isEmpty()) {
            output.append(sortFromStdin(sortArgsParser.isFirstWordNumber(), sortArgsParser.isReverseOrder(), sortArgsParser.isCaseIndependent(), stdin));
        } else {
            output.append(sortFromFiles(sortArgsParser.isFirstWordNumber(), sortArgsParser.isReverseOrder(), sortArgsParser.isCaseIndependent(), sortArgsParser.getFileNames().toArray(new String[0])));
        }

        try {
            if (!output.toString().isEmpty()) {
                stdout.write(output.toString().getBytes());
            }
        } catch (IOException e) {
            throw new SortException(ERR_WRITE_STREAM);//NOPMD
        }
    }

    /**
     * Returns string containing the orders of the lines of the specified file
     *
     * @param isFirstWordNumber Boolean option to treat the first word of a line as a number
     * @param isReverseOrder    Boolean option to sort in reverse order
     * @param isCaseIndependent Boolean option to perform case-independent sorting
     * @param fileNames         Array of String of file names
     * @throws Exception
     */
    @Override
    public String sortFromFiles(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent,
                                String... fileNames) throws AbstractApplicationException {
        if (fileNames == null) {
            throw new SortException(ERR_NULL_ARGS);
        }
        List<String> lines = new ArrayList<>();
        for (String file : fileNames) {
            try {
              validateFile(file);
            } catch (IOException e) {
                throw new SortException(e.getMessage(), e);
            }

            try (InputStream input = IOUtils.openInputStream(file)) {
                lines.addAll(IOUtils.getLinesFromInputStream(input));
            } catch (ShellException | IOException e) {
                throw new SortException(ERR_IO_EXCEPTION, e);
            }

        }
        sortInputString(isFirstWordNumber, isReverseOrder, isCaseIndependent, lines);
        return String.join(STRING_NEWLINE, lines) + STRING_NEWLINE;
    }

    /**
     * Returns string containing the orders of the lines from the standard input
     *
     * @param isFirstWordNumber Boolean option to treat the first word of a line as a number
     * @param isReverseOrder    Boolean option to sort in reverse order
     * @param isCaseIndependent Boolean option to perform case-independent sorting
     * @param stdin             InputStream containing arguments from Stdin
     * @throws Exception
     */
    @Override
    public String sortFromStdin(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent,
                                InputStream stdin) throws AbstractApplicationException {
        if (stdin == null) {
            throw new SortException(ERR_NULL_STREAMS);
        }
        List<String> lines = null;
        try {
            lines = IOUtils.getLinesFromInputStream(stdin);
        } catch (Exception e) {
            throw new SortException(ERR_IO_EXCEPTION, e);
        }
        sortInputString(isFirstWordNumber, isReverseOrder, isCaseIndependent, lines);
        return String.join(STRING_NEWLINE, lines) + STRING_NEWLINE;
    }

    /**
     * Sorts the input ArrayList based on the given conditions. Invoking this function will mutate the ArrayList.
     *
     * @param isFirstWordNumber Boolean option to treat the first word of a line as a number
     * @param isReverseOrder    Boolean option to sort in reverse order
     * @param isCaseIndependent Boolean option to perform case-independent sorting
     * @param input             ArrayList of Strings of lines
     */
    private void sortInputString(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent,
                                 List<String> input) {
        Collections.sort(input, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String temp1 = isCaseIndependent ? str1.toUpperCase() : str1;//NOPMD
                String temp2 = isCaseIndependent ? str2.toUpperCase() : str2;//NOPMD

                // Extract the first group of numbers if possible.
                if (isFirstWordNumber && !temp1.isEmpty() && !temp2.isEmpty()) {
                    String chunk1 = getChunk(temp1);//NOPMD
                    String chunk2 = getChunk(temp2);//NOPMD

                    // If both chunks can be represented as numbers, sort them numerically.
                    int result = 0;
                    if (Character.isDigit(chunk1.charAt(0)) && Character.isDigit(chunk2.charAt(0))) {
                        result = new BigInteger(chunk1).compareTo(new BigInteger(chunk2));
                    } else {
                        result = chunk1.compareTo(chunk2);
                    }
                    if (result != 0) {
                        return result;
                    }
                    return temp1.substring(chunk1.length()).compareTo(temp2.substring(chunk2.length()));
                }

                return temp1.compareTo(temp2);
            }
        });
        if (isReverseOrder) {
            Collections.reverse(input);
        }
    }

    /**
     * Extracts a chunk of numbers or non-numbers from str starting from index 0.
     *
     * @param str Input string to read from
     */
    private String getChunk(String str) {
        int startIndexLocal = 0;
        StringBuilder chunk = new StringBuilder();
        final int strLen = str.length();
        char chr = str.charAt(startIndexLocal++);
        chunk.append(chr);
        final boolean extractDigit = Character.isDigit(chr);
        while (startIndexLocal < strLen) {
            chr = str.charAt(startIndexLocal++);
            if ((extractDigit && !Character.isDigit(chr)) || (!extractDigit && Character.isDigit(chr))) {
                break;
            }
            chunk.append(chr);
        }
        return chunk.toString();
    }
}
