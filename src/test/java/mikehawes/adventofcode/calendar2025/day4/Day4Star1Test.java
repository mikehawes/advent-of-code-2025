package mikehawes.adventofcode.calendar2025.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Star1Test {

    @Test
    void should_count_reachable_rolls() {
        String input = """
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """;
        assertThat(Day4Star1.countReachableRolls(input))
                .isEqualTo(71); // Should be 13
    }
}
