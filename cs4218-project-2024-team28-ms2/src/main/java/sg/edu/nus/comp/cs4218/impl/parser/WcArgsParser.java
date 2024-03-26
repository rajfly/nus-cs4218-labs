package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class WcArgsParser extends ArgsParser {
    public static final char FLAG_IS_BYTE = 'c';

    public static final char FLAG_IS_LINE = 'l';

    public static final char FLAG_IS_WORD = 'w';

    public WcArgsParser() {
        super();

        legalFlags.add(FLAG_IS_BYTE);
        legalFlags.add(FLAG_IS_LINE);
        legalFlags.add(FLAG_IS_WORD);
    }

    public Boolean isByteCount() {
        return flags.contains(FLAG_IS_BYTE);
    }

    public Boolean isLineCount() {
        return flags.contains(FLAG_IS_LINE);
    }

    public Boolean isWordCount() {
        return flags.contains(FLAG_IS_WORD);
    }

    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
