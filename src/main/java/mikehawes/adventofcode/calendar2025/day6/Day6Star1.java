package mikehawes.adventofcode.calendar2025.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

    private enum Operation {
        Add, Multiply;

        public static Operation from(String string) {
            return switch (string) {
                case "*" -> Multiply;
                case "+" -> Add;
                default -> throw new IllegalArgumentException("Unrecognised operation: "+string);
            };
        }
    }

    private record Problem(List<Long> numbers, Operation operation) {

        public static Problem from(int index, List<List<Long>> data, Operation operation) {
            List<Long> numbers = data.stream()
                    .map(line -> line.get(index))
                    .toList();
            return new Problem(numbers, operation);
        }

        public long calculate() {
            LongStream stream = numbers.stream().mapToLong(Long::longValue);
            long result = switch (operation) {
                case Multiply -> stream.reduce(1, (acc, number) -> acc * number);
                case Add -> stream.reduce(0, Long::sum);
            };
            IO.println(this + " = " + result);
            return result;
        }
    }
}
