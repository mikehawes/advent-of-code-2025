package mikehawes.adventofcode.calendar2025.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Star2Test {

    @Test
    void should_find_range_bounds_are_same_character_twice() {
        String range = "11-22";
        assertThat(Day2Star2.sumInvalidIdsInRanges(range))
                .isEqualTo(11 + 22);
    }

    @Test
    void should_find_two_repeats_in_range() {
        String range = "95-115";
        assertThat(Day2Star2.sumInvalidIdsInRanges(range))
                .isEqualTo(99 + 111);
    }
}
