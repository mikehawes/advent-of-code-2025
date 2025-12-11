package mikehawes.adventofcode.calendar2025.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class Day10Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day10.txt"));
        IO.println("Fewest button presses: " + fewestButtonPresses(input));
    }

    public static int fewestButtonPresses(String input) {
        return input.lines()
                .map(Machine::from)
                .mapToInt(Day10Star2::fewestButtonPresses)
                .sum();
    }

    private static int fewestButtonPresses(Machine machine) {
        IO.println("Machine: " + machine);
        List<Integer> maxPressesPerButton = machine.buttons().stream()
                .map(button -> button.maxPressesForTarget(machine))
                .toList();
        long states = maxPressesPerButton.stream().mapToLong(i -> (long) i).reduce(1L, (a, b) -> a * b);
        long maxPresses = maxPressesPerButton.stream().mapToLong(i -> (long) i).sum();
        IO.println("Found states: " + states);
        IO.println("Found max presses: " + maxPresses);
        Joltages start = Joltages.allZero(machine);
        return Dijkstra.fewestSteps(start, machine.joltagesTarget(), Function.identity(),
                joltages -> machine.buttons().stream()
                        .map(button -> button.press(joltages))
                        .toList());
    }
}
