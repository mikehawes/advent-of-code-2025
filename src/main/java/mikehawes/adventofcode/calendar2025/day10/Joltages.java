package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Joltages(List<Integer> levels) {

    public static Joltages from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Integer> levels = Stream.of(noBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
        return new Joltages(levels);
    }

    @Override
    public String toString() {
        return "{" + levels.stream().map(i -> "" + i).collect(Collectors.joining(",")) + "}";
    }
}
