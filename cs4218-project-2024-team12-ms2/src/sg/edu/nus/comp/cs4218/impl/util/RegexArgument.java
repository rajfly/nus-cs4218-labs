package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

@SuppressWarnings("PMD.AvoidStringBufferField")
public final class RegexArgument {
    private StringBuilder plaintext;
    private StringBuilder regex;
    private boolean isRegex;

    public RegexArgument() {
        this.plaintext = new StringBuilder();
        this.regex = new StringBuilder();
        this.isRegex = false;
    }

    public RegexArgument(String str) {
        this();
        merge(str);
    }

    /**
     * Appends a character to the regex argument.
     *
     * @param chr Character to append.
     */
    public void append(char chr) {
        plaintext.append(chr);
        String regexStr;
        if (chr == CHAR_FILE_SEP || chr == CHAR_FORWARDSLASH) {
            regexStr = File.separator;
        } else {
            regexStr = String.valueOf(chr);
        }
        regex.append(Pattern.quote(regexStr));
    }

    /**
     * Appends a string to the regex argument.
     *
     */
    public void appendAsterisk() {
        plaintext.append(CHAR_ASTERISK);
        regex.append("[^").append(StringUtils.fileSeparator()).append("]*");
        isRegex = true;
    }

    /**
     * Appends a string to the regex argument.
     *
     * @param other RegexArgument to append.
     */
    public void merge(RegexArgument other) {
        plaintext.append(other.plaintext);
        regex.append(other.regex);
        isRegex = isRegex || other.isRegex;
    }

    public void merge(String str) {
        plaintext.append(str);
        regex.append(Pattern.quote(str));
    }

    /**
     * Returns the list of files that match the regex argument.
     *
     * @return List of files that match the regex argument.
     */
    public List<String> globFiles() {
        List<String> globbedFiles = new LinkedList<>();

        if (isRegex) {
            Pattern regexPattern = Pattern.compile(regex.toString());
            StringBuilder dir = new StringBuilder();
            String[] tokens = plaintext.toString().replaceAll("\\\\", "/").split("/");
            for (int i = 0; i < tokens.length - 1; i++) {
                dir.append(tokens[i]).append(File.separator);
            }
            Path currentPath;
            if (dir.toString().isEmpty()) {
                currentPath = Paths.get(Environment.currentDirectory);
            } else {
                try {
                    currentPath = PathUtils.resolvePath(dir.toString());
                } catch (IllegalArgumentException e) {
                    return List.of(plaintext.toString());
                }
            }
            if (FileUtils.fileExists(currentPath) && Files.isReadable(currentPath)) {
                List<String> possiblePaths = List.of(Objects.requireNonNull(currentPath.toFile().list()));
                for (String candidate : possiblePaths) {
                    if (regexPattern.matcher(dir + candidate).matches()) {
                        globbedFiles.add(dir + candidate);
                    }
                }
            }

            Collections.sort(globbedFiles);
        }

        if (globbedFiles.isEmpty()) {
            globbedFiles.add(plaintext.toString());
        }

        return globbedFiles;
    }

    /**
     * Returns the list of files that match the regex argument.
     *
     * @return List of files that match the regex argument.
     */
    public boolean isEmpty() {
        return plaintext.length() == 0;
    }

    /**
     * Returns the regex argument as a string.
     *
     * @return Regex argument as a string.
     */
    public String getRegex() {
        return regex.toString();
    }

    /**
     * Returns the regex argument as a string.
     *
     * @return Regex argument as a string.
     */
    public String toString() {
        return plaintext.toString();
    }
}
