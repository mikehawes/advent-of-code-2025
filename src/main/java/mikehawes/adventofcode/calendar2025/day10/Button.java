package mikehawes.adventofcode.calendar2025.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Button(List<Integer> lightIndexes) {

    public static Button from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Integer> lightIndexes = Stream.of(noBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
        return new Button(lightIndexes);
    }

    public IndicatorLights press(IndicatorLights lights) {
        List<Boolean> isOn = new ArrayList<>(lights.isOn());
        for (int index : lightIndexes) {
            isOn.set(index, !isOn.get(index));
        }
        return new IndicatorLights(isOn);
    }

    @Override
    public String toString() {
        return "(" + lightIndexes.stream().map(i -> "" + i).collect(Collectors.joining(",")) + ")";
    }
}
