package mikehawes.adventofcode.calendar2025.day6;

public enum Operation {
    Add, Multiply;

    public static Operation from(String string) {
        return switch (string) {
            case "*" -> Multiply;
            case "+" -> Add;
            default -> throw new IllegalArgumentException("Unrecognised operation: " + string);
        };
    }
}
