package mikehawes.adventofcode.calendar2025.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day9.txt"));
        IO.println("Largest rectangle area: " + largestRectangleArea(input));
    }

    public static long largestRectangleArea(String input) {
        List<Tile> tiles = Tile.listFrom(input);
        Floor floor = Floor.from(tiles);
        FloorGrid grid = floor.buildGrid();
        IO.println(grid.print());
        return grid.findLargestRectangleArea();
    }

}
