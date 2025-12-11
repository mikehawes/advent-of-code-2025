package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record IndicatorLights(List<Boolean> isOn) {

    public static IndicatorLights from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Boolean> isOn = noBrackets.chars().mapToObj(i -> '#' == i).toList();
        return new IndicatorLights(isOn);
    }

    public static IndicatorLights allOff(Machine machine) {
        return new IndicatorLights(IntStream.range(0, machine.lightsTarget().numberOfLights()).mapToObj(_ -> false).toList());
    }

    public int numberOfLights() {
        return isOn.size();
    }

    @Override
    public String toString() {
        return "[" + isOn.stream().map(on -> on ? "#" : ".").collect(Collectors.joining()) + "]";
    }
}
