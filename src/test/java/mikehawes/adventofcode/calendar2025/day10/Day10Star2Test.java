package mikehawes.adventofcode.calendar2025.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10Star2Test {

    @Test
    void should_find_fewest_button_presses() {
        String input = """
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
                [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
                """;
        assertThat(Day10Star2.fewestButtonPresses(input))
                .isEqualTo(33);
    }
}
