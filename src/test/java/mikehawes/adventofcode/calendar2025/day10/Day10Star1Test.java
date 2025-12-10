package mikehawes.adventofcode.calendar2025.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10Star1Test {

    @Test
    void should_read_machine() {
        String input = "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}";
        assertThat(Machine.from(input))
                .isEqualTo(new Machine(
                        new IndicatorLights(List.of(false, true, true, false)),
                        List.of(new Button(List.of(3)),
                                new Button(List.of(1, 3)),
                                new Button(List.of(2)),
                                new Button(List.of(2, 3)),
                                new Button(List.of(0, 2)),
                                new Button(List.of(0, 1)))));
    }
}
