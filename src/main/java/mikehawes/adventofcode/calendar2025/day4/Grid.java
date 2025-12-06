package mikehawes.adventofcode.calendar2025.day4;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Grid(List<List<String>> rows) {

    public static Grid from(String input) {
        return new Grid(input.lines()
                .map(line -> List.of(line.split("")))
                .toList());
    }

    public Grid removeRolls(Set<Position> positions) {
        return new Grid(IntStream.range(0, height()).boxed()
                .map(y -> IntStream.range(0, width())
                        .mapToObj(x -> new Position(x, y))
                        .map(position -> {
                            if(positions.contains(position)) {
                                return ".";
                            } else {
                                return cell(position);
                            }
                        })
                        .toList())
                .toList());
    }

    public Stream<Position> removableRollPositions() {
        return positions().filter(this::isRemovableRoll);
    }

    public Stream<Position> positions() {
        return IntStream.range(0, height()).boxed()
                .flatMap(y -> IntStream.range(0, width())
                        .mapToObj(x -> new Position(x, y)));
    }

    public boolean isRemovableRoll(Position position) {
        String cell = cell(position);
        if (!"@".equals(cell)) {
            return false;
        }
        long adjacentRolls = position.adjacentPositions()
                .filter(p -> p.inGrid(this))
                .map(this::cell)
                .filter("@"::equals)
                .count();
        return adjacentRolls < 4;
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
