package mikehawes.adventofcode.calendar2025.grid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record MutableGrid(List<List<String>> rows) {

    public static MutableGrid create(String cell, int width, int height) {
        return new MutableGrid(IntStream.range(0, height)
                .mapToObj(_ -> IntStream.range(0, width)
                        .mapToObj(_ -> cell)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));
    }

    public void set(Position position, String value) {
        rows.get(position.y()).set(position.x(), value);
    }

    public Grid view() {
        return new Grid(rows);
    }
}
