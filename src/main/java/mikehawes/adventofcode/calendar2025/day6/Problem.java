package mikehawes.adventofcode.calendar2025.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public record Problem(List<Long> numbers, Operation operation) {

    public static Problem from(int index, List<List<Long>> data, Operation operation) {
        List<Long> numbers = data.stream()
                .map(line -> line.get(index))
                .toList();
        return new Problem(numbers, operation);
    }

    public static Problem from(List<String> parts, Operation operation) {
        int maxLength = parts.stream().mapToInt(String::length).max().orElseThrow();
        List<Long> numbers = new ArrayList<>(maxLength);
        for (int i = maxLength - 1; i >= 0; i--) {
            StringBuilder number = new StringBuilder();
            for (String part : parts) {
                if (part.length() > i) {
                    number.append(part.charAt(i));
                }
            }
            numbers.add(Long.parseLong(number.toString()));
        }
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
