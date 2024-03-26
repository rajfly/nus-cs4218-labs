package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class LsArgsParser extends ArgsParser {
    private final static char FLAG_IS_RECURSIVE = 'R';
    private final static char FLAG_SORT_BY_EXT = 'X';

    public LsArgsParser() {
        super();
        legalFlags.add(FLAG_IS_RECURSIVE);
        legalFlags.add(FLAG_SORT_BY_EXT);
    }

    /**
     * Checks if the recursive flag is present.
     * Default value is false if no legal flag is parsed.
     *
     * @return true if recursive flag is present, false otherwise.
     */
    public Boolean isRecursive() {
        return flags.contains(FLAG_IS_RECURSIVE);
    }

    /**
     * Checks if the sort by extension flag is present.
     * Default value is false if no legal flag is parsed.
     *
     * @return true if sort by extension flag is present, false otherwise.
     */
    public Boolean isSortByExt() {
        return flags.contains(FLAG_SORT_BY_EXT);
    }

    /**
     * Returns the list of directories.
     *
     * @return List of directories.
     */
    public List<String> getDirectories() {
        return nonFlagArgs;
    }
}
