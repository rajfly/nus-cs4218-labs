package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class CatArgsParser extends ArgsParser {
    public static final char FLAG_IS_PREFIXED = 'n';
    public static final String STRING_DASH = "-";

    public CatArgsParser() {
        super();
        legalFlags.add(FLAG_IS_PREFIXED);
    }

    /**
     * Checks if the line number flag is present.
     * Default value is false if no legal flag is parsed.
     *
     * @return true if line number flag is present, false otherwise.
     */
    public boolean isLineNumber() {
        return flags.contains(FLAG_IS_PREFIXED);
    }

    /**
     * Returns the list of file names.
     *
     * @return List of file names.
     */
    public List<String> getFileList() {
        return nonFlagArgs;
    }

    /**
     * Checks if the file names contain a dash.
     *
     * @return true if file names contain a dash, false otherwise.
     */
    public boolean isReadFromStdin() {
        return nonFlagArgs.contains(STRING_DASH);
    }

}
