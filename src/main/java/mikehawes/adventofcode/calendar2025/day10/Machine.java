package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;
import java.util.stream.Collectors;

public record Machine(IndicatorLights lightsTarget, List<Button> buttons, Joltages joltagesTarget) {

    public static Machine from(String input) {
        String[] parts = input.split(" ");
        IndicatorLights lights = IndicatorLights.from(parts[0]);
        List<Button> buttons = List.of(parts).subList(1, parts.length - 1).stream()
                .map(Button::from)
                .toList();
        Joltages joltages = Joltages.from(parts[parts.length - 1]);
        return new Machine(lights, buttons, joltages);
    }

    @Override
    public String toString() {
        return lightsTarget + " " + buttons.stream().map(Button::toString).collect(Collectors.joining(" "));
    }

}
