package mikehawes.adventofcode.calendar2025.day8;

import java.util.List;

public record Point(int x, int y, int z) {

    public static Point from(String string) {
        String[] parts = string.split(",");
        return new Point(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
    }

    public static List<Point> listFrom(String string) {
        return string.lines().map(Point::from).toList();
    }

    public double findDistanceTo(Point other) {
        long diffX = other.x - x;
        long diffY = other.y - y;
        long diffZ = other.z - z;
        return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
    }
}
