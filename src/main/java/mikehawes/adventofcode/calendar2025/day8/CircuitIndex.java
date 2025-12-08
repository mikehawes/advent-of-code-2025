package mikehawes.adventofcode.calendar2025.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record CircuitIndex(Map<Point, Circuit> boxToCircuit, List<Circuit> circuits) {

    public static CircuitIndex createEmpty() {
        return new CircuitIndex(new HashMap<>(), new ArrayList<>());
    }

    public void add(Connection connection) {
        Circuit fromCircuit = boxToCircuit.get(connection.from());
        Circuit toCircuit = boxToCircuit.get(connection.to());
        if (fromCircuit != null) {
            if (toCircuit != null) {
                Circuit circuit = add(Circuit.createEmpty());
                circuit.consume(fromCircuit);
                circuit.consume(toCircuit);
                addAndApplyMerge(connection, circuit);
            } else {
                addToCircuit(connection, fromCircuit);
            }
        } else if (toCircuit != null) {
            addToCircuit(connection, toCircuit);
        } else {
            addToCircuit(connection, add(Circuit.createEmpty()));
        }
    }

    public void add(Point box) {
        if (!boxToCircuit.containsKey(box)) {
            boxToCircuit.put(box, add(Circuit.singleBox(box)));
        }
    }

    private void addAndApplyMerge(Connection connection, Circuit circuit) {
        circuit.boxes().forEach(box -> boxToCircuit.put(box, circuit));
        addToCircuit(connection, circuit);
    }

    private void addToCircuit(Connection connection, Circuit circuit) {
        circuit.add(connection);
        boxToCircuit.put(connection.from(), circuit);
        boxToCircuit.put(connection.to(), circuit);
    }

    private Circuit add(Circuit circuit) {
        circuits.add(circuit);
        return circuit;
    }
}
