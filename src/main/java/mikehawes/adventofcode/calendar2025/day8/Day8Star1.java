package mikehawes.adventofcode.calendar2025.day8;

import java.util.List;
import java.util.Map;

public class Day8Star1 {
    public static long joinBoxesMultiplyLargestCircuitLengths(String input, int joins, int multiplyCircuits) {
        List<Point> boxes = Point.listFrom(input);
        List<Connection> connections = Connection.findShortest(joins, boxes);
        Map<Point, Circuit> circuits = Circuit.indexCircuits(boxes, connections);
        return 0;
    }
}
