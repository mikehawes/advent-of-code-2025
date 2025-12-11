package mikehawes.adventofcode.calendar2025.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return lightsTarget + " " + buttons.stream().map(Button::toString).collect(Collectors.joining(" "));
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

}
