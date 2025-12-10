package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;

public record IndicatorLights(List<Boolean> isOn) {

    public static IndicatorLights from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Boolean> isOn = noBrackets.chars().mapToObj(i -> '#' == i).toList();
        return new IndicatorLights(isOn);
    }
}
