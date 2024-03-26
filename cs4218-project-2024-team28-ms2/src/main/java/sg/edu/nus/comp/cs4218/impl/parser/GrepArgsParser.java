package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.ArrayList;
import java.util.List;

public class GrepArgsParser extends ArgsParser {
    private final static char FLAG_IS_CASE = 'i';
    private final static char FLAG_IS_COUNT = 'c';
    private final static char FLAG_IS_PREFIX = 'H';
    private final static int INDEX_PATTERN = 0;
    private final static int INDEX_FILES = 1;

    public GrepArgsParser() {
        super();
        legalFlags.add(FLAG_IS_CASE);
        legalFlags.add(FLAG_IS_COUNT);
        legalFlags.add(FLAG_IS_PREFIX);
    }

    public String getPattern() {
        return nonFlagArgs.isEmpty() ? null : nonFlagArgs.get(INDEX_PATTERN);
    }

    public Boolean isCaseSensitive() {
        return flags.contains(FLAG_IS_CASE);
    }

    public Boolean isCountLines() {
        return flags.contains(FLAG_IS_COUNT);
    }

    public Boolean isPrefixFileName() {
        return flags.contains(FLAG_IS_PREFIX);
    }

    public List<String> getFileNames() {
        return nonFlagArgs.size() <= 1 ? new ArrayList<>() : nonFlagArgs.subList(INDEX_FILES, nonFlagArgs.size());
    }
}
