package mikehawes.adventofcode.calendar2025.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

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
        Machine.State start = machine.mapStateMachine();
        IO.println("Finding fewest button presses...");
        Instant startTime = Instant.now();
        LinkedList<StateAndPresses> queue = new LinkedList<>();
        queue.addLast(new StateAndPresses(start, 0));
        while (!queue.isEmpty()) {
            StateAndPresses state = queue.pollFirst();
            if (state.lightsMatch(machine)) {
                Instant endTime = Instant.now();
                IO.println("Found " + state.presses() + " in " + Duration.between(startTime, endTime));
                return state.presses();
            }
            state.enqueueNext(queue);
        }
        throw new IllegalArgumentException("No route to target found");
    }

    private record StateAndPresses(Machine.State state, int presses) {

        boolean lightsMatch(Machine machine) {
            return machine.lightsTarget().equals(state.lights());
        }

        void enqueueNext(LinkedList<StateAndPresses> queue) {
            int nextPresses = presses + 1;
            for (Machine.State pushed : state.buttonPushes()) {
                queue.addLast(new StateAndPresses(pushed, nextPresses));
            }
        }
    }
}
