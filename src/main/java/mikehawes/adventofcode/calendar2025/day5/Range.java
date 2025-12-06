package mikehawes.adventofcode.calendar2025.day5;

public record Range(long start, long end) {

    public static Range from(String string) {
        String[] parts = string.split("-");
        return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    public boolean contains(long id) {
        return id >= start && id <= end;
    }
}
