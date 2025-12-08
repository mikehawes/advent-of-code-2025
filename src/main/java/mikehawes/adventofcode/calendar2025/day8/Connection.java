package mikehawes.adventofcode.calendar2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public record Connection(Point from, Point to, double distance) {

    public static List<Connection> findShortest(List<Point> points) {
        PriorityQueue<Connection> connections = new PriorityQueue<>(
                Comparator.comparing(Connection::distance));
        for (int i = 0; i < points.size(); i++) {
            Point from = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point to = points.get(j);
                double distance = from.findDistanceTo(to);
                connections.add(new Connection(from, to, distance));
            }
        }
        List<Connection> order = new ArrayList<>(connections.size());
        for (Connection connection = connections.poll(); connection != null; connection = connections.poll()) {
            order.add(connection);
        }
        return order;
    }
}
