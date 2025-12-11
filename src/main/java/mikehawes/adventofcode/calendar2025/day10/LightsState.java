package mikehawes.adventofcode.calendar2025.day10;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public record LightsState(IndicatorLights lights, List<LightsState> buttonPushes) {

    public static LightsState mapFrom(Machine machine) {
        IO.println("Mapping state machine...");
        LightsState start = initState(machine, IndicatorLights.allOff(machine));
        Map<IndicatorLights, LightsState> lightsToState = new HashMap<>();
        lightsToState.put(start.lights(), start);
        start.setButtonPushes(machine, lightsToState);
        return start;
    }

    private static LightsState initState(Machine machine, IndicatorLights lights) {
        return new LightsState(lights, new ArrayList<>(machine.buttons().size()));
    }

    private void setButtonPushes(Machine machine, Map<IndicatorLights, LightsState> lightsToState) {
        List<LightsState> newStates = new ArrayList<>(machine.buttons().size());
        for (Button button : machine.buttons()) {
            IndicatorLights after = button.press(lights);
            LightsState afterState = lightsToState.computeIfAbsent(after, lights -> {
                LightsState newState = initState(machine, lights);
                newStates.add(newState);
                return newState;
            });
            buttonPushes.add(afterState);
        }
        for (LightsState newState : newStates) {
            newState.setButtonPushes(machine, lightsToState);
        }
    }

    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(os, false, StandardCharsets.UTF_8);
        printIfVisit(out, new HashSet<>());
        out.flush();
        return os.toString();
    }

    private void printIfVisit(PrintWriter out, Set<IndicatorLights> visitedStates) {
        if (visitedStates.contains(lights)) {
            return;
        }
        out.println(lights + ":");
        for (LightsState buttonPush : buttonPushes) {
            out.println("  " + buttonPush.lights());
        }
        out.println();
        visitedStates.add(lights);
        for (LightsState buttonPush : buttonPushes) {
            buttonPush.printIfVisit(out, visitedStates);
        }
    }
}
