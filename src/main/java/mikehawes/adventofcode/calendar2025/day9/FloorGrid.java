package mikehawes.adventofcode.calendar2025.day9;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record FloorGrid(Floor floor, Grid grid) {

    private static final Pattern TOP_LINE = Pattern.compile("(\\.|X|═|═╝|╚═|╚═*╝)*");
    private static final Pattern BOTTOM_LINE = Pattern.compile("(\\.|X|═|═╗|╔═|╔═*╗)*");
    private static final Pattern LEFT_LINE = Pattern.compile("(\\.|X|║|║╝|╗║|╗║*╝)*");
    private static final Pattern RIGHT_LINE = Pattern.compile("(\\.|X|║|║╚|╔║|╔║*╚)*");

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
                String topLine = String.join("", grid.rows().get(minY).subList(minX + 1, maxX));
                if (!TOP_LINE.matcher(topLine).matches()) {
                    continue;
                }
                String bottomLine = String.join("", grid.rows().get(maxY).subList(minX + 1, maxX));
                if (!BOTTOM_LINE.matcher(bottomLine).matches()) {
                    continue;
                }
                String leftLine = IntStream.range(minY + 1, maxY)
                        .mapToObj(y -> grid.rows().get(y).get(minX))
                        .collect(Collectors.joining());
                if (!LEFT_LINE.matcher(leftLine).matches()) {
                    continue;
                }
                String rightLine = IntStream.range(minY + 1, maxY)
                        .mapToObj(y -> grid.rows().get(y).get(maxX))
                        .collect(Collectors.joining());
                if (!RIGHT_LINE.matcher(rightLine).matches()) {
                    continue;
                }
                maxArea = area;
            }
        }
        return maxArea;
    }

    public String print() {
        return grid.print();
    }
}
