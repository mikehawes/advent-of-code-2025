package mikehawes.adventofcode.calendar2025.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day2Star1 {

    static void main() throws IOException {
        String document = Files.readString(Path.of("input/day2.txt"));
        IO.println("Invalid IDs sum: " + sumInvalidIdsInRanges(document));
    }

    public static long sumInvalidIdsInRanges(String ranges) {
        return Range.streamFromString(ranges)
                .mapToLong(Range::sumInvalidIds)
                .sum();
    }

}
