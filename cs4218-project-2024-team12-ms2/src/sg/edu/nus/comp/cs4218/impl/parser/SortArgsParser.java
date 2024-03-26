package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class SortArgsParser extends ArgsParser {
    public static final char FLAG_IS_FIRST_NUM = 'n';
    public static final char FLAG_IS_REV_ORDER = 'r';
    public static final char FLAG_CASE_IGNORE = 'f';

    public SortArgsParser() {
        super();
        legalFlags.add(FLAG_IS_FIRST_NUM);
        legalFlags.add(FLAG_IS_REV_ORDER);
        legalFlags.add(FLAG_CASE_IGNORE);
    }

    /**
     * Checks if the first word is a number.
     * Default value is false if no legal flag is parsed.
     * @return true if first word is a number, false otherwise.
     */
    public Boolean isFirstWordNumber() {
        return flags.contains(FLAG_IS_FIRST_NUM);
    }

    /**
     * Checks if the reverse order flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if reverse order flag is present, false otherwise.
     */
    public Boolean isReverseOrder() {
        return flags.contains(FLAG_IS_REV_ORDER);
    }

    /**
     * Checks if the case ignore flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if case ignore flag is present, false otherwise.
     */
    public boolean isCaseIndependent() {
        return flags.contains(FLAG_CASE_IGNORE);
    }

    /**
     * Returns the list of file names.
     * @return List of file names.
     */
    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
