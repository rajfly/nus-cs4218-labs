package sg.edu.nus.comp.cs4218.impl.parser;

public class TeeArgsParser extends ArgsParser {
    private final static char FLAG_IS_APPEND = 'a';

    public TeeArgsParser() {
        super();
        legalFlags.add(FLAG_IS_APPEND);
    }

    /**
     * Checks if the append flag is present in the parsed arguments.
     *
     * @return {@code true} if the append flag is present, {@code false} otherwise.
     */
    public Boolean isAppend() {
        return flags.contains(FLAG_IS_APPEND);
    }

    /**
     * Retrieves the files specified in the parsed arguments.
     *
     * @return An array of strings containing the file names.
     */
    public String[] getFiles() {
        return nonFlagArgs.toArray(new String[0]);
    }
}
