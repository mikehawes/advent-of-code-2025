package mikehawes.adventofcode.calendar2025.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day9Star2Test {

    @Test
    void should_find_largest_rectangle() {
        String input = """
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """;
        assertThat(Day9Star2.largestRectangleArea(input))
                .isEqualTo(24);
    }
}
