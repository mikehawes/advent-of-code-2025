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
        StateMachineNode<IndicatorLights> start = StateMachineNode.mapLightsFrom(machine);
        IO.println("Finding fewest button presses...");
        Map<IndicatorLights, Integer> lightsToPresses = new HashMap<>();
        lightsToPresses.put(start.state(), 0);
        PriorityQueue<StateAndPresses> queue = new PriorityQueue<>(Comparator.comparing(StateAndPresses::presses));
        queue.add(new StateAndPresses(start, 0));
        while (!queue.isEmpty()) {
            StateAndPresses state = queue.poll();
            int nextPresses = state.presses() + 1;
            for (StateMachineNode<IndicatorLights> pushed : state.buttonPushes()) {
                if (pushed.state().equals(machine.lightsTarget())) {
                    IO.println("Found " + nextPresses);
                    return nextPresses;
                }
                int lastPresses = lightsToPresses.getOrDefault(pushed.state(), Integer.MAX_VALUE);
                if (nextPresses < lastPresses) {
                    lightsToPresses.put(pushed.state(), nextPresses);
                    queue.add(new StateAndPresses(pushed, nextPresses));
                }
            }
        }
        throw new IllegalArgumentException("No route to target found");
    }

    private record StateAndPresses(StateMachineNode<IndicatorLights> state, int presses) {

        List<StateMachineNode<IndicatorLights>> buttonPushes() {
            return state.buttonPushes();
        }
    }
}
