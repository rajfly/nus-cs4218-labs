package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class MvArgsParser extends ArgsParser{
    public static final char FLAG_NO_OVERWRITE = 'n';

    public MvArgsParser() {
        super();

        legalFlags.add(FLAG_NO_OVERWRITE);
    }

    public Boolean isNoOverwrite() {
        return flags.contains(FLAG_NO_OVERWRITE);
    }

    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
