package sg.edu.nus.comp.cs4218.impl.parser;

import java.util.*;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_DASH;

public class CutArgsParser extends ArgsParser {

    public static final String INVALID_RANGE = "Invalid range format";
    public static final char FLAG_CHAR = 'c';
    public static final char FLAG_BYTE = 'b';
    public static final String LIST_COMMA = ",";
    public static final String LIST_RANGE = "-";

    public CutArgsParser() {
        super();
        legalFlags.add(FLAG_CHAR);
        legalFlags.add(FLAG_BYTE);
    }

    /**
     * Checks if the char flag is present
     *
     * @return true if the char flag is present, false otherwise
     */
    public boolean isChar() {
        return flags.contains(FLAG_CHAR);
    }

    /**
     * Checks if the byte flag is present
     *
     * @return true if the byte flag is present, false otherwise
     */
    public boolean isByte() {
        return flags.contains(FLAG_BYTE);
    }

    /**
     * Parses the list of files from the non-flag arguments
     * If the non-flag arguments contain only the dash, return an empty list
     *
     * @return List of files
     */
    public List<String> getFileList() {
        if (nonFlagArgs.size() == 1 || (nonFlagArgs.size() > 1 && nonFlagArgs.get(1).equals(STRING_DASH))) {
            return Collections.emptyList();
        }
        return nonFlagArgs.subList(1, nonFlagArgs.size());
    }

    /**
     * Parses the list of ranges from the non-flag arguments
     *
     * @return List of int[] representing the ranges
     */
    public List<int[]> getListRanges() {
        if (nonFlagArgs.size() == 1 && (!nonFlagArgs.get(0)
                .replaceAll(LIST_COMMA, "")
                .replaceAll(LIST_RANGE, "")
                .chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }

        String listToParse = nonFlagArgs.get(0);
        List<int[]> ranges = new ArrayList<>();

        if (listToParse.contains(LIST_COMMA)) {
            String[] parts = listToParse.split(LIST_COMMA);
            for (String part : parts) {
                if (part.contains(LIST_RANGE)) {
                    String[] rangeParts = part.split(LIST_RANGE);
                    int start = Integer.parseInt(rangeParts[0]);
                    int end = Integer.parseInt(rangeParts[1]);
                    int[] range = new int[]{start, end};
                    ranges.add(range);
                    continue;
                }
                int pos = Integer.parseInt(part);
                int[] range = new int[]{pos, pos};
                ranges.add(range);
            }
        } else if (listToParse.contains(LIST_RANGE)) {
            String[] parts = listToParse.split(LIST_RANGE);
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            int[] range = new int[]{start, end};
            ranges.add(range);
            // single number
        } else {
            int pos = Integer.parseInt(listToParse);
            ranges.add(new int[]{pos, pos});
        }

        // remove duplicate int[]
        ranges = removeDuplicateRanges(ranges);

        // sort the ranges by start index, like the behaviour in linux
        ranges.sort(Comparator.comparingInt(o -> o[0]));
        return ranges;

    }

    /**
     * Removes duplicate ranges from the list of ranges
     *
     * @param ranges List of int[] representing the ranges
     * @return List of int[] representing the ranges, with duplicates ranges removed
     */
    public static List<int[]> removeDuplicateRanges(List<int[]> ranges) {
        Set<List<Integer>> set = new HashSet<>();
        List<int[]> uniqueArrays = new ArrayList<>();

        for (int[] array : ranges) {
            List<Integer> arrayAsList = Arrays.asList(Arrays.stream(array).boxed().toArray(Integer[]::new));
            if (!set.contains(arrayAsList)) {
                set.add(arrayAsList);
                uniqueArrays.add(array);
            }
        }

        return uniqueArrays;
    }

}
