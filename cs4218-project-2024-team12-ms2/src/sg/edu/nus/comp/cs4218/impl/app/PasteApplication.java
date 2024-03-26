package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.PasteInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;


public class PasteApplication implements PasteInterface {  //NOPMD - suppressed GodClass - Adheres to SRP as it only handles paste command
    /**
     * Executes the paste application with the specified arguments. Merge lines from the given files or stdin.
     *
     * @param args   Array of arguments for the application. It can include file paths and "-" to denote stdin.
     *               If no files are specified, stdin is used exclusively.
     * @param stdin  An InputStream, used as the source of data if "-" is specified or no files are provided.
     * @param stdout An OutputStream where the command's output is written.
     * @throws PasteException If any errors occur during parsing arguments or processing input/output streams.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws PasteException {
        if (args == null) {
            throw new PasteException(ERR_NULL_ARGS);
        }
        if (stdin == null) {
            throw new PasteException(ERR_NO_ISTREAM);
        }
        if (stdout == null) {
            throw new PasteException(ERR_NO_OSTREAM);
        }

        PasteArgsParser pasteArgsParser = new PasteArgsParser();
        try {
            pasteArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new PasteException(e.getMessage(), e);
        }
        String[] files = pasteArgsParser.getFiles();
        boolean isSerial = pasteArgsParser.isSerial();
        boolean hasStdin = files.length == 0 || Arrays.asList(files).contains(STRING_DASH);
        boolean hasFiles = !Arrays.stream(files).allMatch(STRING_DASH::equals);
        String result;
        if (hasStdin) {
            if (hasFiles) {
                result = mergeFileAndStdin(isSerial, stdin, files);
            } else {
                // ASSUMPTION: if !isSerial, the number of columns will be same as number of dashes
                result = formatResult(mergeStdin(isSerial, stdin), Math.max(1, files.length));
            }
        } else {
            result = mergeFile(isSerial, files);
        }

        try {
            IOUtils.writeIfNotEmpty(stdout, result);
        } catch (IOException e) {
            throw new PasteException(e.getMessage(), e);
        }
    }

    /**
     * Formats the result string into columns based on the number of columns specified.
     *
     * @param result  The result string to be formatted.
     * @param columns The number of columns to format the result into.
     * @return The formatted result string.
     */
    private String formatResult(String result, int columns) {
        if (columns <= 1) {
            return result; // No need to format if only one column is present.
        }

        String[] lines = result.split(STRING_NEWLINE, -1); // Keep trailing empty strings to preserve the format.
        StringBuilder formattedResult = new StringBuilder();
        final String tabDelimiter = Character.toString(CHAR_TAB);
        final String newlineDelimiter = STRING_NEWLINE;

        for (int i = 0, lineCount = lines.length; i < lineCount; i++) {
            formattedResult.append(lines[i]);
            // Determine the correct delimiter based on the current line index and desired column count.
            String delimiter = ((i + 1) % columns == 0) ? newlineDelimiter : tabDelimiter;
            formattedResult.append(delimiter);
        }

        // Handle the case where the last line does not complete a full column set,
        // which would add an unnecessary tab at the end. This checks and removes it.
        int lastIndex = formattedResult.length() - 1;
        if (lastIndex >= 0 && formattedResult.charAt(lastIndex) == CHAR_TAB) {
            formattedResult.deleteCharAt(lastIndex);
        }

        return formattedResult.toString();
    }

    /**
     * Returns string of line-wise concatenated (tab-separated) Stdin arguments. If only one Stdin
     * arg is specified, echo back the Stdin.
     *
     * @param isSerial Paste one file at a time instead of in parallel
     * @param stdin    InputStream containing arguments from Stdin
     * @throws PasteException
     */
    @Override
    public String mergeStdin(Boolean isSerial, InputStream stdin) throws PasteException {
        if (stdin == null) {
            throw new PasteException(ERR_NULL_ARGS);
        }
        try {
            String delimiter;
            if (isSerial) {
                delimiter = Character.toString(CHAR_TAB);
            } else {
                delimiter = STRING_NEWLINE;
            }
            return String.join(delimiter, IOUtils.getLinesFromInputStream(stdin));
        } catch (IOException e) {
            throw new PasteException(e.getMessage(), e);
        }
    }

    /**
     * Returns string of line-wise concatenated (tab-separated) files. If only one file is
     * specified, echo back the file content.
     *
     * @param isSerial Paste one file at a time instead of in parallel
     * @param fileName Array of file names to be read and merged (not including "-" for reading from stdin)
     * @throws PasteException
     */
    @Override
    public String mergeFile(Boolean isSerial, String... fileName) throws PasteException {
        return mergeFileAndStdin(isSerial, System.in, fileName);
    }

    /**
     * Returns string of line-wise concatenated (tab-separated) files and Stdin arguments.
     *
     * @param isSerial  Paste one file at a time instead of in parallel
     * @param stdin     InputStream containing arguments from Stdin
     * @param fileNames Array of file names to be read and merged (including "-" for reading from stdin)
     * @throws PasteException If errors occur while reading from files or stdin.
     */
    @Override
    public String mergeFileAndStdin(Boolean isSerial, InputStream stdin, String... fileNames)
            throws PasteException {
        if (fileNames == null) {
            throw new PasteException(ERR_NULL_ARGS);
        }

        List<Iterator<String>> fileIterators = readFromFileAndStdin(stdin, fileNames);
        StringBuilder output = new StringBuilder();
        if (isSerial) {
            final String tab = Character.toString(CHAR_TAB);
            for (Iterator<String> iterator : fileIterators) {
                StringBuilder fileBuilder;
                fileBuilder = new StringBuilder();
                while (iterator.hasNext()) {
                    if (fileBuilder.length() > 0) {
                        fileBuilder.append(tab); // Append a tab before each line after the first
                    }
                    fileBuilder.append(iterator.next());
                }
                output.append(fileBuilder).append(STRING_NEWLINE);
            }
        } else {
            boolean anyFilesRemaining;
            final String tabDelimiter = Character.toString(CHAR_TAB);
            final String newLineDelimiter = STRING_NEWLINE;

            do {
                StringBuilder combinedLine = new StringBuilder();
                anyFilesRemaining = false;
                int processedFiles = 0;

                for (Iterator<String> iterator : fileIterators) {
                    if (iterator.hasNext()) {
                        combinedLine.append(iterator.next());
                        anyFilesRemaining = true; // There's still data to process
                    }
                    // Append a tab for all but the last element or a newline at the end
                    combinedLine.append((processedFiles++ < fileIterators.size() - 1) ? tabDelimiter : "");
                }

                // Only append the combined line to output if at least one iterator had an element
                if (anyFilesRemaining) {
                    // Ensure we add a newline after processing each set of lines from all iterators
                    output.append(combinedLine).append(newLineDelimiter);
                }

            } while (anyFilesRemaining); // Continue as long as there's data in any iterator
        }
        return output.toString().stripTrailing();
    }

    /**
     * Reads from files and stdin and returns a list of iterators for each source.
     *
     * @param stdin     InputStream containing arguments from Stdin
     * @param fileNames Array of file names to be read and merged (including "-" for reading from stdin)
     * @return List of iterators for each source.
     * @throws PasteException If errors occur while reading from files or stdin.
     */
    private List<Iterator<String>> readFromFileAndStdin(InputStream stdin, String... fileNames) throws PasteException {
        if (stdin == null) {
            throw new PasteException(ERR_NO_ISTREAM);
        }

        List<Iterator<String>> contentSources;
        contentSources = new ArrayList<>();
        Iterator<String> stdinIterator; // Cache for stdin iterator
        stdinIterator = null;
        boolean isStdinRead = false; // Flag to check if stdin has been read

        for (String fileName : fileNames) {
            if (fileName == null) {
                throw new PasteException(ERR_NULL_ARGS);
            }

            // Handle stdin ("-") input source
            if (STRING_DASH.equals(fileName)) {
                if (!isStdinRead) { // If stdin hasn't been read yet, read and cache it
                    try {
                        stdinIterator = IOUtils.getLinesFromInputStream(stdin).iterator();
                        isStdinRead = true; // Mark stdin as read
                    } catch (IOException e) {
                        throw new PasteException(e.getMessage(), e);
                    }
                }
                contentSources.add(stdinIterator); // Add cached stdin iterator
            } else {
                // Handle file input source
                try {
                    contentSources.add(FileUtils.readFileLines(fileName).iterator());
                } catch (IOException e) {
                    throw new PasteException(e.getMessage(), e);
                }
            }
        }
        return contentSources;
    }
}
