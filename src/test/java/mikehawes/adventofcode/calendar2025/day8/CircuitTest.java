package mikehawes.adventofcode.calendar2025.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CircuitTest {

    @Test
    void should_find_unconnected_boxes() {
        Point box0 = new Point(0, 0, 0);
        Point box1 = new Point(1, 0, 0);
        Point box2 = new Point(2, 0, 0);
        assertThat(Circuit.listFrom(
                List.of(box0, box1, box2),
                List.of()))
                .containsExactly(
                        circuit(box0),
                        circuit(box1),
                        circuit(box2));
    }

    @Test
    void should_find_single_connection() {
        Point box0 = new Point(0, 0, 0);
        Point box1 = new Point(1, 0, 0);
        assertThat(Circuit.listFrom(
                List.of(box0, box1),
                List.of(Connection.create(box0, box1))))
                .containsExactly(
                        circuit(Connection.create(box0, box1)));
    }

    @Test
    void should_find_single_circuit_matching_last_to_first() {
        Point box0 = new Point(0, 0, 0);
        Point box1 = new Point(1, 0, 0);
        Point box2 = new Point(2, 0, 0);
        assertThat(Circuit.listFrom(
                List.of(box0, box1),
                List.of(Connection.create(box0, box1),
                        Connection.create(box1, box2))))
                .containsExactly(circuit(
                        Connection.create(box0, box1),
                        Connection.create(box1, box2)));
    }

    @Test
    void should_find_single_circuit_matching_last_to_last() {
        Point box0 = new Point(0, 0, 0);
        Point box1 = new Point(1, 0, 0);
        Point box2 = new Point(2, 0, 0);
        assertThat(Circuit.listFrom(
                List.of(box0, box1),
                List.of(Connection.create(box0, box1),
                        Connection.create(box2, box1))))
                .containsExactly(circuit(
                        Connection.create(box0, box1),
                        Connection.create(box2, box1)));
    }

    private static Circuit circuit(Point box) {
        return new Circuit(Set.of(box), List.of());
    }

    private static Circuit circuit(Connection... connections) {
        List<Connection> list = List.of(connections);
        Set<Point> boxes = list.stream().flatMap(c -> Stream.of(c.from(), c.to())).collect(Collectors.toSet());
        return new Circuit(boxes, list);
    }
}
