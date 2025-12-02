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

    public long sumInvalidIds() {
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
}
