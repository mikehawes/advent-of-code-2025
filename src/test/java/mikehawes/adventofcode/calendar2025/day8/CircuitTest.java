package mikehawes.adventofcode.calendar2025.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CircuitTest {

    @Test
    void should_find_unconnected_boxes() {
        Point box0 = new Point(0, 0, 0);
        Point box1 = new Point(1, 0, 0);
        Point box2 = new Point(2, 0, 0);
        assertThat(Circuit.indexCircuits(
                List.of(box0, box1, box2),
                List.of()))
                .isEqualTo(Map.of(
                        box0, new Circuit(Set.of(box0), List.of()),
                        box1, new Circuit(Set.of(box1), List.of()),
                        box2, new Circuit(Set.of(box2), List.of())
                ));
    }
}
