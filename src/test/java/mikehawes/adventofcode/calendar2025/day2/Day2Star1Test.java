package mikehawes.adventofcode.calendar2025.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Star1Test {

    @Test
    void should_find_range_bounds_are_same_character_twice() {
        String range = "11-22";
        assertThat(Day2Star1.countInvalidIdsInRanges(range))
                .isEqualTo(2);
    }

    @Test
    void should_find_two_characters_repeated_once_in_range() {
        String range = "95-115";
        assertThat(Day2Star1.countInvalidIdsInRanges(range))
                .isEqualTo(1);
    }

    @Test
    void should_sum_two_invalid_id_counts() {
        String ranges = "11-22,95-115";
        assertThat(Day2Star1.countInvalidIdsInRanges(ranges))
                .isEqualTo(3);
    }
}
