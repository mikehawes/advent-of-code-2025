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

}
