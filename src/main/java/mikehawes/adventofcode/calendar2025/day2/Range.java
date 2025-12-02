package mikehawes.adventofcode.calendar2025.day2;

import java.util.Arrays;
import java.util.stream.Stream;

public record Range(long start, long end, String startString, String endString) {

    public static Range from(String rangeString) {
        String[] parts = rangeString.split("-");
        return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]), parts[0], parts[1]);
    }

    public static Stream<Range> streamFromString(String ranges) {
        return Arrays.stream(ranges.trim().split(","))
                .map(Range::from);
    }

    public long sumIdsWithOneRepeat() {
        long sum = 0;
        for (long i = start; i <= end; i++) {
            String id = Long.toString(i);
            int middle = id.length() / 2;
            if (id.substring(0, middle).equals(id.substring(middle))) {
                sum += i;
            }
        }
        return sum;
    }

    public long sumIdsWithRepeats() {
        long sum = 0;
        for (long i = start; i <= end; i++) {
            String id = Long.toString(i);
            int middle = id.length() / 2;
            for(int j = 1; j <= middle; j++) {
                if(isRepeat(id, id.substring(0, j))) {
                    sum += i;
                    break;
                }
            }
        }
        return sum;
    }

    private static boolean isRepeat(String id, String substring) {
        if (id.length() % substring.length() != 0) {
            return false;
        }
        for(int i=substring.length(); i<id.length(); i += substring.length()) {
            if (!id.startsWith(substring, i)) {
                return false;
            }
        }
        return true;
    }
}
