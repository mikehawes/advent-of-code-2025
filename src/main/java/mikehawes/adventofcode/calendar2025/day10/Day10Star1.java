package mikehawes.adventofcode.calendar2025.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day10Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day10.txt"));
        IO.println("Fewest button presses: " + fewestButtonPresses(input));
    }

    public static int fewestButtonPresses(String input) {
        return input.lines()
                .map(Machine::from)
                .mapToInt(Day10Star1::fewestButtonPresses)
                .sum();
    }

    private static int fewestButtonPresses(Machine machine) {
        IO.println("Machine: " + machine);
        State start = machine.mapStateMachine();
        IO.println("Finding fewest button presses...");
        Map<IndicatorLights, Integer> lightsToPresses = new HashMap<>();
        lightsToPresses.put(start.lights(), 0);
        PriorityQueue<StateAndPresses> queue = new PriorityQueue<>(Comparator.comparing(StateAndPresses::presses));
        queue.add(new StateAndPresses(start, 0));
        while (!queue.isEmpty()) {
            StateAndPresses state = queue.poll();
            int nextPresses = state.presses() + 1;
            for (State pushed : state.buttonPushes()) {
                if (pushed.lights().equals(machine.lightsTarget())) {
                    IO.println("Found " + nextPresses);
                    return nextPresses;
                }
                int lastPresses = lightsToPresses.getOrDefault(pushed.lights(), Integer.MAX_VALUE);
                if (nextPresses < lastPresses) {
                    lightsToPresses.put(pushed.lights(), nextPresses);
                    queue.add(new StateAndPresses(pushed, nextPresses));
                }
            }
        }
        throw new IllegalArgumentException("No route to target found");
    }

    private record StateAndPresses(State state, int presses) {

        List<State> buttonPushes() {
            return state.buttonPushes();
        }
    }
}
