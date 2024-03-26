package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.List;

public class MkdirArgsParser extends ArgsParser{
    public static final char FLAG_IS_PARENT = 'p';

    public MkdirArgsParser() {
        super();

        legalFlags.add(FLAG_IS_PARENT);
    }

    public Boolean isParent() {
        return flags.contains(FLAG_IS_PARENT);
    }

    public List<String> getFileNames() {
        return nonFlagArgs;
    }
}
