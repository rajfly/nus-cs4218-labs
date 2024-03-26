package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CutInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CutException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.exception.RmException;
import sg.edu.nus.comp.cs4218.exception.TeeException;
import sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CutApplication implements CutInterface {
    InputStream inputStream;
    /**
     * Executes the 'cut' operation based on the provided arguments and streams.
     * Parses the input arguments, determines whether to perform the 'cut' operation on standard input or files,
     * and writes the output to the provided output stream.
     *
     * @param args   the arguments passed to the 'cut' command
     * @param stdin  the input stream from which to read input data
     * @param stdout the output stream to which the 'cut' result will be written
     * @throws CutException if there is an error during the 'cut' operation, such as invalid arguments, streams, or I/O exceptions
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CutException {
        Boolean isChar;
        Boolean isByte;
        List<int[]> ranges;
        String output;
        inputStream = stdin;

        //check input and output stream
        if (stdout == null || stdin == null) {
            throw new CutException(ERR_NULL_STREAMS);
        }

        //check arguments
        if (args == null) {
            throw new CutException(ERR_NULL_ARGS);
        }
        if (args.length < 2) {
            throw new CutException(ERR_NO_ARGS);
        }

        // Parser
        CutArgsParser parser = new CutArgsParser();
        try {
            parser.parse(args);
        } catch (InvalidArgsException e) {
            throw (CutException) new CutException(ERR_INVALID_FLAG).initCause(e);
        }

        try {
            isChar = parser.isChar();
            isByte = parser.isByte();
            String[] files = parser.getFiles();
            ranges = parser.getRanges();
            // Read from stdin
            if (files.length == 0 || (files.length == 1 && files[0].contains("-"))) {
                output = cutFromStdin(isChar, isByte, ranges, stdin).trim() + STRING_NEWLINE;
                stdout.write(output.getBytes());
            } else { // Read from files
                output = cutFromFiles(isChar, isByte, ranges, files).trim() + STRING_NEWLINE;
                stdout.write(output.getBytes());
            }
        } catch (IOException e) {
            throw (CutException) new CutException(ERR_IO_EXCEPTION).initCause(e); //NOPMD
        } catch (Exception e) {
            throw new CutException(e.getMessage()); //NOPMD
        }
    }

    /**
     * Cuts data from the specified files based on the given parameters.
     *
     * @param isChar    whether to cut by character
     * @param isByte    whether to cut by byte
     * @param ranges    the ranges to cut
     * @param fileName  the names of the files from which to cut data
     * @return the cut data as a string
     * @throws CutException if there is an error accessing or processing the files, such as null arguments,
     *                      file not found, directory instead of file, lack of permission, or I/O exceptions
     */

    @Override
    public String cutFromFiles(Boolean isChar, Boolean isByte, List<int[]> ranges, String... fileName) throws AbstractApplicationException {
        if (fileName == null) {
            throw new CutException(ERR_NULL_ARGS);
        }
        List<String> lines = new ArrayList<>();
        for (String file : fileName) {
            File node = IOUtils.resolveFilePath(file).toFile();
            if (!node.exists()) {
                throw new CutException(ERR_FILE_NOT_FOUND);
            }
            if (node.isDirectory()) {
                throw new CutException(ERR_IS_DIR);
            }
            if (!node.canRead()) {
                throw new CutException(ERR_NO_PERM);
            }
            try(InputStream input = IOUtils.openInputStream(file)) {
                lines.addAll(IOUtils.getLinesFromInputStream(input));
                IOUtils.closeInputStream(input);
            } catch (Exception e){
                throw new CutException(ERR_IO_EXCEPTION);//NOPMD
            }
        }
        try {
            cutInputString(isChar, isByte, ranges, lines);
        } catch (Exception e) {
            throw new CutException(e.getMessage()); //NOPMD
        }
        return String.join(STRING_NEWLINE, lines); //NOPMD
    }

    /**
     * Cuts data from the standard input stream based on the given parameters.
     *
     * @param isChar    whether to cut by character
     * @param isByte    whether to cut by byte
     * @param ranges    the ranges to cut
     * @param stdin     the input stream from which to read data
     * @return the cut data as a string
     * @throws CutException if there is an error accessing or processing the input stream,
     *                      such as null input stream, or I/O exceptions
     */
    @Override
    public String cutFromStdin(Boolean isChar, Boolean isByte, List<int[]> ranges, InputStream stdin) throws CutException {
        if (stdin == null) {
            throw new CutException(ERR_NULL_STREAMS);
        }
        List<String> lines;
        try {
            lines = IOUtils.getLinesFromInputStream(stdin);
        } catch (IOException e) {
            throw new CutException(e.getMessage()); //NOPMD
        }
        try {
            cutInputString(isChar, isByte, ranges, lines);
        } catch (Exception e) {
            throw new CutException(e.getMessage()); //NOPMD
        }
        return String.join(STRING_NEWLINE, lines); //NOPMD
    }

    /**
     * Cuts data from the list of input lines based on the given parameters.
     *
     * @param isChar    whether to cut by character
     * @param isByte    whether to cut by byte
     * @param ranges    the ranges to cut
     * @param lines     the list of input lines from which to cut data
     * @throws CutException if there is an error during the cutting process,
     *                      such as invalid ranges or unsupported cut operation
     */
    private void cutInputString(Boolean isChar, Boolean isByte, List<int[]> ranges, List<String> lines) throws Exception {
        if (isChar) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                StringBuilder output = new StringBuilder();
                for(int[] range : ranges) {
                    output.append(line, range[0] - 1, range[1]);
                }
                lines.set(i, String.valueOf(output));
            }
        } else if (isByte) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                byte[] byteArray = line.getBytes();
                StringBuilder output = new StringBuilder();
                for(int[] range : ranges) {
                    byte[] byteArrayNew = Arrays.copyOfRange(byteArray, range[0] - 1, range[1]);
                    output.append(new String(byteArrayNew));
                }
                lines.set(i, String.valueOf(output));
            }
        }
    }

}
