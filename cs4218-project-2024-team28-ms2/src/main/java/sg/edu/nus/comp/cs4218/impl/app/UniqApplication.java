package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import sg.edu.nus.comp.cs4218.app.UniqInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser;

public class UniqApplication implements UniqInterface { //NOPMD
    /**
     * Runs application with specified input data and specified output stream.
     *
     * @param args
     * @param stdin
     * @param stdout
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (stdout == null) {
            throw new UniqException(ERR_NULL_STREAMS);
        }

        UniqArgsParser uniqArgsParser = new UniqArgsParser();
        try {
            uniqArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw (UniqException) new UniqException(e.getMessage()).initCause(e);
        }

        try {
            String output;
            if (uniqArgsParser.getInputFileName() == null || uniqArgsParser.getInputFileName().isEmpty()) {
                output = uniqFromStdin(uniqArgsParser.isCount(), uniqArgsParser.isRepeated(), uniqArgsParser.isAllRepeated(), stdin, uniqArgsParser.getOutputFileName());
            } else {
                output = uniqFromFile(uniqArgsParser.isCount(), uniqArgsParser.isRepeated(), uniqArgsParser.isAllRepeated(), uniqArgsParser.getInputFileName(), uniqArgsParser.getOutputFileName());
            }
            stdout.write(output.getBytes());
        } catch (IOException e) {
            throw (UniqException) new UniqException(ERR_IO_EXCEPTION).initCause(e);
        }
    }

    /**
     * Filters adjacent matching lines from INPUT_FILE or standard input and writes to an OUTPUT_FILE or to standard output.
     *
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @param inputFileName  of path to input file
     * @param outputFileName of path to output file (if any)
     * @throws Exception
     */
    @Override
    public String uniqFromFile(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, String inputFileName, String outputFileName) throws AbstractApplicationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            // Check if lines list is empty
            if (lines.isEmpty()) {
                return STRING_NEWLINE; // Return newline character
            }
            if (outputFileName == null || outputFileName.isEmpty()) {
                return processLines(isCount, isRepeated, isAllRepeated, lines);
            }
            else {
                if (Files.notExists(Paths.get(outputFileName))) {
                    Files.createFile(Paths.get(outputFileName));
                }
                List<String> processedLines = Collections.singletonList(processLines(isCount, isRepeated, isAllRepeated, lines));
                Files.write(Paths.get(outputFileName), processedLines.stream().collect(Collectors.joining(STRING_NEWLINE)).getBytes(StandardCharsets.UTF_8));
                return STRING_NEWLINE;
            }

        } catch (IOException e) {
            throw (UniqException) new UniqException(ERR_IO_EXCEPTION).initCause(e);
        }
    }

    /**
     * Filters adjacent matching lines from INPUT_FILE or standard input and writes to an OUTPUT_FILE or to standard output.
     *
     * @param isCount        Boolean option to prefix lines by the number of occurrences of adjacent duplicate lines
     * @param isRepeated     Boolean option to print only duplicate lines, one for each group
     * @param isAllRepeated  Boolean option to print all duplicate lines (takes precedence if isRepeated is set to true)
     * @param stdin          InputStream containing arguments from Stdin
     * @param outputFileName of path to output file (if any)
     * @throws Exception
     */
    @Override
    public String uniqFromStdin(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, InputStream stdin, String outputFileName) throws AbstractApplicationException {
        if (stdin == null) {
            throw new UniqException("Null input stream provided");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdin))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return processLines(isCount, isRepeated, isAllRepeated, lines);
        } catch (IOException e) {
            throw (UniqException) new UniqException(ERR_IO_EXCEPTION).initCause(e);
        }
    }

    /**
     * Check if line evaluates to true for isCount, isRepeated, isAllRepeated
     *
     * @param isCount
     * @param isRepeated
     * @param isAllRepeated
     * @param lines
     * @return
     */
    private String processLines(Boolean isCount, Boolean isRepeated, Boolean isAllRepeated, List<String> lines) {
        StringBuilder result = new StringBuilder();
        if (isCount && isAllRepeated) {
            result.append(findAllRepeatedAndCount(lines)); // Count adjacent duplicates for all repeated lines
        } else if (isCount && isRepeated) {
            result.append(findRepeatedAndCount(lines)); // Count adjacent duplicates for all repeated lines
        } else if (isAllRepeated) {
            result.append(findAllRepeatedLines(lines)); // Find all repeated lines
        } else if (isCount) {
            result.append(countAdjacentDuplicates(lines)); // Count adjacent duplicates
        } else if (isRepeated) {
            result.append(findRepeatedLines(lines)); // Find repeated lines
        } else {
            result.append(mergeAdjacentDuplicates(lines)); // Default behavior: merge adjacent duplicates
        }
        return result.toString();
    }

    /**
     * If isCount is True, prefix lines by the number of occurrences of adjacent duplicate lines
     *
     * @param lines
     * @return
     */
    private String countAdjacentDuplicates(List<String> lines) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).equals(lines.get(i - 1))) {
                count++;
            } else {
                result.append(count).append(" ").append(lines.get(i - 1)).append(System.lineSeparator()); //NOPMD
                count = 1;
            }
        }
        if (!lines.isEmpty()) {
            result.append(count).append(" ").append(lines.get(lines.size() - 1)).append(System.lineSeparator()); //NOPMD
        }
        return result.toString();
    }

    /**
     * If isCount is True, prefix lines by the number of occurrences of adjacent duplicate lines
     * If isAllRepeated, print all duplicate lines (takes precedence if used with d flag)
     *
     * @param lines
     * @return
     */
    private String findAllRepeatedAndCount(List<String> lines) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).equals(lines.get(i - 1))) {
                count++;
            } else {
                if (count > 1) {
                    for (int j = 0; j < count; j++) {
                        result.append(count).append(" ").append(lines.get(i - 1)).append(System.lineSeparator()); //NOPMD
                    }
                }
                count = 1;
            }
        }

        if (!lines.isEmpty() && count > 1) {
            for (int j = 0; j < count; j++) {
                result.append(count).append(" ").append(lines.get(lines.size() - 1)).append(System.lineSeparator()); //NOPMD
            }
        }

        return result.toString();
    }

    /**
     * If isCount is True, prefix lines by the number of occurrences of adjacent duplicate lines
     * If isRepeated, print only duplicate lines, one for each group
     *
     * @param lines
     * @return
     */
    private String findRepeatedAndCount(List<String> lines) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).equals(lines.get(i - 1))) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count).append(" ").append(lines.get(i - 1)).append(System.lineSeparator()); //NOPMD
                }
                count = 1;
            }
        }

        if (!lines.isEmpty() && count > 1) {
            result.append(count).append(" ").append(lines.get(lines.size() - 1)).append(System.lineSeparator()); //NOPMD
        }

        return result.toString();
    }

    /**
     * If isRepeated, print only duplicate lines, one for each group
     *
     * @param lines
     * @return
     */
    private String findRepeatedLines(List<String> lines) {
        StringBuilder result = new StringBuilder();
        if (lines.size() > 1) { // Check if there are at least two lines
            if (lines.get(0).equals(lines.get(1))) { // Check if the first two lines are the same
                result.append(lines.get(0)).append(System.lineSeparator());
            }
            for (int i = 2; i < lines.size(); i++) {
                if (lines.get(i).equals(lines.get(i - 1)) && !lines.get(i).equals(lines.get(i - 2))) {
                    result.append(lines.get(i)).append(System.lineSeparator());
                }
            }
        }
        return result.toString();
    }

    /**
     * If isAllRepeated, print all duplicate lines (takes precedence if used with d flag)
     *
     * @param lines
     * @return
     */
    private String findAllRepeatedLines(List<String> lines) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < lines.size() - 1; i++) {
            if (lines.get(i).equals(lines.get(i - 1)) && !lines.get(i).equals(lines.get(i + 1))) {
                result.append(lines.get(i)).append(System.lineSeparator());
                result.append(lines.get(i - 1)).append(System.lineSeparator());
            } else if (lines.get(i).equals(lines.get(i - 1))) {
                result.append(lines.get(i)).append(System.lineSeparator());
            }
        }
        // end case
        if (lines.get(lines.size() - 1).equals(lines.get(lines.size() - 2))) {
            result.append(lines.get(lines.size() - 1)).append(System.lineSeparator());
            result.append(lines.get(lines.size() - 2)).append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * Default case
     * @param lines
     * @return
     */
    private String mergeAdjacentDuplicates(List<String> lines) {
        StringBuilder result = new StringBuilder();
        if (!lines.isEmpty()) {
            result.append(lines.get(0)).append(System.lineSeparator());
            for (int i = 1; i < lines.size(); i++) {
                if (!lines.get(i).equals(lines.get(i - 1))) {
                    result.append(lines.get(i)).append(System.lineSeparator());
                }
            }
        }
        return result.toString();
    }

}
