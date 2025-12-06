package mikehawes.adventofcode.calendar2025.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day6Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day6.txt"));
        IO.println("Sum of results: " + sumResults(input));
    }

    public static long sumResults(String input) {
        List<String> lines = input.lines().toList();
        List<List<Long>> data = lines.subList(0, lines.size() - 1).stream()
                .map(line -> Arrays.stream(line.trim().split("\\s+"))
                        .map(Long::parseLong)
                        .toList())
                .toList();
        String[] opStrings = lines.getLast().trim().split("\\s+");
        List<Operation> operations = Arrays.stream(opStrings)
                .map(Operation::from)
                .toList();
        return IntStream.range(0, data.getFirst().size())
                .mapToObj(i -> Problem.from(i, data, operations.get(i)))
                .mapToLong(Problem::calculate)
                .sum();
    }
}
