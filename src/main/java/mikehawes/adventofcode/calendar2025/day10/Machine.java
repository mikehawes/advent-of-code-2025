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

    public LightsState mapLightsStateMachine() {
        IO.println("Mapping state machine...");
        LightsState start = initState(IndicatorLights.allOff(lightsTarget.numberOfLights()));
        Map<IndicatorLights, LightsState> lightsToState = new HashMap<>();
        lightsToState.put(start.lights(), start);
        setButtonPushes(start, lightsToState);
        return start;
    }

    @Override
    public String toString() {
        return lightsTarget + " " + buttons.stream().map(Button::toString).collect(Collectors.joining(" "));
    }

    private void setButtonPushes(LightsState state, Map<IndicatorLights, LightsState> lightsToState) {
        List<LightsState> newStates = new ArrayList<>(buttons.size());
        for (Button button : buttons) {
            IndicatorLights after = button.press(state.lights());
            LightsState afterState = lightsToState.computeIfAbsent(after, lights -> {
                LightsState newState = initState(lights);
                newStates.add(newState);
                return newState;
            });
            state.buttonPushes().add(afterState);
        }
        for (LightsState newState : newStates) {
            setButtonPushes(newState, lightsToState);
        }
    }

    private LightsState initState(IndicatorLights lights) {
        return new LightsState(lights, new ArrayList<>(buttons.size()));
    }

}
