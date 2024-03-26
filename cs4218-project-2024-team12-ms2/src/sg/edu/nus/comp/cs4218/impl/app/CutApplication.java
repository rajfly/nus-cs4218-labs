package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CutInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CutException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.FileUtils;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;

public class CutApplication implements CutInterface {

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
            throw new CutException(ERR_NULL_ARGS);
        }

        if (stdin == null) {
            throw new CutException(ERR_NO_ISTREAM);
        }

        if (stdout == null) {
            throw new CutException(ERR_NO_OSTREAM);
        }

        CutArgsParser cutArgsParser = new CutArgsParser();
        try {
            cutArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw new CutException(ERR_INVALID_FLAG, e);
        }

        if (!cutArgsParser.isChar() && !cutArgsParser.isByte()) {
            throw new CutException(ERR_SYNTAX);
        }

        if (cutArgsParser.isChar() && cutArgsParser.isByte()) {
            throw new CutException(ERR_TOO_MANY_ARGS);
        }

        String result;
        // validation of the arguments is performed here, and not again in the called methods
        // this assumes that the arguments passed to the `cutFromFiles` and `cutFromStdin` methods are already validated
        List<String> files = cutArgsParser.getFileList();
        List<int[]> ranges = cutArgsParser.getListRanges();

        if (files.isEmpty()) {
            result = cutFromStdin(cutArgsParser.isChar(), cutArgsParser.isByte(), ranges, stdin);
        } else {
            result = cutFromFiles(cutArgsParser.isChar(), cutArgsParser.isByte(), ranges, files.toArray(new String[0]));
        }

        try {
            stdout.write(result.getBytes());
        } catch (Exception e) {
            throw new CutException(ERR_WRITE_STREAM, e);
        }
    }

    /**
     * Cuts out selected portions of each line
     *
     * @param isCharPo Boolean option to cut by character position
     * @param isBytePo Boolean option to cut by byte position
     * @param ranges   List of 2-element arrays containing the start and end indices for cut.
     *                 For instance, cutting on the first column would be represented using a [1,1] array.
     * @param fileName Array of String of file names
     * @return
     * @throws Exception
     */
    @Override
    public String cutFromFiles(Boolean isCharPo, Boolean isBytePo, List<int[]> ranges, String... fileName) throws AbstractApplicationException {
        String result = "";
        List<String> lines = new ArrayList<>();

        for (String file : fileName) {
            try {
                FileUtils.validateFile(file);
                lines.addAll(FileUtils.readFileLines(file));
            } catch (IOException e) {
                throw new CutException(e.getMessage(), e);
            }
        }

        if (isCharPo) {
            result = cutByChar(lines, ranges);
        }

        if (isBytePo) {
            result = cutByByte(lines, ranges);
        }

        return result;
    }

    /**
     * Cuts out selected portions of each line
     *
     * @param isCharPo Boolean option to cut by character position
     * @param isBytePo Boolean option to cut by byte position
     * @param ranges   List of 2-element arrays containing the start and end indices for cut.
     *                 For instance, cutting on the first column would be represented using a [1,1] array.
     * @param stdin    InputStream containing arguments from Stdin
     * @return
     * @throws Exception
     */
    @Override
    public String cutFromStdin(Boolean isCharPo, Boolean isBytePo, List<int[]> ranges, InputStream stdin) throws AbstractApplicationException {
        String result = "";
        List<String> lines;

        try {
            lines = new ArrayList<>(IOUtils.getLinesFromInputStream(stdin));
        } catch (IOException e) {
            throw new CutException(ERR_READING_STREAM, e);
        }

        if (isCharPo) {
            result = cutByChar(lines, ranges);
        }
        if (isBytePo) {
            result = cutByByte(lines, ranges);
        }
        return result;
    }

    /**
     * Cuts out selected portions of each line by character.
     *
     * @param lines  List of lines to cut.
     * @param ranges List of 2-element arrays containing the start and end indices for cut.
     * @return String of cuts of selected portions of each line.
     * @throws CutException If the range is invalid
     */
    private String cutByChar(List<String> lines, List<int[]> ranges) throws CutException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            for (int[] range : ranges) {
                int start = range[0] - 1;
                int end = Math.min(range[1] - 1, line.length() - 1);

                // handle invalid ranges
                if (start < 0 || end < 0 || start > end) {
                    throw new CutException(ERR_INVALID_RANGE);
                }
                stringBuilder.append(line, start, end + 1);
            }
            stringBuilder.append(System.lineSeparator());
        }
        // remove last newline
        int separatorLength = StringUtils.STRING_NEWLINE.length();
        if (stringBuilder.length() >= separatorLength) {
            stringBuilder.setLength(stringBuilder.length() - separatorLength);
        }
        return stringBuilder.toString();
    }

    /**
     * Cuts out selected portions of each line by bytes.
     *
     * @param lines  List of lines to cut.
     * @param ranges List of 2-element arrays containing the start and end indices for cut.
     * @return String of cuts of selected portions of each line.
     * @throws CutException If the range is invalid
     */
    public String cutByByte(List<String> lines, List<int[]> ranges) throws CutException {
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : lines) {
            byte[] bytes = line.getBytes();
            for (int[] range : ranges) {
                int start = range[0] - 1;
                int end = Math.min(range[1] - 1, bytes.length - 1);

                // handle invalid ranges
                if (start < 0 || end < 0 || start > end) {
                    throw new CutException(ERR_INVALID_RANGE);
                }

                stringBuilder.append(new String(bytes, start, end - start + 1));
            }
            stringBuilder.append(System.lineSeparator());
        }
        // remove last newline
        int separatorLength = StringUtils.STRING_NEWLINE.length();
        if (stringBuilder.length() >= separatorLength) {
            stringBuilder.setLength(stringBuilder.length() - separatorLength);
        }
        return stringBuilder.toString();
    }

}
