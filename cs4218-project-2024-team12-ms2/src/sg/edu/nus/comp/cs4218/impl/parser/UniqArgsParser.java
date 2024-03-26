package sg.edu.nus.comp.cs4218.impl.parser;

@SuppressWarnings({"PMD.LongVariable"})
public class UniqArgsParser extends ArgsParser {
    private final static char FLAG_IS_COUNT = 'c';
    private final static char FLAG_ONLY_DUPLICATES = 'd';
    private final static char FLAG_ALL_DUPLICATES = 'D';
    private final static int INDEX_INPUT_FILE = 0;
    private final static int INDEX_OUTPUT_FILE = 1;

    public UniqArgsParser() {
        super();
        legalFlags.add(FLAG_IS_COUNT);
        legalFlags.add(FLAG_ONLY_DUPLICATES);
        legalFlags.add(FLAG_ALL_DUPLICATES);
    }

    /**
     * Checks if the count flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if count flag is present, false otherwise.
     */
    public Boolean isCount() {
        return flags.contains(FLAG_IS_COUNT);
    }

    /**
     * Checks if the only duplicates flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if only duplicates flag is present, false otherwise.
     */
    public Boolean isOnlyDuplicates() {
        return flags.contains(FLAG_ONLY_DUPLICATES);
    }

    /**
     * Checks if the all duplicates flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if all duplicates flag is present, false otherwise.
     */
    public Boolean isAllDuplicates() {
        return flags.contains(FLAG_ALL_DUPLICATES);
    }

    /**
     * Returns the number of files.
     * @return Number of files.
     */
    public int getFileCount() {
        return nonFlagArgs.size();
    }

    /**
     * Returns the input file name.
     * @return Input file name.
     */
    public String getInputFile() {
        return nonFlagArgs.isEmpty()
                ? null
                : nonFlagArgs.get(INDEX_INPUT_FILE);
    }

    /**
     * Returns the output file name.
     * @return Output file name.
     */
    public String getOutputFile() {
        if (getInputFile() != null) {
            return nonFlagArgs.size() > INDEX_OUTPUT_FILE
                    ? nonFlagArgs.get(INDEX_OUTPUT_FILE)
                    : null;
        }
        return nonFlagArgs.size() > 0
                ? nonFlagArgs.get(0)
                : null;
    }
}
