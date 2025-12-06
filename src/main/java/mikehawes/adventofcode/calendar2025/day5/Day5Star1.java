package mikehawes.adventofcode.calendar2025.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day5Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day5.txt"));
        IO.println("Fresh ingredients: " + countFreshIngredients(input));
    }

    public static long countFreshIngredients(String input) {
        String[] parts = input.split("\n\n");
        Ranges ranges = Ranges.from(parts[0]);
        List<Long> ids = parts[1].lines().map(Long::parseLong).toList();
        return ids.stream().filter(ranges::isFresh).count();
    }

    public record Range(long start, long end) {

        public static Range from(String string) {
            String[] parts = string.split("-");
            return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
        }

        public boolean contains(long id) {
            return id >= start && id <= end;
        }
    }

    public record Ranges(List<Range> list) {

        public static Ranges from(String string) {
            return new Ranges(string.lines().map(Range::from).toList());
        }

        public boolean isFresh(long id) {
            return list.stream().anyMatch(r -> r.contains(id));
        }
    }
}
