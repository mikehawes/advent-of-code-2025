package mikehawes.adventofcode.calendar2025.day8;

import java.util.*;

public record Circuit(Set<Point> boxes, List<Connection> connections) {

    public static Map<Point, Circuit> indexCircuits(List<Point> boxes, List<Connection> connections) {
        Map<Point, Circuit> circuits = new HashMap<>();
        for (Connection connection : connections) {
            Circuit fromCircuit = circuits.get(connection.from());
            Circuit toCircuit = circuits.get(connection.to());
            Circuit circuit;
            if (fromCircuit != null) {
                circuit = fromCircuit;
            } else if (toCircuit != null) {
                circuit = toCircuit;
            } else {
                circuit = create();
            }
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
        Circuit circuit = create();
        circuit.boxes.add(box);
        return circuit;
    }

    public static Circuit create() {
        return new Circuit(new HashSet<>(), new ArrayList<>());
    }

    public Circuit add(Connection connection) {
        boxes.add(connection.from());
        boxes.add(connection.to());
        connections.add(connection);
        return this;
    }
}
