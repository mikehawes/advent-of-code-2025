package mikehawes.adventofcode.calendar2025.day9;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.MutableGrid;
import mikehawes.adventofcode.calendar2025.grid.Position;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Floor(List<Long> xValues, List<Long> yValues, List<Position> tiles) {

    public static Floor from(List<Tile> tiles) {
        List<Long> xValues = tiles.stream().map(Tile::x).distinct().sorted().toList();
        List<Long> yValues = tiles.stream().map(Tile::y).distinct().sorted().toList();
        Map<Long, Integer> xMapping = IntStream.range(0, xValues.size()).boxed()
                .collect(Collectors.toMap(xValues::get, Function.identity()));
        Map<Long, Integer> yMapping = IntStream.range(0, yValues.size()).boxed()
                .collect(Collectors.toMap(yValues::get, Function.identity()));
        List<Position> mappedTiles = tiles.stream()
                .map(tile -> new Position(xMapping.get(tile.x()), yMapping.get(tile.y())))
                .toList();
        return new Floor(xValues, yValues, mappedTiles);
    }

    public MutableGrid buildGrid() {
        MutableGrid grid = MutableGrid.create(".", xValues.size(), yValues.size());
        for (int i = 0; i < tiles.size(); i++) {
            renderTile(i, grid);
            renderLine(i, grid);
        }
        Grid view = grid.view();
        List<String> lines = view.rows().stream()
                .map(row -> String.join("", row))
                .toList();
        view.positions().forEach(position -> {
            if (view.cell(position).equals(".") && isInternal(position, lines)) {
                grid.set(position, "X");
            }
        });
        return grid;
    }

    private static boolean isInternal(Position tile, List<String> lines) {
        String line = lines.get(tile.y()).substring(tile.x());
        return countBoundaryCrossings(line) % 2 == 1;
    }

    private static final Pattern LINE_CROSS = Pattern.compile("║|╔═*╝|╚═*╗");

    private static int countBoundaryCrossings(String line) {
        Matcher matcher = LINE_CROSS.matcher(line);
        int crossings = 0;
        while (matcher.find()) {
            crossings++;
        }
        return crossings;
    }

    private void renderTile(int index, MutableGrid grid) {
        Position before = tileBefore(index);
        Position tile = tiles.get(index);
        Position after = tileAfter(index);
        if (before.x() == tile.x()) {
            if (before.y() < tile.y()) {
                if (after.x() < before.x()) {
                    grid.set(tile, "╝");
                } else {
                    grid.set(tile, "╚");
                }
            } else {
                if (after.x() < before.x()) {
                    grid.set(tile, "╗");
                } else {
                    grid.set(tile, "╔");
                }
            }
        } else {
            if (before.x() < tile.x()) {
                if (after.y() < before.y()) {
                    grid.set(tile, "╝");
                } else {
                    grid.set(tile, "╗");
                }
            } else {
                if (after.y() < before.y()) {
                    grid.set(tile, "╚");
                } else {
                    grid.set(tile, "╔");
                }
            }
        }
    }

    private Position tileBefore(int index) {
        if (index == 0) {
            return tiles.getLast();
        } else {
            return tiles.get(index - 1);
        }
    }

    private Position tileAfter(int index) {
        if (index == tiles.size() - 1) {
            return tiles.getFirst();
        } else {
            return tiles.get(index + 1);
        }
    }

    private void renderLine(int index, MutableGrid grid) {
        Position from = tiles.get(index);
        Position to = tileAfter(index);
        if (from.x() == to.x()) {
            if (from.y() > to.y()) {
                Position temp = from;
                from = to;
                to = temp;
            }
            for (int y = from.y() + 1; y < to.y(); y++) {
                grid.set(new Position(from.x(), y), "║");
            }
        } else {
            if (from.x() > to.x()) {
                Position temp = from;
                from = to;
                to = temp;
            }
            for (int x = from.x() + 1; x < to.x(); x++) {
                grid.set(new Position(x, from.y()), "═");
            }
        }
    }
}
