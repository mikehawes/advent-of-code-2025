package mikehawes.adventofcode.calendar2025.day10;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public record Machine(IndicatorLights lightsTarget, List<Button> buttons) {
    public static Machine from(String input) {
        String[] parts = input.split(" ");
        IndicatorLights lights = IndicatorLights.from(parts[0]);
        List<Button> buttons = List.of(parts).subList(1, parts.length - 1).stream()
                .map(Button::from)
                .toList();
        return new Machine(lights, buttons);
    }

    public State mapStateMachine() {
        IO.println("Mapping state machine...");
        State start = initState(IndicatorLights.allOff(lightsTarget.numberOfLights()));
        Map<IndicatorLights, State> lightsToState = new HashMap<>();
        lightsToState.put(start.lights(), start);
        setButtonPushes(start, lightsToState);
        return start;
    }

    private void setButtonPushes(State state, Map<IndicatorLights, State> lightsToState) {
        List<State> newStates = new ArrayList<>(buttons.size());
        for (Button button : buttons) {
            IndicatorLights after = button.press(state.lights());
            State afterState = lightsToState.computeIfAbsent(after, lights -> {
                State newState = initState(lights);
                newStates.add(newState);
                return newState;
            });
            state.buttonPushes().add(afterState);
        }
        for (State newState : newStates) {
            setButtonPushes(newState, lightsToState);
        }
    }

    private State initState(IndicatorLights lights) {
        return new State(lights, new ArrayList<>(buttons.size()));
    }

    public record State(IndicatorLights lights, List<State> buttonPushes) {

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
            for (State buttonPush : buttonPushes) {
                out.println("  " + buttonPush.lights());
            }
            out.println();
            visitedStates.add(lights);
            for (State buttonPush : buttonPushes) {
                buttonPush.printIfVisit(out, visitedStates);
            }
        }
    }
}
