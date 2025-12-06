package mikehawes.adventofcode.calendar2025.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day5Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day5.txt"));
        IO.println("Fresh ingredients: " + countFreshIngredients(input));
    }

    public static long countFreshIngredients(String input) {
        String[] parts = input.split("\n\n");
        Ranges ranges = Ranges.from(parts[0]).removeOverlaps();
        return ranges.list().stream().mapToLong(Range::numIdsCovered).sum();
    }
}
