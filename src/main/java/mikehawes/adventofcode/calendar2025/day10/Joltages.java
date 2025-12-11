package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Joltages(List<Integer> levels) {

    public static Joltages from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Integer> levels = Stream.of(noBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
        return new Joltages(levels);
    }

    public static Joltages allZero(Machine machine) {
        int numLevels = machine.joltagesTarget().numberOfLevels();
        return new Joltages(IntStream.range(0, numLevels).mapToObj(_ -> 0).toList());
    }

    public boolean allEqualOrBelow(Joltages target) {
        for (int i = 0; i < levels.size(); i++) {
            if (levels.get(i) > target.levels.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int numberOfLevels() {
        return levels.size();
    }

    @Override
    public String toString() {
        return "{" + levels.stream().map(i -> "" + i).collect(Collectors.joining(",")) + "}";
    }
}
