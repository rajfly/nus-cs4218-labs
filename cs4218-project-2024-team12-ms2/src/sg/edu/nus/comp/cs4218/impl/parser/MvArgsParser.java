package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.ArrayList;
import java.util.List;

public class MvArgsParser extends ArgsParser {
    private final static char FLAG_NO_OVERWRITE = 'n';

    /**
     * Constructs a new MvArgsParser object.
     */
    public MvArgsParser() {
        super();
        legalFlags.add(FLAG_NO_OVERWRITE);
    }

    /**
     * Checks if the no overwrite flag is set.
     *
     * @return true if the no overwrite flag is set, false otherwise.
     */
    public Boolean isNoOverwrite() {
        return flags.contains(FLAG_NO_OVERWRITE);
    }

    /**
     * Get the source files.
     *
     * @return the source files.
     */
    public List<String> getSourceFiles() {
        int numberOfArgs = nonFlagArgs.size();

        if (nonFlagArgs.isEmpty()) {
            return new ArrayList<>();
        }
        if (numberOfArgs == 1) {
            return nonFlagArgs;
        }
        return nonFlagArgs.subList(0, numberOfArgs - 1);
    }

    /**
     * Retrieves the last argument from the list of non-flag arguments, which is assumed to be the destination path.
     * If the list contains only one argument or is empty, it implies there is no valid destination, thus null is returned.
     *
     * @return A string representing the destination pathname or null if the destination is not available or defined.
     */
    public String getDestination() {
        return nonFlagArgs.size() > 1
                ? nonFlagArgs.get(nonFlagArgs.size() - 1)
                : null;
    }

}
