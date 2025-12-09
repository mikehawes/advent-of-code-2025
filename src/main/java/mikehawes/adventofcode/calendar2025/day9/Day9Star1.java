package mikehawes.adventofcode.calendar2025.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day9.txt"));
        IO.println("Largest rectangle area: " + largestRectangleArea(input));
    }

    public static long largestRectangleArea(String input) {
        List<Tile> tiles = Tile.listFrom(input);
        long maxSize = 0;
        for (int i = 0; i < tiles.size(); i++) {
            Tile from = tiles.get(i);
            for (int j = i + 1; j < tiles.size(); j++) {
                Tile to = tiles.get(j);
                long size = from.rectangleAreaWith(to);
                if (size > maxSize) {
                    IO.println("Found new maximum from " + from + " to " + to + ": " + size);
                    maxSize = size;
                }
            }
        }
        return maxSize;
    }
}
