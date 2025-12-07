package mikehawes.adventofcode.calendar2025.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day7Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day7.txt"));
        IO.println("Number of tachyon paths: " + countTachyonPaths(input));
    }

    public static long countTachyonPaths(String input) {
        Grid grid = Grid.from(input);
        Splitter splits = Splitter.read(grid);
        return splits.paths();
    }
}
