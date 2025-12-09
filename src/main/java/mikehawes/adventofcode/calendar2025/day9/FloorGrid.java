package mikehawes.adventofcode.calendar2025.day9;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record FloorGrid(Floor floor, Grid grid) {

    private static final Pattern TOP_LINE = Pattern.compile("");

    public long findLargestRectangleArea() {
        long maxArea = 0;
        for (int i = 0; i < floor.tiles().size(); i++) {
            Position from = floor.tiles().get(i);
            for (int j = i + 1; j < floor.tiles().size(); j++) {
                Position to = floor.tiles().get(j);
                if (from.x() == to.x() || from.y() == to.y()) {
                    continue;
                }
                int minX = Math.min(from.x(), to.x());
                int maxX = Math.max(from.x(), to.x());
                int minY = Math.min(from.y(), to.y());
                int maxY = Math.max(from.y(), to.y());
                long area = (floor.mapX(maxX) - floor.mapX(minX) + 1) * (floor.mapY(maxY) - floor.mapY(minY) + 1);
                if (area <= maxArea) {
                    continue;
                }
                String topLine = String.join("", grid.rows().get(minY).subList(minX, maxX + 1));
                String bottomLine = String.join("", grid.rows().get(maxY).subList(minX, maxX + 1));
                String leftLine = IntStream.rangeClosed(minY, maxY)
                        .mapToObj(y -> grid.rows().get(y).get(minX))
                        .collect(Collectors.joining());
                String rightLine = IntStream.rangeClosed(minY, maxY)
                        .mapToObj(y -> grid.rows().get(y).get(maxX))
                        .collect(Collectors.joining());
                maxArea = area;
            }
        }
        return maxArea;
    }

    public String print() {
        return grid.print();
    }
}
