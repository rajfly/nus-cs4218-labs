package sg.edu.nus.comp.cs4218.impl.parser;

public class PasteArgsParser extends ArgsParser{
    private final static char PASTE_SERIAL = 's';

    public PasteArgsParser() {
        super();
        legalFlags.add(PASTE_SERIAL);
    }

    public Boolean isSerial() {
        return flags.contains(PASTE_SERIAL);
    }

    public int numFile() {
        int countFile = 0;
        for (String arg : nonFlagArgs) {
            if (!"-".equals(arg)) {
                countFile += 1;
            }
        }
        return countFile;
    }

    public int numStdin(){
        int countStdin = 0;
        for (String arg : nonFlagArgs) {
            if ("-".equals(arg)) {
                countStdin += 1;
            }
        }
        return countStdin;
    }

    public String[] getFiles() {
        return nonFlagArgs.stream()
                .toArray(String[]::new);
    }
}
