package mikehawes.adventofcode.calendar2025.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day9.txt"));
        List<Tile> tiles = Tile.listFrom(input);
        Floor floor = Floor.from(tiles);
        IO.println(floor.print());
    }

}
