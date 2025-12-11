package mikehawes.adventofcode.calendar2025.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Button(List<Integer> indexes) {

    public static Button from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Integer> lightIndexes = Stream.of(noBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
        return new Button(lightIndexes);
    }

    public IndicatorLights press(IndicatorLights lights) {
        List<Boolean> isOn = new ArrayList<>(lights.isOn());
        for (int index : indexes) {
            isOn.set(index, !isOn.get(index));
        }
        return new IndicatorLights(isOn);
    }

    public Joltages press(Joltages joltages) {
        List<Integer> levels = new ArrayList<>(joltages.levels());
        for (int index : indexes) {
            levels.set(index, levels.get(index) + 1);
        }
        return new Joltages(levels);
    }

    @Override
    public String toString() {
        return "(" + indexes.stream().map(i -> "" + i).collect(Collectors.joining(",")) + ")";
    }
}
