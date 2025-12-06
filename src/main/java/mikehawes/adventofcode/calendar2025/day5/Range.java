package mikehawes.adventofcode.calendar2025.day5;

import java.util.Optional;

public record Range(long start, long end) {

    public static Range from(String string) {
        String[] parts = string.split("-");
        return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    public boolean contains(long id) {
        return id >= start && id <= end;
    }

    public Optional<Range> merge(Range range) {
        if (range.start > (end + 1) || range.end < (start - 1)) {
            return Optional.empty();
        }
        return Optional.of(new Range(Math.min(start, range.start), Math.max(end, range.end)));
    }

    public long numIdsCovered() {
        return end - start + 1;
    }
}
