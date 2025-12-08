package mikehawes.adventofcode.calendar2025.day8;

import java.util.*;

public record Circuit(Set<Point> boxes, List<Connection> connections) {

    public static List<Circuit> listFrom(List<Point> boxes, List<Connection> connections) {
        Map<Point, Circuit> index = new HashMap<>();
        List<Circuit> circuits = new ArrayList<>();
        for (Connection connection : connections) {
            Circuit fromCircuit = index.get(connection.from());
            Circuit toCircuit = index.get(connection.to());
            Circuit circuit;
            if (fromCircuit != null) {
                circuit = fromCircuit;
            } else if (toCircuit != null) {
                circuit = toCircuit;
            } else {
                circuit = create();
                circuits.add(circuit);
            }
            circuit.add(connection);
            index.put(connection.from(), circuit);
            index.put(connection.to(), circuit);
        }
        for (Point box : boxes) {
            if (!index.containsKey(box)) {
                circuits.add(singleBox(box));
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
