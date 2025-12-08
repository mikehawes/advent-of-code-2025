package mikehawes.adventofcode.calendar2025.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Day8Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day8.txt"));
        IO.println("Result: " + joinBoxesMultiplyLargestCircuits(input, 1000, 3));
    }

    public static long joinBoxesMultiplyLargestCircuits(String input, int joins, int multiplyCircuits) {
        List<Point> boxes = Point.listFrom(input);
        List<Connection> connections = Connection.findShortest(joins, boxes);
        List<Circuit> circuits = Circuit.listFrom(boxes, connections);
        List<Integer> biggestSizes = findBiggestSizes(multiplyCircuits, circuits);
        return biggestSizes.stream()
                .mapToInt(i -> i)
                .reduce(1, (a, b) -> a * b);
    }

    private static List<Integer> findBiggestSizes(int limit, List<Circuit> circuits) {
        PriorityQueue<Integer> sizes = new PriorityQueue<>();
        for (Circuit circuit : circuits) {
            sizes.add(circuit.size());
            if(sizes.size() > limit) {
                sizes.poll();
            }
        }
        List<Integer> output = new ArrayList<>(sizes.size());
        for (Integer size = sizes.poll(); size != null; size = sizes.poll()) {
            output.add(size);
        }
        return output.reversed();
    }
}
