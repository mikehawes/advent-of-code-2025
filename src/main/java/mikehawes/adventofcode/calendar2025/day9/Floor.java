package mikehawes.adventofcode.calendar2025.day9;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Floor(List<Long> xValues, List<Long> yValues,
                    Map<Long, Integer> xMapping, Map<Long, Integer> yMapping,
                    Grid grid) {

    public static Floor from(List<Tile> tiles) {
        List<Long> xValues = tiles.stream().map(Tile::x).distinct().sorted().toList();
        List<Long> yValues = tiles.stream().map(Tile::y).distinct().sorted().toList();
        Map<Long, Integer> xMapping = IntStream.range(0, xValues.size()).boxed()
                .collect(Collectors.toMap(xValues::get, Function.identity()));
        Map<Long, Integer> yMapping = IntStream.range(0, yValues.size()).boxed()
                .collect(Collectors.toMap(yValues::get, Function.identity()));
        Set<Position> mappedTiles = tiles.stream()
                .map(tile -> new Position(xMapping.get(tile.x()), yMapping.get(tile.y())))
                .collect(Collectors.toSet());
        Grid grid = Grid.create(".", xMapping.size(), yMapping.size());
        grid = grid.mapEachPosition(position -> mappedTiles.contains(position) ? "#" : ".");
        return new Floor(xValues, yValues, xMapping, yMapping, grid);
    }

    public String print() {
        return grid.print();
    }
}
