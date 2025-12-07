package mikehawes.adventofcode.calendar2025.grid;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid {

    private final List<List<String>> rows;

    private Grid(List<List<String>> rows) {
        this.rows = rows;
    }

    public static Grid from(String input) {
        return new Grid(input.lines()
                .map(line -> List.of(line.split("")))
                .toList());
    }

    public Stream<Position> positions() {
        return IntStream.range(0, height()).boxed()
                .flatMap(y -> IntStream.range(0, width())
                        .mapToObj(x -> new Position(x, y)));
    }

    public String cell(Position position) {
        return rows.get(position.y()).get(position.x());
    }

    public boolean contains(Position position) {
        int x = position.x();
        int y = position.y();
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    public int width() {
        return rows.getFirst().size();
    }

    public int height() {
        return rows.size();
    }
}
