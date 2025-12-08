package mikehawes.adventofcode.calendar2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Connection(Point from, Point to, double distance) {

    public static Connection create(Point from, Point to) {
        return new Connection(from, to, from.findDistanceTo(to));
    }

    public static List<Connection> findShortest(int limit, List<Point> boxes) {
        PriorityQueue<Connection> connections = new PriorityQueue<>(
                Comparator.comparing(Connection::distance).reversed());
        for (Connection connection : (Iterable<Connection>) () -> streamAll(boxes).iterator()) {
            connections.add(connection);
            if(connections.size() > limit) {
                connections.poll();
            }
        }
        List<Connection> order = new ArrayList<>(connections.size());
        for (Connection connection = connections.poll(); connection != null; connection = connections.poll()) {
            order.add(connection);
        }
        return order.reversed();
    }

    public static Stream<Connection> streamAll(List<Point> boxes) {
        return IntStream.range(0, boxes.size()).boxed()
                .flatMap(i -> {
                    Point from = boxes.get(i);
                    return IntStream.range(i + 1, boxes.size())
                            .mapToObj(j -> {
                                Point to = boxes.get(j);
                                return create(from, to);
                            });
                });
    }
}
