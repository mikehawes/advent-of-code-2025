package mikehawes.adventofcode.calendar2025.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Star1Test {

    @Test
    void should_find_range_bounds_are_same_character_twice() {
        String range = "11-22";
        assertThat(Day2Star1.countInvalidIdsInRange(range))
                .isEqualTo(2);
    }

    @Test
    void should_find_two_characters_repeated_once_in_range() {
        String range = "998-1012";
        assertThat(Day2Star1.countInvalidIdsInRange(range))
                .isEqualTo(1);
    }
}
