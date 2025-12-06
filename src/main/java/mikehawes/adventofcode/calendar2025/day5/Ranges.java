package mikehawes.adventofcode.calendar2025.day5;

import java.util.List;

public record Ranges(List<Range> list) {

    public static Ranges from(String string) {
        return new Ranges(string.lines().map(Range::from).toList());
    }

    public boolean isFresh(long id) {
        return list.stream().anyMatch(r -> r.contains(id));
    }
}
