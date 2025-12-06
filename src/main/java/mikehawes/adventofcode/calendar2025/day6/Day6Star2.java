package mikehawes.adventofcode.calendar2025.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day6.txt"));
        IO.println("Sum of results: " + sumResults(input));
    }

    public static long sumResults(String input) {
        List<String> lines = input.lines().toList();
        List<String> rows = lines.subList(0, lines.size() - 1);
        List<String> columns = linesToColumns(rows);
        List<Operation> operations = Operation.listFromLine(lines.getLast());
        List<Problem> problems = Problem.listFromColumns(columns, operations);
        return problems.stream()
                .mapToLong(Problem::calculate)
                .sum();
    }

    private static List<String> linesToColumns(List<String> lines) {
        int width = lines.getFirst().length();
        return IntStream.range(0, width)
                .mapToObj(i -> getColumn(lines, i))
                .toList();
    }

    private static String getColumn(List<String> lines, int index) {
        return lines.stream()
                .map(line -> line.substring(index, index + 1))
                .collect(Collectors.joining());
    }
}
