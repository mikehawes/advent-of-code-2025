package mikehawes.adventofcode.calendar2025.day8;

import java.util.*;

import static java.util.function.Predicate.not;

public record Circuit(Set<Point> boxes, List<Connection> connections) {

    public static List<Circuit> listFrom(List<Point> boxes, List<Connection> connections) {
        CircuitIndex index = CircuitIndex.createEmpty();
        connections.forEach(index::add);
        boxes.forEach(index::add);
        return index.circuits().stream().filter(not(Circuit::isEmpty)).toList();
    }

    public static Circuit singleBox(Point box) {
        Circuit circuit = createEmpty();
        circuit.boxes.add(box);
        return circuit;
    }

    public static Circuit createEmpty() {
        return new Circuit(new HashSet<>(), new ArrayList<>());
    }

    public Circuit add(Connection connection) {
        boxes.add(connection.from());
        boxes.add(connection.to());
        connections.add(connection);
        return this;
    }

    public void clear() {
        boxes.clear();
        connections.clear();
    }

    public boolean isEmpty() {
        return boxes.isEmpty();
    }

    public int size() {
        return boxes.size();
    }

    public void consume(Circuit other) {
        other.connections.forEach(this::add);
        other.clear();
    }

}
