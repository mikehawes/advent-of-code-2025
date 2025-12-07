package mikehawes.adventofcode.calendar2025.day7;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

import java.util.HashMap;
import java.util.Map;

public record Splitter(Position position, Splitter left, Splitter right, long paths) {

    public static Splitter read(Grid grid) {
        IO.println("Reading splitters...");
        return read(grid, Tachyon.findStart(grid), new HashMap<>());
    }

    private static Splitter read(Grid grid, Position position, Map<Position, Splitter> splitters) {
        while (!"^".equals(grid.cell(position))) {
            position = position.below();
            if (!grid.contains(position)) {
                return null;
            }
        }
        Splitter existingSplitter = splitters.get(position);
        if (existingSplitter != null) {
            return existingSplitter;
        }
        Splitter left = read(grid, position.left(), splitters);
        Splitter right = read(grid, position.right(), splitters);
        Splitter newSplitter = new Splitter(position, left, right, paths(left) + paths(right));
        splitters.put(position, newSplitter);
        return newSplitter;
    }

    private static long paths(Splitter splitter) {
        if(splitter == null) {
            return 1;
        } else {
            return splitter.paths();
        }
    }
}
