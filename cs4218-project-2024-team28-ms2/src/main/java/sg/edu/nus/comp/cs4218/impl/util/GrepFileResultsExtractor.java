package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static sg.edu.nus.comp.cs4218.impl.app.GrepApplication.IS_DIRECTORY;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_REGEX;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IO_EXCEPTION;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.convertToAbsolutePath;

public final class GrepFileResultsExtractor {
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
    public void grepResultsFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isPrefixFileName,
                                     StringJoiner lineResults, StringJoiner countResults,
                                     String... fileNames) throws GrepException {
        if (isCaseInsensitive == null || isPrefixFileName == null) {
            throw new GrepException(ERR_NULL_ARGS);
        }
        int count;
        for (String f : fileNames) {
            String path = convertToAbsolutePath(f);
            File file = new File(path);
            if (!checkIfFileExists(file, f, lineResults, countResults)) {
                continue;
            }
            if (checkIfDirectory(file, f, lineResults, countResults)) {
                continue;
            };
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                Pattern compiledPattern = compilePattern(pattern, isCaseInsensitive);
                count = 0;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = compiledPattern.matcher(line);
                    if (matcher.find()) { // match
                        if (isPrefixFileName || fileNames.length > 1) {
                            lineResults.add(f + ": " + line);
                        } else {
                            lineResults.add(line);
                        }
                        count++;
                    }
                }
                checkIfPrefix(countResults, f, isPrefixFileName, count, fileNames.length);
            } catch (PatternSyntaxException pse) {
                throw (GrepException) new GrepException(ERR_INVALID_REGEX).initCause(pse);
            } catch (IOException e) {
                throw (GrepException) new GrepException(ERR_IO_EXCEPTION).initCause(e);
            }
        }
    }

    private boolean checkIfFileExists(File file, String filename, StringJoiner lineResults, StringJoiner countResults) {
        if (!file.exists()) {
            lineResults.add(filename + ": " + ERR_FILE_NOT_FOUND);
            countResults.add(filename + ": " + ERR_FILE_NOT_FOUND);
            return false;
        }
        return true;
    }

    private boolean checkIfDirectory(File file, String filename, StringJoiner lineResults, StringJoiner countResults) {
        if (file.isDirectory()) {
            lineResults.add(filename + ": " + IS_DIRECTORY);
            countResults.add(filename + ": " + IS_DIRECTORY);
            return true;
        }
        return false;
    }

    private Pattern compilePattern(String pattern, Boolean isCaseInsensitive) {
        if (isCaseInsensitive) {
            return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        } else {
            return Pattern.compile(pattern);
        }
    }

    private void checkIfPrefix(StringJoiner countResults, String filename, Boolean isPrefixFileName, int count,
                               int filenameLength) {
        if (isPrefixFileName || filenameLength > 1) {
            countResults.add(filename + ": " + count);
        } else {
            countResults.add("" + count);
        }
    }
}
