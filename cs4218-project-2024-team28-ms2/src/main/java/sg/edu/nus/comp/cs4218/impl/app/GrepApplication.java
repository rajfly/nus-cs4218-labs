package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.GrepFileResultsExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_WRITE_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GrepApplication implements GrepInterface {
    public static final String IS_DIRECTORY = "Is a directory";
    public static final String NULL_POINTER = "Null Pointer Exception";
    private static final String STDIN = "(standard input)";

    @Override
    public String grepFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, String... fileNames) throws GrepException {
        if (fileNames.length == 1 && fileNames[0] == null || pattern == null || isCaseInsensitive == null
            || isCountLines == null || isPrefixFileName == null) {
            throw new GrepException(NULL_POINTER);
        }

        StringJoiner lineResults = new StringJoiner(STRING_NEWLINE);
        StringJoiner countResults = new StringJoiner(STRING_NEWLINE);

        GrepFileResultsExtractor extractor = new GrepFileResultsExtractor();
        extractor.grepResultsFromFiles(pattern, isCaseInsensitive, isPrefixFileName,
            lineResults, countResults, fileNames);

        String results;
        if (isCountLines) {
            results = countResults.toString();
        } else {
            results = lineResults.toString();
        }
        return results + STRING_NEWLINE;
    }

    @Override
    public String grepFromStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, InputStream stdin) throws GrepException {
        int count = 0;
        StringJoiner stringJoiner = new StringJoiner(STRING_NEWLINE);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdin));
            String line;
            Pattern compiledPattern;
            if (isCaseInsensitive) {
                compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            } else {
                compiledPattern = Pattern.compile(pattern);
            }
            while ((line = reader.readLine()) != null) {
                Matcher matcher = compiledPattern.matcher(line);
                if (matcher.find()) { // match
                    if (isPrefixFileName) {
                        stringJoiner.add(STDIN + ": " + line);
                    } else {
                        stringJoiner.add(line);
                    }
                    count++;
                }
            }
        } catch (PatternSyntaxException pse) {
            throw (GrepException) new GrepException(ERR_INVALID_REGEX).initCause(pse);
        } catch (IOException e) {
            throw (GrepException) new GrepException(ERR_IO_EXCEPTION).initCause(e);
        }

        String results = "";
        if (isCountLines) {
            if (isPrefixFileName) {
                results = STDIN + ": " + count;
            } else {
                results = count + "";
            }
        } else {
            if (!stringJoiner.toString().isEmpty()) {
                results = stringJoiner.toString();
            }
        }
        return results + STRING_NEWLINE;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws GrepException {
        // Format: grep [-icH] PATTERN [FILES]
        if (stdin == null) {
            throw new GrepException(ERR_NULL_STREAMS);
        }
        if (stdout == null) {
            throw new GrepException(ERR_NULL_STREAMS);
        }
        GrepArgsParser grepArgsParser = new GrepArgsParser();
        try {
            grepArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw (GrepException) new GrepException(e.getMessage()).initCause(e);
        }
        if (grepArgsParser.getPattern() == null) {
            throw new GrepException(ERR_NO_REGEX);
        }
        String result;
        List<String> filenames = grepArgsParser.getFileNames();
        if (filenames.isEmpty()) {
            result = grepFromStdin(grepArgsParser.getPattern(), grepArgsParser.isCaseSensitive(),
                grepArgsParser.isCountLines(), grepArgsParser.isPrefixFileName(), stdin);
        } else {
            result = grepFromFileAndStdin(grepArgsParser.getPattern(), grepArgsParser.isCaseSensitive(),
                grepArgsParser.isCountLines(), grepArgsParser.isPrefixFileName(), stdin,
                grepArgsParser.getFileNames().toArray(new String[0]));
        }
        try {
            stdout.write(result.getBytes());
        } catch (IOException e) {
            throw (GrepException) new GrepException(ERR_WRITE_STREAM).initCause(e);
        }
    }

    @Override
    public String grepFromFileAndStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, InputStream stdin, String... fileNames) throws GrepException {
        List<String> result = new ArrayList<>();

        for (String file : fileNames) {
            if ("-".equals(file)) {
                String stdData = grepFromStdin(pattern, isCaseInsensitive, isCountLines, isPrefixFileName, stdin);
                if (!isPrefixFileName && fileNames.length > 1) {
                    String[] lines = stdData.split(STRING_NEWLINE);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String line : lines) {
                        stringBuilder.append(STDIN + ": ").append(line).append(STRING_NEWLINE);
                    }
                    stdData = stringBuilder.toString();
                }
                result.add(stdData);
            } else {
                String fileData = grepFromFiles(pattern, isCaseInsensitive, isCountLines, isPrefixFileName, file);
                if (!isPrefixFileName && fileNames.length > 1) {
                    String[] lines = fileData.split(STRING_NEWLINE);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String line : lines) {
                        stringBuilder.append(file).append(": ").append(line).append(STRING_NEWLINE);
                    }
                    fileData = stringBuilder.toString();
                }
                result.add(fileData);
            }
        }

        return String.join("", result);
    }
}
