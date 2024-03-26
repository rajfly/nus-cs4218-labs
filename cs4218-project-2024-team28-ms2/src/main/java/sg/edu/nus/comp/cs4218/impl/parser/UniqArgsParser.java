package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

public class UniqArgsParser extends ArgsParser {
    public static final char FLAG_COUNT = 'c';
    public static final char FLAG_REPEATED = 'd';
    public static final char FLAG_ALL_REPEATED = 'D';

    public UniqArgsParser() {
        super();
        legalFlags.add(FLAG_COUNT);
        legalFlags.add(FLAG_REPEATED);
        legalFlags.add(FLAG_ALL_REPEATED);
    }

    public Boolean isCount() {
        return flags.contains(FLAG_COUNT);
    }

    public Boolean isRepeated() {
        return flags.contains(FLAG_REPEATED);
    }

    public Boolean isAllRepeated() {
        return flags.contains(FLAG_ALL_REPEATED);
    }

    public String getInputFileName() {
        if (nonFlagArgs.size() >= 1) {
            return nonFlagArgs.get(0);
        }
        return "";
    }

    public String getOutputFileName() {
        if (nonFlagArgs.size() == 2) {
            return nonFlagArgs.get(1);
        }
        return "";
    }
}
