package mikehawes.adventofcode.calendar2025.day6;

import java.util.Arrays;
import java.util.List;

public enum Operation {
    Add, Multiply;

    public static Operation from(String string) {
        return switch (string) {
            case "*" -> Multiply;
            case "+" -> Add;
            default -> throw new IllegalArgumentException("Unrecognised operation: " + string);
        };
    }

    public static List<Operation> listFromLine(String line) {
        String[] opStrings = line.trim().split("\\s+");
        return Arrays.stream(opStrings)
                .map(Operation::from)
                .toList();
    }
}
