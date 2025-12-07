package mikehawes.adventofcode.calendar2025.day4;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

public record Room(Grid grid) {

    public static Room from(String input) {
        return new Room(Grid.from(input));
    }

    public Room removeRolls(Set<Position> positions) {
        return new Room(new Grid(IntStream.range(0, grid.height()).boxed()
                .map(y -> IntStream.range(0, grid.width())
                        .mapToObj(x -> new Position(x, y))
                        .map(position -> {
                            if(positions.contains(position)) {
                                return ".";
                            } else {
                                return grid.cell(position);
                            }
                        })
                        .toList())
                .toList()));
    }

    public Stream<Position> removableRollPositions() {
        return grid.positions().filter(this::isRemovableRoll);
    }

    public boolean isRemovableRoll(Position position) {
        String cell = grid.cell(position);
        if (!"@".equals(cell)) {
            return false;
        }
        long adjacentRolls = position.adjacentWithDiagonals().stream()
                .filter(grid::contains)
                .map(grid::cell)
                .filter("@"::equals)
                .count();
        return adjacentRolls < 4;
    }
}
