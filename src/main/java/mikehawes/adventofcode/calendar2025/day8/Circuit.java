package mikehawes.adventofcode.calendar2025.day8;

import java.util.*;

public record Circuit(Set<Point> boxes, List<Connection> connections) {

    public static Map<Point, Circuit> indexCircuits(List<Point> boxes, List<Connection> connections) {
        Map<Point, Circuit> circuits = new HashMap<>();
        for (Connection connection : connections) {
            Circuit circuit = empty();
            circuit.add(connection);
            circuits.put(connection.from(), circuit);
            circuits.put(connection.to(), circuit);
        }
        for (Point box : boxes) {
            if (!circuits.containsKey(box)) {
                circuits.put(box, singleBox(box));
            }
        }
        return circuits;
    }

    public static Circuit singleBox(Point box) {
        Circuit circuit = empty();
        circuit.boxes.add(box);
        return circuit;
    }

    private static Circuit empty() {
        return new Circuit(new HashSet<>(), new ArrayList<>());
    }

    public void add(Connection connection) {
        boxes.add(connection.from());
        boxes.add(connection.to());
        connections.add(connection);
    }
}
