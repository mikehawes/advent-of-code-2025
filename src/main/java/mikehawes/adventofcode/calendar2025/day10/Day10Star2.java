package mikehawes.adventofcode.calendar2025.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        return StateMachineNode.mapJoltagesFrom(machine)
                .fewestButtonPresses(machine.joltagesTarget());
    }
}
