package mikehawes.adventofcode.calendar2025.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
}
