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

    public static List<Problem> listFromColumns(List<String> columns, List<Operation> operations) {
        List<Problem> result = new ArrayList<>();
        List<String> problem = new ArrayList<>();
        for(String column : columns) {
            if(column.isBlank()) {
                result.add(fromColumns(problem, operations.get(result.size())));
                problem = new ArrayList<>();
            } else {
                problem.add(column);
            }
        }
        result.add(fromColumns(problem, operations.get(result.size())));
        return result;
    }

    public static Problem fromColumns(List<String> columns, Operation operation) {
        List<Long> numbers = columns.reversed().stream()
                .map(number -> Long.parseLong(number.trim()))
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
