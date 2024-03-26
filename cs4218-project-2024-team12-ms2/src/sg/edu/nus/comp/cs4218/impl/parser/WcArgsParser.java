package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;

public class WcArgsParser extends ArgsParser {
    public static final char FLAG_BYTE_COUNT = 'c';

    public static final char FLAG_LINE_COUNT = 'l';

    public static final char FLAG_WORD_COUNT = 'w';

    public WcArgsParser() {
        super();

        legalFlags.add(FLAG_BYTE_COUNT);
        legalFlags.add(FLAG_LINE_COUNT);
        legalFlags.add(FLAG_WORD_COUNT);
    }

    /**
     * Checks if the lines flag is present.
     * Default value is true if no legal flag is parsed.
     * @return true if line flag is present, false otherwise.
     */
    public Boolean isByteCount() {
        if (flags.stream().anyMatch(legalFlags::contains)) {
            return flags.stream().anyMatch(flag -> flag == FLAG_BYTE_COUNT);
        }
        return true;
    }

    /**
     * Checks if the words flag is present.
     * Default value is true if no legal flag is parsed.
     * @return true if words flag is present, false otherwise.
     */
    public Boolean isLineCount() {
        if (flags.stream().anyMatch(legalFlags::contains)) {
            return flags.stream().anyMatch(flag -> flag == FLAG_LINE_COUNT);
        }
        return true;
    }

    /**
     * Checks if the bytes flag is present.
     * Default value is true if no legal flag is parsed.
     * @return true if bytes flag is present, false otherwise.
     */
    public Boolean isWordCount() {
        if (flags.stream().anyMatch(legalFlags::contains)) {
            return flags.stream().anyMatch(flag -> flag == FLAG_WORD_COUNT);
        }
        return true;
    }

    /**
     * Checks if the file names contain a dash.
     * @return true if file names contain a dash, false otherwise.
     */
    public Boolean filesContainDash() {
        return getFileNames().contains(STRING_DASH);
    }

    /**
     * Returns the file names.
     * @return list of file names.
     */
    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
