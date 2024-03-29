package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GrepApplication implements GrepInterface { //NOPMD - suppressed GodClass - Adheres to SRP as it only handles Grep
    public static final String INVALID_PATTERN = "Invalid pattern syntax";
    public static final String EMPTY_PATTERN = "Pattern should not be empty.";
    public static final String IS_DIRECTORY = "Is a directory";
    public static final String NULL_POINTER = "Null Pointer Exception";

    private static final int NUM_ARGUMENTS = 3;
    private static final char CASE_INSEN_IDENT = 'i';
    private static final char COUNT_IDENT = 'c';
    private static final char PREFIX_FN = 'H';
    private static final int CASE_INSEN_IDX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int PREFIX_FN_IDX = 2;
    private static final char STDIN = '-';
    private static final String STDIN_LABEL = "(standard input): ";

    /**
     * Runs the grep application with the specified arguments.
     *
     * @param pattern          String specifying a regular expression in JAVA format
     * @param isCaseInsensitive Boolean option to perform case insensitive matching
     * @param isCountLines     Boolean option to only write out a count of matched lines
     * @param isPrefixFileName Boolean option to print file name with output lines
     *                         (not implemented)
     * @param fileNames        Array of file names (not including "-" for reading from stdin)
     */
    @Override
    public String grepFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, String... fileNames) throws AbstractApplicationException {
        if (pattern.isEmpty()) {
            throw new GrepException(EMPTY_PATTERN);
        }
        if (fileNames == null || pattern == null) {
            throw new GrepException(NULL_POINTER);
        }

        StringJoiner lineResults = new StringJoiner(STRING_NEWLINE);
        StringJoiner countResults = new StringJoiner(STRING_NEWLINE);

        grepResultsFromFiles(pattern, isCaseInsensitive, isPrefixFileName, lineResults, countResults, fileNames);

        String results = "";
        if (isCountLines) {
            results = countResults.toString() + STRING_NEWLINE;
        } else {
            if (!lineResults.toString().isEmpty()) {
                results = lineResults.toString() + STRING_NEWLINE;
            }
        }
        return results;
    }

    /**
     * Extract the lines and count number of lines for grep from files and insert them into
     * lineResults and countResults respectively.
     *
     * @param pattern           supplied by user
     * @param isCaseInsensitive supplied by user
     * @param isPrefixFileName  supplied by user
     * @param lineResults       a StringJoiner of the grep line results
     * @param countResults      a StringJoiner of the grep line count results
     * @param fileNames         a String Array of file names supplied by user
     */
    @SuppressWarnings("PMD.ExcessiveMethodLength")
    private void grepResultsFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isPrefixFileName, StringJoiner lineResults, StringJoiner countResults, String... fileNames) throws AbstractApplicationException {
        if (pattern.isEmpty()) {
            throw new GrepException(EMPTY_PATTERN);
        }
        int count;
        boolean isSingleFile = (fileNames.length == 1);
        for (String f : fileNames) {
            BufferedReader reader = null;
            try {
                String path = convertToAbsolutePath(f);
                File file = new File(path);
                if (!file.exists()) {
                    lineResults.add(f + ": " + ERR_FILE_NOT_FOUND);
                    countResults.add(f + ": " + ERR_FILE_NOT_FOUND);
                    continue;
                }
                if (file.isDirectory()) { // ignore if it's a directory
                    lineResults.add(f + ": " + IS_DIRECTORY);
                    countResults.add(f + ": " + IS_DIRECTORY);
                    continue;
                }
                reader = new BufferedReader(new FileReader(path));
                String line;
                Pattern compiledPattern;
                if (isCaseInsensitive) {
                    compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
                } else {
                    compiledPattern = Pattern.compile(pattern);
                }
                count = 0;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = compiledPattern.matcher(line);
                    if (matcher.find()) { // match
                        if (isSingleFile) {
                            if (isPrefixFileName) {
                                lineResults.add(f + ": " + line);
                            } else {
                                lineResults.add(line);
                            }
                        } else {
                            lineResults.add(f + ": " + line);
                        }
                        count++;
                    }
                }
                if (isSingleFile) {
                    if (isPrefixFileName) {
                        countResults.add(f + ": " + count);
                    } else {
                        countResults.add("" + count);
                    }
                } else {
                    countResults.add(f + ": " + count);
                }
                reader.close();
            } catch (PatternSyntaxException pse) {
                throw new GrepException(ERR_INVALID_REGEX, pse);
            } catch (FileNotFoundException e) {
                throw new GrepException(ERR_FILE_NOT_FOUND, e);
            } catch (IOException e) {
                throw new GrepException(ERR_IO_EXCEPTION, e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new GrepException(ERR_IO_EXCEPTION, e);
                    }
                }
            }
        }
    }

    /**
     * Converts filename to absolute path, if initially was relative path
     *
     * @param fileName supplied by user
     * @return a String of the absolute path of the filename
     */
    private String convertToAbsolutePath(String fileName) {
        String home = System.getProperty("user.home").trim();
        String currentDir = Environment.currentDirectory.trim();
        String convertedPath = convertPathToSystemPath(fileName);

        String newPath;
        if (convertedPath.length() >= home.length() && convertedPath.substring(0, home.length()).trim().equals(home)) {
            newPath = convertedPath;
        } else {
            newPath = currentDir + CHAR_FILE_SEP + convertedPath;
        }
        return newPath;
    }

    /**
     * Converts path provided by user into path recognised by the system
     *
     * @param path supplied by user
     * @return a String of the converted path
     */
    private String convertPathToSystemPath(String path) {
        String convertedPath = path;
        String pathIdentifier = "\\" + Character.toString(CHAR_FILE_SEP);
        convertedPath = convertedPath.replaceAll("(\\\\)+", pathIdentifier);
        convertedPath = convertedPath.replaceAll("/+", pathIdentifier);

        if (convertedPath.length() != 0 && convertedPath.charAt(convertedPath.length() - 1) == CHAR_FILE_SEP) {
            convertedPath = convertedPath.substring(0, convertedPath.length() - 1);
        }

        return convertedPath;
    }


    /**
     * Extract the lines and count number of lines for grep from stdin and
     * return as string output.
     *
     * @param pattern           supplied by user
     * @param isCaseInsensitive supplied by user
     * @param isCountLines      supplied by user
     * @param isPrefixFileName  supplied by user
     * @param stdin             an InputStream of the standard input
     */
    @Override
    public String grepFromStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, InputStream stdin) throws AbstractApplicationException {
        if (pattern.isEmpty()) {
            throw new GrepException(EMPTY_PATTERN);
        }
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
                        stringJoiner.add(STDIN_LABEL + line);
                    } else {
                        stringJoiner.add(line);
                    }
                    count++;
                }
            }
            reader.close();
        } catch (PatternSyntaxException pse) {
            throw new GrepException(ERR_INVALID_REGEX, pse);
        } catch (NullPointerException npe) {
            throw new GrepException(ERR_FILE_NOT_FOUND, npe);
        } catch (IOException e) {
            throw new GrepException(ERR_IO_EXCEPTION, e);
        }

        String results = "";
        if (isCountLines) {
            results = (isPrefixFileName ? STDIN_LABEL: "") + count + STRING_NEWLINE;
        } else {
            if (!stringJoiner.toString().isEmpty()) {
                results = stringJoiner.toString() + STRING_NEWLINE;
            }
        }
        return results;
    }

    /**
     * Runs the grep application with the specified arguments.
     * @param args
     * @param stdin
     * @param stdout
     * @throws AbstractApplicationException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        try {
            boolean[] grepFlags = new boolean[NUM_ARGUMENTS];
            ArrayList<String> inputFiles = new ArrayList<>();
            String pattern = getGrepArguments(args, grepFlags, inputFiles);
            String result = "";

            if (stdin == null && inputFiles.isEmpty()) {
                throw new Exception(ERR_NO_INPUT);
            }
            if (pattern == null) {
                throw new Exception(ERR_SYNTAX);
            }

            if (pattern.isEmpty()) {
                throw new Exception(EMPTY_PATTERN);
            } else {
                if (inputFiles.isEmpty()) {
                    result = grepFromStdin(pattern, grepFlags[CASE_INSEN_IDX], grepFlags[COUNT_INDEX], grepFlags[PREFIX_FN_IDX], stdin);
                } else {
                    String[] inputFilesArray = new String[inputFiles.size()];
                    inputFilesArray = inputFiles.toArray(inputFilesArray);
                    if (inputFiles.contains(STDIN + "")) {
                        result = grepFromFileAndStdin(pattern, grepFlags[CASE_INSEN_IDX], grepFlags[COUNT_INDEX], grepFlags[PREFIX_FN_IDX], stdin, inputFilesArray);
                    } else {
                        result = grepFromFiles(pattern, grepFlags[CASE_INSEN_IDX], grepFlags[COUNT_INDEX], grepFlags[PREFIX_FN_IDX], inputFilesArray);
                    }

                }
            }
            stdout.write(result.getBytes());
        } catch (GrepException grepException) {
            throw grepException;
        } catch (Exception e) {
            throw new GrepException(e.getMessage(), e);
        }
    }

    /**
     * Separates the arguments provided by user into the flags, pattern and input files.
     *
     * @param args       supplied by user
     * @param grepFlags  a bool array of possible flags in grep
     * @param inputFiles a ArrayList<String> of file names supplied by user
     * @return regex pattern supplied by user. An empty String if not supplied.
     */
    private String getGrepArguments(String[] args, boolean[] grepFlags, ArrayList<String> inputFiles) throws AbstractApplicationException {
        String pattern = null;
        boolean isFile = false; // files can only appear after pattern

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (isFile) {
                inputFiles.add(s);
            } else {
                if (!s.isEmpty() && arg[0] == CHAR_FLAG_PREFIX) {
                    arg = Arrays.copyOfRange(arg, 1, arg.length);
                    for (char c : arg) {
                        switch (c) {
                            case CASE_INSEN_IDENT:
                                grepFlags[CASE_INSEN_IDX] = true;
                                break;
                            case COUNT_IDENT:
                                grepFlags[COUNT_INDEX] = true;
                                break;
                            case PREFIX_FN:
                                grepFlags[PREFIX_FN_IDX] = true;
                                break;
                            default:
                                throw new GrepException(ERR_SYNTAX);
                        }
                    }
                } else { // pattern must come before file names
                    pattern = s;
                    isFile = true; // next arg onwards will be file
                }
            }
        }
        return pattern;
    }

    /**
     * Extract the lines and count number of lines for grep from stdin and files and insert them into
     * lineResults and countResults respectively.
     *
     * @param pattern           supplied by user
     * @param isCaseInsensitive supplied by user
     * @param isCountLines      supplied by user
     * @param isPrefixFileName  supplied by user
     * @param stdin             an InputStream of the standard input
     * @param fileNames         a String Array of file names supplied by user
     */
    @Override
    public String grepFromFileAndStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountLines, Boolean isPrefixFileName, InputStream stdin, String... fileNames) throws AbstractApplicationException {
        if (pattern.isEmpty()) {
            throw new GrepException(EMPTY_PATTERN);
        }
        StringJoiner stringJoiner = new StringJoiner(STRING_NEWLINE);
        boolean firstStream = true;
        for (String f : fileNames) {
            if (f.equals(STDIN + "") && firstStream) {
                stringJoiner.add(grepFromStdin(pattern, isCaseInsensitive, isCountLines, isPrefixFileName || fileNames.length > 1, stdin).trim());
                firstStream = false;
            } else {
                stringJoiner.add(grepFromFiles(pattern, isCaseInsensitive, isCountLines, isPrefixFileName || fileNames.length > 1, f).trim());
            }
        }
        return stringJoiner.toString() + STRING_NEWLINE;
    }
}
