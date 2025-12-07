package mikehawes.adventofcode.calendar2025.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Star2Test {

    @Test
    void should_count_removable_rolls() {
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
        assertThat(Day4Star2.countRemovableRolls(input))
                .isEqualTo(43);
    }
}
