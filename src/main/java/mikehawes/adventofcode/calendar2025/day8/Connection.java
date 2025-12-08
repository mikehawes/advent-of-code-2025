package mikehawes.adventofcode.calendar2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public record Connection(Point from, Point to, double distance) {

    public static Connection create(Point from, Point to) {
        return new Connection(from, to, from.findDistanceTo(to));
    }

    public static List<Connection> findShortest(int limit, List<Point> points) {
        PriorityQueue<Connection> connections = new PriorityQueue<>(
                Comparator.comparing(Connection::distance).reversed());
        for (int i = 0; i < points.size(); i++) {
            Point from = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point to = points.get(j);
                connections.add(create(from, to));
                if(connections.size() > limit) {
                    connections.poll();
                }
            }
        }
        List<Connection> order = new ArrayList<>(connections.size());
        for (Connection connection = connections.poll(); connection != null; connection = connections.poll()) {
            order.add(connection);
        }
        return order.reversed();
    }
}
