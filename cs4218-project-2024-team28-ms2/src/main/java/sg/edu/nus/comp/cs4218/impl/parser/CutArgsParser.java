package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.ArrayList;
import java.util.List;

public class CutArgsParser extends ArgsParser {
    private final static char CUT_CHAR = 'c';
    private final static char CUT_BYTE = 'b';

    public CutArgsParser() {
        super();
        legalFlags.add(CUT_CHAR);
        legalFlags.add(CUT_BYTE);
    }

    public Boolean isChar() {
        return flags.contains(CUT_CHAR);
    }

    public Boolean isByte() {
        return flags.contains(CUT_BYTE);
    }

    public List<int[]> getRanges() throws Exception {
        String argument = nonFlagArgs.get(0);
        List<int[]> outputs = new ArrayList<>();

        if (argument.contains("-")) { //range
            String[] rangeParams = argument.split("-");
            int start = Integer.parseInt(rangeParams[0]);
            int end = Integer.parseInt(rangeParams[1]);
            int[] output = new int[]{start, end};
            outputs.add(output);
        } else if (argument.length() == 1) { //single value
            int singleNum = Integer.parseInt(argument);
            int[] output = new int[]{singleNum, singleNum};
            outputs.add(output);
        } else {
            String[] rangeParams = argument.split(",");
            for (String param : rangeParams) {
                int paramInt = Integer.parseInt(param);
                int[] output = new int[]{paramInt, paramInt};
                outputs.add(output);
            }
        }
        return outputs;
    }

    public String[] getFiles() {
        return nonFlagArgs.subList(1, nonFlagArgs.size()).stream().toArray(String[]::new);
    }
}