package mikehawes.adventofcode.calendar2025.day6;

import java.util.List;
import java.util.stream.LongStream;

public record Problem(List<Long> numbers, Operation operation) {

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
