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
        return Grid.from(input).removableRollPositions().count();
    }
}
