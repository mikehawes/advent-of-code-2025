package mikehawes.adventofcode.calendar2025.day8;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CircuitIndex {

    private final Map<Point, Long> boxToCircuitId = new HashMap<>();
    private final Map<Long, Circuit> idToCircuit = new HashMap<>();
    private long nextId = 0;

    public static CircuitIndex createEmpty() {
        return new CircuitIndex();
    }

    public Collection<Circuit> circuits() {
        return idToCircuit.values();
    }

    public void add(Connection connection) {
        Long fromId = boxToCircuitId.get(connection.from());
        Long toId = boxToCircuitId.get(connection.to());
        if (fromId != null) {
            if (toId != null) {
                Circuit circuit = Circuit.createEmpty();
                long id = add(circuit);
                merge(fromId, circuit, id);
                merge(toId, circuit, id);
                addToCircuit(connection, circuit, id);
            } else {
                addToCircuit(connection, idToCircuit.get(fromId), fromId);
            }
        } else if (toId != null) {
            addToCircuit(connection, idToCircuit.get(toId), toId);
        } else {
            Circuit circuit = Circuit.createEmpty();
            long id = add(circuit);
            addToCircuit(connection, circuit, id);
        }
    }

    private void merge(long oldId, Circuit newCircuit, long newId) {
        Circuit oldCircuit = idToCircuit.remove(oldId);
        if (oldCircuit == null) {
            // From and to circuit are the same
            return;
        }
        oldCircuit.connections().forEach(newCircuit::add);
        oldCircuit.boxes().forEach(box -> boxToCircuitId.put(box, newId));
    }

    public void add(Point box) {
        if (!boxToCircuitId.containsKey(box)) {
            boxToCircuitId.put(box, add(Circuit.singleBox(box)));
        }
    }

    private void addToCircuit(Connection connection, Circuit circuit, long id) {
        circuit.add(connection);
        boxToCircuitId.put(connection.from(), id);
        boxToCircuitId.put(connection.to(), id);
    }

    private long add(Circuit circuit) {
        long id = nextId;
        idToCircuit.put(id, circuit);
        nextId++;
        return id;
    }
}
