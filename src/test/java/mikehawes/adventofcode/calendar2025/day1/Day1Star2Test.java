package mikehawes.adventofcode.calendar2025.day1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Star2Test {

    @Test
    void should_track_multiple_zeroes_in_one_move() {
        assertThat(Day1Star2.findPassword("R200"))
                .isEqualTo("2");
    }

    @Test
    void should_track_multiple_zeroes_ending_on_zero() {
        assertThat(Day1Star2.findPassword("R250"))
                .isEqualTo("3");
    }

    @Test
    void should_find_password_from_example_document() {
        String document = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """;

        assertThat(Day1Star2.findPassword(document))
                .isEqualTo("6");
    }
}
