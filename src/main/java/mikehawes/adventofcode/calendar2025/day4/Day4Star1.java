package mikehawes.adventofcode.calendar2025.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Day4Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day4.txt"));
        IO.println("Reachable paper rolls: " + countReachableRolls(input));
    }

    public static long countReachableRolls(String input) {
        Grid grid = Grid.from(input);
        long reachableRolls = 0;
        for (int y=0; y<grid.height(); y++) {
            for (int x = 0; x < grid.width(); x++) {
                Position position = new Position(x, y);
                String cell = grid.cell(position);
                if ("@".equals(cell)) {
                    long adjacentRolls = position.adjacentPositions()
                            .filter(p -> p.inGrid(grid))
                            .map(grid::cell)
                            .filter("@"::equals)
                            .count();
                    if (adjacentRolls < 4) {
                        reachableRolls++;
                    }
                }
            }
        }
        return reachableRolls;
    }

    private record Grid(List<List<String>> rows) {

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

    private record Position(int x, int y) {

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
}
