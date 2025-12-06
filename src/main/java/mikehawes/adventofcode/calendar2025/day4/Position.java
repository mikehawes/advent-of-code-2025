package mikehawes.adventofcode.calendar2025.day4;

import java.util.stream.Stream;

public record Position(int x, int y) {

    public Stream<Position> adjacentPositions() {
        return Stream.of(
                relative(-1, -1),
                relative(-1, 0),
                relative(-1, 1),
                relative(0, -1),
                relative(0, 1),
                relative(1, -1),
                relative(1, 0),
                relative(1, 1));
    }

    public boolean inGrid(Grid grid) {
        return x >= 0 && x < grid.width() && y >= 0 && y < grid.height();
    }

    public Position relative(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }
}
