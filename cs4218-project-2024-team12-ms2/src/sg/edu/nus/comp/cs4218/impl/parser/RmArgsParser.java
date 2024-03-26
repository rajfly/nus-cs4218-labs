package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;

public class RmArgsParser  extends ArgsParser {
    public static final char FLAG_RECURSIVE = 'r';

    public static final char FLAG_EMPTY = 'd';


    public RmArgsParser() {
        super();

        legalFlags.add(FLAG_RECURSIVE);
        legalFlags.add(FLAG_EMPTY);
    }

    /**
     * Checks if the recursive flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if recursive flag is present, false otherwise.
     */
    public Boolean isRecursive() {
        return flags.contains(FLAG_RECURSIVE);
    }

    /**
     * Checks if the empty flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if empty flag is present, false otherwise.
     */
    public Boolean isEmptyFolder() {
        return flags.contains(FLAG_EMPTY);
    }

    /**
     * Returns the list of file names.
     * @return List of file names.
     */
    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
