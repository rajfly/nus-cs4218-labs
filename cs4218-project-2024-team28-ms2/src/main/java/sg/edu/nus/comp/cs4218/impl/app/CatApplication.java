package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_FILE_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.parser.CatArgsParser;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_GENERAL = "Exception Caught";
    private static final String ERR_NO_SUCH_FILE = "cat: %s: No such file or directory";

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
    public void run(String[] args, InputStream stdin, OutputStream stdout) //NOPMD
            throws AbstractApplicationException {
        // Format: cat [Option] [FILES]
        if (stdout == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }
        if (args == null) {
            throw new CatException(ERR_NULL_ARGS);
        }
        if (Arrays.asList(args).contains(null)) {
            throw new CatException(ERR_NULL_ARGS);
        }
        CatArgsParser catArgsParser = new CatArgsParser();
        try {
            catArgsParser.parse(args);
        } catch (InvalidArgsException e) {
            throw (CatException) new CatException(e.getMessage()).initCause(e);
        }
        StringBuilder output = new StringBuilder();
        try {
            if (catArgsParser.getFileNames().isEmpty()) {
                output.append(catStdin(catArgsParser.isLineNumber(), stdin));
            } else {
                List<String> fileNames = catArgsParser.getFileNames();
                if (fileNames.size() == 1 && fileNames.get(0).equals("-")) {
                    output.append(catStdin(catArgsParser.isLineNumber(), stdin));
                }
                else if (fileNames.contains("-")) {
                    output.append(catFileAndStdin(catArgsParser.isLineNumber(), stdin, fileNames.toArray(new String[0]))); // Read from stdin
                }
                else {
                    for (String fileName : fileNames) {
                        File file = new File(fileName);
                        if (file.isDirectory()) {
                            String dirName = new File(fileName).getName(); // Extract directory name
                            output.append(String.format("cat: %s: " + ERR_IS_DIR + STRING_NEWLINE, dirName));
                        } else if (file.exists()) {
                            output.append(catFiles(catArgsParser.isLineNumber(), fileName));
                        } else {
                            String errorMessage = file.isAbsolute() ? fileName : new File(fileName).getName();
                            output.append(String.format(ERR_NO_SUCH_FILE + STRING_NEWLINE, errorMessage)); // File does not exist
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw (CatException) new CatException(e.getMessage()).initCause(e);
        }
        try {
            if (!output.toString().isEmpty()) {
                stdout.write(output.toString().getBytes());
            }
        } catch (IOException e) {
            throw (CatException) new CatException(ERR_WRITE_STREAM).initCause(e);
        }

    }

    /**
     * Returns string containing the content of the specified file
     *
     * @param isLineNumber Prefix lines with their corresponding line number starting from 1
     * @param fileName     Array of String of file names (not including "-" for reading from stdin)
     * @return
     * @throws AbstractApplicationException
     */
    @Override
    public String catFiles(Boolean isLineNumber, String... fileName) throws AbstractApplicationException {
        if (fileName == null || fileName.length == 0) {
            return "";
        }
        else if (isLineNumber == null) {
            throw new CatException(ERR_NULL_ARGS);
        }

        StringBuilder result = new StringBuilder();
        for (String file : fileName) {
            File file1 = new File(file);
            // Check if the file is a directory
            if (file1.isDirectory()) {
                result.append(String.format("cat: %s: " + ERR_IS_DIR + STRING_NEWLINE, file1.getName()));
                throw new CatException(String.format("%s: " + ERR_IS_DIR, file1.getName()));
            }
            // Check if the file exists
            if (!file1.exists() || !file1.canRead()) {
                String errorMessage = file1.isAbsolute() ? file1.getAbsolutePath() : file1.getName();
                result.append(String.format(ERR_NO_SUCH_FILE + STRING_NEWLINE, errorMessage));
                throw new CatException(String.format(ERR_NO_SUCH_FILE, errorMessage));
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 1;
                while ((line = reader.readLine()) != null) {
                    if (isLineNumber) {
                        result.append(lineNumber).append(' ');
                    }
                    result.append(line).append(System.lineSeparator());
                    lineNumber++;
                }
            } catch (FileNotFoundException e) {
                throw (CatException) new CatException(ERR_READING_FILE).initCause(e);
            }  catch (IOException e) {
                throw (CatException) new CatException(ERR_GENERAL).initCause(e);
            }
        }
        return result.toString();
    }

    /**
     * Returns string containing the content of the standard input
     *
     * @param isLineNumber Prefix lines with their corresponding line number starting from 1
     * @param stdin        InputStream containing arguments from Stdin
     * @return
     * @throws AbstractApplicationException
     */
    @Override
    public String catStdin(Boolean isLineNumber, InputStream stdin) throws AbstractApplicationException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdin))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (isLineNumber) {
                    result.append(lineNumber).append(' ');
                }
                result.append(line).append(System.lineSeparator());
                lineNumber++;
            }
        } catch (IOException e) {
            throw (CatException) new CatException(ERR_GENERAL).initCause(e);
        }
        return result.toString();
    }

    /**
     * Returns string containing the content of the standard input and specified file
     *
     * @param isLineNumber Prefix lines with their corresponding line number starting from 1
     * @param stdin        InputStream containing arguments from Stdin
     * @param fileName     Array of String of file names (including "-" for reading from stdin)
     * @return
     * @throws AbstractApplicationException
     */
    @Override
    public String catFileAndStdin(Boolean isLineNumber, InputStream stdin, String... fileName) //NOPMD
            throws AbstractApplicationException {
        if (fileName == null || fileName.length == 0) {
            throw new CatException(ERR_NO_FILE_ARGS);
        }
        if (isLineNumber == null) {
            throw new CatException(ERR_NULL_ARGS);
        }
        if (stdin == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }

        // Handle files
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasReadStdin = false;
        for (String name : fileName) {
            if (name == null) {
                throw new CatException(ERR_NULL_ARGS);
            }
            if (("-").equals(name)) {
                // Handle stdin
                try {
                    hasReadStdin = true;
                    List<String> input = IOUtils.getLinesFromInputStream(stdin);
                    for (String line : input) {
                        stringBuilder.append(line).append(System.lineSeparator());
                    }
                } catch (Exception e) {
                    throw (CatException) new CatException("Error reading from stdin").initCause(e);
                }
                continue;
            }
            File file = IOUtils.resolveFilePath(name).toFile();
            if (file.isDirectory()) {
                throw new CatException(String.format("%s: " + ERR_IS_DIR, file.getName()));
            }
            if (!file.exists()) {
                String errorMessage = file.isAbsolute() ? file.getAbsolutePath() : file.getName();
                throw new CatException(String.format(ERR_NO_SUCH_FILE, errorMessage));
            }

            try (InputStream inputStream = new FileInputStream(file)){
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    int lineNo = 1;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (isLineNumber) {
                            stringBuilder.append(String.format("%d %s", lineNo++, line)).append(STRING_NEWLINE);
                        } else {
                            stringBuilder.append(line).append(STRING_NEWLINE);
                        }
                    }
                } catch (Exception e) {
                    throw (CatException) new CatException(ERR_READING_FILE).initCause(e);
                }
            } catch (Exception e) {
                throw (CatException) new CatException(e.getMessage()).initCause(e);
            }

        }

        // if stdin have already been read
        if (!hasReadStdin) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdin))) {
                int lineNo = 1;
                String line;
                while ((line = reader.readLine()) != null) {
                    if (isLineNumber) {
                        stringBuilder.append(String.format("%d %s", lineNo++, line)).append(STRING_NEWLINE);
                    } else {
                        stringBuilder.append(line).append(STRING_NEWLINE);
                    }
                }
            } catch (Exception e) {
                throw (CatException) new CatException(e.getMessage()).initCause(e);
            }
        }

        return stringBuilder.toString();
    }


}