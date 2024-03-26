package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;
/**
 * Argument Parser for MkdirApplication
 */

public class MkdirArgsParser extends ArgsParser {
    public static final char FLAG_PARENT = 'p';

    public MkdirArgsParser() {
        super();
        legalFlags.add(FLAG_PARENT);
    }

    /**
     * Checks if the parent flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if parent flag is present, false otherwise.
     */
    public Boolean isParent() {
        return flags.contains(FLAG_PARENT);
    }

    /**
     * Returns the list of file names.
     * @return List of file names.
     */
    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
