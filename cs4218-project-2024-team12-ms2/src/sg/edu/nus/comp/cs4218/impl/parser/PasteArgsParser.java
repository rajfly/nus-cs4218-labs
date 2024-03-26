package sg.edu.nus.comp.cs4218.impl.parser;

public class PasteArgsParser extends ArgsParser {
    private static final char CHAR_SERIAL = 's';

    public PasteArgsParser() {
        super();
        legalFlags.add(CHAR_SERIAL);
    }

    /**
     * Checks if the serial flag is present.
     * Default value is false if no legal flag is parsed.
     * @return true if serial flag is present, false otherwise.
     */
    public boolean isSerial() {
        return flags.contains(CHAR_SERIAL);
    }

    /**
     * Returns the list of file names.
     * @return List of file names.
     */
    public String[] getFiles() {
        return nonFlagArgs.toArray(new String[0]);
    }
}
