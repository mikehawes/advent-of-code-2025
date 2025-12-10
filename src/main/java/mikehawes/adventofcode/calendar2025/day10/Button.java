package mikehawes.adventofcode.calendar2025.day10;

import java.util.List;
import java.util.stream.Stream;

public record Button(List<Integer> lightIndexes) {

    public static Button from(String string) {
        String noBrackets = string.substring(1, string.length() - 1);
        List<Integer> lightIndexes = Stream.of(noBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
        return new Button(lightIndexes);
    }
}
