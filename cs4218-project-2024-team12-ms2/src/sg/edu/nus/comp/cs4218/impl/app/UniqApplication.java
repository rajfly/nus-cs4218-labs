package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.comp.cs4218.app.UniqInterface;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

public class UniqApplication implements UniqInterface {
    /**
     * Runs application with specified input data and specified output stream.
     *
     * @param args
     * @param stdin
     * @param stdout
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws UniqException {
        if (args == null) {
            throw new UniqException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new UniqException(ERR_NO_OSTREAM);
        }

        if (stdin == null) {
            throw new UniqException(ERR_NO_ISTREAM);
        }

        UniqArgsParser parser = new UniqArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw new UniqException(e.getMessage(), e);
        }

        if (parser.getFileCount() > 2) {
            throw new UniqException(ERR_TOO_MANY_ARGS);
        }

        String result;
        Boolean isCount = parser.isCount();
        Boolean isRepeated = parser.isOnlyDuplicates();
        Boolean isAllRepeated = parser.isAllDuplicates();
        String inputFile = parser.getInputFile();
        String outputFile = parser.getOutputFile();


        if (inputFile == null || inputFile.equals(STRING_DASH)) {
            result = uniqFromStdin(isCount, isRepeated, isAllRepeated, stdin, outputFile);
        } else {
            result = uniqFromFile(isCount, isRepeated, isAllRepeated, inputFile, outputFile);
        }

        try {
            if (outputFile == null) {
                stdout.write(result.getBytes());
            }
            if (!result.isEmpty()) {
                stdout.write(STRING_NEWLINE.getBytes());
            }
        } catch (Exception e) {
            throw new UniqException(ERR_WRITE_STREAM, e);
        }
    }

    /**
     * Filters adjacent matching lines from INPUT_FILE or standard input and writes to an OUTPUT_FILE or to standard
     * output.
     *
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @param inputFileName  of path to input file
     * @param outputFileName of path to output file (if any)
     * @throws Exception
     */
    @Override
    public String uniqFromFile(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, String inputFileName,
                               String outputFileName) throws UniqException {

        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(FileUtils.readFileLines(inputFileName));
        } catch (Exception e) {
            throw new UniqException(e.getMessage(), e);
        }
        return processLines(lines, isCount, isRepeated, isAllRepeated, outputFileName);
    }

    /**
     * Filters adjacent matching lines from INPUT_FILE or standard input and writes to an OUTPUT_FILE or to standard
     * output.
     *
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @param stdin          InputStream containing arguments from Stdin
     * @param outputFileName of path to output file (if any)
     * @throws Exception
     */
    @Override
    public String uniqFromStdin(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, InputStream stdin,
                                String outputFileName) throws UniqException {
        List<String> lines;
        try {
            lines = IOUtils.getLinesFromInputStream(stdin);
        } catch (Exception e) {
            throw new UniqException(ERR_READING_STREAM, e);
        }
        return processLines(lines, isCount, isRepeated, isAllRepeated, outputFileName);
    }

    /**
     * Filters adjacent matching lines from INPUT_FILE or standard input and writes to an OUTPUT_FILE or to standard
     * output.
     *
     * @param lines          List of lines to process
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @param outputFileName of path to output file (if any)
     * @throws UniqException If an I/O exception occurs.
     */
    private String processLines(List<String> lines, Boolean isCount, Boolean isRepeated, Boolean isAllRepeated,
                                String outputFileName) throws UniqException {
        // if -c and -D, throw error
        if (isCount && isAllRepeated) {
            throw new UniqException(ERR_ILLEGAL_FLAG);
        }
        ArrayList<String[]> lineCounts = new ArrayList<>();
        int currIndex = 0;
        while (currIndex < lines.size()) {
            String currLine = lines.get(currIndex);
            int nextIndex = currIndex + 1;
            int duplicateCount = 1;
            while (nextIndex < lines.size() && lines.get(nextIndex).equals(currLine)) {
                nextIndex++;
                duplicateCount++;
            }
            lineCounts.add(new String[]{currLine, String.valueOf(duplicateCount)});
            currIndex = nextIndex;
        }

        String result = generateFormattedOutput(lineCounts, isCount, isRepeated, isAllRepeated);
        if (outputFileName != null) {
            try {
                FileUtils.writeFileContent(outputFileName, result);
            } catch (Exception e) {
                throw new UniqException(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Generates formatted output based on the flags provided (-c, -d, -D)
     *
     * @param lineCounts     List of lines and their respective counts
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @return String containing the formatted output
     */
    private String generateFormattedOutput(ArrayList<String[]> lineCounts, Boolean isCount, Boolean isRepeated, Boolean isAllRepeated) {
        List<String> processedLines = new ArrayList<>();
        for (String[] lineCount : lineCounts) {
            String text = lineCount[0];
            int occurrences = Integer.parseInt(lineCount[1]);

            // Handling based on the flags provided (-c, -d, -D)
            if (isAllRepeated) {
                if (occurrences > 1) {
                    addRepeatedLines(processedLines, text, occurrences);
                }
            } else if (isRepeated) {
                if (occurrences > 1) {
                    processedLines.add(formatLine(text, isCount, occurrences));
                }
            } else {
                processedLines.add(formatLine(text, isCount, occurrences));
            }
        }
        return String.join(STRING_NEWLINE, processedLines);
    }

    /**
     * Adds a line to the results list a specified number of times
     * @param results List of results
     * @param line Line to add
     * @param count Number of times to add the line
     */
    private void addRepeatedLines(List<String> results, String line, int count) {
        for (int i = 0; i < count; i++) {
            results.add(line);
        }
    }

    /**
     * Formats a line based on the flags provided (-c, -d, -D)
     *
     * @param line     Line to format
     * @param isCount  Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param count    Number of occurrences of the line
     * @return String containing the formatted line
     */
    private String formatLine(String line, Boolean isCount, int count) {
        if (!isCount) {
            return line;
        }

        return String.format("\t%d %s", count, line);
    }

}
