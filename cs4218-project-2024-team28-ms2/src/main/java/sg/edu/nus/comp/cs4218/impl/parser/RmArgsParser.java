package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.*;

public class RmArgsParser extends ArgsParser{
    private final static char FLAG_IS_RECURSIVE = 'r';
    private final static char FLAG_IS_EMPTY_FOLDER = 'd'; //NOPMD

    public RmArgsParser() {
        super();
        legalFlags.add(FLAG_IS_RECURSIVE);
        legalFlags.add(FLAG_IS_EMPTY_FOLDER);
    }

    public Boolean isRecursive() {
        return flags.contains(FLAG_IS_RECURSIVE);
    }

    public Boolean isEmptyFolder() {
        return flags.contains(FLAG_IS_EMPTY_FOLDER);
    }

    public List<String> getFiles() {
        return nonFlagArgs;
    }
}
