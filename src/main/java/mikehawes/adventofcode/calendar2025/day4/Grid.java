package mikehawes.adventofcode.calendar2025.day4;

import java.util.List;

public record Grid(List<List<String>> rows) {

    public static Grid from(String input) {
        return new Grid(input.lines()
                .map(line -> List.of(line.split("")))
                .toList());
    }

    public String cell(Position position) {
        return rows.get(position.y()).get(position.x());
    }

    public int width() {
        return rows.getFirst().size();
    }

    public int height() {
        return rows.size();
    }
}
