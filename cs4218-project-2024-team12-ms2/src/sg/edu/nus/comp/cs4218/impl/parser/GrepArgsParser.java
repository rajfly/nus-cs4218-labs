package sg.edu.nus.comp.cs4218.impl.parser;

public class GrepArgsParser extends ArgsParser {
    private final static char FLAG_IS_INVERT = 'v';
    private final static int INDEX_PATTERN = 0;
    private final static int INDEX_FILES = 1;

    public GrepArgsParser() {
        super();
        legalFlags.add(FLAG_IS_INVERT);
    }

    /**
     * Checks if the invert flag is present.
     * Default value is false if no legal flag is parsed.
     *
     * @return true if invert flag is present, false otherwise.
     */
    public Boolean isInvert() {
        return flags.contains(FLAG_IS_INVERT);
    }

    /**
     * Returns the pattern to be matched.
     *
     * @return pattern to be matched.
     */
    public String getPattern() {
        return nonFlagArgs.isEmpty() ? nonFlagArgs.get(INDEX_PATTERN) : null;
    }

    /**
     * Returns the list of file names.
     *
     * @return List of file names.
     */
    public String[] getFileNames() {
        return nonFlagArgs.size() <= 1 ? null : nonFlagArgs.subList(INDEX_FILES, nonFlagArgs.size())
                .toArray(new String[0]);
    }
}
