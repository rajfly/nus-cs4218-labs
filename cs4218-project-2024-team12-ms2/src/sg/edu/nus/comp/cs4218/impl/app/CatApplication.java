package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser.STRING_DASH;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_TAB;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplication implements CatInterface {
    /**
     * Runs the cat application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws CatException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new CatException(ERR_NULL_ARGS);
        }

        if (stdin == null || stdout == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }

        CatArgsParser parser = new CatArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw new CatException(e.getMessage(), e);
        }

        Boolean isLineNumber = parser.isLineNumber();
        String result;

        List<String> files = parser.getFileList();

        if (files.isEmpty()) {
            result = catStdin(isLineNumber, stdin);
        } else if (parser.isReadFromStdin()) {
            result = catFileAndStdin(isLineNumber, stdin, files.toArray(new String[0]));
        } else {
            result = catFiles(isLineNumber, files.toArray(new String[0]));
        }

        try {
            stdout.write(result.getBytes());
            stdout.write(STRING_NEWLINE.getBytes());
        } catch (IOException e) {
            throw new CatException(e.getMessage(), e);
        }

    }

    @Override
    public String catFiles(Boolean isLineNumber, String... fileName) throws AbstractApplicationException {
        List<String> fileLines = new ArrayList<>();
        for (String file : fileName) {
            try {
                List<String> lines = FileUtils.readFileContent(file);
                if (lines.isEmpty()) {
                    continue;
                }
                fileLines.add(formatWithLines(isLineNumber, lines));
            } catch (IOException e) {
                throw new CatException(e.getMessage(), e);
            }
        }
        return String.join(STRING_NEWLINE, fileLines);
    }

    @Override
    public String catStdin(Boolean isLineNumber, InputStream stdin) throws AbstractApplicationException {
        List<String> lines;
        try {
            lines = new ArrayList<>(IOUtils.getLinesFromInputStream(stdin));
        } catch (IOException e) {
            throw new CatException(ERR_READING_FILE, e);
        }
        return formatWithLines(isLineNumber, lines);
    }

    /**
     * Formats the lines with line numbers if isLineNumber is true.
     *
     * @param isLineNumber Boolean to indicate if line numbers are to be printed.
     * @param lines        List of lines to be formatted.
     * @return Formatted string.
     */
    private String formatWithLines(Boolean isLineNumber, List<String> lines) {
        if (isLineNumber.equals(Boolean.FALSE)) {
            return String.join(STRING_NEWLINE, lines);
        }

        StringBuilder stringBuilder = new StringBuilder();
        int lineNumber = 1;
        for (String line : lines) {
            if (!line.isEmpty()) {
                stringBuilder.append(lineNumber).append(CHAR_TAB).append(line);
                if (lineNumber != lines.size()) {
                    stringBuilder.append(STRING_NEWLINE);
                }
                lineNumber++;
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String catFileAndStdin(Boolean isLineNumber, InputStream stdin, String... fileName) throws AbstractApplicationException {
        List<String> lines = new ArrayList<>();

        for (String file : fileName) {
            if (file.equals(STRING_DASH)) {
                lines.add(catStdin(isLineNumber, stdin));
            } else {
                lines.add(catFiles(isLineNumber, file));
            }
        }
        return String.join(STRING_NEWLINE, lines);
    }
}
