package mikehawes.adventofcode.calendar2025.day4;

import mikehawes.adventofcode.calendar2025.grid.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day4.txt"));
        IO.println("Removeable paper rolls: " + countRemoveableRolls(input));
    }

    public static long countRemoveableRolls(String input) {
        Room room = Room.from(input);
        Set<Position> removeable = room.removableRollPositions().collect(Collectors.toSet());
        long removedRolls = 0;
        while (!removeable.isEmpty()) {
            room = room.removeRolls(removeable);
            removedRolls += removeable.size();
            removeable = room.removableRollPositions().collect(Collectors.toSet());
        }
        return removedRolls;
    }
}
