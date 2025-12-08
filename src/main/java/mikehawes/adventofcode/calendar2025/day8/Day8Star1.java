package mikehawes.adventofcode.calendar2025.day8;

import java.util.List;

public class Day8Star1 {
    public static long joinBoxesMultiplyLargestCircuitLengths(String input, int joins, int multiplyCircuits) {
        List<Point> boxes = Point.listFrom(input);
        List<Connection> connections = Connection.findShortest(joins, boxes);
        List<Circuit> circuits = Circuit.listFrom(boxes, connections);
        return 0;
    }
}
