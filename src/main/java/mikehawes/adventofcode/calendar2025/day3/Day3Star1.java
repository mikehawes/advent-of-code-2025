package mikehawes.adventofcode.calendar2025.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day3Star1 {

    static void main() throws IOException {
        String document = Files.readString(Path.of("input/day3.txt"));
        IO.println("Sum of max joltages: " + sumMaxJoltageInBanks(document));
    }

    public static long sumMaxJoltageInBanks(String input) {
        return input.lines()
                .map(Bank::from)
                .mapToLong(Bank::findMaxJoltageFromTwoBatteries)
                .sum();
    }
}
