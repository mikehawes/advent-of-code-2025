package mikehawes.adventofcode.calendar2025.day9;

import java.util.List;

public record Tile(long x, long y) {

    public static Tile from(String string) {
        String[] parts = string.split(",");
        return new Tile(
                Long.parseLong(parts[0]),
                Long.parseLong(parts[1]));
    }

    public static List<Tile> listFrom(String string) {
        return string.lines().map(Tile::from).toList();
    }

    public long rectangleAreaWith(Tile other) {
        long xDiff = Math.abs(x - other.x) + 1;
        long yDiff = Math.abs(y - other.y) + 1;
        return xDiff * yDiff;
    }
}
